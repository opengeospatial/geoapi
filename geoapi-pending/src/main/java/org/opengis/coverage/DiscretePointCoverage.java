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
package org.opengis.coverage;

import java.util.Set;
import java.util.List;
import org.opengis.temporal.Period;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A discrete coverage characterized by a finite domain consisting of points. Generally, the domain
 * is a set of irregularly distributed points. However, the principal use of discrete point coverages
 * is to provide a basis for continuous coverage functions, where the evaluation of the continuous
 * coverage function is accomplished by interpolation between the points of the discrete point coverage.
 * Most interpolation algorithms depend upon a structured pattern of spatial relationships between the
 * points. This requires either that the points in the spatial domain of the discrete point coverage
 * be arranged in a regular way, or that the spatial domain of the continuous coverage be partitioned
 * in a regular way in relation to the points of the discrete point coverage. Grid coverages employ
 * the first method; Thiessen polygon coverages and TIN's employ the second.
 * <p>
 * <strong>EXAMPLE:</strong> A set of hydrographic soundings is a discrete point coverage.
 * <p>
 * {@code DiscretePointCoverage} inherits the {@link #getElements elements} and the operations
 * {@link #locate locate}, {@link #find(DirectPosition,int) find}, and {@link #list list} from
 * {@link DiscreteCoverage}, with the restriction that the associated
 * {@linkplain GeometryValuePair geometry-value pairs} and those returned by the operations
 * shall be limited to {@linkplain PointValuePair point-value pairs}.
 *
 * @version ISO 19123:2004
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_DiscretePointCoverage", specification=ISO_19123)
public interface DiscretePointCoverage extends DiscreteCoverage {
    /**
     * Returns the set of <var>point</var>-<var>value</var> pairs included in this coverage.
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<PointValuePair> getElements();

    /**
     * Returns the set of <var>point</var>-<var>value</var> pairs that include the
     * {@linkplain DomainObject domain objects} containing the specified direct position.
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<PointValuePair> locate(DirectPosition p);

    /**
     * Returns the dictionary of <var>point</var>-<var>value</var> pairs that contain the
     * {@linkplain DomainObject objects} in the domain of the coverage each paired with its
     * record of feature attribute values.
     */
    @UML(identifier="list", obligation=MANDATORY, specification=ISO_19123)
    Set<PointValuePair> list();

    /**
     * Returns the set of <var>point</var>-<var>value</var> pairs that contain
     * {@linkplain DomainObject domain objects} that lie within the specified geometry and period.
     * If {@code s} is null, the operation shall return all <var>point</var>-<var>value</var>
     * pairs that contain {@linkplain DomainObject domain objects} within {@code t}. If the value
     * of {@code t} is null, the operation shall return all <var>point</var>-<var>value</var>
     * pair that contain {@linkplain DomainObject domain objects} within {@code s}.
     */
    @UML(identifier="select", obligation=MANDATORY, specification=ISO_19123)
    Set<PointValuePair> select(Geometry s, Period t);

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
    List<PointValuePair> find(DirectPosition p, int limit);

    /**
     * Returns the nearest <var>point</var>-<var>value</var> pair from the specified direct
     * position. This is a shortcut for <code>{@linkplain #find(DirectPosition,int) find}(p,1)</code>.
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    PointValuePair find(DirectPosition p);
}
