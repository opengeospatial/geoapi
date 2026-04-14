/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2025 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import java.io.Serializable;
import java.io.ObjectStreamException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Predicate;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;
import org.opengis.geoapi.internal.Errors;


/**
 * Base class for all code lists. Code lists are like enumerations, but extensible.
 * For example, invoking the {@code valueOf(String)} method in any subclass with a
 * name that was not previously defined will create a new {@code CodeList} element
 * and automatically add it to the arrays returned by {@code values()}.
 *
 * <h2>Guidance for subclasses</h2>
 * The parameter type {@code <E>} <em>shall</em> be the same type as the code list subclass.
 * In other words, if the subclass is {@code Foo}, then it must extend {@code CodeList<Foo>}.
 * In addition, subclasses <em>should</em> provide the following public static methods:
 * {@code E valueOf(String)} and {@code E[] values()}.
 * The following code snippet is a suggested pattern for subclasses:
 *
 * {@snippet lang="java" :
 * import java.util.List;
 * import org.opengis.util.CodeList;
 *
 * public final class Foo extends CodeList<Foo> {
 *     public static final Foo BAR;
 *     public static final Foo BIZ;
 *
 *     private static final List<Foo> VALUES = initialValues(
 *         // Inline assignments for getting compiler error if a field is missing or duplicated.
 *         BAR = new Foo("BAR"),
 *         BIZ = new Foo("BIZ"));
 *
 *     private Foo(String name) {    // Keeping the constructor private is strongly recommended.
 *         super(name);
 *     }
 *
 *     public static Foo valueOf(String name) {
 *         return valueOf(VALUES, name, Foo::new);
 *     }
 *
 *     public static Foo[] values() {
 *         return VALUES.toArray(Foo[]::new);
 *     }
 *
 *     @Override
 *     public Foo[] family() {
 *         return values();
 *     }
 * }
 * }
 *
 * <p>Keeping the constructor private is strongly recommended. The constructor should be invoked only in static
 * fields initialization and in the lambda function of the {@code valueOf(String)} method body. All other codes
 * should invoke {@code valueOf(…)} and never invoke the constructor directly.</p>
 *
 * @param  <E>  the type of this code list.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 */
@UML(identifier="CodeList", specification=ISO_19103)
public abstract class CodeList<E extends CodeList<E>> implements ControlledVocabulary, Comparable<E>, Serializable {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5655809691319522885L;

    /**
     * The code value as a sequential number incremented in the order in which codes are added to their collection.
     * This ordinal value is set by {@link #initialValues(CodeList...)}, {@link #valueOf(List, String, Function)} or
     * at deserialization time and should be considered final after initialization.
     * A value of -1 means that the ordinal has not yet been set.
     *
     * @see #ordinal()
     * @see #readResolve()
     */
    private transient int ordinal;

    /**
     * The code name. If this {@code CodeList} instance is stored in a static field
     * of the code list class, then this name should be identical to the field name.
     *
     * @see #name()
     * @see #names()
     */
    private final String name;

    /**
     * The identifier declared in the {@link UML} annotation, or an empty string if there is
     * no such annotation or if the annotation contains an empty string.  This field will be
     * computed only when first needed.
     *
     * @see #identifier()
     * @see #names()
     */
    private transient String identifier;

    /**
     * Creates a new code list element and add it to the given collection. Subclasses
     * will typically give a static reference to an {@link java.util.ArrayList} for
     * the {@code values} argument. This list is used for {@code values()}
     * method implementations.
     *
     * @param  name    the code name. Shall be the name of the static field if such field exist.
     * @param  values  the collection to add the element to. Shall be the same for all elements.
     *
     * @deprecated This constructor is unsafe because a reference to {@code this} escapes before subclass is
     * fully initialized (see <a href="https://github.com/opengeospatial/geoapi/issues/91">issue #91</a>).
     * Use {@link #CodeList(String)} instead.
     */
    @Deprecated(since="3.1", forRemoval=true)
    protected CodeList(String name, final Collection<E> values) {
        this(name);
        synchronized (values) {
            this.ordinal = values.size();
            if (!values.add((E) this)) {
                // Should not happen with standard collection implementations.
                throw new IllegalArgumentException("Duplicated value: " + name);
            }
        }
    }

