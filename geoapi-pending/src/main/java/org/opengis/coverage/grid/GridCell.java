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

import java.util.Set;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A grid cell delineated by the grid lines of a {@linkplain Grid grid}. Its corners
 * are associated with the {@linkplain GridPoint grid points} at the intersections of
 * the grid lines that bound it
 *
 * @version ISO 19123:2004
 * @author  Martin Schouwenburg
 * @author  Wim Koolhoven
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_GridCell", specification=ISO_19123)
public interface GridCell {
    /**
     * Returns the collection of {@linkplain GridPoint grid points} at the corners of the grid cell.
     * The size of this collection has no upper bound, to allow for grids of any dimension.
     * In a quadrilateral grid, the multiplicity of corner equals 2×<var>d</var>, where
     * <var>d</var> is the value of {@link Grid#getDimension}.
     *
     * @return the corners of the grid cell.
     *
     * @see GridPoint#getCells
     */
    @UML(identifier="corner", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPoint> getCorners();

    /**
     * Returns the {@linkplain Grid grid} of which this cell is a component.
     *
     * @return the grid of which this cell is a component.
     *
     * @see Grid#getCells
     */
    @UML(identifier="framework", obligation=MANDATORY, specification=ISO_19123)
    Grid getFramework();
}
