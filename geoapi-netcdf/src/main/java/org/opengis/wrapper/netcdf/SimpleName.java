/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.List;
import java.util.Objects;
import java.util.Collections;
import java.io.Serializable;

import org.opengis.util.LocalName;
import org.opengis.util.NameSpace;
import org.opengis.util.ScopedName;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;


/**
 * A simple {@link LocaleName} implementation.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleName implements LocalName, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 4287146905141121478L;

    /**
     * The scope (name space) in which this name is local. The scope is set on creation
     * and is not modifiable. The scope of a name determines where a name starts.
     *
     * <p>This field shall not be null, except if this instance is the name of a global namespace.</p>
     */
    private final NameSpace scope;

    /**
     * The value to be returned by {@link #toString()} or {@link #toInternationalString()}.
     */
    final String name;

    /**
     * Creates a new instance for the given name.
     *
     * @param scope  the scope (name space) in which the name is local, or {@code null}.
     * @param name   the value to be returned by {@link #toString()}.
     */
    public SimpleName(final NameSpace scope, final String name) {
        this.scope = scope;
        this.name  = name.trim();
    }

    /**
     * Returns the scope (name space) in which this name is local. The scope is set on creation
     * and is not modifiable. The scope of a name determines where a name starts.
     *
     * @return the scope of this name.
     */
    @Override
    public NameSpace scope() {
        if (scope == null) {
            throw new UnsupportedOperationException("Global namespace can not have scope.");
        }
        return scope;
    }

    /**
     * Returns the number of levels specified by this name, which is always 1 for a local name.
     */
    @Override
    public final int depth() {
        return 1;
    }

    /**
     * Returns the sequence of local name. Since this object is itself a locale name,
     * this method always returns a {@linkplain Collections#singletonList(Object) singleton}
     * list containing only {@code this}.
     */
    @Override
    public final List<LocalName> getParsedNames() {
        return Collections.<LocalName>singletonList(this);
    }

    /**
     * Returns {@code this} since this object is already a local name.
     */
    @Override
    public final LocalName head() {
        return this;
    }

    /**
     * Returns {@code this} since this object is already a local name.
     */
    @Override
    public final LocalName tip() {
        return this;
    }

    /**
     * Returns a view of this name as a fully-qualified name. The {@linkplain #scope() scope}
     * of a fully qualified name will be {@linkplain NameSpace#isGlobal() global}. If the scope
     * of this name is already global, then this method returns {@code this}.
     *
     * @return the fully-qualified name (never {@code null}).
     */
    @Override
    public GenericName toFullyQualifiedName() {
        if (scope == null || scope.isGlobal()) {
            return this;
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Returns this name expanded with the specified scope. One may represent this operation
     * as a concatenation of the specified {@code scope} with {@code this}.
     *
     * @param  scope  the name to use as prefix.
     * @return a concatenation of the given name with this name.
     */
    @Override
    public ScopedName push(final GenericName scope) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a string representation of this generic name.
     * Note that the {@linkplain #scope() scope} is not part of this string representation.
     *
     * @return a string representation of this name.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns a local-dependent string representation of this generic name.
     *
     * @return a localizable string representation of this name.
     */
    @Override
    public InternationalString toInternationalString() {
        return new SimpleCitation(name);
    }

    /**
     * Compares this name with the given object for lexicographical order.
     * This method compares the {@link #toString()} value of each object.
     * Note that the {@linkplain #scope() scope} is not part of this comparison.
     *
     * @param  other  the other object to compare to this name.
     */
    @Override
    public int compareTo(final GenericName other) {
        return toString().compareTo(other.toString());
    }

    /**
     * Compares the given object to this name for equality. This method compares
     * both the {@linkplain #scope() scope} and the name given to the constructor.
     *
     * @param  other The other object to compare to this name.
     */
    @Override
    public boolean equals(final Object other) {
        if (other instanceof SimpleName) {
            final SimpleName that = (SimpleName) other;
            return name.equals(that.name) && Objects.equals(scope, that.scope);
        }
        return false;
    }

    /**
     * Returns a hash code value for this name.
     */
    @Override
    public int hashCode() {
        int code = name.hashCode() ^ (int) serialVersionUID;
        if (scope != null) {
            code += 31*scope.hashCode();
        }
        return code;
    }
}
