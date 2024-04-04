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
 * A 1-dimensional coordinate system that consists of the points that lie on the single axis described.
 * The associated coordinate is the distance – with or without offset – from the origin point, specified
 * through the datum definition, to the point along the axis.
 *
 * <div class="note"><b>Example:</b>
 * usage of the line feature representing a road to describe points on or along that road.
 * </div>
 *
 * <p>This type of <abbr>CS</abbr> can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.EngineeringCRS}.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CSFactory#createLinearCS(Map, CoordinateSystemAxis)
 */
@UML(identifier="LinearCS", specification=ISO_19111)
public interface LinearCS extends CoordinateSystem {
    /**
     * Returns the number of dimensions, which is 1 for this type of coordinate system.
     *
     * @return always 1.
     */
    @Override
    default int getDimension() {
        return 1;
    }
}
