/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
 * A 3-dimensional <abbr>CRS</abbr> with the origin at the approximate centre of mass of the earth.
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
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
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
