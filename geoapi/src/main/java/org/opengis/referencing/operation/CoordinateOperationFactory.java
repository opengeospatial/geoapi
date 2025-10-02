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

import java.util.Map;

import org.opengis.annotation.UML;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.util.FactoryException;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ObjectDomain;
import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.CRSFactory;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.quality.PositionalAccuracy;

import static org.opengis.annotation.Specification.*;


/**
 * Creates coordinate operations from parameter values. {@code CoordinateOperationFactory}
 * allows applications to make {@linkplain CoordinateOperation coordinate operations}
 * that cannot be created by a {@link CoordinateOperationAuthorityFactory}.
 * This factory is very flexible, whereas the authority factory is easier to use.
 *
 * <h2>Metadata</h2>
 * All factory methods expect metadata as <i>key</i>-<i>value</i> pairs in a {@code Map<String,?>} argument,
 * followed by one or more arguments of specific types. As a general rule, the {@code Map<String,?>} argument
 * contains metadata having no incidence on the numerical results of coordinate operations,
 * while changes in the other arguments can cause changes in the numerical results.
 * The standard keys for the {@code Map<String,?>} argument are listed in the {@link ObjectFactory} interface.
 * The following table lists the additional properties for coordinate operation definitions,
 * together with a reminder of some properties defined in {@code ObjectFactory}:
 *
 * <table class="ogc">
 *   <caption>Keys for standard properties</caption>
 *   <tr>
 *     <th>Key</th>
 *     <th>Value type</th>
 *     <th>Value returned by</th>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.operation.CoordinateOperation#OPERATION_VERSION_KEY}</td>
 *     <td>{@link String}</td>
 *     <td>{@link CoordinateOperation#getOperationVersion()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.operation.CoordinateOperation#COORDINATE_OPERATION_ACCURACY_KEY}</td>
 *     <td>{@link PositionalAccuracy}</td>
 *     <td>{@link CoordinateOperation#getCoordinateOperationAccuracy()}</td>
 *   </tr><tr>
 *     <th colspan="3" class="hsep">Defined in parent interface (reminder)</th>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#NAME_KEY}</td>
 *     <td>{@link Identifier}</td>
 *     <td>{@link IdentifiedObject#getName()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#ALIAS_KEY}</td>
 *     <td><code>{@linkplain GenericName}[]</code></td>
 *     <td>{@link IdentifiedObject#getAlias()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#IDENTIFIERS_KEY}</td>
 *     <td><code>{@linkplain Identifier}[]</code></td>
 *     <td>{@link IdentifiedObject#getIdentifiers()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#DOMAINS_KEY}</td>
 *     <td><code>{@linkplain ObjectDomain}[]</code></td>
 *     <td>{@link IdentifiedObject#getDomains()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#REMARKS_KEY}</td>
 *     <td>{@link InternationalString}</td>
 *     <td>{@link IdentifiedObject#getRemarks()}</td>
 *   </tr>
 * </table>
 *
 * <h2>Default methods</h2>
 * All {@code create(…)} methods in this interface are optional.
 * If a method is not overridden by the implementer,
 * the default is to throw an {@link UnimplementedServiceException}
 * with a message saying that the type or service is not supported.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 */
