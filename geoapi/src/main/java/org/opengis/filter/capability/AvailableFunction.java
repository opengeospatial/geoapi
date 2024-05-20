/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2021-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.capability;

import java.util.List;
import java.util.Collections;
import org.opengis.util.LocalName;
import org.opengis.util.TypeName;
import org.opengis.annotation.UML;
import org.opengis.parameter.ParameterDescriptor;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * A function that may be used in filter expressions.
 * The description of each function includes a declaration of the return type,
 * the list of arguments with optional names and expected types.
 *
 * @author  Torsten Friebe (lat/lon)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getFunctions()
 *
 * @since 3.1
 */
@UML(identifier="AvailableFunctions", specification=ISO_19143)
public interface AvailableFunction {
    /**
     * Returns the function name.
     *
     * @return the function name.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19143)
    LocalName getName();

    /**
     * Returns the type of return value.
     *
     * @return the type of return value.
     *
     * @departure integration
     *   OGC/ISO defines the return type as {@link org.opengis.util.ScopedName}.
     *   However this is at odd with other classes returning type information,
     *   which use {@code TypeName}.
     */
    @UML(identifier="returns", obligation=MANDATORY, specification=ISO_19143)
    TypeName getReturnType();

    /**
     * Returns the list of arguments expected by the function.
     *
     * <h4>Unified parameter API</h4>
     * In GeoAPI, the {@code Argument} type defined by ISO 19143 is replaced by {@link ParameterDescriptor}
     * in order to provide a single parameter API (see {@link org.opengis.parameter} for more information).
     * The mapping from ISO 19143 to GeoAPI is defined in the following table.
     * The equivalences are straightforward except for the types:
     * {@code name} is mapped to an {@link org.opengis.metadata.Identifier} instead of {@link LocalName},
     * and {@code type} is mapped to an {@link TypeName} instead of {@link org.opengis.util.ScopedName}.
     *
     * <table class="ogc">
     *   <caption>Argument properties mapped to GeoAPI</caption>
     *   <tr><th>{@code Argument} property</th> <th>{@code ParameterDescriptor} property</th></tr>
     *   <tr><td>{@code name}</td> <td>{@link ParameterDescriptor#getName() name}</td></tr>
     *   <tr><td>{@code type}</td> <td>{@link ParameterDescriptor#getValueType() valueType}</td></tr>
     * </table>
     *
     * @departure harmonization
     *   Usage of the ISO 19143 {@code Argument} type has been replaced by usage of the ISO 19111
     *   {@code OperationParameter} type in order to provide a unified parameter API.
     *
     * @return arguments that the function accepts.
     */
    @UML(identifier="arguments", obligation=OPTIONAL, specification=ISO_19143)
    default List<? extends ParameterDescriptor<?>> getArguments() {
        return Collections.emptyList();
    }
}
