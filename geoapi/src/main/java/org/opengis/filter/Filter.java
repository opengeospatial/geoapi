/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
    List<Expression<? super R, ?>> getExpressions();

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
