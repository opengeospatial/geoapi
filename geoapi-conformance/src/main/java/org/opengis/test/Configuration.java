/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2019 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.io.Serializable;

import org.opengis.util.Factory;
import org.opengis.util.CodeList;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.cs.CSFactory;
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.opengis.referencing.crs.CRSFactory;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.datum.DatumFactory;
import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransformFactory;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;


/**
 * Contains information about the test environment, like available factories and disabled tests.
 * {@code Configuration} is used in two places:
 *
 * <ul>
 *   <li>Before each test is executed, {@link ImplementationDetails} can provide configuration information.
 *       For example an implementation can declare that it does not support the calculation of transform derivative.</li>
 *   <li>After each test is executed, {@link TestListener} can obtain the actual configuration used by the test.
 *       For example listeners can know which {@link Factory} instances were used.</li>
 * </ul>
 *
 * This class provides {@link #get get}, {@link #put put} and {@link #remove remove} methods
 * similar to those of the {@code java.util.Map} interface, with the addition of type-safety.
 * The pre-defined keys are listed below:
 *
 * <table class="ogc">
 * <caption>Configuration properties</caption>
 * <tr>
 *   <th>Supported features</th>
 *   <th>Factories</th>
 *   <th>Other</th>
 * </tr>
 * <tr><td valign="top">
 * {@link Key#isMultiLocaleSupported              isMultiLocaleSupported}<br>
 * {@link Key#isMixedNameSyntaxSupported          isMixedNameSyntaxSupported}<br>
 * {@link Key#isStandardNameSupported             isStandardNameSupported}<br>
 * {@link Key#isStandardAliasSupported            isStandardAliasSupported}<br>
 * {@link Key#isDependencyIdentificationSupported isDependencyIdentificationSupported}<br>
 * {@link Key#isDoubleToDoubleSupported           isDoubleToDoubleSupported}<br>
 * {@link Key#isFloatToFloatSupported             isFloatToFloatSupported}<br>
 * {@link Key#isDoubleToFloatSupported            isDoubleToFloatSupported}<br>
 * {@link Key#isFloatToDoubleSupported            isFloatToDoubleSupported}<br>
 * {@link Key#isOverlappingArraySupported         isOverlappingArraySupported}<br>
 * {@link Key#isInverseTransformSupported         isInverseTransformSupported}<br>
 * {@link Key#isDerivativeSupported               isDerivativeSupported}<br>
 * {@link Key#isNonSquareMatrixSupported          isNonSquareMatrixSupported}<br>
 * {@link Key#isNonBidimensionalSpaceSupported    isNonBidimensionalSpaceSupported}<br>
 * {@link Key#isAxisSwappingSupported             isAxisSwappingSupported}</td><td valign="top">
 * {@link Key#mtFactory                           mtFactory}<br>
 * {@link Key#copFactory                          copFactory}<br>
 * {@link Key#copAuthorityFactory                 copAuthorityFactory}<br>
 * {@link Key#crsFactory                          crsFactory}<br>
 * {@link Key#crsAuthorityFactory                 crsAuthorityFactory}<br>
 * {@link Key#csFactory                           csFactory}<br>
 * {@link Key#csAuthorityFactory                  csAuthorityFactory}<br>
 * {@link Key#datumFactory                        datumFactory}<br>
 * {@link Key#datumAuthorityFactory               datumAuthorityFactory}<br>
 * {@link Key#isFactoryPreservingUserValues       isFactoryPreservingUserValues}</td><td valign="top">
 * {@link Key#validators                          validators}<br>
 * {@link Key#isValidationEnabled                 isValidationEnabled}<br>
 * {@link Key#isToleranceRelaxed                  isToleranceRelaxed}
 * </td></tr></table>
 *
 * @see TestCase#configuration()
 * @see ImplementationDetails#configuration(Factory[])
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class Configuration implements Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 4725836424438658750L;

    /**
     * The map were to store the configuration entries.
     */
    private final Map<Key<?>,Object> properties;

    /**
     * An unmodifiable view of the {@link #properties} map.
     *
     * @see #map()
     */
    private final Map<Key<?>,Object> unmodifiable;

    /**
     * Creates a new, initially empty, configuration map.
     */
    public Configuration() {
        properties = new LinkedHashMap<>();
        unmodifiable = Collections.unmodifiableMap(properties);
    }

    /**
     * Creates a new configuration with the same mappings as the specified configuration.
     *
     * @param  toCopy  the configuration whose mappings are to be placed in this map.
     * @throws NullPointerException if the specified configuration is null.
     */
    public Configuration(final Configuration toCopy) {
        properties = new LinkedHashMap<>(toCopy.properties);
        unmodifiable = Collections.unmodifiableMap(properties);
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null}
     * if this map contains no mapping for the key.
     *
     * @param  <T>  the value type, which is determined by the key.
     * @param  key  the key whose associated value is to be returned.
     * @return the value to which the specified key is mapped, or {@code null}
     *         if this map contains no mapping for the key.
     * @throws NullPointerException if the specified key is null.
     */
    public <T> T get(final Key<T> key) {
        return key.type.cast(properties.get(key));
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param  <T>  the value type, which is determined by the key.
     * @param  key  the key whose associated value is to be removed.
     * @return the value which was previously mapped to the specified key, or {@code null}.
     * @throws NullPointerException if the specified key is null.
     */
    public <T> T remove(final Key<T> key) {
        return key.type.cast(properties.remove(key));
    }

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param  <T>    the value type, which is determined by the key.
     * @param  key    the key with which the specified value is to be associated.
     * @param  value  the value to be associated with the specified key (can be {@code null}).
     * @return the previous value associated with {@code key}, or {@code null} if there was no mapping for that key.
     * @throws NullPointerException if the specified key is null.
     */
    public <T> T put(final Key<T> key, final T value) {
        return key.type.cast(properties.put(key, value));
    }

    /**
     * Declares that all given operations are unsupported. This is a convenience method
     * invoking <code>{@linkplain #put(Key,Object) put}(key, Boolean.False)</code> for
     * all operations given in argument.
     *
     * @param  operations  the operations to declare as unsupported.
     * @throws NullPointerException if a specified key is null.
     */
    @SafeVarargs
    public final void unsupported(final Key<Boolean>... operations) {
        for (final Key<Boolean> operation : operations) {
            put(operation, Boolean.FALSE);
        }
    }

    /**
     * Returns all entries as an unmodifiable map.
     *
     * @return a map view over the entries in this {@code Configuration} object.
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public Map<Key<?>,Object> map() {
        return unmodifiable;
    }

    /**
     * Returns a hash code value for this configuration map.
     */
    @Override
    public int hashCode() {
        return properties.hashCode() ^ (int) serialVersionUID;
    }

    /**
     * Compares this configuration with the given object for equality.
     *
     * @param  other  the other object to compare with this configuration.
     */
    @Override
    public boolean equals(final Object other) {
        if (other instanceof Configuration) {
            return properties.equals(((Configuration) other).properties);
        }
        return false;
    }

    /**
     * Returns a string representation of this configuration map.
     */
    @Override
    public String toString() {
        return properties.toString();
    }

    /**
     * Type-safe keys that can be used in a {@link Configuration} map.
     * This code list is extensible: users can create new instances by
     * invoking the {@link #valueOf(String, Class)} static method.
     *
     * <p><b><u>Note on field names:</u></b><br>
     * Every constants declared in this class have a name matching exactly the field names in
     * {@link TestCase} subclasses. This is a departure from the usual <cite>"all upper-case
     * letters"</cite> convention, but make the relationship with fields more obvious
     * and the parsing of {@link java.util.Properties} files easier.</p>
     *
     * @param  <T>  the type of values associated with the key.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     */
    public static final class Key<T> extends CodeList<Key<?>> {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = -5920183652024058448L;

        /**
         * The list of all keys created. Contains the key constants declared in this class, and
         * any key that the user may have created. Must be declared before any key declaration.
         */
        private static final List<Key<?>> VALUES = new ArrayList<>(32);

        /*
         * If new constants are added, please remember to update the Configuration class javadoc.
         */

        /**
         * Whether the {@link InternationalString} instances can support more than one {@link java.util.Locale}.
         * If {@code false}, then the factory method may retain only one locale among the set of user-provided
         * localized strings.
         *
         * @see org.opengis.test.util.NameTest#isMultiLocaleSupported
         */
        public static final Key<Boolean> isMultiLocaleSupported =
                new Key<>(Boolean.class, "isMultiLocaleSupported");

        /**
         * Whether the {@link GenericName} instances can apply different syntax rules in different
         * parts of their name. If {@code true}, then URI using different separators in different
         * parts of their name (e.g. {@code ":"}, {@code "."}, {@code "/"} and {@code "#"}
         * in {@code "http://www.opengis.net/gml/srs/epsg.xml#4326"}) are supported.
         * If {@code false}, then only a single rule can be applied to the name as a whole
         * (e.g. only the {@code ":"} separator is used in {@code "urn:ogc:def:crs:epsg:4326"}).
         *
         * @see org.opengis.test.util.NameTest#isMixedNameSyntaxSupported
         */
        public static final Key<Boolean> isMixedNameSyntaxSupported =
                new Key<>(Boolean.class, "isMixedNameSyntaxSupported");

        /**
         * Whether the {@link IdentifiedObject} instances have {@linkplain IdentifiedObject#getName()
         * names} matching the names declared in the EPSG database.
         *
         * @see org.opengis.test.referencing.gigs.AuthorityFactoryTestCase#isStandardNameSupported
         */
        public static final Key<Boolean> isStandardNameSupported =
                new Key<>(Boolean.class, "isStandardNameSupported");

        /**
         * Whether the {@link IdentifiedObject} instances have at least the
         * {@linkplain IdentifiedObject#getAlias() aliases} declared in the EPSG database.
         *
         * @see org.opengis.test.referencing.gigs.AuthorityFactoryTestCase#isStandardAliasSupported
         */
        public static final Key<Boolean> isStandardAliasSupported =
                new Key<>(Boolean.class, "isStandardAliasSupported");

        /**
         * Whether the {@link IdentifiedObject} instances created indirectly by the factories
         * are expected to have correct identification information.
         *
         * @see org.opengis.test.referencing.gigs.AuthorityFactoryTestCase#isDependencyIdentificationSupported
         */
        public static final Key<Boolean> isDependencyIdentificationSupported =
                new Key<>(Boolean.class, "isDependencyIdentificationSupported");

        /**
         * Whether the authority factory supports creation of deprecated {@link IdentifiedObject} instances.
         *
         * @see org.opengis.test.referencing.gigs.AuthorityFactoryTestCase#isDeprecatedObjectCreationSupported
         */
        public static final Key<Boolean> isDeprecatedObjectCreationSupported =
                new Key<>(Boolean.class, "isDeprecatedObjectCreationSupported");

        /**
         * Whether {@link MathTransform#transform(double[], int, double[], int, int)} is supported.
         * Implementors can set the value for this key to {@code false} in order to test
         * {@link MathTransform} instances which are not yet fully implemented.
         *
         * @see org.opengis.test.referencing.TransformTestCase#isDoubleToDoubleSupported
         */
        public static final Key<Boolean> isDoubleToDoubleSupported =
                new Key<>(Boolean.class, "isDoubleToDoubleSupported");

        /**
         * Whether {@link MathTransform#transform(float[], int, float[], int, int)} is supported.
         * Implementors can set the value for this key to {@code false} in order to test
         * {@link MathTransform} instances which are not yet fully implemented.
         *
         * @see org.opengis.test.referencing.TransformTestCase#isFloatToFloatSupported
         */
        public static final Key<Boolean> isFloatToFloatSupported =
                new Key<>(Boolean.class, "isFloatToFloatSupported");

        /**
         * Whether {@link MathTransform#transform(double[], int, float[], int, int)} is supported.
         * Implementors can set the value for this key to {@code false} in order to test
         * {@link MathTransform} instances which are not yet fully implemented.
         *
         * @see org.opengis.test.referencing.TransformTestCase#isDoubleToFloatSupported
         */
        public static final Key<Boolean> isDoubleToFloatSupported =
                new Key<>(Boolean.class, "isDoubleToFloatSupported");

        /**
         * Whether {@link MathTransform#transform(float[], int, double[], int, int)} is supported.
         * Implementors can set the value for this key to {@code false} in order to test
         * {@link MathTransform} instances which are not yet fully implemented.
         *
         * @see org.opengis.test.referencing.TransformTestCase#isFloatToDoubleSupported
         */
        public static final Key<Boolean> isFloatToDoubleSupported =
                new Key<>(Boolean.class, "isFloatToDoubleSupported");

        /**
         * Whether source and destination arrays can overlap in {@link MathTransform} operations.
         * Overlapping occur when:
         *
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
        public static final Key<Boolean> isOverlappingArraySupported =
                new Key<>(Boolean.class, "isOverlappingArraySupported");

        /**
         * Whether {@link MathTransform#inverse()} is supported.
         * Implementors can set the value for this key to {@code false} in order to test
         * {@link MathTransform} instances which are not yet fully implemented.
         *
         * @see org.opengis.test.referencing.TransformTestCase#isInverseTransformSupported
         */
        public static final Key<Boolean> isInverseTransformSupported =
                new Key<>(Boolean.class, "isInverseTransformSupported");

        /**
         * Whether {@link MathTransform#derivative(DirectPosition)} is supported.
         * Implementors can set the value for this key to {@code false} in order to test
         * {@link MathTransform} instances which are not yet fully implemented.
         *
         * @see org.opengis.test.referencing.TransformTestCase#isDerivativeSupported
         */
        public static final Key<Boolean> isDerivativeSupported =
                new Key<>(Boolean.class, "isDerivativeSupported");

        /**
         * Whether {@link MathTransformFactory#createAffineTransform(Matrix)} accepts non-square matrixes.
         *
         * @see org.opengis.test.referencing.AffineTransformTest#isNonSquareMatrixSupported
         */
        public static final Key<Boolean> isNonSquareMatrixSupported =
                new Key<>(Boolean.class, "isNonSquareMatrixSupported");

        /**
         * Whether {@link MathTransformFactory} can create transforms between spaces that are
         * not two-dimensional. If {@code true}, then the tested spaces may be one-dimensional
         * (typically elevation or time), three-dimensional or four-dimensional.
         *
         * @see org.opengis.test.referencing.AffineTransformTest#isNonBidimensionalSpaceSupported
         */
        public static final Key<Boolean> isNonBidimensionalSpaceSupported =
                new Key<>(Boolean.class, "isNonBidimensionalSpaceSupported");

        /**
         * Whether (<var>y</var>,<var>x</var>) axis order is supported. This axis swapping is not
         * supported, then the tests that would normally expect (<var>y</var>,<var>x</var>) axis
         * order or <cite>South Oriented</cite> CRS will rather use the (<var>x</var>,<var>y</var>)
         * axis order and <cite>North Oriented</cite> CRS in their test.
         *
         * @see org.opengis.test.referencing.AuthorityFactoryTest#isAxisSwappingSupported
         */
        public static final Key<Boolean> isAxisSwappingSupported =
                new Key<>(Boolean.class, "isAxisSwappingSupported");

        /**
         * Whether the test methods can invoke a <code>{@linkplain TestCase#validators validators}.validate(…)}</code>
         * method. GeoAPI allows to disable the validation checks in some tests where strict conformance to a standard
         * is relaxed.
         *
         * <div class="note"><b>Example:</b>
         * ISO 19111 (the <cite>referencing by coordinates</cite> abstract model) specifies that the name of
         * the latitude axis in a geographic CRS shall be <cite>"Geodetic latitude"</cite> while ISO 19162
         * (a.k.a <cite>Well Known Text 2</cite>) specifies <cite>"Latitude"</cite>. Consequently the GeoAPI
         * conformance module allows implementor to disable the check for ISO 19111 conformance if their WKT
         * parser does not adapt the parsed CRS objects to the ISO 19111 axis naming.</div>
         *
         * @see org.opengis.test.wkt.CRSParserTest#isValidationEnabled
         */
        public static final Key<Boolean> isValidationEnabled =
                new Key<>(Boolean.class, "isValidationEnabled");

        /**
         * Whether the tolerance threshold of a {@link org.opengis.test.referencing.TransformTestCase}
         * has been relaxed. This information is determined after test execution.
         */
        public static final Key<Boolean> isToleranceRelaxed =
                new Key<>(Boolean.class, "isToleranceRelaxed");

        /**
         * The provider of {@linkplain Units units} to use for tests. If this configuration hint
         * is not specified, then the {@linkplain Units#getDefault() default instance} is used.
         */
        public static final Key<Units> units = new Key<>(Units.class, "units");

        /**
         * The {@linkplain MathTransformFactory Math Transform factory} instance used for a test.
         *
         * @see org.opengis.test.referencing.AffineTransformTest#mtFactory
         * @see org.opengis.test.referencing.ParameterizedTransformTest#mtFactory
         * @see org.opengis.test.referencing.PseudoEpsgFactory#mtFactory
         */
        public static final Key<MathTransformFactory> mtFactory =
                new Key<>(MathTransformFactory.class, "mtFactory");

        /**
         * The {@linkplain CoordinateOperationFactory Coordinate Operation factory} instance used for a test.
         *
         * @see org.opengis.test.referencing.PseudoEpsgFactory#copFactory
         * @see org.opengis.test.referencing.gigs.GIGS3005#copFactory
         */
        public static final Key<CoordinateOperationFactory> copFactory =
                new Key<>(CoordinateOperationFactory.class, "copFactory");

        /**
         * The {@linkplain CoordinateOperationAuthorityFactory Coordinate Operation authority factory}
         * instance used for a test.
         *
         * @see org.opengis.test.referencing.gigs.AuthorityFactoryTestCase
         */
        public static final Key<CoordinateOperationAuthorityFactory> copAuthorityFactory =
                new Key<>(CoordinateOperationAuthorityFactory.class, "copAuthorityFactory");

        /**
         * The {@linkplain CRSFactory Coordinate Reference System factory} instance used for a test.
         *
         * @see org.opengis.test.referencing.ObjectFactoryTest#crsFactory
         * @see org.opengis.test.referencing.PseudoEpsgFactory#crsFactory
         * @see org.opengis.test.referencing.gigs.GIGS3004#crsFactory
         */
        public static final Key<CRSFactory> crsFactory =
                new Key<>(CRSFactory.class, "crsFactory");

        /**
         * The {@linkplain CRSAuthorityFactory Coordinate Reference System authority factory}
         * instance used for a test.
         *
         * @see org.opengis.test.referencing.AuthorityFactoryTest#crsAuthorityFactory
         * @see org.opengis.test.referencing.gigs.AuthorityFactoryTestCase
         */
        public static final Key<CRSAuthorityFactory> crsAuthorityFactory =
                new Key<>(CRSAuthorityFactory.class, "crsAuthorityFactory");

        /**
         * The {@linkplain CSFactory Coordinate System factory} instance used for a test.
         *
         * @see org.opengis.test.referencing.ObjectFactoryTest#csFactory
         * @see org.opengis.test.referencing.PseudoEpsgFactory#csFactory
         */
        public static final Key<CSFactory> csFactory =
                new Key<>(CSFactory.class, "csFactory");

        /**
         * The {@linkplain CSAuthorityFactory Coordinate System authority factory} instance used for a test.
         *
         * @see org.opengis.test.referencing.AuthorityFactoryTest#csAuthorityFactory
         * @see org.opengis.test.referencing.gigs.AuthorityFactoryTestCase
         */
        public static final Key<CSAuthorityFactory> csAuthorityFactory =
                new Key<>(CSAuthorityFactory.class, "csAuthorityFactory");

        /**
         * The {@linkplain DatumFactory Datum factory} instance used for a test.
         *
         * @see org.opengis.test.referencing.ObjectFactoryTest#datumFactory
         * @see org.opengis.test.referencing.PseudoEpsgFactory#datumFactory
         * @see org.opengis.test.referencing.gigs.GIGS3003#datumFactory
         */
        public static final Key<DatumFactory> datumFactory =
                new Key<>(DatumFactory.class, "datumFactory");

        /**
         * The {@linkplain DatumAuthorityFactory Datum authority factory} instance used for a test.
         *
         * @see org.opengis.test.referencing.AuthorityFactoryTest#datumAuthorityFactory
         * @see org.opengis.test.referencing.gigs.AuthorityFactoryTestCase
         */
        public static final Key<DatumAuthorityFactory> datumAuthorityFactory =
                new Key<>(DatumAuthorityFactory.class, "datumAuthorityFactory");

        /**
         * Whether the objects created by the tested {@link org.opengis.referencing.ObjectFactory} use the
         * specified values <i>as-is</i>. This flag should be set to {@code false} if the factory performs
         * any of the following operations:
         *
         * <ul>
         *   <li>Convert numerical values from user-provided linear units to metres.</li>
         *   <li>Convert numerical values from user-provided angular units to degrees.</li>
         *   <li>Change ellipsoid second defining parameter
         *       (e.g. from <i>semi-major axis length</i> to an equivalent <i>inverse flattening factor</i>).</li>
         *   <li>Change map projection parameters
         *       (e.g. from <i>standard parallel</i> to an equivalent <i>scale factor</i>).</li>
         *   <li>Any other change that preserve numeric equivalence.</li>
         * </ul>
         *
         * If the factory does not perform any of the above conversions, then this flag can be {@code true}.
         *
         * @see org.opengis.test.referencing.gigs.UserObjectFactoryTestCase#isFactoryPreservingUserValues
         */
        public static final Key<Boolean> isFactoryPreservingUserValues =
                new Key<>(Boolean.class, "isFactoryPreservingUserValues");

        /**
         * The set of {@link Validator} instances to use for validating objects.
         * If no value is provided for this key, then the system-wide
         * {@linkplain Validators#DEFAULT default validators} are used.
         *
         * @see Validators#DEFAULT
         */
        public static final Key<ValidatorContainer> validators =
                new Key<>(ValidatorContainer.class, "validators");

        /**
         * The type of values associated to this key.
         */
        final Class<T> type;

        /**
         * Constructs a key with the given name. The new key is
         * automatically added to the list returned by {@link #values}.
         *
         * @param  type  the type of values associated to the new key.
         * @param  name  the key name. This name must not be in use by any other key.
         */
        private Key(final Class<T> type, final String name) {
            super(name, VALUES);
            this.type = type;
        }

        /**
         * Returns the key that matches the given name, or returns a new one if none match it.
         * If no existing instance is found, then a new one is created for the given name and
         * type.
         *
         * @param  <T>   the type of the key to fetch.
         * @param  type  the type of the key to fetch.
         * @param  name  the name of the key to fetch or to create.
         * @return a key matching the given name.
         * @throws NullPointerException if the given name or type is {@code null}.
         * @throws ClassCastException if a key is found but the type is not assignable to the given type.
         */
        @SuppressWarnings("unchecked")
        public static <T> Key<? extends T> valueOf(final String name, final Class<T> type) {
            Objects.requireNonNull(type, "type");
            final Key<?> key = valueOf(Key.class, (code) -> name.equals(code.name()), null);
            if (key != null) {
                if (!type.isAssignableFrom(key.type)) {
                    throw new ClassCastException(key.type.getName());
                }
                return (Key) key;
            }
            return new Key<>(type, name);
        }

        /**
         * Returns the list of {@code Key}s.
         *
         * @return the list of keys declared in the current JVM.
         */
        public static Key<?>[] values() {
            synchronized (VALUES) {
                return VALUES.toArray(new Key<?>[VALUES.size()]);
            }
        }

        /**
         * Returns the list of codes of the same kind than this code list element.
         * Invoking this method is equivalent to invoking {@link #values()}, except that
         * this method can be invoked on an instance of the parent {@code CodeList} class.
         *
         * @return all code {@linkplain #values() values} for this code list.
         */
        @Override
        public Key<?>[] family() {
            return values();
        }

        /**
         * Returns the type of values assigned to this key.
         *
         * @return the value type.
         */
        public Class<T> valueType() {
            return type;
        }
    }
}
