/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.opengis.annotation.Specification;

import static org.junit.Assert.*;


/**
 * Scans every classes in the OpenGIS classpath.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
final class ClassScanner implements Iterator<Class<?>> {
    /**
     * Extension for class files.
     */
    private static final String EXTENSION = ".class";

    /**
     * The classes to load.
     */
    private final List<String> classNames = new ArrayList<String>();

    /**
     * Index of the next element to return.
     */
    private int index = 0;

    /**
     * Creates a new instance of {@code ClassScanner}.
     */
    public ClassScanner() {
        final URL url = Specification.class.getResource("Specification.class");
        assertNotNull(url);
        final File classFile;
        try {
            classFile = new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
        add(classFile.getParentFile().getParentFile(), "org.opengis");
    }

    /**
     * Adds every class names found in the specified directory and sub-directories.
     */
    private void add(final File directory, final String packageName) {
        assertTrue(directory.isDirectory());
        final StringBuilder b = new StringBuilder(packageName).append('.');
        final int base = b.length();
        for (final File file : directory.listFiles()) {
            String name = file.getName();
            if (file.isDirectory()) {
                add(file, packageName + '.' + name);
            } else if (name.endsWith(EXTENSION)) {
                name = name.substring(0, name.length() - EXTENSION.length());
                b.setLength(base);
                classNames.add(b.append(name).toString());
            }
        }
    }

    /**
     * Returns {@code true} if there is more OpenGIS classes or interfaces in the classpath.
     */
    public boolean hasNext() {
        return index < classNames.size();
    }

    /**
     * Returns the next OpenGIS class or interface in the classpath.
     */
    public Class<?> next() {
        if (hasNext()) try {
            return Class.forName(classNames.get(index++));
        } catch (ClassNotFoundException e) {
            fail(e.getLocalizedMessage());
        }
        throw new NoSuchElementException();
    }

    /**
     * Unsupported operation.
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
