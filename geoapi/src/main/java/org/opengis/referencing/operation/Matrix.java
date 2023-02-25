/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.operation;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * A two dimensional array of numbers. Row and column numbering begins with zero. The API for
 * this interface matches closely the API in various {@linkplain javax.vecmath.GMatrix matrix}
 * implementations available in <A HREF="https://java3d.dev.java.net/">Java3D</A>,
 * which should enable straightforward implementations. Java3D provides matrix for the general
 * case and optimized versions for 3&times;3 and 4&times;4 cases, which are quite common in a
 * transformation package.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see javax.vecmath.Matrix3d
 * @see javax.vecmath.Matrix4d
 * @see javax.vecmath.GMatrix
 * @see java.awt.geom.AffineTransform
 * @see javax.media.jai.PerspectiveTransform
 * @see javax.media.j3d.Transform3D
 * @see <A HREF="http://math.nist.gov/javanumerics/jama/">Jama matrix</A>
 */
@UML(identifier="PT_Matrix", specification=OGC_01009)
public interface Matrix {
    /**
     * Returns the number of rows in this matrix.
     *
     * @return The number of rows in this matrix.
     *
     * @departure integration
     *   Needed for making the matrix usable. The method signature matches the one of
     *   <code>GMatrix</code> in the <cite>vecmath</cite> package, for straightforward
     *   implementation.
     */
    int getNumRow();

    /**
     * Returns the number of columns in this matrix.
     *
     * @return The number of columns in this matrix.
     *
     * @departure integration
     *   Needed for making the matrix usable. The method signature matches the one of
     *   <code>GMatrix</code> in the <cite>vecmath</cite> package, for straightforward
     *   implementation.
     */
    int getNumCol();

    /**
     * Retrieves the value at the specified row and column of this matrix.
     *
     * @param row    The row number to be retrieved (zero indexed).
     * @param column The column number to be retrieved (zero indexed).
     * @return The value at the indexed element.
     *
     * @departure integration
     *   Needed for making the matrix usable. The method signature matches the one of
     *   <code>GMatrix</code> in the <cite>vecmath</cite> package, for straightforward
     *   implementation.
     */
    double getElement(int row, int column);

    /**
     * Modifies the value at the specified row and column of this matrix.
     *
     * @param row    The row number to be retrieved (zero indexed).
     * @param column The column number to be retrieved (zero indexed).
     * @param value  The new matrix element value.
     *
     * @departure integration
     *   Needed for making the matrix usable. The method signature matches the one of
     *   <code>GMatrix</code> in the <cite>vecmath</cite> package, for straightforward
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
     * @return A clone of this matrix.
     */
    Matrix clone();
}
