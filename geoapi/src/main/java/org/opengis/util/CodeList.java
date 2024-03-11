/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;
import org.opengis.geoapi.internal.Vocabulary;
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
 * In addition, subclasses <em>should</em> provide {@code values(String)} and {@code values()} methods.
 * The following code snippet is a suggested pattern for subclasses:
 *
 * {@snippet lang="java" :
 * public final class Foo extends CodeList<Foo> {
 *     public static final Foo BAR = new Foo("BAR");
 *     public static final Foo BIZ = new Foo("BIZ");
 *
 *     private Foo(String name) {
 *         super(name);
 *     }
 *
 *     public static Foo valueOf(String name) {
 *         return valueOf(Foo.class, name, Foo::new).get();
 *     }
 *
 *     public static Foo[] values() {
 *         return values(Foo.class);
 *     }
 *
 *     @Override
 *     public Foo[] family() {
 *         return values();
 *     }
 * }
 * }
 *
 * Above snippet is sufficient for classes in exported packages. If a code list is defined in a non-exported package,
 * then its static fields may need to be initialized with {@code valueOf(String)} instead of {@code new Foo(String)}.
 * It will have a small performance cost, but is needed because of Java Module System restrictions on reflection.
 *
 * @param  <E>  the type of this code list.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
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
     * Map keys are the {@code CodeList} classes for which elements are listed, and map values are those elements.
     * Each code list element shall be stored in the list at the index corresponding to its {@link #ordinal} value.
     * Elements are stored as {@code CodeList} instances if available, or by their {@code String} names otherwise.
     * Names are sometime used instead of instances because assigning {@code this} at construction time is unsafe.
     * Those names will be replaced by the actual instances by the {@code valueOf(String)} method or by reflection.
     *
     * <h4>Class unloading</h4>
     * The use of {@link WeakHashMap} allows class unloading, but can work only as long as the list
     * associated to a class contains only {@code String} elements. After storing at least one instance,
     * the indirect references to the class prevent the {@code Class} key from being garbage-collected.
     *
     * @see #values(Class)
     */
    private static final Map<Class<? extends CodeList<?>>, Elements> VALUES = new WeakHashMap<>();

    /**
     * Elements stored in the {@link #VALUES} map for a given {@code CodeList} class.
     * This list contains {@link CodeList} instances when they are fully constructed,
     * or only their names when storing the instance would be unsafe ("this-escape").
     * The {@link #get(int)} method may return a {@link String} or a {@link CodeList}.
     * If a {@code CodeList} is desired, use {@link #resolve(Class, int)} instead.
     */
    @SuppressWarnings("serial")     // Not intended to be serialized.
    private static final class Elements extends ArrayList<Object> {
        /**
         * Whether all code list names in this list have been replaced by the actual instances.
         * This is used for faster {@link #toArray(Class)} execution when there is no longer a
         * need to check the type of each element.
         *
         * @see #toArray(Class)
         */
        boolean resolved;

        /**
         * Creates a new, initially empty, list.
         *
         * @param  type  the {@link #VALUES} map key to which this list will be associated.
         */
        Elements(final Class<? extends CodeList<?>> type) {
            super(capacity(type));
        }

        /** Workaround while waiting for JEP 447: Statements before super(…). */
        private static int capacity(final Class<? extends CodeList<?>> type) {
            var desc = type.getAnnotation(Vocabulary.class);
            return (desc != null) ? desc.capacity() : 8;        // Code lists typically have few elements.
        }

        /**
         * Returns the element at the given index as a code list element of the specified class.
         * Callers must invoke this method in a block synchronized on {@code this} list.
         *
         * @param  <E>       the compile-time type given as the {@code codeType} parameter.
         * @param  codeType  the type of the code list for which to get an element.
         * @param  index     index of the element to get.
         * @return element at the specified index.
         * @throws InaccessibleObjectException if the element needs to be resolved by reflection and this operation failed.
         *         A failure is generally caused by an error in the declaration of static fields in the code list.
         * @throws IllegalStateException if the code list is inconsistent (e.g., element having wrong index).
         */
        final <E extends CodeList<E>> E resolve(final Class<E> codeType, final int index) {
            final E element;
            final Object code = get(index);
            if (code instanceof String) try {
                final Field field;
                if (codeType.isAnnotationPresent(Vocabulary.class)) {   // True if the code list is a GeoAPI class.
                    field = codeType.getDeclaredField((String) code);
                    field.setAccessible(true);
                } else {
                    field = codeType.getField((String) code);           // Be more conservative for user classes.
                }
                element = codeType.cast(field.get(null));
                if (element.ordinal() == index && element.name().equals(code)) {
                    set(index, element);
                } else {
                    throw new IllegalStateException("Conflict between " + element + " and " + code + '.');
                }
            } catch (ReflectiveOperationException | NullPointerException | ClassCastException e) {
                /*
                 * The search by reflection may fail with:
                 * - IllegalAccessException  if the package containing `codeType` is not exported,
                 * - NoSuchFieldException    if the field does not exist,
                 * - NullPointerException    if the field is not static, or
                 * - ClassCastException      if the field value is not an instance of <E>.
                 */
                throw (InaccessibleObjectException) new InaccessibleObjectException(cannotRead(codeType, code)).initCause(e);
            } else {
                element = codeType.cast(code);
            }
            return element;
        }

        /**
         * Produces the message for a field that cannot be read by reflection.
         * Used for exception messages or for log record messages.
         *
         * @param  codeType  the type of the code list.
         * @param  code      the code that cannot be read.
         * @return the message to use in exception or in log record.
         */
        static String cannotRead(final Class<?> codeType, final Object code) {
            return "Cannot read the " + codeType.getSimpleName() + '.' + code + " field value.";
        }

        /**
         * Returns all elements in this list.
         *
         * @param  <E>       the compile-time type given as the {@code codeType} parameter.
         * @param  codeType  the type of the code list for which to get all elements.
         * @return all elements that are known at the time that this method is invoked.
         * @throws InaccessibleObjectException if an element needs to be resolved by reflection and this operation failed.
         *         A failure is generally caused by an error in the declaration of static fields in the code list.
         */
        final synchronized <E extends CodeList<E>> E[] toArray(final Class<E> codeType) {
            @SuppressWarnings("unchecked")
            final E[] array = (E[]) Array.newInstance(codeType, size());
            if (resolved) {
                return toArray(array);
            }
            for (int i=0; i<array.length; i++) {
                array[i] = resolve(codeType, i);
            }
            resolved = true;
            return array;
        }
    }

    /**
     * The code value as a sequential number incremented in the order in which codes are created.
     * This value is set at construction time and should be considered final. It is potentially
     * modified only during deserialization, because the Java serialization mechanism bypasses
     * the constructor.
     *
     * @see #ordinal()
     * @see #readResolve()
     */
    private transient int ordinal;

    /**
     * The code name. If this {@code CodeList} instance is stored in a static field
     * of the code list class, then this name shall be identical to the field name.
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
     * Creates a new code list element. If this constructor is invoked for initializing a static field,
     * then the given name <em>shall</em> be the case-sensitive name of the static field.
     *
     * @param  name  the code name. Shall be the name of the static field if such field exist.
     */
    @SuppressWarnings("unchecked")
    protected CodeList(final String name) {
        this.name = name;
        Class<?> type = getClass();
        while (type.isAnonymousClass()) {
            type = type.getSuperclass();
            if (type.equals(CodeList.class)) {
                throw new IllegalStateException("Class shall not be anonymous.");
            }
        }
        final Elements values = getOrCreateList((Class<? extends CodeList<?>>) type);
        synchronized (values) {
            ordinal = values.size();
            values.add(name);           // Needed for incrementing the list size.
            values.resolved = false;
        }
    }

    /**
     * Returns the list associated to the specified type of code list, creating the list if needed.
     * This method should be invoked only when the {@code codeType} class is known to be initialized.
     * If the class may be uninitialized, use {@link #valueList(Class)} instead.
     *
     * @param  codeType  the type of code list for which to get the code list values.
     * @return all values for the given type. Never {@code null} but potentially empty.
     */
    private static Elements getOrCreateList(final Class<? extends CodeList<?>> codeType) {
        synchronized (VALUES) {
            return VALUES.computeIfAbsent(codeType, Elements::new);
        }
    }

    /**
     * Returns the code of the given type and name if it exists, or optionally creates a new code.
     * This method returns the first instance (in declaration order) of the given class for which the
     * {@linkplain #name() name} is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case},
     * to the given name. If no such instance is found, then there is a choice:
     *
     * <ul>
     *   <li>If {@code constructor} is {@code null}, then this method returns an empty value.</li>
     *   <li>Otherwise a {@linkplain Optional#ofNullable(Object) nullable} new instance is created
     *       by a call to {@code constructor.apply(name)}.</li>
     * </ul>
     *
     * This method can be used for the implementation of {@code valueOf(String)} in subclasses.
     * See the "Guidance for subclasses" section in the class Javadoc.
     *
     * <h4>Name matching criterion</h4>
     * As of GeoAPI 3.1, names are compared using {@link String#equalsIgnoreCase(String)}.
     * This is different than GeoAPI 3.0, which compared names using {@link String#equals(Object)}.
     * Being case-insensitive allows to recognize some UML identifiers as equivalent to the names
     * of constants declared in {@code CodeList} subclasses.
     *
     * @param  <E>          the compile-time type given as the {@code codeType} parameter.
     * @param  codeType     the type of the code list for which to get an element.
     * @param  name         the name of the code to obtain (case-insensitive), or {@code null}.
     * @param  constructor  the constructor to use if a new code needs to be created,
     *                      or {@code null} for not creating any new code.
     * @return a code matching the given name, or empty if the given name is null or blank,
     *         or if no existing code matches the given name and {@code constructor} is null or returned a null value.
     *
     * @since 3.1
     */
    public static <E extends CodeList<E>> Optional<E> valueOf(final Class<E> codeType,
            String name, final Function<? super String, ? extends E> constructor)
    {
        if (name != null && !(name = name.trim()).isBlank()) {
            final Elements values = valueList(codeType);
            /*
             * The search and, eventually, the code creation are done in the same synchronized
             * block for making sure that the same code is not created twice concurrently.
             */
            synchronized (values) {
                final int size = values.size();
                for (int i=0; i<size; i++) {
                    final E element;
                    final Object code = values.get(i);
                    if (code instanceof String) {
                        if (!name.equalsIgnoreCase((String) code)) continue;
                        element = values.resolve(codeType, i);
                    } else {
                        element = codeType.cast(code);
                    }
                    if (name.equalsIgnoreCase(element.name())) {
                        return Optional.of(element);
                    }
                }
                if (constructor != null) {
                    final E element = constructor.apply(name);
                    if (element != null) {
                        values.set(element.ordinal(), element);
                        return Optional.of(element);
                    }
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the list of code list names or instances for the specified type of code list.
     * Callers shall use the returned list in a block synchronized on the list instance.
     *
     * @param  codeType  the type of code list for which to get the current code list values.
     * @return all current values for the given type (never {@code null}).
     */
    private static Elements valueList(final Class<? extends CodeList<?>> codeType) {
        Elements values;
        synchronized (VALUES) {
            values = VALUES.get(codeType);
        }
        if (values == null) {
            /*
             * If no list has been found for the given type, maybe the class was not yet initialized.
             * Try to force initialization of the given class in order to register its list of static
             * final constants, then check again.
             */
            final String classname = Objects.requireNonNull(codeType, "The codeType argument shall not be null.").getName();
            try {
                Class.forName(classname, true, codeType.getClassLoader());
            } catch (ClassNotFoundException e) {
                throw new TypeNotPresentException(classname, e);             // Should never happen.
            }
            values = getOrCreateList(codeType);
        }
        return values;
    }

    /**
     * Returns the values for the specified type of code list.
     *
     * @param  <E>       the compile-time type given as the {@code codeType} parameter.
     * @param  codeType  the type of code list for which to get the current code list values.
     * @return all current values for the given type (never {@code null}).
     *
     * @since 3.1
     */
    @SuppressWarnings("unchecked")
    public static <E extends CodeList<E>> E[] values(final Class<E> codeType) {
        return valueList(codeType).toArray(codeType);
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
     * Returns the identifier declared in the UML annotation, or {@code null} if none.
     * The UML identifier shall be the ISO or OGC name for this code constant.
     *
     * @return the ISO/OGC identifier for this code, or {@code null} if none.
     *
     * @see UML#identifier()
     */
    @Override
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
                    } catch (IllegalAccessException e) {
                        /*
                         * Should never happen with accessible packages because `getField(String)` returns
                         * only public fields. However, it may happen if the code list is defined in a user
                         * module and that module does not export the package containing the code list.
                         * In such case, we have to trust the name provided in this code list instance.
                         */
                        valid = true;
                        System.getLogger(Errors.LOGGER).log(System.Logger.Level.DEBUG, this::cannotReadField, e);
                    }
                    if (valid) {
                        // Fetching annotations is allowed even with non-exported packages.
                        final UML annotation = field.getAnnotation(UML.class);
                        if (annotation != null) {
                            id = annotation.identifier().intern();
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
                /*
                 * There is no field for a code of this name. It may be normal, because the user
                 * may have created a custom `CodeList` without declaring it as a constant.
                 */
                System.getLogger(Errors.LOGGER).log(System.Logger.Level.TRACE, this::cannotReadField, e);
            }
            identifier = id;
        }
        return id.isEmpty() ? null : id;
    }

    /**
     * Produces the message for a field that cannot be read by reflection.
     * This is used for logging purposes.
     */
    private String cannotReadField() {
        return Elements.cannotRead(getClass(), name);
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
        return ordinal;
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
        return Integer.compare(ordinal, other.ordinal());
    }

    /*
     * Do not define `equals(Object)` and `hashCode()`. The identity comparison is consistent with above
     * `compareTo(E)` method because there is no two CodeLists of the same class having the same ordinal value.
     */

    /**
     * {@return a string representation of this code list}.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + '.' + name;
    }

    /**
     * Resolves the code list to an unique instance after deserialization.
     * The instance shall be resolved using its {@linkplain #name() name}, not its {@linkplain #ordinal() ordinal},
     * because the latter value is not guaranteed to be stable between different GeoAPI versions or,
     * in the case of user-defined code list elements, between different JVM executions.
     *
     * <p>The default implementation tries to replace the deserialized instance by the result of
     * <code>{@link #valueOf(Class, String, Function) valueOf}(getClass(), name(), null)</code>.
     * If {@code valueOf(…)} returned an empty value, then {@code this} is updated with a new
     * ordinal value and added to the list of codes associated to its class.</p>
     *
     * @return either {@code this} or an existing code list for the same name (ignoring case).
     * @throws ObjectStreamException if the deserialization failed.
     */
    protected Object readResolve() throws ObjectStreamException {
        /*
         * Casted to <E> in order to satisfy the compiler, but this code should be safe
         * even if the class is not really `Class<E>` because this method returns `Object`.
         */
        @SuppressWarnings("unchecked")
        final Class<E> codeType = (Class<E>) getClass();
        final E element = valueOf(codeType, name, null).orElse(null);
        if (element != null) {
            return element;
        }
        final Elements values = getOrCreateList(codeType);
        synchronized (values) {
            ordinal = values.size();
            values.add(this);
        }
        return this;
    }
}
