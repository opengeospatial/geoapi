/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.referencing.operation;

import java.util.Set;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.referencing.cs.CoordinateSystem;
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
 * This lack of semantics makes implementing {@code MathTransformFactory} significantly easier
 * than it would be otherwise.</p>
 *
 * <p>For example the affine transform applies a matrix to the coordinates
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
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see <a href="http://www.remotesensing.org/geotiff/proj_list/">Projection transform list on RemoteSensing.org</a>
 */
@UML(identifier="CT_MathTransformFactory", specification=OGC_01009)
public interface MathTransformFactory extends Factory {
    /*
     * NOTE FOR JAVADOC WRITER:
     * The "method" word is ambiguous here, because it can be "Java method" or "coordinate operation method".
     * In this interface, we reserve "method" for coordinate operation methods as much as possible. For Java
     * methods, we rather use "constructor" or "function".
     */

    /**
     * Returns a set of available methods for coordinate operations of the given type.
     * For each element in this set, the {@linkplain OperationMethod#getName() operation method name}
     * must be a valid argument for {@link #getDefaultParameters(String)}.
     *
     * <p>The {@code type} argument can be used for filtering the kind of operations described by the returned
     * {@code OperationMethod}s. The argument is usually (but not restricted to) one of the following types:</p>
     *
     * <ul>
     *   <li>{@link Transformation} for coordinate operations described by empirically derived parameters.</li>
     *   <li>{@link Conversion} for coordinate operations described by definitions.</li>
     *   <li>{@link Projection} for conversions from geodetic latitudes and longitudes to plane (map) coordinates.</li>
     *   <li>{@link SingleOperation} for all coordinate operations, regardless of their type.</li>
     * </ul>
     *
     * The returned set may conservatively contain more {@code OperationMethod} elements than requested
     * if this {@code MathTransformFactory} does not support filtering by the given type.
     *
     * @param  type <code>{@linkplain SingleOperation}.class</code> for fetching all operation methods,
     *         <code>{@linkplain Projection}.class</code> for fetching only map projection methods, <i>etc</i>.
     * @return methods available in this factory for coordinate operations of the given type.
     *
     * @departure extension
     *   This method is not part of the OGC specification.
     *   It has been added as a way to publish the capabilities of a factory.
     *
     * @see #getDefaultParameters(String)
     * @see #createParameterizedTransform(ParameterValueGroup)
     * @see CoordinateOperationFactory#getOperationMethod(String)
     */
    Set<OperationMethod> getAvailableMethods(Class<? extends SingleOperation> type);

    /**
     * Returns the operation method used by the latest call to a {@code create(…)} constructor,
     * or {@code null} if not applicable.
     *
     * <p>Implementors should document how their implementation behave in a multi-threads environment.
     * For example some implementations use {@linkplain java.lang.ThreadLocal thread local variables},
     * while other can choose to returns {@code null} in all cases since {@code getLastMethodUsed()}
     * is optional.</p>
     *
     * <p>Invoking {@code getLastMethodUsed()} can be useful after a call to
     * {@link #createParameterizedTransform createParameterizedTransform(…)}, or after a call to another
     * constructor that delegates its work to {@code createParameterizedTransform(…)}, for example
     * {@link #createBaseToDerived createBaseToDerived(…)}.</p>
     *
     * @return the last method used by a {@code create(…)} constructor, or {@code null} if unknown of unsupported.
     *
     * @see #createParameterizedTransform(ParameterValueGroup)
     *
     * @departure extension
     *   This method is not part of the OGC specification.
     *   It has been added because this information appears to be important in some situations.
     *   We did not defined a {{@code MathTransform}, {@code OperationMethod}} tuple in order
     *   to keep {@code create(…)} simpler in the common case where the operation method is not needed,
     *   and for historical reasons (conformance to OGC 01-009).
     */
    OperationMethod getLastMethodUsed();

