/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2013 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.ReferenceIdentifier;


/**
 * A trivial implementation of {@link ReferenceIdentifier} for internal usage.
 * Not public because strictly reserved to tests.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleReferenceIdentifier implements ReferenceIdentifier {
    /**
     * The value to be returned by {@link #getCode()}.
     */
    private final int code;

    /**
     * Creates a new identifier for the "EPSG" namespace and the given code.
     */
    SimpleReferenceIdentifier(final int code) {
        this.code = code;
    }

    /**
     * Returns the code given at construction time.
     */
    @Override
    public String getCode() {
        return String.valueOf(code);
    }

    /**
     * Returns the code space, currently fixed to {@code "EPSG"}
     * for the needs of {@link PseudoEpsgFactory}.
     */
    @Override
    public String getCodeSpace() {
        return "EPSG";
    }

    /**
     * Returns {@code null} since not yet provided.
     */
    @Override
    public Citation getAuthority() {
        return null;
    }

    /**
     * Returns {@code null} since not yet provided.
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
        return getCodeSpace() + ':' + code;
    }
}
