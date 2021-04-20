/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2019-2021 Open Geospatial Consortium, Inc.
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

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Operator that determines whether its time arguments satisfy the stated spatial relationship.
 * The operator {@linkplain #test evaluates} to {@code true} if the spatial relationship is satisfied.
 * Otherwise, the operator evaluates to {@code false}.
 * The nature of the comparison is dependent on the {@linkplain #getOperatorType() operator type}.
 *
 * <h2>Exceptions</h2>
 * If any input value of {@code TemporalPosition} is indeterminate, an exception shall be raised.
 *
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="TemporalOperator", specification=ISO_19143)
public interface TemporalOperator<R> extends Filter<R> {
    /**
     * Returns the nature of the operator.
     *
     * @return the nature of the operator.
     */
    @Override
    TemporalOperatorName getOperatorType();

    /**
     * Returns the two expressions to be evaluated by this operator.
     * The list content is as below:
     *
     * <ol>
     *   <li>The first expression should be a {@link ValueReference} evaluating to a temporal object.
     *   </li><li>
     *     The second expression should be a {@link ValueReference} or a {@linkplain Literal literal}
     *     evaluating to a temporal object.
     *   </li>
     * </ol>
     *
     * @return a list of size 2 containing the two expressions to be evaluated.
     *
     * @departure constraint
     *   ISO 19143 uses two properties, named {@code operand1} and {@code operand2},
     *   of type {@code ValueReference} and {@code TemporalOperand} respectively.
     *   The later is an union of {@code TM_Object} and {@code ValueReference},
     *   which has no direct equivalence in Java.
     *   The union purpose is replaced by documentation in this method.
     *   The two values are put in a list for retrofitting in {@link Filter#getExpressions()}.
     */
    @Override
    @UML(identifier="operand1, operand2", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<? super R, ?>> getExpressions();
}
