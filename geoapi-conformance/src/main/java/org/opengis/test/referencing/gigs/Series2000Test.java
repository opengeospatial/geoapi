/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2012 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing.gigs;

import java.util.List;
import java.util.Collection;
import javax.measure.unit.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.converter.UnitConverter;
import javax.measure.converter.ConversionException;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.util.GenericName;
import org.opengis.referencing.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.test.Configuration;
import org.opengis.test.FactoryFilter;
import org.opengis.test.TestCase;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static java.lang.StrictMath.*;
import static javax.measure.unit.Unit.ONE;
import static javax.measure.unit.SI.METRE;
import static javax.measure.unit.SI.RADIAN;
import static javax.measure.unit.NonSI.DEGREE_ANGLE;
import static org.opengis.test.Assert.*;


/**
 * Pre-defined geodetic parameter library test.
 * The tests for this series are designed to verify the correctness of geodetic parameters that
 * are delivered with the software. The comparison to be taken as truth is the EPSG Dataset.
 *
 * @see org.opengis.test.referencing.AuthorityFactoryTest
 * @see org.opengis.test.TestSuite
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Parameterized.class)
public strictfp class Series2000Test extends TestCase {
    /**
     * Relative tolerance factor from GIGS documentation.
     */
    private static final double TOLERANCE = 1E-10;

    /**
     * Angular tolerance from GIGS documentation.
     */
    private static final double ANGULAR_TOLERANCE = 1E-7;

    /**
     * Factory to build {@link CoordinateReferenceSystem} instances, or {@code null} if none.
     */
    protected final CRSAuthorityFactory crsAuthorityFactory;

    /**
     * Factory to build {@link CoordinateSystem} instances, or {@code null} if none.
     */
    protected final CSAuthorityFactory csAuthorityFactory;

    /**
     * Factory to build {@link Datum} instances, or {@code null} if none.
     */
    protected final DatumAuthorityFactory datumAuthorityFactory;

    /**
     * Factory to build {@link CoordinateOperation} instances, or {@code null} if none.
     */
    protected final CoordinateOperationAuthorityFactory copAuthorityFactory;

    /**
     * {@code true} if the factories support {@linkplain IdentifiedObject#getName() name}.
     * If {@code true} (the default), then the test methods will ensure that the identified
     * objects created by the factories declare the same name than the GIGS tests.
     * If {@code false}, then the names are ignored.
     */
    protected boolean isNameSupported;

    /**
     * {@code true} if the factories support {@linkplain IdentifiedObject#getAlias() aliases}.
     * If {@code true} (the default), then the test methods will ensure that the identified
     * objects created by the factories declare at least all the aliases enumerated in the
     * GIGS tests - additional aliases, if any, are ignored. If {@code false}, then the aliases
     * are ignored.
     */
    protected boolean isAliasSupported;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code AuthorityFactoryTest} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(FactoryFilter.ByAuthority.EPSG,
                CRSAuthorityFactory.class, CSAuthorityFactory.class, DatumAuthorityFactory.class,
                CoordinateOperationAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factories. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory   Factory for creating {@link CoordinateReferenceSystem} instances.
     * @param csFactory    Factory for creating {@link CoordinateSystem} instances.
     * @param datumFactory Factory for creating {@link Datum} instances.
     * @param copFactory   Factory for creating {@link CoordinateOperation} instances.
     */
    public Series2000Test(final CRSAuthorityFactory crsFactory, final CSAuthorityFactory csFactory,
            final DatumAuthorityFactory datumFactory, final CoordinateOperationAuthorityFactory copFactory)
    {
        super(crsFactory, csFactory, datumFactory, copFactory);
        crsAuthorityFactory   = crsFactory;
        csAuthorityFactory    = csFactory;
        datumAuthorityFactory = datumFactory;
        copAuthorityFactory   = copFactory;
        @SuppressWarnings("unchecked")
        final boolean[] isEnabled = getEnabledFlags(
                Configuration.Key.isNameSupported,
                Configuration.Key.isAliasSupported);
        isNameSupported  = isEnabled[0];
        isAliasSupported = isEnabled[1];
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     * <p>
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isNameSupported}</li>
     *       <li>{@link #isAliasSupported}</li>
     *       <li>{@linkplain #crsAuthorityFactory}</li>
     *       <li>{@linkplain #csAuthorityFactory}</li>
     *       <li>{@linkplain #datumAuthorityFactory}</li>
     *       <li>{@linkplain #copAuthorityFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.isNameSupported,       isNameSupported));
        assertNull(op.put(Configuration.Key.isAliasSupported,      isAliasSupported));
        assertNull(op.put(Configuration.Key.crsAuthorityFactory,   crsAuthorityFactory));
        assertNull(op.put(Configuration.Key.csAuthorityFactory,    csAuthorityFactory));
        assertNull(op.put(Configuration.Key.datumAuthorityFactory, datumAuthorityFactory));
        assertNull(op.put(Configuration.Key.copAuthorityFactory,   copAuthorityFactory));
        return op;
    }

    /**
     * Invoked when the implementation does not support one of the code defined in
     * the GIGS test suite.
     *
     * @param type      The GeoAPI interface of the object to construct.
     * @param code      The EPSG code of the object to create.
     * @param e         The exception we got while trying to instantiate the object.
     * @param important Was the object particularly important to E&P industry?
     */
    private void unsupportedCode(final Class<?> type, final int code,
            final NoSuchIdentifierException e, final boolean important)
    {
        // TODO
    }

    /**
     * Returns the name of the given object, or {@code null} if none.
     */
    private static String getName(final IdentifiedObject object) {
        if (object != null) {
            final ReferenceIdentifier name = object.getName();
            if (name != null) {
                return name.getCode();
            }
        }
        return null;
    }

    /**
     * Compares the given generic names with the given set of expected aliases. This method
     * verifies that the given collection contains at least the expected aliases. However
     * the collection may contain additional aliases, which will be ignored.
     *
     * @param message  The message to show in case of failure.
     * @param expected The expected aliases.
     * @param aliases  The actual aliases.
     */
    private static void testAliases(final String message, final String[] expected,
            final Collection<GenericName> aliases)
    {
        assertNotNull(message, aliases);
next:   for (final String search : expected) {
            for (final GenericName alias : aliases) {
                final String tip = alias.tip().toString();
                if (search.equalsIgnoreCase(tip)) {
                    continue next;
                }
            }
            fail(message + ": alias not found: " + search);
        }
    }

    /**
     * Ensures that the given collection contains at least one identifier having the EPSG
     * codespace (ignoring case) and the given code.
     *
     * @param message     The message to show in case of failure.
     * @param expected    The expected identifier code.
     * @param identifiers The actual identifiers.
     */
    private static void testIdentifier(final String message, final int expected,
            final Collection<? extends ReferenceIdentifier> identifiers)
    {
        assertNotNull(message, identifiers);
        int found = 0;
        for (final ReferenceIdentifier id : identifiers) {
            if (id.getCodeSpace().trim().equalsIgnoreCase("EPSG")) {
                found++;
                try {
                    assertEquals(message, expected, Integer.parseInt(id.getCode()));
                } catch (NumberFormatException e) {
                    fail(message + ".getCode(): " + e);
                }
            }
        }
        assertEquals(message + ": occurrence of EPSG:" + expected, 1, found);
    }

    /**
     * Returns the concatenation of the given prefix and suffix.
     * This is used for building messages in JUnit assert statements.
     */
    private static String message(final StringBuilder prefix, final String suffix) {
        final int length = prefix.length();
        final String message = prefix.append(suffix).toString();
        prefix.setLength(length);
        return message;
    }

    /**
     * Reference Units of Measure test.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>To verify reference units of measure bundled with the geoscience software.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Compare unit definitions included in the software against the EPSG Dataset.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_2001_libUnit.csv">{@code GIGS_2001_libUnit.csv}</a>.
     *   This file contains three separate blocks of data for linear units, angular units and scaling
     *   units. It gives the EPSG code and name for the unit of measure, together with the ratio of
     *   the unit to the ISO base unit for that unit type.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>Unit of measure definitions bundled with software should have the ratio to the
     *   appropriate base unit as in the EPSG Dataset. The values of the base unit per unit
     *   should be correct to at least 10 significant figures. Units missing from the software
     *   or included in the software additional to those in the EPSG Dataset or at variance with
     *   those in the EPSG Dataset should be reported.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error (other than {@linkplain NoSuchAuthorityCodeException
     *         unsupported code}) occurred while creating a unit from an EPSG code.
     * @throws ConversionException If an error occurred while converting a sample value from the
     *         tested unit to the base unit.
     */
    @Test
    public void test2001() throws FactoryException, ConversionException {
        assumeNotNull(csAuthorityFactory);
        final ExpectedData data = new ExpectedData("GIGS_2001_libUnit.csv",
                Integer.class,  // [0]: EPSG UoM Code
                String .class,  // [1]: Type
                String .class,  // [2]: Name of Units used in EPSG db parameters
                Double .class,  // [3]: Base units per unit
                Boolean.class,  // [4]: Particularly important to E&P industry?
                String .class); // [5]: Specific usage / Remarks

        while (data.next()) {
            final int    code   = data.getInt   (0);
            final String type   = data.getString(1);
            final String name   = data.getString(2);
            final double factor = data.getDouble(3);
            final Unit<?> unit, base;
            try {
                unit = csAuthorityFactory.createUnit(String.valueOf(code));
            } catch (NoSuchAuthorityCodeException e) {
                unsupportedCode(Unit.class, code, e, data.getBoolean(4));
                continue;
            }
            if      (type.equalsIgnoreCase("Linear")) base = METRE;
            else if (type.equalsIgnoreCase("Angle" )) base = RADIAN;
            else if (type.equalsIgnoreCase("Scale" )) base = ONE;
            else throw new DataException("Unknown type: " + type);
            UnitConverter converter = unit.getConverterToAny(base);
            assertEquals(name, 0, converter.convert( 0), TOLERANCE);
            switch (code) {
                default: {
                    assertEquals(name,  factor, converter.convert( 1), TOLERANCE * factor);
                    assertEquals(name, -factor, converter.convert(-1), TOLERANCE * factor);
                    for (double sample=-90; sample<=90; sample += 2.8125) {
                        final double expected = sample * factor;
                        assertEquals(name, expected, converter.convert(sample), (expected != 0) ? abs(expected)*TOLERANCE : TOLERANCE);
                    }
                    break;
                }
                case 9110: {
                    // Special cases for sexagesimal degrees
                    converter = unit.getConverterToAny(DEGREE_ANGLE);
                    assertEquals(name,  10.00, converter.convert( 10.0000), 10*TOLERANCE);
                    assertEquals(name, -10.00, converter.convert(-10.0000), 10*TOLERANCE);
                    assertEquals(name,  20.01, converter.convert( 20.0036), 20*TOLERANCE);
                    assertEquals(name, -20.01, converter.convert(-20.0036), 20*TOLERANCE);
                    assertEquals(name,  30.50, converter.convert( 30.3000), 30*TOLERANCE);
                    assertEquals(name, -30.50, converter.convert(-30.3000), 30*TOLERANCE);
                    assertEquals(name,  40.99, converter.convert( 40.5924), 40*TOLERANCE);
                    assertEquals(name, -40.99, converter.convert(-40.5924), 40*TOLERANCE);
                    break;
                }
                case 9203: {
                    // Special cases for dimensionless coefficient.
                    break;
                }
            }
        }
    }

    /**
     * Reference ellipsoid test.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>To verify reference ellipsoid parameters bundled with the geoscience software.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Compare ellipsoid definitions included in the software against the EPSG Dataset.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_2002_libEllipsoid.csv">{@code GIGS_2002_libEllipsoid.csv}</a>.
     *   This file gives the EPSG code and name for the ellipsoid, commonly encountered alternative
     *   name(s) for the same object, the value and units for the semi-major axis, the conversion
     *   ratio to metres for these units, and then a second parameter which will be either the value
     *   of the inverse flattening (unitless) or the value of the semi-minor axis (in the same units
     *   as the semi-major axis). It additionally contain a flag to indicate that the figure is a
     *   sphere: if {@code false} the figure is an oblate ellipsoid.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>Ellipsoid definitions bundled with software, if any, should have same name and defining
     *   parameters as in the EPSG Dataset. Equivalent alternative parameters are acceptable but
     *   should be reported. The values of the parameters should be correct to at least 10 significant
     *   figures. For ellipsoids defined by Clarke and Everest, as well as those adopted by IUGG as
     *   International, several variants exist. These must be clearly distinguished. Ellipsoids
     *   missing from the software or included in the software additional to those in the EPSG Dataset
     *   or at variance with those in the EPSG Dataset should be reported.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error (other than {@linkplain NoSuchAuthorityCodeException
     *         unsupported code}) occurred while creating an ellipsoid from an EPSG code.
     */
    @Test
    public void test2002() throws FactoryException {
        assumeNotNull(datumAuthorityFactory);
        final ExpectedData data = new ExpectedData("GIGS_2002_libEllipsoid.csv",
            Integer.class,  // [ 0]: EPSG Ellipsoid Code
            Boolean.class,  // [ 1]: Particularly important to E&P industry?
            String .class,  // [ 2]: EPSG Ellipsoid Name
            String .class,  // [ 3]: Alias(es) given by EPSG
            Double .class,  // [ 4]: Semi-major axis (a)
            String .class,  // [ 5]: Unit Name
            Double .class,  // [ 6]: Unit Conversion Factor
            Double .class,  // [ 7]: Semi-major axis (a) in metres
            Double .class,  // [ 8]: Second defining parameter: Inverse flattening (1/f)
            Double .class,  // [ 9]: Second defining parameter: Semi-minor axis (b)
            Boolean.class); // [10]: Sphere?

         final StringBuilder prefix = new StringBuilder("Ellipsoid[");
         final int prefixLength = prefix.length();
         while (data.next()) {
            final int code = data.getInt(0);
            final Ellipsoid ellipsoid;
            try {
                ellipsoid = datumAuthorityFactory.createEllipsoid(String.valueOf(code));
            } catch (NoSuchAuthorityCodeException e) {
                unsupportedCode(Ellipsoid.class, code, e, data.getBoolean(1));
                continue;
            }
            validators.validate(ellipsoid);
            prefix.setLength(prefixLength);
            prefix.append(code).append("].");
            testIdentifier(message(prefix, "getIdentifiers()"), code, ellipsoid.getIdentifiers());
            if (isNameSupported) {
                assertEquals(message(prefix, "getName()"), data.getString(2), getName(ellipsoid));
            }
            if (isAliasSupported) {
                testAliases(message(prefix, "getAlias()"), data.getStrings(3), ellipsoid.getAlias());
            }
            /*
             * Get the axis lengths and their unit. Null units are assumed to mean metres
             * (whatever we accept null unit or not is determined by the validators).
             * If the implementation uses metre units but the EPSG definition expected
             * another unit, convert the axis lengths from the later units to metre units.
             */
            final Unit<Length> unit = ellipsoid.getAxisUnit();
            double  semiMajor         = data.getDouble ( 4);
            double  semiMinor         = data.getDouble ( 9);
            double  inverseFlattening = data.getDouble ( 8);
            double  toMetres          = data.getDouble ( 6);
            boolean isSphere          = data.getBoolean(10);
            if (!Double.isNaN(toMetres) && (unit == null || unit.equals(METRE))) {
                semiMajor  = data.getDouble(7);
                semiMinor *= toMetres;
            }
            assertEquals(message(prefix, "isSphere()"),         isSphere,  ellipsoid.isSphere());
            assertEquals(message(prefix, "getSemiMajorAxis()"), semiMajor, ellipsoid.getSemiMajorAxis(), TOLERANCE*semiMajor);
            if (!Double.isNaN(semiMinor)) {
                assertEquals(message(prefix, "getSemiMinorAxis()"), semiMinor,
                        ellipsoid.getSemiMinorAxis(), TOLERANCE*semiMinor);
            }
            if (!Double.isNaN(inverseFlattening)) {
                assertEquals(message(prefix, "getInverseFlattening()"), inverseFlattening,
                        ellipsoid.getInverseFlattening(), TOLERANCE*inverseFlattening);
            }
        }
    }

    /**
     * Reference prime meridians.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>To verify reference prime meridians bundled with the geoscience software.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Compare prime meridian definitions included in the software against the EPSG Dataset.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_2003_libPrimeMeridian.csv">{@code GIGS_2003_libPrimeMeridian.csv}</a>.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>Prime meridian definitions bundled with the software should have the same name and Greenwich
     *   Longitude as in the EPSG Dataset. Equivalent alternative units are acceptable but should be reported.
     *   The values of the Greenwich Longitude should be correct to at least 7 decimal places (of degrees or grads).
     *   Meridians missing from the software or included in the software additional to those in the EPSG Dataset or
     *   at variance with those in the EPSG Dataset should be reported.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error (other than {@linkplain NoSuchAuthorityCodeException
     *         unsupported code}) occurred while creating a prime meridian from an EPSG code.
     */
    @Test
    public void test2003() throws FactoryException {
        assumeNotNull(datumAuthorityFactory);
        final ExpectedData data = new ExpectedData("GIGS_2003_libPrimeMeridian.csv",
            Integer.class,  // [0]: EPSG Prime Meridian Code
            Boolean.class,  // [1]: Particularly important to E&P industry?
            String .class,  // [2]: EPSG Prime Meridian Name
            String .class,  // [3]: EPSG Alias
            String .class,  // [4]: Longitude from Greenwich (sexagesimal)
            String .class,  // [5]: Unit Name
            Double .class,  // [6]: Longitude from Greenwich (decimal degrees)
            String .class); // [7]: Remarks

         final StringBuilder prefix = new StringBuilder("PrimeMeridian[");
         final int prefixLength = prefix.length();
         while (data.next()) {
            final int code = data.getInt(0);
            final PrimeMeridian pm;
            try {
                pm = datumAuthorityFactory.createPrimeMeridian(String.valueOf(code));
            } catch (NoSuchAuthorityCodeException e) {
                unsupportedCode(PrimeMeridian.class, code, e, data.getBoolean(1));
                continue;
            }
            validators.validate(pm);
            prefix.setLength(prefixLength);
            prefix.append(code).append("].");
            testIdentifier(message(prefix, "getIdentifiers()"), code, pm.getIdentifiers());
            if (isNameSupported) {
                assertEquals(message(prefix, "getName()"), data.getString(2), getName(pm));
            }
            if (isAliasSupported) {
                testAliases(message(prefix, "getAlias()"), data.getStrings(3), pm.getAlias());
            }
            /*
             * Before to compare the Greenwich longitude, convert the expected angular value
             * from decimal degrees to the units actually used by the implementation. We do
             * the conversion that way rather than the opposite way in order to have a more
             * appropriate error message in case of failure.
             */
            final Unit<Angle> unit = pm.getAngularUnit();
            double longitude = data.getDouble(6);
            if (unit != null && !unit.equals(DEGREE_ANGLE)) {
                longitude = DEGREE_ANGLE.getConverterTo(unit).convert(longitude);
            }
            assertEquals(message(prefix, "getGreenwichLongitude()"), longitude,
                    pm.getGreenwichLongitude(), ANGULAR_TOLERANCE);
        }
    }

    /**
     * Reference geodetic datums/geodetic CRSs.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>To verify reference geodetic datums and CRSs bundled with the geoscience software.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Compare geodetic datum and geocentric, geographic 3D and geographic 2D CRS definitions
     *   included in the geoscience software against the EPSG Dataset.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_2004_libGeodeticDatumCRS.csv">{@code GIGS_2004_libGeodeticDatumCRS.csv}</a>.
     *   Tests for component logical consistency is included: for example, if a higher-level
     *   library-defined component such as ED50 datum is selected it should then not be possible
     *   to change any of its lower-level components such as the ellipsoid from the pre-defined
     *   value (in this example International 1924).</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>Definitions bundled with the software should have the same name and associated ellipsoid
     *   and prime meridian as in the EPSG Dataset. CRSs missing from the software or included in the
     *   geoscience software additional to those in the EPSG Dataset or at variance with those in the
     *   EPSG Dataset should be reported.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error (other than {@linkplain NoSuchAuthorityCodeException
     *         unsupported code}) occurred while creating a geodetic CRS from an EPSG code.
     */
    @Test
    public void test2004() throws FactoryException {
        assumeTrue(datumAuthorityFactory != null || crsAuthorityFactory != null);
        final ExpectedData data = new ExpectedData("GIGS_2004_libGeodeticDatumCRS.csv",
            Integer.class,  // [0]: EPSG Datum Code
            String .class,  // [1]: Datum Name
            Integer.class,  // [2]: EPSG geocen CRS Code
            Integer.class,  // [3]: EPSG geog3D CRS Code
            Integer.class,  // [4]: EPSG geog2D CRS Code
            String .class,  // [5]: CRS Name
            Boolean.class,  // [6]: Particularly important to E&P industry?
            String .class,  // [7]: Ellipsoid Name
            String .class,  // [8]: Prime Meridian Name
            String .class); // [9]: Remarks

        final StringBuilder prefix = new StringBuilder();
        while (data.next()) {
            final int     datumCode = data.getInt   (0);
            final String  datumName = data.getString(1);
            final String  crsName   = data.getString(5);
            final boolean important = data.getBoolean(6);
            final String  ellName   = data.getString(7);
            final String  pmName    = data.getString(8);
            for (int column=1; column<=4; column++) {
                prefix.setLength(0);
                final GeodeticDatum datum;
                if (column == 1) {
                    /*
                     * First iteration: get directly the datum without building any CRS.
                     * The datum will be tested after the "else" block testing the CRS.
                     */
                    if (datumAuthorityFactory == null) {
                        continue;
                    }
                    try {
                        datum = datumAuthorityFactory.createGeodeticDatum(String.valueOf(datumCode));
                    } catch (NoSuchAuthorityCodeException e) {
                        unsupportedCode(GeodeticDatum.class, datumCode, e, important);
                        continue;
                    }
                    validators.validate(datum);
                } else {
                    /*
                     * All other iterations (columns 2-4): get the geodetic CRS, test its
                     * coordinate system, then extract the datum in order to perform the
                     * same tests than the ones we did in the first iteration.
                     */
                    if (crsAuthorityFactory == null) {
                        continue;
                    }
                    final Integer crsCode = data.getIntOptional(column);
                    if (crsCode == null) {
                        continue; // No CRS in the test file for that column.
                    }
                    final GeodeticCRS crs;
                    try {
                        switch (column) {
                            case 4:  // fallthrough
                            case 3:  crs = crsAuthorityFactory.createGeographicCRS(String.valueOf(crsCode)); break;
                            case 2:  crs = crsAuthorityFactory.createGeocentricCRS(String.valueOf(crsCode)); break;
                            default: throw new AssertionError(column);
                        }
                    } catch (NoSuchAuthorityCodeException e) {
                        final Class<? extends GeodeticCRS> type;
                        switch (column) {
                            case 4:  // fallthrough
                            case 3:  type = GeographicCRS.class; break;
                            case 2:  type = GeocentricCRS.class; break;
                            default: throw new AssertionError(column);
                        }
                        unsupportedCode(type, crsCode, e, important);
                        continue;
                    }
                    validators.validate(crs);
                    prefix.append("GeodeticCRS[").append(crsCode).append("].");
                    final int lengthAfterCRS = prefix.length();
                    testIdentifier(message(prefix, "getIdentifiers()"), crsCode, crs.getIdentifiers());
                    if (isNameSupported) {
                        assertEquals(message(prefix, "getName()"), crsName, getName(crs));
                    }
                    /*
                     * Tests the coordinate system.
                     */
                    final CoordinateSystem cs = crs.getCoordinateSystem();
                    assertNotNull(message(prefix, "getCoordinateSystem()"), cs);
                    prefix.append("CoordinateSystem.").append(datumCode).append("].");
                    final AxisDirection[] expectedDirections;
                    switch (column) {
                        case 4:  expectedDirections = new AxisDirection[] {
                                     AxisDirection.NORTH,
                                     AxisDirection.EAST};
                                 break;
                        case 3:  expectedDirections = new AxisDirection[] {
                                     AxisDirection.NORTH,
                                     AxisDirection.EAST,
                                     AxisDirection.UP};
                                 break;
                        case 2:  expectedDirections = new AxisDirection[] {
                                     AxisDirection.GEOCENTRIC_X,
                                     AxisDirection.GEOCENTRIC_Y,
                                     AxisDirection.GEOCENTRIC_Z};
                                 break;
                        default: throw new AssertionError(column);
                    }
                    assertEquals(message(prefix, "getDimension()"), expectedDirections.length, cs.getDimension());
                    assertAxisDirectionsEqual(message(prefix, "axes"), cs, expectedDirections);
                    prefix.setLength(lengthAfterCRS);
                    datum = crs.getDatum();
                    assertNotNull(message(prefix, "getDatum()"), datum);
                }
                /*
                 * Tests the datum.
                 */
                prefix.append("GeodeticDatum[").append(datumCode).append("].");
                final int lengthAfterDatum = prefix.length();
                testIdentifier(message(prefix, "getIdentifiers()"), datumCode, datum.getIdentifiers());
                if (isNameSupported) {
                    assertEquals(message(prefix, "getName()"), datumName, getName(datum));
                }
                /*
                 * Tests the ellipsoid.
                 */
                final Ellipsoid ellipsoid = datum.getEllipsoid();
                assertNotNull(message(prefix, "getEllipsoid()"), ellipsoid);
                prefix.append("Ellipsoid.");
                if (isNameSupported) {
                    assertEquals(message(prefix, "getName()"), ellName, getName(ellipsoid));
                }
                /*
                 * Tests the prime meridian.
                 */
                prefix.setLength(lengthAfterDatum);
                final PrimeMeridian pm = datum.getPrimeMeridian();
                assertNotNull(message(prefix, "getPrimeMeridian()"), pm);
                prefix.append("PrimeMeridian.");
                if (isNameSupported) {
                    assertEquals(message(prefix, "getName()"), pmName, getName(pm));
                }
            }
        }
    }

    /**
     * Reference map projections.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>To verify reference map projections bundled with the geoscience software.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Compare map projection definitions included in the software against the EPSG Dataset.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_2005_libProjection.csv">{@code GIGS_2005_libProjection.csv}</a>.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>Map projection definitions bundled with the software should have the same name, method
     *   name, defining parameters and parameter values as in the EPSG Dataset. The values of the
     *   parameters should be correct to at least 10 significant figures. Map projections missing
     *   from the software or included in the software additional to those in the EPSG Dataset or
     *   at variance with those in the EPSG Dataset should be reported.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error (other than {@linkplain NoSuchIdentifierException
     *         unsupported identifier}) occurred while creating an operation from an EPSG code.
     */
    @Test
    public void test2005() throws FactoryException {
        assumeNotNull(copAuthorityFactory);
        final ExpectedData data = new ExpectedData("GIGS_2005_libProjection.csv",
            String .class,  // [0]: EPSG Coordinate Operation Code(s)
            Boolean.class,  // [1]: Particularly important to E&P industry?
            String .class,  // [2]: Map Projection Name(s)
            String .class,  // [3]: Coordinate Operation Method
            String .class); // [4]: Remarks

        final StringBuilder prefix = new StringBuilder("Projection[");
        final int prefixLength = prefix.length();
        while (data.next()) {
            final String method = data.getString(3);
            for (final int code : data.getInts(0)) {
                final CoordinateOperation cop;
                try {
                    cop = copAuthorityFactory.createCoordinateOperation(String.valueOf(code));
                } catch (NoSuchIdentifierException e) {
                    // Relaxed the exception type from NoSuchAuthorityCodeException because
                    // CoordinateOperation creation will typically use MathTransformFactory
                    // under the hood, which throws NoSuchIdentifierException for non-implemented
                    // operation methods (may be identified by their name rather than EPSG code).
                    unsupportedCode(CoordinateOperation.class, code, e, data.getBoolean(1));
                    continue;
                }
                validators.validate(cop);
                prefix.setLength(prefixLength);
                prefix.append(code).append("].");
                testIdentifier(message(prefix, "getIdentifiers()"), code, cop.getIdentifiers());
                assertInstanceOf(message(prefix, "class"), Conversion.class, cop);
                final Conversion conversion = (Conversion) cop;
                if (isNameSupported) {
                    assertEquals(message(prefix, "getMethod().getName()"), method, getName(conversion.getMethod()));
                }
            }
        }
    }

    /**
     * Reference projected CRSs.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>To verify reference projected CRSs bundled with the geoscience software.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Compare projected CRS definitions included in the software against the EPSG Dataset.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_2006_libProjectedCRS.csv">{@code GIGS_2006_libProjectedCRS.csv}</a>.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>Projected CRS definitions bundled with the software should have the same name,
     *   coordinate system (including units and axes abbreviations and axes order) and map
     *   projection as in the EPSG Dataset. CRSs missing from the software or included in the
     *   software additional to those in the EPSG Dataset or at variance with those in the EPSG
     *   Dataset should be reported.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error (other than {@linkplain NoSuchIdentifierException
     *         unsupported identifier}) occurred while creating a projected CRS from an EPSG code.
     */
    @Test
    public void test2006() throws FactoryException {
        assumeNotNull(crsAuthorityFactory);
        final ExpectedData data = new ExpectedData("GIGS_2006_libProjectedCRS.csv",
            String .class,  // [0]: EPSG projected CRS Code(s)
            Integer.class,  // [1]: EPSG Datum Code
            Boolean.class,  // [2]: Particularly important to E&P industry?
            String .class,  // [3]: Geographic CRS Name
            String .class,  // [4]: Associated projection(s)
            String .class); // [5]: Remarks

        final StringBuilder prefix = new StringBuilder("ProjectedCRS[");
        final int prefixLength = prefix.length();
        while (data.next()) {
            final int  datumCode = data.getInt(1);
            final String geoName = data.getString(3);
            for (final int code : data.getInts(0)) {
                final ProjectedCRS crs;
                try {
                    crs = crsAuthorityFactory.createProjectedCRS(String.valueOf(code));
                } catch (NoSuchIdentifierException e) { // See comment in test2005()
                    unsupportedCode(ProjectedCRS.class, code, e, data.getBoolean(2));
                    continue;
                }
                validators.validate(crs);
                prefix.setLength(prefixLength);
                prefix.append(code).append("].");
                testIdentifier(message(prefix, "getIdentifiers()"), code, crs.getIdentifiers());
                testIdentifier(message(prefix, "getDatum().getIdentifiers()"),
                        datumCode, crs.getDatum().getIdentifiers());
                if (isNameSupported) {
                    assertEquals(message(prefix, "getBaseCRS().getName()"),
                            geoName, getName(crs.getBaseCRS()));
                }
            }
        }
    }

    /**
     * Reference coordinate transformations.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>To verify reference coordinate transformations bundled with the geoscience software.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Compare transformation definitions included in the software against the EPSG Dataset.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_2007_libGeodTfm.csv">{@code GIGS_2007_libGeodTfm.csv}</a>.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>Transformation definitions bundled with the software should have the same name, method
     *   name, defining parameters and parameter values as in EPSG Dataset. The values of the parameters
     *   should be correct to at least 10 significant figures. Transformations missing from the software
     *   or included in the software additional to those in the EPSG Dataset or at variance with those
     *   in the EPSG Dataset should be reported.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error (other than {@linkplain NoSuchIdentifierException
     *         unsupported identifier}) occurred while creating an operation from an EPSG code.
     */
    @Test
    @Ignore
    public void test2007() throws FactoryException {
        assumeNotNull(copAuthorityFactory);
        final ExpectedData data = new ExpectedData("GIGS_2007_libGeodTfm.csv",
            Integer.class,  // [0]: EPSG Coordinate Operation Code
            Boolean.class,  // [1]: Particularly important to E&P industry?
            String .class,  // [2]: Transformation Name(s)
            String .class,  // [4]: Coordinate Operation Method
            String .class); // [5]: Remarks

        final StringBuilder prefix = new StringBuilder("ProjectedCRS[");
        final int prefixLength = prefix.length();
        while (data.next()) {
            final int code = data.getInt(0);
            final CoordinateOperation operation;
            try {
                operation = copAuthorityFactory.createCoordinateOperation(String.valueOf(code));
            } catch (NoSuchIdentifierException e) { // See comment in test2005()
                unsupportedCode(CoordinateOperation.class, code, e, data.getBoolean(1));
                continue;
            }
            validators.validate(operation);
            prefix.setLength(prefixLength);
            prefix.append(code).append("].");
            testIdentifier(message(prefix, "getIdentifiers()"), code, operation.getIdentifiers());
            if (isNameSupported) {
                assertEquals(message(prefix, "getName()"), data.getString(2), getName(operation));
            }
        }
    }
}
