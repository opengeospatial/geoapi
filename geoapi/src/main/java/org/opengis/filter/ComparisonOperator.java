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

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * An operator that evaluates the mathematical comparison between arguments.
 * If the arguments satisfy the comparison then the operator {@linkplain #test evaluates} to {@code true}.
 * Otherwise the operator evaluates to {@code false}.
 *
 * <p>The arguments are given as {@linkplain #getExpressions() expressions}.
 * The number of expressions depends on the sub-type:</p>
 * <ul>
 *   <li>{@link NullOperator} and {@link NilOperator} expect one expression.</li>
 *   <li>{@link BinaryComparisonOperator} and {@link LikeOperator} expect two expressions.</li>
 *   <li>{@link BetweenComparisonOperator} expects three expressions.</li>
 * </ul>
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="ComparisonOperator", specification=ISO_19143)
public interface ComparisonOperator<R> extends Filter<R> {
    /**
     * Returns the nature of the comparison. A standard set of comparison operators is equal to,
     * less than, greater than, less than or equal to, greater than or equal to and not equal to.
     *
     * @return the nature of the comparison.
     */
    @Override
    @UML(identifier="BinaryComparisonOperator.operatorType", obligation=MANDATORY, specification=ISO_19143)
    ComparisonOperatorName getOperatorType();
}
