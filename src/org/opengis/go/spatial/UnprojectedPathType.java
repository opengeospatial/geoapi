/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.spatial;

// J2SE direct dependencies
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.NoSuchEnumerationException;


/**
 * Enumerations for unprojected path types.
 * This class serves to contain two constants: 
 * 
 * <ol>
 *     <li>PIXEL_STRAIGHT, that indicates
 *         that a path between two points should be drawn on the screen 
 *         as a straight line, regardless of the location or orientation 
 *         of the vertices.</li>
 *     <li>CONTINUOUS_SPLINE, that indicates a spline is to be 
 *         used to connect points along a path.</li>
 * </ol>
 * These should be used in situations where accuracy is unimportant or the
 * appearance of the path is intended to remain the same, regardless of the
 * current viewport of the map.  Note that this applies to both
 * two dimensional and three dimensional displays; in a three dimensional
 * display, the path may cross other objects, but will always appear straight.
 * 
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 *
 * @revisit Localize descriptions.
 */
public class UnprojectedPathType extends PathType {
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List VALUES = new ArrayList(2);

    /**
     * The path that is drawn as screen-straight, regardless of any current projection.
     */
    public static final UnprojectedPathType PIXEL_STRAIGHT =
        new UnprojectedPathType("PIXEL_STRAIGHT",
        "The path that is drawn as screen-straight, regardless of any current projection.");

    /**
     * The path that connects its points via a continuous (although not necessarily smooth) spline.
     */
    public static final UnprojectedPathType CONTINUOUS_SPLINE = 
        new UnprojectedPathType("CONTINUOUS_SPLINE",
        "The path that connects its points via a continuous (although not necessarily smooth) spline.");

    /**
     * Creates a new <code>UnprojectedPathType</code> with the name.
     *
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected UnprojectedPathType(String name, String description) {
        super(VALUES, name, description);
    }

    /**
     * Lookup an enumerator by value.
     *
     * @param value The value to match the object for.
     * @throws NoSuchEnumerationException If there is no object for the given value.
     */
    public static UnprojectedPathType valueOf(int value) throws NoSuchEnumerationException {
        synchronized (VALUES) {
            if (value>=0 && value<VALUES.size()) {
                final UnprojectedPathType e = (UnprojectedPathType) VALUES.get(value);
                assert e.ordinal() == value : value;
                return e;
            }
        }
        throw new NoSuchEnumerationException(value);
    }

    /**
     * Returns the list of <code>UnprojectedPathType</code>s.
     * Useful when making comboboxes like:
     * <pre>
     * JComboBox comboBox = new JComboBox(UnprojectedPathType.values());
     * </pre>
     */
    public static UnprojectedPathType[] values() {
        synchronized (VALUES) {
            return (UnprojectedPathType[]) VALUES.toArray(new UnprojectedPathType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public UnprojectedPathType[] family() {
        return values();
    }
}
