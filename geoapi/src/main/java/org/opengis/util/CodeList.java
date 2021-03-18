/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2003-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import java.io.Serializable;
import java.io.ObjectStreamException;
import java.io.InvalidObjectException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Base class for all code lists. Subclasses shall provides a {@code values()} method
 * which returns all {@code CodeList} element in an array of the appropriate class.
 *
 * <p>Code lists are extensible, i.e. invoking the {@code valueOf(String)} method in any subclass
 * will automatically add the newly created {@code CodeList} element in the array to be returned
 * by {@code values()}.</p>
 *
 * @param <E> The type of this code list.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 4.0
 * @since   1.0
 */
@UML(identifier="CodeList", specification=ISO_19103)
public abstract class CodeList<E extends CodeList<E>> implements ControlledVocabulary, Comparable<E>, Serializable {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5655809691319522885L;

    /**
     * The values for each code list.
     */
    @SuppressWarnings("rawtypes")
    private static final Map<Class<? extends CodeList<?>>, Collection<? extends CodeList<?>>> VALUES = new HashMap<>();

    /**
     * The types expected in constructors.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    private static final Class<String>[] CONSTRUCTOR_PARAMETERS = new Class[] {
        String.class
    };

    /**
     * The code value.
     */
    private transient final int ordinal;

    /**
     * The code name.
     */
    private final String name;

    /**
     * The identifier declared in the {@link UML} annotation, or an empty string if there is
     * no such annotation or if the annotation contains an empty string.  This field will be
     * computed only when first needed.
     */
    private transient String identifier;

    /**
     * Creates a new code list element and add it to the given collection. Subclasses
     * will typically give a static reference to an {@link java.util.ArrayList} for
     * the {@code values} argument. This list is used for {@code values()}
     * method implementations.
     *
     * @param  name    the code name.
     * @param  values  the collection to add the element to.
     * @throws IllegalArgumentException if {@code values} is not the same collection
     *         than the one specified for previous instances of the same code list class.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    protected CodeList(String name, final Collection<E> values) {
        this.name = (name = name.trim());
        synchronized (values) {
            ordinal = values.size();
            if (!values.add((E) this)) {
                // Should not happen with standard collection implementations.
                throw new IllegalArgumentException("Duplicated value: " + name);
            }
        }
        final Class<? extends CodeList<?>> codeType = (Class<? extends CodeList<?>>) getClass();
        final Collection<? extends CodeList<?>> previous;
        synchronized (VALUES) {
            previous = VALUES.putIfAbsent(codeType, values);
        }
        if (previous != null && previous != values) {
            throw new IllegalArgumentException("List already exists: " + values);
        }
    }

    /**
     * Returns the code of the given type that matches the given name, or returns a new one if none
     * match it. More specifically, this methods returns the first instance of the given class for
     * which <code>{@linkplain #name()}.{@linkplain String#equals equals}(name)</code> is {@code true}.
     * If no such instance is found, then a new instance is created using the constructor expecting
     * a single {@link String} argument.
     *
     * <p><strong>Note that invoking this method may result in the creation of a new code value.</strong>
     * If this is not desired, use {@link #valueOf(Class, Predicate, String)} instead.</p>
     *
     * @param  <T>       the compile-time type given as the {@code codeType} parameter.
     * @param  codeType  the type of code list.
     * @param  name      the name of the code to obtain, or {@code null}.
     * @return a code matching the given name (possible a new code), or {@code null} if the given name is null.
     *
     * @departure integration
     *   Provided by analogy with the methods in the Java {@code Enum} class.
     */
    public static <T extends CodeList<T>> T valueOf(final Class<T> codeType, String name) {
        if (name == null) {
            return null;
        }
        name = name.trim();
        final String n = name.trim();       // Need final for lambda.
        return valueOf(codeType, (code) -> n.equals(code.name), name);
    }

