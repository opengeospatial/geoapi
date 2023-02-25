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

import org.opengis.filter.Expression;
import org.opengis.annotation.XmlElement;


/**
 * Indicates how text will be drawn.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("TextSymbolizer")
public interface TextSymbolizer extends Symbolizer {
    /**
     * Returns the expression that will be evaluated to determine what text is
     * displayed.
     * If a Label element is not provided in a TextSymbolizer, then no text shall be rendered.
     */
    @XmlElement("Label")
    Expression getLabel();

    /**
     * Returns the Font to apply on the text.
     */
    @XmlElement("Font")
    Font getFont();

    /**
     * Returns the object that indicates how the text should be placed with
     * respect to the feature geometry.  This object will either be an instance
     * of {@link LinePlacement} or {@link PointPlacement}.
     *
     * @return {@link LinePlacement} or {@link PointPlacement}.
     */
    @XmlElement("LabelPlacement")
    LabelPlacement getLabelPlacement();

    /**
     * Returns the object that indicates if a Halo will be drawn around the text.
     * If null, a halo will not be drawn.
     */
    @XmlElement("Halo")
    Halo getHalo();

    /**
     * Returns the object that indicates how the text will be filled.
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
