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

import com.dautelle.units.Unit;

import javax.swing.Icon;

import org.opengis.go.typical.coord.Pixel;
import org.opengis.spatialschema.coordinate.DirectPosition;

/**
 * The <code>GraphicIcon</code> defines a common abstraction for implementations that render
 * icons on a drawing surface.
 * <p>
 * The rotation of the icon with respect to an external Coordinate Reference System is 
 * measured positively as a clockwise angle, starting from a reference line within the 
 * Coordinate Reference System and ending at the x-axis of the local Cartesian plane.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface GraphicIcon extends Graphic {

    /**
     * Sets the icon represented by this <code>GraphicIcon</code>.
     * @param icon the icon to be rendered.
     */
    public void setIcon(Icon icon);

    /**
     * Returns the icon represented by this <code>GraphicIcon</code>.
     * @return the icon to be rendered.
     */
    public Icon getIcon();

    /**
     * Sets the <code>DirectPosition</code> corresponding to the position of this <code>GraphicIcon</code>.
     * @param coord the wrapper for the positon.
     */
    public void setPosition(DirectPosition coord);

    /**
     * Returns the <code>DirectPosition</code> corresponding to the position of this <code>GraphicIcon</code>.
     * @return the wrapper for the position of the center point.
     */
    public DirectPosition getPosition();

    /**
     * Sets the angle by which to rotate this <code>GraphicIcon</code>. Use rotation to rotate
     * rotatable icons. For example, use the entity's course to rotate an icon
     * whose shape indicates the direction of the entity (e.g., top-down
     * view of a aircraft) Note: the actual drawing angle may differ from the
     * rotation angle once the <code>GraphicIcon</code> has been projected onto the drawing
     * surface.
     * @param angle the new rotation.
     * @param unit the Unit for the angle value.
     */
    public void setRotation(double angle, Unit unit);

    /**
     * Gets the angle by which to rotate this <code>GraphicIcon</code>.
     * @param unit the <code>Unit</code> for the angle value.
     * @return the rotation angle measured clockwise from the horizontal.
     */
    public double getRotation(Unit unit);

    /**
     * Sets the location in the icon (as an offset from the upper left) that
     * will be drawn over the icon's position.  This will also be the point
     * about which rotation will occur.  If the offset coordinate is null, then
     * the icon will be centered over its position.
     */
    public void setOffset(Pixel offset);

    /**
     * Returns the location in the icon (as an offset from the upper left)
     * that will be drawn over the icon's position.  This is the center
     * of rotation as well.  If the offset coordinate is null, then the icon
     * will be centered over its position.
     */
    public Pixel getOffset();

    /**
     * Indicates whether this <code>GraphicIcon</code> is displaying anchor handles that allow the
     * user to change the rotation of this icon.
     */
    public boolean isAllowingRotation();

    /**
     * Sets the boolean that indicates whether this <code>GraphicIcon</code> is displaying
     * anchor handles that allow the user to change the rotation of this icon.
     */
    public void setAllowingRotation(boolean newValue);

}
