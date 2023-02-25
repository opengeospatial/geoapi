/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.namespace.QName;
import org.opengis.annotation.UML;


/**
 * Mapping from XML prefixes or Java types to programmatic namespaces (modules or packages).
 * There is not necessarily a one-to-one relationship between XML namespaces, Java packages
 * or Python modules. For example, we may merge some XML namespaces in a single programmatic
 * namespace if keeping them separated would result in modules or packages with few classes.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public final class NameSpaces {
    /**
     * Modifiable mapping from XML prefixes to packages (not necessarily Java packages).
     * Keys are usually three-letters prefixes like {@code "cit"} for citations.
     * Values are {@code "module/package"} strings, for example {@code "metadata/citation"}.
     * Two keys may map to the same package if GeoAPI decides to merge some packages together.
     */
    private final Map<String,String> prefixesToPackages;

    /**
     * Modifiable mapping from Java interfaces to packages (not necessarily Java packages).
     * This is used for special cases before to test for {@link #prefixesToPackages}.
     */
    private final Map<Class<?>,String> typesToPackages;

    /**
     * Creates a new mapping from XML namespaces (identified by prefixes) to programmatic namespaces.
     */
    public NameSpaces() {
        final Map<String,String> m = new HashMap<>(32);
        m.put("gco", "metadata/naming");
        m.put("lan", "metadata/language");
        m.put("mcc", "metadata/maintenance");   // Default destination for commonClasses.xsd types not listed in 'typesToPackages'.
        m.put("gex", "metadata/extent");
        m.put("cit", "metadata/citation");
        m.put("mmi", "metadata/maintenance");
        m.put("mrd", "metadata/distribution");
        m.put("mdt", "metadata/distribution");              // "transfer" merged with distribution.
        m.put("mco", "metadata/constraints");
        m.put("mri", "metadata/identification");
        m.put("srv", "metadata/service");
        m.put("mac", "metadata/acquisition");
        m.put("mrc", "metadata/content");
        m.put("mrl", "metadata/lineage");
        m.put("mdq", "metadata/quality");                   // "dataQuality" simplified to "quality"
        m.put("mrs", "metadata/representation");            // "referenceSystem" merged with "spatialRepresentation"
        m.put("msr", "metadata/representation");            // "spatialRepresentation" simplified to "representation"
        m.put("mas", "metadata/extension");                 // "applicationSchema" merged with "extension".
        m.put("mex", "metadata/extension");
        m.put("mpc", "metadata/base");                      // "portrayalCatalog" merged with "base".
        m.put("mdb", "metadata/base");
        prefixesToPackages = m;
        /*
         * Types defined in "commonClasses.xsd". Types not listed below will go to "metadata/maintenance"
         */
        final Map<Class<?>,String> t = new HashMap<>(8);
        t.put(org.opengis.metadata.Identifier.class,                        "metadata/citation");
        t.put(org.opengis.metadata.identification.Progress.class,           "metadata/identification");
        t.put(org.opengis.metadata.identification.BrowseGraphic.class,      "metadata/identification");
        t.put(org.opengis.metadata.spatial.SpatialRepresentationType.class, "metadata/representation");
        /*
         * Types having a different name in "dataQuality.xsd" because we have not yet updated that part.
         */
        t.put(org.opengis.metadata.quality.TemporalAccuracy.class, "metadata/quality");
        typesToPackages = t;
    }

    /**
     * Excludes the namespaces identified by the given prefixes. Calls to {@link #name(Class, Map)}
     * for a type in the namespace identified by one of the given prefixes will return {@code null}.
     *
     * @param  prefixes  identifications of the namespaces to exclude.
     */
    public void exclude(final String... prefixes) {
        for (final String prefix : prefixes) {
            final String ns = prefixesToPackages.put(prefix, null);
            if (ns != null) {
                for (final Iterator<String> it = typesToPackages.values().iterator(); it.hasNext();) {
                    if (ns.equals(it.next())) it.remove();
                }
            }
        }
    }

    /**
     * Returns the OGC/ISO name of the given type together with its XML prefix and pseudo-namespace, or {@code null}.
     * Note that while we use the {@link QName} object for convenience, this is <strong>not</strong> the XML name:
     *
     * <ul>
     *   <li>{@link QName#getLocalPart()} will be the OGC/ISO name,
     *        which is usually the same than the XML local part but not always.</li>
     *   <li>{@link QName#getPrefix()} will be the XML prefix if known, or the UML prefix otherwise.
     *       May be empty is no prefix can be inferred.</li>
     *   <li>{@link QName#getNamespaceURI()} will be the programmatic namespace.
     *       This may be a fragment of the XML namespace but never the full URI.</li>
     * </ul>
     *
     * @param  type        the type for which to get the namespace, or {@code null}.
     * @param  definition  value of {@link SchemaInformation#getTypeDefinition(Class)} for the given {@code type},
     *                     or {@code null} if unknown.
     * @return the OGC/ISO name, prefix and pseudo-namespace for the given type, or {@code null} if none.
     */
    public QName name(final Class<?> type, final Map<String, SchemaInformation.Element> definition) {
        if (type != null) {
            final UML uml = type.getAnnotation(UML.class);
            if (uml != null) {
                String prefix = null;
                if (definition != null) {
                    SchemaInformation.Element def = definition.get(null);
                    if (def != null) {
                        prefix = def.prefix();
                    }
                }
                String typeName = uml.identifier();
                final int splitAt = typeName.indexOf('_');
                if (prefix == null) {
                    if (splitAt > 0) {
                        prefix = typeName.substring(0, splitAt);
                    } else {
                        switch (type.getPackage().getName()) {
                            case "org.opengis.util":    prefix = "gco"; break;
                            case "org.opengis.feature": prefix = "GF";  break;
                            case "org.opengis.filter":
                            case "org.opengis.filter.capability": return null;
                            default: {
                                switch (typeName) {
                                    case "DirectPosition": prefix = "GM"; break;
                                    default: prefix = ""; break;
                                }
                            }
                        }
                    }
                }
                typeName = typeName.substring(splitAt + 1);
                if (!typeName.isEmpty()) {                          // Paranoiac check (should never happen).
                    String pkg = typesToPackages.get(type);
                    if (pkg == null) {
                        pkg = prefixesToPackages.get(prefix);
                        if (pkg == null) {
                            if (prefixesToPackages.containsKey(prefix)) {
                                return null;                        // Type explicitly excluded.
                            }
                            pkg = prefix;
                        }
                    }
                    return new QName(pkg, typeName, prefix);
                }
            }
        }
        return null;
    }

    /**
     * Returns all namespace values that may be returned by {@link #name(Class, Map)}.
     * This method returns a modifiable set. Modifications to the returned set will
     * not affect this {@code NameSpaces} instance.
     *
     * @return all package names known to this {@code NameSpaces} instance.
     */
    public Set<String> packages() {
        final Set<String> pkg = new HashSet<>(prefixesToPackages.values());
        pkg.remove(null);
        return pkg;
    }
}
