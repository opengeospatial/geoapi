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

import org.opengis.go.CommonFactoryManager;
import org.opengis.go.spatial.PathType;
import org.opengis.crs.coordrefsys.CoordinateReferenceSystem;
import org.opengis.crs.coordrefsys.CoordinateReferenceSystemFactory;
import org.opengis.crs.coordrefsys.UnsupportedCRSException;
import org.opengis.spatialschema.coordinate.DirectPosition;

import com.dautelle.units.NonSI;
import com.dautelle.units.SI;
import com.dautelle.units.Unit;

/**
 * <code>RangeBearing</code> defines the position of one object with 
 * respect to the position of another object, using a measurement of
 * linear range and angle bearing.
 * <p>
 * An instance of <code>RangeBearing</code> is referenced to
 * a Derived Coordinate Reference System.
 * The origin of the Derived Coordinate Reference System is the position 
 * of the other object.
 * The position value of <code>RangeBearing</code> is computed 
 * relative to the origin of the Derived Coordinate Reference System, and
 * therefore with respect to the position of the other object.
 * <p>
 * The range is defined to be the length of the path line
 * the path, as specified by the PathType parameter.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class RangeBearing implements DirectPosition {
    
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

    /**
     * The units of this coordinate.
     */
    private final Unit[] units = {SI.METER, NonSI.DEGREE_ANGLE};
    
    /**
     * The PathType of this coordinate.
     */
    private PathType pathType;
    
    ////
    // Constructors.
    
    /**
	 * Initializes range, bearing, and Coordinate Reference System to the supplied parameters.
	 * @param range range value
	 * @param bearing bearing value
	 * @param pathType the path type
	 * @param crs the Coordinate Reference System 
	 * 
	 */
    public RangeBearing(double range, double bearing, Unit rangeUnit, Unit bearingUnit, PathType pathType, 
			CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		this.crs = crs;
        ordinates = new double[crs.getDimension()];
        setRange(range, rangeUnit);
		setBearing(bearing, bearingUnit);
        this.pathType = pathType;
    }
    
    /**
     * Initializes the Coordinate Reference System to the supplied parameter.
     * @param crs       the Coordinate Reference System 
     */
    public RangeBearing(CoordinateReferenceSystem crs) throws UnsupportedCRSException {
		this.crs = crs;
        ordinates = new double[crs.getDimension()];
    }
    
    /**
     * Initializes using the default Coordinate Reference System.
     */
    public RangeBearing() throws UnsupportedCRSException {
		this.crs = findCRS(DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL);
		ordinates = new double[crs.getDimension()];
    }
      
    ////
    // Methods for RangeBearing.
	
	/**
	 * Returns the Coordinate Reference System for the given URL.
	 * @param Coordinate Reference System URL.
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
     * Returns true if the given object is a RangeBearing object, and
     * has range and bearing values and a Coordinate Reference System equal to this one.
     * 
     * @param obj the object to compare with this coordinate.
     * @return true if they are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof RangeBearing) {
            return equals((RangeBearing)obj);
        }
        return false;
    }

    /**
     * Returns true if the given RangeBearing
     * has range and bearing values and a Coordinate Reference System equal to this one.
     *
     * @param rangeBearing the RangeBearing to compare with this coordinate.
     * @return true if they are equal, false otherwise
     */
    public boolean equals(RangeBearing rangeBearing){
        boolean equiv = true;
        equiv &= (rangeBearing.getCoordinateReferenceSystem().equals(crs));
        equiv &= (rangeBearing.getRange(units[0]) == ordinates[0]);
        equiv &= (rangeBearing.getBearing(units[1]) == ordinates[1]);
        return equiv;
    }
  
    /**
     * Sets the range in the specified units from the anchor location.
     * @param range The range from the anchor location.
     * @param unit  The Unit for the range value.
     */
    public void setRange(double range, Unit unit) {
        ordinates[0] = unit.getConverterTo(units[0]).convert(range);
    }

    /**
     * Returns the range measured in specified units from the anchor point.
     * @param unit The Unit for the range value.
     * @return The range measured from the anchor point.
     */
    public double getRange(Unit unit) {
        return units[0].getConverterTo(unit).convert(ordinates[0]);
    }

    /**
     * Sets the bearing in specified units clockwise from due north.
     * @param bearing The bearing, clockwise from due north.
     * @param unit    The Unit for the bearing value.
     */
    public void setBearing(double bearing, Unit unit) {
        ordinates[1] = unit.getConverterTo(units[0]).convert(bearing);
    }

    /**
     * Returns the bearing measured in specified units clockwise from due north.
     * @param unit The Unit for the bearing value.
     * @return The bearing measured clockwise from due north.
     */
    public double getBearing(Unit unit) {
        return units[1].getConverterTo(unit).convert(ordinates[1]);
    }

    /**
	 * Sets the PathType for this coordinate.
	 * @param pathType The path to take.
	 * 
	 */
    public void setPathType(PathType pathType){
        this.pathType = pathType;
    }

    /**
	 * Returns the PathType for this coordinate.
	 */
    public PathType getPathType() {
        return pathType;
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
     * Clone this RangeBearing
     */
    public Object clone() {
        RangeBearing result = new RangeBearing(crs);
        result.ordinates[0] = ordinates[0];
        result.ordinates[1] = ordinates[1];
        return result;
    }

    /**
     * Returns the Coordinate Reference System for this RangeBearing.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }
                                 
}

