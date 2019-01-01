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
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.OnlineResource;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Defines and exposes the structure of a resource (model and/or data dictionary).
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_ApplicationSchemaInformation", specification=ISO_19115)
public interface ApplicationSchemaInformation {
    /**
     * Name of the application schema used.
     *
     * @return name of the application schema.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115)
    Citation getName();

    /**
     * Identification of the schema language used.
     *
     * @return the schema language used.
     */
    @UML(identifier="schemaLanguage", obligation=MANDATORY, specification=ISO_19115)
    String getSchemaLanguage();

    /**
     * Formal language used in Application Schema.
     *
     * @return formal language used in Application Schema.
     */
    @UML(identifier="constraintLanguage", obligation=MANDATORY, specification=ISO_19115)
    String getConstraintLanguage();

    /**
     * Full application schema given as an ASCII file.
     *
     * @return application schema as an ASCII file, or {@code null}.
     */
    @UML(identifier="schemaAscii", obligation=OPTIONAL, specification=ISO_19115)
    CharSequence getSchemaAscii();

    /**
     * Full application schema given as a graphics file.
     *
     * @return application schema as a graphics file, or {@code null}.
     */
    @UML(identifier="graphicsFile", obligation=OPTIONAL, specification=ISO_19115)
    OnlineResource getGraphicsFile();

    /**
     * Full application schema given as a software development file.
     *
     * @return application schema as a software development file, or {@code null}.
     */
    @UML(identifier="softwareDevelopmentFile", obligation=OPTIONAL, specification=ISO_19115)
    OnlineResource getSoftwareDevelopmentFile();

    /**
     * Software dependent format used for the application schema software dependent file.
     *
     * @return format used for the application schema software file, or {@code null}.
     */
    @UML(identifier="softwareDevelopmentFileFormat", obligation=OPTIONAL, specification=ISO_19115)
    String getSoftwareDevelopmentFileFormat();
}
