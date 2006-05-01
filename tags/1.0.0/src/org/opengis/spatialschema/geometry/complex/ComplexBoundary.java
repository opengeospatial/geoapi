/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.complex;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Boundary;


/**
 * The boundary of {@linkplain Complex complex} objects. The
 * {@link org.opengis.spatialschema.geometry.Geometry#getBoundary getBoundary()} method for {@link Complex}
 * objects shall return a <code>ComplexBoundary</code>, which is a collection of primitives
 * and a {@linkplain Complex complex} of dimension 1 less than the original object.
 *
 * @UML type GM_ComplexBoundary
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface ComplexBoundary extends Boundary {
}
