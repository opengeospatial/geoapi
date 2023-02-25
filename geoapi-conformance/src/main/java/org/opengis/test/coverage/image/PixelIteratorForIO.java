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
