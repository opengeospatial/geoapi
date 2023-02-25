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

import java.util.Collection;
import java.util.Set;
import org.opengis.util.Record;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Surface;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Evaluates a coverage at direct positions within a Thiessen polygon network constructed from a set
 * of discrete {@linkplain PointValuePair point-value pairs}. Evaluation is based on interpolation
 * between the centres of the {@linkplain ThiessenValuePolygon Thiessen value polygons} surrounding
 * the input position.
 *
 * <h2>Thiessen polygon networks</h2>
 * A finite collection of points on a plane determines a partition of the plane into a collection of
 * polygons equal in number to the collection of points. A Thiessen polygon is generated from one of
 * a defining set of points by forming the set of direct positions that are closer to that point than
 * to any other point in the defining set. The specific point is called the centre of the resulting
 * polygon. The boundaries between neighbouring polygons are the perpendicular bisectors of the lines
 * between their respective centres. Each polygon shares each of its edges with exactly one other
 * polygon. Each polygon contains exactly one point from the defining set. Thiessen polygons are
 * also known as Voronoi Diagrams or Proximal Sets.
 * <p>
 * A Thiessen polygon network is a tessellation of a 2D space using Thiessen polygons. A Thiessen
 * polygon network provides a structure that supports interpolation of feature attribute values from
 * the polygon centres to direct positions within the polygons.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 *
 * @todo Provide a figure derived from figure 11 in ISO 19123.
 */
@UML(identifier="CV_ThiessenPolygonCoverage", specification=ISO_19123)
public interface ThiessenPolygonCoverage extends ContinuousCoverage {
    /**
     * Returns the set of value objects used to evaluate the coverage. This
     * association is optional - an analytical coverage needs no value objects.
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<ThiessenValuePolygon> getElements();

    /**
     * Returns the extent of the Thiessen polygon network. Its boundary determines the boundaries
     * of the outermost polygons in the network, which would otherwise be unbounded.
     *
     * @return the extent of th polygon network.
     */
    @UML(identifier="clipArea", obligation=MANDATORY, specification=ISO_19123)
    Surface getClipArea();

    /**
     * Returns the interpolation method to be used in evaluating the coverage. The most common
     * interpolation methods are "{@linkplain InterpolationMethod#LOST_AREA lost area}" and
     * "{@linkplain InterpolationMethod#NEAREST_NEIGHBOUR nearest neighbour}". Lost area
     * interpolation can return a different record of feature attribute values for each
     * direct position within a {@linkplain ThiessenValuePolygon Thiessen value polygon}. On
     * the other hand, nearest neighbour interpolation will return for any direct position
     * within a Thiessen polygon the record associated with the {@linkplain PointValuePair
     * point-value pair} at the centre of the Thiessen polygon. In other words, a
     * Thiessen polygon coverage that uses nearest neighbour interpolation acts like
     * a {@linkplain DiscreteSurfaceCoverage discrete surface coverage}.
     */
    @UML(identifier="interpolationType", obligation=OPTIONAL, specification=ISO_19123)
    InterpolationMethod getInterpolationMethod();

    /**
     * Returns the set of Thiessen values polygon that include the
     * {@linkplain DomainObject domain objects} containing the specified direct position.
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<ThiessenValuePolygon> locate(DirectPosition p);

    /**
     * Returns a set of records of feature attribute values for the specified direct position.
     * Evaluation of a Thiessen polygon coverage involves two steps. The first is to locate the
     * {@linkplain ThiessenValuePolygon Thiessen value polygon} that contains the input direct
     * position; the second is to interpolate the feature attribute values at the direct position
     * from the {@linkplain PointValuePair point-value pairs} at the centres of the surrounding
     * Thiessen value polygons.
     *
     * @throws PointOutsideCoverageException if the point is outside the coverage domain.
     * @throws CannotEvaluateException if the point cannot be evaluated for some other reason.
     */
    @UML(identifier="evaluate", obligation=MANDATORY, specification=ISO_19123)
    Set<Record> evaluate(DirectPosition p, Collection<String> list) throws CannotEvaluateException;
}
