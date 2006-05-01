/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.geometry;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.DirectPosition;

/**
 * The <code>Bounds</code> interface serves as the base interface
 * for all objects that represent a geometric boundary of some kind.
 * The implementation of the <code>contains</code> method should determine 
 * whether a given point lies inside or outside of this boundary.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public interface Bounds {

    /**
     * This function returns true if two conditions are met: first, that the
     * object has been initialized with valid values; and second, that those
     * values form a valid, non-empty boundary.  What constitutes a "valid"
     * boundary will depend on the particular subclass of
     * <code>Bounds</code> and may depend on the types of coordinates used
     * or the coordinate space in which they reside.  An XY
     * bounding rectangle on a map canvas may not be valid if its "upper left"
     * is to the right of its "lower right".
     */
    public boolean isValid();

    /**
     * This function sets a flag that indicates whether or not this object
     * represents a valid boundary.  This function should be used by code that
     * manipulates bounds objects to indicate whether the return value of an
     * operation is invalid (e.g. the intersection of two rectangles that do
     * not overlap) or valid.
     */
    public void setValid(boolean newValue);

    /**
     * This function determines if the given point lies within the
     * boundary represented by this object.
     * @return true if the point lies inside; false otherwise.
     */
    public boolean contains(DirectPosition location);

    /**
     * Calling this method should enlarge the boundary represented
     * by this object such that it includes the given point.
     * @param location The location to include.
     */
    public void expandToInclude(DirectPosition location);

    /**
     * Returns the Coordinate Reference System for this Bounds. Used by subclasses of Bounds.
     * @return the Coordinate Reference System for this Bounds.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Sets the Coordinate Reference System for this Bounds. Used by subclasses of Bounds.
     * @param crs the Coordinate Reference System for this Bounds.
     */
    public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs);
}
