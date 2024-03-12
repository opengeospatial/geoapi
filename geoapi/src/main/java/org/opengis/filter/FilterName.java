/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2021-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.geoapi.internal.Vocabulary;
import org.opengis.util.CodeList;


/**
 * Nature of filter for operations other than the public code lists.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Vocabulary(capacity=3)
final class FilterName extends CodeList<FilterName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -320789665042646602L;

    /**
     * Default value for {@link ResourceId#getOperatorType()}.
     */
    static final FilterName RESOURCE_ID = new FilterName("RESOURCE_ID");

    /**
     * Possible value for {@link FilterLiteral#getOperatorType()}.
     */
    static final FilterName INCLUDE = new FilterName("INCLUDE"),
                            EXCLUDE = new FilterName("EXCLUDE");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private FilterName(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code FilterName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static FilterName[] values() {
        return values(FilterName.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public FilterName[] family() {
        return values();
    }
}
