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

import java.util.Properties;

import org.opengis.crs.coordrefsys.CoordinateReferenceSystem;
import org.opengis.crs.coordrefsys.CoordinateReferenceSystemFactory;
import org.opengis.crs.coordrefsys.UnsupportedCRSException;
import org.opengis.go.CommonFactoryManager;
import org.opengis.spatialschema.coordinate.DirectPosition;

/**
 * <code>XY</code> represents
 * 2-dimensional vectors in some vector space over the real numbers.
 * This interface does not define the units of measure for the X and Y
 * coordinates.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class XY implements DirectPosition {
	
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
     * @param x         x value
     * @param y         y value
     * @param crs       the Coordinate Reference System 
     */
    public XY(double x, double y, 
    		CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		this.crs = crs;
        ordinates = new double[crs.getDimension()];       
        ordinates[0] = x;
        ordinates[1] = y;
    }
    
    /**
     * Initializes the Coordinate Reference System to the supplied parameter.
     * @param crs       the Coordinate Reference System 
     */
    public XY(CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		this.crs = crs;
        ordinates = new double[crs.getDimension()];
    }
    
	/**
	 * Initializes using the default Coordinate Reference System.
	 */
	public XY() throws UnsupportedCRSException {
		this.crs = findCRS(DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL);
		ordinates = new double[crs.getDimension()];
	}
	
    ////
    // Methods for XY.
	
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
     * Returns true if the given object is a XY object, and
     * has x and y values and a Coordinate Reference System equal to this one.
     * 
     * @param obj the object to compare with this coordinate.
     * @return true if they are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof XY) {
            return equals((XY)obj);
        }
        return false;
    }

    /**
     * Returns true if the given XY
     * has x and y values and a Coordinate Reference System equal to this one.
     *
     * @param xy the XY to compare with this coordinate.
     * @return true if they are equal, false otherwise
     */
    public boolean equals(XY xy){
        boolean equiv = true;
		equiv &= (xy.getCoordinateReferenceSystem().equals(crs));
        equiv &= (xy.getX() == getX());
        equiv &= (xy.getY() == getY());
        return equiv;
    }

    /**
     * Sets the X value of this coordinate.
     * @param x The new X value.
     */
    public void setX(double x) {
        ordinates[0] = x;
    }
    
    /**
     * Sets the Y value of this coordinate.
     * @param y The new Y value.
     */
    public void setY(double y) {
        ordinates[1] = y;
    }

    /**
     * Returns the X coordinate of this coordinate.
     * @return The X coordinate of this coordinate.
     */
    public double getX(){
        return ordinates[0];
    }

    /**
     * Returns the Y coordinate of this coordinate.
     * @return The Y coordinate of this coordinate.
     */
    public double getY() {
        return ordinates[1];
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
     * Clone this XY.
     */
    public Object clone() {
        XY result = new XY(crs);
        result.ordinates[0] = ordinates[0];
        result.ordinates[1] = ordinates[1];
        return result;
    }

    /**
     * Returns the Coordinate Reference System for this XY.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }
}
