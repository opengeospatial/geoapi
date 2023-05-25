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

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.Closeable;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Method;

import static org.junit.Assert.*;
import static org.junit.Assume.*;


/**
 * Information about a released GeoAPI JAR file. This is used by {@link CompatibilityTest}
 * for verifying that a new release does not introduce incompatible changes.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class Release implements Closeable {
    /**
     * Prefix of module information.
     */
    private static final String MODULE_INFO = "module-info";

    /**
     * Filename extension of class files.
     */
    private static final String CLASS_EXT = ".class";

    /**
     * The package of legacy JSR-275 (Unit of Measurement).
     * This API has been replaced by JSR-363 (Unit of Measurement API).
     */
    private static final String LEGACY_UNIT_PACKAGE = "javax.measure.unit.";

    /**
     * Version of the Unit API.
     */
    private static final String UNIT_API_VERSION = "2.1.3";

    /**
     * Path to the JAR file of the GeoAPI version represented by the release.
     */
    private final File file;

    /**
     * Whether the {@linkplain #file JAR file} depends on the legacy JSR-275 API for units of measurement.
     * If {@code false}, then the JAR depends on JSR-363 or later. This field should always be {@code false}
     * (meaning that the JAR depends on JSR-363 or 385) unless we are comparing archived versions of GeoAPI.
     */
    private final boolean isUsingLegacyUnits;

    /**
     * For loading classes in {@link #file}.
     */
    private final ClassLoader loader;

    /**
     * Creates information about the given GeoAPI version.
     *
     * @param  mavenRepository  location of the Maven repository on the local machine.
     * @param  version          the GeoAPI version.
     * @param  parent           parent class loader to assign to the custom class loader.
     * @throws MalformedURLException if an error occurred while building the URL to the JAR files.
     */
    Release(final File mavenRepository, final String version, final ClassLoader parent) throws MalformedURLException {
        isUsingLegacyUnits = version.startsWith("2.") || version.startsWith("3.0.0");
        file = new File(mavenRepository, "org/opengis/geoapi/" + version + "/geoapi-" + version + ".jar");
        assumeTrue("GeoAPI " + version + " not in Maven repository.", file.isFile());
        final File depFile = new File(mavenRepository, isUsingLegacyUnits
                ? "javax/measure/jsr-275/0.9.3/jsr-275-0.9.3.jar"
                : "javax/measure/unit-api/" + UNIT_API_VERSION + "/unit-api-" + UNIT_API_VERSION + ".jar");

        assertTrue("Required dependency not found: " + depFile, depFile.isFile());
        final URL dependency = depFile.toURI().toURL();
        loader = new URLClassLoader(new URL[] {file.toURI().toURL(), dependency}, parent);
    }

    /**
     * Returns the fully qualified names of all classes found in the JAR file.
     */
    final Collection<String> listClasses() throws IOException {
        final List<String> entries = new ArrayList<>();
        try (JarFile jar = new JarFile(file)) {
            final Enumeration<JarEntry> it = jar.entries();
            while (it.hasMoreElements()) {
                String entry = it.nextElement().getName();
                if (entry.endsWith(CLASS_EXT) && !entry.contains(MODULE_INFO)) {
                    entry = entry.substring(0, entry.length() - CLASS_EXT.length()).replace('/', '.');
                    assertTrue(entries.add(entry));
                }
            }
        }
        return entries;
    }

    /**
     * Loads class of the given name.
     */
    final Class<?> loadClass(final String name) throws ClassNotFoundException {
        return Class.forName(name, false, loader);
    }

    /**
     * Returns the parameters of the given methods.
     *
     * @param  source  the API from which the given method has been loaded.
     * @param  method  the method from which to get parameter types.
     */
    final Class<?>[] getParameterTypes(final Release source, final Method method) throws ClassNotFoundException {
        final Class<?>[] paramTypes = method.getParameterTypes();
        for (int i=0; i<paramTypes.length; i++) {
            if (!paramTypes[i].isPrimitive()) {
                /*
                 * GeoAPI types are not represented by the same Class instances, so we need to perform
                 * a call to Class.forName(…) for them even if the class name is exactly the same.
                 * In the case of a migration from JSR-275 to JSR-363, we also have a class name change.
                 */
                String className = paramTypes[i].getName();
                if (source.isUsingLegacyUnits != isUsingLegacyUnits) {
                    if (isUsingLegacyUnits) {
                        if (className.equals("javax.measure.Unit")) {
                            className = LEGACY_UNIT_PACKAGE + "Unit";
                        }
                    } else {
                        className = normalize(className);
                    }
                }
                paramTypes[i] = Class.forName(className, false, loader);
            }
        }
        return paramTypes;
    }

    /**
     * Replaces JSR-275 class names by JSR-363 names.
     */
    static String normalize(String className) {
        if (className.startsWith(LEGACY_UNIT_PACKAGE)) {
            className = "javax.measure." + className.substring(LEGACY_UNIT_PACKAGE.length());
        }
        return className;
    }

    /**
     * Closes the class loader.
     */
    @Override
    public void close() throws IOException {
        if (loader instanceof Closeable) {
            ((Closeable) loader).close();
        }
    }

    /**
     * Returns the JAR filename. This is used when reporting JUnit error for debugging purpose.
     */
    @Override
    public String toString() {
        String name = file.getName();
        if (isUsingLegacyUnits) {
            name += " (uses legacy units)";
        }
        return name;
    }
}
