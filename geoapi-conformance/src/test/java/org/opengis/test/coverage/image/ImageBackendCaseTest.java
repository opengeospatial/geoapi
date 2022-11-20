/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2021 Open Geospatial Consortium, Inc.
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

import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;
import java.awt.image.BufferedImage;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests static methods defined in {@link ImageBackendTestCase}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ImageBackendCaseTest {
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
            assertEquals("SampleModel.getDataType()", type, model.getDataType());
            assertEquals("SampleModel.getNumBands()", 1,    model.getNumBands());
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
        assertEquals("SampleModel.getNumBands()", numBands, model.getNumBands());
    }
}
