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

import java.util.Random;
import java.io.Closeable;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import javax.imageio.IIOParam;


/**
 * Base class for all image I/O tests.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp abstract class ImageIOTestCase extends ImageBackendTestCase {
    /**
     * Default number of iterations when testing the image read operations.
     */
    static final int DEFAULT_NUM_ITERATIONS = 10;

    /**
     * {@code true} if the reader or writer takes in account the parameter value given to
     * {@link IIOParam#setSourceRegion(Rectangle)}. The default value is {@code true}.
     * Subclasses can set this flag to {@code false} when testing an incomplete implementation.
     */
    protected boolean isSubregionSupported = true;

    /**
     * {@code true} if the reader or writer takes in account the two first parameter values given to
     * {@link IIOParam#setSourceSubsampling(int, int, int, int)}. The default value is {@code true}.
     * Subclasses can set this flag to {@code false} when testing an incomplete implementation.
     */
    protected boolean isSubsamplingSupported = true;

    /**
     * {@code true} if the reader or writer takes in account the two last parameter values given to
     * {@link IIOParam#setSourceSubsampling(int, int, int, int)}. The default value is {@code true}.
     * Subclasses can set this flag to {@code false} when testing an incomplete implementation.
     */
    protected boolean isSubsamplingOffsetSupported = true;

    /**
     * {@code true} if the reader or writer takes in account the parameter value given to
     * {@link IIOParam#setSourceBands(int[])}. The default value is {@code true}.
     * Subclasses can set this flag to {@code false} if this feature cannot be tested for
     * the current implementation.
     *
     * <p>Note that this feature cannot be tested with some standard readers like PNG, because
     * those readers require an explicit destination image to be specified if the number of bands
     * to read differs from the number of bands in the source image.</p>
     */
    protected boolean isSourceBandsSupported = true;

    /**
     * The tolerance threshold to use when comparing floating point numbers. The default value
     * is 0. Subclasses can relax this tolerance threshold if needed.
     *
     * <p>This threshold applies only to values of type {@code float} and {@code double};
     * it doesn't apply to integer types.</p>
     */
    protected double sampleToleranceThreshold;

    /**
     * The random number generator.
     */
    final Random random;

    /**
     * Creates a new test case using a default random number generator.
     * The sub-regions, sub-samplings and source bands will be different
     * for every test execution. If reproducible subsetting sequences are
     * needed, use the {@link #ImageIOTestCase(long)} constructor instead.
     */
    protected ImageIOTestCase() {
        random = new Random();
    }

    /**
     * Creates a new test case using a random number generator initialized to the given seed.
     *
     * @param seed  the initial seed for the random numbers generator. Use a constant value if
     *        the tests need to be reproduced with the same sequence of image parameters.
     */
    protected ImageIOTestCase(final long seed) {
        random = new Random(seed);
    }

    /**
     * Returns an iterator on a random region, subsampling and source bands of the given image.
     * The selected parameters are set in the given {@code param} object.
     *
     * @param  image  the image for which to returns an iterator.
     * @param  param  the parameter where to set the random region, subsampling and source bands.
     */
    final PixelIterator getIteratorOnRandomSubset(final RenderedImage image, final IIOParam param) {
        final Rectangle region = getBounds(image);
        if (isSubregionSupported) {
            final int dx = random.nextInt(region.width);
            final int dy = random.nextInt(region.height);
            region.x     += random.nextInt(region.width  - dx);
            region.y     += random.nextInt(region.height - dy);
            region.width  = dx + 1;
            region.height = dy + 1;
            param.setSourceRegion(region);
        }
        int xSubsampling=1, ySubsampling=1;
        if (isSubsamplingSupported) {
            xSubsampling = random.nextInt(region.width /2 + 1) + 1;
            ySubsampling = random.nextInt(region.height/2 + 1) + 1;
            int xOffset=0, yOffset=0;
            if (isSubsamplingOffsetSupported) {
                xOffset = random.nextInt(xSubsampling);
                yOffset = random.nextInt(ySubsampling);
                region.x      += xOffset;
                region.y      += yOffset;
                region.width  -= xOffset;
                region.height -= yOffset;
            }
            param.setSourceSubsampling(xSubsampling, ySubsampling, xOffset, yOffset);
        }
        int[] sourceBands = null;
        if (isSourceBandsSupported) {
            final int numBands = image.getSampleModel().getNumBands();
            sourceBands = new int[random.nextInt(numBands) + 1];
            for (int i=0; i<sourceBands.length; i++) {
                int band;
                do band = random.nextInt(numBands);
                while (contains(sourceBands, i, band));
                sourceBands[i] = band;
            }
            param.setSourceBands(sourceBands);
        }
        return new PixelIterator(image, region, xSubsampling, ySubsampling, sourceBands);
    }

    /**
     * Closes the given input or output stream if it implements the {@link Closeable} interface.
     * Do nothing otherwise.
     *
     * @param  stream  the input or output stream to close, or {@code null} if none.
     * @throws IOException if an error occurred while closing the stream.
     */
    static void close(final Object stream) throws IOException {
        if (stream instanceof Closeable) {
            ((Closeable) stream).close();
        }
    }
}
