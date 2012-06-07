/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012 Open Geospatial Consortium, Inc.
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
import javax.imageio.stream.ImageInputStream;

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
     *
     * @throws IOException If an error occurred while creating the image input stream.
     */
    public ImageReaderCaseTest() throws IOException {
        super(System.nanoTime());
        final Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("PNG");
        while (it.hasNext()) {
            reader = it.next();
            final String classname = reader.getClass().getName();
            if (classname.startsWith("com.sun.imageio.")) {
                break; // Give precedence to standard reader.
            }
        }
        assertNotNull("No PNG image reader found.", reader);
        reader.setInput(ImageIO.createImageInputStream(getInputStream()));
    }

    /**
     * Returns an input stream for the source image.
     */
    private static InputStream getInputStream() {
        final InputStream input = ImageReaderCaseTest.class.getResourceAsStream("PointLoma.png");
        assertNotNull("PointLoma.png file not found", input);
        return input;
    }

    /**
     * Ensures that the image reader can read again the image at the given index. This method
     * closes the current input stream and create a new one if necessary.
     *
     * @param  imageIndex Index of the image to read.
     * @return Always {@code true} in this implementation.
     * @throws IOException If an error occurred while reseting the reader.
     */
    @Override
    protected boolean canReadAgain(final int imageIndex) throws IOException {
        if (!super.canReadAgain(imageIndex)) {
            ((ImageInputStream) reader.getInput()).close();
            reader.setInput(ImageIO.createImageInputStream(getInputStream()));
        }
        return true;
    }
}
