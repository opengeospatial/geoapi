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

import java.awt.Rectangle;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Float.floatToIntBits;
import static java.lang.StrictMath.*;
import static org.junit.Assert.*;


/**
 * A row-major iterator over sample values in a {@link Raster} or {@link RenderedImage}.
 * For any image (tiled or not), this class iterates first over the <em>bands</em>, then
 * over the <em>columns</em> and finally over the <em>rows</em>. If the image is tiled,
 * then this iterator will perform the necessary calls to the {@link RenderedImage#getTile(int, int)}
 * method for each row in order to perform the iteration as if the image was untiled.
 *
 * <p>On creation, this iterator is positioned <em>before</em> the first sample value.
 * To use this iterator, invoke the {@link #next()} method in a {@code while} loop
 * as below:</p>
 *
 * {@snippet lang="java" :
 * PixelIterator it = new PixelIterator(image);
 * while (it.next()) {
 *     float value = it.getSampleFloat();
 *     // Do some processing with the value here...
 * }}
 *
 * @see org.opengis.test.Assert#assertSampleValuesEqual(String, RenderedImage, RenderedImage, double)
 *
 * @author  Rémi Marechal (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class PixelIterator {
    /**
     * The image in which to iterate.
     */
    private final RenderedImage image;

    /**
     * Current raster in which to iterate.
     */
    private Raster raster;

    /**
     * The bands to iterate over, or {@code null} if none.
     */
    private final int[] sourceBands;

    /**
     * The subsampling to apply during the iteration.
     */
    private final int xSubsampling, ySubsampling;

    /**
     * Number of bands to iterate over.
     */
    private final int numBands;

    /**
     * The iteration bounds in the image, in pixel coordinates.
     * This rectangle may span an arbitrary number of tiles.
     */
    private final int minX, maxX, maxY;

    /**
     * The iteration bounds in the image, in tile coordinates.
     */
    private final int minTileX, maxTileX, maxTileY;

    /**
     * The intersection of the iteration bounds together with the current
     * {@linkplain #raster} bounds.
     */
    private int currentMaxX, currentMaxY;

    /**
     * Current band and pixel position in the current {@linkplain #raster}.
     */
    private int band, x, y;

    /**
     * Current raster position in the {@linkplain #image}.
     */
    private int tileX, tileY;

    /**
     * Creates an iterator for the whole area of the given raster.
     *
     * @param raster The raster for which to create an iterator.
     */
    public PixelIterator(final Raster raster) {
        this(new RasterImage(raster));
    }

    /**
     * Creates an iterator for the whole area of the given image.
     *
     * @param image The image for which to create an iterator.
     */
    public PixelIterator(final RenderedImage image) {
        this(image, null, 1, 1, null);
    }

    /**
     * Creates an iterator for a sub-area of the given raster.
     *
     * @param raster        the raster to iterate over.
     * @param subArea       rectangle which represent raster sub area iteration, or {@code null} if none.
     * @param xSubsampling  the iteration step when moving to the next pixel.
     * @param ySubsampling  the iteration step when moving to the next scan line.
     * @param sourceBands   the source bands, or {@code null} if none.
     */
    public PixelIterator(final Raster raster, final Rectangle subArea,
            final int xSubsampling, final int ySubsampling, final int[] sourceBands)
    {
        this(new RasterImage(raster), subArea, xSubsampling, ySubsampling, sourceBands);
    }

    /**
     * Creates an iterator for a sub-area of the given image.
     *
     * @param image         the image to iterate over.
     * @param subArea       rectangle which represent image sub area iteration, or {@code null} if none.
     * @param xSubsampling  the iteration step when moving to the next pixel.
     * @param ySubsampling  the iteration step when moving to the next scan line.
     * @param sourceBands   the source bands, or {@code null} if none.
     */
    public PixelIterator(final RenderedImage image, final Rectangle subArea,
            final int xSubsampling, final int ySubsampling, final int[] sourceBands)
    {
        this.image        = image;
        this.numBands     = (sourceBands != null) ? sourceBands.length : image.getSampleModel().getNumBands();
        this.sourceBands  = sourceBands;
        this.xSubsampling = xSubsampling;
        this.ySubsampling = ySubsampling;

        int minX = image.getMinX();
        int minY = image.getMinY();
        int maxX = image.getWidth()  + minX;
        int maxY = image.getHeight() + minY;
        if (subArea != null) {
            minX = max(minX, subArea.x);
            minY = max(minY, subArea.y);
            maxX = min(maxX, subArea.x + subArea.width);
            maxY = min(maxY, subArea.y + subArea.height);
        }
        this.minX = minX;
        this.maxX = maxX;
        this.maxY = maxY;

        final int gridXOffset = image.getTileGridXOffset();
        final int gridYOffset = image.getTileGridYOffset();
        final int tileWidth   = image.getTileWidth();
        final int tileHeight  = image.getTileHeight();

        final int minTileY;
        minTileX = divide(minX - gridXOffset, tileWidth,  false);
        minTileY = divide(minY - gridYOffset, tileHeight, false);
        maxTileX = divide(maxX - gridXOffset, tileWidth,  true);
        maxTileY = divide(maxY - gridYOffset, tileHeight, true);

        // Initialize attributes to first iteration.
        x     = minX;
        y     = minY;
        band  = -1;
        tileX = minTileX;
        tileY = minTileY;
        updateRaster();
    }

    /**
     * Rounds the given numbers, rounding toward floor or ceil depending on the value
     * of the {@code ceil} argument. This method works for negative numerator too.
     */
    private static int divide(final int numerator, final int denominator, final boolean ceil) {
        assertTrue("Require a non-negative denominator.", denominator > 0);
        int div = numerator / denominator;
        if (ceil) {
            if (numerator > 0 && (numerator % denominator) != 0) {
                div++;
            }
        } else {
            if (numerator < 0 && (numerator % denominator) != 0) {
                div--;
            }
        }
        return div;
    }

    /**
     * Updates the {@linkplain #raster} and related fields for the current
     * {@link #tileX} and {@link #tileY} values.
     */
    private void updateRaster() {
        raster = image.getTile(tileX, tileY);
        currentMaxX = min(maxX, raster.getMinX() + raster.getWidth());
        currentMaxY = min(maxY, raster.getMinY() + raster.getHeight());
    }

    /**
     * Moves to the next sample values and returns {@code true} if the iteration has more pixels.
     *
     * @return {@code true} if the next sample value exist.
     */
    public boolean next() {
        if (++band == numBands) {
            if ((x += xSubsampling) >= currentMaxX) {
                int nextTile = tileX + 1;               // Needed only when the iteration stops before the maxX of the last tile in a row.
                tileX = divide(x - image.getTileGridXOffset(), image.getTileWidth(), false);
                if (max(nextTile, tileX) >= maxTileX) {
                    if ((y += ySubsampling) >= currentMaxY) {
                        nextTile = tileY + 1;           // Needed only when the iteration stops before the maxY of the last row of tiles.
                        tileY = divide(y - image.getTileGridYOffset(), image.getTileHeight(), false);
                        if (max(nextTile, tileY) >= maxTileY) {
                            return false;
                        }
                    }
                    x = minX;
                    tileX = minTileX;
                }
                updateRaster();
            }
            band = 0;
        }
        return true;
    }

    /**
     * Returns the current <var>x</var> coordinate. The coordinate values range from
     * {@linkplain RenderedImage#getMinX() image X minimum} (inclusive) to that minimum
     * plus the {@linkplain RenderedImage#getWidth() image width} (exclusive).
     *
     * @return the current <var>x</var> coordinate.
     *
     * @see RenderedImage#getMinX()
     * @see RenderedImage#getWidth()
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the current <var>y</var> coordinate. The coordinate values range from
     * {@linkplain RenderedImage#getMinY() image Y minimum} (inclusive) to that minimum
     * plus the {@linkplain RenderedImage#getHeight() image height} (exclusive).
     *
     * @return the current <var>y</var> coordinate.
     *
     * @see RenderedImage#getMinY()
     * @see RenderedImage#getHeight()
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the current band index. The index values range from 0 (inclusive) to
     * the {@linkplain SampleModel#getNumBands() number of bands} (exclusive), or to
     * the {@code sourceBands} array length (exclusive) if the array given to the
     * constructor was non-null.
     *
     * @return the current band index.
     *
     * @see SampleModel#getNumBands()
     */
    public int getBand() {
        return (sourceBands != null) ? sourceBands[band] : band;
    }

    /**
     * Returns the type of the sample values, as one of the {@code TYPE_*} constants
     * defined in the {@link DataBuffer} class.
     *
     * @return the type of the sample values.
     *
     * @see SampleModel#getDataType()
     * @see DataBuffer#TYPE_BYTE
     * @see DataBuffer#TYPE_SHORT
     * @see DataBuffer#TYPE_USHORT
     * @see DataBuffer#TYPE_INT
     * @see DataBuffer#TYPE_FLOAT
     * @see DataBuffer#TYPE_DOUBLE
     */
    public int getDataType() {
        return image.getSampleModel().getDataType();
    }

    /**
     * Returns the sample value at the current position, as an integer.
     * This method is appropriate for the
     * {@linkplain DataBuffer#TYPE_BYTE byte},
     * {@linkplain DataBuffer#TYPE_SHORT short},
     * {@linkplain DataBuffer#TYPE_USHORT unsigned short} and
     * {@linkplain DataBuffer#TYPE_INT integer}
     * {@linkplain #getDataType() datatypes}.
     *
     * @return the sample value at the current position.
     *
     * @see Raster#getSample(int, int, int)
     * @see DataBuffer#TYPE_BYTE
     * @see DataBuffer#TYPE_SHORT
     * @see DataBuffer#TYPE_USHORT
     * @see DataBuffer#TYPE_INT
     */
    public int getSample() {
        return raster.getSample(x, y, getBand());
    }

    /**
     * Returns the sample value at the current position, as a floating point number.
     *
     * @return the sample value at the current position.
     *
     * @see Raster#getSampleFloat(int, int, int)
     * @see DataBuffer#TYPE_FLOAT
     */
    public float getSampleFloat() {
        return raster.getSampleFloat(x, y, getBand());
    }

    /**
     * Returns the sample value at the current position, as a double-precision floating point number.
     *
     * @return the sample value at the current position.
     *
     * @see Raster#getSampleDouble(int, int, int)
     * @see DataBuffer#TYPE_DOUBLE
     */
    public double getSampleDouble() {
        return raster.getSampleDouble(x, y, getBand());
    }

    /**
     * Compares all sample values iterated by this {@code PixelIterator} with the sample values
     * iterated by the given iterator. If a mismatch is found, then an {@link AssertionError} is
     * thrown with a detailed error message.
     *
     * <p>This method does not verify the image sizes, number of tiles, number of bands, color
     * model or datatype. Consequently, this method is robust to the following differences:</p>
     *
     * <ul>
     *   <li>Differences in the ({@linkplain RenderedImage#getMinX() x},
     *       {@linkplain RenderedImage#getMinY() y}) origin;</li>
     *   <li>Differences in tile layout (images are compared as if they were untiled);</li>
     *   <li>Differences in the datatype (values are compared using the widest of this iterator
     *       {@linkplain #getDataType() datatype} and the datatype of the given iterator).</li>
     * </ul>
     *
     * If the images have different sizes, then an <cite>"Unexpected end of iteration"</cite>
     * exception will be thrown when the first iterator reaches the iteration end.
     *
     * @param  actual     the iterator that contains the actual values to be compared with the "expected" sample values.
     * @param  tolerance  the tolerance threshold for floating point comparison. This threshold does not apply to integer types.
     * @throws AssertionError if a value in this iterator is not equals to a value in the given iterator with the given
     *         tolerance threshold.
     */
    public void assertSampleValuesEqual(final PixelIterator actual, final double tolerance) throws AssertionError {
        final int dataType = Math.max(getDataType(), actual.getDataType());
        while (next()) {
            assertTrue("Unexpected end of pixel iteration.", actual.next());
            switch (dataType) {
                case DataBuffer.TYPE_DOUBLE: {
                    final double a = actual.getSampleDouble();
                    final double e = this.  getSampleDouble();
                    if (doubleToLongBits(a) == doubleToLongBits(e)) {
                        continue;                                       // All variants of NaN values are considered equal.
                    }
                    if (abs(a-e) <= tolerance) {
                        continue;                                       // Negative and positive zeros are considered equal.
                    }
                    break;
                }
                case DataBuffer.TYPE_FLOAT: {
                    final float a = actual.getSampleFloat();
                    final float e = this.  getSampleFloat();
                    if (floatToIntBits(a) == floatToIntBits(e)) {
                        continue;                                       // All variants of NaN values are considered equal.
                    }
                    if (abs(a-e) <= tolerance) {
                        continue;                                       // Negative and positive zeros are considered equal.
                    }
                    break;
                }
                default: {
                    if (actual.getSample() == getSample()) continue;
                    break;
                }
            }
            /*
             * Remainder of this block is for formatting the error message.
             */
            final Number ev, av;
            switch (dataType) {
                case DataBuffer.TYPE_DOUBLE: ev = getSampleDouble(); av = actual.getSampleDouble(); break;
                case DataBuffer.TYPE_FLOAT:  ev = getSampleFloat();  av = actual.getSampleFloat();  break;
                default:                     ev = getSample();       av = actual.getSample();       break;
            }
            final String lineSeparator = System.getProperty("line.separator", "\n");
            final StringBuilder buffer = new StringBuilder(1024);
            buffer.append("Mismatched sample value: expected ").append(ev).append(" but got ").append(av).append(lineSeparator);
            buffer.append("Pixel coordinate in the complete image: "); position(buffer); buffer.append(lineSeparator);
            buffer.append("Pixel coordinate in the compared image: "); actual.position(buffer); buffer.append(lineSeparator);
            actual.completeComparisonFailureMessage(buffer, lineSeparator);
            fail(buffer.toString());
        }
        assertFalse("Expected end of pixel iteration, but found more values.", actual.next());
    }

    /**
     * Invoked when a sample value mismatch has been found, for allowing {@link PixelIteratorForIO}
     * to append to the error message the I/O parameters used for the reading or writing process.
     */
    void completeComparisonFailureMessage(final StringBuilder buffer, final String lineSeparator) {
    }

    /**
     * Formats the current position of this iterator in the given buffer.
     */
    private void position(final StringBuilder buffer) {
        buffer.append('(').append(getX()).append(", ").append(getY()).append(") band ").append(getBand());
    }

    /**
     * Returns a string representation of this iterator position for debugging purpose.
     *
     * @return a string representation if this iterator position.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder(48);
        position(buffer.append(getClass().getSimpleName()).append('['));
        return buffer.append(']').toString();
    }
}
