/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.geometry.primitive;

import org.opengis.annotation.Association;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basis for 3-dimensional geometry. The extent of a solid is
 * defined by the boundary surfaces.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see PrimitiveFactory#createSolid
 *
 * @todo Some associations are commented out for now.
 */
@UML(identifier="GM_Solid", specification=ISO_19107)
public interface Solid extends Primitive {
    /**
     * Returns a sequence of sets of {@linkplain Surface surfaces} that limit the extent of this
     * {@code Solid}. These surfaces shall be organized into one set of surfaces for each
     * boundary component of this {@code Solid}. Each of these shells shall be a cycle
     * (closed composite surface without boundary).
     *
     * <div class="note"><b>Note:</b>
     * The exterior shell of a solid is defined only because the embedding
     * coordinate space is always a 3D Euclidean one. In general, a solid in a bounded 3-dimensional
     * manifold has no distinguished exterior boundary. In cases where "exterior" boundary is not
     * well defined, all the shells of the {@linkplain SolidBoundary solid boundary} shall be
     * listed as "interior".
     * </div>
     *
     * The {@linkplain OrientableSurface orientable surfaces} that bound a solid shall be oriented
     * outward - that is, the "top" of each {@linkplain Surface surface} as defined by its orientation
     * shall face away from the interior of the solid. Each {@linkplain Shell shell}, when viewed as
     * a composite surface, shall be a cycle.
     *
     * @return the sets of positions on the boundary.
     */
    @UML(identifier="boundary", obligation=MANDATORY, specification=ISO_19107)
    SolidBoundary getBoundary();

    /**
     * Returns the sum of the surface areas of all of the boundary components of a solid.
     *
     * @return the area of this solid.
     *
     * @todo In UML diagram, this operation has an {@code Area} return type.
     */
    @UML(identifier="area", obligation=MANDATORY, specification=ISO_19107)
    double getArea();

    /**
     * Returns the volume of this solid. This is the volume interior to the exterior
     * boundary shell minus the sum of the volumes interior to any interior boundary
     * shell.
     *
     * @return the volume of this solid.
     *
     * @todo In UML diagram, this operation has a {@code Volume} return type.
     */
    @UML(identifier="volume", obligation=MANDATORY, specification=ISO_19107)
    double getVolume();

    /**
     * Returns always {@code null}, since solids have no proxy.
     *
     * @issue https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-63
     */
    @Association("Oriented")
    @UML(identifier="proxy", obligation=FORBIDDEN, specification=ISO_19107)
    OrientablePrimitive[] getProxy();

//    public org.opengis.geometry.complex.GM_CompositeSolid[] getComposite() { return null; }
}
