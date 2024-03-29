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
 * Instructions about where and how a text label should be rendered relative to a line.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Ian Turton, CCG
 */
@XmlElement("LinePlacement")
public interface LinePlacement extends LabelPlacement {
    /**
     * The PerpendicularOffset element of a LinePlacement gives the perpendicular distance
     * away from a line to draw a label.
     *
     * The distance is in uoms and is positive to the left-hand side of the line string.
     * Negative numbers mean right. The default offset is 0.
     */
    @XmlElement("PerpendicularOffset")
    Expression getPerpendicularOffset();

    /**
     * InitialGap specifies how far away the first graphic will be drawn relative to the start of
     * the rendering line
     */
    @XmlElement("InitialGap")
    Expression getInitialGap();

    /**
     * Gap gives the distance between two graphics.
     */
    @XmlElement("Gap")
    Expression getGap();

    /**
     * If IsRepeated is "true", the label will be repeatedly drawn
     * along the line with InitialGap and Gap defining the spaces at the
     * beginning and between labels.
     */
    @XmlElement("IsRepeated")
    boolean isRepeated();

    /**
     * Labels can either be aligned to the line geometry if IsAligned is "true" (the default) or are
     * drawn horizontally.
     */
    @XmlElement("IsAligned")
    boolean IsAligned();

    /**
     * GeneralizeLine allows the actual geometry, be it a
     * linestring or polygon to be generalized for label placement. This is e.g. useful for
     * labelling polygons inside their interior when there is need for the label to resemble the
     * shape of the polygon.
     */
    @XmlElement("GeneralizeLine")
    boolean isGeneralizeLine();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
