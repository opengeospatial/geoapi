/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.metadata;

import java.util.Objects;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;


/**
 * An identifier containing only a citation and an authority code.
 */
public class SimpleIdentifier implements Identifier {
    /**
     * The organization or party responsible for definition and maintenance of the {@linkplain #code}.
     * The {@linkplain Citation#getTitle() citation title} will be used as {@linkplain #getCodeSpace() code space}.
     *
     * @see #getAuthority()
     * @see #getCodeSpace()
     */
    protected final Citation authority;

    /**
     * Alphanumeric value identifying an instance in the authority name space.
     *
     * @see #getCode()
     */
    protected final String code;

    /**
     * Creates a new identifier of the given authority and name.
     *
     * @param authority  the value to be returned by {@link #getAuthority()}, or {@code null} if none.
     * @param code       the value to be returned by {@link #getCode()}.
     */
    public SimpleIdentifier(final Citation authority, final String code) {
        this.authority = authority;
        this.code = Objects.requireNonNull(code);
    }

    /**
     * Returns the person or party responsible for maintenance of the namespace.
     * This method returns the citation given to the constructor.
     *
     * @return party responsible for definition and maintenance of the code, or {@code null} if none.
     */
    @Override
    public Citation getAuthority() {
        return authority;
    }

    /**
     * Returns the identifier or namespace in which the code is valid.
     * The default implementation returns the {@linkplain Citation#getTitle() title}
     * of the {@linkplain #getAuthority() authority}.
     *
     * @return the identifier or namespace in which the code is valid, or {@code null} if none.
     */
    @Override
    public String getCodeSpace() {
        return (authority != null) ? authority.getTitle().toString() : null;
    }

    /**
     * Returns the name given at construction time.
     *
     * @return alphanumeric value identifying an instance in the namespace.
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * Returns a hash code value calculated from the authority and the code.
     */
    @Override
    public int hashCode() {
        int hash = code.hashCode() ^ -86660764;
        if (authority != null) {
            hash += authority.hashCode() * 31;
        }
        return hash;
    }

    /**
     * Compares this identifier with the given object for equality.
     *
     * @param  object  the object to compare with this identifier.
     * @return {@code true} if the given object is equal to this object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object != null && object.getClass() == getClass()) {
            final var other = (SimpleIdentifier) object;
            return code.equals(other.code) && Objects.equals(authority, other.authority);
        }
        return false;
    }

    /**
     * Returns a string representation of this identifier.
     * The default implementation build the string representation as below:
     *
     * <ul>
     *   <li>If this identifier has a {@linkplain #getCodeSpace() code space}, then returns
     *       the concatenation of the code space, the {@code ':'} character, then the
     *       {@linkplain #code}.</li>
     *   <li>Otherwise returns the {@linkplain #code} directly.</li>
     * </ul>
     */
    @Override
    public String toString() {
        final String codespace = getCodeSpace();
        return (codespace != null) ? codespace + ':' + code : code;
    }
}
