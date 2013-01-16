/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2012 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.tools.version;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.File;
import java.io.FileInputStream;
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
 * <p>
 * <b>Usage example:</b>
 * <blockquote><code>
 * JavaElement[] elements = JavaElementCollector.collectAPIChanges(new Version("3.0.0"), new Version("3.1-M04"));
 * </code></blockquote>
 *
 * Note that this class indirectly uses hard-coded reference to the Unit API
 * (JSR-275 or {@code org.unitsofmeasure}) determined from the GeoAPI version.
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
     */
    private JavaElementCollector(final File... jarFiles) throws IOException, ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        final URL[] jarURLs = new URL[jarFiles.length];
        for (int i=0; i<jarFiles.length; i++) {
            jarURLs[i] = jarFiles[i].toURI().toURL();
        }
        final URLClassLoader loader = new URLClassLoader(jarURLs, ClassLoader.getSystemClassLoader().getParent());
        umlAnnotation = Class.forName("org.opengis.annotation.UML", false, loader).asSubclass(Annotation.class);
        umlIdentifier = umlAnnotation.getMethod("identifier", (Class[]) null);
        umlObligation = umlAnnotation.getMethod("obligation", (Class[]) null);
        codeLists     = Class.forName("org.opengis.util.CodeList", false, loader);
        elements      = new LinkedHashSet<JavaElement>(512);
        /*
         * At this point, all fields Shall be initialized. Now fill the
         * 'elements' set with all public and protected API that we can find.
         */
        final Map<String,JavaElement> packages = new HashMap<String,JavaElement>();
        final ZipInputStream file = new ZipInputStream(new FileInputStream(jarFiles[0]));
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
                        JavaElement parent = packages.get(packageName);
                        if (parent == null) {
                            parent = new JavaElement(packageName);
                            packages.put(packageName, parent);
                        }
                        final JavaElement element = new JavaElement(this, parent, type, isPublic);
                        assert elements.contains(element);
                    }
                }
            }
        }
        file.close();
    }

    /**
     * Returns the set of all elements in the given API.
     * This method infers the "Units of measurement" dependency from the GeoAPI version.
     *
     * @param  artefact   The artefact name, either {@code "geoapi"} or {@code "geoapi-conformance"}.
     * @param  version    The GeoAPI version, as used in Maven artefacts.
     * @param  repository The root of Maven local repository.
     * @return The set of elements in the given GeoAPI version.
     */
    private static Set<JavaElement> collectAPI(final String artefact, final Version version, final File repository)
            throws IOException, ClassNotFoundException, NoSuchMethodException,
                    IllegalAccessException, InvocationTargetException
    {
        final File[] files;
        if (artefact.equals("geoapi")) {
            files = new File[] {
                new File(repository, version.getMavenArtefactPath(artefact)),
                new File(repository, Dependency.UNIT_OF_MEASURES.pathInMavenRepository(version))
            };
        } else {
            files = new File[] {
                new File(repository, version.getMavenArtefactPath(artefact)),
                new File(repository, version.getMavenArtefactPath()),
                new File(repository, Dependency.UNIT_OF_MEASURES.pathInMavenRepository(version)),
                new File(repository, Dependency.JUNIT.pathInMavenRepository(version))
            };
        }
        return new JavaElementCollector(files).elements;
    }

    /**
     * Collects the API changes between the given GeoAPI versions.
     * The given version number shall match the version strings used with Maven artefacts.
     *
     * @param  artefact    The artefact name, either {@code "geoapi"} or {@code "geoapi-conformance"}.
     * @param  oldVersion  The old GeoAPI version.
     * @param  newVersion  The new GeoAPI version.
     * @return The API changes.
     */
    static JavaElement[] collectAPIChanges(final String artefact,
            final Version oldVersion, final Version newVersion)
            throws IOException, ClassNotFoundException, NoSuchMethodException,
                    IllegalAccessException, InvocationTargetException
    {
        final File repository = new File(System.getProperty("user.home"), ".m2/repository");
        final Set<JavaElement> oldAPI = collectAPI(artefact, oldVersion, repository);
        final Set<JavaElement> newAPI = collectAPI(artefact, newVersion, repository);
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
            JavaElement element = it.next();
            element.computeChanges(oldAPI.iterator());
            if (element.isDeprecated && element.changes() == null) {
                it.remove(); // Ignore new deprecated elements, since they shall be removed before the release.
            }
        }
        /*
         * For any new elements, remove all children of that elements.
         * We have to copy the elements in a temporary array for protecting them from changes.
         */
        for (final JavaElement parent : newAPI.toArray(new JavaElement[newAPI.size()])) {
            for (final Iterator<JavaElement> it = newAPI.iterator(); it.hasNext();) {
                final JavaElement child = it.next();
                if (child.parent == parent) {
                    it.remove();
                }
            }
        }
        /*
         * Create a consolidated list of old an new API.
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
}
