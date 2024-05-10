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
 * Some specifications are available both at <abbr>OGC</abbr> and <abbr>ISO</abbr>.
 *
 * <h2>Specification versions</h2>
 * Each specification has a {@linkplain #defaultVersion() default version} number, which identifies
 * more accurately the specification used by most non-deprecated GeoAPI elements:
 * <ul>
 *   <li>For <abbr>OGC</abbr> specifications, the version number is the <abbr>OGC</abbr> revision number.</li>
 *   <li>For <abbr>ISO</abbr> specifications, the version number is the publication year (ignoring corrigendum,
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
     * This specification provides basic classes such as names, records and units of measurement.
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
     * ISO 19107, Feature Geometry (OGC Topic 1).
     * This is the specification for package {@link org.opengis.geometry} and sub-packages.
     * It specifies the classes for describing the spatial characteristics of geometries,
     * and a set of spatial operations.
     * It treats vector geometry and topology up to three dimensions.
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
     * it defines classes for describing temporal characteristics of geographic information.
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
     * It defines principles for the definition of features.
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
     * ISO 19111, Referencing by coordinates (OGC Topic 2).
     * This is the specification for package {@link org.opengis.referencing} and sub-packages.
     * It defines the minimum data required to define coordinate reference systems,
     * and the classes for describe operations that change coordinate values.
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
     * ISO 19112, Spatial referencing by geographic identifiers.
     * This is the specification for package {@link org.opengis.referencing.gazetteer}.
     * It defines the essential components of a gazetteer.
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
     * ISO 19115-1, Metadata part 1: fundamentals (OGC Topic 11).
     * This is the specification for package {@link org.opengis.metadata} and most sub-packages.
     * It defines the classes for describing geographic information and services by means of metadata.
     * It provides information about the identification, the extent, the quality, the spatial and temporal aspects,
     * the content, the spatial reference, the portrayal, distribution, and other properties of digital geographic
     * data and services.
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
     * This is the specification for some sub-packages of {@link org.opengis.metadata}.
     * It extends the existing geographic metadata standard by defining the classes for
     * describing imagery and gridded data. It provides information about the properties
     * of the measuring equipment used to acquire the data, the geometry of the measuring process
     * employed by the equipment, and the production process used to digitize the raw data.
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
     * It defines an integrated <abbr>XML</abbr> implementation of ISO 19115‑1, ISO 19115‑2,
     * and concepts from ISO 19139.
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
     * It defines a schema describing the portrayal of geographic information in a form understandable by humans.
     * It does not include standardization of cartographic symbols, and their geometric and functional description.
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
     * ISO 19123, Schema for coverage geometry and functions (OGC Topic 6).
     * This is the specification for package {@link org.opengis.coverage} and sub-packages.
     * Coverages support mapping from a spatial, temporal or spatiotemporal domain to feature attribute values
     * where feature attribute types are common to all geographic positions within the domain.
     * ISO 19123 defines the relationship between the domain of a coverage and an associated attribute range.
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
     * It specifies the behaviour of a service that produces spatially referenced maps
     * dynamically from geographic information.
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
     * It defines Geographic MetaData XML ({@code gmd}) encoding,
     * an XML Schema implementation derived from ISO 19115.
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
     * It defines querying in order to obtain a subset of data which contains certain desired information.
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
     * It establishes the classes for describing the quality of geographic data.
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
     * It defines the structure and content of a text string implementation of the abstract model described in ISO 19111.
     * The string defines frequently needed types of coordinate reference systems and coordinate operations
     * in a self-contained form that is readable by machines and by humans.
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
    OGC_07022((short) 1);

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
