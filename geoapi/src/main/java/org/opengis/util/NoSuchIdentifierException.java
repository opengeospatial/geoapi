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
package org.opengis.util;

import org.opengis.metadata.Identifier;
import org.opengis.parameter.ParameterValueGroup;  // For javadoc


/**
 * Thrown when an identifier provided to a factory method can not be found.
 * The identifier may be provided by {@link org.opengis.referencing.IdentifiedObject#getName()}.
 * In the common case where the identifier is an "authority:code" pair,
 * the {@link org.opengis.referencing.NoSuchAuthorityCodeException} specialization should be used.
 *
 * <p><b>Example:</b> This exception is thrown when a
 * {@linkplain org.opengis.referencing.operation.MathTransform math transform} has been requested
 * with an unknown {@linkplain org.opengis.referencing.operation.OperationMethod operation method}
 * name.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.referencing.operation.MathTransformFactory#createParameterizedTransform(ParameterValueGroup)
 */
public class NoSuchIdentifierException extends FactoryException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = -6846799994429345902L;

    /**
     * The identifier code.
     */
    private final String identifier;

    /**
     * Constructs an exception with the specified detail message and classification name.
     *
     * @param message     the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param identifier  the {@linkplain Identifier#getCode() identifier code}.
     */
    public NoSuchIdentifierException(final String message, final String identifier) {
        super(message);
        this.identifier = identifier;
    }

    /**
     * Returns the {@linkplain Identifier#getCode identifier code}.
     *
     * @return the identifier code.
     */
    public String getIdentifierCode() {
        return identifier;
    }
}
