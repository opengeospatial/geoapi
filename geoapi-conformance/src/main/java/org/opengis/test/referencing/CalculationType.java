/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import org.opengis.geometry.DirectPosition;


/**
 * The kind of calculation that produced the {@link DirectPosition}s being compared.
 * This enumeration is expected by all {@link assertCoordinateEquals(...)} methods in the
 * {@link TransformTestCase} class. The enumeration values are consumed by the following
 * methods:
 * <p>
 * <ul>
 *   <li>{@link TransformTestCase#normalize(DirectPosition, DirectPosition, CalculationType)}</li>
 *   <li>{@link TransformTestCase#tolerance(DirectPosition, int, CalculationType)}</li>
 * </ul>
 * <p>
 * The above methods are hooks for implementors. Subclasses can override those methods for
 * controlling the {@linkplain TransformTestCase#tolerance tolerance} threshold on a case-by-case
 * basis. For example when testing the conversions from a geographic CRS (axes in degrees) to a
 * projected CRS (axes in metres), a precision of 10 centimetres can be requested by setting the
 * {@link TransformTestCase#tolerance} value to 0.1. However when testing the inverse transform,
 * the tolerance threshold must be converted to decimal degrees, which is approximatively 1E-6°
 * for the above-cited precision.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public enum CalculationType {
    /**
     * The ordinate values to compare are the result of an identity operation (a plain copy).
     * Ordinate values are checked for strict equality;
     * the {@linkplain TransformTestCase#tolerance tolerance} threshold shall be ignored.
     */
    STRICT,

    /**
     * The ordinate values to compare are the result of a direct operation
     * performed by the {@linkplain TransformTestCase#transform transform tested}.
     */
    DIRECT_TRANSFORM,

    /**
     * The ordinate values to compare are the result of the
     * {@linkplain MathTransform#inverse() inverse transform}.
     */
    INVERSE_TRANSFORM,

    /**
     * The values to compare are {@linkplain MathTransform#derivative(DirectPosition) derivatives}.
     * In case of doubt, implementations can use for the derivative values the same threshold than
     * for the {@link #DIRECT_TRANSFORM} case.
     */
    DERIVATIVE
}
