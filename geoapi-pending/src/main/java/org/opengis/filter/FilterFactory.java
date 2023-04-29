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

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Collection;
import javax.measure.Quantity;
import javax.measure.quantity.Length;
import org.opengis.util.Factory;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.Envelope;
import org.opengis.filter.capability.FilterCapabilities;


/**
 * Factory of instances of the various {@link Filter} and {@link Expression} subclasses.
 *
 * <p><b>WARNING:</b> this interface is a first draft. Its API may change at any time.
 * In particular different strategies may be tried regarding the parameterized types.</p>
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Jody Garnett (Refractions Research Inc.)
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to use as inputs.
 * @param  <G>  base class of geometry objects. The implementation-neutral type is GeoAPI {@link Geometry},
 *              but this factory allows the use of other implementations such as JTS
 *              {@link org.locationtech.jts.geom.Geometry} or ESRI {@link com.esri.core.geometry.Geometry}.
 * @param  <T>  base class of temporal objects.
 *
 * @since 3.1
 */
public interface FilterFactory<R,G,T> extends Factory {
    /**
     * Describes the abilities of this factory. The description includes restrictions
     * on the available spatial operations, scalar operations, lists of supported functions,
     * and description of which geometry literals are understood.
     *
     * @return description of the abilities of this factory.
     */
    FilterCapabilities getCapabilities();

    /**
     * Creates a predicate to identify an identifiable resource within a filter expression.
     * The predicate uses no versioning and no time range.
     *
     * @param  rid  identifier of the resource that shall be selected by the predicate.
     * @return the predicate.
     */
    default ResourceId<R> resourceId(final String rid) {
        return resourceId(rid, null, null, null);
    }

    /**
     * Creates a predicate to identify an identifiable resource within a filter expression.
     * If {@code startTime} and {@code endTime} are non-null, the filter will select all versions
     * of a resource between the specified dates.
     *
     * @param  rid        identifier of the resource that shall be selected by the predicate.
     * @param  version    version of the resource to select, or {@code null} for any version.
     * @param  startTime  start time of the resource to select, or {@code null} if none.
     * @param  endTime    end time of the resource to select, or {@code null} if none.
     * @return the predicate.
     */
    ResourceId<R> resourceId(String rid, Version version, Instant startTime, Instant endTime);

    /**
     * Creates an expression whose value is computed by retrieving the value indicated by a path in a resource.
     * If all characters in the path are {@linkplain Character#isUnicodeIdentifierPart(int) Unicode identifier parts},
     * then the XPath expression is simply a property name.
     *
     * @param  xpath  the path to the property whose value will be returned by the {@code apply(R)} method.
     * @return an expression evaluating the referenced property value.
     */
    default ValueReference<R,?> property(String xpath) {
        return property(xpath, Object.class);
    }

    /**
     * Creates an expression retrieving the value as an instance of the specified class.
     * The {@code xpath} argument follows the rule described in {@link #property(String)}.
     *
     * <p>The desired type of property values can be specified. For example if the property values should be numbers,
     * then {@code type} can be <code>{@linkplain Number}.class</code>. If property values can be of any type with no
     * conversion desired, then {@code type} should be {@code Object.class}.</p>
     *
     * @param  <V>    the type of the values to be fetched (compile-time value of {@code type}).
     * @param  xpath  the path to the property whose value will be returned by the {@code apply(R)} method.
     * @param  type   the type of the values to be fetched (run-time value of {@code <V>}).
     * @return an expression evaluating the referenced property value.
     */
    <V> ValueReference<R,V> property(String xpath, Class<V> type);

    /**
     * Creates a constant, literal value that can be used in expressions.
     *
     * <h4>Recommended restriction</h4>
     * The given value may be persisted with XML based technologies.
     * As an example a {@link Geometry} may be written out using GML.
     * Consequently, the value should be data objects such as strings, numbers, dates, or geometries.
     * It should not be state of operations.
     *
     * @param  <V>    the type of the value of the literal.
     * @param  value  the literal value. May be {@code null}.
     * @return a literal for the given value.
     */
    <V> Literal<R,V> literal(V value);

