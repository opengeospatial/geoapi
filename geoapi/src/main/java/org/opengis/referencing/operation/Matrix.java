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
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
@UML(identifier="PT_Matrix", specification=OGC_01009)
public interface Matrix {
    /**
     * Returns the number of rows in this matrix.
     *
     * @return the number of rows in this matrix.
     */
    @UML(identifier="num_row", specification=OGC_01009)     // Defined in WKT 1
    int getNumRow();

    /**
     * Returns the number of columns in this matrix.
     *
     * @return the number of columns in this matrix.
     */
    @UML(identifier="num_col", specification=OGC_01009)     // Defined in WKT 1
    int getNumCol();

    /**
     * Returns {@code true} if this matrix is an identity matrix.
     *
     * <p>The default returns {@code true} if this matrix is square and if
     * {@link #getElement(int, int)} returns 1 on the diagonal and 0 everywhere else.
     * Otherwise this method returns {@code false}.</p>
     *
     * @return {@code true} if this matrix is an identity matrix.
     *
     * @departure easeOfUse
     *   Added as a convenience for a frequently requested operation.
     */
    default boolean isIdentity() {
        final int n = getNumRow();
        if (getNumCol() != n) {
            return false;
        }
        for (int row=0; row<n; row++) {
            for (int column=0; column<n; column++) {
                if (getElement(row, column) != (row == column ? 1 : 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retrieves the value at the specified row and column of this matrix.
     *
     * @param  row     the row number to be retrieved (zero indexed).
     * @param  column  the column number to be retrieved (zero indexed).
     * @return the value at the indexed element.
     * @throws IndexOutOfBoundsException if the specified row or column is out of bounds.
     */
    @UML(identifier="elt", specification=OGC_01009)         // Defined in WKT 1
    double getElement(int row, int column);

    /**
     * Modifies the value at the specified row and column of this matrix.
     * This is an optional method.
     *
     * <p>The default throws {@link UnsupportedOperationException}.</p>
     *
     * @param  row     the row number of the value to set (zero indexed).
     * @param  column  the column number of the value to set (zero indexed).
     * @param  value   the new matrix element value.
     * @throws IndexOutOfBoundsException if the specified row or column is out of bounds.
     * @throws UnsupportedOperationException if this matrix is unmodifiable.
     */
    default void setElement(int row, int column, double value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a modifiable copy of this matrix.
     *
     * @return a modifiable copy of this matrix.
     */
    Matrix clone();
}