    /**
     * Creates a new code list element. If this constructor is invoked for initializing a static field,
     * then the given name should be the case-sensitive name of the static field.
     *
     * @param  name  the code name. Should be the name of the static field if such field exists.
     *
     * @since 3.1
     */
    protected CodeList(final String name) {
        this.name = name;
        ordinal = -1;   // Means that the ordinal value is not yet set.
    }

    /**
     * If the ordinal value has not yet been set, sets the ordinal to the specified value.
     * Otherwise, throws an exception if the given value is different than the ordinal, or
     * does nothing if the value is the same (i.e., ignore redundant calls to this method).
     *
     * @param  value  the desired ordinal value.
     * @throws IllegalArgumentException if the ordinal was already set to a different value.
     */
    private synchronized void setOrdinal(final int value) {
        if (ordinal != value) {
            if (ordinal >= 0) {
                throw new IllegalArgumentException(name);
            }
            ordinal = value;
        }
    }

    /**
     * Creates an add-only list of code list values initialized to the given elements.
     * This method should be invoked by subclasses in a pattern like below:
     *
     * {@snippet lang="java" :
     *     public static final Foo BAR;
     *     public static final Foo BIZ;
     *
     *     private static final List<Foo> VALUES = initialValues(
     *         // Inline assignments for getting compiler error if a field is missing or duplicated.
     *         BAR = new Foo("BAR"),
     *         BIZ = new Foo("BIZ"));
     * }
     *
     * The returned list supports the addition of new code list values,
     * but not modification or removal of previous values.
     * The list is thread-safe.
     *
     * @param  <E>     the type of this code list.
     * @param  values  the initial values of the code list.
     * @return a thread-safe add-only list initialized with the given values.
     * @throws NullPointerException if {@code values} is null or any of its element is null.
     * @throws IllegalArgumentException if an element of {@code values} has an inconsistent ordinal value.
     *
     * @since 3.1
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    protected static <E extends CodeList<E>> List<E> initialValues(final E... values) {
        return new Values<>(values);
    }

    /**
     * A thread-safe add-only list of {@code CodeList} values.
     * This list supports the addition of new code list values,
     * but not modification or removal of previous values.
     */
    private static final class Values<E extends CodeList<E>> extends AbstractList<E> implements RandomAccess {
        /**
         * The values in an array considered as immutable.
         * This array is copied when new values are added.
         *
         * <p>Note: the {@code volatile} keyword applies only to the reference to the array,
         * not to the array elements. But this is okay if the array is considered immutable.</p>
         */
        @SuppressWarnings("VolatileArrayField")
        private volatile E[] values;

        /**
         * Creates a new list initialized to a copy of the given array.
         *
         * @param  values  the initial values of the code list.
         * @throws NullPointerException if {@code values} is null or any of its element is null.
         * @throws IllegalArgumentException if an element of {@code values} has an inconsistent ordinal value.
         */
        Values(E[] values) {
            values = values.clone();
            for (int i=0;  i<values.length; i++) {
                CodeList<E> value = values[i];
                value.setOrdinal(i);
            }
            this.values = values;
        }

        /** Returns the current number of values. */
        @Override public int size() {return values.length;}

        /** Returns the value for the given ordinal. */
        @Override public E get(int ordinal) {return values[ordinal];}

        /**
         * Adds the given element to the list of codes.
         *
         * @param  value  the value to add.
         * @return always {@code true}.
         * @throws NullPointerException if {@code value} is null.
         * @throws IllegalArgumentException if {@code value} has an inconsistent ordinal value.
         * @throws ArrayStoreException if the caller cheated with Java generic types.
         */
        @Override
        public synchronized boolean add(final E value) {
            E[] copy = values;
            final int last = copy.length;
            copy = Arrays.copyOf(copy, last + 1);
            copy[last] = value;     // Check for `ArrayStoreException` before to set the ordinal value.
            ((CodeList<E>) value).setOrdinal(last);
            values = copy;
            return true;
        }

