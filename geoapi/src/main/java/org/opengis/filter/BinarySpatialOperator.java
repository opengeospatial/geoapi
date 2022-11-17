/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
import org.opengis.feature.Feature;
import org.opengis.geometry.Envelope;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Operator that tests whether two expressions that evaluate to geometric values satisfy
 * the spatial relationship implied by the operator.
 * The nature of the comparison is dependent on the {@linkplain #getOperatorType() operator type}.
 * A standard set of spatial operators is equal, disjoin, touches, within, overlaps, crosses,
 * intersects, contains, beyond and BBOX.
 *
 * <div class="note"><b>Example:</b>
 * An operator of type {@link SpatialOperatorName#OVERLAPS} evaluates whether the geometric value
 * of the first expression and the geometric value of the second expression spatially overlap.</div>
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="BinarySpatialOperator", specification=ISO_19143)
public interface BinarySpatialOperator<R> extends SpatialOperator<R> {
    /**
     * Returns the nature of the operator. A standard set of spatial operators is equal,
     * disjoin, touches, within, overlaps, crosses, intersects, contains, beyond and BBOX.
     *
     * @return the nature of the operator.
     */
    @Override
    @UML(identifier="operatorType", obligation=MANDATORY, specification=ISO_19143)
    SpatialOperatorName getOperatorType();

    /**
     * Returns the first expression to be evaluated.
     * This is the element at index 0 in the {@linkplain #getExpressions() expressions} list.
     * The returned expression <em>should</em> be an instance of {@link ValueReference}.
     * The {@linkplain Expression#apply expression evaluation} should return one of the following type:
     *
     * <ul>
     *   <li>A geometry instance.</li>
     *   <li>A {@link Feature} instance if the operator shall be applied to the geometric values
     *       of all the spatial properties of the resource.</li>
     * </ul>
     *
     * This {code BinarySpatialOperator} evaluates to {@code true} if all tested spatial property values
     * fulfill the spatial relationship implied by the operator.
     *
     * @return the first expression to be evaluated.
     *
     * @departure generalization
     *   ISO 19143 restricts the type to {@link ValueReference}.
     *   GeoAPI relaxes this restriction by allowing arbitrary expressions.
     */
    @UML(identifier="operand1", obligation=MANDATORY, specification=ISO_19143)
    default Expression<? super R, ?> getOperand1() {
        return getExpressions().get(0);
    }

    /**
     * Returns the second expression to be evaluated.
     * This is the element at index 1 in the {@linkplain #getExpressions() expressions} list.
     * The returned expression should one of the following:
     *
     * <ul>
     *   <li>A {@link ValueReference}.</li>
     *   <li>A {@link Literal} evaluating to a geometry object.</li>
     *   <li>A {@link Literal} evaluating to an {@link Envelope}.</li>
     * </ul>
     *
     * @return the second expression to be evaluated.
     *
     * @departure constraint
     *   ISO 19143 restricts the type to {@code SpatialDescription}, which is a union between
     *   {@code Geometry}, {@code Envelope} and {@code ValueReference}.
     *   Union has no direct equivalence in Java and is replaced by documentation in this method.
     */
    @UML(identifier="operand2", obligation=MANDATORY, specification=ISO_19143)
    default Expression<? super R, ?> getOperand2() {
        return getExpressions().get(1);
    }

    /**
     * Returns the two expressions to be evaluated by this operator.
     * The list shall contain {@linkplain #getOperand1() operand 1}
     * and {@linkplain #getOperand2() operand 2} in that order.
     *
     * @return a list of size 2 containing the two expressions to be evaluated.
     */
    @Override
    List<Expression<? super R, ?>> getExpressions();
}
