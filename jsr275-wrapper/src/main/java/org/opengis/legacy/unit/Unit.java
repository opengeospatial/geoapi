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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.measure.converter.ConversionException;
import javax.measure.unit.BaseUnit;
import javax.measure.unit.ProductUnit;
import org.unitsofmeasurement.quantity.Quantity;
import org.unitsofmeasurement.unit.IncommensurableException;
import org.unitsofmeasurement.unit.UnconvertibleException;


/**
 * An implementation of {@link org.unitsofmeasurement} interface as a wrapper around the
 * {@link javax.measure} object.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
final class Unit<Q extends Quantity<Q>> implements org.unitsofmeasurement.unit.Unit<Q>, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 5132589168153663307L;

    /**
     * The wrapped JSR-275 implementation.
     */
    final javax.measure.unit.Unit<?> impl;

    /**
     * Creates a new unit wrapping the given JSR-275 implementation.
     */
    Unit(final javax.measure.unit.Unit<?> impl) {
        this.impl = impl;
    }

    /**
     * Returns the unit symbol, as the string representation of the JSR-275 unit.
     */
    public String getSymbol() {
        return impl.toString();
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Dimension getDimension() {
        return Units.wrap(impl.getDimension());
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public org.unitsofmeasurement.unit.Unit<Q> getSystemUnit() {
        return (org.unitsofmeasurement.unit.Unit) Units.wrap(impl.toSI());
    }

    /**
     * Builds the product map from the information provided by the
     * {@linkplain #impl JSR-275 implementation}.
     */
    public Map<? extends org.unitsofmeasurement.unit.Unit<?>, Integer> getProductUnits() {
        if (impl instanceof BaseUnit<?>) {
            return null;
        }
        if (impl instanceof ProductUnit<?>) {
            final ProductUnit<?> unit = (ProductUnit<?>) impl;
            final int n = unit.getUnitCount();
            final Map<org.unitsofmeasurement.unit.Unit<?>, Integer> units =
                    new HashMap<org.unitsofmeasurement.unit.Unit<?>, Integer>(Dimension.hashMapCapacity(n));
            for (int i=0; i<n; i++) {
                units.put(Units.wrap(unit.getUnit(i)), unit.getUnitPow(i));
            }
            return units;
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public boolean isCompatible(final org.unitsofmeasurement.unit.Unit<?> unit) {
        return impl.isCompatible(Units.unwrap(unit));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T extends Quantity<T>> org.unitsofmeasurement.unit.Unit<T> asType(final Class<T> type) {
        return (org.unitsofmeasurement.unit.Unit) Units.wrap(impl.asType(Units.toJSR(type)));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public org.unitsofmeasurement.unit.UnitConverter getConverterTo(final org.unitsofmeasurement.unit.Unit<Q> unit)
            throws UnconvertibleException
    {
        return Units.wrap(impl.getConverterTo((javax.measure.unit.Unit) Units.unwrap(unit)));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.UnitConverter getConverterToAny(final org.unitsofmeasurement.unit.Unit<?> unit)
            throws IncommensurableException, UnconvertibleException
    {
        try {
            return Units.wrap(impl.getConverterToAny(Units.unwrap(unit)));
        } catch (ConversionException cause) {
            IncommensurableException e = new IncommensurableException(cause.getLocalizedMessage());
            e.initCause(cause);
            throw e;
        }
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Unit<?> alternate(final String symbol) {
        return Units.wrap(impl.alternate(symbol));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public org.unitsofmeasurement.unit.Unit<Q> transform(final org.unitsofmeasurement.unit.UnitConverter converter) {
        return (org.unitsofmeasurement.unit.Unit) Units.wrap(impl.transform(Units.unwrap(converter)));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public org.unitsofmeasurement.unit.Unit<Q> add(final double offset) {
        return (org.unitsofmeasurement.unit.Unit) Units.wrap(impl.plus(offset));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public org.unitsofmeasurement.unit.Unit<Q> multiply(final double factor) {
        return (org.unitsofmeasurement.unit.Unit) Units.wrap(impl.times(factor));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Unit<?> multiply(final org.unitsofmeasurement.unit.Unit<?> that) {
        return Units.wrap(impl.times(Units.unwrap(that)));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Unit<?> inverse() {
        return Units.wrap(impl.inverse());
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public org.unitsofmeasurement.unit.Unit<Q> divide(final double divisor) {
        return (org.unitsofmeasurement.unit.Unit) Units.wrap(impl.divide(divisor));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Unit<?> divide(final org.unitsofmeasurement.unit.Unit<?> that) {
        return Units.wrap(impl.divide(Units.unwrap(that)));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Unit<?> root(final int n) {
        return Units.wrap(impl.root(n));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Unit<?> pow(final int n) {
        return Units.wrap(impl.pow(n));
    }

    /**
     * Returns a hash-code value for this wrapper.
     */
    @Override
    public int hashCode() {
        return impl.hashCode() ^ (int) serialVersionUID;
    }

    /**
     * Compares this wrapper with the given object for equality.
     */
    @Override
    public boolean equals(final Object other) {
        return (other instanceof Unit<?>) && impl.equals(((Unit<?>) other).impl);
    }

    /**
     * Returns the string representation of this unit.
     */
    @Override
    public String toString() {
        return impl.toString();
    }
}
