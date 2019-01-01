/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import java.util.Map;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.SphericalCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 3-dimensional coordinate reference system with the origin at the approximate centre of mass of the earth.
 * A geocentric CRS deals with the earth's curvature by taking a 3-dimensional spatial view, which obviates
 * the need to model the earth's curvature.
 *
 * <p>This type of CRS can be used with coordinate systems of type
 * {@link org.opengis.referencing.cs.CartesianCS Cartesian} or
 * {@link org.opengis.referencing.cs.SphericalCS Spherical}.</p>
 *
 * @departure historic
 *   This interface is kept conformant with the specification published in 2003. The 2007 revision
 *   of ISO 19111 removed the {@code SC_GeographicCRS} and {@code SC_GeocentricCRS} types,
 *   handling both using the {@code SC_GeodeticCRS} parent type.
 *   GeoAPI keeps them since the distinction between those two types is in wide use.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createGeocentricCRS(String)
 * @see CRSFactory#createGeocentricCRS(Map, GeodeticDatum, CartesianCS)
 * @see CRSFactory#createGeographicCRS(Map, GeodeticDatum, EllipsoidalCS)
 */
@UML(identifier="SC_GeocentricCRS", specification=ISO_19111, version=2003)
public interface GeocentricCRS extends GeodeticCRS {
    /**
     * Returns the coordinate system, which shall be {@linkplain CartesianCS Cartesian}
     * or {@linkplain SphericalCS spherical}.
     *
     * @return the Cartesian or spherical coordinate system.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystem getCoordinateSystem();
}
