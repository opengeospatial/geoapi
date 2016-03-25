/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2015 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;


/**
 * ISO/OGC specifications from which an interface, method, enumeration or code list was derived.
 * Some specifications are available both at OGC and ISO.
 *
 * <p><b>Specification versions:</b></p>
 * Each specification has a {@linkplain #defaultVersion() default version} number, which identifies
 * more accurately the specification used by the vast majority of non-deprecated GeoAPI elements:
 * <ul>
 *   <li>For OGC specifications, the version number is the OGC revision number.</li>
 *   <li>For ISO specifications, the version number is the publication year (ignoring corrigendum,
 *       which are implicit). For example if the specification is {@link #ISO_19115}, then version
 *       2003 stands for <cite>ISO 19115:2003/Cor.1:2006(E)</cite>.</li>
 * </ul>
 * The version numbers are documented in {@code Specification} enumeration constants.
 * Versions other than the {@linkplain #defaultVersion() default version} may be declared
 * in the {@link UML#version()} annotation of some GeoAPI elements, usually (but not only)
 * for deprecated elements.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.0
 *
 * @see UML#specification()
 */
public enum Specification {
    /**
     * ISO 19103, Conceptual schema language.
     * This is the specification for some interfaces in package {@link org.opengis.util}.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Provides rules and guidelines for the use of a conceptual schema language
     * within the ISO geographic information standards.
     * The chosen conceptual schema language is the Unified Modeling Language (UML).</p>
     *
     * <p>ISO TS 19103 provides a profile of UML for use with geographic information.
     * In addition, it provides guidelines on how UML should be used to create
     * standardized geographic information and service models.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2005:</b> ISO/TS 19103:2005(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=37800">Buy from ISO</a>
     */
    ISO_19103((short) 2005),

    /**
     * ISO 19107, Feature Geometry
     * (<a href="http://www.opengeospatial.org/standards/as">OGC Topic 1</a>).
     * This is the specification for package {@link org.opengis.geometry} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Specifies conceptual schemas for describing the spatial characteristics of
     * geographic features, and a set of spatial operations consistent with these schemas. It treats
     * vector geometry and topology up to three dimensions. It defines standard spatial operations
     * for use in access, query, management, processing, and data exchange of geographic information
     * for spatial (geometric and topological) objects of up to three topological dimensions embedded
     * in coordinate spaces of up to three axes.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2003:</b> ISO 19107:2003(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=26012">Buy from ISO</a>
     */
    ISO_19107((short) 2003),

    /**
     * ISO 19108, Temporal Schema.
     * This is the specification for package {@link org.opengis.temporal} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Defines concepts for describing temporal characteristics of geographic
     * information. It depends upon existing information technology standards for the interchange
     * of temporal information. It provides a basis for defining temporal feature attributes,
     * feature operations, and feature associations, and for defining the temporal aspects of
     * metadata about geographic information. Since this International Standard is concerned with
     * the temporal characteristics of geographic information as they are abstracted from the real
     * world, it emphasizes valid time rather than transaction time.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2002:</b> ISO 19108:2002/Cor.1:2006(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=26013">Buy from ISO</a>
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=44883">Corrigendum</a>
     */
    ISO_19108((short) 2002),

    /**
     * ISO 19109, Rules for application schema.
     * This is the specification for package {@link org.opengis.feature}.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Defines rules for creating and documenting application schemas, including principles for the definition
     * of features. Its scope includes the following:</p>
     * <ul>
     *   <li>conceptual modelling of features and their properties from a universe of discourse;</li>
     *   <li>definition of application schemas;</li>
     *   <li>use of the conceptual schema language for application schemas;</li>
     *   <li>transition from the concepts in the conceptual model to the data types in the application schema;</li>
     *   <li>integration of standardized schemas from other ISO geographic information standards with the application
     *       schema.</li>
     * </ul>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2013:</b> ISO 19109:2013 draft   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=39891">Buy from ISO</a>
     *
     * @since 3.1
     */
    ISO_19109((short) 2013),

