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
package org.opengis.referencing.datum;

import java.util.Map;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Location and orientation in 3-dimensional space of an ellipsoid (or sphere) that approximates the shape of the earth.
 * A geodetic reference frame is used with three-dimensional or horizontal (two-dimensional) coordinate reference systems,
 * and requires an ellipsoid definition and a prime meridian definition.
 * This frame can also be used for a Cartesian coordinate system centered in the ellipsoid (or sphere).
 * It is used to describe large portions of the planet's surface up to the entire planet's surface.
 *
 * <p>One ellipsoid can be specified with every geodetic datum, even if the ellipsoid is not used computationally.
 * The latter may be the case when a Geocentric <abbr>CRS</abbr> is used,
 * e.g., in the calculation of satellite orbit and ground positions from satellite observations.
 * Although use of a Geocentric <abbr>CRS</abbr> apparently obviates the need of an ellipsoid,
 * the ellipsoid usually played a role in the determination of the associated geodetic datum.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.0
 * @since   1.0
 *
 * @see Ellipsoid
 * @see PrimeMeridian
 *
 * @see DatumAuthorityFactory#createGeodeticDatum(String)
 * @see DatumFactory#createGeodeticDatum(Map, Ellipsoid, PrimeMeridian)
 */
@UML(identifier="CD_GeodeticDatum", specification=ISO_19111, version=2007)
public interface GeodeticDatum extends Datum {
    /**
     * Returns an approximation of the surface of the geoid.
     * Because of the area for which the approximation is valid
     * — traditionally regionally, but with the advent of satellite positioning often globally —
     * the ellipsoid is typically associated with Geographic and Projected <abbr>CRS</abbr>s.
     *
     * <p>An ellipsoid is defined either by its semi-major axis and inverse flattening,
     * or by its semi-major axis and semi-minor axis.
     * For some applications, for example small-scale mapping in atlases,
     * a spherical approximation of the geoid's surface is used,
     * requiring only the radius of the sphere to be specified.</p>
     *
     * @return the approximation of the surface of the geoid.
     */
    @UML(identifier="ellipsoid", obligation=MANDATORY, specification=ISO_19111)
    Ellipsoid getEllipsoid();

    /**
     * Returns the origin from which longitude values are specified.
     * Most geodetic reference frames use Greenwich as their prime meridian.
     *
     * @return the origin from which longitude values are specified.
     */
    @UML(identifier="primeMeridian", obligation=MANDATORY, specification=ISO_19111)
    PrimeMeridian getPrimeMeridian();
}
