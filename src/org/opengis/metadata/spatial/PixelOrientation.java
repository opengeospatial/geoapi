/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Point in a pixel corresponding to the Earth location of the pixel.
 *
 * @UML codelist MD_PixelOrientationCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public final class PixelOrientation extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7885677198357949308L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(5);

    /**
     * Point in a pixel corresponding to the Earth location of the pixel.
     *
     * @UML conditional center
     */
    public static final CellGeometry CENTER = new CellGeometry("CENTER");

    /**
     * The corner in the pixel closest to the origin of the SRS; if two are at the same
     * distance from the origin, the one with the smallest x-value.
     *
     * @UML conditional lowerLeft
     */
    public static final CellGeometry LOWER_LEFT = new CellGeometry("LOWER_LEFT");

    /**
     * Next corner counterclockwise from the lower left.
     *
     * @UML conditional lowerRight
     */
    public static final CellGeometry LOWER_RIGHT = new CellGeometry("LOWER_RIGHT");

    /**
     * Next corner counterclockwise from the lower right.
     *
     * @UML conditional upperRight
     */
    public static final CellGeometry UPPER_RIGHT = new CellGeometry("UPPER_RIGHT");

    /**
     * Next corner counterclockwise from the upper right.
     *
     * @UML conditional upperLeft
     */
    public static final CellGeometry UPPER_LEFT = new CellGeometry("UPPER_LEFT");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public PixelOrientation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>CellGeometry</code>s.
     */
    public static CellGeometry[] values() {
        synchronized (VALUES) {
            return (CellGeometry[]) VALUES.toArray(new CellGeometry[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{CellGeometry}*/ CodeList[] family() {
        return values();
    }
}
