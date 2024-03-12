/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * Localized resources for elements from OGC/ISO standards.
 * This class provides {@link ResourceBundle} or {@link Properties} objects
 * in which keys can have the following patterns:
 *
 * <ul>
 *   <li>{@code <class>}</li>
 *   <li>{@code <class>.<member>}</li>
 * </ul>
 *
 * where {@code <class>} and {@code <member>} are the UML identifiers as specified in the OGC/ISO standards.
 * Those identifiers for GeoAPI interfaces and methods are given by {@link UML#identifier()}.
 *
 * <h2>Example</h2>
 * The {@code "CI_RoleCode.principalInvestigator"} key is associated to the following values:
 *
 * <ul>
 *   <li>{@link Locale#ENGLISH}<ul>
 *     <li><b>{@code CodeList}:</b> <q>Principal investigator</q></li>
 *     <li><b>{@code Description}:</b> <q>Key party responsible for gathering information and conducting research.</q></li>
 *   </ul></li>
 *   <li>{@link Locale#FRENCH}<ul>
 *     <li><b>{@code CodeList}:</b> <q>Maître d’œuvre principal</q></li>
 *     <li><b>{@code Description}:</b> <q>Acteur qui a assuré la réalisation de la ressource,
 *         éventuellement en faisant appel à des co-traitants ou des sous-traitants.</q></li>
 *   </ul></li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ResourceBundles {
    /**
     * Do not allow instantiation of this class.
     */
    private ResourceBundles() {
    }

    /**
     * Returns the localized resources of the specified base name.
     *
     * @param  basename  the base name of the resource bundle.
     * @param  locale    the locale for which a resource bundle is desired, or {@code null} for the default locale.
     * @return localized texts for the specified or default locale.
     */
    private static ResourceBundle resources(final String basename, final Locale locale) {
        return (locale == null)
                ? ResourceBundle.getBundle(basename)
                : ResourceBundle.getBundle(basename, locale);
    }

    /**
     * Returns the localized resources for the code list labels.
     * Keys are {@code <class>} or {@code <class>.<member>} strings where {@code <class>}
     * and {@code <member>} are UML identifiers as specified in the OGC/ISO standards.
     * Values are human-readable texts in the specified locale if available, or in English as a fallback.
     *
     * <h4>Example</h4>
     * {@code codeLists.getString("CI_RoleCode.principalInvestigator")} returns
     * <q>Principal investigator</q> if the specified locale is {@link Locale#ENGLISH}, or
     * <q>Maître d’œuvre principal</q> if the specified locale is {@link Locale#FRENCH}.
     *
     * @param  locale    the locale for which a resource bundle is desired, or {@code null} for the default locale.
     * @return localized texts for the specified or default locale.
     */
    public static ResourceBundle codeLists(final Locale locale) {
        return resources("org.opengis.metadata.CodeLists", locale);
    }

    /**
     * Returns the localized resources for the property descriptions.
     * Keys are {@code <class>} or {@code <class>.<member>} strings where {@code <class>}
     * and {@code <member>} are UML identifiers as specified in the OGC/ISO standards.
     * Values are human-readable texts in the specified locale if available, or in English as a fallback.
     *
     * <h4>Example</h4>
     * {@code descriptions.getString("CI_RoleCode.principalInvestigator")} returns
     * <q>Key party responsible for gathering information and conducting research.</q>
     * if the specified locale is {@link Locale#ENGLISH}, or
     * <q>Acteur qui a assuré la réalisation de la ressource, éventuellement en faisant appel
     * à des co-traitants ou des sous-traitants.</q> if the specified locale is {@link Locale#FRENCH}.
     *
     * @param  locale  the locale for which a resource bundle is desired, or {@code null} for the default locale.
     * @return localized texts for the specified or default locale.
     */
    public static ResourceBundle descriptions(final Locale locale) {
        return resources("org.opengis.metadata.Descriptions", locale);
    }

    /**
     * Returns the mapping from UML identifiers to Java classes.
     * The returned map is the converse of {@link UML#identifier()}.
     *
     * <h4>Examples</h4>
     * {@code classIndex.getString("SC_CRS")} returns
     * {@code "org.opengis.referencing.crs.CoordinateReferenceSystem"}.
     *
     * <h4>Performance note</h4>
     * This method loads the resources in a new {@code Properties} instance every time
     * that this method is invoked, because {@code Properties} is a mutable object.
     * Callers should invoke this method only once and cache the map.
     *
     * @return mapping from UML identifiers to Java classes in a new {@code Properties} instance.
     * @throws IOException if the properties cannot be loaded.
     *
     * @see UML#identifier()
     */
    public static Properties classIndex() throws IOException {
        final Properties classIndex = new Properties(INDEX_CAPACITY);
        try (InputStream in = UML.class.getResourceAsStream("class-index.properties")) {
            classIndex.load(in);
        }
        return classIndex;
    }

    /**
     * Number of lines in the {@code "class-index.properties"} file.
     */
    static final int INDEX_CAPACITY = 247;
}
