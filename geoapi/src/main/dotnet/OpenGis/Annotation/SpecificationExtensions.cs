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
/// Extension members for <see cref="Specification"/>.
/// </summary>
public static class SpecificationExtensions
{
    /// <summary>
    /// Extension members for a <see cref="Specification"/> value.
    /// </summary>
    /// <param name="specification">The specification.</param>
    extension(Specification specification)
    {
        /// <summary>
        /// Gets the default version of the OGC/ISO specification: the version used when
        /// <see cref="UmlAttribute.Version"/> is 0. This is usually the OGC revision number
        /// or the ISO publication year.
        /// </summary>
        /// <example>
        /// For <see cref="Specification.Iso19115"/>, version 2003 stands for ISO 19115:2003/Cor.1:2006
        /// and version 2014 stands for ISO 19115-1:2014.
        /// </example>
        /// <exception cref="ArgumentOutOfRangeException">The value is not a defined <see cref="Specification"/> member.</exception>
        public int DefaultVersion => specification switch
        {
            Specification.Iso12113         => 2022,
            Specification.Iso17506         => 2022,
            Specification.Iso19103         => 2015,
            Specification.Iso19107         => 2003,
            Specification.Iso19108         => 2002,
            Specification.Iso19109         => 2013,
            Specification.Iso19111         => 2019,
            Specification.Iso19112         => 2003,
            Specification.Iso19115         => 2014,
            Specification.Iso19115Part2    => 2019,
            Specification.Iso19115Part3    => 2016,
            Specification.Iso19117         => 2005,
            Specification.Iso19123         => 2005,
            Specification.Iso19128         => 2005,
            Specification.Iso19139         => 2007,
            Specification.Iso19143         => 2010,
            Specification.Iso19157         => 2013,
            Specification.Iso19162         => 2015,
            Specification.Ogc01009         => 1,
            Specification.Ogc01004         => 1,
            Specification.OgcMovingFeature => 1,
            _ => throw new ArgumentOutOfRangeException(nameof(specification), specification, "Undefined specification."),
        };
    }
}
