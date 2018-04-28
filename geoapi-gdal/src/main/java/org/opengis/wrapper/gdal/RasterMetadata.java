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
import java.util.List;
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
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.content.AttributeGroup;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.content.CoverageContentType;
import org.opengis.metadata.content.CoverageDescription;
import org.opengis.metadata.content.RangeDimension;
import org.opengis.metadata.content.RangeElementDescription;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.identification.CharacterSet;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.lineage.Lineage;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.spatial.CellGeometry;
import org.opengis.metadata.spatial.Dimension;
import org.opengis.metadata.spatial.GridSpatialRepresentation;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.InternationalString;
import org.opengis.util.RecordType;

import static java.util.Collections.emptySet;


/**
 * Metadata about a GDAL dataset for a raster.
 * The raster is assumed two-dimensional.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class RasterMetadata implements Metadata, MetadataScope, CoverageDescription, GridSpatialRepresentation {
    /**
     * Raster shape (size, number of bands).
     */
    private final int xSize, ySize, numBands;

    /**
     * Whether the GDAL driver provided information about the content type (thematic classification,
     * physical measurement, <i>etc</i>) and the bands (scale factor, <i>etc</i>).
     */
    private final boolean hasContentInfo;

    /**
     * Fetches metadata for the given GDAL dataset.
     */
    RasterMetadata(final Dataset ds) throws IOException {
        if (ds == null) {
            throw new IOException("DataSet is closed.");
        }
        xSize    = ds.getRasterXSize();
        ySize    = ds.getRasterYSize();
        numBands = ds.getRasterCount();
        hasContentInfo = false;             // TODO
    }

    /* ISO 19115:2014 properties. */
    @Override public Identifier                               getMetadataIdentifier()              {return null;}
    @Override public Collection<Locale>                       getLanguages()                       {return emptySet();}
    @Override public Collection<Charset>                      getCharacterSets()                   {return emptySet();}
    @Override public Citation                                 getParentMetadata()                  {return null;}
    @Override public Collection<MetadataScope>                getMetadataScopes()                  {return Collections.<MetadataScope>singleton(this);}
    @Override public ScopeCode                                getResourceScope()                   {return ScopeCode.DATASET;}
    @Override public Collection<Responsibility>               getContacts()                        {return emptySet();}
    @Override public Collection<CitationDate>                 getDateInfo()                        {return emptySet();}
    @Override public Collection<Citation>                     getMetadataStandards()               {return emptySet();}
    @Override public Collection<Citation>                     getMetadataProfiles()                {return emptySet();}
    @Override public Collection<Citation>                     getAlternativeMetadataReferences()   {return emptySet();}
    @Override public Collection<OnlineResource>               getMetadataLinkages()                {return emptySet();}
    @Override public Collection<SpatialRepresentation>        getSpatialRepresentationInfo()       {return emptySet();}
    @Override public Collection<ReferenceSystem>              getReferenceSystemInfo()             {return emptySet();}
    @Override public Collection<MetadataExtensionInformation> getMetadataExtensionInfo()           {return emptySet();}
    @Override public Collection<Identification>               getIdentificationInfo()              {return emptySet();}
    @Override public Collection<ContentInformation>           getContentInfo()                     {return hasContentInfo ? Collections.<ContentInformation>singleton(this) : Collections.<ContentInformation>emptySet();}
    @Override public Collection<Distribution>                 getDistributionInfo()                {return emptySet();}
    @Override public Collection<DataQuality>                  getDataQualityInfo()                 {return emptySet();}
    @Override public Collection<PortrayalCatalogueReference>  getPortrayalCatalogueInfo()          {return emptySet();}
    @Override public Collection<Constraints>                  getMetadataConstraints()             {return emptySet();}
    @Override public Collection<ApplicationSchemaInformation> getApplicationSchemaInfo()           {return emptySet();}
    @Override public Collection<AcquisitionInformation>       getAcquisitionInformation()          {return emptySet();}
    @Override public MaintenanceInformation                   getMetadataMaintenance()             {return null;}
    @Override public Collection<Lineage>                      getResourceLineages()                {return emptySet();}
    @Override public InternationalString                      getName()                            {return null;}
    @Override public Integer                                  getNumberOfDimensions()              {return 0;}
    @Override public List<Dimension>                          getAxisDimensionProperties()         {return Collections.emptyList();}
    @Override public CellGeometry                             getCellGeometry()                    {return null;}
    @Override public boolean                                  isTransformationParameterAvailable() {return false;}
    @Override public RecordType                               getAttributeDescription()            {return null;}
    @Override public Identifier                               getProcessingLevelCode()             {return null;}
    @Override public Collection<AttributeGroup>               getAttributeGroups()                 {return emptySet();}
    @Override public Collection<RangeElementDescription>      getRangeElementDescriptions()        {return emptySet();}

    /* ISO 19115:2003 properties to be removed in a future version. */
    @Override @Deprecated public String                     getFileIdentifier()          {return null;}
    @Override @Deprecated public Locale                     getLanguage()                {return null;}
    @Override @Deprecated public Collection<Locale>         getLocales()                 {return emptySet();}
    @Override @Deprecated public CharacterSet               getCharacterSet()            {return null;}
    @Override @Deprecated public String                     getParentIdentifier()        {return null;}
    @Override @Deprecated public Collection<ScopeCode>      getHierarchyLevels()         {return Collections.singleton(getResourceScope());}
    @Override @Deprecated public Collection<String>         getHierarchyLevelNames()     {return emptySet();}
    @Override @Deprecated public Date                       getDateStamp()               {return null;}
    @Override @Deprecated public String                     getMetadataStandardName()    {return null;}
    @Override @Deprecated public String                     getMetadataStandardVersion() {return null;}
    @Override @Deprecated public String                     getDataSetUri()              {return null;}
    @Override @Deprecated public CoverageContentType        getContentType()             {return null;}
    @Override @Deprecated public Collection<RangeDimension> getDimensions()              {return emptySet();}
}
