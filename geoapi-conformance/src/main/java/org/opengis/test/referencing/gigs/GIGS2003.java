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
import javax.measure.Unit;
import javax.measure.quantity.Angle;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.test.Configuration;
import org.opengis.test.FactoryFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.junit.Assert.*;


/**
 * Verifies reference prime meridians bundled with the geoscience software.
 *
 * <table class="gigs">
 * <caption>Test description</caption>
 * <tr>
 *   <th>Test method:</th>
 *   <td>Compare prime meridian definitions included in the software against the EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_2003_libPrimeMeridian.csv">{@code GIGS_2003_libPrimeMeridian.csv}</a>
 *       and EPSG Dataset.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link DatumAuthorityFactory#createPrimeMeridian(String)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>Prime meridian definitions bundled with the software should have the same name and Greenwich Longitude
 *       as in the EPSG Dataset. Equivalent alternative units are acceptable but should be reported.
 *       The values of the Greenwich Longitude should be correct to at least 7 decimal places (of degrees or grads).
 *       Meridians missing from the software or at variance with those in the EPSG Dataset should be reported.</td>
 * </tr></table>
 *
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.gigs.GIGS2003;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS2003 {
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
public strictfp class GIGS2003 extends AuthorityFactoryTestCase<PrimeMeridian> {
    /**
     * The expected Greenwich longitude in decimal degrees.
     */
    public double greenwichLongitude;

    /**
     * The prime meridian created by the factory,
     * or {@code null} if not yet created or if the prime meridian creation failed.
     *
     * @see #datumAuthorityFactory
     */
    private PrimeMeridian primeMeridian;

    /**
     * Factory to use for building {@link PrimeMeridian} instances, or {@code null} if none.
     * This is the factory used by the {@link #getIdentifiedObject()} method.
     */
    protected final DatumAuthorityFactory datumAuthorityFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead of
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS2003} constructor.
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
     * @param datumFactory  factory for creating {@link PrimeMeridian} instances.
     */
    public GIGS2003(final DatumAuthorityFactory datumFactory) {
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
     * Returns the prime meridian instance to be tested. When this method is invoked for the first time, it creates the
     * prime meridian to test by invoking the {@link DatumAuthorityFactory#createPrimeMeridian(String)} method with the
     * current {@link #code} value in argument. The created object is then cached and returned in all subsequent
     * invocations of this method.
     *
     * @return the prime meridian instance to test.
     * @throws FactoryException if an error occurred while creating the prime meridian instance.
     */
    @Override
    public PrimeMeridian getIdentifiedObject() throws FactoryException {
        if (primeMeridian == null) {
            assumeNotNull(datumAuthorityFactory);
            try {
                primeMeridian = datumAuthorityFactory.createPrimeMeridian(String.valueOf(code));
            } catch (NoSuchAuthorityCodeException e) {
                unsupportedCode(PrimeMeridian.class, code);
                throw e;
            }
        }
        return primeMeridian;
    }

    /**
     * Verifies the properties of the prime meridian given by {@link #getIdentifiedObject()}.
     */
    private void verifyPrimeMeridian() throws FactoryException {
        final PrimeMeridian pm = getIdentifiedObject();
        assertNotNull("PrimeMeridian", pm);
        validators.validate(pm);

        // Prime meridian identifiers.
        assertContainsCode("PrimeMeridian.getIdentifiers()", "EPSG", code, pm.getIdentifiers());

        // Prime meridian name.
        if (isStandardNameSupported) {
            configurationTip = Configuration.Key.isStandardNameSupported;
            assertEquals("PrimeMeridian.getName()", name, getVerifiableName(pm));
            configurationTip = null;
        }

        // Prime meridian alias.
        if (isStandardAliasSupported) {
            configurationTip = Configuration.Key.isStandardAliasSupported;
            assertContainsAll("PrimeMeridian.getAlias()", aliases, pm.getAlias());
            configurationTip = null;
        }
        /*
         * Before to compare the Greenwich longitude, convert the expected angular value from decimal degrees
         * to the units actually used by the implementation. We do the conversion that way rather than the
         * opposite way in order to have a more appropriate error message in case of failure.
         */
        final Unit<Angle> unit = pm.getAngularUnit();
        double longitude = greenwichLongitude;
        final Unit<Angle> degree = units.degree();
        if (unit != null && !unit.equals(degree)) {
            longitude = degree.getConverterTo(unit).convert(longitude);
        }
        assertEquals("PrimeMeridian.getGreenwichLongitude()", longitude,
                pm.getGreenwichLongitude(), ANGULAR_TOLERANCE);
    }

    /**
     * Tests “Greenwich” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8901</b></li>
     *   <li>EPSG prime meridian name: <b>Greenwich</b></li>
     *   <li>Greenwich longitude: <b>0°</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     *
     * @see GIGS3003#testGreenwich()
     */
    @Test
    public void testGreenwich() throws FactoryException {
        important          = true;
        code               = 8901;
        name               = "Greenwich";
        aliases            = NONE;
        greenwichLongitude = 0.0;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Ferro” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8909</b></li>
     *   <li>EPSG prime meridian name: <b>Ferro</b></li>
     *   <li>Greenwich longitude: <b>-17°40′</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testFerro() throws FactoryException {
        important          = true;
        code               = 8909;
        name               = "Ferro";
        aliases            = NONE;
        greenwichLongitude = -17.666666666666668;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Jakarta” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8908</b></li>
     *   <li>EPSG prime meridian name: <b>Jakarta</b></li>
     *   <li>Greenwich longitude: <b>106°48′27.79″</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     *
     * @see GIGS3003#testJakarta()
     */
    @Test
    public void testJakarta() throws FactoryException {
        important          = true;
        code               = 8908;
        name               = "Jakarta";
        aliases            = NONE;
        greenwichLongitude = 106.80771944444444;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Paris” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8903</b></li>
     *   <li>EPSG prime meridian name: <b>Paris</b></li>
     *   <li>Greenwich longitude: <b>2.5969213</b></li>
     *   <li>Specific usage / Remarks: <b>Equivalent to 2°20'14.025\".</b></li>
     *   <li>Particularly important to E&amp;P industry.</li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     *
     * @see GIGS3003#testParis()
     */
    @Test
    public void testParis() throws FactoryException {
        important          = true;
        code               = 8903;
        name               = "Paris";
        aliases            = NONE;
        greenwichLongitude = 2.33722917;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Athens” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8912</b></li>
     *   <li>EPSG prime meridian name: <b>Athens</b></li>
     *   <li>Greenwich longitude: <b>23°42′58.815″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testAthens() throws FactoryException {
        code               = 8912;
        name               = "Athens";
        aliases            = NONE;
        greenwichLongitude = 23.7163375;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Bern” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8907</b></li>
     *   <li>EPSG prime meridian name: <b>Bern</b></li>
     *   <li>Greenwich longitude: <b>7°26′22.5″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testBern() throws FactoryException {
        code               = 8907;
        name               = "Bern";
        aliases            = NONE;
        greenwichLongitude = 7.439583333333333;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Bogota” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8904</b></li>
     *   <li>EPSG prime meridian name: <b>Bogota</b></li>
     *   <li>Greenwich longitude: <b>-74°04′51.3″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     *
     * @see GIGS3003#testBogota()
     */
    @Test
    public void testBogota() throws FactoryException {
        code               = 8904;
        name               = "Bogota";
        aliases            = NONE;
        greenwichLongitude = -74.08091666666667;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Brussels” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8910</b></li>
     *   <li>EPSG prime meridian name: <b>Brussels</b></li>
     *   <li>Greenwich longitude: <b>4°22′04.71″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testBrussels() throws FactoryException {
        code               = 8910;
        name               = "Brussels";
        aliases            = NONE;
        greenwichLongitude = 4.367975;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Lisbon” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8902</b></li>
     *   <li>EPSG prime meridian name: <b>Lisbon</b></li>
     *   <li>Greenwich longitude: <b>-9°07′54.862″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testLisbon() throws FactoryException {
        code               = 8902;
        name               = "Lisbon";
        aliases            = NONE;
        greenwichLongitude = -9.13190611111111;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Madrid” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8905</b></li>
     *   <li>EPSG prime meridian name: <b>Madrid</b></li>
     *   <li>Greenwich longitude: <b>-3°41′14.55″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testMadrid() throws FactoryException {
        code               = 8905;
        name               = "Madrid";
        aliases            = NONE;
        greenwichLongitude = -3.687375;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Oslo” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8913</b></li>
     *   <li>EPSG prime meridian name: <b>Oslo</b></li>
     *   <li>Alias(es) given by EPSG: <b>Kristiania</b></li>
     *   <li>Greenwich longitude: <b>10°43′22.5″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testOslo() throws FactoryException {
        code               = 8913;
        name               = "Oslo";
        aliases            = new String[] {"Kristiania"};
        greenwichLongitude = 10.722916666666666;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Paris RGS” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8914</b></li>
     *   <li>EPSG prime meridian name: <b>Paris RGS</b></li>
     *   <li>Greenwich longitude: <b>2°20′13.95″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testParisRGS() throws FactoryException {
        code               = 8914;
        name               = "Paris RGS";
        aliases            = NONE;
        greenwichLongitude = 2.3372083333333333;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Rome” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8906</b></li>
     *   <li>EPSG prime meridian name: <b>Rome</b></li>
     *   <li>Greenwich longitude: <b>12°27′08.4″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testRome() throws FactoryException {
        code               = 8906;
        name               = "Rome";
        aliases            = NONE;
        greenwichLongitude = 12.452333333333334;
        verifyPrimeMeridian();
    }

    /**
     * Tests “Stockholm” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>EPSG prime meridian code: <b>8911</b></li>
     *   <li>EPSG prime meridian name: <b>Stockholm</b></li>
     *   <li>Greenwich longitude: <b>18°03′29.8″</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the EPSG code.
     */
    @Test
    public void testStockholm() throws FactoryException {
        code               = 8911;
        name               = "Stockholm";
        aliases            = NONE;
        greenwichLongitude = 18.05827777777778;
        verifyPrimeMeridian();
    }
}
