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
package org.opengis.geometry.complex;

import java.util.Collection;
import org.opengis.geometry.primitive.Primitive;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A geometric complex with an underlying core geometry that is isomorphic to a primitive. Thus,
 * a composite curve is a collection of curves whose geometry interface could be satisfied by a
 * single curve (albeit a much more complex one). Composites are intended for use as attribute
 * values in datasets in which the underlying geometry has been decomposed, usually to expose its
 * topological nature.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_Composite", specification=ISO_19107)
public interface Composite extends Complex {
    /**
     * Returns a homogeneous collection of {@linkplain Primitive primitives} whose union would be
     * the core geometry of the composite. The complex would include all primitives in the generator
     * and all primitives on the boundary of these primitives, and so forth until
     * {@linkplain org.opengis.geometry.primitive.Point points} are included. Thus the
     * {@code generators} on {@code Composite} is a subset of the
     * {@linkplain Complex#getElements elements} on {@linkplain Complex complex}.
     *
     * @return the list of primitives in this composite.
     *
     * @see CompositePoint#getGenerators
     * @see CompositeCurve#getGenerators
     * @see CompositeSurface#getGenerators
     * @see CompositeSolid#getGenerators
     */
    @UML(identifier="generator", obligation=MANDATORY, specification=ISO_19107)
    Collection<? extends Primitive> getGenerators();
}