    /**
     * Returns the default parameter values for a math transform using the given operation method.
     * The {@code method} argument is the name of any {@code OperationMethod} instance returned by
     * <code>{@link #getAvailableMethods(Class) getAvailableMethods}({@linkplain SingleOperation}.class)</code>.
     * A typical example is
     * "<a href="http://www.remotesensing.org/geotiff/proj_list/transverse_mercator.html">Transverse Mercator</a>").
     *
     * <p>The {@linkplain ParameterDescriptorGroup#getName() parameter group name} shall be a method name or identifier
     * that {@link #createParameterizedTransform(ParameterValueGroup)} can recognize without ambiguity.</p>
     *
     * <p>This function creates new parameter instances at every call.
     * Parameters are intended to be modified by the user before to be given to the above-cited
     * {@code createParameterizedTransform(…)} constructor.</p>
     *
     * @param  method  the case insensitive name of the coordinate operation method to search for.
     * @return a new group of parameter values for the {@code OperationMethod} identified by the given name.
     * @throws NoSuchIdentifierException if there is no method registered for the given name or identifier.
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
     * to a derived CS. This convenience constructor {@linkplain #createConcatenatedTransform concatenates}
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
     * @throws FactoryException if the object creation failed. This exception is thrown
     *         if some required parameter has not been supplied, or has illegal value.
     *
     * @departure extension
     *   This method is part of the GeoAPI mechanism for defining the math transform parameters
     *   or deriving other transforms.
     */
    MathTransform createBaseToDerived(CoordinateReferenceSystem baseCRS,
                                      ParameterValueGroup       parameters,
                                      CoordinateSystem          derivedCS)
            throws NoSuchIdentifierException, FactoryException;

