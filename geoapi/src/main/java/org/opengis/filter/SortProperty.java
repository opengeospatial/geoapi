/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.util.Comparator;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Defines the sort order of a property.
 *
 * @author  Jody Garnett (Refractions Research)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to sort.
 *
 * @see SortBy#getSortProperties()
 *
 * @since 3.1
 */
@UML(identifier="SortProperty", specification=ISO_19143)
public interface SortProperty<R> extends Comparator<R> {
    /**
     * The property to sort by.
     *
     * @return the property to sort by.
     */
    @UML(identifier="valueReference", obligation=MANDATORY, specification=ISO_19143)
    ValueReference<R,?> getValueReference();

    /**
     * The sorting order, ascending or descending.
     * The default value is ascending order.
     *
     * @return the sorting order.
     */
    @UML(identifier="sortOrder", obligation=OPTIONAL, specification=ISO_19143)
    default SortOrder getSortOrder() {
        return SortOrder.ASCENDING;
    }

    /**
     * Compares two resources for order. Returns a negative number if {@code r1} should be sorted before {@code r2},
     * a positive number if {@code r2} should be after {@code r1}, or 0 if both resources are equal.
     * The ordering of null resources or null property values is unspecified.
     *
     * <p>The comparison shall be consistent (ignoring unspecified aspects such as null values) with a comparison
     * done "manually" by fetching the values identified by the {@linkplain #getValueReference() value reference}
     * from the two resources and comparing them according the {@linkplain #getSortOrder() sort order}.
     * In order words, it shall be possible for the users to build their own SQL (or other language) query
     * using above information and get the same results without invoking this {@code compare(…)} method.</p>
     *
     * @param  r1  the first resource to compare.
     * @param  r2  the second resource to compare.
     * @return negative if the first resource is before the second, positive for the converse, or 0 if equal.
     * @throws InvalidFilterValueException if the expression cannot be applied on the given resources.
     * @throws ClassCastException if the types of {@linkplain ValueReference#apply(Object) property values}
     *         prevent them from being compared by this comparator.
     *
     * @see SortBy#compare(Object, Object)
     */
    @Override
    int compare(R r1, R r2);
}
