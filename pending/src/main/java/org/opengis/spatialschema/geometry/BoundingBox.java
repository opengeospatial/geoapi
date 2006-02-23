/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.Envelope;

/**
 * Represents an evenlope with a common coordinate refernce system.
 * <p>
 * This interface combines the ideas of GeogrpahicBoundingBox with
 * those of Envelope. We have provided convience methods using non
 * Java Beans naming conventions to assist in accessing the formal
 * properties of this object.
 * </p>
 * <p>
 * This object contains no additional information beyond that provided
 * by Envelope.
 * </p>
 * 
 * @author Jody Garnett, Refractions Research
 */
public interface BoundingBox extends Envelope {
	/**
	 * Provides the minimium easting ordinate.
	 * <p>
	 * This is a helper method for getMin( i )
	 * where i is the ordindate used to represent easting.
	 */
    double minX();
    
    /**
	 * Provides the maximum easting ordinate.
	 * <p>
	 * This is a helper method for getMax( i )
	 * where i is the ordindate used to represent easting.
	 */
    double maxX();
    
	/**
	 * Provides the minimium northing ordinate.
	 * <p>
	 * This is a helper method for getMin( i )
	 * where i is the ordindate used to represent northing.
	 */
    double minY();
    
    /**
	 * Provides the northing easting ordinate.
	 * <p>
	 * This is a helper method for getMax( i )
	 * where i is the ordindate used to represent northing.
	 */
    double maxY();
    
    /**
     * The coordinate reference system common direct positions defining
     * this bounding box.
     * 
     * @return CoordinateRefernceSystem of provided ordinates.
     */
    CoordinateReferenceSystem crs();
    
    /**
     * Include the provided bounding box, expanding as necesary.
     * @param bounds
     */
    public void include( BoundingBox bounds );
    
    /**
     * True if lengths of all ordinates is zero.
     * 
     * @return true if bounding box is empty
     */
    public boolean isEmpty();
    
    /**
     * Check if we intersect the provided bounds.
     * 
     * @param bounds 
     * @return true of the two bounds intersect
     */
    public boolean intersects( BoundingBox bounds );
    
    /** 
     * Quick range check of provided position.
     * 
     * @param x
     * @param y
     * @return true if provided location is contained by this bounding box.
     */
    public boolean contains( double x, double y);
    
    /** 
     * Quick range check of provided location.
     * 
     * @param x
     * @param y
     * @return true if provided location is contained by this bounding box.
     */
    public boolean contains( DirectPosition location );
    
    /** 
     * Quick range check of provided bounds.
     * 
     * @param x
     * @param y
     * @return true if provided bounds are contained by this bounding box.
     */
    public boolean contains( BoundingBox bounds );
}
