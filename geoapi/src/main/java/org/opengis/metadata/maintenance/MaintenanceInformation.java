/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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

import java.util.Date;
import java.util.List;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.opengis.metadata.citation.DateType;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.temporal.PeriodDuration;
import org.opengis.util.InternationalString;
import org.opengis.geoapi.internal.Legacy;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the scope and frequency of updating.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_MaintenanceInformation", specification=ISO_19115)
public interface MaintenanceInformation {
    /**
     * Frequency with which changes and additions are made to the resource after the
     * initial resource is completed.
     *
     * @return frequency with which changes and additions are made to the resource.
     */
    @UML(identifier="maintenanceAndUpdateFrequency", obligation=OPTIONAL, specification=ISO_19115)
    default MaintenanceFrequency getMaintenanceAndUpdateFrequency() {
        return null;
    }

    /**
     * Date information associated with maintenance of resource.
     * Returns an empty collection if none.
     *
     * @return date information associated with maintenance of resource.
     *
     * @since 3.1
     */
    @UML(identifier="maintenanceDate", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends CitationDate> getMaintenanceDates() {
        return List.of();
    }

    /**
     * Scheduled revision date for resource.
     *
     * @return scheduled revision date, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getMaintenanceDates()} in order to enable inclusion
     *             of a {@link org.opengis.metadata.citation.DateType} to describe the type of the date.
     *             Note that {@link org.opengis.metadata.citation.DateType#NEXT_UPDATE} was added to that code list.
     */
    @Deprecated(since="3.1")
    @UML(identifier="dateOfNextUpdate", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Date getDateOfNextUpdate() {
        return Legacy.getDate(getMaintenanceDates(), DateType.NEXT_UPDATE);
    }

    /**
     * Maintenance period other than those defined.
     *
     * <div class="warning"><b>Upcoming API change — standard time API</b><br>
     * The return type of this method may change in GeoAPI 4.0. It may be replaced by
     * the {@link java.time.temporal.TemporalAmount} from the standard time Java API.
     * In order to anticipate that change, callers should assign the returned value
     * to a {@code TemporalAmount} type instead of {@code PeriodDuration}.
     * </div>
     *
     * @return the maintenance period, or {@code null}.
     */
    @UML(identifier="userDefinedMaintenanceFrequency", obligation=OPTIONAL, specification=ISO_19115)
    default PeriodDuration getUserDefinedMaintenanceFrequency() {
        return null;
    }

    /**
     * Type of resource and / or extent to which the maintenance information applies.
     *
     * @return type of resource and / or extent to which the maintenance information applies, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="maintenanceScope", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Scope> getMaintenanceScopes() {
        return List.of();
    }

    /**
     * Scope of data to which maintenance is applied.
     *
     * @return scope of data to which maintenance is applied.
     *
     * @deprecated As of ISO 19115:2014, {@code getUpdateScopes()} and {@link #getUpdateScopeDescriptions()}
     *             were combined into {@link #getMaintenanceScopes()} in order to allow specifying a scope
     *             that includes a spatial and temporal extent.
     */
    @Deprecated(since="3.1")
    @UML(identifier="updateScope", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<ScopeCode> getUpdateScopes() {
        LinkedHashSet<ScopeCode> codes = new LinkedHashSet<>();
        getMaintenanceScopes().forEach((scope) -> {
            codes.add(scope.getLevel());
        });
        return codes;
    }

    /**
     * Additional information about the range or extent of the resource.
     *
     * @return additional information about the range or extent of the resource.
     *
     * @deprecated As of ISO 19115:2014, {@link #getUpdateScopes()} and {@code getUpdateScopeDescriptions()}
     *             were combined into {@link #getMaintenanceScopes()} in order to allow specifying a scope
     *             that includes a spatial and temporal extent.
     */
    @Deprecated(since="3.1")
    @UML(identifier="updateScopeDescription", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<? extends ScopeDescription> getUpdateScopeDescriptions() {
        for (Scope scope : getMaintenanceScopes()) {
            Collection<? extends ScopeDescription> desc = scope.getLevelDescription();
            if (desc != null) return desc;
        }
        return List.of();
    }

    /**
     * Information regarding specific requirements for maintaining the resource.
     *
     * @return information regarding specific requirements for maintaining the resource.
     */
    @UML(identifier="maintenanceNote", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getMaintenanceNotes() {
        return List.of();
    }

    /**
     * Identification of, and means of communicating with,
     * person(s) and organization(s) with responsibility for maintaining the resource.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ResponsibleParty} is replaced by the {@link Responsibility} parent interface.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return means of communicating with person(s) and organization(s) with responsibility
     *         for maintaining the resource.
     */
    @UML(identifier="contact", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<? extends ResponsibleParty> getContacts() {
        return List.of();
    }
}
