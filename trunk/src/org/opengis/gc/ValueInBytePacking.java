/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gc;

//J2SE direct dependencies
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

//OpenGIS direct dependencies
import org.opengis.util.CodeList;
import org.opengis.cv.SampleDimensionType; // For Javadoc


/**
 * Order of values packed in a byte for sample dimensions with less than 8 bits.
 * This include
 * {@link SampleDimensionType#UNSIGNED_1BIT UNSIGNED_1BIT},
 * {@link SampleDimensionType#UNSIGNED_2BITS UNSIGNED_2BITS} and
 * {@link SampleDimensionType#UNSIGNED_4BITS UNSIGNED_4BITS} data types.
 *
 * @UML codelist GC_ValueInBytePacking
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.1
 *
 * @see GridPacking
 * @see ByteInValuePacking
 *
 * @revisit Localize. Defines serialVersionUID.
 */
public final class ValueInBytePacking extends CodeList {
    /**
     * Low bit firts (little endian order).
     *
     * @UML conditional GC_LoBitFirst
     */
    public static final ValueInBytePacking LO_BIT_FIRST = new ValueInBytePacking(0, "LO_BIT_FIRST");

    /**
     * High bit first (big endian order).
     *
     * @UML conditional GC_HiBitFirst
     */
    public static final ValueInBytePacking HI_BIT_FIRST = new ValueInBytePacking(1, "HI_BIT_FIRST");

    /**
     * List of all enumerations of this type.
     */
    private static final List VALUES = Collections.unmodifiableList(Arrays.asList(new ValueInBytePacking[]{
            LO_BIT_FIRST, HI_BIT_FIRST}));

    /**
     * Constructs an enum with the given name.
     */
    private ValueInBytePacking(final int ordinal, final String name) {
        super(ordinal, name);
    }

    /**
     * Returns the list of <code>ValueInBytePacking</code>s.
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
