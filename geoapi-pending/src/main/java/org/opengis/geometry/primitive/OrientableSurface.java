/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
     * the concept of exterior boundary for a surface is really only
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
     */
    @UML(identifier="composite", obligation=OPTIONAL, specification=ISO_19107)
    CompositeSurface getComposite();
}
