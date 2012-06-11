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

import java.util.Random;
import java.io.File;
import java.io.Closeable;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.awt.image.DataBuffer;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.ImageWriteParam;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Base class for testing {@link ImageWriter} implementations.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp abstract class ImageWriterTestCase extends ImageBackendTestCase implements Closeable {
    /**
     * The image writer to test. This field must be set by subclasses
     * in the {@link #prepareImageWriter()} method.
     */
    protected ImageWriter writer;

    /**
     * The random number generator.
     */
    private final Random random;

    /**
     * {@code true} if the {@linkplain #writer} takes in account the parameter value given to
     * {@link ImageWriteParam#setSourceRegion(Rectangle)}. The default value is {@code true}.
     * Subclasses can set this flag to {@code false} when testing an incomplete
     * {@link ImageWriter} implementation.
     */
    protected boolean isSubregionSupported = true;

    /**
     * {@code true} if the {@linkplain #writer} takes in account the two first parameter values
     * given to {@link ImageWriteParam#setSourceSubsampling(int, int, int, int)}. The default value
     * is {@code true}. Subclasses can set this flag to {@code false} when testing an incomplete
     * {@link ImageWriter} implementation.
     */
    protected boolean isSubsamplingSupported = true;

    /**
     * {@code true} if the {@linkplain #writer} takes in account the two last parameter values
     * given to {@link ImageWriteParam#setSourceSubsampling(int, int, int, int)}. The default value
     * is {@code true}. Subclasses can set this flag to {@code false} when testing an incomplete
     * {@link ImageWriter} implementation.
     */
    protected boolean isSubsamplingOffsetSupported = true;

    /**
     * {@code true} if the {@linkplain #writer} takes in account the parameter value given to
     * {@link ImageWriteParam#setSourceBands(int[])}. The default value is {@code true}. Subclasses
     * can set this flag to {@code false} if this feature can not be tested for the current
     * {@link ImageWriter} implementation.
     *
     * <p>Note that this feature can not be tested with some standard writers like PNG, because
     * those readers require an explicit destination image to be specified if the number of bands
     * to read differs from the number of bands in the source image.</p>
     */
    protected boolean isSourceBandsSupported = true;

    /**
     * Creates a new test case using a default random number generator.
     * The sub-regions, sub-samplings and source bands will be different
     * for every test execution. If reproducible subsetting sequences are
     * needed, use the {@link #ImageWriterTestCase(long)} constructor instead.
     */
    protected ImageWriterTestCase() {
        random = new Random();
    }

    /**
     * Creates a new test case using a random number generator initialized to the given seed.
     * The {@link #writer} field must be initialized by sub-classes.
     *
     * @param seed The initial seed for the random numbers generator. Use a constant value if
     *        the tests need to be reproduced with the same sequence image write parameters.
     */
    protected ImageWriterTestCase(final long seed) {
        random = new Random(seed);
    }

    /**
     * Invoked when the image {@linkplain #writer} is about to be used for the first time.
     * Subclasses need to create a new {@link ImageWriter} instance if needed.
     *
     * @throws IOException If an error occurred while preparing the {@linkplain #writer}.
     */
    protected abstract void prepareImageWriter() throws IOException;

    /**
     * Returns an image reader which can be used for verifying the data written by the tested
     * image writer. This method will be invoked after a test method has written an image with
     * the {@linkplain #writer}. The given {@code input} object is usually an
     * {@link javax.imageio.stream.ImageInputStream}, but could also be a {@link File} instance
     * if the writer has written the image in a temporary file.
     * <p>
     * The default implementation gets the reader by a call to {@link ImageIO#getImageReader(ImageWriter)},
     * then {@linkplain ImageReader#setInput(Object) sets its input}. Subclasses can override this
     * method if they need to instantiate the reader in a different way.
     *
     * @param  input The input to set.
     * @return The image reader with its input initialized.
     * @throws IOException If an error occurred while creating the image reader.
     */
    protected ImageReader prepareImageReader(final Object input) throws IOException {
        final ImageReader reader = ImageIO.getImageReader(writer);
        assertNotNull("The ImageWriter does not declare a compatible reader.", reader);
        reader.setInput(input);
        return reader;
    }

    /**
     * Returns {@code true} if the given writer provider supports the given output type.
     *
     * @param  spi  The provider, or {@code null} if unknown.
     * @param  type The type to check for support.
     * @return {@code true} if the given type is supported.
     */
    private static boolean isSupportedOutput(final ImageWriterSpi spi, final Class<?> type) {
        if (spi == null) {
            /*
             * If the reader or writer does not provide an SPI, assume the standard input/output
             * type as documented in the Image I/O specification.
             */
            return ImageOutputStream.class.isAssignableFrom(type);
        }
        for (final Class<?> supportedType : spi.getOutputTypes()) {
            if (supportedType.isAssignableFrom(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prepares the image writer, then sets its output. The method will set the output to
     * an in-memory output stream if possible. If the writer does not accept output stream
     * (which is illegal according Image I/O specification, but still happen with some formats
     * like NetCDF), try to set the output as a temporary file.
     *
     * @param  capacity The initial capacity. This is an approximative value, since the
     *         actual capacity will growth as needed.
     * @return The byte buffer, or {@code null} if this method created a temporary file instead.
     * @throws IOException In an error occurred while setting the output.
     */
    private ByteArrayOutputStream prepareOutput(final int capacity) throws IOException {
        prepareImageWriter();
        assertNotNull("The 'writer' field shall be set in the 'prepareImageWriter' method.", writer);
        if (isSupportedOutput(writer.getOriginatingProvider(), ImageOutputStream.class)) {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream(capacity);
            writer.setOutput(ImageIO.createImageOutputStream(buffer));
            return buffer;
        } else if (isSupportedOutput(writer.getOriginatingProvider(), File.class)) {
            final File file = File.createTempFile("geoapi", null);
            file.deleteOnExit();
            writer.setOutput(file);
            return null;
        } else {
            throw new IIOException("Unsupported output type.");
        }
    }

    /**
     * Writes the given image in a temporary buffer using the given parameters, then read it backs.
     *
     * @param  image The image to write.
     * @param  param The parameter to use for writing the image, or {@code null}.
     * @return The the image which has been read back.
     * @throws IOException If an error occurred while writing the image or or reading it back.
     */
    private RenderedImage writeAndRead(final RenderedImage image, final ImageWriteParam param) throws IOException {
        final ByteArrayOutputStream buffer = prepareOutput(1024);
        writer.write(null, new IIOImage(image, null, null), param);
        Object data = writer.getOutput();
        close(data);
        writer.setOutput(null);
        if (buffer != null) {
            data = ImageIO.createImageInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        }
        final ImageReader reader = prepareImageReader(data);
        final RenderedImage verify = reader.read(0);
        close(reader);
        reader.dispose();
        return verify;
    }

    /**
     * Tests the writing of an image using a single band of byte values.
     * This test fills an image with random values, write it to a temporary buffer,
     * reads it back and compare the sample values.
     *
     * @throws IOException If an error occurred while writing the image or or reading it back.
     */
    @Test
    public void testOneByteBand() throws IOException {
        final BufferedImage source = createImage(DataBuffer.TYPE_BYTE, 180, 90, 1);
        fill(source.getRaster(), random);
        final RenderedImage target = writeAndRead(source, null);
        assertEquals(source.getWidth(),  target.getWidth());
        assertEquals(source.getHeight(), target.getHeight());
        // TODO: compare pixel values
    }

    /**
     * Disposes the {@link #writer} (if non-null) after each test.
     * The default implementation performs the following cleanup:
     * <p>
     * <ul>
     *   <li>If the {@linkplain ImageWriter#getOutput() writer output} is {@linkplain Closeable closeable}, closes it.</li>
     *   <li>Invokes {@link ImageWriter#reset()} for clearing the output and listeners.</li>
     *   <li>Invokes {@link ImageWriter#dispose()} for performing additional resource disposal, if any.</li>
     *   <li>Sets the {@link #writer} field to {@code null} for preventing accidental use.</li>
     * </ul>
     *
     * @throws IOException In an error occurred while closing the output stream.
     *
     * @see ImageWriter#reset()
     * @see ImageWriter#dispose()
     */
    @After
    @Override
    public void close() throws IOException {
        if (writer != null) {
            close(writer.getOutput());
            writer.reset();
            writer.dispose();
            writer = null;
        }
    }
}
