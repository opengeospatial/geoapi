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
package org.opengis.filter;

import java.util.List;
import java.util.Comparator;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Properties whose values shall be used to order the set of resources that satisfy a filter expression.
 * {@code SortBy} provides an entry point to invoke the sorting mechanism of the filter expression processor.
 * The sorting mechanism is not specified. The only requirement is that the sort sequence shall be consistent,
 * given the same data set and sort request, between consecutive invocations of the sort.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @param  <R>  the type of resources (e.g. {@link org.opengis.feature.Feature}) to sort.
 *
 * @since 3.1
 */
@UML(identifier="SortBy", specification=ISO_19143)
public interface SortBy<R> extends Comparator<R> {
    /**
     * The properties whose values are used for sorting. The list shall have a minimum of one element.
     * In the event that multiple elements exist, the sequence of the {@code SortProperty} elements
     * determines the order of ordering.
     *
     * @return properties whose values are used for sorting.
     */
    @UML(identifier="sortProperty", obligation=MANDATORY, specification=ISO_19143)
    List<SortProperty<R>> getSortProperties();

    /**
     * Compares two resources for order. Returns a negative number if {@code r1} should be sorted before {@code r2},
     * a positive number if {@code r2} should be after {@code r1}, or 0 if both resources are equal.
     * The ordering of null resources or null property values is unspecified.
     *
     * <p>The comparison shall be consistent (ignoring unspecified aspects such as null values)
     * with a comparison done "manually" by the following code:</p>
     *
     * {@snippet lang="java" :
     * for (SortProperty<R> p : getSortProperties()) {
     *     int c = p.compare(r1, r2);
     *     if (c != 0) return c;
     * }
     * return 0;
     * }
     *
     * <p>In order words, it shall be possible for the users to build their own SQL (or other language) query
     * using above information and get the same results without invoking this {@code compare(…)} method.</p>
     *
     * @param  r1  the first resource to compare.
     * @param  r2  the second resource to compare.
     * @return negative if the first resource is before the second, positive for the converse, or 0 if equal.
     * @throws InvalidFilterValueException if an expression cannot be applied on the given resources.
     * @throws ClassCastException if the types of {@linkplain ValueReference#apply(Object) property values}
     *         prevent them from being compared by this comparator.
     *
     * @see SortProperty#compare(Object, Object)
     */
    @Override
    default int compare(final R r1, final R r2) {
        for (final SortProperty<R> p : getSortProperties()) {
            final int c = p.compare(r1, r2);
            if (c != 0) return c;
        }
        return 0;
    }
}
