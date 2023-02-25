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

import java.util.Set;
import java.util.Collections;
import org.opengis.filter.ComparisonOperatorName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Advertisement of which logical, comparison and arithmetic operators the service supports.
 * Scalar capabilities include the ability to process logical expressions and comparisons.
 *
 * @author  Torsten Friebe (lat/lon)
 * @author  Markus Schneider (lat/lon)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getScalarCapabilities()
 *
 * @since 3.1
 */
@UML(identifier="ScalarCapabilities", specification=ISO_19143)
public interface ScalarCapabilities {
    /**
     * Indicates if logical operator support is provided.
     * A value of {@code true} indicates that the filter can process
     * <cite>And</cite>, <cite>Or</cite> and <cite>Not</cite> operators.
     *
     * @return whether logical operators are supported.
     */
    @UML(identifier="logicalOperators", specification=ISO_19143)
    default boolean hasLogicalOperators() {
        return false;
    }

    /**
     * Advertises which comparison operators are supported by the service.
     *
     * @return comparison operators supported by the service.
     */
    @UML(identifier="comparisonOperator", obligation=OPTIONAL, specification=ISO_19143)
    default Set<ComparisonOperatorName> getComparisonOperators() {
        return Collections.emptySet();
    }
}