@UML(identifier="CT_CoordinateTransformationFactory", specification=OGC_01009)
public interface CoordinateOperationFactory extends ObjectFactory {
    /**
     * Returns an operation for conversion or transformation between two coordinate reference systems.
     *
     * <ul>
     *   <li>If an operation exists, it is returned.</li>
     *   <li>If more than one operation exists, the default is returned.</li>
     *   <li>If no operation exists, then the exception is thrown.</li>
     * </ul>
     *
     * Implementations may try to query an authority factory first,
     * and compute the operation next if no operation from
     * {@code source} to {@code target} code was explicitly defined by the authority.
     *
     * @param  sourceCRS  input coordinate reference system.
     * @param  targetCRS  output coordinate reference system.
     * @return a coordinate operation from {@code sourceCRS} to {@code targetCRS}.
     * @throws OperationNotFoundException if no operation path was found from {@code sourceCRS}
     *         to {@code targetCRS}.
     * @throws FactoryException if the operation creation failed for some other reason.
     *
     * @deprecated Moved to {@link RegisterOperations#findCoordinateOperations(CoordinateReferenceSystem,
     * CoordinateReferenceSystem) RegisterOperations}. The latter is defined by the ISO 19111:2019 standard.
     * Another reason is that this method requires the use of a geodetic registry.
     */
    @Deprecated(since = "3.1")
    @UML(identifier="createFromCoordinateSystems", specification=OGC_01009)
    default CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS,
                                                CoordinateReferenceSystem targetCRS) throws FactoryException
    {
        throw new UnimplementedServiceException("Cannot infer coordinate operations between a pair of CRSs.");
    }

    /**
     * Returns an operation using a particular method for conversion or transformation
     * between two coordinate reference systems.
     *
     * <ul>
     *   <li>If the operation exists on the implementation, then it is returned.</li>
     *   <li>If the operation does not exist on the implementation, then the implementation
     *       has the option of inferring the operation from the argument objects.</li>
     *   <li>If for whatever reason the specified operation will not be returned, then
     *       the exception is thrown.</li>
     * </ul>
     *
     * <b>Example:</b> A transformation between two {@linkplain GeographicCRS geographic CRS} using
     * different {@linkplain GeodeticDatum datum} requires a <i>datum shift</i>.
     * Many methods exist for this purpose, including interpolations in a grid,
     * a scale/rotation/translation in geocentric coordinates or the Molodenski approximation.
     * When invoking {@code createOperation(…)} without operation method,
     * this factory may select by default the transformation specified by the authority.
     * When invoking {@code createOperation(…)} with an operation method,
     * user can force usage of Molodenski approximation for instance.
     *
     * @param  sourceCRS  input coordinate reference system.
     * @param  targetCRS  output coordinate reference system.
     * @param  method     the algorithmic method for conversion or transformation.
     * @return a coordinate operation from {@code sourceCRS} to {@code targetCRS}.
     * @throws OperationNotFoundException if no operation path was found from {@code sourceCRS}
     *         to {@code targetCRS}.
     * @throws FactoryException if the operation creation failed for some other reason.
     *
     * @departure extension
     *   This method has been added at user request, in order to specify the desired
     *   transformation path when many are available.
     *
     * @deprecated Replaced by {@link RegisterOperations#findCoordinateOperations(CoordinateReferenceSystem,
     * CoordinateReferenceSystem) RegisterOperations}. The latter is defined by the ISO 19111:2019 standard.
     * Another reason is that this method requires the use of a geodetic registry.
     */
    @Deprecated(since = "3.1")
    default CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS,
                                                CoordinateReferenceSystem targetCRS,
                                                OperationMethod           method) throws FactoryException
    {
        throw new UnimplementedServiceException("Cannot infer coordinate operations between a pair of CRSs.");
    }

    /**
     * Creates a concatenated operation from a sequence of operations.
     * The source coordinate reference system of the first step and the target coordinate reference system of the
     * last step are the source and target coordinate reference system associated with the concatenated operation.
     *
     * @deprecated Replaced by {@linkplain #createConcatenatedOperation(Map, CoordinateReferenceSystem,
     * CoordinateReferenceSystem, CoordinateOperation...) a method with explicit CRS arguments} because
     * of potential swapping of source/target <abbr>CRS</abbr>.
     *
     * @param  properties  name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  operations  the sequence of operations.
     * @return the concatenated operation.
     * @throws FactoryException if the object creation failed.
     *
     * @departure extension
     *   This method has been added because OGC 01-009 does not define a factory
     *   method for creating concatenated operations.
     */
    @Deprecated(since = "3.1")
    default CoordinateOperation createConcatenatedOperation(Map<String, ?> properties,
            CoordinateOperation... operations) throws FactoryException
    {
        return createConcatenatedOperation(properties, null, null, operations);
    }

    /**
     * Creates a defining conversion from a set of properties. Defining conversions have no
     * {@linkplain Conversion#getSourceCRS() source} and {@linkplain Conversion#getTargetCRS() target CRS},
     * and do not need to have a {@linkplain Conversion#getMathTransform() math transform}.
     * Their sole purpose is to be given as an argument to {@linkplain CRSFactory#createDerivedCRS
     * derived CRS} and {@linkplain CRSFactory#createProjectedCRS projected CRS} constructors.
     *
     * <p>Some available properties are {@linkplain ObjectFactory listed there}.</p>
     *
     * @param  properties  set of properties. Shall contain at least {@code "name"}.
     * @param  method      the operation method.
     * @param  parameters  the parameter values. A default set of parameters can be obtained by
     *         {@code method.getParameters().createValue()} and modified before to be given to this constructor.
     * @return the defining conversion.
     * @throws FactoryException if the object creation failed.
     *
     * @see CRSFactory#createProjectedCRS(Map, GeographicCRS, Conversion, CartesianCS)
     * @see CRSFactory#createDerivedCRS(Map, CoordinateReferenceSystem, Conversion, CoordinateSystem)
     *
     * @departure extension
     *   The <i>defining conversion</i> concept appears in ISO 19111 specification without formalization in UML diagrams.
     *   A constructor for this concept is added in GeoAPI for allowing the creation of {@code ProjectedCRS} instances.
     */
    default Conversion createDefiningConversion(Map<String,?>       properties,
                                                OperationMethod     method,
                                                ParameterValueGroup parameters) throws FactoryException
    {
        throw new UnimplementedServiceException(this, Conversion.class);
    }

    /**
     * Creates an operation method from a set of properties and a descriptor group.
     * This factory method allows the creation of arbitrary {@code OperationMethod} instances.
     * Some available properties are {@linkplain ObjectFactory listed there}.
     * Additionally, the following properties should be accepted by this constructor:
     *
     * <table class="ogc">
     *   <caption>Keys for additional standard properties</caption>
     *   <tr>
     *     <th>Property name</th>
     *     <th>Value type</th>
     *     <th>Returned by</th>
     *   </tr><tr>
     *     <td>{@value org.opengis.referencing.operation.OperationMethod#FORMULA_KEY}</td>
     *     <td>{@link Formula}</td>
     *     <td>{@link OperationMethod#getFormula()}</td>
     *   </tr>
     * </table>
     *
     * Note that implementations may have a build-in list of predefined operation methods.
     * For obtaining a build-in instance, see {@link MathTransformFactory} instead.
     *
     * @param  properties  set of properties. Shall contain at least {@code "name"}.
     * @param  parameters  a description of the parameters for the operation method.
     * @return the operation method.
     * @throws FactoryException if the object creation failed.
     *
     * @departure extension
     *   This method has been added because OGC 01-009 does not define a factory
     *   method for creating operation methods.
     *
     * @since 3.1
     */
    default OperationMethod createOperationMethod(Map<String,?> properties,
                ParameterDescriptorGroup parameters) throws FactoryException
    {
        throw new UnimplementedServiceException(this, OperationMethod.class);
    }

    /**
     * Creates an ordered sequence of two or more single coordinate operations.
     * The sequence of operations is constrained by the requirement that the source coordinate reference system
     * of step (<var>n</var>+1) must be the same as the target coordinate reference system of step (<var>n</var>).
     * The source coordinate reference system of the first step and the target coordinate reference system of the
     * last step are the source and target coordinate reference system associated with the concatenated operation.
     *
     * <p>As an exception to the above-cited constraint, a step can swap its source and target <abbr>CRS</abbr>.
     * In such case, the effectively executed operation will be the inverse of that step. The {@code sourceCRS}
     * and {@code targetCRS} arguments of this method are needed for detecting whether such swapping occurred
     * in the first step or in the last step. Those optional arguments can be {@code null} if the caller did
     * not swapped any <abbr>CRS</abbr>.</p>
     *
     * @param  properties  name and other properties to give to the new object.
     *                     Available properties are {@linkplain ObjectFactory listed there}.
     * @param  sourceCRS   the source <abbr>CRS</abbr>, or {@code null} for the source of the first step.
     * @param  targetCRS   the target <abbr>CRS</abbr>, or {@code null} for the target of the last effective step.
     * @param  operations  the sequence of operations. Should contain at least two operations.
     * @return the concatenated operation created from the given arguments.
     * @throws FactoryException if the object creation failed.
     *
     * @since 3.1
     */
    default CoordinateOperation createConcatenatedOperation(
            Map<String,?> properties,
            CoordinateReferenceSystem sourceCRS,
            CoordinateReferenceSystem targetCRS,
            CoordinateOperation... operations) throws FactoryException
    {
        throw new UnimplementedServiceException(this, ConcatenatedOperation.class);
    }
}
