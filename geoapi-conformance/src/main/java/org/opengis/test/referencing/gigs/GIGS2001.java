/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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
import java.util.Random;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.IncommensurableException;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.test.Configuration;
import org.opengis.test.FactoryFilter;
import org.opengis.test.Units;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.junit.Assert.*;


/**
 * Verifies reference units of measure bundled with the geoscience software.
 *
 * <table class="gigs">
 * <caption>Test description</caption>
 * <tr>
 *   <th>Test method:</th>
 *   <td>Compare unit definitions included in the software against the EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_2001_libUnit.csv">{@code GIGS_2001_libUnit.csv}</a>
 *   and EPSG Dataset.
 *   Contains EPSG {@linkplain #code code} and {@linkplain #name name} for the unit of measure, together with the
 *   {@linkplain #unitToBase ratio} of the unit to the ISO {@linkplain #baseUnit base unit} for that unit type.
 *   The test methods are separated in three blocks for linear units, angular units and scaling units.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link CSAuthorityFactory#createUnit(String)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>Unit of measure definitions bundled with software should have the ratio to the appropriate base unit
 *   as in the EPSG Dataset. The values of the base unit per unit should be correct to at least 10 significant figures.
 *   Units missing from the software or at variance with those in the EPSG Dataset should be reported.</td>
 * </tr></table>
 *
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.gigs.GIGS2001;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS2001 {
 *    public MyTest() {
 *        super(new MyCSAuthorityFactory());
 *    }
 *}</pre></blockquote>
 * </div>
 *
 * @author  GIGS (IOGP)
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Alexis Manin (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Parameterized.class)
public strictfp class GIGS2001 extends AuthorityFactoryTestCase<Unit<?>> {
    /**
     * Amount of {@linkplain #baseUnit base units} in one {@linkplain #getIdentifiedObject() tested unit}.
     * If this amount is not a constant (as in sexagesimal unit), then this factor is set to {@link Double#NaN}.
     */
    public double unitToBase;

    /**
     * The base unit of the unit to create. This field will have one of the following values:
     *
     * <ul>
     *   <li>{@link Units#metre()} if the unit to create is linear,</li>
     *   <li>{@link Units#radian()} if the unit to create is angular (except sexagesimal unit),</li>
     *   <li>{@link Units#one()} if the unit to create is a scale or a coefficient, or</li>
     *   <li>(exceptionally) {@link Units#degree()} in the special case of {@link #testSexagesimalDegree()}.
     *       Note that sexagesimal units also have the {@linkplain #unitToBase ratio of the unit to base unit}
     *       set to {@link Double#NaN}.</li>
     * </ul>
     */
    public Unit<?> baseUnit;

    /**
     * The unit of measurement created by the factory,
     * or {@code null} if not yet created or if the unit creation failed.
     *
     * @see #csAuthorityFactory
     */
    private Unit<?> unit;

    /**
     * Factory to use for building {@link Unit} instances, or {@code null} if none.
     * This is the factory used by the {@link #getIdentifiedObject()} method.
     */
    protected final CSAuthorityFactory csAuthorityFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead of
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS2001} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(FactoryFilter.ByAuthority.EPSG, CSAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param csFactory  factory for creating {@link Unit} instances.
     */
    public GIGS2001(final CSAuthorityFactory csFactory) {
        super(csFactory);
        csAuthorityFactory = csFactory;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isStandardNameSupported}</li>
     *       <li>{@link #isStandardAliasSupported}</li>
     *       <li>{@link #isDependencyIdentificationSupported}</li>
     *       <li>{@link #csAuthorityFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.csAuthorityFactory, csAuthorityFactory));
        return op;
    }

    /**
     * Returns the unit instance to be tested. When this method is invoked for the first time, it creates the unit
     * to test by invoking the {@link CSAuthorityFactory#createUnit(String)} method with the current {@link #code}
     * value in argument. The created object is then cached and returned in all subsequent invocations of this method.
     *
     * @return the unit instance to test.
     * @throws FactoryException if an error occurred while creating the unit instance.
     */
    @Override
    public Unit<?> getIdentifiedObject() throws FactoryException {
        if (unit == null) {
            assumeNotNull(csAuthorityFactory);
            try {
                unit = csAuthorityFactory.createUnit(String.valueOf(code));
            } catch (NoSuchAuthorityCodeException e) {
                unsupportedCode(Unit.class, code);
                throw e;
            }
        }
        return unit;
    }

    /**
     * Gets the unit given by {@link #getIdentifiedObject()},
     * then creates and returns the converter from that unit to the base unit.
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    private UnitConverter createConverter() throws FactoryException {
        final Unit<?> unit = getIdentifiedObject();
        assertNotNull("Unit", unit);
        final UnitConverter converter;
        try {
            converter = unit.getConverterToAny(baseUnit);
        } catch (IncommensurableException e) {
            throw new AssertionError("Can not convert “" + name + "” from “" + unit + "” to “" + baseUnit + "”.", e);
        }
        return converter;
    }

    /**
     * Converts random values using the unit converter and compares against the expected value.
     * The expected values are obtained by multiplying the values to convert by the given factor.
     *
     * @param  converter  the converter from tested {@link #unit} to the base unit.
     */
    private void verifyLinearConversions(final UnitConverter converter) {
        final Random random = new Random();
        final double tolerance = TOLERANCE * unitToBase;
        assertEquals(name, 0, converter.convert(0), tolerance);
        assertEquals(name,  unitToBase, converter.convert( 1), tolerance);
        assertEquals(name, -unitToBase, converter.convert(-1), tolerance);
        for (double sample = -90; sample <= 90; sample += 4*random.nextDouble()) {
            final double expected = sample * unitToBase;
            assertEquals(name, expected, converter.convert(sample), tolerance);
        }
    }

    /**
     * Tests “metre” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9001</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>metre</b></li>
     *   <li>Base units per unit: <b>1</b></li>
     *   <li>Specific usage / Remarks: <b>Numerous</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testMetre() throws FactoryException {
        important  = true;
        code       = 9001;
        name       = "metre";
        aliases    = NONE;
        unitToBase = 1.0;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “kilometre” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9036</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>kilometre</b></li>
     *   <li>Base units per unit: <b>1000</b></li>
     *   <li>Specific usage / Remarks: <b>Tunisia</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testKilometre() throws FactoryException {
        important  = true;
        code       = 9036;
        name       = "kilometre";
        aliases    = NONE;
        unitToBase = 1000.0;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “foot” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9002</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>foot</b></li>
     *   <li>Base units per unit: <b>0.3048</b></li>
     *   <li>Specific usage / Remarks: <b>US</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testFoot() throws FactoryException {
        important  = true;
        code       = 9002;
        name       = "foot";
        aliases    = NONE;
        unitToBase = 0.3048;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “US survey foot” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9003</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>US survey foot</b></li>
     *   <li>Base units per unit: <b>0.30480060960121924</b></li>
     *   <li>Specific usage / Remarks: <b>US</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testUSSurveyFoot() throws FactoryException {
        important  = true;
        code       = 9003;
        name       = "US survey foot";
        aliases    = NONE;
        unitToBase = 0.30480060960121924;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “German legal metre” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9031</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>German legal metre</b></li>
     *   <li>Base units per unit: <b>1.0000135965</b></li>
     *   <li>Specific usage / Remarks: <b>Namibia</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testGermanLegalMetre() throws FactoryException {
        important  = true;
        code       = 9031;
        name       = "German legal metre";
        aliases    = NONE;
        unitToBase = 1.0000135965;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “Clarke's foot” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9005</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>Clarke's foot</b></li>
     *   <li>Base units per unit: <b>0.3047972654</b></li>
     *   <li>Specific usage / Remarks: <b>Trinidad</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testClarkeFoot() throws FactoryException {
        important  = true;
        code       = 9005;
        name       = "Clarke's foot";
        aliases    = NONE;
        unitToBase = 0.3047972654;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “Clarke's link” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9039</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>Clarke's link</b></li>
     *   <li>Base units per unit: <b>0.201166195164</b></li>
     *   <li>Specific usage / Remarks: <b>Trinidad</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testClarkeLink() throws FactoryException {
        important  = true;
        code       = 9039;
        name       = "Clarke's link";
        aliases    = NONE;
        unitToBase = 0.201166195164;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “British chain (Sears 1922)” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9042</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>British chain (Sears 1922)</b></li>
     *   <li>Base units per unit: <b>20.116765121552632</b></li>
     *   <li>Specific usage / Remarks: <b>Brunei Malaysia</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     *
     * @see #testBritishChainTruncated()
     */
    @Test
    public void testBritishChain() throws FactoryException {
        important  = true;
        code       = 9042;
        name       = "British chain (Sears 1922)";
        aliases    = NONE;
        unitToBase = 20.116765121552632;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “British foot (Sears 1922)” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9051</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>British foot (Sears 1922)</b></li>
     *   <li>Base units per unit: <b>0.3047997333333333</b></li>
     *   <li>Specific usage / Remarks: <b>Brunei Malaysia</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testBritishFoot() throws FactoryException {
        important  = true;
        code       = 9051;
        name       = "British foot (Sears 1922)";
        aliases    = NONE;
        unitToBase = 0.3047997333333333;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “British yard (Sears 1922)” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9040</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>British yard (Sears 1922)</b></li>
     *   <li>Base units per unit: <b>0.9143984146160287</b></li>
     *   <li>Specific usage / Remarks: <b>New Zealand</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testBritishYard() throws FactoryException {
        important  = true;
        code       = 9040;
        name       = "British yard (Sears 1922)";
        aliases    = NONE;
        unitToBase = 0.9143984146160287;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “British chain (Sears 1922 truncated)” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9301</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>British chain (Sears 1922 truncated)</b></li>
     *   <li>Base units per unit: <b>20.116756</b></li>
     *   <li>Specific usage / Remarks: <b>Malaysia</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     *
     * @see #testBritishChain()
     */
    @Test
    public void testBritishChainTruncated() throws FactoryException {
        important  = true;
        code       = 9301;
        name       = "British chain (Sears 1922 truncated)";
        aliases    = NONE;
        unitToBase = 20.116756;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “Indian yard” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9084</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>Indian yard</b></li>
     *   <li>Base units per unit: <b>0.9143985307444408</b></li>
     *   <li>Specific usage / Remarks: <b>south Asia - historic</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testIndianYard() throws FactoryException {
        code       = 9084;
        name       = "Indian yard";
        aliases    = NONE;
        unitToBase = 0.9143985307444408;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “Gold Coast foot” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9094</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>Gold Coast foot</b></li>
     *   <li>Base units per unit: <b>0.3047997101815088</b></li>
     *   <li>Specific usage / Remarks: <b>Ghana</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testGoldCoastFoot() throws FactoryException {
        code       = 9094;
        name       = "Gold Coast foot";
        aliases    = NONE;
        unitToBase = 0.3047997101815088;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “link” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9098</b></li>
     *   <li>Type: <b>Linear</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>link</b></li>
     *   <li>Base units per unit: <b>0.201168</b></li>
     *   <li>Specific usage / Remarks: <b>Fiji</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testLink() throws FactoryException {
        code       = 9098;
        name       = "link";
        aliases    = NONE;
        unitToBase = 0.201168;
        baseUnit   = units.metre();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “radian” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9101</b></li>
     *   <li>Type: <b>Angle</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>radian</b></li>
     *   <li>Base units per unit: <b>1.0</b></li>
     *   <li>Specific usage / Remarks: <b>Some geocentric 7- and 10-parameter transformations for Colombia and US/Canada.</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testRadian() throws FactoryException {
        important  = true;
        code       = 9101;
        name       = "radian";
        aliases    = NONE;
        unitToBase = 1.0;
        baseUnit   = units.radian();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “degree” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9102</b></li>
     *   <li>Type: <b>Angle</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>degree</b></li>
     *   <li>Base units per unit: <b>0.017453292519943278</b></li>
     *   <li>Specific usage / Remarks: <b>Numerous</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testDegree() throws FactoryException {
        important  = true;
        code       = 9102;
        name       = "degree";
        aliases    = NONE;
        unitToBase = 0.017453292519943278;
        baseUnit   = units.radian();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “arc-second” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9104</b></li>
     *   <li>Type: <b>Angle</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>arc-second</b></li>
     *   <li>Base units per unit: <b>4.848136811095355E-6</b></li>
     *   <li>Specific usage / Remarks: <b>Numerous</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testArcSecond() throws FactoryException {
        important  = true;
        code       = 9104;
        name       = "arc-second";
        aliases    = NONE;
        unitToBase = 4.848136811095355E-6;
        baseUnit   = units.radian();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “grad” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9105</b></li>
     *   <li>Type: <b>Angle</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>grad</b></li>
     *   <li>Base units per unit: <b>0.01570796326794895</b></li>
     *   <li>Specific usage / Remarks: <b>France</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testGrad() throws FactoryException {
        important  = true;
        code       = 9105;
        name       = "grad";
        aliases    = NONE;
        unitToBase = 0.01570796326794895;
        baseUnit   = units.radian();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “microradian” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9109</b></li>
     *   <li>Type: <b>Angle</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>microradian</b></li>
     *   <li>Base units per unit: <b>1E-6</b></li>
     *   <li>Specific usage / Remarks: <b>Some 7- and 10-parameter transformations for Netherlands and Norway.</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testMicroRadian() throws FactoryException {
        important  = true;
        code       = 9109;
        name       = "microradian";
        aliases    = NONE;
        unitToBase = 1E-6;
        baseUnit   = units.radian();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “sexagesimal DMS” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9110</b></li>
     *   <li>Type: <b>Angle</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>sexagesimal DMS</b></li>
     *   <li>Base units per unit: <b>not a constant</b></li>
     *   <li>Specific usage / Remarks: <b>Special EPSG construct for storing sexagesimal degree values as a single real number.</b>
     *       Applications do not necessarily have to adopt this approach but should somehow exactly honour sexagesimal value.
     *       Sexagesimal degree (123º45'67.8"[H]) should be used for display at human interface.</li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testSexagesimalDegree() throws FactoryException {
        important  = true;
        code       = 9110;
        name       = "sexagesimal DMS";
        aliases    = NONE;
        unitToBase = Double.NaN;
        baseUnit   = units.degree();
        final UnitConverter converter = createConverter();
        final double tolerance = 10*TOLERANCE;
        assertEquals(name,  10.00, converter.convert( 10.0000), tolerance);
        assertEquals(name, -10.00, converter.convert(-10.0000), tolerance);
        assertEquals(name,  20.01, converter.convert( 20.0036), tolerance);
        assertEquals(name, -20.01, converter.convert(-20.0036), tolerance);
        assertEquals(name,  30.50, converter.convert( 30.3000), tolerance);
        assertEquals(name, -30.50, converter.convert(-30.3000), tolerance);
        assertEquals(name,  40.99, converter.convert( 40.5924), tolerance);
        assertEquals(name, -40.99, converter.convert(-40.5924), tolerance);
    }

    /**
     * Tests “centesimal second” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9113</b></li>
     *   <li>Type: <b>Angle</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>centesimal second</b></li>
     *   <li>Base units per unit: <b>1.570796326794895E-6</b></li>
     *   <li>Specific usage / Remarks: <b>Used in one geocentric 7-parameter transformation for Switzerland.</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testCentesimalSecond() throws FactoryException {
        code       = 9113;
        name       = "centesimal second";
        aliases    = NONE;
        unitToBase = 1.570796326794895E-6;
        baseUnit   = units.radian();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “unity” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9201</b></li>
     *   <li>Type: <b>Scale</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>unity</b></li>
     *   <li>Base units per unit: <b>1.0</b></li>
     *   <li>Specific usage / Remarks: <b>Numerous</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testUnity() throws FactoryException {
        important  = true;
        code       = 9201;
        name       = "unity";
        aliases    = NONE;
        unitToBase = 1.0;
        baseUnit   = units.one();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “parts per million” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9202</b></li>
     *   <li>Type: <b>Scale</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>parts per million</b></li>
     *   <li>Base units per unit: <b>1E-6</b></li>
     *   <li>Specific usage / Remarks: <b>Numerous</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testPartsPerMillion() throws FactoryException {
        important  = true;
        code       = 9202;
        name       = "parts per million";
        aliases    = NONE;
        unitToBase = 1E-6;
        baseUnit   = units.one();
        verifyLinearConversions(createConverter());
    }

    /**
     * Tests “coefficient” unit creation from the factory.
     *
     * <ul>
     *   <li>EPSG UoM code: <b>9203</b></li>
     *   <li>Type: <b>Scale</b></li>
     *   <li>Name of Units used in EPSG dataset: <b>coefficient</b></li>
     *   <li>Base units per unit: <b>1.0</b></li>
     *   <li>Specific usage / Remarks: <b>Numerous</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the unit from the EPSG code.
     */
    @Test
    public void testCoefficient() throws FactoryException {
        important  = true;
        code       = 9203;
        name       = "coefficient";
        aliases    = NONE;
        unitToBase = 1.0;
        baseUnit   = units.one();
        verifyLinearConversions(createConverter());
    }
}
