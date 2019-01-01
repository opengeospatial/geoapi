/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
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

import java.util.Arrays;
import org.opengis.referencing.operation.Matrix;

import static java.lang.StrictMath.*;
import static org.junit.Assert.*;


/**
 * A trivial implementation of {@link Matrix} for internal usage.
 * Not public because strictly reserved to tests.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleMatrix implements Matrix, Cloneable {
    /**
     * Number of columns.
     */
    private final int numCol;

    /**
     * The matrix elements. The number of elements shall be a multiple of the number of columns.
     * The column index varies fastest.
     */
    private final double[] elements;

    /**
     * Creates a new matrix with the given elements.
     *
     * @param numRow    the number of rows.
     * @param numCol    the number of columns.
     * @param elements  the elements, where columns index varies fastest. This array is <strong>not</strong> cloned.
     */
    public SimpleMatrix(final int numRow, final int numCol, final double... elements) {
        assertEquals("Number of elements is inconsistent with the matrix size.", numRow*numCol, elements.length);
        this.numCol   = numCol;
        this.elements = elements;
    }

    /**
     * Creates a new matrix initialized to the values of the given matrix.
     */
    private SimpleMatrix(final SimpleMatrix toCopy) {
        numCol   = toCopy.numCol;
        elements = toCopy.elements.clone();
    }

    /**
     * Returns the number of rows in this matrix.
     */
    @Override
    public int getNumRow() {
        return elements.length / numCol;
    }

    /**
     * Returns the number of columns in this matrix.
     */
    @Override
    public int getNumCol() {
        return numCol;
    }

    /**
     * Retrieves the value at the specified row and column of this matrix.
     */
    @Override
    public double getElement(final int row, final int column) {
        if (column < 0 || column >= numCol) {
            throw new IndexOutOfBoundsException("Invalid column index: " + column);
        }
        return elements[row*numCol + column];
    }

    /**
     * Modifies the value at the specified row and column of this matrix.
     */
    @Override
    public void setElement(final int row, final int column, final double value) {
        if (column < 0 || column >= numCol) {
            throw new IndexOutOfBoundsException("Invalid column index: " + column);
        }
        elements[row*numCol + column] = value;
    }

    /**
     * Returns {@code true} if this matrix is an identity matrix.
     */
    @Override
    public boolean isIdentity() {
        if (elements.length != numCol*numCol) {
            return false;
        }
        int diagonalIndex = 0;
        for (int i=0; i<elements.length; i++) {
            double expected = 0;
            if (i == diagonalIndex) {
                expected = 1;
                diagonalIndex += numCol+1;
            }
            if (elements[i] != expected) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a clone of this matrix.
     */
    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")      // Okay because this class is final.
    public Matrix clone() {
        return new SimpleMatrix(this);
    }

    /**
     * Returns {@code true} if this matrix is equals to the given object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof SimpleMatrix) {
            final SimpleMatrix other = (SimpleMatrix) object;
            return numCol == other.numCol && Arrays.equals(elements, other.elements);
        }
        return false;
    }

    /**
     * Returns a hash code value for this matrix.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(elements) ^ numCol;
    }

    /**
     * Returns a string representation of this matrix.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder(256);
        toString(this, buffer, System.getProperty("line.separator", "\n"));
        return buffer.toString();
    }

    /**
     * Returns a string representation of the given matrix.
     */
    static void toString(final Matrix matrix, final StringBuilder appendTo, final String lineSeparator) {
        /*
         * Get all numerical values as strings, and compute the column widths.
         */
        final int numCol = matrix.getNumCol();
        final int numRow = matrix.getNumRow();
        final String[] values = new String[numCol * numRow];
        final int[] columnWidths = new int[numCol];
        for (int j=0,p=0; j<numRow; j++) {
            for (int i=0; i<numCol; i++) {
                final String value = String.valueOf(matrix.getElement(j,i));
                columnWidths[i] = max(columnWidths[i], value.length());
                values[p++] = value;
            }
        }
        /*
         * Append the string values, with right-alignment.
         */
        int column = 0;
        for (final String value : values) {
            for (int j = columnWidths[column] - value.length(); --j >= 0;) {
                appendTo.append(' ');
            }
            appendTo.append(value);
            if (++column == numCol) {
                column = 0;
                appendTo.append(lineSeparator);
            } else {
                appendTo.append(' ');
            }
        }
    }
}