        /**
         * Returns an snapshot of this list at the time when this method is invoked.
         * We use {@code Arrays.asList(…)} instead of {@code List.of(…)} for avoiding an array copy.
         * Because the returned list is modifiable except for element removal, this method should be
         * invoked only for <abbr>API</abbr> that do not allow the modification of list elements.
         */
        private List<E> snapshot() {return Arrays.asList(values);}

        /** Delegates to {@code Arrays.asList(values)} for efficient operation on a snapshot. */
        @SuppressWarnings("SuspiciousToArrayCall")
        @Override public <T> T[]        toArray(T[] t) {return snapshot().toArray(t);}
        @Override public Object[]       toArray()      {return snapshot().toArray();}
        @Override public Iterator<E>    iterator()     {return snapshot().iterator();}
        @Override public Spliterator<E> spliterator()  {return snapshot().spliterator();}
    }

    /**
     * Used by {@link CodeList#valueOf(Class, Filter)} to select codes matching an arbitrary
     * criterion.
     *
     * @deprecated Replaced by {@link Predicate} from the standard Java library.
     */
    @Deprecated(since="3.1", forRemoval=true)
    public static interface Filter {
        /**
         * Returns {@code true} if the given code matches the criterion defined by this filter.
         *
         * @param  code  the code to test.
         * @return {@code true} if the code matches the criterion.
         */
        boolean accept(CodeList<?> code);

        /**
         * Returns the name of the code being looked for, or {@code null} if unknown.
         * This method is invoked by {@link CodeList#valueOf(Class, Filter)} if no code
         * match the criterion defined by this filter. In such case, there is a choice:
         *
         * <ul>
         *   <li>If this method returns a non-null name, then a new code of that name is created.</li>
         *   <li>Otherwise, no new code is created and {@code CodeList.valueOf} returns {@code null}.</li>
         * </ul>
         *
         * @return the name of the code being looked for, or {@code null}.
         */
        String codename();
    }

    /**
     * Returns the code of the given type that matches the given name, or creates a new code if there is no match.
     * More specifically, this methods returns the first instance of the given class for which
     * <code>{@linkplain #name()}.{@linkplain String#equals equals}(name)</code> is {@code true}.
     * If no such instance is found, then a new instance is created using the constructor expecting
     * a single {@link String} argument.
     *
     * <p><strong>Note that invoking this method may result in the creation of a new code value.</strong>
     * If this is not desired, use {@link #valueOf(Class, String, Function)} instead.</p>
     *
     * @param  <T>       the compile-time type given as the {@code codeType} parameter.
     * @param  codeType  the type of code list.
     * @param  name      the name of the code to obtain (case sensitive), or {@code null}.
     * @return a code matching the given name (possible a new code), or {@code null} if the given name is null.
     *
     * @deprecated This method depends on reflection, which is restricted in the context of Java Module System.
     * Use {@link #valueOf(Class, String, Function)} instead.
     */
    @Deprecated(since="3.1", forRemoval=true)
    public static <T extends CodeList<T>> T valueOf(final Class<T> codeType, String name) {
        if (name == null) {
            return null;
        }
        name = name.trim();
        final String n = name.trim();       // Need final for lambda.
        return valueOf(codeType, new Filter() {
            @Override public boolean accept(CodeList<?> code) {
                return n.equals(code.name);
            }

            @Override public String codename() {
                return n;
            }
        });
    }

