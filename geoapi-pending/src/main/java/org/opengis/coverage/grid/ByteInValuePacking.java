/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage.grid;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specifies the order of the bytes in multi-byte values.
 *
 * <div class="warning"><b>Warning — this class will change</b><br>
 * Current API is derived from OGC <a href="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</a>.
 * We plan to replace it by new interfaces derived from ISO 19123 (<cite>Schema for coverage geometry
 * and functions</cite>). Current interfaces should be considered as legacy and are included in this
 * distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much
 * compatibility as possible, but no migration plan has been determined yet.
 * </div>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 *
 * @see GridPacking
 * @see ValueInBytePacking
 * @see java.nio.ByteOrder
 *
 * @deprecated In favor of migrating to ISO 19123 definition for Coverage.
 */
@UML(identifier="GC_ByteInValuePacking", specification=OGC_01004)
public final class ByteInValuePacking extends CodeList<ByteInValuePacking> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5830149616089633137L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ByteInValuePacking> VALUES = new ArrayList<ByteInValuePacking>(2);

    /**
     * Big Endian.
     *
     * @see java.nio.ByteOrder#BIG_ENDIAN
     */
    @UML(identifier="GC_wkbXDR", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ByteInValuePacking WKB_XDR = new ByteInValuePacking("WKB_XDR");

    /**
     * Little Endian.
     *
     * @see java.nio.ByteOrder#LITTLE_ENDIAN
     */
    @UML(identifier="GC_wkbNDR", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ByteInValuePacking WKB_NDR = new ByteInValuePacking("WKB_NDR");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private ByteInValuePacking(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ByteInValuePacking}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static ByteInValuePacking[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new ByteInValuePacking[VALUES.size()]);
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
    public ByteInValuePacking[] family() {
        return values();
    }

    /**
     * Returns the byte in value packing that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static ByteInValuePacking valueOf(String code) {
        return valueOf(ByteInValuePacking.class, code);
    }
}