    /**
     * ISO 19111, Spatial Referencing by Coordinates
     * (<a href="http://www.opengeospatial.org/standards/as">OGC Topic 2</a>).
     * This is the specification for package {@link org.opengis.referencing} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Defines the conceptual schema for the description of spatial referencing by
     * coordinates, optionally extended to spatio-temporal referencing. It describes the minimum
     * data required to define one-, two- and three-dimensional spatial coordinate reference systems
     * with an extension to merged spatial-temporal reference systems. It allows additional descriptive
     * information to be provided. It also describes the information required to change coordinates
     * from one coordinate reference system to another.</p>
     *
     * <p>In ISO 19111, a coordinate reference system does not change with time.
     * For coordinate reference systems defined on moving platforms such as cars, ships, aircraft and
     * spacecraft, the transformation to an Earth-fixed coordinate reference system can include a time
     * element.</p>
     *
     * <p>ISO 19111 is applicable to producers and users of geographic information.
     * Although it is applicable to digital geographic data, its principles can be extended to many
     * other forms of geographic data such as maps, charts and text documents.</p>
     *
     * <p>The schema described can be applied to the combination of horizontal
     * position with a third non-spatial parameter which varies monotonically with height or depth.
     * This extension to non-spatial data is beyond the scope of ISO 19111 but can be implemented
     * through profiles.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2003:</b> OGC 03-073r1</li>
     *   <li><b>2007:</b> ISO 19111:2007(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see #OGC_01009
     * @see <a href="http://portal.opengeospatial.org/files/?artifact_id=39049">Download from OGC</a>
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=41126">Buy from ISO</a>
     */
    ISO_19111((short) 2007),

    /**
     * ISO 19111-2, Part 2: Extension for parametric values
     * This is the specification for classes {@link org.opengis.referencing.crs.ParametricCRS} and associated parametric classes.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Extends the existing spatial referencing standard by defining the schema
     * required for describing parameterized systems.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2016:</b> ISO 19111-2:2009(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/fr/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=44075">Buy from ISO</a>
     *
     * @since 3.2
     */
    ISO_19111_2((short) 2009),

    /**
     * ISO 19115-1, Metadata
     * (<a href="http://www.opengeospatial.org/standards/as">OGC Topic 11</a>).
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Defines the schema required for describing geographic information and services by means of metadata.
     * It provides information about the identification, the extent, the quality, the spatial and temporal aspects,
     * the content, the spatial reference, the portrayal, distribution, and other properties of digital geographic
     * data and services.</p>
     *
     * <p>ISO 19115-1 is applicable to:</p>
     * <ul>
     *   <li>the cataloguing of all types of resources, clearinghouse activities, and the full description of datasets and services;</li>
     *   <li>geographic services, geographic datasets, dataset series, and individual geographic features and feature properties.</li>
     * </ul>
     *
     * <p>ISO 19115-1 defines:</p>
     * <ul>
     *   <li>mandatory and conditional metadata sections, metadata entities, and metadata elements;</li>
     *   <li>the minimum set of metadata required to serve most metadata applications (data discovery,
     *       determining data fitness for use, data access, data transfer, and use of digital data and services);</li>
     *   <li>optional metadata elements to allow for a more extensive standard description of resources, if required;</li>
     *   <li>a method for extending metadata to fit specialized needs.</li>
     * </ul>
     *
     * <p>Though ISO 19115-1 is applicable to digital data and services, its principles can be extended to many
     * other types of resources such as maps, charts, and textual documents as well as non-geographic data.
     * Certain conditional metadata elements might not apply to these other forms of data.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2003:</b> ISO 19115:2003/Cor.1:2006(E)</li>
     *   <li><b>2014:</b> ISO 19115-1:2014(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/home/store/catalogue_ics/catalogue_detail_ics.htm?csnumber=53798">Buy from ISO</a>
     */
    ISO_19115((short) 2014),

