/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

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
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 *
 * @see GridPacking
 * @see ByteInValuePacking
 */
public final class ValueInBytePacking extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6895036289489868770L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(2);

    /**
     * Low bit firts (little endian order).
     *
     * @UML conditional GC_LoBitFirst
     */
    public static final ValueInBytePacking LO_BIT_FIRST = new ValueInBytePacking("LO_BIT_FIRST");

    /**
     * High bit first (big endian order).
     *
     * @UML conditional GC_HiBitFirst
     */
    public static final ValueInBytePacking HI_BIT_FIRST = new ValueInBytePacking("HI_BIT_FIRST");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public ValueInBytePacking(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>ValueInBytePacking</code>s.
     */
    public static ValueInBytePacking[] values() {
        synchronized (VALUES) {
            return (ValueInBytePacking[]) VALUES.toArray(new ValueInBytePacking[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ValueInBytePacking}*/ CodeList[] family() {
        return values();
    }
}
