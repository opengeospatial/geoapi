/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012 Open Geospatial Consortium, Inc.
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
import java.util.Map;
import java.util.HashMap;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.unit.NonSI;
import javax.measure.quantity.Length;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.lang.Double.*;
import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;


/**
 * Test for new CRS definition. The test procedures in this series are designed
 * to evaluate the software’s capabilities for adding user-defined CRS and
 * transformation definitions
 *
 * @see org.opengis.test.referencing.ObjectFactoryTest
 * @see org.opengis.test.TestSuite
 *
 * @author  Alexis Manin (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Parameterized.class)
public strictfp class Series3000Test extends GIGSTestCase {
    /**
     * Factory to use for build user's coordinate reference systems,
     * or {@code null} if none.
     */
    protected final CRSFactory crsFactory;

    /**
     * Factory to use for build user's coordinate systems and axes,
     * or {@code null} if none.
     */
    protected final CSFactory csFactory;

    /**
     * Factory to use for build user's datum, ellipsoid and prime meridians,
     * or {@code null} if none.
     */
    protected final DatumFactory datumFactory;

    /**
     * Factory to use for build user's coordinate operations,
     * or {@code null} if none.
     */
    protected final CoordinateOperationFactory copFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code Series3000Test} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(CRSFactory.class, CSFactory.class, DatumFactory.class,
                CoordinateOperationFactory.class);
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
    public Series3000Test(final CRSFactory crsFactory, final CSFactory csFactory,
            final DatumFactory datumFactory, final CoordinateOperationFactory copFactory)
    {
        super(crsFactory, csFactory, datumFactory, copFactory);
        this.crsFactory   = crsFactory;
        this.csFactory    = csFactory;
        this.datumFactory = datumFactory;
        this.copFactory   = copFactory;
    }

    /**
     * Returns the unit of the given name.
     *
     * @param  name The unit name.
     * @return The unit for the given name.
     * @throws IllegalArgumentException If the given name is unsupported by this method.
     */
    private static Unit<Length> parseLinearUnit(final String name) throws IllegalArgumentException {
        if (name.equalsIgnoreCase("metre"))          return SI.METRE;
        if (name.equalsIgnoreCase("kilometre"))      return SI.KILOMETRE;
        if (name.equalsIgnoreCase("US survey foot")) return NonSI.FOOT_SURVEY_US;
        throw new IllegalArgumentException("Unsupported unit name: " + name);
    }

    /**
     * Ellipsoid definition test.
     * <p>
     * <table cellpadding="3"><tr>
     *   <th nowrap align="left" valign="top">Test purpose:</th>
     *   <td>Verify that the software allows correct definition of a user-defined ellipsoid.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test method:</th>
     *   <td>Create user-defined ellipsoid for each of several different ellipsoids.</td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Test data:</th>
     *   <td>EPSG Dataset and file <a href="{@svnurl gigs}/GIGS_3002_userEllipsoid.csv">{@code GIGS_3002_userEllipsoid.csv}</a>.
     *  </td>
     * </tr><tr>
     *   <th nowrap align="left" valign="top">Expected result:</th>
     *   <td>The software should accept the test data. The order in which the name and the prime meridian parameters
     *  are entered is not critical, although that given in the test dataset is recommended. Test result will be
     *  pass or fail. If fail, details of failure should be recorded.</td>
     * </tr></table>
     *
     * @throws FactoryException If an error occurred while creating an ellipsoid.
     */
    @Test
    public void test3002() throws FactoryException {
        test3002(null);
    }

    /**
     * Creates the ellipsoids and optionally tests them. The behavior of this method depends on
     * whatever {@code objects} is null or not:
     * <p>
     * <ul>
     *   <li>If {@code null}, then all ellipsoids will be created and tested.</li>
     *   <li>If non-null, then only the ellipsoids enumerated in the keys will be created,
     *       but none of them will be tested. The created ellipsoids will be stored in the
     *       values of that map.</li>
     * </ul>
     *
     * @param  objects On input, the names of objects to create. On output, the created objects.
     *         If {@code null}, then all objects will be created and tested.
     * @throws FactoryException If an error occurred while creating an object.
     */
    private void test3002(final Map<String,Ellipsoid> objects) throws FactoryException {
        assumeNotNull(datumFactory);
        final ExpectedData data = new ExpectedData("GIGS_3002_userEllipsoid.csv",
                Integer.class,  // [0]: GIGS ellipsoid code
                String .class,  // [1]: GIGS ellipsoid name
                Double .class,  // [2]: Semi-major axis
                String .class,  // [3]: Unit
                Double .class,  // [4]: Inverse flattening
                Double .class,  // [5]: Semi-minor axis
                Boolean.class); // [6]: Is sphere ?

        final StringBuilder prefix = new StringBuilder("Ellipsoid[\"");
        final int prefixLength = prefix.length();
        while (data.next()) {
            final String name = data.getString(1);
            if (objects != null && !objects.containsKey(name)) {
                // If the current row is not for an object on the list of
                // items requested by the caller, skip the object creation.
                continue;
            }
            final int    code        = data.getInt   (0);
            final double semiMajor   = data.getDouble(2);
            final String unitName    = data.getString(3);
            double inverseFlattening = data.getDouble(4);
            double semiMinor         = data.getDouble(5);
            final boolean isSphere, isIvfDefinitive;
            if (isNaN(semiMinor)) {
                if (isNaN(inverseFlattening)) {
                    isSphere          = true;
                    isIvfDefinitive   = false;
                    semiMinor         = semiMajor;
                    inverseFlattening = POSITIVE_INFINITY;
                } else {
                    isSphere        = false;
                    isIvfDefinitive = true;
                    semiMinor       = semiMajor - semiMajor/inverseFlattening;
                }
            } else {
                isSphere        = false;
                isIvfDefinitive = false;
                if (isNaN(inverseFlattening)) {
                    inverseFlattening = semiMajor / (semiMajor-semiMinor);
                }
            }
            /*
             * Create the ellipsoid and save it in the map given by the caller, if non-null.
             */
            final Map<String,Object> properties = new HashMap<String,Object>(4);
            properties.put(Ellipsoid.IDENTIFIERS_KEY, new SimpleReferenceIdentifier(code));
            properties.put(Ellipsoid.NAME_KEY, name);
            final Unit<Length> unit = parseLinearUnit(unitName);
            final Ellipsoid ellipsoid;
            if (isIvfDefinitive) {
                ellipsoid = datumFactory.createFlattenedSphere(properties, semiMajor, inverseFlattening, unit);
            } else {
                ellipsoid = datumFactory.createEllipsoid(properties, semiMajor, semiMinor, unit);
            }
            prefix.setLength(prefixLength);
            prefix.append(name).append("\"]");
            assertNotNull(prefix.toString(), ellipsoid);
            if (objects != null) {
                assertNull("An object already exists for the same name.", objects.put(name, ellipsoid));
                continue;
            }
            /*
             * Test the ellipsoid.
             */
            prefix.append('.');
            validators.validate(ellipsoid);
            assertEquals  (message(prefix, "getName()"),              name,              getName(ellipsoid));
            testIdentifier(message(prefix, "getIdentifiers()"),       code,              ellipsoid.getIdentifiers());
            assertEquals  (message(prefix, "getAxisUnit()"),          unit,              ellipsoid.getAxisUnit());
            assertEquals  (message(prefix, "getSemiMajorAxis()"),     semiMajor,         ellipsoid.getSemiMajorAxis(),     TOLERANCE*semiMajor);
            assertEquals  (message(prefix, "getSemiMinorAxis()"),     semiMinor,         ellipsoid.getSemiMinorAxis(),     TOLERANCE*semiMinor);
            assertEquals  (message(prefix, "getInverseFlattening()"), inverseFlattening, ellipsoid.getInverseFlattening(), TOLERANCE*inverseFlattening);
            assertEquals  (message(prefix, "isIvfDefinitive()"),      isIvfDefinitive,   ellipsoid.isIvfDefinitive());
            assertEquals  (message(prefix, "isSphere()"),             isSphere,          ellipsoid.isSphere());
        }
    }
}
