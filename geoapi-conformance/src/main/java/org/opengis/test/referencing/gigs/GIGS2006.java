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
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.test.Configuration;
import org.opengis.test.FactoryFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;


/**
 * Verifies reference projected CRSs bundled with the geoscience software.
 *
 * <table class="gigs">
 * <caption>Test description</caption>
 * <tr>
 *   <th>Test method:</th>
 *   <td>Compare projected CRS definitions included in the software against the EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_2006_libProjectedCRS.csv">{@code GIGS_2006_libProjectedCRS.csv}</a>
 *       and EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link CRSAuthorityFactory#createProjectedCRS(String)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>Projected CRS definitions bundled with the software should have the same name, coordinate system
 *       (including units and axes abbreviations and axes order) and map projection as in the EPSG Dataset.
 *       CRSs missing from the software or at variance with those in the EPSG Dataset should be reported.</td>
 * </tr></table>
 *
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.gigs.GIGS2006;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS2006 {
 *    public MyTest() {
 *        super(new MyCRSAuthorityFactory());
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
public strictfp class GIGS2006 extends AuthorityFactoryTestCase<ProjectedCRS> {
    /**
     * The EPSG code of the expected datum.
     */
    public int datumCode;

    /**
     * The names of map projection (informative). Projected CRS conversion should have one of those names.
     * However those names are approximate. For example the “Argentina zones” name may apply to a wide
     * range of names like “Argentina zone 1”, “Argentina zone 2”, <i>etc</i>.
     */
    public String[] projectionNames;

    /**
     * {@code true} if the expected axis directions are ({@link AxisDirection#NORTH NORTH},
     * {@link AxisDirection#EAST EAST}) instead of the usual ({@code EAST}, {@code NORTH}).
     */
    public boolean isNorthAxisFirst;

    /**
     * {@code true} if the <var>x</var> values are increasing toward {@link AxisDirection#WEST WEST}
     * instead of {@link AxisDirection#EAST EAST}.
     */
    public boolean isWestOrientated;

    /**
     * {@code true} if the <var>y</var> values are increasing toward {@link AxisDirection#SOUTH SOUTH}
     * instead of {@link AxisDirection#NORTH NORTH}.
     */
    public boolean isSouthOrientated;

    /**
     * The CRS created by the factory, or {@code null} if not yet created or if CRS creation failed.
     *
     * @see #crsAuthorityFactory
     */
    private ProjectedCRS crs;

    /**
     * Factory to use for building {@link ProjectedCRS} instances, or {@code null} if none.
     * This is the factory used by the {@link #getIdentifiedObject()} method.
     */
    protected final CRSAuthorityFactory crsAuthorityFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead of
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS2006} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(FactoryFilter.ByAuthority.EPSG, CRSAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory  factory for creating {@link ProjectedCRS} instances.
     */
    public GIGS2006(final CRSAuthorityFactory crsFactory) {
        super(crsFactory);
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
        assertNull(op.put(Configuration.Key.crsAuthorityFactory, crsAuthorityFactory));
        return op;
    }

    /**
     * Returns the projected CRS instance to be tested. When this method is invoked for the first time, it creates the
     * CRS to test by invoking the {@link CRSAuthorityFactory#createProjectedCRS(String)} method with the current
     * {@link #code} value in argument. The created object is then cached and returned in all subsequent invocations
     * of this method.
     *
     * @return the projected CRS instance to test.
     * @throws FactoryException if an error occurred while creating the projected CRS instance.
     */
    @Override
    public ProjectedCRS getIdentifiedObject() throws FactoryException {
        if (crs == null) {
            assumeNotNull(crsAuthorityFactory);
            try {
                crs = crsAuthorityFactory.createProjectedCRS(String.valueOf(code));
            } catch (NoSuchIdentifierException e) {
                /*
                 * Relaxed the exception type from NoSuchAuthorityCodeException because CoordinateOperation creation
                 * will typically use MathTransformFactory under the hood, which throws NoSuchIdentifierException for
                 * non-implemented operation methods (may be identified by their name rather than EPSG code).
                 */
                unsupportedCode(ProjectedCRS.class, code);
                throw e;
            }
        }
        return crs;
    }

    /**
     * Verifies the properties of the projected CRS given by {@link #getIdentifiedObject()}.
     */
    private void createAndVerifyProjectedCRS(final int code) throws FactoryException {
        this.code = code;
        crs = null;                 // For forcing the fetch of a new projected CRS.

        final ProjectedCRS crs = getIdentifiedObject();
        assertNotNull("ProjectedCRS", crs);
        validators.validate(crs);

        // Projected CRS identifier.
        assertContainsCode("ProjectedCRS.getIdentifiers()", "EPSG", code, crs.getIdentifiers());

        // Projected CRS components.
        if (isDependencyIdentificationSupported) {
            configurationTip = Configuration.Key.isDependencyIdentificationSupported;

            // Geodetic datum name.
            assertContainsCode("ProjectedCRS.getDatum().getIdentifiers()",
                    "EPSG", datumCode, crs.getDatum().getIdentifiers());

            // Base geographic CRS name.
            if (isStandardNameSupported) {
                configurationTip = Configuration.Key.isStandardNameSupported;
                assertEquals("ProjectedCRS.getBaseCRS().getName()", name, getVerifiableName(crs.getBaseCRS()));
            }
            configurationTip = null;
        }

        // Projected CRS coordinate system.
        final CartesianCS cs = crs.getCoordinateSystem();
        assertNotNull("ProjectedCRS.getCoordinateSystem()", crs);
        assertEquals("ProjectedCRS.getCoordinateSystem().getDimension()", 2, cs.getDimension());

        // Coordinate sytem axis directions.
        final AxisDirection[] directions = new AxisDirection[2];
        directions[isNorthAxisFirst ? 1 : 0] = isWestOrientated  ? AxisDirection.WEST  : AxisDirection.EAST;
        directions[isNorthAxisFirst ? 0 : 1] = isSouthOrientated ? AxisDirection.SOUTH : AxisDirection.NORTH;
        assertAxisDirectionsEqual("ProjectedCRS.getCoordinateSystem().getAxis(*)", cs, directions);
    }

    /**
     * Tests “Abidjan 1987”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2165</b>, <b>2043</b>, <b>2041</b></li>
     *   <li>Geographic CRS name: <b>Abidjan 1987</b></li>
     *   <li>Projection names (informative): <b>TM 5 NW</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testAbidjan() throws FactoryException {
        important       = true;
        name            = "Abidjan 1987";
        projectionNames = new String[] {"TM 5 NW", "UTM"};
        datumCode       = 6143;
        createAndVerifyProjectedCRS(2165);
        createAndVerifyProjectedCRS(2043);
        createAndVerifyProjectedCRS(2041);
    }

    /**
     * Tests “Accra”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2136</b>, <b>2137</b></li>
     *   <li>Geographic CRS name: <b>Accra</b></li>
     *   <li>Projection names (informative): <b>TM 1 NW</b>, <b>Ghana National Grid</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testAccra() throws FactoryException {
        important       = true;
        name            = "Accra";
        projectionNames = new String[] {"TM 1 NW", "Ghana National Grid"};
        datumCode       = 6168;
        createAndVerifyProjectedCRS(2136);
        createAndVerifyProjectedCRS(2137);
    }

    /**
     * Tests “AGD66”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>20249</b>, <b>20250</b>, <b>20251</b>, <b>20252</b>, <b>20253</b>,
     *       <b>20254</b>, <b>20255</b>, <b>20256</b></li>
     *   <li>Geographic CRS name: <b>AGD66</b></li>
     *   <li>Projection names (informative): <b>Australian Map Grid zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testAGD66() throws FactoryException {
        important       = true;
        name            = "AGD66";
        projectionNames = new String[] {"Australian Map Grid zones"};
        datumCode       = 6202;
        for (int code = 20249; code <= 20256; code++) {    // Loop over 8 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “AGD84”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>20349</b>, <b>20350</b>, <b>20351</b>, <b>20352</b>, <b>20353</b>,
     *       <b>20354</b>, <b>20355</b>, <b>20356</b></li>
     *   <li>Geographic CRS name: <b>AGD84</b></li>
     *   <li>Projection names (informative): <b>Australian Map Grid zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testAGD84() throws FactoryException {
        important       = true;
        name            = "AGD84";
        projectionNames = new String[] {"Australian Map Grid zones"};
        datumCode       = 6203;
        for (int code = 20349; code <= 20356; code++) {    // Loop over 8 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “Ain el Abd”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2318</b>, <b>20436</b>, <b>20437</b></li>
     *   <li>Geographic CRS name: <b>Ain el Abd</b></li>
     *   <li>Projection names (informative): <b>Aramco Lambert</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testAinElAbd() throws FactoryException {
        important       = true;
        name            = "Ain el Abd";
        projectionNames = new String[] {"Aramco Lambert", "UTM"};
        datumCode       = 6204;
        createAndVerifyProjectedCRS(2318);
        createAndVerifyProjectedCRS(20436);
        createAndVerifyProjectedCRS(20437);
    }

    /**
     * Tests “Amersfoort”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>28992</b></li>
     *   <li>Geographic CRS name: <b>Amersfoort</b></li>
     *   <li>Projection names (informative): <b>RD New</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testAmersfoort() throws FactoryException {
        important       = true;
        name            = "Amersfoort";
        projectionNames = new String[] {"RD New"};
        datumCode       = 6289;
        createAndVerifyProjectedCRS(28992);
    }

    /**
     * Tests “Aratu”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>20822</b>, <b>20823</b>, <b>20824</b></li>
     *   <li>Geographic CRS name: <b>Aratu</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testAratu() throws FactoryException {
        important       = true;
        name            = "Aratu";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6208;
        createAndVerifyProjectedCRS(20822);
        createAndVerifyProjectedCRS(20823);
        createAndVerifyProjectedCRS(20824);
    }

    /**
     * Tests “Batavia”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3001</b>, <b>2308</b></li>
     *   <li>Geographic CRS name: <b>Batavia</b></li>
     *   <li>Projection names (informative): <b>NEIEZ</b>, <b>TM 109 SE</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testBatavia() throws FactoryException {
        important       = true;
        name            = "Batavia";
        projectionNames = new String[] {"NEIEZ", "TM 109 SE", "UTM"};
        datumCode       = 6211;
        createAndVerifyProjectedCRS(3001);
        createAndVerifyProjectedCRS(2308);
    }

    /**
     * Tests “Beijing 1954”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>21413</b>, <b>21414</b>, <b>21415</b>, <b>21416</b>, <b>21417</b>, <b>21418</b>,
     *       <b>21419</b>, <b>21420</b>, <b>21421</b>, <b>21422</b>, <b>21423</b></li>
     *   <li>Geographic CRS name: <b>Beijing 1954</b></li>
     *   <li>Projection names (informative): <b>6-degree Gauss-Kruger</b></li>
     *   <li>Specific usage / Remarks: <b>Check axes order and abbreviations</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testBeijing() throws FactoryException {
        important        = true;
        name             = "Beijing 1954";
        projectionNames  = new String[] {"6-degree Gauss-Kruger"};
        datumCode        = 6214;
        isNorthAxisFirst = true;
        for (int code = 21413; code <= 21423; code++) {    // Loop over 11 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “Bogota 1975”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>21896</b>, <b>21897</b>, <b>21898</b>, <b>21899</b></li>
     *   <li>Geographic CRS name: <b>Bogota 1975</b></li>
     *   <li>Projection names (informative): <b>Colombia zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testBogota() throws FactoryException {
        important        = true;
        name             = "Bogota 1975";
        projectionNames      = new String[] {"Colombia zones"};
        datumCode            = 6218;
        isNorthAxisFirst = true;
        for (int code = 21896; code <= 21899; code++) {    // Loop over 4 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “Camacupa”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>22091</b>, <b>22092</b>, <b>22032</b>, <b>22033</b></li>
     *   <li>Geographic CRS name: <b>Camacupa</b></li>
     *   <li>Projection names (informative): <b>TM 11.30 SE</b>, <b>TM 12 SE</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testCamacupa() throws FactoryException {
        important       = true;
        name            = "Camacupa 1948";
        projectionNames = new String[] {"TM 11.30 SE", "TM 12 SE", "UTM"};
        datumCode       = 6220;
        createAndVerifyProjectedCRS(22091);
        createAndVerifyProjectedCRS(22092);
        createAndVerifyProjectedCRS(22032);
        createAndVerifyProjectedCRS(22033);
    }

    /**
     * Tests “Campo Inchauspe”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>22191</b>, <b>22192</b>, <b>22193</b>, <b>22194</b>, <b>22195</b>, <b>22196</b>, <b>22197</b>, <b>2315</b>, <b>2316</b></li>
     *   <li>Geographic CRS name: <b>Campo Inchauspe</b></li>
     *   <li>Projection names (informative): <b>Argentina zones</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testCampoInchauspe() throws FactoryException {
        important        = true;
        name             = "Campo Inchauspe";
        projectionNames  = new String[] {"Argentina zones", "UTM"};
        datumCode        = 6221;
        isNorthAxisFirst = true;
        for (int code = 22191; code <= 22197; code++) {    // Loop over 7 codes
            createAndVerifyProjectedCRS(code);
        }
        isNorthAxisFirst = false;
        createAndVerifyProjectedCRS(2315);
        createAndVerifyProjectedCRS(2316);
    }

    /**
     * Tests “Carthage”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>22391</b>, <b>22392</b></li>
     *   <li>Geographic CRS name: <b>Carthage</b></li>
     *   <li>Projection names (informative): <b>Tunisia zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testCarthage() throws FactoryException {
        important       = true;
        name            = "Carthage";
        projectionNames = new String[] {"Tunisia zones"};
        datumCode       = 6223;
        createAndVerifyProjectedCRS(22391);
        createAndVerifyProjectedCRS(22392);
    }

    /**
     * Tests “Dealul Piscului 1930”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>31600</b></li>
     *   <li>Geographic CRS name: <b>Dealul Piscului 1930</b></li>
     *   <li>Projection names (informative): <b>Stereo 30</b></li>
     *   <li>Specific usage / Remarks: <b>Check axes order and abbreviations</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testDealulPiscului() throws FactoryException {
        important       = true;
        name            = "Dealul Piscului 1930";
        projectionNames = new String[] {"Stereo 30"};
        datumCode       = 6316;
        createAndVerifyProjectedCRS(31600);
    }

    /**
     * Tests “Deir ez Zor”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>22700</b>, <b>22770</b></li>
     *   <li>Geographic CRS name: <b>Deir ez Zor</b></li>
     *   <li>Projection names (informative): <b>Levant zone, Syria Lambert</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testDeirEzZor() throws FactoryException {
        important       = true;
        name            = "Deir ez Zor";
        projectionNames = new String[] {"Levant zone, Syria Lambert"};
        datumCode       = 6227;
        createAndVerifyProjectedCRS(22700);
        createAndVerifyProjectedCRS(22770);
    }

    /**
     * Tests “DGN95”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>23866</b>, <b>23867</b>, <b>23868</b>, <b>23869</b>, <b>23870</b>,
     *       <b>23871</b>, <b>23872</b>, <b>23877</b>, <b>23878</b>, <b>23879</b>, <i>…5 more</i></li>
     *   <li>Geographic CRS name: <b>DGN95</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testDGN95() throws FactoryException {
        important       = true;
        name            = "DGN95";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6755;
        for (int code = 23866; code <= 23872; code++) {    // Loop over 7 codes
            createAndVerifyProjectedCRS(code);
        }
        for (int code = 23877; code <= 23884; code++) {    // Loop over 8 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “DHDN”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>31466</b>, <b>31467</b>, <b>31468</b>, <b>31469</b></li>
     *   <li>Geographic CRS name: <b>DHDN</b></li>
     *   <li>Projection names (informative): <b>3-degree Gauss-Kruger</b></li>
     *   <li>Specific usage / Remarks: <b>Check axes order and abbreviations</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testDHDN() throws FactoryException {
        important        = true;
        name             = "DHDN";
        projectionNames  = new String[] {"3-degree Gauss-Kruger"};
        datumCode        = 6314;
        isNorthAxisFirst = true;
        for (int code = 31466; code <= 31469; code++) {    // Loop over 4 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “ED50”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>23028</b>, <b>23029</b>, <b>23030</b>, <b>23031</b>, <b>23032</b>,
     *       <b>23033</b>, <b>23034</b>, <b>23035</b>, <b>23090</b>, <b>23095</b></li>
     *   <li>Geographic CRS name: <b>ED50</b></li>
     *   <li>Projection names (informative): <b>UTM</b>, <b>TM 5 NE</b>, <b>TM 0 N</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testED50() throws FactoryException {
        important       = true;
        name            = "ED50";
        projectionNames = new String[] {"UTM", "TM 5 NE", "TM 0 N"};
        datumCode       = 6230;
        for (int code = 23028; code <= 23035; code++) {    // Loop over 8 codes
            createAndVerifyProjectedCRS(code);
        }
        createAndVerifyProjectedCRS(23090);
        createAndVerifyProjectedCRS(23095);
    }

    /**
     * Tests “ED50(ED77)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2058</b>, <b>2059</b>, <b>2060</b></li>
     *   <li>Geographic CRS name: <b>ED50(ED77)</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testED50_77() throws FactoryException {
        important       = true;
        name            = "ED50(ED77)";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6154;
        createAndVerifyProjectedCRS(2058);
        createAndVerifyProjectedCRS(2059);
        createAndVerifyProjectedCRS(2060);
    }

    /**
     * Tests “Egypt 1907”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>22991</b>, <b>22992</b>, <b>22993</b>, <b>22994</b></li>
     *   <li>Geographic CRS name: <b>Egypt 1907</b></li>
     *   <li>Projection names (informative): <b>Egypt belts</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testEgypt() throws FactoryException {
        important       = true;
        name            = "Egypt 1907";
        projectionNames = new String[] {"Egypt belts"};
        datumCode       = 6229;
        for (int code = 22991; code <= 22994; code++) {    // Loop over 4 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “Egypt Gulf of Suez S-650 TL”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3355</b></li>
     *   <li>Geographic CRS name: <b>Egypt Gulf of Suez S-650 TL</b></li>
     *   <li>Projection names (informative): <b>Egypt Red belt</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testGulfOfSuez() throws FactoryException {
        important       = true;
        name            = "Egypt Gulf of Suez S-650 TL";
        projectionNames = new String[] {"Egypt Red belt"};
        datumCode       = 6706;
        createAndVerifyProjectedCRS(3355);
    }

    /**
     * Tests “ELD79”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2068</b>, <b>2069</b>, <b>2070</b>, <b>2071</b>, <b>2072</b>, <b>2073</b>,
     *       <b>2074</b>, <b>2075</b>, <b>2076</b>, <b>2077</b>, <i>…3 more</i></li>
     *   <li>Geographic CRS name: <b>ELD79</b></li>
     *   <li>Projection names (informative): <b>Libya zones</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testELD79() throws FactoryException {
        important       = true;
        name            = "ELD79";
        projectionNames = new String[] {"Libya zones", "UTM"};
        datumCode       = 6159;
        for (int code = 2068; code <= 2080; code++) {    // Loop over 13 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “ETRS89”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>25828</b>, <b>25829</b>, <b>25830</b>, <b>25831</b>, <b>25832</b>,
     *       <b>25833</b>, <b>25834</b>, <b>25835</b></li>
     *   <li>Geographic CRS name: <b>ETRS89</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testETRS89() throws FactoryException {
        important       = true;
        name            = "ETRS89";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6258;
        for (int code = 25828; code <= 25835; code++) {    // Loop over 8 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “Fahud”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>23239</b>, <b>23240</b></li>
     *   <li>Geographic CRS name: <b>Fahud</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testFahud() throws FactoryException {
        important       = true;
        name            = "Fahud";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6232;
        createAndVerifyProjectedCRS(23239);
        createAndVerifyProjectedCRS(23240);
    }

    /**
     * Tests “FD58”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3200</b></li>
     *   <li>Geographic CRS name: <b>FD58</b></li>
     *   <li>Projection names (informative): <b>Iraq zone</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testFD58() throws FactoryException {
        important       = true;
        name            = "FD58";
        projectionNames = new String[] {"Iraq zone"};
        datumCode       = 6132;
        createAndVerifyProjectedCRS(3200);
    }

    /**
     * Tests “GDA94”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>28349</b>, <b>28350</b>, <b>28351</b>, <b>28352</b>, <b>28353</b>,
     *       <b>28354</b>, <b>28355</b>, <b>28356</b></li>
     *   <li>Geographic CRS name: <b>GDA94</b></li>
     *   <li>Projection names (informative): <b>Australian Map Grid zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testGDA94() throws FactoryException {
        important       = true;
        name            = "GDA94";
        projectionNames = new String[] {"Australian Map Grid zones"};
        datumCode       = 6283;
        for (int code = 28349; code <= 28356; code++) {    // Loop over 8 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “GDM2000”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3375</b>, <b>3376</b></li>
     *   <li>Geographic CRS name: <b>GDM2000</b></li>
     *   <li>Projection names (informative): <b>RSO grids</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testGDM2000() throws FactoryException {
        important       = true;
        name            = "GDM2000";
        projectionNames = new String[] {"RSO grids"};
        datumCode       = 6742;
        createAndVerifyProjectedCRS(3375);
        createAndVerifyProjectedCRS(3376);
    }

    /**
     * Tests “HD72”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>23700</b></li>
     *   <li>Geographic CRS name: <b>HD72</b></li>
     *   <li>Projection names (informative): <b>EOV</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testHD72() throws FactoryException {
        important       = true;
        name            = "HD72";
        projectionNames = new String[] {"EOV"};
        datumCode       = 6237;
        createAndVerifyProjectedCRS(23700);
    }

    /**
     * Tests “IGN Astro 1960”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3367</b>, <b>3368</b>, <b>3369</b></li>
     *   <li>Geographic CRS name: <b>IGN Astro 1960</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testIGNAstro() throws FactoryException {
        important       = true;
        name            = "IGN Astro 1960";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6700;
        createAndVerifyProjectedCRS(3367);
        createAndVerifyProjectedCRS(3368);
        createAndVerifyProjectedCRS(3369);
    }

    /**
     * Tests “Indian 1954”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>23946</b>, <b>23947</b>, <b>23948</b></li>
     *   <li>Geographic CRS name: <b>Indian 1954</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testIndian1954() throws FactoryException {
        important       = true;
        name            = "Indian 1954";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6239;
        createAndVerifyProjectedCRS(23946);
        createAndVerifyProjectedCRS(23947);
        createAndVerifyProjectedCRS(23948);
    }

    /**
     * Tests “Indian 1960”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3148</b>, <b>3149</b></li>
     *   <li>Geographic CRS name: <b>Indian 1960</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testIndian1960() throws FactoryException {
        important       = true;
        name            = "Indian 1960";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6131;
        createAndVerifyProjectedCRS(3148);
        createAndVerifyProjectedCRS(3149);
    }

    /**
     * Tests “Indian 1975”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>24047</b>, <b>24048</b></li>
     *   <li>Geographic CRS name: <b>Indian 1975</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testIndian1975() throws FactoryException {
        important       = true;
        name            = "Indian 1975";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6240;
        createAndVerifyProjectedCRS(24047);
        createAndVerifyProjectedCRS(24048);
    }

    /**
     * Tests “Kalianpur 1937”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>24305</b>, <b>24306</b>, <b>24375</b></li>
     *   <li>Geographic CRS name: <b>Kalianpur 1937</b></li>
     *   <li>Projection names (informative): <b>India zones</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testKalianpur1937() throws FactoryException {
        important       = true;
        name            = "Kalianpur 1937";
        projectionNames = new String[] {"India zones", "UTM"};
        datumCode       = 6144;
        createAndVerifyProjectedCRS(24305);
        createAndVerifyProjectedCRS(24306);
        createAndVerifyProjectedCRS(24375);
    }

    /**
     * Tests “Kalianpur 1962”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>24312</b>, <b>24313</b>, <b>24376</b>, <b>24377</b></li>
     *   <li>Geographic CRS name: <b>Kalianpur 1962</b></li>
     *   <li>Projection names (informative): <b>India zones</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testKalianpur1962() throws FactoryException {
        important       = true;
        name            = "Kalianpur 1962";
        projectionNames = new String[] {"India zones", "UTM"};
        datumCode       = 6145;
        createAndVerifyProjectedCRS(24312);
        createAndVerifyProjectedCRS(24313);
        createAndVerifyProjectedCRS(24376);
        createAndVerifyProjectedCRS(24377);
    }

    /**
     * Tests “Kalianpur 1975”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>24342</b>, <b>24343</b>, <b>24379</b>, <b>24380</b></li>
     *   <li>Geographic CRS name: <b>Kalianpur 1975</b></li>
     *   <li>Projection names (informative): <b>India zones</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testKalianpur1975() throws FactoryException {
        important       = true;
        name            = "Kalianpur 1975";
        projectionNames = new String[] {"India zones", "UTM"};
        datumCode       = 6146;
        createAndVerifyProjectedCRS(24342);
        createAndVerifyProjectedCRS(24343);
        createAndVerifyProjectedCRS(24379);
        createAndVerifyProjectedCRS(24380);
    }

    /**
     * Tests “Kertau 1968”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>24547</b>, <b>24548</b></li>
     *   <li>Geographic CRS name: <b>Kertau 1968</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testKertau() throws FactoryException {
        important       = true;
        name            = "Kertau 1968";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6245;
        createAndVerifyProjectedCRS(24547);
        createAndVerifyProjectedCRS(24548);
    }

    /**
     * Tests “Kertau (RSO)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3167</b>, <b>3168</b></li>
     *   <li>Geographic CRS name: <b>Kertau (RSO)</b></li>
     *   <li>Projection names (informative): <b>RSO</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testKertau_RSO() throws FactoryException {
        important       = true;
        name            = "Kertau (RSO)";
        projectionNames = new String[] {"RSO"};
        datumCode       = 6751;
        createAndVerifyProjectedCRS(3167);
        createAndVerifyProjectedCRS(3168);
    }

    /**
     * Tests “KOC”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>24600</b></li>
     *   <li>Geographic CRS name: <b>KOC</b></li>
     *   <li>Projection names (informative): <b>KOC Lambert</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testKOC() throws FactoryException {
        important       = true;
        name            = "KOC";
        projectionNames = new String[] {"KOC Lambert"};
        datumCode       = 6246;
        createAndVerifyProjectedCRS(24600);
    }

    /**
     * Tests “LGD2006”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3177</b>, <b>3190</b>, <b>3191</b>, <b>3192</b>, <b>3193</b>, <b>3194</b>,
     *       <b>3195</b>, <b>3196</b>, <b>3197</b>, <b>3198</b>, <i>…4 more</i></li>
     *   <li>Geographic CRS name: <b>LGD2006</b></li>
     *   <li>Projection names (informative): <b>Libya zones</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testLGD2006() throws FactoryException {
        important       = true;
        name            = "LGD2006";
        projectionNames = new String[] {"Libya zones", "UTM"};
        datumCode       = 6754;
        createAndVerifyProjectedCRS(3177);
        for (int code = 3190; code <= 3199; code++) {    // Loop over 10 codes
            createAndVerifyProjectedCRS(code);
        }
        createAndVerifyProjectedCRS(3201);
        createAndVerifyProjectedCRS(3202);
        createAndVerifyProjectedCRS(3203);
    }

    /**
     * Tests “Luzon 1911”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>25391</b>, <b>25392</b>, <b>25393</b>, <b>25394</b>, <b>25395</b></li>
     *   <li>Geographic CRS name: <b>Luzon 1911</b></li>
     *   <li>Projection names (informative): <b>Philippine zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testLuzon() throws FactoryException {
        important       = true;
        name            = "Luzon 1911";
        projectionNames = new String[] {"Philippine zones"};
        datumCode       = 6253;
        for (int code = 25391; code <= 25395; code++) {    // Loop over 5 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “MAGNA-SIRGAS”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3114</b>, <b>3115</b>, <b>3116</b>, <b>3117</b>, <b>3118</b></li>
     *   <li>Geographic CRS name: <b>MAGNA-SIRGAS</b></li>
     *   <li>Projection names (informative): <b>Colombia zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testMAGNA_SIRGAS() throws FactoryException {
        important        = true;
        name             = "MAGNA-SIRGAS";
        projectionNames  = new String[] {"Colombia zones"};
        datumCode        = 6686;
        isNorthAxisFirst = true;
        for (int code = 3114; code <= 3118; code++) {    // Loop over 5 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “Malongo 1987”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>25932</b></li>
     *   <li>Geographic CRS name: <b>Malongo 1987</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testMalongo() throws FactoryException {
        important       = true;
        name            = "Malongo 1987";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6259;
        createAndVerifyProjectedCRS(25932);
    }

    /**
     * Tests “Manoca 1962”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2215</b></li>
     *   <li>Geographic CRS name: <b>Manoca 1962</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testManoca() throws FactoryException {
        important       = true;
        name            = "Manoca 1962";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6193;
        createAndVerifyProjectedCRS(2215);
    }

    /**
     * Tests “Mauritania 1999”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3343</b>, <b>3344</b>, <b>3345</b></li>
     *   <li>Geographic CRS name: <b>Mauritania 1999</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testMauritania() throws FactoryException {
        important       = true;
        name            = "Mauritania 1999";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6702;
        createAndVerifyProjectedCRS(3343);
        createAndVerifyProjectedCRS(3344);
        createAndVerifyProjectedCRS(3345);
    }

    /**
     * Tests “LKS94”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3346</b></li>
     *   <li>Geographic CRS name: <b>LKS94</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testLKS94() throws FactoryException {
        important        = true;
        name             = "LKS94";
        projectionNames  = new String[] {"UTM"};
        datumCode        = 6126;
        isNorthAxisFirst = true;
        createAndVerifyProjectedCRS(3346);
    }

    /**
     * Tests “Pulkovo 1942”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3350</b>, <b>3351</b>, <b>3352</b></li>
     *   <li>Geographic CRS name: <b>Pulkovo 1942</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPulkovo1942_UTM() throws FactoryException {
        important        = true;
        name             = "Pulkovo 1942";
        projectionNames  = new String[] {"UTM"};
        datumCode        = 6284;
        isNorthAxisFirst = true;
        createAndVerifyProjectedCRS(3350);
        createAndVerifyProjectedCRS(3351);
        createAndVerifyProjectedCRS(3352);
    }

    /**
     * Tests “Mhast (offshore)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3354</b></li>
     *   <li>Geographic CRS name: <b>Mhast (offshore)</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testMhast_offshore() throws FactoryException {
        important       = true;
        name            = "Mhast (offshore)";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6705;
        createAndVerifyProjectedCRS(3354);
    }

    /**
     * Tests “Mhast (onshore)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3353</b></li>
     *   <li>Geographic CRS name: <b>Mhast (onshore)</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testMhast_onshore() throws FactoryException {
        important       = true;
        name            = "Mhast (onshore)";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6704;
        createAndVerifyProjectedCRS(3353);
    }

    /**
     * Tests “Minna”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>26331</b>, <b>26332</b>, <b>26391</b>, <b>26392</b>, <b>26393</b></li>
     *   <li>Geographic CRS name: <b>Minna</b></li>
     *   <li>Projection names (informative): <b>Nigeria belts</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testMinna() throws FactoryException {
        important       = true;
        name            = "Minna";
        projectionNames = new String[] {"Nigeria belts", "UTM"};
        datumCode       = 6263;
        createAndVerifyProjectedCRS(26331);
        createAndVerifyProjectedCRS(26332);
        createAndVerifyProjectedCRS(26391);
        createAndVerifyProjectedCRS(26392);
        createAndVerifyProjectedCRS(26393);
    }

    /**
     * Tests “Monte Mario”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3003</b>, <b>3004</b></li>
     *   <li>Geographic CRS name: <b>Monte Mario</b></li>
     *   <li>Projection names (informative): <b>Italy zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testMonteMario() throws FactoryException {
        important       = true;
        name            = "Monte Mario";
        projectionNames = new String[] {"Italy zones"};
        datumCode       = 6265;
        createAndVerifyProjectedCRS(3003);
        createAndVerifyProjectedCRS(3004);
    }

    /**
     * Tests “M'poraloko”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>26632</b>, <b>26692</b></li>
     *   <li>Geographic CRS name: <b>M'poraloko</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testMPoraloko() throws FactoryException {
        important       = true;
        name            = "M'poraloko";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6266;
        createAndVerifyProjectedCRS(26632);
        createAndVerifyProjectedCRS(26692);
    }

    /**
     * Tests “NAD27”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>26711</b>, <b>26712</b>, <b>32012</b>, <b>32040</b>, <b>32064</b>, <b>32065</b>, <b>32066</b></li>
     *   <li>Geographic CRS name: <b>NAD27</b></li>
     *   <li>Projection names (informative): <b>State Plane zones</b>, <b>BLM zones</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNAD27() throws FactoryException {
        important       = true;
        name            = "NAD27";
        projectionNames = new String[] {"State Plane zones", "BLM zones", "UTM"};
        datumCode       = 6267;
        createAndVerifyProjectedCRS(26711);
        createAndVerifyProjectedCRS(26712);
        createAndVerifyProjectedCRS(32012);
        createAndVerifyProjectedCRS(32040);
        createAndVerifyProjectedCRS(32064);
        createAndVerifyProjectedCRS(32065);
        createAndVerifyProjectedCRS(32066);
    }

    /**
     * Tests “NAD27 Michigan”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>6201</b>, <b>6202</b></li>
     *   <li>Geographic CRS name: <b>NAD27</b></li>
     *   <li>Projection names (informative): <b>State Plane zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * If {@link #isDeprecatedObjectCreationSupported} is {@code true}, then this method tests also:
     *
     * <ul>
     *   <li>Projected CRS codes: <b>26811</b>, <b>26812</b>, <b>26813</b></li>
     *   <li>Geographic CRS name: <b>NAD27 Michigan</b></li>
     *   <li>Projection names (informative): <b>State Plane zones</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNAD27_Michigan() throws FactoryException {
        important       = true;
        name            = "NAD27";
        projectionNames = new String[] {"State Plane zones"};
        datumCode       = 6267;
        createAndVerifyProjectedCRS(6201);
        createAndVerifyProjectedCRS(6202);
        if (isDeprecatedObjectCreationSupported) {
            name      = "NAD27 Michigan";
            datumCode = 6268;
            createAndVerifyProjectedCRS(26811);
            createAndVerifyProjectedCRS(26812);
            createAndVerifyProjectedCRS(26813);
        }
    }

    /**
     * Tests “NAD83”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3347</b>, <b>26911</b>, <b>26912</b>, <b>32156</b>, <b>3736</b>, <b>26990</b>,
     *       <b>2253</b>, <b>32140</b>, <b>2278</b></li>
     *   <li>Geographic CRS name: <b>NAD83</b></li>
     *   <li>Projection names (informative): <b>State Plane zones</b>, <b>UTM</b></li>
     *   <li>Specific usage / Remarks: <b>Check units</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNAD83() throws FactoryException {
        important       = true;
        name            = "NAD83";
        projectionNames = new String[] {"State Plane zones", "UTM"};
        datumCode       = 6269;
        createAndVerifyProjectedCRS(3347);
        createAndVerifyProjectedCRS(26911);
        createAndVerifyProjectedCRS(26912);
        createAndVerifyProjectedCRS(32156);
        createAndVerifyProjectedCRS(3736);
        createAndVerifyProjectedCRS(26990);
        createAndVerifyProjectedCRS(2253);
        createAndVerifyProjectedCRS(32140);
        createAndVerifyProjectedCRS(2278);
    }

    /**
     * Tests “NAD83(CSRS)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3348</b>, <b>2955</b>, <b>2956</b></li>
     *   <li>Geographic CRS name: <b>NAD83(CSRS)</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNAD83_CSRS() throws FactoryException {
        important       = true;
        name            = "NAD83(CSRS)";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6140;
        createAndVerifyProjectedCRS(3348);
        createAndVerifyProjectedCRS(2955);
        createAndVerifyProjectedCRS(2956);
    }

    /**
     * Tests “NAD83(HARN)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2845</b>, <b>2917</b>, <b>2809</b>, <b>2898</b></li>
     *   <li>Geographic CRS name: <b>NAD83(HARN)</b></li>
     *   <li>Projection names (informative): <b>State Plane zones</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNAD83_HARN() throws FactoryException {
        important       = true;
        name            = "NAD83(HARN)";
        projectionNames = new String[] {"State Plane zones", "UTM"};
        datumCode       = 6152;
        createAndVerifyProjectedCRS(2845);
        createAndVerifyProjectedCRS(2917);
        createAndVerifyProjectedCRS(2809);
        createAndVerifyProjectedCRS(2898);
    }

    /**
     * Tests “Nahrwan 1967”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>27039</b>, <b>27040</b></li>
     *   <li>Geographic CRS name: <b>Nahrwan 1967</b></li>
     *   <li>Projection names (informative): <b>Nahrwan 1967</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNahrwan() throws FactoryException {
        important       = true;
        name            = "Nahrwan 1967";
        projectionNames = new String[] {"Nahrwan 1967"};
        datumCode       = 6270;
        createAndVerifyProjectedCRS(27039);
        createAndVerifyProjectedCRS(27040);
    }

    /**
     * Tests “Naparima 1955”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2067</b></li>
     *   <li>Geographic CRS name: <b>Naparima 1955</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNaparima() throws FactoryException {
        important       = true;
        name            = "Naparima 1955";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6158;
        createAndVerifyProjectedCRS(2067);
    }

    /**
     * Tests “Nord Sahara 1959”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>30791</b>, <b>30792</b>, <b>30730</b>, <b>30731</b>, <b>30732</b></li>
     *   <li>Geographic CRS name: <b>Nord Sahara 1959</b></li>
     *   <li>Projection names (informative): <b>Voirol Unifie</b>, <b>UTM</b></li>
     *   <li>Specific usage / Remarks: <b>Check old Voirol</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNordSahara() throws FactoryException {
        important       = true;
        name            = "Nord Sahara 1959";
        projectionNames = new String[] {"Voirol Unifie", "UTM"};
        datumCode       = 6307;
        createAndVerifyProjectedCRS(30791);
        createAndVerifyProjectedCRS(30792);
        createAndVerifyProjectedCRS(30730);
        createAndVerifyProjectedCRS(30731);
        createAndVerifyProjectedCRS(30732);
    }

    /**
     * Tests “NTF (Paris)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>27571</b>, <b>27572</b>, <b>27573</b></li>
     *   <li>Geographic CRS name: <b>NTF (Paris)</b></li>
     *   <li>Projection names (informative): <b>Lambert zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNTF_Paris() throws FactoryException {
        important       = true;
        name            = "NTF (Paris)";
        projectionNames = new String[] {"Lambert zones"};
        datumCode       = 6807;
        createAndVerifyProjectedCRS(27571);
        createAndVerifyProjectedCRS(27572);
        createAndVerifyProjectedCRS(27573);
    }

    /**
     * Tests “NZGD2000”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2193</b>, <b>2133</b>, <b>2134</b>, <b>2135</b></li>
     *   <li>Geographic CRS name: <b>NZGD2000</b></li>
     *   <li>Projection names (informative): <b>TM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNZGD2000() throws FactoryException {
        important        = true;
        name             = "NZGD2000";
        projectionNames  = new String[] {"TM"};
        datumCode        = 6167;
        isNorthAxisFirst = true;
        createAndVerifyProjectedCRS(2193);
        isNorthAxisFirst = false;
        createAndVerifyProjectedCRS(2133);
        createAndVerifyProjectedCRS(2134);
        createAndVerifyProjectedCRS(2135);
    }

    /**
     * Tests “NZGD49”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>27200</b>, <b>27291</b>, <b>27292</b></li>
     *   <li>Geographic CRS name: <b>NZGD49</b></li>
     *   <li>Projection names (informative): <b>NZMG</b>, <b>TM zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testNZGD49() throws FactoryException {
        important       = true;
        name            = "NZGD49";
        projectionNames = new String[] {"NZMG", "TM zones"};
        datumCode       = 6272;
        createAndVerifyProjectedCRS(27200);
        createAndVerifyProjectedCRS(27291);
        createAndVerifyProjectedCRS(27292);
    }

    /**
     * Tests “OSGB 1936”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>27700</b></li>
     *   <li>Geographic CRS name: <b>OSGB 1936</b></li>
     *   <li>Projection names (informative): <b>TM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testOSGB1936() throws FactoryException {
        important       = true;
        name            = "OSGB 1936";
        projectionNames = new String[] {"TM"};
        datumCode       = 6277;
        createAndVerifyProjectedCRS(27700);
    }

    /**
     * Tests “PSD93”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3440</b></li>
     *   <li>Geographic CRS name: <b>PSD93</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPSD93() throws FactoryException {
        important       = true;
        name            = "PSD93";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6134;
        createAndVerifyProjectedCRS(3440);
    }

    /**
     * Tests “Pointe Noire”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>28232</b></li>
     *   <li>Geographic CRS name: <b>Pointe Noire</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPointeNoire() throws FactoryException {
        important       = true;
        name            = "Pointe Noire";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6282;
        createAndVerifyProjectedCRS(28232);
    }

    /**
     * Tests “POSGAR 94”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>22181</b>, <b>22182</b>, <b>22183</b>, <b>22184</b>, <b>22185</b>, <b>22186</b>, <b>22187</b></li>
     *   <li>Geographic CRS name: <b>POSGAR 94</b></li>
     *   <li>Projection names (informative): <b>Argentina zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPOSGAR94() throws FactoryException {
        important        = true;
        name             = "POSGAR 94";
        projectionNames  = new String[] {"Argentina zones"};
        datumCode        = 6694;
        isNorthAxisFirst = true;
        for (int code = 22181; code <= 22187; code++) {    // Loop over 7 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “POSGAR 98”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>22171</b>, <b>22172</b>, <b>22173</b>, <b>22174</b>, <b>22175</b>, <b>22176</b>, <b>22177</b></li>
     *   <li>Geographic CRS name: <b>POSGAR 98</b></li>
     *   <li>Projection names (informative): <b>Argentina zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPOSGAR98() throws FactoryException {
        important        = true;
        name             = "POSGAR 98";
        projectionNames  = new String[] {"Argentina zones"};
        datumCode        = 6190;
        isNorthAxisFirst = true;
        for (int code = 22171; code <= 22177; code++) {    // Loop over 7 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “PRS92”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3121</b>, <b>3122</b>, <b>3123</b>, <b>3124</b>, <b>3125</b></li>
     *   <li>Geographic CRS name: <b>PRS92</b></li>
     *   <li>Projection names (informative): <b>Philippine zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPRS92() throws FactoryException {
        important       = true;
        name            = "PRS92";
        projectionNames = new String[] {"Philippine zones"};
        datumCode       = 6683;
        for (int code = 3121; code <= 3125; code++) {    // Loop over 5 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “PSAD56”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>24817</b>, <b>24818</b>, <b>24819</b>, <b>24820</b>, <b>24877</b>, <b>24878</b>,
     *       <b>24879</b>, <b>24891</b>, <b>24892</b>, <b>24893</b></li>
     *   <li>Geographic CRS name: <b>PSAD56</b></li>
     *   <li>Projection names (informative): <b>UTM</b>, <b>Peru zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPSAD56() throws FactoryException {
        important       = true;
        name            = "PSAD56";
        projectionNames = new String[] {"UTM", "Peru zones"};
        datumCode       = 6248;
        for (int code = 24817; code <= 24820; code++) {    // Loop over 4 codes
            createAndVerifyProjectedCRS(code);
        }
        createAndVerifyProjectedCRS(24877);
        createAndVerifyProjectedCRS(24878);
        createAndVerifyProjectedCRS(24879);
        createAndVerifyProjectedCRS(24891);
        createAndVerifyProjectedCRS(24892);
        createAndVerifyProjectedCRS(24893);
    }

    /**
     * Tests “Pulkovo 1942”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>28409</b>, <b>28416</b>, <b>28424</b></li>
     *   <li>Geographic CRS name: <b>Pulkovo 1942</b></li>
     *   <li>Projection names (informative): <b>6-degree Gauss-Kruger</b></li>
     *   <li>Specific usage / Remarks: <b>Check axes order and abbreviations</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPulkovo1942() throws FactoryException {
        important        = true;
        name             = "Pulkovo 1942";
        projectionNames  = new String[] {"6-degree Gauss-Kruger"};
        datumCode        = 6284;
        isNorthAxisFirst = true;
        createAndVerifyProjectedCRS(28409);
        createAndVerifyProjectedCRS(28416);
        createAndVerifyProjectedCRS(28424);
    }

    /**
     * Tests “Pulkovo 1942(58)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3334</b>, <b>3335</b>, <b>3844</b></li>
     *   <li>Geographic CRS name: <b>Pulkovo 1942(58)</b></li>
     *   <li>Projection names (informative): <b>6-degree Gauss-Kruger</b>, <b>Stereo70</b></li>
     *   <li>Specific usage / Remarks: <b>Check axes order and abbreviations</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPulkovo1942_58() throws FactoryException {
        important        = true;
        name             = "Pulkovo 1942(58)";
        projectionNames  = new String[] {"6-degree Gauss-Kruger", "Stereo70"};
        datumCode        = 6179;
        isNorthAxisFirst = true;
        createAndVerifyProjectedCRS(3334);
        createAndVerifyProjectedCRS(3335);
        createAndVerifyProjectedCRS(3844);
    }

    /**
     * Tests “Pulkovo 1942(83)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3836</b></li>
     *   <li>Geographic CRS name: <b>Pulkovo 1942(83)</b></li>
     *   <li>Projection names (informative): <b>6-degree Gauss-Kruger</b></li>
     *   <li>Specific usage / Remarks: <b>Check axes order and abbreviations</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testPulkovo1942_83() throws FactoryException {
        important        = true;
        name             = "Pulkovo 1942(83)";
        projectionNames  = new String[] {"6-degree Gauss-Kruger"};
        datumCode        = 6178;
        isNorthAxisFirst = true;
        createAndVerifyProjectedCRS(3836);
    }

    /**
     * Tests “Qatar 1948”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2099</b></li>
     *   <li>Geographic CRS name: <b>Qatar 1948</b></li>
     *   <li>Projection names (informative): <b>Qatar Grid</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testQatar1948() throws FactoryException {
        important       = true;
        name            = "Qatar 1948";
        projectionNames = new String[] {"Qatar Grid"};
        datumCode       = 6286;
        createAndVerifyProjectedCRS(2099);
    }

    /**
     * Tests “Qatar 1974”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>28600</b></li>
     *   <li>Geographic CRS name: <b>Qatar 1974</b></li>
     *   <li>Projection names (informative): <b>Qatar National Grid</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testQatar1974() throws FactoryException {
        important       = true;
        name            = "Qatar 1974";
        projectionNames = new String[] {"Qatar National Grid"};
        datumCode       = 6285;
        createAndVerifyProjectedCRS(28600);
    }

    /**
     * Tests “QND95”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2932</b></li>
     *   <li>Geographic CRS name: <b>QND95</b></li>
     *   <li>Projection names (informative): <b>Qatar National Grid</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testQND95() throws FactoryException {
        important       = true;
        name            = "QND95";
        projectionNames = new String[] {"Qatar National Grid"};
        datumCode       = 6614;
        createAndVerifyProjectedCRS(2932);
    }

    /**
     * Tests “REGVEN”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2201</b>, <b>2202</b>, <b>2203</b></li>
     *   <li>Geographic CRS name: <b>REGVEN</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testREGVEN() throws FactoryException {
        important       = true;
        name            = "REGVEN";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6189;
        createAndVerifyProjectedCRS(2201);
        createAndVerifyProjectedCRS(2202);
        createAndVerifyProjectedCRS(2203);
    }

    /**
     * Tests “RGF93”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2154</b></li>
     *   <li>Geographic CRS name: <b>RGF93</b></li>
     *   <li>Projection names (informative): <b>Lambert 93</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testRGF93() throws FactoryException {
        important       = true;
        name            = "RGF93";
        projectionNames = new String[] {"Lambert 93"};
        datumCode       = 6171;
        createAndVerifyProjectedCRS(2154);
    }

    /**
     * Tests “SAD69”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>29101</b>, <b>29194</b></li>
     *   <li>Geographic CRS name: <b>SAD69</b></li>
     *   <li>Projection names (informative): <b>UTM</b>, <b>Brazil Polyconic</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testSAD69() throws FactoryException {
        important       = true;
        name            = "SAD69";
        projectionNames = new String[] {"UTM", "Brazil Polyconic"};
        datumCode       = 6618;
        createAndVerifyProjectedCRS(29101);
        createAndVerifyProjectedCRS(29194);
    }

    /**
     * Tests “Schwarzeck”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>29371</b>, <b>29333</b></li>
     *   <li>Geographic CRS name: <b>Schwarzeck</b></li>
     *   <li>Projection names (informative): <b>Lo/22 zones</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testSchwarzeck() throws FactoryException {
        important         = true;
        name              = "Schwarzeck";
        projectionNames   = new String[] {"Lo/22 zones", "UTM"};
        datumCode         = 6293;
        isWestOrientated  = true;
        isSouthOrientated = true;
        createAndVerifyProjectedCRS(29371);
        isWestOrientated  = false;
        isSouthOrientated = false;
        createAndVerifyProjectedCRS(29333);
    }

    /**
     * Tests “SIRGAS 1995”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>31997</b></li>
     *   <li>Geographic CRS name: <b>SIRGAS 1995</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testSIRGAS1995() throws FactoryException {
        important       = true;
        name            = "SIRGAS 1995";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6170;
        createAndVerifyProjectedCRS(31997);
    }

    /**
     * Tests “SIRGAS 2000”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>31982</b></li>
     *   <li>Geographic CRS name: <b>SIRGAS 2000</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testSIRGAS2000() throws FactoryException {
        important       = true;
        name            = "SIRGAS 2000";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6674;
        createAndVerifyProjectedCRS(31982);
    }

    /**
     * Tests “Tananarive”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>29738</b></li>
     *   <li>Geographic CRS name: <b>Tananarive</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testTananarive() throws FactoryException {
        important       = true;
        name            = "Tananarive";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6297;
        createAndVerifyProjectedCRS(29738);
    }

    /**
     * Tests “Tananarive (Paris)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>29701</b>, <b>29702</b></li>
     *   <li>Geographic CRS name: <b>Tananarive (Paris)</b></li>
     *   <li>Projection names (informative): <b>Laborde</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testTananarive_Paris() throws FactoryException {
        important       = true;
        name            = "Tananarive (Paris)";
        projectionNames = new String[] {"Laborde"};
        datumCode       = 6810;
        createAndVerifyProjectedCRS(29701);
        createAndVerifyProjectedCRS(29702);
    }

    /**
     * Tests “TC(1948)”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>30339</b></li>
     *   <li>Geographic CRS name: <b>TC(1948)</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testTC_1948() throws FactoryException {
        important       = true;
        name            = "TC(1948)";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6303;
        createAndVerifyProjectedCRS(30339);
    }

    /**
     * Tests “Timbalai 1948”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>29850</b>, <b>29871</b>, <b>29872</b>, <b>29873</b></li>
     *   <li>Geographic CRS name: <b>Timbalai 1948</b></li>
     *   <li>Projection names (informative): <b>RSO</b>, <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testTimbalai() throws FactoryException {
        important       = true;
        name            = "Timbalai 1948";
        projectionNames = new String[] {"RSO", "UTM"};
        datumCode       = 6298;
        createAndVerifyProjectedCRS(29850);
        createAndVerifyProjectedCRS(29871);
        createAndVerifyProjectedCRS(29872);
        createAndVerifyProjectedCRS(29873);
    }

    /**
     * Tests “Trinidad 1903”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>30200</b>, <b>2314</b></li>
     *   <li>Geographic CRS name: <b>Trinidad 1903</b></li>
     *   <li>Projection names (informative): <b>Cassini</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testTrinidad() throws FactoryException {
        important       = true;
        name            = "Trinidad 1903";
        projectionNames = new String[] {"Cassini"};
        datumCode       = 6302;
        createAndVerifyProjectedCRS(30200);
        createAndVerifyProjectedCRS(2314);
    }

    /**
     * Tests “WGS 72BE”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>32488</b></li>
     *   <li>Geographic CRS name: <b>WGS 72BE</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testWGS72BE() throws FactoryException {
        important       = true;
        name            = "WGS 72BE";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6324;
        createAndVerifyProjectedCRS(32488);
    }

    /**
     * Tests “WGS 84”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>3832</b>, <b>32601</b>, <b>32602</b>, <b>32603</b>, <b>32604</b>, <b>32605</b>,
     *       <b>32606</b>, <b>32607</b>, <b>32608</b>, <b>32609</b>, <b>32610</b>, <i>…110 more</i></li>
     *   <li>Geographic CRS name: <b>WGS 84</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Specific usage / Remarks: <b>Check axes abbreviations</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testWGS84() throws FactoryException {
        important       = true;
        name            = "WGS 84";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6326;
        createAndVerifyProjectedCRS(3832);
        for (int code = 32601; code <= 32660; code++) {    // Loop over 60 codes
            createAndVerifyProjectedCRS(code);
        }
        for (int code = 32701; code <= 32760; code++) {    // Loop over 60 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “Xian 1980”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2338</b>, <b>2339</b>, <b>2340</b>, <b>2341</b>, <b>2342</b>, <b>2343</b>, <b>2344</b>, <b>2345</b>, <b>2346</b>, <b>2347</b>, <i>…1 more</i></li>
     *   <li>Geographic CRS name: <b>Xian 1980</b></li>
     *   <li>Projection names (informative): <b>6-degree Gauss-Kruger</b></li>
     *   <li>Specific usage / Remarks: <b>Check axes order and abbreviations</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testXian() throws FactoryException {
        important        = true;
        name             = "Xian 1980";
        projectionNames  = new String[] {"6-degree Gauss-Kruger"};
        datumCode        = 6610;
        isNorthAxisFirst = true;
        for (int code = 2338; code <= 2348; code++) {    // Loop over 11 codes
            createAndVerifyProjectedCRS(code);
        }
    }

    /**
     * Tests “Yemen NGN96”-based projected CRS creation from the factory.
     *
     * <ul>
     *   <li>Projected CRS codes: <b>2089</b>, <b>2090</b></li>
     *   <li>Geographic CRS name: <b>Yemen NGN96</b></li>
     *   <li>Projection names (informative): <b>UTM</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the projected CRS from the EPSG code.
     */
    @Test
    public void testYemen() throws FactoryException {
        important       = true;
        name            = "Yemen NGN96";
        projectionNames = new String[] {"UTM"};
        datumCode       = 6163;
        createAndVerifyProjectedCRS(2089);
        createAndVerifyProjectedCRS(2090);
    }
}
