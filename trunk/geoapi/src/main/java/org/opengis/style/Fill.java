/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.style;

import org.opengis.filter.expression.Expression;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;


/**
 * Indicates how the interior of polygons will be filled.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
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

    /**
     * Sets the graphic that will be used to tile the interior of polygons.
     * This can be set to null if solid color fill is desired.
     * See {@link #getGraphicFill} for details.
     * 
     * @param graphicFill : new GraphicFill
     */
    @XmlElement("GraphicFill")
    void setGraphicFill(GraphicFill graphicFill);
    
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
     * Sets the color to be used for solid-filling the interior of polygons.
     * See {@link #getColor} for details.
     * 
     * @param expression : new fill color
     */
    @XmlParameter("Fill")
    void setColor(Expression expression);

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
     * Sets the opacity of the fill.  This value must be a floating point
     * number ranging from 0.0 to 1.0, where 0.0 means completely transparent
     * and 1.0 means completely opaque.  If null, the default value is 1.0,
     * completely opaque.
     * See {@link #getOpacity} for details.
     * 
     * @param expression : new opacity
     */
    @XmlParameter("Opacity")
    void setOpacity(Expression expression);
}