    /**
     * Returns the code of the given type that matches the given criterion, or returns a new one
     * if none match it. More specifically, this methods returns the first element (in declaration
     * order) of the given class where <code>filter.{@linkplain Filter#accept accept}(code)</code>
     * returns {@code true}. If no such element is found, then there is a choice:
     *
     * <ul>
     *   <li>If {@link Filter#codename()} returns {@code null}, then this method returns {@code null}.</li>
     *   <li>Otherwise a new instance is created using the constructor expecting a single {@link String}
     *       argument, which is given the value returned by {@code codename()}.</li>
     * </ul>
     *
     * @param  <T>       the compile-time type given as the {@code codeType} parameter.
     * @param  codeType  the type of code list.
     * @param  filter    the criterion for the code to obtain.
     * @return a code matching the given criterion, or {@code null} if there is no match and
     *         {@link Filter#codename()} returns {@code null}.
     *
     * @deprecated This method depends on reflection, which is restricted in the context of Java Module System.
     * Use {@link #valueOf(Class, String, Function)} instead.
     */
    @Deprecated(since="3.1", forRemoval=true)
    public static <T extends CodeList<T>> T valueOf(final Class<T> codeType, final Filter filter) {
        try {
            final Method method = codeType.getMethod("values", (Class<?>[]) null);
            if (Modifier.isStatic(method.getModifiers())) {
                for (final CodeList<?> code : (CodeList<?>[]) method.invoke(null)) {
                    if (filter.accept(code)) {
                        return codeType.cast(code);
                    }
                }
            }
        } catch (ReflectiveOperationException error) {
            System.getLogger(Errors.LOGGER).log(
                    System.Logger.Level.DEBUG,
                    () -> "Cannot access the " + codeType.getSimpleName() + "values() method.",
                    error);
        }
        final String nameIfNew = filter.codename();
        if (nameIfNew != null) try {
            final Method method = codeType.getMethod("valueOf", String.class);
            return codeType.cast(method.invoke(null, nameIfNew));
        } catch (ReflectiveOperationException exception) {
            throw new IllegalArgumentException("Cannot create code of type " + codeType.getSimpleName(), exception);
        }
        return null;
    }

    /**
     * Returns the given string with only the characters that are valid Unicode identifier parts.
     * In the common case where all characters are Unicode identifier parts, returns {@code name}.
     */
    static String toComparableName(final String name) {
        final int length = name.length();
        int i=0, s, c;
        do {
            if (i >= length) return name;   // Optimization for the most common case.
            c = name.codePointAt(s = i);
            i += Character.charCount(c);
        } while (Character.isUnicodeIdentifierPart(c));

        // Create the buffer only if we found at least one invalid character.
        final var b = new StringBuilder(length).append(name, 0, s);
        while (i < length) {
            c = name.codePointAt(i);
            if (Character.isUnicodeIdentifierPart(c)) {
                b.appendCodePoint(c);
            }
            i += Character.charCount(c);
        }
        return b.toString();
    }

    /**
     * Compares two strings, ignoring case and characters that are not Unicode identifier parts.
     * As an optimization, this method invokes {@link #toComparableName(String)} only if there
     * is some chances that it will make the strings equal.
     *
     * @param  search     the string to search. Must be the result of {@link #toComparableName(String)}.
     * @param  candidate  a string to compare against {@code search}. May contain non-Unicode identifier parts.
     * @return whether the given strings are equal, ignoring case and non-Unicode identifier parts.
     */
    private static boolean compare(final String search, String candidate) {
        if (candidate.length() > search.length()) {
            candidate = toComparableName(candidate);    // May reduce the string length.
        }
        return search.equalsIgnoreCase(candidate);
    }

