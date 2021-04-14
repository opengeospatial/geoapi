/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.EnumSet;
import java.util.ArrayList;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.annotation.Specification;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.opengis.annotation.Specification.*;


/**
 * Verifies or generates the {@value #INDEX_FILENAME} file. If the file is absent, then it will be generated
 * (the developer is responsible to ensure that all changes compared to previous version are wanted). If the
 * file is presents, then its content will be compared with the content of the file that would have been generated.
 *
 * <p>This class use information provided by {@link UML} annotations. Those information are verified by
 * {@link MethodSignatureTest}, which should pass before {@link #generateOrVerifyIndex()} is executed.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final strictfp class ClassIndexTest extends SourceGenerator {
    /**
     * The name of the index file to read or generate. This file will be located in the
     * "{@code org/opengis/annotation}" directory.
     */
    private static final String INDEX_FILENAME = "class-index.properties";

    /**
     * The encoding to use for the {@value #INDEX_FILENAME} file. We use the default encoding
     * used by {@link java.util.Properties} when loading a file from an {@link InputStream}.
     */
    private static final String ENCODING = "ISO-8859-1";

    /**
     * Verifies the class index (if it exists) or generates the class index (if it doesn't exist).
     * First, this method get the list of every interface, enumeration or
     * {@linkplain org.opengis.util.CodeList code list} derived from an OGC or ISO specification.
     * Then there is a choice:
     *
     * <ul>
     *   <li>If the {@value #INDEX_FILENAME} file exists, then its content will be compared with
     *       the generated index. Any difference found will cause test failure.</li>
     *   <li>If the {@value #INDEX_FILENAME} file does not exist, then it will be generated.</li>
     * </ul>
     *
     * @throws IOException if an error occurred while reading the existing {@value #INDEX_FILENAME} file
     *         or writing a new one.
     */
    @Test
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public void generateOrVerifyIndex() throws IOException {
        final Map<String,String> merged = new HashMap<>();
        assertNull(merged.put("LI_ProcessStep",         "LE_ProcessStep"));
        assertNull(merged.put("LI_Source",              "LE_Source"));
        assertNull(merged.put("MD_Band",                "MI_Band"));
        assertNull(merged.put("MD_CoverageDescription", "MI_CoverageDescription"));
        assertNull(merged.put("MD_Georectified",        "MI_Georectified"));
        assertNull(merged.put("MD_Georeferenceable",    "MI_Georeferenceable"));
        assertNull(merged.put("MD_ImageDescription",    "MI_ImageDescription"));
        assertNull(merged.put("MD_Metadata",            "MI_Metadata"));

        final String index = createIndex(EnumSet.of(ISO_19115, ISO_19115_2, ISO_19111), merged);
        final InputStream in = UML.class.getResourceAsStream(INDEX_FILENAME);
        if (in != null) {
            final String actual = load(in);
            assertEquals("The content of the \"" + INDEX_FILENAME + "\" file is different from " +
                         "the content found by scanning the compiled classes.", index, actual);
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
     * @param  in  the input stream to read. This stream will be closed at the end of file.
     * @return the full content of the given stream.
     * @throws IOException if an error occurred while reading the given file.
     */
    private static String load(final InputStream in) throws IOException {
        final StringBuilder buffer = new StringBuilder(20000);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, ENCODING))) {
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append('\n');
            }
        }
        return buffer.toString();
    }

    /**
     * Writes the given index if possible, or returns the reason why the index can not be written.
     *
     * @param  index  the full index content to write.
     * @return {@code null} on success, or the reason why this method can not write the index otherwise.
     * @throws IOException if an I/O error occurred while writing the index.
     */
    private String save(final String index) throws IOException {
        Path file = sourceDirectory("resources").resolve("org").resolve("opengis").resolve("annotation").resolve(INDEX_FILENAME);
        if (Files.exists(file)) {
            return "\"" + file + "\" already exists.";
        }
        try (Writer out = new OutputStreamWriter(new FileOutputStream(file.toFile()), ENCODING)) {
            out.write(index);
        }
        return null;
    }

    /**
     * Scans the list of all compiled classes of the given standards, and generate the index
     * content from it. Lines in the returned string are sorted by alphabetical order. The
     * line separator is fixed to the Linux one; we don't use the platform-specific line
     * separator in order to keep the build platform-independent.
     *
     * @param  standards  the standards to include in the index.
     * @param  merged  list of ISO 19115 interfaces (keys) which were merged with corresponding
     *         ISO 19115-2 interfaces (values). This map can also be used for the types of any
     *         other standards that have been merged like the ISO 19115[-2] ones. Note that this
     *         map will be destroyed by this method.
     * @return the index content.
     */
    private String createIndex(final Set<Specification> standards, final Map<String,String> merged) {
        final StringBuilder buffer = new StringBuilder(20000);
        final List<String> lines = new ArrayList<>();
        for (final Class<?> c : Content.ALL.types()) {
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
}
