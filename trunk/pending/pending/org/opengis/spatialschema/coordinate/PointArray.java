/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.spatialschema.coordinate;

import org.opengis.crs.coordrefsys.CoordinateReferenceSystem;


/**
 * The <code>PointArray</code> interface outlines a means of efficiently storing large
 * numbers of homogeneous <code>DirectPosition</code>s; i.e. all having the 
 * same <code>CoordinateReferenceSystem</code>.
 * Classes implementing the <code>PointArray</code> interface are
 * not required to store only one type of <code>DirectPosition</code> (the benefit 
 * of a homogenous collection arises in sub-interfaces). A simple implementation of 
 * <code>PointArray</code> will generally be no more efficient than a simple 
 * array of <code>DirectPosition</code>s.
 */
public interface PointArray {
    
    /**
     * Returns the size (the number of elements) of this 
     * <code>PointArray</code>.
     * @return this <code>PointArray</code>'s size.
     */
    public int size();
    
    /**
     * Returns the elements of this <code>PointArray</code> as an array of
     * <code>DirectPosition</code>s.
     * @return the elements as an array of DirectPositions.
     */
    public DirectPosition[] toArray();
       
    /**
     * Returns the Coordinate Reference System of this
     * <code>PointArray</code>.
     * @return the Coordinate Reference System for this PointArray.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem();
    
    /**
     * Returns the dimensionality of the coordinates in this <code>PointArray</code>. 
     * This may be less than or equal to the dimensionality of the 
     * CoordinateReferenceSystem for these coordinates.
     * @return the dimensionality of this PointArray.
     */
    public int getDimension();
    
    /**
     * Sets the <code>DirectPosition</code> in the particular location in this 
     * <code>PointArray</code>.
     * @param index the location in the PointArray.
     * @param directPosition the DirectPosition to add to the PointArray.
     */
    public void setDirectPosition(int index, DirectPosition directPosition);
    
    /**
     * Gets the <code>DirectPosition</code> at the particular location in this 
     * <code>PointArray</code>. If the directPostion argument references
     * a <code>DirectPosition</code> object, that object will be populated with the
     * value from the List.
     * @param index the location in the PointArray.
     * @param directPosition the DirectPosition to populate with the value from the PointArray.
     * @return the DirectPosition corresponding to the index.
     */
    public DirectPosition getDirectPosition(int index, DirectPosition directPosition);

}