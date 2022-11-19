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
package org.opengis.coverage.grid.quadrilateral;

import java.util.List;
import org.opengis.util.Record;
import org.opengis.annotation.UML;
import org.opengis.coverage.grid.GridEnvelope;
import org.opengis.coverage.grid.GridPoint;
import org.opengis.coverage.grid.SequenceRule;
import org.opengis.coverage.grid.GridCoordinates;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A modified copy of {@link org.opengis.coverage.grid.GridValuesMatrix} which does not inherit
 * {@link org.opengis.coverage.grid.Grid}. The later is replaced by a composition to be
 * queried by {@link #getGrid}.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @author  Alexander Petkov
 */
@UML(identifier="CV_GridValuesMatrix", specification=ISO_19123)
public interface GridValuesMatrix {
    /**
     * Returns a sequence of <var>N</var> feature attribute value records where <var>N</var> is the
     * number of {@linkplain GridPoint grid points} within the section of the grid specified by
     * the {@linkplain #getExtent extent}.
     *
     * @todo Comment (Wim): Both extracting an image for display and operation chaining require
     *       memory efficient and fast access to the data. How should the data be structured per
     *       record? Some use cases:
     *       <table class="ogc">
     *         <caption>Use cases</caption>
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
     */
    @UML(identifier="sequencingRule", obligation=MANDATORY, specification=ISO_19123)
    SequenceRule getSequencingRule();

    /**
     * Identifies the grid point to be associated with the first record
     * in the values sequence.
     */
    @UML(identifier="startSequence", obligation=MANDATORY, specification=ISO_19123)
    GridCoordinates getStartSequence();

    /**
     * Returns the limits of a section of the grid. This envelope cannot be empty.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19123)
    GridEnvelope getExtent();

    /**
     * Associates this GridValuesMatrix with a geometric description provided by the Grid object.
     * The extent attribute of the associated Grid object must be synchronized with the extent
     * attribute of this object.
     */
    Grid getGrid();
}
