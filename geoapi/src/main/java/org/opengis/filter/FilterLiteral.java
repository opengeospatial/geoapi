/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
        return value ? FilterName.INCLUDE : FilterName.EXCLUDE;
    }

    /**
     * Returns an empty list since this literal depends on no expression.
     */
    @Override
    public List<Expression<Object,?>> getExpressions() {
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
