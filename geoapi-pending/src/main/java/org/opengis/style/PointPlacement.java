/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
 * Instructions about how a text label is positioned relative to a geometric point.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Ian Turton
 */
@XmlElement("PointPlacement")
public interface PointPlacement extends LabelPlacement {
    /**
     * The AnchorPoint element of a PointPlacement gives the location inside of a label to use
     * for anchoring the label to the main-geometry point.
     *
     * This will often be used to avoid over-plotting a graphic symbol marking a city or some
     * such feature. The displacements are in units of pixels above and to the right of the point.
     * A system may reflect this displacement about the X and/or Y axes to de-conflict labels.
     * The default displacement is X=0, Y=0.
     *
     * See {@link AnchorPoint} for details.
     *
     * @return the anchor point, or {@code null} for the X=0.5 Y=0.5 default.
     */
    @XmlElement("PointPlacement")
    AnchorPoint getAnchorPoint();

    /**
     * The Displacement gives the X and Y displacements from the "hot-spot" point. This
     * element may be used to avoid over-plotting of multiple graphic symbols used as part of
     * the same point symbol. The displacements are in units of measure above and to the right
     * of the point. The default displacement is X=0, Y=0.
     *
     * If Displacement is used in conjunction with Size and/or Rotation then the graphic
     * symbol shall be scaled and/or rotated before it is displaced.s
     */
    @XmlElement("Displacement")
    Displacement getDisplacement();

    /**
     * Returns the expression that will be used to calculate the rotation of the
     * graphic when it is drawn.
     *
     * The Rotation of a PointPlacement gives the clockwise rotation of the label in degrees
     * from the normal direction for a font (left-to-right for Latin-derived human languages at
     * least).
     */
    @XmlElement("Rotation")
    Expression getRotation();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
