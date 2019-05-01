/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
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
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
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
    private static final List<TopologyLevel> VALUES = new ArrayList<>(9);

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
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private TopologyLevel(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code TopologyLevel}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static TopologyLevel[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new TopologyLevel[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public TopologyLevel[] family() {
        return values();
    }

    /**
     * Returns the topology level that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static TopologyLevel valueOf(String code) {
        return valueOf(TopologyLevel.class, code);
    }
}
