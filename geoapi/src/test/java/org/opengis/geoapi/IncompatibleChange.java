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
package org.opengis.geoapi;

import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


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
     *
     * @param  method   the fully qualified class name and method where the incompatible changes happen.
     * @param  oldType  the old return type.
     * @param  newType  the new return type.
     */
    IncompatibleChange(final String method, final String oldType, final String newType) {
        this.method  = method;
        this.oldType = oldType;
        this.newType = newType;
    }

    /**
     * {@return the accepted incompatible changes between GeoAPI 3.0.2 and GeoAPI 3.1}.
     */
    static Set<IncompatibleChange> for31() {
        return fill("org.opengis.metadata.content.Band.getUnits",
                    "javax.measure.Unit<javax.measure.quantity.Length>",
                    "javax.measure.Unit<?>");
    }

    /**
     * {@return the accepted incompatible changes between GeoAPI 3.1 and GeoAPI 4.0}.
     */
    static Set<IncompatibleChange> for40() {
        return fill();
    }

    /**
     * Creates a set of incompatible changes from (method, oldType, newType) tuples.
     *
     * @param  types  (method, oldType, newType) tuples.
     * @return set of incompatible changes.
     */
    private static Set<IncompatibleChange> fill(final String... types) {
        final Set<IncompatibleChange> changes = new HashSet<>();
        for (int i=0; i<types.length;) {
            final IncompatibleChange c = new IncompatibleChange(types[i++], types[i++], types[i++]);
            assertTrue(changes.add(c), c.method);
        }
        return changes;
    }

    /**
     * {@return a hash code value for this change}.
     */
    @Override
    public int hashCode() {
        return method.hashCode() + 31 * (oldType.hashCode() + 31 * newType.hashCode());
    }

    /**
     * Compares the given object with this instance for equality.
     *
     * @param  obj  the object to compare.
     * @return whether the two objects are equal.
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
     * {@return a string representation of this incompatible change}.
     */
    @Override
    public String toString() {
        return toString(new StringBuilder(256), System.getProperty("line.separator", "\n")).toString();
    }

    /**
     * Implementation of {@link #toString()}.
     *
     * @param  buffer         where to format the string.
     * @param  lineSeparator  value of {@link System#lineSeparator()}.
     * @return the given buffer for method call chaining.
     */
    final StringBuilder toString(final StringBuilder buffer, final String lineSeparator) {
        return buffer.append("Incompatible change in the return type of ").append(method).append(':').append(lineSeparator)
                     .append("    (old) ").append(oldType).append(lineSeparator)
                     .append("    (new) ").append(newType).append(lineSeparator);
    }
}
