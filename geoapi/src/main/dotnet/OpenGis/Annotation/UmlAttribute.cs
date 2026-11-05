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
using System;

namespace OpenGis.Annotation;

/// <summary>
/// Maps an interface, class, enumeration, property, method or enumeration member
/// to the UML element it comes from in an OGC/ISO specification.
/// </summary>
/// <remarks>
/// The attribute is available at run time, so tools can use reflection to trace
/// each GeoAPI element back to its UML identifier, obligation and specification.
/// Members without this attribute are GeoAPI derived or convenience members.
/// </remarks>
/// <example>
/// <code>
/// [Uml("SingleCRS", Specification.Iso19111)]
/// public interface ISingleCrs : ICoordinateReferenceSystem
/// {
///     [Uml("datum", Obligation.Conditional, Specification.Iso19111)]
///     IDatum? Datum { get; }
/// }
/// </code>
/// </example>
[AttributeUsage(
    AttributeTargets.Interface | AttributeTargets.Class | AttributeTargets.Struct | AttributeTargets.Enum |
    AttributeTargets.Property | AttributeTargets.Method | AttributeTargets.Field,
    AllowMultiple = false,
    Inherited = false)]
public sealed class UmlAttribute : Attribute
{
    /// <summary>
    /// Initializes a new attribute for a mandatory UML element.
    /// </summary>
    /// <param name="identifier">The UML identifier used in the specification.</param>
    /// <param name="specification">The specification where the UML element is defined.</param>
    /// <exception cref="ArgumentException"><paramref name="identifier"/> is null, empty or white space.</exception>
    public UmlAttribute(string identifier, Specification specification)
        : this(identifier, Obligation.Mandatory, specification)
    {
    }

    /// <summary>
    /// Initializes a new attribute for a UML element with the given obligation.
    /// </summary>
    /// <param name="identifier">The UML identifier used in the specification.</param>
    /// <param name="obligation">The obligation declared in the UML.</param>
    /// <param name="specification">The specification where the UML element is defined.</param>
    /// <exception cref="ArgumentException"><paramref name="identifier"/> is null, empty or white space.</exception>
    public UmlAttribute(string identifier, Obligation obligation, Specification specification)
    {
        ArgumentException.ThrowIfNullOrWhiteSpace(identifier);
        Identifier = identifier;
        Obligation = obligation;
        Specification = specification;
    }

    /// <summary>
    /// Gets the UML identifier of the annotated element, as used in the specification.
    /// Scripts can use this identifier to map a GeoAPI member to the UML element it comes from.
    /// </summary>
    public string Identifier { get; }

    /// <summary>
    /// Gets the obligation declared in the UML.
    /// If the obligation is <see cref="Obligation.Mandatory"/>, null values are not allowed.
    /// </summary>
    public Obligation Obligation { get; }

    /// <summary>
    /// Gets the specification where the UML element is defined.
    /// </summary>
    public Specification Specification { get; }

    /// <summary>
    /// Gets or sets the version of the specification where the UML element is defined,
    /// or 0 for the specification's default version.
    /// </summary>
    /// <remarks>
    /// Most elements keep the default value 0, meaning the version given by
    /// the specification's <c>DefaultVersion</c> (see <see cref="SpecificationExtensions"/>), usually the latest
    /// OGC/ISO version. A few elements use an older version on purpose: for example ISO 19115:2003
    /// defines <c>PT_Locale</c> in a way closer to <see cref="System.Globalization.CultureInfo"/>
    /// than ISO 19115:2014 does. Valid version numbers are documented on each
    /// <see cref="Annotation.Specification"/> member.
    /// </remarks>
    public int Version { get; set; }
}
