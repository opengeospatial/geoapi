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

import java.util.Arrays;
import java.awt.Rectangle;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import javax.imageio.IIOParam;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Float.floatToIntBits;
import static java.lang.StrictMath.*;
import static org.junit.Assert.*;


/**
 * An iterator over pixels in a {@link RenderedImage}. This iterator supports multi-tiled images.
 * The traversal order is row-major.
 *
 * @author Rémi Marechal (Geomatys).
 * @author Martin Desruisseaux (Geomatys).
 * @version 3.1
 * @since   3.1
 */
final strictfp class PixelIterator {
    /**
     * The image in which to iterate.
     */
    private final RenderedImage image;

    /**
     * Current raster in which to iterate.
     */
    private Raster raster;

    /**
     * The bands to iterate over, or {@code null} if none.
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
     * Creates a row-major rendered image iterator for the whole image.
     */
    PixelIterator(final RenderedImage image) {
        this(image, null, 1, 1, null);
    }

    /**
     * Creates a row-major rendered image iterator.
     *
     * @param image        Image to iterate over.
     * @param subArea      Rectangle which represent image sub area iteration, or {@code null} if none.
     * @param xSubsampling The iteration step when moving to the next pixel.
     * @param ySubsampling The iteration step when moving to the next scan line.
     * @param sourceBands  The source bands, or {@code null} if none.
     */
    PixelIterator(final RenderedImage image, final Rectangle subArea,
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
                int nextTile = tileX + 1; // Needed only when the iteration stops before the maxX of the last tile in a row.
                tileX = divide(x - image.getTileGridXOffset(), image.getTileWidth(), false);
                if (max(nextTile, tileX) >= maxTileX) {
                    if ((y += ySubsampling) >= currentMaxY) {
                        nextTile = tileY + 1; // Needed only when the iteration stops before the maxY of the last row of tiles.
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
     * Returns the current X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the current Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the current band index.
     */
    public int getBand() {
        return (sourceBands != null) ? sourceBands[band] : band;
    }

    /**
     * Returns the current integer value from iteration.
     */
    public int getSample() {
        return raster.getSample(x, y, getBand());
    }

    /**
     * Returns the current float value from iteration.
     */
    public float getSampleFloat() {
        return raster.getSampleFloat(x, y, getBand());
    }

    /**
     * Returns the current double value from iteration.
     */
    public double getSampleDouble() {
        return raster.getSampleDouble(x, y, getBand());
    }

    /**
     * Compares all sample values iterated by this {@code PixelIterator} with the sample values
     * iterated by the given iterator. If a mismatch is found, an {@link AssertionError} is thrown
     * with a detailed error message.
     *
     * @param actual The iterator that contains the actual values to be compared with the
     *               "expected" sample values.
     * @param param  The parameter which was used for producing the actual image, or {@code null}
     *               if not. This parameter is used only for producing an error message; it has no
     *               incidence on the iteration.
     * @param tolerance The tolerance threshold for floating point comparison.
     *               This threshold does not apply to integer types.
     */
    final void assertSampleValuesEqual(final PixelIterator actual, final IIOParam param, final double tolerance) {
        final int dataType = image.getSampleModel().getDataType();
        while (next()) {
            assertTrue("Unexpected end of pixel iteration.", actual.next());
            switch (dataType) {
                case DataBuffer.TYPE_DOUBLE: {
                    final double a = actual.getSampleDouble();
                    final double e = this.  getSampleDouble();
                    if (doubleToLongBits(a) == doubleToLongBits(e)) {
                        continue; // All variants of NaN values are considered equal.
                    }
                    if (abs(a-e) <= tolerance) {
                        continue; // Negative and positive zeros are considered equal.
                    }
                    break;
                }
                case DataBuffer.TYPE_FLOAT: {
                    final float a = actual.getSampleFloat();
                    final float e = this.  getSampleFloat();
                    if (floatToIntBits(a) == floatToIntBits(e)) {
                        continue; // All variants of NaN values are considered equal.
                    }
                    if (abs(a-e) <= tolerance) {
                        continue; // Negative and positive zeros are considered equal.
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
            final StringBuilder buffer = new StringBuilder(1024);
            buffer.append("Mismatched sample value: expected ").append(ev).append(" but got ").append(av);
            buffer.append("\nPixel coordinate in the complete image: ("); position(buffer);
            buffer.append("\nPixel coordinate in the compared image: ("); actual.position(buffer);
            buffer.append('\n');
            if (param != null) {
                final Rectangle region = param.getSourceRegion();
                if (region != null) {
                    buffer.append("Source region: origin = (").append(region.x).append(", ").append(region.y)
                            .append("), size = (").append(region.width).append(", ").append(region.height).append(")\n");
                }
                buffer.append("Source subsampling: (")
                      .append(param.getSourceXSubsampling()).append(", ")
                      .append(param.getSourceYSubsampling()).append(") with offset (")
                      .append(param.getSubsamplingXOffset()).append(", ")
                      .append(param.getSubsamplingYOffset()).append(")\n");
                final int[] sourceBands = param.getSourceBands();
                if (sourceBands != null) {
                    buffer.append("Source bands: ").append(Arrays.toString(sourceBands));
                }
            }
            fail(buffer.toString());
        }
        assertFalse("Expected end of pixel iteration, but found more values.", actual.next());
    }

    /**
     * Formats the current position of this iterator in the given buffer.
     */
    private void position(final StringBuilder buffer) {
        buffer.append('(').append(getX()).append(", ").append(getY()).append(") band ").append(getBand());
    }

    /**
     * Returns a string representation of this iterator position for debugging purpose.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder(48);
        position(buffer.append(getClass().getSimpleName()).append('['));
        return buffer.append(']').toString();
    }
}
