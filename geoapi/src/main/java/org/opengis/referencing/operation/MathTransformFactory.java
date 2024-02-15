/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.referencing.operation;

import java.util.Set;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.crs.*; // Contains some import for javadoc.
import org.opengis.parameter.*;       // Contains some import for javadoc.
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Low level factory for creating {@linkplain MathTransform math transforms}.
 * Many high level GIS applications will never need to use this factory directly;
 * they can use a {@linkplain CoordinateOperationFactory coordinate operation factory}
 * instead. However, the {@code MathTransformFactory} interface can be used directly
 * by applications that wish to transform other types of coordinates (e.g. color coordinates,
 * or image pixel coordinates).
 * <p>
 * A {@linkplain MathTransform math transform} is an object that actually does
 * the work of applying formulae to coordinate values. The math transform does
 * not know or care how the coordinates relate to positions in the real world.
 * This lack of semantics makes implementing {@code MathTransformFactory}
 * significantly easier than it would be otherwise.
 * <p>
 * For example the affine transform applies a matrix to the coordinates
 * without knowing how what it is doing relates to the real world. So if
 * the matrix scales <var>Z</var> values by a factor of 1000, then it could
 * be converting meters into millimeters, or it could be converting kilometers
 * into meters.
 * <p>
 * Because {@linkplain MathTransform math transforms} have low semantic value
 * (but high mathematical value), programmers who do not have much knowledge
 * of how GIS applications use coordinate systems, or how those coordinate
 * systems relate to the real world can implement {@code MathTransformFactory}.
 * The low semantic content of {@linkplain MathTransform math transforms} also
 * means that they will be useful in applications that have nothing to do with
 * GIS coordinates. For example, a math transform could be used to map color
 * coordinates between different color spaces, such as converting (red, green, blue)
 * colors into (hue, light, saturation) colors.
 * <p>
 * Since a {@linkplain MathTransform math transform} does not know what its source
 * and target coordinate systems mean, it is not necessary or desirable for a math
 * transform object to keep information on its source and target coordinate systems.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see <A HREF="http://www.remotesensing.org/geotiff/proj_list/">Projection transform list on RemoteSensing.org</A>
 */
@UML(identifier="CT_MathTransformFactory", specification=OGC_01009)
public interface MathTransformFactory extends Factory {
    /**
     * Returns a set of available methods for {@linkplain MathTransform math transforms}. For
     * each element in this set, the {@linkplain OperationMethod#getName operation method name}
     * must be known to the {@link #getDefaultParameters(String)} method in this factory.
     * The set of available methods is implementation dependent.
     *
     * @param  type <code>{@linkplain SingleOperation}.class</code> for fetching all operation methods,
     *         or <code>{@linkplain Projection}.class</code> for fetching only map projection methods.
     * @return All {@linkplain MathTransform math transform} methods available in this factory.
     *
     * @departure extension
     *   This method is not part of the OGC specification. It has been added as a way to publish
     *   the capabilities of a factory.
     *
     * @see #getDefaultParameters(String)
     * @see #createParameterizedTransform(ParameterValueGroup)
     */
    Set<OperationMethod> getAvailableMethods(Class<? extends SingleOperation> type);

    /**
     * Returns the operation method used for the latest call to
     * {@link #createParameterizedTransform createParameterizedTransform},
     * or {@code null} if not applicable.
     * <p>
     * Implementors should document how their implementation behave in a multi-threads environment.
     * For example some implementations use {@linkplain java.lang.ThreadLocal thread local variables},
     * while other can choose to returns {@code null} in all cases since this method is optional.
     * <p>
     * Note that this method may apply as well to convenience methods that delegate their work to
     * {@code createParameterizedTransform}, like {@link #createBaseToDerived createBaseToDerived}.
     *
     * @return The last method used, or {@code null} if unknown of unsupported.
     *
     * @departure extension
     *   This method is not part of the OGC specification. It has been added because this information
     *   appears to be needed in practice. A more object-oriented approach would have been to
     *   return a {<code>MathTransform</code>, <code>OperationMethod</code>} tuple in the
     *   <code>createParameterizedTransform(&hellip)</code> method, but we wanted to keep the
     *   later unchanged for historical reasons (it is inherited from OGC 01-009) and because
     *   only a minority of use cases need the operation method.
     *   <p>
     *   Note that the existence of this method does not break thread-safety if the implementor
     *   stores this information in a <code>ThreadLocal</code> variable.
     *
     * @since 2.1
     */
    OperationMethod getLastMethodUsed();

