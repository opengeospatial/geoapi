/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.xml;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Mapping from XML prefixes or Java types to programmatic namespaces (modules or packages).
 * There is not necessarily a one-to-one relationship between XML namespaces, Java packages
 * or Python modules. For example we may merge some XML namespaces in a single programmatic
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
        m.put("mdq", "metadata/dataQuality");
        m.put("mrs", "metadata/spatialRepresentation");     // "referenceSystem" merged with spatialRepresentation
        m.put("msr", "metadata/spatialRepresentation");
        m.put("mas", "metadata/applicationSchema");
        m.put("mex", "metadata/applicationSchema");         // "extension" merged with application schema.
        m.put("mpc", "metadata/portrayalCatalog");    // TODO: merge with another module?
        m.put("mdb", "metadata/base");
        prefixesToPackages = m;
        /*
         * Types defined in "commonClasses.xsd". Types not listed below will go to "metadata/maintenance"
         */
        final Map<Class<?>,String> t = new HashMap<>(8);
        t.put(org.opengis.metadata.Identifier.class,                        "metadata/identification");
        t.put(org.opengis.metadata.identification.Progress.class,           "metadata/identification");
        t.put(org.opengis.metadata.identification.BrowseGraphic.class,      "metadata/identification");
        t.put(org.opengis.metadata.spatial.SpatialRepresentationType.class, "metadata/spatialRepresentation");
        typesToPackages = t;
    }

    /**
     * Returns the proposed module or package for the given type.
     * The {@code prefix} argument is usually a three-letters prefix like {@code "cit"} for citations.
     * Return values are {@code "module/package"} strings, for example {@code "metadata/citation"}.
     *
     * @param  prefix  prefix of the XML namespace (e.g. {@code "cit"} for citations.
     * @param  type    GeoAPI interface (e.g. {@link org.opengis.metadata.citation.Citations}).
     * @return the proposed namespace, or {@code null} if unknown.
     */
    public String toPackage(final String prefix, final Class<?> type) {
        String pkg = typesToPackages.get(type);
        if (pkg == null) {
            pkg = prefixesToPackages.get(prefix);
        }
        return pkg;
    }

    /**
     * Returns all values that may be returned by {@link #toPackage(String, Class)}.
     * This method returns a modifiable set. Modification to the returned set will
     * not affect this {@code NameSpaces} instance.
     *
     * @return all package names known to this {@code NameSpaces} instance.
     */
    public Set<String> packages() {
        return new HashSet<>(prefixesToPackages.values());
    }
}
