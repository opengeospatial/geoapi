/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.complex.Complex;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The abstract root data type for all the data types used to represent the boundary of geometric
 * objects. Any subclass of {@link Geometry} will use a subclass of <code>Boundary</code> to
 * represent its boundary through the operation {@link Geometry#getBoundary}. By the nature of
 * geometry, boundary objects are cycles.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @since 1.0
 */
@UML (identifier="GM_Boundary", specification=ISO_19107)
public interface Boundary extends Complex {
    /**
     * Always returns <code>true</code> since boundary objects are cycles.
     *
     * @return Always <code>true</code>.
     */
    boolean isCycle();
}
