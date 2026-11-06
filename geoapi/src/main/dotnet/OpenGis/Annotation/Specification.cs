/*
 *    GeoAPI - .NET interfaces for OGC/ISO standards
 *    Copyright © 2026 Open Geospatial Consortium, Inc.
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
namespace OpenGis.Annotation;

/// <summary>
/// ISO/OGC specifications from which a type, member, enumeration or code list was derived.
/// Some specifications are available both at OGC and ISO.
/// </summary>
/// <remarks>
/// <para>
/// Each specification has a default version, given by the <c>DefaultVersion</c> extension property
/// (see <see cref="SpecificationExtensions"/>), which identifies the specification used by most GeoAPI elements:
/// </para>
/// <list type="bullet">
///   <item>For OGC specifications, the version number is the OGC revision number.</item>
///   <item>For ISO specifications, the version number is the publication year, ignoring corrigenda.
///     For example, version 2003 of <see cref="Iso19115"/> stands for ISO 19115:2003/Cor.1:2006.</item>
/// </list>
/// <para>
/// Versions other than the default may be declared in <see cref="UmlAttribute.Version"/>.
/// The version numbers used by GeoAPI are documented on each member.
/// </para>
/// </remarks>
public enum Specification
{
    /// <summary>
    /// ISO 12113, Runtime 3D asset delivery format — Khronos glTF 2.0.
    /// Classes for describing a 3D rendering scene, including materials, geometries and animations.
    /// </summary>
    /// <remarks>Versions: 2022 = ISO 12113:2022 (default).</remarks>
    Iso12113,

    /// <summary>
    /// ISO 17506, COLLADA digital asset schema specification for 3D visualization of industrial data.
    /// </summary>
    /// <remarks>Versions: 2022 = ISO 17506:2022 (default).</remarks>
    Iso17506,

    /// <summary>
    /// ISO 19103, Conceptual schema language.
    /// Basic classes such as names, records and units of measurement (namespace <c>OpenGis.Util</c>).
    /// </summary>
    /// <remarks>Versions: 2005 = ISO 19103:2005; 2015 = ISO 19103:2015 (default).</remarks>
    Iso19103,

    /// <summary>
    /// ISO 19107, Feature geometry (OGC Topic 1).
    /// Classes for the spatial characteristics of geometries and a set of spatial operations.
    /// </summary>
    /// <remarks>Versions: 2003 = ISO 19107:2003 (default).</remarks>
    Iso19107,

    /// <summary>
    /// ISO 19108, Temporal schema.
    /// </summary>
    /// <remarks>Versions: 2002 = ISO 19108:2002/Cor.1:2006 (default).</remarks>
    Iso19108,

    /// <summary>
    /// ISO 19109, Rules for application schema.
    /// </summary>
    /// <remarks>Versions: 2013 = ISO 19109:2013 draft (default).</remarks>
    Iso19109,

    /// <summary>
    /// ISO 19111, Referencing by coordinates (OGC Topic 2).
    /// </summary>
    /// <remarks>Versions: 2003 = OGC 03-073r1; 2007 = ISO 19111:2007; 2019 = ISO 19111:2019 (default).</remarks>
    Iso19111,

    /// <summary>
    /// ISO 19112, Spatial referencing by geographic identifiers.
    /// </summary>
    /// <remarks>Versions: 2003 = ISO 19112:2003 (default).</remarks>
    Iso19112,

    /// <summary>
    /// ISO 19115-1, Metadata part 1: fundamentals (OGC Topic 11).
    /// </summary>
    /// <remarks>
    /// Versions: 2003 = ISO 19115:2003/Cor.1:2006; 2014 = ISO 19115-1:2014 (default);
    /// 2018 = ISO 19115-1:2014/Amd 1:2018; 2020 = ISO 19115-1:2014/Amd 2:2020.
    /// </remarks>
    Iso19115,

    /// <summary>
    /// ISO 19115-2, Metadata part 2: extensions for acquisition and processing.
    /// </summary>
    /// <remarks>Versions: 2009 = ISO 19115-2:2009; 2019 = ISO 19115-2:2019 (default).</remarks>
    Iso19115Part2,

    /// <summary>
    /// ISO 19115-3, Metadata part 3: XML schema implementation for fundamental concepts.
    /// </summary>
    /// <remarks>Versions: 2016 = ISO 19115-3:2016 (default).</remarks>
    Iso19115Part3,

    /// <summary>
    /// ISO 19117, Portrayal.
    /// </summary>
    /// <remarks>Versions: 2005 = ISO 19117:2005 (default).</remarks>
    Iso19117,

    /// <summary>
    /// ISO 19123, Schema for coverage geometry and functions (OGC Topic 6).
    /// </summary>
    /// <remarks>Versions: 2005 = ISO 19123:2005 (default).</remarks>
    Iso19123,

    /// <summary>
    /// ISO 19128, Web map server interface.
    /// </summary>
    /// <remarks>Versions: 2005 = ISO 19128:2005 (default).</remarks>
    Iso19128,

    /// <summary>
    /// ISO 19139, Metadata XML schema implementation.
    /// </summary>
    /// <remarks>Versions: 2007 = ISO 19139:2007 (default).</remarks>
    Iso19139,

    /// <summary>
    /// ISO 19143, Filter encoding.
    /// </summary>
    /// <remarks>Versions: 2010 = ISO 19143:2010 (default).</remarks>
    Iso19143,

    /// <summary>
    /// ISO 19157, Data quality.
    /// </summary>
    /// <remarks>Versions: 2013 = ISO 19157:2013 (default).</remarks>
    Iso19157,

    /// <summary>
    /// ISO 19162, Well-known text representation of coordinate reference systems.
    /// </summary>
    /// <remarks>Versions: 2015 = ISO 19162:2015 (default).</remarks>
    Iso19162,

    /// <summary>
    /// OGC 01-009, Coordinate Transformation Services.
    /// Complements <see cref="Iso19111"/> where an aspect is not defined in the ISO specification.
    /// </summary>
    /// <remarks>Versions: 1 = OGC 01-009 (default).</remarks>
    Ogc01009,

    /// <summary>
    /// OGC 01-004, Grid Coverages.
    /// Complements <see cref="Iso19123"/> where an aspect is not defined in the ISO specification.
    /// </summary>
    /// <remarks>Versions: 1 = OGC 01-004 (default).</remarks>
    Ogc01004,

    /// <summary>
    /// OGC 18-075, Moving Features.
    /// Complements <see cref="Iso19109"/> for defining dynamic attributes.
    /// </summary>
    /// <remarks>Versions: 1 = OGC 18-075 (default).</remarks>
    OgcMovingFeature,
}
