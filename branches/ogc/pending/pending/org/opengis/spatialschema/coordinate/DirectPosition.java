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
 * <code>DirectPosition</code> defines a common abstraction for classes that
 * encapsulate a location in some coordinate space.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface DirectPosition  {

    //**  deconstructor  **
    
    /**
     * Releases any resources that may have been allocated for this object.
     * It is an error to reference a DirectPosition after its <code>dispose</code>
     * method has been called.
     * <p>
     * For example, implementations might use this method to return this
     * <code>DirectPosition</code> back to a pool of <code>DirectPosition</code>s, if
     * the <code>SpatialschemaFactory</code> is implemented to
     * manage a pool of <code>DirectPosition</code>s.
     */
    public void dispose();

    //**  action methods  **


    /**
     * Makes an exact copy of this coordinate.
     */
    public Object clone() throws CloneNotSupportedException;
    
    //**  accessor  **
    
    /**
     * Returns the Coordinate Reference System of this
     * <code>DirectPosition</code>.
     * @return the Coordinate Reference System for this DirectPosition.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem();
    
    /**
     * Returns the dimensionality of this <code>DirectPosition</code>. 
     * This may be less than or equal to the dimensionality of the 
     * CoordinateReferenceSystem for this DirectPostiion.
     * @return the dimensionality of this DirectPosition.
     */
    public int getDimension();
    
    /**
     * Sets the ordinate value along the specified dimension for this 
     * <code>DirectPosition</code>.
     * @param dimension the dimension for the ordinate of interest.
     * @param value the ordinate value  of interest.
     */
    public void setOrdinate(int dimension,double value);
    
    /**
     * Gets the ordiante value along the specified dimension for this
     * <code>DirectPosition</code>.
     * @param dimension the dimension for the ordinate of interest.
     * @return the ordinate value for the given dimension.
     */
    public double getOrdinate(int dimension);
}
