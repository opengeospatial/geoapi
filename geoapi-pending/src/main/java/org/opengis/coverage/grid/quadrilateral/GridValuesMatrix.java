/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
