/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.complex;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.gm.primitive.Primitive;


/**
 * A geometric complex with an underlying core geometry that is isomorphic to a primitive. Thus,
 * a composite curve is a collection of curves whose geometry interface could be satisfied by a
 * single curve (albeit a much more complex one). Composites are intended for use as attribute
 * values in datasets in which the underlying geometry has been decomposed, usually to expose its
 * topological nature.
 *
 * @UML type GM_Composite
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface Composite extends Complex {
    /**
     * Returns a homogeneous collection of {@link Primitive}s whose union would be the core
     * geometry of the composite. The complex would include all primitives in the generator
     * and all primitives on the boundary of these primitives, and so forth until
     * {@link org.opengis.gm.primitive.Point}s are included. Thus the
     * <code>generators</code> on <code>Composite</code> is a subset of the
     * {@link Complex#getElements elements} on {@link Complex}.
     *
     * @return The list of primitives in this composite.
     * @UML association generator
     */
    public List/*<Primitive>*/ getGenerators();
}
