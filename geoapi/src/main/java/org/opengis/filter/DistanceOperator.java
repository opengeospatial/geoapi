/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.util.List;
import javax.measure.Quantity;
import javax.measure.quantity.Length;
import org.opengis.geometry.Geometry;
import org.opengis.feature.Feature;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Spatial operator that checks that one geometry satisfies some relation to a buffer around another geometry.
 * This could be used to find, for example, all the geometries that come within 10 meters of a river.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="DistanceOperator", specification=ISO_19143)
public interface DistanceOperator<R> extends SpatialOperator<R> {
    /**
     * Returns the nature of the operator. A standard set of spatial operators is within and beyond.
     *
     * @return the nature of the operator.
     */
    @Override
    @UML(identifier="operatorType", obligation=MANDATORY, specification=ISO_19143)
    DistanceOperatorName getOperatorType();

    /**
     * Returns the two expressions to be evaluated by this operator.
     * The result of evaluating the two first expressions should be {@link Geometry} instances.
     * The list content is as below:
     *
     * <ol>
     *   <li>The first expression should be a {@link ValueReference} evaluating to a {@link Geometry}.
     *     But it may also evaluate to a {@link Feature} if the operator shall be applied
     *     to the geometric values of all the spatial properties of the resource.
     *     In this case, the operator evaluates to {@code true} if all tested
     *     spatial property values fulfill the spatial relationship implied by the operator.
     *   </li><li>
     *     The second expression should be a {@linkplain Literal literal} evaluating
     *     to the same geometry than the one returned by {@link #getGeometry()}.
     *   </li><li>
     *     The third expression should be a {@linkplain Literal literal} evaluating
     *     to the same measure than the one returned by {@link #getDistance()}.
     *   </li>
     * </ol>
     *
     * @return a list of size 3 containing the expression to be evaluated,
     *         the geometry and the distance in that order.
     *
     * @departure harmonization
     *   ISO 19143 defines a single expression ({@code valueReference})
     *   completed by 2 parameters ({@code geometry} and {@code distance}).
     *   GeoAPI puts the expression and the 2 parameters in a single list for enabling access
     *   to all parameters in a {@linkplain Filter#getExpressions() more generic way}.
     */
    @Override
    @UML(identifier="valueReference", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<? super R, ?>> getExpressions();

    /**
     * The literal geometry from which distances are measured.
     *
     * @return the geometry from which distances are measured.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19143)
    Geometry getGeometry();

    /**
     * Returns the buffer distance around the geometry that will be used when comparing features' geometries.
     * An implementation may throw an exception if the units of measurement differ from
     * the units of the coordinate system of its geometry or the feature's geometry.
     *
     * @return the buffer distance around the geometry of the second expression.
     */
    @UML(identifier="distance", obligation=MANDATORY, specification=ISO_19143)
    Quantity<Length> getDistance();
}
