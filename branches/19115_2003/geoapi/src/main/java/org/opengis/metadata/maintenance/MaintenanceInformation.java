/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/maintenance/MaintenanceInformation.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.maintenance;

// J2SE direct dependencies
import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Collection;
import java.util.Date;

import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;


/**
 * Information about the scope and frequency of updating.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_MaintenanceInformation", specification=ISO_19115)
public interface MaintenanceInformation extends MetadataEntity{
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
    long getUserDefinedMaintenanceFrequency();

    /**
     * Scope of data to which maintenance is applied.
     */
    @UML(identifier="updateScope", obligation=OPTIONAL, specification=ISO_19115)
     Collection /*<ScopeCode>*/ getUpdateScope();

    /**
     * Additional information about the range or extent of the resource.
     */
    @UML(identifier="updateScopeDescription", obligation=OPTIONAL, specification=ISO_19115)
     Collection /*<ScopeDescription>*/ getUpdateScopeDescription();

    /**
     * Information regarding specific requirements for maintaining the resource.
     */
    @UML(identifier="maintenanceNote", obligation=CONDITIONAL, specification=ISO_19115)
    Collection /*<InternationalString>*/ getMaintenanceNote();
    
    /**
     * Return the contact data
     */
    @UML(identifier="contact", obligation=CONDITIONAL, specification=ISO_19115)
    Collection /*<ResponsibleParty>*/ getContact() ;
    
}
