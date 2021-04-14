/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.extent;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Geographic position of the resource. This is only an approximate reference
 * so specifying the coordinate reference system is unnecessary.
 * A precision up to 2 decimal places is usually sufficient.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="EX_GeographicBoundingBox", specification=ISO_19115)
public interface GeographicBoundingBox extends GeographicExtent {
    /**
     * The western-most coordinate of the limit of the resource extent.
     * The value is expressed in longitude in decimal degrees (positive east).
     *
     * @return the western-most longitude between -180 and +180°.
     * @unitof Angle
     */
    @UML(identifier="westBoundLongitude", obligation=MANDATORY, specification=ISO_19115)
    double getWestBoundLongitude();

    /**
     * The eastern-most coordinate of the limit of the resource extent.
     * The value is expressed in longitude in decimal degrees (positive east).
     *
     * @return the eastern-most longitude between -180 and +180°.
     * @unitof Angle
     */
    @UML(identifier="eastBoundLongitude", obligation=MANDATORY, specification=ISO_19115)
    double getEastBoundLongitude();

    /**
     * The southern-most coordinate of the limit of the resource extent.
     * The value is expressed in latitude in decimal degrees (positive north).
     *
     * @return the southern-most latitude between -90 and +90°.
     * @unitof Angle
     */
    @UML(identifier="southBoundLatitude", obligation=MANDATORY, specification=ISO_19115)
    double getSouthBoundLatitude();

    /**
     * The northern-most coordinate of the limit of the resource extent.
     * The value is expressed in latitude in decimal degrees (positive north).
     *
     * @return the northern-most latitude between -90 and +90°.
     * @unitof Angle
     */
    @UML(identifier="northBoundLatitude", obligation=MANDATORY, specification=ISO_19115)
    double getNorthBoundLatitude();
}
