/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.extent;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Geographic position of the dataset. This is only an approximate
 * so specifying the co-ordinate reference system is unnecessary.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="EX_GeographicBoundingBox")
public interface GeographicBoundingBox extends GeographicExtent {
    /**
     * Returns the western-most coordinate of the limit of the
     * dataset extent. The value is expressed in longitude in
     * decimal degrees (positive east).
     *
     * @return The western-most longitude between -180 and +180°.
     * @unitof Angle
     */
/// @UML (identifier="westBoundLongitude", obligation=MANDATORY)
    double getWestBoundLongitude();

    /**
     * Returns the eastern-most coordinate of the limit of the
     * dataset extent. The value is expressed in longitude in
     * decimal degrees (positive east).
     *
     * @return The eastern-most longitude between -180 and +180°.
     * @unitof Angle
     */
/// @UML (identifier="eastBoundLongitude", obligation=MANDATORY)
    double getEastBoundLongitude();

    /**
     * Returns the southern-most coordinate of the limit of the
     * dataset extent. The value is expressed in latitude in
     * decimal degrees (positive north).
     *
     * @return The southern-most latitude between -90 and +90°.
     * @unitof Angle
     */
/// @UML (identifier="southBoundLatitude", obligation=MANDATORY)
    double getSouthBoundLatitude();

    /**
     * Returns the northern-most, coordinate of the limit of the
     * dataset extent. The value is expressed in latitude in
     * decimal degrees (positive north).
     *
     * @return The northern-most latitude between -90 and +90°.
     * @unitof Angle
     */
/// @UML (identifier="northBoundLatitude", obligation=MANDATORY)
    double getNorthBoundLatitude();
}
