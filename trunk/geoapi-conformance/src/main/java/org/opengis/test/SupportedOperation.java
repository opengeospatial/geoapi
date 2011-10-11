/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import java.util.Properties;
import org.opengis.util.Factory;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransformFactory;


/**
 * The keys that can be used in a {@link Properties} map for declaring which aspects of a
 * particular implementation can be tested. Implementors will typically use this enumeration
 * in their {@link ImplementationDetails#configuration(Factory[])} method as in the example
 * below:
 *
 * <blockquote><pre>&#64;Override
 *public {@linkplain Properties} configuration({@linkplain Factory}... factories) {
 *    Properties configuration = new Properties();
 *    {@linkplain #unsupported unsupported}(configuration, {@linkplain #DERIVATIVE_TRANSFORM}, {@linkplain #NON_SQUARE_MATRIX}, {@linkplain #UNOFFICIAL_EPSG_CODES});
 *    return configuration;
 *}</pre></blockquote>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public enum SupportedOperation {
    /**
     * Whatever the {@link IdentifiedObject} instances have {@linkplain IdentifiedObject#getName()
     * names} matching the names declared in the EPSG database.
     *
     * @see org.opengis.test.referencing.gigs.Series2000Test#isNameSupported
     */
    NAME("isNameSupported"),

    /**
     * Whatever the {@link IdentifiedObject} instances have at least the
     * {@linkplain IdentifiedObject#getAlias() aliases} declared in the EPSG database.
     *
     * @see org.opengis.test.referencing.gigs.Series2000Test#isAliasSupported
     */
    ALIAS("isAliasSupported"),

    /**
     * Whatever {@link MathTransform#transform(double[], int, double[], int, int)} is supported.
     * Implementors can set the value for this key to {@code false} in order to test
     * {@linkplain MathTransform math transforms} which are not yet fully implemented.
     *
     * @see org.opengis.test.referencing.TransformTestCase#isDoubleToDoubleSupported
     */
    TRANSFORM_DOUBLE_TO_DOUBLE("isDoubleToDoubleSupported"),

    /**
     * Whatever {@link MathTransform#transform(float[], int, float[], int, int)} is supported.
     * Implementors can set the value for this key to {@code false} in order to test
     * {@linkplain MathTransform math transforms} which are not yet fully implemented.
     *
     * @see org.opengis.test.referencing.TransformTestCase#isFloatToFloatSupported
     */
    TRANSFORM_FLOAT_TO_FLOAT("isFloatToFloatSupported"),

    /**
     * Whatever {@link MathTransform#transform(double[], int, float[], int, int)} is supported.
     * Implementors can set the value for this key to {@code false} in order to test
     * {@linkplain MathTransform math transforms} which are not yet fully implemented.
     *
     * @see org.opengis.test.referencing.TransformTestCase#isDoubleToFloatSupported
     */
    TRANSFORM_DOUBLE_TO_FLOAT("isDoubleToFloatSupported"),

    /**
     * Whatever {@link MathTransform#transform(float[], int, double[], int, int)} is supported.
     * Implementors can set the value for this key to {@code false} in order to test
     * {@linkplain MathTransform math transforms} which are not yet fully implemented.
     *
     * @see org.opengis.test.referencing.TransformTestCase#isFloatToDoubleSupported
     */
    TRANSFORM_FLOAT_TO_DOUBLE("isFloatToDoubleSupported"),

    /**
     * Whatever source and destination arrays can overlap in {@link MathTransform} operations.
     * Overlapping occur when:
     * <p>
     * <ul>
     *   <li>The invoked method is one of the following:
     *     <ul>
     *       <li>{@link MathTransform#transform(double[], int, double[], int, int)}</li>
     *       <li>{@link MathTransform#transform(float[], int, float[], int, int)}</li>
     *     </ul></li>
     *   <li>The {@code srcPts} and {@code dstPts} arguments are references to the same array.</li>
     *   <li>The {@code srcOff} and {@code dstOff} offsets are such that the source region of
     *       the array overlaps with the target region.</li>
     * </ul>
     *
     * @see org.opengis.test.referencing.TransformTestCase#isOverlappingArraySupported
     */
    TRANSFORM_OVERLAPPING_ARRAY("isOverlappingArraySupported"),

    /**
     * Whatever {@link MathTransform#inverse()} is supported.
     * Implementors can set the value for this key to {@code false} in order to test
     * {@linkplain MathTransform math transforms} which are not yet fully implemented.
     *
     * @see org.opengis.test.referencing.TransformTestCase#isInverseTransformSupported
     */
    INVERSE_TRANSFORM("isInverseTransformSupported"),

    /**
     * Whatever {@link MathTransform#derivative(DirectPosition)} is supported.
     * Implementors can set the value for this key to {@code false} in order to test
     * {@linkplain MathTransform math transforms} which are not yet fully implemented.
     *
     * @see org.opengis.test.referencing.TransformTestCase#isDerivativeSupported
     */
    DERIVATIVE_TRANSFORM("isDerivativeSupported"),

    /**
     * Whatever {@link MathTransformFactory#createAffineTransform(Matrix)} accepts non-square matrixes.
     *
     * @see org.opengis.test.referencing.AffineTransformTest#isNonSquareMatrixSupported
     */
    NON_SQUARE_MATRIX("isNonSquareMatrixSupported"),

    /**
     * Whatever (<var>y</var>,<var>x</var>) axis order is supported. This axis swapping is not
     * supported, then the tests that would normally expect (<var>y</var>,<var>x</var>) axis
     * order or <cite>South Oriented</cite> CRS will rather use the (<var>x</var>,<var>y</var>)
     * axis order and <cite>North Oriented</cite> CRS in their test.
     *
     * @see org.opengis.test.referencing.AuthorityFactoryTest#isAxisSwappingSupported
     */
    AXIS_SWAPPING("isAxisSwappingSupported"),

    /**
     * Whatever the {@link org.opengis.referencing.AuthorityFactory} support the objects creation
     * from unofficial EPSG codes. Some example of unofficial codes used in this test suite are:
     * <p>
     * <ul>
     *   <li>310642901 - Miller projection</li>
     * </ul>
     *
     * @see org.opengis.test.referencing.AuthorityFactoryTest#isUnofficialEpsgSupported
     *
     * @deprecated Will be removed before GeoAPI 3.1 release.
     */
    @Deprecated
    UNOFFICIAL_EPSG_CODES("isUnofficialEpsgSupported");

    /**
     * The name of the key used in the {@link Properties} map. This is also the name of the
     * {@code boolean} field in the {@link TestCase} subclasses that use this property.
     */
    public final String key;

    /**
     * Creates a new enum for the given key.
     */
    private SupportedOperation(final String key) {
        this.key = key;
    }

    /**
     * Returns whatever the operation represented by this enum is declared as supported in the
     * given configuration map. If the given map is {@code null} of if it does not contain the
     * {@linkplain #key} for this enum, then the default value is {@code true}.
     *
     * @param  configuration The map from which to read the support flag, or {@code null} if none.
     * @return {@code false} if the operation represented by this enum has been explicitely
     *         disabled in the given configuration map.
     */
    public boolean isSupported(final Properties configuration) {
        if (configuration != null) {
            final String value = configuration.getProperty(key);
            if (value != null) {
                return Boolean.parseBoolean(value);
            }
        }
        return true;
    }

    /**
     * Sets whatever the operation represented by this enum is supported. If an operation is not
     * explicitely declared as unsupported, then the default value is {@code true}.
     *
     * @param  configuration The map where to write the support flag.
     * @param  supported {@code false} if the operation is unsupported.
     * @return The previous supported flag of the operation represented by this enum.
     */
    public boolean setSupported(final Properties configuration, final boolean supported) {
        final Object old = configuration.setProperty(key, Boolean.toString(supported));
        return (old == null) || Boolean.parseBoolean(old.toString());
    }

    /**
     * Declared all the given operations as unsupported. This is a convenience method invoking
     * <code>{@linkplain #setSupported(Properties, boolean) setSupported}(configuration, false)</code>
     * for all the operations given in argument.
     *
     * @param configuration The map where to write the support flags.
     * @param operations The operations to declare as unsupported.
     */
    public static void unsupported(final Properties configuration, final SupportedOperation... operations) {
        for (final SupportedOperation operation : operations) {
            operation.setSupported(configuration, false);
        }
    }
}
