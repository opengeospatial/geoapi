/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.style;

import java.util.ArrayList;
import java.util.List;
import org.opengis.util.CodeList;
import org.opengis.util.SimpleEnumerationType;


/**
 * Defines the various YAnchor types.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public class YAnchor extends SimpleEnumerationType<YAnchor> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -256924479162362298L;

    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List<YAnchor> VALUES = new ArrayList<YAnchor>(4);

    /**
      * Align to the top of the field.
      */
    public static final YAnchor TOP = new YAnchor("TOP", "");

    /**
      * Align the middle of the field.
      */
    public static final YAnchor MIDDLE = new YAnchor("MIDDLE", "");

    /**
      * Align to the baseline of the field.
      */
    public static final YAnchor BASELINE = new YAnchor("BASELINE", "");

    /**
      * Align to the bottom of the field.
      */
    public static final YAnchor BOTTOM = new YAnchor("BOTTOM", "");

    /**
     * The next value to be assigned and the count of number of styles
     * actually given out.
     */
    private static int next_value = 0;

    //*************************************************************************
    //  Static Methods
    //*************************************************************************

    /**
     * Gets the number of <code>YAnchor</code>s that have been
     * created.
     * @return the number of styles.
     *
     * @deprecated Use <code>{@linkplain #values}.length</code> instead.
     */
    @Deprecated
    public static int getNumberOfStyles() {
        return next_value;
    }

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
        super(VALUES, name, description);
    }

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>YAnchor</code>s.
     */
    public static YAnchor[] values() {
        synchronized (VALUES) {
            return (YAnchor[]) VALUES.toArray(new YAnchor[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{YAnchor}*/ CodeList[] family() {
        return values();
    }
}
