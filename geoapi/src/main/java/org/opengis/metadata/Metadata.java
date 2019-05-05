/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Locale;
import java.nio.charset.Charset;

import org.opengis.metadata.acquisition.AcquisitionInformation;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.DateType;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.metadata.identification.CharacterSet;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.lineage.Lineage;
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
     * Unique identifier for this metadata record.
     *
     * <div class="note"><b>Note:</b>
     * OGC 07-045 (Catalog Service Specification — ISO metadata application profile) recommends usage
     * of a UUID (Universal Unique Identifier) as specified by <a href="http://www.ietf.org">IETF</a>
     * to ensure identifier’s uniqueness.</div>
     *
     * @return unique identifier for this metadata record, or {@code null}.
     */
    @Profile(level=CORE)
    @UML(identifier="metadataIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    Identifier getMetadataIdentifier();

    /**
     * Unique identifier for this metadata file, or {@code null} if none.
     *
     * <div class="note"><b>Note:</b>
     * OGC 07-045 (Catalog Service Specification — ISO metadata application profile) recommends usage
     * of a UUID (Universal Unique Identifier) as specified by <a href="http://www.ietf.org">IETF</a>
     * to ensure identifier’s uniqueness.</div>
     *
     * @return unique identifier for this metadata file, or {@code null} in none.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getMetadataIdentifier()}
     *   in order to include the codespace attribute.
     */
    @Deprecated
    @UML(identifier="fileIdentifier", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    String getFileIdentifier();

    /**
     * Language(s) and character set(s) used for documenting metadata.
     * The first entry in iteration order shall be the default language and its character set.
     * All other entries, if any, are alternate language(s) and character set(s) used within the resource.
     *
     * <h3>Relationship with ISO 19115</h3>
     * Each ({@link Locale}, {@link Charset}) entry is equivalent to an instance of ISO {@code PT_Locale} class.
     * The language code and the character set are mandatory elements in ISO 19115 standard; consequently this map
     * should not contain null key or null values, but implementations are encouraged to be tolerant for historical
     * reasons (languages and character sets were defined as separated attributes in legacy ISO 19115:2003 standard).
     * The same character set may be associated to many languages. ISO 19115-1:2014 identifies those character sets
     * by references to the <a href="http://www.iana.org/assignments/character-sets">IANA Character Set register</a>,
     * which is represented in Java by {@link java.nio.charset.Charset}.
     * Instances can be obtained by a call to {@link Charset#forName(String)}.
     *
     * <div class="note"><b>Examples:</b>
     * {@code UCS-2}, {@code UCS-4}, {@code UTF-7}, {@code UTF-8}, {@code UTF-16},
     * {@code ISO-8859-1} (a.k.a. {@code ISO-LATIN-1}), {@code ISO-8859-2}, {@code ISO-8859-3}, {@code ISO-8859-4},
     * {@code ISO-8859-5}, {@code ISO-8859-6}, {@code ISO-8859-7}, {@code ISO-8859-8}, {@code ISO-8859-9},
     * {@code ISO-8859-10}, {@code ISO-8859-11}, {@code ISO-8859-12}, {@code ISO-8859-13}, {@code ISO-8859-14},
     * {@code ISO-8859-15}, {@code ISO-8859-16},
     * {@code JIS_X0201}, {@code Shift_JIS}, {@code EUC-JP}, {@code US-ASCII}, {@code EBCDIC}, {@code EUC-KR},
     * {@code Big5}, {@code GB2312}.
     * </div>
     *
     * <h3>XML representation</h3>
     * XML documents shall format languages using the ISO 639-2 language code as returned by {@link Locale#getISO3Language()}.
     *
     * @departure integration
     *   GeoAPI replaces ISO 19115-1:2014 {@code LanguageCode}, {@code CountryCode} and {@code MD_CharacterSetCode}
     *   code lists by equivalent objects from the standard Java library. The {@code PT_Locale} class, which is a
     *   container for above code-lists, is replaced by {@link Map} entries in order to avoid to introduce a new class
     *   and because the character set information is not as relevant in Java than in XML documents.
     *   For example the character encoding information is irrelevant to {@code InternationalString}
     *   because the Java language fixes the encoding of all {@code String} instances to UTF-16.
     *
     *   <p>In addition ISO 19115:2014 defines {@code defaultLocale} and {@code otherLocale(s)} as separated attributes,
     *   but GeoAPI groups them in a single collection for compatibility with standard Java methods like
     *   <code>{@link Locale#lookup(List, Collection) Locale.lookup(…, Collection&lt;Locale&gt;)}</code>.
     *   This API design makes easy to provide the collection of {@link Locale} objects with
     *   <code>getLocalesAndCharsets().{@linkplain Map#keySet() keySet()}</code>.</p>
     *
     * @return language(s) and character set(s) used for documenting metadata.
     *
     * @see org.opengis.metadata.identification.DataIdentification#getLocalesAndCharsets()
     * @see org.opengis.metadata.content.FeatureCatalogueDescription#getLocalesAndCharsets()
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="defaultLocale+otherLocale", obligation=CONDITIONAL, specification=ISO_19115)
    Map<Locale,Charset> getLocalesAndCharsets();

    /**
     * Language used for documenting metadata.
     *
     * @return language used for documenting metadata, or {@code null}.
     *
     * @deprecated As of GeoAPI 3.1, replaced by {@link #getLocalesAndCharsets()}.
     */
    @Deprecated
    @UML(identifier="language", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Locale getLanguage();

    /**
     * Provides information about an alternatively used localized character string for a linguistic extension.
     *
     * @return alternatively used localized character string for a linguistic extension.
     *
     * @deprecated As of GeoAPI 3.1, replaced by {@link #getLocalesAndCharsets()}.
     */
    @Deprecated
    @UML(identifier="locale", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Collection<Locale> getLocales();

    /**
     * The character coding standard used for the metadata set.
     *
     * @return character coding standard used for the metadata, or {@code null}.
     *
     * @deprecated As of GeoAPI 3.1, replaced by {@code getLocalesAndCharsets().values()}.
     */
    @Deprecated
    @UML(identifier="characterSet", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    CharacterSet getCharacterSet();

    /**
     * Identification of the parent metadata record.
     * This is non-null if this metadata is a subset (child) of another metadata that is described elsewhere.
     *
     * @condition Mandatory if there is an upper object.
     *
     * @return identification of the parent metadata record, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="parentMetadata", obligation=CONDITIONAL, specification=ISO_19115)
    Citation getParentMetadata();

    /**
     * File identifier of the metadata to which this metadata is a subset (child).
     *
     * <div class="note"><b>Note:</b>
     * OGC 07-045 (Catalog Service Specification — ISO metadata application profile) recommends usage
     * of a UUID (Universal Unique Identifier) as specified by <a href="http://www.ietf.org">IETF</a>
     * to ensure identifier’s uniqueness.</div>
     *
     * @return identifier of the metadata to which this metadata is a subset, or {@code null}.
     *
     * @condition {@linkplain #getHierarchyLevels() Hierarchy level} is not equal to
     *            {@link ScopeCode#DATASET}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getParentMetadata()}.
     */
    @Deprecated
    @UML(identifier="parentIdentifier", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    String getParentIdentifier();

    /**
     * The scope or type of resource for which metadata is provided.
     *
     * @condition Mandatory if the metadata is about a resource other than a dataset.
     *
     * @return scope or type of resource for which metadata is provided.
     *
     * @since 3.1
     */
    @UML(identifier="metadataScope", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends MetadataScope> getMetadataScopes();

    /**
     * Scope to which the metadata applies.
     * Metadata for which no hierarchy is listed are interpreted to be
     * "{@linkplain ScopeCode#DATASET dataset}" metadata by default.
     *
     * @return scope to which the metadata applies.
     *
     * @condition Mandatory if the metadata is about a resource other than a dataset.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getMetadataScopes()}
     *   followed by {@link MetadataScope#getResourceScope()}.
     */
    @Deprecated
    @UML(identifier="hierarchyLevel", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    Collection<ScopeCode> getHierarchyLevels();

    /**
     * Name of the hierarchy levels for which the metadata is provided.
     *
     * @return hierarchy levels for which the metadata is provided.
     *
     * @condition {@linkplain #getHierarchyLevels() Hierarchy level} is not equal to
     *            {@link ScopeCode#DATASET}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getMetadataScopes()}
     *   followed by {@link MetadataScope#getName()}.
     */
    @Deprecated
    @UML(identifier="hierarchyLevelName", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    Collection<String> getHierarchyLevelNames();

    /**
     * Parties responsible for the metadata information.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ResponsibleParty} is replaced by the {@link Responsibility} parent interface.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return parties responsible for the metadata information.
     *
     * @see Identification#getPointOfContacts()
     */
    @Profile(level=CORE)
    @UML(identifier="contact", obligation=MANDATORY, specification=ISO_19115, version=2003)
    Collection<? extends ResponsibleParty> getContacts();

    /**
     * Date(s) associated with the metadata.
     * The collection shall contains at least an element for {@link DateType#CREATION}.
     *
     * @return date(s) associated with the metadata.
     *
     * @see Citation#getDates()
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="dateInfo", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends CitationDate> getDateInfo();

    /**
     * Date that the metadata was created.
     *
     * @return date that the metadata was created.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getDateInfo()}.
     */
    @Deprecated
    @UML(identifier="dateStamp", obligation=MANDATORY, specification=ISO_19115, version=2003)
    Date getDateStamp();

    /**
     * Name of the metadata standard (including profile name) used.
     *
     * @return name of the metadata standard used, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getMetadataStandards()}
     *   followed by {@link Citation#getTitle()}.
     */
    @Deprecated
    @UML(identifier="metadataStandardName", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    String getMetadataStandardName();

    /**
     * Version (profile) of the metadata standard used.
     *
     * @return version of the metadata standard used, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getMetadataStandards()}
     *   followed by {@link Citation#getEdition()}.
     */
    @Deprecated
    @Profile(level=CORE)
    @UML(identifier="metadataStandardVersion", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    String getMetadataStandardVersion();

    /**
     * Citation(s) for the standard(s) to which the metadata conform.
     * Metadata standard citations should include an identifier.
     *
     * @return the standard(s) to which the metadata conform.
     *
     * @see #getMetadataProfiles()
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="metadataStandard", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getMetadataStandards();

    /**
     * Citation(s) for the profile(s) of the metadata standard to which the metadata conform.
     * Metadata profile standard citations should include an identifier.
     *
     * @return the profile(s) to which the metadata conform.
     *
     * @see #getMetadataStandards()
     * @see #getMetadataExtensionInfo()
     *
     * @since 3.1
     */
    @UML(identifier="metadataProfile", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getMetadataProfiles();

    /**
     * Reference(s) to alternative metadata or metadata in a non-ISO standard for the same resource.
     *
     * @return reference(s) to alternative metadata (e.g. Dublin core, FGDC).
     *
     * @since 3.1
     */
    @UML(identifier="alternativeMetadataReference", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getAlternativeMetadataReferences();

    /**
     * Online location(s) where the metadata is available.
     *
     * @return online location(s) where the metadata is available.
     *
     * @since 3.1
     */
    @UML(identifier="metadataLinkage", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends OnlineResource> getMetadataLinkages();

    /**
     * Uniform Resource Identifier (URI) of the dataset to which the metadata applies.
     *
     * @return Uniform Resource Identifier of the dataset, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getIdentificationInfo()} followed by
     *    {@link Identification#getCitation()} followed by {@link Citation#getOnlineResources()}.
     */
    @Deprecated
    @UML(identifier="dataSetURI", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    String getDataSetUri();

    /**
     * Digital representation of spatial information in the dataset.
     *
     * @return digital representation of spatial information in the dataset.
     */
    @UML(identifier="spatialRepresentationInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends SpatialRepresentation> getSpatialRepresentationInfo();

    /**
     * Description of the spatial and temporal reference systems used in the dataset.
     *
     * @return spatial and temporal reference systems used in the dataset.
     */
    @Profile(level=CORE)
    @UML(identifier="referenceSystemInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ReferenceSystem> getReferenceSystemInfo();

    /**
     * Information describing metadata extensions.
     *
     * @return metadata extensions.
     *
     * @see #getMetadataProfiles()
     */
    @UML(identifier="metadataExtensionInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends MetadataExtensionInformation> getMetadataExtensionInfo();

    /**
     * Basic information about the resource(s) to which the metadata applies.
     *
     * @return the resource(s) to which the metadata applies.
     */
    @Profile(level=CORE)
    @UML(identifier="identificationInfo", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Identification> getIdentificationInfo();

    /**
     * Information about the feature and coverage characteristics.
     *
     * @return information about the feature and coverage characteristics.
     */
    @UML(identifier="contentInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ContentInformation> getContentInfo();

    /**
     * Information about the distributor of and options for obtaining the resource(s).
     *
     * <div class="warning"><b>Upcoming API change — multiplicity</b><br>
     * As of ISO 19115:2014, this singleton has been replaced by a collection.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return the distributor of and options for obtaining the resource(s), or {@code null}.
     */
    @Profile(level=CORE)
    @UML(identifier="distributionInfo", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Distribution getDistributionInfo();

    /**
     * Overall assessment of quality of a resource(s).
     *
     * @return overall assessment of quality of a resource(s).
     */
    @Profile(level=CORE)
    @UML(identifier="dataQualityInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends DataQuality> getDataQualityInfo();

    /**
     * Information about the catalogue of rules defined for the portrayal of a resource(s).
     *
     * @return the catalogue of rules defined for the portrayal of a resource(s).
     */
    @UML(identifier="portrayalCatalogueInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends PortrayalCatalogueReference> getPortrayalCatalogueInfo();

    /**
     * Restrictions on the access and use of metadata.
     *
     * @return restrictions on the access and use of metadata.
     *
     * @see Identification#getResourceConstraints()
     */
    @UML(identifier="metadataConstraints", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Constraints> getMetadataConstraints();

    /**
     * Information about the conceptual schema of a dataset.
     *
     * @return the conceptual schema of a dataset.
     */
    @UML(identifier="applicationSchemaInfo", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends ApplicationSchemaInformation> getApplicationSchemaInfo();

    /**
     * Information about the acquisition of the data.
     *
     * @return the acquisition of data.
     */
    @UML(identifier="acquisitionInformation", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends AcquisitionInformation> getAcquisitionInformation();

    /**
     * Information about the frequency of metadata updates, and the scope of those updates.
     *
     * @return the frequency of metadata updates and their scope, or {@code null}.
     *
     * @see Identification#getResourceMaintenances()
     */
    @UML(identifier="metadataMaintenance", obligation=OPTIONAL, specification=ISO_19115)
    MaintenanceInformation getMetadataMaintenance();

    /**
     * Information about the provenance, sources and/or the production processes applied to the resource.
     *
     * @return the provenance, sources and/or the production processes.
     *
     * @since 3.1
     */
    @UML(identifier="resourceLineage", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Lineage> getResourceLineages();
}
