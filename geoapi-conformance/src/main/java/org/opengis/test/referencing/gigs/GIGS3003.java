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
import javax.measure.quantity.Angle;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.referencing.datum.DatumFactory;
import org.opengis.test.Configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.junit.Assert.*;


/**
 * Verifies that the software allows correct definition of a user-defined prime meridian.
 *
 * <table class="gigs" summary="Test description"><tr>
 *   <th>Test method:</th>
 *   <td>Create user-defined prime meridian for each of several different prime meridians.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_3003_userPrimeMeridian.csv">{@code GIGS_3003_userPrimeMeridian.csv}</a>.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link DatumFactory#createPrimeMeridian(Map, double, Unit)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>The software should accept the test data. The properties of the created objects will
 *       be compared with the properties given to the factory method.</td>
 * </tr></table>
 *
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.gigs.GIGS3003;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS3003 {
 *    public MyTest() {
 *        super(new MyDatumFactory());
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
public strictfp class GIGS3003 extends UserObjectFactoryTestCase<PrimeMeridian> {
    /**
     * The prime meridian Greenwich longitude, in unit of {@link #angularUnit}.
     */
    public double greenwichLongitude;

    /**
     * The unit of measurement of {@link #greenwichLongitude}.
     */
    public Unit<Angle> angularUnit;

    /**
     * The prime meridian created by the factory,
     * or {@code null} if not yet created or if the prime meridian creation failed.
     *
     * @see #datumFactory
     */
    private PrimeMeridian primeMeridian;

    /**
     * Factory to use for building {@link PrimeMeridian} instances, or {@code null} if none.
     * This is the factory used by the {@link #getIdentifiedObject()} method.
     */
    protected final DatumFactory datumFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS3003} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(DatumFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param datumFactory  factory for creating {@link PrimeMeridian} instances.
     */
    public GIGS3003(final DatumFactory datumFactory) {
        super(datumFactory);
        this.datumFactory = datumFactory;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isFactoryPreservingUserValues}</li>
     *       <li>{@linkplain #datumFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.datumFactory, datumFactory));
        return op;
    }

    /**
     * Sets the prime meridian instance to verify. This method is invoked only by other test classes
     * which need to verify the prime meridian contained in a geodetic datum instead than the prime
     * meridian immediately after creation.
     */
    final void setIdentifiedObject(final PrimeMeridian instance) {
        primeMeridian = instance;
    }

    /**
     * Returns the prime meridian instance to be tested. When this method is invoked for the first time,
     * it creates the prime meridian to test by invoking the corresponding method from {@link DatumFactory}
     * with the current {@link #properties properties} map in argument.
     * The created object is then cached and returned in all subsequent invocations of this method.
     *
     * @return the prime meridian instance to test.
     * @throws FactoryException if an error occurred while creating the prime meridian instance.
     */
    @Override
    public PrimeMeridian getIdentifiedObject() throws FactoryException {
        if (primeMeridian == null) {
            assumeNotNull(datumFactory);
            primeMeridian = datumFactory.createPrimeMeridian(properties, greenwichLongitude, angularUnit);
        }
        return primeMeridian;
    }

    /**
     * Verifies the properties of the prime meridian given by {@link #getIdentifiedObject()}.
     */
    final void verifyPrimeMeridian() throws FactoryException {
        if (skipTests) {
            return;
        }
        final String name = getName();
        final String code = getCode();
        final PrimeMeridian primeMeridian = getIdentifiedObject();
        assertNotNull("PrimeMeridian", primeMeridian);
        validators.validate(primeMeridian);

        verifyPrimeMeridian(primeMeridian, name, greenwichLongitude, angularUnit);
        verifyIdentification(primeMeridian, name, code);
        if (isFactoryPreservingUserValues) {
            configurationTip = Configuration.Key.isFactoryPreservingUserValues;
            assertEquals("PrimeMeridian.getAngularUnit()",        angularUnit,        primeMeridian.getAngularUnit());
            assertEquals("PrimeMeridian.getGreenwichLongitude()", greenwichLongitude, primeMeridian.getGreenwichLongitude(), ANGULAR_TOLERANCE);
            configurationTip = null;
        }
    }

    /**
     * Tests “GIGS PM A” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>GIGS prime meridian code: <b>68901</b></li>
     *   <li>GIGS prime meridian name: <b>GIGS PM A</b></li>
     *   <li>EPSG equivalence: <b>8901 – Greenwich</b></li>
     *   <li>Greenwich longitude: <b>0°</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the properties.
     *
     * @see GIGS2003#testGreenwich()
     */
    @Test
    public void testGreenwich() throws FactoryException {
        setCodeAndName(68901, "GIGS PM A");
        greenwichLongitude = 0.0;
        angularUnit        = units.degree();
        verifyPrimeMeridian();
    }

    /**
     * Tests “GIGS PM D” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>GIGS prime meridian code: <b>68908</b></li>
     *   <li>GIGS prime meridian name: <b>GIGS PM D</b></li>
     *   <li>EPSG equivalence: <b>8908 – Jakarta</b></li>
     *   <li>Greenwich longitude: <b>106°48'27.79° (106.80771944444444°)</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the properties.
     *
     * @see GIGS2003#testJakarta()
     */
    @Test
    public void testJakarta() throws FactoryException {
        setCodeAndName(68908, "GIGS PM D");
        greenwichLongitude = 106.80771944444444;
        angularUnit        = units.degree();
        verifyPrimeMeridian();
    }

    /**
     * Tests “GIGS PM H” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>GIGS prime meridian code: <b>68903</b></li>
     *   <li>GIGS prime meridian name: <b>GIGS PM H</b></li>
     *   <li>EPSG equivalence: <b>8903 – Paris</b></li>
     *   <li>Greenwich longitude: <b>2.5969213 grad (2.33722917°)</b></li>
     *   <li>Specific usage / Remarks: <b>Equivalent to 2°20'14.025.</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the properties.
     *
     * @see GIGS2003#testParis()
     */
    @Test
    public void testParis() throws FactoryException {
        setCodeAndName(68903, "GIGS PM H");
        greenwichLongitude = 2.5969213;
        angularUnit        = units.grad();
        verifyPrimeMeridian();
    }

    /**
     * Tests “GIGS PM I” prime meridian creation from the factory.
     *
     * <ul>
     *   <li>GIGS prime meridian code: <b>68904</b></li>
     *   <li>GIGS prime meridian name: <b>GIGS PM I</b></li>
     *   <li>EPSG equivalence: <b>8904 – Bogota</b></li>
     *   <li>Greenwich longitude: <b>-74°04'51.3° (74.08091666666667°)</b></li>
     * </ul>
     *
     * @throws FactoryException if an error occurred while creating the prime meridian from the properties.
     *
     * @see GIGS2003#testBogota()
     */
    @Test
    public void testBogota() throws FactoryException {
        setCodeAndName(68904, "GIGS PM I");
        greenwichLongitude = 74.08091666666667;
        angularUnit        = units.degree();
        verifyPrimeMeridian();
    }
}
