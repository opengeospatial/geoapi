/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cv;

//J2SE direct dependencies
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.awt.image.DataBuffer; // For Javadoc

//OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Specifies the various dimension types for coverage values.
 * For grid coverages, these correspond to band types.
 *
 * @UML codelist CV_SampleDimensionType
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @revisit Localize. Defines serialVersionUID.
 */
public final class SampleDimensionType extends CodeList {
    /**
     * Unsigned 1 bit integers.
     *
     * @UML conditional CV_1BIT
     * @rename Renamed <code>CV_1BIT</code> as <code>UNSIGNED_1BIT</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     */
    public static final SampleDimensionType UNSIGNED_1BIT = new SampleDimensionType(0, "UNSIGNED_1BIT");

    /**
     * Unsigned 2 bits integers.
     *
     * @UML conditional CV_2BIT
     * @rename Renamed <code>CV_2BIT</code> as <code>UNSIGNED_2BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     */
    public static final SampleDimensionType UNSIGNED_2BITS = new SampleDimensionType(1, "UNSIGNED_2BITS");

    /**
     * Unsigned 4 bits integers.
     *
     * @UML conditional CV_4BIT
     * @rename Renamed <code>CV_4BIT</code> as <code>UNSIGNED_4BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     */
    public static final SampleDimensionType UNSIGNED_4BITS = new SampleDimensionType(2, "UNSIGNED_4BITS");

    /**
     * Unsigned 8 bits integers.
     *
     * @UML conditional CV_8BIT_U
     * @rename Renamed <code>CV_8BIT_U</code> as <code>UNSIGNED_8BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #SIGNED_8BITS
     * @see DataBuffer#TYPE_BYTE
     */
    public static final SampleDimensionType UNSIGNED_8BITS = new SampleDimensionType(3, "UNSIGNED_8BITS");

    /**
     * Signed 8 bits integers.
     *
     * @UML conditional CV_8BIT_S
     * @rename Renamed <code>CV_8BIT_S</code> as <code>SIGNED_8BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #UNSIGNED_8BITS
     */
    public static final SampleDimensionType SIGNED_8BITS = new SampleDimensionType(4, "SIGNED_8BITS");

    /**
     * Unsigned 16 bits integers.
     *
     * @UML conditional CV_16BIT_U
     * @rename Renamed <code>CV_16BIT_U</code> as <code>UNSIGNED_16BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #SIGNED_16BITS
     * @see DataBuffer#TYPE_USHORT
     */
    public static final SampleDimensionType UNSIGNED_16BITS = new SampleDimensionType(5, "UNSIGNED_16BITS");

    /**
     * Signed 16 bits integers.
     *
     * @UML conditional CV_16BIT_S
     * @rename Renamed <code>CV_16BIT_S</code> as <code>SIGNED_16BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #UNSIGNED_16BITS
     * @see DataBuffer#TYPE_SHORT
     */
    public static final SampleDimensionType SIGNED_16BITS = new SampleDimensionType(6, "SIGNED_16BITS");

    /**
     * Unsigned 32 bits integers.
     *
     * @UML conditional CV_32BIT_U
     * @rename Renamed <code>CV_32BIT_U</code> as <code>UNSIGNED_32BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #SIGNED_32BITS
     */
    public static final SampleDimensionType UNSIGNED_32BITS = new SampleDimensionType(7, "UNSIGNED_32BITS");

    /**
     * Signed 32 bits integers.
     *
     * @UML conditional CV_32BIT_S
     * @rename Renamed <code>CV_32BIT_S</code> as <code>SIGNED_32BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #UNSIGNED_32BITS
     * @see DataBuffer#TYPE_INT
     */
    public static final SampleDimensionType SIGNED_32BITS = new SampleDimensionType(8, "SIGNED_32BITS");

    /**
     * Simple precision floating point numbers.
     *
     * @UML conditional CV_32BIT_REAL
     * @rename Renamed <code>CV_32BIT_REAL</code> as <code>REAL_32BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #REAL_64BITS
     * @see DataBuffer#TYPE_FLOAT
     */
    public static final SampleDimensionType REAL_32BITS = new SampleDimensionType(9, "REAL_32BITS");

    /**
     * Double precision floating point numbers.
     *
     * @UML conditional CV_64BIT_REAL
     * @rename Renamed <code>CV_64BIT_REAL</code> as <code>REAL_64BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #REAL_32BITS
     * @see DataBuffer#TYPE_DOUBLE
     */
    public static final SampleDimensionType REAL_64BITS = new SampleDimensionType(10, "REAL_64BITS");

    /**
     * List of all enumerations of this type.
     */
    private static final List VALUES = Collections.unmodifiableList(Arrays.asList(new SampleDimensionType[]{
            UNSIGNED_1BIT, UNSIGNED_2BITS, UNSIGNED_4BITS, UNSIGNED_8BITS,
            SIGNED_8BITS, UNSIGNED_16BITS, SIGNED_16BITS, UNSIGNED_32BITS,
            SIGNED_32BITS, REAL_32BITS, REAL_64BITS}));

    /**
     * Constructs an enum with the given name.
     */
    private SampleDimensionType(final int ordinal, final String name) {
        super(ordinal, name);
    }

    /**
     * Returns the list of <code>SampleDimensionType</code>s.
     */
    public static List values() {
        return VALUES;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public List family() {
        return VALUES;
    }
}
