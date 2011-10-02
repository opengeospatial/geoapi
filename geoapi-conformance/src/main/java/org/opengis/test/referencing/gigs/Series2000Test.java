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
package org.opengis.test.referencing.gigs;

import java.util.List;
import javax.measure.unit.Unit;
import javax.measure.converter.UnitConverter;
import javax.measure.converter.ConversionException;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.test.FactoryFilter;
import org.opengis.test.TestCase;

import org.junit.*;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import static java.lang.StrictMath.*;
import static javax.measure.unit.Unit.ONE;
import static javax.measure.unit.SI.METRE;
import static javax.measure.unit.NonSI.DEGREE_ANGLE;


/**
 * Pre-defined geodetic parameter library test.
 * The tests for this series are designed to verify the correctness of geodetic parameters that
 * are delivered with the software. The comparison to be taken as truth is the EPSG Dataset.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see org.opengis.test.referencing.AuthorityFactoryTest
 */
public strictfp class Series2000Test extends TestCase {
    /**
     * Factory to build {@link CoordinateReferenceSystem} instances, or {@code null} if none.
     */
    protected final CRSAuthorityFactory crsFactory;

    /**
     * Factory to build {@link CoordinateSystem} instances, or {@code null} if none.
     */
    protected final CSAuthorityFactory csFactory;

    /**
     * Factory to build {@link Datum} instances, or {@code null} if none.
     */
    protected final DatumAuthorityFactory datumFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code AuthorityFactoryTest} constructor.
     *
     * @since 3.1
     */
    @Parameterized.Parameters
    public static List<Factory[]> factories() {
        return factories(FactoryFilter.ByAuthority.EPSG,
                CRSAuthorityFactory.class, CSAuthorityFactory.class, DatumAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factories. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory   Factory for creating {@link CoordinateReferenceSystem} instances.
     * @param csFactory    Factory for creating {@link CoordinateSystem} instances.
     * @param datumFactory Factory for creating {@link Datum} instances.
     */
    public Series2000Test(final CRSAuthorityFactory crsFactory,
            final CSAuthorityFactory csFactory, final DatumAuthorityFactory datumFactory)
    {
        this.crsFactory   = crsFactory;
        this.csFactory    = csFactory;
        this.datumFactory = datumFactory;
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
     *   <td>EPSG Dataset and file <code>GIGS_2001_libUnit_[version_date].xls</code>. This file
     *   contains three separate blocks of data for linear units, angular units and scaling units.
     *   It gives the EPSG code and name for the unit of measure, together with the ratio of the
     *   unit to the ISO base unit for that unit type.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>Unit of measure definitions bundled with software should have the ratio to the
     *   appropriate base unit as in the EPSG Dataset. The values of the base unit per unit
     *   should be correct to at least 10 significant figures. Units missing from the software
     *   or included in the software additional to those in the EPSG Dataset or at variance with
     *   those in the EPSG Dataset should be reported.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error occurred while creating a Unit from an EPSG code.
     *         This does not include {@link NoSuchAuthorityCodeException}, which are reported
     *         as unsupported unit rather than test failure.
     * @throws ConversionException If an error occurred while converting a sample value from the
     *         tested unit to the base unit.
     */
    @Test
    public void test2001() throws FactoryException, ConversionException {
        testUnitConversion(METRE, 9001, "metre",                                 1.0);
        testUnitConversion(METRE, 9036, "kilometre",                          1000.0);
        testUnitConversion(METRE, 9002, "foot",                                  0.3048);
        testUnitConversion(METRE, 9003, "US survey foot",                        0.30480061);
        testUnitConversion(METRE, 9031, "German legal metre",                    1.000013597);
        testUnitConversion(METRE, 9005, "Clarke's foot",                         0.304797265);
        testUnitConversion(METRE, 9039, "Clarke's link",                         0.201166195);
        testUnitConversion(METRE, 9042, "British chain (Sears 1922)",           20.11676512);
        testUnitConversion(METRE, 9051, "British foot (Sears 1922)",             0.304799472);
        testUnitConversion(METRE, 9040, "British yard (Sears 1922)",             0.914398415);
        testUnitConversion(METRE, 9301, "British chain (Sears 1922 truncated)", 20.116756);
        testUnitConversion(METRE, 9084, "Indian yard",                           0.914398531);
        testUnitConversion(METRE, 9094, "Gold Coast foot",                       0.30479971);
        testUnitConversion(METRE, 9098, "link",                                  0.201168);

        testUnitConversion(DEGREE_ANGLE, 9101, "radian",            1.0);
        testUnitConversion(DEGREE_ANGLE, 9102, "degree",            0.017453293);
        testUnitConversion(DEGREE_ANGLE, 9104, "arc-second",        4.85E-06);
        testUnitConversion(DEGREE_ANGLE, 9105, "grad",              0.015707963);
        testUnitConversion(DEGREE_ANGLE, 9109, "microradian",       0.000001);
        testUnitConversion(DEGREE_ANGLE, 9110, "sexagesimal DMS",   Double.NaN); // Handled especially
        testUnitConversion(DEGREE_ANGLE, 9113, "centesimal second", 1.57E-06);

        testUnitConversion(ONE, 9201, "unity", 1);
        testUnitConversion(ONE, 9202, "parts per million", 0.000001);
        testUnitConversion(ONE, 9203, "coefficient", 1);
    }

    /**
     * Invoked by {@link #test2001()} for each particular unit to be tested.
     * This method tests the conversion of an arbitrary range of sample values.
     *
     * @param  base   The base unit.
     * @param  code   The EPSG code of the unit to test.
     * @param  name   The expected name of the tested unit.
     * @param  factor The expected conversion factor from tested unit to base unit.
     * @throws FactoryException If an error occurred while creating a Unit from an EPSG code.
     *         This does not include {@link NoSuchAuthorityCodeException}, which are reported
     *         as unsupported unit rather than test failure.
     * @throws ConversionException If an error occurred while converting a sample value from the
     *         tested unit to the base unit.
     */
    private void testUnitConversion(final Unit<?> base, final int code, final String name, final double factor)
            throws FactoryException, ConversionException
    {
        final Unit<?> unit;
        try {
            unit = csFactory.createUnit(String.valueOf(code));
        } catch (NoSuchAuthorityCodeException e) {
            // TODO: report this unsupported unit.
            return;
        }
        final UnitConverter converter = unit.getConverterToAny(base);
        for (double sample=-90; sample<=90; sample += 2.8125) {
            final double expected;
            if (Double.isNaN(factor)) {
                expected = 0; // TODO
                return;
            } else {
                expected = sample * factor;
            }
            assertEquals(name, expected, converter.convert(sample), (expected != 0) ? abs(expected) * 1E-10 : 1E-10);
        }
    }
}
