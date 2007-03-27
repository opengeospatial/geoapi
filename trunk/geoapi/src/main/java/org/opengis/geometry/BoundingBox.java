/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry;

// OpenGIS direct dependencies
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.metadata.extent.GeographicBoundingBox;  // For javadoc

// Annotations
import org.opengis.annotation.Extension;


/**
 * Represents an envelope with a common coordinate reference system.
 * <p>
 * This interface combines the ideas of {@link GeographicBoundingBox} with
 * those of {@link Envelope}. We have provided convenience methods using non
 * Java Beans naming conventions to assist in accessing the formal
 * properties of this object.
 * <p>
 * This object contains no additional information beyond that provided
 * by Envelope.
 * 
 * @author Jody Garnett, Refractions Research
 * @since GeoAPI 2.1
 */
@Extension
public interface BoundingBox extends Envelope {
	/**
	 * Provides the minimium easting ordinate.
	 * <p>
	 * This is a helper method for <code>{@linkplain #getMinimum getMinimum}(i)</code>
	 * where <var>i</var> is the ordindate used to represent easting.
	 */
    double minX();
    
    /**
	 * Provides the maximum easting ordinate.
	 * <p>
	 * This is a helper method for <code>{@linkplain #getMaximum getMaximum}(i)</code>
	 * where <var>i</var> is the ordindate used to represent easting.
	 */
    double maxX();
    
	/**
	 * Provides the minimium northing ordinate.
	 * <p>
	 * This is a helper method for <code>{@linkplain #getMinimum getMinimum}(i)</code>
	 * where <var>i</var> is the ordindate used to represent northing.
	 */
    double minY();
    
    /**
	 * Provides the northing easting ordinate.
	 * <p>
	 * This is a helper method for <code>{@linkplain #getMaximum getMaximum}(i)</code>
	 * where <var>i</var> is the ordindate used to represent northing.
	 */
    double maxY();
    
    /**
     * The coordinate reference system common direct positions defining
     * this bounding box.
     * 
     * @return CoordinateRefernceSystem of provided ordinates.
     *
     * @todo Should be provided by {@code Envelope.getCoordinateReferenceSystem()}.
     */
    CoordinateReferenceSystem crs();
    
    /**
     * Initialize the bounding box with another bounding box.
     */
    void init( BoundingBox bounds);
    
    /**
     * Include the provided bounding box, expanding as necesary.
     */
    void include( BoundingBox bounds );
    
    /**
     * Include the provided coordinates, expanding as necessary.
     */
    void include( double x, double y );
    
    /**
     * True if lengths of all ordinates is zero.
     * 
     * @return true if bounding box is empty
     */
    boolean isEmpty();
    
    /**
     * Check if we intersect the provided bounds.
     * 
     * @return true of the two bounds intersect
     */
    boolean intersects( BoundingBox bounds );
    
    /** 
     * Quick range check of provided position.
     * 
     * @return true if provided location is contained by this bounding box.
     */
    boolean contains( double x, double y);
    
    /** 
     * Quick range check of provided location.
     * 
     * @return true if provided location is contained by this bounding box.
     */
    boolean contains( DirectPosition location );
    
    /** 
     * Quick range check of provided bounds.
     * 
     * @return true if provided bounds are contained by this bounding box.
     */
    boolean contains( BoundingBox bounds );
}
