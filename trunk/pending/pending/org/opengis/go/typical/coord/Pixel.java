/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.typical.coord;

import java.awt.geom.Point2D;
import java.util.Properties;

import org.opengis.crs.coordrefsys.CoordinateReferenceSystem;
import org.opengis.crs.coordrefsys.CoordinateReferenceSystemFactory;
import org.opengis.crs.coordrefsys.UnsupportedCRSException;
import org.opengis.go.CommonFactoryManager;
import org.opengis.spatialschema.coordinate.DirectPosition;

/**
 * <code>Pixel</code> extends the native pixel representation of the underlying
 * language.
 * <p>
 * In Java, <code>Pixel</code> models the public
 * API of the abstract <code>java.awt.geom.Point2D</code> class.  Thus the
 * classes that implement <code>Pixel</code> give all the
 * functionality of the java classes that implement
 * <code>java.awt.geom.Point2D</code>, along with the additional benefit of
 * being tagged as <code>DirectPosition</code> as well.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class Pixel extends Point2D implements DirectPosition {
    

	/**
	 * The Default Coordinate Reference System URL for this coordinate.
	 */
	// TODO: Fix the default value.
	public static final String DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL = "urn:x-ogc:srs:OGC::XXXXXX";

	/**
	 * The Coordinate Reference System for this coordinate.
	 */
	private CoordinateReferenceSystem crs;
    
    /**
     * The values of this coordinate.
     */
    private double[] ordinates;
    
    ////
    // Constructors.
    
    /**
     * Initializes x, y, and Coordinate Reference System to the supplied parameters.
     * @param x         x value in pixels
     * @param y         y value in pixels
     * @param crs       the Coordinate Reference System 
     */
    public Pixel(double x, double y, CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		super();
		this.crs = crs;
        ordinates = new double[crs.getDimension()];
        setLocation(x,y);
    }
    
    /**
     * Initializes lat, lon, alt, and datum to the supplied parameters
     * @param crs       the Coordinate Reference System 
     */
    public Pixel(CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		super();
		this.crs = crs;
        ordinates = new double[crs.getDimension()];
    }
    
	/**
     * Initializes using the default Coordinate Reference System.
	 */
	public Pixel() throws UnsupportedCRSException {

		this.crs = findCRS(DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL);
		ordinates = new double[crs.getDimension()];
	}
	
    ////
    // Methods for Pixel.
	
	/**
	 * Returns the Coordinate Reference System for the given URL.
	 * @param crsURL Coordinate Reference System URL.
	 * @return Coordinate Reference System.
	 */
	private CoordinateReferenceSystem findCRS(String crsURL) throws UnsupportedCRSException {
	   Properties props = new Properties();
	   props.setProperty(CoordinateReferenceSystemFactory.COORDINATE_REFERECE_SYSTEM_URL, crsURL);
	   CoordinateReferenceSystem crs;
	   try {
			crs = CommonFactoryManager.getCommonFactory("CommonFactory").getCoordinateReferenceSystemFactory().getCoordinateReferenceSystem(props);
	   } catch (ClassNotFoundException e) {
			throw new UnsupportedCRSException("ClassNotFoundException: " + e);
	   } catch (IllegalAccessException e) {
			throw new UnsupportedCRSException ("IllegalAccessExceptione: " + e);
	   } catch (InstantiationException e) {
			throw new UnsupportedCRSException ("InstantiationExceptione: " + e);
	   }
	   return crs;
	}
	
    /**
     * Returns true if the given object is a Pixel object, and
     * has x and y values and a Coordinate Reference System equal to this one.
     * 
     * @param obj the object to compare with this coordinate.
     * @return true if they are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Pixel) {
            return equals((Pixel)obj);
        }
        return false;
    }

    /**
     * Returns true if the given Pixel has x, y, and
     * has x and y values and a Coordinate Reference System equal to this one.
     *
     * @param pixel the Pixel to compare with this coordinate.
     * @return true if they are equal, false otherwise
     */
    public boolean equals(Pixel pixel){
        boolean equiv = true;
        equiv &= (pixel.getCoordinateReferenceSystem().equals(crs));
        equiv &= (pixel.getX() == getX());
        equiv &= (pixel.getY() == getY());
        return equiv;
    }
    
    /**
     * Returns the X coordinate of the point in double precision.
     * @return the x coordinate, as a double
     */
    public double getX() {
        return ordinates[0];
    }
        

    /**
     * Returns the Y coordinate of the point in double precision.
     * @return the y coordinate, as a double
     */
    public double getY() {
        return ordinates[1];
    }
    
    /**
     * Sets the location of this point to the specified coordinates.
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     */
    public void setLocation(double x, double y) {
        ordinates[0] = x;
        ordinates[1] = y;
    }

    ////
    // Methods supporting DirectPosition.
    
    /**
     * Returns the dimension of this coordiante.
     * @return the dimension.
     */
    public int getDimension() {
        return crs.getDimension();
    }
    
    /**
     * Generically sets the generic value if this coordinate for the given dimension.
     * @param dimension
     * @param ordinate
     */
    public void setOrdinate(int dimension, double ordinate) {
        ordinates[dimension] = ordinate;
    }
    
    /**
     * Generically returns the value of this coordinate for the given dimension.
     */
    public double getOrdinate(int dimension) {
        return ordinates[dimension];
    }

    /** 
     * Dispose of this class.
     */
    public void dispose() {
		crs = null;
		ordinates = null;
    }

    /**
     * Clone this Pixel.
     */
    public Object clone() {
        Pixel result = new Pixel(crs);
        result.ordinates[0] = ordinates[0];
        result.ordinates[1] = ordinates[1];
        return result;
    }

    /**
     * Returns the Coordinate Reference System for this Pixel.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }
}
