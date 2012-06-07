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

import java.io.Closeable;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import javax.imageio.stream.ImageInputStream;

import org.opengis.test.TestCase;


/**
 * Base class for all tests defined in the {@code org.opengis.test.coverage.image} package.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp abstract class ImageBackendTestCase extends TestCase {
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
     * @param  image The image for which to get the bounds.
     * @return The image bounds.
     */
    static Rectangle getBounds(final RenderedImage image) {
        return new Rectangle(
            image.getMinX(),
            image.getMinY(),
            image.getWidth(),
            image.getHeight());
    }

    /**
     * Closes the given input or output stream if it implements the {@link Closeable} interface.
     * Do nothing otherwise. Note that this method perform a special check for Image I/O streams,
     * which implement the {@code Closeable} interface only since JDK7.
     *
     * @param  stream The input or output stream to close, or {@code null} if none.
     * @throws IOException In an error occurred while closing the stream.
     */
    static void close(final Object stream) throws IOException {
        if (stream instanceof Closeable) {
            ((Closeable) stream).close();
        } else if (stream instanceof ImageInputStream) {
            /*
             * ImageInputStream extends Closeable only since JDK7. For JDK6, we need an
             * explicit check. Note that we don't need to check for ImageOutputStream
             * since it extends ImageInputStream.
             */
            ((ImageInputStream) stream).close();
        }
    }
}
