/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.spatial;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Degree of complexity of the spatial relationships.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_TopologyLevelCode", specification=ISO_19115)
public final class TopologyLevel extends CodeList<TopologyLevel> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -179324311133793389L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<TopologyLevel> VALUES = new ArrayList<TopologyLevel>(9);

    /**
     * Geometry objects without any additional structure which describes topology.
     */
    @UML(identifier="geometryOnly", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TopologyLevel GEOMETRY_ONLY = new TopologyLevel("GEOMETRY_ONLY");

    /**
     * 1-dimensional topological complex.
     */
    @UML(identifier="topology1D", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TopologyLevel TOPOLOGY_1D = new TopologyLevel("TOPOLOGY_1D");

    /**
     * 1-dimensional topological complex which is planar.
     */
    @UML(identifier="planarGraph", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TopologyLevel PLANAR_GRAPH = new TopologyLevel("PLANAR_GRAPH");

    /**
     * 2-dimensional topological complex which is planar.
     */
    @UML(identifier="fullPlanarGraph", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TopologyLevel FULL_PLANAR_GRAPH = new TopologyLevel("FULL_PLANAR_GRAPH");

    /**
     * 1-dimensional topological complex which is isomorphic to a subset of a surface.
     */
    @UML(identifier="surfaceGraph", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TopologyLevel SURFACE_GRAPH = new TopologyLevel("SURFACE_GRAPH");

    /**
     * 2-dimensional topological complex which is isomorphic to a subset of a surface.
     */
    @UML(identifier="fullSurfaceGraph", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TopologyLevel FULL_SURFACE_GRAPH = new TopologyLevel("FULL_SURFACE_GRAPH");

    /**
     * 3-dimensional topological complex.
     */
    @UML(identifier="topology3D", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TopologyLevel TOPOLOGY_3D = new TopologyLevel("TOPOLOGY_3D");

    /**
     * Complete coverage of a 3D coordinate space.
     */
    @UML(identifier="fullTopology3D", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TopologyLevel FULL_TOPOLOGY_3D = new TopologyLevel("FULL_TOPOLOGY_3D");

    /**
     * Topological complex without any specified geometric realization.
     */
    @UML(identifier="abstract", obligation=CONDITIONAL, specification=ISO_19115)
    public static final TopologyLevel ABSTRACT = new TopologyLevel("ABSTRACT");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public TopologyLevel(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code TopologyLevel}s.
     */
    public static TopologyLevel[] values() {
        synchronized (VALUES) {
            return (TopologyLevel[]) VALUES.toArray(new TopologyLevel[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{TopologyLevel}*/ CodeList[] family() {
        return values();
    }
}