    /**
     * Filter operator that compares that its two sub-expressions are equal to each other.
     * The comparison is case-sensitive and evaluates to {@code true} if {@linkplain MatchAction#ANY Any}
     * of the value in the collection can satisfy the predicate.
     *
     * @param  expression1  the first of the two expressions to be used by this comparator.
     * @param  expression2  the second of the two expressions to be used by this comparator.
     * @return a filter evaluating {@code expression1} = {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_EQUAL_TO
     */
    default BinaryComparisonOperator<R> equal(Expression<R,?> expression1,
                                              Expression<R,?> expression2)
    {
        return equal(Objects.requireNonNull(expression1),
                     Objects.requireNonNull(expression2),
                     true, MatchAction.ANY);
    }

    /**
     * Filter operator that compares that its two sub-expressions are equal to each other.
     *
     * @param  expression1     the first of the two expressions to be used by this comparator.
     * @param  expression2     the second of the two expressions to be used by this comparator.
     * @param  isMatchingCase  specifies whether comparisons are case sensitive.
     * @param  matchAction     specifies how the comparisons shall be evaluated for a collection of values.
     * @return a filter evaluating {@code expression1} = {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_EQUAL_TO
     */
    BinaryComparisonOperator<R> equal(Expression<R,?> expression1,
                                      Expression<R,?> expression2,
                                      boolean isMatchingCase, MatchAction matchAction);

    /**
     * Filter operator that compares that its two sub-expressions are not equal to each other.
     * The comparison is case-sensitive and evaluates to {@code true} if {@linkplain MatchAction#ANY Any}
     * of the value in the collection can satisfy the predicate.
     *
     * @param  expression1  the first of the two expressions to be used by this comparator.
     * @param  expression2  the second of the two expressions to be used by this comparator.
     * @return a filter evaluating {@code expression1} ≠ {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_NOT_EQUAL_TO
     */
    default BinaryComparisonOperator<R> notEqual(Expression<R,?> expression1,
                                                 Expression<R,?> expression2)
    {
        return notEqual(Objects.requireNonNull(expression1),
                        Objects.requireNonNull(expression2),
                        true, MatchAction.ANY);
    }

    /**
     * Filter operator that compares that its two sub-expressions are not equal to each other.
     *
     * @param  expression1     the first of the two expressions to be used by this comparator.
     * @param  expression2     the second of the two expressions to be used by this comparator.
     * @param  isMatchingCase  specifies whether comparisons are case sensitive.
     * @param  matchAction     specifies how the comparisons shall be evaluated for a collection of values.
     * @return a filter evaluating {@code expression1} ≠ {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_NOT_EQUAL_TO
     */
    BinaryComparisonOperator<R> notEqual(Expression<R,?> expression1,
                                         Expression<R,?> expression2,
                                         boolean isMatchingCase, MatchAction matchAction);

    /**
     * Filter operator that checks that its first sub-expression is less than its second sub-expression.
     * The comparison is case-sensitive and evaluates to {@code true} if {@linkplain MatchAction#ANY Any}
     * of the value in the collection can satisfy the predicate.
     *
     * @param  expression1  the first of the two expressions to be used by this comparator.
     * @param  expression2  the second of the two expressions to be used by this comparator.
     * @return a filter evaluating {@code expression1} &lt; {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_LESS_THAN
     * @todo Revisit if we can be more specific on the second parameterized type in expressions.
     */
    default BinaryComparisonOperator<R> less(Expression<R,?> expression1,
                                             Expression<R,?> expression2)
    {
        return less(Objects.requireNonNull(expression1),
                    Objects.requireNonNull(expression2),
                    true, MatchAction.ANY);
    }

    /**
     * Filter operator that checks that its first sub-expression is less than its second sub-expression.
     *
     * @param  expression1     the first of the two expressions to be used by this comparator.
     * @param  expression2     the second of the two expressions to be used by this comparator.
     * @param  isMatchingCase  specifies whether comparisons are case sensitive.
     * @param  matchAction     specifies how the comparisons shall be evaluated for a collection of values.
     * @return a filter evaluating {@code expression1} &lt; {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_LESS_THAN
     * @todo Revisit if we can be more specific on the second parameterized type in expressions.
     */
    BinaryComparisonOperator<R> less(Expression<R,?> expression1,
                                     Expression<R,?> expression2,
                                     boolean isMatchingCase, MatchAction matchAction);

