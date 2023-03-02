/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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
import java.util.ArrayList;
import org.opengis.util.CodeList;


/**
 * Nature of filter for operations other than the public code lists.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class FilterName extends CodeList<FilterName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -320789665042646602L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<FilterName> VALUES = new ArrayList<>(3);

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
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private FilterName(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code FilterName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static FilterName[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(FilterName[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
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
