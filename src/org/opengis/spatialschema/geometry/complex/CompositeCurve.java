/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.complex;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.OrientableCurve;


/**
 * A {@linkplain Complex complex} with all the geometric properties of a curve. Thus, this
 * composite can be considered as a type of {@linkplain OrientableCurve orientable curve}.
 * Essentially, a composite curve is a list of {@linkplain OrientableCurve orientable curves}
 * agreeing in orientation in a manner such that each curve (except the first) begins where
 * the previous one ends.
 *
 * @UML type GM_CompositeCurve
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit This interface extends (indirectly) both {@link org.opengis.spatialschema.geometry.primitive.Primitive} and
 *          {@link org.opengis.spatialschema.geometry.complex.Complex}. Concequently, there is a clash in the semantics
 *          of some set theoretic operation. Specifically, <code>Primitive.contains(...)</code>
 *          (returns FALSE for end points) is different from <code>Complex.contains(...)</code>
 *          (returns TRUE for end points).
 */
public interface CompositeCurve extends Composite, OrientableCurve {
    /**
     * Returns the list of orientable curves in this composite.
     *
     * To get a full representation of the elements in the {@linkplain Complex complex},
     * the {@linkplain org.opengis.spatialschema.geometry.primitive.Point points} on the boundary of the
     * generator set of {@linkplain org.opengis.spatialschema.geometry.primitive.Curve curve} would be
     * added to the curves in the generator list.
     *
     * @return The list of orientable curves in this composite.
     * @UML association generator
     */
    public List/*<OrientableCurve>*/ getGenerators();
}
