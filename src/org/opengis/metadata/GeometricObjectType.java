/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Name of point and vector spatial objects used to locate zero-, one-, and twodimensional
 * spatial locations in the dataset.
 *
 * @UML codelist MD_GeometricObjectTypeCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public final class GeometricObjectType extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
//    private static final long serialVersionUID = -1901029875497457189L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(6);

    /**
     * Set of geometric primitives such that their boundaries can be represented as a
     * union of other primitives.
     *
     * @UML conditional complexes
     */
    public static final GeometricObjectType COMPLEXES = new GeometricObjectType("COMPLEXES");

    /**
     * Connected set of curves, solids or surfaces.
     *
     * @UML conditional composites
     */
    public static final GeometricObjectType COMPOSITES = new GeometricObjectType("COMPOSITES");

    /**
     * Bounded, 1-dimensional geometric primitive, representing the continuous image of a line.
     *
     * @UML conditional curve
     */
    public static final GeometricObjectType CURVE = new GeometricObjectType("CURVE");

    /**
     * Zero-dimensional geometric primitive, representing a position but not having an extent.
     *
     * @UML conditional point
     */
    public static final GeometricObjectType POINT = new GeometricObjectType("POINT");

    /**
     * Bounded, connected 3-dimensional geometric primitive, representing the
     * continuous image of a region of space.
     *
     * @UML conditional solid
     */
    public static final GeometricObjectType SOLID = new GeometricObjectType("SOLID");

    /**
     * Bounded, connected 2-dimensional geometric, representing the continuous image
     * of a region of a plane.
     *
     * @UML conditional surface
     */
    public static final GeometricObjectType SURFACE = new GeometricObjectType("SURFACE");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public GeometricObjectType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>GeometricObjectType</code>s.
     */
    public static GeometricObjectType[] values() {
        synchronized (VALUES) {
            return (GeometricObjectType[]) VALUES.toArray(new GeometricObjectType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{GeometricObjectType}*/ CodeList[] family() {
        return values();
    }
}
