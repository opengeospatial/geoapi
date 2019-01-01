/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2019 Open Geospatial Consortium, Inc.
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
import org.opengis.util.Record;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Ties feature attributes values to the a grid geometry. It holds a sequence of records associated
 * with a sequencing rule that specifies an algorithm for assigning records of feature attribute
 * values to grid points.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_GridValuesMatrix", specification=ISO_19123)
public interface GridValuesMatrix extends Grid {
    /**
     * Returns a sequence of <var>N</var> feature attribute value records where <var>N</var> is the
     * number of {@linkplain GridPoint grid points} within the section of the grid specified by
     * the {@linkplain #getExtent extent}.
     *
     * @return the feature attribute values records.
     *
     * @todo Comment (Wim): Both extracting an image for display and operation chaining require
     *       memory efficient and fast access to the data. How should the data be structured per
     *       record? Some use cases:
     *       <table border="1" summary="Use case examples">
     *         <tr><td>DTM<td>double<td>List&lt;double&gt;
     *         <tr><td>TM image<td>7 bytes<td>
     *         <tr><td>Landuse map<td>string<td>{@code List<double>} with look up table for the strings
     *       </table>
     */
    @UML(identifier="values", obligation=MANDATORY, specification=ISO_19123)
    List<Record> getValues();

    /**
     * Describes how the grid points are ordered for association to the elements of the sequence
     * values.
     *
     * @return the sequencing rule.
     */
    @UML(identifier="sequencingRule", obligation=MANDATORY, specification=ISO_19123)
    SequenceRule getSequencingRule();

    /**
     * Identifies the grid point to be associated with the first record
     * in the values sequence.
     *
     * @return the grid point associated with the first record.
     */
    @UML(identifier="startSequence", obligation=MANDATORY, specification=ISO_19123)
    GridCoordinates getStartSequence();

    /**
     * Returns the limits of a section of the grid. This envelope can not be empty.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19123)
    GridEnvelope getExtent();
}
