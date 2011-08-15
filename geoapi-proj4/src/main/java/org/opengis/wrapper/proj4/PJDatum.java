/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.wrapper.proj4;

import java.util.Date;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.measure.unit.NonSI;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

import org.proj4.PJ;
import org.opengis.util.FactoryException;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.metadata.extent.Extent;


/**
 * Wraps the <a href="http://proj.osgeo.org/">Proj4</a> {@code PJ} native data structure
 * in a geodetic datum. The PJ structure combines datum, ellipsoid and prime meridian
 * information.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJDatum extends PJ implements GeodeticDatum, PrimeMeridian, Ellipsoid {
    /**
     * The datum or ellipsoid name.
     */
    private final ReferenceIdentifier name;

    /**
     * Creates a new {@code PJ} structure from the given Proj4 data.
     *
     * @param definition The Proj4 definition string.
     */
    PJDatum(final ReferenceIdentifier name, final String definition) throws FactoryException {
        super(definition);
        this.name = name;
    }

    /**
     * Returns the name given at construction time.
     */
    @Override
    public ReferenceIdentifier getName() {
        return name;
    }

    /*
     * Various GeoAPI method having no direct mapping in the Proj4 library.
     */
    @Override public Collection<GenericName>  getAlias()            {return Collections.emptySet();}
    @Override public Set<ReferenceIdentifier> getIdentifiers()      {return Collections.emptySet();}
    @Override public InternationalString      getScope()            {return null;}
    @Override public InternationalString      getRemarks()          {return null;}
    @Override public InternationalString      getAnchorPoint()      {return null;}
    @Override public Date                     getRealizationEpoch() {return null;}
    @Override public Extent                   getDomainOfValidity() {return null;}

    /**
     * Returns the ellipsoid associated with the geodetic datum.
     */
    public Ellipsoid getEllipsoid() {
        return this;
    }

    /**
     * Returns the prime meridian associated with the geodetic datum.
     */
    public PrimeMeridian getPrimeMeridian() {
        return this;
    }

    /**
     * Returns the axis unit, which is assumed metres in the case of the Proj4 library.
     */
    @Override
    public Unit<Length> getAxisUnit() {
        return SI.METRE;
    }

    /**
     * Returns the units of the prime meridian. All angular units are converted from
     * radians to degrees in those wrappers.
     */
    @Override
    public Unit<Angle> getAngularUnit() {
        return NonSI.DEGREE_ANGLE;
    }

    /**
     * Returns {@code true} unconditionally since the inverse excentricy squared in definitive
     * in the Proj4 library, and the excentricity is directly related to the flattening.
     */
    @Override
    public boolean isIvfDefinitive() {
        return true;
    }

    /**
     * Throws unconditionally an exception since there is no WKt formatting provided by
     * the Proj4 library.
     */
    @Override
    public String toWKT() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
