/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Random;
import ucar.unidata.geoloc.projection.Mercator;

import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.referencing.operation.TransformException;
import org.opengis.test.referencing.TransformTestCase;

import org.junit.Test;

import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests the {@link NetcdfProjection} class. The projected values correctness
 * (external consistency) is not verified - only internal consistency is verified.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class NetcdfProjectionTest extends TransformTestCase {
    /**
     * Creates a new test case initialized with a default NetCDF projection.
     */
    public NetcdfProjectionTest() {
        final Mercator projection = new Mercator();
        projection.setName("Default Mercator projection");
        transform = new NetcdfProjection(projection, null, null);
        tolerance = 1E-10;
        isDerivativeSupported = false;
    }

    /**
     * Tests the consistency of various {@code transform} methods. This method runs the
     * {@link #verifyInDomain(double[], double[], int[], Random)} test method using a
     * simple {@link Mercator} implementation.
     *
     * @throws TransformException Should never happen.
     */
    @Test
    public void testConsistency() throws TransformException {
        validate(transform);
        verifyInDomain(new double[] {-180, -80}, // Minimal ordinate values to test.
                       new double[] {+180, +80}, // Maximal ordinate values to test.
                       new int[]    { 360, 160}, // Number of points to test.
                       new Random(216919106));
    }

    /**
     * Tests projection name and classname.
     */
    @Test
    public void testNames() {
        final NetcdfProjection projection = (NetcdfProjection) transform;
        assertEquals("Default Mercator projection", projection.getName().getCode());
        assertEquals("Mercator", projection.getMethod().getName().getCode());
    }

    /**
     * Tests the {@link NetcdfProjection#getDomainOfValidity()} method.
     * In NetCDF 4.2, the declared bounding box was approximatively
     * west=-152.85, east=-57.15, south=-43.1, north=43.1. However
     * we presume that this bounding box may change in the future.
     */
    @Test
    public void testDomainOfValidity() {
        final NetcdfProjection projection = (NetcdfProjection) transform;
        final GeographicBoundingBox box = (GeographicBoundingBox)
                projection.getDomainOfValidity().getGeographicElements().iterator().next();
        assertBetween("westBoundLongitude", -180, -152, box.getWestBoundLongitude());
        assertBetween("eastBoundLongitude",  -58, +180, box.getEastBoundLongitude());
        assertBetween("southBoundLatitude",  -90,  -43, box.getSouthBoundLatitude());
        assertBetween("northBoundLatitude",   43,  +90, box.getNorthBoundLatitude());
    }
}
