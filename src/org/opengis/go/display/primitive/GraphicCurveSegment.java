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

// OpenGIS direct dependencies
import org.opengis.go.spatial.PathType;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.geometry.PointArray;
import org.opengis.spatialschema.geometry.primitive.CurveSegment;


/**
 * Defines common abstractions for implementations of 1-dimensional lines made up of
 * one or more line segments and of 2-dimensional lines made up of a closed set of
 * three or more line segments. The {@link #isClosed()} method provides the distinction.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public interface GraphicCurveSegment extends Graphic {

    /**
     * Sets the geometry based on ISO 19107 geometric forms.
     *
     * @param curveSegment a geometry CurveSegment.
     */
    public void setCurveSegment(CurveSegment curveSegment);

    /**
     * Returns the geometry based on ISO 19107 geometric forms.
     *
     * @return the geometry CurveSegment.
     */
    public CurveSegment getCurveSegment();

    /**
     * Returns this <code>GraphicCurveSegment</code>'s set of positions as a 
     * <code>PointArray</code>.  The returned <code>PointArray</code>
     * may or may not be identical to a <code>PointArray</code> passed in
     * to the <code>setPointArray</code> method.  It will definitely not
     * be the same <code>PointArray</code> if any positions have been
     * added, inserted, or deleted.
     * Value is acquired from the underlying LineString geometry for this Graphic.
     *
     * @return the set of positions as a <code>PointArray</code>.
     */
    public PointArray getPointArray();

    /**
     * Sets this <code>GraphicCurveSegment</code>'s set of positions to the 
     * given <code>PointArray</code>.  Any changes made to the given
     * <code>PointArray</code> after calling this method may adversly affect
     * this <code>GraphicCurveSegment</code>.
     * Value is set on the underlying LineString geometry for this Graphic.
     *
     * @param pointArray The new set of positions.
     */
    public void setPointArray(PointArray pointArray);

    /**
     * Returns the positions that make up the line segments.
     * Value is acquired from the underlying LineString geometry for this Graphic.
     *
     * @return the array positions.
     */
    public DirectPosition[] getPoints();

    /**
     * Sets the positions that make up the line segments.
     * Value is set on the underlying LineString geometry for this Graphic.
     *
     * @param coords the array positions.
     */
    public void setPoints(DirectPosition[] coords);

    /**
     * Appends the given position to the graphic linestring's array of
     * positions.  If the graphic linestring isClosed(), then the new position is added
     * to the end of the array, before the duplicated start position.
     * Value is set on the underlying LineString geometry for this Graphic.
     *
     * @param coord the postion to add.
     */
    public void addPoint(DirectPosition coord);

    /**
     * Removes the postion at the specified index from the array of 
     * positions.
     * Value is deleted on the underlying LineString geometry for this Graphic.
     *
     * @param index the index of the position to remove.
     */
    public void deletePoint(int index);

    /**
     * Returns the position at the specified index in the array of
     * positions.
     * Value is acquired from the underlying LineString geometry for this Graphic.
     *
     * @param index the index of the position to return.
     * @return the position at the given index.
     */
    public DirectPosition getPoint(int index);

    /**
     * Inserts the given position at the specified index in the array
     * of positions.
     * Value is inserted on the underlying LineString geometry for this Graphic.
     *
     * @param index the index to insert the new position at.
     * @param coord the position to insert.
     */
    public void insertPoint(int index, DirectPosition coord);

    /**
     * Replaces the position at the specified index in the array of positions
     * with the new, specified position.
     * Value is set on the underlying LineString geometry for this Graphic.
     *
     * @param index the index of the position to replace.
     * @param coord the position to store at the specified index.
     */
    public void setPoint(int index, DirectPosition coord);

    /**
     * Returns the boolean flag indicating whether the graphic linestring is closed
     * (representing a polygon) or open (a graphic linestring).
     *
     * @return whether or not the graphic linestring is closed.
     */
    public boolean isClosed();

    /**
     * Sets the graphic linestring open (false) or closed (true).  If the graphic linestring is
     * closed, then the start position will be duplicated and added to the end
     * of the array of positions.
     *
     * @param closed the flag indicating closed (true) or open (false).
     */
    public void setClosed(boolean closed);

    //**  EDITABLE/ANIMATION  **

    /**
     * Indicates whether clicking on an edge of this graphic linestring should insert
     * a new vertex at that location when the object is in edit mode.
     */
    public boolean isAllowingNewVertices();

    /**
     * Sets the boolean that indicates whether clicking on an edge of this
     * graphic linestring should insert a new vertex at that location.
     */
    public void setAllowingNewVertices(boolean newValue);

    //**  PROJECTED  **

    /**
     * Sets the method that is used in computing the "in-between" pixels
     * between vertices when this object is rendered on the screen.
     *
     * @param pathType The new path type. This must be one of the static constants in
     *   the PathType class or one of its subclasses.
     */
    public void setPathType(PathType pathType);

    /**
     * Retrieves the methods that is used in computing the "in-between"
     * pixels between vertices when this object is rendered on the screen.
     */
    public PathType getPathType();

}

