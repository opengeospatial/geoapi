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
package org.opengis.referencing.cs;

import java.util.Map;


/**
 * A 4-dimensional spatio-temporal coordinate system with time expressed as lengths.
 * The three spacial dimensions give the position of points relative to orthogonal straight axes.
 * The fourth dimension gives the position in time since an epoch.
 * All axes shall have the same length unit of measure,
 * with time expressed as the distance covered by light in the vacuum during the elapsed time.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.InertialCRS} or
 * {@link org.opengis.referencing.crs.EngineeringCRS}.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version Testbed-19
 * @since   Testbed-19
 *
 * @see CSAuthorityFactory#createMinkowskiCS(String)
 * @see CSFactory#createMinkowskiCS(Map, CoordinateSystemAxis, CoordinateSystemAxis, CoordinateSystemAxis, CoordinateSystemAxis)
 */
public interface MinkowskiCS extends CartesianCS {
}
