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
 * Instances of <code>Mark</code> indicate the various
 * methods of sybolizing points with marks.  This class defines static
 * constants for all of the valid choices.  Depending on the choice of
 * custom mark, circle, cross, square, star, triangle, x mark, 
 * may affect the appearance of an object.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class Mark extends SimpleEnumerationType {

	//*************************************************************************
	//  Static Fields
	//*************************************************************************
    
	/**
	 * This constant indicates that a point should be symbolized with a custom mark.
	 */
	   public static final Mark CUSTOM =
		new Mark("CUSTOM", "Symbolize with a custom mark.");

	/**
	 * This constant indicates that a point should be symbolized with a circle mark.
	 */
	public static final Mark CIRCLE =
		new Mark("CIRCLE", "Symbolize with a circle mark.");

	/**
	 * This constant indicates that a point should be symbolized with a cross mark.
	 */
	public static final Mark CROSS =
		new Mark("CROSS", "Symbolize with a cross mark.");

	/**
	 * This constant indicates that a point should be symbolized with a square mark.
	 */
	public static final Mark SQUARE =
		new Mark("SQUARE", "Symbolize with a square mark.");

	/**
	 * This constant indicates that a point should be symbolized with a star mark.
	 */
	public static final Mark STAR =
		new Mark("STAR", "Symbolize with a star mark.");

	/**
	 * This constant indicates that a point should be symbolized with a triangle mark.
	 */
	public static final Mark TRIANGLE =
		new Mark("TRIANGLE", "Symbolize with a triangle mark.");

	/**
	 * This constant indicates that a point should be symbolized with an X mark.
	 */
	public static final Mark X =
		new Mark("X", "Symbolize with an X mark.");
                        
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
	 * Gets the number of <code>Mark</code>s that have been
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
	 * Construct a new Mark with the given name and description.
	 * This constructor should only be used to make the static
	 * constants in this class or by a provider subclasses to create
	 * implementation specific styles that can be accessed by
	 * <code>DisplayCapabilities.getSupportedMarks()</code>.
	 * @param name a String defining the name of the Arrow pattern.
	 * @param description a String describing the pattern.
	 */
	protected Mark(String name, String description) {
		super(nextValue(), name, description);
	}        
}
