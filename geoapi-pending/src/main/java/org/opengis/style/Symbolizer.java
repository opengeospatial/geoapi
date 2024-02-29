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

import javax.measure.Unit;
import javax.measure.quantity.Length;
import org.opengis.feature.Feature;
import org.opengis.filter.Expression;
import org.opengis.filter.ValueReference;
import org.opengis.annotation.XmlElement;


/**
 * Description of how a feature is to appear on a map.
 * A symbolizer describes how the shape should appear,
 * together with graphical properties such as color and opacity.
 * A symbolizer is obtained by specifying one of a small number of different types
 * and then supplying parameters to override its default behaviour.
 * The predefined type of symbolizers are
 * {@linkplain LineSymbolizer line},
 * {@linkplain PolygonSymbolizer polygon},
 * {@linkplain PointSymbolizer point},
 * {@linkplain TextSymbolizer text}, and
 * {@linkplain RasterSymbolizer raster}.
 *
 * <h2>Particular cases if the geometry is not the defined type of the symbolizer</h2>
 * Geometry types other than inherently linear types can also be used. If a point geometry is
 * used, it should be interpreted as a line of "epsilon" (arbitrarily small) length with a
 * horizontal orientation centered on the point, and should be rendered with two end caps.
 * If a polygon is used (or other "area" type), then its closed outline is used as the line string
 * (with no end caps). If a raster geometry is used, its coverage-area outline is used for the
 * line, rendered with no end caps.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 */
public interface Symbolizer {
    /**
     * Returns a  measure unit.
     * This parameter is inherited from GML.
     * Renderers shall use the unit to correctly render symbols.
     *
     * Recommended uom definitions are :
     * <ul>
     *   <li>{@code metre}</li>
     *   <li>{@code foot}</li>
     *   <li>{@code pixel}</li>
     * </ul>
     *
     * @return can be null. If the unit is null than we shall use a the pixel unit
     */
    @XmlElement("uom")
    Unit<Length> getUnitOfMeasure();

    /**
     * Returns the name of the geometry feature attribute to use for drawing.
     * May return null (or Expression.NIL) if this symbol is to use the default geometry attribute,
     * whatever it may be. Using null in this fashion is similar to a ValueReference using
     * the XPath expression ".".
     *
     * <p>The content of the element gives the property name in XPath syntax. In principle, a fixed geometry
     * could be defined using GML or operators could be defined for computing the geometry
     * from references or literals. However, using a feature property directly is by far the most
     * commonly useful method.</p>
     *
     * @return Geometry attribute name if geometry expression is a {@link ValueReference},
     *      or <code>null</code> if geometry expression is not a {@link ValueReference}.
     * @deprecated use {@link Symbolizer#getGeometry()} instead.
     */
    @XmlElement("Geometry")
    @Deprecated
    String getGeometryPropertyName();

    /**
     * Expression used to define a geometry for drawing. May return null if the default
     * geometry attribute should be used. This expression is often a ValueReference.
     *
     * @return Expression used to define a geometry for drawing, or Expression.NIL if the default geometry should be used.
     */
    Expression<Feature,?> getGeometry();

    /**
     * Returns a name for this symbolizer.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     *
     * @return a name for this style.
     */
    @XmlElement("Name")
    String getName();

    /**
     * Returns the description of this symbolizer.
     *
     * @return description with usual informations used for user interfaces.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * Calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     * @return value produced
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
