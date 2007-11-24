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

import java.util.ArrayList;
import java.util.List;
import javax.units.Unit;
import org.opengis.go.display.style.PolygonSymbolizer;
import org.opengis.go.spatial.PathType;
import org.opengis.geometry.DirectPosition;
import org.opengis.util.CodeList;
import org.opengis.util.SimpleEnumerationType;


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
     * The geometric values are set on the underlying <code>Conic</code> geometry for this <code>Graphic</code>.
     *
     * @param center the location of the centerpoint of the ellipse defining this <code>GraphicArc</code>.
     * @param width the length of the major axis of the ellipse defining this <code>GraphicArc</code>.
     * @param height the length of the minor axis of the ellipse defining this <code>GraphicArc</code>.
     * @param lengthUnit the unit of width and height.
     * @param rotation the rotation of the ellipse defining this <code>GraphicArc</code> with respect to the Coordinate Reference System of the centerpoint DirectPosition.
     * @param start the angle of starting point of the arc, drawn clockwise from the positive side of the major axis.
     * @param end the angle of the ending point of the arc, drawn clockwise from the positive side of the major axis.
     * @param angleUnit the unit of rotation.
     * @throws GeometryNotSupportedException if the width value is smaller than the height value is not an ellipse.
     */
    void setArc(
        DirectPosition center,
        double width,
        double height,
        Unit lengthUnit,
        double rotation,
        double start,
        double end,
        Unit angleUnit);

    /**
     * Sets the center of the ellipse defining this <code>GraphicArc</code>.
     * Value is set on the underlying <code>Conic</code> geometry for this <code>Graphic</code>.
     */
    void setCenter(DirectPosition center);

    /**
     * Returns the center of the ellipse defining this <code>GraphicArc</code>.
     * Value is acquired from the underlying <code>Conic</code> geometry for this <code>Graphic</code>.
     */
    DirectPosition getCenter();

    /**
     * Sets the size of the width axis
     * of the ellipse defining this <code>GraphicArc</code>, in terms of the given <code>Unit</code>.
     * Value is set on the underlying <code>Conic</code> geometry for this <code>Graphic</code>.
     * @param width Size of the width axis.
     * @param unit the <code>Unit</code> of the width value.
     */
    void setWidth(double width, Unit unit);

    /**
     * Returns the size of the width axis
     * of the ellipse defining this <code>GraphicArc</code>, in terms of the given <code>Unit</code>.
     * Value is acquired from the underlying <code>Conic</code> geometry for this <code>Graphic</code>.
     * @param unit the <code>Unit</code> of the width value.
     * @return The size of the width axis of the arc.
     */
    double getWidth(Unit unit);

    /**
     * Sets the size of the height axis
     * of the ellipse defining this <code>GraphicArc</code>, in terms of the given <code>Unit</code>.
     * Value is set on the underlying <code>Conic</code> geometry for this <code>Graphic</code>.
     * @param height Size of the height axis.
     * @param unit the <code>Unit</code> of the height value.
     */
    void setHeight(double height, Unit unit);

    /**
     * Returns the size of the height axis
     * of the ellipse defining this <code>GraphicArc</code>, in terms of the given <code>Unit</code>.
     * Value is acquired from the underlying <code>Conic</code> geometry for this <code>Graphic</code>.
     * @param unit the <code>Unit</code> of the height value.
     * @return The size of the height axis of the arc.
     */
    double getHeight(Unit unit);

    /**
     * Sets the orientation for the width axis.  On a <code>Canvas</code>, this
     * might be the angle between the positive X axis and the width
     * axis of the arc. For a projected arc, this might be the angle
     * between due north and the width axis.
     * @param rotation  orientation of the arc.
     * @param unit the <code>Unit</code> for the angle value.
     */
    void setRotation(double rotation, Unit unit);

    /**
     * Returns this <code>GraphicArc</code>'s orientation source.
     * @param unit the <code>Unit</code> for the angle value.
     * @return orientation of the arc.
     */
    double getRotation(Unit unit);

    /**
     * Sets this <code>GraphicArc</code>'s start bearing. The arc is stroked
     * counter-clockwise from start to end.
     * @param start the start Orientation.
     * @param unit the <code>Unit</code> for the angle value.
     */
    void setStart(double start, Unit unit);

    /**
     * Returns this <code>GraphicArc</code>'s start bearing. The arc is stroked
     * counter-clockwise from start to end.
     * @param unit the <code>Unit</code> for the angle value.
     * @return the arc start Orientation.
     */
    double getStart(Unit unit);

    /**
     * Sets this <code>GraphicArc</code>'s end bearing.  The arc is stroked
     * counter-clockwise from start to end.
     * @param end the end orientation.
     * @param unit the <code>Unit</code> for the angle value.
     */
    void setEnd(double end, Unit unit);

    /**
     * Returns this <code>GraphicArc</code>'s ending bearing. The arc is stroked
     * counter-clockwise from start to end.
     * @param unit the <code>Unit</code> for the angle value.
     * @return the arc end orientation.
     */
    double getEnd(Unit unit);

    /**
     * Sets the closureType, which determines how the endpoints of this
     * <code>GraphicArc</code> are connected.  This is one of the constants defined in the
     * <code>ArcClosure</code> class.  The integer values corresponding to
     * these constants are the same as the closure type values in
     * <code>java.awt.geom.Arc2D</code>.
     * @param closureType  Closure type of the arc (one of the ArcClosure
     * constants: OPEN, CHORD, or PIE)
     */
    void setClosureType(ArcClosure closureType);

    /**
     * Returns the closureType.
     * @return Closure type of the arc (one of <code>ArcClosure</code> constants
     * OPEN, CHORD, or PIE).  The integer values corresponding to these
     * constants are the same as the constants OPEN, CHORD, and PIE from
     * <code>java.awt.geom.Arc2d</code>.
     */
    ArcClosure getClosureType();

    /**
     * Indicates whether this <code>GraphicArc</code> is displaying an anchor handle for changing
     * the rotation of the defining ellipse.
     */
    boolean isAllowingRotation();

    /**
     * Sets the boolean that indicates whether this <code>GraphicArc</code> displays
     * an anchor handle for changing the rotation of the defining ellipse.
     */
    void setAllowingRotation(boolean newValue);

    /**
     * Indicates whether this <code>GraphicArc</code> is a circle. The implementation should
     * test for this based on the geometry settings.
     */
    boolean isCircle();

    /**
     * Indicates whether this <code>GraphicArc</code> is a closed ellipse. The implementation should
     * test for this based on the geometry settings.
     */
    boolean isClosedEllipse();

    //**  EDITABLE/ANIMATION  **

    /**
     * Indicates whether this <code>GraphicArc</code> is displaying edit handles for changing
     * the angular extents of the defining ellipse.
     */
    boolean isAllowingExtentsChange();

    /**
     * Sets the boolean that indicates whether this <code>GraphicArc</code> is displaying
     * edit handles for changing the angular extents of the defining ellipse.
     */
    void setAllowingExtentsChange(boolean newValue);

    //**  PROJECTED  **

    /**
     * Returns the <code>GraphicStyle</code> for this <code>GraphicPolygon</code>,
     * which is required to be a <code>PolygonSymbolizer</code>.
     * @return the GraphicPolygon's <code>GraphicStyle</code>.
     */
    PolygonSymbolizer getPolygonSymbolizer();

    /**
     * Sets the algorithm that is used in computing the "in-between" pixels
     * of the <code>ArcClosure</code> verteces when the arc is rendered. This does not
     * when there is no closure, i.e. when isClosedEllipse() is false, or
     * for <code>ArcClosure.OPEN</code>.
     *
     * @param pathType This must be one of the static constants in
     *   the PathType class or one of its subclasses.
     */
    void setClosurePathType(PathType pathType);

    /**
     * Retrieves the algorithm that is used in computing the "in-between" pixels
     * of the <code>ArcClosure</code> verteces when the arc is rendered. This does not
     * when there is no closure, i.e. when isClosedEllipse() is false, or
     * for <code>ArcClosure.OPEN</code>.
     */
    PathType getClosurePathType();

    /**
     * Instances of the <code>ArcClosure</code> class represent the various
     * methods of connecting the endpoints of an arc.  There are three static
     * constants defined in this class that can be used as parameters to
     * the <code>setClosureType</code> method.
     *
     * @author Open GIS Consortium, Inc.
     */
    public static class ArcClosure extends SimpleEnumerationType<ArcClosure> {
        /**
         * Serial number for compatibility with different versions.
         */
        private static final long serialVersionUID = 7401948294780735937L;

        /**
         * The list of enumeration available in this virtual machine.
         * <strong>Must be declared first!</strong>.
         */
        private static final List<ArcClosure> VALUES = new ArrayList<ArcClosure>(3);

        /**
         * Closure type that indicates that the endpoints of the arc
         * should not be connected.
         */
        public static final ArcClosure OPEN = new ArcClosure("OPEN", "");

        /**
         * Closure type that indicates that one line should be drawn between
         * the endpoints of the arc.
         */
        public static final ArcClosure CHORD = new ArcClosure("CHORD", "");

        /**
         * Closure type that indicates that two lines, one from each
         * endpoint of the arc, should be drawn to the center of the ellipse
         * defining the arc.
         */
        public static final ArcClosure PIE = new ArcClosure("PIE", "");

        /**
         * Constructor that should only be called to create the static
         * constants in this class.
         */
        private ArcClosure(String name, String description) {
            super(VALUES, name, description);
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
         *
         * @deprecated Use {@link #values} instead.
         */
        @Deprecated
        public static ArcClosure[] getArray() {
            return values();
        }

        /**
         * Returns the list of <code>ArcClosure</code>s.
         */
        public static ArcClosure[] values() {
            synchronized (VALUES) {
                return (ArcClosure[]) VALUES.toArray(new ArcClosure[VALUES.size()]);
            }
        }

        /**
         * Returns the list of enumerations of the same kind than this enum.
         */
        public /*{ArcClosure}*/ CodeList[] family() {
            return values();
        }
    }  // end class ArcClosure
}
