/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE direct dependencies
import java.util.Set;
import java.util.Date;
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.referencing.ReferenceSystem;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Root entity which defines metadata about a resource or resources.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @revisit Many method in this interface returns an array. Should we returns a
 *          {@link java.util.List} or {@link java.util.Set} instead?
 */
///@UML (identifier="MD_MetaData")
public interface MetaData {
    /**
     * Unique identifier for this metadata file, or <code>null</code> if none.
     */
/// @UML (identifier="fileIdentifier", obligation=OPTIONAL)
    String getFileIdentifier();

    /**
     * Language used for documenting metadata.
     */
/// @UML (identifier="language", obligation=CONDITIONAL)
    Locale getLanguage();

    /**
     * Full name of the character coding standard used for the metadata set.
     *
     * @revisit We should use {@link java.nio.charset.Charset} if J2SE 1.4 is allowed.
     */
/// @UML (identifier="characterSet", obligation=OPTIONAL)
    String getCharacterSet();

    /**
     * File identifier of the metadata to which this metadata is a subset (child).
     */
/// @UML (identifier="parentIdentifier", obligation=OPTIONAL)
    String getParentIdentifier();

    /**
     * Scope to which the metadata applies.
     *
     * @revisit Add a link toward annex I in ISO 19115 (to be inserted in the javadoc).
     */
/// @UML (identifier="hierarchyLevel", obligation=OPTIONAL)
    Set/*<ScopeCode>*/ getHierarchyLevels();

    /**
     * Name of the hierarchy levels for which the metadata is provided.
     *
     * @revisit Should we merge with {@link #getHierarchyLevels} using a {@link java.util.Map}?
     */
/// @UML (identifier="hierarchyLevelName", obligation=OPTIONAL)
    Set/*<String>*/ getHierarchyLevelNames();

    /**
     * Party responsible for the metadata information.
     */
/// @UML (identifier="contact", obligation=MANDATORY)
    ResponsibleParty getContact();

    /**
     * Date that the metadata was created.
     */
/// @UML (identifier="dateStamp", obligation=MANDATORY)
    Date getDateStamp();

    /**
     * Name of the metadata standard (including profile name) used.
     */
/// @UML (identifier="metadataStandardName", obligation=OPTIONAL)
    String getMetadataStandardName();

    /**
     * Version (profile) of the metadata standard used.
     */
/// @UML (identifier="metadataStandardVersion", obligation=OPTIONAL)
    String getMetadataStandardVersion();

    /**
     * Digital representation of spatial information in the dataset.
     */
/// @UML (identifier="spatialRepresentationInfo", obligation=OPTIONAL)
    Set/*<SpatialRepresentation>*/ getSpatialRepresentationInfo();

    /**
     * Description of the spatial and temporal reference systems used in the dataset.
     *
     * @revisit ISO 19115 use a <code>MD_ReferenceSystem</code> object instead of the ISO 19111 object.
     */
/// @UML (identifier="referenceSystemInfo", obligation=OPTIONAL)
     Set/*<ReferenceSystem>*/ getReferenceSystemInfo();

     /**
      * Information describing metadata extensions.
      */
/// @UML (identifier="metadataExtensionInfo", obligation=OPTIONAL)
     Set/*<MetadataExtensionInformation>*/ getMetadataExtensionInfo();

     /**
      * Basic information about the resource(s) to which the metadata applies.
      */
/// @UML (identifier="identificationInfo", obligation=MANDATORY)
     Set/*<Identification>*/ getIdentificationInfo();

     /**
      * Provides information about the feature catalogue and describes the coverage and
      * image data characteristics.
      */
/// @UML (identifier="contentInfo", obligation=OPTIONAL)
     Set/*<ContentInformation>*/ getContentInfo();

     /**
      * Provides information about the distributor of and options for obtaining the resource(s).
      */
/// @UML (identifier="distributionInfo", obligation=OPTIONAL)
     Distribution getDistributionInfo();

     /**
      * Provides overall assessment of quality of a resource(s).
      */
/// @UML (identifier="dataQualityInfo", obligation=OPTIONAL)
     Set/*<DataQuality>*/ getDataQualityInfo();

     /**
      * Provides information about the catalogue of rules defined for the portrayal of a resource(s).
      */
/// @UML (identifier="portrayalCatalogueInfo", obligation=OPTIONAL)
     Set/*<PortrayalCatalogueReference>*/ getPortrayalCatalogueInfo();

     /**
      * Provides restrictions on the access and use of data.
      */
/// @UML (identifier="metadataConstraints", obligation=OPTIONAL)
     Set/*<Constraints>*/ getMetadataConstraints();

     /**
      * Provides information about the conceptual schema of a dataset.
      */
/// @UML (identifier="applicationSchemaInfo", obligation=OPTIONAL)
     Set/*<ApplicationSchemaInformation>*/ getApplicationSchemaInfo();
     
     /**
      * Provides information about the frequency of metadata updates, and the scope of those updates.
      */
/// @UML (identifier="metadataMaintenance", obligation=OPTIONAL)
     MaintenanceInformation getMetadataMaintenance();
}
