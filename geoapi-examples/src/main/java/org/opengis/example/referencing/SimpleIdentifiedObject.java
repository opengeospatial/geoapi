/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Objects;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.example.metadata.SimpleIdentifier;


/**
 * An {@code IdentifiedObject} abstract base class, which contain only the {@linkplain #getName() name} attribute.
 * All other {@code IdentifiedObject} attributes are {@code null}, empty optional or empty collections.
 */
public class SimpleIdentifiedObject implements IdentifiedObject {
    /**
     * The name of this identified object.
     */
    protected final Identifier name;

    /**
     * Creates a new object of the given authority and name.
     *
     * @param name  the name of the new object.
     */
    public SimpleIdentifiedObject(final Identifier name) {
        this.name = Objects.requireNonNull(name);
    }

    /**
     * Creates a new object of the given authority and name.
     *
     * @param authority  the value to be returned by {@link #getAuthority()}, or {@code null} if none.
     * @param name       the name of the new object.
     */
    public SimpleIdentifiedObject(final Citation authority, final String name) {
        this.name = new SimpleIdentifier(authority, name);
    }

    /**
     * Returns the name of this identified object.
     */
    @Override
    public Identifier getName() {
        return name;
    }

    /**
     * {@return a short label for this identified object}.
     * This is used for formatting error messages.
     */
    protected String label() {
        return name.getCode();
    }

    /**
     * Returns a <i>Well-Known Text</i> (WKT) for this object. The default implementation
     * throws unconditionally the exception since we do not support WKT formatting.
     *
     * @return the Well Know Text for this object.
     * @throws UnsupportedOperationException if this object cannot be formatted as WKT.
     */
    @Override
    public String toWKT() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a hash code value calculated from the {@linkplain #getName() name} identifier.
     * This hash code calculation is sufficient if each object name is unique.
     */
    @Override
    public int hashCode() {
        return name.hashCode() ^ 3;
    }

    /**
     * Compares this identifier with the given object for equality.
     *
     * @param  object  the object to compare with this {@code SimpleIdentifiedObject}.
     * @return {@code true} if the given object is equal to this object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object != null && object.getClass() == getClass()) {
            return name.equals(((SimpleIdentifiedObject) object).name);
        }
        return false;
    }

    /**
     * Returns a string representation of this identified object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + name + ']';
    }
}
