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
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
import org.opengis.test.Configuration;
import org.opengis.test.FactoryFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;


/**
 * Verifies reference map projections bundled with the geoscience software.
 *
 * <table class="gigs">
 * <caption>Test description</caption>
 * <tr>
 *   <th>Test method:</th>
 *   <td>Compare map projection definitions included in the software against the EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_2005_libProjection.csv">{@code GIGS_2005_libProjection.csv}</a>
 *       and EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link CoordinateOperationAuthorityFactory#createCoordinateOperation(String)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>Map projection definitions bundled with the software should have the same name, method name,
 *       defining parameters and parameter values as in the EPSG Dataset. The values of the parameters
 *       should be correct to at least 10 significant figures. Map projections missing from the software
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
 *import org.opengis.test.referencing.gigs.GIGS2005;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS2005 {
 *    public MyTest() {
 *        super(new MyCoordinateOperationAuthorityFactory());
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
public strictfp class GIGS2005 extends AuthorityFactoryTestCase<Conversion> {
    /**
     * The name of the expected operation method.
     */
    public String methodName;

    /**
     * The coordinate conversion created by the factory,
     * or {@code null} if not yet created or if the conversion creation failed.
     *
     * @see #copAuthorityFactory
     */
    private Conversion conversion;

    /**
     * Factory to use for building {@link Conversion} instances, or {@code null} if none.
     * This is the factory used by the {@link #getIdentifiedObject()} method.
     */
    protected final CoordinateOperationAuthorityFactory copAuthorityFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead of
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS2005} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(FactoryFilter.ByAuthority.EPSG, CoordinateOperationAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param copFactory  factory for creating {@link CoordinateOperation} instances.
     */
    public GIGS2005(final CoordinateOperationAuthorityFactory copFactory) {
        super(copFactory);
        copAuthorityFactory = copFactory;
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
     *       <li>{@link #copAuthorityFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.copAuthorityFactory, copAuthorityFactory));
        return op;
    }

    /**
     * Returns the coordinate operation instance to be tested. When this method is invoked for the first time, it creates
     * the operation to test by invoking the {@link CoordinateOperationAuthorityFactory#createCoordinateOperation(String)}
     * method with the current {@link #code} value in argument. The created object is then cached and returned in all
     * subsequent invocations of this method.
     *
     * @return the coordinate operation instance to test.
     * @throws FactoryException if an error occurred while creating the coordinate operation instance.
     */
    @Override
    public Conversion getIdentifiedObject() throws FactoryException {
        if (conversion == null) {
            assumeNotNull(copAuthorityFactory);
            final String codeAsString = String.valueOf(code);
            final CoordinateOperation operation;
            try {
                operation = copAuthorityFactory.createCoordinateOperation(codeAsString);
            } catch (NoSuchIdentifierException e) {
                /*
                 * Relaxed the exception type from NoSuchAuthorityCodeException because CoordinateOperation creation
                 * will typically use MathTransformFactory under the hood, which throws NoSuchIdentifierException for
                 * non-implemented operation methods (may be identified by their name rather than EPSG code).
                 */
                unsupportedCode(Conversion.class, code);
                throw e;
            }
            if (operation != null) {  // For consistency with the behavior in other classes.
                assertInstanceOf(codeAsString, Conversion.class, operation);
                conversion = (Conversion) operation;
            }
        }
        return conversion;
    }

    /**
     * Verifies the properties of the conversion given by {@link #getIdentifiedObject()}.
     */
    private void createAndVerifyProjection(final int code) throws FactoryException {
        this.code = code;
        conversion = null;              // For forcing the fetch of a new operation.

        final Conversion conversion = getIdentifiedObject();
        assertNotNull("Conversion", conversion);
        validators.validate(conversion);

        // Map projection identifier.
        assertContainsCode("Conversion.getIdentifiers()", "EPSG", code, conversion.getIdentifiers());

        // Map projection name.
        if (isStandardNameSupported) {
            configurationTip = Configuration.Key.isStandardNameSupported;
            assertEquals("Conversion.getMethod().getName()", methodName, getVerifiableName(conversion.getMethod()));
            configurationTip = null;
        }
    }

    /**
     * Tests “UTM” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16001</b>, <b>16002</b>, <b>16003</b>, <b>16004</b>, <b>16005</b>,
     *       <b>16006</b>, <b>16007</b>, <b>16008</b>, <b>16009</b>, <b>16010</b>, <i>…110 more</i></li>
     *   <li>EPSG coordinate operation name: <b>UTM</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Specific usage / Remarks: <b>All 120 zones</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testUTM() throws FactoryException {
        important  = true;
        name       = "UTM";
        methodName = "Transverse Mercator";
        for (int code = 16001; code <= 16060; code++) {    // Loop over 60 codes
            createAndVerifyProjection(code);
        }
        for (int code = 16101; code <= 16160; code++) {    // Loop over 60 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “6-degree Gauss-Kruger + zone prefix” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16201</b>, <b>16202</b>, <b>16203</b>, <b>16204</b>, <b>16205</b>,
     *       <b>16206</b>, <b>16207</b>, <b>16208</b>, <b>16209</b>, <b>16210</b>, <i>…50 more</i></li>
     *   <li>EPSG coordinate operation name: <b>6-degree Gauss-Kruger</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Specific usage / Remarks: <b>With zone prefix in easting.</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void test6DegreeGaussKrugerWithZonePrefix() throws FactoryException {
        important  = true;
        name       = "6-degree Gauss-Kruger";
        methodName = "Transverse Mercator";
        for (int code = 16201; code <= 16260; code++) {    // Loop over 60 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “6-degree Gauss-Kruger” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16301</b>, <b>16302</b>, <b>16303</b>, <b>16304</b>, <b>16305</b>,
     *       <b>16306</b>, <b>16307</b>, <b>16308</b>, <b>16309</b>, <b>16310</b>, <i>…50 more</i></li>
     *   <li>EPSG coordinate operation name: <b>6-degree Gauss-Kruger</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Specific usage / Remarks: <b>Without zone prefix in easting.</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void test6DegreeGaussKruger() throws FactoryException {
        important  = true;
        name       = "6-degree Gauss-Kruger";
        methodName = "Transverse Mercator";
        for (int code = 16301; code <= 16360; code++) {    // Loop over 60 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “3-degree Gauss-Kruger + zone prefix” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16261</b>, <b>16262</b>, <b>16263</b>, <b>16264</b>, <b>16265</b>,
     *       <b>16266</b>, <b>16267</b>, <b>16268</b>, <b>16269</b>, <b>16270</b>, <i>…54 more</i></li>
     *   <li>EPSG coordinate operation name: <b>3-degree Gauss-Kruger</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Specific usage / Remarks: <b>With zone prefix in easting.</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void test3DegreeGaussKrugerWithZonePrefix() throws FactoryException {
        important  = true;
        name       = "3-degree Gauss-Kruger";
        methodName = "Transverse Mercator";
        for (int code = 16261; code <= 16299; code++) {    // Loop over 39 codes
            createAndVerifyProjection(code);
        }
        for (int code = 16070; code <= 16089; code++) {    // Loop over 20 codes
            createAndVerifyProjection(code);
        }
        createAndVerifyProjection(16099);
        for (int code = 16091; code <= 16094; code++) {    // Loop over 4 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “3-degree Gauss-Kruger” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16362</b>, <b>16364</b>, <b>16366</b>, <b>16368</b>, <b>16370</b>,
     *       <b>16372</b>, <b>16374</b>, <b>16376</b>, <b>16378</b>, <b>16380</b>, <i>…22 more</i></li>
     *   <li>EPSG coordinate operation name: <b>3-degree Gauss-Kruger</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Specific usage / Remarks: <b>Without zone prefix in easting.</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void test3DegreeGaussKruger() throws FactoryException {
        important  = true;
        name       = "3-degree Gauss-Kruger";
        methodName = "Transverse Mercator";
        for (int code = 16362; code <= 16398; code += 2) {    // Loop over 19 codes
            createAndVerifyProjection(code);
        }
        for (int code = 16170; code <= 16194; code += 2) {    // Loop over 13 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “Aramco Lambert” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19977</b></li>
     *   <li>EPSG coordinate operation name: <b>Aramco Lambert</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Conformal (2SP)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testAramcoLambert() throws FactoryException {
        important  = true;
        name       = "Aramco Lambert";
        methodName = "Lambert Conic Conformal (2SP)";
        createAndVerifyProjection(19977);
    }

    /**
     * Tests “Argentina zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18031</b>, <b>18032</b>, <b>18033</b>, <b>18034</b>, <b>18035</b>, <b>18036</b>, <b>18037</b></li>
     *   <li>EPSG coordinate operation name: <b>Argentina zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testArgentinaZones() throws FactoryException {
        important  = true;
        name       = "Argentina zones";
        methodName = "Transverse Mercator";
        for (int code = 18031; code <= 18037; code++) {    // Loop over 7 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “Australian Map Grid zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>17448</b> (see note), <b>17449</b>, <b>17450</b>, <b>17451</b>, <b>17452</b>,
     *       <b>17453</b>, <b>17454</b>, <b>17455</b>, <b>17456</b>, <b>17457</b>, <b>17458</b></li>
     *   <li>EPSG coordinate operation name: <b>Australian Map Grid zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * <b>Note:</b> EPSG:17448 is tested only if {@link #isDeprecatedObjectCreationSupported} is {@code true}.
     * Raison is: Falls outside EEZ area.
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testAustralianMapGridZones() throws FactoryException {
        important  = true;
        name       = "Australian Map Grid zones";
        methodName = "Transverse Mercator";
        for (int code = 17449; code <= 17458; code++) {    // Loop over 10 codes
            createAndVerifyProjection(code);
        }
        if (isDeprecatedObjectCreationSupported) {
            createAndVerifyProjection(17448);
        }
    }

    /**
     * Tests “BLM zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>15914</b>, <b>15915</b>, <b>15916</b>, <b>15917</b></li>
     *   <li>EPSG coordinate operation name: <b>BLM zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Specific usage / Remarks: <b>UTM in ftUS</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testBLMZones() throws FactoryException {
        important  = true;
        name       = "BLM zones";
        methodName = "Transverse Mercator";
        for (int code = 15914; code <= 15917; code++) {    // Loop over 4 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “British National Grid” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19916</b></li>
     *   <li>EPSG coordinate operation name: <b>British National Grid</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testBritishNationalGrid() throws FactoryException {
        important  = true;
        name       = "British National Grid";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(19916);
    }

    /**
     * Tests “Brazil Polyconic” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19941</b></li>
     *   <li>EPSG coordinate operation name: <b>Brazil Polyconic</b></li>
     *   <li>Coordinate operation method: <b>American Polyconic</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testBrazilPolyconic() throws FactoryException {
        important  = true;
        name       = "Brazil Polyconic";
        methodName = "American Polyconic";
        createAndVerifyProjection(19941);
    }

    /**
     * Tests “Colombia zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18051</b>, <b>18052</b>, <b>18053</b>, <b>18054</b>, <b>18055</b>,
     *       <b>18056</b>, <b>18057</b>, <b>18058</b>, <b>18059</b></li>
     *   <li>EPSG coordinate operation name: <b>Colombia zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testColombiaZones() throws FactoryException {
        important  = true;
        name       = "Colombia zones";
        methodName = "Transverse Mercator";
        for (int code = 18051; code <= 18059; code++) {    // Loop over 9 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “Egypt belts” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18071</b>, <b>18072</b>, <b>18073</b>, <b>18074</b></li>
     *   <li>EPSG coordinate operation name: <b>Egypt belts</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testEgyptBelts() throws FactoryException {
        important  = true;
        name       = "Egypt belts";
        methodName = "Transverse Mercator";
        for (int code = 18071; code <= 18074; code++) {    // Loop over 4 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “EOV” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19931</b></li>
     *   <li>EPSG coordinate operation name: <b>EOV</b></li>
     *   <li>Coordinate operation method: <b>Hotine Oblique Mercator (variant B)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testEOV() throws FactoryException {
        important  = true;
        name       = "EOV";
        methodName = "Hotine Oblique Mercator (variant B)";
        createAndVerifyProjection(19931);
    }

    /**
     * Tests “France mainland zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18081</b>, <b>18082</b>, <b>18083</b></li>
     *   <li>EPSG coordinate operation name: <b>France mainland zones</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Conformal (1SP)</b></li>
     *   <li>Specific usage / Remarks: <b>Use grads</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testFranceMainlandZones() throws FactoryException {
        important  = true;
        name       = "France mainland zones";
        methodName = "Lambert Conic Conformal (1SP)";
        createAndVerifyProjection(18081);
        createAndVerifyProjection(18082);
        createAndVerifyProjection(18083);
    }

    /**
     * Tests “Lambert-93” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18085</b></li>
     *   <li>EPSG coordinate operation name: <b>Lambert-93</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Conformal (2SP)</b></li>
     *   <li>Specific usage / Remarks: <b>Use grads</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testLambert93() throws FactoryException {
        important  = true;
        name       = "Lambert-93";
        methodName = "Lambert Conic Conformal (2SP)";
        createAndVerifyProjection(18085);
    }

    /**
     * Tests “Ghana National Grid” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19959</b></li>
     *   <li>EPSG coordinate operation name: <b>Ghana National Grid</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testGhanaNationalGrid() throws FactoryException {
        important  = true;
        name       = "Ghana National Grid";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(19959);
    }

    /**
     * Tests “India zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18231</b>, <b>18232</b>, <b>18233</b>, <b>18234</b>, <b>18235</b>,
     *       <b>18236</b>, <b>18237</b>, <b>18238</b></li>
     *   <li>EPSG coordinate operation name: <b>India zones</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Conformal (1SP)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testIndiaZones() throws FactoryException {
        important  = true;
        name       = "India zones";
        methodName = "Lambert Conic Conformal (1SP)";
        for (int code = 18231; code <= 18238; code++) {    // Loop over 8 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “Iraq zone” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19906</b></li>
     *   <li>EPSG coordinate operation name: <b>Iraq zone</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Conformal (1SP)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testIraqZone() throws FactoryException {
        important  = true;
        name       = "Iraq zone";
        methodName = "Lambert Conic Conformal (1SP)";
        createAndVerifyProjection(19906);
    }

    /**
     * Tests “Italy zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18121</b>, <b>18122</b></li>
     *   <li>EPSG coordinate operation name: <b>Italy zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testItalyZones() throws FactoryException {
        important  = true;
        name       = "Italy zones";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(18121);
        createAndVerifyProjection(18122);
    }

    /**
     * Tests “Laborde” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19911</b>, <b>19861</b></li>
     *   <li>EPSG coordinate operation name: <b>Laborde Grid approximation</b>, <b>Laborde Grid</b></li>
     *   <li>Coordinate operation method: <b>Hotine Oblique Mercator (variant B)</b>, <b>Laborde Oblique Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testLaborde() throws FactoryException {
        important  = true;
        name       = "Laborde Grid approximation";
        methodName = "Hotine Oblique Mercator (variant B)";
        createAndVerifyProjection(19911);

        name       = "Laborde Grid";
        methodName = "Laborde Oblique Mercator";
        createAndVerifyProjection(19861);
    }

    /**
     * Tests “Levant zone” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19940</b></li>
     *   <li>EPSG coordinate operation name: <b>Levant zone</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Near-Conformal</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testLevantZone() throws FactoryException {
        important  = true;
        name       = "Levant zone";
        methodName = "Lambert Conic Near-Conformal";
        createAndVerifyProjection(19940);
    }

    /**
     * Tests “Libya zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18240</b>, <b>18241</b>, <b>18242</b>, <b>18243</b>, <b>18244</b>,
     *       <b>18245</b>, <b>18246</b>, <b>18247</b>, <b>18248</b>, <b>18310</b>, <i>…9 more</i></li>
     *   <li>EPSG coordinate operation name: <b>Libya zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testLibyaZones() throws FactoryException {
        important  = true;
        name       = "Libya zones";
        methodName = "Transverse Mercator";
        for (int code = 18240; code <= 18248; code++) {    // Loop over 9 codes
            createAndVerifyProjection(code);
        }
        for (int code = 18310; code <= 18319; code++) {    // Loop over 10 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “SW Africa Survey Grid” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>17611</b></li>
     *   <li>EPSG coordinate operation name: <b>SW Africa Survey Grid</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator (South Orientated)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testSWAfricaSurveyGrid() throws FactoryException {
        important  = true;
        name       = "SW Africa Survey Grid";
        methodName = "Transverse Mercator (South Orientated)";
        createAndVerifyProjection(17611);
    }

    /**
     * Tests “NEIEZ” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19905</b></li>
     *   <li>EPSG coordinate operation name: <b>NEIEZ</b></li>
     *   <li>Coordinate operation method: <b>Mercator (variant A)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testNEIEZ() throws FactoryException {
        important  = true;
        name       = "NEIEZ";
        methodName = "Mercator (variant A)";
        createAndVerifyProjection(19905);
    }

    /**
     * Tests “Nigeria belts” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18151</b>, <b>18152</b>, <b>18153</b></li>
     *   <li>EPSG coordinate operation name: <b>Nigeria belts</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testNigeriaBelts() throws FactoryException {
        important  = true;
        name       = "Nigeria belts";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(18151);
        createAndVerifyProjection(18152);
        createAndVerifyProjection(18153);
    }

    /**
     * Tests “NZ TM zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18141</b>, <b>18142</b>, <b>19971</b></li>
     *   <li>EPSG coordinate operation name: <b>NZ TM zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testNZTMZones() throws FactoryException {
        important  = true;
        name       = "NZ TM zones";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(18141);
        createAndVerifyProjection(18142);
        createAndVerifyProjection(19971);
    }

    /**
     * Tests “NZMG” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19917</b></li>
     *   <li>EPSG coordinate operation name: <b>NZMG</b></li>
     *   <li>Coordinate operation method: <b>New Zealand Map Grid</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testNZMG() throws FactoryException {
        important  = true;
        name       = "NZMG";
        methodName = "New Zealand Map Grid";
        createAndVerifyProjection(19917);
    }

    /**
     * Tests “Peru zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18161</b>, <b>18162</b>, <b>18163</b></li>
     *   <li>EPSG coordinate operation name: <b>Peru zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testPeruZones() throws FactoryException {
        important  = true;
        name       = "Peru zones";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(18161);
        createAndVerifyProjection(18162);
        createAndVerifyProjection(18163);
    }

    /**
     * Tests “Philippine zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18171</b>, <b>18172</b>, <b>18173</b>, <b>18174</b>, <b>18175</b></li>
     *   <li>EPSG coordinate operation name: <b>Philippine zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testPhilippineZones() throws FactoryException {
        important  = true;
        name       = "Philippine zones";
        methodName = "Transverse Mercator";
        for (int code = 18171; code <= 18175; code++) {    // Loop over 5 codes
            createAndVerifyProjection(code);
        }
    }

    /**
     * Tests “Qatar Grid” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19953</b></li>
     *   <li>EPSG coordinate operation name: <b>Qatar Grid</b></li>
     *   <li>Coordinate operation method: <b>Cassini-Soldner</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testQatarGrid() throws FactoryException {
        important  = true;
        name       = "Qatar Grid";
        methodName = "Cassini-Soldner";
        createAndVerifyProjection(19953);
    }

    /**
     * Tests “Qatar National Grid” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19919</b></li>
     *   <li>EPSG coordinate operation name: <b>Qatar National Grid</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testQatarNationalGrid() throws FactoryException {
        important  = true;
        name       = "Qatar National Grid";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(19919);
    }

    /**
     * Tests “RD New” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19914</b></li>
     *   <li>EPSG coordinate operation name: <b>RD New</b></li>
     *   <li>Coordinate operation method: <b>Oblique Stereographic</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testRDNew() throws FactoryException {
        important  = true;
        name       = "RD New";
        methodName = "Oblique Stereographic";
        createAndVerifyProjection(19914);
    }

    /**
     * Tests “Malaysia RSO grids” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19956</b>, <b>19957</b>, <b>19958</b></li>
     *   <li>EPSG coordinate operation name: <b>Malaysia RSO grids</b></li>
     *   <li>Coordinate operation method: <b>Hotine Oblique Mercator (variant B)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testMalaysiaRSOGrids_VariantB() throws FactoryException {
        important  = true;
        name       = "Malaysia RSO grids";
        methodName = "Hotine Oblique Mercator (variant B)";
        createAndVerifyProjection(19956);
        createAndVerifyProjection(19957);
        createAndVerifyProjection(19958);
    }

    /**
     * Tests “Malaysia RSO grids” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19871</b>, <b>19872</b>, <b>19894</b>, <b>19895</b></li>
     *   <li>EPSG coordinate operation name: <b>Malaysia RSO grids</b></li>
     *   <li>Coordinate operation method: <b>Hotine Oblique Mercator (variant A)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testMalaysiaRSOGrids_VariantA() throws FactoryException {
        important  = true;
        name       = "Malaysia RSO grids";
        methodName = "Hotine Oblique Mercator (variant A)";
        createAndVerifyProjection(19871);
        createAndVerifyProjection(19872);
        createAndVerifyProjection(19894);
        createAndVerifyProjection(19895);
    }

    /**
     * Tests “US State Plane zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>15002</b>, <b>15003</b>, <b>15004</b>, <b>15005</b>, <b>15006</b>,
     *       <b>15007</b>, <b>15008</b>, <b>15009</b>, <b>15032</b>, <b>15033</b>, <i>…12 more</i></li>
     *   <li>EPSG coordinate operation name: <b>US State Plane zones</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Specific usage / Remarks: <b>SPCS27 and SPCS83. Check especially FE/FN units.
     *       Concentrate on states with E&amp;P interest: AK; CA; LA; MI; NM; OK; TX; WY</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testUSStatePlaneZones_TM() throws FactoryException {
        important  = true;
        name       = "US State Plane zones";
        methodName = "Transverse Mercator";
        for (int code = 15002; code <= 15009; code++) {    // Loop over 8 codes
            createAndVerifyProjection(code);
        }
        for (int code = 15032; code <= 15039; code++) {    // Loop over 8 codes
            createAndVerifyProjection(code);
        }
        createAndVerifyProjection(13001);
        createAndVerifyProjection(13031);
        createAndVerifyProjection(15339);
        createAndVerifyProjection(14903);
        createAndVerifyProjection(14933);
        createAndVerifyProjection(14937);
    }

    /**
     * Tests “US State Plane zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>10404</b>, <b>10405</b>, <b>10406</b>, <b>10408</b>, <b>10434</b>,
     *       <b>10435</b>, <b>10436</b>, <b>15310</b>, <b>15311</b>, <b>15312</b>, <i>…33 more</i></li>
     *   <li>EPSG coordinate operation name: <b>US State Plane zones</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Conformal (2SP)</b></li>
     *   <li>Specific usage / Remarks: <b>SPCS27 and SPCS83. Check especially FE/FN units.
     *       Concentrate on states with E&amp;P interest: AK; CA; LA; MI; NM; OK; TX; WY</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * <b>Note:</b> EPSG:12112 and 12113 are tested only if {@link #isDeprecatedObjectCreationSupported} is {@code true}.
     * Raison is: Method changed to accord with NGS practice.
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testUSStatePlaneZones_LCC() throws FactoryException {
        important  = true;
        name       = "US State Plane zones";
        methodName = "Lambert Conic Conformal (2SP)";
        createAndVerifyProjection(10404);
        createAndVerifyProjection(10405);
        createAndVerifyProjection(10406);
        createAndVerifyProjection(10408);
        createAndVerifyProjection(10434);
        createAndVerifyProjection(10435);
        createAndVerifyProjection(10436);
        createAndVerifyProjection(15310);
        createAndVerifyProjection(15311);
        createAndVerifyProjection(15312);
        createAndVerifyProjection(11701);
        createAndVerifyProjection(11702);
        createAndVerifyProjection(11731);
        createAndVerifyProjection(11732);
        createAndVerifyProjection(15391);
        createAndVerifyProjection(15392);
        createAndVerifyProjection(12142);
        createAndVerifyProjection(12143);
        createAndVerifyProjection(15334);
        createAndVerifyProjection(15335);
        createAndVerifyProjection(13501);
        createAndVerifyProjection(13502);
        createAndVerifyProjection(13531);
        createAndVerifyProjection(13532);
        createAndVerifyProjection(15349);
        createAndVerifyProjection(15350);
        for (int code = 14201; code <= 14205; code++) {    // Loop over 5 codes
            createAndVerifyProjection(code);
        }
        for (int code = 14231; code <= 14235; code++) {    // Loop over 5 codes
            createAndVerifyProjection(code);
        }
        for (int code = 15357; code <= 15361; code++) {    // Loop over 5 codes
            createAndVerifyProjection(code);
        }
        if (isDeprecatedObjectCreationSupported) {
            createAndVerifyProjection(12112);
            createAndVerifyProjection(12113);
        }
    }

    /**
     * Tests “Stereo33” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19927</b></li>
     *   <li>EPSG coordinate operation name: <b>Stereo33</b></li>
     *   <li>Coordinate operation method: <b>Oblique Stereographic</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testStereo33() throws FactoryException {
        important  = true;
        name       = "Stereo33";
        methodName = "Oblique Stereographic";
        createAndVerifyProjection(19927);
    }

    /**
     * Tests “Stereo70” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19926</b></li>
     *   <li>EPSG coordinate operation name: <b>Stereo70</b></li>
     *   <li>Coordinate operation method: <b>Oblique Stereographic</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testStereo70() throws FactoryException {
        important  = true;
        name       = "Stereo70";
        methodName = "Oblique Stereographic";
        createAndVerifyProjection(19926);
    }

    /**
     * Tests “Syria Lambert” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19948</b></li>
     *   <li>EPSG coordinate operation name: <b>Syria Lambert</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Conformal (1SP)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testSyriaLambert() throws FactoryException {
        important  = true;
        name       = "Syria Lambert";
        methodName = "Lambert Conic Conformal (1SP)";
        createAndVerifyProjection(19948);
    }

    /**
     * Tests “TM 0 N” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16400</b></li>
     *   <li>EPSG coordinate operation name: <b>TM 0 N</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testTM0N() throws FactoryException {
        important  = true;
        name       = "TM 0 N";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(16400);
    }

    /**
     * Tests “TM 1 NW” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>17001</b></li>
     *   <li>EPSG coordinate operation name: <b>TM 1 NW</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testTM1NW() throws FactoryException {
        important  = true;
        name       = "TM 1 NW";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(17001);
    }

    /**
     * Tests “TM 109 SE” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16709</b></li>
     *   <li>EPSG coordinate operation name: <b>TM 109 SE</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testTM109SE() throws FactoryException {
        important  = true;
        name       = "TM 109 SE";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(16709);
    }

    /**
     * Tests “TM 11.30 SE” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16611</b></li>
     *   <li>EPSG coordinate operation name: <b>TM 11.30 SE</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testTM1130SE() throws FactoryException {
        important  = true;
        name       = "TM 11.30 SE";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(16611);
    }

    /**
     * Tests “TM 12 SE” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16612</b></li>
     *   <li>EPSG coordinate operation name: <b>TM 12 SE</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testTM12SE() throws FactoryException {
        important  = true;
        name       = "TM 12 SE";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(16612);
    }

    /**
     * Tests “TM 5 NE” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>16405</b></li>
     *   <li>EPSG coordinate operation name: <b>TM 5 NE</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testTM5NE() throws FactoryException {
        important  = true;
        name       = "TM 5 NE";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(16405);
    }

    /**
     * Tests “TM 5 NW” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>17005</b></li>
     *   <li>EPSG coordinate operation name: <b>TM 5 NW</b></li>
     *   <li>Coordinate operation method: <b>Transverse Mercator</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testTM5NW() throws FactoryException {
        important  = true;
        name       = "TM 5 NW";
        methodName = "Transverse Mercator";
        createAndVerifyProjection(17005);
    }

    /**
     * Tests “Trinidad grid” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>19925</b>, <b>19975</b></li>
     *   <li>EPSG coordinate operation name: <b>Trinidad grid</b></li>
     *   <li>Coordinate operation method: <b>Cassini-Soldner</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testTrinidadGrid() throws FactoryException {
        important  = true;
        name       = "Trinidad grid";
        methodName = "Cassini-Soldner";
        createAndVerifyProjection(19925);
        createAndVerifyProjection(19975);
    }

    /**
     * Tests “Tunisia zones” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18181</b>, <b>18182</b></li>
     *   <li>EPSG coordinate operation name: <b>Tunisia zones</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Conformal (1SP)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testTunisiaZones() throws FactoryException {
        important  = true;
        name       = "Tunisia zones";
        methodName = "Lambert Conic Conformal (1SP)";
        createAndVerifyProjection(18181);
        createAndVerifyProjection(18182);
    }

    /**
     * Tests “Voirol Unifie” coordinate operation creation from the factory.
     *
     * <ul>
     *   <li>EPSG coordinate operation codes: <b>18021</b>, <b>18022</b></li>
     *   <li>EPSG coordinate operation name: <b>Voirol Unifie</b></li>
     *   <li>Coordinate operation method: <b>Lambert Conic Conformal (1SP)</b></li>
     *   <li>Specific usage / Remarks: <b>Check not old parameters (codes 18011-18012)</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the coordinate operation from the EPSG code.
     */
    @Test
    public void testVoirolUnifie() throws FactoryException {
        important  = true;
        name       = "Voirol Unifie";
        methodName = "Lambert Conic Conformal (1SP)";
        createAndVerifyProjection(18021);
        createAndVerifyProjection(18022);
    }
}
