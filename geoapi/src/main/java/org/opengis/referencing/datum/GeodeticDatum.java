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
 * Position, scale and orientation of a geocentric Cartesian 3D coordinate system relative to the planet.
 * It may also identify a defined ellipsoid (or sphere) that approximates the shape of the planet
 * and which is centred on and aligned to this geocentric coordinate system.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see Ellipsoid
 * @see PrimeMeridian
 *
 * @see DatumAuthorityFactory#createGeodeticDatum(String)
 * @see DatumFactory#createGeodeticDatum(Map, Ellipsoid, PrimeMeridian)
 */
@UML(identifier="GeodeticReferenceFram", specification=ISO_19111)
public interface GeodeticDatum extends Datum {
    /**
     * Returns an approximation of the surface of the geoid.
     * Because of the area for which the approximation is valid
     * — traditionally regionally, but with the advent of satellite positioning often globally —
     * the ellipsoid is typically associated with Geographic and Projected <abbr>CRS</abbr>s.
     *
     * <h4>Obligation</h4>
     * If the <abbr>CRS</abbr> using this frame is associated to an {@link org.opengis.referencing.cs.EllipsoidalCS},
     * then the ellipsoid is mandatory. This is the case of all {@link org.opengis.referencing.crs.GeographicCRS}.
     * But if the <abbr>CRS</abbr> is associated to a {@link org.opengis.referencing.cs.CartesianCS} or
     * {@link org.opengis.referencing.cs.SphericalCS}, then the ellipsoid is optional.
     * However, if there is a recommended reference ellipsoid for the reference frame
     * then it is advised to return that ellipsoid.
     *
     * @return the approximation of the surface of the geoid, or {@code null} if unspecified.
     *         A non-null value is mandatory when this frame is used with ellipsoidal coordinate systems,
     *         and recommended for all other cases.
     */
    @UML(identifier="ellipsoid", obligation=CONDITIONAL, specification=ISO_19111)
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
