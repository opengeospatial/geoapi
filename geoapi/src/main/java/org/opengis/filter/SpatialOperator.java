/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Operator that determines whether its geometric arguments satisfy the stated spatial relationship.
 * The operator {@linkplain #test evaluates} to {@code true} if the spatial relationship is satisfied.
 * Otherwise, the operator evaluates to {@code false}.
 * The nature of the comparison is dependent on the {@linkplain #getOperatorType() operator type}.
 *
 * <p>This code list does not include the relationships involving a distance parameter.
 * See {@link DistanceOperatorName} for "within" and "beyond a distance" operations.</p>
 *
 * <h2>Null operand</h2>
 * For all spatial operators in a filter expression, except {@link #DISJOINT} and {@link #BEYOND},
 * testing a pair of geometric values where one of the values evaluates to {@code null},
 * shall result in the expression evaluating to {@code false} indicating that the two geometries are disjoint.
 * In the case of the {@link #DISJOINT} and {@link #BEYOND} operators, which test for disjointness,
 * the expression shall evaluate to {@code true}.
 *
 * @author  Chris Dillard (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to filter.
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SpatialOperator", specification=ISO_19143)
public interface SpatialOperator<R> extends Filter<R> {
    /**
     * Returns the nature of the operator. The code list can be {@link SpatialOperatorName}
     * or {@link DistanceOperatorName}, depending on the sub-interface.
     *
     * @return the nature of the operator.
     */
    @Override
    CodeList<?> getOperatorType();
}
