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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A name that references either an attribute slot in a record, an attribute, operation,
 * or association role in an object instance or a type description in some schema.
 * In a {@code Record} containing an arbitrary number of attributes:
 *
 * <ul>
 *   <li>{@code MemberName} is the name of an attribute. It is similar the name of a field in a Java class.</li>
 *   <li>{@link TypeName} is the name of the attribute definition.
 *       It is similar to the name of a {@link Class}.</li>
 * </ul>
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 */
@UML(identifier="MemberName", specification=ISO_19103)
public interface MemberName extends LocalName {
    /**
     * Returns the type of the data associated with the member or record field.
     *
     * <h4>Comparison with the Java language</h4>
     * {@code MemberName} is similar to the name of a field in a Java class,
     * while the returned {@code TypeName} is similar to the name of the
     * Java {@link Class} used for representing a value in this field.
     *
     * @return the type of the data associated with the member.
     */
    @UML(identifier="attributeType", obligation=MANDATORY, specification=ISO_19103)
    TypeName getAttributeType();

    /**
     * Returns the name of the member.
     * Member names typically use a {@code '.'} navigation separator,
     * so that their {@linkplain #toFullyQualifiedName() fully qualified name} is of the form
     * {@code "[schema].[type].[member]"}.
     */
    @Override
    @UML(identifier="aName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
