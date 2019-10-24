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

import org.opengis.geometry.Geometry;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Any general conic curve. Any of the conic section curves can be canonically represented
 * in polar co-ordinates (&rho;, φ) as:
 *
 * <img src="doc-files/ConicCurve.png" alt="Conic curve">
 *
 * <p>where "<var>P</var>" is semi-latus rectum and "<var>e</var>" is the eccentricity. This gives
 * a conic with focus at the pole (origin), and the vertex on the conic nearest this focus in the
 * direction of the polar axis, <var>φ</var>=0.</p>
 *
 * For <var>e</var>=0, this is a circle.
 * For 0&nbsp;&lt;&nbsp;<var>e</var>&nbsp;&lt;&nbsp;1, this is an ellipse.
 * For <var>e</var>=1, this is a parabola.
 * For <var>e</var>&gt;1, this is one branch of a hyperbola.
 *
 *
 * <p>These generic conics can be viewed in a two-dimensional Cartesian parameter space
 * (<var>u</var>,&nbsp;<var>v</var>) given by the usual coordinate conversions
 * <var>u</var>=<var>&rho;</var>cos(<var>φ</var>) and
 * <var>v</var>=<var>&rho;</var>sin(<var>φ</var>).
 * We can then convert this to a 3D coordinate reference system by using an affine transformation,
 * (<var>u</var>,&nbsp;<var>v</var>) → (<var>x</var>,&nbsp;<var>y</var>,&nbsp;<var>z</var>)
 * which is defined by:</p>
 *
 * (TODO: paste the matrix here, same as AffinePlacement)
 *
 * <p>This gives us <var>φ</var> as the constructive parameter.
 * The {@linkplain DirectPosition direct position} given by
 * (<var>x₀</var>, <var>y₀</var>, <var>z₀</var>)
 * is the image of the origin in the local coordinate space (<var>u</var>, <var>v</var>)
 * Alternatively, the origin may be shifted to the vertex of the conic as</p>
 *
 * <p><var>u'</var> = <var>&rho;</var>cos(<var>φ</var>) - P/(1 + <var>e</var>)
 * &nbsp;&nbsp;and&nbsp;&nbsp;
 * <var>v'</var> = <var>&rho;</var>sin(<var>φ</var>)</p>
 *
 * <p>and <var>v</var> can be used as the constructive parameter.
 * In general, conics with small eccentricity and small <var>P</var>, use the first or
 * "central" representation. Those with large eccentricity or large <var>P</var> tend
 * to use the second or "linear" representation.</p>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_Conic", specification=ISO_19107)
public interface Conic extends CurveSegment {
    /**
     * Returns an affine transformation object that maps the conic from parameter space into the
     * coordinate space of the target coordinate reference system of the conic corresponding to
     * the coordinate reference system of the {@linkplain Geometry}. This affine transformation
     * is given by the formulae in the class description.
     */
    @UML(identifier="position", obligation=MANDATORY, specification=ISO_19107)
    AffinePlacement getPosition();

    /**
     * Returns {@code false} if the affine transformation is used on the unshifted
     * (<var>u</var>, <var>v</var>) and {@code true} if the affine transformation
     * is applied to the shifted parameters (<var>u'</var>, <var>v'</var>). This controls
     * whether the focus or the vertex of the conic is at the origin in parameter space.
     */
    @UML(identifier="shifted", obligation=MANDATORY, specification=ISO_19107)
    boolean isShifted();

    /**
     * Returns the value of the eccentricity parameter "<var>e</var>" used in the defining
     * equation above. It controls the general shape of the curve, determining whether the
     * curve is a circle, ellipse, parabola, or hyperbola.
     */
    @UML(identifier="eccentricity", obligation=MANDATORY, specification=ISO_19107)
    double getEccentricity();

    /**
     * Returns the value of the parameter "<var>P</var>" used in the defining equation
     * above. It controls how broad the conic is at each of its foci.
     */
    @UML(identifier="semiLatusRectum", obligation=MANDATORY, specification=ISO_19107)
    double getSemiLatusRectum();

    /**
     * Return the start point parameter used in the constructive paramerization.
     * The following relation must be hold:
     *
     * <p><code>{@linkplain #forConstructiveParam forConstructiveParam}(getStartConstructiveParam())
     * .{@linkplain Object#equals equals}( {@link #getStartPoint getStartPoint}() )</code></p>
     *
     * There is no assumption that the
     * {@linkplain #getStartConstructiveParam start constructive parameter} is less than the
     * {@linkplain #getEndConstructiveParam end constructive parameter}, but the parameterization
     * must be strictly monotonic (strictly increasing, or strictly decreasing).
     */
    @UML(identifier="startConstrParam", obligation=MANDATORY, specification=ISO_19107)
    double getStartConstructiveParam();

    /**
     * Return the end point parameter used in the constructive paramerization.
     * The following relation must be hold:
     *
     * <p><code>{@linkplain #forConstructiveParam forConstructiveParam}(getEndConstructiveParam())
     * .{@linkplain Object#equals equals}( {@link #getEndPoint getEndPoint}() )</code></p>
     *
     * There is no assumption that the
     * {@linkplain #getStartConstructiveParam start constructive parameter} is less than the
     * {@linkplain #getEndConstructiveParam end constructive parameter}, but the parameterization
     * must be strictly monotonic (strictly increasing, or strictly decreasing).
     */
    @UML(identifier="endConstrParam", obligation=MANDATORY, specification=ISO_19107)
    double getEndConstructiveParam();
}
