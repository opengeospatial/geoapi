/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.io.Serializable;
import javax.naming.Name;
import javax.naming.InvalidNameException;

import org.opengis.util.NameSpace;
import org.opengis.util.TypeName;
import org.opengis.util.MemberName;
import org.opengis.util.LocalName;
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
 *
 * @see #jndiName()
 */
public class SimpleName implements GenericName, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 5767162955069918493L;

    /**
     * A {@link TypeName} specialization of {@link Local}. The JNDI name wrapped by this
     * class shall contain exactly one component. In such case the {@linkplain #head() head},
     * the {@linkplain #tip() tip} and the {@linkplain #getParsedNames() parsed names} are
     * simply {@code this} instance.
     *
     * @author Martin Desruisseaux
     */
    public static class Type extends Local implements TypeName {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = -8971196012273803431L;

        /**
         * Creates a new instance backed by the given JNDI name. This constructor does not clone the
         * given JNDI name. While this implementation is robust to change in the wrapped object, it is
         * a better practice to keep the JNDI name unmodified after {@code SimpleName} construction.
         *
         * @param  scope The scope (name space) in which the given name is local.
         * @param  name  The JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
         * @throws IllegalArgumentException If the given name does not have exactly 1 component.
         */
        public Type(final SimpleNameSpace scope, final Name name) throws IllegalArgumentException {
            super(scope, name);
        }
    }

    /**
     * A {@link MemberName} specialization of {@link Local}. The JNDI name wrapped by this
     * class shall contain exactly one component. In such case the {@linkplain #head() head},
     * the {@linkplain #tip() tip} and the {@linkplain #getParsedNames() parsed names} are
     * simply {@code this} instance.
     *
     * @author Martin Desruisseaux
     */
    public static class Member extends Local implements MemberName {
        /**
         * For cross-version compatibility.
         */
        private static final long serialVersionUID = -3171497526465359628L;

        /**
         * The type of the data associated with the record member.
         */
        private final TypeName attributeType;

        /**
         * Creates a new instance backed by the given JNDI name. This constructor does not clone the
         * given JNDI name. While this implementation is robust to change in the wrapped object, it is
         * a better practice to keep the JNDI name unmodified after {@code SimpleName} construction.
         *
         * @param  scope The scope (name space) in which the given name is local.
         * @param  name  The JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
         * @param  attributeType The type of the data associated with the record member.
         * @throws IllegalArgumentException If the given name does not have exactly 1 component.
         */
        public Member(final SimpleNameSpace scope, final Name name, final TypeName attributeType)
                throws IllegalArgumentException
        {
            super(scope, name);
            Objects.requireNonNull(attributeType, "An attribute type must be specified.");
            this.attributeType = attributeType;
        }

        /**
         * Returns the type of the data associated with the record member.
         */
        @Override
        public TypeName getAttributeType() {
            return attributeType;
        }

        /**
         * Compares the given object to this name for equality. This method compares
         * the attribute type in addition to the field compared by the super-class.
         */
        @Override
        public boolean equals(final Object other) {
            return super.equals(other) && attributeType.equals(((Member) other).attributeType);
        }
    }

    /**
     * A {@link LocalName} specialization of {@link SimpleName}. The JNDI name wrapped by this
     * class shall contain exactly one component. In such case the {@linkplain #head() head},
     * the {@linkplain #tip() tip} and the {@linkplain #getParsedNames() parsed names} are
     * simply {@code this} instance.
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
         * Since this object is itself a locale name, returns a
         * {@linkplain Collections#singletonList(Object) singleton}
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
     * A {@link ScopedName} specialization of {@link SimpleName}.
     * The JNDI name wrapped by this class shall contain more than one component.
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
         * Returns every elements of the {@linkplain #getParsedNames() parsed names list} except for
         * the {@linkplain #head() head}. This implementation creates a new generic name from the
         * JNDI name {@linkplain Name#getSuffix(int) suffix}.
         *
         * @see Name#getSuffix(int)
         */
        @Override
        public GenericName tail() {
            return create(scope, name.getSuffix(1));
        }

        /**
         * Returns every elements of the {@linkplain #getParsedNames() parsed names list} except for
         * the {@linkplain #tip() tip}. This implementation creates a new generic name from the JNDI
         * name {@linkplain Name#getPrefix(int) prefix}.
         *
         * @see Name#getPrefix(int)
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
     *
     * @see #scope()
     */
    final SimpleNameSpace scope;

    /**
     * The JNDI name wrapped by this {@code SimpleName}. While JNDI names are mutable,
     * {@code GenericName} are expected to be immutable. Consequently users are advised
     * to not modify this object.
     *
     * @see #jndiName()
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
     * Returns the given generic name as a {@link SimpleName} implementation. The current
     * implementation just casts the given value, but future versions may copy the name components.
     */
    static SimpleName castOrCopy(final GenericName name) {
        return (SimpleName) name;
    }

    /**
     * Creates a new generic name for the given JNDI name. This method returns an instance of
     * {@link LocalName} or {@link ScopedName}, depending on the number of parsed components.
     *
     * @param  scope The name scope, or {@code null} for the name of a global namespace.
     * @param  name The JNDI name.
     * @return The generic name.
     */
    static SimpleName create(final SimpleNameSpace scope, final Name name) {
        switch (name.size()) {
            case 0:  return new SimpleName       (scope, name);
            case 1:  return new SimpleName.Local (scope, name);
            default: return new SimpleName.Scoped(scope, name);
        }
    }

    /**
     * Returns the factory to use for creating new name instances.
     */
    private SimpleNameFactory factory() {
        return (scope != null) ? scope.factory : SimpleNameFactory.DEFAULT;
    }

    /**
     * Returns a clone of the {@linkplain JNDI name} wrapped by this object.
     *
     * @return A clone of {@link #name}.
     */
    public Name jndiName() {
        return (Name) name.clone();
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
        SimpleNameSpace parent = scope;
        final List<LocalName> names = new ArrayList<LocalName>(name.size());
        final Enumeration<String> it = name.getAll();
        if (it.hasMoreElements()) try {
            final SimpleNameFactory factory = factory();
            while (true) {
                final String n = it.nextElement();
                names.add(factory.createLocalName(parent, n));
                if (!it.hasMoreElements()) {
                    break;
                }
                parent = new SimpleNameSpace(parent, n);
            }
        } catch (InvalidNameException e) {
            throw new IllegalStateException(e);
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
        return factory().createLocalName(scope, name.get(0));
    }

    /**
     * Returns the last element in the sequence of {@linkplain #getParsedNames() parsed names}.
     *
     * @see Name#get(int)
     */
    @Override
    public LocalName tip() {
        final SimpleNameFactory factory = factory();
        SimpleNameSpace parent = scope;
        final int n = name.size() - 1;
        for (int p=parent.name.name.size(); p<n; p++) {
            parent = new SimpleNameSpace(factory, create(parent, name.getPrefix(p)));
        }
        return factory.createLocalName(scope, name.get(n));
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
        Name n = scope.name.jndiName();
        try {
            n = n.addAll(name);
        } catch (InvalidNameException e) {
            throw new IllegalStateException(e);
        }
        return create(scope.name.scope, n);
    }

    /**
     * Returns this name expanded with the specified scope. One may represent this operation
     * as a concatenation of the specified {@code scope} with {@code this}.
     *
     * @param scope The name to use as prefix.
     * @return A concatenation of the given name with this name.
     *
     * @see Name#addAll(Name)
     */
    @Override
    public ScopedName push(final GenericName scope) {
        final SimpleName sc = castOrCopy(scope);
        Name n = sc.jndiName();
        try {
            n = n.addAll(name);
        } catch (InvalidNameException e) {
            throw new IllegalStateException(e);
        }
        return new Scoped(sc.scope, n);
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
     */
    @Override
    public int compareTo(final GenericName other) {
        return name.compareTo(castOrCopy(other).name);
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
