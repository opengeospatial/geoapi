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

import org.opengis.filter.Expression;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;


/**
 * Indicates how the interior of polygons will be filled.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 */
@XmlElement("Fill")
public interface Fill {
    /**
     * If this object is to be filled with tiled copies of an image, then returns
     * a non-null Graphic that indicates what image should be drawn.
     *
     * @return Graphic object or null if no graphic pattern to use.
     */
    @XmlElement("GraphicFill")
    GraphicFill getGraphicFill();

    //*************************************************************
    // SVG PARAMETERS
    //*************************************************************

    /**
     * Indicates the color to be used for solid-filling the interior of polygons.
     * The format of the color is {@code "#rrggbb"} where {@code rr}, {@code gg},
     * and {@code bb} are two digit hexadecimal integers specify the red, green,
     * and blue color intensities, repsectively.  If null, the default color is
     * 50% gray, {@code "#808080"}.
     *
     * @return Expression : if null the color used shall be a 50% gray {@code "#808080"}.
     */
    @XmlParameter("Fill")
    Expression getColor();

    /**
     * Indicates the opacity of the fill.  This value must be a floating point
     * number ranging from 0.0 to 1.0, where 0.0 means completely transparent
     * and 1.0 means completely opaque.  If null, the default value is 1.0,
     * completely opaque.
     *
     * @return Expression  : if null, value used shall be 1.0
     */
    @XmlParameter("Opacity")
    Expression getOpacity();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
