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
package org.opengis.metadata.maintenance;

import java.util.Collection;
import java.util.Date;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.temporal.PeriodDuration;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the scope and frequency of updating.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - MaintenanceFrequency
 * @navassoc 1 - - PeriodDuration
 * @navassoc - - - ScopeCode
 * @navassoc - - - ScopeDescription
 * @navassoc - - - ResponsibleParty
 */
@UML(identifier="MD_MaintenanceInformation", specification=ISO_19115)
public interface MaintenanceInformation {
    /**
     * Frequency with which changes and additions are made to the resource after the
     * initial resource is completed.
     *
     * @return Frequency with which changes and additions are made to the resource.
     */
    @UML(identifier="maintenanceAndUpdateFrequency", obligation=MANDATORY, specification=ISO_19115)
    MaintenanceFrequency getMaintenanceAndUpdateFrequency();

    /**
     * Scheduled revision date for resource.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change
     *     in GeoAPI 3.1 release. It may be replaced by a type matching more closely
     *     either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Scheduled revision date, or {@code null}.
     */
    @UML(identifier="dateOfNextUpdate", obligation=OPTIONAL, specification=ISO_19115)
    Date getDateOfNextUpdate();

    /**
     * Maintenance period other than those defined.
     *
     * @return The Maintenance period, or {@code null}.
     */
    @UML(identifier="userDefinedMaintenanceFrequency", obligation=OPTIONAL, specification=ISO_19115)
    PeriodDuration getUserDefinedMaintenanceFrequency();

    /**
     * Scope of data to which maintenance is applied.
     *
     * @return Scope of data to which maintenance is applied.
     */
    @UML(identifier="updateScope", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ScopeCode> getUpdateScopes();

    /**
     * Additional information about the range or extent of the resource.
     *
     * @return Additional information about the range or extent of the resource.
     */
    @UML(identifier="updateScopeDescription", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ScopeDescription> getUpdateScopeDescriptions();

    /**
     * Information regarding specific requirements for maintaining the resource.
     *
     * @return Information regarding specific requirements for maintaining the resource.
     *
     * @since 2.1
     */
    @UML(identifier="maintenanceNote", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends InternationalString> getMaintenanceNotes();

    /**
     * Identification of, and means of communicating with,
     * person(s) and organization(s) with responsibility for maintaining the metadata.
     *
     * @return Means of communicating with person(s) and organization(s) with responsibility
     *         for maintaining the metadata.
     *
     * @since 2.1
     */
    @UML(identifier="contact", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ResponsibleParty> getContacts();
}
