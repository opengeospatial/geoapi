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

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Boundary;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The boundary of {@linkplain Complex complex} objects. The
 * {@link org.opengis.spatialschema.geometry.Geometry#getBoundary getBoundary()} method for {@link Complex}
 * objects shall return a <code>ComplexBoundary</code>, which is a collection of primitives
 * and a {@linkplain Complex complex} of dimension 1 less than the original object.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 */
@UML (identifier="GM_ComplexBoundary", specification=ISO_19107)
public interface ComplexBoundary extends Boundary {
}
