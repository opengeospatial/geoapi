/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.metadata.identification;

import java.util.Collection;
import java.util.Date;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Brief description of ways in which the resource(s) is/are currently used.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - ResponsibleParty
 */
@UML(identifier="MD_Usage", specification=ISO_19115)
public interface Usage {
    /**
     * Brief description of the resource and/or resource series usage.
     *
     * @return Description of the resource usage.
     */
    @UML(identifier="specificUsage", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getSpecificUsage();

    /**
     * Date and time of the first use or range of uses of the resource and/or resource series.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change
     *     in GeoAPI 3.1 release. It may be replaced by a type matching more closely
     *     either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Date of the first use of the resource, or {@code null}.
     */
    @UML(identifier="usageDateTime", obligation=OPTIONAL, specification=ISO_19115)
    Date getUsageDate();

    /**
     * Applications, determined by the user for which the resource and/or resource series
     * is not suitable.
     *
     * @return Applications for which the resource and/or resource series is not suitable, or {@code null}.
     */
    @UML(identifier="userDeterminedLimitations", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getUserDeterminedLimitations();

    /**
     * Identification of and means of communicating with person(s) and organization(s)
     * using the resource(s).
     *
     * @return Means of communicating with person(s) and organization(s) using the resource(s).
     */
    @UML(identifier="userContactInfo", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends ResponsibleParty> getUserContactInfo();
}
