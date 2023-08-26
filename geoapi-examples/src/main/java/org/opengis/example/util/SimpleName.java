/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Optional;
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
 * of {@link LocalName} or {@link ScopedName} implementations, depending on whether the
 * {@linkplain Name#size() size} of the wrapped JNDI name is 1 or more, respectively.
 *
 * <p>This implementation is a <cite>view</cite>: any change applied on the wrapped JNDI object
 * will be immediately reflected in the {@code SimpleName} wrapper. Note however that
 * {@code GenericName} are usually expected to be immutable, so users are advised to not
 * modify the wrapped JNDI name.</p>
 *
 * @author Martin Desruisseaux
 *
 * @see #jndiName()
 */
public class SimpleName implements GenericName {
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
         * The "OGC:Real" type name.
         */
        public static final TypeName REAL;
        static {
            final SimpleNameFactory f = SimpleNameFactory.DEFAULT;
            final NameSpace OGC = f.createNameSpace(f.createLocalName(null, "OGC"), null);
            REAL = f.createTypeName(OGC, "Real", Double.class);
        }

        /**
         * The Java type represented by this name, or {@code null} if none.
         */
        private final java.lang.reflect.Type javaType;

        /**
         * Creates a new instance backed by the given JNDI name. This constructor does not clone the
         * given JNDI name. While this implementation is robust to change in the wrapped object, it is
         * a better practice to keep the JNDI name unmodified after {@code SimpleName} construction.
         *
         * @param  scope     the scope (name space) in which the given name is local, or {@code null}.
         * @param  name      the JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
         * @param  javaType  Java type represented by this name, or {@code null} if none.
         * @throws IllegalArgumentException if the given name does not have exactly 1 component.
         */
        public Type(final SimpleNameSpace scope, final Name name, final java.lang.reflect.Type javaType)
                throws IllegalArgumentException
        {
            super(scope, name);
            this.javaType = javaType;
        }

        /**
         * Returns the Java type represented by this name.
         *
         * @return the Java type (usually a {@link Class}) for this type name.
         */
        @Override
        public Optional<java.lang.reflect.Type> toJavaType() {
            return Optional.ofNullable(javaType);
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
         * The type of the data associated with the record member.
         */
        private final TypeName attributeType;

        /**
         * Creates a new instance backed by the given JNDI name. This constructor does not clone the
         * given JNDI name. While this implementation is robust to change in the wrapped object, it is
         * a better practice to keep the JNDI name unmodified after {@code SimpleName} construction.
         *
         * @param  scope  the scope (name space) in which the given name is local, or {@code null}.
         * @param  name   the JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
         * @param  attributeType  the type of the data associated with the record member.
         * @throws IllegalArgumentException if the given name does not have exactly 1 component.
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
         * Creates a new instance without namespace. This constructor shall be used only for the
         * creation of the root {@link NameSpace}.
         *
         * @param  name  an {@linkplain Name#isEmpty() empty} JNDI name (<strong>not</strong> cloned).
         * @throws IllegalArgumentException if the given name is not empty.
         */
        Local(final Name name) throws IllegalArgumentException {
            super(null, name);
            if (!name.isEmpty()) {
                throw new IllegalArgumentException(name.toString());
            }
        }

        /**
         * Creates a new instance backed by the given JNDI name. This constructor does not clone the
         * given JNDI name. While this implementation is robust to change in the wrapped object, it is
         * a better practice to keep the JNDI name unmodified after {@code SimpleName} construction.
         *
         * @param  scope  the scope (name space) in which the given name is local.
         * @param  name   the JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
         * @throws IllegalArgumentException if the given name does not have exactly 1 component.
         */
        public Local(final SimpleNameSpace scope, final Name name) throws IllegalArgumentException {
            super(scope, name);
            if (super.depth() != 1) {
                throw new IllegalArgumentException("Local name shall have exactly 1 component.");
            }
        }

        /**
         * Since this object is itself a locale name, returns a
         * {@linkplain Collections#singletonList(Object) singleton}
         * list containing only {@code this}.
         */
        @Override
        public List<LocalName> getParsedNames() {
            switch (super.depth()) {
                case 0:  return Collections.emptyList();                    // Only for the root namespace.
                case 1:  return Collections.<LocalName>singletonList(this);
                default: return super.getParsedNames();
            }
        }

        /**
         * Returns {@code this} since this object is already a local name.
         *
         * @return {@code this}.
         */
        @Override
        public LocalName head() {
            switch (super.depth()) {
                case 0:  return null;               // Only for the root namespace; prevent never-ending loop.
                case 1:  return this;               // The normal case, which should always be selected.
                default: return super.head();       // Only if user modified the JNDI Name.
            }
        }

        /**
         * Returns {@code this} since this object is already a local name.
         *
         * @return {@code this}.
         */
        @Override
        public LocalName tip() {
            switch (super.depth()) {
                case 0:  return null;               // Only for the root namespace; prevent never-ending loop.
                case 1:  return this;               // The normal case, which should always be selected.
                default: return super.tip();        // Only if user modified the JNDI Name.
            }
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
         * Creates a new instance backed by the given JNDI name. This constructor does not clone the
         * given JNDI name. While this implementation is robust to change in the wrapped object, it is
         * a better practice to keep the JNDI name unmodified after {@code SimpleName} construction.
         *
         * @param  scope  the scope (name space) in which the given name is local, or {@code null}.
         * @param  name   the JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
         * @throws IllegalArgumentException if the given name has less than 2 components.
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
            try {
                return create(new SimpleNameSpace(scope, name.get(0)), name.getSuffix(1));
            } catch (InvalidNameException e) {
                throw new IllegalStateException(e);
            }
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
     *
     * <p>This field shall not be null, except for the global namespace.</p>
     *
     * @see #scope()
     */
    final SimpleNameSpace scope;

    /**
     * The JNDI name wrapped by this {@code SimpleName}. While JNDI names are mutable,
     * {@code GenericName} are expected to be immutable. Consequently, users are advised
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
     * @param scope  the scope (name space) in which the given name is local, or {@code null}.
     * @param name   the JNDI name wrapped by this {@code SimpleName} (<strong>not</strong> cloned).
     */
    protected SimpleName(final SimpleNameSpace scope, final Name name) {
        Objects.requireNonNull(name, "A JNDI name must be provided.");
        this.scope = (scope != SimpleNameSpace.ROOT) ? scope : null;
        this.name  = name;
    }

    /**
     * {@return the given generic name as a {@code SimpleName} implementation}.
     * The current implementation just casts the given value,
     * but future versions may copy the name components.
     *
     * @param  name  the name to cast or copy.
     */
    static SimpleName castOrCopy(final GenericName name) {
        return (SimpleName) name;
    }

    /**
     * Creates a new generic name for the given JNDI name. This method returns an instance of
     * {@link LocalName} or {@link ScopedName}, depending on the number of parsed components.
     *
     * @param  scope  the name scope, or {@code null} for the name of a global namespace.
     * @param  name   the JNDI name.
     * @return the generic name.
     */
    static SimpleName create(final SimpleNameSpace scope, final Name name) {
        switch (name.size()) {
            case 0:  return new SimpleName       (scope, name);
            case 1:  return new SimpleName.Local (scope, name);
            default: return new SimpleName.Scoped(scope, name);
        }
    }

    /**
     * Returns the given scope, or the root namespace if the given argument is null.
     *
     * @param  scope  the scope to return, or {@code null} for the root.
     * @return the given scope, or {@link SimpleNameSpace#ROOT}.
     */
    private static SimpleNameSpace nonNull(final SimpleNameSpace scope) {
        return (scope != null) ? scope : SimpleNameSpace.ROOT;
    }

    /**
     * Returns a clone of the {@linkplain Name JNDI name} wrapped by this object.
     *
     * @return a clone of {@link #name}.
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
        return nonNull(scope);
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
        final List<LocalName> names = new ArrayList<>(name.size());
        final Enumeration<String> it = name.getAll();
        if (it.hasMoreElements()) try {
            final SimpleNameFactory factory = nonNull(parent).factory;
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
        return nonNull(scope).factory.createLocalName(scope, name.get(0));
    }

    /**
     * Returns the last element in the sequence of {@linkplain #getParsedNames() parsed names}.
     *
     * @see Name#get(int)
     */
    @Override
    public LocalName tip() {
        final int n = name.size() - 1;
        final SimpleNameSpace parent;
        try {
            parent = new SimpleNameSpace(scope, name.getPrefix(n));
        } catch (InvalidNameException e) {
            throw new IllegalStateException(e);
        }
        return nonNull(scope).factory.createLocalName(parent, name.get(n));
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
     * @return a concatenation of the given name with this name.
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
     * @return a string representation of this name.
     */
    @Override
    public String toString() {
        return name.toString();
    }

    /**
     * Returns a local-dependent string representation of this generic name.
     * The default implementation wraps the {@link #toString()} value.
     *
     * @return a localizable string representation of this name.
     */
    @Override
    public InternationalString toInternationalString() {
        return new SimpleCitation(toString());
    }

    /**
     * Compares this name with the given object for lexicographical order.
     * Note that the {@linkplain #scope() scope} is not part of this comparison.
     *
     * @param  other  the other object to compare to this name.
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
     * @param  other  the other object to compare to this name.
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
        int code = name.hashCode() ^ 69918493;
        if (scope != null) {
            code += 31*scope.hashCode();
        }
        return code;
    }
}
