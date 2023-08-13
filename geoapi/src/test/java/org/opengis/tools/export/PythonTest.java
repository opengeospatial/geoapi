/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.export;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.opengis.geoapi.SchemaException;
import org.opengis.geoapi.SchemaInformation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assume.assumeTrue;


/**
 * Generates or verifies Python abstract classes from Java interfaces.
 * This test is executed only if at least one of the following conditions is meet:
 *
 * <ul>
 *   <li>The {@code "ISO_SCHEMAS_DIR"} environment variable is set to the path of a directory containing a copy of
 *       <a href="https://schemas.isotc211.org/">https://schemas.isotc211.org/</a> files (not necessarily with all
 *       sub-directories). In particular, that directory shall contain an {@code 19115} sub-directory.</li>
 *   <li>The {@code "org.opengis.extensiveTesting"} Java property is set to {@code true}. In such case, the test
 *       will download the XSD files from the network, which may be slow.</li>
 * </ul>
 *
 * Otherwise the test is skipped.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   4.0
 * @version 4.0
 */
public final class PythonTest extends JavaToPython {
    /**
     * Creates a new Python classes writer or verifier.
     *
     * @throws ParserConfigurationException if the XML parser cannot be created.
     * @throws IOException     if an I/O error occurred while reading a file.
     * @throws SAXException    if a file cannot be parsed as a XML document.
     * @throws SchemaException if a XML document cannot be interpreted as an OGC/ISO schema.
     */
    public PythonTest() throws ParserConfigurationException, IOException, SAXException, SchemaException {
        super(cacheDirectory());
    }

    /**
     * Returns the directory of the local copy of ISO schemas, or {@code null} if none.
     * If there is no local copy and the {@code "org.opengis.extensiveTesting"} property
     * is not set to {@code true}, then this method skip the test since downloading the
     * schemas from {@value SchemaInformation#SCHEMA_ROOT_URL} is slow.
     *
     * @return the directory of the local copy of ISO schemas, or {@code null} for downloading from internet.
     */
    private static Path cacheDirectory() {
        final String dir = System.getenv("ISO_SCHEMAS_DIR");
        if (dir != null) {
            return Paths.get(dir);
        }
        assumeTrue("\n" +
                   "Cannot execute the test because XML schemas are not available.\n" +
                   "To enable this test, apply one of the following configurations:\n" +
                   "\n" +
                   "  • Set the ISO_SCHEMAS_DIR environment variable to the path of\n" +
                   "    a directory containing a copy of " + SchemaInformation.SCHEMA_ROOT_URL + "\n" +
                   "    files (only the 19115 sub-directory is needed)." +
                   "\n" +
                   "  • Or execute the test with org.opengis.extensiveTesting property\n" +
                   "    set to true (may be slow, depending on network connection).\n",
                   Boolean.getBoolean("org.opengis.extensiveTesting"));
        return null;
    }

    /**
     * Compares content of the given file against expected content.
     * This method is invoked when the file already exists.
     * Current implementation skips the verification of following files,
     * because they were edited by hand:
     *
     * <ul>
     *   <li>{@code quality.py}</li>
     * </ul>
     *
     * For all other files, this method compares the expected lines with actual lines read from the file.
     * The file may contain some additional lines compared to the expected lines. Those additional lines
     * are for example hand-written comments.
     *
     * @param  file     the existing file to verify.
     * @param  content  expected content of the file.
     */
    @Override
    protected void verify(final Path file, final StringBuilder content) throws IOException {
        if ("quality.py".equals(file.getFileName().toString())) {
            return;
        }
        try (LineNumberReader in = new LineNumberReader(new InputStreamReader(Files.newInputStream(file), ENCODING))) {
            int startExpected = 0, endExpected;
            while ((endExpected = content.indexOf(lineSeparator, startExpected)) >= 0) {
                if (endExpected > startExpected) {
                    final String expected = content.substring(startExpected, endExpected);
                    String firstMismatchedLine = null;
                    int mismatchedLineNumber = 0;
                    for (String actual; !expected.equals(actual = in.readLine());) {
                        /*
                         * If the actual line does not match the expected line, this is not necessarily a
                         * test failure. Maybe the expected line exists somewhere down. Continue searching
                         * and report a failure only if we reach end-of-file without finding that line.
                         */
                        if (actual == null) {
                            fail(String.format("Unexpected content at line %d of file %s%n" +
                                    "Expected: %s%nActual:   %s%n", mismatchedLineNumber, file, expected,
                                    (firstMismatchedLine != null) ? firstMismatchedLine : "<end of file>"));
                            break;
                        }
                        if (!actual.isEmpty()) {
                            if (isMatch(expected, actual)) {
                                break;
                            }
                            if (firstMismatchedLine == null) {
                                firstMismatchedLine = actual;
                                mismatchedLineNumber = in.getLineNumber();
                            }
                        }
                    }
                }
                startExpected = endExpected + lineSeparator.length();
            }
        }
    }

    /**
     * Returns {@code true} if the given lines should be considered as a match despite being different.
     * This method is invoked only if {@code actual} is non-empty and not equal to {@code expected}.
     *
     * @param  expected  the line that we expected.
     * @param  actual    a line read from the Python file.
     * @return whether we have a match.
     */
    private boolean isMatch(final String expected, final String actual) {
        /*
         * Skip comparison of "Copyright (C) year" line because the year varies.
         */
        if (expected.startsWith(COPYRIGHT)) {
            return actual.startsWith(COPYRIGHT);
        }
        if (expected.endsWith(PythonProperty.IMPLEMENTATION_PROVIDED)) {
            return true;
        }
        /*
         * If the actual line contains a type but the expected line did not, then
         * the actual line is more accurate maybe because it has been edited by hand.
         * Example:
         *
         *    Expected:     def reference_system_info(self):
         *    Actual:       def reference_system_info(self) -> Sequence[ReferenceSystem]:
         */
        if (!expected.contains("->")) {
            final int start = actual.indexOf(" -> ");
            if (start >= 0) {
                final int end = actual.indexOf(':', start);
                if (end >= 0) {
                    final String reduced = actual.substring(0, start) + actual.substring(end);
                    return expected.equals(reduced);
                }
            }
        }
        /*
         * Relax comparison for the """Date(s) other than creation dateEG: expiry date.""" sentence,
         * which we rewrote as  """Date(s) other than creation date. Example: expiry date.""".
         */
        int s = expected.indexOf("EG:");
        if (s >= 30) {
            final String reduced = expected.substring(0, s);
            return actual.startsWith(reduced);
        }
        return false;
    }
}