    /**
     * Filter operator that checks that its first sub-expression is greater than its second sub-expression.
     * The comparison is case-sensitive and evaluates to {@code true} if {@linkplain MatchAction#ANY Any}
     * of the value in the collection can satisfy the predicate.
     *
     * @param  expression1  the first of the two expressions to be used by this comparator.
     * @param  expression2  the second of the two expressions to be used by this comparator.
     * @return a filter evaluating {@code expression1} &gt; {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_GREATER_THAN
     * @todo Revisit if we can be more specific on the second parameterized type in expressions.
     */
    default BinaryComparisonOperator<R> greater(Expression<R,?> expression1,
                                                Expression<R,?> expression2)
    {
        return greater(Objects.requireNonNull(expression1),
                       Objects.requireNonNull(expression2),
                       true, MatchAction.ANY);
    }

    /**
     * Filter operator that checks that its first sub-expression is greater than its second sub-expression.
     *
     * @param  expression1     the first of the two expressions to be used by this comparator.
     * @param  expression2     the second of the two expressions to be used by this comparator.
     * @param  isMatchingCase  specifies whether comparisons are case sensitive.
     * @param  matchAction     specifies how the comparisons shall be evaluated for a collection of values.
     * @return a filter evaluating {@code expression1} &gt; {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_GREATER_THAN
     * @todo Revisit if we can be more specific on the second parameterized type in expressions.
     */
    BinaryComparisonOperator<R> greater(Expression<R,?> expression1,
                                        Expression<R,?> expression2,
                                        boolean isMatchingCase, MatchAction matchAction);

    /**
     * Filter operator that checks that its first sub-expression is less than or equal to its second sub-expression.
     * The comparison is case-sensitive and evaluates to {@code true} if {@linkplain MatchAction#ANY Any}
     * of the value in the collection can satisfy the predicate.
     *
     * @param  expression1  the first of the two expressions to be used by this comparator.
     * @param  expression2  the second of the two expressions to be used by this comparator.
     * @return a filter evaluating {@code expression1} ≤ {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_LESS_THAN_OR_EQUAL_TO
     * @todo Revisit if we can be more specific on the second parameterized type in expressions.
     */
    default BinaryComparisonOperator<R> lessOrEqual(Expression<R,?> expression1,
                                                    Expression<R,?> expression2)
    {
        return lessOrEqual(Objects.requireNonNull(expression1),
                           Objects.requireNonNull(expression2),
                           true, MatchAction.ANY);
    }

    /**
     * Filter operator that checks that its first sub-expression is less than or equal to its second sub-expression.
     *
     * @param  expression1     the first of the two expressions to be used by this comparator.
     * @param  expression2     the second of the two expressions to be used by this comparator.
     * @param  isMatchingCase  specifies whether comparisons are case sensitive.
     * @param  matchAction     specifies how the comparisons shall be evaluated for a collection of values.
     * @return a filter evaluating {@code expression1} ≤ {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_LESS_THAN_OR_EQUAL_TO
     * @todo Revisit if we can be more specific on the second parameterized type in expressions.
     */
    BinaryComparisonOperator<R> lessOrEqual(Expression<R,?> expression1,
                                            Expression<R,?> expression2,
                                            boolean isMatchingCase, MatchAction matchAction);

    /**
     * Filter operator that checks that its first sub-expression is greater than its second sub-expression.
     * The comparison is case-sensitive and evaluates to {@code true} if {@linkplain MatchAction#ANY Any}
     * of the value in the collection can satisfy the predicate.
     *
     * @param  expression1  the first of the two expressions to be used by this comparator.
     * @param  expression2  the second of the two expressions to be used by this comparator.
     * @return a filter evaluating {@code expression1} ≥ {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_GREATER_THAN_OR_EQUAL_TO
     * @todo Revisit if we can be more specific on the second parameterized type in expressions.
     */
    default BinaryComparisonOperator<R> greaterOrEqual(Expression<R,?> expression1,
                                                       Expression<R,?> expression2)
    {
        return greaterOrEqual(Objects.requireNonNull(expression1),
                              Objects.requireNonNull(expression2),
                              true, MatchAction.ANY);
    }

