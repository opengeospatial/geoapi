/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Identifies a font of a certain family, style, and size.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Font")
public interface Font {
    /**
     * Indicates the name of the font or font family to use.  Any number of
     * comma-separated values may be provided and they are assumed to be in
     * preferred order.  The list of available font families is system
     * dependent.  If null, a system dependent default will be used.
     */
    @XmlElement("font-familly") // TODO: Actually a CssParameter
    Expression getFamily();

    /**
     * Sets the name of the font or font family to use.
     * See {@link #getFamily} for details.
     */
    @XmlElement("font-familly") // TODO: Actually a CssParameter
    void setFamily(Expression expression);

    /**
     * Indicates the style of the font.  Allowed values are
     * "normal", "italic", and "oblique".  If null, the default is "normal".
     */
    @XmlElement("font-style") // TODO: Actually a CssParameter
    Expression getStyle();

    /**
     * Sets the style of the font.  Allowed values are
     * "normal", "italic", and "oblique".  If null, the default is "normal".
     * See {@link #getStyle} for details.
     */
    @XmlElement("font-style") // TODO: Actually a CssParameter
    void setStyle(Expression expression);

    /**
     * Expression that indicates the weight of the font.  Allowed values are
     * "normal" and "bold".  If null, the default is "normal".
     */
    @XmlElement("font-weight") // TODO: Actually a CssParameter
    Expression getWeight();

    /**
     * Expression that indicates the weight of the font.
     * See {@link #getWeight} for details.
     */
    @XmlElement("font-weight") // TODO: Actually a CssParameter
    void setWeight(Expression expression);

    /**
     * Expression that indicates the pixel size of the font.  If null, the
     * default value is 10.
     */
    @XmlElement("font-size") // TODO: Actually a CssParameter
    Expression getSize();

    /**
     * Expression that indicates the pixel size of the font.
     * See {@link #getSize} for details.
     */
    @XmlElement("font-size") // TODO: Actually a CssParameter
    void setSize(Expression expression);
}
