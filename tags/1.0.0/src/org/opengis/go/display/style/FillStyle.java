/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

// J2SE direct dependencies
import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;
import org.opengis.util.SimpleEnumerationType;


/**
 * Indicates the various methods of filling the interior of objects.
 * This class defines static constants for all of the valid choices.
 * Depending on the choice of fill style, the current color, background
 * color, fill pattern, and gradient points may affect the appearance of
 * an object.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public class FillStyle extends SimpleEnumerationType {

    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List VALUES = new ArrayList(5);

    /**
     * This constant indicates that an object should be completely filled
     * with the color.
     */
    public static final FillStyle SOLID = new FillStyle("SOLID", "Fill entirely with the color.");

    /**
     * This constant indicates that an object should not be filled at all.
     */
    public static final FillStyle EMPTY = new FillStyle("EMPTY", "Do not fill.");

    /**
     * This constant indicates that an object should be filled with the
     * fill pattern specified in the <code>GraphicStyle</code>.  The
     * current foreground color is drawn over pixels that correspond to
     * pattern pixels that are set.  Other pixels are left alone.
     * (The term "stippled" is taken from the X-Windows documentation.)
     */
    public static final FillStyle STIPPLED =
        new FillStyle("STIPPLED", "Fill using a transparent, tiled pattern.");

    /**
     * This constant indicates that an object should be filled with the
     * fill pattern specified in the <code>GraphicStyle</code>.  The
     * current foreground color is drawn over pixels that correspond to
     * pattern pixels that are set.  The current background color is used
     * for unset pattern pixels.  (The term "opaque stippled" is taken
     * from the X-Windows documentation.)
     */
    public static final FillStyle OPAQUE_STIPPLED =
        new FillStyle("OPAQUE_STIPPLED", "Fill using an opaque, tiled pattern.");

    /**
     * This constant indicates that an object should be filled with a
     * gradient.  The gradient will be a smooth acyclic transition from the
     * current foreground color to the current background color.  The
     * orientation and size of the gradient are determined by the anchor
     * points <pre>(gradientPoints[0], gradientPoints[1])</pre> and
     * <pre>(gradientPoints[2], gradientPoints[3])</pre> which anchor the
     * foreground and background colors, respectively.
     */
    public static final FillStyle GRADIENT =
            new FillStyle("GRADIENT",
                    "Fill using a smooth transition between the current " +
                    "foreground and background colors.");

    //*************************************************************************
    //  Constructor
    //*************************************************************************

    /**
     * Construct a new FillStyle with the given name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedFillStyles()</code>.
     *
     * @param name a String defining the name of the Arrow pattern.
     * @param description a String describing the pattern.
     */
    protected FillStyle(String name, String description) {
        super(VALUES, name, description);
    }

    /**
     * Returns the list of <code>FillStyle</code>s.
     */
    public static FillStyle[] values() {
        synchronized (VALUES) {
            return (FillStyle[]) VALUES.toArray(new FillStyle[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public CodeList[] family() {
        return values();
    }
}
