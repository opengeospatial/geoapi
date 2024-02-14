/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
package org.opengis.coverage.grid;

import java.util.List;
import java.util.Set;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Contains the geometric characteristics of a qualdrilateral grid. A grid is a network composed
 * of two or more sets of curves in which members of each set intersect the members of other sets
 * in a systematic way. The curves are called <dfn>grid lines</dfn>; the points at which they
 * intersect are <dfn>grid points</dfn>; the interstices between the grid lines are called
 * <dfn>grid cells</dfn>.
 * <p>
 * {@code Grid} has three subclasses, which lie in two partitions. The Positioning partition includes
 * {@link RectifiedGrid} and {@link ReferenceableGrid}, which contain information that relates the grid
 * coordinates to an external {@linkplain CoordinateReferenceSystem coordinate reference system}. The
 * Valuation partition includes {@link GridValuesMatrix}, which contains information for assigning
 * values from the range to each of the grid points.
 * <p>
 * {@code Grid} is not an abstract class: an instance of {@code Grid} need not be an instance of any
 * of its subclasses. The partitions indicate that an instance of the subclass {@link GridValuesMatrix}
 * may be, at the same time, an instance of either the subclass {@link RectifiedGrid} or of the subclass
 * {@link ReferenceableGrid}.
 *
 * @version ISO 19123:2004
 * @author  Martin Schouwenburg
 * @author  Wim Koolhoven
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_Grid", specification=ISO_19123)
public interface Grid {
    /**
     * Returns the dimensionality of the grid. The dimensionality is the number
     * of definining curve sets that constitute the grid.
     *
     * @return the dimensionality of the grid.
     */
    @UML(identifier="dimension", obligation=MANDATORY, specification=ISO_19123)
    int getDimension();

    /**
     * Returns a list containing the names of the grid axes. Each name is
     * linked to one of the defining curve sets that constitute the grid.
     *
     * @return the names of the grid axes.
     */
    @UML(identifier="axisNames", obligation=MANDATORY, specification=ISO_19123)
    List<String> getAxisNames();

    /**
     * Returns the limits of a section of the grid. The envelope contains the low
     * and high coordinates of the minimal envelope that can contain the grid.
     *
     * @return the limits of a section of the grid.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19123)
    GridEnvelope getExtent();

    /**
     * Returns the set of {@linkplain GridPoint grid points} that are located at the
     * intersections of the grid lines. The collection contains one or more grid points.
     *
     * @return the intersections of the grid lines.
     *
     * @see GridPoint#getFramework
     */
    @UML(identifier="intersection", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPoint> getIntersections();

    /**
     * Returns the set of {@linkplain GridCell grid cells} delineated by the grid lines.
     * The collection contains one or more grid cells.
     *
     * @return the grid cells delineated by the grid lines.
     *
     * @see GridCell#getFramework
     */
    @UML(identifier="cell", obligation=MANDATORY, specification=ISO_19123)
    Set<GridCell> getCells();
}
