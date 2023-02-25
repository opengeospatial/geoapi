/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
     * the exterior shell of a solid is defined only because the embedding
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
     */
    @UML(identifier="proxy", obligation=FORBIDDEN, specification=ISO_19107)
    OrientablePrimitive[] getProxy();

//    public org.opengis.geometry.complex.GM_CompositeSolid[] getComposite() { return null; }
}
