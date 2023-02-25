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

import java.util.List;
import org.opengis.filter.Expression;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;


/**
 * The Font element identifies a font of a certain family, style, and size.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("Font")
public interface Font {

    //*************************************************************
    // SVG PARAMETERS
    //*************************************************************

    /**
     * The "font-family" SvgParameter element gives the family name of a font to use.
     * Allowed values are system-dependent. Any number of font-family SvgParameter
     * elements may be given and they are assumed to be in preferred order.
     *
     * @return live list of font family
     */
    @XmlParameter("font-familly")
    List<Expression> getFamily();

    /**
     * The "font-style" SvgParameter element gives the style to use for a font. The allowed
     * values are "normal", "italic", and "oblique".
     * If null, the default is "normal".
     * @return Expression or Expression.NIL
     */
    @XmlParameter("font-style")
    Expression getStyle();

    /**
     * The "font-weight" SvgParameter element gives the amount of weight or boldness to use
     * for a font. Allowed values are "normal" and "bold".
     * If null, the default is "normal".
     *
     * @return Expression or or Expression.NIL
     */
    @XmlParameter("font-weight")
    Expression getWeight();

    /**
     * The "font-size" SvgParameter element gives the size to use for the font in pixels. The
     * default is defined to be 10 pixels, though various systems may have restrictions on what
     * sizes are available.
     *
     * @return Expression or null
     */
    @XmlParameter("font-size")
    Expression getSize();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);

}
