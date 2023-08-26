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

import org.opengis.util.ControlledVocabulary;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Captures the {@link SortBy} order, ascending or descending.
 *
 * @author  Jody Garnett (Refractions Research)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SortOrder", specification=ISO_19143)
public enum SortOrder implements ControlledVocabulary {
    /**
     * Indicates that an ascending sort, in the collation sequence of the underlying data store, is performed.
     * This is the {@link SortProperty} default value.
     */
    @UML(identifier="ascending", obligation=CONDITIONAL, specification=ISO_19143)
    ASCENDING("ascending", "ASC"),

    /**
     * Indicates that a descending sort, in the collation sequence of the filter expression processor, is performed.
     */
    @UML(identifier="descending", obligation=CONDITIONAL, specification=ISO_19143)
    DESCENDING("descending", "DESC");

    /**
     * The UML identifier.
     */
    private final String identifier;

    /**
     * The name used in SQL statements.
     */
    private final String sql;

    /**
     * Creates a new constant with the given UML identifier.
     *
     * @param identifier  the UML identifier.
     * @param sql  name used in SQL statements.
     */
    private SortOrder(final String identifier, final String sql) {
        this.identifier = identifier;
        this.sql = sql;
    }

    /**
     * Returns the UML identifier for this enumeration constant.
     */
    @Override
    public String identifier() {
        return identifier;
    }

    /**
     * Returns the name used in SQL statements.
     * This is {@code "ASC"} or {@code "DESC"}.
     *
     * @return name used in SQL statements.
     */
    public String toSQL() {
        return sql;
    }

    /**
     * Returns the programmatic name of this constant together with its {@linkplain #identifier() identifier}
     * and SQL name.
     */
    @Override
    public String[] names() {
        return new String[] {name(), identifier, sql};
    }

    /**
     * Returns all constants defined by this enumeration type.
     * Invoking this method is equivalent to invoking {@link #values()}, except that this
     * method can be invoked on an instance of the {@code ControlledVocabulary} interface
     * (i.e. the enumeration type does not need to be known at compile-time).
     *
     * @return all {@linkplain #values() values} for this enumeration.
     */
    @Override
    public SortOrder[] family() {
        return values();
    }
}
