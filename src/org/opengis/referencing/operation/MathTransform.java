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
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.MismatchedDimensionException;


/**
 * Transforms multi-dimensional coordinate points. This interface transforms coordinate
 * value for a point given in the {@linkplain CoordinateOperation#getSourceCRS source
 * coordinate reference system} to coordinate value for the same point in the
 * {@linkplain CoordinateOperation#getTargetCRS target coordinate reference system}.
 *
 * In a {@linkplain Conversion conversion}, the transformation is accurate to within the
 * limitations of the computer making the calculations. In a {@linkplain Transformation
 * transformation}, where some of the operational parameters are derived from observations,
 * the transformation is accurate to within the limitations of those observations.
 *
 * If a client application wishes to query the source and target
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference systems}
 * of an operation, then it should keep hold of the {@link CoordinateOperation} interface,
 * and use the contained math transform object whenever it wishes to perform a transform.
 *
 * @UML abstract CT_MathTransform
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 *
 * @see java.awt.geom.AffineTransform
 * @see javax.media.jai.PerspectiveTransform
 * @see javax.media.j3d.Transform3D
 * @see MathTransformFactory
 * @see CoordinateOperation#getMathTransform
 */
public interface MathTransform {
    /**
     * Gets the dimension of input points.
     *
     * @UML mandatory dimSource
     */
    int getDimSource();
    
    /**
     * Gets the dimension of output points.
     *
     * @UML mandatory dimTarget
     */
    int getDimTarget();
    
