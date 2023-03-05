/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.tools.version;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;


/**
 * Holds information for building the set of all {@code JavaElement}s found in a JAR file.
 * This class is used only the time needed for creating the set of elements, then discarded.
 * Every fields declared in this class are used only by the {@link JavaElement} constructors.
 *
 * <p><b>Usage example:</b></p>
 * <blockquote><code>
 * JavaElement[] elements = JavaElementCollector.collectAPIChanges(new Version("3.0.2"), new Version("3.1-M07"));
 * </code></blockquote>
 *
 * Note that this class indirectly uses hard-coded reference to the Unit API
 * (JSR-275, JSR-363 or JSR-385) determined from the GeoAPI version.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class JavaElementCollector {
    /**
     * The {@code @UML} annotation defined in the JAR files provided in the constructor.
     */
    final Class<? extends Annotation> umlAnnotation;

    /**
     * The {@code UML.identifier()} method in the UML annotation.
     */
    final Method umlIdentifier;

    /**
     * The {@code UML.obligation()} method in the UML annotation.
     */
    final Method umlObligation;

    /**
     * The list of elements found in the API.
     */
    final Set<JavaElement> elements;

    /**
     * Base type of code lists.
     */
    final Class<?> codeLists;

    /**
     * Collects the API definition in the given JAR file. This constructor will report the
     * differences only for the first JAR file in the give array. All other JAR files are
     * dependencies.
     *
     * @param jarFiles The JAR files which define the API.
     * @param hierarchy An optional map in which to store type hierarchy.
     *        If non-null, this map will be filled with (type, parent) entries.
     */
    private JavaElementCollector(final List<File> jarFiles, final Map<String,String> hierarchy)
            throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        final URL[] jarURLs = new URL[jarFiles.size()];
        for (int i=0; i<jarURLs.length; i++) {
            final File file = jarFiles.get(i);
            if (!file.isFile() || !file.canRead()) {
                throw new FileNotFoundException("File \"" + file + "\" not found or cannot be read.");
            }
            jarURLs[i] = file.toURI().toURL();
        }
        final URLClassLoader loader = new URLClassLoader(jarURLs, ClassLoader.getSystemClassLoader().getParent());
        umlAnnotation = Class.forName("org.opengis.annotation.UML", false, loader).asSubclass(Annotation.class);
        umlIdentifier = umlAnnotation.getMethod("identifier", (Class[]) null);
        umlObligation = umlAnnotation.getMethod("obligation", (Class[]) null);
        codeLists     = Class.forName("org.opengis.util.CodeList", false, loader);
        elements      = new LinkedHashSet<>(512);
        /*
         * At this point, all fields Shall be initialized. Now fill the
         * 'elements' set with all public and protected API that we can find.
         */
        final Map<String,JavaElement> packages = new HashMap<>();
        try (ZipInputStream file = new ZipInputStream(new FileInputStream(jarFiles.get(0)))) {
            ZipEntry entry;
            while ((entry = file.getNextEntry()) != null) {
                String classname = entry.getName();
                if (classname.endsWith(".class")) {
                    classname = classname.substring(0, classname.length() - 6).replace('/', '.');
                    final Class<?> type = Class.forName(classname, false, loader);
                    if (!type.isSynthetic()) {
                        final int modifiers = type.getModifiers();
                        final boolean isPublic = Modifier.isPublic(modifiers);
                        if (isPublic || Modifier.isProtected(modifiers)) {
                            final String packageName = type.getPackage().getName();
                            JavaElement container = packages.get(packageName);
                            if (container == null) {
                                container = new JavaElement(packageName);
                                packages.put(packageName, container);
                            }
                            final JavaElement element = new JavaElement(this, container, type, isPublic);
                            assert elements.contains(element);
                            if (hierarchy != null && element.type != null) {
                                hierarchy.put(type.getCanonicalName(), element.type);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the set of all elements in the given API.
     * This method infers the various dependency from the GeoAPI version.
     *
     * @param  artefact   The artefact name, either {@code "geoapi"} or {@code "geoapi-conformance"}.
     * @param  version    The GeoAPI version, as used in Maven artefacts.
     * @param  repository The root of Maven local repository.
     * @param  hiearchy   An optional map in which to store type hierarchy.
     *         If non-null, this map will be filled with (type, parent) entries.
     * @return the set of elements in the given GeoAPI version.
     */
    private static Set<JavaElement> collectAPI(String artefact, final Version version, final File repository,
            final Map<String,String> hierarchy)
            throws IOException, ClassNotFoundException, NoSuchMethodException,
                    IllegalAccessException, InvocationTargetException
    {
        /*
         * The "geoapi-conformance" module was named "conformance" in older GeoAPI versions.
         */
        if (version.major < 3 && artefact.equals("geoapi-conformance")) {
            artefact = "conformance";
        }
        /*
         * Older GeoAPI versions were using a dummy module for pending GeoAPI interfaces
         * required by the normative part of GeoAPI. Newer GeoAPI versions use the Maven
         * build helper plugins instead.
         */
        final boolean pendingIncludesCore = version.compareTo(Version.PENDING_INCLUDES_CORE) >= 0;
        /*
         * At this point, we collected the version-dependent information.
         * Now build the list.
         */
        final List<File> files = new ArrayList<>();
        files.add(new File(repository, version.getMavenArtefactPath(artefact)));
        if (artefact.equals("geoapi")) {
            if (!pendingIncludesCore) {
                files.add(new File(repository, version.getMavenArtefactPath("geoapi-dummy-pending")));
            }
        } else {
            final boolean dependsOnPending = version.isMilestone();
            if (!dependsOnPending || !pendingIncludesCore) {
                files.add(new File(repository, version.getMavenArtefactPath("geoapi")));
            }
            if (dependsOnPending || !pendingIncludesCore) {
                files.add(new File(repository, version.getMavenArtefactPath("geoapi-pending")));
            }
        }
        final List<Dependency> dependencies = new ArrayList<>(2);
        dependencies.add(Dependency.UNIT_OF_MEASURES);
        if (artefact.endsWith("conformance")) {
            dependencies.add(Dependency.JUNIT);
        }
        for (final Dependency dependency : dependencies) {
            for (final String path : dependency.pathInMavenRepository(version)) {
                files.add(new File(repository, path));
            }
        }
        return new JavaElementCollector(files, hierarchy).elements;
    }

    /**
     * Collects the API changes between the given GeoAPI versions.
     * The given version number shall match the version strings used with Maven artefacts.
     *
     * @param  artefact    The artefact name, either {@code "geoapi"} or {@code "geoapi-conformance"}.
     * @param  oldVersion  The old GeoAPI version.
     * @param  newVersion  The new GeoAPI version.
     * @return the API changes.
     */
    static JavaElement[] collectAPIChanges(final String artefact,
            final Version oldVersion, final Version newVersion)
            throws IOException, ClassNotFoundException, NoSuchMethodException,
                    IllegalAccessException, InvocationTargetException
    {
        final File repository = new File(System.getProperty("user.home"), ".m2/repository");
        final Map<String,String> hierarchy = new HashMap<>();
        final Set<JavaElement> oldAPI = collectAPI(artefact, oldVersion, repository, null);
        final Set<JavaElement> newAPI = collectAPI(artefact, newVersion, repository, hierarchy);
        for (final Iterator<JavaElement> it = oldAPI.iterator(); it.hasNext();) {
            final JavaElement old = it.next();
            if (newAPI.remove(old)) {
                it.remove();
            }
        }
        /*
         * For elements which exist both in old and new API, compute the changes
         * and remove the element from the set of old API.
         */
        for (final Iterator<JavaElement> it = newAPI.iterator(); it.hasNext();) {
            final JavaElement element = it.next();
            element.computeChanges(oldAPI.iterator());
            if (element.isDeprecated && element.changes() == null) {
                it.remove();        // Ignore new deprecated elements, since they shall be removed before the release.
            }
        }
        /*
         * If a "removed" element actually moved to a parent type, do not consider that change as a removal.
         * Only after that, mark all remaining elements as removed.
         */
mark:   for (final Iterator<JavaElement> it = oldAPI.iterator(); it.hasNext();) {
            final JavaElement element = it.next();
            if (element.kind.isMember) {
                final String name = element.javaName;
                for (final JavaElement newElement : newAPI) {
                    if (newElement.kind.isMember && name.equals(newElement.javaName)) {
                        /*
                         * Found a property of the same name. Verify if it is contained in a type
                         * which is the parent of the container of the old element.
                         */
                        final String newType = newElement.container.getClassName();
                        String type = element.container.getClassName();
                        do if (newType.equals(type)) {
                            it.remove();
                            continue mark;
                        } while ((type = hierarchy.get(type)) != null);
                    }
                }
            }
            element.markAsRemoved();
        }
        removeChildrenOfNewOrDeleted(oldAPI);
        removeChildrenOfNewOrDeleted(newAPI);
        /*
         * Scans the list of changes for changes that are identified as removal of the UML annotation.
         * There is not many of such removal, and most of them are actually the UML annotation moving
         * to another method. Thi block tries to find where the UML annotation moved.
         */
        for (final JavaElement element : newAPI) {
            if (element.kind.isMember) {
                final JavaElementChanges changes = element.changes();
                if (changes != null && changes.isUmlRemoved()) {
                    for (final JavaElement other : newAPI) {
                        if (JavaElement.isSameElement(element.container, other.container)) {
                            changes.markIfUmlMovedTo(other);
                        }
                    }
                }
            }
        }
        /*
         * Create a consolidated list of old and new API.
         */
        int index = newAPI.size();
        final JavaElement[] elements = newAPI.toArray(new JavaElement[index + oldAPI.size()]);
        for (final JavaElement element : oldAPI) {
            element.markAsRemoved();
            elements[index++] = element;
        }
        assert index == elements.length;
        Arrays.sort(elements);
        return elements;
    }

    /**
     * For any new or deleted elements, remove all elements that are enclosed in the removed elements.
     * For example if a new interface has been added, we will not report all methods in that interface
     * as new elements.
     */
    private static void removeChildrenOfNewOrDeleted(final Set<JavaElement> elements) {
        // We have to copy the elements in a temporary array for protecting them from changes.
        for (final JavaElement container : elements.toArray(JavaElement[]::new)) {
            final JavaElementChanges changes = container.changes();
            if (changes == null || changes.isRemoved) {
                for (final Iterator<JavaElement> it = elements.iterator(); it.hasNext();) {
                    final JavaElement child = it.next();
                    if (child.container == container) {
                        it.remove();
                    }
                }
            }
        }
    }
}
