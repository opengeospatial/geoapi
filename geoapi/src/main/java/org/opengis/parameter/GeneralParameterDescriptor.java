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
package org.opengis.parameter;

import java.util.Optional;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.Specification;
import org.opengis.util.MemberName;                     // For javadoc
import org.opengis.util.InternationalString;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.IdentifiedObject;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract definition of a parameter or group of parameters used by an operation method.
 * This interface combines information provided by Referencing by Coordinates (ISO 19111),
 * Service Metadata (ISO 19115), Data Quality (ISO 19157), filter encoding (ISO 19143)
 * and Web Processing Services (WPS) standards.
 * The main information are:
 *
 * <table class="ogc">
 *   <caption>Main parameter properties and their mapping to other standards</caption>
 *   <tr>
 *     <th>Getter method</th>
 *     <th class="sep">ISO 19111</th>
 *     <th class="sep">ISO 19115</th>
 *     <th class="sep">ISO 19157</th>
 *     <th class="sep">ISO 19143</th>
 *     <th class="sep">WPS</th>
 *     <th class="sep">Remarks</th>
 *   </tr><tr>
 *     <td>{@link #getName()}</td>
 *     <td class="sep">{@code name}</td>
 *     <td class="sep">{@code name}</td>
 *     <td class="sep">{@code name}</td>
 *     <td class="sep">{@code name}</td>
 *     <td class="sep">{@code Identifier}</td>
 *     <td class="sep">See {@linkplain #getName() method javadoc} for {@code MemberName} ↔ {@code Identifier} mapping.</td>
 *   </tr><tr>
 *     <td>{@link #getAlias() getAlias()}</td>
 *     <td class="sep">{@code alias}</td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *   </tr><tr>
 *     <td>{@link #getIdentifiers() getIdentifiers()}</td>
 *     <td class="sep">{@code identifier}</td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep">Optional, contrarily to name which is mandatory.</td>
 *   </tr><tr>
 *     <td><code>{@linkplain #getName()}.{@linkplain Identifier#getDescription() getDescription()}</code></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep">{@code definition}</td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep">Should be a short sentence.</td>
 *   </tr>
 *   <!-- "Title" (WPS) equivalent to "designation" (Feature), but not yet provided. -->
 *   <tr>
 *     <td>{@link #getDescription()}</td>
 *     <td class="sep"></td>
 *     <td class="sep">{@code description}</td>
 *     <td class="sep">{@code description}</td>
 *     <td class="sep"></td>
 *     <td class="sep">{@code Abstract}</td>
 *     <td class="sep">Explanation more detailed than the definition.</td>
 *   </tr><tr>
 *     <td>{@link #getRemarks() getRemarks()}</td>
 *     <td class="sep">{@code remarks}</td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *   </tr><tr>
 *     <td>{@link ParameterDescriptor#getValueType() getValueType()}</td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep">{@code valueType}</td>
 *     <td class="sep">{@code type}</td>
 *     <td class="sep"></td>
 *     <td class="sep">Name that describes the type of parameter values.</td>
 *   </tr><tr>
 *     <td>{@link #getDirection()}</td>
 *     <td class="sep"></td>
 *     <td class="sep">{@code direction}</td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep">Tells if the parameter is a WPS {@code Input} or {@code Output} structure.</td>
 *   </tr><tr>
 *     <td>{@link #getMinimumOccurs()}</td>
 *     <td class="sep">{@code minimumOccurs}</td>
 *     <td class="sep">{@code optionality}</td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep">{@code MinOccurs}</td>
 *     <td class="sep">{@code optionality   = (minimumOccurs > 0)}</td>
 *   </tr><tr>
 *     <td>{@link #getMaximumOccurs()}</td>
 *     <td class="sep">{@code maximumOccurs}</td>
 *     <td class="sep">{@code repeatability}</td>
 *     <td class="sep"></td>
 *     <td class="sep"></td>
 *     <td class="sep">{@code MaxOccurs}</td>
 *     <td class="sep">{@code repeatability = (maximumOccurs > 1)}</td>
 *   </tr>
 * </table>
 *
 * @departure rename
 *   GeoAPI uses a name which contains the "{@code Descriptor}" word for consistency with other
 *   libraries in Java (e.g. {@code ParameterListDescriptor} in Java Advanced Imaging).
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Jody Garnett (Refractions Research)
 * @version 3.1
 * @since   2.0
 *
 * @see GeneralParameterValue
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="GeneralOperationParameter", specification=ISO_19111)
public interface GeneralParameterDescriptor extends IdentifiedObject {
    /**
     * Returns the name, as used by the service or operation for this parameter.
     *
     * <h4>Unified parameter API</h4>
     * The metadata standard ({@linkplain Specification#ISO_19115 ISO 19115}) defines the
     * {@code name} property as of type {@link MemberName} instead of {@code Identifier},
     * while the data quality standard ({@linkplain Specification#ISO_19157 ISO 19157})
     * defines equivalent properties directly in its parameter class.
     * The following table provides the suggested mapping from ISO models to parameter descriptor.
     *
     * <table class="ogc">
     *   <caption>Mapping from ISO abstract models to unified parameter API</caption>
     *   <tr>
     *     <th>Name used by ISO 19115</th>
     *     <th>Property in ISO 19157</th>
     *     <th>Property in unified parameter API</th>
     *   </tr><tr>
     *     <td><code>{@linkplain MemberName#scope() MemberName.scope()}.name().toString()</code></td>
     *     <td></td>
     *     <td>{@link Identifier#getCodeSpace()}</td>
     *   </tr><tr>
     *     <td>{@link MemberName#toString()}</td>
     *     <td>{@code DQM_Parameter.name}</td>
     *     <td>{@link Identifier#getCode()}</td>
     *   </tr><tr>
     *     <td>{@link MemberName#getAttributeType()}</td>
     *     <td>{@code DQM_Parameter.valueType}</td>
     *     <td>{@link ParameterDescriptor#getValueType()}</td>
     *   </tr><tr>
     *     <td></td>
     *     <td>{@code DQM_Parameter.definition}</td>
     *     <td>{@link Identifier#getDescription()}</td>
     *   </tr>
     * </table>
     *
     * @return the name, as used by the service or operation for this parameter.
     */
    @Override
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19111)
    Identifier getName();

    /**
     * Returns whether the parameter is an input to the service, an output or both.
     * This information applies mostly to <i>service metadata</i>.
     * The default value is {@link ParameterDirection#IN}.
     *
     * @return indication if the parameter is an input to the service, an output or both.
     *
     * @since 3.1
     */
    @UML(identifier="SV_Parameter.direction", obligation=MANDATORY, specification=ISO_19115)
    default ParameterDirection getDirection() {
        return ParameterDirection.IN;
    }

    /**
     * Returns a narrative explanation of the role of this parameter.
     *
     * @return a narrative explanation of the role of this parameter.
     *
     * @see #getName()
     * @see #getRemarks()
     *
     * @since 3.1
     */
    @UML(identifier="SV_Parameter.description", obligation=OPTIONAL, specification=ISO_19115)
    default Optional<InternationalString> getDescription() {
        return Optional.empty();
    }

    /**
     * Returns the minimum number of times that values for this parameter group or parameter are required.
     * The default value is 1. A value of 0 means an optional parameter.
     *
     * @return the minimum occurrence.
     *
     * @see #getMaximumOccurs()
     */
    @UML(identifier="minimumOccurs", obligation=OPTIONAL, specification=ISO_19111)
    default int getMinimumOccurs() {
        return 1;
    }

    /**
     * Returns the maximum number of times that values for this parameter group or parameter can be included.
     * The default value is 1. A value greater than 1 means a repeatable parameter.
     *
     * <p>If this parameter is an instance of {@link ParameterDescriptor} used for the description of
     * {@link org.opengis.referencing.operation.OperationMethod} parameters, then the value is always 1.
     * In other contexts (e.g. parameter group or service metadata) it may vary.
     *
     * @departure generalization
     *   Moved up (in the interface hierarchy) the {@code maximumOccurs} method from
     *   {@code ParameterDescriptorGroup} into this super-interface, for parallelism
     *   with the {@code minimumOccurs} method.
     *
     * @return the maximum occurrence, or {@link Integer#MAX_VALUE} if there is no limit.
     *
     * @see #getMinimumOccurs()
     */
    @UML(identifier="CC_OperationParameterGroup.maximumOccurs", obligation=OPTIONAL, specification=ISO_19111)
    default int getMaximumOccurs() {
        return 1;
    }

    /**
     * Creates a new instance of {@linkplain GeneralParameterValue parameter value or group}
     * initialized with the {@linkplain ParameterDescriptor#getDefaultValue default value(s)}.
     * The {@linkplain GeneralParameterValue#getDescriptor parameter value descriptor} for
     * the created parameter value(s) will be {@code this} object.
     *
     * @return a new parameter initialized to its default value.
     *
     * @departure extension
     *   This method is not part of the ISO specification. It is provided in GeoAPI as a kind of
     *   factory method.
     */
    GeneralParameterValue createValue();
}
