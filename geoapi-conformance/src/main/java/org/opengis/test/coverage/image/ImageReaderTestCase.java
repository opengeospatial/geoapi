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

import java.io.Closeable;
import java.io.IOException;
import java.awt.image.RenderedImage;
import javax.imageio.ImageReader;
import javax.imageio.ImageReadParam;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.metadata.IIOMetadataFormatImpl;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import org.opengis.coverage.grid.Grid;
import org.opengis.coverage.grid.GridEnvelope;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.identification.DataIdentification;

import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;


/**
 * Base class for testing {@link ImageReader} implementations. This test reads different regions and
 * bands of an image at different sub-sampling levels, and compares the results with the complete image.
 * If the image reader can also provide GeoAPI metadata objects,
 * then this class will verify the consistency of some basic attributes.
 * For example, it will verify that the {@linkplain GridEnvelope grid envelope} (if any) is consistent with
 * the image {@linkplain ImageReader#getWidth(int) width} and {@linkplain ImageReader#getHeight(int) height}.
 *
 * <p>To use this test, subclasses need to set the {@link #reader} field to a non-null value in the
 * {@link #prepareImageReader(boolean)} method. The {@linkplain ImageReader#getInput() reader input}
 * shall be set by the subclass when requested by the caller. Example:</p>
 *
 * {@snippet lang="java" :
 * public class MyImageReaderTest extends ImageReaderTestCase {
 *     @Override
 *     protected void prepareImageReader(boolean setInput) throws IOException {
 *         if (reader == null) {
 *             reader = new MyImageReader();
 *         }
 *         if (setInput) {
 *             reader.setInput(ImageIO.createImageInputStream(new File("MyTestImage")));
 *         }
 *     }
 * }}
 *
 * <p>Subclasses inherit the following tests:</p>
 * <table class="ogc">
 *   <caption>Inherited tests</caption>
 *   <tr><th>Inherited method</th>                   <th>Tested method</th></tr>
 *   <tr><td>{@link #testStreamMetadata()}</td>      <td>{@link ImageReader#getStreamMetadata()}</td></tr>
 *   <tr><td>{@link #testImageMetadata()}</td>       <td>{@link ImageReader#getImageMetadata(int)}</td></tr>
 *   <tr><td>{@link #testReadAsBufferedImage()}</td> <td>{@link ImageReader#read(int, ImageReadParam)}</td></tr>
 *   <tr><td>{@link #testReadAsRenderedImage()}</td> <td>{@link ImageReader#readAsRenderedImage(int, ImageReadParam)}</td></tr>
 *   <tr><td>{@link #testReadAsRaster()}</td>        <td>{@link ImageReader#readRaster(int, ImageReadParam)}</td></tr>
 * </table>
 *
 * <p>In addition, subclasses may consider to override the following methods:</p>
 * <ul>
 *   <li>{@link #getMetadata(Class, IIOMetadata)} - to extract metadata objects from specific nodes.</li>
 *   <li>{@link #close()} - to modify the policy of {@linkplain #reader} disposal.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public abstract strictfp class ImageReaderTestCase extends ImageIOTestCase implements Closeable {
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
     * The image reader to test. This field must be set by subclasses
     * in the {@link #prepareImageReader(boolean)} method.
     */
    protected ImageReader reader;

    /**
     * Creates a new test case using a default random number generator.
     * The sub-regions, sub-samplings and source bands will be different
     * for every test execution. If reproducible subsetting sequences are
     * needed, use the {@link #ImageReaderTestCase(long)} constructor instead.
     */
    protected ImageReaderTestCase() {
        super();
    }

    /**
     * Creates a new test case using a random number generator initialized to the given seed.
     *
     * @param seed  the initial seed for the random numbers generator. Use a constant value if
     *        the tests need to be reproduced with the same sequence of image read parameters.
     */
    protected ImageReaderTestCase(final long seed) {
        super(seed);
    }

    /**
     * Asserts that the {@linkplain ImageReader#getInput() reader input} is set to a non-null value.
     */
    private static void assertInputSet(final ImageReader reader) {
        assertNotNull("The 'reader' field shall be set in the 'prepareImageReader' method.", reader);
        assertNotNull("reader.setInput(Object) shall be invoked before any test is run.", reader.getInput());
    }

    /**
     * Invokes {@link #prepareImageReader(boolean)} with a value of {@code true}, ensures that
     * the input is set, then validate the provider.
     *
     * @throws IOException if an error occurred while preparing the {@linkplain #reader}.
     */
    private void prepareImageReader() throws IOException {
        prepareImageReader(true);
        assertInputSet(reader);
        validators.validate(reader.getOriginatingProvider());
    }

    /**
     * Invoked when the image {@linkplain #reader} is about to be used for the first time, or when
     * its {@linkplain ImageReader#getInput() input} needs to be reinitialized. Subclasses need to
     * create a new {@link ImageReader} instance if needed and set its input in this method.
     *
     * <p>This method may be invoked more than once during the same test if the input became invalid.
     * This may occur because the tests will read the same image many time in different ways, and not
     * all input streams can seek back to the beginning of the image stream. In such case,
     * {@code ImageReaderTestCase} will {@linkplain java.io.Closeable#close() close} the input and
     * invoke this method in order to get a fresh {@link javax.imageio.stream.ImageInputStream}.</p>
     *
     * <p><b>Example:</b></p>
     * {@snippet lang="java" :
     * @Override
     * protected void prepareImageReader(boolean setInput) throws IOException {
     *     if (reader == null) {
     *         reader = new MyImageReader();
     *     }
     *     if (setInput) {
     *         reader.setInput(ImageIO.createImageInputStream(new File("MyTestImage")));
     *     }
     * }}
     *
     * This method may be invoked with a {@code false} argument value when the methods to be
     * tested don't need an input, for example {@link ImageReader#canReadRaster()}.
     *
     * @param  setInput {@code true} if this method shall {@linkplain ImageReader#setInput(Object)
     *         set the reader input}, or {@code false} if this is not yet necessary.
     * @throws IOException if an error occurred while preparing the {@linkplain #reader}.
     */
    protected abstract void prepareImageReader(boolean setInput) throws IOException;

    /**
     * Returns the user object of the given type found in the given Image I/O metadata, or
     * {@code null} if none. The default implementation {@linkplain IIOMetadata#getAsTree(String)
     * gets the tree of nodes} for all {@linkplain IIOMetadata#getMetadataFormatNames() metadata
     * formats} except the {@linkplain IIOMetadataFormatImpl#standardMetadataFormatName standard
     * format}, then iterates down the tree in search for a {@linkplain IIOMetadataNode#getUserObject()
     * user object} of the given type. If an ambiguity is found, this method conservatively returns
     * {@code true}.
     *
     * <p>Implementers are encouraged to override this method if they can look for metadata in a
     * more accurate way.</p>
     *
     * <p>See {@link #testStreamMetadata()} and {@link #testImageMetadata()} for a list of types
     * requested by the default {@code ImageReaderTestCase} implementation.</p>
     *
     * @param  <T>       the compile-time type of the object to search.
     * @param  type      the type of the object to search.
     * @param  metadata  the metadata where to search for the object.
     * @return the user object of the given type, or {@code null} if not found.
     * @throws IOException if this method requires an I/O operation and that operation failed.
     */
    protected <T> T getMetadata(final Class<T> type, final IIOMetadata metadata) throws IOException {
        T found = null;
        for (final String format : metadata.getMetadataFormatNames()) {
            if (!format.equals(IIOMetadataFormatImpl.standardMetadataFormatName)) {
                final T userObject = getMetadata(type, metadata.getAsTree(format));
                if (userObject != null) {
                    if (found == null) {
                        found = userObject;
                    } else if (!found.equals(userObject)) {
                        return null;
                    }
                }
            }
        }
        return found;
    }

    /**
     * Returns the user object of the given type in the given node, or in one of its children.
     * If the user object is found in the given node, we will returns it directly. If we have
     * to scan the children, we will return it only if we find only one object, in order to
     * avoid ambiguity.
     *
     * @param  <T>   the compile-time type of the object to search.
     * @param  type  the type of the object to search.
     * @param  node  the node where to search for the object, or {@code null} if none.
     * @return the user object of the given type, or {@code null} if not found.
     */
    private static <T> T getMetadata(final Class<T> type, final Node node) {
        if (node == null) {             // Because IIOMetadata.getAsTree(String) may return null.
            return null;
        }
        if (node instanceof IIOMetadataNode) {
            final Object userObject = ((IIOMetadataNode) node).getUserObject();
            if (type.isInstance(userObject)) {
                return type.cast(userObject);
            }
        }
        T found = null;
        final NodeList childs = node.getChildNodes();
        final int length = childs.getLength();
        for (int i=0; i<length; i++) {
            final T userObject = getMetadata(type, childs.item(i));
            if (userObject != null) {
                if (found == null) {
                    found = userObject;
                } else if (!found.equals(userObject)) {
                    return null;
                }
            }
        }
        return found;
    }

    /**
     * Verifies the validity of metadata attributes as documented in the
     * {@link #testStreamMetadata()} and {@link #testImageMetadata()} methods.
     */
    private void validate(final IIOMetadata metadata) throws IOException {
        final Metadata md = getMetadata(Metadata.class, metadata);
        if (md != null) {
            for (final Identification identification : md.getIdentificationInfo()) {
                validators.validate(identification.getCitation());
                for (final Extent extent : identification.getExtents()) {
                    validators.validate(extent);
                }
            }
        }
    }

    /**
     * Tests {@link ImageReader#getStreamMetadata()}. The default implementation invokes
     * <code>{@linkplain #getMetadata(Class, IIOMetadata) getMetadata}({@linkplain Metadata}.class,</code>
     * <var>stream metadata</var><code>)</code>, then validates the following properties:
     *
     * <ul>
     *   <li>{@link Metadata#getIdentificationInfo()}<ul>
     *     <li>{@link DataIdentification#getCitation()}</li>
     *     <li>{@link DataIdentification#getExtents()}</li>
     *   </ul></li>
     * </ul>
     *
     * @throws IOException if an error occurred while reading the metadata.
     */
    @Test
    public void testStreamMetadata() throws IOException {
        prepareImageReader();
        final IIOMetadata metadata = reader.getStreamMetadata();
        if (metadata != null) {
            validate(metadata);
        }
    }

    /**
     * Tests {@link ImageReader#getImageMetadata(int)}. The default implementation invokes
     * {@link #getMetadata(Class, IIOMetadata)} for the types listed below, then validates
     * their properties:
     *
     * <ul>
     *   <li>{@link Metadata#getIdentificationInfo()}: see {@link #testStreamMetadata()}</li>
     *   <li>{@link Extent}:
     *       {@linkplain org.opengis.test.metadata.ExtentValidator#validate(Extent) Validate}
     *       the spatial extent, if any.</li>
     *   <li>{@link Grid#getExtent()}<ul>
     *     <li>{@link GridEnvelope}: Verify that the {@linkplain GridEnvelope#getSize(int) span}
     *         in at least one dimension is equal to the {@linkplain ImageReader#getWidth(int)
     *         image width}, and a span in another dimension is equal to the
     *         {@linkplain ImageReader#getHeight(int) image height}.</li>
     *   </ul></li>
     * </ul>
     *
     * @throws IOException if an error occurred while reading the metadata.
     */
    @Test
    public void testImageMetadata() throws IOException {
        prepareImageReader();
        final int numImages = reader.getNumImages(true);
        for (int imageIndex=0; imageIndex<numImages; imageIndex++) {
            final IIOMetadata metadata = reader.getImageMetadata(imageIndex);
            if (metadata != null) {
                validate(metadata);
                final GridEnvelope extent;
                final Grid grid = getMetadata(Grid.class, metadata);
                if (grid != null) {
                    extent = grid.getExtent();
                } else {
                    extent = getMetadata(GridEnvelope.class, metadata);
                }
                if (extent != null) {
                    final int width  = reader.getWidth (imageIndex);
                    final int height = reader.getHeight(imageIndex);
                    boolean foundWidth = false, foundHeight = false;
                    final int dimension = extent.getDimension();
                    for (int i=0; i<dimension; i++) {
                        final long span = extent.getSize(i);
                        if (span == width) {
                            foundWidth = true;
                        } else if (span == height) {
                            foundHeight = true;
                        }
                    }
                    if (!foundWidth || !foundHeight) {
                        fail("Expected an image of size " + width + '×' + height +
                                " but found a grid extent of " + extent);
                    }
                }
            }
        }
    }

    /**
     * Reads random subsets of the image at the given index, and compares the result with the
     * given complete image. This method sets the {@link ImageReadParam} parameters to random
     * sub-regions, sub-samplings and source bands values and invokes one of the following
     * methods as determined by the {@code api} argument:
     *
     * <ul>
     *   <li><code>{@link ImageReader#read(int, ImageReadParam)}</code></li>
     *   <li><code>{@link ImageReader#readAsRenderedImage(int, ImageReadParam)}</code></li>
     *   <li><code>{@link ImageReader#readRaster(int, ImageReadParam)}</code></li>
     * </ul>
     *
     * The above method call is repeated {@code numIterations} time with different parameters.
     * The kind of parameters to be tested is controlled by the {@code isXXXSupported} boolean
     * fields in this class.
     *
     * <p>The pixel values for each image resulting from the above read operations are
     * compared with the corresponding pixel values of the given complete image.</p>
     *
     * @param  completeImage  the complete image as returned by <code>{@linkplain #reader}.{@link ImageReader#read(int) read}(imageIndex)</code> without read parameters.
     * @param  api            the API to use for reading the images.
     * @param  imageIndex     index of the images to read.
     * @param  numIterations  maximum number of iterations to perform.
     * @throws IOException if an error occurred while reading the image.
     */
    private void readRandomSubsets(final RenderedImage completeImage, final API api,
            final int imageIndex, final int numIterations) throws IOException
    {
        final ImageReader reader = this.reader;                                         // Protect from changes.
        assertInputSet(reader);
        for (int iterationCount=0; iterationCount<numIterations; iterationCount++) {
            if (reader.getMinIndex() > imageIndex) {
                close(reader.getInput());
                reader.setInput(null);
                prepareImageReader(true);
            }
            final ImageReadParam param = reader.getDefaultReadParam();
            final PixelIterator expected = getIteratorOnRandomSubset(completeImage, param);
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
            expected.assertSampleValuesEqual(new PixelIteratorForIO(image, param), sampleToleranceThreshold);
        }
    }

    /**
     * Tests the {@link ImageReader#read(int, ImageReadParam) ImageReader.read} method.
     * First, this method reads the full image with a call to {@link ImageReader#read(int)}.
     * Then, this method invokes {@link ImageReader#read(int, ImageReadParam)} an arbitrary
     * amount of time for the following configurations (note: any {@code isXXXSupported} field
     * which was set to {@code false} prior the execution of this test will stay {@code false}):
     *
     * <ul>
     *   <li>Reads the full image once (all {@code isXXXSupported} fields set to {@code false}).</li>
     *   <li>Reads various sub-regions (only {@link #isSubregionSupported isSubregionSupported} may be {@code true})</li>
     *   <li>Reads at various sub-sampling (only {@link #isSubsamplingSupported isSubsamplingSupported} may be {@code true})</li>
     *   <li>Reads various bands (only {@link #isSourceBandsSupported isSourceBandsSupported} may be {@code true})</li>
     *   <li>A mix of sub-regions, sub-sampling and source bands</li>
     * </ul>
     *
     * The pixel values for each image resulting from the above read operations are
     * compared with the corresponding pixel values of the complete image.
     *
     * <div class="note"><b>Note:</b>
     * in the spirit of JUnit, this test should have been splitted in smaller test cases:
     * one for sub-regions, one for sub-samplings, <i>etc</i>. However in this particular case,
     * consolidation of those tests in a single method provides the following benefits:
     * <ul>
     *   <li>The potentially large complete image is read only once.</li>
     *   <li>If the tests for subregions or subsamplings fails, avoid the test mixing both.</li>
     *   <li>Less methods to override if the implementer want to provide his own test.</li>
     * </ul>
     * </div>
     *
     * @throws IOException if an error occurred while reading the image.
     */
    @Test
    public void testReadAsBufferedImage() throws IOException {
        testImageReads(API.READ);
    }

    /**
     * Tests the {@link ImageReader#readAsRenderedImage(int, ImageReadParam) ImageReader.readAsRenderedImage} method.
     * This method performs the same test than {@link #testReadAsBufferedImage()}, except that the
     * {@link ImageReader#readAsRenderedImage(int, ImageReadParam)} method is invoked instead of
     * {@code ImageReader.read(int, ImageReadParam)}.
     *
     * @throws IOException if an error occurred while reading the image.
     */
    @Test
    public void testReadAsRenderedImage() throws IOException {
        testImageReads(API.READ_AS_RENDERED_IMAGE);
    }

    /**
     * Tests the {@link ImageReader#readRaster(int, ImageReadParam) ImageReader.readRaster} method.
     * This method performs the same test than {@link #testReadAsBufferedImage()}, except that the
     * {@link ImageReader#readRaster(int, ImageReadParam)} method is invoked instead of
     * {@code ImageReader.read(int, ImageReadParam)}.
     *
     * <p>This test is ignored if {@link ImageReader#canReadRaster()} returns {@code false}.</p>
     *
     * @throws IOException if an error occurred while reading the raster.
     */
    @Test
    public void testReadAsRaster() throws IOException {
        prepareImageReader(false);
        assumeTrue(reader.canReadRaster());
        testImageReads(API.READ_RASTER);
    }

    /**
     * Implementation of the {@link #testReadAsBufferedImage()}, {@link #testReadAsRenderedImage()}
     * and {@link #testReadAsRaster()} methods.
     *
     * @param  api  the API to use for reading images.
     * @throws IOException if an error occurred while reading the image.
     */
    private void testImageReads(final API api) throws IOException {
        prepareImageReader();
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
     * Disposes the {@linkplain #reader} (if non-null) after each test.
     * The default implementation performs the following cleanup:
     *
     * <ul>
     *   <li>If the {@linkplain ImageReader#getInput() reader input} is {@linkplain Closeable closeable}, closes it.</li>
     *   <li>Invokes {@link ImageReader#reset()} for clearing the input and listeners.</li>
     *   <li>Invokes {@link ImageReader#dispose()} for performing additional resource disposal, if any.</li>
     *   <li>Sets the {@link #reader} field to {@code null} for preventing accidental use.</li>
     * </ul>
     *
     * @throws IOException if an error occurred while closing the input stream.
     *
     * @see ImageReader#reset()
     * @see ImageReader#dispose()
     */
    @After
    @Override
    public void close() throws IOException {
        if (reader != null) {
            close(reader.getInput());
            reader.reset();
            reader.dispose();
            reader = null;
        }
    }
}
