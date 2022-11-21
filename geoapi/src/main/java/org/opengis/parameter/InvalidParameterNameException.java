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
 * Thrown when an unexpected parameter was found in a {@linkplain ParameterDescriptorGroup parameter group}.
 * For example, this exception may be thrown at construction time if the same parameter name is used twice.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see ParameterNotFoundException
 */
@UML(identifier="GC_InvalidParameterName", specification=OGC_01004)
public class InvalidParameterNameException extends IllegalArgumentException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = -8473266898408204803L;

    /**
     * The invalid parameter name.
     */
    private final String parameterName;

    /**
     * Creates an exception with the specified message and parameter name.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param parameterName  the invalid parameter name.
     */
    public InvalidParameterNameException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Creates an exception with the specified message, cause and parameter name.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause          the cause, saved for later retrieval by the {@link #getCause()} method.
     * @param parameterName  the invalid parameter name.
     *
     * @since 3.1
     */
    public InvalidParameterNameException(String message, Throwable cause, String parameterName) {
        super(message, cause);
        this.parameterName = parameterName;
    }

    /**
     * Returns the invalid parameter name.
     *
     * @return the name of the invalid parameter.
     */
    public String getParameterName() {
        return parameterName;
    }
}
