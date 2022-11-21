/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


/**
 * An annotation mapping each interface, methods or fields to the UML identifier where they come from.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, FIELD, METHOD})
public @interface UML {
    /**
     * The UML identifier for the annotated interface, method or code list element.
     * Scripts can use this identifier in order to maps a GeoAPI method to the UML
     * entity where it come from.
     *
     * @return the UML identifier used in the standard.
     */
    String identifier();

    /**
     * The obligation declared in the UML. This metadata can be queried in order to
     * determine if a null value is allowed for the annotated method or not. If the
     * obligation is {@link Obligation#MANDATORY}, then null value are not allowed.
     *
     * @return the obligation declared in the standard.
     */
    Obligation obligation() default Obligation.MANDATORY;

    /**
     * The specification where this UML come from.
     *
     * @return the originating specification.
     */
    Specification specification();

    /**
     * The version of the specification where this UML come from,
     * or 0 for the {@linkplain Specification#defaultVersion() default version}.
     * The valid version numbers are listed in {@link Specification} enumeration constants.
     *
     * <p><b>When older standard versions are used:</b></p>
     * The vast majority of non-deprecated GeoAPI methods leave {@code UML.version()} to its default value,
     * meaning that the {@code Specification} default version (usually latest OGC/ISO version) is used.
     * However, there is a few exceptions when an older version of an OGC or ISO standard is preferred.
     * Examples:
     *
     * <ul>
     *   <li>{@linkplain Specification#ISO_19115 ISO 19115}:2003 defined {@code PT_Locale} in a way closer
     *       to the {@link java.util.Locale java.util.Locale} model than the newer ISO 19115:2014.</li>
     *   <li>An {@linkplain Specification#ISO_19111 ISO 19111} revision by OGC defined geographic and geocentric CRS
     *       in a more type-safe way than ISO 19111:2007. The later model requires C/C++ {@code union} construct for
     *       type-safety, which does not exist in the Java language.</li>
     * </ul>
     *
     * @return the specification version, or 0 for the default (usually latest) specification.
     *
     * @see Specification#defaultVersion()
     *
     * @since 3.1
     */
    short version() default 0;
}
