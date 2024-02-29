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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * An operator that evaluates the mathematical comparison between two arguments.
 * If the arguments satisfy the comparison then the filter evaluates to true.
 * Otherwise the filter evaluates to false.
 *
 * <p>The nature of the comparison is dependent on the {@linkplain #getOperatorType() operator type}.
 * A standard set of comparison operators is equal to, less than, greater than,
 * less than or equal to, greater than or equal to and not equal to.</p>
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="BinaryComparisonOperator", specification=ISO_19143)
public interface BinaryComparisonOperator<R> extends ComparisonOperator<R> {
    /**
     * Returns the two expressions to be compared by this operator.
     * The expressions can be of any kind.
     *
     * @return a list of size 2 containing the two expressions to be compared.
     */
    @Override
    @UML(identifier="expression", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<R,?>> getExpressions();

    /**
     * Returns the element on the left side of the comparison expression.
     * This is the element at index 0 in the {@linkplain #getExpressions() list of expressions}.
     *
     * @return the first element in the list of expressions.
     */
    default Expression<R,?> getOperand1() {
        return getExpressions().get(0);
    }

    /**
     * Returns the element on the right side of the comparison expression.
     * This is the element at index 1 in the {@linkplain #getExpressions() list of expressions}.
     *
     * @return the second element in the list of expressions.
     */
    default Expression<R,?> getOperand2() {
        return getExpressions().get(1);
    }

    /**
     * Specifies how a filter expression processor should perform string comparisons.
     * A value of {@code true} means that string comparisons shall match case.
     * The value {@code false} means that string comparisons are performed caselessly.
     * The default value {@code true}.
     *
     * @return {@code true} if comparisons are case sensitive, otherwise {@code false}.
     */
    @UML(identifier="matchCase", obligation=OPTIONAL, specification=ISO_19143)
    default boolean isMatchingCase() {
        return true;
    }

    /**
     * Specifies how the comparison predicate shall be evaluated for a collection of values.
     * Values can be {@link MatchAction#ALL ALL} if all values in the collection shall satisfy the predicate,
     * {@link MatchAction#ANY ANY} if any of the value in the collection can satisfy the predicate, or
     * {@link MatchAction#ONE ONE} if only one of the values in the collection shall satisfy the predicate.
     * The default value is {@code ANY}.
     *
     * @return how the comparison predicate shall be evaluated for a collection of values.
     *         Shall not be null.
     */
    @UML(identifier="matchAction", obligation=OPTIONAL, specification=ISO_19143)
    default MatchAction getMatchAction() {
        return MatchAction.ANY;
    }
}
