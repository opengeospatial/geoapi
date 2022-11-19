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
import java.util.Optional;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * An operator that evaluates if the value of an expression is nil.
 * The difference with {@link NullOperator} is that a value should exist
 * but cannot be provided for the reason given by {@link #getNilReason()}.
 * A nil value is not necessarily {@code null}; implementations are free to use placeholders.
 *
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@UML(identifier="NilOperator", specification=ISO_19143)
public interface NilOperator<R> extends ComparisonOperator<R> {
    /**
     * Returns the nature of the comparison.
     * The default implementation returns {@code PROPERTY_IS_NIL}.
     *
     * @return the nature of the comparison.
     */
    @Override
    default ComparisonOperatorName getOperatorType() {
        return ComparisonOperatorName.PROPERTY_IS_NIL;
    }

    /**
     * Returns the expression whose value will be checked for nil.
     *
     * @return a list of size 1 containing the expression to test for nil value.
     */
    @Override
    @UML(identifier="expression", obligation=MANDATORY, specification=ISO_19143)
    List<Expression<? super R, ?>> getExpressions();

    /**
     * Returns the reason why the value is nil.
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
     * @return the reason why the value is nil, or empty for any reason.
     */
    @UML(identifier="nilReason", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<String> getNilReason() {
        return Optional.empty();
    }
}
