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
import java.util.logging.Logger;
import java.io.IOException;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dataset.CoordinateSystem;

import org.opengis.referencing.crs.CompoundCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ReferenceIdentifier;

import org.junit.Test;

import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;
import static org.opengis.referencing.cs.AxisDirection.*;


/**
 * Tests the {@link NetcdfCRS} class using Coordinate Systems built from test files.
 * <p>
 * External projects can override the {@link #wrap(CoordinateSystem)}
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
     * The CRS object being tested. This field is set to the value returned by
     * {@link #wrap(CoordinateSystem, NetcdfDataset)} when a {@code testXXX()}
     * method is executed.
     */
    protected CoordinateReferenceSystem crs;

    /**
     * Creates a new test case.
     */
    public NetcdfCRSTest() {
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
     * Asserts that the a name or identifier of the given identified object is equals to the given
     * value. The default implementation verifies that the {@linkplain IdentifiedObject#getName()
     * name} has the following properties:
     * <p>
     * <ul>
     *   <li>The {@linkplain ReferenceIdentifier#getCodeSpace() code space} is {@code "NetCDF"}.</li>
     *   <li>The {@linkplain ReferenceIdentifier#getCode() code} is the given expected value.</li>
     * </ul>
     * <p>
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
     * Tests the geographic CRS declared in the
     * {@value org.opengis.wrapper.netcdf.IOTestCase#THREDDS} file.
     *
     * @throws IOException If an error occurred while reading the test file.
     */
    @Test
    public void testGeographic() throws IOException {
        final NetcdfDataset file = new NetcdfDataset(open(THREDDS));
        try {
            final CoordinateReferenceSystem crs = wrap(getSingleton(file.getCoordinateSystems()), file);
            this.crs = crs; // For subclasses usage.
            validate(crs);
            assertInstanceOf("Expected a geographic CRS.", GeographicCRS.class, crs);
            final EllipsoidalCS cs = ((GeographicCRS) crs).getCoordinateSystem();
            assertAxisDirectionsEqual("GeographicCRS.cs", cs, EAST, NORTH);
            assertNameEquals("x", cs.getAxis(0));
            assertNameEquals("y", cs.getAxis(1));
            assertNameEquals("y x", crs);
        } finally {
            file.close();
        }
    }

    /**
     * Tests the compound CRS declared in the
     * {@value org.opengis.wrapper.netcdf.IOTestCase#NCEP} file.
     *
     * @throws IOException If an error occurred while reading the test file.
     */
    @Test
    public void testGeographicWithTime() throws IOException {
        final NetcdfDataset file = new NetcdfDataset(open(NCEP));
        try {
            final CoordinateReferenceSystem crs = wrap(getSingleton(file.getCoordinateSystems()), file);
            this.crs = crs; // For subclasses usage.
            validate(crs);
            assertInstanceOf("Expected a (geographic + time) CRS.", CompoundCRS.class, crs);
            assertAxisDirectionsEqual("Expected a (geographic + time) CRS.", crs.getCoordinateSystem(), EAST, NORTH, FUTURE);
            final List<CoordinateReferenceSystem> components = ((CompoundCRS) crs).getComponents();
            assertEquals("Expected a (geographic + time) CRS.", 2, components.size());
            final CoordinateReferenceSystem geographic = components.get(0);
            final CoordinateReferenceSystem temporal   = components.get(1);
            assertInstanceOf("CompoundCRS.component[0]", GeographicCRS.class, geographic);
            assertInstanceOf("CompoundCRS.component[1]", TemporalCRS.class,   temporal);
            final EllipsoidalCS ellp = ((GeographicCRS) geographic).getCoordinateSystem();
            final TimeCS        time = ((TemporalCRS)   temporal)  .getCoordinateSystem();
            assertAxisDirectionsEqual("GeographicCRS.cs", ellp, EAST, NORTH);
            assertAxisDirectionsEqual("TemporalCRS.cs",   time, FUTURE);
            assertNameEquals("lon",     ellp.getAxis(0));
            assertNameEquals("lat",     ellp.getAxis(1));
            assertNameEquals("valtime", time.getAxis(0));
            assertNameEquals("valtime lat lon", crs);
            assertEquals("Time since 1992-1-1 UTC", new Date(694224000000L), ((TemporalCRS) temporal).getDatum().getOrigin());
        } finally {
            file.close();
        }
    }
}
