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

import java.util.Arrays;
import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import javax.imageio.IIOParam;


/**
 * Iterates over the pixel of an image resulting form I/O operation. The only difference between
 * this iterator and the public one is a more detailed error message in case of comparison failure.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final strictfp class PixelIteratorForIO extends PixelIterator {
    /**
     * The parameter which was used for producing the actual image, or {@code null} if not. This
     * parameter is used only for producing an error message; it has no incidence on the iteration.
     */
    private final IIOParam param;

    /**
     * Creates a new iterator for the given image, which has been read using the given parameters.
     *
     * @param image  the image to iterate over.
     * @param param  the parameter which was used for producing the actual image, or {@code null}
     *               if not. This parameter is used only for producing an error message; it has no
     *               incidence on the iteration.
     */
    PixelIteratorForIO(final RenderedImage image, final IIOParam param) {
        super(image);
        this.param = param;
    }

    /**
     * Invoked when a sample value mismatch has been found. This method appends
     * the error message the I/O parameters used for the reading or writing process.
     */
    @Override
    void completeComparisonFailureMessage(final StringBuilder buffer, final String lineSeparator) {
        if (param != null) {
            final Rectangle region = param.getSourceRegion();
            if (region != null) {
                buffer.append("Source region: origin = (").append(region.x).append(", ").append(region.y)
                        .append("), size = (").append(region.width).append(", ").append(region.height).append(')').append(lineSeparator);
            }
            buffer.append("Source subsampling: (")
                    .append(param.getSourceXSubsampling()).append(", ")
                    .append(param.getSourceYSubsampling()).append(") with offset (")
                    .append(param.getSubsamplingXOffset()).append(", ")
                    .append(param.getSubsamplingYOffset()).append(')').append(lineSeparator);
            final int[] sourceBands = param.getSourceBands();
            if (sourceBands != null) {
                buffer.append("Source bands: ").append(Arrays.toString(sourceBands)).append(lineSeparator);
            }
        }
    }
}
