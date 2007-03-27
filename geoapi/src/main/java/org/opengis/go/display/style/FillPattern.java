/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

import java.util.ArrayList;
import java.util.List;
import org.opengis.util.CodeList;
import org.opengis.util.SimpleEnumerationType;


/**
 * Indicates the various fill patterns.  This class defines static constants for
 * all of the standard choices.  Depending on the choice of fill style, the
 * current foreground color, background color, fill pattern, and gradient points
 * may affect the appearance of an object.
 *
 * <p>An implementation is not required to implement the entire list of available
 * fill patterns.  At runtime, the list of implemented patterns can be retrieved
 * from a {@link org.opengis.go.display.DisplayCapabilities} object (that was itself
 * retrieved from a <code>Canvas</code>).  An implementation may also implement
 * other patterns not listed here.</p>
 * 
 * <p>NOTE: Two different implementations may have fill patterns with
 * the same name, but use different <code>FillPattern</code> instances
 * (since they may not know about each others name use). If clients
 * need to use the file pattern to find a fill pattern they should
 * search the array returned by <code>getSupportedFillPatterns()</code>
 * from the <code>DisplayCapabilities</code> instance associated with
 * the correct provider.</p>
 *
 * <p>This list was compiled by examining the behavior of various
 * software packages.  Where possible, a reference document is given.
 * For others, a description of the style is given.</p>
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */
public class FillPattern extends SimpleEnumerationType<FillPattern> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6942734861758918827L;

    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List<FillPattern> VALUES = new ArrayList<FillPattern>(11);

    /**
     * No fill pattern.
     */
    public static final FillPattern NONE = new FillPattern("NONE", "No fill.");

    /**
     * Regularly spaced single pixels.
     */
    public static final FillPattern DOTS =
        new FillPattern("DOTS", "Regularly spaced single pixels.");

    /**
     * Parallel vertical lines.
     */
    public static final FillPattern VERTICAL_LINES =
        new FillPattern("VERTICAL_LINES", "Parallel vertical lines.");

    /**
     * Parallel horizontal lines.
     */
    public static final FillPattern HORIZONTAL_LINES =
        new FillPattern("HORIZONTAL_LINES", "Parallel horizontal lines.");

    /**
     * Parallel slanted lines running from upper left to lower right.
     */
    public static final FillPattern NEGATIVE_SLANTS =
        new FillPattern(
            "NEGATIVE_SLANTS",
            "Parallel slanted lines running from upper left to lower right.");

    /**
     * Parallel slanted lines running from lower left to upper right.
     */
    public static final FillPattern POSITIVE_SLANTS =
        new FillPattern(
            "POSITIVE_SLANTS",
            "Parallel slanted lines running from lower left to upper right.");

    /**
     * Small closely spaced '+' marks.
     */
    public static final FillPattern PLUSSES =
        new FillPattern("PLUSSES", "Small closely spaced '+' marks.");

    /**
     * Two series of intersecting parallel lines.
     */
    public static final FillPattern XHATCH =
        new FillPattern("XHATCH", "Two series of intersecting parallel lines.");

    /**
     * 25% fill.  Can be used to simulate transparency.
     */
    public static final FillPattern FILL_25_PERCENT =
        new FillPattern("FILL_25_PERCENT", "25% fill.  Can be used to simulate transparency.");

    /**
     * 50% fill.  Can be used to simulate transparency.
     */
    public static final FillPattern FILL_50_PERCENT =
        new FillPattern("FILL_50_PERCENT", "50% fill.  Can be used to simulate transparency.");

    /**
     * 75% fill.  Can be used to simulate transparency.
     */
    public static final FillPattern FILL_75_PERCENT
        = new FillPattern("FILL_75_PERCENT", "75% fill.  Can be used to simulate transparency.");

    //*************************************************************************
    //  Constructor
    //*************************************************************************

    /**
     * Construct a new FillPattern with the give name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedFillPatterns()</code>.
     *
     * @param name a String defining the name of the fill pattern.
     * @param description a String describing the pattern.
     */
    protected FillPattern(String name, String description) {
        super(VALUES, name, description, loadIconResource(FillPattern.class, name + ".gif"));
    }

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>FillPattern</code>s.
     */
    public static FillPattern[] values() {
        synchronized (VALUES) {
            return (FillPattern[]) VALUES.toArray(new FillPattern[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{FillPattern}*/ CodeList[] family() {
        return values();
    }
}
