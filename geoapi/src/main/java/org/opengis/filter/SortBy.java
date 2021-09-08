/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
    List<? extends SortProperty<R>> getSortProperties();

    /**
     * Compares two resources for order. Returns a negative number if {@code r1} should be sorted before {@code r2},
     * a positive number if {@code r2} should be after {@code r1}, or 0 if both resources are equal.
     * The ordering of null resources or null property values is unspecified.
     *
     * <p>The comparison shall be consistent (ignoring unspecified aspects such as null values)
     * with a comparison done "manually" by the following code:</p>
     *
     * <pre>
     * for (SortProperty&lt;R&gt; p : {@linkplain #getSortProperties()}) {
     *     int c = p.compare(r1, r2);
     *     if (c != 0) return c;
     * }
     * return 0;</pre>
     *
     * <p>In order words, it shall be possible for the users to build their own SQL (or other language) query
     * using above information and get the same results without invoking this {@code compare(…)} method.</p>
     *
     * @param  r1  the first resource to compare.
     * @param  r2  the second resource to compare.
     * @return negative if the first resource is before the second, positive for the converse, or 0 if equal.
     * @throws InvalidFilterValueException if an expression can not be applied on the given resources.
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
