/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

import java.awt.Color;


/**
 * The <code>Fill</code> interface encapsulates the drawing attributes
 * that can be applied to fills on line Graphics and closed Graphics.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface Fill {
    /**
     * Fill color attribute name.
     */
    String COLOR = "FILL_COLOR";

    /**
     * Fill background color attribute name.
     */
    String BACKGROUND_COLOR = "FILL_BACKGROUND_COLOR";

    /**
     * Halo fill gradient attribute name.
     */
    String GRADIENT_POINTS = "FILL_GRADIENT_POINTS";

    /**
     * Fill opacity attribute name.
     */
    String OPACITY = "FILL_OPACITY";

    /**
     * Fill pattern attribute name.
     */
    String FILL_PATTERN = "FILL_PATTERN";

    /**
     * Fill style attribute name.
     */
    String FILL_STYLE = "FILL_STYLE";

    /**
     * Returns the fill color value.
     * @return the fill color value.
     */
    Color getColor();

    /**
     * Returns whether the fill color value has been set.
     * @return true if the fill color value has been set, false otherwise.
     */
    boolean isColorSet();

    /**
     * Sets the fill color value.
     * @param fillColor the fill color value.
     */
    void setColor(Color fillColor);

    /**
     * Sets the fact that the fill color value has been set.
     * @param flag true if the fill color value has been set, false otherwise.
     */
    void setColorSet(boolean flag);

    /**
     * Returns the fill background color value.
     * @return the fill background color value.
     */
    Color getBackgroundColor();

    /**
     * Returns whether the fill background color value has been set.
     * @return true if the fill background color value has been set, false otherwise.
     */
    boolean isBackgroundColorSet();

    /**
     * Sets the fill background color value.
     * @param fillBackgroundColor the fill background color value.
     */
    void setBackgroundColor(Color fillBackgroundColor);

    /**
     * Sets the fact that the fill background color value has been set.
     * @param flag true if the fill background color value has been set, false otherwise.
     */
    void setBackgroundColorSet(boolean flag);

    /**
     * Returns the fill gradient points value.
     * @return the fill gradient points value.
     */
    float[] getGradientPoints();

    /**
     * Returns whether the fill gradient points value has been set.
     * @return true if the fill gradient points value has been set, false otherwise.
     */
    boolean isGradientPointsSet();

    /**
     * Sets the fill gradient points value.
     * @param fillGradientPoints the fill gradient points value.
     */
    void setGradientPoints(float[] fillGradientPoints);

    /**
     * Sets the fact that the fill gradient points value has been set.
     * @param flag true if the fill gradient points value has been set, false otherwise.
     */
    void setGradientPointsSet(boolean flag);

    /**
     * Returns the fill opacity value.
     * @return the fill opacity value.
     */
    float getOpacity();

    /**
     * Returns whether the fill opacity value has been set.
     * @return true if the fill opacity value has been set, false otherwise.
     */
    boolean isOpacitySet();

    /**
     * Sets the fill opacity value.
     * @param fillOpacity the fill opacity value.
     */
    void setOpacity(float fillOpacity);

    /**
     * Sets the fact that the fill opacity value has been set.
     * @param flag true if the fill opacity value has been set, false otherwise.
     */
    void setOpacitySet(boolean flag);

    /**
     * Returns the fill pattern value.
     * @return the fill pattern value.
     */
    FillPattern getFillPattern();

    /**
     * Returns whether the fill pattern value has been set.
     * @return true if the fill pattern value has been set, false otherwise.
     */
    boolean isFillPatternSet();

    /**
     * Sets the fill pattern value.
     * @param fillPattern the fill pattern value.
     */
    void setFillPattern(FillPattern fillPattern);

    /**
     * Sets the fact that the fill pattern value has been set.
     * @param flag true if the fill pattern value has been set, false otherwise.
     */
    void setFillPatternSet(boolean flag);

    /**
     * Returns the fill style value.
     * @return the fill style value.
     */
    FillStyle getFillStyle();

    /**
     * Returns whether the fill style value has been set.
     * @return true if the fill style value has been set, false otherwise.
     */
    boolean isFillStyleSet();

    /**
     * Sets the fill style value.
     * @param fillStyle the fill style value.
     */
    void setFillStyle(FillStyle fillStyle);

    /**
     * Sets the fact that the fill style value has been set.
     * @param flag true if the fill style value has been set, false otherwise.
     */
    void setFillStyleSet(boolean flag);
}