    /**
     * Returns the default parameter values for a math transform using the given method.
     * The {@code method} argument is the name of any operation method returned by
     * <code>{@link #getAvailableMethods getAvailableMethods}({@linkplain CoordinateOperation}.class)</code>.
     * A typical example is
     * <code>"<A HREF="http://www.remotesensing.org/geotiff/proj_list/transverse_mercator.html">Transverse_Mercator</A>"</code>).
     * <P>
     * The {@linkplain ParameterDescriptorGroup#getName parameter group name} shall be the
     * method name, or an alias to be understood by <code>{@linkplain #createParameterizedTransform
     * createParameterizedTransform}(parameters)</code>. This method creates new parameter instances
     * at every call. Parameters are intended to be modified by the user before to be given to the
     * above-cited {@code createParameterizedTransform} method.
     *
     * @param  method The case insensitive name of the method to search for.
     * @return The default parameter values.
     * @throws NoSuchIdentifierException if there is no transform registered for the specified method.
     *
     * @departure extension
     *   This method is part of the GeoAPI mechanism for defining the math transform parameters
     *   or deriving other transforms.
     *
     * @see #getAvailableMethods(Class)
     * @see #createParameterizedTransform(ParameterValueGroup)
     */
    ParameterValueGroup getDefaultParameters(String method) throws NoSuchIdentifierException;

    /**
     * Creates a {@linkplain #createParameterizedTransform parameterized transform} from a base CRS
     * to a derived CS. This convenience method {@linkplain #createConcatenatedTransform concatenates}
     * the parameterized transform with any other transform required for performing units changes and
     * ordinates swapping, as described in the {@linkplain #createParameterizedTransform note on
     * cartographic projections}.
     * <p>
     * In addition, implementations are encouraged to infer the {@code "semi_major"} and
     * {@code "semi_minor"} parameter values from the {@linkplain Ellipsoid ellipsoid}, if
     * they are not explicitly given.
     *
     * @param  baseCRS The source coordinate reference system.
     * @param  parameters The parameter values for the transform.
     * @param  derivedCS The target coordinate system.
     * @return The parameterized transform.
     * @throws NoSuchIdentifierException if there is no transform registered for the method.
     * @throws FactoryException if the object creation failed. This exception is thrown
     *         if some required parameter has not been supplied, or has illegal value.
     *
     * @departure extension
     *   This method is part of the GeoAPI mechanism for defining the math transform parameters
     *   or deriving other transforms.
     *
     * @since 2.1
     */
    MathTransform createBaseToDerived(CoordinateReferenceSystem baseCRS,
                                      ParameterValueGroup       parameters,
                                      CoordinateSystem          derivedCS)
            throws NoSuchIdentifierException, FactoryException;

