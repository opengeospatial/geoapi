/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.attribute.BasicFileAttributes;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.opengis.util.CodeList;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Verify the list of members in each {@link Content} enumeration value.
 * This test compares the content with the classes found on the {@code geoapi/target/classes} directory.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ContentTest implements FileVisitor<Path> {
    /**
     * Suffix of {@code package-info} and {@code module-info} classes.
     */
    private static final String INFO_SUFFIX = "-info";

    /**
     * Name of the class to load. Components are added in this buffer as we walk down the directory tree.
     * Also used during the tests (after class loading) for listing the erroneous classes, if any.
     */
    private final StringBuilder buffer;

    /**
     * All interfaces, code lists, enumerations and exceptions found while walking in the directories.
     */
    private final Set<Class<?>>[] types;

    /**
     * Types to ignore. Only public types that are not annotations need to be declared here.
     */
    private final Set<Class<?>> ignoreTypes;

    /**
     * Dependencies to ignore because of circularity. Keys are the dependents (the class having a circular dependency)
     * and values are the dependencies. For example, ({@code BoundingPolygon.class}, {@code Geometry.class}) means that
     * the {@code BoundingPolygon} interface has a dependency toward {@code Geometry}, but we want to allow having the
     * geometry interface defined only later.
     *
     * <p>Most circular dependencies do not need to be declared in this map because {@code ContentTest} already has a
     * mechanism for detecting circular dependencies. However, in some cases the mechanism is not sufficient or we want
     * a different order.</p>
     */
    private final Map<Class<?>, List<Class<?>>> skipDependencies;

    /**
     * Creates a new test case.
     */
    @SuppressWarnings({"rawtypes", "unchecked", "deprecation"})
    public ContentTest() {
        buffer = new StringBuilder();
        types = new Set[Content.values().length];
        for (int i=0; i<types.length; i++) {
            types[i] = new HashSet<>();
        }
        ignoreTypes = new HashSet<>(List.of(
                org.opengis.annotation.Specification.class,
                org.opengis.annotation.Stereotype.class,
                org.opengis.annotation.ResourceBundles.class,
                org.opengis.util.CodeList.class));

        skipDependencies = new HashMap<>(20);
        skipDependency(org.opengis.metadata.citation.Party.class,                 org.opengis.metadata.Identifier.class);
        skipDependency(org.opengis.metadata.citation.Organisation.class,          org.opengis.metadata.Identifier.class);
        skipDependency(org.opengis.metadata.citation.Individual.class,            org.opengis.metadata.Identifier.class);
        skipDependency(org.opengis.metadata.citation.Responsibility.class,        org.opengis.metadata.extent.Extent.class);
        skipDependency(org.opengis.metadata.citation.ResponsibleParty.class,      org.opengis.metadata.extent.Extent.class);
        skipDependency(org.opengis.metadata.extent.BoundingPolygon.class,         org.opengis.geometry.Geometry.class);
        skipDependency(org.opengis.metadata.extent.VerticalExtent.class,          org.opengis.referencing.crs.VerticalCRS.class);
        skipDependency(org.opengis.metadata.citation.Organisation.class,          org.opengis.metadata.identification.BrowseGraphic.class);
        skipDependency(org.opengis.metadata.citation.Citation.class,              org.opengis.metadata.identification.BrowseGraphic.class);
        skipDependency(org.opengis.metadata.constraint.LegalConstraints.class,    org.opengis.metadata.identification.BrowseGraphic.class);
        skipDependency(org.opengis.metadata.constraint.SecurityConstraints.class, org.opengis.metadata.identification.BrowseGraphic.class);
        skipDependency(org.opengis.metadata.quality.CoverageResult.class,         org.opengis.metadata.spatial.SpatialRepresentation.class);
    }

    /**
     * Adds a dependency to skip.
     *
     * @param dependent   interface containing the dependency.
     * @param dependency  the dependency to skip.
     */
    private void skipDependency(final Class<?> dependent, final Class<?> dependency) {
        skipDependencies.compute(dependent, (k,v) -> {
            if (v == null) {
                v = new ArrayList<>(2);
            }
            v.add(dependency);
            return v;
        });
    }

    /**
     * Verifies all {@link Content} enumerations.
     *
     * @throws IOException if an error occurred while scanning the directory content.
     */
    @Test
    public void verify() throws IOException {
        Files.walkFileTree(SourceGenerator.targetDirectory().resolve("org"), this);
        final Set<Class<?>> dependencies         = new HashSet<>();
        final Set<Class<?>> dependencyChecks     = new HashSet<>();
        final Set<Class<?>> circularDependencies = new HashSet<>();
        for (final Content category : new Content[] {Content.INTERFACES, Content.CODE_LISTS, Content.ENUMERATIONS, Content.EXCEPTIONS}) {
            buffer.setLength(0);
            final Set<Class<?>> expected = types[category.ordinal()];
            for (final Class<?> actual : category.types()) {
                /*
                 * If the current type is not in the list of expected types, format an error message but do not
                 * fail now. We will declare a test failure only after we got the full list of unexpected types.
                 */
                if (!expected.remove(actual)) {
                    appendToErrorMessage(actual);
                }
                /*
                 * Verifies that all dependencies where declared before the current class, ignoring circular dependencies.
                 * If this test fails, it will override the error message that the previous check may have prepared.
                 */
                findDependencies(actual, expected, dependencies, false);
                final List<Class<?>> toIgnoreList = skipDependencies.remove(actual);
                if (toIgnoreList != null) {
                    for (final Class<?> toIgnore : toIgnoreList) {
                        if (!dependencies.remove(toIgnore)) {
                            fail("skipDependencies does not need to contain an entry for " + actual + " = " + toIgnore);
                        }
                    }
                }
                if (!dependencies.isEmpty()) {
                    assertTrue(dependencyChecks.add(actual));
                    assertTrue(dependencyChecks.addAll(dependencies));
                    for (final Iterator<Class<?>> it = dependencies.iterator(); it.hasNext();) {
                        final Class<?> c = it.next();
                        findDependencies(c, dependencyChecks, circularDependencies, true);
                        if (circularDependencies.contains(actual)) {
                            it.remove();
                        }
                        circularDependencies.clear();
                    }
                    dependencyChecks.clear();
                    if (!dependencies.isEmpty()) {
                        buffer.setLength(0);
                        buffer.append("Following dependencies should by declared before ").append(actual).append(':').append(System.lineSeparator());
                        for (final Class<?> c : dependencies) {
                            appendToErrorMessage(c);
                        }
                        fail(buffer.toString());
                    }
                }
            }
            /*
             * At this point we finished all tests. If we have found unexpected types, the error message has
             * been deferred until this point. We can report the failure now.
             */
            if (buffer.length() != 0) {
                buffer.insert(0, "Unexpected classes in " + category + ':' + System.lineSeparator());
                fail(buffer.toString());
            }
            /*
             * Report missing types, ignoring deprecated ones.
             */
            if (!expected.isEmpty()) {
                boolean hasNonDeprecated = false;
                buffer.append("Missing classes in ").append(category).append(':').append(System.lineSeparator());
                for (final Class<?> c : expected) {
                    if (!c.isAnnotationPresent(Deprecated.class)) {
                        hasNonDeprecated = true;
                        appendToErrorMessage(c);
                    }
                }
                if (hasNonDeprecated) {
                    fail(buffer.toString());
                }
            }
            /*
             * A failure in this last check would not be an error in Content.java,
             * but rather a possible issue in this ContentTest.java.
             */
            if (!skipDependencies.isEmpty()) {
                fail("skipDependencies contains unnecessary entries: " + skipDependencies);
            }
        }
    }

    /**
     * Stores in the given {@code dependencies} set all interfaces, parent class, parameter type or return type
     * used by the given type which is also in the {@code remaining} set. This is used for verifying that all
     * dependencies are declared before the given type.
     *
     * @param  type          the type to verify.
     * @param  remaining     the remaining type that has not yet been processed.
     * @param  dependencies  where to store any dependency found.
     * @param  deep          {@code true} for performing a deep search (by checking the dependencies of dependencies).
     */
    private static void findDependencies(Class<?> type, final Set<Class<?>> remaining, final Set<Class<?>> dependencies, final boolean deep) {
        do {
            if (remaining.contains(type)) {
                dependencies.add(type);
            }
            for (final Field m : type.getDeclaredFields()) {
                final Class<?> mt = Content.typeOf(m);
                if (remaining.contains(mt) && dependencies.add(mt) && deep) {
                    findDependencies(mt, remaining, dependencies, deep);
                }
            }
            for (final Method m : type.getDeclaredMethods()) {
                for (final Class<?> mt : m.getParameterTypes()) {
                    if (remaining.contains(mt) && dependencies.add(mt) && deep) {
                        findDependencies(mt, remaining, dependencies, deep);
                    }
                }
                final Class<?> mt = Content.typeOf(m);
                if (remaining.contains(mt) && dependencies.add(mt) && deep) {
                    findDependencies(mt, remaining, dependencies, deep);
                }
            }
            for (final Class<?> it : type.getInterfaces()) {
                findDependencies(it, remaining, dependencies, deep);
            }
            type = type.getSuperclass();
        } while (type != null);
    }

    /**
     * Invoked when entering in a directory. This method is public as an implementation side-effect
     * for scanning the Maven target directory content; it should not be invoked directly.
     *
     * @param  dir    the directory we are about to scan.
     * @param  attrs  ignored.
     * @return {@link FileVisitResult#CONTINUE}.
     * @throws IOException if an I/O error occurred.
     */
    @Override
    public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
        buffer.append(dir.getFileName()).append('.');
        return FileVisitResult.CONTINUE;
    }

    /**
     * Invoked when exiting a directory. This method is public as an implementation side-effect
     * for scanning the Maven target directory content; it should not be invoked directly.
     *
     * @param  dir    the directory we finished to scan.
     * @param  error  the error that occurred, or {@code null} if none.
     * @return {@link FileVisitResult#CONTINUE}.
     * @throws IOException if an I/O error occurred.
     */
    @Override
    public FileVisitResult postVisitDirectory(final Path dir, final IOException error) throws IOException {
        if (error != null) throw error;
        buffer.setLength(buffer.lastIndexOf(".", buffer.length() - 2) + 1);
        return FileVisitResult.CONTINUE;
    }

    /**
     * Invoked for each files in a directory. This method processes {@code .class} files, but not a {@code *-info} classes.
     * This method is public as an implementation side-effect for scanning the Maven target directory content; it should not
     * be invoked directly.
     *
     * @param  file  the file to process.
     * @param  attrs ignored.
     * @return {@link FileVisitResult#CONTINUE}.
     * @throws IOException if an I/O error occurred.
     */
    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        if (!Files.isHidden(file)) {
            String name = file.getFileName().toString();
            if (name.endsWith(SourceGenerator.CLASS_SUFFIX)) {
                name = name.substring(0, name.length() - SourceGenerator.CLASS_SUFFIX.length());
                if (!name.endsWith(INFO_SUFFIX)) {
                    final int length = buffer.length();
                    final String classname = buffer.append(name).toString();
                    final Class<?> c;
                    try {
                        c = Class.forName(classname);
                    } catch (ClassNotFoundException e) {
                        fail(e.toString());
                        return FileVisitResult.TERMINATE;
                    }
                    if (Modifier.isPublic(c.getModifiers()) && !c.isAnnotation() && !ignoreTypes.remove(c)) {
                        final Content category;
                        if (c.isInterface())                          category = Content.INTERFACES;
                        else if (CodeList .class.isAssignableFrom(c)) category = Content.CODE_LISTS;
                        else if (Enum     .class.isAssignableFrom(c)) category = Content.ENUMERATIONS;
                        else if (Exception.class.isAssignableFrom(c)) category = Content.EXCEPTIONS;
                        else {
                            fail("No category for " + c);
                            return FileVisitResult.TERMINATE;
                        }
                        final Set<Class<?>> members = types[category.ordinal()];
                        assertTrue(classname, members.add(c));                      // Fails if a class is declared twice.
                    }
                    buffer.setLength(length);
                }
            }
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * Invokes when an error occurred while walking the tree.
     *
     * @param  file   ignored.
     * @param  error  the error that occurred.
     * @return never return.
     * @throws IOException the given {@code error}.
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException error) throws IOException {
        throw error;
    }

    /**
     * Adds a bullet point with the name of the given class. Used for formatting error message.
     * The caller is responsible to invoke this method only in context where {@link #buffer} is
     * available for building error messages (and not used for something else).
     */
    private void appendToErrorMessage(final Class<?> type) {
        buffer.append("  • ").append(type.getCanonicalName()).append(System.lineSeparator());
    }
}
