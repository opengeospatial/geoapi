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
import java.util.Date;
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.metadata.identification.Identification;
import org.opengis.referencing.ReferenceSystem;


/**
 * Root entity which defines metadata about a resource or resources.
 *
 * @UML datatype MD_MetaData
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @revisit Many method in this interface returns an array. Should we returns a
 *          {@link java.util.List} or {@link java.util.Set} instead?
 */
public interface MetaData {
    /**
     * Unique identifier for this metadata file, or <code>null</code> if none.
     *
     * @UML optional fileIdentifier
     */
    String getFileIdentifier();

    /**
     * Language used for documenting metadata.
     *
     * @UML conditional language
     */
    Locale getLanguage();

    /**
     * Full name of the character coding standard used for the metadata set.
     *
     * @UML optional characterSet
     *
     * @revisit We should use {@link java.nio.charset.Charset} if J2SE 1.4 is allowed.
     */
    String getCharacterSet();

    /**
     * File identifier of the metadata to which this metadata is a subset (child).
     *
     * @UML optional parentIdentifier
     */
    String getParentIdentifier();

    /**
     * Scope to which the metadata applies.
     *
     * @UML optional hierarchyLevel
     *
     * @revisit Add a link toward annex I in ISO 19115 (to be inserted in the javadoc).
     */
    ScopeCode[] getHierarchyLevels();

    /**
     * Name of the hierarchy levels for which the metadata is provided.
     *
     * @UML optional hierarchyLevelName
     *
     * @revisit Should we merge with {@link #getHierarchyLevel} using a {@link java.util.Map}?
     */
    String[] getHierarchyLevelNames();

    /**
     * Party responsible for the metadata information.
     *
     * @UML mandatory contact
     */
    Contact getContact();

    /**
     * Date that the metadata was created.
     *
     * @UML mandatory dateStamp
     */
    Date getDateStamp();

    /**
     * Name of the metadata standard (including profile name) used.
     *
     * @UML optional metadataStandardName
     */
    String getMetadataStandardName();

    /**
     * Version (profile) of the metadata standard used.
     *
     * @UML optional metadataStandardVersion
     */
    String getMetadataStandardVersion();

    /**
     * Digital representation of spatial information in the dataset.
     *
     * @UML optional spatialRepresentationInfo
     */
    SpatialRepresentation[] getSpatialRepresentationInfo();

    /**
     * Description of the spatial and temporal reference systems used in the dataset.
     *
     * @UML optional referenceSystemInfo
     *
     * @revisit ISO 19115 use a <code>MD_ReferenceSystem</code> object instead of the ISO 19111 object.
     */
     ReferenceSystem[] getReferenceSystemInfo();

     /**
      * Information describing metadata extensions.
      *
      * @UML optional metadataExtensionInfo
      */
//     MetadataExtensionInformation[] getMetadataExtensionInfo();

     /**
      * Basic information about the resource(s) to which the metadata applies.
      *
      * @UML mandatory identificationInfo
      */
     Identification[] getIdentificationInfo();

     /**
      * Provides information about the feature catalogue and describes the coverage and
      * image data characteristics.
      *
      * @UML optional contentInfo
      */
     ContentInformation[] getContentInfo();

     /**
      * Provides information about the distributor of and options for obtaining the resource(s).
      *
      * @UML optional distributionInfo
      */
     Distribution getDistributionInfo();

     /**
      * Provides overall assessment of quality of a resource(s).
      *
      * @UML optional dataQualityInfo
      */
     DataQuality[] dataQualityInfo();

     /**
      * Provides information about the catalogue of rules defined for the portrayal of a resource(s).
      *
      * @UML optional portrayalCatalogueInfo
      */
     PortrayalCatalogueReference[] getPortrayalCatalogueInfo();

     /**
      * Provides restrictions on the access and use of data.
      *
      * @UML optional metadataConstraints
      */
     Constraints[] getMetadataConstraints();

     /**
      * Provides information about the conceptual schema of a dataset.
      *
      * @UML optional applicationSchemaInfo
      */
//     ApplicationSchemaInformation[] getApplicationSchemaInfo();
     
     /**
      * Provides information about the frequency of metadata updates, and the scope of those updates.
      *
      * @UML optional metadataMaintenance
      */
     MaintenanceInformation getMetadataMaintenance();
}
