/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing;

import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identifier used for reference systems.
 *
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.0
 * @since   2.1
 */
@UML(identifier="RS_Identifier", specification=ISO_19115)
public interface ReferenceIdentifier extends Identifier {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} <code>createFoo(&hellip;)</code>
     * methods. This is used for setting the value to be returned by {@link #getCodeSpace()}.
     *
     * @see #getCodeSpace()
     */
    String CODESPACE_KEY = "codespace";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} <code>createFoo(&hellip;)</code>
     * methods. This is used for setting the value to be returned by {@link #getVersion()}.
     *
     * @see #getVersion()
     */
    String VERSION_KEY = "version";

    /**
     * Name or identifier of the person or organization responsible for namespace.
     *
     * @return The identifier code space.
     */
    @UML(identifier="codeSpace", obligation=OPTIONAL, specification=ISO_19115)
    String getCodeSpace();

    /**
     * Version identifier for the namespace, as specified by the code authority.
     * When appropriate, the edition is identified by the effective date, coded
     * using ISO 8601 date format.
     *
     * @return The version for the namespace (for example the version of the
     *         underlying EPSG database).
     */
    @UML(identifier="version", obligation=OPTIONAL, specification=ISO_19115)
    String getVersion();
}
