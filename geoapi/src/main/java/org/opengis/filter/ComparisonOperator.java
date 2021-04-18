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

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * An operator that evaluates the mathematical comparison between arguments.
 * If the arguments satisfy the comparison then the operator {@linkplain #test evaluates} to {@code true}.
 * Otherwise the operator evaluates to {@code false}.
 *
 * <p>The arguments are given as {@linkplain #getExpressions() expressions}.
 * The number of expressions depends on the sub-type:</p>
 * <ul>
 *   <li>{@link NullOperator} and {@link NilOperator} except one expression.</li>
 *   <li>{@link BinaryComparisonOperator} and {@link LikeOperator} expect two expressions.</li>
 *   <li>{@link BetweenComparisonOperator} expects three expressions.</li>
 * </ul>
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <T>  the type of inputs to filter.
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="ComparisonOperator", specification=ISO_19143)
public interface ComparisonOperator<T> extends Filter<T> {
    /**
     * Returns the nature of the comparison. A standard set of comparison operators is equal to,
     * less than, greater than, less than or equal to, greater than or equal to and not equal to.
     *
     * @return the nature of the comparison.
     */
    @Override
    @UML(identifier="BinaryComparisonOperator.operatorType", obligation=MANDATORY, specification=ISO_19143)
    ComparisonOperatorName getOperatorType();
}
