/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
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

import java.util.Locale;
import java.util.function.Function;
import org.opengis.util.CodeList;
import org.opengis.util.ControlledVocabulary;
import org.opengis.util.InternationalString;
import org.jpy.PyObject;


/**
 * Converter from Python objects to Java objects.
 * A single converter is built once-for-all before to convert an arbitrary amount of objects.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 */
class Converter<T> implements Function<PyObject,T> {
    /**
     * The Java type of converted objects.
     */
    final Class<T> type;

    /**
     * Creates a new converter.
     */
    private Converter(final Class<T> type) {
        this.type = type;
    }

    /**
     * Converts the given Python object to a Java object of the converter {@link #type}.
     * The default implementation wraps the Python object in a proxy forwarding method calls
     * to the equivalent Python property. Subclasses need to override for primitive types.
     */
    @Override
    public T apply(final PyObject value) {
        return (value != null) ? value.createProxy(type) : null;
    }

    /** Shared converter from Python objects to {@code int} primitive. */
    private static final Converter<Boolean> PRIMITIVE_BOOLEAN = new Converter<Boolean>(Boolean.class) {
        @Override public Boolean apply(final PyObject value) {
            return (value != null) ? value.getIntValue() != 0 : Boolean.FALSE;
        }
    };

    /** Shared converter from Python objects to {@code int} primitive numbers. */
    private static final Converter<Integer> PRIMITIVE_INTEGER = new Converter<Integer>(Integer.class) {
        @Override public Integer apply(final PyObject value) {
            return (value != null) ? value.getIntValue() : 0;
        }
    };

    /** Shared converter from Python objects to {@code double} primitive numbers. */
    private static final Converter<Double> PRIMITIVE_DOUBLE = new Converter<Double>(Double.class) {
        @Override public Double apply(final PyObject value) {
            return (value != null) ? value.getDoubleValue() : Double.NaN;
        }
    };

    /** Shared converter from Python objects to {@code Boolean} wrapper. */
    private static final Converter<Boolean> BOOLEAN = new Converter<Boolean>(Boolean.class) {
        @Override public Boolean apply(final PyObject value) {
            return (value != null) ? value.getIntValue() != 0 : null;
        }
    };

    /** Shared converter from Python objects to {@code Integer} wrapped numbers. */
    private static final Converter<Integer> INTEGER = new Converter<Integer>(Integer.class) {
        @Override public Integer apply(final PyObject value) {
            return (value != null) ? value.getIntValue() : null;
        }
    };

    /** Shared converter from Python objects to {@code Double} wrapped numbers. */
    private static final Converter<Double> DOUBLE = new Converter<Double>(Double.class) {
        @Override public Double apply(final PyObject value) {
            return (value != null) ? value.getDoubleValue() : null;
        }
    };

    /** Shared converter from Python objects to {@link String} instances. */
    private static final Converter<String> STRING = new Converter<String>(String.class) {
        @Override public String apply(final PyObject value) {
            return (value != null) ? value.getStringValue() : null;
        }
    };

    /** Shared converter from Python objects to {@link InternationalString} instances. */
    private static final Converter<InternationalString> I18N = new Converter<InternationalString>(InternationalString.class) {
        @Override public InternationalString apply(final PyObject value) {
            return (value != null) ? new Literal(value.getStringValue()) : null;
        }
    };

    /**
     * Converter for code list values. The conversion is based only on the enum name, case-insensitive.
     */
    private static final class ForCodeList<T extends CodeList<T>> extends Converter<T> {
        /** Creates a new converter for the given code list class. */
        ForCodeList(final Class<T> type) {
            super(type);
        }

        /** Returns the name of the given enumeration or code list value. */
        static String name(PyObject value) {
            if (value != null) {
                value = value.getAttribute("value");
                if (value != null) {
                    String name = value.getStringValue();
                    if (name != null && !(name = name.trim()).isEmpty()) {
                        return name;
                    }
                }
            }
            return null;
        }

        /** Converts the given Python enumerated value to a Java {@code CodeList} value. */
        @Override public T apply(final PyObject value) {
            final String name = name(value);
            if (name == null) return null;
            return CodeList.valueOf(type, (c) -> {
                for (final String n : c.names()) {
                    if (name.equalsIgnoreCase(n)) {
                        return true;
                    }
                }
                return false;
            }, name);
        }
    }

    /**
     * Converter for enumerated values. The conversion is based only on the enum name, case-insensitive.
     */
    private static final class ForEnum<T extends Enum<T>> extends Converter<T> {
        /** Creates a new converter for the given enumeration class. */
        ForEnum(final Class<T> type) {
            super(type);
        }

