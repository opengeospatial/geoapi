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
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import javax.measure.converter.LinearConverter;


/**
 * An implementation of {@link org.unitsofmeasurement} interface as a wrapper around the
 * {@link javax.measure} object.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
final class UnitConverter implements org.unitsofmeasurement.unit.UnitConverter, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 1866696459039929270L;

    /**
     * The wrapped JSR-275 implementation.
     */
    final javax.measure.converter.UnitConverter impl;

    /**
     * Creates a new unit converter wrapping the given JSR-275 implementation.
     */
    UnitConverter(final javax.measure.converter.UnitConverter impl) {
        this.impl = impl;
    }

    /**
     * Returns {@code true} if the implementation is the identity constant.
     */
    public boolean isIdentity() {
        return javax.measure.converter.UnitConverter.IDENTITY.equals(impl);
    }

    /**
     * Returns {@code true} if the implementation is an instance of {@link LinearConverter}.
     */
    public boolean isLinear() {
        return (impl instanceof LinearConverter);
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.UnitConverter inverse() {
        return Units.wrap(impl.inverse());
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public double convert(final double value) {
        return impl.convert(value);
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public Number convert(final Number value) {
        return impl.convert(value.doubleValue());
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public BigDecimal convert(final BigDecimal value, final MathContext ctx) throws ArithmeticException {
        return impl.convert(value, ctx);
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.UnitConverter concatenate(final org.unitsofmeasurement.unit.UnitConverter converter) {
        return Units.wrap(impl.concatenate(Units.unwrap(converter)));
    }

    /**
     * Unsupported operation.
     */
    public List<? extends UnitConverter> getCompoundConverters() {
        throw new UnsupportedOperationException();
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
        return (other instanceof UnitConverter) && impl.equals(((UnitConverter) other).impl);
    }

    /**
     * Returns the string representation of this converter.
     */
    @Override
    public String toString() {
        return impl.toString();
    }
}
