/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2012 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.*;
import static org.junit.Assert.*;
import static org.opengis.annotation.Specification.*;


/**
 * Generates the list of every interfaces or {@linkplain org.opengis.util.CodeList code lists}
 * derived from an ISO specification. Then there is a choice:
 * <p>
 * <ul>
 *   <li>If the {@value #INDEX_FILENAME} file exists, then its content will be compared with
 *       the generated index. Any difference found will cause test failure.</li>
 *   <li>If the {@value #INDEX_FILENAME} file does not exist, then it will be generated.</li>
 * </ul>
 * <p>
 * This class is designated for working with the Maven directory layout. If it doesn't
 * recognize that layout, then the test is skipped.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ClassIndexTest implements FileFilter {
    /**
     * The name of the index file to read or generate. This file will be located in the
     * "{@code org/opengis/annotation}" directory.
     */
    public static final String INDEX_FILENAME = "class-index.properties";

    /**
     * The encoding to use for the {@value #INDEX_FILENAME} file. We use the default encoding
     * used by {@link java.util.Properties} when loading a file from an {@link InputStream}.
     */
    private static final String ENCODING = "ISO-8859-1";

    /**
     * The Maven {@code target} directory found by the last call to {@link #listClasses(Class)}.
     */
    private File targetDirectory;

    /**
     * Verifies the index (if it exists) or generates the index (if it doesn't exist).
     *
     * @throws IOException If an error occurred while reading the existing
     *         {@value #INDEX_FILENAME} file.
     */
    @Test
    public void generateOrVerifyIndex() throws IOException {
        final Map<String,String> merged = new HashMap<String,String>();
        assertNull(merged.put("LI_ProcessStep",         "LE_ProcessStep"));
        assertNull(merged.put("LI_Source",              "LE_Source"));
        assertNull(merged.put("MD_Band",                "MI_Band"));
        assertNull(merged.put("MD_CoverageDescription", "MI_CoverageDescription"));
        assertNull(merged.put("MD_Georectified",        "MI_Georectified"));
        assertNull(merged.put("MD_Georeferenceable",    "MI_Georeferenceable"));
        assertNull(merged.put("MD_ImageDescription",    "MI_ImageDescription"));
        assertNull(merged.put("MD_Metadata",            "MI_Metadata"));

        final String index = createIndex(EnumSet.of(ISO_19115, ISO_19115_2, ISO_19111), merged);
        final InputStream in = ClassIndexTest.class.getResourceAsStream(INDEX_FILENAME);
        if (in != null) {
            assertEquals("The content of the \"" + INDEX_FILENAME + "\" file is different from " +
                         "the content found be scanning the compiled classes.", index, load(in));
        } else {
            final String reason = save(index);
            if (reason != null) {
                System.out.println("Can not write the index because " + reason +
                                   " The index is printed below:\n" + index);
            }
        }
    }

    /**
     * Reads fully the given file. The file encoding is assumed to be {@link #ENCODING}.
     * This method does not skip any line and closes the given stream at end of file.
     *
     * @param  in The input stream to read. This stream will be closed at the end of file.
     * @return The full content of the given stream.
     * @throws IOException If an error occurred while reading the given file.
     */
    private static String load(final InputStream in) throws IOException {
        final StringBuilder buffer = new StringBuilder(20000);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in, ENCODING));
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line).append('\n');
        }
        reader.close();
        return buffer.toString();
    }

    /**
     * Writes the given index if possible, or returns the reason why the index can not be written.
     *
     * @param  index The full index content to write.
     * @return {@code null} on success, or the reason why this method can not write the index otherwise.
     * @throws IOException If an I/O error occurred while writing the index.
     */
    private String save(final String index) throws IOException {
        File file = targetDirectory;
        if (file == null || !file.getName().equals("classes")) {
            return "\"" + file + "\" is not a Maven target directory.";
        }
        file = file.getParentFile();
        if (file == null || !file.getName().equals("target")) {
            return "\"" + file + "\" is not a Maven target directory.";
        }
        file = new File(file.getParentFile(), "src/main/resources/org/opengis/annotation");
        if (!file.isDirectory()) {
            return "\"" + file + "\" is not a directory.";
        }
        file = new File(file, INDEX_FILENAME);
        if (file.exists()) {
            return "\"" + file + "\" already exists.";
        }
        final Writer out = new OutputStreamWriter(new FileOutputStream(file), ENCODING);
        out.write(index);
        out.close();
        return null;
    }

    /**
     * Scans the list of all compiled classes of the given standards, and generate the index
     * content from it. Lines in the returned string are sorted by alphabetical order. The
     * line separator is fixed to the Linux one; we don't use the platform-specific line
     * separator in order to keep the build platform-independent.
     *
     * @param  standards The standards to include in the index.
     * @param  merged List of ISO 19115 interfaces (keys) which were merged with corresponding
     *         ISO 19115-2 interfaces (values). This map can also be used for the types of any
     *         other standards that have been merged like the ISO 19115[-2] ones. Note that this
     *         map will be destroyed by this method.
     * @return The index content.
     */
    private String createIndex(final Set<Specification> standards, final Map<String,String> merged) {
        final StringBuilder buffer = new StringBuilder(20000);
        final List<String> lines = new ArrayList<String>();
        for (final Class<?> c : listClasses(UML.class)) {
            final UML uml = c.getAnnotation(UML.class);
            if (uml != null && standards.contains(uml.specification())) {
                String identifier = uml.identifier();
                final String classname = c.getName();
                do {
                    assertTrue(lines.add(buffer.append(identifier).append('=').append(classname).toString()));
                    buffer.setLength(0);
                } while ((identifier = merged.remove(identifier)) != null);
            }
        }
        assertTrue(merged.toString(), merged.isEmpty());
        Collections.sort(lines);
        for (final String line : lines) {
            buffer.append(line).append('\n');
        }
        return buffer.toString();
    }

    /**
     * Finds the Maven module containing the given class, then finds all classes in that module.
     * Only the Maven {@code target} directory containing the given sample class is scanned; this
     * is not necessarily the full classpath (which is not desired anyway since we don't want to
     * include pending interfaces, test classes, <i>etc.</i>).
     * <p>
     * This method sets the {@link #targetDirectory} to the Maven "{@code target}" directory
     * inferred from the given sample class.
     *
     * @param  sample A sample class to use for finding the Maven {@code target} directory.
     * @return All classes found in the inferred target directory.
     */
    private Set<Class<?>> listClasses(final Class<?> sample) {
        String pathname = sample.getName();
        int s = pathname.lastIndexOf('.');
        String name = pathname.substring(s+1) + ".class";
        final URI uri;
        try {
            uri = sample.getResource(name).toURI();
        } catch (URISyntaxException e) {
            fail("Can't not create a URI for the " + pathname + " class:\n" + e);
            return Collections.emptySet();
        }
        File file = new File(uri);
        while (true) { // Break condition in the middle of the loop.
            assertEquals("Unexpected name.", name, file.getName());
            file = file.getParentFile();
            assertNotNull("Missing parent directory.", file);
            if (s < 0) break;
            pathname = pathname.substring(0, s);
            s = pathname.lastIndexOf('.');
            name = pathname.substring(s+1);
        }
        targetDirectory = file;
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        listClasses(file, new StringBuilder(), classes);
        return classes;
    }

    /**
     * Scans the given directory for {@code .class} files, and adds each class found in the given
     * set. This method invokes itself recursively.
     */
    private void listClasses(final File directory, final StringBuilder name, final Set<Class<?>> classes) {
        final int length = name.length();
        for (final File file : directory.listFiles(this)) {
            name.append(file.getName()).append('.');
            if (file.isDirectory()) {
                listClasses(file, name, classes);
            } else {
                String classname = name.toString();
                classname = classname.substring(0, classname.lastIndexOf(".class"));
                final Class<?> c;
                try {
                    c = Class.forName(classname);
                } catch (ClassNotFoundException e) {
                    fail(e.toString());
                    continue;
                }
                assertTrue(classname, classes.add(c)); // Fails if a class is declared twice.
            }
            name.setLength(length);
        }
    }

    /**
     * Returns {@code true} if the given file is a directory or a {@code .class} file.
     * This method is used in order to filter the directory of class files.
     *
     * @param  file The file to test.
     * @return {@code true} if the given file is a directory or a class file.
     */
    @Override
    public boolean accept(final File file) {
        final String name = file.getName();
        return !file.isHidden() && (file.isDirectory() || (file.isFile() && name.endsWith(".class")));
    }
}
