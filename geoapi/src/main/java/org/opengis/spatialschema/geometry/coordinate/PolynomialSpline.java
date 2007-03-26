/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
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
 * A polynimal spline.
 * An "<var>n</var><sup>th</sup> degree" polynomial spline shall be defined piecewise as an
 * <var>n</var>-degree polynomial, with up to C<sup><var>n</var>-1</sup> continuity
 * at the control points where the defining polynomial changes. This level of continuity is
 * controlled by the attribute {@link #getNumDerivativesInterior numDerivativesInterior}.
 * Parameters shall include directions for as many as degree - 2 derivatives of the polynomial
 * at the start and end point of the segment. {@link LineString} is equivalent to a
 * 1<sup>st</sup> degree polynomial spline. It has simple continuity at the
 * {@linkplain #getControlPoints control points} (C</sup>0</sup>), but does
 * not require derivative information (degree - 2 = -1).
 * <p>
 * NOTE: The major difference between the polynomial splines and the b-splines (basis splines)
 * is that polynomial splines pass through their control points, making the control point and
 * sample point array identical.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_PolynomialSpline", specification=ISO_19107)
public interface PolynomialSpline extends SplineCurve {
    /**
     * The interpolation mechanism for a {@code PolynomialSpline}
     * is {@link CurveInterpolation#POLYNOMIAL_SPLINE POLYNOMIAL_SPLINE}.
     */
    @UML(identifier="interpolation", obligation=MANDATORY, specification=ISO_19107)
    CurveInterpolation getInterpolation();

    /**
     * The values used for the initial derivative (up to {@linkplain #getDegree degree} - 2)
     * used for interpolation in this {@code PolynomialSpline} at the start point
     * of the spline.
     *
     * The {@linkplain List#size size} of the returned list is
     * <code>({@linkplain #getDegree degree} - 2)</code>.
     */
    @UML(identifier="vectorAtStart", obligation=MANDATORY, specification=ISO_19107)
    List/*double[]*/ getVectorAtStart();

    /**
     * The values used for the final derivative (up to {@linkplain #getDegree degree} - 2)
     * used for interpolation in this {@code PolynomialSpline} at the end point of
     * the spline.
     *
     * The {@linkplain List#size size} of the returned list is
     * <code>({@linkplain #getDegree degree} - 2)</code>.
     */
    @UML(identifier="vectorAtEnd", obligation=MANDATORY, specification=ISO_19107)
    List/*double[]*/ getVectorAtEnd();
}
