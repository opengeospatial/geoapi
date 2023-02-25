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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The boundary of {@linkplain Solid solids}. Solid boundaries are similar to
 * {@linkplain SurfaceBoundary surface boundaries}. In normal 3-dimensional Euclidean
 * space, one {@linkplain Shell shell} is distinguished as the exterior. In the more
 * general case, this is not always possible.
 *
 * <div class="note"><b>Note:</b>
 * an alternative use of solids with no external shell would be to define
 * "complements" of finite solids. These infinite solids would have only interior boundaries. If
 * this specification is extended to 4D Euclidean space, or if 3D compact manifolds are used
 * (probably not in geographic information), then other examples of bounded solids without exterior
 * boundaries are possible.
 * </div>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see SurfaceBoundary
 */
@UML(identifier="GM_SolidBoundary", specification=ISO_19107)
public interface SolidBoundary extends PrimitiveBoundary {
    /**
     * Returns the exterior shell, or {@code null} if none.
     *
     * @return the exterior shell, or {@code null}.
     */
    @UML(identifier="exterior", obligation=MANDATORY, specification=ISO_19107)
    Shell getExterior();

    /**
     * Returns the interior shells.
     *
     * @return the interior shells. Never {@code null}, but may be an empty array.
     */
    @UML(identifier="interior", obligation=MANDATORY, specification=ISO_19107)
    Shell[] getInteriors();
}
