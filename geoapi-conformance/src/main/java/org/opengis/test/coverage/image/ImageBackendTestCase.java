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

import java.util.Random;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.RenderedImage;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.BandedSampleModel;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import org.opengis.test.TestCase;


/**
 * Base class for all tests defined in the {@code org.opengis.test.coverage.image} package.
 * This class is abstract for now because it does not yet define public API, and only
 * {@link ImageIOTestCase} extends it. However, we may make it public in a future version
 * if we add tests for other kind of operations than Image I/O.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
abstract strictfp class ImageBackendTestCase extends TestCase {
    /**
     * Small value for comparison of sample values. Since scientific data often store their
     * values as {@code float} numbers, this {@code SAMPLE_TOLERANCE} value must be of the
     * order of {@code float} relative precision, not {@code double}.
     */
    static final float SAMPLE_TOLERANCE = 1E-5f;

    /**
     * Creates a new test case.
     */
    protected ImageBackendTestCase() {
    }

    /**
     * Returns the bounds of the given image.
     *
     * @param  image  the image for which to get the bounds.
     * @return the image bounds.
     */
    static Rectangle getBounds(final RenderedImage image) {
        return new Rectangle(
            image.getMinX(),
            image.getMinY(),
            image.getWidth(),
            image.getHeight());
    }

    /**
     * Creates a banded raster for the given data type.
     *
     * @param  dataType  the data type as one of the {@link DataBuffer} constants.
     * @param  width     the desired raster width.
     * @param  height    the desired raster height.
     * @param  numBands  the desired number of bands.
     * @return a writable raster of the given type and size.
     */
    static WritableRaster createBandedRaster(final int dataType, final int width, final int height, final int numBands) {
        switch (dataType) {
            case DataBuffer.TYPE_BYTE:
            case DataBuffer.TYPE_USHORT:
            case DataBuffer.TYPE_INT: {
                // As of JDK7, the method called below supports only the above-cited types.
                return Raster.createBandedRaster(dataType, width, height, numBands, null);
            }
            default: {
                // For all other types, we need to create the sample model ourselves.
                return Raster.createWritableRaster(new BandedSampleModel(dataType, width, height, numBands), null);
            }
        }
    }

    /**
     * Fills the given raster with random numbers.
     *
     * @param raster  the raster to fill.
     * @param random  the random numbers generator to use.
     */
    static void fill(final WritableRaster raster, final Random random) {
        final int xmin = raster.getMinX();
        final int ymin = raster.getMinY();
        final int xmax = raster.getWidth()  + xmin;
        final int ymax = raster.getHeight() + ymin;
        final int numBands = raster.getNumBands();
        final int dataType = raster.getSampleModel().getDataType();
        for (int y=ymin; y<ymax; y++) {
            for (int x=xmin; x<xmax; x++) {
                for (int b=0; b<numBands; b++) {
                    switch (dataType) {
                        default:                     raster.setSample(x, y, b, random.nextInt());    break;
                        case DataBuffer.TYPE_FLOAT:  raster.setSample(x, y, b, random.nextFloat());  break;
                        case DataBuffer.TYPE_DOUBLE: raster.setSample(x, y, b, random.nextDouble()); break;
                    }
                }
            }
        }
    }

    /**
     * Returns an initially empty image of the given type. For one-banded images of any type,
     * this method will returns a gray scale image. For three or four banded images, this method
     * will use an (A)RGB color model.
     *
     * @param  dataType  the data type as one of the {@link DataBuffer} constants.
     * @param  width     the desired raster width.
     * @param  height    the desired raster height.
     * @param  numBands  the desired number of bands.
     * @return an image of the given type and size.
     * @throws IllegalArgumentException if this method does not support the given combination
     *         of number of bands and data type.
     */
    static BufferedImage createImage(final int dataType, final int width, final int height, final int numBands) {
        final ColorModel cm;
        switch (numBands) {
            case 1: {
                cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), false, true, Transparency.OPAQUE, dataType);
                break;
            }
            case 3: {
                switch (dataType) {
                    case DataBuffer.TYPE_BYTE: return new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                    default: throw new IllegalArgumentException("Unsupported data type: " + dataType);
                }
            }
            default: {
                throw new IllegalArgumentException("Unsupported number of bands: " + numBands);
            }
        }
        return new BufferedImage(cm, cm.createCompatibleWritableRaster(width, height), false, null);
    }

    /**
     * Returns {@code true} if the given array contains the given value.
     * Only the <var>length</var> first elements are checked.
     */
    static boolean contains(final int[] array, int length, final int value) {
        while (--length >= 0) {
            if (array[length] == value) {
                return true;
            }
        }
        return false;
    }
}
