/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;

// OpenGIS direct dependencies
import org.opengis.rs.ReferenceSystem;


/**
 * Abstract coordinate reference system, usually defined by a coordinate system and a datum.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit Is this class really needed? It stands between {@link org.opengis.rs.ReferenceSystem}
 *          and {@link CoordinateReferenceSystem}, but doesn't bring much. Actually, it looks like
 *          an anomaly in an otherwise natural hierarchy (<code>CoordinateReferenceSystem extends
 *          ReferenceSystem</code>). The only purpose for <code>CRS</code> seems to be to give to
 *          {@link CompoundCRS} an ancestor different from all other <code>FooCRS</code> classes.
 *          Together with the {@link CompoundCRS#getCRS} return type, it enforces the compound CRS
 *          contract (contains any CRS except other compound CRS). But is this contract really a
 *          good one? Why not lets the choice to the user/implementer? It would both simplify the
 *          API and allow some useful cases. It is easy to implements <code>CompoundCRS</code>
 *          containing others <code>CompoundCRS</code> (except for
 *          {@link CoordinateReferenceSystem#getCoordinateSystem getCoordinateSystem()} and
 *          {@link CoordinateReferenceSystem#getDatum getDatum()} methods), and I can see use
 *          cases where it may be useful. For example if a <code>CompoundCRS</code> is used as
 *          a building block for some others <code>CompoundCRS</code>, it is useful to get back
 *          the original <code>CompoundCRS</code> and compare it with some predefined CS using
 *          {@link Object#equals}. Predefined <code>CompoundCRS</code> may be common in user's
 *          code, since it is often set for a particular problem.
 */
public interface CRS extends ReferenceSystem {
}
