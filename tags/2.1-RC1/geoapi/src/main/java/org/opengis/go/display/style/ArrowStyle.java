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
 * Indicates the various enumerations of drawing a line with an arrow head.
 * This class defines static constants for all of the standard choices.
 *
 * <p>An implementation is not required to implement the entire list of available
 * arrow styles.  At runtime, the list of implemented arrow styles can be retrieved
 * from a {@link org.opengis.go.display.DisplayCapabilities} object (that was itself
 * retrieved from a <code>Canvas</code>). An implementation may also implement
 * other arrow styles not listed here.</p>
 *
 * <p>NOTE: Two different implementations may have arrow styles with
 * the same name, but use different <code>ArrowStyle</code>
 * instances. If clients need to use the arrow style name to find a
 * arrow style they should search the array returned by
 * <code>getSupportedArrowStyles</code> from the
 * <code>DisplayCapabilities</code> instance associated with the
 * correct provider.</p>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public class ArrowStyle extends SimpleEnumerationType<ArrowStyle> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -1295348723850401634L;

    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List<ArrowStyle> VALUES = new ArrayList<ArrowStyle>(4);

    /**
     * Solid line with an no arrowhead.
     */
    public static final ArrowStyle NONE = new ArrowStyle("NONE", "No arrow head will be drawn");

    /**
     * Solid line with an open arrowhead on one end in the foreground color.
     */
    public static final ArrowStyle OPEN_ARROW =
        new ArrowStyle("OPEN_ARROW", "Single line with an open arrowhead.");

    /**
     * Solid line with a filled arrowhead on one end.
     */
    public static final ArrowStyle SOLID_ARROW =
        new ArrowStyle("SOLID_ARROW", "Single line with a filled arrowhead.");

    /**
     * Double line with large arrowhead.
     */
    public static final ArrowStyle BROAD_ARROW
        = new ArrowStyle("BROAD_ARROW",
                         "Double line with large arrowhead.");

    //*************************************************************************
    //  Constructor
    //*************************************************************************

    /**
     * Construct a new ArrowStyle with the given name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedArrowStyles()</code>.
     *
     * @param name a String defining the name of the Arrow pattern.
     * @param description a String describing the pattern.
     */
    protected ArrowStyle(String name, String description) {
        super(VALUES, name, description, loadIconResource(ArrowStyle.class, name + ".gif"));
    }

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>ArrowStyle</code>s.
     */
    public static ArrowStyle[] values() {
        synchronized (VALUES) {
            return (ArrowStyle[]) VALUES.toArray(new ArrowStyle[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ArrowStyle}*/ CodeList[] family() {
        return values();
    }
}
