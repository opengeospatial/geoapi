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

import org.opengis.crs.FactoryException;
import org.opengis.crs.crs.CoordinateReferenceSystem;
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
    
    //*************************************************************************
    //
    //*************************************************************************
    
    /**
     * The Default Coordinate Reference System URL for this coordinate.
     */
    // TODO: Fix the default value.This should be an Derived Coordinate Reference System of type Engineering.
    public static final String DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL = "urn:x-ogc:srs:OGC::XXXXXX";

    //*************************************************************************
    //
    //*************************************************************************
    
    /**
     * The Coordinate Reference System for this coordinate.
     */
    private CoordinateReferenceSystem crs;

    /**
     * The values of this coordinate. Overrides XY field.
     */
    private double[] ordinates;

    //*************************************************************************
    // Constructors.
    //*************************************************************************

    /**
     * Initializes x, y, and Coordinate Reference System to the supplied parameters.
     * @param x         x value
     * @param y         y value
     * @param crs       the Coordinate Reference System
     */
    public XYZ(double x, double y, double z, CoordinateReferenceSystem crs) {
        setCRS(crs);
        ordinates[0] = x;
        ordinates[1] = y;
        ordinates[2] = z;
    }

    /**
     * Initializes the Coordinate Reference System to the supplied parameter.
     * @param crs       the Coordinate Reference System
     */
    public XYZ(CoordinateReferenceSystem crs) {
        setCRS(crs);
    }

    /**
     * Initializes the Coordinate Reference System to the supplied parameter.
     */
    public XYZ() {
        setCRS(findCRS(DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL));
    }

    //*************************************************************************
    // Methods for XYZ.
    //*************************************************************************

    private void setCRS(CoordinateReferenceSystem crs) {
        this.crs = crs;
        if (crs != null) {
            ordinates = new double[crs.getCoordinateSystem().getDimension()];
        } else {
            ordinates = new double[3];
        }
    }

    /**
     * Returns the Coordinate Reference System for the given URL.
     * @param crsURL Coordinate Reference System URL.
     * @return Coordinate Reference System.
     */
    private CoordinateReferenceSystem findCRS(String crsURL) {
        CoordinateReferenceSystem crs = null;
        try {
            crs = CommonFactoryManager.getCRSAuthorityFactory().createCoordinateReferenceSystem(crsURL);
        } catch(FactoryException fe) {
            System.err.println(fe.getMessage());
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
    public double getZ() {
        return ordinates[2];
    }

    //*************************************************************************
    // Methods supporting DirectPosition (override XY methods).
    //*************************************************************************

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
     * Returns all coordinates.
     */
    public double[] getCoordinates() {
        return (double[]) ordinates.clone();
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
