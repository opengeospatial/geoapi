/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
import java.util.List;
import java.util.Collection;
import org.opengis.util.ScopedName;
import org.opengis.filter.TemporalOperatorName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Advertisement of which temporal operators and temporal operands the service supports.
 * Temporal capabilities include the ability to filter temporal data of specified temporal types
 * based on the temporal operators After, Before, Begins, BegunBy, TContains, During, TEquals,
 * TOverlaps, Meets, Ends, OverlappedBy, MetBy, EndedBy and AnyInteracts.
 *
 * @author  Johann Sorel (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getTemporalCapabilities()
 *
 * @since 3.1
 */
@UML(identifier="TemporalCapabilities", specification=ISO_19143)
public interface TemporalCapabilities {
    /**
     * Advertises which temporal operands are supported by the service.
     * Temporal operands listed here are defined globally, indicating that
     * all temporal operators know how to process the specified operands.
     *
     * @return temporal operands supported globally by the service.
     */
    @UML(identifier="temporalOperand", obligation=MANDATORY, specification=ISO_19143)
    Collection<? extends ScopedName> getTemporalOperands();

    /**
     * Advertises which temporal operators are supported by the service.
     * Keys are temporal operator names and values are temporal operands defined
     * {@linkplain #getTemporalOperands() globally} or locally for each temporal operator,
     * indicating that the specific operator knows how to process the specified operands.
     *
     * @return temporal operators supported by the service.
     *
     * @departure easeOfUse
     *   GeoAPI replaces the {@code TemporalOperatorDescription} type by {@code Map.Entry}.
     *   It reduces the number of interfaces and makes easy to query the operands for a specific operator.
     */
    @UML(identifier="temporalOperator", obligation=MANDATORY, specification=ISO_19143)
    Map<TemporalOperatorName, List<? extends ScopedName>> getTemporalOperators();
}
