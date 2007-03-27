/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.geometry.coordinate;

// J2SE direct dependencies
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19107;

import java.util.List;

import org.opengis.annotation.UML;


/**
 * A polyhedral surface that is composed only of {@linkplain Triangle triangles}.
 * There is no restriction on how the triangulation is derived.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_TriangulatedSurface", specification=ISO_19107)
public interface TriangulatedSurface extends PolyhedralSurface {
    /**
     * Associates this surface with its individual triangles.
     */
    @UML(identifier="patch", obligation=MANDATORY, specification=ISO_19107)
    List<Triangle> getPatches();
}
