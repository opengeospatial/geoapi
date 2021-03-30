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
import java.util.Collections;
import java.io.Serializable;
import java.io.ObjectStreamException;
import org.opengis.util.CodeList;


/**
 * A filter that produces a constant {@code true} or {@code false} value.
 * This is a placeholder filter intended to be used in data structuring definition.
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class FilterLiteral implements Filter<Object>, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -8211972295113356707L;

    /**
     * The constant returned by {@link Filter#include()}.
     */
    @SuppressWarnings("rawtypes")
    static final Filter INCLUDE = new FilterLiteral(true);

    /**
     * The constant returned by {@link Filter#exclude()}.
     */
    @SuppressWarnings("rawtypes")
    static final Filter EXCLUDE = new FilterLiteral(false);

    /**
     * The constant evaluation result.
     */
    private final boolean value;

    /**
     * Creates a new filter.
     */
    private FilterLiteral(final boolean value) {
        this.value = value;
    }

    /**
     * Returns an identification of this operator.
     */
    @Override
    public CodeList<?> getOperatorType() {
        throw new UnsupportedOperationException();      // TODO
    }

    /**
     * Returns an empty list since this literal depends on no expression.
     */
    @Override
    public List<Expression<? super Object, ?>> getExpressions() {
        return Collections.emptyList();
    }

    /**
     * Returns the constant value.
     */
    @Override
    public boolean test(Object object) {
        return value;
    }

    /**
     * Returns a string representation of this filter.
     */
    @Override
    public String toString() {
        return "Filter." + (value ? "INCLUDE" : "EXCLUDE");
    }

    /**
     * Returns the canonical instance on deserialization.
     */
    private Object readResolve() throws ObjectStreamException {
        return value ? INCLUDE : EXCLUDE;
    }
}
