/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test.coverage.image;

import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests static methods defined in {@link ImageBackendTestCase}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ImageBackendCaseTest {
    /**
     * Creates a new test case.
     */
    public ImageBackendCaseTest() {
    }

    /**
     * Tests the {@link ImageBackendTestCase#createImage(int, int, int, int)} method
     * with a single band.
     */
    @Test
    public void testCreateOneBandedImage() {
        final int[] types = {
            DataBuffer.TYPE_BYTE,
            DataBuffer.TYPE_USHORT,
            DataBuffer.TYPE_SHORT,
            DataBuffer.TYPE_INT,
            DataBuffer.TYPE_FLOAT,
            DataBuffer.TYPE_DOUBLE
        };
        for (final int type : types) {
            final BufferedImage image = ImageBackendTestCase.createImage(type, 1, 1, 1);
            final SampleModel model = image.getSampleModel();
            assertEquals(type, model.getDataType(), "SampleModel.getDataType()");
            assertEquals(1,    model.getNumBands(), "SampleModel.getNumBands()");
        }
    }

    /**
     * Tests the {@link ImageBackendTestCase#createImage(int, int, int, int)} method
     * with 3 bands.
     */
    @Test
    public void testCreateThreeBandedImage() {
        final int numBands = 3;                                     // Just for making code more readable.
        final BufferedImage image = ImageBackendTestCase.createImage(DataBuffer.TYPE_BYTE, 1, 1, numBands);
        final SampleModel model = image.getSampleModel();
        assertEquals(numBands, model.getNumBands(), "SampleModel.getNumBands()");
    }
}
