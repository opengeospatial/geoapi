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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * A compact way of encoding a range check.
 * The lower and upper boundary values are inclusive.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <T>  the type of inputs to filter.
 */
@UML(identifier="BetweenComparisonOperator", specification=ISO_19143)
public interface BetweenComparisonOperator<T> extends ComparisonOperator<T> {
    /**
     * Returns the expression to be compared by this operator, together with boundaries.
     * The expressions can be of any kind.
     *
     * @return a list of size 3 containing the expression to be compared,
     *         the lower boundary and the upper boundary in that order.
     *
     * @departure generalization
     *   This is defined as a singleton in the ISO standard. GeoAPI completes with lower
     *   and upper bounds for compliance with {@link Filter#getExpressions()} contract.
     */
    @Override
    @UML(identifier="expression", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<? super T, ?>> getExpressions();

    /**
     * Returns the lower bound (inclusive) an an expression.
     * This is element at index 1 in the {@linkplain #getExpressions() expressions} list.
     *
     * @return the lower bound, inclusive.
     */
    @UML(identifier="lowerBoundary", obligation=MANDATORY, specification=ISO_19143)
    default Expression<? super T, ?> getLowerBoundary() {
        return getExpressions().get(1);
    }

    /**
     * Returns the upper bound (inclusive) as an expression.
     * This is element at index 2 in the {@linkplain #getExpressions() expressions} list.
     *
     * @return the upper bound, inclusive.
     */
    @UML(identifier="upperBoundary", obligation=MANDATORY, specification=ISO_19143)
    default Expression<? super T, ?> getUpperBoundary() {
        return getExpressions().get(2);
    }
}
