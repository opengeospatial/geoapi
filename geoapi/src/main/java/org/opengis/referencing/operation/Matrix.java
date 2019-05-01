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
package org.opengis.referencing.operation;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * A two dimensional array of numbers. Row and column numbering begins with zero.
 *
 * <div class="note"><b>API note:</b>
 * The API for this interface matches closely the API in various {@link javax.vecmath} implementations,
 * which should enable straightforward implementations on top of {@code javax.vecmath}.
 * The later package provides matrix for the general case and optimized versions for 3×3 and 4×4 cases,
 * which are quite common in a transformation package.
 *
 * <p>Examples of third-party classes that can be used for implementing this {@code Matrix} interface:</p>
 * <ul>
 *   <li>Standard Java: {@link java.awt.geom.AffineTransform}</li>
 *   <li>Java Advanced Imaging (JAI): {@link javax.media.jai.PerspectiveTransform}</li>
 *   <li>Java3D: {@link javax.media.j3d.Transform3D}, {@link javax.vecmath.GMatrix},
 *       {@link javax.vecmath.Matrix4d}, {@link javax.vecmath.Matrix3d}</li>
 *   <li>Other: <a href="http://math.nist.gov/javanumerics/jama/">Jama matrix</a></li>
 * </ul>
 * </div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="PT_Matrix", specification=OGC_01009)
public interface Matrix {
    /**
     * Returns the number of rows in this matrix.
     *
     * @return the number of rows in this matrix.
     *
     * @departure integration
     *   Needed for making the matrix usable. The method signature matches the one of
     *   {@code GMatrix} in the <cite>vecmath</cite> package, for straightforward
     *   implementation.
     */
    int getNumRow();

    /**
     * Returns the number of columns in this matrix.
     *
     * @return the number of columns in this matrix.
     *
     * @departure integration
     *   Needed for making the matrix usable. The method signature matches the one of
     *   {@code GMatrix} in the <cite>vecmath</cite> package, for straightforward
     *   implementation.
     */
    int getNumCol();

    /**
     * Retrieves the value at the specified row and column of this matrix.
     *
     * @param  row     the row number to be retrieved (zero indexed).
     * @param  column  the column number to be retrieved (zero indexed).
     * @return the value at the indexed element.
     *
     * @departure integration
     *   Needed for making the matrix usable. The method signature matches the one of
     *   {@code GMatrix} in the <cite>vecmath</cite> package, for straightforward
     *   implementation.
     */
    double getElement(int row, int column);

    /**
     * Modifies the value at the specified row and column of this matrix.
     *
     * @param  row     the row number of the value to set (zero indexed).
     * @param  column  the column number of the value to set (zero indexed).
     * @param  value   the new matrix element value.
     *
     * @departure integration
     *   Needed for making the matrix usable. The method signature matches the one of
     *   {@code GMatrix} in the <cite>vecmath</cite> package, for straightforward
     *   implementation.
     */
    void setElement(int row, int column, double value);

    /**
     * Returns {@code true} if this matrix is an identity matrix.
     *
     * @return {@code true} if this matrix is an identity matrix.
     *
     * @departure easeOfUse
     *   Added as a convenience for a frequently requested operation.
     */
    boolean isIdentity();

    /**
     * Returns a clone of this matrix.
     *
     * @return a clone of this matrix.
     */
    Matrix clone();
}
