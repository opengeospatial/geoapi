/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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

import static org.junit.jupiter.api.Assertions.*;


/**
 * A dummy implementation of {@link Matrix}
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class MatrixMock implements Matrix {
    /**
     * Number of rows in the matrix.
     */
    private static final int NUM_ROW = 3;

    /**
     * Number of columns in the matrix.
     */
    private final int numCol;

    /**
     * Value of arbitrary elements.
     */
    private final double m11, m02;

    /**
     * Creates a new matrix mock. The number of rows is fixed to 3.
     *
     * @param  numCol number of columns. Shall be 3 for identity matrix.
     * @param  m11    a value on the diagonal. Shall be 1 for identity matrix.
     * @param  m02    a value off diagonal. Shall be 0 for identity matrix.
     */
    MatrixMock(final int numCol, final double m11, final double m02) {
        this.numCol = numCol;
        this.m11 = m11;
        this.m02 = m02;
    }

    /**
     * {@return the number of rows in the matrix}.
     */
    @Override
    public int getNumRow() {
        return NUM_ROW;
    }

    /**
     * {@return the number of columns in the matrix}.
     */
    @Override
    public int getNumCol() {
        return numCol;
    }

    /**
     * {@return the matrix element value at the given row and column}.
     *
     * @param row  zero-based index of the row.
     * @param col  zero-based index of the column.
     */
    @Override
    public double getElement(final int row, final int col) {
        assertTrue(row >= 0 && row < NUM_ROW);
        assertTrue(col >= 0 && col < numCol);
        if (row == 1 && col == 1) return m11;
        if (row == 0 && col == 2) return m02;
        if (row == col) return 1;
        return 0;
    }

    /**
     * {@return {@code this} since this matrix is not clonable}.
     */
    @Override
    public Matrix clone() {
        return this;
    }
}
