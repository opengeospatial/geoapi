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
import java.util.Optional;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * An operator that evaluates if the value of an expression is nil.
 * The difference with {@link NullOperator} is that a value should exist
 * but cannot be provided for the reason given by {@link #getNilReason()}.
 * A nil value is not necessarily {@code null}; implementations are free to use placeholders.
 *
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="NilOperator", specification=ISO_19143)
public interface NilOperator<R> extends ComparisonOperator<R> {
    /**
     * Returns the nature of the comparison.
     * The default implementation returns {@code PROPERTY_IS_NIL}.
     *
     * @return the nature of the comparison.
     */
    @Override
    default ComparisonOperatorName getOperatorType() {
        return ComparisonOperatorName.PROPERTY_IS_NIL;
    }

    /**
     * Returns the expression whose value will be checked for nil.
     *
     * @return a list of size 1 containing the expression to test for nil value.
     */
    @Override
    @UML(identifier="expression", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<? super R, ?>> getExpressions();

    /**
     * Returns the reason why the value is nil.
     * Possible reasons are:
     *
     * <ul>
     *   <li><b>inapplicable</b> — there is no value.</li>
     *   <li><b>template</b>     — the value will be available later.</li>
     *   <li><b>missing</b>      — the correct value is not readily available to the sender of this data.
     *                             Furthermore, a correct value may not exist.</li>
     *   <li><b>unknown</b>      — the correct value is not known to, and not computable by, the sender of this data.
     *                             However, a correct value probably exists..</li>
     *   <li><b>withheld</b>     — the value is not divulged.</li>
     *   <li>Other strings at implementation choice.</li>
     * </ul>
     *
     * @return the reason why the value is nil, or empty for any reason.
     */
    @UML(identifier="nilReason", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<String> getNilReason() {
        return Optional.empty();
    }
}
