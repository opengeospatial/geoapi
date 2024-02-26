/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.util;

import java.util.Objects;
import javax.naming.Name;
import javax.naming.CompoundName;
import javax.naming.InvalidNameException;

import org.opengis.util.NameSpace;
import org.opengis.util.GenericName;


/**
 * A {@link NameSpace} defining the scope of {@link SimpleName} instances.
 */
public class SimpleNameSpace implements NameSpace {
    /**
     * The root namespace.
     */
    static final SimpleNameSpace ROOT = new SimpleNameSpace();

    /**
     * The factory to use for creating name instances in this namespace.
     */
    protected final SimpleNameFactory factory;

    /**
     * The identifier of this namespace.
     */
    final SimpleName name;

    /**
     * Creates the {@link #ROOT} namespace with an empty name.
     */
    private SimpleNameSpace() {
        factory = SimpleNameFactory.DEFAULT;
        try {
            name = new SimpleName.Local(new CompoundName("", factory.syntax));
        } catch (InvalidNameException e) {
            throw new AssertionError(e);                // Should never happen.
        }
    }

    /**
     * Creates a new instance for name and using the given factory.
     * This constructor is package-private in order to prevent improper values.
     *
     * @param  factory  the factory to use for creating name instances in this namespace.
     * @param  name     the identifier of this namespace.
     */
    SimpleNameSpace(final SimpleNameFactory factory, final SimpleName name) {
        this.factory = factory;
        this.name    = name;
    }

    /**
     * Creates a new instance for the given parent namespace and name. The new instance will share
     * the {@linkplain #factory} instance from its parent, unless {@code parent} is {@code null} in
     * which case the {@linkplain SimpleNameFactory#DEFAULT default factory} will be used.
     *
     * @param  parent  the parent of the new namespace, or {@code null} if none.
     * @param  name    the identifier of this namespace.
     * @throws InvalidNameException if the given name violates the JNDI
     *         {@linkplain SimpleNameFactory#syntax syntax}.
     */
    public SimpleNameSpace(final SimpleNameSpace parent, final String name) throws InvalidNameException {
        Objects.requireNonNull(name, "A name shall be specified.");
        final Name jndi;
        if (parent == null) {
            factory = SimpleNameFactory.DEFAULT;
            jndi = new CompoundName(name, factory.syntax);
        } else {
            factory = parent.factory;
            jndi = new CompoundName(name, factory.syntax).addAll(0, parent.name.name);
        }
        this.name = SimpleName.create(null, jndi);
    }

    /**
     * Creates a new instance for the given parent namespace and name. The new instance will share
     * the {@linkplain #factory} instance from its parent, unless {@code parent} is {@code null} in
     * which case the {@linkplain SimpleNameFactory#DEFAULT default factory} will be used.
     *
     * @param  parent  the parent of the new namespace, or {@code null} if none.
     * @param  name    the identifier of this namespace.
     * @throws InvalidNameException if the given name violates the JNDI
     *         {@linkplain SimpleNameFactory#syntax syntax}.
     */
    public SimpleNameSpace(final SimpleNameSpace parent, Name name) throws InvalidNameException {
        Objects.requireNonNull(name, "A name shall be specified.");
        if (parent == null) {
            factory = SimpleNameFactory.DEFAULT;
        } else {
            factory = parent.factory;
            name = parent.name.jndiName().addAll(name);
        }
        this.name = SimpleName.create(null, name);
    }

    /**
     * {@return the given namespace as a {@code SimpleNameSpace} implementation}.
     * The current implementation just casts the given value, but future versions may copy the namespace.
     *
     * @param  name  the name to cast or copy.
     */
    static SimpleNameSpace castOrCopy(final NameSpace name) {
        return (SimpleNameSpace) name;
    }

    /**
     * Indicates whether this namespace is a "top level" namespace.
     * Global, or top-level namespaces are not contained within another namespace.
     * The global namespace has no parent.
     *
     * @return {@code true} if this namespace has no parent.
     */
    @Override
    public boolean isGlobal() {
        return name.depth() == 0;
    }

    /**
     * Represents the identifier of this namespace. Namespace identifiers shall be
     * {@linkplain GenericName#toFullyQualifiedName() fully-qualified names} where:
     *
     * <blockquote><code>
     * name.{@linkplain GenericName#scope() scope()}.{@linkplain #isGlobal()} == true
     * </code></blockquote>
     *
     * @return the identifier of this namespace.
     */
    @Override
    public GenericName name() {
        return name;
    }

    /**
     * Returns a string representation of this namespace.
     *
     * @return a string representation of this name.
     */
    @Override
    public String toString() {
        return name.toString();
    }

    /**
     * Compares the given object to this namespace for equality.
     *
     * @param  other The other object to compare to this namespace.
     */
    @Override
    public boolean equals(final Object other) {
        if (other instanceof SimpleNameSpace) {
            final SimpleNameSpace that = (SimpleNameSpace) other;
            return name.equals(that.name) && factory.equals(that.factory);
        }
        return false;
    }

    /**
     * Returns a hash code value for this namespace.
     */
    @Override
    public int hashCode() {
        return name.hashCode() ^ -904105087;
    }
}
