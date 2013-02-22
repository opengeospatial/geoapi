/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2013 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing.gigs;

import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.ReferenceIdentifier;


/**
 * A simple implementation if {@link ReferenceIdentifier}, used for GIGS testing purpose only.
 *
 * @author  Alexis Manin (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final strictfp class SimpleReferenceIdentifier implements ReferenceIdentifier {
    /**
     * The value to be returned by {@link #getCode()}.
     */
    private final String code;

    /**
     * Creates a new identifier for the given EPSG code.
     *
     * @param code The EPSG code.
     */
    SimpleReferenceIdentifier(int code){
        this.code  = String.valueOf(code);
    }

    /**
     * Unsupported - returns {@code null} for now.
     */
    @Override
    public Citation getAuthority() {
        return null;
    }

    /**
     * Returns the code space, which is fixed to {@code "EPSG"}.
     */
    @Override
    public String getCodeSpace() {
        return "EPSG";
    }

    /**
     * Returns the code given at construction time.
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * Returns {@code null} since we do not hold version information.
     */
    @Override
    public String getVersion() {
        return null;
    }

    /**
     * Returns a string representation of this identifier.
     */
    @Override
    public String toString() {
        return "EPSG:" + code;
    }

    /**
     * Returns an arbitrary hash code value for this identifier.
     */
    @Override
    public int hashCode() {
        return code.hashCode() ^ 237674218;
    }

    /**
     * Compares this identifier with the given object for equality.
     */
    @Override
    public boolean equals(final Object obj) {
        return (obj instanceof SimpleReferenceIdentifier) &&
                code.equals(((SimpleReferenceIdentifier) obj).code);
    }
}
