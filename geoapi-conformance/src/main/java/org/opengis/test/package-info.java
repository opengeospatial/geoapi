/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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
 * Base classes for validators and test suites for all GeoAPI objects. The
 * {@link org.opengis.test.Validators} class provides static {@code validate} methods that can be
 * used for validating existing instances of various kind. Those methods can be conveniently
 * imported in a test class with the following Java statement:
 *
 * <blockquote><code>
 * import static org.opengis.test.Validators.*;
 * </code></blockquote>
 *
 * <p>No other validator class need to be considered, unless the validation process needs
 * to be customized.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
package org.opengis.test;
