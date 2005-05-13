/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2001 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.ct;

// OpenGIS dependencies
import org.opengis.pt.*;

// Various JDK's classes
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Transforms multi-dimensional coordinate points.
 * If a client application wishes to query the source and target
 * coordinate systems of a transformation, then it should keep hold
 * of the {@link CT_CoordinateTransformation} interface, and use the
 * contained math transform object whenever it wishes to perform a transform.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransform}.
 */
@Deprecated
public interface CT_MathTransform extends Remote {
    /**
     * Gets flags classifying domain points within a convex hull.
     * The supplied ordinates are interpreted as a sequence of points, which
     * generates a convex hull in the source space.  Conceptually, each of the
     * (usually infinite) points inside the convex hull is then tested against
     * the source domain.  The flags of all these tests are then combined.  In
     * practice, implementations of different transforms will use different
     * short-cuts to avoid doing an infinite number of tests.
     *
     * @param  ord Packed ordinates of points used to generate convex hull.
     * @return flags classifying domain points within the convex hull.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated No replacement.
     */
    @Deprecated
    CT_DomainFlags getDomainFlags(double[] ord) throws RemoteException;

    /**
     * Gets transformed convex hull.
     * The supplied ordinates are interpreted as a sequence of points, which
     * generates a convex hull in the source space.  The returned sequence of
     * ordinates represents a convex hull in the output space.  The number of
     * output points will often be different from the number of input points.
     * Each of the input points should be inside the valid domain (this can be
     * checked by testing the points' domain flags individually).  However,
     * the convex hull of the input points may go outside the valid domain.
     * The returned convex hull should contain the transformed image of the
     * intersection of the source convex hull and the source domain. 
     * <br><br>
     * A convex hull is a shape in a coordinate system, where if two positions A
     * and B are inside the shape, then all positions in the straight line
     * between A and B are also inside the shape.  So in 3D a cube and a sphere
     * are both convex hulls.  Other less obvious examples of convex hulls are
     * straight lines, and single points.  (A single point is a convex hull,
     * because the positions A and B must both be the same - i.e. the point
     * itself.  So the straight line between A and B has zero length.)
     *
     * Some examples of shapes that are NOT convex hulls are donuts, and horseshoes.
     *
     * @param  ord Packed ordinates of points used to generate convex hull.
     * @return The transformed convex hull.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated No replacement at this time.
     */
    @Deprecated
    double[] getCodomainConvexHull(double[] ord) throws RemoteException;

    /**
     * Gets a Well-Known text representation of this object.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated No replacement at this time.
     */
    @Deprecated
    String getWKT() throws RemoteException;

    /**
     * Gets an XML representation of this object.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated No replacement at this time.
     */
    @Deprecated
    String getXML() throws RemoteException;

    /**
     * Transforms a coordinate point.
     * The passed parameter point should not be modified.
     *
     * @param  cp Point to transform.
     * @return The transformed point.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransform#transform}.
     */
    @Deprecated
    PT_CoordinatePoint transform(PT_CoordinatePoint cp) throws RemoteException;

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
     * The size of the passed array must be an integer multiple of
     * {@link #getDimSource DimSource}.
     *
     * The returned ordinal values are packed in a similar way.
     * In some DCPs. the ordinals may be transformed in-place, and the
     * returned array may be the same as the passed array.
     * So any client code should not attempt to reuse the passed ordinal
     * values (although they can certainly reuse the passed array).
     * If there is any problem then the server implementation will throw an
     * exception.  If this happens then the client should not make any
     * assumptions about the state of the ordinal values.
     *
     * @param  ord Packed ordinates of points to transform.
     * @return The packed transformed points. May be <code>ord</code>.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransform#transform(double[],int,double[],int,int)}.
     */
    @Deprecated
    double[] transformList(double[] ord) throws RemoteException;

    /**
     * Gets the derivative of this transform at a point. 
     * If the transform does not have a well-defined derivative at the point,
     * then this function should fail in the usual way for the DCP.
     * The derivative is the matrix of the non-translating portion of the
     * approximate affine map at the point.  The matrix will have dimensions
     * corresponding to the source and target coordinate systems.
     * If the input dimension is M, and the output dimension is N, then
     * the matrix will have size [M][N].  The elements of the matrix
     * <code>{elt[n][m]&nbsp;:&nbsp;n=0..(N-1)}</code> form a vector in the
     * output space which is parallel to the displacement caused by a small
     * change in the m'th ordinate in the input space.
     *
     * @param  cp Point in domain at which to get derivative.
     * @return The derivative of this transform at the suplied point.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransform#derivative}.
     */
    @Deprecated
    PT_Matrix derivative(PT_CoordinatePoint cp) throws RemoteException;

    /**
     * Creates the inverse transform of this object.
     * This method may fail if the transform is not one to one.
     * However, all cartographic projections should succeed.
     *
     * @return The inverse transform.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransform#inverse}.
     */
    @Deprecated
    CT_MathTransform inverse() throws RemoteException;

    /**
     * Gets the dimension of input points.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransform#getDimSource}.
     */
    @Deprecated
    int getDimSource() throws RemoteException;

    /**
     * Gets the dimension of output points.
     *
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransform#getDimTarget}.
     */
    @Deprecated
    int getDimTarget() throws RemoteException;

    /**
     * Tests whether this transform does not move any points.
     *
     * @return <code>true</code> if this <code>MathTransform</code> is
     *         an identity transform; <code>false</code> otherwise.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransform#isIdentity}.
     */
    @Deprecated
    boolean isIdentity() throws RemoteException;
}
