/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.complex;

import java.util.Set;
import org.opengis.geometry.primitive.Solid;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain Complex complex} with all the geometric properties of a solid. Essentially,
 * a composite solid is a set of solids that join in pairs on common boundary surfaces to form
 * a single solid.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.1
 *
 * @todo This interface extends (indirectly) both {@link org.opengis.geometry.primitive.Primitive} and
 *       {@link org.opengis.geometry.complex.Complex}. Consequently, there is a clash in the semantics
 *       of some set theoretic operation. Specifically, {@code Primitive.contains(…)}
 *       (returns FALSE for end points) is different from {@code Complex.contains(…)}
 *       (returns TRUE for end points).
 */
@UML(identifier="GM_CompositeSurface", specification=ISO_19107)
public interface CompositeSolid extends Composite, Solid {
    /**
     * Returns the set of solids that form the core of this complex.
     * To get a full representation of the elements in the {@linkplain Complex complex},
     * the {@linkplain org.opengis.geometry.primitive.Surface surfaces},
     * {@linkplain org.opengis.geometry.primitive.Curve curves} and
     * {@linkplain org.opengis.geometry.primitive.Point points} on the boundary of the
     * generator set if {@linkplain Solid solids} would have to be added to the generator list.
     *
     * @return the set of solids in this composite.
     *
     * @see Solid#getComposite
     */
    @UML(identifier="generator", obligation=MANDATORY, specification=ISO_19107)
    Set<Solid> getGenerators();
}
