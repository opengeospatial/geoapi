/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.logging.Logger;
import java.io.IOException;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dataset.CoordinateSystem;
import javax.measure.unit.Unit;
import javax.measure.unit.SI;
import javax.measure.unit.NonSI;

import org.opengis.referencing.crs.SingleCRS;
import org.opengis.referencing.crs.CompoundCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.operation.Projection;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.test.referencing.OperationValidator;
import org.opengis.test.referencing.CRSValidator;
import org.opengis.test.referencing.CSValidator;
import org.opengis.test.ValidatorContainer;
import org.opengis.test.Validators;

import org.junit.Test;

import static org.opengis.test.Assert.*;
import static org.opengis.referencing.cs.AxisDirection.*;


/**
 * Tests the {@link NetcdfCRS} class using Coordinate Systems built from test files.
 * Each test defined in this class performs the following steps:
 *
 * <ul>
 *   <li>{@linkplain #open(String) Opens} a NetCDF test file specific to the test method.</li>
 *   <li>{@linkplain #wrap(CoordinateSystem, NetcdfDataset) Wraps} the NetCDF coordinate system
 *       in the GeoAPI implementation to be tested.</li>
 *   <li>{@linkplain Validators#validate(CoordinateReferenceSystem) Validates}
 *       the wrapped {@linkplain #crs}.</li>
 *   <li>Performs verifications specific to the test methods.</li>
 *   <li>{@linkplain NetcdfDataset#close() Closes} the NetCDF file.</li>
 * </ul>
 *
 * External projects can override the {@link #wrap(CoordinateSystem, NetcdfDataset)}
 * method in order to test their own NetCDF wrapper, as in the example below:
 *
 * <blockquote><pre>public class MyTest extends NetcdfCRSTest {
 *    &#64;Override
 *    protected CoordinateReferenceSystem wrap(CoordinateSystem cs, NetcdfDataset file) throws IOException {
 *        return new MyWrapper(cs);
 *    }
 *}</pre></blockquote>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class NetcdfCRSTest extends IOTestCase {
    /**
     * Tolerance factor for floating point comparison. We need enough tolerance for
     * comparing {@code float} values (used internally in some NetCDF files) with
     * {@code double} values (used by the NetCDF wrappers).
     */
    private static final double EPS = 1E-6;

    /**
     * The validator to use for validating the {@link CoordinateReferenceSystem} instance.
     * This validator is specified at construction time.
     */
    protected final CRSValidator validator;

    /**
     * The CRS object being tested. This field is set to the value returned by
     * {@link #wrap(CoordinateSystem, NetcdfDataset)} when a {@code testXXX()}
     * method is executed.
     */
    protected CoordinateReferenceSystem crs;

    /**
     * If the tested {@linkplain #crs} has a vertical component, that component.
     * Otherwise, {@code null}. This field is set when a {@code testXXX()} method
     * from this class is executed.
     */
    protected VerticalCRS verticalCRS;

    /**
     * If the tested {@linkplain #crs} has a temporal component, that component.
     * Otherwise, {@code null}. This field is set when a {@code testXXX()} method
     * from this class is executed.
     */
    protected TemporalCRS temporalCRS;

    /**
     * Creates a new test case using the default validator.
     * This constructor sets the {@link CRSValidator#enforceStandardNames} field
     * to {@code false}, since NetCDF axis names are non-standard.
     */
    public NetcdfCRSTest() {
        this(Validators.DEFAULT.clone());
    }

    /**
     * Work around for RFE #4093999 in Sun's bug database
     * ("Relax constraint on placement of this()/super() call in constructors").
     */
    private NetcdfCRSTest(final ValidatorContainer container) {
        this(new CRSValidator(container));
        // OperationValidator has recursive call to CRSValidator,
        // so we need to redirect those recursive calls as well.
        container.coordinateOperation = new OperationValidator(container);
        container.crs = validator;
        container.cs = new CSValidator(container);
        container.cs.requireMandatoryAttributes = false;
        validator.enforceStandardNames = false;
    }

    /**
     * Creates a new test case using the given validator. This constructor is provided for
     * subclasses wanting to use different validation methods. It is caller responsibility
     * to configure the given validator (for example whether to
     * {@linkplain CRSValidator#enforceStandardNames enforce standard names} or not).
     *
     * @param validator The validator to use for validating the {@link CoordinateReferenceSystem}
     *        instance.
     */
    protected NetcdfCRSTest(final CRSValidator validator) {
        this.validator = validator;
    }

    /**
     * Wraps the given NetCDF file into a GeoAPI CRS object. The default implementation
     * creates a {@link NetcdfCRS} instance. Subclasses can override this method for
     * creating their own instance.
     *
     * @param  cs   The NetCDF coordinate system to wrap.
     * @param  file The originating dataset file, or {@code null} if none.
     * @return A CRS implementation created from the given NetCDF coordinate system.
     * @throws IOException If an error occurred while wrapping the given NetCDF coordinate system.
     */
    protected CoordinateReferenceSystem wrap(final CoordinateSystem cs, final NetcdfDataset file) throws IOException {
        return NetcdfCRS.wrap(cs, file, Logger.getLogger("org.opengis.wrapper.netcdf"));
    }

    /**
     * Returns the single element from the given collection. If the given collection is null
     * or does not contains exactly one element, then an {@link AssertionError} is thrown.
     *
     * @param  <E> The type of collection elements.
     * @param  collection The collection from which to get the singleton.
     * @return The singleton element from the collection.
     */
    private static <E> E assertSingleton(final Iterable<? extends E> collection) {
        assertNotNull("Null collection.", collection);
        final Iterator<? extends E> it = collection.iterator();
        assertTrue("The collection is empty.", it.hasNext());
        final E element = it.next();
        assertFalse("The collection has more than one element.", it.hasNext());
        return element;
    }

    /**
     * Asserts that the a name or identifier of the given identified object is equals to the given
     * value. The default implementation verifies that the {@linkplain IdentifiedObject#getName()
     * name} has the following properties:
     *
     * <ul>
     *   <li>The {@linkplain ReferenceIdentifier#getCodeSpace() code space} is {@code "NetCDF"}.</li>
     *   <li>The {@linkplain ReferenceIdentifier#getCode() code} is the given expected value.</li>
     * </ul>
     *
     * Subclasses shall override this method if the NetCDF name is stored elsewhere
     * (as an {@linkplain IdentifiedObject#getIdentifiers() identifier} or an
     * {@linkplain IdentifiedObject#getAlias() alias}), or if they use a different
     * code value.
     *
     * @param expected The expected code value.
     * @param object   The identified object to verify.
     */
    protected void assertNameEquals(final String expected, final IdentifiedObject object) {
        final ReferenceIdentifier name = object.getName();
        assertNotNull("IdentifiedObject.name", name);
        assertEquals("Code space", "NetCDF", name.getCodeSpace());
        assertEquals("Code value", expected, name.getCode());
    }

    /**
     * Verifies that the given axis has the expected properties.
     *
     * @param name The expected axis name.
     * @param unit The expected axis unit.
     * @param axis The axis to verify.
     *
     * @todo The unit check is disabled for now, because the JSR-275 API can not parse the
     *       NetCDF syntax. Revisit this issue after we replaced JSR-275 API by UOM interfaces.
     */
    private void assertAxisEquals(final String name, final Unit<?> unit, final CoordinateSystemAxis axis) {
        assertNameEquals(name, axis);
        final Unit<?> axisUnit = axis.getUnit();
        if (axisUnit != null) {
            // assertEquals(name, unit, axisUnit);
        }
    }

    /**
     * Tests the geographic CRS declared in the
     * {@value org.opengis.wrapper.netcdf.IOTestCase#THREDDS} file.
     * The default implementation tests the following conditions:
     *
     * <ul>
     *   <li>The {@linkplain #crs CRS} shall be an instance of {@link GeographicCRS}.</li>
     *   <li>The axis directions shall be ({@link AxisDirection#EAST EAST}, {@link AxisDirection#NORTH NORTH}).</li>
     *   <li>The axis units shall be (<var>degrees</var>, <var>degrees</var>).</li>
     *   <li>The axis names shall be ({@code "x"}, {@code "y"}) as declared in the NetCDF file.</li>
     *   <li>The {@linkplain #crs CRS} name shall be {@code "y x"} as declared in the NetCDF file.</li>
     * </ul>
     *
     * The checks for CRS and axis names can be modified by overriding the
     * {@link #assertNameEquals(String, IdentifiedObject)} method.
     *
     * @throws IOException If an error occurred while reading the test file.
     */
    @Test
    public void testGeographic() throws IOException {
        final NetcdfDataset file = new NetcdfDataset(open(THREDDS));
        try {
            crs = wrap(assertSingleton(file.getCoordinateSystems()), file);
            validator.dispatch(crs);
            assertInstanceOf("Expected a geographic CRS.", GeographicCRS.class, crs);
            final EllipsoidalCS cs = ((GeographicCRS) crs).getCoordinateSystem();
            assertAxisDirectionsEqual("GeographicCRS.cs", cs, EAST, NORTH);
            assertAxisEquals("x", NonSI.DEGREE_ANGLE, cs.getAxis(0));
            assertAxisEquals("y", NonSI.DEGREE_ANGLE, cs.getAxis(1));
            assertNameEquals("y x", crs);
        } finally {
            file.close();
        }
    }

    /**
     * Asserts that the tested {@linkplain #crs} is an instance of {@link CompoundCRS} in which
     * the first component is of the given type and the second component is a temporal CRS. This
     * method sets the {@linkplain #temporalCRS} field to the second component and returns the
     * first component for convenience.
     *
     * @param <T> The compile-time type of the class specified by the {@code horizontalType} argument.
     * @param message The message to use in case of failure.
     * @param horizontalType The expected type of the horizontal component.
     * @param hasVerticalCRS {@code true} if the CRS is expected to have an horizontal component.
     * @return The first component, which should be the horizontal one.
     */
    private <T extends SingleCRS> T separateComponents(final String message,
            final Class<T> horizontalType, final boolean hasVerticalCRS)
    {
        validator.dispatch(crs);
        assertInstanceOf(message, CompoundCRS.class, crs);
        assertAxisDirectionsEqual(message, crs.getCoordinateSystem(),
                hasVerticalCRS ? new AxisDirection[] {EAST, NORTH, UP, FUTURE}
                               : new AxisDirection[] {EAST, NORTH, FUTURE});
        final List<CoordinateReferenceSystem> components = ((CompoundCRS) crs).getComponents();
        int n = hasVerticalCRS ? 3 : 2;
        assertEquals("CompoundCRS number of components:", n, components.size());
        CoordinateReferenceSystem candidate = components.get(--n);
        assertInstanceOf("CompoundCRS.component[last] type:", TemporalCRS.class, candidate);
        temporalCRS = (TemporalCRS) candidate;
        if (hasVerticalCRS) {
            candidate = components.get(--n);
            assertInstanceOf("CompoundCRS.component[1] type:", VerticalCRS.class, candidate);
            verticalCRS = (VerticalCRS) candidate;
        }
        candidate = components.get(--n);
        assertInstanceOf("CompoundCRS.component[0] type:", horizontalType, candidate);
        return horizontalType.cast(candidate);
    }

    /**
     * Tests the compound CRS (<cite>geographic</cite> + <cite>time</cite>) declared in the
     * {@value org.opengis.wrapper.netcdf.IOTestCase#NCEP} file.
     * The default implementation tests the following conditions:
     *
     * <ul>
     *   <li>The {@linkplain #crs CRS} shall be an instance of {@link CompoundCRS}
     *       with 2 components: a {@link GeographicCRS} and a {@link TemporalCRS}.</li>
     *   <li>The axis directions shall be ({@link AxisDirection#EAST EAST},
     *       {@link AxisDirection#NORTH NORTH}, {@link AxisDirection#FUTURE FUTURE}).</li>
     *   <li>The axis units shall be (<var>degrees</var>, <var>degrees</var>, <var>hours</var>).</li>
     *   <li>The axis names shall be ({@code "lon"}, {@code "lat"}, {@code "valtime"})
     *       as declared in the NetCDF file.</li>
     *   <li>The {@linkplain #crs CRS} name shall be {@code "valtime lat lon"}
     *       as declared in the NetCDF file.</li>
     *   <li>The {@linkplain TemporalDatum#getOrigin() temporal datum origin} shall be
     *       January 1st, 1992 at midnight UTC.</li>
     * </ul>
     *
     * The checks for CRS and axis names can be modified by overriding the
     * {@link #assertNameEquals(String, IdentifiedObject)} method.
     *
     * @throws IOException If an error occurred while reading the test file.
     */
    @Test
    public void testGeographic_XYT() throws IOException {
        final NetcdfDataset file = new NetcdfDataset(open(NCEP));
        try {
            crs = wrap(assertSingleton(file.getCoordinateSystems()), file);
            final GeographicCRS geographic = separateComponents("Expected a (geographic + time) CRS.", GeographicCRS.class, false);
            final EllipsoidalCS ellp = (geographic) .getCoordinateSystem();
            final TimeCS        time = (temporalCRS).getCoordinateSystem();
            assertAxisDirectionsEqual("GeographicCRS.cs", ellp, EAST, NORTH);
            assertAxisDirectionsEqual("TemporalCRS.cs",   time, FUTURE);
            assertAxisEquals("lon",     NonSI.DEGREE_ANGLE, ellp.getAxis(0));
            assertAxisEquals("lat",     NonSI.DEGREE_ANGLE, ellp.getAxis(1));
            assertAxisEquals("valtime", NonSI.HOUR,         time.getAxis(0));
            assertNameEquals("valtime lat lon", crs);
            assertEquals("Time since 1992-1-1 UTC", new Date(694224000000L), temporalCRS.getDatum().getOrigin());
        } finally {
            file.close();
        }
    }

    /**
     * Tests the compound CRS (<cite>projected</cite> + <cite>height</cite> + <cite>time</cite>)
     * declared in the {@value org.opengis.wrapper.netcdf.IOTestCase#CIP} file.
     * The default implementation tests the following conditions:
     *
     * <ul>
     *   <li>The {@linkplain #crs CRS} shall be an instance of {@link CompoundCRS}
     *       with 3 components: a {@link ProjectedCRS}, a {@link VerticalCRS} and
     *       a {@link TemporalCRS}.</li>
     *   <li>The axis directions shall be ({@link AxisDirection#EAST EAST},
     *       {@link AxisDirection#NORTH NORTH}, {@link AxisDirection#UP UP},
     *       {@link AxisDirection#FUTURE FUTURE}).</li>
     *   <li>The axis units shall be (<var>km</var>, <var>km</var>, <var>100 feet</var>, <var>seconds</var>).</li>
     *   <li>The axis names shall be ({@code "x0"}, {@code "y0"}, {@code "z0"}, {@code "time"})
     *       as declared in the NetCDF file.</li>
     *   <li>The {@linkplain #crs CRS} name shall be {@code "time, z0, y0, x0"}
     *       as declared in the NetCDF file.</li>
     *   <li>The {@linkplain TemporalDatum#getOrigin() temporal datum origin} shall be
     *       January 1st, 1970 at midnight UTC.</li>
     *   <li>The projection parameters shall have the values documented in the
     *       {@link IOTestCase#CIP} javadoc.</li>
     * </ul>
     *
     * The checks for CRS and axis names can be modified by overriding the
     * {@link #assertNameEquals(String, IdentifiedObject)} method.
     *
     * @throws IOException If an error occurred while reading the test file.
     */
    @Test
    public void testProjected_XYZT() throws IOException {
        final NetcdfDataset file = new NetcdfDataset(open(CIP));
        try {
            final List<CoordinateSystem> crsList = file.getCoordinateSystems();
            assertEquals("Unexpected number of NetCDF coordinate systems.", 2, crsList.size());
            crs = wrap(crsList.get(0), file);
            final ProjectedCRS projected = separateComponents("Expected a (projected + vertical + time) CRS.", ProjectedCRS.class, true);
            final CartesianCS cart = projected  .getCoordinateSystem();
            final VerticalCS  vert = verticalCRS.getCoordinateSystem();
            final TimeCS      time = temporalCRS.getCoordinateSystem();
            assertAxisDirectionsEqual("ProjectedCRS.cs", cart, EAST, NORTH);
            assertAxisDirectionsEqual("VerticalCRS.cs",  vert, UP);
            assertAxisDirectionsEqual("TemporalCRS.cs",  time, FUTURE);
            assertAxisEquals("x0",   SI.KILOMETRE,           cart.getAxis(0));
            assertAxisEquals("y0",   SI.KILOMETRE,           cart.getAxis(1));
            assertAxisEquals("z0",   NonSI.FOOT.times(0.01), vert.getAxis(0));
            assertAxisEquals("time", SI.SECOND,              time.getAxis(0));
            assertNameEquals("time z0 y0 x0", crs);
            assertEquals("Time since 1992-1-1 UTC", new Date(0L), temporalCRS.getDatum().getOrigin());
            /*
             * Following part is specific to ProjectedCRS.
             */
            final Projection  projection = projected.getConversionFromBase();
            final ParameterValueGroup  p = projection.getParameterValues();
            assertEquals("Unexpected number of parameters.",            5,     p.values().size());
            assertEquals("grid_mapping_name", "lambert_conformal_conic",       p.parameter("grid_mapping_name").stringValue());
            assertEquals("latitude_of_projection_origin",              25.0,   p.parameter("latitude_of_projection_origin").doubleValue(), EPS);
            assertEquals("longitude_of_central_meridian",             -95.0,   p.parameter("longitude_of_central_meridian").doubleValue(), EPS);
            assertEquals("earth_radius",                          6371229.000, p.parameter("earth_radius").doubleValue(), EPS);
            assertArrayEquals("standard_parallel", new double[] {25.0, 25.05}, p.parameter("standard_parallel").doubleValueList(), EPS);
        } finally {
            file.close();
        }
    }
}
