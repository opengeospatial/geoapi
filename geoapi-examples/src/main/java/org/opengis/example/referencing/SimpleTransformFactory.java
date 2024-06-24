/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Set;
import java.util.Collections;

import org.opengis.util.FactoryException;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.metadata.citation.Citation;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransformFactory;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.SingleOperation;
import org.opengis.example.metadata.SimpleCitation;


/**
 * A {@link MathTransformFactory} for creating {@link SimpleTransform} instances.
 * The only methods supported by this simple implementation are:
 *
 * <ul>
 *   <li>{@link #getVendor()}</li>
 *   <li>{@link #getAvailableMethods(Class)}, which returns an empty set.</li>
 *   <li>{@link #createAffineTransform(Matrix)}</li>
 *   <li>{@link #createMatrix(int, int)}</li>
 * </ul>
 *
 * This base class is immutable and safe for multi-threading.
 */
public class SimpleTransformFactory implements MathTransformFactory {
    /**
     * A shared instance of this factory.
     *
     * @see #provider()
     */
    private static final SimpleTransformFactory INSTANCE = new SimpleTransformFactory();

    /**
     * Creates a new factory.
     *
     * @see #provider()
     */
    protected SimpleTransformFactory() {
    }

    /**
     * Returns a shared instance of this factory.
     *
     * <p><b>API note:</b>
     * This method is invoked by {@link java.util.ServiceLoader} when this factory is fetched as a service.</p>
     *
     * @return a shared instance of this factory.
     */
    public static MathTransformFactory provider() {
        return INSTANCE;
    }

    /**
     * Returns the implementer of this factory.
     * Subclasses should replace {@code "GeoAPI example"} by their vendor name.
     */
    @Override
    public Citation getVendor() {
        return SimpleCitation.GEOAPI;
    }

    /**
     * Returns a set of available methods for {@linkplain MathTransform math transforms}.
     * The default implementation returns an empty set.
     */
    @Override
    public Set<OperationMethod> getAvailableMethods(Class<? extends SingleOperation> type) {
        return Collections.emptySet();
    }

    /**
     * Returns the default parameter values for a math transform using the given method.
     * The default implementation throws an exception in all cases since parameterized
     * transforms are not implemented by this simple factory.
     */
    @Override
    public ParameterValueGroup getDefaultParameters(String method) throws NoSuchIdentifierException {
        throw new NoSuchIdentifierException("Parameterized transforms are not implemented.", method);
    }

    /**
     * Creates a builder for a parameterized transform. The default implementation throws an exception
     * in all cases since parameterized transforms are not implemented by this simple factory.
     */
    @Override
    public MathTransform.Builder builder(String method) throws NoSuchIdentifierException {
        throw new NoSuchIdentifierException("Parameterized transforms are not implemented.", method);
    }

    /**
     * Creates an affine transform from a matrix. If the transform's input dimension is {@code M},
     * and output dimension is {@code N}, then the matrix will have size {@code [N+1][M+1]}.
     * The +1 in the matrix dimensions allows the matrix to do a shift, as well as a rotation.
     * The {@code [M][j]} element of the matrix will be the <var>j</var>'th coordinate of the moved
     * origin. The {@code [i][N]} element of the matrix will be 0 for <var>i</var> less than
     * {@code M}, and 1 for <var>i</var> equals {@code M}.
     *
     * @param  matrix  the matrix used to define the affine transform.
     * @return the affine transform.
     * @throws FactoryException if the object creation failed.
     *
     * @see AffineTransform2D
     * @see ProjectiveTransform
     */
    @Override
    public MathTransform createAffineTransform(final Matrix matrix) throws FactoryException {
        if (matrix.getNumCol()     == 3 &&
            matrix.getNumRow()     == 3 &&
            matrix.getElement(2,0) == 0 &&
            matrix.getElement(2,1) == 0 &&
            matrix.getElement(2,2) == 1)
        {
            return new AffineTransform2D(matrix);
        }
        return new ProjectiveTransform(SimpleCitation.GEOAPI, "Projective transform", null, null,
                (matrix instanceof SimpleMatrix) ? (SimpleMatrix) matrix : new SimpleMatrix(matrix));
    }

    /**
     * Creates a matrix of size {@code numRow}&nbsp;×&nbsp;{@code numCol}.
     * Elements on the diagonal (<var>j</var> == <var>i</var>) are set to 1.
     *
     * @param  numRow  number of rows.
     * @param  numCol  number of columns.
     * @return a new matrix of the given size.
     * @throws FactoryException if the matrix creation failed.
     */
    @Override
    public Matrix createMatrix(int numRow, int numCol) throws FactoryException {
        return new SimpleMatrix(numRow, numCol);
    }

    /**
     * Creates a transform by concatenating two existing transforms. The default implementation
     * throws an exception in all cases since concatenated transforms are not implemented by this
     * simple factory.
     */
    @Override
    public MathTransform createConcatenatedTransform(MathTransform transform1, MathTransform transform2) throws FactoryException {
        throw new FactoryException("Concatenated transforms are not implemented.");
    }

    /**
     * Creates a transform which passes through a subset of coordinates to another transform.
     * The default implementation throws an exception in all cases since pass through transforms
     * are not implemented by this simple factory.
     */
    @Override
    public MathTransform createPassThroughTransform(int firstAffectedCoordinate, MathTransform subTransform, int numTrailingCoordinates) throws FactoryException {
        throw new FactoryException("Pass through transforms are not implemented.");
    }

    /**
     * Creates a transform from a WKT string. The default implementation throws an exception
     * in all cases since WKT parsing is not implemented by this simple factory.
     */
    @Override
    public MathTransform createFromWKT(String wkt) throws FactoryException {
        throw new FactoryException("WKT parsing is not implemented.");
    }
}
