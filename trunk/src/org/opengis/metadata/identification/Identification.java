/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.Progress;
import org.opengis.metadata.Constraints;
import org.opengis.metadata.MaintenanceInformation;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.distribution.Format;


/**
 * Basic information required to uniquely identify a resource or resources.
 *
 * @UML datatype MD_Identification
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Identification {
    /**
     * Citation data for the resource(s).
     *
     * @UML mandatory citation
     */
    Citation getCitation();

    /**
     * Brief narrative summary of the content of the resource(s).
     *
     * @param  locale The desired locale for the abstract to be returned, or <code>null</code>
     *         for an abstract in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The abstract in the given locale.
     *         If no abstract is available in the given locale, then some default locale is used.
     * @UML mandatory abstract
     */
    String getAbstract(Locale locale);

    /**
     * Summary of the intentions with which the resource(s) was developed.
     *
     * @param  locale The desired locale for the summary to be returned, or <code>null</code>
     *         for a summary in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The summary in the given locale.
     *         If no summary is available in the given locale, then some default locale is used.
     * @UML optional purpose
     */
    String getPurpose(Locale locale);

    /**
     * Recognition of those who contributed to the resource(s).
     *
     * @UML optional credit
     */
    String[] getCredits();

    /**
     * Status of the resource(s).
     *
     * @UML optional status
     */
    Progress[] getStatus();

    /**
     * Identification of, and means of communication with, person(s) and organizations(s)
     * associated with the resource(s).
     *
     * @UML optional pointOfContact
     */
    ResponsibleParty[] getPointOfContacts();

    /**
     * Provides information about the frequency of resource updates, and the scope of those updates.
     *
     * @UML optional resourceMaintenance
     */
    MaintenanceInformation[] getResourceMaintenance();

    /**
     * Provides a graphic that illustrates the resource(s) (should include a legend for the graphic).
     *
     * @UML optional graphicOverview
     */
    BrowseGraphic[] getGraphicOverviews();

    /**
     * Provides a description of the format of the resource(s).
     *
     * @UML optional resourceFormat
     */
    Format[] getResourceFormat();

    /**
     * Provides category keywords, their type, and reference source.
     *
     * @UML optional descriptiveKeywords
     */
    Keywords[] getDescriptiveKeywords();

    /**
     * Provides basic information about specific application(s) for which the resource(s)
     * has/have been or is being used by different users.
     *
     * @UML optional resourceSpecificUsage
     */
    Usage[] getResourceSpecificUsages();

    /**
     * Provides information about constraints which apply to the resource(s).
     *
     * @UML optional resourceConstraints
     */
    Constraints[] getResourceConstraints();
}
