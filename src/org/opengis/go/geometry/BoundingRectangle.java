/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.geometry;

import org.opengis.spatialschema.geometry.DirectPosition;

/**
 * <code>BoundingRectangle</code> defines a common abstraction for
 * implementations of boundaries defined by a top-left 
 * <code>DirectPosition</code> and a bottom-right <code>DirectPosition</code>.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface BoundingRectangle extends Bounds {

    /**
     * Sets the <code>DirectPosition</code> for the top-left position of the
     * rectangle.
     * @param coord the top-left position.
     */
    public void setTopLeft(DirectPosition coord);

    /**
     * Returns the <code>DirectPosition</code> for the top-left position of the 
     * rectangle.
     * @return the top-left position.
     */
    public DirectPosition getTopLeft();

    /**
     * Sets the <code>DirectPosition</code> for the bottom-right position of the
     * rectangle.
     * @param coord the bottom-right position.
     */
    public void setBottomRight(DirectPosition coord);

    /**
     * Returns the <code>DirectPosition</code> for the bottom-right position of the
     * rectangle.
     * @return the bottom-right position.
     */
    public DirectPosition getBottomRight();

    /**
     * Returns the <code>BoundingRectangle</code> of the intersection of this
     * <code>BoundingRectangle</code> and the one passed in.
     * @param bounds the BoundingRectangle to intersect with this
     * <code>BoundingRectangle</code>.
     * @param result An optional BoundingRectangle to hold the result
     * of this operation. If <code>result</code> is <code>null</code>, this
     * method will create a new <code>BoundingRectangle</code> to return.
     * @return the intersection in the form of a BoundingRectangle, which may
     * be invalid (according to the <code>isValid()</code> function) if the
     * two BoundingRectangles do not intersect.  The return value should
     * never be null.
     */
    public BoundingRectangle getIntersection(BoundingRectangle bounds, BoundingRectangle result);

    /**
     * Returns the smallest <code>BoundingRectangle</code> that contains both
     * this <code>BoundingRectangle</code> and the
     * <code>BoundingRectangle</code> passed in.
     * @param bounds the BoundingRectangle to combine with this
     * BoundingRectangle.
     * @param result An optional BoundingRectangle to hold the result
     * of this operation. If <code>result</code> is <code>null</code>, this
     * method will create a new <code>BoundingRectangle</code> to return.
     * @return the combined extent in the form of a BoundingRectangle
     * which may be invalid (according to the <code>isValid()</code> function)
     * if it is not possible to construct a rectangle that encompasses
     * both of the given rectangles.  The return value should never
     * be null.
     */
    public BoundingRectangle getCombinedExtent(BoundingRectangle bounds, BoundingRectangle result);
}
