/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Set;
import org.opengis.util.FactoryException;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests factory consistency.
 */
public class SimpleAuthorityFactoryTest {
    /**
     * Creates a new test case.
     */
    public SimpleAuthorityFactoryTest() {
    }

    /**
     * Tests the creation of geographic CRS.
     *
     * @throws FactoryException if a CRS creation failed.
     */
    @Test
    public void testGeographic() throws FactoryException {
        final CRSAuthorityFactory factory = SimpleAuthorityFactory.provider();
        final Set<String> codes = factory.getAuthorityCodes(GeographicCRS.class);
        assertEquals(2, codes.size());
        for (final String code : codes) {
            assertNotNull(factory.createGeographicCRS(code));
        }
    }

    /**
     * Tests the creation of vertical CRS.
     *
     * @throws FactoryException if a CRS creation failed.
     */
    @Test
    public void testVertical() throws FactoryException {
        final CRSAuthorityFactory factory = SimpleAuthorityFactory.provider();
        final Set<String> codes = factory.getAuthorityCodes(VerticalCRS.class);
        assertEquals(1, codes.size());
        for (final String code : codes) {
            assertNotNull(factory.createVerticalCRS(code));
        }
    }

    /**
     * Tests the creation of temporal CRS.
     *
     * @throws FactoryException if a CRS creation failed.
     */
    @Test
    public void testTemporal() throws FactoryException {
        final CRSAuthorityFactory factory = SimpleAuthorityFactory.provider();
        final Set<String> codes = factory.getAuthorityCodes(TemporalCRS.class);
        assertEquals(1, codes.size());
        for (final String code : codes) {
            assertNotNull(factory.createTemporalCRS(code));
        }
    }

    /**
     * Tests the creation of all CRS.
     *
     * @throws FactoryException if a CRS creation failed.
     */
    @Test
    public void testAll() throws FactoryException {
        final CRSAuthorityFactory factory = SimpleAuthorityFactory.provider();
        final Set<String> codes = factory.getAuthorityCodes(IdentifiedObject.class);
        assertEquals(4, codes.size());
        for (final String code : codes) {
            assertNotNull(factory.createCoordinateReferenceSystem(code));
        }
    }
}
