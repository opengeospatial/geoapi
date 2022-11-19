/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2021 Open Geospatial Consortium, Inc.
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

import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ImageReaderWriterSpi;
import javax.imageio.metadata.IIOMetadataFormat;
import javax.imageio.metadata.IIOMetadataFormatImpl;

import org.opengis.test.Validator;
import org.opengis.test.ValidatorContainer;

import static org.opengis.test.Assert.*;


/**
 * Validators for implementations of {@link java.awt.image} or {@link javax.imageio} services.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class ImageValidator extends Validator {
    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public ImageValidator(final ValidatorContainer container) {
        super(container, "org.opengis.test.coverage.image");
    }

    /**
     * Validates the given provider of image readers.
     * First, this method verifies that mandatory elements are non-null, arrays are non-empty
     * (Image I/O specification requires them to be {@code null} rather than empty), and class
     * names are valid. Next, this method invokes {@link #validate(IIOMetadataFormat)} for each
     * metadata format (which can be null).
     *
     * @param provider  the provider to validate, or {@code null} if none.
     */
    public void validate(final ImageReaderSpi provider) {
        if (provider != null) {
            validateProvider(provider, ImageReader.class);
            final Class<?>[] inputTypes = provider.getInputTypes();
            mandatory("ImageReaderSpi: shall have an inputTypes array.", inputTypes);
            validateArray("inputTypes", inputTypes);
            final String[] imageWriterSpiNames = provider.getImageWriterSpiNames();
            validateArray("imageWriterSpiNames", imageWriterSpiNames);
            if (imageWriterSpiNames != null) {
                final ClassLoader loader = provider.getClass().getClassLoader();
                for (int i=0; i<imageWriterSpiNames.length; i++) {
                    final String field = "imageWriterSpiNames[" + i + ']';
                    final String className = imageWriterSpiNames[i];
                    assertNotNull(field + " cannot be null.", className);
                    validateClass(field, ImageWriterSpi.class, loader, className);
                }
            }
        }
    }

    /**
     * Validates the given provider of image writers.
     * First, this method verifies that mandatory elements are non-null, arrays are non-empty
     * (Image I/O specification requires them to be {@code null} rather than empty), and class
     * names are valid. Next, this method invokes {@link #validate(IIOMetadataFormat)} for each
     * metadata format (which can be null).
     *
     * @param provider  the provider to validate, or {@code null} if none.
     */
    public void validate(final ImageWriterSpi provider) {
        if (provider != null) {
            validateProvider(provider, ImageWriter.class);
            final Class<?>[] outputTypes = provider.getOutputTypes();
            mandatory("ImageWriterSpi: shall have an outputTypes array.", outputTypes);
            validateArray("outputTypes", outputTypes);
            final String[] imageReaderSpiNames = provider.getImageReaderSpiNames();
            validateArray("imageReaderSpiNames", imageReaderSpiNames);
            if (imageReaderSpiNames != null) {
                final ClassLoader loader = provider.getClass().getClassLoader();
                for (int i=0; i<imageReaderSpiNames.length; i++) {
                    final String field = "imageReaderSpiNames[" + i + ']';
                    final String className = imageReaderSpiNames[i];
                    assertNotNull(field + " cannot be null.", className);
                    validateClass(field, ImageReaderSpi.class, loader, className);
                }
            }
        }
    }

    /**
     * Validates the given image reader or image writer provider.
     *
     * @param spi  the provider to validate, or {@code null} if none.
     */
    private void validateProvider(final ImageReaderWriterSpi spi, final Class<?> pluginType) {
        mandatory("ImageReaderWriterSpi: shall have a vendorName string.", spi.getVendorName());
        mandatory("ImageReaderWriterSpi: shall have a version string.",    spi.getVersion());
        final String[] formatNames = spi.getFormatNames();
        mandatory("ImageReaderWriterSpi: shall have a formatNames array.", formatNames);
        validateArray("formatNames",  formatNames);
        validateArray("fileSuffixes", spi.getFileSuffixes());
        validateArray("MIMETypes",    spi.getMIMETypes());
        validateClass("pluginClassName", pluginType, spi.getClass().getClassLoader(), spi.getPluginClassName());
        validateMetadataFormatName("Stream", spi.getNativeStreamMetadataFormatName(), spi.getExtraStreamMetadataFormatNames());
        validateMetadataFormatName("Image",  spi.getNativeImageMetadataFormatName(),  spi.getExtraImageMetadataFormatNames());
        /*
         * Ensures that a IIOMetadataFormat can be instantiated for each declared format name.
         * Then, invokes validate(IIOMetadataFormat) for each format. Note that the format are
         * allowed to be null according Image I/O specification.
         */
        if (spi.isStandardStreamMetadataFormatSupported()) {
            assertSame("Expected the standard metadata format instance.",
                    IIOMetadataFormatImpl.getStandardFormatInstance(),
                    spi.getStreamMetadataFormat(IIOMetadataFormatImpl.standardMetadataFormatName));
        }
        if (spi.isStandardImageMetadataFormatSupported()) {
            assertSame("Expected the standard metadata format instance.",
                    IIOMetadataFormatImpl.getStandardFormatInstance(),
                    spi.getImageMetadataFormat(IIOMetadataFormatImpl.standardMetadataFormatName));
        }
        String formatName = spi.getNativeStreamMetadataFormatName();
        if (formatName != null) {
            validate(spi.getStreamMetadataFormat(formatName));
        }
        formatName = spi.getNativeImageMetadataFormatName();
        if (formatName != null) {
            validate(spi.getImageMetadataFormat(formatName));
        }
        String[] names = spi.getExtraStreamMetadataFormatNames();
        if (names != null) {
            for (final String name : names) {
                validate(spi.getStreamMetadataFormat(name));
            }
        }
        names = spi.getExtraImageMetadataFormatNames();
        if (names != null) {
            for (final String name : names) {
                validate(spi.getImageMetadataFormat(name));
            }
        }
    }

    /**
     * Validates the image or stream metadata format names.
     * This method ensures that there is no duplicated values.
     */
    private static void validateMetadataFormatName(final String type, String nativeMetadataFormatName,
            final String[] extraMetadataFormatNames)
    {
        if (nativeMetadataFormatName != null) {
            nativeMetadataFormatName = nativeMetadataFormatName.trim();
            assertFalse("The native" + type + "MetadataFormatName value cannot be equal to \"" +
                    IIOMetadataFormatImpl.standardMetadataFormatName + "\".",
                    IIOMetadataFormatImpl.standardMetadataFormatName.equalsIgnoreCase(nativeMetadataFormatName));
        }
        if (extraMetadataFormatNames != null) {
            final String field = "extra" + type + "MetadataFormatNames";
            validateArray(field, extraMetadataFormatNames);
            for (int i=0; i<extraMetadataFormatNames.length; i++) {
                final String formatName = extraMetadataFormatNames[i].trim();
                assertFalse("The " + field + '[' + i + "] value cannot be equal to \"" +
                        IIOMetadataFormatImpl.standardMetadataFormatName + "\".",
                        IIOMetadataFormatImpl.standardMetadataFormatName.equalsIgnoreCase(formatName));
                if (nativeMetadataFormatName != null) {
                    assertFalse("The " + field + '[' + i + "] value cannot be equal to \"" +
                            nativeMetadataFormatName + "\" since it is already declared as the native format name.",
                            nativeMetadataFormatName.equalsIgnoreCase(formatName));
                }
            }
        }
    }

    /**
     * Ensures that the given array complies with the conditions of Java Image I/O.
     * The array can be either null or non-empty; empty arrays are illegal.
     * Then ensures that there is no duplicated value.
     *
     * @param field  the field name, used in case of errors.
     * @param array  the array to validate, or {@code null} if none.
     */
    private static void validateArray(final String field, final Object[] array) {
        if (array != null) {
            assertStrictlyPositive("The " + field + " array shall be either null or non-empty.", array.length);
            for (int i=0; i<array.length; i++) {
                final Object element = array[i];
                assertNotNull(field + '[' + i + ']', element);
                for (int j=i; ++i<array.length;) {
                    if (element.equals(array[i])) {
                        fail(field + '[' + i + "] and [" + j + "] duplicate the \"" + element + "\" value.");
                    }
                }
            }
        }
    }

    /**
     * Ensure that given class exists and is an instance of the given type.
     *
     * @param field         the name of the tested field.
     * @param expectedType  the expected base class.
     * @param loader        the loader to use for loading the class.
     * @param classname     the name of the class to test.
     */
    private void validateClass(final String field, final Class<?> expectedType,
            final ClassLoader loader, final String classname)
    {
        mandatory("ImageReaderWriterSpi: shall have a " + field + " string.", classname);
        if (classname != null) try {
            final Class<?> actual = Class.forName(classname, false, loader);
            assertTrue(actual.getCanonicalName() + " is not an instance of " +
                    expectedType.getSimpleName() + '.', expectedType.isAssignableFrom(actual));
        } catch (ClassNotFoundException e) {
            throw new AssertionError("Class \"" + classname + "\" declared in " + field + " was not found.", e);
        }
    }

    /**
     * Validates the given metadata format.
     *
     * @param format  the metadata format to validate, or {@code null} if none.
     */
    public void validate(final IIOMetadataFormat format) {
        // Not yet implemented.
    }
}
