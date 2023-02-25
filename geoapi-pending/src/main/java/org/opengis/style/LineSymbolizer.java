/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
 * @since GeoAPI 2.2
 */
@XmlElement("LineSymbolizer")
public interface LineSymbolizer extends Symbolizer {
    /**
     * Returns the object containing all the information necessary to draw
     * styled lines.
     * @return Stroke object, contain information about how to draw lines
     */
    @XmlElement("Stroke")
    Stroke getStroke();

    /**
     * PerpendicularOffset allows to draw lines in parallel to the original geometry. For
     * complex line strings these parallel lines have to be constructed so that the distance
     * between original geometry and drawn line stays equal. This construction can result in
     * drawn lines that are actually smaller or longer than the original geometry.
     *
     * The distance is in uoms and is positive to the left-hand side of the line string. Negative
     * numbers mean right. The default offset is 0.
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
