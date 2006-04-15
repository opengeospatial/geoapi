/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

// J2SE direct dependencies
import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;
import org.opengis.util.SimpleEnumerationType;


/**
 * Instances of <code>LineCap</code> indicate the various
 * ways of capping the end of a styled line. This class defines static
 * constants for all of the standard choices.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public class LineCap extends SimpleEnumerationType<LineCap> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7111743758813550715L;

    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List<LineCap> VALUES = new ArrayList<LineCap>(3);

    /**
     * This constant indicates that line ends should be drawn with no extra
     * decoration.
     */
    public static final LineCap BUTT = new LineCap("BUTT", "Draw line ends with no decoration.");

    /**
     * This constant indicates that line ends should be drawn with a half
     * circle of diameter equal to the width of the line.
     */
    public static final LineCap ROUND = new LineCap("ROUND", "Draw line ends with half circles.");

    /**
     * This constant indicates that the line should be capped by a straight
     * line extending half the line's width past the endpoint of the line.
     */
    public static final LineCap SQUARE =
            new LineCap("SQUARE", "Draw line ends with a square cap.");

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
        super(VALUES, name, description);
    }

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>LineCap</code>s.
     */
    public static LineCap[] values() {
        synchronized (VALUES) {
            return (LineCap[]) VALUES.toArray(new LineCap[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{LineCap}*/ CodeList[] family() {
        return values();
    }
}
