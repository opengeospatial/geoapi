/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.bridge.python;

import java.util.List;
import java.util.Objects;
import java.util.Collections;
import org.jpy.PyModule;
import org.jpy.PyObject;


/**
 * Interfaces Java applications with an environment in which a Python interpreter is running.
 * Only one instance of {@code Environment} is needed.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 */
public class Environment {
    /**
     * Accessor to Python built-in functions.
     * Used for {@code len(collection)}, {@code iter(collection)}, {@code next(collection)} and {@code str(object)}.
     */
    final PyObject builtins;

    /**
     * Creates a new environment with default configuration.
     * A Python interpreter must be available at the time this constructor is invoked.
     */
    public Environment() {
        builtins = PyModule.getBuiltins();
    }

    /**
     * Represents the given Python object as a Java object of the given type.
     * The given {@code type} argument can be any of the following:
     *
     * <ul>
     *   <li>A {@link Double}, {@link Integer} or {@link Boolean}.</li>
     *   <li>A {@link CharSequence}, {@link String} or {@link InternationalString}.</li>
     *   <li>An enumeration such as {@link org.opengis.annotation.Obligation}.</li>
     *   <li>A code list such as {@link org.opengis.metadata.Datatype}.</li>
     *   <li>A GeoAPI interface (not an implementation class) such as {@link org.opengis.metadata.Metadata}.</li>
     *   <li>A non-GeoAPI interface such as {@link java.util.function.Supplier}.</li>
     * </ul>
     *
     * GeoAPI and non-GeoAPI interfaces are handled differently; see {@link #getInterfacing(Class)} for details.
     *
     * @param  <T>     compile-time value of the {@code type} argument.
     * @param  object  the Python object to wrap in a Java object, or {@code null}.
     * @param  type    interface to be implemented by the desired Java wrapper.
     * @return the given Python object as a Java instance of the given type, or {@code null} if the given object was null.
     * @throws UnconvertibleTypeException if this method does not know how to convert Python objects to the given type.
     */
    public <T> T toJava(final PyObject object, final Class<T> type) throws UnconvertibleTypeException {
        Objects.requireNonNull(type);
        if (object == null) {
            return null;
        }
        if (Iterable.class.isAssignableFrom(type)) {
            throw new UnconvertibleTypeException("Can not convert to a collection. Use the asList method instead.");
        } else {
            return Converter.verifiedInstance(this, type).apply(object);
        }
    }

    /**
     * Represents the given Python sequence as a read-only Java list containing elements of the given type.
     * The given {@code type} argument can be the same than the ones accepted by {@link #toJava(PyObject, Class)}.
     *
     * @param  <E>     compile-time value of the {@code type} argument.
     * @param  object  the Python sequence to represent as a Java list, or {@code null} for an empty list.
     * @param  type    interface to be implemented by the Java elements in the list.
     * @return the given Python sequence as a Java list with elements of the given type.
     * @throws UnconvertibleTypeException if this method does not know how to convert Python objects to the given type.
     */
    public <E> List<E> asList(final PyObject object, final Class<E> type) throws UnconvertibleTypeException {
        Objects.requireNonNull(type);
        if (object != null) {
            return new Sequence<>(this, type, object);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Returns the Java type for the given Python object. This method assumes that the Java type for the given
     * Python object is at least the {@code base} type, but it may also be a subtype of {@code base}.
     *
     * @param  <T>       compile-time value of the {@code base} argument.
     * @param  base      the base type of the desired interface.
     * @param  object    the Python object for which to get the Java type, or {@code null}.
     * @return the Python object type as a type assignable to {@code base}. May be {@code base} itself.
     */
    public <T> Class<? extends T> getJavaType(final Class<T> base, final PyObject object) {
        Objects.requireNonNull(base);
        if (object != null) {
            final Interfacing inf = getInterfacing(base);
            if (inf.hasKnownSubtypes(base)) {
                return inf.getJavaType(base, object, builtins);
            }
        }
        return base;
    }

    /**
     * Specifies how the Java methods in the given interface should be mapped to Python methods or attributes.
     * There is two main interfacing modes supported by default: if this method returns {@link Interfacing#GEOAPI},
     * then the mapping between Java and Python uses the following rules:
     *
     * <ul>
     *   <li>For any Java method, the name of the corresponding Python attribute is given
     *       by the {@link org.opengis.annotation.UML} annotation associated to the method.
     *       If a method has no such annotation, then its name is used as a fallback.</li>
     *   <li>GeoAPI-specific property types are supported ({@link org.opengis.util.CodeList}
     *       and {@link InternationalString}) in addition of some Java standard types like
     *       {@link Enum} and {@link java.util.Collection}.</li>
     * </ul>
     *
     * If this method returns {@link Interfacing#DEFAULT},
     * then the Python-Java mapping is delegated to the underlying JPY library.
     * If this method returns another value, then the behavior is defined by
     * {@link Interfacing#toJava(PyObject, Class)}.
     *
     * @param  type  the Java type for which to determine the interfacing mode.
     * @return a specification of how to interface Java methods to Python.
     */
    protected Interfacing getInterfacing(final Class<?> type) {
        return type.getPackageName().startsWith(Interfacing.GeoAPI.JAVA_PREFIX) ? Interfacing.GEOAPI : Interfacing.DEFAULT;
    }
}
