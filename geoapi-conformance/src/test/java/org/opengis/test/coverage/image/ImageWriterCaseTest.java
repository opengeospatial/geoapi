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
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import org.junit.Ignore;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import static org.junit.Assume.*;


/**
 * Tests {@link ImageWriterTestCase} using the standard PNG reader bundled in the JDK.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ImageWriterCaseTest extends ImageWriterTestCase {
    /**
     * Creates a new test case.
     */
    public ImageWriterCaseTest() {
        isSourceBandsSupported = false;
    }

    /**
     * Disables the creation of temporary caches on disk - use the memory instead.
     * We don't need disk cache since we test only small images.
     */
    @BeforeClass
    public static void configureImageIO() {
        ImageIO.setUseCache(false);
    }

    /**
     * Creates the image writer.
     */
    @Override
    protected void prepareImageWriter(boolean optionallySetOutput) {
        if (writer == null) {
            final Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("PNG");
            while (it.hasNext()) {
                writer = it.next();
                final String classname = writer.getClass().getName();
                if (classname.startsWith("com.sun.imageio.")) {
                    break;                                          // Give precedence to standard writer.
                }
            }
            assertNotNull("No PNG image writer found.", writer);
        }
    }

    /**
     * As of JDK7, the PNG writer does not detect that it can't write signed values.
     */
    @Override
    @Ignore("ImageWriterSpi.canDecode(RenderedImage) doen't return 'false'")
    public void testOneShortBand() throws IOException {
        assumeTrue(false);
    }
}
