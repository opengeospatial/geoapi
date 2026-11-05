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
using System.Reflection;
using OpenGis.Annotation;
using Xunit;

namespace OpenGis.Tests.Annotation;

public sealed class ObligationTest
{
    private static UmlAttribute? UmlOf(Obligation value)
        => typeof(Obligation).GetField(value.ToString())!.GetCustomAttribute<UmlAttribute>();

    [Theory]
    [InlineData(Obligation.Mandatory, "mandatory")]
    [InlineData(Obligation.Optional, "optional")]
    [InlineData(Obligation.Conditional, "conditional")]
    public void MembersCarryUmlIdentifier(Obligation value, string identifier)
    {
        UmlAttribute uml = UmlOf(value)!;
        Assert.Equal(identifier, uml.Identifier);
        Assert.Equal(Specification.Iso19115, uml.Specification);
    }

    [Fact]
    public void ForbiddenIsGeoApiDeparture()
    {
        Assert.Null(UmlOf(Obligation.Forbidden));
    }

    [Fact]
    public void DefaultIsMandatory()
    {
        Assert.Equal(Obligation.Mandatory, default(Obligation));
    }
}
