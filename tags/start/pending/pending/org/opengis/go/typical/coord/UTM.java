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

import com.dautelle.units.SI;
import com.dautelle.units.Unit;

/**
 * <code>UTM</code> represents
 * a position on the earth in the Universal Transverse Mercator coordinate
 * system.  In this coordinate system, geographic locations are denoted
 * by a UTM zone, a northing value in meters, and an easting value in meters.
 * The UTM zone consists of an integer between 1 and 60 and an character
 * from 'A' to 'Z'.
 * <p>
 * As a convenience, the UTM interface also provides an altitude value that
 * can be set and retrieved.  This is so that when a conversion is performed
 * from <code>LatLonAlt</code> to <code>UTM</code> and then back to
 * <code>LatLonAlt</code>, there will be no loss of altitude information.
 * Many applications will choose to ignore the altitude value present in a
 * UTM coordinate.  Equality comparisons of this object with another
 * UTM should not consider the altitude in the comparison.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class UTM implements DirectPosition {

	/**
	 * The Default Coordinate Reference System URL for this coordinate.
	 * This constant referes to a CRS based on the "Abidjan 1987" datum at UTM Zone 30N, corresponding
	 * to EPSG Code 2041.
	 */
	public static final String DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL = "urn:x-ogc:srs:EPSG::2041";
	
    /**
     * The Coordinate Reference System for this coordinate.
     */
    private CoordinateReferenceSystem crs;
    
    /**
     * The values of this coordinate.
     */
    private double[] ordinates;
    
	/**
	 * The units of this coordinate.
	 */
	private final Unit[] units = {SI.METER, SI.METER, SI.METER};
	
	/**
	 * A flag for whether the altitude is known.
	 */
	private boolean isAltKnown = false;
	
    ////
    // Constructors.
    
    /**
     * Initializes northing, easting, altitude, and Coordinate Reference System to the supplied parameters.
     * @param northing  northing value
     * @param easting   easting value
     * @param altitude  altitude value
     * @param zoneLetter the zone letter
     * @param crsURL      the Coordinate Reference System URL
     */
    public UTM(double northing, double easting, double altitude, Unit unit, Unit altitudeUnit,  
			CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		this.crs = crs;
        ordinates = new double[crs.getDimension()];
        setNorthing(northing, unit);
        setEasting(easting, unit);
        setAltitude(altitude, altitudeUnit);
    }
    
    /**
     * Initializes the Coordinate Reference System to the supplied parameter.
     * @param crs       the Coordinate Reference System
     */
    public UTM(CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		this.crs = crs;
        ordinates = new double[crs.getDimension()];
    }
    
	/**
	 * Initializes using the default Coordinate Reference System.
	 */
	public UTM() throws UnsupportedCRSException {
		this.crs = findCRS(DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL);
		ordinates = new double[crs.getDimension()];
	}
	
    ////
    // Methods for UTM.
    
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
     * Returns true if the given object is a UTM object, and
     * has x and y values and a Coordinate Reference System equal to this one.
     * 
     * @param obj the object to compare with this coordinate.
     * @return true if they are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof UTM) {
            return equals((UTM)obj);
        }
        return false;
    }

    /**
     * Returns true if the given UTM
     * has x and y values and a Coordinate Reference System equal to this one.
     *
     * @param utm the UTM to compare with this coordinate.
     * @return true if they are equal, false otherwise
     */
    public boolean equals(UTM utm){
        boolean equiv = true;
        equiv &= (utm.getCoordinateReferenceSystem().equals(crs));
        equiv &= (utm.getNorthing(units[0]) == getNorthing(units[0]));
        equiv &= (utm.getEasting(units[1]) == getEasting(units[1]));
        equiv &= (utm.getAltitude(units[2]) == getAltitude(units[2]));
        return equiv;
    }

    /**
     * Returns the northing value of this coordinate.
     */
    public double getNorthing(Unit unit) {
        return units[0].getConverterTo(unit).convert(ordinates[0]);
    }

    /**
     * Sets the northing value of this coordinate.
     */
    public void setNorthing(double northing, Unit unit) {
        ordinates[0] = unit.getConverterTo(units[0]).convert(northing);
    }
    
    /**
     * Retrieves the easting value of this coordinate.
     */
    public double getEasting(Unit unit) {
        return units[1].getConverterTo(unit).convert(ordinates[1]);
    }
    
    /**
     * Sets the easting value of this coordinate.
     */
    public void setEasting(double easting, Unit unit) {
        ordinates[1] = unit.getConverterTo(units[1]).convert(easting);
    }

    /**
     * Retrieves the altitude value stored in this UTM as a convenience.
     */
    public double getAltitude(Unit unit) {
        return units[2].getConverterTo(unit).convert(ordinates[2]);
    }
    
    /**
     * Sets the altitude value stored in this UTM as a convenience.  This
     * value is stored only so that coordinate conversion (such as to LatLonAlt)
     * will not lose any information.
     */
    public void setAltitude(double altitude, Unit unit){
        ordinates[2] = unit.getConverterTo(units[2]).convert(altitude);
		isAltKnown = true;
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
		crs = null;
		ordinates = null;
    }

    /**
     * Clone this UTM.
     */
    public Object clone() {
        UTM result = new UTM(crs);
        result.ordinates[0] = ordinates[0];
        result.ordinates[1] = ordinates[1];
        result.ordinates[2] = ordinates[2];
		result.isAltKnown = isAltKnown;
        return result;
    }

    /**
     * Returns the Coordinate Reference System for this UTM.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }
}

