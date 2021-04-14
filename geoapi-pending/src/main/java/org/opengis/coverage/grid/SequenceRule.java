/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage.grid;

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Contains information for mapping {@linkplain GridCoordinates grid coordinates} to a position
 * within the sequence of records of feature attribute values.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_SequenceRule", specification=ISO_19123)
public interface SequenceRule {
    /**
     * Identifies the type of sequencing method that shall be used.
     * The default value shall be "{@linkplain SequenceType#LINEAR linear}".
     *
     * @return the type of sequencing method.
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19123)
    SequenceType getType();

    /**
     * Returns a list of signed {@linkplain Grid#getAxisNames axis names} that indicates the order
     * in which {@linkplain GridPoint grid points} shall be mapped to position within the sequence
     * of records of feature attribute values. An additional element may be included in the list to
     * allow for interleaving of feature attribute values.
     * Example: <code>{"x", "-y"}</code>
     *
     * @return an ordered list of axis names that indicates the scanning direction.
     */
    @UML(identifier="scanDirection", obligation=MANDATORY, specification=ISO_19123)
    List<String> getScanDirection();
}
