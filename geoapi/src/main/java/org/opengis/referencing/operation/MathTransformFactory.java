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
 * @version 4.0
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
     */
    Set<OperationMethod> getAvailableMethods(Class<? extends SingleOperation> type);

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
