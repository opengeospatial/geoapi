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


/**
 * Thrown if adding or removing a {@linkplain ParameterValue parameter value} in a
 * {@linkplain ParameterValueGroup group} would result in more or less parameters than the expected range.
 * The minimum and maximum occurrences are defined by the {@link ParameterDescriptorGroup}
 * instance associated with the {@code ParameterValueGroup}.
 *
 * <p>This exception may be thrown directly by the {@link ParameterValueGroup#addGroup(String)}
 * method, or indirectly during the add or remove operations applied on the list returned by
 * {@link ParameterValueGroup#values()}.</p>
 *
 * <div class="note"><b>Note 1:</b>
 * the <cite>cardinality</cite> is the number of elements in a set. Contrast with <cite>multiplicity</cite>,
 * which is the range of possible cardinalities a set can hold.</div>
 *
 * <div class="note"><b>Note 2:</b>
 * this exception is of kind {@code IllegalStateException} instead than {@code IllegalArgumentException}
 * because it is not caused by a bad argument. It is rather a consequence of an {@link ParameterValueGroup}
 * being "full".</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
public class InvalidParameterCardinalityException extends IllegalStateException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = 4030549323541812311L;

    /**
     * The name of the parameter with invalid cardinality.
     */
    private final String parameterName;

    /**
     * Creates an exception with the specified message and parameter name.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param parameterName  the name of the parameter with invalid cardinality.
     */
    public InvalidParameterCardinalityException(String message, String parameterName) {
        super(message);
        this.parameterName = parameterName;
    }

    /**
     * Creates an exception with the specified message, cause and parameter name.
     *
     * @param message        the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause          the cause, saved for later retrieval by the {@link #getCause()} method.
     * @param parameterName  the name of the parameter with invalid cardinality.
     *
     * @since 3.1
     */
    public InvalidParameterCardinalityException(String message, Throwable cause, String parameterName) {
        super(message, cause);
        this.parameterName = parameterName;
    }

    /**
     * Returns the name of the parameter with invalid cardinality.
     *
     * @return the name of the parameter with invalid cardinality.
     */
    public String getParameterName() {
        return parameterName;
    }
}
