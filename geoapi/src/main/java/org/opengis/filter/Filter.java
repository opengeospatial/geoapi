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
import java.util.function.Predicate;
import org.opengis.util.CodeList;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Identification of a subset of resources from a collection of resources
 * whose property values satisfy a set of logically connected predicates.
 * Often a filter is used to define a set of {@link Feature} instances that are to be operated upon.
 * The operating set can be comprised of one or more enumerated features or a set of features defined by
 * specifying spatial and non-spatial constraints on the geometric and scalar properties of a {@link FeatureType}.
 *
 * <p>Roughly speaking, a filter encodes the information present in the {@code WHERE} clause of a SQL statement.
 * There are various sub-interfaces of this interface that represent many types of filters,
 * such as simple property comparisons or spatial queries.</p>
 *
 * <p>The second use of {@code Filter} focuses on expressing constraints (or Facets).
 * This use places restrictions on the allowable and is captured as part of schema information {@link FeatureType}.
 * This is similar to the XML concept of "facets".</p>
 *
 * <h2>Parameter types</h2>
 * Filters are typically used for filtering feature instances.
 * In such case, the {@code <R>} type parameter should be {@link org.opengis.feature.Feature}.
 * However it is legal to use filters for other kinds of resources, for example coverage's geometry-value pairs.
 * The latter are sometime considered as a kind of features.
 *
 * <p>The value of {@code <? super R>} can be obtained at runtime by a call to {@link #getResourceClass()}.</p>
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 *
 * @departure easeOfUse
 *   In ISO specification, {@code Filter} contains a "filter" property of type {@code Operator}.
 *   Then various {@code Operator} subclasses are provided such as {@code SpatialOperator}, <i>etc.</i>
 *   GeoAPI removes this indirection level and extends {@code Filter} directly instead.
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="Filter", specification=ISO_19143)
public interface Filter<R> extends Predicate<R> {
    /**
     * A filter that always evaluates to {@code true}.
     * This is a placeholder for meaning "no filtering".
     * Filtering a set with {@code Filter.INCLUDE} results in the original set.
     *
     * <table class="ogc">
     *   <caption>Use with logic operators</caption>
     *   <tr><th>Operation</th> <th>Result</th></tr>
     *   <tr><td>{@code INCLUDE} or  Filter</td> <td>{@code INCLUDE}</td></tr>
     *   <tr><td>{@code INCLUDE} and Filter</td> <td>Filter</td></tr>
     *   <tr><td>not {@code INCLUDE}</td> <td>{@link #exclude EXCLUDE}</td></tr>
     * </table>
     *
     * @param  <R>  the type of resources to filter.
     * @return the "no filtering" filter.
     */
    @SuppressWarnings("unchecked")
    static <R> Filter<R> include() {
        return FilterLiteral.INCLUDE;
    }

    /**
     * A filter that always evaluates to {@code false}.
     * Filtering a set with {@code Filter.EXCLUDE} results in the empty set.
     *
     * <table class="ogc">
     *   <caption>Use with logic operators</caption>
     *   <tr><th>Operation</th> <th>Result</th></tr>
     *   <tr><td>{@code EXCLUDE} or  Filter</td> <td>Filter</td></tr>
     *   <tr><td>{@code EXCLUDE} and Filter</td> <td>{@code EXCLUDE}</td></tr>
     *   <tr><td>not {@code EXCLUDE}</td> <td>{@link #include INCLUDE}</td></tr>
     * </table>
     *
     * @param  <R>  the type of resources to filter.
     * @return the "exclude all" filter.
     */
    @SuppressWarnings("unchecked")
    static <R> Filter<R> exclude() {
        return FilterLiteral.EXCLUDE;
    }

    /**
     * Returns the nature of the operator. The code list can be {@link LogicalOperatorName},
     * {@link SpatialOperatorName}, {@link DistanceOperatorName} or {@link TemporalOperatorName}
     * depending on the {@code Filter} sub-interface.
     *
     * @return the nature of this operator.
     *
     * @departure generalization
     *   The ISO/OGC standard defines an {@code operatorType} property in {@code UnaryLogicOperator},
     *   {@code BinaryLogicOperator}, {@link BinaryComparisonOperator}, {@link BinarySpatialOperator}
     *   and {@link DistanceOperator}. This method has been added for providing a single access point
     *   for that information without the need to check for each sub-type.
     */
    CodeList<?> getOperatorType();

    /**
     * Returns the class of resources expected by this filter. This is the runtime value of the {@code <R>} type,
     * except that some implementations may accept instances of a more generic type such as {@code Object.class}.
     * For example {@link #include()} and {@link #exclude()} put no restriction on the resource type
     * because those filters ignore the given resource.
     *
     * <h4>Implementation note</h4>
     * This filter resource class should be assignable to all resource classes expected by
     * the {@linkplain #getExpressions() expressions} that are arguments of this filter.
     * The type parametrization rules guarantee that at least one such specialized class exists: {@code <R>}.
     * The behavior of this method is undefined if compile-time type safety was bypassed with unchecked casts,
     * resulting in possible inconsistencies in the tree of expressions.
     * The undefined behavior may be throwing an exception or returning {@code null}.
     *
     * @return type of resources accepted by this filter.
     *
     * @see Expression#getResourceClass()
     */
    Class<? super R> getResourceClass();

    /**
     * Returns the expressions used as arguments for this filter.
     * This method shall return all parameters used directly by the filter,
     * including the ones also available by dedicated methods
     * (possibly as {@linkplain Literal literals}).
     *
     * @return the expressions used as inputs, or an empty list if none.
     *
     * @departure generalization
     *   This method has been added for providing a single access point for information provided in sub-types.
     *   The properties captured here have different names in OGC/ISO specification depending on the sub-type:
     *   {@code expression} (with a cardinality of 1, 2 or unlimited), {@code operand1}, {@code operand2}, or
     *   {@code valueReference}. This method provides a way to access those expressions without the need to
     *   make special cases for each sub-type.
     */
    List<Expression<R,?>> getExpressions();
    /*
     * API design note: the `<R>` parameterized type could be more generic. It could be `<? super R>` instead.
     * However expressions and filters are often chained, and following a chain of filters become difficult if,
     * when asking parameters of parameters, the `<? super R>` type become a kind of `<? super ? super R>` type.
     * The latter is reported by the compiler as `<? super #CAP1>`. Experience with implementation shows that it
     * is difficult to avoid unsafe cast in such cases.
     */

    /**
     * Given an object, determines if the test(s) represented by this filter are passed.
     * This ability is used to allow queries against both features and and non spatial data
     * (such as record) and to express constraints on permissable data values.
     *
     * @param  object  the object (often a {@link Feature} instance) to evaluate.
     * @return {@code true} if the test(s) are passed for the provided object.
     * @throws NullPointerException if {@code object} is null.
     * @throws InvalidFilterValueException if the filter cannot be applied on the given object.
     */
    @Override
    boolean test(R object) throws InvalidFilterValueException;
}
