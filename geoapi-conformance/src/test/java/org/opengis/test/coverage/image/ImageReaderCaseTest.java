/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.junit.BeforeClass;
import static org.junit.Assert.*;


/**
 * Tests {@link ImageReaderTestCase} using the standard PNG reader bundled in the JDK.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ImageReaderCaseTest extends ImageReaderTestCase {
    /**
     * Creates a new test case.
     */
    public ImageReaderCaseTest() {
        isSourceBandsSupported = false;
    }

    /**
     * Prepares the PNG image reader for reading our test image.
     *
     * @throws IOException if an error occurred while preparing the reader.
     */
    @Override
    protected void prepareImageReader(final boolean setInput) throws IOException {
        if (reader == null) {
            final Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("PNG");
            while (it.hasNext()) {
                reader = it.next();
                final String classname = reader.getClass().getName();
                if (classname.startsWith("com.sun.imageio.")) {
                    break;                                          // Give precedence to standard reader.
                }
            }
            assertNotNull("No PNG image reader found.", reader);
        }
        if (setInput) {
            final InputStream input = ImageReaderCaseTest.class.getResourceAsStream("PointLoma.png");
            assertNotNull("PointLoma.png file not found", input);
            reader.setInput(ImageIO.createImageInputStream(input));
        }
    }

    /**
     * Disables the creation of temporary caches on disk - use the memory instead.
     * We don't need disk cache since we test only small images.
     */
    @BeforeClass
    public static void configureImageIO() {
        ImageIO.setUseCache(false);
    }
}
