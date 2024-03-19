/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2016-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.datum;

import java.util.Map;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.ISO_19111_2;


/**
 * A textual description and/or a set of parameters identifying a particular reference surface used as
 * the origin of a parametric coordinate system, including its position with respect to the Earth.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see DatumAuthorityFactory#createParametricDatum(String)
 * @see DatumFactory#createParametricDatum(Map)
 */
@UML(identifier="CD_ParametricDatum", specification=ISO_19111_2)
public interface ParametricDatum extends Datum {
}
