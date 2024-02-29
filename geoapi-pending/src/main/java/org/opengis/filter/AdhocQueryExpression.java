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
import java.util.Optional;
import org.opengis.util.TypeName;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * An expression with a query that is not known before the time it is being executed.
 * An ad hoc query expression contains:
 *
 * <ul>
 *   <li>the names of one or more resource types to query,</li>
 *   <li>an optional projection clause enumerating the properties of the resource to present in the response,</li>
 *   <li>an option selection clause that constraints the properties of those resources types
 *       in order to define a result set,</li>
 *   <li>an optional sorting clause specifying the order in which the result set is presented.</li>
 * </ul>
 *
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="AdhocQueryExpression", specification=ISO_19143)
public interface AdhocQueryExpression extends QueryExpression {
    /**
     * Names of one or more correlated resource types to be queried.
     *
     * @return names of resource types to be queries.
     *
     * @departure easeOfUse
     *   The filter specification associates a class {@code Type} with the following properties,
     *   where {@code ofType} and {@code ofKind} are in a union (which means that exactly one of
     *   those 2 properties shall be provided):
     *   <ul>
     *     <li>{@code ofType} as a {@link org.opengis.util.ScopedName}.</li>
     *     <li>{@code ofKind} as a {@link org.opengis.util.ScopedName}.</li>
     *     <li>{@code alias}  as a {@link org.opengis.util.LocalName}.
     *         "the optional alias may be used within an ad hoc query expression
     *          to specify alternate names for the resource type name."</li>
     *   </ul>
     *   Since we have no details about {@code ofType} and {@code ofKind} yet, we omitted the
     *   {@code Type} interface for now. If we add this functionality in a future version,
     *   we may consider creating a sub-interface of {@link TypeName} instead.
     */
    @UML(identifier="types", obligation=MANDATORY, specification=ISO_19143)
    List<? extends TypeName> getTypeNames();

    /**
     * Optional resource properties that shall be available in a query response.
     * While it can be any type of objects, the common case is list of {@link ValueReference}.
     *
     * @return the projection clauses, or an empty list if none.
     *
     * @todo According specification, elements should be sub-types of {@code AbstractAdhocProjectionClause}.
     */
    @UML(identifier="projection", obligation=OPTIONAL, specification=ISO_19143)
    List<?> getProjectionClauses();

    /**
     * Query predicates that shall be applied to a dataset in order to define a subset of data to be operated upon.
     * While it can be any type of objects, the common case is a {@link Filter}.
     *
     * @return the query clause.
     *
     * @todo According specification, should be a sub-types of {@code AbstractSelectionClause}.
     */
    @UML(identifier="selection", obligation=OPTIONAL, specification=ISO_19143)
    Optional<?> getSelectionClause();

    /**
     * Asserts the order in which resources shall appear in response to an ad hoc query expression.
     *
     * @return order in which resources shall appear.
     *
     * @todo According specification, should be a sub-type of {@code AbstractSortingClause}.
     */
    @UML(identifier="sorting", obligation=OPTIONAL, specification=ISO_19143)
    Optional<?> getSortingClause();
}
