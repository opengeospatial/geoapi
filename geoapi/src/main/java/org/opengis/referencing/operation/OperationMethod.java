/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.referencing.IdentifiedObject;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Algorithm or procedure used to perform a coordinate operation.
 * Most operation methods use a number of operation {@linkplain org.opengis.parameter.ParameterDescriptor parameters},
 * although some coordinate conversions use none.
 * Each {@linkplain CoordinateOperation coordinate operation} using the method assigns
 * {@linkplain org.opengis.parameter.ParameterValue values} to these parameters.
 *
 * <p>As this class comes close to the heart of any coordinate transformation software,
 * it is recommended to make extensive use of {@linkplain #getIdentifiers() identifiers},
 * referencing well-known datasets wherever possible. The {@linkplain #getName() name} may be ambiguous
 * because there is yet no standard way of spelling or naming the various coordinate operation methods.
 * Client software requesting a coordinate operation to be executed by a coordinate transformation implementation may
 * therefore ask for an operation method this server doesn't recognise, although a perfectly valid method may be available.
 * Some recommended EPSG identifiers are reproduced below
 * (see {@linkplain org.opengis.annotation.Specification#ISO_19162 ISO 19162}
 * or the <a href="https://epsg.org/">EPSG repository</a> for a more complete list):</p>
 *
 * <table class="ogc">
 *   <caption>EPSG identifier for some operation method names</caption>
 *   <tr><th>Name</th>                                <th>Alias</th>                       <th>Identifier</th></tr>
 *   <tr><td>Albers Equal Area</td>                   <td>Albers</td>                      <td>9822</td></tr>
 *   <tr><td>Hotine Oblique Mercator (variant A)</td> <td>Rectified skew orthomorphic</td> <td>9812</td></tr>
 *   <tr><td>Hotine Oblique Mercator (variant B)</td> <td>Rectified skew orthomorphic</td> <td>9815</td></tr>
 *   <tr><td>Lambert Azimuthal Equal Area</td>        <td>Lambert Equal Area</td>          <td>9820</td></tr>
 *   <tr><td>Lambert Conic Conformal (1SP)</td>       <td>Lambert Conic Conformal</td>     <td>9801</td></tr>
 *   <tr><td>Lambert Conic Conformal (2SP)</td>       <td>Lambert Conic Conformal</td>     <td>9802</td></tr>
 *   <tr><td>Mercator (variant A)</td>                <td>Mercator</td>                    <td>9804</td></tr>
 *   <tr><td>Mercator (variant B)</td>                <td>Mercator</td>                    <td>9805</td></tr>
 *   <tr><td>Oblique stereographic</td>               <td>Double stereographic</td>        <td>9809</td></tr>
 *   <tr><td>Transverse Mercator</td>                 <td>Gauss-Boaga / Gauss-Krüger</td>  <td>9807</td></tr>
 * </table>
 *
 * <h2>Example</h2>
 * An operation method named <q>Mercator (variant A)</q> (EPSG:9804) declares the following parameters.
 * Note that implementations can optionally assign
 * {@linkplain org.opengis.parameter.ParameterDescriptor#getDefaultValue() default values} to those parameters.
 * <ul>
 *   <li><q>Latitude of natural origin</q> in degrees.</li>
 *   <li><q>Longitude of natural origin</q> in degrees.</li>
 *   <li><q>Scale factor at natural origin</q> as a dimensionless number.</li>
 *   <li><q>False easting</q> in metres.</li>
 *   <li><q>False northing</q> in metres.</li>
 * </ul>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CoordinateOperation
 * @see MathTransformFactory#getAvailableMethods(Class)
 * @see CoordinateOperationFactory#getOperationMethod(String)
 * @see CoordinateOperationAuthorityFactory#createOperationMethod(String)
 * @see CoordinateOperationFactory#createOperationMethod(Map, Integer, Integer, ParameterDescriptorGroup)
 */
@UML(identifier="OperationMethod", specification=ISO_19111)
public interface OperationMethod extends IdentifiedObject {
    /*
     * NOTE FOR JAVADOC WRITER:
     * The "method" word is ambiguous here, because it can be "Java method" or "coordinate operation method".
     * In this interface, we reserve "method" for coordinate operation methods as much as possible.
     */

    /**
     * Key for the <code>{@value}</code> property.
     * This is used for setting the value to be returned by {@link #getFormula()}.
     *
     * @see #getFormula()
     */
    String FORMULA_KEY = "formula";

    /**
     * Formula(s) or procedure used by this operation method. This may be a reference to a
     * publication. Note that the operation method may not be analytic, in which case this
     * attribute references or contains the procedure, not an analytic formula.
     *
     * @return the formula used by this method.
     */
    @UML(identifier="formulaReference", obligation=MANDATORY, specification=ISO_19111)
    Formula getFormula();

    /**
     * Returns the set of parameters.
     * If the operation has no parameter, then this method shall return an empty group.
     *
     * @return the parameters, or an empty group (never {@code null}) if none.
     *
     * @departure easeOfUse
     *   The sequence if {@code GeneralParameter} is replaced by a {@code ParameterGroup}
     *   because it provides method for fetching parameters by their names.
     *
     * @see SingleOperation#getParameterValues()
     */
    @UML(identifier="parameter", obligation=OPTIONAL, specification=ISO_19111)
    ParameterDescriptorGroup getParameters();
}
