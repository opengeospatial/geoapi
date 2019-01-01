/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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

import java.io.ObjectStreamException;
import java.io.Serializable;


/**
 * Indicating "filter all", evaluates to {@code false}.
 * This is a placeholder filter intended to be used in data structuring definition.
 *
 * <ul>
 *   <li>EXCLUDE or Filter ==&gt; Filter</li>
 *   <li>EXCLUDE and Filter ==&gt; EXCLUDE</li>
 *   <li>EXCLUDE ==&gt; INCLUDE</li>
 * </ul>
 *
 * The above does imply that the AND opperator can short circuit on encountering ALL.
 *
 * @author Jody Garnett (Refractions Research, Inc.)
 * @author Martin Desruisseaux (Geomatys)
 *
 * @deprecated To be removed from public API.
 */
@Deprecated
public final class ExcludeFilter implements Filter, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -716705962006999508L;

    /**
     * Not extensible.
     */
    ExcludeFilter() {
    }

    /**
     * Accepts a visitor.
     */
    @Override
    public Object accept(FilterVisitor visitor, Object extraData) {
        return visitor.visit(this, extraData);
    }

    /**
     * Returns {@code false}, content is excluded.
     */
    @Override
    public boolean evaluate(Object object) {
        return false;
    }

    /**
     * Returns a string representation of this filter.
     */
    @Override
    public String toString() {
        return "Filter.EXCLUDE";
    }

    /**
     * Returns the canonical instance on deserialization.
     */
    private Object readResolve() throws ObjectStreamException {
        return Filter.EXCLUDE;
    }
}
