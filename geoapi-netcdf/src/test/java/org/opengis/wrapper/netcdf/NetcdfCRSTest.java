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

import org.opengis.referencing.crs.CompoundCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import org.junit.Test;

import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;
import static org.opengis.wrapper.netcdf.NetcdfMetadataTest.*;


/**
 * Tests the {@link NetcdfCRS} class using coordinate system built from test files.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class NetcdfCRSTest {
    /**
     * Tests the geographic CRS declared in a NetCDF file.
     *
     * @throws IOException If an error occurred while reading the test file.
     */
    @Test
    public void testGeographic() throws IOException {
        final NetcdfDataset file = new NetcdfDataset(open(BINARY_FILE));
        final CoordinateReferenceSystem crs = NetcdfCRS.wrap(getSingleton(file.getCoordinateSystems()));
        validate(crs);
        assertInstanceOf("Expected a (geographic + time) CRS.", CompoundCRS.class, crs);
        assertEquals    ("Expected a (geographic + time) CRS.", 3, crs.getCoordinateSystem().getDimension());
        final List<CoordinateReferenceSystem> components = ((CompoundCRS) crs).getComponents();
        assertEquals(2, components.size());
        assertInstanceOf("Expected a (geographic + time) CRS.", GeographicCRS.class, components.get(0));
        assertInstanceOf("Expected a (geographic + time) CRS.", TemporalCRS.class,   components.get(1));
        file.close();
    }
}
