/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2014 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.identification;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.MetadataScope;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.Format;
import org.opengis.metadata.extent.Extent;
import org.opengis.temporal.Duration;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Basic information required to uniquely identify a resource or resources.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="MD_Identification", specification=ISO_19115)
public interface Identification {
    /**
     * Citation for the resource.
     *
     * @return Citation for the resource.
     */
    @Profile(level=CORE)
    @UML(identifier="citation", obligation=MANDATORY, specification=ISO_19115)
    Citation getCitation();

    /**
     * Brief narrative summary of the resource.
     *
     * @return Brief narrative summary of the resource.
     */
    @Profile(level=CORE)
    @UML(identifier="abstract", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getAbstract();

    /**
     * Summary of the intentions with which the resource was developed.
     *
     * @return The intentions with which the resource was developed, or {@code null}.
     */
    @UML(identifier="purpose", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getPurpose();

    /**
     * Recognition of those who contributed to the resource.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * The return type will be changed to {@code Collection<? extends InternationalString>} in GeoAPI 4.0.
     * </div>
     *
     * @return Recognition of those who contributed to the resource.
     */
    @UML(identifier="credit", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getCredits();

    /**
     * Status of the resource.
     *
     * @return Status of the resource.
     */
    @UML(identifier="status", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Progress> getStatus();

    /**
     * Identification of, and means of communication with, person(s) and organisations
     * associated with the resource(s).
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ResponsibleParty} is replaced by the {@link Responsibility} parent interface.
     * This change will be applied in GeoAPI 4.0.
     * </div>
     *
     * @return Means of communication with person(s) and organisations(s) associated with the resource.
     *
     * @see org.opengis.metadata.Metadata#getContacts()
     */
    @Profile(level=CORE)
    @UML(identifier="pointOfContact", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ResponsibleParty> getPointOfContacts();

    /**
     * Methods used to spatially represent geographic information.
     *
     * @return Methods used to spatially represent geographic information.
     *
     * @since 3.1
     */
    @UML(identifier="spatialRepresentationType", obligation=OPTIONAL, specification=ISO_19115)
    Collection<SpatialRepresentationType> getSpatialRepresentationTypes();

    /**
     * Factor which provides a general understanding of the density of spatial data in the resource.
     * May also describe the range of resolutions in which a digital resource may be used.
     *
     * <blockquote><font size="-1"><b>Note:</b>
     * This element should be repeated when describing upper and lower range.
     * </font></blockquote>
     *
     * @return Factor which provides a general understanding of the density of spatial resource.
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="spatialResolution", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Resolution> getSpatialResolutions();

    /**
     * Smallest resolvable temporal period in a resource.
     *
     * @return Smallest resolvable temporal period in a resource.
     *
     * @since 3.1
     */
    @UML(identifier="temporalResolution", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Duration> getTemporalResolutions();

    /**
     * Main theme(s) of the resource.
     *
     * @return Main theme(s).
     *
     * @condition Mandatory if {@link MetadataScope#getResourceScope()} equals {@link ScopeCode#DATASET}
     *            or {@link ScopeCode#SERIES}.
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="topicCategory", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<TopicCategory> getTopicCategories();

    /**
     * Spatial and temporal extent of the resource.
     *
     * @return Spatial and temporal extent of the resource.
     *
     * @condition Mandatory with either a
     * {@linkplain org.opengis.metadata.extent.GeographicBoundingBox geographic bounding box} or a
     * {@linkplain org.opengis.metadata.extent.GeographicDescription geographic description} if
     * {@link MetadataScope#getResourceScope()} equals {@link ScopeCode#DATASET} or {@link ScopeCode#SERIES}.
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="extent", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Extent> getExtents();

    /**
     * Other documentation associated with the resource.
     *
     * <blockquote><font size="-1"><b>Example:</b>
     * related articles, publications, user guides, data dictionaries.
     * </font></blockquote>
     *
     * @return Other documentation associated with the resource.
     *
     * @since 3.1
     */
    @UML(identifier="additionalDocumentation", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getAdditionalDocumentations();

    /**
     * Code that identifies the level of processing in the producers coding system of a resource.
     *
     * <blockquote><font size="-1"><b>Example:</b>
     * NOAA level 1B.
     * </font></blockquote>
     *
     * @return Code that identifies the level of processing in the producers coding system of a resource.
     *
     * @since 3.1
     *
     * @see org.opengis.metadata.content.CoverageDescription#getProcessingLevelCode()
     */
    @UML(identifier="processingLevel", obligation=OPTIONAL, specification=ISO_19115)
    Identifier getProcessingLevel();

    /**
     * Information about the frequency of resource updates, and the scope of those updates.
     *
     * @return Frequency and scope of resource updates.
     */
    @UML(identifier="resourceMaintenance", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends MaintenanceInformation> getResourceMaintenances();

    /**
     * Graphic that illustrates the resource(s) (should include a legend for the graphic).
     *
     * @return A graphic that illustrates the resource(s).
     */
    @UML(identifier="graphicOverview", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends BrowseGraphic> getGraphicOverviews();

    /**
     * Description of the format of the resource(s).
     *
     * @return Description of the format.
     */
    @UML(identifier="resourceFormat", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Format> getResourceFormats();

    /**
     * Category keywords, their type, and reference source.
     *
     * @return Category keywords, their type, and reference source.
     */
    @UML(identifier="descriptiveKeywords", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Keywords> getDescriptiveKeywords();

    /**
     * Basic information about specific application(s) for which the resource(s)
     * has/have been or is being used by different users.
     *
     * @return Information about specific application(s) for which the resource(s)
     *         has/have been or is being used.
     */
    @UML(identifier="resourceSpecificUsage", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Usage> getResourceSpecificUsages();

    /**
     * Information about constraints which apply to the resource(s).
     *
     * @return Constraints which apply to the resource(s).
     */
    @UML(identifier="resourceConstraints", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Constraints> getResourceConstraints();

    /**
     * Associated resource information.
     *
     * @return Associated resource information.
     *
     * @since 3.1
     */
    @UML(identifier="associatedResource", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends AssociatedResource> getAssociatedResources();

    /**
     * Aggregate dataset information.
     *
     * @return Aggregate dataset information.
     *
     * @since 2.1
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getAssociatedResources()}.
     */
    @Deprecated
    @UML(identifier="aggregationInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends AggregateInformation> getAggregationInfo();
}
