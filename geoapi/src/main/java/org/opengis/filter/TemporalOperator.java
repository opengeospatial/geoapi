/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Operator that determines whether its time arguments satisfy the stated spatial relationship.
 * The operator {@linkplain #test evaluates} to {@code true} if the spatial relationship is satisfied.
 * Otherwise, the operator evaluates to {@code false}.
 * The nature of the comparison is dependent on the {@linkplain #getOperatorType() operator type}.
 *
 * <h2>Exceptions</h2>
 * If any input value of {@code TemporalPosition} is indeterminate, an exception shall be raised.
 *
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="TemporalOperator", specification=ISO_19143)
public interface TemporalOperator<R> extends Filter<R> {
    /**
     * Returns the nature of the operator.
     *
     * @return the nature of the operator.
     */
    @Override
    TemporalOperatorName getOperatorType();

    /**
     * Returns the two expressions to be evaluated by this operator.
     * The list content is as below:
     *
     * <ol>
     *   <li>
     *     The first expression should be a {@link ValueReference} evaluating to a temporal object.
     *   </li><li>
     *     The second expression should be a {@link ValueReference} or a {@linkplain Literal literal}
     *     evaluating to a temporal object.
     *   </li>
     * </ol>
     *
     * @return a list of size 2 containing the two expressions to be evaluated.
     *
     * @departure constraint
     *   ISO 19143 uses two properties, named {@code operand1} and {@code operand2},
     *   of type {@code ValueReference} and {@code TemporalOperand} respectively.
     *   The later is a union of {@code TM_Object} and {@code ValueReference},
     *   which has no direct equivalence in Java.
     *   The union purpose is replaced by documentation in this method.
     *   The two values are put in a list for retrofitting in {@link Filter#getExpressions()}.
     */
    @Override
    @UML(identifier="operand1, operand2", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<R,?>> getExpressions();
}
