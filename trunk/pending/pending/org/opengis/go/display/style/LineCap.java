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
 * Instances of <code>LineCap</code> indicate the various
 * ways of capping the end of a styled line. This class defines static
 * constants for all of the standard choices.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class LineCap extends SimpleEnumerationType {

	//*************************************************************************
	//  Static Fields
	//*************************************************************************

	/**
	 * This constant indicates that line ends should be drawn with no extra
	 * decoration.
	 */
	public static final LineCap BUTT =
		new LineCap("BUTT", "Draw line ends with no decoration.");

	/**
	 * This constant indicates that line ends should be drawn with a half
	 * circle of diameter equal to the width of the line.
	 */
	public static final LineCap ROUND =
		new LineCap("ROUND", "Draw line ends with half circles.");

	/**
	 * This constant indicates that the line should be capped by a straight
	 * line extending half the line's width past the endpoint of the line.
	 */
	public static final LineCap SQUARE =
		new LineCap("SQUARE", "Draw line ends with a square cap.");
                        
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
	 * Gets the number of <code>LineCap</code>s that have been
	 * created.
	 * @return the number of styles.
	 */
	public static int getNumberOfStyles() {
		return next_value;
	}

	//*************************************************************************
	//  Constructor
	//*************************************************************************
    
	/**
	 * Construct a new LineCap with the given name and description.
	 * This constructor should only be used to make the static
	 * constants in this class or by a provider subclasses to create
	 * implementation specific styles that can be accessed by
	 * <code>DisplayCapabilities.getSupportedLineCaps()</code>.
	 * @param name a String defining the name of the Arrow pattern.
	 * @param description a String describing the pattern.
	 */
	protected LineCap(String name, String description) {
		super(nextValue(), name, description);
	}        
}

