/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.maintenance;

// J2SE direct dependencies
import java.util.Date;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information about the scope and frequency of updating.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_MaintenanceInformation")
public interface MaintenanceInformation {
    /**
     * Frequency with which changes and additions are made to the resource after the
     * initial resource is completed.
     */
/// @UML (identifier="maintenanceAndUpdateFrequency", obligation=MANDATORY)
    MaintenanceFrequency getMaintenanceAndUpdateFrequency();

    /**
     * Scheduled revision date for resource.
     */
/// @UML (identifier="dateOfNextUpdate", obligation=OPTIONAL)
    Date getDateOfNextUpdate();

    /**
     * Maintenance period other than those defined.
     *
     * @return The period, in milliseconds.
     * @unitof PeriodDuration
     */
/// @UML (identifier="userDefinedMaintenanceFrequency", obligation=OPTIONAL)
    long getUserDefinedMaintenanceFrequency();

    /**
     * Scope of data to which maintenance is applied.
     */
/// @UML (identifier="updateScope", obligation=OPTIONAL)
    ScopeCode getUpdateScope();

    /**
     * Additional information about the range or extent of the resource.
     */
/// @UML (identifier="updateScopeDescription", obligation=OPTIONAL)
    ScopeDescription getUpdateScopeDescription();

    /**
     * Information regarding specific requirements for maintaining the resource.
     */
/// @UML (identifier="maintenanceNote", obligation=CONDITIONAL)
    InternationalString getMaintenanceNote();
}
