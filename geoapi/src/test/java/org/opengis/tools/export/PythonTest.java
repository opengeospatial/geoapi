/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.export;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.opengis.geoapi.SchemaException;
import org.opengis.geoapi.SchemaInformation;

import static org.junit.Assume.*;


/**
 * Generates or verifies Python abstract classes from Java interfaces.
 * This test is executed only if at least one of the following conditions is meet:
 *
 * <ul>
 *   <li>The {@code "ISO_SCHEMAS_DIR"} environment variable is set to the path of a directory containing a copy of
 *       <a href="http://standards.iso.org/iso/">http://standards.iso.org/iso/</a> files (not necessarily with all
 *       sub-directories). In particular, that directory shall contain an {@code 19115} sub-directory.</li>
 *   <li>The {@code "org.opengis.extensiveTesting"} Java property is set to {@code true}. In such case, the test
 *       will download the XSD files from the network, which may be slow.</li>
 * </ul>
 *
 * Otherwise the test is skipped.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public final strictfp class PythonTest extends JavaToPython {
    /**
     * Creates a new Python classes writer or verifier.
     *
     * @throws ParserConfigurationException if the XML parser can not be created.
     * @throws IOException     if an I/O error occurred while reading a file.
     * @throws SAXException    if a file can not be parsed as a XML document.
     * @throws SchemaException if a XML document can not be interpreted as an OGC/ISO schema.
     */
    public PythonTest() throws ParserConfigurationException, IOException, SAXException, SchemaException {
        super(cacheDirectory());
    }

    /**
     * Returns the directory of the local copy of ISO schemas, or {@code null} if none.
     * If there is no local copy and the {@code "org.opengis.extensiveTesting"} property
     * is not set to {@code true}, then this method skip the test since downloading the
     * schemas from {@value SchemaInformation#ROOT_NAMESPACE} is slow.
     *
     * @return the directory of the local copy of ISO schemas, or {@code null} for downloading from internet.
     */
    private static Path cacheDirectory() {
        final String dir = System.getenv("ISO_SCHEMAS_DIR");
        if (dir != null) {
            return Paths.get(dir);
        }
        assumeTrue("\n" +
                   "Can not execute the test because XML schemas are not available.\n" +
                   "To enable this test, apply one of the following configurations:\n" +
                   "\n" +
                   "  • Set the ISO_SCHEMAS_DIR environment variable to the path of\n" +
                   "    a directory containing a copy of " + SchemaInformation.ROOT_NAMESPACE + "\n" +
                   "    files (only the 19115 sub-directory is needed)." +
                   "\n" +
                   "  • Or execute the test with org.opengis.extensiveTesting property\n" +
                   "    set to true (may be slow, depending on network connection).\n",
                   Boolean.getBoolean("org.opengis.extensiveTesting"));
        return null;
    }

    /**
     * Specifies whether verification of the given file should be skipped.
     * Current implementation skips the verification of following files,
     * because they were edited by hand:
     *
     * <ul>
     *   <li>{@code quality.py}</li>
     * </ul>
     *
     * @param  file  the existing file to test.
     * @return whether the given file should be skipped.
     */
    @Override
    protected boolean skipVerification(final Path file) {
        if ("quality.py".equals(file.getFileName().toString())) {
            return true;
        }
        return super.skipVerification(file);
    }
}
