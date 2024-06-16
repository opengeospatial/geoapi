/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2021-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.filter;

import java.util.List;
import java.util.Objects;
import java.util.AbstractList;
import java.util.Optional;
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
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
final class FilterExpressions<R> extends AbstractList<Expression<R,?>> {
    /**
     * The filters to view as expressions.
     *
     * @see LogicalOperator#getOperands()
     */
    private final List<Filter<R>> filters;

    /**
     * Creates a new list of expression wrapping the given list of expressions.
     *
     * @param  filters  the filters to view as expressions.
     */
    FilterExpressions(final List<Filter<R>> filters) {
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
    public Expression<R,?> get(final int index) {
        return new Element<>(filters.get(index));
    }

    /**
     * A filter viewed as an expression having a {@code Boolean} return value.
     *
     * @param  <R>  type of resources used as inputs.
     */
    private static final class Element<R> implements Expression<R,Boolean> {
        /**
         * The filter to view as expression.
         */
        private final Filter<R> filter;

        /**
         * Creates a new expression wrapping the given filter.
         *
         * @param  filter  the filter to view as expression.
         */
        Element(final Filter<R> filter) {
            this.filter = Objects.requireNonNull(filter);
        }

        /**
         * Derives a function name from the filter name.
         */
        @Override
        public ScopedName getFunctionName() {
            final CodeList<?> type = filter.getOperatorType();
            final Optional<String> identifier = type.identifier();
            if (identifier.isPresent()) {
                return new Name(Name.STANDARD, identifier.get());
            } else {
                return new Name(Name.EXTENSION, type.name());
            }
        }

        /**
         * Returns the class of resources expected by the wrapped filter.
         */
        @Override
        public Class<? super R> getResourceClass() {
            return filter.getResourceClass();
        }

        /**
         * Returns the expressions used as arguments for the wrapped filter.
         */
        @Override
        public List<Expression<R,?>> getParameters() {
            return filter.getExpressions();
        }

        /**
         * Given an object, determines if the test(s) represented by the wrapped filter are passed.
         */
        @Override
        public Boolean apply(final R input) throws InvalidFilterValueException {
            return filter.test(input);
        }

        /**
         * Returns {@code this} if compatible with specified type, or throws an exception otherwise.
         */
        @Override
        @SuppressWarnings("unchecked")
        public <N> Expression<R,N> toValueType(final Class<N> target) {
            if (target.isAssignableFrom(Boolean.class)) return (Expression<R,N>) this;
            else throw new ClassCastException();
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
