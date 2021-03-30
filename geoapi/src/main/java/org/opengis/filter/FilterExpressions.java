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
import java.util.Objects;
import java.util.AbstractList;
import org.opengis.util.CodeList;
import org.opengis.util.ScopedName;


/**
 * List of filters viewed as a list of expressions.
 * This is a default implementation of {@link LogicalOperator#getExpressions()}
 * which redirects all evaluations to {@link LogicalOperator#getOperands()}.
 * This class computes nothing by itself; it is only a bridge between two representations.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <T>  the type of inputs to filter.
 */
final class FilterExpressions<T> extends AbstractList<Expression<? super T, ?>> {
    /**
     * The filters to view as expressions.
     *
     * @see LogicalOperator#getOperands()
     */
    private final List<Filter<? super T>> filters;

    /**
     * Creates a new list of expression wrapping the given list of expressions.
     */
    FilterExpressions(final List<Filter<? super T>> filters) {
        this.filters = Objects.requireNonNull(filters);
    }

    /**
     * Returns {@code true} if the wrapped filter list is empty.
     */
    @Override
    public boolean isEmpty() {
        return filters.isEmpty();
    }

    /**
     * Returns the size of the wrapped filter list.
     */
    @Override
    public int size() {
        return filters.size();
    }

    /**
     * Returns the expression wrapper for the filter at the given index.
     */
    @Override
    public Expression<? super T, ?> get(final int index) {
        return new Element<>(filters.get(index));
    }

    /**
     * A filter viewed as an expression having a {@code Boolean} return value.
     */
    private static final class Element<T> implements Expression<T,Boolean> {
        /**
         * The filter to view as expression.
         */
        private final Filter<T> filter;

        /**
         * Creates a new expression wrapping the given filter.
         */
        Element(final Filter<T> filter) {
            this.filter = Objects.requireNonNull(filter);
        }

        /**
         * Derives a function name from the filter name.
         */
        @Override
        public ScopedName getFunctionName() {
            final CodeList<?> type = filter.getOperatorType();
            final String identifier = type.identifier();
            if (identifier != null) {
                return new Name(Name.STANDARD, identifier);
            } else {
                return new Name(Name.EXTENSION, type.name());
            }
        }

        /**
         * Returns the expressions used as arguments for the wrapped filter.
         */
        @Override
        public List<Expression<? super T, ?>> getParameters() {
            return filter.getExpressions();
        }

        /**
         * Given an object, determines if the test(s) represented by the wrapped filter are passed.
         */
        @Override
        public Boolean apply(final T input) throws InvalidFilterValueException {
            return filter.test(input);
        }

        /**
         * Returns a hash code value derived from the filter hash code.
         */
        @Override
        public int hashCode() {
            return ~filter.hashCode();
        }

        /**
         * Returns {@code true} if the given expression wraps a
         * filter equals to the one wrapped by this expression.
         */
        @Override
        public boolean equals(final Object obj) {
            return (obj instanceof Element) && filter.equals(((Element) obj).filter);
        }

        /**
         * Returns a string representation of this expression for debugging purposes.
         */
        @Override
        public String toString() {
            return "Expression[" + filter.toString() + ']';
        }
    }
}
