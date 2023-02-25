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
import org.opengis.coverage.ValueObject;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basis for interpolating within a {@linkplain ContinuousQuadrilateralGridCoverage
 * continuous quadrilateral grid coverage}. A {@code GridValueCell} is a collection
 * of {@linkplain GridPointValuePair grid point value pairs} with a geometric structure
 * defined by a {@linkplain GridCell grid cell}.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_GridValueCell", specification=ISO_19123)
public interface GridValueCell extends ValueObject {
    /**
     * Returns the {@linkplain GridCell grid cell} that defines the structure of the
     * {@linkplain GridPointValuePair grid point value pairs} that support the interpolation
     * of a feature attribute value at a {@linkplain org.opengis.geometry.DirectPosition direct position} within the
     * {@linkplain GridCell grid cell}.
     *
     * @departure constraint
     *   ISO defines this method as a specialization of <code>getGeometry()</code>. We
     *   cannot reflect this association in Java because of incompatible return type.
     *
     * @return the structure of grid points.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    GridCell getGridCell();

    /**
     * Returns the set of <var>grid point</var>-<var>value</var> pairs at the corners of this
     * {@code GridValueCell}.
     */
    @UML(identifier="controlValue", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPointValuePair> getControlValues();
}
