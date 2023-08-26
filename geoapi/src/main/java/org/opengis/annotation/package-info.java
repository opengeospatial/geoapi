/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
 * Set of annotations mapping <a href="http://www.geoapi.org">GeoAPI</a>
 * interfaces to <a href="http://www.opengeospatial.org">Open Geospatial</a> UML or XML schemas.
 * The most frequently used annotation is {@link org.opengis.annotation.UML}, which documents
 * the standard in which are defined the type or method, the original name of the element and
 * the obligation level of the type if other than the default mandatory level of obligation.
 * As an example, the annotation for the {@link org.opengis.referencing.crs.ProjectedCRS}
 * interface appears in the source code as:
 *
 * {@snippet lang="java" :
 * @UML(identifier = "ProjectedCRS", specification = ISO_19111)
 * }
 *
 * These annotations are available at runtime by Java introspection. This is useful, for example,
 * when code needs to marshall data using the name defined by the ISO standard rather than the
 * GeoAPI name.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.0
 */
package org.opengis.annotation;
