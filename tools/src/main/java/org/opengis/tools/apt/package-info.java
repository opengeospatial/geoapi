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

/**
 * Annotation processing tools for processing GeoAPI after compilation. Those tools are
 * used mostly for javadoc generation. They are not part of normal GeoAPI distributions.
 *
 * <p>The {@link org.opengis.tools.apt.IndexGenerator} processor is used for regenerating
 * the {@code geoapi/src/main/javadoc/content.html} file.
 * See its class javadoc for usage instruction.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.0
 */
package org.opengis.tools.apt;
