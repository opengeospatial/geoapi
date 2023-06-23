/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
 * Holds the information that indicates how to draw the lines and the interior of polygons.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 */
@XmlElement("PolygonSymbolizer")
public interface PolygonSymbolizer extends Symbolizer {
    /**
     * Returns the object containing all the information necessary to draw
     * styled lines.  This is used for the edges of polygons.
     */
    @XmlElement("Stroke")
    Stroke getStroke();

    /**
     * Returns the object that holds the information about how the interior of
     * polygons should be filled.  This may be null if the polygons are not to
     * be filled at all.
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * The Displacement gives the X and Y displacements from the original geometry. This
     * element may be used to avoid over-plotting of multiple PolygonSymbolizers for one
     * geometry or supplying "shadows" of polygon gemeotries. The displacements are in units
     * of pixels above and to the right of the point. The default displacement is X=0, Y=0.
     */
    @XmlElement("Displacement")
    Displacement getDisplacement();

    /**
     * PerpendicularOffset works as defined for LineSymbolizer, allowing to draw polygons
     * smaller or larger than their actual geometry. The distance is in uoms and is positive to the
     * outside of the polygon. Negative numbers mean drawing the polygon smaller. The default
     * offset is 0.
     */
    @XmlElement("PerpendicularOffset")
    Expression getPerpendicularOffset();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
