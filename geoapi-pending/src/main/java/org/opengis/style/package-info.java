/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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
 * Symbology encoding for styling map data independently of their source.
 * The interfaces in this package are derived from
 * OGC 05-077r4 — Symbology Encoding Implementation Specification 1.1.0.
 * That document defines an XML encoding that can be used for styling feature and coverage data.
 * The interfaces include different kinds of symbolizers.
 *
 * <p>Each style style has <var>N</var> rules, and each rule has <var>M</var> symbolizers:</p>
 * <p><img src="doc-files/rules.jpg" alt="Associations from style to symbolizer"/></p>
 *
 * <p>Different symbolizers are available for each supported data type:</p>
 * <p><img src="doc-files/symbolizers.jpg" alt="Symbolizers types"/></p>
 *
 * <h2>Future evolution</h2>
 * This package is <em>not</em> part of GeoAPI release and may change deeply in a future development.
 * At the time that this package was created, no abstract model was available and the only source of
 * class definitions was the XML encoding. But later on, the ISO 19117:2012 specification has been
 * published with the UML of an abstract model that could be used for deriving the Java interfaces.
 * Furthermore as of 2023, various OGC working groups are actively working on new style and symbology API.
 * All those works are different than the XML schema, especially ISO 19117:2012 which is quite different.
 * Consequently the GeoAPI style interfaces may change and this <code>org.opengis.style</code> package in
 * GeoAPI-pending should <em>not</em> be considered ready for any formal release.
 */
package org.opengis.style;
