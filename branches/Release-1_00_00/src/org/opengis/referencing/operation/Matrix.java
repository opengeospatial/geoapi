/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// OpenGIS direct dependencies
import org.opengis.util.Cloneable;


/**
 * A two dimensional array of numbers. Row and column numbering begins with zero. The API for
 * this interface matches closely the API in various {@linkplain javax.vecmath.GMatrix matrix}
 * implementations available in <A HREF="http://java.sun.com/products/java-media/3D/">Java3D</A>,
 * which should enable straightforward implementations. Java3D provides matrix for the general
 * case and optimized versions for 3&times;3 and 4&times;4 cases, which are quite common in a
 * transformation package.
 *
 * @UML abstract PT_Matrix
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 *
 * @see javax.vecmath.Matrix3d
 * @see javax.vecmath.Matrix4d
 * @see javax.vecmath.GMatrix
 * @see java.awt.geom.AffineTransform
 * @see javax.media.jai.PerspectiveTransform
 * @see javax.media.j3d.Transform3D
 * @see <A HREF="http://math.nist.gov/javanumerics/jama/">Jama matrix</A>
 * @see <A HREF="http://jcp.org/jsr/detail/83.jsp">JSR-83 Multiarray package</A>
 */
public interface Matrix extends Cloneable {
    /**
     * Returns the number of rows in this matrix.
     *
     * @return The number of rows in this matrix.
     */
    int getNumRow();
    // Same signature than GMatrix, for straightforward implementation.

    /**
     * Returns the number of colmuns in this matrix.
     *
     * @return The number of columns in this matrix.
     */
    int getNumCol();
    // Same signature than GMatrix, for straightforward implementation.

    /**
     * Retrieves the value at the specified row and column of this matrix.
     *
     * @param row    The row number to be retrieved (zero indexed).
     * @param column The column number to be retrieved (zero indexed).
     * @return The value at the indexed element.
     */
    double getElement(int row, int column);
    // Same signature than GMatrix, for straightforward implementation.

    /**
     * Modifies the value at the specified row and column of this matrix.
     *
     * @param row    The row number to be retrieved (zero indexed).
     * @param column The column number to be retrieved (zero indexed).
     * @param value  The new matrix element value.
     */
    void setElement(int row, int column, double value);
    // Same signature than GMatrix, for straightforward implementation.

    /**
     * Returns <code>true</code> if this matrix is an identity matrix.
     *
     * @return <code>true</code> if this matrix is an identity matrix.
     */
    boolean isIdentity();

    /**
     * Returns a clone of this matrix.
     */
    /*{Matrix}*/ Object clone();
}
