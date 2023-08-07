/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
import java.util.Optional;


/**
 * Origin and orientation of an inertial CRS.
 * An inertial reference frame defines the precise location and orientation in 3- or 4-dimensional space
 * of a defined ellipsoid (or sphere), or of a Cartesian (3D case) or Minkowski (4D case) coordinate system
 * centered in this ellipsoid (or sphere).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version Testbed-19
 * @since   Testbed-19
 *
 * @see CelestialBody
 * @see Ellipsoid
 * @see PrimeMeridian
 *
 * @see DatumAuthorityFactory#createInertialReferenceFrame(String)
 * @see DatumFactory#createInertialReferenceFrame(Map, Ellipsoid, PrimeMeridian)
 */
public interface InertialReferenceFrame extends Datum {
    /**
     * Returns the ellipsoid.
     *
     * @return the ellipsoid.
     */
    default Optional<Ellipsoid> getEllipsoid() {
        return Optional.empty();
    }

    /**
     * Returns the prime direction.
     * The prime direction is defined relative to a distant feature such as a star.
     * For example, the Ecliptic coordinate reference system defines the primary direction by the March equinox.
     *
     * @return the prime direction.
     */
    default Optional<PrimeMeridian> getPrimeDirection() {
        return Optional.empty();
    }
}
