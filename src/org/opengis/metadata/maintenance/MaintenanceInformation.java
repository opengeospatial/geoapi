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
import java.util.Locale;


/**
 * Information about the scope and frequency of updating.
 *
 * @UML datatype MD_MaintenanceInformation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface MaintenanceInformation {
    /**
     * Frequency with which changes and additions are made to the resource after the
     * initial resource is completed.
     *
     * @UML mandatory maintenanceAndUpdateFrequency
     */
    MaintenanceFrequency getMaintenanceAndUpdateFrequency();

    /**
     * Scheduled revision date for resource.
     *
     * @UML optional dateOfNextUpdate
     */
    Date getDateOfNextUpdate();

    /**
     * Maintenance period other than those defined.
     *
     * @return The period, in milliseconds.
     * @UML optional userDefinedMaintenanceFrequency
     * @unitof PeriodDuration
     */
    long getUserDefinedMaintenanceFrequency();

    /**
     * Scope of data to which maintenance is applied.
     *
     * @UML optional updateScope
     */
    ScopeCode getUpdateScope();

    /**
     * Additional information about the range or extent of the resource.
     *
     * @UML optional updateScopeDescription
     */
    ScopeDescription getUpdateScopeDescription();

    /**
     * Information regarding specific requirements for maintaining the resource.
     *
     * @param  locale The desired locale for the note to be returned, or <code>null</code>
     *         for a note in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The note in the given locale.
     *         If no note is available in the given locale, then some default locale is used.
     * @UML conditional maintenanceNote
     */
    String getMaintenanceNote(Locale locale);
}
