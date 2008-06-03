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
 * Indicate the various enumerations of drawing a line.  This class defines static
 * constants for all of the standard choices. Depending on the choice of line style,
 * the current foreground color, and background color may affect the appearance of an
 * object.
 *
 * <p>An implementation is not required to implement the entire list of available line
 * styles.  At runtime, the list of implemented line styles can be retrieved from a
 * {@link org.opengis.go.display.DisplayCapabilities} object (that was itself retrieved
 * from a <code>Canvas</code>). An implementation may also implement
 * other line styles not listed here.</p>
 *
 * <p>NOTE: Two different implementations may have line styles with
 * the same name, but use different <code>LineStyle</code>
 * instances. If clients need to use the line style name to find a
 * line style they should search the array returned by
 * <code>getSupportedLineStyles()</code> from the
 * <code>DisplayCapabilities</code> instance associated with the
 * correct provider.</p>
 *
 * <p>This list was compiled by examining the behavior of various
 * software packages.  Where possible, a reference document is given.
 * For others, a description of the style or an indication of the
 * software where the style can be seen is given.</p>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public class LineStyle extends SimpleEnumerationType<LineStyle> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -3641627651863436629L;


    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List<LineStyle> VALUES = new ArrayList<LineStyle>(2);

    /**
     * The line should be drawn as a single line.
     */
    public static final LineStyle SINGLE = new LineStyle("SINGLE", "Single Line");

    /**
     * The line should be drawn as double parallel lines.
     */
    public static final LineStyle DOUBLE
        = new LineStyle("DOUBLE", "Double Line");

    //*************************************************************************
    //  Constructor
    //*************************************************************************

    /**
     * Construct a new LineStyle with the give name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific styles that can be accessed by
     * <code>DisplayCapabilities.getSupportedLineStyles()</code>.
     *
     * @param name a String defining the name of the fill pattern.
     * @param description a String describing the pattern.
     */
    protected LineStyle(String name, String description) {
        super(VALUES, name, description, loadIconResource(LineStyle.class, name + ".gif"));
    }

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of {@code LineStyle}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static LineStyle[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new LineStyle[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public LineStyle[] family() {
        return values();
    }
}