    /**
     * Transforms the specified <code>ptSrc</code> and stores the result in
     * <code>ptDst</code>. If <code>ptDst</code> is <code>null</code>, a new
     * {@link DirectPosition} object is allocated and then the result of the
     * transformation is stored in this object. In either case, <code>ptDst</code>,
     * which contains the transformed point, is returned for convenience.
     * If <code>ptSrc</code> and <code>ptDst</code> are the same object,
     * the input point is correctly overwritten with the transformed point.
     *
     * @param ptSrc the specified coordinate point to be transformed.
     * @param ptDst the specified coordinate point that stores the
     *              result of transforming <code>ptSrc</code>, or
     *              <code>null</code>.
     * @return the coordinate point after transforming <code>ptSrc</code>
     *         and storing the result in <code>ptDst</code>, or a newly
     *         created point if <code>ptDst</code> was null.
     * @throws MismatchedDimensionException if <code>ptSrc</code> or
     *         <code>ptDst</code> doesn't have the expected dimension.
     * @throws TransformException if the point can't be transformed.
     *
     * @UML operation transform
     */
    DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst)
            throws MismatchedDimensionException, TransformException;
    
    /**
     * Transforms a list of coordinate point ordinal values.
     * This method is provided for efficiently transforming many points.
     * The supplied array of ordinal values will contain packed ordinal
     * values. For example, if the source dimension is 3, then the ordinals
     * will be packed in this order:
     *
     * (<var>x<sub>0</sub></var>,<var>y<sub>0</sub></var>,<var>z<sub>0</sub></var>,
     *  <var>x<sub>1</sub></var>,<var>y<sub>1</sub></var>,<var>z<sub>1</sub></var> ...).
     *
     * @param srcPts the array containing the source point coordinates.
     * @param srcOff the offset to the first point to be transformed
     *               in the source array.
     * @param dstPts the array into which the transformed point
     *               coordinates are returned. May be the same
     *               than <code>srcPts</code>.
     * @param dstOff the offset to the location of the first
     *               transformed point that is stored in the
     *               destination array.
     * @param numPts the number of point objects to be transformed.
     * @throws TransformException if a point can't be transformed.
     *
     * @UML operation transformList
     */
    void transform(double[] srcPts, int srcOff,
                   double[] dstPts, int dstOff,
                   int numPts) throws TransformException;
    
    /**
     * Transforms a list of coordinate point ordinal values.
     * This method is provided for efficiently transforming many points.
     * The supplied array of ordinal values will contain packed ordinal
     * values.  For example, if the source dimension is 3, then the ordinals
     * will be packed in this order:
     *
     * (<var>x<sub>0</sub></var>,<var>y<sub>0</sub></var>,<var>z<sub>0</sub></var>,
     *  <var>x<sub>1</sub></var>,<var>y<sub>1</sub></var>,<var>z<sub>1</sub></var> ...).
     *
     * @param srcPts the array containing the source point coordinates.
     * @param srcOff the offset to the first point to be transformed
     *               in the source array.
     * @param dstPts the array into which the transformed point
     *               coordinates are returned. May be the same
     *               than <code>srcPts</code>.
     * @param dstOff the offset to the location of the first
     *               transformed point that is stored in the
     *               destination array.
     * @param numPts the number of point objects to be transformed.
     * @throws TransformException if a point can't be transformed.
     */
    void transform(float[] srcPts, int srcOff,
                   float[] dstPts, int dstOff,
                   int numPts) throws TransformException;

    /**
     * Gets the derivative of this transform at a point. The derivative is the
     * matrix of the non-translating portion of the approximate affine map at
     * the point. The matrix will have dimensions corresponding to the source
     * and target coordinate systems. If the input dimension is <var>M</var>,
     * and the output dimension is <var>N</var>, then the matrix will have size
     * <code>N&times;M</code>. The elements of the matrix
     *
     *              <code>{e<sub>n,m</sub> : n=0..(N-1)}</code>
     *
     * form a vector in the output space which is parallel to the displacement
     * caused by a small change in the <var>m</var>'th ordinate in the input space.
     * <br><br>
     * For example, if the input dimension is 4 and the
     * output dimension is 3, then a small displacement
     *
     * <code>(x<sub>0</sub>,&nbsp;x<sub>1</sub>,&nbsp;x<sub>2</sub>,&nbsp;x<sub>3</sub>)</code>
     *
     * in the input space will result in a displacement
     *
     * <code>(y<sub>0</sub>,&nbsp;y<sub>1</sub>,&nbsp;y<sub>2</sub>)</code>
     *
     * in the output space computed as below (<code>e<sub>n,m</sub></code>
     * are the matrix's elements):
     *
     * <pre>
     * [ y<sub>0</sub> ]     [ e<sub>00</sub>  e<sub>01</sub>  e<sub>02</sub>  e<sub>03</sub> ] [ x<sub>0</sub> ]
     * [ y<sub>1</sub> ]  =  [ e<sub>10</sub>  e<sub>11</sub>  e<sub>12</sub>  e<sub>13</sub> ] [ x<sub>1</sub> ]
     * [ y<sub>2</sub> ]     [ e<sub>20</sub>  e<sub>21</sub>  e<sub>22</sub>  e<sub>23</sub> ] [ x<sub>2</sub> ]
     *    <sub> </sub>          <sub>  </sub>   <sub>  </sub>   <sub>  </sub>   <sub>  </sub>   [ x<sub>3</sub> ]
     * </pre>
     *
     * @param  point The coordinate point where to evaluate the derivative. Null
     *         value is accepted only if the derivative is the same everywhere.
     *         For example affine transform accept null value since they produces
     *         identical derivative no matter the coordinate value. But most map
     *         projection will requires a non-null value.
     * @return The derivative at the specified point (never <code>null</code>).
     *         This method never returns an internal object: changing the matrix
     *         will not change the state of this math transform.
     * @throws NullPointerException if the derivative dependents on coordinate
     *         and <code>point</code> is <code>null</code>.
     * @throws MismatchedDimensionException if <code>point</code> doesn't have
     *         the expected dimension.
     * @throws TransformException if the derivative can't be evaluated at the
     *         specified point.
     *
     * @UML operation derivative
     */
    Matrix derivative(final DirectPosition point)
            throws MismatchedDimensionException, TransformException;
    
    /**
     * Creates the inverse transform of this object. The target of the inverse transform
     * is the source of the original. The source of the inverse transform is the target
     * of the original. Using the original transform followed by the inverse's transform
     * will result in an identity map on the source coordinate space, when allowances for
     * error are made. This method may fail if the transform is not one to one. However,
     * all cartographic projections should succeed.
     *
     * @return The inverse transform.
     * @throws NoninvertibleTransformException if the transform can't be inversed.
     *
     * @UML operation inverse
     */
    MathTransform inverse() throws NoninvertibleTransformException;
    
    /**
     * Tests whether this transform does not move any points.
     *
     * @return <code>true</code> if this <code>MathTransform</code> is
     *         an identity transform; <code>false</code> otherwise.
     *
     * @UML operation isIdentity
     */
    boolean isIdentity();
}
