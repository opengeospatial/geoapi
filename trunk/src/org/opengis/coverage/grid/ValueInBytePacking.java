/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

//OpenGIS direct dependencies
import org.opengis.util.CodeList;
import org.opengis.coverage.SampleDimensionType; // For Javadoc
 

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
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6895036289489868770L;

    /**
     * Low bit firts (little endian order).
     *
     * @UML conditional GC_LoBitFirst
     */
    public static final ValueInBytePacking LO_BIT_FIRST = new ValueInBytePacking("LO_BIT_FIRST", 0);

    /**
     * High bit first (big endian order).
     *
     * @UML conditional GC_HiBitFirst
     */
    public static final ValueInBytePacking HI_BIT_FIRST = new ValueInBytePacking("HI_BIT_FIRST", 1);

    /**
     * List of all enumerations of this type.
     */
    private static final ValueInBytePacking[] VALUES = new ValueInBytePacking[] {
            LO_BIT_FIRST, HI_BIT_FIRST };

    /**
     * Constructs an enum with the given name.
     */
    private ValueInBytePacking(final String name, final int ordinal) {
        super(name, ordinal);
    }

    /**
     * Returns the list of <code>ValueInBytePacking</code>s.
     */
    public static ValueInBytePacking[] values() {
        return (ValueInBytePacking[]) VALUES.clone();
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ValueInBytePacking}*/ CodeList[] family() {
        return values();
    }
}