        /** Converts the given Python enumerated value to a Java enumerated value. */
        @Override public T apply(final PyObject value) {
            final String name = ForCodeList.name(value);
            if (name != null) try {
                return Enum.valueOf(type, name.toUpperCase(Locale.US));     // Fast check (sufficient in most cases).
            } catch (IllegalArgumentException e) {
                for (final T c : type.getEnumConstants()) {                 // Fallback on more costly check.
                    if (c instanceof ControlledVocabulary) {
                        for (final String n : ((ControlledVocabulary) c).names()) {
                            if (name.equalsIgnoreCase(n)) return c;
                        }
                    }
                }
                throw e;
            }
            return null;
        }
    }

    /**
     * Converter from Python objects to Java objects implementing a GeoAPI interface.
     * This converter is used when the given type has no known sub-type.
     * This is the case of a majority of GeoAPI interfaces.
     */
    private static class GeoAPI<T> extends Converter<T> {
        /** Information about the Python environment (builtin functions, etc). */
        final Environment environment;

        /** Creates a new converter for the given Java type. */
        GeoAPI(final Environment environment, final Class<T> type) {
            super(type);
            this.environment = environment;
        }

        /** Converts the given Python object to a Java object of the converter {@link #type}. */
        @Override public T apply(final PyObject value) {
            return (value != null) ? Singleton.create(environment, value, type) : null;
        }
    }

    /**
     * Converter from Python objects to Java objects implementing a GeoAPI interface.
     * This converter is used when the given type is known to have sub-types.
     */
    private static final class Specializable<T> extends GeoAPI<T> {
        /** Creates a new converter for the given Java type. */
        Specializable(final Environment environment, final Class<T> type) {
            super(environment, type);
        }

        /** Converts the given Python object to a Java object of the converter {@link #type}. */
        @Override public T apply(final PyObject value) {
            return (value == null) ? null : Singleton.create(environment, value,
                    Interfacing.GEOAPI.getJavaType(type, value, environment.builtins));
        }
    }

    /**
     * Converter from Python objects to Java objects using a user-provided function.
     * This is used only if the user provided a custom {@link Interfacing} instance.
     */
    private static final class UserDefined<T> extends Converter<T> {
        /** User-defined function for interfacing Java to Python. */
        private final Interfacing provided;

        /** Creates a new converter for the given Java type. */
        UserDefined(final Interfacing provided, final Class<T> type) {
            super(type);
            this.provided = provided;
        }

        /** Converts the given Python object to a Java object of the converter {@link #type}. */
        @Override public T apply(final PyObject value) {
            return (value != null) ? provided.toJava(value, type) : null;
        }
    }

    /**
     * Returns a converter from Python objects to the given Java type.
     * The converter is not guaranteed to be suitable for the given type;
     * caller should verify (or delegate to a method that will verify).
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    static Converter<?> instance(final Environment environment, final Class<?> type) {
        // 'if' statements should be ordered from most frequently-used to less frequently-used.
        final Converter<?> c;
        if (CharSequence.class.isAssignableFrom(type)) {
            if (InternationalString.class.isAssignableFrom(type)) {
                c = I18N;
            } else {
                c = STRING;
            }
        } else if (type.isInterface()) {
            final Interfacing inf = environment.getInterfacing(type);
            if (inf == Interfacing.GEOAPI) {
                if (inf.hasKnownSubtypes(type)) {
                    c = new Specializable<>(environment, type);
                } else {
                    c = new GeoAPI<>(environment, type);
                }
            } else if (inf == Interfacing.DEFAULT) {
                c = new Converter<>(type);
            } else {
                c = new UserDefined<>(inf, type);
            }
        } else if (Number.class.isAssignableFrom(type)) {
            if (Double.class.equals(type) || Float.class.equals(type)) {
                c = DOUBLE;
            } else {
                c = INTEGER;
            }
        } else if (type.isPrimitive()) {
            if (type == Double.TYPE || type == Float.TYPE) {
                c = PRIMITIVE_DOUBLE;
            } else if (type == Boolean.TYPE) {
                c = PRIMITIVE_BOOLEAN;
            } else {                                // We forget the 'char' case for now.
                c = PRIMITIVE_INTEGER;
            }
        } else if (CodeList.class.isAssignableFrom(type)) {
            return new ForCodeList(type.asSubclass(CodeList.class));
        } else if (type.isEnum()) {
            return new ForEnum(type.asSubclass(Enum.class));
        } else if (Boolean.class.equals(type)) {
            c = BOOLEAN;
        } else {
            throw new UnconvertibleTypeException(type);
        }
        return c;
    }

    /**
     * Returns a safe converter from Python objects to the given Java type.
     * This method verifies that the returned converter is okay for the Java type.
     */
    @SuppressWarnings("unchecked")
    static <T> Converter<? extends T> verifiedInstance(final Environment environment, final Class<T> type) {
        final Converter<?> c = instance(environment, type);
        if (type.isAssignableFrom(c.type)) {
            return (Converter<? extends T>) c;
        } else {
            throw new UnconvertibleTypeException(type);
        }
    }
}
