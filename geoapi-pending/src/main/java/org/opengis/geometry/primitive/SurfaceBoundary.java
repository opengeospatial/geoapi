/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The boundary of {@linkplain Surface surfaces}. A {@code SurfaceBoundary} consists of some number
 * of {@linkplain Ring rings}, corresponding to the various components of its boundary. In the normal 2D
 * case, one of these rings is distinguished as being the exterior boundary. In a general manifold this
 * is not always possible, in which case all boundaries shall be listed as interior boundaries,
 * and the exterior will be empty.
 *
 * <div class="note"><b>Note:</b>
 * the use of exterior and interior here is not intended to invoke the
 * definitions of "interior" and "exterior" of geometric objects. The terms are in common usage,
 * and reflect a linguistic metaphor that uses the same linguistic constructs for the concept of
 * being inside an object to being inside a container. In normal mathematical terms, the exterior
 * boundary is the one that appears in the Jordan Separation Theorem (Jordan Curve Theorem extended
 * beyond 2D). The exterior boundary is the one that separates the surface (or solid in 3D) from
 * infinite space. The interior boundaries separate the object at hand from other bounded objects.
 * The uniqueness of the exterior comes from the uniqueness of unbounded space. Essentially, the
 * Jordan Separation Theorem shows that normal 2D or 3D space separates into bounded and unbounded
 * pieces by the insertion of a ring or shell, respectively. It goes beyond that, but this
 * specification is restricted to at most 3 dimensions.
 * <p>
 * <strong>EXAMPLE 1:</strong> If the underlying manifold is an infinite cylinder, then two
 * transverse cuts of the cylinder define a compact surface between the cuts, and two separate
 * unbounded portions of the cylinders. In this case, either cut could reasonably be called
 * exterior. In cases of such ambiguity, the standard chooses to list all boundaries in the
 * "interior" set. The only guarantee of an exterior boundary being unique is in the 2-dimensional
 * plane, E².
 * <p>
 * <strong>EXAMPLE 2:</strong> Taking the equator of a sphere, and generating a 1 meter buffer,
 * we have a surface with two isomorphic boundary components. There is no unbiased manner to
 * distinguish one of these as an exterior.
 * </div>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see SolidBoundary
 */
@UML(identifier="GM_SurfaceBoundary", specification=ISO_19107)
public interface SurfaceBoundary extends PrimitiveBoundary {
    /**
     * Returns the exterior ring, or {@code null} if none.
     *
     * @return the exterior ring, or {@code null}.
     */
    @UML(identifier="exterior", obligation=MANDATORY, specification=ISO_19107)
    Ring getExterior();

    /**
     * Returns the interior rings.
     *
     * @return the interior rings. Never {@code null}, but may be an empty array.
     *
     * @todo Consider using a Collection return type instead.
     */
    @UML(identifier="interior", obligation=MANDATORY, specification=ISO_19107)
    List<Ring> getInteriors();
}
