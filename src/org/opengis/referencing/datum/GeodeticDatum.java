/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.datum;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Defines the location and precise orientation in 3-dimensional space of a defined ellipsoid
 * (or sphere) that approximates the shape of the earth. Used also for Cartesian coordinate
 * system centered in this ellipsoid (or sphere).
 *
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see Ellipsoid
 * @see PrimeMeridian
 */
///@UML (identifier="CD_GeodeticDatum")
public interface GeodeticDatum extends Datum {
    /**
     * Returns the ellipsoid.
     */
/// @UML (identifier="usesEllipsoid", obligation=MANDATORY)
    Ellipsoid getEllipsoid();

    /**
     * Returns the prime meridian.
     */
/// @UML (identifier="usesPrimeMeridian", obligation=MANDATORY)
    PrimeMeridian getPrimeMeridian();
}
