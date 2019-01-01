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
package org.opengis.coverage;

import java.util.List;
import java.util.ArrayList;
import java.awt.image.DataBuffer;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specifies the various dimension types for coverage values.
 * For grid coverages, these correspond to band types.
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
 * @see SampleDimension
 */
@UML(identifier="CV_SampleDimensionType", specification=OGC_01004)
public final class SampleDimensionType extends CodeList<SampleDimensionType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4153433145134818506L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<SampleDimensionType> VALUES = new ArrayList<SampleDimensionType>(11);

    /**
     * Unsigned 1 bit integers.
     *
     * @departure rename
     *   Renamed {@code CV_1BIT} as {@code UNSIGNED_1BIT} since we
     *   drop the prefix, but can't get a name starting with a digit.
     */
    @UML(identifier="CV_1BIT", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType UNSIGNED_1BIT = new SampleDimensionType("UNSIGNED_1BIT");

    /**
     * Unsigned 2 bits integers.
     *
     * @departure rename
     *   Renamed {@code CV_2BIT} as {@code UNSIGNED_2BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     */
    @UML(identifier="CV_2BIT", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType UNSIGNED_2BITS = new SampleDimensionType("UNSIGNED_2BITS");

    /**
     * Unsigned 4 bits integers.
     *
     * @departure rename
     *   Renamed {@code CV_4BIT} as {@code UNSIGNED_4BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     */
    @UML(identifier="CV_4BIT", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType UNSIGNED_4BITS = new SampleDimensionType("UNSIGNED_4BITS");

    /**
     * Unsigned 8 bits integers.
     *
     * @departure rename
     *   Renamed {@code CV_8BIT_U} as {@code UNSIGNED_8BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     *
     * @see #SIGNED_8BITS
     * @see DataBuffer#TYPE_BYTE
     */
    @UML(identifier="CV_8BIT_U", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType UNSIGNED_8BITS = new SampleDimensionType("UNSIGNED_8BITS");

    /**
     * Signed 8 bits integers.
     *
     * @departure rename
     *   Renamed {@code CV_8BIT_S} as {@code SIGNED_8BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     *
     * @see #UNSIGNED_8BITS
     */
    @UML(identifier="CV_8BIT_S", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType SIGNED_8BITS = new SampleDimensionType("SIGNED_8BITS");

    /**
     * Unsigned 16 bits integers.
     *
     * @departure rename
     *   Renamed {@code CV_16BIT_U} as {@code UNSIGNED_16BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     *
     * @see #SIGNED_16BITS
     * @see DataBuffer#TYPE_USHORT
     */
    @UML(identifier="CV_16BIT_U", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType UNSIGNED_16BITS = new SampleDimensionType("UNSIGNED_16BITS");

    /**
     * Signed 16 bits integers.
     *
     * @departure rename
     *   Renamed {@code CV_16BIT_S} as {@code SIGNED_16BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     *
     * @see #UNSIGNED_16BITS
     * @see DataBuffer#TYPE_SHORT
     */
    @UML(identifier="CV_16BIT_S", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType SIGNED_16BITS = new SampleDimensionType("SIGNED_16BITS");

    /**
     * Unsigned 32 bits integers.
     *
     * @departure rename
     *   Renamed {@code CV_32BIT_U} as {@code UNSIGNED_32BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     *
     * @see #SIGNED_32BITS
     */
    @UML(identifier="CV_32BIT_U", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType UNSIGNED_32BITS = new SampleDimensionType("UNSIGNED_32BITS");

    /**
     * Signed 32 bits integers.
     *
     * @departure rename
     *   Renamed {@code CV_32BIT_S} as {@code SIGNED_32BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     *
     * @see #UNSIGNED_32BITS
     * @see DataBuffer#TYPE_INT
     */
    @UML(identifier="CV_32BIT_S", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType SIGNED_32BITS = new SampleDimensionType("SIGNED_32BITS");

    /**
     * Simple precision floating point numbers.
     *
     * @departure rename
     *   Renamed {@code CV_32BIT_REAL} as {@code REAL_32BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     *
     * @see #REAL_64BITS
     * @see DataBuffer#TYPE_FLOAT
     */
    @UML(identifier="CV_32BIT_REAL", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType REAL_32BITS = new SampleDimensionType("REAL_32BITS");

    /**
     * Double precision floating point numbers.
     *
     * @departure rename
     *   Renamed {@code CV_64BIT_REAL} as {@code REAL_64BITS} since we
     *   drop the prefix, but can't get a name starting with a digit.
     *
     * @see #REAL_32BITS
     * @see DataBuffer#TYPE_DOUBLE
     */
    @UML(identifier="CV_64BIT_REAL", obligation=CONDITIONAL, specification=OGC_01004)
    public static final SampleDimensionType REAL_64BITS = new SampleDimensionType("REAL_64BITS");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private SampleDimensionType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code SampleDimensionType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static SampleDimensionType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new SampleDimensionType[VALUES.size()]);
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
    public SampleDimensionType[] family() {
        return values();
    }

    /**
     * Returns the sample dimension type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static SampleDimensionType valueOf(String code) {
        return valueOf(SampleDimensionType.class, code);
    }
}
