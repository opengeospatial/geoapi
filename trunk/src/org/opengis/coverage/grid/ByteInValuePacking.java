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

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Specifies the order of the bytes in multi-byte values.
 *
 * @UML codelist GC_ByteInValuePacking
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 *
 * @see GridPacking
 * @see ValueInBytePacking
 * @see java.nio.ByteOrder
 */
public final class ByteInValuePacking extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5830149616089633137L;

    /**
     * Big Endian.
     *
     * @UML conditional GC_wkbXDR
     * @see java.nio.ByteOrder#BIG_ENDIAN
     */
    public static final ByteInValuePacking WKB_XDR = new ByteInValuePacking("WKB_XDR", 0);

    /**
     * Little Endian.
     *
     * @UML conditional GC_wkbNDR
     * @see java.nio.ByteOrder#LITTLE_ENDIAN
     */
    public static final ByteInValuePacking WKB_NDR = new ByteInValuePacking("WKB_NDR", 1);

    /**
     * List of all enumerations of this type.
     */
    private static final ByteInValuePacking[] VALUES = new ByteInValuePacking[] {
            WKB_XDR, WKB_NDR };

    /**
     * Constructs an enum with the given name.
     */
    private ByteInValuePacking(final String name, final int ordinal) {
        super(name, ordinal);
    }

    /**
     * Returns the list of <code>ByteInValuePacking</code>s.
     */
    public static ByteInValuePacking[] values() {
        return (ByteInValuePacking[]) VALUES.clone();
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ByteInValuePacking}*/ CodeList[] family() {
        return values();
    }
}
