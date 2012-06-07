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
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import javax.imageio.ImageReader;
import javax.imageio.ImageReadParam;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Base class for testing {@link ImageReader} implementations. This test reads an image
 * in different sub-region, at different sub-sampling levels and reading different bands,
 * and compares the results with the full image.
 *
 * <p>To use this test, subclasses need to set the {@link #reader} field to a non-null value
 * either at construction time or in a method having the {@link org.junit.Before} annotation.
 * The {@linkplain ImageReader#getInput() reader input} must be set by the sub-class.
 * Example:</p>
 *
 * <blockquote><pre>public class MyImageReaderTest extends ImageReaderTestCase {
 *    private static final File MY_TEST_FILE = new File("MyTestImage");
 *
 *    public MyImageReaderTest() throws IOException {
 *        super(System.nanoTime()); // Replace by a constant number if reproducible tests is needed.
 *        reader = new MyImageReader();
 *        reader.setInput(ImageIO.createImageInputStream(MY_TEST_FILE));
 *    }
 *
 *    &#64;Override
 *    protected boolean canReadAgain(final int imageIndex) throws IOException {
 *        if (!super.canReadAgain(imageIndex)) {
 *            ((ImageInputStream) reader.getInput()).close();
 *            reader.setInput(ImageIO.createImageInputStream(MY_TEST_FILE));
 *        }
 *        return true;
 *    }
 *}</pre></blockquote>
 *
 * <p>Subclasses inherit the following tests:</p>
 * <ul>
 *   <li>{@link #testImageReads()} - to test the {@link ImageReader#read(int, ImageReadParam)} method.</li>
 * </ul>
 *
 * <p>In addition, subclasses may consider to override the following methods:</p>
 * <ul>
 *   <li>{@link #canReadAgain(int)} - to reset the image input stream if needed.</li>
 *   <li>{@link #dispose()} - to modify the {@link #reader} disposal.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp abstract class ImageReaderTestCase extends ImageBackendTestCase {
    /**
     * Default number of iterations when testing the image read operations.
     */
    private static final int DEFAULT_NUM_ITERATIONS = 10;

    /**
     * The image reader to test. This field must be set by sub-classes either at
     * construction time, or in a method having the {@link org.junit.Before} annotation.
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
    protected boolean isSubregionSupported;

    /**
     * {@code true} if the {@linkplain #reader} takes in account the two first parameter values
     * given to {@link ImageReadParam#setSourceSubsampling(int, int, int, int)}. The default value
     * is {@code true}. Subclasses can set this flag to {@code false} when testing an incomplete
     * {@link ImageReader} implementation.
     */
    protected boolean isSubsamplingSupported;

    /**
     * {@code true} if the {@linkplain #reader} takes in account the two last parameter values
     * given to {@link ImageReadParam#setSourceSubsampling(int, int, int, int)}. The default value
     * is {@code true}. Subclasses can set this flag to {@code false} when testing an incomplete
     * {@link ImageReader} implementation.
     */
    protected boolean isSubsamplingOffsetSupported;

    /**
     * Creates a new test case using a random number generator initialized to the given seed.
     * The {@link #reader} field must be initialized by sub-classes.
     *
     * @param seed The initial seed for the random numbers generator. Use a constant value if
     *        the tests need to be reproduced with the same sequence image read parameters.
     */
    protected ImageReaderTestCase(final long seed) {
        random = new Random(seed);
        isSubregionSupported         = true;
        isSubsamplingSupported       = true;
        isSubsamplingOffsetSupported = true;
    }

    /**
     * Asserts that the {@linkplain ImageReader#getInput() reader input} is set to a non-null value.
     */
    private static void assertInputSet(final ImageReader reader) {
        assertNotNull("The 'reader' field shall be set in the constructor or in a @Before method.", reader);
        assertNotNull("reader.setInput(Object) shall be invoked before any test is run.", reader.getInput());
    }

    /**
     * Returns {@code true} if the image {@linkplain #reader} can read again the image at the given
     * index. This method is invoked <em>after</em> a test method has read the image at least once
     * and before the test method attempts to read the same image again with a different sub-region,
     * sub-sampling or source bands.
     * <p>
     * The default implementation returns {@code true} if and only if {@link ImageReader#getMinIndex()}
     * returns a value not greater than the given {@code imageIndex}. Subclasses may override this
     * method in order to reset the {@linkplain ImageReader#getInput() reader input} to a fresh
     * {@link javax.imageio.stream.ImageInputStream} if needed. See the class javadoc for an example.
     *
     * @param  imageIndex Index of the image to read.
     * @return {@code true} if the {@linkplain #reader} can read again an image at the given index.
     * @throws IOException If an error occurred while reseting the {@linkplain #reader}.
     */
    protected boolean canReadAgain(final int imageIndex) throws IOException {
        return reader.getMinIndex() <= imageIndex;
    }

    /**
     * Reads random subsets of the image at the given index, and compares the result with the
     * given complete image. This method sets the {@link ImageReadParam} parameters to random
     * sub-regions, sub-samplings and source bands values and invokes the following method:
     *
     * <blockquote><pre>{@linkplain #reader}.{@link ImageReader#read(int, ImageReadParam) read}(imageIndex, parameters);</pre></blockquote>
     *
     * The above method call is repeated at most {@code numIterations} time with different parameters.
     * The kind of parameters to be tested is controlled by the {@code isXXXSupported} boolean fields
     * in this class.
     * <p>
     * The pixel values for each image resulting from the above read operations are
     * compared with the corresponding pixel values of the given complete image.
     *
     * @param  completeImage The complete image as returned by <code>{@linkplain #reader}.{@link ImageReader#read(int) read}(imageIndex)</code> without read parameters.
     * @param  imageIndex    Index of the image to read.
     * @param  numIterations Maximum number of iterations to perform.
     * @return Number of iterations performed. This method may stop before the iteration is
     *         completed if the {@link #canReadAgain(int)} method returned {@code false}.
     * @throws IOException If an error occurred while reading the image.
     */
    protected int readRandomSubsets(final RenderedImage completeImage, final int imageIndex, final int numIterations) throws IOException {
        assertInputSet(reader);
        final Rectangle completeRegion = getBounds(completeImage);
        final int dataType = completeImage.getSampleModel().getDataType();
        int iterationCount;
        for (iterationCount=0; iterationCount<numIterations; iterationCount++) {
            if (!canReadAgain(imageIndex)) {
                break;
            }
            final ImageReadParam param = reader.getDefaultReadParam();
            Rectangle region = new Rectangle(completeRegion);
            if (isSubregionSupported) {
                region.x     += random.nextInt(region.width);
                region.y     += random.nextInt(region.height);
                region.width  = random.nextInt(region.width)  + 1;
                region.height = random.nextInt(region.height) + 1;
                region = region.intersection(completeRegion);
                param.setSourceRegion(region);
            }
            int xSubsampling=1, ySubsampling=1, xOffset=0, yOffset=0;
            if (isSubsamplingSupported) {
                xSubsampling = random.nextInt(region.width)  + 1;
                ySubsampling = random.nextInt(region.height) + 1;
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
            /*
             * At this point, we are ready to read the image and compare the global attributes.
             */
            final RenderedImage image = reader.read(imageIndex, param);
            assertEquals("RenderedImage.getWidth()",  (region.width  + xSubsampling-1) / xSubsampling, image.getWidth());
            assertEquals("RenderedImage.getHeight()", (region.height + ySubsampling-1) / ySubsampling, image.getHeight());
            final PixelIterator actual   = new PixelIterator(image);
            final PixelIterator expected = new PixelIterator(completeImage, region, xSubsampling, ySubsampling, null);
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
                    region = param.getSourceRegion();
                    fail("Mismatched sample value: expected " + ev + " but got " + av + '\n'
                       + "Pixel coordinate in the complete image: (" + expected.getX() + ", " + expected.getY() + ") band " + expected.getBand() + '\n'
                       + "Pixel coordinate in the tested image: (" + actual.getX() + ", " + actual.getY() + ") band " + actual.getBand() + '\n'
                       + "Source region: origin = (" + (region.x - xOffset) + ", " + (region.y - yOffset) + "), "
                                        + "size = (" + (region.width + xOffset) + ", " + (region.height + yOffset) + ")\n"
                       + "Source subsampling: (" + xSubsampling + ", " + ySubsampling + ") with offset (" + xOffset + ", " + yOffset + ")\n");
                }
            }
        }
        return iterationCount;
    }

    /**
     * Invokes {@link #readRandomSubsets(RenderedImage, int, int)} with the default number of iterations.
     */
    private void readRandomSubsets(final RenderedImage completeImage, final int imageIndex) throws IOException {
        assertEquals("Tried to read " + DEFAULT_NUM_ITERATIONS + " times the image at index " + imageIndex + ", " +
                "but the ImageReader stopped before. Consider overriding the canReadAgain(int) method " +
                "in order to reset the input stream, or override the testImageReads() method for performing " +
                "a more limited amount of read operations.",
                DEFAULT_NUM_ITERATIONS, readRandomSubsets(completeImage, imageIndex, DEFAULT_NUM_ITERATIONS));
    }

    /**
     * Tests the {@link ImageReader#read(int, ImageReadParam)} method. First,
     * this method reads the full image with the following method call:
     *
     * <blockquote><pre>{@linkplain #reader}.{@link ImageReader#read(int) read}(imageIndex);</pre></blockquote>
     *
     * <p>Then, this method invokes {@link #readRandomSubsets(RenderedImage, int, int)} for the
     * following configurations (note: any {@code isXXXSupported} field which was set to
     * {@code false} prior the execution of this test will stay {@code false}):</p>
     *
     * <ul>
     *   <li>Reads the full image once (all {@code isXXXSupported} fields set to {@code false}).</li>
     *   <li>Reads various sub-regions (only {@link #isSubregionSupported} may be {@code true})</li>
     *   <li>Reads at various sub-sampling (only {@link #isSubsamplingSupported may be {@code true})</li>
     *   <li>A mix of sub-regions, sub-sampling and source bands</li>
     * </ul>
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
    public void testImageReads() throws IOException {
        assertInputSet(reader);
        final boolean subregion   = isSubregionSupported;
        final boolean subsampling = isSubsamplingSupported;
        final boolean offset      = isSubsamplingOffsetSupported;
        final int numImages = reader.getNumImages(true);
        for (int imageIndex=0; imageIndex<numImages; imageIndex++) {
            final RenderedImage completeImage = reader.read(imageIndex);
            /*
             * Reads the complete image again.
             */
            isSubregionSupported         = false;
            isSubsamplingSupported       = false;
            isSubsamplingOffsetSupported = false;
            assertEquals("The image shall be readeable at least once.",
                    1, readRandomSubsets(completeImage, imageIndex, 1));
            /*
             * Tests reading sub-regions only (no subsampling).
             */
            if (subregion) {
                isSubregionSupported = true;
                readRandomSubsets(completeImage, imageIndex);
                isSubregionSupported = false;
            }
            /*
             * Tests reading the complete region with various subsamplings.
             */
            if (subsampling) {
                isSubsamplingSupported = true;
                readRandomSubsets(completeImage, imageIndex);
                isSubsamplingSupported = false;
            }
            /*
             * Mixes all.
             */
            isSubregionSupported         = subregion;
            isSubsamplingSupported       = subsampling;
            isSubsamplingOffsetSupported = offset;
            if (subregion | subsampling | offset) {
                readRandomSubsets(completeImage, imageIndex);
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
