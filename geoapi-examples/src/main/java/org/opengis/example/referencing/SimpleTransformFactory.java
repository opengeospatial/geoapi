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
import org.opengis.example.metadata.SimpleCitation;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransformFactory;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.SingleOperation;


/**
 * A {@link MathTransformFactory} for creating {@link SimpleTransform} instances.
 * The only methods supported by this simple implementation are:
 *
 * <ul>
 *   <li>{@link #getVendor()}</li>
 *   <li>{@link #getAvailableMethods(Class)}, which returns an empty set.</li>
 *   <li>{@link #getLastMethodUsed()}, which returns {@code null}.</li>
 *   <li>{@link #createAffineTransform(Matrix)}</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleTransformFactory implements MathTransformFactory {
    /**
     * The value to be returned by {@link #getVendor()}.
     * Implementations should replace {@code "GeoAPI-example"} by their name.
     */
    private static final Citation VENDOR = new SimpleCitation("GeoAPI-example");

    /**
     * Creates a new factory.
     */
    public SimpleTransformFactory() {
    }

    /**
     * Returns the implementer of this factory.
     */
    @Override
    public Citation getVendor() {
        return VENDOR;
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
     * Returns the operation method used for the latest call to
     * {@link #createParameterizedTransform createParameterizedTransform}, or {@code null}
     * if not applicable. The default implementation returns {@code null} in all cases.
     */
    @Override
    public OperationMethod getLastMethodUsed() {
        return null;
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
     * Returns the exception to be thrown when a transform for the given parameters is requested.
     */
    private static NoSuchIdentifierException unsupported(final ParameterValueGroup parameters) {
        return new NoSuchIdentifierException("Parameterized transforms are not implemented.",
                parameters.getDescriptor().getName().getCode());
    }

    /**
     * Creates a {@linkplain #createParameterizedTransform parameterized transform} from a base CRS
     * to a derived CS. The default implementation throws an exception in all cases since parameterized
     * transforms are not implemented by this simple factory.
     */
    @Override
    public MathTransform createBaseToDerived(CoordinateReferenceSystem baseCRS,
            ParameterValueGroup parameters, CoordinateSystem derivedCS) throws FactoryException
    {
        throw unsupported(parameters);
    }

    /**
     * Creates a transform from a group of parameters. The default implementation throws an
     * exception in all cases since parameterized transforms are not implemented by this simple
     * factory.
     */
    @Override
    public MathTransform createParameterizedTransform(ParameterValueGroup parameters) throws FactoryException {
        throw unsupported(parameters);
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
        return new ProjectiveTransform(VENDOR, "Projective transform", null, null,
                (matrix instanceof SimpleMatrix) ? (SimpleMatrix) matrix : new SimpleMatrix(matrix));
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