    /**
     * Returns the code of the given type that matches the given criterion, or potentially a new code if there is no match.
     * More specifically, this methods returns the first element (in declaration order) of the given
     * class where <code>filter.{@linkplain Predicate#test test}(code)</code> returns {@code true}.
     * If no such element is found, then there is a choice:
     *
     * <ul>
     *   <li>If {@code nameIfNew} is {@code null}, then this method returns {@code null}.</li>
     *   <li>Otherwise a new instance is created using the constructor expecting a single {@link String}
     *       argument, which is given the {@code nameIfNew} value.</li>
     * </ul>
     *
     * <p>This method is useful when a lenient comparisons of names is desired (for example ignoring cases or
     * taking in account all names enumerated by {@link #names()}, or when the caller does not want to create
     * new code value in case of no match.</p>
     *
     * @param  <T>        the compile-time type given as the {@code codeType} parameter.
     * @param  codeType   the type of code list.
     * @param  filter     the criterion for the code to obtain.
     * @param  nameIfNew  the name to use if a new code list needs to be created,
     *                     or {@code null} for not creating any new code list.
     * @return a code matching the given criterion, or {@code null} if none and {@code nameIfNew} is null.
     *
     * @since 3.1
     */
    public static <T extends CodeList<T>> T valueOf(final Class<T> codeType,
            final Predicate<CodeList<?>> filter, final String nameIfNew)
    {
        Collection<? extends CodeList<?>> values;
        synchronized (VALUES) {
            values = VALUES.get(codeType);
            if (values == null) {
                if (codeType == null) {
                    throw new IllegalArgumentException("Code type is null");
                } else {
                    /*
                     * If no list has been found for the given type, maybe the class was not yet initialized.
                     * Try to force class initialization of the given class in order to register its list of
                     * static final constants, then check again.
                     */
                    final String typeName = codeType.getName();
                    try {
                        Class.forName(typeName, true, codeType.getClassLoader());
                    } catch (ClassNotFoundException e) {
                        throw new TypeNotPresentException(typeName, e);             // Should never happen.
                    }
                    values = VALUES.get(codeType);
                    if (values == null) {
                        throw new IllegalStateException("No list of " + codeType.getSimpleName());
                    }
                }
            }
        }
        /*
         * At this point we got the list of all code list values. Now search for a value matching
         * the filter specified to this method. The search and, eventually, the code creation are
         * done in the same synchronized block for making sure that the came code is nit created
         * twice concurrently.
         */
        synchronized (values) {
            for (final CodeList<?> code : values) {
                if (filter.test(code)) {
                    return codeType.cast(code);
                }
            }
            if (nameIfNew == null || Modifier.isAbstract(codeType.getModifiers())) {
                return null;
            }
            /*
             * No value value found, but the caller allows us to create a new value. We need access to the constructor,
             * which may not be public. But requesting access to private constructor is a security-sensitive operation.
             * As a conservative approach, we will request for a privileged action only for code lists in "org.opengis"
             * packages. Note that it still possible for users to instantiate code lists from other packages, but they
             * will need to configure their security file for granting access from their own application in addition
             * to GeoAPI. The "doPriviliged" block is for allowing users to grant access to GeoAPI only if they wish.
             *
             * TODO: the check for package name may still not sufficient on a security point of view.
             *       We should also check if the package is sealed, and maybe check if it is signed by OGC.
             *       Revisit with JDK9 modularization.
             */
            try {
                final Constructor<T> constructor = codeType.getDeclaredConstructor(CONSTRUCTOR_PARAMETERS);
                if (!Modifier.isPublic(constructor.getModifiers())) {
                    final Package pkg = codeType.getPackage();
                    if (pkg != null && pkg.getName().startsWith("org.opengis.")) {
                        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                            constructor.setAccessible(true);
                            return null;
                        });
                    } else {
                        constructor.setAccessible(true);
                    }
                }
                return constructor.newInstance(nameIfNew);
            } catch (ReflectiveOperationException exception) {
                throw new IllegalArgumentException("Can not create code of type " + codeType.getSimpleName(), exception);
            }
        }
    }

    /**
     * Returns the list of codes of the same kind than this code.
     * Invoking this method gives identical results than invoking the static {@code values()} methods
     * provided in {@code CodeList} subclasses, except that {@code family()} does not require the class
     * to be known at compile-time — provided that at least one instance of the family is available.
     *
     * @return the codes of the same kind than this code.
     *
     * @departure integration
     *   Provided by analogy with {@code Enum.family()}, which was defined in a initial
     *   draft of Java 5 before the final release.
     */
    @Override
    public abstract E[] family();

    /**
     * Returns all the names of this code. The returned array contains the
     * following elements, with duplicated values and null values removed:
     *
     * <ul>
     *   <li>The programmatic {@linkplain #name() name}</li>
     *   <li>The UML {@linkplain #identifier() identifier}</li>
     *   <li>Any other special case, if any. Examples:
     *     <ul>
     *       <li>The legacy name of {@link org.opengis.metadata.constraint.Restriction#LICENCE}.</li>
     *       <li>The alternative name of {@link org.opengis.referencing.datum.PixelInCell#CELL_CENTER}.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * Those names are typically equal except for the case (programmatic names are upper case
     * while UML names are lower case) and special characters like {@code '-'}.
     *
     * @return all names of this code constant. This array is never null and never empty.
     *
     * @departure extension
     *   Defined because each {@code CodeList} has at least two names, the Java programmatic
     *   name and the UML identifier, while some subclasses have additional names.
     */
    @Override
    public String[] names() {
        final String name = this.name;
        final String identifier = identifier();
        if (identifier != null && !identifier.equals(name)) {
            return new String[] {name, identifier};
        } else {
            return new String[] {name};
        }
    }

    /**
     * Returns the programmatic name of this code list constant. This
     * is the name of the public static field which declare the code.
     *
     * @departure integration
     *   Provided by analogy with the methods in the Java {@code Enum} class.
     *
     * @return the name of this code constant.
     */
    @Override
    public final String name() {
        return name;
    }

    /**
     * Returns the identifier declared in the {@link UML} annotation, or {@code null} if none.
     * The UML identifier shall be the ISO or OGC name for this code constant.
     *
     * @return the ISO/OGC identifier for this code constant, or {@code null} if none.
     *
     * @departure extension
     *   Defined because each {@code CodeList} has a UML identifier in addition of the Java
     *   programmatic name.
     */
    @Override
    public String identifier() {
        // Save the field in a local variable for protection against concurrent change (this
        // operation is guaranteed atomic according Java specification). We don't synchronize
        // since it is not a problem if this method is executed twice in concurrent threads.
        String identifier = this.identifier;
        if (identifier == null) {
            @SuppressWarnings("rawtypes")
            final Class<? extends CodeList> codeType = getClass();
            Field field;
            try {
                field = codeType.getField(name);
            } catch (NoSuchFieldException e) {
                // There is no field for a code of this name. It may be normal, since the user
                // may have created a custom CodeList without declaring it as a constant.
                field = null;
            }
            if (field != null && Modifier.isStatic(field.getModifiers())) {
                final Object value;
                try {
                    value = field.get(null);
                } catch (IllegalAccessException e) {
                    // Should never happen since getField(String) returns only public fields.
                    throw new AssertionError(e);
                }
                if (equals(value)) {
                    final UML annotation = field.getAnnotation(UML.class);
                    if (annotation != null) {
                        identifier = annotation.identifier();
                    }
                }
            }
            if (identifier == null) {
                identifier = "";
            } else {
                identifier = identifier.intern();
            }
            this.identifier = identifier;
        }
        return identifier.length() != 0 ? identifier : null;
    }

    /**
     * Returns the ordinal of this code constant. This is its position in its elements declaration,
     * where the initial constant is assigned an ordinal of zero.
     *
     * @return the position of this code constants in elements declaration.
     *
     * @departure integration
     *   Provided by analogy with the methods in the Java {@code Enum} class.
     */
    @Override
    public final int ordinal() {
        return ordinal;
    }

    /**
     * Compares this code with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>Code list constants are only comparable to other code list constants of the
     * same type.  The natural order implemented by this method is the order in which
     * the constants are declared.</p>
     *
     * @param  other  the code constant to compare with this code.
     * @return a negative value if the given code is less than this code,
     *         a positive value if greater or 0 if equal.
     */
    @Override
    @SuppressWarnings("rawtypes")
    public final int compareTo(final E other) {
        final Class<? extends CodeList> ct =  this.getClass();
        final Class<? extends CodeList> co = other.getClass();
        if (!ct.equals(co)) {
            throw new ClassCastException("Can't compare " + ct.getSimpleName() + " to " + co.getSimpleName());
        }
        return ordinal - ((CodeList<?>) other).ordinal;
    }

    /*
     * Do not define `equals(Object)` and `hashCode()`. The identity comparison is consistent with above
     * `compareTo(E)` method because there is no two CodeLists of the same class having the same ordinal value.
     */

    /**
     * Returns a string representation of this code list.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + name + ']';
    }

    /**
     * Resolves the code list to an unique instance after deserialization. The instance is resolved
     * using its {@linkplain #name() name} only (not its {@linkplain #ordinal() ordinal}).
     *
     * @return this code list as an unique instance.
     * @throws ObjectStreamException if the deserialization failed.
     */
    protected Object readResolve() throws ObjectStreamException {
        @SuppressWarnings("unchecked")
        final Class<? extends CodeList<?>> codeType = (Class<? extends CodeList<?>>) getClass();
        final Collection<? extends CodeList<?>> values;
        synchronized (VALUES) {
            values = VALUES.get(codeType);
        }
        if (values != null) {
            synchronized (values) {
                for (final CodeList<?> code : values) {
                    if (!codeType.isInstance(code)) {
                        // Paranoiac check - should never happen unless the subclass
                        // modifies itself the collection given to the constructor,
                        // in which case we will not touch it.
                        return this;
                    }
                    if (code.name.equals(name)) {
                        return code;
                    }
                }
                // We have verified with codeType.isInstance(code) that every elements are
                // of the appropriate class. This is the best we can do for type safety.
                @SuppressWarnings("unchecked")
                final Collection<CodeList<?>> unsafe = (Collection) values;
                if (!unsafe.add(this)) {
                    // Paranoiac check - should never happen.
                    throw new InvalidObjectException(name);
                }
            }
        }
        return this;
    }
}
