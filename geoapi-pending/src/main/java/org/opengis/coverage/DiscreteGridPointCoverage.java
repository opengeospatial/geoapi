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
package org.opengis.coverage;

import java.util.List;
import java.util.Set;
import org.opengis.util.Record;
import org.opengis.temporal.Period;
import org.opengis.coverage.grid.GridPoint;
import org.opengis.coverage.grid.GridPointValuePair;
import org.opengis.coverage.grid.GridCoordinates;
import org.opengis.coverage.grid.GridValuesMatrix;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain DiscreteCoverage discrete coverage} with a domain defined as a set of
 * {@linkplain GridPoint grid points} that are associated with records of feature attribute
 * values through a {@linkplain GridValuesMatrix grid values matrix}.
 * <p>
 * {@code DiscreteGridPointCoverage} inherits the {@linkplain #getElements elements} and the
 * operations {@link #locate locate}, {@link #find(DirectPosition,int) find}, and
 * {@link #list list}, from {@link DiscreteCoverage}, with the restriction that the
 * associated {@linkplain GeometryValuePair geometry-value pairs} and those returned by the
 * operations shall be limited to
 * {@linkplain GridPointValuePair (grid point)-(value) pairs}. The
 * {@linkplain #getElements elements} may be generated from the
 * {@linkplain GridValuesMatrix grid values matrix} through the
 * {@linkplain #getValueAssignment value assignment}. The inherited operations
 * {@link #evaluate evaluate} and {@link #evaluateInverse evaluateInverse} use
 * {@linkplain GridValuesMatrix grid values matrix} to assign values to the
 * {@linkplain GeometryValuePair geometry-value pairs}.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 *
 * @todo Should we restrict {@link DomainObject} to {@link GridPoint} as well? It sound like
 *       a logical consequence of {@link GridPointValuePair} restriction.
 * @todo Should we move this interface to org.opengis.coverage.grid as all the other grid interfaces,
 *       or should it remain in org.opengis.coverage to follow the ordering in ISO19123?
 * @todo What should be the behavior of {@link #evaluate(DirectPosition)} when the position
 *       is not exactly on a grid point? Current proposal is to return an empty set.
 */
@UML(identifier="CV_DiscreteGridPointCoverage", specification=ISO_19123)
public interface DiscreteGridPointCoverage extends DiscreteCoverage {
    /**
     * Returns the set of <var>point</var>-<var>value</var> pairs included in this coverage.
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<GridPointValuePair> getElements();

    /**
     * Links this discrete grid point coverage to the grid values matrix
     * for which it is an evaluator.
     *
     * @return the underlying grid values matrix.
     */
    @UML(identifier="valueAssignment", obligation=MANDATORY, specification=ISO_19123)
    GridValuesMatrix getValueAssignment();

    /**
     * Returns the set of <var>point</var>-<var>value</var> pairs that include the
     * {@linkplain DomainObject domain objects} containing the specified direct position.
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<GridPointValuePair> locate(DirectPosition p);

    /**
     * Returns the dictionary of <var>point</var>-<var>value</var> pairs that contain the
     * {@linkplain DomainObject objects} in the domain of the coverage each paired with its
     * record of feature attribute values.
     */
    @UML(identifier="list", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPointValuePair> list();

    /**
     * Returns the set of <var>point</var>-<var>value</var> pairs that contain
     * {@linkplain DomainObject domain objects} that lie within the specified geometry and period.
     * If {@code s} is null, the operation shall return all <var>point</var>-<var>value</var>
     * pairs that contain {@linkplain DomainObject domain objects} within {@code t}. If the value
     * of {@code t} is null, the operation shall return all <var>point</var>-<var>value</var>
     * pair that contain {@linkplain DomainObject domain objects} within {@code s}.
     */
    @UML(identifier="select", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPointValuePair> select(Geometry s, Period t);

    /**
     * Returns the sequence of <var>point</var>-<var>value</var> pairs that include the
     * {@linkplain DomainObject domain objects} nearest to the direct position and their distances
     * from the direction position. The sequence shall be ordered by distance from the direct position,
     * beginning with the record containing the {@linkplain DomainObject domain object} nearest to the
     * direct position. The length of the sequence (the number of <var>point</var>-<var>value</var>
     * pairs returned) shall be no greater than the number specified by the parameter {@code limit}.
     * The default shall be to return a single <var>point</var>-<var>value</var> pair. The operation
     * shall return a warning if the last {@linkplain DomainObject domain object} in the sequence is at
     * a distance from the direct position equal to the distance of other
     * {@linkplain DomainObject domain objects} that are not included in the sequence.
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    List<GridPointValuePair> find(DirectPosition p, int limit);

    /**
     * Returns the nearest <var>point</var>-<var>value</var> pair from the specified direct
     * position. This is a shortcut for <code>{@linkplain #find(DirectPosition,int) find}(p,1)</code>.
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    GridPointValuePair find(DirectPosition p);

    /**
     * Uses data from the associated {@linkplain GridValuesMatrix grid values matrix} to construct
     * and return the grid point value pair associated with the specified grid position.
     *
     * @param g The position where to search for a value.
     * @return the point and value at the specified position.
     */
    @UML(identifier="point", obligation=MANDATORY, specification=ISO_19123)
    GridPointValuePair point(GridCoordinates g);

    /**
     * Returns a set of {@linkplain GridPoint grid points} for the specified record of feature
     * attribute values. Normally, this method returns the set of {@linkplain GridPoint points}
     * in the domain that are associated with values equal to those in the input record. However,
     * the operation may return other {@linkplain GridPoint points} derived from those in the
     * domain, as specified by the application schema.
     */
    @UML(identifier="evaluateInverse", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPoint> evaluateInverse(Record v);
}
