/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.util;

import java.io.Serializable;
import javax.naming.Name;
import javax.naming.CompoundName;
import javax.naming.InvalidNameException;

import org.opengis.util.NameSpace;
import org.opengis.util.LocalName;
import org.opengis.util.GenericName;


/**
 * A simple {@link NameSpace}.
 *
 * @author Martin Desruisseaux
 */
public class SimpleNameSpace implements NameSpace, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -8425839143904105087L;

    /**
     * The factory to use for creating name instances in this namespace.
     */
    protected final SimpleNameFactory factory;

    /**
     * The identifier of this namespace.
     */
    final SimpleName name;

    /**
     * Creates a new instance for name and using the given factory.
     * This constructor is package-private in order to prevent improper values.
     */
    SimpleNameSpace(final SimpleNameFactory factory, final SimpleName name) {
        this.factory = factory;
        this.name    = name;
    }

    /**
     * Creates a new instance for the given name and
     * {@linkplain SimpleNameFactory#syntax default JNDI syntax}.
     *
     * @param  name The identifier of this namespace.
     * @throws InvalidNameException If the given name violates the JNDI syntax.
     */
    public SimpleNameSpace(final String name) throws InvalidNameException {
        this(null, name);
    }

    /**
     * Creates a new instance for the given parent namespace and name. The new instance will share
     * the {@linkplain #factory} instance from its parent, unless {@code parent} is {@code null} in
     * which case the {@linkplain SimpleNameFactory#DEFAULT default factory} will be used.
     *
     * @param  parent The parent of the new namespace, or {@code null} if none.
     * @param  name The identifier of this namespace.
     * @throws InvalidNameException If the given name violates the JNDI
     *         {@linkplain SimpleNameFactory#syntax syntax}.
     */
    public SimpleNameSpace(SimpleNameSpace parent, final String name) throws InvalidNameException {
        Objects.requireNonNull(name, "A name shall be specified.");
        Name jndi;
        int p;
        if (parent == null) {
            p = 0;
            factory = SimpleNameFactory.DEFAULT;
            jndi = new CompoundName(name, factory.syntax);
        } else {
            factory = parent.factory;
            jndi = parent.name.jndiName();
            p = jndi.size();
            jndi = jndi.addAll(new CompoundName(name, factory.syntax));
        }
        final int n = jndi.size() - 1;
        while (p < n) {
            parent = new SimpleNameSpace(factory, SimpleName.create(parent, jndi.getPrefix(p)));
            p++;
        }
        this.name = SimpleName.create(parent, jndi);
    }

    /**
     * Returns the given namespace as a {@link SimpleName} implementation. The current
     * implementation just casts the given value, but future versions may copy the namespace.
     */
    static SimpleNameSpace castOrCopy(final NameSpace name) {
        return (SimpleNameSpace) name;
    }

    /**
     * Indicates whether this namespace is a "top level" namespace. Global, or top-level
     * namespaces are not contained within another namespace.
     *
     * @return {@code true} if this namespace has no parent.
     */
    @Override
    public boolean isGlobal() {
        return (name instanceof LocalName);
    }

    /**
     * Returns the identifier of this namespace. If the {@linkplain #isGlobal() global} attribute is
     * {@code true}, indicating that this is a top level {@code NameSpace}, then the name shall be a
     * {@linkplain LocalName local name}.
     *
     * @return The identifier of this namespace.
     */
    @Override
    public GenericName name() {
        return name;
    }

    /**
     * Returns a string representation of this namespace.
     *
     * @return A string representation of this name.
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
        return name.hashCode() ^ (int) serialVersionUID;
    }
}
