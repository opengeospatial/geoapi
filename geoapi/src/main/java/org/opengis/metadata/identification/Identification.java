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
package org.opengis.metadata.identification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.opengis.util.InternationalString;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.MetadataScope;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.Format;
import org.opengis.metadata.extent.Extent;
import org.opengis.temporal.Duration;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


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
     * @return citation for the resource.
     */
    @UML(identifier="citation", obligation=MANDATORY, specification=ISO_19115)
    Citation getCitation();

    /**
     * Brief narrative summary of the resource.
     *
     * @return brief narrative summary of the resource.
     */
    @UML(identifier="abstract", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getAbstract();

    /**
     * Summary of the intentions with which the resource was developed.
     *
     * @return the intentions with which the resource was developed, or {@code null}.
     */
    @UML(identifier="purpose", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getPurpose() {
        return null;
    }

    /**
     * Recognition of those who contributed to the resource.
     *
     * @return recognition of those who contributed to the resource.
     */
    @UML(identifier="credit", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getCredits() {
        return Collections.emptyList();
    }

    /**
     * Status of the resource.
     *
     * @return status of the resource.
     */
    @UML(identifier="status", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<Progress> getStatus() {
        return Collections.emptySet();          // Use Set instead of List for hash-safe final classes.
    }

    /**
     * Identification of, and means of communication with, person(s) and organisations
     * associated with the resource(s).
     *
     * @return means of communication with person(s) and organisations(s) associated with the resource.
     *
     * @see org.opengis.metadata.Metadata#getContacts()
     */
    @UML(identifier="pointOfContact", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Responsibility> getPointOfContacts() {
        return Collections.emptyList();
    }

    /**
     * Methods used to spatially represent geographic information.
     *
     * @return methods used to spatially represent geographic information.
     *
     * @since 3.1
     */
    @UML(identifier="spatialRepresentationType", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<SpatialRepresentationType> getSpatialRepresentationTypes() {
        return Collections.emptySet();          // Use Set instead of List for hash-safe final classes.
    }

    /**
     * Factor which provides a general understanding of the density of spatial data in the resource.
     * May also describe the range of resolutions in which a digital resource may be used.
     *
     * <div class="note"><b>Note:</b>
     * this element should be repeated when describing upper and lower range.
     * </div>
     *
     * @return factor which provides a general understanding of the density of spatial resource.
     *
     * @since 3.1
     */
    @UML(identifier="spatialResolution", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Resolution> getSpatialResolutions() {
        return Collections.emptyList();
    }

    /**
     * Smallest resolvable temporal period in a resource.
     *
     * @return smallest resolvable temporal period in a resource.
     *
     * @since 3.1
     */
    @UML(identifier="temporalResolution", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Duration> getTemporalResolutions() {
        return Collections.emptyList();
    }

    /**
     * Main theme(s) of the resource.
     *
     * @return main theme(s).
     *
     * @condition Mandatory if {@link MetadataScope#getResourceScope()} equals {@link ScopeCode#DATASET}
     *            or {@link ScopeCode#SERIES}.
     *
     * @since 3.1
     */
    @UML(identifier="topicCategory", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<TopicCategory> getTopicCategories();

    /**
     * Spatial and temporal extent of the resource.
     *
     * @return spatial and temporal extent of the resource.
     *
     * @condition Mandatory with either a
     * {@linkplain org.opengis.metadata.extent.GeographicBoundingBox geographic bounding box} or a
     * {@linkplain org.opengis.metadata.extent.GeographicDescription geographic description} if
     * {@link MetadataScope#getResourceScope()} equals {@link ScopeCode#DATASET} or {@link ScopeCode#SERIES}.
     *
     * @since 3.1
     */
    @UML(identifier="extent", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Extent> getExtents();

    /**
     * Other documentation associated with the resource.
     *
     * <div class="note"><b>Example:</b>
     * related articles, publications, user guides, data dictionaries.
     * </div>
     *
     * @return other documentation associated with the resource.
     *
     * @since 3.1
     */
    @UML(identifier="additionalDocumentation", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getAdditionalDocumentations() {
        return Collections.emptyList();
    }

    /**
     * Code that identifies the level of processing in the producers coding system of a resource.
     *
     * <div class="note"><b>Example:</b>
     * NOAA level 1B.
     * </div>
     *
     * @return code that identifies the level of processing in the producers coding system of a resource.
     *
     * @since 3.1
     *
     * @see org.opengis.metadata.content.CoverageDescription#getProcessingLevelCode()
     */
    @UML(identifier="processingLevel", obligation=OPTIONAL, specification=ISO_19115)
    default Identifier getProcessingLevel() {
        return null;
    }

    /**
     * Information about the frequency of resource updates, and the scope of those updates.
     *
     * @return frequency and scope of resource updates.
     */
    @UML(identifier="resourceMaintenance", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends MaintenanceInformation> getResourceMaintenances() {
        return Collections.emptyList();
    }

    /**
     * Graphic that illustrates the resource(s) (should include a legend for the graphic).
     *
     * @return a graphic that illustrates the resource(s).
     */
    @UML(identifier="graphicOverview", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends BrowseGraphic> getGraphicOverviews() {
        return Collections.emptyList();
    }

    /**
     * Description of the format of the resource(s).
     *
     * @return description of the format.
     *
     * @see org.opengis.metadata.distribution.Distribution#getDistributionFormats()
     */
    @UML(identifier="resourceFormat", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Format> getResourceFormats() {
        return Collections.emptyList();
    }

    /**
     * Category keywords, their type, and reference source.
     *
     * @return category keywords, their type, and reference source.
     */
    @UML(identifier="descriptiveKeywords", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Keywords> getDescriptiveKeywords() {
        return Collections.emptyList();
    }

    /**
     * Basic information about specific application(s) for which the resource(s)
     * has/have been or is being used by different users.
     *
     * @return information about specific application(s) for which the resource(s)
     *         has/have been or is being used.
     */
    @UML(identifier="resourceSpecificUsage", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Usage> getResourceSpecificUsages() {
        return Collections.emptyList();
    }

    /**
     * Information about constraints which apply to the resource(s).
     *
     * @return constraints which apply to the resource(s).
     */
    @UML(identifier="resourceConstraints", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Constraints> getResourceConstraints() {
        return Collections.emptyList();
    }

    /**
     * Associated resource information.
     *
     * @return associated resource information.
     *
     * @since 3.1
     */
    @UML(identifier="associatedResource", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends AssociatedResource> getAssociatedResources() {
        return Collections.emptyList();
    }

    /**
     * Aggregate dataset information.
     *
     * @return aggregate dataset information.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getAssociatedResources()}.
     */
    @Deprecated(since="3.1")
    @UML(identifier="aggregationInfo", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<? extends AggregateInformation> getAggregationInfo() {
        ArrayList<AggregateInformation> info = new ArrayList<>();
        for (final AssociatedResource res : getAssociatedResources()) {
            info.add(new AggregateInformation() {
                @Override public Citation        getName()                 {return res.getName();}
                @Override public AssociationType getAssociationType()      {return res.getAssociationType();}
                @Override public InitiativeType  getInitiativeType()       {return res.getInitiativeType();}
                @Override public Citation        getMetadataReference()    {return res.getMetadataReference();}
                @Override public Citation        getAggregateDataSetName() {return res.getName();}
                @Override public Identifier      getAggregateDataSetIdentifier() {
                    Citation name = res.getName();
                    if (name != null) {
                        Iterator<? extends Identifier> it = name.getIdentifiers().iterator();
                        if (it.hasNext()) return it.next();
                    }
                    return null;
                }
            });
        }
        return info;
    }
}
