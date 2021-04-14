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

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.referencing.datum.VerticalDatum;
import org.opengis.test.Configuration;
import org.opengis.test.FactoryFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.junit.Assert.*;


/**
 * Verifies reference vertical datums and CRSs bundled with the geoscience software.
 *
 * <table class="gigs">
 * <caption>Test description</caption>
 * <tr>
 *   <th>Test method:</th>
 *   <td>Compare vertical datum and CRS definitions included in the software against the EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_2008_libVerticalDatumCRS.csv">{@code GIGS_2008_libVerticalDatumCRS.csv}</a>
 *       and EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link DatumAuthorityFactory#createVerticalDatum(String)} and<br>
 *       {@link CRSAuthorityFactory#createVerticalCRS(String)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>Definitions bundled with the software should have the same name and coordinate system
 *       (including axes direction and units) as in EPSG Dataset. CRSs missing from the software
 *       or at variance with those in the EPSG Dataset should be reported.</td>
 * </tr></table>
 *
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.gigs.GIGS2008;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS2008 {
 *    public MyTest() {
 *        super(new MyDatumAuthorityFactory(),
 *              new MyCRSAuthorityFactory());
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
public strictfp class GIGS2008 extends AuthorityFactoryTestCase<VerticalCRS> {
    /**
     * The EPSG code of the expected {@link VerticalDatum}.
     */
    public int datumCode;

    /**
     * The expected EPSG name of the {@link VerticalDatum}.
     */
    public String datumName;

    /**
     * The vertical CRS created by the factory, or {@code null} if not yet created or if CRS creation failed.
     *
     * @see #crsAuthorityFactory
     */
    private VerticalCRS crs;

    /**
     * Factory to use for building {@link VerticalDatum} instances, or {@code null} if none.
     */
    protected final DatumAuthorityFactory datumAuthorityFactory;

    /**
     * Factory to use for building {@link VerticalCRS} instances, or {@code null} if none.
     * This is the factory used by the {@link #getIdentifiedObject()} method.
     */
    protected final CRSAuthorityFactory crsAuthorityFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS2008} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(FactoryFilter.ByAuthority.EPSG, DatumAuthorityFactory.class, CRSAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param datumFactory  factory for creating {@link VerticalDatum} instances.
     * @param crsFactory    factory for creating {@link VerticalCRS} instances.
     */
    public GIGS2008(final DatumAuthorityFactory datumFactory, final CRSAuthorityFactory crsFactory) {
        super(datumFactory, crsFactory);
        datumAuthorityFactory = datumFactory;
        crsAuthorityFactory = crsFactory;
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
     *       <li>{@link #crsAuthorityFactory}</li>
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
        assertNull(op.put(Configuration.Key.crsAuthorityFactory, crsAuthorityFactory));
        return op;
    }

    /**
     * Returns the vertical CRS instance to be tested. When this method is invoked for the first time, it creates the
     * vertical CRS to test by invoking the {@link CRSAuthorityFactory#createVerticalCRS(String)} method with the
     * current {@link #code} value in argument. The created object is then cached and returned in all subsequent
     * invocations of this method.
     *
     * @return the vertical CRS instance to test.
     * @throws FactoryException if an error occurred while creating the vertical CRS instance.
     */
    @Override
    public VerticalCRS getIdentifiedObject() throws FactoryException {
        if (crs == null) {
            assumeNotNull(crsAuthorityFactory);
            try {
                crs = crsAuthorityFactory.createVerticalCRS(String.valueOf(code));
            } catch (NoSuchAuthorityCodeException e) {
                unsupportedCode(VerticalCRS.class, code);
                throw e;
            }
        }
        return crs;
    }

    /**
     * Creates a vertical datum for the current {@link #datumCode}, then verifies its name and properties.
     */
    private void createAndVerifyVerticalDatum() throws FactoryException {
        assumeTrue(datumAuthorityFactory != null || crsAuthorityFactory != null);
        if (datumAuthorityFactory != null) {
            final VerticalDatum datum;
            try {
                datum = datumAuthorityFactory.createVerticalDatum(String.valueOf(datumCode));
            } catch (NoSuchAuthorityCodeException e) {
                unsupportedCode(VerticalDatum.class, datumCode);
                throw e;
            }

            // Datum validation.
            assertNotNull("VerticalDatum", datum);
            validators.validate(datum);

            // Datum identifier. Important in order to distinguish datum.
            assertContainsCode("VerticalDatum.getIdentifiers()", "EPSG", datumCode, datum.getIdentifiers());

            // Datum name.
            if (isStandardNameSupported) {
                configurationTip = Configuration.Key.isStandardNameSupported;
                assertEquals("VerticalDatum.getName()", datumName, getVerifiableName(datum));
                configurationTip = null;
            }
        }
    }

    /**
     * Verifies the properties of the vertical CRS given by {@link #getIdentifiedObject()}.
     */
    private void verifyVerticalCRS() throws FactoryException {
        if (crsAuthorityFactory != null) {
            final VerticalCRS crs = getIdentifiedObject();

            // CRS validation.
            assertNotNull("VerticalCRS", crs);
            validators.validate(crs);

            // CRS identifier.
            assertContainsCode("VerticalCRS.getIdentifiers()", "EPSG", code, crs.getIdentifiers());

            // CRS name.
            if (isStandardNameSupported) {
                configurationTip = Configuration.Key.isStandardNameSupported;
                assertEquals("VerticalCRS.getName()", name, getVerifiableName(crs));
                configurationTip = null;
            }

            // Datum associated to the CRS.
            final VerticalDatum datum = crs.getDatum();
            assertNotNull("VerticalCRS.getDatum()", datum);

            // Datum identification.
            if (isDependencyIdentificationSupported) {
                configurationTip = Configuration.Key.isDependencyIdentificationSupported;
                assertContainsCode("VerticalCRS.getDatum().getIdentifiers()", "EPSG",
                        datumCode, datum.getIdentifiers());

                configurationTip = Configuration.Key.isStandardNameSupported;
                assertEquals("VerticalCRS.getDatum().getName()", datumName, getVerifiableName(datum));
                configurationTip = null;
            }
        }
    }

    /**
     * Tests “AHD (Tasmania) height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5112</b></li>
     *   <li>EPSG vertical datum name: <b>Australian Height Datum (Tasmania)</b></li>
     *   <li>EPSG vertical CRS code: <b>5712</b></li>
     *   <li>EPSG vertical CRS name: <b>AHD (Tasmania) height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testAHD_Tasmania() throws FactoryException {
        important = true;
        datumName = "Australian Height Datum (Tasmania)";
        datumCode = 5112;
        name      = "AHD (Tasmania) height";
        code      = 5712;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “AHD height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5111</b></li>
     *   <li>EPSG vertical datum name: <b>Australian Height Datum</b></li>
     *   <li>EPSG vertical CRS code: <b>5711</b></li>
     *   <li>EPSG vertical CRS name: <b>AHD height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testAHD() throws FactoryException {
        important = true;
        datumName = "Australian Height Datum";
        datumCode = 5111;
        name      = "AHD height";
        code      = 5711;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “AIOC95 depth” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5133</b></li>
     *   <li>EPSG vertical datum name: <b>AIOC 1995</b></li>
     *   <li>EPSG vertical CRS code: <b>5734</b></li>
     *   <li>EPSG vertical CRS name: <b>AIOC95 depth</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testAIOC95Depth() throws FactoryException {
        important = true;
        datumName = "AIOC 1995";
        datumCode = 5133;
        name      = "AIOC95 depth";
        code      = 5734;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “AIOC95 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5133</b></li>
     *   <li>EPSG vertical datum name: <b>AIOC 1995</b></li>
     *   <li>EPSG vertical CRS code: <b>5797</b></li>
     *   <li>EPSG vertical CRS name: <b>AIOC95 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testAIOC95Height() throws FactoryException {
        important = true;
        datumName = "AIOC 1995";
        datumCode = 5133;
        name      = "AIOC95 height";
        code      = 5797;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Baltic 1982 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5184</b></li>
     *   <li>EPSG vertical datum name: <b>Baltic 1982</b></li>
     *   <li>EPSG vertical CRS code: <b>5786</b></li>
     *   <li>EPSG vertical CRS name: <b>Baltic 1982 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testBaltic1982() throws FactoryException {
        important = true;
        datumName = "Baltic 1982";
        datumCode = 5184;
        name      = "Baltic 1982 height";
        code      = 5786;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Baltic 1977 depth” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5105</b></li>
     *   <li>EPSG vertical datum name: <b>Baltic 1977</b></li>
     *   <li>EPSG vertical CRS code: <b>5612</b></li>
     *   <li>EPSG vertical CRS name: <b>Baltic 1977 depth</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testBalticDepth() throws FactoryException {
        important = true;
        datumName = "Baltic 1977";
        datumCode = 5105;
        name      = "Baltic 1977 depth";
        code      = 5612;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Baltic 1977 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5105</b></li>
     *   <li>EPSG vertical datum name: <b>Baltic 1977</b></li>
     *   <li>EPSG vertical CRS code: <b>5705</b></li>
     *   <li>EPSG vertical CRS name: <b>Baltic 1977 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testBalticHeight() throws FactoryException {
        important = true;
        datumName = "Baltic 1977";
        datumCode = 5105;
        name      = "Baltic 1977 height";
        code      = 5705;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Bandar Abbas height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5150</b></li>
     *   <li>EPSG vertical datum name: <b>Bandar Abbas</b></li>
     *   <li>EPSG vertical CRS code: <b>5752</b></li>
     *   <li>EPSG vertical CRS name: <b>Bandar Abbas height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testBandarAbbas() throws FactoryException {
        important = true;
        datumName = "Bandar Abbas";
        datumCode = 5150;
        name      = "Bandar Abbas height";
        code      = 5752;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Caspian depth” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5106</b></li>
     *   <li>EPSG vertical datum name: <b>Caspian Sea</b></li>
     *   <li>EPSG vertical CRS code: <b>5706</b></li>
     *   <li>EPSG vertical CRS name: <b>Caspian depth</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testCaspianSea() throws FactoryException {
        important = true;
        datumName = "Caspian Sea";
        datumCode = 5106;
        name      = "Caspian depth";
        code      = 5706;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “CGVD28 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5114</b></li>
     *   <li>EPSG vertical datum name: <b>Canadian Geodetic Vertical Datum of 1928</b></li>
     *   <li>EPSG vertical CRS code: <b>5713</b></li>
     *   <li>EPSG vertical CRS name: <b>CGVD28 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testCGVD28() throws FactoryException {
        important = true;
        datumName = "Canadian Geodetic Vertical Datum of 1928";
        datumCode = 5114;
        name      = "CGVD28 height";
        code      = 5713;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “DHHN85 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5182</b></li>
     *   <li>EPSG vertical datum name: <b>Deutsches Haupthoehennetz 1985</b></li>
     *   <li>EPSG vertical CRS code: <b>5784</b></li>
     *   <li>EPSG vertical CRS name: <b>DHHN85 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testDHHN85() throws FactoryException {
        important = true;
        datumName = "Deutsches Haupthoehennetz 1985";
        datumCode = 5182;
        name      = "DHHN85 height";
        code      = 5784;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “DHHN92 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5181</b></li>
     *   <li>EPSG vertical datum name: <b>Deutsches Haupthoehennetz 1992</b></li>
     *   <li>EPSG vertical CRS code: <b>5783</b></li>
     *   <li>EPSG vertical CRS name: <b>DHHN92 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testDHHN92() throws FactoryException {
        important = true;
        datumName = "Deutsches Haupthoehennetz 1992";
        datumCode = 5181;
        name      = "DHHN92 height";
        code      = 5783;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “EGM96 geoid height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5171</b></li>
     *   <li>EPSG vertical datum name: <b>EGM96 geoid</b></li>
     *   <li>EPSG vertical CRS code: <b>5773</b></li>
     *   <li>EPSG vertical CRS name: <b>EGM96 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testEGM96() throws FactoryException {
        important = true;
        datumName = "EGM96 geoid";
        datumCode = 5171;
        name      = "EGM96 height";
        code      = 5773;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “EVRF2000 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5129</b></li>
     *   <li>EPSG vertical datum name: <b>European Vertical Reference Frame 2000</b></li>
     *   <li>EPSG vertical CRS code: <b>5730</b></li>
     *   <li>EPSG vertical CRS name: <b>EVRF2000 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testEVRF2000() throws FactoryException {
        important = true;
        datumName = "European Vertical Reference Frame 2000";
        datumCode = 5129;
        name      = "EVRF2000 height";
        code      = 5730;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “EVRF2007 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5215</b></li>
     *   <li>EPSG vertical datum name: <b>European Vertical Reference Frame 2007</b></li>
     *   <li>EPSG vertical CRS code: <b>5621</b></li>
     *   <li>EPSG vertical CRS name: <b>EVRF2007 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testEVRF2007() throws FactoryException {
        important = true;
        datumName = "European Vertical Reference Frame 2007";
        datumCode = 5215;
        name      = "EVRF2007 height";
        code      = 5621;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Fahud HD height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5124</b></li>
     *   <li>EPSG vertical datum name: <b>Fahud Height Datum</b></li>
     *   <li>EPSG vertical CRS code: <b>5725</b></li>
     *   <li>EPSG vertical CRS name: <b>Fahud HD height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testFahudHD() throws FactoryException {
        important = true;
        datumName = "Fahud Height Datum";
        datumCode = 5124;
        name      = "Fahud HD height";
        code      = 5725;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Fao height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5149</b></li>
     *   <li>EPSG vertical datum name: <b>Fao</b></li>
     *   <li>EPSG vertical CRS code: <b>5751</b></li>
     *   <li>EPSG vertical CRS name: <b>Fao height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testFao() throws FactoryException {
        important = true;
        datumName = "Fao";
        datumCode = 5149;
        name      = "Fao height";
        code      = 5751;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “KOC CD height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5188</b></li>
     *   <li>EPSG vertical datum name: <b>KOC Construction Datum</b></li>
     *   <li>EPSG vertical CRS code: <b>5790</b></li>
     *   <li>EPSG vertical CRS name: <b>KOC CD height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testKOC_CD() throws FactoryException {
        important = true;
        datumName = "KOC Construction Datum";
        datumCode = 5188;
        name      = "KOC CD height";
        code      = 5790;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “KOC WD depth” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5187</b></li>
     *   <li>EPSG vertical datum name: <b>KOC Well Datum</b></li>
     *   <li>EPSG vertical CRS code: <b>5789</b></li>
     *   <li>EPSG vertical CRS name: <b>KOC WD depth</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testKOC_WD() throws FactoryException {
        important = true;
        datumName = "KOC Well Datum";
        datumCode = 5187;
        name      = "KOC WD depth";
        code      = 5789;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “KOC WD depth (ft)” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5187</b></li>
     *   <li>EPSG vertical datum name: <b>KOC Well Datum</b></li>
     *   <li>EPSG vertical CRS code: <b>5614</b></li>
     *   <li>EPSG vertical CRS name: <b>KOC WD depth (ft)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testKOC_WD_ft() throws FactoryException {
        important = true;
        datumName = "KOC Well Datum";
        datumCode = 5187;
        name      = "KOC WD depth (ft)";
        code      = 5614;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Kuwait PWD height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5186</b></li>
     *   <li>EPSG vertical datum name: <b>Kuwait PWD</b></li>
     *   <li>EPSG vertical CRS code: <b>5788</b></li>
     *   <li>EPSG vertical CRS name: <b>Kuwait PWD height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testKuwaitPWD() throws FactoryException {
        important = true;
        datumName = "Kuwait PWD";
        datumCode = 5186;
        name      = "Kuwait PWD height";
        code      = 5788;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Lagos 1955 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5194</b></li>
     *   <li>EPSG vertical datum name: <b>Lagos 1955</b></li>
     *   <li>EPSG vertical CRS code: <b>5796</b></li>
     *   <li>EPSG vertical CRS name: <b>Lagos 1955 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testLagos() throws FactoryException {
        important = true;
        datumName = "Lagos 1955";
        datumCode = 5194;
        name      = "Lagos 1955 height";
        code      = 5796;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “msl depth” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5100</b></li>
     *   <li>EPSG vertical datum name: <b>Mean Sea Level</b></li>
     *   <li>EPSG vertical CRS code: <b>5715</b></li>
     *   <li>EPSG vertical CRS name: <b>MSL depth</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testMslDepth() throws FactoryException {
        important = true;
        datumName = "Mean Sea Level";
        datumCode = 5100;
        name      = "MSL depth";
        code      = 5715;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “msl height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5100</b></li>
     *   <li>EPSG vertical datum name: <b>Mean Sea Level</b></li>
     *   <li>EPSG vertical CRS code: <b>5714</b></li>
     *   <li>EPSG vertical CRS name: <b>MSL height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testMslHeight() throws FactoryException {
        important = true;
        datumName = "Mean Sea Level";
        datumCode = 5100;
        name      = "MSL height";
        code      = 5714;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “NAP height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5109</b></li>
     *   <li>EPSG vertical datum name: <b>Normaal Amsterdams Peil</b></li>
     *   <li>EPSG vertical CRS code: <b>5709</b></li>
     *   <li>EPSG vertical CRS name: <b>NAP height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testNAP() throws FactoryException {
        important = true;
        datumName = "Normaal Amsterdams Peil";
        datumCode = 5109;
        name      = "NAP height";
        code      = 5709;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “NAVD88 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5103</b></li>
     *   <li>EPSG vertical datum name: <b>North American Vertical Datum 1988</b></li>
     *   <li>EPSG vertical CRS code: <b>5703</b></li>
     *   <li>EPSG vertical CRS name: <b>NAVD88 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testNAVD88() throws FactoryException {
        important = true;
        datumName = "North American Vertical Datum 1988";
        datumCode = 5103;
        name      = "NAVD88 height";
        code      = 5703;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “NGF IGN69 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5119</b></li>
     *   <li>EPSG vertical datum name: <b>Nivellement General de la France - IGN69</b></li>
     *   <li>EPSG vertical CRS code: <b>5720</b></li>
     *   <li>EPSG vertical CRS name: <b>NGF-IGN69 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testIGN69() throws FactoryException {
        important = true;
        datumName = "Nivellement General de la France - IGN69";
        datumCode = 5119;
        name      = "NGF-IGN69 height";
        code      = 5720;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “NGF Lallemand height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5118</b></li>
     *   <li>EPSG vertical datum name: <b>Nivellement General de la France - Lallemand</b></li>
     *   <li>EPSG vertical CRS code: <b>5719</b></li>
     *   <li>EPSG vertical CRS name: <b>NGF Lallemand height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testLallemand() throws FactoryException {
        important = true;
        datumName = "Nivellement General de la France - Lallemand";
        datumCode = 5118;
        name      = "NGF Lallemand height";
        code      = 5719;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “NGVD29 height (ftUS)” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5102</b></li>
     *   <li>EPSG vertical datum name: <b>National Geodetic Vertical Datum 1929</b></li>
     *   <li>EPSG vertical CRS code: <b>5702</b></li>
     *   <li>EPSG vertical CRS name: <b>NGVD29 height (ftUS)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testNGVD29() throws FactoryException {
        important = true;
        datumName = "National Geodetic Vertical Datum 1929";
        datumCode = 5102;
        name      = "NGVD29 height (ftUS)";
        code      = 5702;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “ODN height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5101</b></li>
     *   <li>EPSG vertical datum name: <b>Ordnance Datum Newlyn</b></li>
     *   <li>EPSG vertical CRS code: <b>5701</b></li>
     *   <li>EPSG vertical CRS name: <b>ODN height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testODN() throws FactoryException {
        important = true;
        datumName = "Ordnance Datum Newlyn";
        datumCode = 5101;
        name      = "ODN height";
        code      = 5701;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “PHD93 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5123</b></li>
     *   <li>EPSG vertical datum name: <b>PDO Height Datum 1993</b></li>
     *   <li>EPSG vertical CRS code: <b>5724</b></li>
     *   <li>EPSG vertical CRS name: <b>PHD93 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testPHD93() throws FactoryException {
        important = true;
        datumName = "PDO Height Datum 1993";
        datumCode = 5123;
        name      = "PHD93 height";
        code      = 5724;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Yellow Sea 1956 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5104</b></li>
     *   <li>EPSG vertical datum name: <b>Yellow Sea 1956</b></li>
     *   <li>EPSG vertical CRS code: <b>5736</b></li>
     *   <li>EPSG vertical CRS name: <b>Yellow Sea 1956 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testYellowSea1956() throws FactoryException {
        important = true;
        datumName = "Yellow Sea 1956";
        datumCode = 5104;
        name      = "Yellow Sea 1956 height";
        code      = 5736;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }

    /**
     * Tests “Yellow Sea 1985 height” vertical CRS creation from the factory.
     *
     * <ul>
     *   <li>EPSG vertical datum code: <b>5137</b></li>
     *   <li>EPSG vertical datum name: <b>Yellow Sea 1985</b></li>
     *   <li>EPSG vertical CRS code: <b>5737</b></li>
     *   <li>EPSG vertical CRS name: <b>Yellow Sea 1985 height</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the vertical datum or CRS from the EPSG code.
     */
    @Test
    public void testYellowSea1985() throws FactoryException {
        important = true;
        datumName = "Yellow Sea 1985";
        datumCode = 5137;
        name      = "Yellow Sea 1985 height";
        code      = 5737;
        createAndVerifyVerticalDatum();
        verifyVerticalCRS();
    }
}
