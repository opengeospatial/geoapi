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
package org.opengis.metadata;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.nio.charset.Charset;

import org.opengis.metadata.acquisition.AcquisitionInformation;
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
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Metadata", specification=ISO_19115)
public interface Metadata {
    /**
     * Unique identifier for this metadata file, or {@code null} if none.
     *
     * @return Unique identifier for this metadata file, or {@code null}.
     */
    @Profile(level=CORE)
    @UML(identifier="fileIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    String getFileIdentifier();

    /**
     * Language used for documenting metadata.
     *
     * @return Language used for documenting metadata, or {@code null}.
     *
     * @departure historic
     *   GeoAPI has kept the <code>language</code> and <code>characterSet</code> properties as defined in ISO 19115:2003.
     *   The ISO 19115:2014 revision merged the language and character encoding information into a single class
     *   (namely <code>PT_Locale</code>), but this design does not fit well with the Java model.
     *   For example the character encoding information is irrelevant to <code>InternationalString</code>
     *   since the Java language fixes the encoding of all <code>String</code> instances to UTF-16.
     *
     * @see org.opengis.metadata.identification.DataIdentification#getLanguages()
     * @see org.opengis.metadata.content.FeatureCatalogueDescription#getLanguages()
     * @see Locale#getISO3Language()
     */
    @Profile(level=CORE)
    @UML(identifier="language", obligation=CONDITIONAL, specification=ISO_19115)
    Locale getLanguage();

    /**
     * Provides information about an alternatively used localized character string for a linguistic extension.
     *
     * @return Alternatively used localized character string for a linguistic extension.
     *
     * @since 2.1
     */
    @UML(identifier="locale", obligation=OPTIONAL, specification=ISO_19115)
    Collection<Locale> getLocales();

    /**
     * The character coding standard used for the metadata set.
     * Instances can be obtained by a call to {@link Charset#forName(String)}.
     *
     * <div class="api-change"><b>JDK integration</b> — as of ISO 19115:2014,
     * {@code CharacterSet} is replaced by a reference to the
     * <a href="http://www.iana.org/assignments/character-sets">IANA Character Set register</a>,
     * which is represented in Java by {@link java.nio.charset.Charset}.
     * This change will be applied in GeoAPI 4.0.
     * </div>
     *
     * <blockquote><font size="-1"><b>Examples:</b>
     * {@code UCS-2}, {@code UCS-4}, {@code UTF-7}, {@code UTF-8}, {@code UTF-16},
     * {@code ISO-8859-1} (a.k.a. {@code ISO-LATIN-1}), {@code ISO-8859-2}, {@code ISO-8859-3}, {@code ISO-8859-4},
     * {@code ISO-8859-5}, {@code ISO-8859-6}, {@code ISO-8859-7}, {@code ISO-8859-8}, {@code ISO-8859-9},
     * {@code ISO-8859-10}, {@code ISO-8859-11}, {@code ISO-8859-12}, {@code ISO-8859-13}, {@code ISO-8859-14},
     * {@code ISO-8859-15}, {@code ISO-8859-16},
     * {@code JIS_X0201}, {@code Shift_JIS}, {@code EUC-JP}, {@code US-ASCII}, {@code EBCDIC}, {@code EUC-KR},
     * {@code Big5}, {@code GB2312}.
     * </font></blockquote>
     *
     * @return Character coding standard used for the metadata, or {@code null}.
     *
     * @departure historic
     *   GeoAPI has kept the <code>language</code> and <code>characterSet</code> properties as defined in ISO 19115:2003.
     *   See <code>getLanguages()</code> for more information.
     *
     * @see org.opengis.metadata.identification.DataIdentification#getCharacterSets()
     * @see Charset#forName(String)
     */
    @Profile(level=CORE)
    @UML(identifier="characterSet", obligation=CONDITIONAL, specification=ISO_19115) // Actually from ISO 19115:2003
    CharacterSet getCharacterSet();

    /**
     * File identifier of the metadata to which this metadata is a subset (child).
     *
     * @return Identifier of the metadata to which this metadata is a subset, or {@code null}.
     *
     * @condition {@linkplain #getHierarchyLevels() Hierarchy level} is not equal to
     *            {@link ScopeCode#DATASET}.
     */
    @UML(identifier="parentIdentifier", obligation=CONDITIONAL, specification=ISO_19115)
    String getParentIdentifier();

    /**
     * Scope to which the metadata applies.
     * Metadata for which no hierarchy is listed are interpreted to be
     * "{@linkplain ScopeCode#DATASET dataset}" metadata by default.
     *
     * @return Scope to which the metadata applies.
     *
     * @condition Mandatory if the metadata is about a resource other than a dataset.
     */
    @UML(identifier="hierarchyLevel", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<ScopeCode> getHierarchyLevels();

    /**
     * Name of the hierarchy levels for which the metadata is provided.
     *
     * @return Hierarchy levels for which the metadata is provided.
     *
     * @condition {@linkplain #getHierarchyLevels() Hierarchy level} is not equal to
     *            {@link ScopeCode#DATASET}.
     */
    @UML(identifier="hierarchyLevelName", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<String> getHierarchyLevelNames();

    /**
     * Parties responsible for the metadata information.
     *
     * @return Parties responsible for the metadata information.
     *
     * @see Identification#getPointOfContacts()
     *
     * @since 2.1
     */
    @Profile(level=CORE)
    @UML(identifier="contact", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends ResponsibleParty> getContacts();

    /**
     * Date that the metadata was created.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0" SUMMARY="Warning! This API will change.">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change
     *     in GeoAPI 3.1 release. It may be replaced by a type matching more closely
     *     either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Date that the metadata was created.
     */
    @Profile(level=CORE)
    @UML(identifier="dateStamp", obligation=MANDATORY, specification=ISO_19115)
    Date getDateStamp();

    /**
     * Name of the metadata standard (including profile name) used.
     *
     * @return Name of the metadata standard used, or {@code null}.
     */
    @Profile(level=CORE)
    @UML(identifier="metadataStandardName", obligation=OPTIONAL, specification=ISO_19115)
    String getMetadataStandardName();

    /**
     * Version (profile) of the metadata standard used.
     *
     * @return Version of the metadata standard used, or {@code null}.
     */
    @Profile(level=CORE)
    @UML(identifier="metadataStandardVersion", obligation=OPTIONAL, specification=ISO_19115)
    String getMetadataStandardVersion();

    /**
     * Uniformed Resource Identifier (URI) of the dataset to which the metadata applies.
     *
     * @return Uniformed Resource Identifier of the dataset, or {@code null}.
     *
     * @since 2.1
     */
    @UML(identifier="dataSetURI", obligation=OPTIONAL, specification=ISO_19115)
    String getDataSetUri();

    /**
     * Digital representation of spatial information in the dataset.
     *
     * @return Digital representation of spatial information in the dataset.
     */
    @UML(identifier="spatialRepresentationInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends SpatialRepresentation> getSpatialRepresentationInfo();

    /**
     * Description of the spatial and temporal reference systems used in the dataset.
     *
     * @return Spatial and temporal reference systems used in the dataset.
     */
    @Profile(level=CORE)
    @UML(identifier="referenceSystemInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ReferenceSystem> getReferenceSystemInfo();

    /**
     * Information describing metadata extensions.
     *
     * @return Metadata extensions.
     */
    @UML(identifier="metadataExtensionInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends MetadataExtensionInformation> getMetadataExtensionInfo();

    /**
     * Basic information about the resource(s) to which the metadata applies.
     *
     * @return The resource(s) to which the metadata applies.
     */
    @Profile(level=CORE)
    @UML(identifier="identificationInfo", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Identification> getIdentificationInfo();

    /**
     * Provides information about the feature catalogue and describes the coverage and
     * image data characteristics.
     *
     * @return The feature catalogue, coverage descriptions and image data characteristics.
     */
    @UML(identifier="contentInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ContentInformation> getContentInfo();

    /**
     * Provides information about the distributor of and options for obtaining the resource(s).
     *
     * @return The distributor of and options for obtaining the resource(s), or {@code null}.
     */
    @Profile(level=CORE)
    @UML(identifier="distributionInfo", obligation=OPTIONAL, specification=ISO_19115)
    Distribution getDistributionInfo();

    /**
     * Provides overall assessment of quality of a resource(s).
     *
     * @return Overall assessment of quality of a resource(s).
     */
    @Profile(level=CORE)
    @UML(identifier="dataQualityInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends DataQuality> getDataQualityInfo();

    /**
     * Provides information about the catalogue of rules defined for the portrayal of a resource(s).
     *
     * @return The catalogue of rules defined for the portrayal of a resource(s).
     */
    @UML(identifier="portrayalCatalogueInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends PortrayalCatalogueReference> getPortrayalCatalogueInfo();

    /**
     * Provides restrictions on the access and use of data.
     *
     * @return Restrictions on the access and use of data.
     */
    @UML(identifier="metadataConstraints", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Constraints> getMetadataConstraints();

    /**
     * Provides information about the conceptual schema of a dataset.
     *
     * @return The conceptual schema of a dataset.
     */
    @UML(identifier="applicationSchemaInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ApplicationSchemaInformation> getApplicationSchemaInfo();

    /**
     * Provides information about the frequency of metadata updates, and the scope of those updates.
     *
     * @return The frequency of metadata updates and their scope, or {@code null}.
     */
    @UML(identifier="metadataMaintenance", obligation=OPTIONAL, specification=ISO_19115)
    MaintenanceInformation getMetadataMaintenance();

    /**
     * Provides information about the acquisition of the data.
     *
     * @return The acquisition of data.
     */
    @UML(identifier="acquisitionInformation", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends AcquisitionInformation> getAcquisitionInformation();
}