    /**
     * Creates a transform from a group of parameters. The method name is inferred from
     * the {@linkplain ParameterDescriptorGroup#getName parameter group name}. Example:
     *
     * <blockquote><pre>
     * ParameterValueGroup p = factory.getDefaultParameters("Transverse_Mercator");
     * p.parameter("semi_major").setValue(6378137.000);
     * p.parameter("semi_minor").setValue(6356752.314);
     * MathTransform mt = factory.createParameterizedTransform(p);
     * </pre></blockquote>
     *
     * <b>Note on cartographic projections:</b>
     * <P>Cartographic projection transforms are used by {@linkplain ProjectedCRS projected coordinate reference systems}
     * to map geographic coordinates (e.g. <var>longitude</var> and <var>latitude</var>) into (<var>x</var>,<var>y</var>)
     * coordinates. These (<var>x</var>,<var>y</var>) coordinates can be imagined to lie on a plane, such as a paper map
     * or a screen. All cartographic projection transforms created through this method will have the following properties:</P>
     * <UL>
     *   <LI>Converts from (<var>longitude</var>,<var>latitude</var>) coordinates to (<var>x</var>,<var>y</var>).</LI>
     *   <LI>All angles are assumed to be degrees, and all distances are assumed to be meters.</LI>
     *   <LI>The domain shall be a subset of {[-180,180)&times;(-90,90)}.</LI>
     * </UL>
     * <P>Although all cartographic projection transforms must have the properties listed above, many projected coordinate
     * reference systems have different properties. For example, in Europe some projected CRSs use grads instead of degrees,
     * and often the {@linkplain ProjectedCRS#getBaseCRS base geographic CRS} is (<var>latitude</var>, <var>longitude</var>)
     * instead of (<var>longitude</var>, <var>latitude</var>). This means that the cartographic projected transform is often
     * used as a single step in a series of transforms, where the other steps change units and swap ordinates.</P>
     *
     * @param  parameters The parameter values.
     * @return The parameterized transform.
     * @throws NoSuchIdentifierException if there is no transform registered for the method.
     * @throws FactoryException if the object creation failed. This exception is thrown
     *         if some required parameter has not been supplied, or has illegal value.
     *
     * @see #getDefaultParameters(String)
     * @see #getAvailableMethods(Class)
     */
    @UML(identifier="createParameterizedTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createParameterizedTransform(ParameterValueGroup parameters)
            throws NoSuchIdentifierException, FactoryException;

    /**
     * Creates an affine transform from a matrix.
     * If the transform's input dimension is {@code M}, and output dimension
     * is {@code N}, then the matrix will have size {@code [N+1][M+1]}.
     * The +1 in the matrix dimensions allows the matrix to do a shift, as well as
     * a rotation. The {@code [M][j]} element of the matrix will be the <var>j</var>'th
     * ordinate of the moved origin. The {@code [i][N]} element of the matrix
     * will be 0 for <var>i</var> less than {@code M}, and 1 for <var>i</var>
     * equals {@code M}.
     *
     * @param matrix The matrix used to define the affine transform.
     * @return The affine transform.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createAffineTransform", obligation=MANDATORY, specification=OGC_01009)
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
     */
    @UML(identifier="createConcatenatedTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createConcatenatedTransform(MathTransform transform1,
                                              MathTransform transform2) throws FactoryException;

    /**
     * Creates a transform which passes through a subset of ordinates to another transform.
     * This allows transforms to operate on a subset of ordinates. For example giving
     * (<var>latitude</var>, <var>longitude</var>, <var>height</var>) coordinates, a pass
     * through transform can convert the height values from meters to feet without affecting
     * the (<var>latitude</var>, <var>longitude</var>) values.
     *
     * @param  firstAffectedOrdinate The lowest index of the affected ordinates.
     * @param  subTransform Transform to use for affected ordinates.
     * @param  numTrailingOrdinates Number of trailing ordinates to pass through.
     *         Affected ordinates will range from {@code firstAffectedOrdinate}
     *         inclusive to {@code dimTarget-numTrailingOrdinates} exclusive.
     * @return A pass through transform with the following dimensions:<br>
     *         <pre>
     * Source: firstAffectedOrdinate + subTransform.getDimSource() + numTrailingOrdinates
     * Target: firstAffectedOrdinate + subTransform.getDimTarget() + numTrailingOrdinates</pre>
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createPassThroughTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createPassThroughTransform(int firstAffectedOrdinate,
                                             MathTransform subTransform,
                                             int numTrailingOrdinates) throws FactoryException;

    /**
     * Creates a math transform object from a XML string.
     *
     * @param  xml Math transform encoded in XML format.
     * @return The math transform (never {@code null}).
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createFromXML", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createFromXML(String xml) throws FactoryException;

    /**
     * Creates a math transform object from a string.
     * The <A HREF="../doc-files/WKT.html">definition for WKT</A> is
     * shown using Extended Backus Naur Form (EBNF).
     *
     * @param  wkt Math transform encoded in Well-Known Text format.
     * @return The math transform (never {@code null}).
     * @throws FactoryException if the Well-Known Text cannot be parsed,
     *         or if the math transform creation failed from some other reason.
     */
    @UML(identifier="createFromWKT", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createFromWKT(String wkt) throws FactoryException;
}
