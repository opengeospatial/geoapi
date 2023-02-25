/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Combination of one or more conditional expressions.
 * The logical operator AND evaluates to {@code true} if all the combined expressions evaluate to true.
 * The operator OR evaluates to {@code true} is any of the combined expressions evaluate to true.
 * The NOT operator reverses the logical value of an expression.
 *
 * <p>The arity is determined the length of the {@linkplain #getOperands() operands} list.
 * If 1, this operator is an <cite>unary logic operator</cite>.
 * If 2 or more, this operator is a <cite>binary logic operator</cite>.
 * The length may be more than 2 if the AND or OR operation is repeated for all operands.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="LogicalOperator", specification=ISO_19143)
public interface LogicalOperator<R> extends Filter<R> {
    /**
     * Returns the nature of the operator.
     *
     * @return the nature of the operator.
     */
    @Override
    @UML(identifier="UnaryLogicOperator.operatorType, BinaryLogicOperator.operatorType", obligation=MANDATORY, specification=ISO_19143)
    LogicalOperatorName getOperatorType();

    /**
     * Returns a view of filter operands as expressions having {@code Boolean} return values.
     * The expression list shall have the same size than the {@linkplain #getOperands() operands list}.
     * For each index <var>i</var> valid for the lists, the following relation shall hold:
     *
     * {@snippet lang="java" :
     * R any = ...;
     * Object  e = expressions.get(i).apply(any);
     * Boolean f = filters.get(i).test(any);
     * assert e.equals(f);
     * }
     *
     * @return a view of {@linkplain #getOperands() operands list} as expressions.
     */
    @Override
    default List<Expression<? super R, ?>> getExpressions() {
        return new FilterExpressions<>(getOperands());
    }

    /**
     * Returns a list containing all of the child filters of this object.
     * This list will contain at least one element, and each element will
     * be an instance of {@code Filter}.
     *
     * @return all child filters used as operands.
     */
    @UML(identifier="UnaryLogicOperator.operands, BinaryLogicOperator.operands", obligation=MANDATORY, specification=ISO_19143)
    List<Filter<? super R>> getOperands();
}
