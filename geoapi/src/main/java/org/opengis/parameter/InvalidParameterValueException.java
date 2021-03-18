/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.parameter;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * Thrown by {@link ParameterValue} setter methods when they are given an invalid value.
 * The value may be invalid because it is not assignable to the Java
 * {@linkplain ParameterDescriptor#getValueClass() value class}, not a member of the
 * {@linkplain ParameterDescriptor#getValidValues() valid values} set, or any other reason.
 * This exception is typically thrown by the following methods:
 *
 * <ul>
 *   <li>{@link ParameterValue#setValue(int)}</li>
 *   <li>{@link ParameterValue#setValue(double)}</li>
 *   <li>{@link ParameterValue#setValue(Object)}</li>
 *   <li>Any other setter method.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see InvalidParameterTypeException
 */
@UML(identifier="GC_InvalidParameterValue", specification=OGC_01004)
public class InvalidParameterValueException extends IllegalArgumentException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = 3814037056147642789L;

    /**
     * The parameter name.
     */
    private final String parameterName;

    /**
     * The invalid parameter value.
     */
    private final Object value;

    /**
     * Creates an exception with the specified invalid value.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param parameterName  the parameter name.
     * @param value          the invalid parameter value.
     */
    public InvalidParameterValueException(String message, String parameterName, Object value) {
        super(message);
        this.parameterName = parameterName;
        this.value = value;
    }

    /**
     * Creates an exception with the specified invalid value as a floating point.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param parameterName  the parameter name.
     * @param value          the invalid parameter value.
     */
    public InvalidParameterValueException(String message, String parameterName, double value) {
        this(message, parameterName, Double.valueOf(value));
    }

    /**
     * Creates an exception with the specified invalid value as an integer.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param parameterName  the parameter name.
     * @param value          the invalid parameter value.
     */
    public InvalidParameterValueException(String message, String parameterName, int value) {
        this(message, parameterName, Integer.valueOf(value));
    }

    /**
     * Creates an exception with the specified message, cause and invalid value.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause          the cause, saved for later retrieval by the {@link #getCause()} method.
     * @param parameterName  the parameter name.
     * @param value          the invalid parameter value.
     *
     * @since 3.1
     */
    public InvalidParameterValueException(String message, Throwable cause, String parameterName, Object value) {
        super(message, cause);
        this.parameterName = parameterName;
        this.value = value;
    }

    /**
     * Returns the parameter name.
     *
     * @return the parameter name.
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Returns the invalid parameter value.
     *
     * @return the invalid parameter value.
     */
    public Object getValue() {
        return value;
    }
}
