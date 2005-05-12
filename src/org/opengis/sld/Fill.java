/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

// OpenGIS direct dependencies
import org.opengis.filter.expression.Expression;


/**
 * Indicates how the interior of polygons will be filled.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
public interface Fill {
    /**
     * If this object is to be filled with tiled copies of an image, then this
     * method will return a non-null Graphic that indicates what image should be
     * drawn.
     */
    public Graphic getGraphicFill();

    /**
     * Sets the graphic that will be used to tile the interior of polygons.
     * This can be set to null if solid color fill is desired.
     */
    public void setGraphicFill(Graphic graphicFill);

    /**
     * This indicates the color to be used for solid-filling the interior of
     * polygons.  The format of the color is "#rrggbb" where rr, gg, and bb are
     * two digit hexadecimal integers specify the red, green, and blue color
     * intensities, repsectively.  If null, the default color is 50% gray,
     * "#808080".
     */
    public Expression getColor();

    /**
     * This indicates the color to be used for solid-filling the interior of
     * polygons.  The format of the color is "#rrggbb" where rr, gg, and bb are
     * two digit hexadecimal integers specify the red, green, and blue color
     * intensities, repsectively.  If null, the default color is 50% gray,
     * "#808080".
     */
    public void setColor(Expression expression);

    /**
     * Indicates the opacity of the fill.  This value must be a floating point
     * number ranging from 0.0 to 1.0, where 0.0 means completely transparent
     * and 1.0 means completely opaque.  If null, the default value is 1.0,
     * completely opaque.
     */
    public Expression getOpacity();

    /**
     * Indicates the opacity of the fill.  This value must be a floating point
     * number ranging from 0.0 to 1.0, where 0.0 means completely transparent
     * and 1.0 means completely opaque.  If null, the default value is 1.0,
     * completely opaque.
     */
    public void setOpacity(Expression expression);
}
