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

import org.opengis.spatialschema.coordinate.Conic;
import org.opengis.spatialschema.coordinate.DirectPosition;
import org.opengis.go.spatial.PathType;
import org.opengis.go.util.NoSuchEnumerationException;
import org.opengis.go.util.SimpleEnumerationType;
import org.opengis.go.display.util.GeometryNotSupportedException;

import com.dautelle.units.Unit;

/**
 * A <code>GraphicArc</code> represents a portion of an ellipse.
 * <P>
 * A <code>GraphicArc</code> is constructed from either an elliptic instance of
 * <code>Conic</code>, or the following graphical values:
 * <UL>
 * <LI>Its <code>center</code>, an instance of <code>DirectPosition</code>,
 * <LI>Its <code>width</code> and <code>height</code>, double precision values measured in length units from the <code>center</code>,
 * <LI>Its <code>rotation</code> angle, a double precision value,
 * <LI>Its arc <code>start</code> angle, a double precision value,
 * <LI>Its arc <code>end</code> angle, a double precision value.
 * </UL>
 * <P>
 * The geometric shape of <code>GraphicArc</code> can be modified via these parameters 
 * (the parameters are converted and passed to the underlying <code>Conic</code> object).
 * Similarly, if the underlying <code>Conic</code> changes shape but remains an ellipse, 
 * then the <code>GraphicArc</code> reflects that behavior 
 * (see <code>Graphic.refresh()</code>). If the <code>Conic</code> changes to a form 
 * other than an ellipse, then the behavior of <code>GraphicArc</code> is undefined.
 * <p>
 * The ellipse is oriented by centering it at the origin of a local Cartesian plane, 
 * with the major axis of the ellipse is aligned with the x-axis (abcissa).
 * Start and end points of the elliptic arc are measured positively as a counterclockwise angle
 * from the positive x-axis.
 * <p>
 * The rotation of the ellipse with respect to an external Coordinate Reference System is 
 * measured positively as a clockwise angle, starting from a reference line within the 
 * Coordinate Reference System and ending at the x-axis of the local Cartesian plane.
 * <p>
 * The external Coordinate Reference System is the Coordinate Reference System of the DirectPosition
 * correlating to the center of the ellipse.
 *
 * <img src="images/GraphicArc.gif">
 * 
 * If the width and height parameters are specified
 * in <code>com.dautelle.units.Length.Pixel</code>s, then a
 * <code>GraphicArc</code> shall retain shape and orientation regardless
 * of where it appears on the screen canvas.  If a
 * <code>setWidth</code> or <code>setHeight</code> method is called
 * with a conflicting type of <code>Unit</code>, then the
 * <code>GraphicArc</code> should convert its other parameter to the new
 * <code>Unit</code>s.  Thus, if a <code>GraphicArc </code> is defined in
 * pixels, and setWidth is called with a new value and the
 * <code>com.dautelle.units.Length.Meter</code> <code>Unit</code>,
 * then the <code>GraphicArc</code> should convert its height to meters as
 * well.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface GraphicArc extends Graphic {

    /**
     * Sets the geometry for this <code>GraphicArc</code>.
     * <p>
     * The geometry is oriented by centering the ellipse at the origin of a local Cartesian plane, 
     * with the major axis of the ellipse is aligned with the x-axis (abcissa).
     * Start and end points of the elliptic arc are measured positively as a counterclockwise angle
     * from the positive x-axis.
     * <p>
     * The rotation of the ellipse with respect to an external Coordinate Reference System is 
     * measured positively as a clockwise angle, starting from a reference line within the 
     * Coordinate Reference System and ending at the x-axis of the local Cartesian plane.
     * <p>
     * The geometric values are set on the underlying Conic geometry for this Graphic.
     * 
     * @param center the location of the centerpoint of the ellipse defining this arc.
     * @param width the length of the major axis of the ellipse defining this arc.
     * @param height the length of the minor axis of the ellipse defining this arc.
     * @param lengthUnit the unit of width and height.
     * @param rotation the rotation of the ellipse defining this arc with respect to the Coordinate Reference System of the centerpoint DirectPosition.
     * @param start the angle of starting point of the arc, drawn clockwise from the positive side of the major axis.
     * @param end the angle of the ending point of the arc, drawn clockwise from the positive side of the major axis.
     * @param angleUnit the unit of rotation.
     * @throws GeometryNotSupportedException if the width value is smaller than the height value is not an ellipse.
     */
    public void setArc(DirectPosition center, double width, double height, Unit lengthUnit, 
    		double rotation, double start, double end, Unit angleUnit);
    
    /**
     * Convenience method to set the location of the center of the ellipse defining this arc. 
     * Value is set on the underlying Conic geometry for this Graphic.
     */
    public void setCenter(DirectPosition center);

    /**
     * Convenience method to return the center of the of the ellipse defining this arc.
     * Value is acquired from the underlying Conic geometry for this Graphic.
     */
    public DirectPosition getCenter();

    /**
     * Convenience method to set the size of the width axis 
     * of the ellipse defining this arc, in terms of the given Unit.
     * Value is set on the underlying Conic geometry for this Graphic.
     * @param width Size of the width axis.
     * @param unit the Unit of the width value.
     */
    public void setWidth(double width, Unit unit);

    /**
     * Convenience method to return the size of the width axis 
     * of the ellipse defining this arc, in terms of the given Unit.
     * Value is acquired from the underlying Conic geometry for this Graphic.
     * @param unit the Unit of the width value.
     * @return The size of the width axis of the arc.
     */
    public double getWidth(Unit unit);

    /**
     * Convenience method to set the size of the height axis 
     * of the ellipse defining this arc, in terms of the given Unit.
     * Value is set on the underlying Conic geometry for this Graphic.
     * @param height Size of the height axis.
     * @param unit the Unit of the height value.
     */
    public void setHeight(double height, Unit unit);

    /**
     * Convenience method to return the size of the height axis 
     * of the ellipse defining this arc, in terms of the given Unit.
     * Value is acquired from the underlying Conic geometry for this Graphic.
     * @param unit the Unit of the height value.
     * @return The size of the height axis of the arc.
     */
    public double getHeight(Unit unit);

    /**
     * Sets the geometry based on ISO 19107 Conic geometry for this Graphic. 
     * The Conic must be an ellipse (or a circle), otherwise 
     * GeometryNotSupportedException is thrown.
     * @param conic the elliptic conic for this Graphic.
     * @throws GeometryNotSupportedException if Conic is not an ellipse.
     */
    public void setConic(Conic conic) throws GeometryNotSupportedException;
    
    /**
     * Sets the orientation for the width axis.  On a canvas, this
     * might be the angle between the positive X axis and the width
     * axis of the arc. For a projected arc, this might be the angle
     * between due north and the width axis.
     * @param orientation  orientation of the arc.
     * @param unit the Unit for the angle value.
     */
    public void setRotation(double rotation, Unit unit);

    /**
     * Returns the arc's orientation source.
     * @param unit the Unit for the angle value.
     * @return orientation of the arc.
     */
    public double getRotation(Unit unit);

    /**
     * Set the arc's start bearing. The arc is stroked
     * counter-clockwise from start to end.
     * @param start the start Orientation.
     * @param unit the Unit for the angle value.
     */
    public void setStart(double start, Unit unit);

    /**
     * Get the arc's start bearing. The arc is stroked
     * counter-clockwise from start to end.
     * @param unit the Unit for the angle value.
     * @return the arc start Orientation. 
     */
    public double getStart(Unit unit);

    /**
     * Set the arc's end bearing.  The arc is stroked
     * counter-clockwise from start to end.
     * @param end the end orientation.
     * @param unit the Unit for the angle value.
     */
    public void setEnd(double end, Unit unit);

    /**
     * Get the arc's ending bearing. The arc is stroked
     * counter-clockwise from start to end.
     * @param unit the Unit for the angle value.
     * @return the arc end orientation.
     */
    public double getEnd(Unit unit);

    /**
     * Sets the closureType, which determines how the endpoints of the
     * arc are connected.  This is one of the constants defined in the
     * <code>ArcClosure</code> class.  The integer values corresponding to
     * these constants are the same as the closure type values in
     * <code>java.awt.geom.Arc2D</code>.
     * @param closureType  Closure type of the arc (one of the ArcClosure
     * constants: OPEN, CHORD, or PIE)
     */
    public void setClosureType(ArcClosure closureType);

    /**
     * Returns the closureType.
     * @return Closure type of the arc (one of ArcClosure constants
     * OPEN, CHORD, or PIE).  The integer values corresponding to these
     * constants are the same as the constants OPEN, CHORD, and PIE from
     * <code>java.awt.geom.Arc2d</code>.
     */
    public ArcClosure getClosureType();

    /**
     * Indicates whether this primitive is displaying an anchor handle for changing
     * the rotation of the ellipse that contains this arc.
     */
    public boolean isAllowingRotation();

    /**
     * Sets the boolean that indicates whether this primitive displays a
     * an anchor handle for changing the rotation of the ellipse containing this arc.
     */
    public void setAllowingRotation(boolean newValue);
    
    /**
     * Indicates whether this primitive is a circle. The implementation should
     * test for this based on the geometry settings.
     */
    public boolean isCircle();
    
    /**
     * Indicates whether this primitive is a closed ellipse. The implementation should
     * test for this based on the geometry settings.
     */
    public boolean isClosedEllipse();
    
    //**  EDITABLE/ANIMATION  **

    /**
     * Indicates whether this primitive is displaying edit handles for changing
     * the angular extents of this arc.
     */
    public boolean isAllowingExtentsChange();

    /**
     * Sets the boolean that indicates whether this primitive is displaying
     * edit handles for changing the angular extents of this arc.
     */
    public void setAllowingExtentsChange(boolean newValue);
    
    //**  PROJECTED  **
    
    /**
     * Sets the algorithm that is used in computing the "in-between" pixels
     * of the ArcClosure verteces when the arc is rendered. This does not
     * when there is no closure, i.e. when isClosedEllipse() is false, or
     * for ArcClosure.OPEN.
     *
     * @param newPathType This must be one of the static constants in
     *   the PathType class or one of its subclasses.
     */
    public void setClosurePathType(PathType pathType);

    /**
     * Retrieves the algorithm that is used in computing the "in-between" pixels
     * of the ArcClosure verteces when the arc is rendered. This does not
     * when there is no closure, i.e. when isClosedEllipse() is false, or
     * for ArcClosure.OPEN.
     */
    public PathType getClosurePathType();
    
    /**
     * Instances of the <code>ArcClosure</code> class represent the various
     * methods of connecting the endpoints of an arc.  There are three static
     * constants defined in this class that can be used as parameters to
     * the <code>setClosureType</code> method.
     *
     * @author Open GIS Consortium, Inc.
     */
    public static class ArcClosure extends SimpleEnumerationType {
        /**
         * Closure type that indicates that the endpoints of the arc
         * should not be connected.
         */
        public static final ArcClosure OPEN  = new ArcClosure(
            java.awt.geom.Arc2D.OPEN, "Open", "");
        /**
         * Closure type that indicates that one line should be drawn between
         * the endpoints of the arc.
         */
        public static final ArcClosure CHORD = new ArcClosure(
            java.awt.geom.Arc2D.CHORD, "Chord", "");
        /**
         * Closure type that indicates that two lines, one from each
         * endpoint of the arc, should be drawn to the center of the ellipse
         * defining the arc.
         */
        public static final ArcClosure PIE   = new ArcClosure(
            java.awt.geom.Arc2D.PIE, "Pie", "");

        /** Enumeration value of the <code>OPEN</code> constant. */
        public static final int OPEN_VALUE = OPEN.getValue();
        /** Enumeration value of the <code>CHORD</code> constant. */
        public static final int CHORD_VALUE = CHORD.getValue();
        /** Enumeration value of the <code>PIE</code> constant. */
        public static final int PIE_VALUE = PIE.getValue();

        /**
         * A list containing all the enumerators so that the list can be
         * "walked" and also to do reverse lookups (id to object).
         */
        private static final ArcClosure[] enumList = {
            OPEN,
            CHORD,
            PIE
        };

        /**
         * Constructor that should only be called to create the static
         * constants in this class.
         */
        private ArcClosure(int value, String name, String description) {
            super(value, name, description);
        }

        /**
         * Method to lookup an <code>ArcClosure</code> object from its
         * integer value.
         * @param value The value to match the object with.
         * @throws NoSuchEnumerationException If there is no object for the
         *         given value.
         */
        public static ArcClosure getByValue(int value)
                      throws NoSuchEnumerationException {

            for (int i = 0; i < enumList.length; i++) {
                if (enumList[i].getValue() == value) {
                    return enumList[i];
                }
            }

            throw new NoSuchEnumerationException(value);
        }

        /**
         * Utility method that retrieves the list of all
         * <code>ArcClosure</code>s.  This is useful when making combo-boxes
         * with code like:
         * <code><pre>
         * JComboBox comboBox = new JComboBox(ArcClosure.getArray());
         * </pre></code><p>
         * <b>IMPORTANT SAFETY TIP:</b><br>
         * Modifying the array returned is a Bad Thing. Don't do it.
         */
        public static ArcClosure[] getArray() {
            return enumList;
        }
    }
}
