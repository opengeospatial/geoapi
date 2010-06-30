/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2010 Open Geospatial Consortium, Inc.
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


/**
 * Exception that is thrown when an invalid enumeration lookup is performed
 * in the {@link SimpleEnumerationType} class.
 *
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 1.0
 *
 * @deprecated Not used.
 */
@Deprecated
public class NoSuchEnumerationException extends Exception {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 5827953825646995065L;

    /**
     * The invalid value.
     */
    private final int value;

    /**
     * Constructs an exception with the given invalid value.
     *
     * @param value The invalid value.
     * @todo Localize the error message.
     */
    public NoSuchEnumerationException(final int value) {
        super("No enumeration exists for the value " + value);
        this.value = value;
    }

    /**
     * Returns the invalid value.
     */
    public int getValue() {
        return value;
    }
}

