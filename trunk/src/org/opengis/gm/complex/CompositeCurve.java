/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.complex;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.gm.primitive.OrientableCurve;


/**
 * A {@link Complex} with all the geometric properties of a curve. Thus, this composite can be
 * considered as a type of orientable curve ({@link OrientableCurve}). Essentially, a composite
 * curve is a list of orientable curves ({@link OrientableCurve}) agreeing in orientation in a
 * manner such that each curve (except the first) begins where the previous one ends.
 *
 * @UML type GM_CompositeCurve
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
public interface CompositeCurve extends Composite, OrientableCurve {
    /**
     * Returns the list of orientable curves in this composite.
     *
     * To get a full representation of the elements in the {@link Complex},
     * the {@link org.opengis.gm.primitive.Point}s on the boundary of the
     * generator set of {@link org.opengis.gm.primitive.Curve} would be
     * added to the curves in the generator list.
     *
     * @return The list of orientable curves in this composite.
     * @UML association generator
     */
    public List/*<OrientableCurve>*/ getGenerators();
}
