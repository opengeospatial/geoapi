/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Boundary;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * The boundary of {@linkplain Primitive primitive} objects. This is the root for the various
 * return types of the {@link org.opengis.spatialschema.geometry.Geometry#getBoundary getBoundary()} method for
 * subtypes of {@link Primitive}. Since points have no boundary, no special subclass is needed
 * for their boundary.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
///@UML (identifier="GM_PrimitiveBoundary")
public interface PrimitiveBoundary extends Boundary {
}
