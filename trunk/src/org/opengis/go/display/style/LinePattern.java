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
import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;
import org.opengis.util.SimpleEnumerationType;


/**
 * Indicate the various enumerations of drawing a patterned line.
 * This class defines static constants for all of the standard choices.
 *
 * <p>An implementation is not required to implement the entire list of
 * available line patterns.  At runtime, the list of implemented line
 * patterns can be retrieved from a {@link org.opengis.go.display.DisplayCapabilities}
 * object (that was itself retrieved from a <code>Canvas</code>).
 * An implementation may also implement other line patterns not listed here by
 * creating instances of a {@link DashArray}.</p>
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public class LinePattern extends SimpleEnumerationType {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List VALUES = new ArrayList(6);
    
    /**
     * The line should be drawn with no pattern.
     */
    public static final LinePattern NONE = new LinePattern("NONE", "A line with no pattern.");
    
    /**
     * The line should be drawn dashed.
     */
    public static final LinePattern DASHED = new LinePattern("DASHED", "A dashed line");
    
    /**
     * The line should be drawn dotted.
     */
    public static final LinePattern DOTTED = new LinePattern("DOTTED", "A dotted line");
    
    /**
     * The line should be drawn dot dashed.
     */
    public static final LinePattern DOT_DASHED = new LinePattern("DOT_DASHED", "Dot dashed line");
    
    /**
     * The line should be drawn long dashed.
     */
    public static final LinePattern LONG_DASHED =
        new LinePattern("LONG_DASHED", "Long dashed line");
        
    /**
     * The line should be drawn long dot dashed.
     */
    public static final LinePattern LONG_DOT_DASHED
            = new LinePattern("LONG_DOT_DASHED", "Long dot dashed line");
    
    //*************************************************************************
    //  Constructor
    //*************************************************************************
    
    protected LinePattern(String name, String description) {
        super(VALUES, name, description, loadIconResource(LinePattern.class, name + ".gif"));
    }

    /**
     * Returns the list of <code>LinePattern</code>s.
     */
    public static LinePattern[] values() {
        synchronized (VALUES) {
            return (LinePattern[]) VALUES.toArray(new LinePattern[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public CodeList[] family() {
        return values();
    }
}



