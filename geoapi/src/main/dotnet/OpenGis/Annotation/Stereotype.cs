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
/// Type of modeling element as declared in the OGC/ISO UML diagram.
/// Values are associated with GeoAPI types by <see cref="ClassifierAttribute"/>.
/// </summary>
/// <remarks>
/// This enumeration is closely related to the ISO 19115 <c>MD_DatatypeCode</c> code list, but not identical,
/// since ISO 19115 treats data type and stereotype as two distinct pieces of information.
/// It does not include the code list and enumeration stereotypes, because those types are identified
/// by the code list base class and by C# <see langword="enum"/> respectively.
/// </remarks>
public enum Stereotype
{
    /// <summary>
    /// Class that specifies a domain of objects together with the operations applicable to them,
    /// without defining their physical implementation.
    /// This is the default for types without <see cref="ClassifierAttribute"/>.
    /// </summary>
    Type = 0,

    /// <summary>
    /// Encapsulation of data, as opposed to taxonomic or behavioral descriptions.
    /// Data types may not have an identity of their own and are usually aggregated
    /// into a container, such as being an attribute of another class.
    /// </summary>
    DataType = 1,

    /// <summary>
    /// Root class for a structural polymorphism. Abstract types are not directly instantiable.
    /// </summary>
    Abstract = 2,

    /// <summary>
    /// Type consisting of one and only one of several alternatives (listed as member attributes).
    /// </summary>
    Union = 3,

    /// <summary>
    /// Class whose instances are classes.
    /// </summary>
    Metaclass = 4,
}
