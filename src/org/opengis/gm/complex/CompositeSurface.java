/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.complex;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.gm.primitive.OrientableSurface;


/**
 * A {@link Complex} with all the geometric properties of a surface. Thus, this composite can be
 * considered as a type of orientable surface ({@link OrientableSurface}). Essentially, a
 * composite surface is a collection of oriented surfaces that join in pairs on common boundary
 * curves and which, when considered as a whole, form a single surface.
 *
 * @UML type GM_CompositeSurface
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit This interface extends (indirectly) both {@link org.opengis.gm.primitive.Primitive} and
 *          {@link org.opengis.gm.complex.Complex}. Concequently, there is a clash in the semantics
 *          of some set theoretic operation. Specifically, <code>Primitive.contains(...)</code>
 *          (returns FALSE for end points) is different from <code>Complex.contains(...)</code>
 *          (returns TRUE for end points).
 */
public interface CompositeSurface extends Composite, OrientableSurface {
    /**
     * Returns the list of orientable surfaces in this composite.
     *
     * To get a full representation of the elements in the {@link Complex}, the
     * {@link org.opengis.gm.primitive.Curve}s and {@link org.opengis.gm.primitive.Point}s
     * on the boundary of the generator set of {@link org.opengis.gm.primitive.Surface}s
     * would be added to the curves in the generator list.
     *
     * @return The list of orientable surfaces in this composite.
     * @UML association generator
     */
    public List<OrientableSurface> getGenerators();
}
