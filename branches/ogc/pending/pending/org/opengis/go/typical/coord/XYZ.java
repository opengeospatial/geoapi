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
/**
 * <code>XYZ</code> represents
 * 3-dimensional vectors in some vector space over the real numbers.
 * This interface does not define the units of measure for the X, Y, and Z
 * coordinates.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class XYZ {
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
     * The values of this coordinate. Overrides XY field.
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
    public XYZ(double x, double y, double z, CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		this.crs = crs;
		ordinates = new double[crs.getDimension()];       
		ordinates[0] = x;
		ordinates[1] = y;
        ordinates[2] = z;
    }
    
    /**
     * Initializes the Coordinate Reference System to the supplied parameter.
     * @param crs       the Coordinate Reference System 
     */
    public XYZ(CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		this.crs = crs;
		ordinates = new double[crs.getDimension()];
    }
    
	/**
	 * Initializes the Coordinate Reference System to the supplied parameter.
	 * @param crs       the Coordinate Reference System
	 */
	public XYZ() throws UnsupportedCRSException {
		crs = findCRS(DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL);
        ordinates = new double[crs.getDimension()];
	}
    
	////
	// Methods for XYZ.
	
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
     * Sets the X, Y, and Z values held by this coordinante.
     * @param x The new X value.
     * @param y The new Y value.
     * @param z The new Z value.
     */
    public void setXYZ(double x, double y, double z) {
        ordinates[0] = x;
        ordinates[1] = y;
        ordinates[2] = z;
    }
    
    /**
     * Returns the Z coordinate of this tuple.
     * @return The Z coordinate of this tuple.
     */
    public double getZ(){
        return ordinates[2];
    }
    
    ////
    // Methods supporting DirectPosition (override XY methods).
	
    /**
     * Generically sets the generic value if this coordinate for the given dimension.
     * @param dimension
     * @param ordinate
     */
    public void setOrdinate(double ordinate, int dimension) {
        ordinates[dimension] = ordinate;
    }
    
    /**
     * Generically returns the value of this coordinate for the given dimension.
     */
    public double getOrdinate(int dimension) {
        return ordinates[dimension];
    }

    /**
     * Clone this XYZ.
     */
    public Object clone() {
        XYZ result = new XYZ(crs);
        result.ordinates[0] = ordinates[0];
        result.ordinates[1] = ordinates[1];
        result.ordinates[2] = ordinates[2];
        return result;
    }
}
