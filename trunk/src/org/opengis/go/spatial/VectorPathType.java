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
 * Contains one static constant, VECTOR, indicating that the in-between points of a path
 * are points on the Euclidean shortest distance line between the vertices.  This line
 * should be computed in whatever "real-world" space the points live.  So for example,
 * if the points are (var>latitude</var>, <var>longitude</var>, <var>altitude</var>),
 * then the shortest distance line should be computed in a coordinate system such as
 * Earth Centered Earth Fixed (ECEF).
 * <P>
 * As an example of the use of VECTOR, consider two buildings some distance
 * apart, each with a person standing on top.  If one of the people were to
 * fire a laser at the other, the beam would follow a straight path that does
 * not bend with the surface of the earth.  This straight path is what is
 * meant by VECTOR.
 * 
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 *
 * @revisit Localize descriptions.
 */
public class VectorPathType extends PathType {
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List VALUES = new ArrayList(1);

    /**
     * The path that is the Euclidean shortest distance path.
     */
    public static final VectorPathType VECTOR =
        new VectorPathType("VECTOR",
        "The path that is the Euclidean shortest distance path.");

    /**
     * Creates a new <code>VectorPathType</code> with the given name.
     *
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected VectorPathType(String name, String description) {
        super(VALUES, name, description);
    }

    /**
     * Lookup an enumerator by value.
     *
     * @param value The value to match the object for.
     * @throws NoSuchEnumerationException If there is no object for the given value.
     */
    public static VectorPathType valueOf(int value) throws NoSuchEnumerationException {
        synchronized (VALUES) {
            if (value>=0 && value<VALUES.size()) {
                final VectorPathType e = (VectorPathType) VALUES.get(value);
                assert e.ordinal() == value : value;
                return e;
            }
        }
        throw new NoSuchEnumerationException(value);
    }

    /**
     * Returns the list of <code>VectorPathType</code>s.
     * Useful when making comboboxes like:
     * <pre>
     * JComboBox comboBox = new JComboBox(VectorPathType.values());
     * </pre>
     */
    public static VectorPathType[] values() {
        synchronized (VALUES) {
            return (VectorPathType[]) VALUES.toArray(new VectorPathType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public VectorPathType[] family() {
        return values();
    }
}
