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
 * Instances of <code>ArrowStyle</code> indicate the various
 * enumerations of drawing a line with an arrow head.  This class defines static
 * constants for all of the standard choices.
 *
 * <p>An implementation is not required to implement the
 * entire list of available arrow styles.  At runtime, the list of
 * implemented arrow styles can be retrieved from a
 * DisplayCapabilities object (that was itself retrieved from a
 * Canvas). An implementation may also implement
 * other arrow styles not listed here.</p>

 * <p>The integer constant returned from nextValue() is only a
 * convenience for the implemetor, allowing the use of array indexing
 * on integers. Implementators can build an array with sparse entries
 * for the arrow patterns the implementation supports.</p>
 *
 * <p>NOTE: Two different implementations may have arrow styles with
 * the same name, but use different <code>ArrowStyle</code>
 * instances. If clients need to use the arrow style name to find a
 * arrow style they should search the array returned by
 * <code>getSupportedArrowStyles</code> from the
 * <code>DisplayCapabilities</code> instance associated with the
 * correct provider.</p>
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */

public class ArrowStyle extends SimpleEnumerationType {

	//*************************************************************************
    //  Static Fields
    //*************************************************************************
    
	/**
	 * Solid line with an no arrowhead.
	 */
	public static final ArrowStyle NONE 
		= new ArrowStyle("NONE", "No arrow head will be drawn");
    /**
     * Solid line with an open arrowhead on one end in the foreground color.
     */
    public static final ArrowStyle OPEN_ARROW
        = new ArrowStyle("OPEN_ARROW",
                        "Single line with an open arrowhead.");
    /**
     * Solid line with a filled arrowhead on one end.
     */
    public static final ArrowStyle SOLID_ARROW
        = new ArrowStyle("SOLID_ARROW",
                        "Single line with a filled arrowhead.");
   
    /**
     * Double line with large arrowhead.
     */
    public static final ArrowStyle BROAD_ARROW
        = new ArrowStyle("BROAD_ARROW",
                        "Double line with large arrowhead.");
                        
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
     * Gets the number of <code>ArrowStyle</code>s that have been
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
     * Construct a new ArrowStyle with the given name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedArrowStyles()</code>.
     * @param name a String defining the name of the Arrow pattern.
     * @param description a String describing the pattern.
     */
    protected ArrowStyle(String name, String description) {
        super(nextValue(), name, description, loadIconResource(ArrowStyle.class, name + ".gif"));
    }        
}