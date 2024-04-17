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
package org.opengis.referencing.operation;

import org.opengis.referencing.crs.GeodeticCRS;
import org.opengis.referencing.crs.ProjectedCRS;


/**
 * A conversion from geodetic (<var>longitude</var>,<var>latitude</var>) coordinates
 * to Cartesian (<var>x</var>,<var>y</var>) coordinates.
 * Ellipsoidal height plays no role in the conversion process,
 * but may pass-through in a three-dimensional Cartesian <abbr>CS</abbr>.
 *
 * @departure extension
 *   This interface is not part of the OGC/ISO abstract specification.
 *   It has been added in GeoAPI 3.0 for making possible to restrict the type of
 *   <abbr>CRS</abbr> associated to this conversion, and for use as a filter for
 *   {@linkplain org.opengis.referencing.operation.MathTransformFactory#getAvailableMethods(Class)
 *   listing the map projection methods} available in an implementation.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 4.0
 * @since   1.0
 *
 * @see org.opengis.referencing.crs.ProjectedCRS
 */
public interface Projection extends Conversion {
    /**
     * Returns the source CRS, which must be geodetic or {@code null}.
     * This type <abbr>CRS</abbr> is usually {@link GeographicCRS}, but not necessarily.
     *
     * @see ProjectedCRS#getBaseCRS()
     *
     * @since 4.0
     */
    @Override
    GeodeticCRS getSourceCRS();

    /**
     * Returns the target CRS, which must be projected or {@code null}.
     *
     * @since 4.0
     */
    @Override
    ProjectedCRS getTargetCRS();
}
