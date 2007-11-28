/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
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
 * @author <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @author Cory Horner (Refractions Research)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_MaintenanceInformation", specification=ISO_19115)
public interface MaintenanceInformation {
    /**
     * Frequency with which changes and additions are made to the resource after the
     * initial resource is completed.
     */
    @UML(identifier="maintenanceAndUpdateFrequency", obligation=MANDATORY, specification=ISO_19115)
    MaintenanceFrequency getMaintenanceAndUpdateFrequency();

    /**
     * Scheduled revision date for resource.
     */
    @UML(identifier="dateOfNextUpdate", obligation=OPTIONAL, specification=ISO_19115)
    Date getDateOfNextUpdate();

    /**
     * Maintenance period other than those defined.
     *
     * @return The period, in milliseconds.
     * @unitof PeriodDuration
     */
    @UML(identifier="userDefinedMaintenanceFrequency", obligation=OPTIONAL, specification=ISO_19115)
    PeriodDuration getUserDefinedMaintenanceFrequency();

    /**
     * Scope of data to which maintenance is applied.
     *
     * @deprecated use getUpdateScopes
     */
    ScopeCode getUpdateScope();

    /**
     * Additional information about the range or extent of the resource.
     *
     * @deprecated use getUpdateScopeDescriptions
     */
    ScopeDescription getUpdateScopeDescription();

    /**
     * Scope of data to which maintenance is applied.
     */
    @UML(identifier="updateScope", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ScopeCode> getUpdateScopes();

    /**
     * Additional information about the range or extent of the resource.
     */
    @UML(identifier="updateScopeDescription", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ScopeDescription> getUpdateScopeDescriptions();

    /**
     * Information regarding specific requirements for maintaining the resource.
     *
     * @deprecated use getMaintenanceNotes()
     */
    InternationalString getMaintenanceNote();

    /**
     * Information regarding specific requirements for maintaining the resource.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="maintenanceNote", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends InternationalString> getMaintenanceNotes();

    /**
     * Identification of, and means of communicating with,
     * person(s) and organization(s) with responsibility for maintaining the metadata.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="contact", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ResponsibleParty> getContacts();
}
