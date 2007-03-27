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
import org.opengis.geometry.primitive.Surface;


/**
 * A surface composed of {@linkplain Polygon polygon surfaces} connected along their common
 * boundary curves. This differs from {@link Surface} only in the restriction on the types of
 * surface patches acceptable. 
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @see GeometryFactory#createPolyhedralSurface
 */
@UML(identifier="GM_PolyhedralSurface", specification=ISO_19107)
public interface PolyhedralSurface extends Surface {
    /**
     * Associates this surface with its individual facet polygons.
     */
    @UML(identifier="patch", obligation=MANDATORY, specification=ISO_19107)
    List<? extends Polygon> getPatches();
}
