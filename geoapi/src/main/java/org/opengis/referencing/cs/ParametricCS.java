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
package org.opengis.referencing.cs;

import java.util.Map;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.ISO_19111_2;


/**
 * A 1-dimensional coordinate system in which a physical property or function is used as the dimension.
 * This coordinate system uses parameter values or functions to describe the position of a point.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.ParametricCRS}.</p>
 *
 * @author  Johann Sorel (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see CSAuthorityFactory#createParametricCS(String)
 * @see CSFactory#createParametricCS(Map, CoordinateSystemAxis)
 */
@UML(identifier="CS_ParametricCS", specification=ISO_19111_2)
public interface ParametricCS extends CoordinateSystem {
}