    /**
     * Filter operator that checks that its first sub-expression is greater than its second sub-expression.
     *
     * @param  expression1     the first of the two expressions to be used by this comparator.
     * @param  expression2     the second of the two expressions to be used by this comparator.
     * @param  isMatchingCase  specifies whether comparisons are case sensitive.
     * @param  matchAction     specifies how the comparisons shall be evaluated for a collection of values.
     * @return a filter evaluating {@code expression1} ≥ {@code expression2}.
     *
     * @see ComparisonOperatorName#PROPERTY_IS_GREATER_THAN_OR_EQUAL_TO
     * @todo Revisit if we can be more specific on the second parameterized type in expressions.
     */
    BinaryComparisonOperator<R> greaterOrEqual(Expression<R,?> expression1,
                                               Expression<R,?> expression2,
                                               boolean isMatchingCase, MatchAction matchAction);

    /**
     * Filter operation for a range check.
     * The lower and upper boundary values are inclusive.
     *
     * @param  expression     the expression to be compared by this comparator.
     * @param  lowerBoundary  the lower bound (inclusive) as an expression.
     * @param  upperBoundary  the upper bound (inclusive) as an expression.
     * @return a filter evaluating ({@code expression} ≥ {@code lowerBoundary})
     *                       &amp; ({@code expression} ≤ {@code upperBoundary}).
     */
    BetweenComparisonOperator<R> between(Expression<R,?> expression,
                                         Expression<R,?> lowerBoundary,
                                         Expression<R,?> upperBoundary);

    /**
     * Character string comparison operator with pattern matching and default wildcards.
     * The wildcard character is {@code '%'}, the single character is {@code '_'} and
     * the escape character is {@code '\\'}. The comparison is case-sensitive.
     *
     * @param  expression  source of values to compare against the pattern.
     * @param  pattern     pattern to match against expression values.
     * @return a character string comparison operator with pattern matching.
     */
    default LikeOperator<R> like(Expression<R,?> expression, String pattern) {
        return like(Objects.requireNonNull(expression), Objects.requireNonNull(pattern), '%', '_', '\\', true);
    }

    /**
     * Character string comparison operator with pattern matching and specified wildcards.
     *
     * @param  expression      source of values to compare against the pattern.
     * @param  pattern         pattern to match against expression values.
     * @param  wildcard        pattern character for matching any sequence of characters.
     * @param  singleChar      pattern character for matching exactly one character.
     * @param  escape          pattern character for indicating that the next character should be matched literally.
     * @param  isMatchingCase  specifies how a filter expression processor should perform string comparisons.
     * @return a character string comparison operator with pattern matching.
     */
    LikeOperator<R> like(Expression<R,?> expression, String pattern,
            char wildcard, char singleChar, char escape, boolean isMatchingCase);

    /**
     * An operator that tests if an expression's value is {@code null}.
     * This corresponds to checking whether the property exists in the real-world.
     *
     * @param  expression  source of values to compare against {@code null}.
     * @return a filter that checks if an expression's value is {@code null}.
     */
    NullOperator<R> isNull(Expression<R,?> expression);

    /**
     * An operator that tests if an expression's value is nil.
     * The difference with {@link NullOperator} is that a value should exist
     * but cannot be provided for the reason given by {@code nilReason}.
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
     * @param  expression  source of values to compare against nil values.
     * @param  nilReason   the reason why the value is nil, or {@code null} for accepting any reason.
     * @return a filter that checks if an expression's value is nil for the specified reason.
     */
    NilOperator<R> isNil(Expression<R,?> expression, String nilReason);

    /**
     * Creates a {@code AND} filter between two filters.
     *
     * @param  operand1  the first operand of the AND operation.
     * @param  operand2  the second operand of the AND operation.
     * @return a filter evaluating {@code operand1 AND operand2}.
     *
     * @see LogicalOperatorName#AND
     */
    default LogicalOperator<R> and(Filter<R> operand1, Filter<R> operand2) {
        return and(List.<Filter<R>>of(Objects.requireNonNull(operand1),
                                              Objects.requireNonNull(operand2)));
    }

    /**
     * Creates a {@code AND} filter between two or more filters.
     *
     * @param  operands  a collection of at least 2 operands.
     * @return a filter evaluating {@code operand1 AND operand2 AND operand3}…
     * @throws IllegalArgumentException if the given collection contains less than 2 elements.
     *
     * @see LogicalOperatorName#AND
     */
    LogicalOperator<R> and(Collection<? extends Filter<R>> operands);

