/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.gdal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import org.gdal.gdal.Dataset;
import org.opengis.metadata.ApplicationSchemaInformation;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.MetadataExtensionInformation;
import org.opengis.metadata.MetadataScope;
import org.opengis.metadata.PortrayalCatalogueReference;
import org.opengis.metadata.acquisition.AcquisitionInformation;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.citation.PresentationForm;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.Series;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.content.AttributeGroup;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.content.CoverageContentType;
import org.opengis.metadata.content.CoverageDescription;
import org.opengis.metadata.content.RangeDimension;
import org.opengis.metadata.content.RangeElementDescription;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.distribution.Format;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.identification.AggregateInformation;
import org.opengis.metadata.identification.AssociatedResource;
import org.opengis.metadata.identification.BrowseGraphic;
import org.opengis.metadata.identification.CharacterSet;
import org.opengis.metadata.identification.DataIdentification;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.identification.Keywords;
import org.opengis.metadata.identification.Progress;
import org.opengis.metadata.identification.Resolution;
import org.opengis.metadata.identification.TopicCategory;
import org.opengis.metadata.identification.Usage;
import org.opengis.metadata.lineage.Lineage;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.spatial.CellGeometry;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.temporal.Duration;
import org.opengis.util.InternationalString;
import org.opengis.util.RecordType;

import static java.util.Collections.emptySet;


/**
 * Metadata about a GDAL dataset for a raster, which is assumed two-dimensional.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="http://gdal.org/gdal_datamodel.html">GDAL data model</a>
 * @see <a href="http://gdal.org/java/org/gdal/gdal/Dataset.html">Java API for GDAL Dataset</a>
 */
final class RasterMetadata extends GridGeometry implements Metadata, MetadataScope, DataIdentification, Citation, CoverageDescription {
    /**
     * The dataset name, or {@code null} if none.
     */
    private final String description;

    /**
     * Whether each point represents a cell, and area or a volume.
     */
    private final CellGeometry cellGeometry;

    /**
     * Fetches metadata from the given GDAL dataset.
     */
    RasterMetadata(final Dataset ds) throws IOException {
        super(ds);
        description  = trim(ds.GetDescription());
        String value = trim(ds.GetMetadataItem("AREA_OR_POINT"));
        if ("Point".equalsIgnoreCase(value)) {
            cellGeometry = CellGeometry.POINT;
        } else if ("Area".equalsIgnoreCase(value)) {
            cellGeometry = CellGeometry.AREA;
        } else {
            cellGeometry = null;
        }
    }

    /**
     * Trims the leading and trailing spaces in the given string and returns {@code null} if the result is empty.
     */
    private static String trim(String value) {
        return (value = value.trim()).isEmpty() ? null : value;
    }

    /* ISO 19115:2014 properties for which we provide information. */
    @Override public Collection<MetadataScope>                getMetadataScopes()                  {return Collections.<MetadataScope>singleton(this);}
    @Override public ScopeCode                                getResourceScope()                   {return ScopeCode.DATASET;}
    @Override public Collection<Identification>               getIdentificationInfo()              {return Collections.<Identification>singleton(this);}
    @Override public Collection<SpatialRepresentation>        getSpatialRepresentationInfo()       {return Collections.<SpatialRepresentation>singleton(this);}
    @Override public Collection<ContentInformation>           getContentInfo()                     {return Collections.<ContentInformation>singleton(this);}
    @Override public Citation                                 getCitation()                        {return this;}
    @Override public InternationalString                      getTitle()                           {return new Literal(description);}
    @Override public CellGeometry                             getCellGeometry()                    {return cellGeometry;}

