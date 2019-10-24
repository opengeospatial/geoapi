/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import javax.vecmath.GMatrix;
import org.opengis.referencing.operation.Matrix;


/**
 * A {@link Matrix} built on top of Java3D {@code vecmath} library.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleMatrix extends GMatrix implements Matrix {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -9053188309751616226L;

    /**
     * Creates a matrix of size {@code size}&nbsp;×&nbsp;{@code size}.
     * Elements on the diagonal (<var>j</var> == <var>i</var>) are set to 1.
     */
    public SimpleMatrix(final int size) {
        super(size, size);
    }

    /**
     * Returns {@code true} if this matrix is an identity matrix.
     */
    @Override
    public boolean isIdentity() {
        final int numRow = getNumRow();
        final int numCol = getNumCol();
        if (numRow != numCol) {
            return false;
        }
        for (int j=0; j<numRow; j++) {
            for (int i=0; i<numCol; i++) {
                if (getElement(j,i) != (i==j ? 1 : 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a clone of this matrix.
     */
    @Override
    public SimpleMatrix clone() {
        return (SimpleMatrix) super.clone();
    }
}
