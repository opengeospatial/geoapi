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
 * Defines the various XAnchor types.
 * 
 * @version $Revision$, $Date$
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 *
 * @revisit Localize descriptions.
 */
public class XAnchor extends SimpleEnumerationType {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List VALUES = new ArrayList(3);
    
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
    
    //*************************************************************************
    //  Constructor
    //*************************************************************************
    
    /**
     * Construct a new XAnchor with the given name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedXAnchors()</code>.
     *
     * @param name a String defining the name of the Arrow pattern.
     * @param description a String describing the pattern.
     */
    protected XAnchor(String name, String description) {
        super(VALUES, name, description);
    }

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>XAnchor</code>s.
     */
    public static XAnchor[] values() {
        synchronized (VALUES) {
            return (XAnchor[]) VALUES.toArray(new XAnchor[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public CodeList[] family() {
        return values();
    }
}
