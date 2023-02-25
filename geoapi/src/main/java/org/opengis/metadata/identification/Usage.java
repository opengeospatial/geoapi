/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
import java.util.Collections;
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
    default Date getUsageDate() {
        return null;
    }

    /**
     * Applications, determined by the user for which the resource and/or resource series is not suitable.
     *
     * @return applications for which the resource and/or resource series is not suitable, or {@code null}.
     */
    @UML(identifier="userDeterminedLimitations", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getUserDeterminedLimitations() {
        return null;
    }

    /**
     * Identification of and means of communicating with person(s) and organization(s) using the resource(s).
     * Returns an empty collection if none.
     *
     * @return means of communicating with person(s) and organization(s) using the resource(s).
     */
    @UML(identifier="userContactInfo", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Responsibility> getUserContactInfo() {
        return Collections.emptyList();
    }

    /**
     * Responses to the user-determined limitations.
     *
     * <div class="note"><b>Example:</b>
     * this has been fixed in version <var>x</var>.
     * </div>
     *
     * @return responses to the user-determined limitations.
     *
     * @since 3.1
     */
    @UML(identifier="response", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getResponses() {
        return Collections.emptyList();
    }

    /**
     * Publications that describe usage of data.
     *
     * @return publications that describe usage of data.
     *
     * @since 3.1
     */
    @UML(identifier="additionalDocumentation", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getAdditionalDocumentation() {
        return Collections.emptyList();
    }

    /**
     * Citations of a description of known issues associated with the resource
     * along with proposed solutions if available.
     *
     * @return citations of a description of known issues associated with the resource.
     *
     * @since 3.1
     */
    @UML(identifier="identifiedIssues", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getIdentifiedIssues() {
        return Collections.emptyList();
    }
}
