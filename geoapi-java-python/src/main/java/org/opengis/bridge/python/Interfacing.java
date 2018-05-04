/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018 Open Geospatial Consortium, Inc.
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
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.jpy.PyObject;


/**
 * A code list specifying how a Python object should be interfaced to a Java of a given interface.
 * There is two-predefined modes: {@link #GEOAPI} and {@link #DEFAULT}.
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
     * The mapping between Java and Python uses the following rules:
     *
     * <ul>
     *   <li>For any Java method, the name of the corresponding Python attribute is given
     *       by the {@link org.opengis.annotation.UML} annotation associated to the method.
     *       If a method has no such annotation, then its name is used as a fallback.</li>
     *   <li>GeoAPI-specific property types are supported ({@link org.opengis.util.CodeList}
     *       and {@link InternationalString}) in addition of some Java standard types like
     *       {@link Enum} and {@link java.util.Collection}.</li>
     * </ul>
     */
    public static final Interfacing GEOAPI = new BuiltIn("GEOAPI");

    /**
     * The mapping between Java and Python is delegated to the underlying JPY library.
     */
    public static final Interfacing DEFAULT = new BuiltIn("DEFAULT");

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
     * An {@link Interfacing} implementation for the {@link #GEOAPI} and {@link #DEFAULT} built-in types.
     * This implementation does not support {@link #toJava(PyObject, Class)} since those conversions are
     * handled in a special way by {@link Converter}.
     */
    private static final class BuiltIn extends Interfacing {
        private static final long serialVersionUID = 2492683726885765988L;

        BuiltIn(final String name) {
            super(name);
        }

        @Override
        protected <T> T toJava(PyObject object, Class<T> type) {
            throw new UnconvertibleTypeException(type);
        }
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
            return VALUES.toArray(new Interfacing[VALUES.size()]);
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
}
