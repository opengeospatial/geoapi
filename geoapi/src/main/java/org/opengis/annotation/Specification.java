/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2024 Open Geospatial Consortium, Inc.
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
 * ISO/OGC specifications from which an interface, method, enumeration or code list was derived.
 * Some specifications are available both at OGC and ISO.
 *
 * <h2>Specification versions</h2>
 * Each specification has a {@linkplain #defaultVersion() default version} number, which identifies
 * more accurately the specification used by the vast majority of non-deprecated GeoAPI elements:
 * <ul>
 *   <li>For OGC specifications, the version number is the OGC revision number.</li>
 *   <li>For ISO specifications, the version number is the publication year (ignoring corrigendum,
 *       which are implicit). For example if the specification is {@link #ISO_19115}, then version
 *       2003 stands for <cite>ISO 19115:2003/Cor.1:2006</cite>.</li>
 * </ul>
 * The version numbers are documented in {@code Specification} enumeration constants.
 * Versions other than the {@linkplain #defaultVersion() default version} may be declared
 * in the {@link UML#version()} annotation of some GeoAPI elements, usually (but not only)
 * for deprecated elements.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Cédric Briançon (Geomatys)
 * @author  Johann Sorel (Geomatys)
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
     * <div class="note">
     * <p>Provides rules and guidelines for the use of a conceptual schema language
     * within the context of geographic information.
     * The chosen conceptual schema language is the Unified Modeling Language (UML).</p>
     *
     * <p>ISO 19103 provides a profile of UML for use with geographic information.
     * The standardization target type of this standard is UML schemas describing geographic information.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2005:</b> ISO 19103:2005</li>
     *   <li><b>2015:</b> ISO 19103:2015   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/56734.html">ISO 19103:2015 on standards catalogue</a>
     */
    ISO_19103((short) 2015),

    /**
     * ISO 19107, Feature Geometry
     * (<a href="http://www.opengeospatial.org/standards/as">OGC Topic 1</a>).
     * This is the specification for package {@link org.opengis.geometry} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Specifies conceptual schemas for describing the spatial characteristics of
     * geographic features, and a set of spatial operations consistent with these schemas. It treats
     * vector geometry and topology up to three dimensions. It defines standard spatial operations
     * for use in access, query, management, processing, and data exchange of geographic information
     * for spatial (geometric and topological) objects of up to three topological dimensions embedded
     * in coordinate spaces of up to three axes.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2003:</b> ISO 19107:2003   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/26012.html">ISO 19107:2003 on standards catalogue</a>
     */
    ISO_19107((short) 2003),

    /**
     * ISO 19108, Temporal Schema.
     * This is the specification for package {@link org.opengis.temporal} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Defines concepts for describing temporal characteristics of geographic
     * information. It depends upon existing information technology standards for the interchange
     * of temporal information. It provides a basis for defining temporal feature attributes,
     * feature operations, and feature associations, and for defining the temporal aspects of
     * metadata about geographic information. Since this International Standard is concerned with
     * the temporal characteristics of geographic information as they are abstracted from the real
     * world, it emphasizes valid time rather than transaction time.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2002:</b> ISO 19108:2002/Cor.1:2006   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/26013.html">ISO 19108:2002 on standards catalogue</a>
     * @see <a href="https://www.iso.org/standard/44883.html">Corrigendum</a>
     */
    ISO_19108((short) 2002),

    /**
     * ISO 19109, Rules for application schema.
     * This is the specification for package {@link org.opengis.feature}.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
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
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2013:</b> ISO 19109:2013 draft   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/59193.html">ISO 19109:2015 on standards catalogue</a>
     *
     * @since 3.1
     */
    ISO_19109((short) 2013),

    /**
     * ISO 19111, Referencing by coordinates
     * (<a href="http://www.opengeospatial.org/standards/as">OGC Topic 2</a>).
     * This is the specification for package {@link org.opengis.referencing} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Defines the conceptual schema for the description of referencing by coordinates.
     * It describes the minimum data required to define coordinate reference systems.
     * This document supports the definition of:</p>
     * <ul>
     *   <li>spatial coordinate reference systems where coordinate values do not change with time. The system may:
     *     <ul>
     *       <li>be geodetic and apply on a national or regional basis, or</li>
     *       <li>apply locally such as for a building or construction site, or</li>
     *       <li>apply locally to an image or image sensor;</li>
     *       <li>be referenced to a moving platform such as a car, a ship, an aircraft or a spacecraft.
     *           Such a coordinate reference system can be related to a second coordinate reference system
     *           which is referenced to the Earth through a transformation that includes a time element;</li>
     *     </ul>
     *   </li>
     *   <li>spatial coordinate reference systems in which coordinate values of points on or near the surface
     *       of the earth change with time due to tectonic plate motion or other crustal deformation.
     *       Such dynamic systems include time evolution, however they remain spatial in nature;</li>
     *   <li>parametric coordinate reference systems which use a non-spatial parameter that varies monotonically
     *       with height or depth;</li>
     *   <li>temporal coordinate reference systems which use dateTime, temporal count or temporal measure quantities
     *       that vary monotonically with time;</li>
     *   <li>mixed spatial, parametric or temporal coordinate reference systems.</li>
     * </ul>
     * <p>The definition of a coordinate reference system does not change with time,
     * although in some cases some of the defining parameters can include a rate of change of the parameter.
     * The coordinate values within a dynamic and in a temporal coordinate reference system can change with time.</p>
     *
     * <p>This document also describes the conceptual schema for defining the information required to describe
     * operations that change coordinate values. In addition to the minimum data required for the definition of
     * the coordinate reference system or coordinate operation, the conceptual schema allows additional descriptive
     * information – coordinate reference system metadata – to be provided.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2003:</b> OGC 03-073r1</li>
     *   <li><b>2007:</b> ISO 19111:2007</li>
     *   <li><b>2019:</b> ISO 19111:2019   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see #OGC_01009
     * @see <a href="https://www.iso.org/standard/74039.html">ISO 19111:2019 on standards catalogue</a>
     * @see <a href="https://portal.ogc.org/files/18-005r5">Download from OGC</a>
     */
    ISO_19111((short) 2019),

    /**
     * ISO 19111-2, Part 2: Extension for parametric values
     * This is the specification for classes {@link org.opengis.referencing.crs.ParametricCRS} and associated parametric classes.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Extends the existing spatial referencing standard by defining the schema
     * required for describing parameterized systems.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2009:</b> ISO 19111-2:2009   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/fr/standard/44075.html">ISO 19111-2:2009 on standards catalogue</a>
     *
     * @since 3.1
     *
     * @deprecated To be removed after we upgraded to ISO 19111:2019.
     * This enumeration was not present in GeoAPI 3.0.2, so it is safe to remove.
     */
    @Deprecated(forRemoval=true)
    ISO_19111_2((short) 2009),

    /**
     * ISO 19112, Spatial referencing by geographic identifiers.
     * This is the specification for package {@link org.opengis.referencing.gazetteer}.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Defines the conceptual schema for spatial references based on geographic identifiers.
     * It establishes a general model for spatial referencing using geographic identifiers,
     * defines the components of a spatial reference system and defines the essential components of a gazetteer.
     * Spatial referencing by coordinates is not addressed in this document; however, a mechanism for recording
     * complementary coordinate references is included.</p>
     *
     * <p>ISO 19112 assists users in understanding the spatial references used in datasets.
     * It enables gazetteers to be constructed in a consistent manner and supports the development of other standards
     * in the field of geographic information. It is applicable to digital geographic data, and its principles may be
     * extended to other forms of geographic data such as maps, charts and textual documents.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2003:</b> ISO 19112:2003   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/26017.html">ISO 19112:2003 on standards catalogue</a>
     * @see <a href="https://portal.opengeospatial.org/files/?artifact_id=46964">OGC 11-122r1 Gazetteer Service — Profile of the Web Feature Service</a>
     *
     * @since 3.1
     */
    ISO_19112((short) 2003),

    /**
     * ISO 19115-1, Metadata part 1: fundamentals
     * (<a href="http://www.opengeospatial.org/standards/as">OGC Topic 11</a>).
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
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
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2003:</b> ISO 19115:2003/Cor.1:2006</li>
     *   <li><b>2014:</b> ISO 19115-1:2014   ({@linkplain #defaultVersion() default version})</li>
     *   <li><b>2018:</b> ISO 19115-1:2014/Amd 1:2018</li>
     *   <li><b>2020:</b> ISO 19115-1:2014/Amd 2:2020</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/53798.html">ISO 19115-1:2014 on standards catalogue</a>
     */
    ISO_19115((short) 2014),

    /**
     * ISO 19115-2, Metadata part 2: Extensions for acquisition and processing.
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Extends the existing geographic metadata standard by defining the schema
     * required for describing imagery and gridded data. It provides information about the properties
     * of the measuring equipment used to acquire the data, the geometry of the measuring process
     * employed by the equipment, and the production process used to digitize the raw data. This
     * extension deals with metadata needed to describe the derivation of geographic information
     * from raw data, including the properties of the measuring system, and the numerical methods
     * and computational procedures used in the derivation. The metadata required to address coverage
     * data in general is addressed sufficiently in the general part of ISO 19115.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2009:</b> ISO 19115-2:2009</li>
     *   <li><b>2019:</b> ISO 19115-2:2019   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/67039.html">ISO 19115-2:2019 on standards catalogue</a>
     */
    ISO_19115_2((short) 2019),

    /**
     * ISO 19115-3, Metadata part 3: XML schema implementation for fundamental concepts.
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Defines an integrated XML implementation of ISO 19115‑1, ISO 19115‑2, and concepts from ISO/TS 19139
     * by defining the following artefacts:</p>
     *
     * <ul>
     *   <li>a set of XML schema required to validate metadata instance documents conforming to
     *     conceptual model elements defined in ISO 19115‑1, ISO 19115‑2, and ISO/TS 19139;</li>
     *   <li>a set of ISO/IEC 19757‑3 (Schematron) rules that implement validation constraints
     *     in the ISO 19115‑1 and ISO 19115‑2 UML models that are not validated by the XML schema;</li>
     *   <li>an Extensible Stylesheet Language Transformation (XSLT) for transforming ISO 19115-1 metadata
     *     encoded using the ISO/TS 19139 XML schema and ISO 19115‑2 metadata encoded using the ISO/TS 19139‑2
     *     XML schema into an equivalent document that is valid against the XML schema defined in this document.</li>
     * </ul>
     *
     * <p>ISO/TS 19115-3:2016 describes the procedure used to generate XML schema from ISO geographic information
     * conceptual models related to metadata. The procedure includes creation of an UML model for XML implementation
     * derived from the conceptual UML model.</p>
     *
     * <p>This implementation model does not alter the semantics of the target conceptual model, but adds abstract
     * classes that remove dependencies between model packages, tagged values and stereotypes required by the UML
     * to XML transformation software, and refactors the packaging of a few elements into XML namespaces.
     * The XML schema has been generated systematically from the UML model for XML implementation according
     * to the rules defined in ISO/TS 19139 or ISO 19118.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2016:</b> ISO 19115-3:2016   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/32579.html">ISO/TS 19115-3:2016 on standards catalogue</a>
     *
     * @since 3.1
     */
    ISO_19115_3((short) 2016),

    /**
     * ISO 19117, Portrayal.
     * This is an abstract specification for portraying features.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Defines a schema describing the portrayal of geographic information in a
     * form understandable by humans. It includes the methodology for describing symbols and mapping
     * of the schema to an application schema. It does not include standardization of cartographic
     * symbols, and their geometric and functional description.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2005:</b> ISO 19117:2005   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/40395.html">ISO 19117:2005 on standards catalogue</a>
     */
    ISO_19117((short) 2005),

    /**
     * ISO 19123, Schema for coverage geometry and functions
     * (<a href="http://www.opengeospatial.org/standards/as">OGC Topic 6</a>).
     * This is the specification for package {@link org.opengis.coverage} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
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
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2005:</b> ISO 19123:2005   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see #OGC_01004
     * @see <a href="https://www.iso.org/standard/40121.html">ISO 19123:2005 on standards catalogue</a>
     * @see <a href="http://portal.opengeospatial.org/files/?artifact_id=19820">Download from OGC</a>
     */
    ISO_19123((short) 2005),

    /**
     * ISO 19128, Web map server interface.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Specifies the behaviour of a service that produces spatially referenced
     * maps dynamically from geographic information. It specifies operations to retrieve a description
     * of the maps offered by a server, to retrieve a map, and to query a server about features
     * displayed on a map. ISO 19128 is applicable to pictorial renderings of maps in a
     * graphical format; it is not applicable to retrieval of actual feature data or coverage data
     * values.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2005:</b> ISO 19128:2005   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/32546.html">ISO 19128:2005 on standards catalogue</a>
     * @see <a href="http://portal.opengeospatial.org/files/?artifact_id=5316">Download from OGC</a>
     */
    ISO_19128((short) 2005),

    /**
     * ISO 19139, Metadata XML schema implementation.
     * This is the specification for package {@link org.opengis.metadata} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Defines Geographic MetaData XML ({@code gmd}) encoding, an XML Schema
     * implementation derived from ISO 19115.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2007:</b> ISO 19139:2007   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/32557.html">ISO/TS 19139:2007 on standards catalogue</a>
     *
     * @deprecated since {@link #ISO_19115_3} publication, this standard does not define new elements used by GeoAPI.
     */
    @Deprecated(since="3.1")
    ISO_19139((short) 2007),

    /**
     * ISO 19143, Filter encoding.
     * This is the specification for package {@link org.opengis.filter} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * A fundamental operation performed on a set of data or resources is that of querying
     * in order to obtain a subset of the data which contains certain desired information
     * that satisfies some query criteria and which is also, perhaps, sorted in some specified manner.
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2010:</b> ISO 19143:2010   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/42137.html">ISO 19143:2010 on standards catalogue</a>
     * @see <a href="http://docs.opengeospatial.org/is/09-026r2/09-026r2.html">OGC 09-026 on OGC Public Document Repository</a>
     *
     * @since 3.1
     */
    ISO_19143((short) 2010),

    /**
     * ISO 19157, Data quality.
     * This is the specification for package {@link org.opengis.metadata.quality} and sub-packages.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Establishes the principles for describing the quality of geographic data:</p>
     * <ul>
     *   <li>defines components for describing data quality;</li>
     *   <li>specifies components and content structure of a register for data quality measures;</li>
     *   <li>describes general procedures for evaluating the quality of geographic data;</li>
     *   <li>establishes principles for reporting data quality.</li>
     * </ul>
     *
     * <p>ISO 19157:2013 also defines a set of data quality measures for use in evaluating and reporting data quality.
     * It is applicable to data producers providing quality information to describe and assess how well a data set
     * conforms to its product specification and to data users attempting to determine whether or not specific
     * geographic data are of sufficient quality for their particular application.
     * ISO 19157:2013 does not attempt to define minimum acceptable levels of quality for geographic data.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2013:</b> ISO 19157:2013   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/32575.html">ISO/TS 19157:2013 on standards catalogue</a>
     *
     * @since 3.1
     */
    ISO_19157((short) 2013),

    /**
     * ISO 19162, Well known text representation of coordinate reference systems.
     * This is the specification of input and output format of
     * {@link org.opengis.referencing.crs.CRSFactory#createFromWKT(String)} and
     * {@link org.opengis.referencing.crs.CoordinateReferenceSystem#toWKT()}.
     *
     * <p><b>ISO abstract:</b></p>
     * <div class="note">
     * <p>Defines the structure and content of a text string implementation of the abstract model for coordinate reference systems
     * described in ISO 19111:2007 and ISO 19111-2:2009. The string defines frequently needed types of coordinate reference systems
     * and coordinate operations in a self-contained form that is easily readable by machines and by humans.
     * The essence is its simplicity; as a consequence there are some constraints upon the more open content allowed in ISO 19111:2007.
     * To retain simplicity in the well-known text (WKT) description of coordinate reference systems and coordinate operations,
     * the scope of this International Standard excludes parameter grouping and pass-through coordinate operations.
     * The text string provides a means for humans and machines to correctly and unambiguously interpret and utilise
     * a coordinate reference system definition with look-ups or cross references only to define coordinate operation mathematics.
     * Because it omits metadata about the source of the data and may omit metadata about the applicability of the information,
     * the WKT string is not suitable for the storage of definitions of coordinate reference systems or coordinate operations.</p>
     * </div>
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>2015:</b> ISO 19162:2015   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see <a href="https://www.iso.org/standard/63094.html">ISO 19162:2015 on standards catalogue</a>
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
     *
     * @deprecated This specification has been retired.
     */
    @Deprecated(since="3.1")
    OGC_03064((short) 10),

    /**
     * Coordinate Transformation Services.
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
     * Grid Coverages.
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
     * Moving Features specification.
     * This is the specification used as a complement of {@linkplain #ISO_19109 ISO 10109}
     * for defining dynamic attributes.
     *
     * <p><b>Version numbers used in GeoAPI:</b></p>
     * <ul>
     *   <li><b>1:</b> OGC 18-075   ({@linkplain #defaultVersion() default version})</li>
     * </ul>
     *
     * @see #ISO_19109
     * @see <a href="http://docs.opengeospatial.org/is/18-075/18-075.html">OGC 18-075 on OGC Public Document Repository</a>
     *
     * @since 3.1
     */
    OGC_MOVING_FEATURE((short) 1),

    /**
     * Observations and Measurements, part 1.
     * This is the specification for package {@link org.opengis.observation}.
     *
     * @deprecated To be replaced by Topic 20: Observations and Measurements
     * @todo Remove before release, since it was not part of GeoAPI 3.0.2.
     *
     * @since 3.1
     */
    @Deprecated(forRemoval=true)
    OGC_07022((short) 1),

    /**
     * Specification not yet determined. This is a temporary enumeration
     * for the processing of API submitted by some contributors.
     *
     * @deprecated To be removed.
     */
    @Deprecated(since="3.1", forRemoval=true)
    UNSPECIFIED((short) 1);

    /**
     * The default version of OGC/ISO standard for this enumeration constant.
     */
    private final short defaultVersion;

    /**
     * Creates a new enum constant with the given default version.
     *
     * @param defaultVersion  the default version of OGC/ISO standard for this enumeration constant.
     */
    private Specification(final short defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    /**
     * The default version of OGC/ISO standard for this enumeration constant.
     * This is the version used when no value was explicitly given to the {@link UML#version()} annotation.
     * The meaning of this numerical code is documented in the Javadoc of this {@code Specification} constant.
     * This is usually the OGC revision number or ISO publication year.
     *
     * <div class="note"><b>Examples:</b>
     * <ul>
     *   <li>For {@link #OGC_03064}, version 10 stands for <cite>OGC 03-064r10</cite>.</li>
     *   <li>For {@link #ISO_19115}, version 2003 stands for <cite>ISO 19115:2003/Cor.1:2006</cite>
     *       and version 2014 stands for <cite>ISO 19115:2014</cite>.</li>
     * </ul></div>
     *
     * @return the default version of OGC/ISO standard for this enumeration constant.
     *
     * @see UML#version()
     *
     * @since 3.1
     */
    public short defaultVersion() {
        return defaultVersion;
    }
}
