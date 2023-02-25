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
import java.util.Collection;
import org.opengis.util.Record;
import org.opengis.coverage.DomainObject;
import org.opengis.coverage.ContinuousCoverage;
import org.opengis.coverage.InterpolationMethod;
import org.opengis.coverage.DiscreteSurfaceCoverage;
import org.opengis.coverage.CannotEvaluateException;
import org.opengis.coverage.PointOutsideCoverageException;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Evaluates a coverage at direct positions within a network of hexagons centered on a set of grid
 * points. Evaluation is based on interpolation between the centres of the {@link ValueHexagon}s
 * surrounding the input position.
 *
 * <p>Coverages are sometimes based on tessellations composed of regular hexagons. Such tessellations
 * are usually called hexagonal grids. In fact, the centers of a set of regular hexagons that form
 * such a tessellation correspond to the grid points of a quadrilateral grid. That grid can be
 * described as a rectified grid in which the two offset vectors are of equal length but differ in
 * direction by 60°. The length of a side of the hexagon is <var>L</var> = <var>S</var>×tan&nbsp;30°,
 * where <var>S</var> is the length of the offset vector. This means that the values in the coverage
 * range can be stored as a grid values matrix and accessed through a sequence rule. The hexagons
 * are the Thiessen polygons that are generated around the grid points.</p>
 *
 * <div class="note"><b>Note:</b>
 * a set of Thiessen polygons generated from the grid points of any 2-dimensional
 * rectified grid described by two offset vectors that are equal in length but not orthogonal
 * will be a set of congruent hexagons. The hexagons will be irregular unless the offset vectors
 * differ in direction by exactly 60°.
 * </div>
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 *
 * @todo evaluateInverse
 */
@UML(identifier="CV_HexagonalGridCoverage", specification=ISO_19123)
public interface HexagonalGridCoverage extends ContinuousCoverage {
    /**
     * Returns the set of value objects used to evaluate the coverage. This
     * association is optional - an analytical coverage needs no value objects.
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<ValueHexagon> getElements();

    /**
     * Returns the interpolation method to be used in evaluating the coverage. The most common
     * interpolation methods are "{@linkplain InterpolationMethod#LOST_AREA lost area}" and
     * "{@linkplain InterpolationMethod#NEAREST_NEIGHBOUR nearest neighbour}". Lost area
     * interpolation can return a different record of feature attribute values for each
     * direct position within a {@linkplain ValueHexagon value hexagon}. On the other hand,
     * nearest neighbour interpolation will return for any direct position within a value hexagon
     * the record associated with the {@linkplain GridPointValuePair grid point-value pair} at the
     * centre of the value hexagon. In other words, a hexagonal grid coverage that uses nearest
     * neighbour interpolation acts like a {@linkplain DiscreteSurfaceCoverage discrete surface
     * coverage}.
     */
    @UML(identifier="interpolationType", obligation=OPTIONAL, specification=ISO_19123)
    InterpolationMethod getInterpolationMethod();

    /**
     * Returns the set of values hexagon that include the {@linkplain DomainObject domain objects}
     * containing the specified direct position.
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<ValueHexagon> locate(DirectPosition p);

    /**
     * Returns a set of records of feature attribute values for the specified direct position.
     * Evaluation of a hexagonal grid coverage involves two steps. The first is to locate the
     * {@linkplain ValueHexagon value hexagon} that contains the input direct position; the
     * second is to interpolate the feature attribute values at the direct position from the
     * {@linkplain GridPointValuePair grid point-value pairs} at the centres of the surrounding
     * value hexagons.
     *
     * @throws PointOutsideCoverageException if the point is outside the coverage domain.
     * @throws CannotEvaluateException if the point cannot be evaluated for some other reason.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set<Record> evaluate(DirectPosition p, Collection<String> list)
            throws PointOutsideCoverageException, CannotEvaluateException;

    /**
     * Links this hexagonal coverage to the grid values matrix for which it is an evaluator.
     * The returned matrix is specialized by four constraints:
     *
     * <ul>
     *   <li>It is a {@linkplain RectifiedGrid rectified grid}.</li>
     *   <li>The inherited attribute {@linkplain Grid#getDimension dimension} has a value of 2.</li>
     *   <li>The {@linkplain RectifiedGrid#getOffsetVectors offset vectors} differ in direction by
     *       60 degrees.</li>
     *   <li>The lengths of the {@linkplain RectifiedGrid#getOffsetVectors offset vectors} are equal.</li>
     * </ul>
     *
     * @return the underlying data.
     */
    @UML(identifier="source", obligation=MANDATORY, specification=ISO_19123)
    GridValuesMatrix getSource();
}
