/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// J2SE direct dependencies
import java.util.List;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A polyhedral surface that is composed only of {@linkplain Triangle triangles}.
 * There is no restriction on how the triangulation is derived.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface TriangulatedSurface extends PolyhedralSurface {
    /**
     * Associates this surface with its individual triangles.
     */
/// @UML (identifier="patch", obligation=MANDATORY)
    List/*<Triangle>*/ getPatches();
}
