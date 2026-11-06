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
using System.Reflection;
using OpenGis.Annotation;
using Xunit;

namespace OpenGis.Tests.Annotation;

public sealed class UmlAttributeTest
{
    [Fact]
    public void AttributeUsageAllowsTypesAndMembers()
    {
        AttributeUsageAttribute usage = typeof(UmlAttribute).GetCustomAttribute<AttributeUsageAttribute>()!;
        Assert.Equal(
            AttributeTargets.Interface | AttributeTargets.Class | AttributeTargets.Struct | AttributeTargets.Enum |
            AttributeTargets.Property | AttributeTargets.Method | AttributeTargets.Field,
            usage.ValidOn);
        Assert.False(usage.AllowMultiple);
        Assert.False(usage.Inherited);
    }

    [Fact]
    public void TwoArgumentConstructorDefaultsToMandatory()
    {
        var uml = new UmlAttribute("SingleCRS", Specification.Iso19111);
        Assert.Equal("SingleCRS", uml.Identifier);
        Assert.Equal(Obligation.Mandatory, uml.Obligation);
        Assert.Equal(Specification.Iso19111, uml.Specification);
        Assert.Equal(0, uml.Version);
    }

    [Fact]
    public void ThreeArgumentConstructorKeepsObligation()
    {
        var uml = new UmlAttribute("datum", Obligation.Conditional, Specification.Iso19111) { Version = 2007 };
        Assert.Equal("datum", uml.Identifier);
        Assert.Equal(Obligation.Conditional, uml.Obligation);
        Assert.Equal(Specification.Iso19111, uml.Specification);
        Assert.Equal(2007, uml.Version);
    }

    [Theory]
    [InlineData(null)]
    [InlineData("")]
    [InlineData("  ")]
    public void RejectsMissingIdentifier(string? identifier)
    {
        Assert.ThrowsAny<ArgumentException>(() => new UmlAttribute(identifier!, Specification.Iso19111));
    }

    [Fact]
    public void IsReadableByReflection()
    {
        UmlAttribute uml = typeof(Obligation).GetCustomAttribute<UmlAttribute>()!;
        Assert.Equal("MD_ObligationCode", uml.Identifier);
        Assert.Equal(Specification.Iso19115, uml.Specification);
    }
}
