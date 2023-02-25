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

import org.opengis.geometry.complex.CompositeCurve;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represent a single connected component of a {@linkplain SurfaceBoundary surface boundary}.
 * It consists of a number of references to {@linkplain OrientableCurve orientable curves}
 * connected in a cycle (an object whose boundary is empty). A {@code Ring} is structurally
 * similar to a {@linkplain CompositeCurve composite curve} in that the end point of each
 * {@linkplain OrientableCurve orientable curve} in the sequence is the start point of the next
 * {@linkplain OrientableCurve orientable curve} in the sequence. Since the sequence is circular,
 * there is no exception to this rule. Each ring, like all boundaries is a cycle and each ring is
 * simple.
 * <p>
 * Even though each {@code Ring} is simple, the boundary need not be simple. The easiest
 * case of this is where one of the interior rings of a surface is tangent to its exterior ring.
 * Implementations may enforce stronger restrictions on the interaction of boundary elements.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see SurfaceBoundary
 * @see Shell
 */
@UML(identifier="GM_Ring", specification=ISO_19107)
public interface Ring extends CompositeCurve {
    /**
     * Always returns {@code true} since ring objects are simples.
     *
     * @return always {@code true}.
     */
    @UML(identifier="isSimple", obligation=MANDATORY, specification=ISO_19107)
    boolean isSimple();
}
