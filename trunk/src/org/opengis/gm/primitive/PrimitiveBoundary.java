/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;

// OpenGIS direct dependencies
import org.opengis.gm.Boundary;


/**
 * The boundary of {@link Primitive} objects. This is the root for the various return types of the
 * {@link org.opengis.gm.Geometry#getBoundary getBoundary()} method for subtypes of {@link Primitive}.
 * Since points have no boundary, no special subclass is needed for their boundary.
 *
 * @UML type GM_PrimitiveBoundary
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface PrimitiveBoundary extends Boundary {
}