    /**
     * ISO 19115-2, Metadata part 2: extensions for imagery and gridded data.
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Extends the existing geographic metadata standard by defining the schema
     * required for describing imagery and gridded data. It provides information about the properties
     * of the measuring equipment used to acquire the data, the geometry of the measuring process
     * employed by the equipment, and the production process used to digitize the raw data. This
     * extension deals with metadata needed to describe the derivation of geographic information
     * from raw data, including the properties of the measuring system, and the numerical methods
     * and computational procedures used in the derivation. The metadata required to address coverage
     * data in general is addressed sufficiently in the general part of ISO 19115.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2009:</b> ISO 19115-2:2009(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=39229">Buy from ISO</a>
     *
     * @since 2.3
     */
    ISO_19115_2((short) 2009),

    /**
     * ISO 19117, Portrayal.
     * This is an abstract specification for portraying features.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Defines a schema describing the portrayal of geographic information in a
     * form understandable by humans. It includes the methodology for describing symbols and mapping
     * of the schema to an application schema. It does not include standardization of cartographic
     * symbols, and their geometric and functional description.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2005:</b> ISO 19117:2005(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=40395">Buy from ISO</a>
     */
    ISO_19117((short) 2005),

    /**
     * ISO 19123, Schema for coverage geometry and functions
     * (<a href="http://www.opengeospatial.org/standards/as">OGC Topic 6</a>).
     * This is the specification for package {@link org.opengis.coverage} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Defines a conceptual schema for the spatial characteristics of coverages.
     * Coverages support mapping from a spatial, temporal or spatiotemporal domain to feature attribute
     * values where feature attribute types are common to all geographic positions within the domain.
     * A coverage domain consists of a collection of direct positions in a coordinate space that may
     * be defined in terms of up to three spatial dimensions as well as a temporal dimension.
     * Examples of coverages include rasters, triangulated irregular networks, point coverages and
     * polygon coverages. Coverages are the prevailing data structures in a number of application
     * areas, such as remote sensing, meteorology and mapping of bathymetry, elevation, soil and
     * vegetation.</p>
     *
     * <p>ISO 19123 defines the relationship between the domain of a coverage
     * and an associated attribute range. The characteristics of the spatial domain are defined
     * whereas the characteristics of the attribute range are not part of ISO 19123.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2005:</b> ISO 19123:2005(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see #OGC_01004
     * @see <a href="http://portal.opengeospatial.org/files/?artifact_id=19820">Download from OGC</a>
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=40121">Buy from ISO</a>
     */
    ISO_19123((short) 2005),

    /**
     * ISO 19128, Web map server interface.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Specifies the behaviour of a service that produces spatially referenced
     * maps dynamically from geographic information. It specifies operations to retrieve a description
     * of the maps offered by a server, to retrieve a map, and to query a server about features
     * displayed on a map. ISO 19128 is applicable to pictorial renderings of maps in a
     * graphical format; it is not applicable to retrieval of actual feature data or coverage data
     * values.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2005:</b> ISO 19128:2005(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://portal.opengeospatial.org/files/?artifact_id=5316">Download from OGC</a>
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=32546">Buy from ISO</a>
     */
    ISO_19128((short) 2005),

    /**
     * ISO 19139, Metadata XML schema implementation.
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>Defines Geographic MetaData XML ({@code gmd}) encoding, an XML Schema
     * implementation derived from ISO 19115.</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2007:</b> ISO 19139:2007(E)   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/iso_catalogue/catalogue_tc/catalogue_detail.htm?csnumber=32557">Buy from ISO</a>
     *
     * @since 2.3
     */
    ISO_19139((short) 2007),

    /**
     * ISO 19162, Well known text representation of coordinate reference systems.
     * This is the specification of input and output format of
     * {@link org.opengis.referencing.crs.CRSFactory#createFromWKT(String)} and
     * {@link org.opengis.referencing.crs.CoordinateReferenceSystem#toWKT()}.
     *
     * <p><b>ISO abstract:</b></p>
     * <blockquote style="font-size:small">
     * <p>(under development)</p>
     * </blockquote>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2015:</b> ISO 19162:2015   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.iso.org/iso/home/store/catalogue_tc/catalogue_detail.htm?csnumber=63094">Buy from ISO</a>
     *
     * @since 3.1
     */
    ISO_19162((short) 2015),

