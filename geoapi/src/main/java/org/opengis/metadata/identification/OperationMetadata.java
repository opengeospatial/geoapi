/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import java.util.List;
import java.util.Collection;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.metadata.citation.OnlineResource;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Describes the signature of one and only one method provided by the service.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SV_OperationMetadata", specification=ISO_19115)
public interface OperationMetadata {
    /**
     * An unique identifier for this interface.
     *
     * @return an unique identifier for this interface.
     */
    @UML(identifier="operationName", obligation=MANDATORY, specification=ISO_19115)
    String getOperationName();

    /**
     * Distributed computing platforms (DCPs) on which the operation has been implemented.
     *
     * @return distributed computing platforms on which the operation has been implemented.
     */
    @UML(identifier="distributedComputingPlatform", obligation=MANDATORY, specification=ISO_19115)
    Collection<DistributedComputingPlatform> getDistributedComputingPlatforms();

    /**
     * Free text description of the intent of the operation and the results of the operation.
     *
     * @return free text description of the intent of the operation and the results of the operation,
     *         or {@code null} if none.
     */
    @UML(identifier="operationDescription", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getOperationDescription() {
        return null;
    }

    /**
     * The name used to invoke this interface within the context of the DCP.
     * The name is identical for all Distributed computing platforms (DCPs).
     *
     * @return the name used to invoke this interface within the context of the DCP, or {@code null} if none.
     */
    @UML(identifier="invocationName", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getInvocationName() {
        return null;
    }

    /**
     * Handle for accessing the service interface.
     *
     * @return handle for accessing the service interface.
     */
    @UML(identifier="connectPoint", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends OnlineResource> getConnectPoints();

    /**
     * The parameters that are required for this interface.
     * Returns an empty collection if none.
     *
     * <h4>Unified parameter API</h4>
     * In GeoAPI, the {@code SV_Parameter} type defined by ISO 19115 is replaced by {@link ParameterDescriptor}
     * in order to provide a single parameter API (see {@link org.opengis.parameter} for more information).
     * The mapping from ISO 19115 to GeoAPI is defined in the following table.
     * The equivalences are straightforward except for the {@code name} property, which is mapped to
     * an {@link org.opengis.metadata.Identifier} instead of {@link org.opengis.util.MemberName}
     * ({@linkplain ParameterDescriptor#getName() more information on the mapping of names}).
     *
     * <table class="ogc">
     *   <caption>Service metadata properties mapped to GeoAPI</caption>
     *   <tr><th>{@code SV_Parameter} property</th> <th>{@code ParameterDescriptor} property</th></tr>
     *   <tr><td>{@code name}</td>                  <td>{@link ParameterDescriptor#getName()          name}</td></tr>
     *   <tr><td>{@code name.attributeType}</td>    <td>{@link ParameterDescriptor#getValueClass()    valueClass}</td></tr>
     *   <tr><td>{@code direction}</td>             <td>{@link ParameterDescriptor#getDirection()     direction}</td></tr>
     *   <tr><td>{@code description}</td>           <td>{@link ParameterDescriptor#getDescription()   description}</td></tr>
     *   <tr><td>{@code optionality}</td>           <td>{@link ParameterDescriptor#getMinimumOccurs() minimumOccurs} &gt; 0</td></tr>
     *   <tr><td>{@code repeatability}</td>         <td>{@link ParameterDescriptor#getMaximumOccurs() maximumOccurs} &gt; 1</td></tr>
     * </table>
     *
     * @departure harmonization
     *   Usage of the ISO 19115 {@code SV_Parameter} type has been replaced by usage of the ISO 19111
     *   {@code CC_OperationParameter} type, completed with new {@code SV_Parameter} properties,
     *   in order to provide a unified parameter API. Note that {@code CC_OperationParameter} is named
     *   {@code ParameterDescriptor} in GeoAPI to reflect its extended scope.
     *
     * @return the parameters that are required for this interface, or an empty collection if none.
     *
     * @see org.opengis.parameter.GeneralParameterDescriptor
     */
    @UML(identifier="parameter", obligation=OPTIONAL, specification=ISO_19115)     // Was "parameters" in ISO 19115:2003
    default Collection<? extends ParameterDescriptor<?>> getParameters() {
        return Collections.emptyList();
    }

    /**
     * List of operation that must be completed immediately before current operation is invoked.
     * The return value is structured as a list for capturing alternate predecessor paths
     * and sets for capturing parallel predecessor paths.
     *
     * @return list of operation that must be completed immediately, or an empty list if none.
     */
    @UML(identifier="dependsOn", obligation=OPTIONAL, specification=ISO_19115)
    default List<? extends OperationMetadata> getDependsOn() {
        return Collections.emptyList();
    }
}
