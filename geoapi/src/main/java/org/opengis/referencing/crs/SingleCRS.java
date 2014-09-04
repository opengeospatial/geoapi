/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract coordinate reference system, consisting of a single
 * {@linkplain CoordinateSystem Coordinate System} and a single
 * {@linkplain Datum Datum} (as opposed to {@linkplain CompoundCRS Compound CRS}).
 * <p>
 * A coordinate reference system consists of an ordered sequence of coordinate system
 * axes that are related to the earth through a datum. A coordinate reference system
 * is defined by one datum and by one coordinate system. Most coordinate reference system
 * do not move relative to the earth, except for engineering coordinate reference systems
 * defined on moving platforms such as cars, ships, aircraft, and spacecraft.
 * <p>
 * Coordinate reference systems are commonly divided into sub-types. The common classification
 * criterion for sub-typing of coordinate reference systems is the way in which they deal with
 * earth curvature. This has a direct effect on the portion of the earth's surface that can be
 * covered by that type of CRS with an acceptable degree of error. The exception to the rule is
 * the subtype "Temporal" which has been added by analogy.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @see org.opengis.referencing.cs.CoordinateSystem
 * @see org.opengis.referencing.datum.Datum
 *
 * @navassoc 1 - - Datum
 */
@UML(identifier="SC_SingleCRS", specification=ISO_19111)
public interface SingleCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system.
     */
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum.
     *
     * @return The datum.
     */
    @UML(identifier="datum", obligation=OPTIONAL, specification=ISO_19111)
    Datum getDatum();
}
