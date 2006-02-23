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

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

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
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(2);

    /**
     * Big Endian.
     *
     * @UML conditional GC_wkbXDR
     * @see java.nio.ByteOrder#BIG_ENDIAN
     */
    public static final ByteInValuePacking WKB_XDR = new ByteInValuePacking("WKB_XDR");

    /**
     * Little Endian.
     *
     * @UML conditional GC_wkbNDR
     * @see java.nio.ByteOrder#LITTLE_ENDIAN
     */
    public static final ByteInValuePacking WKB_NDR = new ByteInValuePacking("WKB_NDR");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public ByteInValuePacking(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>ByteInValuePacking</code>s.
     */
    public static ByteInValuePacking[] values() {
        synchronized (VALUES) {
            return (ByteInValuePacking[]) VALUES.toArray(new ByteInValuePacking[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ByteInValuePacking}*/ CodeList[] family() {
        return values();
    }
}
