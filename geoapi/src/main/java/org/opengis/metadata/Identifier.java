/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Value uniquely identifying an object within a namespace.
 *
 * <div class="note"><b>Example:</b>
 * For the WGS 84 geographic coordinate reference system,
 * {@code code}        = {@code "4326"},
 * {@code codeSpace}   = {@code "EPSG"},
 * {@code description} = {@code "WGS 84"} and
 * {@code authority}   = OGP geodetic committee.</div>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="MD_Identifier", specification=ISO_19115)
public interface Identifier {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getAuthority()}.
     *
     * @see #getAuthority()
     */
    String AUTHORITY_KEY = "authority";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getCode()}.
     *
     * @see #getCode()
     */
    String CODE_KEY = "code";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getCodeSpace()}.
     *
     * @see #getCodeSpace()
     */
    String CODESPACE_KEY = "codespace";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getVersion()}.
     *
     * @see #getVersion()
     */
    String VERSION_KEY = "version";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} {@code createFoo(Map, ...)} methods.
     * This is used for setting the value to be returned by {@link #getDescription()}.
     *
     * @see #getDescription()
     *
     * @since 3.1
     */
    String DESCRIPTION_KEY = "description";

    /**
     * Person or party responsible for maintenance of the namespace.
     *
     * @return the person or party responsible for maintenance of the namespace, or {@code null} if none.
     */
    @UML(identifier="authority", obligation=OPTIONAL, specification=ISO_19115)
    Citation getAuthority();

    /**
     * Alphanumeric value identifying an instance in the namespace.
     * Should avoid characters that are not legal in URLs.
     *
     * <div class="note"><b>Example:</b> {@code "4326"}.</div>
     *
     * @return value identifying an instance in the namespace.
     */
    @UML(identifier="code", obligation=MANDATORY, specification=ISO_19115)
    String getCode();

    /**
     * Identifier or namespace in which the code is valid.
     *
     * <div class="note"><b>Example:</b> {@code "EPSG"}.</div>
     *
     * @return the identifier or namespace in which the code is valid, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="codeSpace", obligation=OPTIONAL, specification=ISO_19115)
    String getCodeSpace();

    /**
     * Version identifier for the namespace, as specified by the code authority.
     * When appropriate, the edition is identified by the effective date, coded
     * using ISO 8601 date format.
     *
     * <div class="note"><b>Example:</b>
     * the version of the underlying EPSG database.</div>
     *
     * @return the version identifier for the namespace, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="version", obligation=OPTIONAL, specification=ISO_19115)
    String getVersion();

    /**
     * Natural language description of the meaning of the code value.
     *
     * <div class="note"><b>Example:</b> for {@code codeSpace = "EPSG"} and {@code code = "4326"},
     * the description can be "WGS 84".</div>
     *
     * @return the natural language description, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getDescription();
}
