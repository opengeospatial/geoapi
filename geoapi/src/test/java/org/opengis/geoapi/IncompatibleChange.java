/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.*;


/**
 * An incompatible change which may be accepted for the new release.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class IncompatibleChange {
    /**
     * The fully qualified class name and method where the incompatible changes happen.
     */
    final String method;

    /**
     * The old return type.
     */
    private final String oldType;

    /**
     * The new return type.
     */
    private final String newType;

    /**
     * Creates a new incompatible change declaration.
     */
    IncompatibleChange(final String method, final String oldType, final String newType) {
        this.method  = method;
        this.oldType = oldType;
        this.newType = newType;
    }

    /**
     * Returns the accepted incompatible changes between GeoAPI 3.0.1 and GeoAPI 3.1.
     */
    static Set<IncompatibleChange> for31() {
        return fill("org.opengis.metadata.content.Band.getUnits",
                    "javax.measure.Unit<javax.measure.quantity.Length>",
                    "javax.measure.Unit<?>");
    }

    /**
     * Returns the accepted incompatible changes between GeoAPI 3.1 and GeoAPI 4.0.
     */
    static Set<IncompatibleChange> for40() {
        return fill();
    }

    /**
     * Creates a set of incompatible changes from (method, oldType, newType) tuples.
     */
    private static Set<IncompatibleChange> fill(final String... types) {
        final Set<IncompatibleChange> changes = new HashSet<>();
        for (int i=0; i<types.length;) {
            final IncompatibleChange c = new IncompatibleChange(types[i++], types[i++], types[i++]);
            assertTrue(c.method, changes.add(c));
        }
        return changes;
    }

    /**
     * Returns a hash code value for this change.
     */
    @Override
    public int hashCode() {
        return method.hashCode() + 31 * (oldType.hashCode() + 31 * newType.hashCode());
    }

    /**
     * Compares the given object with this instance for equality.
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof IncompatibleChange) {
            final IncompatibleChange other = (IncompatibleChange) obj;
            return method.equals(other.method) && oldType.equals(other.oldType) && newType.equals(other.newType);
        }
        return false;
    }

    /**
     * Returns a string representation of this incompatible change.
     */
    @Override
    public String toString() {
        return toString(new StringBuilder(256), System.getProperty("line.separator", "\n")).toString();
    }

    /**
     * Implementation of {@link #toString()}.
     */
    final StringBuilder toString(final StringBuilder buffer, final String lineSeparator) {
        return buffer.append("Incompatible change in the return type of ").append(method).append(':').append(lineSeparator)
                     .append("    (old) ").append(oldType).append(lineSeparator)
                     .append("    (new) ").append(newType).append(lineSeparator);
    }
}
