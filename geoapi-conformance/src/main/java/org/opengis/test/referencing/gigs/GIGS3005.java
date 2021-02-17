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
import javax.measure.quantity.Length;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.test.Configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;


/**
 * Verifies that the software allows correct definition of a user-defined map projection.
 *
 * <table class="gigs">
 * <caption>Test description</caption>
 * <tr>
 *   <th>Test method:</th>
 *   <td>Create user-defined projection for each of several different map projections.</td>
 * </tr><tr>
 *   <th>Test data:</th>
 *   <td><a href="doc-files/GIGS_3005_userProjection.csv">{@code GIGS_3005_userProjection.csv}</a>.</td>
 * </tr><tr>
 *   <th>Tested API:</th>
 *   <td>{@link CoordinateOperationFactory#getOperationMethod(String)} and<br>
 *       {@link CoordinateOperationFactory#createDefiningConversion(Map, OperationMethod, ParameterValueGroup)}.</td>
 * </tr><tr>
 *   <th>Expected result:</th>
 *   <td>The geoscience software should accept the test data. The order in which the projection parameters
 *       are entered is not critical, although that given in the test dataset is recommended.</td>
 * </tr></table>
 *
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.gigs.GIGS300(;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends GIGS3005 {
 *    public MyTest() {
 *        super(new MyCoordinateOperationFactory());
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
public strictfp class GIGS3005 extends UserObjectFactoryTestCase<Conversion> {
    /**
     * The name of the operation method to use.
     */
    public String methodName;

    /**
     * The parameters of the conversion to create.
     */
    public ParameterValueGroup definition;

    /**
     * The coordinate operation method.
     */
    private OperationMethod method;

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
    protected final CoordinateOperationFactory copFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code GIGS3004} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(CoordinateOperationFactory.class);
    }

    /**
     * Creates a new test using the given factory. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param copFactory  factory for creating {@link Conversion} instances.
     */
    public GIGS3005(final CoordinateOperationFactory copFactory) {
        super(copFactory);
        this.copFactory = copFactory;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isFactoryPreservingUserValues}</li>
     *       <li>{@linkplain #copFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run.
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.copFactory, copFactory));
        return op;
    }

    /**
     * Instantiates the {@link #definition} field.
     * It is caller's responsibility to set the parameter values.
     *
     * @throws FactoryException if an error occurred while creating the parameters.
     */
    @SuppressWarnings("null")
    private void createDefaultParameters() throws FactoryException {
        assumeNotNull(copFactory);
        /*
         * Get the OperationMethod defined by the library. Libraries are not required
         * to implement every possible operation methods, in which case unimplemented
         * methods will be reported. If the method is supported, then this method will
         * verify the following properties:
         *
         *  - The number of source dimensions
         *  - The number of target dimensions
         *
         * We currently do not verify the name because libraries often have their own
         * hard-coded implementations of operation methods instead than creating new
         * instances from the given properties.
         */
        try {
            method = copFactory.getOperationMethod(methodName);
        } catch (NoSuchIdentifierException e) {
            unsupportedCode(OperationMethod.class, methodName);
            throw e;
        }
        if (method == null) {
            fail("CoordinateOperationFactory.getOperationMethod(\"" + methodName + "\") shall not return null.");
        }
        validators.validate(method);
        assertEquals("OperationMethod.getSourceDimensions()", Integer.valueOf(2), method.getSourceDimensions());
        assertEquals("OperationMethod.getTargetDimensions()", Integer.valueOf(2), method.getTargetDimensions());
        definition = method.getParameters().createValue();
    }

    /**
     * Returns the coordinate operation instance to be tested. When this method is invoked for the first time,
     * it creates the coordinate operation to test by invoking the corresponding method from
     * {@link CoordinateOperationFactory} with the current {@link #properties properties} map in argument.
     * The created object is then cached and returned in all subsequent invocations of this method.
     *
     * @return the geodetic datum instance to test.
     * @throws FactoryException if an error occurred while creating the coordinate operation instance.
     */
    @Override
    @SuppressWarnings("null")
    public Conversion getIdentifiedObject() throws FactoryException {
        if (conversion == null) {
            validators.validate(definition);
            final CoordinateOperation operation = copFactory.createDefiningConversion(properties, method, definition);
            if (operation != null) {  // For consistency with the behavior in other classes.
                assertInstanceOf(getName(), Conversion.class, operation);
                conversion = (Conversion) operation;
            }
        }
        return conversion;
    }

    /**
     * Verifies the properties of the conversion given by {@link #getIdentifiedObject()}.
     */
    final void verifyConversion() throws FactoryException {
        if (skipTests) {
            return;
        }
        final String name = getName();
        final String code = getCode();
        final Conversion conversion = getIdentifiedObject();
        assertNotNull("Conversion", conversion);
        validators.validate(conversion);
        verifyIdentification(conversion, name, code);
        /*
         * Now verify the properties of the projection we just created. We require the parameter
         * group to contain at least the values that we gave to it. If the library defines some
         * additional parameters, then those extra parameters will be ignored.
         */
        final ParameterValueGroup projectionParameters = conversion.getParameterValues();
        assertNotNull("Conversion.getParameterValues()", projectionParameters);
        if (isFactoryPreservingUserValues) {
            for (final GeneralParameterValue info : definition.values()) {
                final String paramName = getName(info.getDescriptor());
                if ("semi_major".equalsIgnoreCase(paramName) ||
                    "semi_minor".equalsIgnoreCase(paramName))
                {
                    /*
                     * Semi-major and semi-minor parameters were part of OGC 01-009 specification.
                     * Since ISO 19111 they are not anymore explicit part of projection parameters.
                     * However some implementations may still use them, so we make this test tolerant.
                     */
                    continue;
                }
                if (info instanceof ParameterValue<?>) {
                    final ParameterValue<?> expected = (ParameterValue<?>) info;
                    final ParameterValue<?> param = projectionParameters.parameter(paramName);
                    assertNotNull(paramName, param);
                    final double value = expected.doubleValue();
                    assertEquals(paramName, value, param.doubleValue(expected.getUnit()), StrictMath.abs(TOLERANCE * value));
                }
            }
        }
    }

    /**
     * Tests “GIGS projection 1” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65001</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 1</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator</b></li>
     *   <li>EPSG equivalence: <b>16031 – UTM zone 31N</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>3°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.9996</td></tr>
     *   <tr><td>False easting</td><td>500000 metres</td></tr>
     *   <tr><td>False northing</td><td>0 metre</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testUTM()
     */
    @Test
    public void testUTM_zone31N() throws FactoryException {
        setCodeAndName(65001, "GIGS projection 1");
        methodName = "Transverse Mercator";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(3.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.9996, units.one());
        definition.parameter("False easting").setValue(500000.0, metre);
        definition.parameter("False northing").setValue(0.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 2” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65002</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 2</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator</b></li>
     *   <li>EPSG equivalence: <b>19916 – British National Grid</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>49°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>-2°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.999601272</td></tr>
     *   <tr><td>False easting</td><td>400000 metres</td></tr>
     *   <tr><td>False northing</td><td>-100000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testBritishNationalGrid()
     */
    @Test
    public void testBritishNationalGrid() throws FactoryException {
        setCodeAndName(65002, "GIGS projection 2");
        methodName = "Transverse Mercator";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(49.0, degree);
        definition.parameter("Longitude of natural origin").setValue(-2.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.999601272, units.one());
        definition.parameter("False easting").setValue(400000.0, metre);
        definition.parameter("False northing").setValue(-100000.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 2 alt A” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65021</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 2 alt A</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>-2°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.999601272</td></tr>
     *   <tr><td>False easting</td><td>400000 metres</td></tr>
     *   <tr><td>False northing</td><td>-5527462.688 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testBritishNationalGrid_altA() throws FactoryException {
        setCodeAndName(65021, "GIGS projection 2 alt A");
        methodName = "Transverse Mercator";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(-2.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.999601272, units.one());
        definition.parameter("False easting").setValue(400000.0, metre);
        definition.parameter("False northing").setValue(-5527462.688, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 2 alt B” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65022</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 2 alt B</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>-2°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.999601272</td></tr>
     *   <tr><td>False easting</td><td>400000 metres</td></tr>
     *   <tr><td>False northing</td><td>-5527063.816 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testBritishNationalGrid_altB() throws FactoryException {
        setCodeAndName(65022, "GIGS projection 2 alt B");
        methodName = "Transverse Mercator";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(-2.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.999601272, units.one());
        definition.parameter("False easting").setValue(400000.0, metre);
        definition.parameter("False northing").setValue(-5527063.816, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 4” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65004</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 4</b></li>
     *   <li>EPSG operation method: <b>Oblique Stereographic</b></li>
     *   <li>EPSG equivalence: <b>19914 – RD New</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>52°09'22.178N</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>5°23'15.5E</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.9999079</td></tr>
     *   <tr><td>False easting</td><td>155000 metres</td></tr>
     *   <tr><td>False northing</td><td>463000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testRDNew()
     */
    @Test
    public void testRDNew() throws FactoryException {
        setCodeAndName(65004, "GIGS projection 4");
        methodName = "Oblique Stereographic";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(52.1561606, degree);
        definition.parameter("Longitude of natural origin").setValue(5.3876389, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.9999079, units.one());
        definition.parameter("False easting").setValue(155000.0, metre);
        definition.parameter("False northing").setValue(463000.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 5” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65005</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 5</b></li>
     *   <li>EPSG operation method: <b>Mercator (1SP)</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>3°11'32.21E</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.997</td></tr>
     *   <tr><td>False easting</td><td>3900000 metres</td></tr>
     *   <tr><td>False northing</td><td>900000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testGIGSProjection5() throws FactoryException {
        setCodeAndName(65005, "GIGS projection 5");
        methodName = "Mercator (1SP)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(3.1922806, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.997, units.one());
        definition.parameter("False easting").setValue(3900000.0, metre);
        definition.parameter("False northing").setValue(900000.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 6” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65006</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 6</b></li>
     *   <li>EPSG operation method: <b>Lambert Conic Conformal (2SP)</b></li>
     *   <li>EPSG equivalence: <b>19961 – Belgian Lambert 72</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of false origin</td><td>90°</td></tr>
     *   <tr><td>Longitude of false origin</td><td>4°22'02.952E</td></tr>
     *   <tr><td>Latitude of 1st standard parallel</td><td>51°10'00.00204N</td></tr>
     *   <tr><td>Latitude of 2nd standard parallel</td><td>49°50'00.00204N</td></tr>
     *   <tr><td>Easting at false origin</td><td>150000.013 metres</td></tr>
     *   <tr><td>Northing at false origin</td><td>5400088.438 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testBelgianLambert72() throws FactoryException {
        setCodeAndName(65006, "GIGS projection 6");
        methodName = "Lambert Conic Conformal (2SP)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of false origin").setValue(90.0, degree);
        definition.parameter("Longitude of false origin").setValue(4.3674867, degree);
        definition.parameter("Latitude of 1st standard parallel").setValue(51.1666672, degree);
        definition.parameter("Latitude of 2nd standard parallel").setValue(49.8333339, degree);
        definition.parameter("Easting at false origin").setValue(150000.013, metre);
        definition.parameter("Northing at false origin").setValue(5400088.438, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 7” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65007</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 7</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator</b></li>
     *   <li>EPSG equivalence: <b>17454 – Australian Map Grid zone 54</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>141°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.9996</td></tr>
     *   <tr><td>False easting</td><td>500000 metres</td></tr>
     *   <tr><td>False northing</td><td>10000000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testAustralianMapGridZones()
     */
    @Test
    public void testAustralianMapGridZone54() throws FactoryException {
        setCodeAndName(65007, "GIGS projection 7");
        methodName = "Transverse Mercator";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(141.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.9996, units.one());
        definition.parameter("False easting").setValue(500000.0, metre);
        definition.parameter("False northing").setValue(1.0E7, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 8” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65008</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 8</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator</b></li>
     *   <li>EPSG equivalence: <b>17455 – Australian Map Grid zone 55</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>147°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.9996</td></tr>
     *   <tr><td>False easting</td><td>500000 metres</td></tr>
     *   <tr><td>False northing</td><td>10000000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testAustralianMapGridZones()
     */
    @Test
    public void testAustralianMapGridZone55() throws FactoryException {
        setCodeAndName(65008, "GIGS projection 8");
        methodName = "Transverse Mercator";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(147.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.9996, units.one());
        definition.parameter("False easting").setValue(500000.0, metre);
        definition.parameter("False northing").setValue(1.0E7, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 9” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65009</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 9</b></li>
     *   <li>EPSG operation method: <b>Albers Equal Area</b></li>
     *   <li>EPSG equivalence: <b>17365 – Australian Albers</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of false origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of false origin</td><td>132°</td></tr>
     *   <tr><td>Latitude of 1st standard parallel</td><td>-18°</td></tr>
     *   <tr><td>Latitude of 2nd standard parallel</td><td>-36°</td></tr>
     *   <tr><td>Easting at false origin</td><td>0 metre</td></tr>
     *   <tr><td>Northing at false origin</td><td>0 metre</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testAustralianAlbers() throws FactoryException {
        setCodeAndName(65009, "GIGS projection 9");
        methodName = "Albers Equal Area";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of false origin").setValue(0.0, degree);
        definition.parameter("Longitude of false origin").setValue(132.0, degree);
        definition.parameter("Latitude of 1st standard parallel").setValue(-18.0, degree);
        definition.parameter("Latitude of 2nd standard parallel").setValue(-36.0, degree);
        definition.parameter("Easting at false origin").setValue(0.0, metre);
        definition.parameter("Northing at false origin").setValue(0.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 10” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65010</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 10</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator (South Orientated)</b></li>
     *   <li>EPSG equivalence: <b>17521 – South African Survey Grid zone 21</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>21°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>1</td></tr>
     *   <tr><td>False easting</td><td>0 metre</td></tr>
     *   <tr><td>False northing</td><td>0 metre</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testSouthAfricanSurveyGridZone21() throws FactoryException {
        setCodeAndName(65010, "GIGS projection 10");
        methodName = "Transverse Mercator (South Orientated)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(21.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(1.0, units.one());
        definition.parameter("False easting").setValue(0.0, metre);
        definition.parameter("False northing").setValue(0.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 11” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65011</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 11</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator</b></li>
     *   <li>EPSG equivalence: <b>18035 – Argentina zone 5</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>-90°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>-60°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>1</td></tr>
     *   <tr><td>False easting</td><td>5500000 metres</td></tr>
     *   <tr><td>False northing</td><td>0 metre</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testArgentinaZones()
     */
    @Test
    public void testArgentinaZone5() throws FactoryException {
        setCodeAndName(65011, "GIGS projection 11");
        methodName = "Transverse Mercator";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(-90.0, degree);
        definition.parameter("Longitude of natural origin").setValue(-60.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(1.0, units.one());
        definition.parameter("False easting").setValue(5500000.0, metre);
        definition.parameter("False northing").setValue(0.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 12” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65012</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 12</b></li>
     *   <li>EPSG operation method: <b>American Polyconic</b></li>
     *   <li>EPSG equivalence: <b>19941 – Brazil Polyconic</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>-54°</td></tr>
     *   <tr><td>False easting</td><td>5000000 metres</td></tr>
     *   <tr><td>False northing</td><td>10000000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testBrazilPolyconic()
     */
    @Test
    public void testBrazilPolyconic() throws FactoryException {
        setCodeAndName(65012, "GIGS projection 12");
        methodName = "American Polyconic";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(-54.0, degree);
        definition.parameter("False easting").setValue(5000000.0, metre);
        definition.parameter("False northing").setValue(1.0E7, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 13” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65013</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 13</b></li>
     *   <li>EPSG operation method: <b>Hotine Oblique Mercator (variant B)</b></li>
     *   <li>Specific usage / Remarks: <b>metre</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of projection centre</td><td>4°</td></tr>
     *   <tr><td>Longitude of projection centre</td><td>115°</td></tr>
     *   <tr><td>Azimuth of initial line</td><td>53°18'56.9158</td></tr>
     *   <tr><td>Angle from Rectified to Skew Grid</td><td>53°07'48.3685</td></tr>
     *   <tr><td>Scale factor on initial line</td><td>0.99984</td></tr>
     *   <tr><td>Easting at projection centre</td><td>590521.147 metres</td></tr>
     *   <tr><td>Northing at projection centre</td><td>442890.861 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testGIGSProjection13() throws FactoryException {
        setCodeAndName(65013, "GIGS projection 13");
        methodName = "Hotine Oblique Mercator (variant B)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of projection centre").setValue(4.0, degree);
        definition.parameter("Longitude of projection centre").setValue(115.0, degree);
        definition.parameter("Azimuth of initial line").setValue(53.3158099, degree);
        definition.parameter("Angle from Rectified to Skew Grid").setValue(53.1301024, degree);
        definition.parameter("Scale factor on initial line").setValue(0.99984, units.one());
        definition.parameter("Easting at projection centre").setValue(590521.147, metre);
        definition.parameter("Northing at projection centre").setValue(442890.861, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 14” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65014</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 14</b></li>
     *   <li>EPSG operation method: <b>Hotine Oblique Mercator (variant A)</b></li>
     *   <li>EPSG equivalence: <b>19894 – East Malaysia BRSO</b></li>
     *   <li>Specific usage / Remarks: <b>metre</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of projection centre</td><td>4°</td></tr>
     *   <tr><td>Longitude of projection centre</td><td>115°</td></tr>
     *   <tr><td>Azimuth of initial line</td><td>53°18'56.9158</td></tr>
     *   <tr><td>Angle from Rectified to Skew Grid</td><td>53°07'48.3685</td></tr>
     *   <tr><td>Scale factor on initial line</td><td>0.99984</td></tr>
     *   <tr><td>False easting</td><td>0 metre</td></tr>
     *   <tr><td>False northing</td><td>0 metre</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testEastMalaysia() throws FactoryException {
        setCodeAndName(65014, "GIGS projection 14");
        methodName = "Hotine Oblique Mercator (variant A)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of projection centre").setValue(4.0, degree);
        definition.parameter("Longitude of projection centre").setValue(115.0, degree);
        definition.parameter("Azimuth of initial line").setValue(53.3158099, degree);
        definition.parameter("Angle from Rectified to Skew Grid").setValue(53.1301024, degree);
        definition.parameter("Scale factor on initial line").setValue(0.99984, units.one());
        definition.parameter("False easting").setValue(0.0, metre);
        definition.parameter("False northing").setValue(0.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 15” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65015</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 15</b></li>
     *   <li>EPSG operation method: <b>Cassini-Soldner</b></li>
     *   <li>EPSG equivalence: <b>19893 – Johor Grid</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>2°07'18.0471N</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>103°25'40.5704E</td></tr>
     *   <tr><td>False easting</td><td>-14810.562 metres</td></tr>
     *   <tr><td>False northing</td><td>8758.32 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testJohorGrid() throws FactoryException {
        setCodeAndName(65015, "GIGS projection 15");
        methodName = "Cassini-Soldner";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(2.1216797, degree);
        definition.parameter("Longitude of natural origin").setValue(103.4279361, degree);
        definition.parameter("False easting").setValue(-14810.562, metre);
        definition.parameter("False northing").setValue(8758.32, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 16” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65016</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 16</b></li>
     *   <li>EPSG operation method: <b>Lambert Azimuthal Equal Area</b></li>
     *   <li>EPSG equivalence: <b>19986 – Europe Equal Area 2001</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>52°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>10°</td></tr>
     *   <tr><td>False easting</td><td>4321000 metres</td></tr>
     *   <tr><td>False northing</td><td>3210000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testEuropeEqualArea() throws FactoryException {
        setCodeAndName(65016, "GIGS projection 16");
        methodName = "Lambert Azimuthal Equal Area";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(52.0, degree);
        definition.parameter("Longitude of natural origin").setValue(10.0, degree);
        definition.parameter("False easting").setValue(4321000.0, metre);
        definition.parameter("False northing").setValue(3210000.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 17” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65017</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 17</b></li>
     *   <li>EPSG operation method: <b>Lambert Conic Conformal (2SP)</b></li>
     *   <li>EPSG equivalence: <b>15262 – Utah North (ft)</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of false origin</td><td>40°20'N</td></tr>
     *   <tr><td>Longitude of false origin</td><td>111°30'W</td></tr>
     *   <tr><td>Latitude of 1st standard parallel</td><td>41°47'N</td></tr>
     *   <tr><td>Latitude of 2nd standard parallel</td><td>40°43'N</td></tr>
     *   <tr><td>Easting at false origin</td><td>1640419.948 foots</td></tr>
     *   <tr><td>Northing at false origin</td><td>3280839.895 foots</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testUtahNorth() throws FactoryException {
        setCodeAndName(65017, "GIGS projection 17");
        methodName = "Lambert Conic Conformal (2SP)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> foot = units.foot();
        definition.parameter("Latitude of false origin").setValue(40.3333333, degree);
        definition.parameter("Longitude of false origin").setValue(-111.5, degree);
        definition.parameter("Latitude of 1st standard parallel").setValue(41.7833333, degree);
        definition.parameter("Latitude of 2nd standard parallel").setValue(40.7166667, degree);
        definition.parameter("Easting at false origin").setValue(1640419.948, foot);
        definition.parameter("Northing at false origin").setValue(3280839.895, foot);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 18” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65018</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 18</b></li>
     *   <li>EPSG operation method: <b>Lambert Conic Conformal (2SP)</b></li>
     *   <li>EPSG equivalence: <b>15297 – Utah North (ftUS)</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of false origin</td><td>40°20'N</td></tr>
     *   <tr><td>Longitude of false origin</td><td>111°30'W</td></tr>
     *   <tr><td>Latitude of 1st standard parallel</td><td>41°47'N</td></tr>
     *   <tr><td>Latitude of 2nd standard parallel</td><td>40°43'N</td></tr>
     *   <tr><td>Easting at false origin</td><td>1640416.667 US survey foots</td></tr>
     *   <tr><td>Northing at false origin</td><td>3280833.333 US survey foots</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testUtahNorth_ftUS() throws FactoryException {
        setCodeAndName(65018, "GIGS projection 18");
        methodName = "Lambert Conic Conformal (2SP)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> footSurveyUS = units.footSurveyUS();
        definition.parameter("Latitude of false origin").setValue(40.3333333, degree);
        definition.parameter("Longitude of false origin").setValue(-111.5, degree);
        definition.parameter("Latitude of 1st standard parallel").setValue(41.7833333, degree);
        definition.parameter("Latitude of 2nd standard parallel").setValue(40.7166667, degree);
        definition.parameter("Easting at false origin").setValue(1640416.667, footSurveyUS);
        definition.parameter("Northing at false origin").setValue(3280833.333, footSurveyUS);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 19” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65019</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 19</b></li>
     *   <li>EPSG operation method: <b>Lambert Conic Conformal (1SP)</b></li>
     *   <li>EPSG equivalence: <b>18082 – Lambert zone II</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>52 grads</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>0 grad</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.99987742</td></tr>
     *   <tr><td>False easting</td><td>600000 metres</td></tr>
     *   <tr><td>False northing</td><td>2200000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testLambertZoneII() throws FactoryException {
        setCodeAndName(65019, "GIGS projection 19");
        methodName = "Lambert Conic Conformal (1SP)";
        createDefaultParameters();
        final Unit<Angle>  grad  = units.grad();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(52.0, grad);
        definition.parameter("Longitude of natural origin").setValue(0.0, grad);
        definition.parameter("Scale factor at natural origin").setValue(0.99987742, units.one());
        definition.parameter("False easting").setValue(600000.0, metre);
        definition.parameter("False northing").setValue(2200000.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 23” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65023</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 23</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator</b></li>
     *   <li>EPSG equivalence: <b>No direct equivalent</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>3°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.9996</td></tr>
     *   <tr><td>False easting</td><td>1640416.667 US survey foots</td></tr>
     *   <tr><td>False northing</td><td>0 US survey foot</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testGIGSProjection23() throws FactoryException {
        setCodeAndName(65023, "GIGS projection 23");
        methodName = "Transverse Mercator";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> footSurveyUS = units.footSurveyUS();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(3.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.9996, units.one());
        definition.parameter("False easting").setValue(1640416.667, footSurveyUS);
        definition.parameter("False northing").setValue(0.0, footSurveyUS);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 24” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65024</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 24</b></li>
     *   <li>EPSG operation method: <b>Mercator (Variant B)</b></li>
     *   <li>EPSG equivalence: <b>19884 – Caspian Sea Mercator</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of 1st standard parallel</td><td>42°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>51°</td></tr>
     *   <tr><td>False easting</td><td>0 metre</td></tr>
     *   <tr><td>False northing</td><td>0 metre</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testCaspianSeaMercator() throws FactoryException {
        setCodeAndName(65024, "GIGS projection 24");
        methodName = "Mercator (Variant B)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of 1st standard parallel").setValue(42.0, degree);
        definition.parameter("Longitude of natural origin").setValue(51.0, degree);
        definition.parameter("False easting").setValue(0.0, metre);
        definition.parameter("False northing").setValue(0.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 25” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65025</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 25</b></li>
     *   <li>EPSG operation method: <b>Lambert Conic Conformal (1SP)</b></li>
     *   <li>EPSG equivalence: <b>18086 – France EuroLambert</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>46°48'N</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>2°20'14.025E</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.99987742</td></tr>
     *   <tr><td>False easting</td><td>600000 metres</td></tr>
     *   <tr><td>False northing</td><td>2200000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     */
    @Test
    public void testFranceEuroLambert() throws FactoryException {
        setCodeAndName(65025, "GIGS projection 25");
        methodName = "Lambert Conic Conformal (1SP)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(46.8, degree);
        definition.parameter("Longitude of natural origin").setValue(2.3372292, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.99987742, units.one());
        definition.parameter("False easting").setValue(600000.0, metre);
        definition.parameter("False northing").setValue(2200000.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 26” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65026</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 26</b></li>
     *   <li>EPSG operation method: <b>Hotine Oblique Mercator (variant B)</b></li>
     *   <li>EPSG equivalence: <b>19931 – EOV</b></li>
     *   <li>Specific usage / Remarks: <b>metre</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of projection centre</td><td>47°08'39.8174N</td></tr>
     *   <tr><td>Longitude of projection centre</td><td>19°02'54.8584E</td></tr>
     *   <tr><td>Azimuth of initial line</td><td>90°</td></tr>
     *   <tr><td>Angle from Rectified to Skew Grid</td><td>90°</td></tr>
     *   <tr><td>Scale factor on initial line</td><td>0.99993</td></tr>
     *   <tr><td>Easting at projection centre</td><td>650000 metres</td></tr>
     *   <tr><td>Northing at projection centre</td><td>200000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testEOV()
     */
    @Test
    public void testEOV() throws FactoryException {
        setCodeAndName(65026, "GIGS projection 26");
        methodName = "Hotine Oblique Mercator (variant B)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of projection centre").setValue(47.14439372222222, degree);
        definition.parameter("Longitude of projection centre").setValue(19.048571777777777, degree);
        definition.parameter("Azimuth of initial line").setValue(90.0, degree);
        definition.parameter("Angle from Rectified to Skew Grid").setValue(90.0, degree);
        definition.parameter("Scale factor on initial line").setValue(0.99993, units.one());
        definition.parameter("Easting at projection centre").setValue(650000.0, metre);
        definition.parameter("Northing at projection centre").setValue(200000.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 27” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65027</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 27</b></li>
     *   <li>EPSG operation method: <b>Mercator (variant A)</b></li>
     *   <li>EPSG equivalence: <b>19905 – NEIEZ</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>110°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.997</td></tr>
     *   <tr><td>False easting</td><td>3900000 metres</td></tr>
     *   <tr><td>False northing</td><td>900000 metres</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testNEIEZ()
     */
    @Test
    public void testNEIEZ() throws FactoryException {
        setCodeAndName(65027, "GIGS projection 27");
        methodName = "Mercator (variant A)";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(110.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.997, units.one());
        definition.parameter("False easting").setValue(3900000.0, metre);
        definition.parameter("False northing").setValue(900000.0, metre);
        verifyConversion();
    }

    /**
     * Tests “GIGS projection 28” conversion creation from the factory.
     *
     * <ul>
     *   <li>GIGS conversion code: <b>65028</b></li>
     *   <li>GIGS conversion name: <b>GIGS projection 28</b></li>
     *   <li>EPSG operation method: <b>Transverse Mercator</b></li>
     *   <li>EPSG equivalence: <b>16008 – UTM zone 8N</b></li>
     * </ul>
     * <table class="ogc">
     *   <caption>Conversion parameters</caption>
     *   <tr><th>Parameter name</th><th>Value</th></tr>
     *   <tr><td>Latitude of natural origin</td><td>0°</td></tr>
     *   <tr><td>Longitude of natural origin</td><td>-135°</td></tr>
     *   <tr><td>Scale factor at natural origin</td><td>0.9996</td></tr>
     *   <tr><td>False easting</td><td>500000 metres</td></tr>
     *   <tr><td>False northing</td><td>0 metre</td></tr>
     * </table>
     *
     * @throws FactoryException if an error occurred while creating the conversion from the properties.
     *
     * @see GIGS2005#testUTM()
     */
    @Test
    public void testUTM_zone8N() throws FactoryException {
        setCodeAndName(65028, "GIGS projection 28");
        methodName = "Transverse Mercator";
        createDefaultParameters();
        final Unit<Angle> degree = units.degree();
        final Unit<Length> metre = units.metre();
        definition.parameter("Latitude of natural origin").setValue(0.0, degree);
        definition.parameter("Longitude of natural origin").setValue(-135.0, degree);
        definition.parameter("Scale factor at natural origin").setValue(0.9996, units.one());
        definition.parameter("False easting").setValue(500000.0, metre);
        definition.parameter("False northing").setValue(0.0, metre);
        verifyConversion();
    }
}
