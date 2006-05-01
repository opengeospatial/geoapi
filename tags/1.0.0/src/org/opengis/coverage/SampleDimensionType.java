/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;
import java.awt.image.DataBuffer; // For Javadoc

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Specifies the various dimension types for coverage values.
 * For grid coverages, these correspond to band types.
 *
 * @UML codelist CV_SampleDimensionType
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 *
 * @see SampleDimension
 */
public final class SampleDimensionType extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4153433145134818506L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(11);

    /**
     * Unsigned 1 bit integers.
     *
     * @UML conditional CV_1BIT
     * @rename Renamed <code>CV_1BIT</code> as <code>UNSIGNED_1BIT</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     */
    public static final SampleDimensionType UNSIGNED_1BIT = new SampleDimensionType("UNSIGNED_1BIT");

    /**
     * Unsigned 2 bits integers.
     *
     * @UML conditional CV_2BIT
     * @rename Renamed <code>CV_2BIT</code> as <code>UNSIGNED_2BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     */
    public static final SampleDimensionType UNSIGNED_2BITS = new SampleDimensionType("UNSIGNED_2BITS");

    /**
     * Unsigned 4 bits integers.
     *
     * @UML conditional CV_4BIT
     * @rename Renamed <code>CV_4BIT</code> as <code>UNSIGNED_4BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     */
    public static final SampleDimensionType UNSIGNED_4BITS = new SampleDimensionType("UNSIGNED_4BITS");

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
    public static final SampleDimensionType UNSIGNED_8BITS = new SampleDimensionType("UNSIGNED_8BITS");

    /**
     * Signed 8 bits integers.
     *
     * @UML conditional CV_8BIT_S
     * @rename Renamed <code>CV_8BIT_S</code> as <code>SIGNED_8BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #UNSIGNED_8BITS
     */
    public static final SampleDimensionType SIGNED_8BITS = new SampleDimensionType("SIGNED_8BITS");

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
    public static final SampleDimensionType UNSIGNED_16BITS = new SampleDimensionType("UNSIGNED_16BITS");

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
    public static final SampleDimensionType SIGNED_16BITS = new SampleDimensionType("SIGNED_16BITS");

    /**
     * Unsigned 32 bits integers.
     *
     * @UML conditional CV_32BIT_U
     * @rename Renamed <code>CV_32BIT_U</code> as <code>UNSIGNED_32BITS</code> since we
     *         drop the prefix, but can't get a name starting with a digit.
     *
     * @see #SIGNED_32BITS
     */
    public static final SampleDimensionType UNSIGNED_32BITS = new SampleDimensionType("UNSIGNED_32BITS");

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
    public static final SampleDimensionType SIGNED_32BITS = new SampleDimensionType("SIGNED_32BITS");

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
    public static final SampleDimensionType REAL_32BITS = new SampleDimensionType("REAL_32BITS");

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
    public static final SampleDimensionType REAL_64BITS = new SampleDimensionType("REAL_64BITS");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public SampleDimensionType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>SampleDimensionType</code>s.
     */
    public static SampleDimensionType[] values() {
        synchronized (VALUES) {
            return (SampleDimensionType[]) VALUES.toArray(new SampleDimensionType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{SampleDimensionType}*/ CodeList[] family() {
        return values();
    }
}
