/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A coordinate reference system that is defined by its coordinate conversion from another
 * coordinate reference system but is not a projected coordinate reference system. This
 * category includes coordinate reference systems derived from a {@linkplain ProjectedCRS
 * projected coordinate reference system}.
 *
 * @departure integration
 *   ISO 19111 defines a <code>DerivedCRSType</code> code list. The later is omitted in GeoAPI since
 *   Java expressions like <code>(baseCRS instanceof FooCRS)</code> provides the same capability
 *   with more flexibility.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="SC_DerivedCRS", specification=ISO_19111)
public interface DerivedCRS extends GeneralDerivedCRS {
}
