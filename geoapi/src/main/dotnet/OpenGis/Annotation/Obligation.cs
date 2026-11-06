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
/// Whether an element is mandatory, optional or has another obligation.
/// </summary>
// TODO(SWG): Q1 — C# enums cannot implement IControlledVocabulary (Java: identifier() and family()).
[Uml("MD_ObligationCode", Specification.Iso19115)]
public enum Obligation
{
    /// <summary>
    /// Element is always required.
    /// </summary>
    [Uml("mandatory", Specification.Iso19115)]
    Mandatory = 0,

    /// <summary>
    /// Element is not required.
    /// </summary>
    [Uml("optional", Specification.Iso19115)]
    Optional = 1,

    /// <summary>
    /// Element is required when a specific condition is met.
    /// </summary>
    [Uml("conditional", Specification.Iso19115)]
    Conditional = 2,

    /// <summary>
    /// Element should always be null. Used only when a sub-type overrides an association
    /// and forces it to a null value.
    /// </summary>
    /// <remarks>
    /// GeoAPI departure (constraint): ISO specifications sometimes override a parent member with a note
    /// saying that the member is not allowed for a particular class. Since C# member signatures cannot
    /// express this constraint, GeoAPI defines this obligation, which is not in the ISO specifications.
    /// An example is the anchor definition of a temporal datum.
    /// </remarks>
    Forbidden = 3,
}
