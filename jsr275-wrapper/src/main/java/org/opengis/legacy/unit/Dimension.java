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


/**
 * An implementation of {@link org.unitsofmeasurement} interface as a wrapper around the
 * {@link javax.measure} object.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
final class Dimension implements org.unitsofmeasurement.unit.Dimension, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 7128113265468028533L;

    /**
     * The wrapped JSR-275 implementation.
     */
    final javax.measure.unit.Dimension impl;

    /**
     * Creates a new dimension wrapping the given JSR-275 implementation.
     */
    Dimension(final javax.measure.unit.Dimension impl) {
        this.impl = impl;
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Dimension multiply(final org.unitsofmeasurement.unit.Dimension that) {
        return Units.wrap(impl.times(Units.unwrap(that)));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Dimension divide(final org.unitsofmeasurement.unit.Dimension that) {
        return Units.wrap(impl.divide(Units.unwrap(that)));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Dimension pow(final int n) {
        return Units.wrap(impl.pow(n));
    }

    /**
     * Delegates to the {@linkplain #impl JSR-275 implementation}.
     */
    public org.unitsofmeasurement.unit.Dimension root(final int n) {
        return Units.wrap(impl.root(n));
    }

    /**
     * Builds the product map from the information provided by the
     * {@linkplain #impl JSR-275 implementation}.
     */
    public Map<? extends org.unitsofmeasurement.unit.Dimension, Integer> getProductDimensions() {
        final int n = impl.getDimensionCount();
        if (n == 0) {
            return null;
        }
        final Map<org.unitsofmeasurement.unit.Dimension, Integer> dimensions =
                new HashMap<org.unitsofmeasurement.unit.Dimension, Integer>(hashMapCapacity(n));
        for (int i=0; i<n; i++) {
            dimensions.put(Units.wrap(impl.getDimension(i)), impl.getDimensionPow(i));
        }
        return dimensions;
    }

    /**
     * Returns the hash map capacity to use for the given number of elements.
     * This method assumes a load factor of 0.75.
     */
    static int hashMapCapacity(final int n) {
        return n + ((n + 3) >> 2);
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
        return (other instanceof Dimension) && impl.equals(((Dimension) other).impl);
    }

    /**
     * Returns the string representation of this dimension.
     */
    @Override
    public String toString() {
        return impl.toString();
    }
}
