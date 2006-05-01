/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// OpenGIS direct dependencies
import org.opengis.referencing.Factory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchClassificationException;
import org.opengis.parameter.GeneralParameterValue;


/**
 * Low level factory for creating {@linkplain MathTransform math transforms}.
 * Many high level GIS applications will never need to use this factory directly;
 * they can use a {@linkplain CoordinateOperationFactory coordinate operation factory}
 * instead. However, the <code>MathTransformFactory</code> interface can be used directly
 * by applications that wish to transform other types of coordinates (e.g. color coordinates,
 * or image pixel coordinates).
 * <br><br>
 * A {@linkplain MathTransform math transform} is an object that actually does
 * the work of applying formulae to coordinate values. The math transform does
 * not know or care how the coordinates relate to positions in the real world.
 * This lack of semantics makes implementing <code>MathTransformFactory</code>
 * significantly easier than it would be otherwise.
 *
 * For example the affine transform applies a matrix to the coordinates
 * without knowing how what it is doing relates to the real world. So if
 * the matrix scales <var>Z</var> values by a factor of 1000, then it could
 * be converting meters into millimeters, or it could be converting kilometers
 * into meters.
 * <br><br>
 * Because {@linkplain MathTransform math transforms} have low semantic value
 * (but high mathematical value), programmers who do not have much knowledge
 * of how GIS applications use coordinate systems, or how those coordinate
 * systems relate to the real world can implement <code>MathTransformFactory</code>.
 * The low semantic content of {@linkplain MathTransform math transforms} also
 * means that they will be useful in applications that have nothing to do with
 * GIS coordinates. For example, a math transform could be used to map color
 * coordinates between different color spaces, such as converting (red, green, blue)
 * colors into (hue, light, saturation) colors.
 * <br><br>
 * Since a {@linkplain MathTransform math transform} does not know what its source
 * and target coordinate systems mean, it is not necessary or desirable for a math
 * transform object to keep information on its source and target coordinate systems.
 *
 * @UML abstract CT_MathTransformFactory
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 */
public interface MathTransformFactory extends Factory {
    /**
     * Creates an affine transform from a matrix.
     * If the transform's input dimension is <code>M</code>, and output dimension
     * is <code>N</code>, then the matrix will have size <code>[N+1][M+1]</code>.
     * The +1 in the matrix dimensions allows the matrix to do a shift, as well as
     * a rotation. The <code>[M][j]</code> element of the matrix will be the j'th
     * ordinate of the moved origin. The <code>[i][N]</code> element of the matrix
     * will be 0 for <var>i</var> less than <code>M</code>, and 1 for <var>i</var>
     * equals <code>M</code>.
     *
     * @param matrix The matrix used to define the affine transform.
     * @return The affine transform.
     * @throws FactoryException if the object creation failed.
     * @UML operation createAffineTransform
     */
    MathTransform createAffineTransform(Matrix matrix) throws FactoryException;

    /**
     * Creates a transform by concatenating two existing transforms.
     * A concatenated transform acts in the same way as applying two
     * transforms, one after the other.
     *
     * The dimension of the output space of the first transform must match
     * the dimension of the input space in the second transform.
     * If you wish to concatenate more than two transforms, then you can
     * repeatedly use this method.
     *
     * @param  transform1 The first transform to apply to points.
     * @param  transform2 The second transform to apply to points.
     * @return The concatenated transform.
     * @throws FactoryException if the object creation failed.
     * @UML operation createConcatenatedTransform
     */
    MathTransform createConcatenatedTransform(MathTransform transform1, MathTransform transform2) throws FactoryException;

    /**
     * Creates a transform which passes through a subset of ordinates to another transform.
     * This allows transforms to operate on a subset of ordinates. For example, if you have
     * (<var>Lat</var>,<var>Lon</var>,<var>Height</var>) coordinates, then you may wish to
     * convert the height values from meters to feet without affecting the
     * (<var>Lat</var>,<var>Lon</var>) values.
     *
     * @param  firstAffectedOrdinate The lowest index of the affected ordinates.
     * @param  subTransform Transform to use for affected ordinates.
     * @param  numTrailingOrdinates Number of trailing ordinates to pass through.
     *         Affected ordinates will range from <code>firstAffectedOrdinate</code>
     *         inclusive to <code>dimTarget-numTrailingOrdinates</code> exclusive.
     * @return A pass through transform with the following dimensions:<br>
     *         <pre>
     * Source: firstAffectedOrdinate + subTransform.getDimSource() + numTrailingOrdinates
     * Target: firstAffectedOrdinate + subTransform.getDimTarget() + numTrailingOrdinates</pre>
     * @throws FactoryException if the object creation failed.
     * @UML operation createPassThroughTransform
     */
    MathTransform createPassThroughTransform(int firstAffectedOrdinate, MathTransform subTransform, int numTrailingOrdinates) throws FactoryException;

    /**
     * Creates a transform from a classification name and parameters.
     * The client must supply <code>"semi_major"</code> and <code>"semi_minor"</code>
     * parameters for cartographic projection transforms. 
     *
     * @param  classification The classification name of the transform
     *         (e.g. "Transverse_Mercator"). It should be one of the name
     *         returned by {@link #getAvailableTransforms}. Leading and
     *         trailing spaces are ignored. Comparisons are case-insensitive.
     * @param  parameters The parameter values. A default set can be obtained with
     *         <code>{@linkplain #getDefaultParameters getDefaultParameters}(classification)}</code>
     *         and modified before to be given to this method.
     * @return The parameterized transform.
     * @throws NoSuchClassificationException if there is no transform registered
     *         with the specified classification name.
     * @throws FactoryException if the object creation failed. This exception is thrown
     *         if some required parameter has not been supplied, or has illegal value.
     * @UML operation createParameterizedTransform
     *
     * @revisit Is the classification name the same than
     *          {@linkplain OperationMethod#getName operation method name}?
     */
    MathTransform createParameterizedTransform(String classification, GeneralParameterValue[] parameters) throws FactoryException;

    /**
     * Returns the default parameter values for the specified classification.
     * This method always returns clones. It is safe to modify the returned
     * parameter values and give them to
     * <code>{@linkplain #createParameterizedTransform createParameterizedTransform}(classification, parameters)</code>.
     *
     * @param  classification The classification name of the transform
     *         (e.g. "Transverse_Mercator"). It should be one of the name
     *         returned by {@link #getAvailableTransforms}. Leading and
     *         trailing spaces are ignored. Comparisons are case-insensitive.
     * @return The default parameter values.
     * @throws NoSuchClassificationException if there is no provider registered
     *         with the specified classification name.
     */
    GeneralParameterValue[] getDefaultParameters(String classification) throws NoSuchClassificationException;

    /**
     * Creates a math transform object from a XML string.
     *
     * @param  xml Math transform encoded in XML format.
     * @throws FactoryException if the object creation failed.
     * @UML operation createFromXML
     */
    MathTransform createFromXML(String xml) throws FactoryException;

    /**
     * Creates a math transform object from a string.
     * The <A HREF="../doc-files/WKT.html">definition for WKT</A> is
     * shown using Extended Backus Naur Form (EBNF).
     *
     * @param  wkt Kath transform encoded in Well-Known Text format.
     * @throws FactoryException if the object creation failed.
     * @UML operation createFromWKT
     */
    MathTransform createFromWKT(String wkt) throws FactoryException;
}
