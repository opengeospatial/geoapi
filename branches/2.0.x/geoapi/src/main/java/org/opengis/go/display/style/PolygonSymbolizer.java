/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

// J2SE direct dependencies
import java.awt.Color;

/**
 * Encapsulates the style data applicable to
 * {@link org.opengis.go.display.primitive.Graphic}s that are of type Polygon
 * in the sense of SLD (OGC 02-070).
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */
public interface PolygonSymbolizer extends LineSymbolizer {

    //*************************************************************************
    //  Static Fields
    //*************************************************************************

    //**  Default PolygonSymbolizer property values  **

    /**  Default fill color value.  */
    public static final Color DEFAULT_FILL_COLOR = Color.GRAY;

    /**  Default fill background color value.  */
    public static final Color DEFAULT_FILL_BACKGROUND_COLOR = Color.BLACK;

    /**  Default fill gradient points value.  */
    public static final float[] DEFAULT_FILL_GRADIENT_POINTS = new float[2];

    /**  Default fill opacity value.  */
    public static final float DEFAULT_FILL_OPACITY = 1.f;

    /**  Default fill pattern value.  */
    public static final FillPattern DEFAULT_FILL_PATTERN = FillPattern.NONE;

    /**  Default fill style value.  */
    public static final FillStyle DEFAULT_FILL_STYLE = FillStyle.EMPTY;

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the fill color rgb value.
     * @return the fill color value.
     */
    public Color getFillColor();

    /**
     * Sets the fill color rgb value.  
     * Do not use the {@link Color} alpha channel to set fill opacity; 
     * use {@link #setFillOpacity} instead.
     * @param fillColor the fill color value.
     */
    public void setFillColor(Color fillColor);

    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    public Color getFillBackgroundColor();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */
    public void setFillBackgroundColor(Color fillBackgroundColor);

    /**
     * Returns the fill gradient points value.
     * @return the fill gradient points value.
     */
    public float[] getFillGradientPoints();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value.
     */
    public void setFillGradientPoints(float[] fillGradientPoints);

    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    public float getFillOpacity();

    /**
     * Sets the fill opacity value.  If semi-transparency is specified in
     * both this attribute and the alpha value of the
     * fill color, the alpha channel value should be ignored.
     * @param fillOpacity the fill opacity value.
     */
    public void setFillOpacity(float fillOpacity);

    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    public FillPattern getFillPattern();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */
    public void setFillPattern(FillPattern fillPattern);

    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    public FillStyle getFillStyle();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */
    public void setFillStyle(FillStyle fillStyle);
}

