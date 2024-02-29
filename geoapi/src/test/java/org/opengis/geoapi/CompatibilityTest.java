/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2024 Open Geospatial Consortium, Inc.
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
import java.net.MalformedURLException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Verifies the compatibility of a GeoAPI JAR file compared to its previous version.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   3.1
 */
public final class CompatibilityTest extends CompatibilityVerifier {
    /**
     * Creates a new API comparator.
     *
     * @throws MalformedURLException if an error occurred while building the URL to the JAR files.
     */
    public CompatibilityTest() throws MalformedURLException {
        super("3.0.2", "3.1-SNAPSHOT");
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
    @Override
    @AfterEach
    public void close() throws IOException {
        super.close();
    }
}
