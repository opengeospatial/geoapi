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

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Geometry;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Geometry that is an aggregate of other geometries.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
///@UML (identifier="GM_Aggregate")
public interface Aggregate extends Geometry {
    /**
     * Returns the {@linkplain Set} containing the elements that compose this
     * aggregate.  The {@linkplain Set} may be modified if this geometry is
     * {@linkplain #isMutable mutable}.
     */
/// @UML (identifier="element", obligation=MANDATORY)
    public Set/*<Geometry>*/ getElements();
}
