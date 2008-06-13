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
package org.opengis.metadata;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.metadata.identification.CharacterSet;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Root entity which defines metadata about a resource or resources.
 *
 * @author <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @author Cory Horner (Refractions Research)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="MD_MetaData", specification=ISO_19115)
public interface MetaData {
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
    @UML(identifier="characterSet", obligation=CONDITIONAL, specification=ISO_19115)
    CharacterSet getCharacterSet();

    /**
     * File identifier of the metadata to which this metadata is a subset (child).
     */
    @UML(identifier="parentIdentifier", obligation=CONDITIONAL, specification=ISO_19115)
    String getParentIdentifier();

    /**
     * Scope to which the metadata applies.
     */
    @UML(identifier="hierarchyLevel", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<ScopeCode> getHierarchyLevels();

    /**
     * Name of the hierarchy levels for which the metadata is provided.
     */
    @UML(identifier="hierarchyLevelName", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<String> getHierarchyLevelNames();

    /**
     * Party responsible for the metadata information.
     *
     * @deprecated Replaced by {@link #getContacts}.
     */
    ResponsibleParty getContact();

    /**
     * Party responsible for the metadata information.
     *
     * @since GeoAPI 2.1
     */
    @Profile (level=CORE)
    @UML(identifier="contact", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends ResponsibleParty> getContacts();

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
     * Uniformed Resource Identifier (URI) of the dataset to which the metadata applies.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="dataSetURI", obligation=OPTIONAL, specification=ISO_19115)
    String getDataSetUri();

    /**
     * Provides information about an alternatively used localized character
     * string for a linguistic extension
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="locale", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Locale> getLocales();

    /**
     * Digital representation of spatial information in the dataset.
     */
    @UML(identifier="spatialRepresentationInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends SpatialRepresentation> getSpatialRepresentationInfo();

    /**
     * Description of the spatial and temporal reference systems used in the dataset.
     */
    @Profile (level=CORE)
    @UML(identifier="referenceSystemInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ReferenceSystem> getReferenceSystemInfo();

    /**
     * Information describing metadata extensions.
     */
    @UML(identifier="metadataExtensionInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends MetadataExtensionInformation> getMetadataExtensionInfo();

    /**
     * Basic information about the resource(s) to which the metadata applies.
     */
    @Profile (level=CORE)
    @UML(identifier="identificationInfo", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Identification> getIdentificationInfo();

    /**
     * Provides information about the feature catalogue and describes the coverage and
     * image data characteristics.
     */
    @UML(identifier="contentInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ContentInformation> getContentInfo();

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
    Collection<? extends DataQuality> getDataQualityInfo();

    /**
     * Provides information about the catalogue of rules defined for the portrayal of a resource(s).
     */
    @UML(identifier="portrayalCatalogueInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends PortrayalCatalogueReference> getPortrayalCatalogueInfo();

    /**
     * Provides restrictions on the access and use of data.
     */
    @UML(identifier="metadataConstraints", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Constraints> getMetadataConstraints();

    /**
     * Provides information about the conceptual schema of a dataset.
     */
    @UML(identifier="applicationSchemaInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ApplicationSchemaInformation> getApplicationSchemaInfo();

    /**
     * Provides information about the frequency of metadata updates, and the scope of those updates.
     */
    @UML(identifier="metadataMaintenance", obligation=OPTIONAL, specification=ISO_19115)
    MaintenanceInformation getMetadataMaintenance();
}
