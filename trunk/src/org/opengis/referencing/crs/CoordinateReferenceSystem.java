/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.referencing.ReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Abstract coordinate reference system, usually defined by a coordinate system and a datum.
 *
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
///@UML (identifier="SC_CRS")
public interface CoordinateReferenceSystem extends ReferenceSystem {
    /**
     * Returns the coordinate system. One of {@link CoordinateSystem coordinate system}
     * sub-interfaces is associated with {@linkplain SingleCRS single CRS}. Other CRS
     * like {@linkplain CompoundCRS compound CRS} may also provides a synthetic coordinate
     * system in order to allow users to access to two commonly requested information:
     * {@linkplain CoordinateSystem#getDimension dimension} and
     * {@linkplain CoordinateSystem#getAxis axis}.
     */
    CoordinateSystem getCoordinateSystem();
}
