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

import com.dautelle.units.Unit;

import org.opengis.crs.coordrefsys.CoordinateReferenceSystem;
import org.opengis.crs.coordrefsys.CoordinateReferenceSystemFactory;
import org.opengis.crs.coordrefsys.UnsupportedCRSException;
import org.opengis.go.CommonFactoryManager;
import org.opengis.spatialschema.coordinate.DirectPosition;

/**
 * <code>LatLonAlt</code> represents a position on the Earth denoted by 
 * latitude, longitude, and altitude. 
 * 
 * The default Coordinate Reference System for LatLonAlt is "WGS 84 (3D deg)", 
 * which is cataloged as EPSG code #63266413. 
 * Latitude is expressed in degrees, with north positive.
 * Longitude is expressed in degrees, with east positive.
 * Altitude is expressed in meters.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class LatLonAlt implements DirectPosition {
	
	/**
	 * The default Coordinate Reference System URL for this coordinate.
	 * This constant referes to CRS's based on the "WGS 1984 (3D deg)" datum, corresponding
	 * to EPSG Code 63266420. Latitude and Longitude are in degrees, and altitude 
	 * is in meters.
	 */
	public static final String DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL = "urn:x-ogc:srs:EPSG::63266413";

    /**
     * The Coordinate Reference System for this coordinate.
     */
    private CoordinateReferenceSystem crs;
    
    /**
     * The values of this coordinate.
     */
	private double[] ordinates;
			
	/**
	 * The units for this coordinate.
	 */
	private Unit[] units;
	  
    /**
     * A flag for whether the altitude is known.
     */
    private boolean isAltKnown = false;


    ////
    // Constructors.
    
    /**
     * Initializes latitude, longitude, altitude, and Coordinate Reference System to the supplied parameters.
     * 
     * @param lat       latitude in degrees
     * @param lon       longitude in degrees
     * @param alt       altitude in meters
     * @param unit      the unit for latitude and longitude
     * @param altUnit   the unit for altitude
     * @param crsURL       the Coordinate Reference System URL
     */
    public LatLonAlt(double lat, double lon, double alt, Unit unit, Unit altUnit, 
    		CoordinateReferenceSystem crs) throws UnsupportedCRSException {
    	this.crs = crs;
		units = crs.getUnits();
        ordinates = new double[crs.getDimension()];
        setLatLonAlt(lat, lon, alt, unit, altUnit);
    }
    
    /**
     * Initializes Coordinate Reference System to the supplied parameter.
     * @param crs       the Coordinate Reference System
     */
    public LatLonAlt(CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		this.crs = crs;
    	units = crs.getUnits();
        ordinates = new double[crs.getDimension()];
    }
    
	/**
     * Initializes using the default Coordinate Reference System for this coordinate type.
	 */
	public LatLonAlt() throws UnsupportedCRSException {
		this.crs = findCRS(DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL);
		units = crs.getUnits();
		ordinates = new double[crs.getDimension()];
	}
	
    ////
    // Methods for LatLonAlt.
	
	/**
	 * Returns the Coordinate Reference System for the given URL.
	 * @param crsURL Coordinate Reference System URL.
	 * @return Coordinate Reference System.
	 */
	private CoordinateReferenceSystem findCRS(String crsURL) 
			throws UnsupportedCRSException {
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
     * Sets the coordinate to the given parameters.
     * Calling this method will cause the <code>isAltitudeKnown()</code>
     * function to return true until <code>setAltitudeKnown()</code> is
     * called with a parameter of false.
     *
     * @param lat The new latitude coordinate.
     * @param lon The new longitude coordinate.
     * @param unit The Unit for the lat and lon coordinates.
     * @param alt The new altitude coordinate.
     * @param altUnit The Unit for the alt coordinate.
     */
    public void setLatLonAlt(double lat, double lon, double alt, Unit unit,
       		Unit altUnit){
		setLat(lat, unit);
		setLon(lon, unit);
		setAlt(alt, altUnit);
    }
    
    /**
     * Sets the coordinate to the given parameters.
     *
     * @param lat The new latitude coordinate.
     * @param lon The new longitude coordinate.
     * @param unit The Unit for the lat and lon coordinates.
     */
    public void setLatLon(double lat, double lon, Unit unit){
		setLat(lat, unit);
		setLon(lon, unit);
    }

    /**
     * Sets the latitude of this LatLonAlt to the given value.
     *
     * @param lat The new latitude.
     * @param unit The Unit for the latitude coordinate.
     */
    public void setLat(double lat, Unit unit){
     	ordinates[0] = unit.getConverterTo(units[0]).convert(lat);
    }

    /**
     * Returns the latitude of this LatLonAlt in terms of the specified Unit.
     * @param unit The Unit for the latitude coordinate.
     * @return The latitude of this LatLonAlt.
     */
    public double getLat(Unit unit){
        return units[0].getConverterTo(unit).convert(ordinates[0]);
    }

    /**
     * Sets the longitude of this LatLonAlt to the given value.
     *
     * @param lon The new longitude.
     * @param unit The Unit for the longitude coordinate.
     */
    public void setLon(double lon, Unit unit){
    	ordinates[1] = unit.getConverterTo(units[1]).convert(lon);
    }

    /**
     * Returns the longitude of this LatLonAlt in terms of the specified Unit.
     * @param unit The Unit for the longitude coordinate.
     * @return The longitude of this LatLonAlt.
     */
    public double getLon(Unit unit){
        return units[1].getConverterTo(unit).convert(ordinates[1]);
    }

    /**
     * Sets the altitude coordinate to the given parameter.
     * Calling this method will cause the <code>isAltitudeKnown()</code>
     * function to return true until <code>setAltitudeKnown()</code> is
     * called with a parameter of false.
     *
     * @param alt The new altitude coordinate.
     * @param unit The Unit of the altitude coordinate.
     */
    public void setAlt(double alt, Unit unit){
       	ordinates[2] = unit.getConverterTo(units[2]).convert(alt);
        isAltKnown = true;
    }

    /**
     * Returns the altitude of this LatLonAlt in terms of the specified Unit.
     * @param unit The Unit for the altitude coordinate.
     * @return The altitude of this LatLonAlt.
     */
    public double getAlt(Unit unit){
        return units[2].getConverterTo(unit).convert(ordinates[2]);
    }
    
    /**
     * Returns true if the given object is a LatLonAlt object, and
     * has latitude, longitude, and altitude and a Coordinate Reference System equal to this one.
     * 
     * @param obj   Object to compare with this coordinate.
     * @return true if they are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof LatLonAlt) {
            return equals((LatLonAlt)obj);
        }
        return false;
    }
    
    /**
     * Returns true if the given LatLonAlt has latitude, longitude, and
     * altitude and a Coordinate Reference System equal to this one.
     *
     * @param lla   LatLonAlt to compare with this coordinate.
     * @return true if they are equal, false otherwise.
     */
    public boolean equals(LatLonAlt lla){
    	boolean equiv = true;
        equiv &= (lla.getCoordinateReferenceSystem().equals(crs));
    	equiv &= (lla.getLat(units[0]) == ordinates[0]);
    	equiv &= (lla.getLon(units[1]) == ordinates[1]);
    	if (getDimension() > 2) {
    		equiv &= (lla.getAlt(units[2]) == ordinates[2]);
    	}
    	return equiv;
    }

    /**
     * Returns true if the altitude value in this LatLonAlt object is
     * known to be valid.  For coordinates where the altitude is not known
     * or not important, this function will return false.
     */
    public boolean isAltitudeKnown() {
    	return isAltKnown;
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
		units = null;
	}

	/**
     * Clone this LatLonAlt
	 */
    public Object clone() {
        LatLonAlt result = new LatLonAlt(crs);
        result.ordinates[0] = ordinates[0];
        result.ordinates[1] = ordinates[1];
        result.ordinates[2] = ordinates[2];
		result.units[0] = units[0];
		result.units[1] = units[1];
		result.units[2] = units[2];
        result.isAltKnown = isAltKnown;
        return result;
    }

	/**
	 * Returns the Coordinate Reference System for this LatLonAlt.
	 */
	public CoordinateReferenceSystem getCoordinateReferenceSystem() {
		return crs;
	}

}
