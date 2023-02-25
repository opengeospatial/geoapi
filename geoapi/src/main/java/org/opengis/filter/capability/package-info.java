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

/**
 * Metadata about the specific elements that a particular implementation supports.
 * A client application can inspect the filter capabilities metadata and be able
 * to determine which operators and types a filter expression processor supports.
 *
 * <p>Filter capabilities are divided into five categories:
 * {@linkplain IdCapabilities id capabilities},
 * {@linkplain ScalarCapabilities scalar capabilities},
 * {@linkplain SpatialCapabilities spatial capabilities},
 * {@linkplain TemporalCapabilities temporal capabilities}
 * and the ability to test for the existence or absence of a named property.</p>
 *
 * @version 3.1
 * @since   3.1
 */
package org.opengis.filter.capability;