    /**
     * Creates a {@code OR} filter between two filters.
     *
     * @param  operand1  the first operand of the OR operation.
     * @param  operand2  the second operand of the OR operation.
     * @return a filter evaluating {@code operand1 OR operand2}.
     *
     * @see LogicalOperatorName#OR
     */
    default LogicalOperator<R> or(Filter<R> operand1, Filter<R> operand2) {
        return or(List.<Filter<R>>of(Objects.requireNonNull(operand1),
                                             Objects.requireNonNull(operand2)));
    }

    /**
     * Creates a {@code OR} filter between two or more filters.
     *
     * @param  operands  a collection of at least 2 operands.
     * @return a filter evaluating {@code operand1 OR operand2 OR operand3}…
     * @throws IllegalArgumentException if the given collection contains less than 2 elements.
     *
     * @see LogicalOperatorName#OR
     */
    LogicalOperator<R> or(Collection<? extends Filter<R>> operands);

    /**
     * Creates a {@code NOT} filter for the given filter.
     *
     * @param  operand  the operand of the NOT operation.
     * @return a filter evaluating {@code NOT operand}.
     *
     * @see LogicalOperatorName#NOT
     */
    LogicalOperator<R> not(Filter<R> operand);

////////////////////////////////////////////////////////////////////////////////
//
//  SPATIAL FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates an operator that checks if the bounding box of the feature's geometry interacts
     * with the bounding box provided in the filter properties.
     *
     * @param  geometry  expression fetching the geometry to check for interaction with bounds.
     * @param  bounds    the bounds to check geometry against.
     * @return a filter checking for any interactions between the bounding boxes.
     *
     * @see SpatialOperatorName#BBOX
     */
    BinarySpatialOperator<R> bbox(Expression<R, ? extends G> geometry, Envelope bounds);

    /**
     * Creates an operator that checks if the geometry of the two operands are equal.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @return a filter for the "Equals" operator between the two geometries.
     *
     * @see SpatialOperatorName#EQUALS
     *
     * @todo Rename {@code equal}.
     */
    BinarySpatialOperator<R> equals(Expression<R, ? extends G> geometry1,
                                    Expression<R, ? extends G> geometry2);

    /**
     * Creates an operator that checks if the first operand is disjoint from the second.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @return a filter for the "Disjoint" operator between the two geometries.
     *
     * @see SpatialOperatorName#DISJOINT
     */
    BinarySpatialOperator<R> disjoint(Expression<R, ? extends G> geometry1,
                                      Expression<R, ? extends G> geometry2);

    /**
     * Creates an operator that checks if the two geometric operands intersect.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @return a filter for the "Intersects" operator between the two geometries.
     *
     * @see SpatialOperatorName#INTERSECTS
     */
    BinarySpatialOperator<R> intersects(Expression<R, ? extends G> geometry1,
                                        Expression<R, ? extends G> geometry2);

    /**
     * Creates an operator that checks if the two geometric operands touch each other, but do not overlap.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @return a filter for the "Touches" operator between the two geometries.
     *
     * @see SpatialOperatorName#TOUCHES
     */
    BinarySpatialOperator<R> touches(Expression<R, ? extends G> geometry1,
                                     Expression<R, ? extends G> geometry2);

    /**
     * Creates an operator that checks if the first geometric operand crosses the second.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @return a filter for the "Crosses" operator between the two geometries.
     *
     * @see SpatialOperatorName#CROSSES
     */
    BinarySpatialOperator<R> crosses(Expression<R, ? extends G> geometry1,
                                     Expression<R, ? extends G> geometry2);

    /**
     * Creates an operator that checks if the first geometric operand is completely
     * contained by the constant geometric operand.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @return a filter for the "Within" operator between the two geometries.
     *
     * @see SpatialOperatorName#WITHIN
     */
    BinarySpatialOperator<R> within(Expression<R, ? extends G> geometry1,
                                    Expression<R, ? extends G> geometry2);

    /**
     * Creates an operator that checks if the first geometric operand contains the second.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @return a filter for the "Contains" operator between the two geometries.
     *
     * @see SpatialOperatorName#CONTAINS
     */
    BinarySpatialOperator<R> contains(Expression<R, ? extends G> geometry1,
                                      Expression<R, ? extends G> geometry2);

