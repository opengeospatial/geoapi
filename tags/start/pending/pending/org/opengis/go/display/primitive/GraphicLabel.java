/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.primitive;

import org.opengis.go.display.style.XAnchor;
import org.opengis.go.display.style.YAnchor;
import org.opengis.spatialschema.coordinate.DirectPosition;

import com.dautelle.units.Unit;

/**
 * <code>GraphicLabel</code> defines a common abstraction for implementations
 * that render text on a drawing surface.
 * <p>
 * The rotation of the label with respect to an external Coordinate Reference System is 
 * measured positively as a clockwise angle, starting from a reference line within the 
 * Coordinate Reference System and ending at the x-axis of the local Cartesian plane.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface GraphicLabel extends Graphic {
    
    /**
     * Sets the text that the label will render.
     * @param text the text to display.
     */
    public void setText(String text);

    /**
     * Returns the text that will be displayed.
     * @return the text to be rendered.
     */
    public String getText();

    /**
     * Sets the position of the upper left corner of the label. 
     * @param coord the wrapper for the position of the label.
     */
    public void setPosition(DirectPosition coord);

    /**
     * Returns the DirectPosition corresponding to the position of the label.
     * @return the wrapper for the position.
     */
    public DirectPosition getPosition();

    /**
     * Specifies the x-axis portion of the place on the text string that
     * corresponds to the rendering position (see the [gs]etPosition method). 
     * @param xAnchor the x-axis location on the string
     * (i.e., left, center, right).
     * 
     */
    public void setXAnchor(XAnchor xAnchor);

    /**
     * Returns the x-axis portion of the place on the text string that
     * corresponds to the rendering position (see the [gs]etPosition method). 
     * @return the x-axis portion of the text anchor point. 
     */
    public XAnchor getXAnchor();

    /**
     * Specifies the y-axis portion of the place on the text string that
     * corresponds to the rendering position (see the [gs]etPosition method). 
     * @param yAnchor the y-axis location on the string
     * (i.e., top, middle, baseline, bottom).
     */
    public void setYAnchor(YAnchor yAnchor);

    /**
     * Returns the y-axis portion of the place on the text string that
     * corresponds to the rendering position (see the [gs]etPosition method). 
     * @return the y-axis portion of the text anchor point. 
     */
    public YAnchor getYAnchor();

    /**
     * Sets the angle by which to rotate the text before rendering. The
     * text is rotated about its anchor (see the set[XY]Anchor methods).
     * @param rotation the new rotation.
     * @param unit the Unit for the angle value.
     */
    public void setRotation(double rotation, Unit unit); 

    /**
     * Returns the orientation by which to rotate the text before rendering.
     * @param unit the Unit for the angle value.
     * @return the rotation orientation.
     */
    public double getRotation(Unit unit); 
    
    /**
     * Indicates whether this primitive is displaying anchor handles that allow the
     * user to change the rotation of the text.
     */
    public boolean isAllowingRotation();

    /**
     * Sets the boolean that indicates whether this primitive is displaying
     * anchor handles that allow the user to change the rotation of the text.
     */
    public void setAllowingRotation(boolean newValue);
    
}
