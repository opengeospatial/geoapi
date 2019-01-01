/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.datum;

import java.util.Map;
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A prime meridian defines the origin from which longitude values are determined.
 * Most geodetic datums use Greenwich as their prime meridian.
 *
 * <p>Constraints:</p>
 * <ul>
 *   <li>If the prime meridian {@linkplain #getName() name} is “Greenwich” then the value of
 *       {@linkplain #getGreenwichLongitude() Greenwich longitude} shall be 0 degrees.</li>
 *   <li>Conversely if the Greenwich longitude value is zero, then the prime meridian name
 *       shall be “Greenwich”.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0.1
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createPrimeMeridian(String)
 * @see DatumFactory#createPrimeMeridian(Map, double, Unit)
 */
@UML(identifier="CD_PrimeMeridian", specification=ISO_19111)
public interface PrimeMeridian extends IdentifiedObject {
    /**
     * Longitude of the prime meridian measured from the Greenwich meridian, positive eastward.
     * The {@code greenwichLongitude} default value is zero, and that value shall be used
     * when the {@linkplain #getName() meridian name} value is "Greenwich".
     *
     * @return the prime meridian Greenwich longitude, in {@linkplain #getAngularUnit angular unit}.
     * @unitof Angle
     */
    @UML(identifier="greenwichLongitude", obligation=CONDITIONAL, specification=ISO_19111)
    double getGreenwichLongitude();

    /**
     * Returns the angular unit of the {@linkplain #getGreenwichLongitude() Greenwich longitude}.
     *
     * @departure historic
     *   This attribute is inherited from an older OGC specification.
     *   In ISO 19111, {@code greenwichLongitude} is a property of type {@code Angle}
     *   rather than {@code double}, and the unit of measure is part of the {@code Angle} value.
     *
     * @return the angular unit of Greenwich longitude.
     */
    @UML(identifier="getAngularUnit", specification=OGC_01009)
    Unit<Angle> getAngularUnit();
}
