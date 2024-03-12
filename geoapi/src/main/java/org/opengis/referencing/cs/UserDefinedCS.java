/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
import static org.opengis.annotation.Specification.*;


/**
 * A 2- or 3-dimensional coordinate system that consists of any combination of axes not covered by any other CS type.
 *
 * <div class="note"><b>Example:</b>
 * a multilinear coordinate system which contains one coordinate axis that may have any 1-D shape which has no
 * intersections with itself. This non-straight axis is supplemented by one or two straight axes to complete a
 * 2 or 3 dimensional coordinate system. The non-straight axis is typically incrementally straight or curved.
 * </div>
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.EngineeringCRS}.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see CSFactory#createUserDefinedCS(Map, CoordinateSystemAxis, CoordinateSystemAxis)
 * @see CSFactory#createUserDefinedCS(Map, CoordinateSystemAxis, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="CS_UserDefinedCS", specification=ISO_19111, version=2007)
public interface UserDefinedCS extends CoordinateSystem {
}
