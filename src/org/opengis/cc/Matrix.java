/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;

// J2SE direct dependencies and extensions
import java.awt.geom.AffineTransform;        // For JavaDoc
import javax.media.jai.PerspectiveTransform; // For JavaDoc
import javax.media.j3d.Transform3D;          // For JavaDoc


/**
 * A two dimensional array of numbers.
 * Row and column numbering begins with zero.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see javax.vecmath.GMatrix
 * @see java.awt.geom.AffineTransform
 * @see javax.media.jai.PerspectiveTransform
 * @see javax.media.j3d.Transform3D
 * @see <A HREF="http://math.nist.gov/javanumerics/jama/">Jama matrix</A>
 * @see <A HREF="http://jcp.org/jsr/detail/83.jsp">JSR-83 Multiarray package</A>
 */
public interface Matrix {
    /**
     * Returns the number of rows in this matrix.
     *
     * @return The number of rows in this matrix.
     */
    public int getNumRow();
    // Same signature than GMatrix, for straightforward implementation.

    /**
     * Returns the number of colmuns in this matrix.
     *
     * @return The number of columns in this matrix.
     */
    public int getNumCol();
    // Same signature than GMatrix, for straightforward implementation.

    /**
     * Retrieves the value at the specified row and column of this matrix.
     *
     * @param row    The row number to be retrieved (zero indexed).
     * @param column The column number to be retrieved (zero indexed).
     * @return The value at the indexed element.
     */
    public double getElement(int row, int column);
    // Same signature than GMatrix, for straightforward implementation.

    /**
     * Returns <code>true</code> if this matrix is an identity matrix.
     *
     * @return <code>true</code> if this matrix is an identity matrix.
     */
    public boolean isIdentity();
}
