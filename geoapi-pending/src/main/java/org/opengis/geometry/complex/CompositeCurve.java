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
package org.opengis.geometry.complex;

import java.util.List;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain Complex complex} with all the geometric properties of a curve. Thus, this
 * composite can be considered as a type of {@linkplain OrientableCurve orientable curve}.
 * Essentially, a composite curve is a list of {@linkplain OrientableCurve orientable curves}
 * agreeing in orientation in a manner such that each curve (except the first) begins where
 * the previous one ends.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @todo This interface extends (indirectly) both {@link org.opengis.geometry.primitive.Primitive} and
 *       {@link org.opengis.geometry.complex.Complex}. Consequently, there is a clash in the semantics
 *       of some set theoretic operation. Specifically, {@code Primitive.contains(…)}
 *       (returns FALSE for end points) is different from {@code Complex.contains(…)}
 *       (returns TRUE for end points).
 */
@UML(identifier="GM_CompositeCurve", specification=ISO_19107)
public interface CompositeCurve extends Composite, OrientableCurve {
    /**
     * Returns the list of orientable curves in this composite.
     * To get a full representation of the elements in the {@linkplain Complex complex},
     * the {@linkplain org.opengis.geometry.primitive.Point points} on the boundary of the
     * generator set of {@linkplain org.opengis.geometry.primitive.Curve curve} would be
     * added to the curves in the generator list.
     *
     * @return the list of orientable curves in this composite.
     *
     * @see OrientableCurve#getComposite
     */
    @UML(identifier="generator", obligation=MANDATORY, specification=ISO_19107)
    List<OrientableCurve> getGenerators();
}
