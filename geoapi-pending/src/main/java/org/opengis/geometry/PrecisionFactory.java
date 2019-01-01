/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry;

/**
 * A factory for managing {@linkplain Precision direct position} creation. A
 * Precision is used to describe the accuracy at which you would like your
 * geometry maintained during operations and transformations.
 *
 * <p>Here are a couple of examples of creating a Precision using a
 * PrecisionFactory:</p>
 *
 * <pre>
 * factory.createPrecision(PrecisionType.FIXED, 1000);      // three significant digits
 * factory.createPrecision(PrecisionType.FLOAT, 0);         // float precision - 6 digits
 * factory.createPrecision(PrecisionType.DOUBLE, 0);        // double precision - 16 digits
 * </pre>
 *
 * Although the
 * DirectPosition makes use of double when representing ordinates some
 * implementations, transfer mechanisms or storage facilities will not be able
 * to maintain this accuracy. In order to maintain a valid geometry representation
 * in these situations you will need to provide a {@link Precision} as a strategy
 * object used to round coordinates during creation and transformation. We cannot
 * allow you to round to the correct precision afterwards as the result may be an
 * invalid geometry.
 *
 * <p>The easiest example is the construction of a very small
 * polygon for a WFS configured to use 2 significant digits when generating
 * GML. When generating a polygon in meters of less than 1 cm in size the
 * rounding policy would "collapse" all the points of the outer ring into the
 * same location - a WFS faced with this situation may choose to skip the polygon
 * or represent it as a Point.</p>
 *
 * @author Jody Garnett
 * @since GeoAPI 2.1
 */
public interface PrecisionFactory {
    /**
     * Creates a Precision of the provided type, scale is used for
     * PrecisionType.FIXED.
     *
     * <p>Here are a couple of examples:</p>
     * <pre>
     * factory.createPrecision(PrecisionType.FIXED, 1000);      // three significant digits
     * factory.createPrecision(PrecisionType.FLOAT, 0);         // float precision - 6 digits
     * factory.createPrecision(PrecisionType.DOUBLE, 0);        // double precision - 16 digits
     * </pre>
     *
     * @param code PrecisionType The rounding policy used
     * @param scale Multiplying factor used to obtain a precise coordinate
     * @return Precision capable of rounding as described by type and scale
     */
    Precision createFixedPrecision(PrecisionType code, double scale);
}
