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

// J2SE direct dependencies
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// OpenGIS direct dependencies
import org.opengis.util.SimpleEnumerationType;


/**
 * Indicates the various various ways of joining two lines at their intersection.
 * This class defines static constants for all of the standard choices.
 * 
 * @version 0.2
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
    private static final List mutableValues = new ArrayList();

    /**
     * An immutable view of {@link #mutableValues} to be returned by {@link #values()}.
     */
    private static final List values = Collections.unmodifiableList(mutableValues);

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
        super(mutableValues, name, description);
    }        

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>LineJoin</code>s.
     */
    public static List values() {
        return values;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public List family() {
        return values;
    }
}
