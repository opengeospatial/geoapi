/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
 * A compact way of encoding a range check.
 * The lower and upper boundary values are inclusive.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="BetweenComparisonOperator", specification=ISO_19143)
public interface BetweenComparisonOperator<R> extends ComparisonOperator<R> {
    /**
     * Returns the nature of the comparison.
     * The default implementation returns {@code PROPERTY_IS_BETWEEN}.
     *
     * @return the nature of the comparison.
     */
    @Override
    default ComparisonOperatorName getOperatorType() {
        return ComparisonOperatorName.PROPERTY_IS_BETWEEN;
    }

    /**
     * Returns the expression to be compared by this operator, together with boundaries.
     * The expressions can be of any kind.
     *
     * @return a list of size 3 containing the expression to be compared,
     *         the lower boundary and the upper boundary in that order.
     */
    @Override
    List<Expression<? super R, ?>> getExpressions();

    /**
     * Returns the expression to be compared by this operator.
     * This is the element at index 0 in the {@linkplain #getExpressions() expressions} list.
     *
     * @return the expression to be compared.
     */
    @UML(identifier="expression", obligation=MANDATORY, specification=ISO_19143)
    default Expression<? super R, ?> getExpression() {
        return getExpressions().get(0);
    }

    /**
     * Returns the lower bound (inclusive) an an expression.
     * This is the element at index 1 in the {@linkplain #getExpressions() expressions} list.
     *
     * @return the lower bound, inclusive.
     */
    @UML(identifier="lowerBoundary", obligation=MANDATORY, specification=ISO_19143)
    default Expression<? super R, ?> getLowerBoundary() {
        return getExpressions().get(1);
    }

    /**
     * Returns the upper bound (inclusive) as an expression.
     * This is the element at index 2 in the {@linkplain #getExpressions() expressions} list.
     *
     * @return the upper bound, inclusive.
     */
    @UML(identifier="upperBoundary", obligation=MANDATORY, specification=ISO_19143)
    default Expression<? super R, ?> getUpperBoundary() {
        return getExpressions().get(2);
    }
}
