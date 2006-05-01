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
 * Indicates the various various ways of joining two lines at their intersection.
 * This class defines static constants for all of the standard choices.
 * 
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 *
 * @revisit Localize descriptions.
 */
public class LineJoin extends SimpleEnumerationType {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List VALUES = new ArrayList(3);
    
    /**
     * This constant indicates that lines should be joined at
     * intersections by extending their outside edges until they meet.
     */
    public static final LineJoin MITER =
        new LineJoin("MITER", "Join lines by extending outside edges until they meet.");
        
    /**
     * This constant indicates that lines should be joined by a circular
     * arc of radius equal to half the line width.
     */
    public static final LineJoin ROUND =
        new LineJoin("ROUND", "Join lines with a circular arc equal to hald the line width.");
        
    /**
     * This constant indicates that lines should be joined by connecting
     * the outer corners of the lines with a straight line segment.
     */
    public static final LineJoin BEVEL =
            new LineJoin("BEVEL",
                    "Joine lines by connecting outer corners with a segment.");
    
    //*************************************************************************
    //  Constructor
    //*************************************************************************
    
    /**
     * Construct a new LineJoin with the given name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedLineJoins()</code>.
     *
     * @param name a String defining the name of the Arrow pattern.
     * @param description a String describing the pattern.
     */
    protected LineJoin(String name, String description) {
        super(VALUES, name, description);
    }        

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>LineJoin</code>s.
     */
    public static LineJoin[] values() {
        synchronized (VALUES) {
            return (LineJoin[]) VALUES.toArray(new LineJoin[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public CodeList[] family() {
        return values();
    }
}
