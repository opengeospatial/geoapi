/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2011 Open Geospatial Consortium, Inc.
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
 * A set of base types from {@linkplain org.opengis.annotation.Specification#ISO_19103 ISO 19103}
 * which can not be mapped directly from Java, plus utilities.
 *
 * <h3>Names and Namespaces</h3>
 * <p align="justify">The job of a "name" in the context of ISO 19103 is to associate that name
 * with an {@link java.lang.Object}.  Examples given are <cite>objects</cite>: which form namespaces
 * for their attributes, and <cite>Schema</cite>: which form namespaces for their components.
 * A straightforward and natural use of the namespace structure defined in 19103 is the translation
 * of given names into specific storage formats.  XML has different naming rules than shapefiles,
 * and both are different than NetCDF.  This common framework can easily be harnessed to impose
 * constraints specific to a particular application without requiring that a separate implementation
 * of namespaces be provided for each format.</p>
 *
 * <h3>Records and Schemas</h3>
 * <p align="justify">Records and Schemas are similar to a {@code struct} in C/C++, a table in SQL,
 * a {@code RECORD} in Pascal, or an attribute-only class in Java if it were stripped of all notions
 * of inheritance.  They are organized into named collections called Schemas. Both records and schemas
 * behave as dictionaries for their members and are similar to "packages" in Java.</p>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Jesse Crossley (SYS Technologies)
 * @author  Bryce Nordgren (USDA)
 * @version 3.0
 * @since   1.0
 */
package org.opengis.util;
