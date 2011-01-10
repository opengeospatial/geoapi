/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.legacy.unit;

import java.util.Map;
import java.util.WeakHashMap;


/**
 * Provides static methods for wrapping JSR-275 implementations into
 * <a href="http://www.unitsofmeasurement.org">www.unitsofmeasurement.org</a> interfaces,
 * or the converse.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public final class Units {
    /**
     * The units and dimensions created up to date.
     * Access to this map must be synchronized by {@code pool}.
     */
    private static final Map<Object, Object> pool = new WeakHashMap<Object, Object>();

    /**
     * Do not allow instantiation of this class.
     */
    private Units() {
    }

    /**
     * Wraps the given JSR-275 unit.
     *
     * @param  unit The JSR-275 implementation, or {@code null}.
     * @return A Unit implementation wrapping the given JSR-275 implementation,
     *         or {@code null} if the given object was null.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static org.unitsofmeasurement.unit.Unit<?> wrap(final javax.measure.unit.Unit<?> unit) {
        if (unit == null || unit instanceof org.unitsofmeasurement.unit.Unit<?>) {
            return (org.unitsofmeasurement.unit.Unit<?>) unit;
        }
        org.unitsofmeasurement.unit.Unit<?> wrapped;
        synchronized (pool) {
            wrapped = (org.unitsofmeasurement.unit.Unit<?>) pool.get(unit);
            if (wrapped == null) {
                wrapped = new Unit(unit);
                pool.put(unit, wrapped);
            }
        }
        return wrapped;
    }

    /**
     * Unwraps the given unit.
     *
     * @param  unit The unit, or {@code null} if none.
     * @return The JSR-275 implementation backing the given unit, or {@code null}
     *         if the given unit was null.
     * @throws ClassCastException If the given unit is not an instance created by the
     *         {@link #wrap(javax.measure.unit.Unit) wrap} method.
     */
    public static javax.measure.unit.Unit<?> unwrap(final org.unitsofmeasurement.unit.Unit<?> unit)
            throws ClassCastException
    {
        if (unit == null || unit instanceof javax.measure.unit.Unit<?>) {
            return (javax.measure.unit.Unit<?>) unit;
        }
        return ((Unit<?>) unit).impl;
    }

    /**
     * Wraps the given JSR-275 unit converter.
     *
     * @param  converter The JSR-275 implementation, or {@code null}.
     * @return A converter implementation wrapping the given JSR-275 implementation,
     *         or {@code null} if the given object was null.
     */
    public static org.unitsofmeasurement.unit.UnitConverter wrap(final javax.measure.converter.UnitConverter converter) {
        if (converter == null) {
            return null;
        }
        org.unitsofmeasurement.unit.UnitConverter wrapped;
        synchronized (pool) {
            wrapped = (org.unitsofmeasurement.unit.UnitConverter) pool.get(converter);
            if (wrapped == null) {
                wrapped = new UnitConverter(converter);
                pool.put(converter, wrapped);
            }
        }
        return wrapped;
    }

    /**
     * Unwraps the given unit converter.
     *
     * @param  converter The converter, or {@code null} if none.
     * @return The JSR-275 implementation backing the given converter, or {@code null}
     *         if the given converter was null.
     * @throws ClassCastException If the given converter is not an instance created by the
     *         {@link #wrap(javax.measure.converter.UnitConverter) wrap} method.
     */
    public static javax.measure.converter.UnitConverter unwrap(final org.unitsofmeasurement.unit.UnitConverter converter)
            throws ClassCastException
    {
        if (converter == null) {
            return null;
        }
        return ((UnitConverter) converter).impl;
    }

    /**
     * Wraps the given JSR-275 dimension.
     *
     * @param  dimension The JSR-275 implementation, or {@code null}.
     * @return A dimension implementation wrapping the given JSR-275 implementation,
     *         or {@code null} if the given object was null.
     */
    public static org.unitsofmeasurement.unit.Dimension wrap(final javax.measure.unit.Dimension dimension) {
        if (dimension == null) {
            return null;
        }
        org.unitsofmeasurement.unit.Dimension wrapped;
        synchronized (pool) {
            wrapped = (org.unitsofmeasurement.unit.Dimension) pool.get(dimension);
            if (wrapped == null) {
                wrapped = new Dimension(dimension);
                pool.put(dimension, wrapped);
            }
        }
        return wrapped;
    }

    /**
     * Unwraps the given dimension.
     *
     * @param  dimension The dimension, or {@code null} if none.
     * @return The JSR-275 implementation backing the given dimension, or {@code null}
     *         if the given dimension was null.
     * @throws ClassCastException If the given dimension is not an instance created by the
     *         {@link #wrap(javax.measure.unit.Dimension) wrap} method.
     */
    public static javax.measure.unit.Dimension unwrap(final org.unitsofmeasurement.unit.Dimension dimension)
            throws ClassCastException
    {
        if (dimension == null) {
            return null;
        }
        return ((Dimension) dimension).impl;
    }

    /**
     * Converts a JSR-275 quantity interface to the corresponding UOM quantity interface.
     *
     * @param  type The JSR-275 quantity type to convert, or {@code null}.
     * @return The given quantity as an UOM interface, or {@code null} if the given type was null.
     * @throws IllegalArgumentException If the given type is unknown to this method.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Class<? extends org.unitsofmeasurement.quantity.Quantity<?>> toUOM(final Class<? extends javax.measure.quantity.Quantity> type) {
        if (type == null) {
            return null;
        }
        return (Class) convertType(type, "javax.measure.quantity.", "org.unitsofmeasurement.quantity.", "Duration", "Time")
                .asSubclass(org.unitsofmeasurement.quantity.Quantity.class);
    }

    /**
     * Converts a UOM quantity interface to the corresponding JSR-275 quantity interface.
     *
     * @param  type The UOM quantity type to convert, or {@code null}.
     * @return The given quantity as an JSR-275 interface, or {@code null} if the given type was null.
     * @throws IllegalArgumentException If the given type is unknown to this method.
     */
    public static Class<? extends javax.measure.quantity.Quantity> toJSR(final Class<? extends org.unitsofmeasurement.quantity.Quantity<?>> type) {
        if (type == null) {
            return null;
        }
        return convertType(type, "org.unitsofmeasurement.quantity.", "javax.measure.quantity.", "Time", "Duration")
                .asSubclass(javax.measure.quantity.Quantity.class);
    }

    /**
     * Converts a quantity interface from the old package to the new package, or conversely.
     *
     * @param  type       The quantity type to convert.
     * @param  oldPackage The package name of the type to convert.
     * @param  newPackage The package name of the type to return.
     * @param  oldTime    The old name for the time quantity.
     * @param  newTime    The new name for the time quantity.
     * @return The given quantity as an interface in the new package.
     * @throws IllegalArgumentException If the given type is unknown to this method.
     */
    private static Class<?> convertType(final Class<?> type, final String oldPackage, final String newPackage,
            final String oldTime, final String newTime)
            throws IllegalArgumentException
    {
        ClassNotFoundException cause = null;
        String classname = type.getName();
        if (classname.startsWith(oldPackage)) {
            classname = classname.substring(oldPackage.length());
            if (classname.equals(oldTime)) {
                classname = newTime;
            }
            classname = newPackage + classname;
            try {
                return Class.forName(classname);
            } catch (ClassNotFoundException e) {
                cause = e;
            }
        }
        throw new IllegalArgumentException("Unsupported quantity: " + type.getSimpleName(), cause);
    }
}
