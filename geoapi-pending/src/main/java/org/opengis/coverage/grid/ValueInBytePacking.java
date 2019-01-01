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
import org.opengis.coverage.SampleDimensionType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Order of values packed in a byte for sample dimensions with less than 8 bits.
 * This include
 * {@link SampleDimensionType#UNSIGNED_1BIT UNSIGNED_1BIT},
 * {@link SampleDimensionType#UNSIGNED_2BITS UNSIGNED_2BITS} and
 * {@link SampleDimensionType#UNSIGNED_4BITS UNSIGNED_4BITS} data types.
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
 * @see ByteInValuePacking
 *
 * @deprecated In favor of migrating to ISO 19123 definition for Coverage.
 */
@UML(identifier="GC_ValueInBytePacking", specification=OGC_01004)
public final class ValueInBytePacking extends CodeList<ValueInBytePacking> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6895036289489868770L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ValueInBytePacking> VALUES = new ArrayList<ValueInBytePacking>(2);

    /**
     * Low bit first (little endian order).
     */
    @UML(identifier="GC_LoBitFirst", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ValueInBytePacking LO_BIT_FIRST = new ValueInBytePacking("LO_BIT_FIRST");

    /**
     * High bit first (big endian order).
     */
    @UML(identifier="GC_HiBitFirst", obligation=CONDITIONAL, specification=OGC_01004)
    public static final ValueInBytePacking HI_BIT_FIRST = new ValueInBytePacking("HI_BIT_FIRST");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private ValueInBytePacking(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ValueInBytePacking}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static ValueInBytePacking[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new ValueInBytePacking[VALUES.size()]);
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
    public ValueInBytePacking[] family() {
        return values();
    }

    /**
     * Returns the value in byte packing that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static ValueInBytePacking valueOf(String code) {
        return valueOf(ValueInBytePacking.class, code);
    }
}
