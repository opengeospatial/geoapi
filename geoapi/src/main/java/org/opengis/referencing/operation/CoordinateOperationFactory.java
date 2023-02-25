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

import java.util.Map;
import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.cs.CartesianCS;  // For javadoc
import org.opengis.referencing.cs.CoordinateSystem;  // For javadoc
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.util.FactoryException;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Creates {@linkplain CoordinateOperation coordinate operations}.
 * This factory is capable to find coordinate {@linkplain Transformation transformations}
 * or {@linkplain Conversion conversions} between two
 * {@linkplain CoordinateReferenceSystem coordinate reference systems}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="CT_CoordinateTransformationFactory", specification=OGC_01009)
public interface CoordinateOperationFactory extends ObjectFactory {
    /**
     * Returns an operation for conversion or transformation between two coordinate reference systems.
     * <ul>
     *   <li>If an operation exists, it is returned.</li>
     *   <li>If more than one operation exists, the default is returned.</li>
     *   <li>If no operation exists, then the exception is thrown.</li>
     * </ul>
     * <p>
     * Implementations may try to
     * {@linkplain CoordinateOperationAuthorityFactory#createFromCoordinateReferenceSystemCodes
     * query an authority factory} first, and compute the operation next if no operation from
     * {@code source} to {@code target} code was explicitly defined by the authority.
     *
     * @param  sourceCRS Input coordinate reference system.
     * @param  targetCRS Output coordinate reference system.
     * @return A coordinate operation from {@code sourceCRS} to {@code targetCRS}.
     * @throws OperationNotFoundException if no operation path was found from {@code sourceCRS}
     *         to {@code targetCRS}.
     * @throws FactoryException if the operation creation failed for some other reason.
     */
    @UML(identifier="createFromCoordinateSystems", specification=OGC_01009)
    CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS,
                                        CoordinateReferenceSystem targetCRS)
            throws OperationNotFoundException, FactoryException;

    /**
     * Returns an operation using a particular method for conversion or transformation
     * between two coordinate reference systems.
     * <ul>
     *   <li>If the operation exists on the implementation, then it is returned.</li>
     *   <li>If the operation does not exist on the implementation, then the implementation
     *       has the option of inferring the operation from the argument objects.</li>
     *   <li>If for whatever reason the specified operation will not be returned, then
     *       the exception is thrown.</li>
     * </ul>
     * <p>
     * <b>Example:</b> A transformation between two {@linkplain GeographicCRS geographic CRS} using
     * different {@linkplain GeodeticDatum datum}
     * requires a <cite>datum shift</cite>. Many methods exist for this purpose, including interpolations
     * in a grid, a scale/rotation/translation in geocentric coordinates or the Molodenski approximation.
     * When invoking {@code createOperation} without operation method, this factory may select by
     * default the most accurate transformation (typically interpolation in a grid). When invoking
     * {@code createOperation} with an operation method, user can force usage of Molodenski
     * approximation for instance.
     *
     * @param  sourceCRS Input coordinate reference system.
     * @param  targetCRS Output coordinate reference system.
     * @param  method The algorithmic method for conversion or transformation.
     * @return A coordinate operation from {@code sourceCRS} to {@code targetCRS}.
     * @throws OperationNotFoundException if no operation path was found from {@code sourceCRS}
     *         to {@code targetCRS}.
     * @throws FactoryException if the operation creation failed for some other reason.
     *
     * @departure extension
     *   This method has been added at user request, in order to specify the desired
     *   transformation path when many are available.
     */
    CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS,
                                        CoordinateReferenceSystem targetCRS,
                                        OperationMethod           method)
            throws OperationNotFoundException, FactoryException;

    /**
     * Creates a concatenated operation from a sequence of operations.
     *
     * @param  properties Name and other properties to give to the new object.
     *         Available properties are {@linkplain ObjectFactory listed there}.
     * @param  operations The sequence of operations.
     * @return The concatenated operation.
     * @throws FactoryException if the object creation failed.
     *
     * @departure extension
     *   This method has been added because OGC 01-009 does not define a factory
     *   method for creating such object.
     */
    CoordinateOperation createConcatenatedOperation(Map<String, ?> properties,
                                                    CoordinateOperation... operations)
            throws FactoryException;

    /**
     * Constructs a defining conversion from a set of properties. Defining conversions have no
     * {@linkplain Conversion#getSourceCRS source} and {@linkplain Conversion#getTargetCRS target
     * CRS}, and do not need to have a {@linkplain Conversion#getMathTransform math transform}.
     * Their sole purpose is to be given as an argument to
     * {@linkplain org.opengis.referencing.crs.CRSFactory#createDerivedCRS derived CRS} and
     * {@linkplain org.opengis.referencing.crs.CRSFactory#createProjectedCRS projected CRS} constructors.
     * <p>
     * Some available properties are {@linkplain ObjectFactory listed there}.
     * Additionally, the following properties are understood by this constructor:
     * <p>
     * <table border='1'>
     *   <tr bgcolor="#CCCCFF" class="TableHeadingColor">
     *     <th nowrap>Property name</th>
     *     <th nowrap>Value type</th>
     *     <th nowrap>Value given to</th>
     *   </tr>
     *   <tr>
     *     <td nowrap>&nbsp;{@value org.opengis.referencing.operation.CoordinateOperation#OPERATION_VERSION_KEY}&nbsp;</td>
     *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
     *     <td nowrap>&nbsp;{@link CoordinateOperation#getOperationVersion()}</td>
     *   </tr>
     *   <tr>
     *     <td nowrap>&nbsp;{@value org.opengis.referencing.operation.CoordinateOperation#COORDINATE_OPERATION_ACCURACY_KEY}&nbsp;</td>
     *     <td nowrap>&nbsp;<code>{@linkplain org.opengis.metadata.quality.PositionalAccuracy}[]</code>&nbsp;</td>
     *     <td nowrap>&nbsp;{@link CoordinateOperation#getCoordinateOperationAccuracy()}</td>
     *   </tr>
     *   <tr>
     *     <td nowrap>&nbsp;{@value org.opengis.referencing.operation.CoordinateOperation#DOMAIN_OF_VALIDITY_KEY}&nbsp;</td>
     *     <td nowrap>&nbsp;{@link org.opengis.metadata.extent.Extent}&nbsp;</td>
     *     <td nowrap>&nbsp;{@link CoordinateOperation#getDomainOfValidity()}</td>
     *   </tr>
     *   <tr>
     *     <td nowrap>&nbsp;{@value org.opengis.referencing.operation.CoordinateOperation#SCOPE_KEY}&nbsp;</td>
     *     <td nowrap>&nbsp;{@link String} or {@link org.opengis.util.InternationalString}&nbsp;</td>
     *     <td nowrap>&nbsp;{@link CoordinateOperation#getScope()}</td>
     *   </tr>
     * </table>
     *
     * @param  properties Set of properties. Should contains at least {@code "name"}.
     * @param  method The operation method.
     * @param  parameters The parameter values.
     * @return The defining conversion.
     * @throws FactoryException if the object creation failed.
     *
     * @see org.opengis.referencing.crs.CRSFactory#createProjectedCRS(Map, GeographicCRS, Conversion, CartesianCS)
     * @see org.opengis.referencing.crs.CRSFactory#createDerivedCRS(Map, CoordinateReferenceSystem, Conversion, CoordinateSystem)
     *
     * @since 2.1
     */
    Conversion createDefiningConversion(Map<String,?>       properties,
                                        OperationMethod     method,
                                        ParameterValueGroup parameters)
            throws FactoryException;
}
