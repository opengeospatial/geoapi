/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.style;

import org.opengis.go.util.SimpleEnumerationType;

/**
 * Instances of <code>FillPattern</code> indicate the various fill
 * patterns.  This class defines static constants for all of the
 * standard choices.  Depending on the choice of fill style, the
 * current foreground color, background color, fill pattern, and
 * gradient points may affect the appearance of an object.
 *
 * <p>An implementation is not required to implement the
 * entire list of available fill patterns.  At runtime, the list of
 * implemented patterns can be retrieved from a display Capabilities
 * object (that was itself retrieved from a Canvas). 
 * An implementation may also implement other
 * patterns not listed here.</p>
 * 
 * <p>The integer constant returned from nextValue() is only a
 * convenience for the implemetor, allowing the use of array indexing
 * on integers. Implementators can build an array with sparse entries
 * for the fill patterns the implementation supports.</p>
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
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class FillPattern extends SimpleEnumerationType {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************

    /**
     * No fill pattern.
     */
    public static final FillPattern NONE
        = new FillPattern("NONE", "No fill.");
    /**
     * Regularly spaced single pixels.
     */
    public static final FillPattern DOTS
        = new FillPattern("DOTS", "Regularly spaced single pixels.");
    /**
     * Parallel vertical lines.
     */
    public static final FillPattern VERTICAL_LINES
        = new FillPattern("VERTICAL_LINES", "Parallel vertical lines.");
    /**
     * Parallel horizontal lines.
     */
    public static final FillPattern HORIZONTAL_LINES
        = new FillPattern("HORIZONTAL_LINES", "Parallel horizontal lines.");
    /**
     * Parallel slanted lines running from upper left to lower right.
     */
    public static final FillPattern NEGATIVE_SLANTS
        = new FillPattern("NEGATIVE_SLANTS", "Parallel slanted lines running from upper left to lower right.");
    /**
     * Parallel slanted lines running from lower left to upper right.
     */
    public static final FillPattern POSITIVE_SLANTS
        = new FillPattern("POSITIVE_SLANTS", "Parallel slanted lines running from lower left to upper right.");
    /**
     * Small closely spaced '+' marks.
     */
    public static final FillPattern PLUSSES
        = new FillPattern("PLUSSES", "Small closely spaced '+' marks.");
    /**
     * Two series of intersecting parallel lines.
     */
    public static final FillPattern XHATCH
        = new FillPattern("XHATCH", "Two series of intersecting parallel lines.");
    /**
     * 25% fill.  Can be used to simulate transparency.
     */
    public static final FillPattern FILL_25_PERCENT
        = new FillPattern("FILL_25_PERCENT", "25% fill.  Can be used to simulate transparency.");
    /**
     * 50% fill.  Can be used to simulate transparency.
     */
    public static final FillPattern FILL_50_PERCENT
        = new FillPattern("FILL_50_PERCENT", "50% fill.  Can be used to simulate transparency.");
    /**
     * 75% fill.  Can be used to simulate transparency.
     */
    public static final FillPattern FILL_75_PERCENT
        = new FillPattern("FILL_75_PERCENT", "75% fill.  Can be used to simulate transparency.");

    /**
     * The next value to be assigned and the count of number of styles
     * actually given out.
     */
    private static int next_value = 0;
    
    //*************************************************************************
    //  Static Methods
    //*************************************************************************
    
    /**
     * Give out the next value.
     */
    private static synchronized int nextValue() {
        return next_value++;
    }
    
    /**
     * Gets the number of <code>FillPattern</code> objects that have
     * been created.
     * @return the number of patterns.
     */
    public static int getNumberOfPatterns() {
        return next_value;
    }

    //*************************************************************************
    //  Constructor
    //*************************************************************************
    
    /**
     * Construct a new FillPattern with the give name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedFillPatterns()</code>.
     * @param name a String defining the name of the fill pattern.
     * @param description a String describing the pattern.
     */
    protected FillPattern(String name, String description) {
        super(nextValue(), name, description, loadIconResource(FillPattern.class, name + ".gif"));
    }
    
}