    /**
     * Creates a transform from a group of parameters. The {@link OperationMethod} name is inferred from
     * the {@linkplain ParameterDescriptorGroup#getName() parameter group name}. Example:
     *
     * <blockquote><pre>ParameterValueGroup p = factory.getDefaultParameters("Transverse_Mercator");
     * p.parameter("semi_major").setValue(6378137.000);
     * p.parameter("semi_minor").setValue(6356752.314);
     * MathTransform mt = factory.createParameterizedTransform(p);</pre></blockquote>
     *
     * <section class="note">
     * <h2>Note on cartographic projections:</h2>
     * Cartographic projection transforms are used by {@linkplain ProjectedCRS projected coordinate reference systems}
     * to map geographic coordinates (e.g. <var>longitude</var> and <var>latitude</var>) into (<var>x</var>,<var>y</var>)
     * coordinates. These (<var>x</var>,<var>y</var>) coordinates can be imagined to lie on a plane, such as a paper map
     * or a screen. All cartographic projection transforms created through this constructor will have the following
     * properties:
     *
     * <ul>
     *   <li>Converts from (<var>longitude</var>,<var>latitude</var>) coordinates to (<var>x</var>,<var>y</var>).</li>
     *   <li>All angles are assumed to be degrees, and all distances are assumed to be meters.</li>
     *   <li>The domain shall be a subset of {[-180,180)×(-90,90)}.</li>
     *   <li>Axis directions are usually ({@linkplain org.opengis.referencing.cs.AxisDirection#EAST east},
     *       {@linkplain org.opengis.referencing.cs.AxisDirection#NORTH north}), but exceptions may exist
     *       for some operation methods like <cite>"Lambert Conic Conformal (West Orientated)"</cite>
     *       (EPSG:9826) or <cite>"Transverse Mercator (South Orientated)"</cite> (EPSG:9808).</li>
     * </ul>
     *
     * Although all cartographic projection transforms must have the properties listed above, many projected coordinate
     * reference systems have different properties. For example, in Europe some projected CRSs use grads instead of degrees,
     * and often the {@linkplain ProjectedCRS#getBaseCRS() base geographic CRS} is (<var>latitude</var>, <var>longitude</var>)
     * instead of (<var>longitude</var>, <var>latitude</var>). This means that the cartographic projected transform is often
     * used as a single step in a series of transforms, where the other steps change units and swap coordinates.
     *
     * <p>When the change of axis directions is part of the map projection definition as in <cite>"Transverse Mercator
     * (South Orientated)"</cite>, there is a conflict with the above-cited (<var>east</var>, <var>north</var>) directions.
     * In such cases the {@code createParameterizedTransform(…)} behavior is implementation specific, since different
     * libraries may resolve this conflict in different ways.
     * Users can invoke {@link #createBaseToDerived createBaseToDerived(…)} instead for more determinist results.</p>
     * </section>
     *
     * @param  parameters  the parameter values.
     * @return the parameterized transform.
     * @throws NoSuchIdentifierException if there is no transform registered for the coordinate operation method.
     * @throws FactoryException if the object creation failed. This exception is thrown
     *         if some required parameter has not been supplied, or has illegal value.
     *
     * @see #getDefaultParameters(String)
     * @see #getAvailableMethods(Class)
     * @see #getLastMethodUsed()
     */
    @UML(identifier="createParameterizedTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createParameterizedTransform(ParameterValueGroup parameters)
            throws NoSuchIdentifierException, FactoryException;

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
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createAffineTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createAffineTransform(Matrix matrix) throws FactoryException;

    /**
     * Creates a transform by concatenating two existing transforms.
     * A concatenated transform acts in the same way as applying two
     * transforms, one after the other.
     *
     * <p>The dimension of the output space of the first transform must match the dimension
     * of the input space in the second transform. If you wish to concatenate more than two
     * transforms, then you can repeatedly use this constructor.</p>
     *
     * @param  transform1  the first transform to apply to points.
     * @param  transform2  the second transform to apply to points.
     * @return the concatenated transform.
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createConcatenatedTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createConcatenatedTransform(MathTransform transform1,
                                              MathTransform transform2) throws FactoryException;

    /**
     * Creates a transform which passes through a subset of coordinates to another transform.
     * This allows transforms to operate on a subset of coordinates. For example giving
     * (<var>latitude</var>, <var>longitude</var>, <var>height</var>) coordinates, a pass
     * through transform can convert the height values from meters to feet without affecting
     * the (<var>latitude</var>, <var>longitude</var>) values.
     *
     * @param  firstAffectedCoordinate  the lowest index of the affected coordinates.
     * @param  subTransform             transform to use for affected coordinates.
     * @param  numTrailingCoordinates   number of trailing coordinates to pass through.
     *         Affected coordinates will range from {@code firstAffectedCoordinate}
     *         inclusive to {@code dimTarget-numTrailingCoordinates} exclusive.
     * @return a pass through transform with the following dimensions:<br>
     *         <pre>
     * Source: firstAffectedCoordinate + subTransform.getDimSource() + numTrailingCoordinates
     * Target: firstAffectedCoordinate + subTransform.getDimTarget() + numTrailingCoordinates</pre>
     * @throws FactoryException if the object creation failed.
     */
    @UML(identifier="createPassThroughTransform", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createPassThroughTransform(int firstAffectedCoordinate,
                                             MathTransform subTransform,
                                             int numTrailingCoordinates) throws FactoryException;

    /**
     * Creates a math transform object from a <cite>Well-Known Text</cite>.
     * The <a href="../doc-files/WKT.html">definition for WKT</a> is
     * shown using Extended Backus Naur Form (EBNF).
     *
     * @param  wkt  math transform encoded in Well-Known Text format.
     * @return the math transform (never {@code null}).
     * @throws FactoryException if the Well-Known Text can't be parsed,
     *         or if the math transform creation failed from some other reason.
     *
     * @see MathTransform#toWKT()
     */
    @UML(identifier="createFromWKT", obligation=MANDATORY, specification=OGC_01009)
    MathTransform createFromWKT(String wkt) throws FactoryException;
}
