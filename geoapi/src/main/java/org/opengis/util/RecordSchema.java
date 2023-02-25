/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.util;

import java.util.Map;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A collection of {@linkplain RecordType record types}. All schemas possess an associated
 * {@linkplain NameSpace name space} within which the {@linkplain RecordType record type}
 * names are defined. A schema is a flat data structure, similar to a Java package.
 *
 * <p>Record schemas do not provide a hierarchical framework within which data types may be organized.
 * {@linkplain NameSpace Name spaces}, however, do define a hierarchical framework for arbitrary
 * named items. Record schemas can participate in this framework by virtue of the fact that they
 * are all identified by {@linkplain LocalName local name} or some subclass.  A schema's location
 * in the hierarchy can be communicated by
 *
 * <code>{@linkplain #getSchemaName()}.{@linkplain LocalName#scope() scope()}.{@linkplain NameSpace#name() name()}</code>.
 * </p>
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 *
 * @deprecated The {@code RecordSchema} interface has been removed in the 2015 revision of ISO 19103 standard.
 */
@Deprecated
@UML(identifier="RecordSchema", specification=ISO_19103, version=2005)
public interface RecordSchema {
    /**
     * Returns the schema name. The {@linkplain LocalName#scope scope} of the schema name is
     * associated with a {@linkplain NameSpace name space} which fixes this schema to a specific
     * location in the type hierarchy.
     *
     * @return the schema name.
     */
    @UML(identifier="schemaName", obligation=MANDATORY, specification=ISO_19103, version=2005)
    LocalName getSchemaName();

    /**
     * Returns the dictionary of all (<var>name</var>, <var>record type</var>) pairs
     * in this schema.
     *
     * @return all (<var>name</var>, <var>record type</var>) pairs in this schema.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19103, version=2005)
    Map<TypeName, RecordType> getDescription();

    /**
     * Looks up the provided type name and returns the associated record type. If the type name is not
     * defined within this schema, then this method returns {@code null}. This is functionally equivalent
     * to <code>{@linkplain #getDescription()}.{@linkplain Map#get get}(name)</code>.
     *
     * @param  name  the name of the type to lookup.
     * @return the type for the given name, or {@code null} if none.
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103, version=2005)
    RecordType locate(TypeName name);
}
