/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata;

import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Value uniquely identifying an object within a namespace.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - Citation
 */
@UML(identifier="MD_Identifier", specification=ISO_19115)
public interface Identifier {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} <code>createFoo(&hellip;)</code>
     * methods. This is used for setting the value to be returned by {@link #getCode()}.
     *
     * @see #getCode()
     */
    String CODE_KEY = "code";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} <code>createFoo(&hellip;)</code>
     * methods. This is used for setting the value to be returned by {@link #getAuthority()}.
     *
     * @see #getAuthority()
     */
    String AUTHORITY_KEY = "authority";

    /**
     * Alphanumeric value identifying an instance in the namespace.
     *
     * @return Value identifying an instance in the namespace.
     */
    @UML(identifier="code", obligation=MANDATORY, specification=ISO_19115)
    String getCode();

    /**
     * Organization or party responsible for definition and maintenance of the
     * {@linkplain #getCode code}.
     *
     * @return Party responsible for definition and maintenance of the code.
     */
    @UML(identifier="authority", obligation=OPTIONAL, specification=ISO_19115)
    Citation getAuthority();
}
