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
package org.opengis.test.referencing.gigs;

import java.util.List;
import javax.measure.Unit;
import javax.measure.quantity.Length;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.test.Configuration;
import org.opengis.test.FactoryFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.junit.Assert.*;


/**
 * Verifies reference ellipsoid parameters bundled with the geoscience software.
 *
 * <table class="gigs" summary="Test description"><tr>
 *   <th>Test method:</th>
 *   <td>Compare ellipsoid definitions included in the software against the EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_2002_libEllipsoid.csv">{@code GIGS_2002_libEllipsoid.csv}</a>
 *   and EPSG Dataset.
 *   Contains EPSG {@linkplain #code code} and {@linkplain #name name} for the ellipsoid,
 *   commonly encountered {@linkplain #aliases alternative name(s)} for the same object,
 *   the value and units for the {@link #semiMajorAxis semi-major axis},
 *   the conversion ratio to metres for these units, and then a second parameter which will be either
 *   the value of the {@linkplain #inverseFlattening inverse flattening} (unitless) or
 *   the value of the {@link #semiMinorAxis semi-minor axis} (in the same units as the semi-major axis).
 *   This class additionally contain a flag to indicate that the figure {@linkplain #isSphere is a sphere}:
 *   if {@code false} the figure is an oblate ellipsoid.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link DatumAuthorityFactory#createEllipsoid(String)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>Ellipsoid definitions bundled with software, if any, should have same name and defining parameters
 *   as in the EPSG Dataset. Equivalent alternative parameters are acceptable but should be reported.
 *   The values of the parameters should be correct to at least 10 significant figures.
 *   For ellipsoids defined by Clarke and Everest, as well as those adopted by IUGG as International,
 *   several variants exist. These must be clearly distinguished.
 *   Ellipsoids missing from the software or at variance with those in the EPSG Dataset should be reported.</td>
 * </tr></table>
 *
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementors can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.gigs.GIGS2002;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS2002 {
 *    public MyTest() {
 *        super(new MyDatumAuthorityFactory());
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
public strictfp class GIGS2002 extends AuthorityFactoryTestCase<Ellipsoid> {
    /**
     * The conversion factor from the unit of {@link #semiMajorAxis} to metres.
     */
    private double toMetres;

    /**
     * The {@link #semiMajorAxis} value converted to a length in metres.
     */
    private double semiMajorInMetres;

    /**
     * The expected semi-major axis length, in the units specified by the EPSG dataset.
     * This value can also be obtained as a length in metres by a call to the {@link #getSemiMajorAxis(boolean)} method.
     *
     * @see #getSemiMajorAxis(boolean)
     */
    public double semiMajorAxis;

    /**
     * The expected semi-minor axis length, or {@link Double#NaN} if the second defining parameters is not this field.
     * If not {@code NaN}, the value is in the same units than {@link #semiMajorAxis}.
     * This value can be obtained as a length in metres by a call to the {@link #getSemiMinorAxis(boolean)} method.
     *
     * @see #getSemiMinorAxis(boolean)
     */
    public double semiMinorAxis;

    /**
     * The expected inverse flattening, or {@link Double#NaN} if the second defining parameters is not this field.
     */
    public double inverseFlattening;

    /**
     * Indicates if the figure of the Earth is a sphere.
     * If {@code false} the figure is an oblate ellipsoid.
     */
    public boolean isSphere;

    /**
     * The ellipsoid created by the factory,
     * or {@code null} if not yet created or if the ellipsoid creation failed.
     *
     * @see #datumAuthorityFactory
     */
    private Ellipsoid ellipsoid;

    /**
     * Factory to use for building {@link Ellipsoid} instances, or {@code null} if none.
     * This is the factory used by the {@link #getIdentifiedObject()} method.
     */
    protected final DatumAuthorityFactory datumAuthorityFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS2002} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(FactoryFilter.ByAuthority.EPSG, DatumAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param datumFactory  factory for creating {@link Ellipsoid} instances.
     */
    public GIGS2002(final DatumAuthorityFactory datumFactory) {
        super(datumFactory);
        datumAuthorityFactory = datumFactory;
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
     *       <li>{@link #datumAuthorityFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.datumAuthorityFactory, datumAuthorityFactory));
        return op;
    }

    /**
     * Returns the ellipsoid instance to be tested. When this method is invoked for the first time, it creates the
     * ellipsoid to test by invoking the {@link DatumAuthorityFactory#createEllipsoid(String)} method with the current
     * {@link #code} value in argument. The created object is then cached and returned in all subsequent invocations of
     * this method.
     *
     * @return the ellipsoid instance to test.
     * @throws FactoryException if an error occurred while creating the ellipsoid instance.
     */
    @Override
    public Ellipsoid getIdentifiedObject() throws FactoryException {
        if (ellipsoid == null) {
            assumeNotNull(datumAuthorityFactory);
            try {
                ellipsoid = datumAuthorityFactory.createEllipsoid(String.valueOf(code));
            } catch (NoSuchAuthorityCodeException e) {
                unsupportedCode(Ellipsoid.class, code);
                throw e;
            }
        }
        return ellipsoid;
    }

    /**
     * Returns the length of the semi-major axis, either in the units specified by the EPSG dataset or in metres.
     *
     * @param  inMetres {@code true} for the length in metres.
     * @return the semi-major axis length, guaranteed to be in metres if {@code inMetres} is {@code true}.
     *
     * @see #semiMajorAxis
     */
    public double getSemiMajorAxis(final boolean inMetres) {
        assertEquals("Inconsistent semi-major axis length in metres.", semiMajorInMetres, semiMajorAxis*toMetres, 0.01);
        return inMetres ? semiMajorInMetres : semiMajorAxis;
    }

    /**
     * Returns the length of the semi-minor axis, either in the units specified by the EPSG dataset or in metres.
     * This method can be invoked only if the semi-minor axis length is the second defining parameter.
     *
     * @param  inMetres {@code true} for the length in metres.
     * @return the semi-minor axis length, guaranteed to be in metres if {@code inMetres} is {@code true}.
     *
     * @see #semiMinorAxis
     */
    public double getSemiMinorAxis(final boolean inMetres) {
        double value = semiMinorAxis;
        assertFalse("Semi-minor axis length is not the second defining parameter.", Double.isNaN(value));
        if (inMetres) {
            semiMinorAxis *= toMetres;
        }
        return value;
    }

    /**
     * Verifies the properties of the ellipsoid given by {@link #getIdentifiedObject()}.
     */
    private void verifyEllipsoid() throws FactoryException {
        final Ellipsoid ellipsoid = getIdentifiedObject();
        assertNotNull("Ellipsoid", ellipsoid);
        validators.validate(ellipsoid);

        // Ellipsoid identifier.
        assertContainsCode("Ellipsoid.getIdentifiers()", "EPSG", code, ellipsoid.getIdentifiers());

        // Ellipsoid name.
        if (isStandardNameSupported) {
            configurationTip = Configuration.Key.isStandardNameSupported;
            assertEquals("Ellipsoid.getName()", name, getVerifiableName(ellipsoid));
            configurationTip = null;
        }

        // Ellipsoid alias.
        if (isStandardAliasSupported) {
            configurationTip = Configuration.Key.isStandardAliasSupported;
            assertContainsAll("Ellipsoid.getAlias()", aliases, ellipsoid.getAlias());
            configurationTip = null;
        }
        /*
         * Get the axis lengths and their unit. Null units are assumed to mean metres
         * (whether we accept null unit or not is determined by the validators).
         * If the implementation uses metre units but the EPSG definition expected
         * another unit, convert the axis lengths from the later units to metre units.
         */
        final Unit<Length> unit = ellipsoid.getAxisUnit();
        final boolean inMetres = toMetres != 1 && (unit == null || unit.equals(units.metre()));
        double expectedAxisLength = getSemiMajorAxis(inMetres);
        assertEquals("Ellipsoid.getSemiMajorAxis()",
                expectedAxisLength, ellipsoid.getSemiMajorAxis(), TOLERANCE*expectedAxisLength);

        if (!Double.isNaN(semiMinorAxis)) {
            expectedAxisLength = getSemiMinorAxis(inMetres);
            assertEquals("Ellipsoid.getSemiMinorAxis()", expectedAxisLength,
                    ellipsoid.getSemiMinorAxis(), TOLERANCE*expectedAxisLength);
        }
        if (!Double.isNaN(inverseFlattening)) {
            assertEquals("Ellipsoid.getInverseFlattening()", inverseFlattening,
                    ellipsoid.getInverseFlattening(), TOLERANCE*inverseFlattening);
        }
        assertEquals("Ellipsoid.isSphere()", isSphere, ellipsoid.isSphere());
    }

    /**
     * Tests “Airy 1830” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7001</b></li>
     *   <li>EPSG ellipsoid name: <b>Airy 1830</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377563.396</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>299.3249646</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testAiry()
     */
    @Test
    public void testAiry() throws FactoryException {
        important         = true;
        code              = 7001;
        name              = "Airy 1830";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377563.396;
        semiMajorAxis     = 6377563.396;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 299.3249646;
        verifyEllipsoid();
    }

    /**
     * Tests “Airy Modified 1849” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7002</b></li>
     *   <li>EPSG ellipsoid name: <b>Airy Modified 1849</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377340.189</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>299.3249646</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testAiryModified() throws FactoryException {
        important         = true;
        code              = 7002;
        name              = "Airy Modified 1849";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377340.189;
        semiMajorAxis     = 6377340.189;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 299.3249646;
        verifyEllipsoid();
    }

    /**
     * Tests “Australian National Spheroid” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7003</b></li>
     *   <li>EPSG ellipsoid name: <b>Australian National Spheroid</b></li>
     *   <li>Alias(es) given by EPSG: <b>ANS</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378160</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.25</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testAustralianNationalSpheroid()
     */
    @Test
    public void testAustralianNationalSpheroid() throws FactoryException {
        important         = true;
        code              = 7003;
        name              = "Australian National Spheroid";
        aliases           = new String[] {"ANS"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378160.0;
        semiMajorAxis     = 6378160.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.25;
        verifyEllipsoid();
    }

    /**
     * Tests “Bessel 1841” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7004</b></li>
     *   <li>EPSG ellipsoid name: <b>Bessel 1841</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377397.155</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>299.1528128</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testBessel()
     */
    @Test
    public void testBessel() throws FactoryException {
        important         = true;
        code              = 7004;
        name              = "Bessel 1841";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377397.155;
        semiMajorAxis     = 6377397.155;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 299.1528128;
        verifyEllipsoid();
    }

    /**
     * Tests “Bessel Namibia (GLM)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7046</b></li>
     *   <li>EPSG ellipsoid name: <b>Bessel Namibia (GLM)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377397.155</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>299.1528128</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testBesselNamibia() throws FactoryException {
        important         = true;
        code              = 7046;
        name              = "Bessel Namibia (GLM)";
        aliases           = NONE;
        toMetres          = 1.000013597;
        semiMajorInMetres = 6377483.865;
        semiMajorAxis     = 6377397.155;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 299.1528128;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1858” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7007</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1858</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>20926348</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>20855233</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testClarke1858() throws FactoryException {
        important         = true;
        code              = 7007;
        name              = "Clarke 1858";
        aliases           = NONE;
        toMetres          = 0.304797265;
        semiMajorInMetres = 6378293.645;
        semiMajorAxis     = 20926348.0;
        semiMinorAxis     = 20855233.0;
        inverseFlattening = Double.NaN;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1866” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7008</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1866</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378206.4</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356583.8</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testClarke1866()
     */
    @Test
    public void testClarke1866() throws FactoryException {
        important         = true;
        code              = 7008;
        name              = "Clarke 1866";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378206.4;
        semiMajorAxis     = 6378206.4;
        semiMinorAxis     = 6356583.8;
        inverseFlattening = Double.NaN;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1866 Michigan” ellipsoid creation from the factory <em>(deprecated)</em>.
     * This is test is executed only if {@link #isDeprecatedObjectCreationSupported} is {@code true}.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7009</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1866 Michigan</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>20926631.531</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>20855688.674</b></li>
     *   <li><b>Deprecated:</b> Ellipsoid scaling moved from datum to map projection to accord with NGS practice.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testClarkeMichigan() throws FactoryException {
        important         = true;
        code              = 7009;
        name              = "Clarke 1866 Michigan";
        aliases           = NONE;
        toMetres          = 0.30480061;
        semiMajorInMetres = 6378450.048;
        semiMajorAxis     = 20926631.531;
        semiMinorAxis     = 20855688.674;
        inverseFlattening = Double.NaN;
        assumeTrue("Creation of deprecated objects not supported.", isDeprecatedObjectCreationSupported);
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1880 (IGN)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7011</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1880 (IGN)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378249.2</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356515</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testClarkeIGN()
     */
    @Test
    public void testClarkeIGN() throws FactoryException {
        important         = true;
        code              = 7011;
        name              = "Clarke 1880 (IGN)";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378249.2;
        semiMajorAxis     = 6378249.2;
        semiMinorAxis     = 6356515.0;
        inverseFlattening = Double.NaN;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1880 (RGS)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7012</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1880 (RGS)</b></li>
     *   <li>Alias(es) given by EPSG: <b>Clarke Modified 1880</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378249.145</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>293.465</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testClarkeRGS() throws FactoryException {
        important         = true;
        code              = 7012;
        name              = "Clarke 1880 (RGS)";
        aliases           = new String[] {"Clarke Modified 1880"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378249.145;
        semiMajorAxis     = 6378249.145;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 293.465;
        verifyEllipsoid();
    }

    /**
     * Tests “Everest 1830 (1937 Adjustment)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7015</b></li>
     *   <li>EPSG ellipsoid name: <b>Everest 1830 (1937 Adjustment)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377276.345</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>300.8017</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testEverest1937() throws FactoryException {
        important         = true;
        code              = 7015;
        name              = "Everest 1830 (1937 Adjustment)";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377276.345;
        semiMajorAxis     = 6377276.345;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 300.8017;
        verifyEllipsoid();
    }

    /**
     * Tests “Everest 1830 (1962 Definition)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7044</b></li>
     *   <li>EPSG ellipsoid name: <b>Everest 1830 (1962 Definition)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377301.243</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>300.8017255</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testEverest1962() throws FactoryException {
        important         = true;
        code              = 7044;
        name              = "Everest 1830 (1962 Definition)";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377301.243;
        semiMajorAxis     = 6377301.243;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 300.8017255;
        verifyEllipsoid();
    }

    /**
     * Tests “Everest 1830 (1967 Definition)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7016</b></li>
     *   <li>EPSG ellipsoid name: <b>Everest 1830 (1967 Definition)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377298.556</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>300.8017</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testEverest1967() throws FactoryException {
        important         = true;
        code              = 7016;
        name              = "Everest 1830 (1967 Definition)";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377298.556;
        semiMajorAxis     = 6377298.556;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 300.8017;
        verifyEllipsoid();
    }

    /**
     * Tests “Everest 1830 (1975 Definition)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7045</b></li>
     *   <li>EPSG ellipsoid name: <b>Everest 1830 (1975 Definition)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377299.151</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>300.8017255</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testEverest1975() throws FactoryException {
        important         = true;
        code              = 7045;
        name              = "Everest 1830 (1975 Definition)";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377299.151;
        semiMajorAxis     = 6377299.151;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 300.8017255;
        verifyEllipsoid();
    }

    /**
     * Tests “Everest 1830 Modified” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7018</b></li>
     *   <li>EPSG ellipsoid name: <b>Everest 1830 Modified</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377304.063</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>300.8017</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testEverestModified() throws FactoryException {
        important         = true;
        code              = 7018;
        name              = "Everest 1830 Modified";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377304.063;
        semiMajorAxis     = 6377304.063;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 300.8017;
        verifyEllipsoid();
    }

    /**
     * Tests “GRS 1967” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7036</b></li>
     *   <li>EPSG ellipsoid name: <b>GRS 1967</b></li>
     *   <li>Alias(es) given by EPSG: <b>International 1967</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378160</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.2471674</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testGRS1967()
     */
    @Test
    public void testGRS1967() throws FactoryException {
        important         = true;
        code              = 7036;
        name              = "GRS 1967";
        aliases           = new String[] {"International 1967"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378160.0;
        semiMajorAxis     = 6378160.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.2471674;
        verifyEllipsoid();
    }

    /**
     * Tests “GRS 1967 Modified” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7050</b></li>
     *   <li>EPSG ellipsoid name: <b>GRS 1967 Modified</b></li>
     *   <li>Alias(es) given by EPSG: <b>GRS 1967</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378160</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.25</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testGRS1967Modified() throws FactoryException {
        important         = true;
        code              = 7050;
        name              = "GRS 1967 Modified";
        aliases           = new String[] {"GRS 1967"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378160.0;
        semiMajorAxis     = 6378160.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.25;
        verifyEllipsoid();
    }

    /**
     * Tests “GRS 1980” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7019</b></li>
     *   <li>EPSG ellipsoid name: <b>GRS 1980</b></li>
     *   <li>Alias(es) given by EPSG: <b>International 1979</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378137</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.2572221</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testGRS1980()
     */
    @Test
    public void testGRS1980() throws FactoryException {
        important         = true;
        code              = 7019;
        name              = "GRS 1980";
        aliases           = new String[] {"International 1979"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378137.0;
        semiMajorAxis     = 6378137.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.2572221;
        verifyEllipsoid();
    }

    /**
     * Tests “Helmert 1906” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7020</b></li>
     *   <li>EPSG ellipsoid name: <b>Helmert 1906</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378200</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.3</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testHelmert() throws FactoryException {
        important         = true;
        code              = 7020;
        name              = "Helmert 1906";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378200.0;
        semiMajorAxis     = 6378200.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.3;
        verifyEllipsoid();
    }

    /**
     * Tests “Indonesian National Spheroid” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7021</b></li>
     *   <li>EPSG ellipsoid name: <b>Indonesian National Spheroid</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378160</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.247</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testIndonesianNational() throws FactoryException {
        important         = true;
        code              = 7021;
        name              = "Indonesian National Spheroid";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378160.0;
        semiMajorAxis     = 6378160.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.247;
        verifyEllipsoid();
    }

    /**
     * Tests “International 1924” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7022</b></li>
     *   <li>EPSG ellipsoid name: <b>International 1924</b></li>
     *   <li>Alias(es) given by EPSG: <b>Hayford 1909</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378388</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>297</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testInternational1924()
     */
    @Test
    public void testInternational1924() throws FactoryException {
        important         = true;
        code              = 7022;
        name              = "International 1924";
        aliases           = new String[] {"Hayford 1909"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378388.0;
        semiMajorAxis     = 6378388.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 297.0;
        verifyEllipsoid();
    }

    /**
     * Tests “Krassowsky 1940” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7024</b></li>
     *   <li>EPSG ellipsoid name: <b>Krassowsky 1940</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378245</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.3</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testKrassowsky()
     */
    @Test
    public void testKrassowsky() throws FactoryException {
        important         = true;
        code              = 7024;
        name              = "Krassowsky 1940";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378245.0;
        semiMajorAxis     = 6378245.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.3;
        verifyEllipsoid();
    }

    /**
     * Tests “War Office” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7029</b></li>
     *   <li>EPSG ellipsoid name: <b>War Office</b></li>
     *   <li>Alias(es) given by EPSG: <b>McCaw 1924</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378300</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>296</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testWarOffice() throws FactoryException {
        important         = true;
        code              = 7029;
        name              = "War Office";
        aliases           = new String[] {"McCaw 1924"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378300.0;
        semiMajorAxis     = 6378300.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 296.0;
        verifyEllipsoid();
    }

    /**
     * Tests “WGS 72” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7043</b></li>
     *   <li>EPSG ellipsoid name: <b>WGS 72</b></li>
     *   <li>Alias(es) given by EPSG: <b>NWL 10D</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378135</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.26</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testWGS72() throws FactoryException {
        important         = true;
        code              = 7043;
        name              = "WGS 72";
        aliases           = new String[] {"NWL 10D"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378135.0;
        semiMajorAxis     = 6378135.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.26;
        verifyEllipsoid();
    }

    /**
     * Tests “WGS 84” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7030</b></li>
     *   <li>EPSG ellipsoid name: <b>WGS 84</b></li>
     *   <li>Alias(es) given by EPSG: <b>WGS84</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378137</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.257223563</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testWGS84()
     */
    @Test
    public void testWGS84() throws FactoryException {
        important         = true;
        code              = 7030;
        name              = "WGS 84";
        aliases           = new String[] {"WGS84"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378137.0;
        semiMajorAxis     = 6378137.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.257223563;
        verifyEllipsoid();
    }

    /**
     * Tests “IAG 1975” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7049</b></li>
     *   <li>EPSG ellipsoid name: <b>IAG 1975</b></li>
     *   <li>Alias(es) given by EPSG: <b>Xian 1980</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378140</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.257</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testIAG1975() throws FactoryException {
        important         = true;
        code              = 7049;
        name              = "IAG 1975";
        aliases           = new String[] {"Xian 1980"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378140.0;
        semiMajorAxis     = 6378140.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.257;
        verifyEllipsoid();
    }

    /**
     * Tests “Average Terrestrial System 1977” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7041</b></li>
     *   <li>EPSG ellipsoid name: <b>Average Terrestrial System 1977</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378135</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.257</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testAverageTerrestrialSystem() throws FactoryException {
        code              = 7041;
        name              = "Average Terrestrial System 1977";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378135.0;
        semiMajorAxis     = 6378135.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.257;
        verifyEllipsoid();
    }

    /**
     * Tests “Bessel Modified” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7005</b></li>
     *   <li>EPSG ellipsoid name: <b>Bessel Modified</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377492.018</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>299.1528128</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testBesselModified() throws FactoryException {
        code              = 7005;
        name              = "Bessel Modified";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377492.018;
        semiMajorAxis     = 6377492.018;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 299.1528128;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1866 Authalic Sphere” spheroid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7052</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1866 Authalic Sphere</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6370997</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6370997</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     *
     * @see GIGS3002#testClarkeAuthalicSphere()
     */
    @Test
    public void testClarkeAuthalicSphere() throws FactoryException {
        code              = 7052;
        name              = "Clarke 1866 Authalic Sphere";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6370997.0;
        semiMajorAxis     = 6370997.0;
        semiMinorAxis     = 6370997.0;
        inverseFlattening = Double.NaN;
        isSphere          = true;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1880” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7034</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1880</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>20926202</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>20854895</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testClarke1880() throws FactoryException {
        code              = 7034;
        name              = "Clarke 1880";
        aliases           = NONE;
        toMetres          = 0.304797265;
        semiMajorInMetres = 6378249.145;
        semiMajorAxis     = 20926202.0;
        semiMinorAxis     = 20854895.0;
        inverseFlattening = Double.NaN;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1880 (Arc)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7013</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1880 (Arc)</b></li>
     *   <li>Alias(es) given by EPSG: <b>Modified Clarke 1880 (South Africa)</b>, <b>Clarke 1880 (Cape)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378249.145</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>293.4663077</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testClarkeArc() throws FactoryException {
        code              = 7013;
        name              = "Clarke 1880 (Arc)";
        aliases           = new String[] {"Modified Clarke 1880 (South Africa)", "Clarke 1880 (Cape)"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378249.145;
        semiMajorAxis     = 6378249.145;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 293.4663077;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1880 (Benoit)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7010</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1880 (Benoit)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378300.789</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356566.435</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testClarkeBenoit() throws FactoryException {
        code              = 7010;
        name              = "Clarke 1880 (Benoit)";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378300.789;
        semiMajorAxis     = 6378300.789;
        semiMinorAxis     = 6356566.435;
        inverseFlattening = Double.NaN;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1880 (international foot)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7055</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1880 (international foot)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>20926202</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>20854895</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testClarkeInternationalFoot() throws FactoryException {
        code              = 7055;
        name              = "Clarke 1880 (international foot)";
        aliases           = NONE;
        toMetres          = 0.3048;
        semiMajorInMetres = 6378306.37;
        semiMajorAxis     = 20926202.0;
        semiMinorAxis     = 20854895.0;
        inverseFlattening = Double.NaN;
        verifyEllipsoid();
    }

    /**
     * Tests “Clarke 1880 (SGA 1922)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7014</b></li>
     *   <li>EPSG ellipsoid name: <b>Clarke 1880 (SGA 1922)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378249.2</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>293.46598</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testClarkeSGA() throws FactoryException {
        code              = 7014;
        name              = "Clarke 1880 (SGA 1922)";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378249.2;
        semiMajorAxis     = 6378249.2;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 293.46598;
        verifyEllipsoid();
    }

    /**
     * Tests “Danish 1876” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7051</b></li>
     *   <li>EPSG ellipsoid name: <b>Danish 1876</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377019.27</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>300</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testDanish() throws FactoryException {
        code              = 7051;
        name              = "Danish 1876";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377019.27;
        semiMajorAxis     = 6377019.27;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 300.0;
        verifyEllipsoid();
    }

    /**
     * Tests “Everest (1830 Definition)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7042</b></li>
     *   <li>EPSG ellipsoid name: <b>Everest (1830 Definition)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>20922931.80</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>20853374.58</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testEverest1830() throws FactoryException {
        code              = 7042;
        name              = "Everest (1830 Definition)";
        aliases           = NONE;
        toMetres          = 0.30479951;
        semiMajorInMetres = 6377299.366;
        semiMajorAxis     = 20922931.80;
        semiMinorAxis     = 20853374.58;
        inverseFlattening = Double.NaN;
        verifyEllipsoid();
    }

    /**
     * Tests “Everest 1830 (RSO 1969)” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7056</b></li>
     *   <li>EPSG ellipsoid name: <b>Everest 1830 (RSO 1969)</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6377295.664</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>300.8017</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testEverestRSO() throws FactoryException {
        code              = 7056;
        name              = "Everest 1830 (RSO 1969)";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6377295.664;
        semiMajorAxis     = 6377295.664;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 300.8017;
        verifyEllipsoid();
    }

    /**
     * Tests “GEM 10C” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7031</b></li>
     *   <li>EPSG ellipsoid name: <b>GEM 10C</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378137</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.257223563</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testGEM10C() throws FactoryException {
        code              = 7031;
        name              = "GEM 10C";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378137.0;
        semiMajorAxis     = 6378137.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.257223563;
        verifyEllipsoid();
    }

    /**
     * Tests “GRS 1980 Authalic Sphere” spheroid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7048</b></li>
     *   <li>EPSG ellipsoid name: <b>GRS 1980 Authalic Sphere</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6371007</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6371007</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testGRS1980AuthalicSphere() throws FactoryException {
        code              = 7048;
        name              = "GRS 1980 Authalic Sphere";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6371007.0;
        semiMajorAxis     = 6371007.0;
        semiMinorAxis     = 6371007.0;
        inverseFlattening = Double.NaN;
        isSphere          = true;
        verifyEllipsoid();
    }

    /**
     * Tests “Hough 1960” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7053</b></li>
     *   <li>EPSG ellipsoid name: <b>Hough 1960</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378270</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>297</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testHough1960() throws FactoryException {
        code              = 7053;
        name              = "Hough 1960";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378270.0;
        semiMajorAxis     = 6378270.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 297.0;
        verifyEllipsoid();
    }

    /**
     * Tests “Hughes 1980” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7058</b></li>
     *   <li>EPSG ellipsoid name: <b>Hughes 1980</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378273</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6356889.449</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testHughes1980() throws FactoryException {
        code              = 7058;
        name              = "Hughes 1980";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378273.0;
        semiMajorAxis     = 6378273.0;
        semiMinorAxis     = 6356889.449;
        inverseFlattening = Double.NaN;
        verifyEllipsoid();
    }

    /**
     * Tests “International 1924 Authalic Sphere” spheroid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7057</b></li>
     *   <li>EPSG ellipsoid name: <b>International 1924 Authalic Sphere</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6371228</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6371228</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testInternational1924AuthalicSphere() throws FactoryException {
        code              = 7057;
        name              = "International 1924 Authalic Sphere";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6371228.0;
        semiMajorAxis     = 6371228.0;
        semiMinorAxis     = 6371228.0;
        inverseFlattening = Double.NaN;
        isSphere          = true;
        verifyEllipsoid();
    }

    /**
     * Tests “NWL 9D” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7025</b></li>
     *   <li>EPSG ellipsoid name: <b>NWL 9D</b></li>
     *   <li>Alias(es) given by EPSG: <b>WGS 66</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378145</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.25</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testNWL9D() throws FactoryException {
        code              = 7025;
        name              = "NWL 9D";
        aliases           = new String[] {"WGS 66"};
        toMetres          = 1.0;
        semiMajorInMetres = 6378145.0;
        semiMajorAxis     = 6378145.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.25;
        verifyEllipsoid();
    }

    /**
     * Tests “OSU86F” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7032</b></li>
     *   <li>EPSG ellipsoid name: <b>OSU86F</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378136.2</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.257223563</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testOSU86F() throws FactoryException {
        code              = 7032;
        name              = "OSU86F";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378136.2;
        semiMajorAxis     = 6378136.2;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.257223563;
        verifyEllipsoid();
    }

    /**
     * Tests “OSU91A” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7033</b></li>
     *   <li>EPSG ellipsoid name: <b>OSU91A</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378136.3</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.257223563</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testOSU91A() throws FactoryException {
        code              = 7033;
        name              = "OSU91A";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378136.3;
        semiMajorAxis     = 6378136.3;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.257223563;
        verifyEllipsoid();
    }

    /**
     * Tests “Plessis 1817” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7027</b></li>
     *   <li>EPSG ellipsoid name: <b>Plessis 1817</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6376523</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>308.64</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testPlessis() throws FactoryException {
        code              = 7027;
        name              = "Plessis 1817";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6376523.0;
        semiMajorAxis     = 6376523.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 308.64;
        verifyEllipsoid();
    }

    /**
     * Tests “Popular Visualisation Sphere” spheroid creation from the factory <em>(deprecated)</em>.
     * This is test is executed only if {@link #isDeprecatedObjectCreationSupported} is {@code true}.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7059</b></li>
     *   <li>EPSG ellipsoid name: <b>Popular Visualisation Sphere</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378137</b></li>
     *   <li>Semi-minor axis (<var>b</var>): <b>6378137</b></li>
     *   <li><b>Deprecated:</b> IOGP revised its approach to description of Popular Visualisation CRS.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testPopularVisualisationSphere() throws FactoryException {
        code              = 7059;
        name              = "Popular Visualisation Sphere";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378137.0;
        semiMajorAxis     = 6378137.0;
        semiMinorAxis     = 6378137.0;
        inverseFlattening = Double.NaN;
        isSphere          = true;
        assumeTrue("Creation of deprecated objects not supported.", isDeprecatedObjectCreationSupported);
        verifyEllipsoid();
    }

    /**
     * Tests “PZ-90” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7054</b></li>
     *   <li>EPSG ellipsoid name: <b>PZ-90</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378136</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>298.2578393</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testPZ90() throws FactoryException {
        code              = 7054;
        name              = "PZ-90";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378136.0;
        semiMajorAxis     = 6378136.0;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 298.2578393;
        verifyEllipsoid();
    }

    /**
     * Tests “Struve 1860” ellipsoid creation from the factory.
     *
     * <ul>
     *   <li>EPSG ellipsoid code: <b>7028</b></li>
     *   <li>EPSG ellipsoid name: <b>Struve 1860</b></li>
     *   <li>Semi-major axis (<var>a</var>): <b>6378298.3</b></li>
     *   <li>Inverse flattening (1/<var>f</var>): <b>294.73</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the ellipsoid from the EPSG code.
     */
    @Test
    public void testStruve() throws FactoryException {
        code              = 7028;
        name              = "Struve 1860";
        aliases           = NONE;
        toMetres          = 1.0;
        semiMajorInMetres = 6378298.3;
        semiMajorAxis     = 6378298.3;
        semiMinorAxis     = Double.NaN;
        inverseFlattening = 294.73;
        verifyEllipsoid();
    }
}
