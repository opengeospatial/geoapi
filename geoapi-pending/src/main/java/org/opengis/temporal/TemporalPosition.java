/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2014 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Used for describing {@link TemporalPosition temporal positions} referenced 
 * to other {@linkplain TemporalReferenceSystem temporal reference systems}.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_TemporalPosition", specification=ISO_19108)
public interface TemporalPosition {
    /**
     * Returns the only value for temporal position unless a subtype of
     * {@link TemporalPosition} is used as the data type, or {@code null} if none.
     * When this attribute is used with a subtype of {@link TemporalPosition},
     * it provides a qualifier to the specific value for temporal position provided by the subtype.
     *
     * @return the only value for temporal position unless a subtype of
     * {@link TemporalPosition} is used as the data type, or {@code null} if none.
     */
    @UML(identifier="indeterminatePosition", obligation=OPTIONAL, specification=ISO_19108)
    IndeterminateValue getIndeterminatePosition();

    /**
     * Returns the association which connect the {@link TemporalPosition} to a {@link TemporalReferenceSystem}.
     * Every {@link TemporalPosition} shall be associated with a {@link TemporalReferenceSystem}.
     * This association need not be explicite at the instance level.
     * If not specified, it is assumed to be an association to Gregorian Calendar and UTC.
     *
     * @return the association which connect the {@link TemporalPosition} to a {@link TemporalReferenceSystem}.
     * @since 4.0
     */
    @UML(identifier="frame", obligation=MANDATORY, specification=ISO_19108)
    TemporalReferenceSystem getFrame();
}
