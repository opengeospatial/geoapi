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
 * Defines the various YAnchor types.
 * 
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 *
 * @revisit Localize descriptions.
 */
public class YAnchor extends SimpleEnumerationType {
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
     * Align to the top of the field.
     */
    public static final YAnchor TOP = new YAnchor("Top", "");

    /**
     * Align the middle of the field.
     */
    public static final YAnchor MIDDLE = new YAnchor("Middle", "");

    /**
     * Align to the baseline of the field.
     */
    public static final YAnchor BASELINE = new YAnchor("Baseline", "");

    /**
     * Align to the bottom of the field.
     */
    public static final YAnchor BOTTOM = new YAnchor("Bottom", "");                        

    //*************************************************************************
    //  Constructor
    //*************************************************************************

    /**
     * Construct a new YAnchor with the given name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedYAnchors()</code>.
     *
     * @param name a String defining the name of the Arrow pattern.
     * @param description a String describing the pattern.
     */
    protected YAnchor(String name, String description) {
        super(mutableValues, name, description);
    }

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>YAnchor</code>s.
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
