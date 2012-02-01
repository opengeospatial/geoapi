/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.example.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.io.Serializable;
import javax.naming.Name;
import javax.naming.InvalidNameException;

import org.opengis.util.LocalName;
import org.opengis.util.NameSpace;
import org.opengis.util.ScopedName;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.example.metadata.SimpleCitation;


/**
 * A {@link GenericName} backed by a JNDI {@link Name} instance. This name can be the basis
 * of {@link LocalName} or {@link ScopedName} implementations, depending on whatever the
 * {@linkplain Name#size() size} of the wrapped JNDI name is 1 or more, respectively.
 * <p>
 * This implementation is a <cite>view</cite>: any change applied on the wrapped JNDI object
 * will be immediately reflected in the {@code SimpleName} wrapper. Note however that
 * {@code GenericName} are usually expected to be immutable, so users are advised to not
 * modify the wrapped JNDI name.
 *
 * @author Martin Desruisseaux
 */
public class SimpleName implements GenericName, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 5767162955069918493L;

    /**
     * The {@link LocalName} specialization. The JNDI name wrapped by this class
     * shall contain exactly one component.
     *
     * @author Martin Desruisseaux
     */
    public static class Local extends SimpleName implements LocalName {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = 7289656986139657450L;

        /**
         * Creates a new instance backed by the given JNDI name. This constructor does not clone the
         * given JNDI name. While this implementation is robust to change in the wrapped object, it is
         * a better practice to keep the JNDI name unmodified after {@code SimpleName} construction.
         *
         * @param  scope The scope (name space) in which the given name is local.
         * @param  name  The JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
         * @throws IllegalArgumentException If the given name does not have exactly 1 component.
         */
        public Local(final SimpleNameSpace scope, final Name name) throws IllegalArgumentException {
            super(scope, name);
            if (!isValid()) {
                throw new IllegalArgumentException("Local name shall have exactly 1 component.");
            }
        }

        /**
         * Returns {@code true} if this local name is valid. This method should never returns
         * {@code false} after construction unless the user has modified the {@link #name}.
         * In such case, we will fail back on the super-class implementation as a safety.
         */
        private boolean isValid() {
            return depth() == 1;
        }

        /**
         * Returns the sequence of local name. Since this object is itself a locale name,
         * this method returns a {@linkplain Collections#singletonList(Object) singleton}
         * list containing only {@code this}.
         */
        @Override
        public List<LocalName> getParsedNames() {
            return isValid() ? Collections.<LocalName>singletonList(this) : super.getParsedNames();
        }

        /**
         * Returns {@code this} since this object is already a local name.
         */
        @Override
        public LocalName head() {
            return isValid() ? this : super.head();
        }

        /**
         * Returns {@code this} since this object is already a local name.
         */
        @Override
        public LocalName tip() {
            return isValid() ? this : super.tip();
        }
    }

    /**
     * The {@link ScopedName} specialization. The JNDI name wrapped by this class
     * shall contain more than one component.
     *
     * @author Martin Desruisseaux
     */
    public static class Scoped extends SimpleName implements ScopedName {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = -8174256917494442466L;

        /**
         * Creates a new instance backed by the given JNDI name. This constructor does not clone the
         * given JNDI name. While this implementation is robust to change in the wrapped object, it is
         * a better practice to keep the JNDI name unmodified after {@code SimpleName} construction.
         *
         * @param  scope The scope (name space) in which the given name is local.
         * @param  name  The JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
         * @throws IllegalArgumentException If the given name has less than 2 components.
         */
        public Scoped(final SimpleNameSpace scope, final Name name) throws IllegalArgumentException {
            super(scope, name);
            if (name.size() < 2) {
                throw new IllegalArgumentException("Scoped name shall have 2 or more components.");
            }
        }

        /**
         * Creates a new instance as the concatenation of the two given names.
         *
         * @param  path The first part of the new name.
         * @param  tail The second part of the new name.
         * @throws InvalidNameException If the addition of the components would violate the syntax
         *         rules of the JNDI name.
         */
        public Scoped(final SimpleName path, final SimpleName tail) throws InvalidNameException {
            super(path, tail);
            if (name.size() < 2) {
                throw new IllegalArgumentException("Scoped name shall have 2 or more components.");
            }
        }

        /**
         * Returns every elements of the {@linkplain #getParsedNames() parsed names list} except for
         * the {@linkplain #head() head}.
         *
         * @see Name#getSuffix(int)
         */
        @Override
        public GenericName tail() {
            return create(scope, name.getSuffix(1));
        }

        /**
         * Returns every elements of the {@linkplain #getParsedNames() parsed names list} except for
         * the {@linkplain #tip() tip}.
         */
        @Override
        public GenericName path() {
            return create(scope, name.getPrefix(name.size() - 1));
        }
    }

    /**
     * The scope (name space) in which this name is local. The scope is set on creation
     * and is not modifiable. The scope of a name determines where a name starts.
     * <p>
     * This field shall not be null, except if this instance is the name of a global namespace.
     */
    final SimpleNameSpace scope;

    /**
     * The JNDI name wrapped by this {@code SimpleName}. While JNDI names are mutable,
     * {@code GenericName} are expected to be immutable. Consequently users are advised
     * to not modify this object.
     */
    protected final Name name;

    /**
     * Creates a new instance backed by the given JNDI name. This constructor does not clone the
     * given JNDI name. While this implementation is robust to change in the wrapped object, it is
     * a better practice to keep the JNDI name unmodified after {@code SimpleName} construction.
     *
     * @param scope The scope (name space) in which the given name is local.
     * @param name  The JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
     */
    protected SimpleName(final SimpleNameSpace scope, final Name name) {
        Objects.requireNonNull(scope, "The scope is mandatory.");
        Objects.requireNonNull(name,  "A JNDI name must be provided.");
        this.scope = scope;
        this.name  = name;
    }

    /**
     * Creates a new instance as the concatenation of the two given names.
     *
     * @param  path The first part of the new name.
     * @param  tail The second part of the new name.
     * @throws InvalidNameException If the addition of the components would violate the syntax
     *         rules of the JNDI name.
     */
    SimpleName(final SimpleName path, final SimpleName tail) throws InvalidNameException {
        scope = path.scope;
        final Name prefix = (Name) path.name.clone();
        name = prefix.addAll(prefix.size(), tail.name);
    }

    /**
     * Creates a new generic name for the given JNDI name. This method returns an instance of
     * {@link LocalName} or {@link ScopedName}, depending on the number of parsed components.
     *
     * @param  name The JNDI name.
     * @return The generic name.
     */
    static GenericName create(final SimpleNameSpace scope, final Name name) {
        switch (name.size()) {
            case 0:  return new SimpleName       (scope, name);
            case 1:  return new SimpleName.Local (scope, name);
            default: return new SimpleName.Scoped(scope, name);
        }
    }

    /**
     * Returns the scope (name space) in which this name is local. The scope of a name
     * determines where a name starts. The scope is set on creation and is not modifiable.
     */
    @Override
    public NameSpace scope() {
        if (scope == null) {
            throw new UnsupportedOperationException("Global namespace can not have scope.");
        }
        return scope;
    }

    /**
     * Returns the number of levels specified by this name. The default implementation
     * returns the {@linkplain Name#size() size} of the wrapped JNDI name.
     *
     * @see Name#size()
     */
    @Override
    public int depth() {
        return name.size();
    }

    /**
     * Returns the sequence of {@linkplain LocalName local names} making this generic name.
     * The length of this sequence is the {@linkplain #depth() depth}. It does not include
     * the {@linkplain #scope() scope}.
     *
     * @see Name#getAll()
     */
    @Override
    public List<LocalName> getParsedNames() {
        SimpleNameSpace scope = this.scope;
        final List<LocalName> names = new ArrayList<LocalName>(name.size());
        final Enumeration<String> it = name.getAll();
        if (it.hasMoreElements()) while (true) {
            final String n = it.nextElement();
            names.add(scope.createLocalName(scope, n));
            if (!it.hasMoreElements()) {
                break;
            }
//TODO      scope = new SimpleNameSpace(scope, n);
        }
        return names;
    }

    /**
     * Returns the first element in the sequence of {@linkplain #getParsedNames() parsed names}.
     *
     * @see Name#get(int)
     */
    @Override
    public LocalName head() {
        return scope.createLocalName(scope, name.get(0));
    }

    /**
     * Returns the last element in the sequence of {@linkplain #getParsedNames() parsed names}.
     *
     * @see Name#get(int)
     */
    @Override
    public LocalName tip() {
        return scope.createLocalName(scope, name.get(name.size() - 1));
    }

    /**
     * Returns a view of this name as a fully-qualified name. The {@linkplain #scope() scope}
     * of a fully qualified name will be {@linkplain NameSpace#isGlobal() global}. If the scope
     * of this name is already global, then this method returns {@code this}.
     *
     * @return The fully-qualified name (never {@code null}).
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
     * @param scope The name to use as prefix.
     * @return A concatenation of the given name with this name.
     */
    @Override
    public ScopedName push(final GenericName scope) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a string representation of this generic name.
     * Note that the {@linkplain #scope() scope} is not part of this string representation.
     *
     * @return A string representation of this name.
     */
    @Override
    public String toString() {
        return name.toString();
    }

    /**
     * Returns a local-dependent string representation of this generic name.
     * The default implementation wraps the {@link #toString()} value.
     *
     * @return A localizable string representation of this name.
     */
    @Override
    public InternationalString toInternationalString() {
        return new SimpleCitation(toString());
    }

    /**
     * Compares this name with the given object for lexicographical order.
     * Note that the {@linkplain #scope() scope} is not part of this comparison.
     *
     * @param other The other object to compare to this name.
     * @throws ClassCastException If the given object is not an instance of {@link SimpleName}.
     */
    @Override
    public int compareTo(final GenericName other) throws ClassCastException {
        return name.compareTo(((SimpleName) other).name);
    }

    /**
     * Compares the given object to this name for equality. This method compares
     * both the {@linkplain #scope() scope} and the {@linkplain #name} given to
     * the constructor.
     *
     * @param  other The other object to compare to this name.
     */
    @Override
    public boolean equals(final Object other) {
        if (other != null && getClass().equals(other.getClass())) {
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
