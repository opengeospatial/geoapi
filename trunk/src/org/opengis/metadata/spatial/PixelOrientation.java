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

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Point in a pixel corresponding to the Earth location of the pixel.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_PixelOrientationCode")
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
     */
/// @UML (identifier="center", obligation=CONDITIONAL)
    public static final PixelOrientation CENTER = new PixelOrientation("CENTER");

    /**
     * The corner in the pixel closest to the origin of the SRS; if two are at the same
     * distance from the origin, the one with the smallest x-value.
     */
/// @UML (identifier="lowerLeft", obligation=CONDITIONAL)
    public static final PixelOrientation LOWER_LEFT = new PixelOrientation("LOWER_LEFT");

    /**
     * Next corner counterclockwise from the lower left.
     */
/// @UML (identifier="lowerRight", obligation=CONDITIONAL)
    public static final PixelOrientation LOWER_RIGHT = new PixelOrientation("LOWER_RIGHT");

    /**
     * Next corner counterclockwise from the lower right.
     */
/// @UML (identifier="upperRight", obligation=CONDITIONAL)
    public static final PixelOrientation UPPER_RIGHT = new PixelOrientation("UPPER_RIGHT");

    /**
     * Next corner counterclockwise from the upper right.
     */
/// @UML (identifier="upperLeft", obligation=CONDITIONAL)
    public static final PixelOrientation UPPER_LEFT = new PixelOrientation("UPPER_LEFT");

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
     * Returns the list of <code>PixelOrientation</code>s.
     */
    public static PixelOrientation[] values() {
        synchronized (VALUES) {
            return (PixelOrientation[]) VALUES.toArray(new PixelOrientation[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{PixelOrientation}*/ CodeList[] family() {
        return values();
    }
}
