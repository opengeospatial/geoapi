/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;


/**
 * Defines the location and precise orientation in 3-dimensional space of a defined ellipsoid
 * (or sphere) that approximates the shape of the earth. Used also for Cartesian coordinate
 * system centered in this ellipsoid (or sphere).
 *  
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see Ellipsoid
 * @see PrimeMeridian
 */
public interface GeodeticDatum extends Datum {
    /**
     * Returns the ellipsoid.
     *
     * @return The ellipsoid.
     * @association usesEllipsoid
     */
    public Ellipsoid getEllipsoid();

    /**
     * Returns the prime meridian.
     *
     * @return The prime meridian.
     * @association usesPrimeMeridian
     */
    public PrimeMeridian getPrimeMeridian();
}
