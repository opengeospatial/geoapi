/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;


/**
 * The specifications from which an interface, method or code list was derived.
 * Some specifications are available both at OGC and ISO.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 */
public enum Specification {
    /**
     * ISO 19103:2005, Geographic information - Conceptual schema language.
     * This is the specification for some interfaces in package {@link org.opengis.util}.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Provides rules and guidelines for the use of a conceptual schema language
     * within the ISO geographic information standards. The chosen conceptual schema language is the
     * Unified Modeling Language (UML).</p>
     *
     * <p align="justify">ISO TS 19103:2005 provides a profile of UML for use with geographic
     * information. In addition, it provides guidelines on how UML should be used to create
     * standardized geographic information and service models.</p>
     * </font></blockquote>
     *
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=37800">Buy from ISO</A>
     */
    ISO_19103,

    /**
     * ISO 19107:2003, Feature Geometry
     * (<A HREF="http://www.opengeospatial.org/standards/as">OGC Topic 1</A>).
     * This is the specification for package {@link org.opengis.geometry} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Specifies conceptual schemas for describing the spatial characteristics of
     * geographic features, and a set of spatial operations consistent with these schemas. It treats
     * vector geometry and topology up to three dimensions. It defines standard spatial operations
     * for use in access, query, management, processing, and data exchange of geographic information
     * for spatial (geometric and topological) objects of up to three topological dimensions embedded
     * in coordinate spaces of up to three axes.</p>
     * </font></blockquote>
     *
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=26012">Buy from ISO</A>
     */
    ISO_19107,

    /**
     * ISO 19108:2002, Temporal Schema.
     * This is the specification for package {@link org.opengis.temporal} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Defines concepts for describing temporal characteristics of geographic
     * information. It depends upon existing information technology standards for the interchange
     * of temporal information. It provides a basis for defining temporal feature attributes,
     * feature operations, and feature associations, and for defining the temporal aspects of
     * metadata about geographic information. Since this International Standard is concerned with
     * the temporal characteristics of geographic information as they are abstracted from the real
     * world, it emphasizes valid time rather than transaction time.</p>
     * </font></blockquote>
     *
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=26013">Buy from ISO</A>
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=44883">Corrigendum</A>
     */
    ISO_19108,

    /**
     * ISO 19111:2007, Spatial Referencing by Coordinates
     * (<A HREF="http://www.opengeospatial.org/standards/as">OGC Topic 2</A>).
     * This is the specification for package {@link org.opengis.referencing} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Defines the conceptual schema for the description of spatial referencing by
     * coordinates, optionally extended to spatio-temporal referencing. It describes the minimum
     * data required to define one-, two- and three-dimensional spatial coordinate reference systems
     * with an extension to merged spatial-temporal reference systems. It allows additional descriptive
     * information to be provided. It also describes the information required to change coordinates
     * from one coordinate reference system to another.</p>
     *
     * <p align="justify">In ISO 19111:2007, a coordinate reference system does not change with time.
     * For coordinate reference systems defined on moving platforms such as cars, ships, aircraft and
     * spacecraft, the transformation to an Earth-fixed coordinate reference system can include a time
     * element.</p>
     *
     * <p align="justify">ISO 19111:2007 is applicable to producers and users of geographic information.
     * Although it is applicable to digital geographic data, its principles can be extended to many
     * other forms of geographic data such as maps, charts and text documents.</p>
     *
     * <p align="justify">The schema described can be applied to the combination of horizontal
     * position with a third non-spatial parameter which varies monotonically with height or depth.
     * This extension to non-spatial data is beyond the scope of ISO 19111:2007 but can be implemented
     * through profiles.</p>
     * </font></blockquote>
     *
     * @see #OGC_01009
     * @see <A HREF="http://portal.opengeospatial.org/files/?artifact_id=39049">Download from OGC</A>
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=41126">Buy from ISO</A>
     */
    ISO_19111,

    /**
     * ISO 19115:2003, Metadata
     * (<A HREF="http://www.opengeospatial.org/standards/as">OGC Topic 11</A>).
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Defines the schema required for describing geographic information and services.
     * It provides information about the identification, the extent, the quality, the spatial and
     * temporal schema, spatial reference, and distribution of digital geographic data.</p>
     *
     * <p>ISO 19115:2003 is applicable to:</p>
     * <ul>
     *   <li>the cataloguing of datasets, clearinghouse activities, and the full description of datasets;</li>
     *   <li>geographic datasets, dataset series, and individual geographic features and feature properties.</li>
     * </ul>
     *
     * <p>ISO 19115:2003 defines:</p>
     * <ul>
     *   <li>mandatory and conditional metadata sections, metadata entities, and metadata elements;</li>
     *   <li>the minimum set of metadata required to serve the full range of metadata applications
     *       (data discovery, determining data fitness for use, data access, data transfer, and use
     *       of digital data);</li>
     *   <li>optional metadata elements - to allow for a more extensive standard description of
     *       geographic data, if required;</li>
     *   <li>a method for extending metadata to fit specialized needs.</li>
     * </ul>
     *
     * <p align="justify">Though ISO 19115:2003 is applicable to digital data, its principles
     * can be extended to many other forms of geographic data such as maps, charts, and textual
     * documents as well as non-geographic data.</p>
     *
     * <p align="justify"><b>NOTE:</b> Certain mandatory metadata elements may not apply to these
     * other forms of data.</p>
     * </font></blockquote>
     *
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=26020">Buy from ISO</A>
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=44361">Corrigendum</A>
     */
    ISO_19115,

