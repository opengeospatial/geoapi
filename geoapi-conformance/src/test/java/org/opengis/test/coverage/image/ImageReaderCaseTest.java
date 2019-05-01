/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2019 Open Geospatial Consortium, Inc.
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
public strictfp class ImageReaderCaseTest extends ImageReaderTestCase {
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
