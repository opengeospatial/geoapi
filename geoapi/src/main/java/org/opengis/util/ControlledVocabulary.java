/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import java.util.Optional;


/**
 * Common interface of all {@linkplain Enum enumerations} and {@linkplain CodeList code lists} defined in GeoAPI.
 * Enumerations are <em>closed</em> controlled vocabularies: it is not possible to add new members
 * (except by releasing new versions of the library).
 * By contrast, code lists are <em>open</em> vocabularies:
 * they provide a basic set of members defined at compile-time,
 * but users are free to add new members at runtime.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @departure integration
 *   Provided for allowing developers to handles {@code Enum} and {@code CodeList}
 *   in the same way for some common tasks.
 */
public interface ControlledVocabulary {
    /**
     * Returns the name of this enumeration constant or code list value.
     *
     * @return the name of this enumeration constant or code list value.
     */
    String name();

    /**
     * Returns all the names of this constant. The returned array contains the
     * following elements, with duplicated values and null values removed:
     *
     * <ul>
     *   <li>The programmatic {@linkplain #name() name}</li>
     *   <li>The UML {@linkplain #identifier() identifier}</li>
     *   <li>Any other special case, if any. Examples:
     *     <ul>
     *       <li>The legacy name of {@link org.opengis.metadata.constraint.Restriction#LICENCE}.</li>
     *       <li>American spelling such as "cell center" as an alternative to "cell centre".</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * Those names are typically equal except for the case (programmatic names are upper case
     * while UML names are lower case) and special characters like {@code '-'}.
     *
     * @return all names of this constant. This array is never null and never empty.
     */
    default String[] names() {
        final String name = name();
        final String id = identifier().orElse(null);
        if (id != null && !id.equals(name)) {
            return new String[] {name, id};
        } else {
            return new String[] {name};
        }
    }

    /**
     * Returns the identifier declared in the {@link org.opengis.annotation.UML} annotation.
     * The UML identifier shall be the ISO or OGC name for this enumeration or code list constant.
     *
     * @return the ISO/OGC identifier for this constant.
     */
    default Optional<String> identifier() {
        return Optional.empty();
    }

    /**
     * Returns the ordinal of this constant. This is its position in its elements declaration,
     * where the initial constant is assigned an ordinal of zero.
     *
     * @return the position of this constants in elements declaration.
     */
    int ordinal();

    /**
     * Returns the enumeration or list of codes of the same kind as this item.
     * Invoking this method gives identical results than invoking the static {@code values()} methods
     * provided in {@code Enum} and {@code CodeList} subclasses, except that {@code family()} does not
     * require the class to be known at compile-time — provided that at leat one instance of the family
     * is available.
     *
     * @return the enumeration or list of codes of the same kind as this item.
     */
    ControlledVocabulary[] family();
}
