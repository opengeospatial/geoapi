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

import org.opengis.geometry.complex.CompositeSurface;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A surface and an orientation inherited from {@link OrientablePrimitive}. If the orientation is
 * positive, then the {@code OrientableSurface} is a {@linkplain Surface surface}. If the
 * orientation is negative, then the {@code OrientableSurface} is a reference to a
 * {@linkplain Surface surface} with an upNormal that reverses the direction for this
 * {@code OrientableSurface}, the sense of "the top of the surface".
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_OrientableSurface", specification=ISO_19107)
public interface OrientableSurface extends OrientablePrimitive {
    /**
     * Returns the set of circular sequences of {@linkplain OrientableCurve orientable curve} that
     * limit the extent of this {@code OrientableSurface}. These curves shall be organized
     * into one circular sequence of curves for each boundary component of the
     * {@code OrientableSurface}. In cases where "exterior" boundary is not
     * well defined, all the rings of the {@linkplain SurfaceBoundary surface boundary}
     * shall be listed as "interior".
     *
     * <div class="note"><b>Note:</b>
     * The concept of exterior boundary for a surface is really only
     * valid in a 2-dimensional plane. A bounded cylinder has two boundary components, neither
     * of which can logically be classified as its exterior. Thus, in 3 dimensions, there is no
     * valid definition of exterior that covers all cases.
     * </div>
     *
     * @return the sets of positions on the boundary.
     */
    @UML(identifier="boundary", obligation=MANDATORY, specification=ISO_19107)
    SurfaceBoundary getBoundary();

    /**
     * Returns the primitive associated with this {@code OrientableSurface}.
     *
     * @return the primitive, or {@code null} if the association is
     *         not available or not implemented that way.
     *
     * @see Surface#getProxy
     * @issue https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-63
     */
    @UML(identifier="primitive", obligation=OPTIONAL, specification=ISO_19107)
    Surface getPrimitive();

    /**
     * Returns the owner of this orientable surface. This method is <em>optional</em> since
     * the association in ISO 19107 is navigable only from {@code CompositeSurface} to
     * {@code OrientableSurface}, not the other way.
     *
     * @return the owner of this orientable surface, or {@code null} if the association is
     *         not available or not implemented that way.
     *
     * @see CompositeSurface#getGenerators
     * @issue https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-63
     */
    @UML(identifier="composite", obligation=OPTIONAL, specification=ISO_19107)
    CompositeSurface getComposite();
}
