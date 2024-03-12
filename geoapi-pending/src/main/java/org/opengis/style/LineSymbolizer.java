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
package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.Expression;


/**
 * Gives directions for how to draw lines on a map.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 */
@XmlElement("LineSymbolizer")
public interface LineSymbolizer extends Symbolizer {
    /**
     * Returns the object containing all the information necessary to draw styled lines.
     *
     * @return information about how to draw lines.
     */
    @XmlElement("Stroke")
    Stroke getStroke();

    /**
     * A distance to apply for drawing lines in parallel to the original geometry.
     * These parallel lines have to be constructed so that the distance between
     * original geometry and drawn line stays equal.
     * This construction may result in drawn lines that are
     * actually smaller or longer than the original geometry.
     *
     * <p>The distance units of measurement is given by {@link #getUnitOfMeasure()}.
     * The value is positive to the left-hand side of the line string.
     * Negative numbers mean right. The default offset is 0.</p>
     *
     * @return distance to apply for drawing lines in parallel to the original geometry.
     */
    @XmlElement("PerpendicularOffset")
    Expression getPerpendicularOffset();

    /**
     * Calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
