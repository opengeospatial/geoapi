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

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.Surface;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A surface composed of {@linkplain Polygon polygon surfaces} connected along their common
 * boundary curves. This differs from {@link Surface} only in the restriction on the types of
 * surface patches acceptable. 
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see GeometryFactory#createPolyhedralSurface
 */
public interface PolyhedralSurface extends Surface {
    /**
     * Associates this surface with its individual facet polygons.
     */
/// @UML (identifier="patch", obligation=MANDATORY)
    List/*<? extends Polygon>*/ getPatches();
}
