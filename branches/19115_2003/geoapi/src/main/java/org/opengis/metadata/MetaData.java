/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/MetaData.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE direct dependencies
import static org.opengis.annotation.ComplianceLevel.CORE;
import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.opengis.annotation.Profile;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.referencing.ReferenceSystem;


/**
 * Root entity which defines metadata about a resource or resources.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="MD_MetaData", specification=ISO_19115)
public interface MetaData extends MetadataEntity {
    /**
     * Unique identifier for this metadata file, or {@code null} if none.
     */
    @Profile (level=CORE)
    @UML(identifier="fileIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    String getFileIdentifier();

    /**
     * Language used for documenting metadata.
     */
    @Profile (level=CORE)
    @UML(identifier="language", obligation=CONDITIONAL, specification=ISO_19115)
    Locale getLanguage();

    /**
     * Full name of the character coding standard used for the metadata set.
     */
    @Profile (level=CORE)
    @UML(identifier="characterSet", obligation=OPTIONAL, specification=ISO_19115)
    Charset getCharacterSet();

    /**
     * File identifier of the metadata to which this metadata is a subset (child).
     */
    @UML(identifier="parentIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    String getParentIdentifier();

    /**
     * Scope to which the metadata applies.
     */
    @UML(identifier="hierarchyLevel", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ScopeCode> getHierarchyLevels();

    /**
     * Name of the hierarchy levels for which the metadata is provided.
     */
    @UML(identifier="hierarchyLevelName", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getHierarchyLevelNames();

    /**
     * Party responsibles for the metadata information.
     */
    @Profile (level=CORE)
    @UML(identifier="contact", obligation=MANDATORY, specification=ISO_19115)
    Collection<ResponsibleParty> getContacts();

    /**
     * Date that the metadata was created.
     */
    @Profile (level=CORE)
    @UML(identifier="dateStamp", obligation=MANDATORY, specification=ISO_19115)
    Date getDateStamp();

    /**
     * Name of the metadata standard (including profile name) used.
     */
    @Profile (level=CORE)
    @UML(identifier="metadataStandardName", obligation=OPTIONAL, specification=ISO_19115)
    String getMetadataStandardName();

    /**
     * Version (profile) of the metadata standard used.
     */
    @Profile (level=CORE)
    @UML(identifier="metadataStandardVersion", obligation=OPTIONAL, specification=ISO_19115)
    String getMetadataStandardVersion();

    /**
     * Digital representation of spatial information in the dataset.
     */
    @UML(identifier="spatialRepresentationInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<SpatialRepresentation> getSpatialRepresentationInfo();

    /**
     * Description of the spatial and temporal reference systems used in the dataset.
     *
     * @todo ISO 19115 use a {@code MD_ReferenceSystem} object instead of the ISO 19111 object.
     */
    @Profile (level=CORE)
    @UML(identifier="referenceSystemInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ReferenceSystem> getReferenceSystemInfo();

    /**
     * Information describing metadata extensions.
     */
    @UML(identifier="metadataExtensionInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<MetadataExtensionInformation> getMetadataExtensionInfo();

    /**
     * Basic information about the resource(s) to which the metadata applies.
     */
    @Profile (level=CORE)
    @UML(identifier="identificationInfo", obligation=MANDATORY, specification=ISO_19115)
    Collection<Identification> getIdentificationInfo();

    /**
     * Provides information about the feature catalogue and describes the coverage and
     * image data characteristics.
     */
    @UML(identifier="contentInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ContentInformation> getContentInfo();

    /**
     * Provides information about the distributor of and options for obtaining the resource(s).
     */
    @Profile (level=CORE)
    @UML(identifier="distributionInfo", obligation=OPTIONAL, specification=ISO_19115)
    Distribution getDistributionInfo();

    /**
     * Provides overall assessment of quality of a resource(s).
     */
    @Profile (level=CORE)
    @UML(identifier="dataQualityInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<DataQuality> getDataQualityInfo();

    /**
     * Provides information about the catalogue of rules defined for the portrayal of a resource(s).
     */
    @UML(identifier="portrayalCatalogueInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<PortrayalCatalogueReference> getPortrayalCatalogueInfo();

    /**
     * Provides restrictions on the access and use of data.
     */
    @UML(identifier="metadataConstraints", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Constraints> getMetadataConstraints();

    /**
     * Provides information about the conceptual schema of a dataset.
     */
    @UML(identifier="applicationSchemaInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ApplicationSchemaInformation> getApplicationSchemaInfo();
     
    /**
     * Provides information about the frequency of metadata updates, and the scope of those updates.
     */
    @UML(identifier="metadataMaintenance", obligation=OPTIONAL, specification=ISO_19115)
    MaintenanceInformation getMetadataMaintenance();
    
    /**
     * Uniformed Resource Identifier (URI) of the dataset to which the metadata applies
     */
    @UML(identifier="dataSetURI", obligation=OPTIONAL, specification=ISO_19115)
    URI getDataSetURI();
}
