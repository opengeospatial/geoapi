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
import org.opengis.pt.PT_Matrix;

// Various JDK's classes
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Creates math transforms.
 * <code>CT_MathTransformFactory</code> is a low level factory that is used to
 * create {@link CT_MathTransform} objects. Many high level GIS applications
 * will never need to use a <code>CT_MathTransformFactory</code> directly; they
 * can use a {@link CT_CoordinateTransformationFactory} instead.  However, the
 * <code>CT_MathTransformFactory</code> interface is specified here, since it
 * can be used directly by applications that wish to transform other types of
 * coordinates (e.g. color coordinates, or image pixel coordinates).
 * <br><br>
 * The following comments assume that the same vendor implements the math
 * transform factory interfaces and math transform interfaces.
 * <br><br>
 * A math transform is an object that actually does the work of applying
 * formulae to coordinate values.  The math transform does not know or
 * care how the coordinates relate to positions in the real world.  This
 * lack of semantics makes implementing <code>CT_MathTransformFactory</code>
 * significantly easier than it would be otherwise.
 * <br><br>
 * For example <code>CT_MathTransformFactory</code> can create affine math
 * transforms.  The affine transform applies a matrix to the coordinates
 * without knowing how what it is doing relates to the real world.  So if
 * the matrix scales <var>Z</var> values by a factor of 1000, then it could
 * be converting meters into millimeters, or it could be converting kilometers
 * into meters.
 * <br><br>
 * Because math transforms have low semantic value (but high mathematical
 * value), programmers who do not have much knowledge of how GIS applications
 * use coordinate systems, or how those coordinate systems relate to the real
 * world can implement <code>CT_MathTransformFactory</code>.
 * <br><br>
 * The low semantic content of math transforms also means that they will be
 * useful in applications that have nothing to do with GIS coordinates.  For
 * example, a math transform could be used to map color coordinates between
 * different color spaces, such as converting (red, green, blue) colors into
 * (hue, light, saturation) colors.
 * <br><br>
 * Since a math transform does not know what its source and target coordinate
 * systems mean, it is not necessary or desirable for a math transform object
 * to keep information on its source and target coordinate systems.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 *
 * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransformFactory}.
 */
public interface CT_MathTransformFactory extends Remote {
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
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransformFactory#createAffineTransform}
     */
    CT_MathTransform createAffineTransform(PT_Matrix matrix) throws RemoteException;

    /**
     * Creates a transform by concatenating two existing transforms.
     * A concatenated transform acts in the same way as applying two
     * transforms, one after the other.
     * <br><br>
     * The dimension of the output space of the first transform must match
     * the dimension of the input space in the second transform.
     * If you wish to concatenate more than two transforms, then you can
     * repeatedly use this method.
     *
     * @param  transform1 The first transform to apply to points.
     * @param  transform2 The second transform to apply to points.
     * @return The concatenated transform.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransformFactory#createConcatenatedTransform}
     */
    CT_MathTransform createConcatenatedTransform(CT_MathTransform transform1, CT_MathTransform transform2) throws RemoteException;

    /**
     * Creates a transform which passes through a subset of ordinates to another transform.
     * This allows transforms to operate on a subset of ordinates.  For example,
     * if you have (Lat,Lon,Height) coordinates, then you may wish to convert the
     * height values from meters to feet without affecting the (Lat,Lon) values.
     * If you wanted to affect the (Lat,Lon) values and leave the Height values
     * alone, then you would have to swap the ordinates around to
     * (Height,Lat,Lon).  You can do this with an affine map.
     *
     * @param  firstAffectedOrdinate The lowest index of the affected ordinates.
     * @param  subTransform Transform to use for affected ordinates.
     * @return The pass through transform.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransformFactory#createPassThroughTransform}
     */
    CT_MathTransform createPassThroughTransform(int firstAffectedOrdinate, CT_MathTransform subTransform) throws RemoteException;

    /**
     * Creates a transform from a classification name and parameters.
     * The client must ensure that all the linear parameters are expressed in
     * meters, and all the angular parameters are expressed in degrees.  Also,
     * they must supply "semi_major" and "semi_minor" parameters for
     * cartographic projection transforms. 
     *
     * @param  classification The classification name of the transform (e.g. "Transverse_Mercator").
     * @param  parameters The parameter values in standard units.
     * @return The parameterized transform.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransformFactory#createParameterizedTransform}
     */
    CT_MathTransform createParameterizedTransform(String classification, CT_Parameter[] parameters) throws RemoteException;

    /**
     * Creates a math transform from a Well-Known Text string.
     *
     * @param  wellKnownText Math transform encoded in Well-Known Text format.
     * @return The transform.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransformFactory#createFromWKT}
     */
    CT_MathTransform createFromWKT(String wellKnownText) throws RemoteException;

    /**
     * Creates a math transform from XML.
     *
     * @param xml Math transform encoded in XML format.
     * @return The transform.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Replaced by {@link org.opengis.referencing.operation.MathTransformFactory#createFromXML}
     */
    CT_MathTransform createFromXML(String xml) throws RemoteException;

    /**
     * Tests whether parameter is angular. 
     * Clients must ensure that all angular parameter values are in degrees.
     *
     * @param  parameterName Name of parameter to test.
     * @return <code>true</code> if the parameter is angular.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Check parameter {@link javax.units.Unit unit} instead.
     */
    boolean isParameterAngular(String parameterName) throws RemoteException;

    /**
     * Tests whether parameter is linear. 
     * Clients must ensure that all linear parameter values are in meters.
     *
     * @param parameterName Name of parameter to test.
     * @return <code>true</code> if the parameter is linear.
     * @throws RemoteException if a remote method call failed.
     *
     * @deprecated Check parameter {@link javax.units.Unit unit} instead.
     */
    boolean isParameterLinear(String parameterName) throws RemoteException;
}
