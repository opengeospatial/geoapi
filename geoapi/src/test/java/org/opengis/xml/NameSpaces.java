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
import java.util.HashMap;


/**
 * Information about XML namespaces.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public final class NameSpaces {
    /**
     * Do not allow (for now) instantiation of this class.
     */
    private NameSpaces() {
    }

    /**
     * Returns a mapping from XML prefix to packages (not necessarily Java packages).
     * Keys are usually three-letters name like {@code "cit"} for citations.
     * Values are {@code "module/package"} string, for example {@code "metadata/citation"}.
     * Two keys may map to the same package if GeoAPI decides to merge some packages together.
     *
     * @return a modifiable mapping from XML prefix to package names.
     */
    public static Map<String,String> toPackages() {
        final Map<String,String> m = new HashMap<>(32);
        m.put("gco", "metadata/common");
        m.put("lan", "metadata/language");
        m.put("mcc", "metadata/commonClasses");       // TODO: merge with "common" or "base"?
        m.put("gex", "metadata/extent");
        m.put("cit", "metadata/citation");
        m.put("mmi", "metadata/maintenance");
        m.put("mrd", "metadata/distribution");
        m.put("mdt", "metadata/transfer");            // TODO: merge with "distribution"?
        m.put("mco", "metadata/constraints");
        m.put("mri", "metadata/identification");
        m.put("srv", "metadata/service");
        m.put("mac", "metadata/acquisition");
        m.put("mrc", "metadata/content");
        m.put("mrl", "metadata/lineage");
        m.put("mdq", "metadata/dataQuality");
        m.put("mrs", "metadata/referenceSystem");     // TODO: merge with "spatialRepresentation"?
        m.put("msr", "metadata/spatialRepresentation");
        m.put("mas", "metadata/applicationSchema");
        m.put("mex", "metadata/extension");
        m.put("mpc", "metadata/portrayalCatalog");    // TODO: merge with another module?
        m.put("mdb", "metadata/base");
        return m;
    }
}
