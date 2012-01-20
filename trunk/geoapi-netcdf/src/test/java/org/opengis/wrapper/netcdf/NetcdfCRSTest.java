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

import java.util.List;
import java.io.IOException;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dataset.CoordinateSystem;

import org.opengis.referencing.crs.CompoundCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import org.junit.Test;

import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests the {@link NetcdfCRS} class using Coordinate Systems built from test files.
 * <p>
 * External projects can override the {@link #wrap(CoordinateSystem)}
 * method in order to test their own NetCDF wrapper.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class NetcdfCRSTest extends IOTestCase {
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
     * @param  cs The NetCDF coordinate system to wrap.
     * @return A CRS implementation created from the given NetCDF coordinate system.
     * @throws IOException If an error occurred while wrapping the given NetCDF coordinate system.
     */
    protected CoordinateReferenceSystem wrap(final CoordinateSystem cs) throws IOException {
        return NetcdfCRS.wrap(cs);
    }

    /**
     * Tests the geographic CRS declared in the
     * {@value org.opengis.wrapper.netcdf.IOTestCase#GEOTIME_NC} file.
     *
     * @throws IOException If an error occurred while reading the test file.
     */
    @Test
    public void testGeographicWithTime() throws IOException {
        final NetcdfDataset file = new NetcdfDataset(open(GEOTIME_NC));
        try {
            final CoordinateReferenceSystem crs = wrap(getSingleton(file.getCoordinateSystems()));
            validate(crs);
            assertInstanceOf("Expected a (geographic + time) CRS.", CompoundCRS.class, crs);
            assertEquals    ("Expected a (geographic + time) CRS.", 3, crs.getCoordinateSystem().getDimension());
            final List<CoordinateReferenceSystem> components = ((CompoundCRS) crs).getComponents();
            assertEquals(2, components.size());
            assertInstanceOf("Expected a (geographic + time) CRS.", GeographicCRS.class, components.get(0));
            assertInstanceOf("Expected a (geographic + time) CRS.", TemporalCRS.class,   components.get(1));
        } finally {
            file.close();
        }
    }
}
