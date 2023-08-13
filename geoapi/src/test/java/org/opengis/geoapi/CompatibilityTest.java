/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.Closeable;
import java.io.File;
import java.lang.reflect.Type;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.After;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assume.assumeFalse;


/**
 * Verifies the compatibility of a GeoAPI JAR file compared to its previous version.
 *
 * <p>This test class also has a {@link #listChanges()} method, which only lists new methods and methods having
 * an incompatible change. This is used for generating the list of incompatible changes to be expected in the next
 * major GeoAPI release.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   3.1
 */
public final class CompatibilityTest implements Closeable {
    /**
     * The GeoAPI version used as a reference.
     */
    private final Release oldAPI;

    /**
     * The GeoAPI version to compare against the reference one.
     */
    private final Release newAPI;

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
     * Lists the new methods and the methods having an incompatible change.
     *
     * @throws IOException if an error occurred while reading a JAR file.
     * @throws ClassNotFoundException if a class that existed in the previous GeoAPI release
     *         has not been found in the new release.
     * @throws NoSuchMethodException if a method that existed in the previous GeoAPI release
     *         has not been found in the new release.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void listChanges() throws IOException, ClassNotFoundException, NoSuchMethodException {
        try (final CompatibilityTest c = new CompatibilityTest("3.1-SNAPSHOT", "4.0-SNAPSHOT")) {
            for (final IncompatibleChange change : c.acceptedIncompatibleChanges) {
                System.out.println(change.method + "=I");
            }
            for (final IncompatibleChange change : c.createIncompatibleChangesList()) {
                System.out.println(change.method + "=MC");
            }
            for (final String change : c.listNewMethods()) {
                System.out.println(change + "=N");
            }
        }
    }

    /**
     * Creates a new API comparator.
     *
     * @throws MalformedURLException if an error occurred while building the URL to the JAR files.
     */
    public CompatibilityTest() throws MalformedURLException {
        this("3.0.2", "3.1-SNAPSHOT");
    }

    /**
     * Creates a new API comparator between the given versions of GeoAPI.
     *
     * @param  oldVersion  the GeoAPI version used as a reference.
     * @param  newVersion  the GeoAPI version to compare against the reference one.
     * @throws MalformedURLException if an error occurred while building the URL to the JAR files.
     */
    private CompatibilityTest(final String oldVersion, final String newVersion) throws MalformedURLException {
        final File mavenRepository = new File(System.getProperty("user.home"), ".m2/repository");
        final ClassLoader parent = CompatibilityTest.class.getClassLoader().getParent();
        oldAPI = new Release(mavenRepository, oldVersion, parent);
        newAPI = new Release(mavenRepository, newVersion, parent);
        if (newVersion.startsWith("3.1")) {
            deletedClasses = Collections.emptySet();
            acceptedIncompatibleChanges = IncompatibleChange.for31();
        } else if (newVersion.startsWith("4.0")) {
            deletedClasses = Collections.singleton("org.opengis.metadata.Obligation");
            acceptedIncompatibleChanges = IncompatibleChange.for40();
        } else {
            throw new IllegalStateException("Unsupported version: " + newVersion);
        }
    }

    /**
     * Returns the list of new methods found in the new GeoAPI version. This method is not used by
     * {@link #verifyCompatibility()} since adding new methods are compatible changes. This method
     * is used only by {@link #listChanges()} as part of the release notes.
     *
     * @return list of new methods, or {@code null} if none.
     * @throws IOException if an error occurred while reading a JAR file.
     * @throws ClassNotFoundException if a class that existed in the previous GeoAPI release
     *         has not been found in the new release.
     * @throws NoSuchMethodException if a method that existed in the previous GeoAPI release
     *         has not been found in the new release.
     */
    private List<String> listNewMethods() throws IOException, ClassNotFoundException, NoSuchMethodException {
        final List<String> newMethods = new ArrayList<>();
        for (final String className : newAPI.listClasses()) {
            if (className.indexOf('$') >= 0) {
                continue;                                                           // Skip inner classes.
            }
            final Class<?> newClass = newAPI.loadClass(className);
            if (!Modifier.isPublic(newClass.getModifiers())) {
                continue;                                                           // Skip non-public classes.
            }
            Class<?> oldClass;
            try {
                oldClass = oldAPI.loadClass(className);
            } catch (ClassNotFoundException e) {
                oldClass = null;                                                    // Will mark all methods as new.
            }
            for (final Method newMethod : newClass.getDeclaredMethods()) {
                if (!Modifier.isPublic(newMethod.getModifiers())) {
                    continue;                                                       // Skip non-public methods.
                }
                if (newMethod.isSynthetic()) {
                    continue;                                                       // Skip methods added by compiler.
                }
                final String methodName = newMethod.getName();
                if (oldClass != null) try {
                    final Class<?>[] paramTypes = oldAPI.getParameterTypes(newAPI, newMethod);
                    final Method oldMethod = oldClass.getMethod(methodName, paramTypes);
                    /*
                     * At this point we found that the method existed, so we will not report it has a new method.
                     * As a paranoiac check we verify that the parameters of the new method are the same than the
                     * parameters of the old method, but this check should actually never fail (if the parameters
                     * were not the same, the method would not have been found).
                     */
                    assertArrayEquals(paramTypes, oldMethod.getParameterTypes(), methodName);
                    continue;
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    // Ignore - will execute the same code as if 'oldClass' were null.
                }
                assertTrue(newMethods.add(newClass.getCanonicalName() + '.' + methodName));
            }
        }
        return newMethods;
    }

