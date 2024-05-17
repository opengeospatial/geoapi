/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Arrays;
import java.util.Set;
import java.util.LinkedHashSet;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.example.metadata.SimpleCitation;


/**
 * A simple implementation of factory capable to create objects from authority codes.
 */
public class SimpleAuthorityFactory implements CRSAuthorityFactory {
    /**
     * The shared instance of this factory.
     */
    private static final CRSAuthorityFactory INSTANCE = new SimpleAuthorityFactory();

    /**
     * Creates a new factory.
     */
    protected SimpleAuthorityFactory() {
    }

    /**
     * Returns a shared instance of this factory.
     *
     * <p><b>API note:</b>
     * This method is invoked by {@link java.util.ServiceLoader} when this factory is fetched as a service.</p>
     *
     * @return a shared instance of this factory.
     */
    public static CRSAuthorityFactory provider() {
        return INSTANCE;
    }

    /**
     * Returns "GeoAPI example" as a pseudo-authority.
     *
     * @return "GeoAPI example".
     */
    @Override
    public Citation getAuthority() {
        return SimpleCitation.GEOAPI;
    }

    /**
     * Returns "GeoAPI example" as a pseudo-vendor.
     *
     * @return "GeoAPI example".
     */
    @Override
    public Citation getVendor() {
        return SimpleCitation.GEOAPI;
    }

    /**
     * Returns the supported codes for CRS of the specified type.
     *
     * @param  type  the type of referencing object for which to get authority codes.
     * @return the set of authority codes for referencing objects of the given type.
     */
    @Override
    public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) {
        final Set<String> codes = new LinkedHashSet<>();
        if (type.isAssignableFrom(GeographicCRS.class)) {
            codes.addAll(Arrays.asList("4326", "4047"));
        }
        if (type.isAssignableFrom(VerticalCRS.class)) {
            codes.add("5714");
        }
        if (type.isAssignableFrom(TemporalCRS.class)) {
            codes.add("JulianDate");
        }
        return codes;
    }

    /**
     * Returns a coordinate reference system for the given code.
     *
     * @param  code  value allocated by this pseudo-authority.
     * @return the coordinate reference system for the given code.
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     */
    @Override
    public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws NoSuchAuthorityCodeException {
        if (code.equalsIgnoreCase("JulianDate")) {
            return SimpleCRS.Temporal.JULIAN;
        }
        NumberFormatException cause = null;
        try {
            switch (Integer.parseInt(code)) {
                case 4326: return SimpleCRS.Geographic.WGS84;
                case 4047: return SimpleCRS.Geographic.SPHERE;
                case 5714: return SimpleCRS.Vertical.MSL;
            }
        } catch (NumberFormatException e) {
            cause = e;
        }
        var e = new NoSuchAuthorityCodeException("Unknown CRS code" + code, "GeoAPI example", code);
        if (cause != null) e.initCause(cause);
        throw e;
    }
}
