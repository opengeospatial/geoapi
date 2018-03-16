/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2018 Open Geospatial Consortium, Inc.
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
package org.opengis;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Arrays;
import java.io.File;
import java.io.FileFilter;
import java.net.URI;
import java.net.URISyntaxException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.opengis.util.CodeList;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;


/**
 * Verify the list of members in each {@link Content} enumeration value.
 * This class is designated for working with the Maven directory layout.
 * If it does not recognize that layout, then the test is skipped.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final strictfp class ContentTest implements FileFilter {
    /**
     * {@code true} if this test includes some classes from the {@code geoapi-pending} module.
     * This is never used when building with Maven.
     */
    private static final boolean PENDING = false;

    /**
     * Suffix of compiled class files.
     */
    private static final String CLASS_SUFFIX = ".class";

    /**
     * Suffix of {@code package-info} and {@code module-info} classes.
     */
    private static final String INFO_SUFFIX = "-info";

    /**
     * The Maven {@code target} directory found by the last call to {@link #listClasses(Class)}.
     */
    private static File targetDirectory;

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
     * and values are the dependencies. For example ({@code BoundingPolygon.class}, {@code Geometry.class}) means that
     * the {@code BoundingPolygon} interface has a dependency toward {@code Geometry}, but we want to allow having the
     * geometry interface defined only later.
     *
     * <p>Most circular dependencies do not need to be declared in this map because {@code ContentTest} already has a
     * mechanism for detecting circular dependencies. However in some cases the mechanism is not sufficient or we want
     * a different order.</p>
     */
    private final Map<Class<?>,Class<?>> skipDependencies;

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
        ignoreTypes = new HashSet<>(Arrays.asList(
                org.opengis.annotation.ComplianceLevel.class,
                org.opengis.annotation.Specification.class,
                org.opengis.annotation.Stereotype.class,
                org.opengis.util.CodeList.Filter.class,
                org.opengis.util.CodeList.class));

        skipDependencies = new HashMap<>(16);
        assertNull(skipDependencies.put(org.opengis.referencing.ReferenceIdentifier.class,         org.opengis.metadata.citation.Citation.class));
        assertNull(skipDependencies.put(org.opengis.metadata.extent.BoundingPolygon.class,         org.opengis.geometry.Geometry.class));
        assertNull(skipDependencies.put(org.opengis.metadata.extent.VerticalExtent.class,          org.opengis.referencing.crs.VerticalCRS.class));
        assertNull(skipDependencies.put(org.opengis.metadata.citation.Organisation.class,          org.opengis.metadata.identification.BrowseGraphic.class));
        assertNull(skipDependencies.put(org.opengis.metadata.citation.Citation.class,              org.opengis.metadata.identification.BrowseGraphic.class));
        assertNull(skipDependencies.put(org.opengis.metadata.constraint.LegalConstraints.class,    org.opengis.metadata.identification.BrowseGraphic.class));
        assertNull(skipDependencies.put(org.opengis.metadata.constraint.SecurityConstraints.class, org.opengis.metadata.identification.BrowseGraphic.class));
        assertNull(skipDependencies.put(org.opengis.metadata.quality.CoverageResult.class,         org.opengis.metadata.spatial.SpatialRepresentation.class));
        if (PENDING) {
            assertNull(skipDependencies.put(org.opengis.geometry.Geometry.class,                   org.opengis.referencing.operation.MathTransform.class));
            assertNull(skipDependencies.put(org.opengis.geometry.primitive.Point.class,            org.opengis.referencing.operation.MathTransform.class));
        }
    }

    /**
     * Verifies all {@link Content} enumerations.
     */
    @Test
    public void verify() {
        listClasses(targetMavenDirectory());
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
                final Class<?> toIgnore = skipDependencies.remove(actual);
                if (toIgnore != null && !dependencies.remove(toIgnore)) {
                    fail("skipDependencies does not need to contain an entry for " + actual + " = " + toIgnore);
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
     * Finds the Maven module containing the given class, then returns the target directory of that module.
     * Only the Maven {@code target} directory containing the {@code Metadata} class is scanned;
     * this is not necessarily the full classpath (which is not desired anyway since we don't want
     * to include pending interfaces, test classes, <i>etc.</i>).
     */
    static synchronized File targetDirectory() {
        File dir = targetDirectory;
        if (dir == null) {
            final Class<?> sample = org.opengis.metadata.Metadata.class;        // Any class expected to exist in the target directory.
            String pathname = sample.getName();
            int s = pathname.lastIndexOf('.');
            String name = pathname.substring(s+1) + CLASS_SUFFIX;
            final URI uri;
            try {
                uri = sample.getResource(name).toURI();
            } catch (URISyntaxException e) {
                fail("Can not create a URI for the " + pathname + " class:\n" + e);
                return null;
            }
            dir = new File(uri);
            for (;;) {                                                          // Break condition is in the middle of the loop.
                assertEquals("Unexpected name.", name, dir.getName());
                dir = dir.getParentFile();
                assertNotNull("Missing parent directory.", dir);
                if (s < 0) break;
                pathname = pathname.substring(0, s);
                s = pathname.lastIndexOf('.');
                name = pathname.substring(s+1);
            }
dir = new File("/Users/desruisseaux/Projets/GeoAPI/master/geoapi/target/classes");
            targetDirectory = dir;
        }
        return dir;
    }

    /**
     * Gets the {@linkplain #targetDirectory() target directory} and skip the test if the directory is not the Maven one.
     * This is used for skipping the test if it is run from an IDE that does not use Maven.
     */
    private static File targetMavenDirectory() {
        final File dir = targetDirectory();
        if (dir != null && dir.getName().equals("classes")) {
            final File parent = dir.getParentFile();
            if (parent != null && parent.getName().equals("target")) {
                return dir;
            }
        }
        assumeTrue(dir + " is not a Maven target directory.", false);
        return dir;
    }

    /**
     * Scans the given directory for {@code .class} files, and adds each class found in the given set.
     * This method invokes itself recursively.
     */
    private void listClasses(final File directory) {
        final int length = buffer.length();
        for (final File file : directory.listFiles(this)) {
            buffer.append(file.getName());
            if (file.isDirectory()) {
                buffer.append('.');
                listClasses(file);
            } else {
                buffer.setLength(buffer.length() - CLASS_SUFFIX.length());
                final String classname = buffer.toString();
                final Class<?> c;
                try {
                    c = Class.forName(classname);
                } catch (ClassNotFoundException e) {
                    fail(e.toString());
                    continue;
                }
                if (Modifier.isPublic(c.getModifiers()) && !c.isAnnotation() && !ignoreTypes.remove(c)) {
                    final Content category;
                    if (c.isInterface())                          category = Content.INTERFACES;
                    else if (CodeList .class.isAssignableFrom(c)) category = Content.CODE_LISTS;
                    else if (Enum     .class.isAssignableFrom(c)) category = Content.ENUMERATIONS;
                    else if (Exception.class.isAssignableFrom(c)) category = Content.EXCEPTIONS;
                    else {
                        fail("No category for " + c);
                        continue;
                    }
                    final Set<Class<?>> members = types[category.ordinal()];
                    assertTrue(classname, members.add(c));                      // Fails if a class is declared twice.
                }
            }
            buffer.setLength(length);
        }
    }

    /**
     * Returns {@code true} if the given file is a directory or a {@code .class} file, but not a {@code *-info} class.
     * This method is public as an implementation side-effect for filtering the Maven target directory content.
     * This method should not be invoked directly.
     *
     * @param  file  the file to test.
     * @return {@code true} if the given file is a directory or a class file.
     */
    @Override
    public boolean accept(final File file) {
        if (!file.isHidden()) {
            if (file.isDirectory()) {
                return true;
            }
            if (file.isFile()) {
                final String filename = file.getName();
                if (filename.endsWith(CLASS_SUFFIX)) {
                    return !filename.regionMatches(
                            filename.length() - (CLASS_SUFFIX.length() + INFO_SUFFIX.length()), INFO_SUFFIX, 0, INFO_SUFFIX.length());
                }
            }
        }
        return false;
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
