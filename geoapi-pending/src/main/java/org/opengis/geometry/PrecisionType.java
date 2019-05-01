/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;


/**
 * The rounding policy used for a {@linkplain Precision precision model}.
 *
 * @author Jody Garnett
 * @since GeoAPI 2.1
 */
public final class PrecisionType extends CodeList<PrecisionType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2771887290382853282L;

    /**
     * Indicates Precision Model uses floating point math (rather then a grid).
     */
    private final boolean isFloating;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PrecisionType> VALUES = new ArrayList<PrecisionType>(3);

    /**
     * Fixed precision indicates that coordinates have a fixed number of decimal places.
     */
    public static final PrecisionType FIXED = new PrecisionType("FIXED", false);

    /**
     * Floating precision corresponds to the standard Java double-precision floating-point
     * representation, which is based on the IEEE-754 standard.
     */
    public static final PrecisionType DOUBLE = new PrecisionType("DOUBLE", true);

    /**
     * Floating single precision corresponds to the standard Java single-precision
     * floating-point representation, which is based on the IEEE-754 standard.
     */
    public static final PrecisionType FLOAT  = new PrecisionType("FLOAT", true);

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     * @param isFloating {@code true} if the precision model uses floating point math
     *        (rather then a grid).
     */
    private PrecisionType(final String name, final boolean isFloating) {
        super(name, VALUES);
        this.isFloating = isFloating;
    }

    /**
     * Constructs an enum with a default {@code isFloating} value.
     * This is needed for {@link CodeList#valueOf} reflection.
     */
    private PrecisionType(final String name) {
        this(name, true);
    }

    /**
     * Returns {@code true} if {@code PrecisionModelType} is a represented using floating point
     * arithmetic (rather then a grid).
     *
     * @return true if floating point arithmetic is used.
     */
    public boolean isFloating(){
        return isFloating;
    }

    /**
     * Returns the list of {@code PrecisionModelType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static PrecisionType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new PrecisionType[VALUES.size()]);
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
    public PrecisionType[] family() {
        return values();
    }

    /**
     * Returns the precision type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static PrecisionType valueOf(String code) {
        return valueOf(PrecisionType.class, code);
    }
}
