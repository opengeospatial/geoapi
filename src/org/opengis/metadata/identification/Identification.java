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
import java.util.Set;
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.Format;
import org.opengis.util.InternationalString;


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
     * @UML mandatory abstract
     */
    InternationalString getAbstract();

    /**
     * Summary of the intentions with which the resource(s) was developed.
     *
     * @UML optional purpose
     */
    InternationalString getPurpose();

    /**
     * Recognition of those who contributed to the resource(s).
     *
     * @UML optional credit
     */
    List/*<String>*/ getCredits();

    /**
     * Status of the resource(s).
     *
     * @UML optional status
     */
    Set/*<Progress>*/ getStatus();

    /**
     * Identification of, and means of communication with, person(s) and organizations(s)
     * associated with the resource(s).
     *
     * @UML optional pointOfContact
     */
    Set/*<ResponsibleParty>*/ getPointOfContacts();

    /**
     * Provides information about the frequency of resource updates, and the scope of those updates.
     *
     * @UML optional resourceMaintenance
     */
    Set/*<MaintenanceInformation>*/ getResourceMaintenance();

    /**
     * Provides a graphic that illustrates the resource(s) (should include a legend for the graphic).
     *
     * @UML optional graphicOverview
     */
    Set/*<BrowseGraphic>*/ getGraphicOverviews();

    /**
     * Provides a description of the format of the resource(s).
     *
     * @UML optional resourceFormat
     */
    Set/*<Format>*/ getResourceFormat();

    /**
     * Provides category keywords, their type, and reference source.
     *
     * @UML optional descriptiveKeywords
     */
    Set/*<Keywords>*/ getDescriptiveKeywords();

    /**
     * Provides basic information about specific application(s) for which the resource(s)
     * has/have been or is being used by different users.
     *
     * @UML optional resourceSpecificUsage
     */
    Set/*<Usage>*/ getResourceSpecificUsages();

    /**
     * Provides information about constraints which apply to the resource(s).
     *
     * @UML optional resourceConstraints
     */
    Set/*<Constraints>*/ getResourceConstraints();
}
