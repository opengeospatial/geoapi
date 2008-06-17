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
package org.opengis.go.display.primitive;

import javax.measure.unit.Unit;
import org.opengis.go.display.style.TextSymbolizer;
import org.opengis.go.display.style.XAnchor;
import org.opengis.go.display.style.YAnchor;
import org.opengis.geometry.DirectPosition;


/**
 * Defines a common abstraction for implementations that render text on a drawing surface.
 * The rotation of the label with respect to an external Coordinate Reference System is
 * measured positively as a clockwise angle, starting from a reference line within the
 * Coordinate Reference System and ending at the x-axis of the local Cartesian plane.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface GraphicLabel extends Graphic {
    /**
     * Sets the text that the label will render.
     * @param text the text to display.
     */
    void setText(String text);

    /**
     * Returns the text that will be displayed.
     * @return the text to be rendered.
     */
    String getText();

    /**
     * Sets the position of the upper left corner of the label.
     * @param coord the wrapper for the position of the label.
     */
    void setPosition(DirectPosition coord);

    /**
     * Returns the DirectPosition corresponding to the position of the label.
     * @return the wrapper for the position.
     */
    DirectPosition getPosition();

    /**
     * Specifies the x-axis portion of the place on the text string that
     * corresponds to the rendering position (see the [gs]etPosition method).
     * @param xAnchor the x-axis location on the string
     * (i.e., left, center, right).
     *
     */
    void setXAnchor(XAnchor xAnchor);

    /**
     * Returns the x-axis portion of the place on the text string that
     * corresponds to the rendering position (see the [gs]etPosition method).
     * @return the x-axis portion of the text anchor point.
     */
    XAnchor getXAnchor();

    /**
     * Specifies the y-axis portion of the place on the text string that
     * corresponds to the rendering position (see the [gs]etPosition method).
     * @param yAnchor the y-axis location on the string
     * (i.e., top, middle, baseline, bottom).
     */
    void setYAnchor(YAnchor yAnchor);

    /**
     * Returns the y-axis portion of the place on the text string that
     * corresponds to the rendering position (see the [gs]etPosition method).
     * @return the y-axis portion of the text anchor point.
     */
    YAnchor getYAnchor();

    /**
     * Sets the angle by which to rotate the text before rendering. The
     * text is rotated about its anchor (see the set[XY]Anchor methods).
     * @param rotation the new rotation.
     * @param unit the Unit for the angle value.
     */
    void setRotation(double rotation, Unit unit);

    /**
     * Returns the orientation by which to rotate the text before rendering.
     * @param unit the Unit for the angle value.
     * @return the rotation orientation.
     */
    double getRotation(Unit unit);

    /**
     * Returns the <code>GraphicStyle</code> for this <code>GraphicLabel</code>,
     * which is required to be a <code>TextSymbolizer</code>.
     * @return the GraphicLabel's <code>GraphicStyle</code>.
     */
    TextSymbolizer getTextSymbolizer();

    /**
     * Indicates whether this primitive is displaying anchor handles that allow the
     * user to change the rotation of the text.
     */
    boolean isAllowingRotation();

    /**
     * Sets the boolean that indicates whether this primitive is displaying
     * anchor handles that allow the user to change the rotation of the text.
     */
    void setAllowingRotation(boolean newValue);
}
