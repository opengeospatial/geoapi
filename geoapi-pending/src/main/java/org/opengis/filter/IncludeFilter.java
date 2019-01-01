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
 * Indicating "no filtering", evaluates to {@code true}.
 * This is a placeholder filter intended to be used in data structuring definition.
 *
 * <ul>
 *   <li>INCLUDE or  Filter ==&gt; INCLUDE</li>
 *   <li>INCLUDE and Filter ==&gt; Filter</li>
 *   <li>not INCLUDE ==&gt; EXCLUDE</li>
 * </ul>
 *
 * The above does imply that the OR operator can short circuit on encountering NONE.
 *
 * @author Jody Garnett (Refractions Research, Inc.)
 * @author Martin Desruisseaux (Geomatys)
 *
 * @deprecated To be removed from public API.
 */
@Deprecated
public final class IncludeFilter implements Filter, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -8429407144421087160L;

    /**
     * Not extensible.
     */
    IncludeFilter() {
    }

    /**
     * Accepts a visitor.
     */
    @Override
    public Object accept(FilterVisitor visitor, Object extraData) {
        return visitor.visit( this, extraData );
    }

    /**
     * Returns {@code true}, content is included.
     */
    @Override
    public boolean evaluate(Object object) {
        return true;
    }

    /**
     * Returns a string representation of this filter.
     */
    @Override
    public String toString() {
        return "Filter.INCLUDE";
    }

    /**
     * Returns the canonical instance on deserialization.
     */
    private Object readResolve() throws ObjectStreamException {
        return Filter.INCLUDE;
    }
}
