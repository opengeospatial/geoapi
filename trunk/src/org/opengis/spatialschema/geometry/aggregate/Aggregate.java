/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.aggregate;

// OpenGIS direct dependencies
import java.util.Set;

import org.opengis.spatialschema.geometry.Geometry;

/**
 * Geometry that is an aggregate of other geometries.
 */
///@UML (identifier="GM_Aggregate")
public interface Aggregate extends Geometry {
    /**
     * Returns the {@linkplain Set} containing the elements that compose this
     * aggregate.  The {@linkplain Set} may be modified if this geometry is
     * mutable.
     */
/// @UML (identifier="element", obligation=MANDATORY)
    public Set/*<Geometry>*/ getElements();
}
