/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2014 Open Geospatial Consortium, Inc.
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

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.io.IOException;
import java.io.Closeable;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Type;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;


/**
 * Verifies the compatibility of a GeoAPI JAR file compared to its previous version.
 *
 * <p>This test class also has a {@link #main(String[])} method, which only lists the methods having
 * an incompatible change. This main method is used for generating the list of incompatible changes
 * to be expected in the next major GeoAPI release.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class CompatibilityTest {
    /**
     * Filename extension of class files.
     */
    private static final String CLASS_EXT = ".class";

    /**
     * The GeoAPI version used as a reference.
     */
    private final String oldVersion;

    /**
     * The GeoAPI version to compare against the reference one.
     */
    private final String newVersion;

    /**
     * The root of Maven repository.
     */
    private final File mavenRepository;

    /**
     * Classes which were intentionally deleted. Such classes should be very rare (we usually try to deprecate
     * classes instead), but may happen when it was not possible to retrofit the deprecated class as a subtype
     * of the non-deprecated class.
     */
    private final Set<String> deletedClasses;

    /**
     * Incompatible changes which are accepted for this release.
     * For minor releases we should have very few of them - only if the previous type was clearly wrong.
     * For major releases the amount of incompatible changes may be greater.
     */
    private final Set<IncompatibleChange> acceptedIncompatibleChanges;

    /**
     * All incompatible changes found. A non-empty list after test completion will be considered
     * a test failure.
     */
    private final List<IncompatibleChange> incompatibleChanges;

    /**
     * Creates a new API comparator.
     */
    public CompatibilityTest() {
        this("3.0.0", "3.1-SNAPSHOT");
    }

    /**
     * Creates a new API comparator between the given versions of GeoAPI.
     */
    private CompatibilityTest(final String oldVersion, final String newVersion) {
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;
        mavenRepository = new File(System.getProperty("user.home"), ".m2/repository");
        if (newVersion.startsWith("3.1")) {
            deletedClasses = Collections.emptySet();
            acceptedIncompatibleChanges = IncompatibleChange.for31();
        } else if (newVersion.startsWith("4.0")) {
            deletedClasses = Collections.singleton("org.opengis.metadata.Obligation");
            acceptedIncompatibleChanges = IncompatibleChange.for40();
        } else {
            throw new IllegalStateException("Unsupported version: " + newVersion);
        }
        incompatibleChanges = new ArrayList<IncompatibleChange>();
    }

    /**
     * Verifies that all changes compared to the latest GeoAPI release are compatible changes.
     *
     * @throws IOException if an error occurred while reading a JAR file.
     * @throws ClassNotFoundException if a class that existed in the previous GeoAPI release
     *         has not been found in the new release.
     * @throws NoSuchMethodException if a method that existed in the previous GeoAPI release
     *         has not been found in the new release.
     */
    @Test
    public void verifyGeoAPI() throws IOException, ClassNotFoundException, NoSuchMethodException {
        createIncompatibleChangesList();
        assertNoIncompatibility();
    }

    /**
     * Verifies that all changes between {@link #oldVersion} and {@link #newVersion} are compatible changes.
     * If any incompatible change is found, it will be added to the {@link #incompatibleChanges} list.
     */
    private void createIncompatibleChangesList() throws IOException, ClassNotFoundException, NoSuchMethodException {
        final File dependency = new File(mavenRepository, "javax/measure/jsr-275/0.9.3/jsr-275-0.9.3.jar");
        assumeTrue("Required dependency not found: " + dependency, dependency.isFile());
        createIncompatibleChangesList(
                new File(mavenRepository, "org/opengis/geoapi/" + oldVersion + "/geoapi-" + oldVersion + ".jar"),
                new File(mavenRepository, "org/opengis/geoapi/" + newVersion + "/geoapi-" + newVersion + ".jar"),
                dependency.toURI().toURL());
    }

    /**
     * Verifies that all changes between the two given GeoAPI JAR files are compatible changes.
     * If any incompatible change is found, it will be added to the {@link #incompatibleChanges} list.
     */
    private void createIncompatibleChangesList(final File oldFile, final File newFile, final URL dependency)
            throws IOException, ClassNotFoundException, NoSuchMethodException
    {
        assumeTrue("GeoAPI " + oldVersion + " not in Maven repository.", oldFile.isFile());
        assumeTrue("GeoAPI " + newVersion + " not in Maven repository.", newFile.isFile());
        final ClassLoader parent = CompatibilityTest.class.getClassLoader().getParent();
        final ClassLoader oldAPI = new URLClassLoader(new URL[] {oldFile.toURI().toURL(), dependency}, parent);
        final ClassLoader newAPI = new URLClassLoader(new URL[] {newFile.toURI().toURL(), dependency}, parent);
        for (final String className : listClasses(oldFile)) {
            final Class<?> oldClass = Class.forName(className, false, oldAPI);
            if (!Modifier.isPublic(oldClass.getModifiers())) {
                continue; // Skip non-public classes.
            }
            if (deletedClasses.contains(className)) {
                continue; // Skip intentionally deleted classes.
            }
            final Class<?> newClass = Class.forName(className, false, newAPI);
            /*
             * At this point we have a class from the previous GeoAPI release (oldClass) and its new version.
             * Now compare all public methods. We skip protected methods for simplicity, since we perform our
             * checks using Class.getMethod (we can not use Class.getDeclaredMethod because we want to search
             * in parent classes if needed). Note that arguments and return values which are GeoAPI types are
             * represented by different Class instances between the two GeoAPI version, so we need to convert
             * them using their class name.
             */
            for (final Method oldMethod : oldClass.getDeclaredMethods()) {
                if (!Modifier.isPublic(oldMethod.getModifiers())) {
                    continue; // Skip non-public methods.
                }
                final String methodName = oldMethod.getName();
                final Class<?>[] paramTypes = oldMethod.getParameterTypes();
                for (int i=0; i<paramTypes.length; i++) {
                    if (!paramTypes[i].isPrimitive()) {
                        // GeoAPI types are not represented by the same Class instances.
                        paramTypes[i] = Class.forName(paramTypes[i].getName(), false, newAPI);
                    }
                }
                final Method newMethod = newClass.getMethod(methodName, paramTypes);
                assertArrayEquals(methodName, paramTypes, newMethod.getParameterTypes()); // Paranoiac check (should never fail).
                /*
                 * Compare generic arguments (if any). We require an exact match,
                 * including for parameterized types.
                 */
                final Type[] oldGPT = oldMethod.getGenericParameterTypes();
                final Type[] newGPT = newMethod.getGenericParameterTypes();
                assertEquals(methodName, oldGPT.length, newGPT.length); // Paranoiac check (should never fail).
                for (int i=0; i<oldGPT.length; i++) {
                    final String oldType = oldGPT[i].toString(); // TODO: use getTypeName() on JDK8.
                    final String newType = newGPT[i].toString();
                    if (!newType.equals(oldType)) {
                        final String lineSeparator = System.getProperty("line.separator", "\n"); // TODO: Use System.lineSeparator() on JDK7.
                        fail("Incompatible change in argument #" + (i+1) + " of "
                                + className + '.' + methodName + ':' + lineSeparator
                                + "    (old) " + oldType + lineSeparator
                                + "    (new) " + newType + lineSeparator);
                    }
                }
                /*
                 * Compare generic return type, with a tolerance for a pre-defined set of incompatible changes.
                 * Any change not listed in the collection of accepted incompatible changes will be considered
                 * as an error.
                 */
                if (!oldMethod.isSynthetic()) {
                    final String oldType = oldMethod.getGenericReturnType().toString(); // TODO: use getTypeName() on JDK8.
                    final String newType = newMethod.getGenericReturnType().toString();
                    if (!newType.equals(oldType)) {
                        final IncompatibleChange change = new IncompatibleChange(className + '.' + methodName, oldType, newType);
                        if (!acceptedIncompatibleChanges.remove(change)) {
                            incompatibleChanges.add(change);
                        }
                    }
                }
            }
        }
        if (oldAPI instanceof Closeable) ((Closeable) oldAPI).close(); // For JDK7+ (not available on JDK6).
        if (newAPI instanceof Closeable) ((Closeable) newAPI).close();
        if (!acceptedIncompatibleChanges.isEmpty()) {
            fail("The collection of \"accepted incompatible changes\" has not been fully used.\n" +
                 "Is this test configured with the right collection? Remaining items are:\n" +
                 acceptedIncompatibleChanges);
        }
    }

    /**
     * Returns the name of all classes found in the given JAR file.
     */
    private static Collection<String> listClasses(final File file) throws IOException {
        final List<String> entries = new ArrayList<String>();
        final JarFile jar = new JarFile(file);
        final Enumeration<JarEntry> it = jar.entries();
        while (it.hasMoreElements()) {
            String entry = it.nextElement().getName();
            if (entry.endsWith(CLASS_EXT)) {
                entry = entry.substring(0, entry.length() - CLASS_EXT.length()).replace('/', '.');
                assertTrue(entries.add(entry));
            }
        }
        jar.close();
        return entries;
    }

    /**
     * Asserts that the {@link #incompatibleChanges} list is empty.
     * If not, the list of all incompatible changes will be formatted.
     */
    private void assertNoIncompatibility() {
        if (!incompatibleChanges.isEmpty()) {
            final String lineSeparator = System.getProperty("line.separator", "\n"); // TODO: Use System.lineSeparator() on JDK7.
            final StringBuilder buffer = new StringBuilder(240 * incompatibleChanges.size());
            for (final IncompatibleChange change : incompatibleChanges) {
                change.toString(buffer, lineSeparator);
            }
            fail(buffer.toString());
        }
    }

    /**
     * Lists the method having an incompatible change.
     *
     * @param  args Ignored.
     * @throws IOException if an error occurred while reading a JAR file.
     * @throws ClassNotFoundException if a class that existed in the previous GeoAPI release
     *         has not been found in the new release.
     * @throws NoSuchMethodException if a method that existed in the previous GeoAPI release
     *         has not been found in the new release.
     */
    public static void main(final String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException {
        final CompatibilityTest c = new CompatibilityTest("3.1-SNAPSHOT", "4.0-SNAPSHOT");
        c.createIncompatibleChangesList();
        for (final IncompatibleChange change : c.acceptedIncompatibleChanges) {
            System.out.println(change.method + "=I");
        }
        for (final IncompatibleChange change : c.incompatibleChanges) {
            System.out.println(change.method + "=MC");
        }
    }
}
