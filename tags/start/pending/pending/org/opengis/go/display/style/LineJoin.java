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
 * Instances of <code>LineJoin</code> indicate the various
 * various ways of joining two lines at their intersection. This class defines static
 * constants for all of the standard choices.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class LineJoin extends SimpleEnumerationType {

	//*************************************************************************
	//  Static Fields
	//*************************************************************************
    
    
	/**
	 * This constant indicates that lines should be joined at
	 * intersections by extending their outside edges until they meet.
	 */
	public static final LineJoin MITER =
		new LineJoin("MITER",
			"Join lines by extending outside edges until they meet.");

	/**
	 * This constant indicates that lines should be joined by a circular
	 * arc of radius equal to half the line width.
	 */
	public static final LineJoin ROUND =
		new LineJoin("ROUND",
			"Join lines with a circular arc equal to hald the line width.");

	/**
	 * This constant indicates that lines should be joined by connecting
	 * the outer corners of the lines with a straight line segment.
	 */
	public static final LineJoin BEVEL =
		new LineJoin("BEVEL",
			"Joine lines by connecting outer corners with a segment.");
                        
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
	 * Gets the number of <code>LineJoin</code>s that have been
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
	 * Construct a new LineJoin with the given name and description.
	 * This constructor should only be used to make the static
	 * constants in this class or by a provider subclasses to create
	 * implementation specific styles that can be accessed by
	 * <code>DisplayCapabilities.getSupportedLineJoins()</code>.
	 * @param name a String defining the name of the Arrow pattern.
	 * @param description a String describing the pattern.
	 */
	protected LineJoin(String name, String description) {
		super(nextValue(), name, description);
	}        
}