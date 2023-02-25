/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
 * A two dimensional array of numbers. Row and column numbering begins with zero.
 *
 * <div class="note"><b>API note:</b>
 * the API for this interface matches closely the API in various {@link javax.vecmath} implementations,
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
 *   <li>Other: <a href="https://math.nist.gov/javanumerics/jama/">Jama matrix</a></li>
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
     * Returns {@code true} if this matrix is an identity matrix.
     *
     * @return {@code true} if this matrix is an identity matrix.
     *
     * @departure easeOfUse
     *   Added as a convenience for a frequently requested operation.
     */
    boolean isIdentity();

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
     * @throws IndexOutOfBoundsException if the specified row or column is out of bounds.
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
     * @throws IndexOutOfBoundsException if the specified row or column is out of bounds.
     * @throws UnsupportedOperationException if this matrix is unmodifiable.
     *
     * @departure integration
     *   Needed for making the matrix usable. The method signature matches the one of
     *   {@code GMatrix} in the <cite>vecmath</cite> package, for straightforward
     *   implementation.
     */
    void setElement(int row, int column, double value);

    /**
     * Returns a modifiable copy of this matrix.
     *
     * @return a modifiable copy of this matrix.
     */
    Matrix clone();
}
