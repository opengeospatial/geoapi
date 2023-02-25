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

import org.opengis.geometry.complex.CompositeCurve;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A curve and an orientation inherited from {@link OrientablePrimitive}. If the orientation is
 * positive, then the {@code OrientableCurve} is a {@linkplain Curve curve}. If the orientation
 * is negative, then the {@code OrientableCurve} is related to another {@linkplain Curve curve}
 * with a parameterization that reverses the sense of the curve traversal.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_OrientableCurve", specification=ISO_19107)
public interface OrientableCurve extends OrientablePrimitive {
    /**
     * Returns an ordered pair of points, which are the start point and end point of the curve.
     * If the curve is closed, then the boundary shall be empty.
     *
     * @return the sets of positions on the boundary.
     */
    @UML(identifier="boundary", obligation=MANDATORY, specification=ISO_19107)
    CurveBoundary getBoundary();

    /**
     * Returns the primitive associated with this {@code OrientableCurve}.
     *
     * @return the primitive, or {@code null} if the association is
     *         not available or not implemented that way.
     *
     * @see Curve#getProxy
     */
    @UML(identifier="primitive", obligation=OPTIONAL, specification=ISO_19107)
    Curve getPrimitive();

    /**
     * Returns the owner of this orientable curve. This method is <em>optional</em> since
     * the association in ISO 19107 is navigable only from {@code CompositeCurve} to
     * {@code OrientableCurve}, not the other way.
     *
     * @return the owner of this orientable curve, or {@code null} if the association is
     *         not available or not implemented that way.
     *
     * @see CompositeCurve#getGenerators
     */
    @UML(identifier="composite", obligation=OPTIONAL, specification=ISO_19107)
    CompositeCurve getComposite();
}
