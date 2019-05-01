/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.geometry.primitive.CurveInterpolation;
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
 * {@linkplain #getControlPoints control points} (C⁰), but does
 * not require derivative information (degree - 2 = -1).
 * <p>
 * NOTE: The major difference between the polynomial splines and the b-splines (basis splines)
 * is that polynomial splines pass through their control points, making the control point and
 * sample point array identical.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
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
