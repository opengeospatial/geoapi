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

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.ImageCRS;
import org.opengis.go.CommonFactoryManager;
import org.opengis.spatialschema.geometry.DirectPosition;

/**
 * <code>Pixel</code> extends the native pixel representation of the
 * underlying language.
 * <p>
 * In Java, <code>Pixel</code> models the public API of the abstract
 * <code>java.awt.geom.Point2D</code> class. Thus the classes that implement
 * <code>Pixel</code> give all the functionality of the java classes that
 * implement <code>java.awt.geom.Point2D</code>, along with the additional
 * benefit of being tagged as <code>DirectPosition</code> as well.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class Pixel extends Point2D implements DirectPosition {

    //*************************************************************************
    //  Static Fields
    //*************************************************************************

    /**
     * The Default Coordinate Reference System URL for this coordinate.
     */
    // TODO: Fix the default value. This should be an Image Coordinate Reference
    // System.
    public static final String DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL = "urn:x-ogc:srs:OGC::XXXXXX";

    //*************************************************************************
    //  Fields
    //*************************************************************************

    /**
     * The Coordinate Reference System for this coordinate.
     */
    private ImageCRS crs;

    /**
     * The values of this coordinate.
     */
    private double[] ordinates;

    //*************************************************************************
    // Constructors.
    //*************************************************************************

    /**
     * Initializes x, y, and Coordinate Reference System to the supplied
     * parameters.
     * 
     * @param x x value in pixels
     * @param y y value in pixels
     * @param crs the Coordinate Reference System
     */
    public Pixel(double x, double y, ImageCRS crs) {
        super();
        setCRS(crs);
        setLocation(x, y);
    }

    /**
     * Initializes lat, lon, alt, and datum to the supplied parameters
     * 
     * @param crs the Coordinate Reference System
     */
    public Pixel(ImageCRS crs) {
        super();
        setCRS(crs);
    }

    /**
     * Initializes using the default Coordinate Reference System.
     */
    public Pixel() {
        setCRS((ImageCRS)CommonFactoryManager.createCoordinateReferenceSystem(DEFAULT_COORDINATE_REFERENCE_SYSTEM_URL));
    }

    //*************************************************************************
    // Methods for Pixel.
    //*************************************************************************

    private void setCRS(ImageCRS crs) {
        this.crs = crs;
        if (crs != null) {
            ordinates = new double[crs.getCoordinateSystem().getDimension()];
        } else {
            ordinates = new double[2];
        }
    }

    /**
     * Returns true if the given object is a Pixel object, and has x and y
     * values and a Coordinate Reference System equal to this one.
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
     * Returns true if the given Pixel has x, y, and has x and y values and a
     * Coordinate Reference System equal to this one.
     * 
     * @param pixel the Pixel to compare with this coordinate.
     * @return true if they are equal, false otherwise
     */
    public boolean equals(Pixel pixel) {
        boolean equiv = true;
        equiv &= (pixel.getCoordinateReferenceSystem().equals(crs));
        equiv &= (pixel.getX() == getX());
        equiv &= (pixel.getY() == getY());
        return equiv;
    }

    /**
     * Returns the X coordinate of the point in double precision.
     * 
     * @return the x coordinate, as a double
     */
    public double getX() {
        return ordinates[0];
    }

    /**
     * Returns the Y coordinate of the point in double precision.
     * 
     * @return the y coordinate, as a double
     */
    public double getY() {
        return ordinates[1];
    }

    /**
     * Sets the location of this point to the specified coordinates.
     * 
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     */
    public void setLocation(double x, double y) {
        ordinates[0] = x;
        ordinates[1] = y;
    }

    //*************************************************************************
    // Methods supporting DirectPosition.
    //*************************************************************************

    /**
     * Returns the dimension of this coordiante.
     * 
     * @return the dimension.
     */
    public int getDimension() {
        return crs.getCoordinateSystem().getDimension();
    }

    /**
     * Generically sets the generic value if this coordinate for the given
     * dimension.
     * 
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
     * Returns all coordinates.
     */
    public double[] getCoordinates() {
        return (double[])ordinates.clone();
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