/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema;

import org.opengis.crs.crs.CoordinateReferenceSystem;

/**
 * Holds the coordinates for a position within some coordinate reference system. Since
 * <code>DirectPosition</code>s, as data types, will often be included in larger objects
 * (such as {@linkplain org.opengis.gm.Geometry geometries}) that have references to
 * {@linkplain CRS}, the {@link #getCoordinateReferenceSystem} method may returns
 * <code>null</code> if this particular <code>DirectPosition</code> is included in a larger
 * object with such a reference to a {@linkplain CRS}. In this case, the cordinate reference
 * system is implicitly assumed to take on the value of the containing object's {@linkplain CRS}.
 * 
 * @UML datatype DirectPosition
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */

public interface DirectPosition {

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

    public void setOrdinate(int dimension, double value);

    /**
     * Gets the ordiante value along the specified dimension for this
     * <code>DirectPosition</code>.
     * @param dimension the dimension for the ordinate of interest.
     * @return the ordinate value for the given dimension.
     */

    public double getOrdinate(int dimension);

}

