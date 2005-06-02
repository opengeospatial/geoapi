/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.CurveInterpolation;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Cubic splines.
 * Cubic splines are similar to line strings in that they are a sequence of segments each with
 * its own defining function. A cubic spline uses the control points and a set of derivative
 * parameters to define a piecewise 3rd degree polynomial interpolation. Unlike line-strings,
 * the parameterization by arc length is not necessarily still a polynomial. Splines have two
 * parameterizations that are used in this specification, the defining one (constructive
 * parameter) and the one that has been reparameterized by arc length to satisfy the requirements
 * in {@link GenericCurve}.
 * <p>
 * The function describing the curve must be C<sup>2</sup>, that is, have a continuous
 * 1<sup>st</sup> and 2<sup>nd</sup> derivative at all points, and pass through the
 * {@linkplain #getControlPoints control points} in the order given. Between the control points,
 * the curve segment is defined by a cubic polynomial. At each control point, the polynomial
 * changes in such a manner that the 1<sup>st</sup> and 2<sup>nd</sup> derivative vectors are
 * the same from either side. The control parameters record must contain
 * {@link #getVectorAtStart vectorAtStart}, and {@link #getVectorAtEnd vectorAtEnd}
 * which are the unit tangent vectors at {@code controlPoint[0]} and <code>controlPoint[n]</coded>
 * where <var>n</var> = {@code controlPoint.length-1}.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_CubicSpline", specification=ISO_19107)
public interface CubicSpline extends PolynomialSpline {
    /**
     * The interpolation mechanism for a {@code CubicSpline}
     * is {@link CurveInterpolation#CUBIC_SPLINE CUBIC_SPLINE}.
     */
    @UML(identifier="interpolation", obligation=MANDATORY, specification=ISO_19107)
    CurveInterpolation getInterpolation();

    /**
     * The values used for the initial derivative.
     * The restriction on {@code vectorAtStart} and {@code vectorAtEnd} reduce these
     * sequences to a single tangent vector each. Consequently, the {@linkplain List#size size}
     * of the returned list is 1.
     */
    @UML(identifier="vectorAtStart", obligation=MANDATORY, specification=ISO_19107)
    List/*double[]*/ getVectorAtStart();

    /**
     * The values used for the final derivative.
     * The restriction on {@code vectorAtStart} and {@code vectorAtEnd} reduce these
     * sequences to a single tangent vector each. Consequently, the {@linkplain List#size size}
     * of the returned list is 1.
     */
    @UML(identifier="vectorAtEnd", obligation=MANDATORY, specification=ISO_19107)
    List/*double[]*/ getVectorAtEnd();
}
