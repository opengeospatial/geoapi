/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import java.util.Optional;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.util.LocalName;
import org.opengis.util.ScopedName;
import org.opengis.filter.Expression;
import org.opengis.filter.FilterFactory;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Metadata about the specific elements that a particular implementation supports.
 * A client application can inspect the filter capabilities metadata and be able
 * to determine which operators and types a filter expression processor supports.
 *
 * @author  Torsten Friebe (lat/lon)
 * @author  Markus Schneider (lat/lon)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="FilterCapabilities", specification=ISO_19143)
public interface FilterCapabilities {
    /**
     * Declaration of which conformance classes a particular implementation supports.
     *
     * @return declaration of which conformance classes a particular implementation supports.
     */
    @UML(identifier="conformance", obligation=MANDATORY, specification=ISO_19143)
    Conformance getConformance();

    /**
     * Provides a list of element names that represent the resource identifier elements that the service supports.
     *
     * @return capabilities used to convey supported identifier operators.
     */
    @UML(identifier="idCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<IdCapabilities> getIdCapabilities() {
        return Optional.empty();
    }

    /**
     * Advertises which logical, comparison and arithmetic operators the service supports.
     * If empty, then a client shall assume that the service does not support any logical
     * or comparison operators and does not implement any additional functions.
     *
     * @return logical, comparison and arithmetic operators supported.
     */
    @UML(identifier="scalarCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<ScalarCapabilities> getScalarCapabilities() {
        return Optional.empty();
    }

    /**
     * Advertises which spatial operators and geometric operands the service supports.
     * If empty, a client shall assume that the service does not support any spatial operators.
     *
     * @return capabilities used to convey supported spatial operators.
     */
    @UML(identifier="spatialCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<SpatialCapabilities> getSpatialCapabilities() {
        return Optional.empty();
    }

    /**
     * Advertises which temporal operators and temporal operands the service supports.
     * If empty, a client shall assume that the service does not support any temporal operators.
     *
     * @return capabilities used to convey supported temporal operators.
     */
    @UML(identifier="temporalCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<TemporalCapabilities> getTemporalCapabilities() {
        return Optional.empty();
    }

    /**
     * Enumerates the functions that may be used in filter expressions.
     * Keys are function names that can be used in calls to {@link FilterFactory#function(String, Expression[])}.
     * Associated values are descriptions of the functions.
     *
     * <h4>Invariants</h4>
     * For each entry, the key should be equal to <code>{@linkplain AvailableFunction#getName()}.toString()</code>,
     * optionally with the {@link LocalName} replaced by a {@link ScopedName} for string representation purposes.
     *
     * @return the functions that may be used in filter expressions.
     *
     * @see FilterFactory#function(String, Expression[])
     */
    @UML(identifier="functions", obligation=OPTIONAL, specification=ISO_19143)
    default Map<String, ? extends AvailableFunction> getFunctions() {
        return Collections.emptyMap();
    }

    /**
     * Advertises any additional operators added to the filter syntax.
     *
     * @return additional operators added to the filter syntax.
     */
    @UML(identifier="extendedCapabilities", obligation=OPTIONAL, specification=ISO_19143)
    default Optional<ExtendedCapabilities> getExtendedCapabilities() {
        return Optional.empty();
    }
}
