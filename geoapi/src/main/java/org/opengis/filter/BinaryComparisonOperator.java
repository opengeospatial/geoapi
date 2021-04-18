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

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * An operator that evaluates the mathematical comparison between two arguments.
 * If the arguments satisfy the comparison then the filter evaluates to true.
 * Otherwise the filter evaluates to false.
 *
 * <p>The nature of the comparison is dependent on the {@linkplain #getOperatorType() operator type}.
 * A standard set of comparison operators is equal to, less than, greater than,
 * less than or equal to, greater than or equal to and not equal to.</p>
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <T>  the type of inputs to filter.
 */
@UML(identifier="BinaryComparisonOperator", specification=ISO_19143)
public interface BinaryComparisonOperator<T> extends ComparisonOperator<T> {
    /**
     * Returns the two expressions to be compared by this operator.
     * The expressions can be of any kind.
     *
     * @return a list of size 2 containing the two expressions to be compared.
     */
    @Override
    @UML(identifier="expression", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<? super T, ?>> getExpressions();

    /**
     * Specifies how a filter expression processor should perform string comparisons.
     * A value of {@code true} means that string comparisons shall match case.
     * The value {@code false} means that string comparisons are performed caselessly.
     * The default value {@code true}.
     *
     * @return {@code true} if comparisons are case sensitive, otherwise {@code false}.
     */
    @UML(identifier="matchCase", obligation=OPTIONAL, specification=ISO_19143)
    default boolean isMatchingCase() {
        return true;
    }

    /**
     * Specifies how the comparison predicate shall be evaluated for a collection of values.
     * Values can be {@link MatchAction#ALL ALL} if all values in the collection shall satisfy the predicate,
     * {@link MatchAction#ANY ANY} if any of the value in the collection can satisfy the predicate, or
     * {@link MatchAction#ONE ONE} if only one of the values in the collection shall satisfy the predicate.
     * The default value is {@code ANY}.
     *
     * @return how the comparison predicate shall be evaluated for a collection of values.
     *         Shall not be null.
     */
    @UML(identifier="matchAction", obligation=OPTIONAL, specification=ISO_19143)
    default MatchAction getMatchAction() {
        return MatchAction.ANY;
    }
}
