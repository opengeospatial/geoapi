/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.sort;

import java.io.ObjectStreamException;
import java.io.Serializable;
import org.opengis.filter.expression.PropertyName;


/**
 * Default implementation of {@link SortBy} as a "null object". Used for {@link SortBy} constants.
 *
 * @author Jody Garnett (Refractions Research)
 * @author Martin Desruisseaux (Geomatys)
 */
final class NullSortBy implements SortBy, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -4846119001746135007L;

    /**
     * The sort order.
     */
    private final SortOrder order;

    /**
     * Creates a new Null object for the specified sort order.
     */
    NullSortBy(final SortOrder order) {
        this.order = order;
    }

    /**
     * Natural order usually associated with FID, or Key Attribtues.
     */
    public PropertyName getPropertyName() {
        return null;
    }

    /**
     * Returns the sort order.
     */
    public SortOrder getSortOrder() {
        return order;
    }

    /**
     * Returns a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return (int) serialVersionUID ^ order.hashCode();
    }

    /**
     * Compares this object with the specified one for equality.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof NullSortBy) {
            return order.equals(((NullSortBy) object).order);
        }
        return false;
    }

    /**
     * Returns a single instance after deserialization.
     */
    private Object readResolve() throws ObjectStreamException {
        if (order.equals(SortOrder.ASCENDING))  return SortBy.NATURAL_ORDER;
        if (order.equals(SortOrder.DESCENDING)) return SortBy.REVERSE_ORDER;
        return this;
    }
}
