/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2012 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import org.opengis.util.InternationalString;
import java.util.Collection;
import java.util.Date;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A temporal coordinate system to simplify  the computation of temporal distances
 * between points and the functional description of temporal operations.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 *
 * @todo Retrofit in {@link org.opengis.referencing.cs.TimeCS}.
 */
@UML(identifier="TM_CoordinateSystem", specification=ISO_19108)
public interface TemporalCoordinateSystem extends TemporalReferenceSystem {
    /**
     * Position of the origin of the scale on which the temporal coordinate system is based
     * expressed as a date in the Gregorian calendar and time of day in UTC.
     */
    @UML(identifier="origin", obligation=MANDATORY, specification=ISO_19108)
    Date getOrigin();

    /**
     * Identifies the base interval for this temporal coordinate system
     * as a unit of measure specified by ISO 31-1,
     * or a multiple of one of those units, as specified by ISO 1000.
     */
    @UML(identifier="interval", obligation=MANDATORY, specification=ISO_19108)
    InternationalString getInterval();

    /**
     * Transforms a value of a {@linkplain TemporalCoordinate coordinate} within this
     * temporal coordinate system and returns the equivalent {@linkplain DateAndTime date
     * and time} in the Gregorian Calendar and UTC
     */
    @UML(identifier="transformCoord", obligation=MANDATORY, specification=ISO_19108)
    Date transformCoord(TemporalCoordinate coordinates);

    /**
     * Transforms a {@linkplain DateAndTime date and time} in the Gregorian Calendar and UTC
     * to an equivalent {@linkplain TemporalCoordinate coordinate} within this temporal
     * coordinate system.
     */
    @UML(identifier="transformDateTime", obligation=MANDATORY, specification=ISO_19108)
    TemporalCoordinate transformDateTime(Date datetime);
}
