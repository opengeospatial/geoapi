/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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

import org.opengis.referencing.datum.InertialReferenceFrame;


/**
 * A 2-, 3- or 4-dimensional coordinate reference system with axes at fixed position relative to stars.
 *
 * <p>This type of CRS can be used with coordinate systems of type
 * {@link org.opengis.referencing.cs.EllipsoidalCS},
 * {@link org.opengis.referencing.cs.SphericalCS},
 * {@link org.opengis.referencing.cs.CartesianCS} or
 * {@link org.opengis.referencing.cs.MinkowskiCS}.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version Testbed-19
 * @since   Testbed-19
 *
 * @see CRSAuthorityFactory#createInertialCRS(String)
 * @see CRSFactory#createInertialCRS(Map, InertialReferenceFrame, CoordinateSystem)
 */
public interface InertialCRS extends SingleCRS {
    /**
     * Returns the datum, which shall be inertial.
     */
    @Override
    InertialReferenceFrame getDatum();
}
