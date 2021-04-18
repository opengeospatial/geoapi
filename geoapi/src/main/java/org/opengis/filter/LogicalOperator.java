/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
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
 * @param  <T>  the type of inputs to filter.
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="LogicalOperator", specification=ISO_19143)
public interface LogicalOperator<T> extends Filter<T> {
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
     * <blockquote><pre> T any = ...;
     * Object  e = expressions.get(i).apply(any);
     * Boolean f = filters.get(i).test(any);
     * assert e.equals(f);</pre>
     * </blockquote>
     *
     * @return a view of {@linkplain #getOperands() operands list} as expressions.
     */
    @Override
    default List<Expression<? super T, ?>> getExpressions() {
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
    List<Filter<? super T>> getOperands();
}