    /**
     * Returns the list of incompatible changes found between the old and the new GeoAPI version.
     * If this method is used for a JUnit test, then the expected result is an empty list.
     *
     * @return list of incompatible changes.
     * @throws IOException if an error occurred while reading a JAR file.
     * @throws ClassNotFoundException if a class that existed in the previous GeoAPI release
     *         has not been found in the new release.
     * @throws NoSuchMethodException if a method that existed in the previous GeoAPI release
     *         has not been found in the new release.
     */
    private List<IncompatibleChange> createIncompatibleChangesList() throws IOException, ClassNotFoundException, NoSuchMethodException {
        final List<IncompatibleChange> incompatibleChanges = new ArrayList<>();
        for (final String className : oldAPI.listClasses()) {
            final Class<?> oldClass = oldAPI.loadClass(className);
            if (!Modifier.isPublic(oldClass.getModifiers())) {
                continue;                                                         // Skip non-public classes.
            }
            if (deletedClasses.contains(className)) {
                continue;                                              // Skip intentionally deleted classes.
            }
            final Class<?> newClass = newAPI.loadClass(className);
            assumeFalse("Old and new classes have not been loaded by different class loaders. "
                      + "This may happen with Java 9 when at least one module is modularized. "
                      + "We need to revisit, maybe with the use of ModuleLayer.",
                        oldClass.equals(newClass));
            /*
             * At this point we have a class from the previous GeoAPI release (oldClass) and its new version.
             * Now compare all public methods. We skip protected methods for simplicity, since we perform our
             * checks using Class.getMethod (we cannot use Class.getDeclaredMethod because we want to search
             * in parent classes if needed). Note that arguments and return values which are GeoAPI types are
             * represented by different Class instances between the two GeoAPI version, so we need to convert
             * them using their class name.
             */
            for (final Method oldMethod : oldClass.getDeclaredMethods()) {
                if (!Modifier.isPublic(oldMethod.getModifiers())) {
                    continue;                                                     // Skip non-public methods.
                }
                final String methodName = oldMethod.getName();
                final Class<?>[] paramTypes = newAPI.getParameterTypes(oldAPI, oldMethod);
                final Method newMethod = newClass.getMethod(methodName, paramTypes);
                assertArrayEquals(paramTypes, newMethod.getParameterTypes(), methodName);   // Paranoiac check (should never fail).
                /*
                 * Compare generic arguments (if any). We require an exact match,
                 * including for parameterized types.
                 */
                final Type[] oldGPT = oldMethod.getGenericParameterTypes();
                final Type[] newGPT = newMethod.getGenericParameterTypes();
                assertEquals(oldGPT.length, newGPT.length, methodName);         // Paranoiac check (should never fail).
                for (int i=0; i<oldGPT.length; i++) {
                    final String oldType = Release.normalize(oldGPT[i].getTypeName());
                    final String newType = Release.normalize(newGPT[i].getTypeName());
                    if (!newType.equals(oldType)) {
                        final String lineSeparator = System.lineSeparator();
                        fail("Incompatible change in argument #" + (i+1) + " of "
                                + className + '.' + methodName + ':' + lineSeparator
                                + "    (old) " + oldType + " from " + oldAPI + lineSeparator
                                + "    (new) " + newType + " from " + newAPI + lineSeparator);
                    }
                }
                /*
                 * Compare generic return type, with a tolerance for a predefined set of incompatible changes.
                 * Any change not listed in the collection of accepted incompatible changes will be considered
                 * as an error.
                 */
                if (!oldMethod.isSynthetic()) {
                    final String oldType = Release.normalize(oldMethod.getGenericReturnType().getTypeName());
                    final String newType = Release.normalize(newMethod.getGenericReturnType().getTypeName());
                    if (!newType.equals(oldType)) {
                        final IncompatibleChange change = new IncompatibleChange(className + '.' + methodName, oldType, newType);
                        if (!acceptedIncompatibleChanges.remove(change)) {
                            incompatibleChanges.add(change);
                        }
                    }
                }
            }
        }
        if (!acceptedIncompatibleChanges.isEmpty()) {
            final String lineSeparator = System.lineSeparator();
            fail("The collection of \"accepted incompatible changes\" has not been fully used." + lineSeparator +
                 "Is this test configured with the right collection? Remaining items are:" + lineSeparator +
                 acceptedIncompatibleChanges + lineSeparator +
                 "JAR files are:" + lineSeparator +
                 "    old API: " + oldAPI + lineSeparator +
                 "    new API: " + newAPI + lineSeparator);
        }
        return incompatibleChanges;
    }

    /**
     * Verifies that all changes compared to the latest GeoAPI release are compatible changes.
     * This test asserts that the list of incompatible changes is empty.
     * If not, that list will be formatted in the error message.
     *
     * @throws IOException if an error occurred while reading a JAR file.
     * @throws ClassNotFoundException if a class that existed in the previous GeoAPI release
     *         has not been found in the new release.
     * @throws NoSuchMethodException if a method that existed in the previous GeoAPI release
     *         has not been found in the new release.
     */
    @Test
    public void verifyCompatibility() throws IOException, ClassNotFoundException, NoSuchMethodException {
        final List<IncompatibleChange> incompatibleChanges = createIncompatibleChangesList();
        if (!incompatibleChanges.isEmpty()) {
            final String lineSeparator = System.lineSeparator();
            final StringBuilder buffer = new StringBuilder(240 * incompatibleChanges.size());
            for (final IncompatibleChange change : incompatibleChanges) {
                change.toString(buffer, lineSeparator);
            }
            fail(buffer.toString());
        }
    }

    /**
     * Releases the resources used by this test.
     *
     * @throws IOException if an error occurred while releasing the resources.
     */
    @After
    @Override
    public void close() throws IOException {
        oldAPI.close();
        newAPI.close();
    }
}
