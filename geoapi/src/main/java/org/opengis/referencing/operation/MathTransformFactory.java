/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.crs.*;                   // Contains some import for javadoc.
import org.opengis.parameter.*;                         // Contains some import for javadoc.
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Low level factory for creating {@code MathTransform} instances.
 * Many high level GIS applications will never need to use this factory directly;
 * they can use a {@linkplain CoordinateOperationFactory coordinate operation factory} instead.
 * However, the {@code MathTransformFactory} interface can be used directly
 * by applications that wish to transform other types of coordinates
 * (e.g. color coordinates, or image pixel coordinates).
 *
 * <p>A {@link MathTransform} is an object that actually does the work of applying {@link Formula} to coordinate values.
 * The math transform does not know or care how the coordinates relate to positions in the real world.
 * This lack of semantics makes implementing {@code MathTransformFactory} significantly easier than it would be otherwise.
 * For example, the {@linkplain #createAffineTransform(Matrix) affine transform} applies a matrix to the coordinates
 * without knowing how what it is doing relates to the real world.
 * So if the matrix scales <var>z</var> values by a factor of 1000, then it could be
 * converting metres into millimetres, or it could be converting kilometres into metres.</p>
 *
 * <p>Because {@link MathTransform}s have low semantic value (but high mathematical value),
 * programmers who do not have much knowledge of how GIS applications use coordinate systems,
 * or how those coordinate systems relate to the real world, can implement {@code MathTransformFactory}.
 * The low semantic content of {@link MathTransform}s also means that they will be useful in applications
 * that have nothing to do with GIS coordinates. For example, a math transform could be used to map color
 * coordinates between different color spaces, such as converting (red, green, blue) colors into
 * (hue, light, saturation) colors.</p>
 *
 * <p>Since a {@link MathTransform} does not know what its source and target coordinate systems mean,
 * it is not necessary or desirable for a math transform object to keep information on its source and
 * target coordinate systems.</p>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.referencing.RegisterOperations#getFactory(Class)
 */
@UML(identifier="CT_MathTransformFactory", specification=OGC_01009)
public interface MathTransformFactory extends Factory {
    /*
     * NOTE FOR JAVADOC WRITER:
     * The "method" word is ambiguous here, because it can be "Java method" or "coordinate operation method".
     * In this interface, we reserve "method" for coordinate operation methods as much as possible. For Java
     * methods, we rather use "constructor".
     */

    /**
     * Returns a set of available methods for coordinate operations of the given type.
     * For each element in this set, the {@linkplain OperationMethod#getName() operation method name}
     * should be a valid argument for {@link #builder(String)} or {@link #getDefaultParameters(String)}.
     *
     * <p>The {@code type} argument can be used for filtering the kind of operations described by the returned
     * {@code OperationMethod}s. The argument is usually (but not restricted to) one of the following types:</p>
     *
     * <ul>
     *   <li>{@link Conversion} for coordinate operations described by definitions (including map projections).</li>
     *   <li>{@link Transformation} for coordinate operations described by empirically derived parameters.</li>
     *   <li>{@link PointMotionOperation} for changes due to the motion of the point between two coordinate epochs.</li>
     *   <li>{@link SingleOperation} for all coordinate operations, regardless of their type.</li>
     * </ul>
     *
     * The returned set may conservatively contain more {@code OperationMethod} elements than requested
     * if this {@code MathTransformFactory} does not support filtering by the given type.
     *
     * @param  type  the base type of the desired coordinate operations, or {@code null} for no filtering.
     * @return methods available in this factory for coordinate operations of the given type.
     *
     * @departure extension
     *   This is an addition to the {@link org.opengis.annotation.Specification#OGC_01009 OGC 01-009}
     *   specification for providing a way to declare the capabilities of a math transform factory.
     *
     * @see #builder(String)
     * @see #getDefaultParameters(String)
     * @see #createParameterizedTransform(ParameterValueGroup)
     */
    Set<OperationMethod> getAvailableMethods(Class<? extends SingleOperation> type);

    /**
     * Returns the operation method used by the latest call to a {@code create(…)} constructor,
     * or {@code null} if not applicable.
     *
     * <h4>Implementation note</h4>
     * Implementers should document how their implementation behave in a multi-threads environment.
     * For example, some implementations use {@linkplain java.lang.ThreadLocal thread local variables},
     * while other can choose to return {@code null} in all cases since {@code getLastMethodUsed()} is optional.
     *
     * @return the last method used by a {@code create(…)} constructor, or {@code null} if unknown of unsupported.
     *
     * @deprecated Replaced by {@link MathTransform.Builder#getMethod()} for avoiding ambiguities and for thread safety.
     */
    @Deprecated(since = "3.1")
    default OperationMethod getLastMethodUsed() {
        return null;
    }

    /**
     * Returns a builder for a parameterized math transform using the specified operation method.
     * The {@code method} argument should be the name or authority code of an {@link OperationMethod}
     * instance returned by <code>{@link #getAvailableMethods(Class) getAvailableMethods}(null)</code>.
     * A typical example is <cite>"Transverse Mercator"</cite>.
     *
     * <p>While the use of authority codes is recommended for the {@code method} argument,
     * implementations should also accept method names when they are unambiguous (i.e., have exactly one match).
     * This flexibility is for allowing the parsing of map projections in formats
     * such as Well Known Text (<abbr>WKT</abbr>) version 1.</p>
     *
     * <table class="ogc">
     *   <caption>Example of operation methods</caption>
     *   <tr><th>EPSG code</th><th>EPSG name</th>                      <th>OGC name</th></tr>
     *   <tr><td>9804</td>     <td>Mercator (variant A)</td>           <td>{@code Mercator_1SP}</td></tr>
     *   <tr><td>9805</td>     <td>Mercator (variant B)</td>           <td>{@code Mercator_2SP}</td></tr>
     *   <tr><td>9807</td>     <td>Transverse Mercator</td>            <td>{@code Transverse_Mercator}</td></tr>
     *   <tr><td>9801</td>     <td>Lambert Conic Conformal (1SP)</td>  <td>{@code Lambert_Conformal_Conic_1SP}</td></tr>
     *   <tr><td>9802</td>     <td>Lambert Conic Conformal (2SP)</td>  <td>{@code Lambert_Conformal_Conic_2SP}</td></tr>
     *   <tr><td>9820</td>     <td>Lambert Azimuthal Equal Area</td>   <td>{@code Lambert_Azimuthal_Equal_Area}</td></tr>
     *   <tr><td>9822</td>     <td>Albers Equal Area</td>              <td>{@code Albers_Conic_Equal_Area}</td></tr>
     *   <tr><td>9806</td>     <td>Cassini-Soldner</td>                <td>{@code Cassini_Soldner}</td></tr>
     *   <tr><td>9840</td>     <td>Orthographic</td>                   <td>{@code Orthographic}</td></tr>
     * </table>
     *
     * The list of supported methods is typically hard-coded by the implementation rather than extracted from a registry,
     * because those operations usually need to be associated (directly or indirectly) to executable Java codes doing the
     * actual coordinate operations. The names of all supported methods can be obtained by the following code:
     *
     * {@snippet lang="java" :
     * String[] methodNames = getAvailableMethods(null).stream().map((m) -> m.getName().getCode()).toArray();
     * }
     *
     * @param  method  the case insensitive name or identifier of the desired coordinate operation method.
     * @return a builder for a meth transform implementing the formulas identified by the given method.
     * @throws NoSuchIdentifierException if there is no supported method for the given name or identifier.
     *
     * @see #getAvailableMethods(Class)
     *
     * @departure extension
     *   This is an addition to the {@link org.opengis.annotation.Specification#OGC_01009 OGC 01-009}
     *   specification for facilitating the resolution of ambiguities in some situations such as west-
     *   or south-orientated map projections, and for thread-safety.
     *
     * @since 3.1
     */
    MathTransform.Builder builder(String method) throws NoSuchIdentifierException;

    /**
     * Returns the default parameter values for a math transform which uses the given operation method.
     * A new group of parameter values is created at every call. The values should be modified in-place,
     * then given to the {@link #createParameterizedTransform(ParameterValueGroup)} constructor.
     * The {@linkplain ParameterDescriptorGroup#getName() name of the returned parameter group}
     * may differ from the specified method name as it shall be a method name or identifier that
     * {@code createParameterizedTransform(…)} can recognize without ambiguity.
     *
     * <h4>Recommended alternative</h4>
     * This approach is a shortcut good enough for most map projections,
     * but may be ambiguous in some cases such as west- or south-orientated projections.
     * For more deterministic results, consider using {@link #builder(String)} instead.
     *
     * @param  method  the case insensitive name or identifier of the desired coordinate operation method.
     * @return a new group of parameter values for the {@code OperationMethod} identified by the given name.
     * @throws NoSuchIdentifierException if there is no supported method for the given name or identifier.
     *
     * @departure extension
     *   This is an addition to the {@link org.opengis.annotation.Specification#OGC_01009 OGC 01-009}
     *   for facilitating the creation of parameterized transforms.
     *
     * @see #getAvailableMethods(Class)
     * @see #createParameterizedTransform(ParameterValueGroup)
     */
    default ParameterValueGroup getDefaultParameters(String method) throws NoSuchIdentifierException {
        return builder(method).parameters();
    }

    /**
     * Creates a parameterized transform from a base CRS to a derived CS.
     * This convenience constructor {@linkplain #createConcatenatedTransform concatenates}
     * the parameterized transform with any other transform required for performing units changes and
     * coordinates swapping, as described in the {@linkplain #createParameterizedTransform note on
     * cartographic projections}.
     *
     * <p>In addition, implementations are encouraged to infer the {@code "semi_major"} and
     * {@code "semi_minor"} parameter values from the {@linkplain Ellipsoid ellipsoid}
     * associated to the {@code baseCRS}, if those parameters are not explicitly given
     * and if they are applicable (typically for cartographic projections).
     * This inference is consistent with the EPSG database model.</p>
     *
     * @param  baseCRS     the source coordinate reference system.
     * @param  parameters  the parameter values for the transform.
     * @param  derivedCS   the target coordinate system.
     * @return the parameterized transform from {@code baseCRS} to {@code derivedCS},
     *         including unit conversions and axis swapping.
     * @throws NoSuchIdentifierException if there is no transform registered for the coordinate operation method.
     * @throws FactoryException if the transform creation failed.
     *         This exception is thrown if some required parameter has not been supplied, or has illegal value.
     *
     * @deprecated Replaced by {@link #builder(String)}.
     */
    @Deprecated(since = "3.1")
    default MathTransform createBaseToDerived(CoordinateReferenceSystem baseCRS,
                                              ParameterValueGroup       parameters,
                                              CoordinateSystem          derivedCS)
            throws NoSuchIdentifierException, FactoryException
    {
        final var builder = builder(parameters.getDescriptor().getName().getCode());
        copy(parameters, builder.parameters());
        if (baseCRS != null) {
            Datum     rfm = (baseCRS instanceof SingleCRS) ? ((SingleCRS) baseCRS).getDatum()     : null;
            Ellipsoid eld = (rfm instanceof GeodeticDatum) ? ((GeodeticDatum) rfm).getEllipsoid() : null;
            builder.setSourceAxes(baseCRS.getCoordinateSystem(), eld);
        }
        builder.setTargetAxes(derivedCS, null);
        return builder.create();
    }

    /**
     * Creates a transform from a group of parameters. The {@link OperationMethod} name is inferred from
     * the {@linkplain ParameterDescriptorGroup#getName() parameter group name}. Example:
     *
     * {@snippet lang="java" :
     * ParameterValueGroup p = factory.getDefaultParameters("Transverse_Mercator");
     * p.parameter("semi_major").setValue(6378137.000);
     * p.parameter("semi_minor").setValue(6356752.314);
     * MathTransform mt = factory.createParameterizedTransform(p);
     * }
     *
     * <h4>Note on cartographic projections</h4>
     * Cartographic projection are used by {@link ProjectedCRS} to map geographic coordinates
     * (e.g., <var>longitude</var> and <var>latitude</var>) into (<var>easting</var>, <var>northing</var>) coordinates.
     * The latter coordinates can be imagined to lie on a plane, such as a paper map or a screen.
     * All cartographic projections created through this constructor will have the following properties:
     *
     * <ul>
     *   <li>Converts from (<var>longitude</var>, <var>latitude</var>) coordinates to (<var>easting</var>, <var>northing</var>).</li>
     *   <li>All angles are assumed to be degrees, and all distances are assumed to be meters.</li>
     *   <li>The domain shall be a subset of {[-180,180)×(-90,90)}.</li>
     *   <li>Axis directions are usually ({@linkplain org.opengis.referencing.cs.AxisDirection#EAST east},
     *       {@linkplain org.opengis.referencing.cs.AxisDirection#NORTH north}), but exceptions may exist
     *       for some operation methods like <cite>Lambert Conic Conformal (West Orientated)</cite>
     *       (EPSG:9826) or <cite>Transverse Mercator (South Orientated)</cite> (EPSG:9808).</li>
     * </ul>
     *
     * Although all cartographic projections created by this constructor should have the properties listed above,
     * some projected coordinate reference systems have different properties.
     * For example, in Europe some projected CRSs use grads instead of degrees,
     * and often the {@linkplain ProjectedCRS#getBaseCRS() base geographic CRS} is (<var>latitude</var>, <var>longitude</var>)
     * instead of (<var>longitude</var>, <var>latitude</var>). This means that the cartographic projection is often
     * used as a single step in a series of conversions, where the other steps change units and swap coordinates.
     *
     * <h4>Recommended alternative</h4>
     * When the change of axis directions is part of the map projection definition as in <cite>Transverse Mercator
     * (South Orientated)</cite>, there is a conflict with the above-cited (<var>east</var>, <var>north</var>) directions.
     * In such cases, the {@code createParameterizedTransform(…)} behavior is implementation specific,
     * since different libraries may resolve this conflict in different ways.
     * For more reliable results, users should invoke {@link #builder(String)} instead.
     *
     * @param  parameters  the parameter values.
     * @return the parameterized transform.
     * @throws NoSuchIdentifierException if there is no transform registered for the coordinate operation method.
     * @throws FactoryException if the transform creation failed.
     *         This exception is thrown if some required parameters have not been supplied, or have illegal values.
     *
     * @see #getDefaultParameters(String)
     * @see #getAvailableMethods(Class)
     */
    @UML(identifier="createParameterizedTransform", obligation=MANDATORY, specification=OGC_01009)
    default MathTransform createParameterizedTransform(final ParameterValueGroup parameters)
            throws NoSuchIdentifierException, FactoryException
    {
        final var builder = builder(parameters.getDescriptor().getName().getCode());
        copy(parameters, builder.parameters());
        return builder.create();
    }

    /**
     * Copies the parameter values from one group to another group.
     *
     * @param  source  user-supplied parameter values.
     * @param  target  where to copy the parameters.
     */
    private static void copy(final ParameterValueGroup source, final ParameterValueGroup target) {
        for (final GeneralParameterValue value : source.values()) {
            final String name = value.getDescriptor().getName().getCode();
            if (value instanceof ParameterValueGroup) {
                copy((ParameterValueGroup) value, target.addGroup(name));
            } else {
                final var src  = (ParameterValue) value;
                final var tgt  = target.parameter(name);
                final var unit = src.getUnit();
                if (unit != null) {
                    tgt.setValue(src.doubleValue(), unit);
                } else {
                    tgt.setValue(src.getValue());
                }
            }
        }
    }

    /**
     * Creates an affine transform from a matrix.
     * If the transform's input dimension is {@code M}, and output dimension is {@code N},
     * then the matrix will have size {@code [N+1][M+1]}.
     * The +1 in the matrix dimensions allows the matrix to do a shift, as well as a rotation.
     * The {@code [M][j]} element of the matrix will be the <var>j</var>'th coordinate of the moved origin.
     * The {@code [i][N]} element of the matrix will be 0 for <var>i</var> less than {@code M},
     * and 1 for <var>i</var> equals {@code M}.
     *
     * @param  matrix  the matrix used to define the affine transform.
     * @return the affine transform.
     * @throws FactoryException if the transform creation failed.
     */
    @UML(identifier="createAffineTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createAffineTransform(Matrix matrix) throws FactoryException;

    /**
     * Creates a matrix of size {@code numRow}&nbsp;×&nbsp;{@code numCol}.
     * Elements on the diagonal (<var>j</var> = <var>i</var>) are set to 1.
     *
     * @param  numRow  number of rows.
     * @param  numCol  number of columns.
     * @return a new matrix of the given size.
     * @throws FactoryException if the matrix creation failed.
     *
     * @since 3.1
     */
    Matrix createMatrix(int numRow, int numCol) throws FactoryException;

    /**
     * Creates a transform by concatenating two existing transforms.
     * A concatenated transform acts in the same way as applying two transforms, one after the other.
     * The dimension of the output space of the first transform
     * must match the dimension of the input space in the second transform.
     * For concatenating more than two transforms, this constructor can be invoked repeatedly.
     *
     * @param  transform1  the first transform to apply to points.
     * @param  transform2  the second transform to apply to points.
     * @return the concatenated transform.
     * @throws FactoryException if the transform creation failed.
     */
    @UML(identifier="createConcatenatedTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createConcatenatedTransform(MathTransform transform1,
                                              MathTransform transform2) throws FactoryException;

    /**
     * Creates a transform which passes through a subset of coordinates to another transform.
     * This allows transforms to operate on a subset of coordinates. For example, giving
     * (<var>latitude</var>, <var>longitude</var>, <var>height</var>) coordinates, a pass
     * through transform can convert the height values from meters to feet without affecting
     * the (<var>latitude</var>, <var>longitude</var>) values.
     *
     * <h4>Number of dimensions</h4>
     * Affected coordinates will range from {@code firstAffectedCoordinate} inclusive
     * to {@code dimTarget-numTrailingCoordinates} exclusive.
     * The returned transform will have the following number of dimensions:
     *
     * <ul>
     *   <li>Source: {@code firstAffectedCoordinate + subTransform.getDimSource() + numTrailingCoordinates}</li>
     *   <li>Target: {@code firstAffectedCoordinate + subTransform.getDimTarget() + numTrailingCoordinates}</li>
     * </ul>
     *
     * @param  firstAffectedCoordinate  the lowest index of the affected coordinates.
     * @param  subTransform             transform to use for affected coordinates.
     * @param  numTrailingCoordinates   number of trailing coordinates to pass through.
     * @return a pass through transform with the dimensions documented above.
     * @throws FactoryException if the transform creation failed.
     */
    @UML(identifier="createPassThroughTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createPassThroughTransform(int firstAffectedCoordinate,
                                             MathTransform subTransform,
                                             int numTrailingCoordinates) throws FactoryException;

    /**
     * Creates a math transform object from a <i>Well-Known Text</i>.
     * The WKT of math transforms is defined in {@linkplain org.opengis.annotation.Specification#OGC_01009 OGC 01-009}.
     *
     * @param  wkt  math transform encoded in Well-Known Text format.
     * @return the math transform (never {@code null}).
     * @throws FactoryException if the Well-Known Text cannot be parsed,
     *         or if the math transform creation failed from some other reason.
     *
     * @see MathTransform#toWKT()
     */
    @UML(identifier="createFromWKT", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createFromWKT(String wkt) throws FactoryException;
}
