/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2015 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing.gigs;

import java.util.Map;
import java.util.HashMap;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.PrimeMeridian;
import static org.junit.Assert.*;


/**
 * Maps some EPSG names to their code value.
 * This map is used when a test case needs some dependencies, and those dependencies
 * are expected to be defined in the EPSG database instead than in a previous test case.
 *
 * <p>Objects defined in a previous test case have a name beginning with {@code "GIGS"},
 * for example {@code "GIGS geodetic datum A"}. All other names are defined in the EPSG
 * database.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class CodeForName {
    /**
     * Mapping from EPSG names to EPSG codes. We use for now the same map instance for
     * all kind of objects since name clash is not yet an issue. This may be revisited
     * in any future version.
     */
    private static final Map<String,CodeForName> MAP = new HashMap<String,CodeForName>(16);
    static {
        add(Ellipsoid.class,     "WGS 84",             7030);
        add(Ellipsoid.class,     "Airy 1830",          7001);
        add(Ellipsoid.class,     "Bessel 1841",        7004);
        add(Ellipsoid.class,     "International 1924", 7022);
        add(Ellipsoid.class,     "GRS 1980",           7019);
        add(Ellipsoid.class,     "Clarke 1880 (IGN)",  7011);
        add(PrimeMeridian.class, "Greenwich",          8901);
        add(PrimeMeridian.class, "Jakarta",            8908);
        add(PrimeMeridian.class, "Paris",              8903);
    }

    /**
     * Adds the given entry in the map. Used by the static initializer only.
     */
    private static void add(final Class<?> type, final String name, final int code) {
        assertNull(name, MAP.put(name, new CodeForName(type, code)));
    }

    /**
     * The type of object for this entry.
     */
    private final Class<?> type;

    /**
     * The EPSG code this entry.
     */
    private final int code;

    /**
     * Creates a new entry. Used by the static initializer only.
     */
    private CodeForName(final Class<?> type, final int code) {
        this.type = type;
        this.code = code;
    }

    /**
     * Returns the EPSG code for the object of the given type and name.
     *
     * @throws IllegalArgumentException If no code is found for the given type and name.
     */
    public static int get(final Class<?> type, final String name) {
        final CodeForName entry = MAP.get(name);
        if (entry != null && entry.type == type) {
            return entry.code;
        }
        throw new IllegalArgumentException(name);
    }
}
