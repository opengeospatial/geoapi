/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
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
 * Indicates how the interior of polygons will be filled.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Fill")
public interface Fill {
    /**
     * If this object is to be filled with tiled copies of an image, then returns
     * a non-null Graphic that indicates what image should be drawn.
     */
    @XmlElement("GraphicFill")
    Graphic getGraphicFill();

    /**
     * Sets the graphic that will be used to tile the interior of polygons.
     * This can be set to null if solid color fill is desired.
     * See {@link #getGraphicFill} for details.
     */
    @XmlElement("GraphicFill")
    void setGraphicFill(Graphic graphicFill);

    /**
     * Indicates the color to be used for solid-filling the interior of polygons.
     * The format of the color is {@code "#rrggbb"} where {@code rr}, {@code gg},
     * and {@code bb} are two digit hexadecimal integers specify the red, green,
     * and blue color intensities, repsectively.  If null, the default color is
     * 50% gray, {@code "#808080"}.
     */
    Expression getColor();

    /**
     * Sets the color to be used for solid-filling the interior of polygons.
     * See {@link #getColor} for details.
     */
    void setColor(Expression expression);

    /**
     * Indicates the opacity of the fill.  This value must be a floating point
     * number ranging from 0.0 to 1.0, where 0.0 means completely transparent
     * and 1.0 means completely opaque.  If null, the default value is 1.0,
     * completely opaque.
     */
    Expression getOpacity();

    /**
     * Sets the opacity of the fill.  This value must be a floating point
     * number ranging from 0.0 to 1.0, where 0.0 means completely transparent
     * and 1.0 means completely opaque.  If null, the default value is 1.0,
     * completely opaque.
     * See {@link #getOpacity} for details.
     */
    void setOpacity(Expression expression);
}
