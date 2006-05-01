/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

// J2SE direct dependencies
import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;
import org.opengis.util.SimpleEnumerationType;


/**
 * Indicate the various methods of sybolizing points with marks.  This class defines static
 * constants for all of the valid choices.  Depending on the choice of custom mark, circle,
 * cross, square, star, triangle, x mark, may affect the appearance of an object.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */

public class Mark extends SimpleEnumerationType {

    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List VALUES = new ArrayList(7);

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

    //*************************************************************************
    //  Constructor
    //*************************************************************************

    /**
     * Construct a new Mark with the given name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedMarks()</code>.
     *
     * @param name a String defining the name of the Arrow pattern.
     * @param description a String describing the pattern.
     */
    protected Mark(String name, String description) {
        super(VALUES, name, description);
    }        

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>Mark</code>s.
     */
    public static Mark[] values() {
        synchronized (VALUES) {
            return (Mark[]) VALUES.toArray(new Mark[VALUES.size()]);
    }
}

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public CodeList[] family() {
        return  values();
    }
}
