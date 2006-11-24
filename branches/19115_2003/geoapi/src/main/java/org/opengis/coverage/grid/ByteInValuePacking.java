/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specifies the order of the bytes in multi-byte values.
 *
 * <P>&nbsp;</P>
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
 *   <TR><TD>
 *     <P align="justify"><STRONG>WARNING: THIS CLASS WILL CHANGE.</STRONG> Current API is derived from OGC
 *     <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</A>.
 *     We plan to replace it by new interfaces derived from ISO 19123 (<CITE>Schema for coverage geometry
 *     and functions</CITE>). Current interfaces should be considered as legacy and are included in this
 *     distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much 
 *     compatibility as possible, but no migration plan has been determined yet.</P>
 *   </TD></TR>
 * </TABLE>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 * @deprecated In favor of migrating to ISO 19123 definition for Coverage.
 *
 * @see GridPacking
 * @see ValueInBytePacking
 * @see java.nio.ByteOrder
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
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public ByteInValuePacking(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ByteInValuePacking}s.
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
