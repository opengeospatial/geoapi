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
package org.opengis.metadata.identification;

// J2SE direct dependencies
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.Format;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;
import static org.opengis.annotation.Specification.*;


/**
 * Basic information required to uniquely identify a resource or resources.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="MD_Identification", specification=ISO_19115)
public interface Identification {
    /**
     * Citation data for the resource(s).
     */
    @Profile (level=CORE)
    @UML(identifier="citation", obligation=MANDATORY, specification=ISO_19115)
    Citation getCitation();

    /**
     * Brief narrative summary of the content of the resource(s).
     */
    @Profile (level=CORE)
    @UML(identifier="abstract", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getAbstract();

    /**
     * Summary of the intentions with which the resource(s) was developed.
     */
    @UML(identifier="purpose", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getPurpose();

    /**
     * Recognition of those who contributed to the resource(s).
     */
    @UML(identifier="credit", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getCredits();

    /**
     * Status of the resource(s).
     */
    @UML(identifier="status", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Progress> getStatus();

    /**
     * Identification of, and means of communication with, person(s) and organizations(s)
     * associated with the resource(s).
     */
    @Profile (level=CORE)
    @UML(identifier="pointOfContact", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ResponsibleParty> getPointOfContacts();

    /**
     * Provides information about the frequency of resource updates, and the scope of those updates.
     */
    @UML(identifier="resourceMaintenance", obligation=OPTIONAL, specification=ISO_19115)
    Collection<MaintenanceInformation> getResourceMaintenance();

    /**
     * Provides a graphic that illustrates the resource(s) (should include a legend for the graphic).
     */
    @UML(identifier="graphicOverview", obligation=OPTIONAL, specification=ISO_19115)
    Collection<BrowseGraphic> getGraphicOverviews();

    /**
     * Provides a description of the format of the resource(s).
     */
    @UML(identifier="resourceFormat", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Format> getResourceFormat();

    /**
     * Provides category keywords, their type, and reference source.
     */
    @UML(identifier="descriptiveKeywords", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Keywords> getDescriptiveKeywords();

    /**
     * Provides basic information about specific application(s) for which the resource(s)
     * has/have been or is being used by different users.
     */
    @UML(identifier="resourceSpecificUsage", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Usage> getResourceSpecificUsages();

    /**
     * Provides information about constraints which apply to the resource(s).
     */
    @UML(identifier="resourceConstraints", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Constraints> getResourceConstraints();
}
