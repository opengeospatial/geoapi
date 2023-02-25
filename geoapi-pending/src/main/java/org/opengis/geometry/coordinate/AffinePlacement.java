/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A placement defined by linear transformation from the parameter space to the target
 * coordinate space. In 2-dimensional Cartesian parameter space, (<var>u</var>, <var>v</var>),
 * transforms into a 3-dimensional coordinate reference system, (<var>x</var>, <var>y</var>, <var>z</var>),
 * by using an affine transformation, (<var>u</var>, <var>v</var>) → (<var>x</var>, <var>y</var>, <var>z</var>),
 * which is defined:
 *
 * <img src="doc-files/AffinePlacement.png" alt="Afine placement">
 *
 * Then, given this equation, the {@link #getLocation()} method returns the direct position
 * (<var>x₀</var>, <var>y₀</var>, <var>z₀</var>), which
 * is the target position of the origin in (<var>u</var>, <var>v</var>). The two
 * {@linkplain #getReferenceDirection(int) reference directions}
 * (<var>u</var><sub>x</sub>, <var>u</var><sub>y</sub>, <var>u</var><sub>z</sub>)
 * and (<var>v</var><sub>x</sub>, <var>v</var><sub>y</sub>, <var>v</var><sub>z</sub>) are the
 * target directions of the unit basis vectors at the origin in (<var>u</var>, <var>v</var>).
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Axel Francois (LSIS/Geomatys)
 * @version 3.1
 * @since   2.0
 *
 * @todo Refactor as an {@code org.opengis.referencing.operation.AffineTransform} interface?
 */
@UML(identifier="GM_AffinePlacement", specification=ISO_19107)
public interface AffinePlacement extends Placement {
    /**
     * Gives the target of the parameter space origin. This is the vector
     * (<var>x₀</var>, <var>y₀</var>, <var>z₀</var>)
     * in the formulae shown in the class description.
     *
     * @return the target of the parameter space origin.
     */
    @UML(identifier="location", obligation=MANDATORY, specification=ISO_19107)
    Position getLocation();

    /**
     * Gives the target directions for the coordinate basis vectors of the parameter space.
     * These are the columns of the matrix in the formulae given in the class description.
     * The number of directions given shall be {@link #getInDimension() inDimension}.
     * The dimension of the directions shall be {@link #getOutDimension() outDimension}.
     *
     * @param  dimension  the in dimension, as an index from 0 inclusive to
     *         {@link #getInDimension() inDimension} exclusive.
     * @return the direction as an array of length {@link #getOutDimension() outDimension}.
     */
    @UML(identifier="refDirection", obligation=MANDATORY, specification=ISO_19107)
    double[] getReferenceDirection(int dimension);
}
