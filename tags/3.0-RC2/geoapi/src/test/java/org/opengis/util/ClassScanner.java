/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2011 Open Geospatial Consortium, Inc.
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
