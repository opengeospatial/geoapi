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
 * Instances of <code>LinePattern</code> indicate the various
 * enumerations of drawing a patterned line.  This class defines static
 * constants for all of the standard choices.
 *
 * <p>An implementation is not required to implement the
 * entire list of available line patterns.  At runtime, the list of
 * implemented line patterns can be retrieved from a
 * DisplayCapabilities object (that was itself retrieved from a
 * Canvas). An implementation may also implement
 * other line patterns not listed here by creating instances
 * of a <code>DashArray</code>.</p>

 * <p>The integer constant returned from nextValue() is only a
 * convenience for the implemetor, allowing the use of array indexing
 * on integers. Implementators can build an array with sparse entries
 * for the line patterns the implementation supports.</p>
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class LinePattern extends SimpleEnumerationType {

	//*************************************************************************
    //  Static Fields
    //*************************************************************************

	/**
	 * The line should be drawn with no pattern.
	 */
	public static final LinePattern NONE 
		= new LinePattern("NONE", "A line with no pattern.");
	
	/**
	 * The line should be drawn dashed.
	 */
	public static final LinePattern DASHED 
		= new LinePattern("DASHED", "A dashed line");
		
	/**
	 * The line should be drawn dotted.
	 */
	public static final LinePattern DOTTED
		= new LinePattern("DOTTED", "A dotted line");
		
	/**
	 * The line should be drawn dot dashed.
	 */
	public static final LinePattern DOT_DASHED
		= new LinePattern("DOT_DASHED", "Dot dashed line");
		
	/**
	 * The line should be drawn long dashed.
	 */
	public static final LinePattern LONG_DASHED
		= new LinePattern("LONG_DASHED", "Long dashed line");
		
	/**
	 * The line should be drawn long dot dashed.
	 */
	public static final LinePattern LONG_DOT_DASHED
		= new LinePattern("LONG_DOT_DASHED", "Long dot dashed line");
	
	/**
     * The next value to be assigned and the count of number of line patterns
     * actually given out.
     */
    private static int next_value = 0;
    
    //*************************************************************************
    //  Static methods
    //*************************************************************************
    
    /**
     * Give out the next value.
     */
    private static synchronized int nextValue() {
        return next_value++;
    }

    /**
     * Gets the number of <code>LinePattern</code>s that have been
     * created.
     * @return the number of line patterns.
     */
    public static int getNumberOfPatterns() {
        return next_value;
    }

    //*************************************************************************
    //  Constructor
    //*************************************************************************
	
	protected LinePattern(String name, String description) {
        super(nextValue(), name, description, loadIconResource(LinePattern.class, name + ".gif"));
    }
	
	
}
