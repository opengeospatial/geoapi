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
 * Holds the information that indicates how to draw the lines and the interior of polygons.
 * If a polygon has holes, then they are not filled,
 * but the borders around the holes are stroked in the usual way.
 * Islands within holes are filled and stroked, and so on.
 *
 * <p>The fill should be rendered first, then the stroke should be rendered on top of the fill.
 * A missing stroke element means that the geometry will not be stroked.</p>
 *
 * <h2>Non-polygon kinds of geometry</h2>
 * If a point geometry is referenced instead of a polygon, then a small, square, orthonormal polygon
 * should be constructed for rendering. If a line is referenced, then the line (string) is closed for
 * filling (only) by connecting its end point to its start point, any line crossings are corrected in
 * some way, and only the original line is stroked. If a raster geometry is used, then the raster-coverage
 * area is used as the polygon. A missing Geometry element selects the “default” geometry for a feature type.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 */
@XmlElement("PolygonSymbolizer")
public interface PolygonSymbolizer extends Symbolizer {
    /**
     * Returns the object containing all the information necessary to draw styled lines.
     * This is used for the edges of polygons.
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
     * A distance to apply for drawing lines in parallel to the original polygon.
     * This property allow to draw polygons smaller or larger than their actual geometry.
     * The distance units of measurement is given by {@link #getUnitOfMeasure()}.
     * The value is positive outside the polygon. The default offset is 0.
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
