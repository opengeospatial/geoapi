/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.bridge.python;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Collections;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.nio.charset.StandardCharsets;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.jpy.PyObject;


/**
 * A code list specifying how a Python object should be interfaced to a Java implementation
 * of a given interface. There is two-predefined modes: {@link #GEOAPI} and {@link #DEFAULT}.
 * Users can define different modes if they override the {@link #toJava(PyObject, Class)} method.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 *
 * @see Environment#getInterfacing(Class)
 *
 * @since 4.0
 */
public abstract class Interfacing extends CodeList<Interfacing> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -869703977561151644L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Interfacing> VALUES = new ArrayList<>(2);

    /**
     * The mapping between Java and Python uses the GeoAPI specific rules.
     * Those rules are:
     *
     * <ul>
     *   <li>For any Java method, the name of the corresponding Python attribute is given
     *       by the {@link org.opengis.annotation.UML} annotation associated to the method,
     *       converted from camel-case to snake-case. If a method has no UML annotation,
     *       then its Java name is used as a fallback.</li>
     *   <li>GeoAPI-specific property types are supported ({@link org.opengis.util.CodeList}
     *       and {@link InternationalString}) in addition of some Java standard types like
     *       {@link Enum} and {@link java.util.Collection}.</li>
     * </ul>
     */
    public static final Interfacing GEOAPI = new GeoAPI("GEOAPI");

    /**
     * The mapping between Java and Python is delegated to the underlying JPY library.
     */
    public static final Interfacing DEFAULT = new Default("DEFAULT");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    protected Interfacing(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns {@code true} if the given Java interface may potentially have sub-interfaces recognized by
     * {@link #toJavaType(PyObject)}. This is an optimization for avoiding potentially costly checks.
     * In case of doubt, conservatively returns {@code true}.
     *
     * <p>The default implementation returns {@code false} for consistency with {@link #toJavaType(PyObject)}
     * default implementation. This method should be overridden if {@code toJavaType(PyObject)} is overridden.</p>
     *
     * @param  type  the Java interface to check for sub-typing.
     * @return {@code true} if the given interface may have sub-interfaces known to {@link #toJavaType(PyObject)}.
     */
    protected boolean hasKnownSubtypes(Class<?> type) {
        return false;
    }

    /**
     * Returns the Java type for the given Python type, taking in account only types assignable to the given base.
     * Caller should have verified that {@link #hasKnownSubtypes(Class)} returns {@code true} before to invoke this
     * method.
     *
     * @param  <T>       compile-time value of the {@code base} argument.
     * @param  base      the base type of the desired interface.
     * @param  object    the Python object for which to get the Java type.
     * @param  builtins  the value of {@link Environment#builtins}.
     * @return the Python object type as a type assignable to {@code base}. May be {@code base} itself.
     *
     * @see Environment#getJavaType(Class, PyObject)
     */
    final <T> Class<? extends T> getJavaType(final Class<T> base, final PyObject object, final PyObject builtins) {
        final Class<?> c = specialize(base, builtins.call("type", object));
        return (c != null) ? c.asSubclass(base) : base;
    }

    /**
     * Returns the Java type for the given Python type, or {@code null} if unknown.
     * This method check only the given type. In case of unrecognized type, it does
     * not verify if a parent of the given type would be recognized.
     *
     * <p>The default implementation returns {@code null}. If this method is overridden,
     * then {@link #hasKnownSubtypes(Class)} may need to be overridden too.</p>
     *
     * @param  type  the Python type as given by {@code type(object)} in Python.
     * @return the Java type for the given Python type, or {@code null} if unknown.
     *
     * @see Environment#getJavaType(Class, PyObject)
     */
    protected Class<?> toJavaType(final PyObject type) {
        return null;
    }

    /**
     * Represents the given Python object as a Java object of the given type.
     * This method is invoked for user-provided {@code Interfacing} subclasses,
     * i.e. if {@link Environment#getInterfacing(Class)} returns a value other
     * then {@link #GEOAPI} or {@link #DEFAULT}.
     *
     * @param  <T>     compile-time value of the {@code type} argument.
     * @param  object  the Python object to wrap in a Java object.
     * @param  type    interface to be implemented by the desired Java wrapper.
     * @return the given Python object as a Java instance of the given type.
     * @throws UnconvertibleTypeException if this method does not know how to convert Python objects to the given type.
     *
     * @see Environment#toJava(PyObject, Class)
     */
    protected abstract <T> T toJava(final PyObject object, final Class<T> type);

    /**
     * Returns the list of {@code Interfacing}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Interfacing[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(Interfacing[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public Interfacing[] family() {
        return values();
    }

    /**
     * Returns the interfacing code that matches the given string.
     * More specifically, this methods returns the first instance for which
     * <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code> returns {@code true}.
     * If no existing instance is found, then an exception is thrown.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     * @throws IllegalArgumentException if there is no code for the given name.
     */
    public static Interfacing valueOf(final String code) {
        final Interfacing c = valueOf(Interfacing.class, code);
        if (c != null) {
            return c;
        } else {
            throw new IllegalArgumentException("No interfacing named " + code);
        }
    }

    /**
     * An {@link Interfacing} implementation for the {@link #DEFAULT} built-in types.
     * This implementation does not support {@link #toJava(PyObject, Class)} since
     * those conversions are handled in a special way by {@link Converter}.
     */
    private static final class Default extends Interfacing {
        private static final long serialVersionUID = 2492683726885765988L;

        Default(final String name) {
            super(name);
        }

        @Override
        protected Object readResolve() throws ObjectStreamException {
            return DEFAULT;
        }

        @Override
        protected <T> T toJava(PyObject object, Class<T> type) {
            throw new UnconvertibleTypeException(type);
        }
    }

    /**
     * An {@link Interfacing} implementation for the {@link #GEOAPI} built-in types.
     * Only one instance shall exist in a running JVM.
     */
    static final class GeoAPI extends Interfacing {
        private static final long serialVersionUID = -869703977561151644L;

        /**
         * The prefix of Java class names handled by {@code GeoAPI}.
         */
        static final String JAVA_PREFIX = "org.opengis.";

        /**
         * The prefix of Python class names handled by {@code GeoAPI}.
         */
        static final String PYTHON_PREFIX = "opengis.";

        /**
         * The file in the {@link UML} package containing the list of all GeoAPI interfaces.
         * This is used for populating {@link #typesForNames}.
         */
        static final String CLASS_LIST = "class-index.properties";

        /**
         * The file in the {@link GeoAPI} package containing the list of all GeoAPI interfaces
         * having subclasses. This is used for populating {@link #subclassed}.
         */
        static final String SUBCLASSED_LIST = "subclassed.txt";

        /**
         * Initial capacity for the {@link #typesForNames} map, as the number of lines in the
         * {@value #CLASS_LIST} file divided by 0.75.
         */
        static final int CLASS_CAPACITY = 288;

        /**
         * Initial capacity for the {@link #subclassed} set, as the number of lines in the
         * {@value #SUBCLASSED_LIST} file divided by 0.75.
         */
        static final int SUBCLASSED_CAPACITY = 46;

        /**
         * The Java classes for given Python type names. The content of this map is derived
         * from the content of the {@code "class-index.properties"} file distributed with GeoAPI.
         * This map shall not be modified after construction for thread-safety reasons.
         */
        private transient final Map<String,String> typesForNames;

        /**
         * The interfaces from the {@link #typesForNames} entries which have at least one sub-type.
         * Used for determining if it is worth to perform the relatively costly detection of the
         * subtype implemented by a Python object.
         */
        private transient final Set<Class<?>> subclassed;

        /** For JUnit tests only. */
        final Map<String,String> typesForNames() {
            return Collections.unmodifiableMap(typesForNames);
        }

        /** For JUnit tests only. */
        final Set<Class<?>> subclassed() {
            return Collections.unmodifiableSet(subclassed);
        }

        /** Deprecated types to exclude for avoiding name collision. */
        final Set<String> excludes() {
            final Set<String> excludes = new HashSet<>(4);
            excludes.add("RS_Identifier");
            excludes.add("DQ_Scope");
            return excludes;
        }

        /**
         * Returns the unique instance on deserialization.
         */
        @Override
        protected Object readResolve() throws ObjectStreamException {
            return GEOAPI;
        }

        /**
         * Creates the {@link #GEOAPI} singleton.
         */
        GeoAPI(final String name) {
            super(name);
            /*
             * Load the list of all classes without creating the Class instead yet,
             * except for resolving ambiguities. The amount of classes is potentially
             * large and only a small amount of it is typically used.
             */
            final Properties p = new Properties(CLASS_CAPACITY + 14);
            try (InputStream in = UML.class.getResourceAsStream(CLASS_LIST)) {
                p.load(in);
            } catch (NullPointerException | IOException e) {
                throw (Error) new ExceptionInInitializerError(error(CLASS_LIST, true)).initCause(e);
            }
            final Set<String> excludes = excludes();
            typesForNames = new HashMap<>(CLASS_CAPACITY);
            for (final Map.Entry<Object,Object> e : p.entrySet()) {
                String type = (String) e.getKey();
                if (!excludes.contains(type)) {
                    type = type.substring(type.indexOf('_') + 1).intern();
                    typesForNames.put(type, ((String) e.getValue()).intern());
                }
            }
            /*
             * Load the list of classes that may have sub-types. We convert the String
             * into Class instances here because the amount of classes is smaller.
             */
            subclassed = new HashSet<>(SUBCLASSED_CAPACITY);
            final ClassLoader loader = UML.class.getClassLoader();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    GeoAPI.class.getResourceAsStream(SUBCLASSED_LIST), StandardCharsets.UTF_8)))
            {
                String line;
                while ((line = in.readLine()) != null) {
                    subclassed.add(Class.forName(line, false, loader));
                }
            } catch (NullPointerException | IOException | ClassNotFoundException e) {
                throw (Error) new ExceptionInInitializerError(error(SUBCLASSED_LIST, true)).initCause(e);
            }
        }

        /**
         * Builds the error message for a file that we cannot load or use.
         */
        private static String error(final String filename, final boolean loading) {
            return (loading ? "Cannot load the \"" : "Outdated \"") + filename + "\" resource.";
        }

        /**
         * Returns {@code true} if the given Java interface may potentially have sub-interfaces recognized by
         * {@link #toJavaType(PyObject)}. This is an optimization for avoiding potentially costly checks.
         */
        @Override
        protected boolean hasKnownSubtypes(final Class<?> type) {
            return subclassed.contains(type);
        }

        /**
         * Not invoked. {@link Converter#apply(PyObject)} uses a special hook for the GeoAPI case.
         */
        @Override
        protected <T> T toJava(PyObject object, Class<T> type) {
            throw new UnconvertibleTypeException(type);
        }

        /**
         * Returns the Java type for the given Python type, or {@code null} if the given type
         * is not one of the types defined by the {@code opengis} Python package.
         * This method does not verify if a parent type could be returned.
         *
         * @param  type  the Python type as given by {@code type(object)} in Python.
         * @return the Java type for the given Python type, or {@code null} if none.
         */
        @Override
        protected Class<?> toJavaType(final PyObject type) {
            if (type != null) {
                PyObject obj = type.getAttribute("__module__");
                if (obj != null) {
                    final String module = obj.getStringValue();
                    if (module != null && module.startsWith(PYTHON_PREFIX)) {
                        obj = type.getAttribute("__name__");
                        if (obj != null) {
                            final String name = typesForNames.get(obj.getStringValue());
                            if (name != null) try {
                                return Class.forName(name, false, UML.class.getClassLoader());
                            } catch (ClassNotFoundException e) {
                                throw new EnvironmentException(error(CLASS_LIST, false), e);
                            }
                        }
                    }
                }
            }
            return null;
        }
    }

    /**
     * Returns the Java type for the given Python type, taking in account only types assignable to the given base.
     *
     * @param  base  the base type of the desired interface.
     * @param  type  the Python type, as returned by {@code type(object)} in Python.
     * @return an interface assignable to {@code base}, or {@code null} if none.
     *
     * @see #getJavaType(Class, PyObject, PyObject)
     */
    private Class<?> specialize(final Class<?> base, final PyObject type) {
        if (type != null) {
            final PyObject bases = type.getAttribute("__bases__");
            if (bases != null) {
                final int n = bases.callMethod("__len__").getIntValue();
                for (int i=0; i<n; i++) {
                    final PyObject parent = bases.callMethod("__getitem__", i);
                    Class<?> c = toJavaType(parent);
                    if (c == null) {
                        c = specialize(base, parent);
                        if (c != null) return c;
                    } else if (base.isAssignableFrom(c)) {
                        return c;
                    } else {
                        /*
                         * Do not search the parent if the class that we found is not assignable to base.
                         * If 'c' is not a subtype of 'base', its parents will not be subtypes neither.
                         */
                    }
                }
            }
        }
        return null;
    }
}
