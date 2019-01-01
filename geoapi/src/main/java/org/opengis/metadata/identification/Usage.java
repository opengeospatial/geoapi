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
package org.opengis.metadata.identification;

import java.util.Collection;
import java.util.Date;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Brief description of ways in which the resource(s) is/are currently or has been used.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Usage", specification=ISO_19115)
public interface Usage {
    /**
     * Brief description of the resource and/or resource series usage.
     *
     * @return description of the resource usage.
     */
    @UML(identifier="specificUsage", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getSpecificUsage();

    /**
     * Date and time of the first use or range of uses of the resource and/or resource series.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The return type of this method may change in GeoAPI 4.0 release. It may be replaced by a
     * type matching more closely either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * </div>
     *
     * @return date of the first use of the resource, or {@code null}.
     *
     * @todo This become a collection in ISO 19115:2014.
     */
    @UML(identifier="usageDateTime", obligation=OPTIONAL, specification=ISO_19115)
    Date getUsageDate();

    /**
     * Applications, determined by the user for which the resource and/or resource series is not suitable.
     *
     * @return applications for which the resource and/or resource series is not suitable, or {@code null}.
     */
    @UML(identifier="userDeterminedLimitations", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getUserDeterminedLimitations();

    /**
     * Identification of and means of communicating with person(s) and organization(s) using the resource(s).
     * Returns an empty collection if none.
     *
     * @return means of communicating with person(s) and organization(s) using the resource(s).
     */
    @UML(identifier="userContactInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Responsibility> getUserContactInfo();

    /**
     * Responses to the user-determined limitations.
     *
     * <div class="note"><b>Example:</b>
     * This has been fixed in version <var>x</var>.
     * </div>
     *
     * @return responses to the user-determined limitations.
     *
     * @since 3.1
     */
    @UML(identifier="response", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends InternationalString> getResponses();

    /**
     * Publications that describe usage of data.
     *
     * @return publications that describe usage of data.
     *
     * @since 3.1
     */
    @UML(identifier="additionalDocumentation", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getAdditionalDocumentation();

    /**
     * Citations of a description of known issues associated with the resource
     * along with proposed solutions if available.
     *
     * @return citations of a description of known issues associated with the resource.
     *
     * @since 3.1
     */
    @UML(identifier="identifiedIssues", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getIdentifiedIssues();
}
