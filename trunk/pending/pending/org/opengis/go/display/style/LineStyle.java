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
 * Instances of <code>LineStyle</code> indicate the various
 * enumerations of drawing a line.  This class defines static
 * constants for all of the standard choices. Depending on the choice
 * of line style, the current foreground color, and background color
 * may affect the appearance of an object.
 *
 * <p>An implementation is not required to implement the
 * entire list of available line styles.  At runtime, the list of
 * implemented line styles can be retrieved from a
 * DisplayCapabilities object (that was itself retrieved from a
 * Canvas). An implementation may also implement
 * other line styles not listed here.</p>

 * <p>The integer constant returned from nextValue() is only a
 * convenience for the implemetor, allowing the use of array indexing
 * on integers. Implementators can build an array with sparse entries
 * for the fill patterns the implementation supports.</p>
 *
 * <p>NOTE: Two different implementations may have line styles with
 * the same name, but use different <code>LineStyle</code>
 * instances. If clients need to use the line style name to find a
 * line style they should search the array returned by
 * <code>getSupportedLineStyles()</code> from the
 * <code>DisplayCapabilities</code> instance associated with the
 * correct provider.</p>
 *
 * <p>This list was compiled by examining the behavior of various
 * software packages.  Where possible, a reference document is given.
 * For others, a description of the style or an indication of the
 * software where the style can be seen is given.</p>
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class LineStyle extends SimpleEnumerationType {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    
    /**
     * The line should be drawn as a single line.
     */ 
    public static final LineStyle SINGLE
    	= new LineStyle("SINGLE", "Single Line");    

    /**
     * The line should be drawn as double parallel lines.
     */
    public static final LineStyle DOUBLE
        = new LineStyle("DOUBLE", "Double Line");

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
     * Gets the number of <code>LineStyle</code>s that have been
     * created.
     * @return the number of styles.
     */
    public int getNumberOfStyles() {
        return next_value;
    }

    //*************************************************************************
    //  Constructor
    //*************************************************************************
    
    /**
     * Construct a new LineStyle with the give name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedLineStyles()</code>.
     * @param name a String defining the name of the fill pattern.
     * @param description a String describing the pattern.
     */
    protected LineStyle(String name, String description) {
        super(nextValue(), name, description, loadIconResource(LineStyle.class, name + ".gif"));
    }    
}