    /**
     * GO-1 Application Objects.
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>10:</b> OGC 03-064r10   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="http://www.opengeospatial.org/standards/go">Download from OGC</a>
     */
    OGC_03064((short) 10),

    /**
     * Coordinate Transformation Services implementation specification.
     * This is the specification used as a complement of {@linkplain #ISO_19111 ISO 19111}
     * when an aspect was not defined in the ISO specification.
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>1:</b> OGC 01-009   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see #ISO_19111
     * @see <a href="http://www.opengeospatial.org/standards/ct">Download from OGC</a>
     */
    OGC_01009((short) 1),

    /**
     * Grid Coverages implementation specification.
     * This is the specification used as a complement of {@linkplain #ISO_19123 ISO 19123}
     * when an aspect was not defined in the ISO specification.
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>1:</b> OGC 01-004   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see #ISO_19123
     * @see <a href="http://www.opengeospatial.org/standards/gc">Download from OGC</a>
     */
    OGC_01004((short) 1),

    /**
     * Filter encoding implementation specification.
     * This is the specification for package {@link org.opengis.filter} and sub-packages.
     *
     * @see <a href="http://www.opengeospatial.org/standards/filter">Download from OGC</a>
     *
     * @deprecated To be replaced by {@code OGC 09-026}.
     */
    @Deprecated
    OGC_02059((short) 1),

    /**
     * Styled Layer Descriptor (SLD) implementation specification.
     * This is the specification for package {@link org.opengis.sld} and sub-packages.
     *
     * @see <a href="http://www.opengeospatial.org/standards/sld">Download from OGC</a>
     *
     * @deprecated To be replaced by {@code OGC 05-078}.
     */
    @Deprecated
    OGC_02070((short) 1),

    /**
     * Web Feature Service implementation specification.
     * This is the specification for package {@link org.opengis.feature} and sub-packages.
     *
     * @see <a href="http://www.opengeospatial.org/standards/wfs">Download from OGC</a>
     *
     * @deprecated To be replaced by {@code OGC 09-025}.
     */
    @Deprecated
    OGC_04094((short) 1),

    /**
     * Observations and Measurements, part 1.
     * This is the specification for package {@link org.opengis.observation}.
     *
     * @deprecated To be replaced by Topic 20: Observations and Measurements
     */
    @Deprecated
    OGC_07022((short) 1),

    /**
     * Observations and Measurements, part 2: Sampling Features.
     * This is the specification for package {@link org.opengis.observation.sampling}.
     *
     * @deprecated To be replaced by Topic 20: Observations and Measurements
     */
    @Deprecated
    OGC_07002((short) 1),

    /**
     * Specification not yet determined. This is a temporary enumeration
     * for the processing of API submitted by some contributors.
     */
    UNSPECIFIED((short) 1);

    /**
     * The default version of OGC/ISO standard for this enumeration constant.
     */
    private final short defaultVersion;

    /**
     * Creates a new enum constant with the given default version.
     */
    private Specification(final short defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    /**
     * The default version of OGC/ISO standard for this enumeration constant.
     * This is the version used when no value was explicitely given to the {@link UML#version()} annotation.
     * The meaning of this numerical code is documented in the Javadoc of this {@code Specification} constant.
     * This is usually the OGC revision number or ISO publication year.
     *
     * <div class="note"><b>Examples:</b>
     * <ul>
     *   <li>For {@link #OGC_03064}, version 10 stands for <cite>OGC 03-064r10</cite>.</li>
     *   <li>For {@link #ISO_19115}, version 2003 stands for <cite>ISO 19115:2003/Cor.1:2006(E)</cite>
     *       and version 2014 stands for <cite>ISO 19115:2014</cite>.</li>
     * </ul></div>
     *
     * @return The default version of OGC/ISO standard for this enumeration constant.
     *
     * @see UML#version()
     *
     * @since 3.1
     */
    public short defaultVersion() {
        return defaultVersion;
    }
}
