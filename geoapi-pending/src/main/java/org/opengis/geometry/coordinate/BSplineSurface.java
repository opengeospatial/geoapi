/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A rational or polynomial parametric surface that is represented by control points, basis
 * functions and possibly weights. If the weights are all equal then the spline is piecewise
 * polynomial. If they are not equal, then the spline is piecewise rational. If the boolean
 * {@link #isPolynomial isPolynomial} is set to {@code true} then the weights shall all be
 * set to 1.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 */
@UML(identifier="GM_BSplineSurface", specification=ISO_19107)
public interface BSplineSurface extends GriddedSurface {
    /**
     * The algebraic degree of the basis functions for the first and second parameter.
     * If only one value is given, then the two degrees are equal.
     *
     * @return the degrees as an array of length 1 or 2.
     */
    @UML(identifier="degree", obligation=MANDATORY, specification=ISO_19107)
    int[] getDegrees();

    /**
     * Identifies particular types of surface which this spline is being used to approximate.
     * It is for information only, used to capture the original intention. If no such approximation
     * is intended, then this method returns {@code null}.
     *
     * @return the type of surface, or {@code null} if this information is not available.
     */
    @UML(identifier="surfaceForm", obligation=OPTIONAL, specification=ISO_19107)
    BSplineSurfaceForm getSurfaceForm();

    /**
     * Returns two sequences of distinct knots used to define the B-spline basis functions for
     * the two parameters. Recall that the knot data type holds information on knot multiplicity.
     *
     * @return the sequence of knots as an array of length 2.
     */
    @UML(identifier="knot", obligation=MANDATORY, specification=ISO_19107)
    List<Knot>[] getKnots();

    /**
     * Gives the type of knot distribution used in defining this spline. This is for information
     * only and is set according to the different construction-functions.
     *
     * @return the type of knot distribution, or {@code null} if none.
     */
    @UML(identifier="knotSpec", obligation=OPTIONAL, specification=ISO_19107)
    KnotType getKnotSpec();

    /**
     * Returns {@code true} if this is a polynomial spline.
     */
    @UML(identifier="isPolynomial", obligation=MANDATORY, specification=ISO_19107)
    boolean isPolynomial();
}
