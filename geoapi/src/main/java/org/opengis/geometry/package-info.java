/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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

/**
 * Core interfaces needed to investigate coordinate-defined geometry.
 * Those interfaces inherit an optional association to a
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system}.
 * All {@linkplain org.opengis.geometry.DirectPosition direct positions} exposed through the interfaces
 * shall be in the coordinate reference system of the geometric object accessed.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Axel Francois (LSIS/Geomatys)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.geometry;
