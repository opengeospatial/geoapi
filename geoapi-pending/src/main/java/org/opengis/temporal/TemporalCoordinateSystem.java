/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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

import java.util.Date;
import javax.measure.Unit;
import javax.measure.quantity.Time;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A temporal coordinate system to simplify  the computation of temporal distances
 * between points and the functional description of temporal operations.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_CoordinateSystem", specification=ISO_19108)
public interface TemporalCoordinateSystem extends TemporalReferenceSystem {

    /**
     * Returns position of the origin of the scale on which the temporal coordinate system is based
     * expressed as a date in the Gregorian calendar and time of day in UTC.
     *
     * @return position of the origin of the scale on which the temporal coordinate system is based
     * expressed as a date in the Gregorian calendar and time of day in UTC.
     */
    @UML(identifier="origin", obligation=MANDATORY, specification=ISO_19108)
    Date getOrigin();

    /**
     * Returns standard unit of time used to measure {@link Duration} on the axis of this {@link TemporalCoordinateSystem}.
     * It exprimate as a unit of measure specified by ISO 31-1,
     * or a multiple of one of those units, as specified by ISO 1000.
     *
     * @return standard unit of time used to measure {@link Duration} on the axis of this {@link TemporalCoordinateSystem}.
     */
    @UML(identifier="interval", obligation=MANDATORY, specification=ISO_19108)
    Unit<Time> getInterval();


    /**
     * Returns transformation of a value from a {@linkplain TemporalCoordinate coordinate} within this
     * temporal coordinate system into the equivalent {@linkplain DateAndTime date
     * and time} in the Gregorian Calendar and UTC.
     *
     * @param coordinates current {@linkplain TemporalCoordinate coordinate} coordinates which will be transform into UTC.
     * @return transformation of a value from a {@linkplain TemporalCoordinate coordinate} within this
     * temporal coordinate system into the equivalent {@linkplain DateAndTime date
     * and time} in the Gregorian Calendar and UTC.
     */
    @UML(identifier="transformCoord", obligation=MANDATORY, specification=ISO_19108)
    Date transformCoord(TemporalCoordinate coordinates);

    /**
     * Returns transformation of a {@linkplain DateAndTime date and time} in the Gregorian Calendar and UTC
     * into an equivalent {@linkplain TemporalCoordinate coordinate} within this temporal
     * coordinate system.
     *
     * @param datetime current UTC or {@link Date} coordinates which will be convert into this {@link TemporalCoordinateSystem}.
     * @return a transformation of a {@linkplain DateAndTime date and time} in the Gregorian Calendar and UTC
     * to an equivalent {@linkplain TemporalCoordinate coordinate} within this temporal
     * coordinate system.
     */
    @UML(identifier="transformDateTime", obligation=MANDATORY, specification=ISO_19108)
    TemporalCoordinate transformDateTime(Date datetime);
}
