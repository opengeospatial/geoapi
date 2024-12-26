/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The type of a B-spline.
 * A B-spline is uniform if and only if all knots are of {@linkplain Knot#getMultiplicity
 * multiplicity 1} and they differ by a positive constant from the preceding knot. A B-spline
 * is quasi-uniform if and only if the knots are of multiplicity (degree+1) at the ends, of
 * multiplicity 1 elsewhere, and they differ by a positive constant from the preceding knot.
 * This code list is used to describe the distribution of knots in the parameter space of
 * various splines.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Vocabulary(capacity=3)
@UML(identifier="GM_KnotType", specification=ISO_19107)
public class KnotType extends CodeList<KnotType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -431722533158166557L;

    /**
     * The form of knots is appropriate for a uniform B-spline.
     */
    @UML(identifier="uniform", obligation=CONDITIONAL, specification=ISO_19107)
    public static final KnotType UNIFORM = new KnotType("UNIFORM");

    /**
     * The form of knots is appropriate for a quasi-uniform B-spline.
     */
    @UML(identifier="quasiUniform", obligation=CONDITIONAL, specification=ISO_19107)
    public static final KnotType QUASI_UNIFORM = new KnotType("QUASI_UNIFORM");

    /**
     * The form of knots is appropriate for a piecewise Bezier curve.
     */
    @UML(identifier="piecewiseBezier", obligation=CONDITIONAL, specification=ISO_19107)
    public static final KnotType PIECEWISE_BEZIER = new KnotType("PIECEWISE_BEZIER");

    /**
     * Constructs an element of the given name.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by another element of this type.
     */
    private KnotType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code KnotType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static KnotType[] values() {
        return values(KnotType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public KnotType[] family() {
        return values();
    }

    /**
     * Returns the knot type that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static KnotType valueOf(String code) {
        return valueOf(KnotType.class, code, KnotType::new).get();
    }
}
