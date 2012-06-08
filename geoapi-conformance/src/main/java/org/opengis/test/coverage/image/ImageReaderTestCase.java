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
import java.util.Random;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import javax.imageio.ImageReader;
import javax.imageio.ImageReadParam;

import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;


/**
 * Base class for testing {@link ImageReader} implementations. This test reads an image
 * in different sub-regions, at different sub-sampling levels and reading different bands,
 * and compares the results with the complete image.
 *
 * <p>To use this test, subclasses need to set the {@link #reader} field to a non-null value in
 * the {@link #prepareImageReader()} method. The {@linkplain ImageReader#getInput() reader input}
 * shall be set by the subclass when requested by the caller. Example:</p>
 *
 * <blockquote><pre>public class MyImageReaderTest extends ImageReaderTestCase {
 *    &#64;Override
 *    protected void prepareImageReader(boolean needsInput) throws IOException {
 *        if (reader == null) {
 *            reader = new MyImageReader();
 *        }
 *        if (needsInput) {
 *            reader.setInput(ImageIO.createImageInputStream(new File("MyTestImage")));
 *        }
 *    }
 *}</pre></blockquote>
 *
 * <p>Subclasses inherit the following tests:</p>
 * <ul>
 *   <li>{@link #testReadAsBufferedImage()} - to test the {@link ImageReader#read(int, ImageReadParam)} method.</li>
 *   <li>{@link #testReadAsRenderedImage()} - to test the {@link ImageReader#readAsRenderedImage(int, ImageReadParam)} method.</li>
 *   <li>{@link #testReadAsRaster()} - to test the {@link ImageReader#readRaster(int, ImageReadParam)} method.</li>
 * </ul>
 *
 * <p>In addition, subclasses may consider to override the following methods:</p>
 * <ul>
 *   <li>{@link #dispose()} - to modify the policy of {@link #reader} disposal.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp abstract class ImageReaderTestCase extends ImageBackendTestCase {
    /**
     * The {@link ImageReader} API to use for testing read operations.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    private static enum API {
        /**
         * Use the {@link ImageReader#read(int, ImageReadParam)} method.
         */
        READ,

        /**
         * Use the {@link ImageReader#readAsRenderedImage(int, ImageReadParam)} method.
         */
        READ_AS_RENDERED_IMAGE,

        /**
         * Use the {@link ImageReader#readRaster(int, ImageReadParam)} method.
         */
        READ_RASTER
    }

    /**
     * Default number of iterations when testing the image read operations.
     */
    private static final int DEFAULT_NUM_ITERATIONS = 10;

    /**
     * The image reader to test. This field must be set by subclasses
     * in the {@link #prepareImageReader()} method.
     */
    protected ImageReader reader;

    /**
     * The random number generator.
     */
    private final Random random;

    /**
     * {@code true} if the {@linkplain #reader} takes in account the parameter value given to
     * {@link ImageReadParam#setSourceRegion(Rectangle)}. The default value is {@code true}.
     * Subclasses can set this flag to {@code false} when testing an incomplete
     * {@link ImageReader} implementation.
     */
    protected boolean isSubregionSupported = true;

    /**
     * {@code true} if the {@linkplain #reader} takes in account the two first parameter values
     * given to {@link ImageReadParam#setSourceSubsampling(int, int, int, int)}. The default value
     * is {@code true}. Subclasses can set this flag to {@code false} when testing an incomplete
     * {@link ImageReader} implementation.
     */
    protected boolean isSubsamplingSupported = true;

    /**
     * {@code true} if the {@linkplain #reader} takes in account the two last parameter values
     * given to {@link ImageReadParam#setSourceSubsampling(int, int, int, int)}. The default value
     * is {@code true}. Subclasses can set this flag to {@code false} when testing an incomplete
     * {@link ImageReader} implementation.
     */
    protected boolean isSubsamplingOffsetSupported = true;

    /**
     * {@code true} if the {@linkplain #reader} takes in account the parameter value given to
     * {@link ImageReadParam#setSourceBands(int[])}. The default value is {@code true}. Subclasses
     * can set this flag to {@code false} if this feature can not be tested for the current
     * {@link ImageReader} implementation.
     *
     * <p>Note that this feature can not be tested with some standard readers like PNG, because
     * those readers require an explicit destination image to be specified if the number of bands
     * to read differs from the number of bands in the source image.</p>
     */
    protected boolean isSourceBandsSupported = true;

    /**
     * Creates a new test case using a default random number generator.
     * The sub-regions, sub-samplings and source bands will be different
     * for every text execution. If reproducible subsetting sequences are
     * needed, use the {@link #ImageReaderTestCase(long)} constructor instead.
     */
    protected ImageReaderTestCase() {
        random = new Random();
    }

    /**
     * Creates a new test case using a random number generator initialized to the given seed.
     * The {@link #reader} field must be initialized by sub-classes.
     *
     * @param seed The initial seed for the random numbers generator. Use a constant value if
     *        the tests need to be reproduced with the same sequence image read parameters.
     */
    protected ImageReaderTestCase(final long seed) {
        random = new Random(seed);
    }

    /**
     * Asserts that the {@linkplain ImageReader#getInput() reader input} is set to a non-null value.
     */
    private static void assertInputSet(final ImageReader reader) {
        assertNotNull("The 'reader' field shall be set in the constructor or in a @Before method.", reader);
        assertNotNull("reader.setInput(Object) shall be invoked before any test is run.", reader.getInput());
    }

    /**
     * Invoked when the image {@linkplain #reader} is about to be used for the first time, or when
     * its {@linkplain ImageReader#getInput() input} needs to be reinitialized. Subclasses need to
     * create a new {@link ImageReader} instance if needed and set its input in this method.
     *
     * <p>This method may be invoked more than once during the same test if the input became invalid.
     * This may occur because the tests will read the same image many time in different ways, and not
     * all input streams can seek back to the beginning of the image stream. The input is considered
     * invalid if {@link ImageReader#getMinIndex()} returns a value greater than the index of the
     * image to read. In such case, the input is {@linkplain java.io.Closeable#close() closed} by
     * the caller and this method needs to set a fresh {@link javax.imageio.stream.ImageInputStream}.</p>
     *
     * <p>Example:</p>
     * <blockquote><pre>&#64;Override
     *protected void prepareImageReader() throws IOException {
     *    if (reader == null) {
     *        reader = new MyImageReader();
     *    }
     *    if (needsInput) {
     *        reader.setInput(ImageIO.createImageInputStream(new File("MyTestImage")));
     *    }
     *}</pre></blockquote>
     *
     * @param  needsInput {@code true} if this method shall {@linkplain ImageReader#setInput(Object)
     *         set the reader input}, or {@code false} if this is not yet necessary.
     * @throws IOException If an error occurred while preparing the {@linkplain #reader}.
     */
    protected abstract void prepareImageReader(boolean needsInput) throws IOException;

    /**
     * Reads random subsets of the image at the given index, and compares the result with the
     * given complete image. This method sets the {@link ImageReadParam} parameters to random
     * sub-regions, sub-samplings and source bands values and invokes one of the following
     * methods as determined by the {@code api} argument:
     * <p>
     * <ul>
     *   <li><code>{@link ImageReader#read(int, ImageReadParam)}</code></li>
     *   <li><code>{@link ImageReader#readAsRenderedImage(int, ImageReadParam)}</code></li>
     *   <li><code>{@link ImageReader#readRaster(int, ImageReadParam)}</code></li>
     * </ul>
     * <p>
     * The above method call is repeated {@code numIterations} time with different parameters.
     * The kind of parameters to be tested is controlled by the {@code isXXXSupported} boolean
     * fields in this class.
     * <p>
     * The pixel values for each image resulting from the above read operations are
     * compared with the corresponding pixel values of the given complete image.
     *
     * @param  completeImage The complete image as returned by <code>{@linkplain #reader}.{@link ImageReader#read(int) read}(imageIndex)</code> without read parameters.
     * @param  api           The API to use for reading the images.
     * @param  imageIndex    Index of the images to read.
     * @param  numIterations Maximum number of iterations to perform.
     * @throws IOException If an error occurred while reading the image.
     */
    private void readRandomSubsets(final RenderedImage completeImage, final API api,
            final int imageIndex, final int numIterations) throws IOException
    {
        final ImageReader reader = this.reader; // Protect from changes.
        assertInputSet(reader);
        final Rectangle completeRegion = getBounds(completeImage);
        final int numBands = completeImage.getSampleModel().getNumBands();
        final int dataType = completeImage.getSampleModel().getDataType();
        for (int iterationCount=0; iterationCount<numIterations; iterationCount++) {
            if (reader.getMinIndex() > imageIndex) {
                close(reader.getInput());
                reader.setInput(null);
                prepareImageReader(true);
            }
            final ImageReadParam param = reader.getDefaultReadParam();
            Rectangle region = new Rectangle(completeRegion);
            Rectangle iteratorRegion = region;
            if (isSubregionSupported) {
                region.x     += random.nextInt(region.width);
                region.y     += random.nextInt(region.height);
                region.width  = random.nextInt(region.width)  + 1;
                region.height = random.nextInt(region.height) + 1;
                region = region.intersection(completeRegion);
                iteratorRegion.setBounds(region);
                param.setSourceRegion(region);
            }
            int xSubsampling=1, ySubsampling=1, xOffset=0, yOffset=0;
            if (isSubsamplingSupported) {
                xSubsampling = random.nextInt(iteratorRegion.width)  + 1;
                ySubsampling = random.nextInt(iteratorRegion.height) + 1;
                if (isSubsamplingOffsetSupported) {
                    xOffset = random.nextInt(xSubsampling);
                    yOffset = random.nextInt(ySubsampling);
                    if (iteratorRegion == region) {
                        iteratorRegion = new Rectangle(region);
                    }
                    iteratorRegion.x      += xOffset;
                    iteratorRegion.y      += yOffset;
                    iteratorRegion.width  -= xOffset;
                    iteratorRegion.height -= yOffset;
                }
                param.setSourceSubsampling(xSubsampling, ySubsampling, xOffset, yOffset);
            }
            int[] sourceBands = null;
            if (isSourceBandsSupported) {
                sourceBands = new int[random.nextInt(numBands) + 1];
                for (int i=0; i<sourceBands.length; i++) {
                    int band;
                    do band = random.nextInt(numBands);
                    while (contains(sourceBands, i, band));
                    sourceBands[i] = band;
                }
                param.setSourceBands(sourceBands);
            }
            /*
             * At this point, we are ready to read the image and compare the global attributes.
             * The read method to use depends on the API specified by the caller, which is
             * different for each 'testFoo()' method declared in the class.
             */
            final RenderedImage image;
            switch (api) {
                case READ: {
                    image = reader.read(imageIndex, param);
                    break;
                }
                case READ_AS_RENDERED_IMAGE: {
                    image = reader.readAsRenderedImage(imageIndex, param);
                    break;
                }
                case READ_RASTER: {
                    image = new RasterImage(reader.readRaster(imageIndex, param));
                    break;
                }
                default: throw new IllegalArgumentException(api.toString());
            }
            assertEquals("RenderedImage.getWidth()",  (iteratorRegion.width  + xSubsampling-1) / xSubsampling, image.getWidth());
            assertEquals("RenderedImage.getHeight()", (iteratorRegion.height + ySubsampling-1) / ySubsampling, image.getHeight());
            final PixelIterator actual   = new PixelIterator(image);
            final PixelIterator expected = new PixelIterator(completeImage, iteratorRegion, xSubsampling, ySubsampling, sourceBands);
            while (expected.next()) {
                assertTrue("Unexpected end of pixel iteration.", actual.next());
                final boolean equals;
                switch (dataType) {
                    case DataBuffer.TYPE_DOUBLE: {
                        equals = (Double.doubleToLongBits(actual.getSampleDouble()) ==
                                  Double.doubleToLongBits(expected.getSampleDouble()));
                        break;
                    }
                    case DataBuffer.TYPE_FLOAT: {
                        equals = (Float.floatToIntBits(actual.getSampleFloat()) ==
                                  Float.floatToIntBits(expected.getSampleFloat()));
                        break;
                    }
                    default: {
                        equals = (actual.getSample() == expected.getSample());
                        break;
                    }
                }
                if (!equals) {
                    final Number ev, av;
                    switch (dataType) {
                        case DataBuffer.TYPE_DOUBLE: ev = expected.getSampleDouble(); av = actual.getSampleDouble(); break;
                        case DataBuffer.TYPE_FLOAT:  ev = expected.getSampleFloat();  av = actual.getSampleFloat();  break;
                        default:                     ev = expected.getSample();       av = actual.getSample();       break;
                    }
                    fail("Mismatched sample value: expected " + ev + " but got " + av + '\n'
                       + "Pixel coordinate in the complete image: (" + expected.getX() + ", " + expected.getY() + ") band " + expected.getBand() + '\n'
                       + "Pixel coordinate in the tested image: (" + actual.getX() + ", " + actual.getY() + ") band " + actual.getBand() + '\n'
                       + "Source region: origin = (" + region.x + ", " + region.y + "), size = (" + region.width + ", " + region.height + ")\n"
                       + "Source subsampling: (" + xSubsampling + ", " + ySubsampling + ") with offset (" + xOffset + ", " + yOffset + ")\n"
                       + "Source bands: " + Arrays.toString(sourceBands) + '\n');
                }
            }
        }
    }

    /**
     * Returns {@code true} if the given array contains the given value.
     * Only the <var>n</var> first elements are checked.
     */
    private static boolean contains(final int[] array, int n, final int value) {
        while (--n >= 0) {
            if (array[n] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tests the {@link ImageReader#read(int, ImageReadParam) ImageReader.read} method.
     * First, this method reads the full image with a call to {@link ImageReader#read(int)}.
     * Then, this method invokes {@link ImageReader#read(int, ImageReadParam)} an arbitrary
     * amount of time for the following configurations (note: any {@code isXXXSupported} field
     * which was set to {@code false} prior the execution of this test will stay {@code false}):
     * <p>
     * <ul>
     *   <li>Reads the full image once (all {@code isXXXSupported} fields set to {@code false}).</li>
     *   <li>Reads various sub-regions (only {@link #isSubregionSupported} may be {@code true})</li>
     *   <li>Reads at various sub-sampling (only {@link #isSubsamplingSupported may be {@code true})</li>
     *   <li>A mix of sub-regions, sub-sampling and source bands</li>
     * </ul>
     *
     * <p>The pixel values for each image resulting from the above read operations are
     * compared with the corresponding pixel values of the complete image.</p>
     *
     * <blockquote><font size="-1"><p><b>Implementation note:</b> in the spirit of JUnit, this test
     * should have been splitted in smaller test cases: one for sub-regions, one for sub-samplings,
     * <i>etc</i>. However in this particular case, consolidation of those tests in a single method
     * provides the following benefits:</p>
     * <ul>
     *   <li>The potentially large complete image is read only once.</li>
     *   <li>If the tests for subregions or subsamplings fails, avoid the test mixing both.</li>
     *   <li>Less methods to override if the implementor want to provide his own test.</li>
     * </ul>
     * </font></blockquote>
     *
     * @throws IOException If an error occurred while reading the image.
     */
    @Test
    public void testReadAsBufferedImage() throws IOException {
        testImageReads(API.READ);
    }

    /**
     * Tests the {@link ImageReader#readAsRenderedImage(int, ImageReadParam) ImageReader.readAsRenderedImage} method.
     * This method performs the same test than {@link #testReadAsBufferedImage()}, except that the
     * {@link ImageReader#readAsRenderedImage(int, ImageReadParam)} method is invoked instead than
     * {@code ImageReader.read(int, ImageReadParam)}.
     *
     * @throws IOException If an error occurred while reading the image.
     */
    @Test
    public void testReadAsRenderedImage() throws IOException {
        testImageReads(API.READ_AS_RENDERED_IMAGE);
    }

    /**
     * Tests the {@link ImageReader#readRaster(int, ImageReadParam) ImageReader.readRaster} method.
     * This method performs the same test than {@link #testReadAsBufferedImage()}, except that the
     * {@link ImageReader#readRaster(int, ImageReadParam)} method is invoked instead than
     * {@code ImageReader.read(int, ImageReadParam)}.
     * <p>
     * This test is ignored if {@link ImageReader#canReadRaster()} returns {@code false}.
     *
     * @throws IOException If an error occurred while reading the raster.
     */
    @Test
    public void testReadAsRaster() throws IOException {
        prepareImageReader(false);
        assumeTrue(reader.canReadRaster());
        testImageReads(API.READ_RASTER);
    }

    /**
     * Implementation of the {@link #testReadAsBufferedImage()} method.
     *
     * @param  api The API to use for reading images.
     * @throws IOException If an error occurred while reading the image.
     */
    private void testImageReads(final API api) throws IOException {
        prepareImageReader(true);
        assertInputSet(reader);
        final boolean subregion   = isSubregionSupported;
        final boolean subsampling = isSubsamplingSupported;
        final boolean offset      = isSubsamplingOffsetSupported;
        final boolean bands       = isSourceBandsSupported;
        final int numImages = reader.getNumImages(true);
        for (int imageIndex=0; imageIndex<numImages; imageIndex++) {
            final RenderedImage completeImage = reader.read(imageIndex);
            final boolean actualBands = bands && completeImage.getSampleModel().getNumBands() > 1;
            /*
             * Reads the complete image again.
             */
            isSubregionSupported         = false;
            isSubsamplingSupported       = false;
            isSubsamplingOffsetSupported = false;
            isSourceBandsSupported       = false;
            readRandomSubsets(completeImage, api, imageIndex, 1);
            /*
             * Tests reading sub-regions only (no subsampling).
             */
            if (subregion) {
                isSubregionSupported = true;
                readRandomSubsets(completeImage, api, imageIndex, DEFAULT_NUM_ITERATIONS);
                isSubregionSupported = false;
            }
            /*
             * Tests reading the complete region with various subsamplings.
             */
            if (subsampling) {
                isSubsamplingSupported = true;
                readRandomSubsets(completeImage, api, imageIndex, DEFAULT_NUM_ITERATIONS);
                isSubsamplingSupported = false;
            }
            /*
             * Tests reading the complete image with different source bands.
             */
            if (actualBands) {
                isSourceBandsSupported = true;
                readRandomSubsets(completeImage, api, imageIndex, DEFAULT_NUM_ITERATIONS/2);
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
                readRandomSubsets(completeImage, api, imageIndex, DEFAULT_NUM_ITERATIONS*2);
            }
        }
    }

    /**
     * Disposes the {@link #reader} (if non-null) after each test.
     * The default implementation performs the following cleanup:
     * <p>
     * <ul>
     *   <li>If the {@linkplain ImageReader#getInput() reader input} is {@linkplain java.io.Closeable closeable}, closes it.</li>
     *   <li>Invokes {@link ImageReader#reset()} for clearing the input and listeners.</li>
     *   <li>Invokes {@link ImageReader#dispose()} for performing additional resource disposal, if any.</li>
     *   <li>Sets the {@link #reader} field to {@code null} for preventing accidental use.</li>
     * </ul>
     *
     * @throws IOException In an error occurred while closing the input stream.
     *
     * @see ImageReader#reset()
     * @see ImageReader#dispose()
     */
    @After
    public void dispose() throws IOException {
        if (reader != null) {
            close(reader.getInput());
            reader.reset();
            reader.dispose();
            reader = null;
        }
    }
}