    /**
     * ISO 19115-2:2009, Metadata part 2: extensions for imagery and gridded data.
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Extends the existing geographic metadata standard by defining the schema
     * required for describing imagery and gridded data. It provides information about the properties
     * of the measuring equipment used to acquire the data, the geometry of the measuring process
     * employed by the equipment, and the production process used to digitize the raw data. This
     * extension deals with metadata needed to describe the derivation of geographic information
     * from raw data, including the properties of the measuring system, and the numerical methods
     * and computational procedures used in the derivation. The metadata required to address coverage
     * data in general is addressed sufficiently in the general part of ISO 19115.</p>
     * </font></blockquote>
     *
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=39229">Buy from ISO</A>
     *
     * @since 2.3
     */
    ISO_19115_2,

    /**
     * ISO 19117:2005, Portrayal.
     * This is an abstract specification for portraying features.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Defines a schema describing the portrayal of geographic information in a
     * form understandable by humans. It includes the methodology for describing symbols and mapping
     * of the schema to an application schema. It does not include standardization of cartographic
     * symbols, and their geometric and functional description.</p>
     * </font></blockquote>
     *
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=40395">Buy from ISO</A>
     */
    ISO_19117,

    /**
     * ISO 19123:2005, Schema for coverage geometry and functions
     * (<A HREF="http://www.opengeospatial.org/standards/as">OGC Topic 6</A>).
     * This is the specification for package {@link org.opengis.coverage} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Defines a conceptual schema for the spatial characteristics of coverages.
     * Coverages support mapping from a spatial, temporal or spatiotemporal domain to feature attribute
     * values where feature attribute types are common to all geographic positions within the domain.
     * A coverage domain consists of a collection of direct positions in a coordinate space that may
     * be defined in terms of up to three spatial dimensions as well as a temporal dimension.
     * Examples of coverages include rasters, triangulated irregular networks, point coverages and
     * polygon coverages. Coverages are the prevailing data structures in a number of application
     * areas, such as remote sensing, meteorology and mapping of bathymetry, elevation, soil and
     * vegetation.</p>
     *
     * <p align="justify">ISO 19123:2005 defines the relationship between the domain of a coverage
     * and an associated attribute range. The characteristics of the spatial domain are defined
     * whereas the characteristics of the attribute range are not part of ISO 19123:2005.</p>
     * </font></blockquote>
     *
     * @see #OGC_01004
     * @see <A HREF="http://portal.opengeospatial.org/files/?artifact_id=19820">Download from OGC</A>
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=40121">Buy from ISO</A>
     */
    ISO_19123,

    /**
     * ISO 19128:2005, Web map server interface.
     * This is the specification for package {@link org.opengis.layer} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Specifies the behaviour of a service that produces spatially referenced
     * maps dynamically from geographic information. It specifies operations to retrieve a description
     * of the maps offered by a server, to retrieve a map, and to query a server about features
     * displayed on a map. ISO 19128:2005 is applicable to pictorial renderings of maps in a
     * graphical format; it is not applicable to retrieval of actual feature data or coverage data
     * values.</p>
     * </font></blockquote>
     *
     * @see <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Download from OGC</A>
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=32546">Buy from ISO</A>
     */
    ISO_19128,

    /**
     * ISO 19139:2007, Metadata. XML schema implementation.
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote><font size="-1">
     * <p align="justify">Defines Geographic MetaData XML ({@code gmd}) encoding, an XML Schema
     * implementation derived from ISO 19115.</p>
     * </font></blockquote>
     *
     * @see <A HREF="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=32557">Buy from ISO</A>
     *
     * @since 2.3
     */
    ISO_19139,

    /**
     * GO-1 Application Objects.
     * This is the specification for package {@link org.opengis.go} and sub-packages.
     *
     * @see <A HREF="http://www.opengeospatial.org/standards/go">Download from OGC</A>
     */
    OGC_03064,

    /**
     * Coordinate Transformation Services implementation specification.
     * This is the specification used as a complement of {@linkplain #ISO_19111 ISO 19111}
     * when an aspect was not defined in the ISO specification.
     *
     * @see #ISO_19111
     * @see <A HREF="http://www.opengeospatial.org/standards/ct">Download from OGC</A>
     */
    OGC_01009,

    /**
     * Grid Coverages implementation specification.
     * This is the specification used as a complement of {@linkplain #ISO_19123 ISO 19123}
     * when an aspect was not defined in the ISO specification.
     *
     * @see #ISO_19123
     * @see <A HREF="http://www.opengeospatial.org/standards/gc">Download from OGC</A>
     */
    OGC_01004,

    /**
     * Specification not yet determined. This is a temporary enumeration
     * for the processing of API submitted by some contributors.
     */
    UNSPECIFIED
}