    /**
     * Creates an operator that checks if the interior of the first geometric operand
     * somewhere overlaps the interior of the second geometric operand.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @return a filter for the "Overlaps" operator between the two geometries.
     *
     * @see SpatialOperatorName#OVERLAPS
     */
    BinarySpatialOperator<R>  overlaps(Expression<R, ? extends G> geometry1,
                                       Expression<R, ? extends G> geometry2);

    /**
     * Creates an operator that checks if all of a feature's geometry is more distant
     * than the given distance from the given geometry.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @param  distance   minimal distance for evaluating the expression as {@code true}.
     * @return operator that evaluates to {@code true} when all of a feature's geometry
     *         is more distant than the given distance from the second geometry.
     *
     * @see DistanceOperatorName#BEYOND
     */
    DistanceOperator<R> beyond(Expression<R, ? extends G> geometry1,
                               Expression<R, ? extends G> geometry2,
                               Quantity<Length> distance);

    /**
     * Creates an operator that checks if any part of the first geometry lies within
     * the given distance of the second geometry.
     *
     * @param  geometry1  expression fetching the first geometry of the binary operator.
     * @param  geometry2  expression fetching the second geometry of the binary operator.
     * @param  distance   maximal distance for evaluating the expression as {@code true}.
     * @return operator that evaluates to {@code true} when any part of the feature's geometry
     *         lies within the given distance of the second geometry.
     *
     * @see DistanceOperatorName#WITHIN
     */
    DistanceOperator<R> within(Expression<R, ? extends G> geometry1,
                               Expression<R, ? extends G> geometry2,
                               Quantity<Length> distance);

////////////////////////////////////////////////////////////////////////////////
//
//  TEMPORAL FILTERS
//
////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates an operator that checks if first temporal operand is after the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "After" operator between the two temporal values.
     *
     * @see TemporalOperatorName#AFTER
     */
    TemporalOperator<R> after(Expression<R, ? extends T> time1,
                              Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand is before the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "Before" operator between the two temporal values.
     *
     * @see TemporalOperatorName#BEFORE
     */
    TemporalOperator<R> before(Expression<R, ? extends T> time1,
                               Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand begins at the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "Begins" operator between the two temporal values.
     *
     * @see TemporalOperatorName#BEGINS
     */
    TemporalOperator<R> begins(Expression<R, ? extends T> time1,
                               Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand begun by the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "BegunBy" operator between the two temporal values.
     *
     * @see TemporalOperatorName#BEGUN_BY
     */
    TemporalOperator<R> begunBy(Expression<R, ? extends T> time1,
                                Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand is contained by the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "TContains" operator between the two temporal values.
     *
     * @see TemporalOperatorName#CONTAINS
     */
    TemporalOperator<R> tcontains(Expression<R, ? extends T> time1,
                                  Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand is during the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "During" operator between the two temporal values.
     *
     * @see TemporalOperatorName#DURING
     */
    TemporalOperator<R> during(Expression<R, ? extends T> time1,
                               Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand is equal to the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "TEquals" operator between the two temporal values.
     *
     * @see TemporalOperatorName#EQUALS
     */
    TemporalOperator<R> tequals(Expression<R, ? extends T> time1,
                                Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand overlaps the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "TOverlaps" operator between the two temporal values.
     *
     * @see TemporalOperatorName#OVERLAPS
     */
    TemporalOperator<R> toverlaps(Expression<R, ? extends T> time1,
                                  Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand meets the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "Meets" operator between the two temporal values.
     *
     * @see TemporalOperatorName#MEETS
     */
    TemporalOperator<R> meets(Expression<R, ? extends T> time1,
                              Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand ends at the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "Ends" operator between the two temporal values.
     *
     * @see TemporalOperatorName#ENDS
     */
    TemporalOperator<R> ends(Expression<R, ? extends T> time1,
                             Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand is overlapped by the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "OverlappedBy" operator between the two temporal values.
     *
     * @see TemporalOperatorName#OVERLAPPED_BY
     */
    TemporalOperator<R> overlappedBy(Expression<R, ? extends T> time1,
                                     Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand is met by the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "MetBy" operator between the two temporal values.
     *
     * @see TemporalOperatorName#MET_BY
     */
    TemporalOperator<R> metBy(Expression<R, ? extends T> time1,
                              Expression<R, ? extends T> time2);

    /**
     * Creates an operator that checks if first temporal operand is ended by the second.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "EndedBy" operator between the two temporal values.
     *
     * @see TemporalOperatorName#ENDED_BY
     */
    TemporalOperator<R> endedBy(Expression<R, ? extends T> time1,
                                Expression<R, ? extends T> time2);

    /**
     * Creates a shortcut operator semantically equivalent to NOT (Before OR Meets OR MetBy OR After).
     * This is applicable to periods only.
     *
     * @param  time1  expression fetching the first temporal value.
     * @param  time2  expression fetching the second temporal value.
     * @return a filter for the "AnyInteracts" operator between the two temporal values.
     *
     * @see TemporalOperatorName#ANY_INTERACTS
     */
    TemporalOperator<R> anyInteracts(Expression<R, ? extends T> time1,
                                     Expression<R, ? extends T> time2);

////////////////////////////////////////////////////////////////////////////////
//
//  MISCELLANEOUS
//
////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a function computing the numeric addition of the first and second operand.
     *
     * @param  operand1  expression fetching the first number.
     * @param  operand2  expression fetching the second number.
     * @return an expression for the "Add" function between the two numerical values.
     */
    Expression<R,Number> add(Expression<R, ? extends Number> operand1,
                             Expression<R, ? extends Number> operand2);

    /**
     * Creates a function computing the numeric difference between the first and second operand.
     *
     * @param  operand1  expression fetching the first number.
     * @param  operand2  expression fetching the second number.
     * @return an expression for the "Subtract" function between the two numerical values.
     */
    Expression<R,Number> subtract(Expression<R, ? extends Number> operand1,
                                  Expression<R, ? extends Number> operand2);

    /**
     * Creates a function computing the numeric product of their first and second operand.
     *
     * @param  operand1  expression fetching the first number.
     * @param  operand2  expression fetching the second number.
     * @return an expression for the "Multiply" function between the two numerical values.
     */
    Expression<R,Number> multiply(Expression<R, ? extends Number> operand1,
                                  Expression<R, ? extends Number> operand2);

    /**
     * Creates a function computing the numeric quotient resulting from dividing the first operand by the second.
     *
     * @param  operand1  expression fetching the first number.
     * @param  operand2  expression fetching the second number.
     * @return an expression for the "Divide" function between the two numerical values.
     */
    Expression<R,Number> divide(Expression<R, ? extends Number> operand1,
                                Expression<R, ? extends Number> operand2);

    /**
     * Creates an implementation-specific function with a single parameter.
     * The names of available functions is given by {@link #getCapabilities()}.
     *
     * @param  name       name of the function to call.
     * @param  parameter  expression providing values for the function argument.
     * @return an expression which will call the specified function.
     * @throws IllegalArgumentException if the given name is not recognized,
     *         or if the argument is illegal for the specified function.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    default Expression<R,?> function(String name, Expression<R,?> parameter) {
        return function(name, new Expression[] {
            Objects.requireNonNull(parameter)
        });
    }

    /**
     * Creates an implementation-specific function with two parameters.
     * The names of available functions is given by {@link #getCapabilities()}.
     *
     * @param  name    name of the function to call.
     * @param  param1  expression providing values for the first function argument.
     * @param  param2  expression providing values for the second function argument.
     * @return an expression which will call the specified function.
     * @throws IllegalArgumentException if the given name is not recognized,
     *         or if the arguments are illegal for the specified function.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    default Expression<R,?> function(String name, Expression<R,?> param1, Expression<R,?> param2) {
        return function(name, new Expression[] {
            Objects.requireNonNull(param1),
            Objects.requireNonNull(param2)
        });
    }

    /**
     * Creates an implementation-specific function with an arbitrary number of parameters.
     * The names of available functions is given by {@link #getCapabilities()}.
     *
     * @param  name        name of the function to call.
     * @param  parameters  expressions providing values for the function arguments.
     * @return an expression which will call the specified function.
     * @throws IllegalArgumentException if the given name is not recognized,
     *         or if the arguments are illegal for the specified function.
     */
    Expression<R,?> function(String name, Expression<R,?>[] parameters);

    /**
     * Indicates a property by which contents should be sorted, along with intended order.
     * Implementation may require that the given expression evaluates to {@link Comparable} objects.
     *
     * @param  property  the property to sort by.
     * @param  order     the sorting order, ascending or descending.
     * @return definition of sort order of a property.
     */
    SortProperty<R> sort(ValueReference<R,?> property, SortOrder order);
}
