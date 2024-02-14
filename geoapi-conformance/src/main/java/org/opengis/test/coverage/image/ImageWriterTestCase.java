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

import java.io.File;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.awt.image.DataBuffer;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.ImageWriteParam;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


/**
 * Base class for testing {@link ImageWriter} implementations. This test writes different regions
 * and bands of an image at different sub-sampling levels, then read back the images and compare
 * the sample values.
 *
 * <p>To use this test, subclasses need to set the {@link #writer} field to a non-null value
 * in the {@link #prepareImageWriter(boolean)} method. Example:</p>
 *
 * {@snippet lang="java" :
 * public class MyImageWriterTest extends ImageWriterTestCase {
 *     @Override
 *     protected void prepareImageWriter(boolean optionallySetOutput) throws IOException {
 *         if (writer == null) {
 *             writer = new MyImageWriter();
 *         }
 *     }
 * }}
 *
 * The {@linkplain #writer} shall accept at least one of the following
 * {@linkplain ImageWriterSpi#getOutputTypes() output types}, in preference order:
 *
 * <ul>
 *   <li>{@link ImageOutputStream} - mandatory according Image I/O specification.</li>
 *   <li>{@link File} - fallback if the writer doesn't support {@code ImageOutputStream}.
 *       This fallback exists because {@code ImageOutputStream} is hard to support when the
 *       writer is implemented by a native library.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public abstract strictfp class ImageWriterTestCase extends ImageIOTestCase implements Closeable {
    /**
     * The prefix used for temporary files that may be created by this test case.
     * Those files are created only if a writer cannot write in an image output stream.
     */
    private static final String TEMPORARY_FILE_PREFIX = "geoapi";

    /**
     * The image writer to test. This field must be set by subclasses
     * in the {@link #prepareImageWriter(boolean)} method.
     */
    protected ImageWriter writer;

    /**
     * The reader to use for verifying the writer output. By default, this field is {@code null}
     * until a reader is first needed, in which case the field is assigned to a reader instance
     * created by {@link ImageIO#getImageReader(ImageWriter)}. Subclasses can set explicitly a
     * value to this field if they need the tests to use another reader instead.
     *
     * <p>{@code ImageWriterTestCase} will use only the {@link ImageReader#read(int)} method.
     * Consequently, this reader doesn't need to support {@code ImageReadParam} usage.</p>
     */
    protected ImageReader reader;

    /**
     * Creates a new test case using a default random number generator.
     * The sub-regions, sub-samplings and source bands will be different
     * for every test execution. If reproducible subsetting sequences are
     * needed, use the {@link #ImageWriterTestCase(long)} constructor instead.
     */
    protected ImageWriterTestCase() {
        super();
    }

    /**
     * Creates a new test case using a random number generator initialized to the given seed.
     *
     * @param seed  the initial seed for the random numbers generator. Use a constant value if
     *        the tests need to be reproduced with the same sequence of image write parameters.
     */
    protected ImageWriterTestCase(final long seed) {
        super(seed);
    }

    /**
     * Invoked when the image {@linkplain #writer} is about to be used for the first time.
     * Subclasses need to create a new {@link ImageWriter} instance if needed.
     *
     * <p>If the {@code optionallySetOutput} argument is {@code true}, then subclasses can optionally
     * {@linkplain ImageWriter#setOutput(Object) set the output} to a temporary file or other object
     * suitable to the writer. This operation is optional: if no output has been explicitly set,
     * {@code ImageWriterTestCase} will automatically set the output to an in-memory stream or to
     * a temporary file.</p>
     *
     * <p><b>Example:</b></p>
     * {@snippet lang="java" :
     * @Override
     * protected void prepareImageWriter(boolean optionallySetOutput) throws IOException {
     *     if (writer == null) {
     *         writer = new MyImageWriter();
     *     }
     *     if (optionallySetOutput) {
     *         writer.setOutput(output);                  // Optional operation.
     *     }
     * }}
     *
     * This method may be invoked with a {@code false} argument value when the methods to be
     * tested do not need an output, for example {@link ImageWriter#canWriteRasters()}.
     *
     * @param  optionallySetOutput {@code true} if this method can {@linkplain ImageWriter#setOutput(Object)
     *         set the writer output} (optional operation), or {@code false} if this is not yet necessary.
     * @throws IOException if an error occurred while preparing the {@linkplain #writer}.
     */
    protected abstract void prepareImageWriter(boolean optionallySetOutput) throws IOException;

    /**
     * Completes stream or image metadata to be given to the tested {@linkplain #writer}.
     * This method is invoked after the default metadata have been created, and before they
     * are given to the tested image writer, as below:
     *
     * <p><b>For stream metadata:</b></p>
     * {@snippet lang="java" :
     * IIOMetadata metadata = writer.getDefaultStreamMetadata}(param);
     * if (metadata != null) {
     *     completeImageMetadata(metadata, null);
     * }}
     *
     * <p><b>For image metadata:</b></p>
     * {@snippet lang="java" :
     * IIOMetadata metadata = writer.getDefaultImageMetadata(ImageTypeSpecifier.createFromRenderedImage(image), param);
     * if (metadata != null) {
     *     completeImageMetadata(metadata, image);
     * }}
     *
     * The default implementation does nothing (note: this may change in a future version).
     * Subclasses can override this method for providing custom metadata.
     *
     * @param  metadata  the stream or image metadata to complete before to be given to the tested image writer.
     * @param  image     the image for which to create image metadata, or {@code null} for stream metadata.
     * @throws IOException if the implementation needs to perform an I/O operation and that operation failed.
     *
     * @see ImageWriter#getDefaultStreamMetadata(ImageWriteParam)
     * @see ImageWriter#getDefaultImageMetadata(ImageTypeSpecifier, ImageWriteParam)
     */
    protected void completeImageMetadata(final IIOMetadata metadata, final RenderedImage image) throws IOException {
    }

    /**
     * Returns {@code true} if the given reader provider supports the given input type. If the
     * given provider is {@code null}, then this method conservatively assumes that the type is
     * supported on the assumption that the user provided an incomplete {@link ImageReader}
     * implementation, but his reader input type is consistent with his writer output type.
     *
     * @param  spi   the provider to filter.
     * @param  type  the input type.
     * @return whether the provider supports the given type.
     *
     * @see #closeAndRead(ByteArrayOutputStream)
     */
    private static boolean isSupportedInput(final ImageReaderSpi spi, final Class<?> type) {
        if (spi == null) {
            return true;
        }
        for (final Class<?> supportedType : spi.getInputTypes()) {
            if (supportedType.isAssignableFrom(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code true} if the given writer provider supports the given output type.
     * If the given provider is {@code null}, then this method assumes that the standard
     * {@link ImageOutputStream} type is expected as in the Image I/O specification.
     *
     * @param  spi   the provider to filter.
     * @param  type  the output type.
     * @return whether the provider supports the given type.
     *
     * @see #open(int)
     */
    private static boolean isSupportedOutput(final ImageWriterSpi spi, final Class<?> type) {
        if (spi == null) {
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
     * Returns {@code true} if the writer can writes the given image.
     * If no writer provider is found, then this method assumes {@code true}.
     *
     * <p>This method also performs an opportunist validation of the image writer provider.</p>
     *
     * @param  image  the image to filter.
     * @return whether the writer can encode the given image.
     * @throws IOException if the writer cannot be prepared.
     */
    private boolean canEncodeImage(final RenderedImage image) throws IOException {
        prepareImageWriter(false);
        if (writer != null) {
            final ImageWriterSpi spi = writer.getOriginatingProvider();
            validators.validate(spi);
            if (spi != null) {
                return spi.canEncodeImage(image);
            }
        }
        return true;
    }

    /**
     * Sets the image writer output to a temporary buffer or a temporary file. If the writer does
     * not accept {@link ImageOutputStream} (which is illegal according Image I/O specification,
     * but still happen with some formats like netCDF), then this method will try to set the output
     * to a temporary file.
     *
     * @param  capacity  the initial capacity. This is an approximated value, since the actual capacity will growth as needed.
     * @return the byte buffer, or {@code null} if this method created a temporary file instead.
     * @throws IOException In an error occurred while setting the output.
     */
    private ByteArrayOutputStream open(final int capacity) throws IOException {
        assertNotNull(writer, "The 'writer' field shall be set at construction time or in a method annotated by @Before.");
        if (writer.getOutput() != null) {
            return null;                                // The output has been set by the user himself.
        }
        final ImageWriterSpi spi = writer.getOriginatingProvider();
        if (isSupportedOutput(spi, OutputStream.class)) {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream(capacity);
            writer.setOutput(buffer);
            return buffer;
        } else if (isSupportedOutput(spi, ImageOutputStream.class)) {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream(capacity);
            writer.setOutput(ImageIO.createImageOutputStream(buffer));
            return buffer;
        } else if (isSupportedOutput(spi, File.class)) {
            String suffix = null;
            final String[] suffixes = spi.getFileSuffixes();
            if (suffixes != null && suffixes.length != 0) {
                suffix = suffixes[0];
                if (!suffix.isEmpty() && suffix.charAt(0) != '.') {
                    suffix = '.' + suffix;
                }
            }
            final File file = File.createTempFile(TEMPORARY_FILE_PREFIX, suffix);
            file.deleteOnExit();
            writer.setOutput(file);
            return null;
        } else {
            throw new IIOException("Unsupported output type.");
        }
    }

    /**
     * Closes the image writer output, then reads its content. This method is invoked after a test
     * method has wrote an image with the {@linkplain #writer}.
     *
     * <p>This method will use only the {@link ImageReader#read(int)} method, so the reader doesn't
     * need to support fully the {@code ImageReadParam}.</p>
     *
     * @param  buffer  the buffer returned by {@link #open(int)}, which may be {@code null}.
     * @return the image.
     * @throws IOException if an error occurred while closing the output or reading the image.
     */
    private RenderedImage closeAndRead(final ByteArrayOutputStream buffer) throws IOException {
        Object input = writer.getOutput();
        close(input);
        writer.setOutput(null);
        if (reader == null) {
            reader = ImageIO.getImageReader(writer);
            assertNotNull(reader, "The ImageWriter does not declare a compatible reader.");
        }
        if (buffer != null) {
            input = ImageIO.createImageInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        }
        if (!isSupportedInput(reader.getOriginatingProvider(), input.getClass())) {
            /*
             * If the reader doesn't support the given input type, try to wrap it in an
             * ImageInputStream - which is mandatory according Image I/O specification.
             * If we cannot wrap it, process anyway and let the reader throws the exception.
             */
            if (!(input instanceof ImageInputStream)) {
                final ImageInputStream in = ImageIO.createImageInputStream(input);
                if (in != null) {
                    input = in;
                }
            }
        }
        reader.setInput(input);
        try {
            return reader.read(0);
        } finally {
            reader.setInput(null);
            close(input);
        }
    }

    /**
     * Writes random subsets of the given image, reads back the image and compares the sample
     * values. This method sets the {@link ImageWriteParam} parameters to random sub-regions,
     * sub-samplings and source bands values and invokes {@link ImageWriter#write(IIOMetadata,
     * IIOImage, ImageWriteParam)}.
     *
     * <p>The above method call is repeated {@code numIterations} time with different parameters.
     * The kind of parameters to be tested is controlled by the {@code isXXXSupported} boolean
     * fields in this class.</p>
     *
     * @param  image          the image to write.
     * @param  numIterations  maximum number of iterations to perform.
     * @throws IOException if an error occurred while writing the image or reading it back.
     */
    private void writeRandomSubsets(final RenderedImage image, final int numIterations) throws IOException {
        for (int iterationCount=0; iterationCount<numIterations; iterationCount++) {
            prepareImageWriter(true);       // Give a chance to subclasses to set their own output.
            final ImageWriteParam param = writer.getDefaultWriteParam();
            final PixelIterator expected = getIteratorOnRandomSubset(image, param);
            final ByteArrayOutputStream buffer = open(1024);
            final IIOMetadata streamMetadata = writer.getDefaultStreamMetadata(param);
            if (streamMetadata != null) {
                completeImageMetadata(streamMetadata, null);
            }
            final IIOMetadata imageMetadata = writer.getDefaultImageMetadata(ImageTypeSpecifier.createFromRenderedImage(image), param);
            if (imageMetadata != null) {
                completeImageMetadata(imageMetadata, image);
            }
            writer.write(streamMetadata, new IIOImage(image, null, imageMetadata), param);
            final RenderedImage actual = closeAndRead(buffer);
            expected.assertSampleValuesEqual(new PixelIteratorForIO(actual, param), sampleToleranceThreshold);
        }
    }

    /**
     * Implementation of the {@code testFooWrite()} methods.
     *
     * @param  image  the image to write.
     * @throws IOException if an error occurred while writing the image or reading it back.
     */
    private void testImageWrites(final RenderedImage image) throws IOException {
        final boolean subregion   = isSubregionSupported;
        final boolean subsampling = isSubsamplingSupported;
        final boolean offset      = isSubsamplingOffsetSupported;
        final boolean bands       = isSourceBandsSupported;
        final boolean actualBands = bands && image.getSampleModel().getNumBands() > 1;
        /*
         * Writes the complete image.
         */
        isSubregionSupported         = false;
        isSubsamplingSupported       = false;
        isSubsamplingOffsetSupported = false;
        isSourceBandsSupported       = false;
        writeRandomSubsets(image, 1);
        /*
         * Tests writing sub-regions only (no subsampling).
         */
        if (subregion) {
            isSubregionSupported = true;
            writeRandomSubsets(image, DEFAULT_NUM_ITERATIONS);
            isSubregionSupported = false;
        }
        /*
         * Tests writing the complete region with various subsamplings.
         */
        if (subsampling) {
            isSubsamplingSupported = true;
            writeRandomSubsets(image, DEFAULT_NUM_ITERATIONS);
            isSubsamplingSupported = false;
        }
        /*
         * Tests writing the complete image with different source bands.
         */
        if (actualBands) {
            isSourceBandsSupported = true;
            writeRandomSubsets(image, DEFAULT_NUM_ITERATIONS);
            isSourceBandsSupported = false;
        }
        /*
         * Mixes all.
         */
        isSubregionSupported         = subregion;
        isSubsamplingSupported       = subsampling;
        isSubsamplingOffsetSupported = offset;
        isSourceBandsSupported       = bands;
        if (subregion | subsampling | offset | actualBands) {
            writeRandomSubsets(image, DEFAULT_NUM_ITERATIONS);
        }
    }

    /**
     * Tests the {@link ImageWriter#write(IIOMetadata, IIOImage, ImageWriteParam) ImageWriter.write}
     * method for a single band of byte values. First, this method creates an single-banded image
     * filled with random byte values. Then, this method invokes write the image an arbitrary amount
     * of time for the following configurations (note: any {@code isXXXSupported} field
     * which was set to {@code false} prior the execution of this test will stay {@code false}):
     *
     * <ul>
     *   <li>Writes the full image once (all {@code isXXXSupported} fields set to {@code false}).</li>
     *   <li>Writes various sub-regions (only {@link #isSubregionSupported isSubregionSupported} may be {@code true})</li>
     *   <li>Writes at various sub-sampling (only {@link #isSubsamplingSupported isSubsamplingSupported} may be {@code true})</li>
     *   <li>Reads various bands (only {@link #isSourceBandsSupported isSourceBandsSupported} may be {@code true})</li>
     *   <li>A mix of sub-regions, sub-sampling and source bands</li>
     * </ul>
     *
     * Then the image is read again and the pixel values are compared with the corresponding
     * pixel values of the original image.
     *
     * @throws IOException if an error occurred while writing the image or or reading it back.
     */
    @Test
    public void testOneByteBand() throws IOException {
        final BufferedImage image = createImage(DataBuffer.TYPE_BYTE, 180, 90, 1);
        fill(image.getRaster(), random);
        assumeTrue(canEncodeImage(image), "Skipped because the writer cannot encode the test image.");
        testImageWrites(image);
    }

    /**
     * Same test as {@link #testOneByteBand()}, but using RGB values in three bands.
     *
     * @throws IOException if an error occurred while writing the image or or reading it back.
     */
    @Test
    public void testThreeByteBands() throws IOException {
        final BufferedImage image = createImage(DataBuffer.TYPE_BYTE, 180, 90, 3);
        fill(image.getRaster(), random);
        assumeTrue(canEncodeImage(image), "Skipped because the writer cannot encode the test image.");
        testImageWrites(image);
    }

    /**
     * Same test as {@link #testOneByteBand()}, but using the signed {@code short} type.
     *
     * @throws IOException if an error occurred while writing the image or or reading it back.
     */
    @Test
    public void testOneShortBand() throws IOException {
        final BufferedImage image = createImage(DataBuffer.TYPE_SHORT, 180, 90, 1);
        fill(image.getRaster(), random);
        assumeTrue(canEncodeImage(image), "Skipped because the writer cannot encode the test image.");
        testImageWrites(image);
    }

    /**
     * Same test as {@link #testOneByteBand()}, but using the unsigned {@code short} type.
     *
     * @throws IOException if an error occurred while writing the image or or reading it back.
     */
    @Test
    public void testOneUnsignedShortBand() throws IOException {
        final BufferedImage image = createImage(DataBuffer.TYPE_USHORT, 180, 90, 1);
        fill(image.getRaster(), random);
        assumeTrue(canEncodeImage(image), "Skipped because the writer cannot encode the test image.");
        testImageWrites(image);
    }

    /**
     * Same test as {@link #testOneByteBand()}, but using the signed {@code int} type.
     *
     * @throws IOException if an error occurred while writing the image or or reading it back.
     */
    @Test
    public void testOneIntBand() throws IOException {
        final BufferedImage image = createImage(DataBuffer.TYPE_INT, 180, 90, 1);
        fill(image.getRaster(), random);
        assumeTrue(canEncodeImage(image), "Skipped because the writer cannot encode the test image.");
        testImageWrites(image);
    }

    /**
     * Same test as {@link #testOneByteBand()}, but using the signed {@code float} type.
     *
     * @throws IOException if an error occurred while writing the image or or reading it back.
     */
    @Test
    public void testOneFloatBand() throws IOException {
        final BufferedImage image = createImage(DataBuffer.TYPE_FLOAT, 180, 90, 1);
        fill(image.getRaster(), random);
        assumeTrue(canEncodeImage(image), "Skipped because the writer cannot encode the test image.");
        testImageWrites(image);
    }

    /**
     * Same test as {@link #testOneByteBand()}, but using the signed {@code double} type.
     *
     * @throws IOException if an error occurred while writing the image or or reading it back.
     */
    @Test
    public void testOneDoubleBand() throws IOException {
        final BufferedImage image = createImage(DataBuffer.TYPE_DOUBLE, 180, 90, 1);
        fill(image.getRaster(), random);
        assumeTrue(canEncodeImage(image), "Skipped because the writer cannot encode the test image.");
        testImageWrites(image);
    }

    /**
     * Disposes the {@linkplain #reader} and the {@linkplain #writer} (if non-null) after each test.
     * The default implementation performs the following cleanup:
     *
     * <ul>
     *   <li>If the {@linkplain ImageWriter#getOutput() writer output} is {@linkplain Closeable closeable}, closes it.</li>
     *   <li>Invokes {@link ImageWriter#reset()} for clearing the output and listeners.</li>
     *   <li>Invokes {@link ImageWriter#dispose()} for performing additional resource disposal, if any.</li>
     *   <li>Sets the {@link #writer} field to {@code null} for preventing accidental use.</li>
     *   <li>Performs the same steps as above for the {@linkplain #reader}, if non-null.</li>
     * </ul>
     *
     * @throws IOException if an error occurred while closing the output stream.
     *
     * @see ImageWriter#reset()
     * @see ImageWriter#dispose()
     */
    @Override
    @AfterEach
    public void close() throws IOException {
        if (writer != null) {
            close(writer.getOutput());
            writer.reset();
            writer.dispose();
            writer = null;
        }
        if (reader != null) {
            close(reader.getInput());
            reader.reset();
            reader.dispose();
            reader = null;
        }
    }
}
