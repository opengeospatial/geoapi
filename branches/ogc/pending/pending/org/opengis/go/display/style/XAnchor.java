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
 * The <code>XAnchor</code> class defines the various XAnchor types.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class XAnchor extends SimpleEnumerationType {

	//*************************************************************************
	//  Static Fields
	//*************************************************************************
    
	/**
	  * Align to the left of the field.
	  */
	public static final XAnchor LEFT = new XAnchor("LEFT", "");

	/**
	  * Align the center of the field.
	  */
	public static final XAnchor CENTER = new XAnchor("CENTER", "");

	/**
	  * Align to the right of the field.
	  */
	public static final XAnchor RIGHT = new XAnchor("RIGHT", "");

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
	 * Gets the number of <code>XAnchor</code>s that have been
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
	 * Construct a new XAnchor with the given name and description.
	 * This constructor should only be used to make the static
	 * constants in this class or by a provider subclasses to create
	 * implementation specific styles that can be accessed by
	 * <code>DisplayCapabilities.getSupportedXAnchors()</code>.
	 * @param name a String defining the name of the Arrow pattern.
	 * @param description a String describing the pattern.
	 */
	protected XAnchor(String name, String description) {
		super(nextValue(), name, description);
	}
}