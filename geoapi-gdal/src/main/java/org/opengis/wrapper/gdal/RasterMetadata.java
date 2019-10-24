/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.gdal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Locale;
import org.gdal.gdal.Dataset;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.MetadataScope;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.content.CoverageDescription;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.identification.TopicCategory;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.spatial.CellGeometry;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.util.InternationalString;
import org.opengis.util.RecordType;

import static java.util.Collections.emptySet;
import static java.util.Collections.emptyMap;


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
final class RasterMetadata extends GridGeometry implements Metadata, MetadataScope, Identification, Citation, CoverageDescription {
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
    @Override public Collection<MetadataScope>         getMetadataScopes()            {return Collections.<MetadataScope>singleton(this);}
    @Override public ScopeCode                         getResourceScope()             {return ScopeCode.DATASET;}
    @Override public Collection<Identification>        getIdentificationInfo()        {return Collections.<Identification>singleton(this);}
    @Override public Collection<SpatialRepresentation> getSpatialRepresentationInfo() {return Collections.<SpatialRepresentation>singleton(this);}
    @Override public Collection<ContentInformation>    getContentInfo()               {return Collections.<ContentInformation>singleton(this);}
    @Override public Citation                          getCitation()                  {return this;}
    @Override public InternationalString               getTitle()                     {return new Literal(description);}
    @Override public CellGeometry                      getCellGeometry()              {return cellGeometry;}

    /* ISO 19115:2014 properties that are empty of null for now. */
    @Override public Map<Locale,Charset>          getLocalesAndCharsets()      {return emptyMap();}
    @Override public Collection<ResponsibleParty> getContacts()                {return emptySet();}
    @Override public Collection<CitationDate>     getDateInfo()                {return emptySet();}
    @Override public RecordType                   getAttributeDescription()    {return null;}
    @Override public InternationalString          getAbstract()                {return null;}
    @Override public Collection<TopicCategory>    getTopicCategories()         {return emptySet();}
    @Override public Collection<Extent>           getExtents()                 {return emptySet();}
}