    /* ISO 19115:2014 properties that are empty of null for now. */
    @Override public Identifier                               getMetadataIdentifier()              {return null;}
    @Override public Collection<Locale>                       getLanguages()                       {return emptySet();}
    @Override public Collection<Charset>                      getCharacterSets()                   {return emptySet();}
    @Override public Citation                                 getParentMetadata()                  {return null;}
    @Override public Collection<Responsibility>               getContacts()                        {return emptySet();}
    @Override public Collection<CitationDate>                 getDateInfo()                        {return emptySet();}
    @Override public Collection<Citation>                     getMetadataStandards()               {return emptySet();}
    @Override public Collection<Citation>                     getMetadataProfiles()                {return emptySet();}
    @Override public Collection<Citation>                     getAlternativeMetadataReferences()   {return emptySet();}
    @Override public Collection<OnlineResource>               getMetadataLinkages()                {return emptySet();}
    @Override public Collection<ReferenceSystem>              getReferenceSystemInfo()             {return emptySet();}
    @Override public Collection<MetadataExtensionInformation> getMetadataExtensionInfo()           {return emptySet();}
    @Override public Collection<Distribution>                 getDistributionInfo()                {return emptySet();}
    @Override public Collection<DataQuality>                  getDataQualityInfo()                 {return emptySet();}
    @Override public Collection<PortrayalCatalogueReference>  getPortrayalCatalogueInfo()          {return emptySet();}
    @Override public Collection<Constraints>                  getMetadataConstraints()             {return emptySet();}
    @Override public Collection<ApplicationSchemaInformation> getApplicationSchemaInfo()           {return emptySet();}
    @Override public Collection<AcquisitionInformation>       getAcquisitionInformation()          {return emptySet();}
    @Override public MaintenanceInformation                   getMetadataMaintenance()             {return null;}
    @Override public Collection<Lineage>                      getResourceLineages()                {return emptySet();}
    @Override public InternationalString                      getName()                            {return null;}
    @Override public RecordType                               getAttributeDescription()            {return null;}
    @Override public Identifier                               getProcessingLevelCode()             {return null;}
    @Override public Collection<AttributeGroup>               getAttributeGroups()                 {return emptySet();}
    @Override public Collection<RangeElementDescription>      getRangeElementDescriptions()        {return emptySet();}
    @Override public InternationalString                      getAbstract()                        {return null;}
    @Override public InternationalString                      getPurpose()                         {return null;}
    @Override public Collection<InternationalString>          getCredits()                         {return emptySet();}
    @Override public Collection<Progress>                     getStatus()                          {return emptySet();}
    @Override public Collection<Responsibility>               getPointOfContacts()                 {return emptySet();}
    @Override public Collection<SpatialRepresentationType>    getSpatialRepresentationTypes()      {return emptySet();}
    @Override public Collection<Resolution>                   getSpatialResolutions()              {return emptySet();}
    @Override public Collection<Duration>                     getTemporalResolutions()             {return emptySet();}
    @Override public Collection<TopicCategory>                getTopicCategories()                 {return emptySet();}
    @Override public Collection<Extent>                       getExtents()                         {return emptySet();}
    @Override public Collection<Citation>                     getAdditionalDocumentations()        {return emptySet();}
    @Override public Identifier                               getProcessingLevel()                 {return null;}
    @Override public Collection<MaintenanceInformation>       getResourceMaintenances()            {return emptySet();}
    @Override public Collection<BrowseGraphic>                getGraphicOverviews()                {return emptySet();}
    @Override public Collection<Format>                       getResourceFormats()                 {return emptySet();}
    @Override public Collection<Keywords>                     getDescriptiveKeywords()             {return emptySet();}
    @Override public Collection<Usage>                        getResourceSpecificUsages()          {return emptySet();}
    @Override public Collection<Constraints>                  getResourceConstraints()             {return emptySet();}
    @Override public Collection<AssociatedResource>           getAssociatedResources()             {return emptySet();}
    @Override public InternationalString                      getEnvironmentDescription()          {return null;}
    @Override public InternationalString                      getSupplementalInformation()         {return null;}
    @Override public Collection<InternationalString>          getAlternateTitles()                 {return emptySet();}
    @Override public Collection<CitationDate>                 getDates()                           {return emptySet();}
    @Override public InternationalString                      getEdition()                         {return null;}
    @Override public Date                                     getEditionDate()                     {return null;}
    @Override public Collection<Identifier>                   getIdentifiers()                     {return emptySet();}
    @Override public Collection<Responsibility>               getCitedResponsibleParties()         {return emptySet();}
    @Override public Collection<PresentationForm>             getPresentationForms()               {return emptySet();}
    @Override public Series                                   getSeries()                          {return null;}
    @Override public Collection<InternationalString>          getOtherCitationDetails()            {return emptySet();}
    @Override public String                                   getISBN()                            {return null;}
    @Override public String                                   getISSN()                            {return null;}
    @Override public Collection<OnlineResource>               getOnlineResources()                 {return emptySet();}
    @Override public Collection<BrowseGraphic>                getGraphics()                        {return emptySet();}

    /* ISO 19115:2003 properties to be removed in a future version. */
    @Override @Deprecated public String                           getFileIdentifier()          {return null;}
    @Override @Deprecated public Locale                           getLanguage()                {return null;}
    @Override @Deprecated public Collection<Locale>               getLocales()                 {return emptySet();}
    @Override @Deprecated public CharacterSet                     getCharacterSet()            {return null;}
    @Override @Deprecated public String                           getParentIdentifier()        {return null;}
    @Override @Deprecated public Collection<ScopeCode>            getHierarchyLevels()         {return Collections.singleton(getResourceScope());}
    @Override @Deprecated public Collection<String>               getHierarchyLevelNames()     {return emptySet();}
    @Override @Deprecated public Date                             getDateStamp()               {return null;}
    @Override @Deprecated public String                           getMetadataStandardName()    {return null;}
    @Override @Deprecated public String                           getMetadataStandardVersion() {return null;}
    @Override @Deprecated public String                           getDataSetUri()              {return null;}
    @Override @Deprecated public CoverageContentType              getContentType()             {return null;}
    @Override @Deprecated public Collection<RangeDimension>       getDimensions()              {return emptySet();}
    @Override @Deprecated public Collection<AggregateInformation> getAggregationInfo()         {return emptySet();}
    @Override @Deprecated public InternationalString              getCollectiveTitle()         {return null;}
}
