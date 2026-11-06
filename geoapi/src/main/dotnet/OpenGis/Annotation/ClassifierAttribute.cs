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
/// Specifies the stereotype (abstract, data type, union, ...) of a type, as declared in the OGC/ISO UML diagram.
/// </summary>
/// <remarks>
/// <para>
/// The UML class diagrams of ISO/OGC specifications declare some types as abstract, meaning that
/// instances are expected to implement one of their sub-types. C# has no "abstract interface",
/// so GeoAPI communicates the ISO/OGC intent with this attribute.
/// </para>
/// <para>
/// Implementations are not required to represent abstract interfaces by abstract classes.
/// This attribute is informative, for testing tools, reflection-based implementations or user interfaces.
/// When the attribute is absent, the stereotype is <see cref="Stereotype.Type"/>.
/// </para>
/// </remarks>
[AttributeUsage(
    AttributeTargets.Interface | AttributeTargets.Class | AttributeTargets.Struct | AttributeTargets.Enum,
    AllowMultiple = false,
    Inherited = false)]
public sealed class ClassifierAttribute : Attribute
{
    /// <summary>
    /// Initializes a new attribute with the given stereotype.
    /// </summary>
    /// <param name="value">The type of modeling element as declared in the OGC/ISO UML diagram.</param>
    public ClassifierAttribute(Stereotype value)
    {
        Value = value;
    }

    /// <summary>
    /// Gets the type of modeling element (type, data type, abstract, union or metaclass)
    /// as declared in the OGC/ISO UML diagram.
    /// </summary>
    public Stereotype Value { get; }
}