    /**
     * Returns the code of the given type and name if it exists, or optionally creates a new code.
     * This method returns the first instance (in declaration order) of the given class for which the
     * {@linkplain #name() name} is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case},
     * to the given name. If no such instance is found, then there is a choice:
     *
     * <ul>
     *   <li>If {@code constructor} is {@code null}, then this method returns {@code null}.</li>
     *   <li>Otherwise a new instance is created by a call to {@code constructor.apply(name)}.</li>
     * </ul>
     *
     * This method can be used for the implementation of {@code valueOf(String)} in subclasses.
     * See the "Guidance for subclasses" section in the class Javadoc.
     *
     * <h4>Name matching criterion</h4>
     * As of GeoAPI 3.1, names are compared using {@link String#equalsIgnoreCase(String)},
     * taking in account only the characters that are
     * {@linkplain Character#isUnicodeIdentifierPart(int) unicode identifer part}.
     * This is different than GeoAPI 3.0, which compared names using {@link String#equals(Object)}.
     * Being case-insensitive allows to recognize some <abbr>UML</abbr> identifiers as equivalent
     * to the names of constants declared in {@code CodeList} subclasses.
     *
     * @param  <E>          the type of the code list for which to get an element.
     * @param  values       list of existing values, usually created by {@link #initialValues(CodeList...)}.
     * @param  name         the name of the code to obtain (case-insensitive).
     * @param  constructor  the constructor to use if a new code needs to be created,
     *                      or {@code null} for not creating any new code.
     * @return a code matching the given name, or {@code null} if no existing code matches the given name
     *         and {@code constructor} is null or returned a null value.
     * @throws NullPointerException if {@code values} or {@code name} is null.
     * @throws IllegalArgumentException if the given name does not contain at least
     *         one character that is a Unicode identifier part.
     *
     * @since 3.1
     */
    protected static <E extends CodeList<E>> E valueOf(final List<E> values,
            final String name, final Function<? super String, ? extends E> constructor)
    {
        final String search = toComparableName(name);
        if (search.isBlank()) {
            throw new IllegalArgumentException(name);
        }
        /*
         * The search and, eventually, the code creation are done in the same synchronized
         * block for making sure that the same code is not created twice concurrently.
         */
        synchronized (values) {
            for (E element : values) {
                if (compare(search, element.name())) {
                    return element;
                }
            }
            if (constructor != null) {
                E element = constructor.apply(name);
                if (element != null) {
                    ((CodeList<E>) element).setOrdinal(values.size());
                    if (values.add(element)) {
                        return element;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the list of codes of the same kind as this code.
     * Invoking this method gives identical results than invoking the static {@code values()} methods
     * provided in {@code CodeList} subclasses, except that {@code family()} does not require the class
     * to be known at compile-time — provided that at least one instance of the family is available.
     *
     * @return the codes of the same kind as this code.
     */
    @Override
    public abstract E[] family();
    // We do not provide default implementation because casting to `CodeList<E>` is unsafe.

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] names() {
        final String id = identifier();
        if (id != null && !id.equals(name)) {
            return new String[] {name, id};
        } else {
            return new String[] {name};
        }
    }

    /**
     * Returns the programmatic name of this code list element.
     * If this element is a constant defined in a {@code CodeList} subclass,
     * then this is the name of the public static field for that constant.
     *
     * @return the name of this code constant.
     */
    @Override
    public final String name() {
        return name;
    }

    /**
     * Returns the identifier declared in the <abbr>UML</abbr> annotation, or {@code null} if none.
     * The <abbr>UML</abbr> identifier shall be the <abbr>ISO</abbr> or <abbr>OGC</abbr> name for this code constant.
     *
     * <div class="warning"><b>Upcoming API change</b><br>
     * The return value may be replaced by {@code Optional<String>} in GeoAPI 4.0
     * for reducing the risk of {@code NullPointerException}.
     * </div>
     *
     * @return the <abbr>ISO</abbr>/<abbr>OGC</abbr> identifier for this code, or {@code null} if none.
     *
     * @see UML#identifier()
     */
    public String identifier() {
        /*
         * Save the field in a local variable for protection against concurrent change (this
         * operation is guaranteed atomic according Java specification). We don't synchronize
         * because it is not a problem if this method is executed twice in concurrent threads.
         */
        String id = identifier;
        if (id == null) {
            id = "";
            try {
                final Field field = getClass().getField(name);
                if (Modifier.isStatic(field.getModifiers())) {
                    boolean valid;
                    try {
                        valid = equals(field.get(null));
                    } catch (IllegalAccessException error) {
                        /*
                         * Should never happen with accessible packages because `getField(String)` returns
                         * only public fields. However, it may happen if the code list is defined in a user
                         * module and that module does not export the package containing the code list.
                         * In such case, we have to trust the name provided in this code list instance.
                         */
                        valid = true;
                        cannotReadField(System.Logger.Level.DEBUG, error);
                    }
                    if (valid) {
                        // Fetching annotations is allowed even with non-exported packages.
                        final UML annotation = field.getAnnotation(UML.class);
                        if (annotation != null) {
                            id = annotation.identifier().intern();
                        }
                    }
                }
            } catch (NoSuchFieldException error) {
                /*
                 * There is no field for a code of this name. It may be normal, because the user
                 * may have created a custom `CodeList` without declaring it as a constant.
                 */
                cannotReadField(System.Logger.Level.TRACE, error);
            }
            identifier = id;
        }
        return id.isEmpty() ? null : id;
    }

    /**
     * Logs a message for a field that cannot be read by reflection.
     *
     * @param  level  the level at which to log the message.
     * @param  error  the exception that occurred.
     */
    private void cannotReadField(System.Logger.Level level, ReflectiveOperationException error) {
        System.getLogger(Errors.LOGGER).log(
                level,
                () -> "Cannot read the " + getClass().getSimpleName() + '.' + name() + " field value.",
                error);
    }

    /**
     * Returns the ordinal of this code element. This is the index of this element in the array returned
     * by {@link #family()}. Those elements are in the order of the constants declared in subclasses,
     * where the first element is assigned an ordinal value of zero.
     *
     * <h4>Stability</h4>
     * For a given GeoAPI version,
     * the code list elements declared as constants in subclasses always have the same ordinal values.
     * However, the ordinal value of the same constant may differ between different GeoAPI versions
     * if new elements were inserted or deleted between existing elements.
     * User-defined elements (created by calls to a {@code valueOf(…)} method) are not guaranteed
     * to have the same ordinal value between different execution of the same application,
     * because these values depend on the order in which code list elements are created.
     *
     * @return the position of this code in elements declaration.
     */
    @Override
    public final int ordinal() {
        if (ordinal >= 0) return ordinal;
        throw new IllegalStateException("Not yet initialized.");
    }

    /**
     * Compares this code with the specified object for order.
     * Returns a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     *
     * <p>Code list constants are only comparable to other code list constants of the same type.
     * The natural order implemented by this method is the order in which the constants are declared.</p>
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
            throw new ClassCastException("Cannot compare " + ct.getSimpleName() + " to " + co.getSimpleName() + '.');
        }
        return Integer.compare(ordinal(), other.ordinal());
    }

    /*
     * Do not define `equals(Object)` and `hashCode()`. The identity comparison is consistent with above
     * `compareTo(E)` method because there is no two CodeLists of the same class having the same ordinal value.
     */

    /**
     * Returns a string representation of this code list value.
     *
     * @return a string representation of this code list value.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + '.' + name;
    }

    /**
     * Resolves the code list to an unique instance after deserialization.
     * The instance shall be resolved using its {@linkplain #name() name}, not its {@linkplain #ordinal() ordinal},
     * because the latter value is not guaranteed to be stable between different GeoAPI versions or,
     * in the case of user-defined code list elements, between different <abbr>JVM</abbr> executions.
     *
     * <p>The default implementation tries to replace the deserialized instance by the result of
     * a call to the public static method {@code valueOf(name)} on the class of the code list.</p>
     *
     * @return either {@code this} or an existing code list for the same name (ignoring case).
     * @throws ObjectStreamException if the deserialization failed.
     */
    protected Object readResolve() throws ObjectStreamException {
        ordinal = -1;
        try {
            final Method method = getClass().getMethod("valueOf", String.class);
            if (Modifier.isStatic(method.getModifiers())) {
                return method.invoke(null, name);
            }
        } catch (ReflectiveOperationException error) {
            System.getLogger(Errors.LOGGER).log(
                    System.Logger.Level.DEBUG,
                    () -> "Cannot access the " + getClass().getSimpleName() + "valueOf(String) method.",
                    error);
        }
        return this;
    }
}
