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
package org.opengis.geometry.primitive;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * List of codes that may be used to identify the interpolation mechanisms.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@Vocabulary(capacity=10)
@UML(identifier="GM_SurfaceInterpolation", specification=ISO_19107)
public final class SurfaceInterpolation extends CodeList<SurfaceInterpolation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8717227444427181227L;

    /**
     * The interior of the surface is not specified. The assumption is that the surface
     * follows the reference surface defined by the coordinate reference system.
     */
    @UML(identifier="none", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation NONE = new SurfaceInterpolation(
                                            "NONE");

    /**
     * The interpolation method shall return points on a single plane. The boundary in this
     * case shall be contained within that plane.
     */
    @UML(identifier="planar", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation PLANAR = new SurfaceInterpolation(
                                            "PLANAR");

    /**
     * The surface is a section of a spherical surface.
     */
    @UML(identifier="spherical", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation SPHERICAL = new SurfaceInterpolation(
                                            "SPHERICAL");

    /**
     * The surface is a section of a elliptical surface.
     */
    @UML(identifier="elliptical", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation ELLIPTICAL = new SurfaceInterpolation(
                                            "ELLIPTICAL");

    /**
     * The surface is a section of a conic surface.
     */
    @UML(identifier="conic", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation CONIC = new SurfaceInterpolation(
                                            "CONIC");

    /**
     * The control points are organized into adjoining triangles, which form small planar segments.
     */
    @UML(identifier="tin", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation TIN = new SurfaceInterpolation(
                                            "TIN");

    /**
     * The control points are organized into a 2-dimensional grid and each cell
     * within the grid is spanned by a surface which shall be defined by a family of curves.
     */
    @UML(identifier="parametricCurve", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation PARAMETRIC_CURVE = new SurfaceInterpolation(
                                            "PARAMETRIC_CURVE");

    /**
     * The control points are organized into an irregular 2-dimensional grid and
     * each cell within this grid is spanned by a polynomial spline function.
     */
    @UML(identifier="polynomialSpline", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation POLYNOMIAL_SPLINE = new SurfaceInterpolation(
                                            "POLYNOMIAL_SPLINE");

    /**
     * The control points are organized into an irregular 2-dimensional grid and each cell
     * within this grid is spanned by a rational (quotient of polynomials) spline function.
     */
    @UML(identifier="rationalSpline", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation RATIONAL_SPLINE = new SurfaceInterpolation(
                                            "RATIONAL_SPLINE");

    /**
     * The control points are organized into adjoining triangles, each of
     * which is spanned by a polynomial spline function.
     */
    @UML(identifier="triangulatedSpline", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SurfaceInterpolation TRIANGULATED_SPLINE = new SurfaceInterpolation(
                                            "TRIANGULATED_SPLINE");

    /**
     * Constructs an element of the given name.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by another element of this type.
     */
    private SurfaceInterpolation(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code SurfaceInterpolation}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static SurfaceInterpolation[] values() {
        return values(SurfaceInterpolation.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public SurfaceInterpolation[] family() {
        return values();
    }

    /**
     * Returns the surface interpolation that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static SurfaceInterpolation valueOf(String code) {
        return valueOf(SurfaceInterpolation.class, code, SurfaceInterpolation::new).get();
    }
}
