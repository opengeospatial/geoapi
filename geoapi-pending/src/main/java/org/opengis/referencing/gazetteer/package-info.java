/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2016-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
 * Mapping between geographic identifiers and locations, either as descriptions or coordinates.
 * This package deals only with spatial referencing by <em>geographic identifiers</em>,
 * as specified in the {@linkplain org.opengis.annotation.Specification#ISO_19112 ISO 19112} standard.
 * Spatial referencing <em>by coordinates</em> (ISO 19111) is the subject of another package,
 * namely {@link org.opengis.referencing.crs}.
 *
 * <p>Geographic identifiers are location descriptors such as country name, postal code or grid indexes.
 * Gazetteers provide linking mechanism between referencing using geographic identifiers (this package)
 * and referencing using coordinates ({@link org.opengis.referencing.crs} package).</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.referencing.gazetteer;
