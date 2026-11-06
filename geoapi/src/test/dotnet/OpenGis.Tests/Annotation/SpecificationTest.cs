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
using OpenGis.Annotation;
using Xunit;

namespace OpenGis.Tests.Annotation;

public sealed class SpecificationTest
{
    [Theory]
    [InlineData(Specification.Iso12113, 2022)]
    [InlineData(Specification.Iso17506, 2022)]
    [InlineData(Specification.Iso19103, 2015)]
    [InlineData(Specification.Iso19107, 2003)]
    [InlineData(Specification.Iso19108, 2002)]
    [InlineData(Specification.Iso19109, 2013)]
    [InlineData(Specification.Iso19111, 2019)]
    [InlineData(Specification.Iso19112, 2003)]
    [InlineData(Specification.Iso19115, 2014)]
    [InlineData(Specification.Iso19115Part2, 2019)]
    [InlineData(Specification.Iso19115Part3, 2016)]
    [InlineData(Specification.Iso19117, 2005)]
    [InlineData(Specification.Iso19123, 2005)]
    [InlineData(Specification.Iso19128, 2005)]
    [InlineData(Specification.Iso19139, 2007)]
    [InlineData(Specification.Iso19143, 2010)]
    [InlineData(Specification.Iso19157, 2013)]
    [InlineData(Specification.Iso19162, 2015)]
    [InlineData(Specification.Ogc01009, 1)]
    [InlineData(Specification.Ogc01004, 1)]
    [InlineData(Specification.OgcMovingFeature, 1)]
    public void DefaultVersionMatchesGeoApi(Specification specification, int expected)
    {
        Assert.Equal(expected, specification.DefaultVersion);
    }

    [Fact]
    public void EveryMemberHasDefaultVersion()
    {
        foreach (Specification specification in Enum.GetValues<Specification>())
        {
            Assert.True(specification.DefaultVersion > 0, specification.ToString());
        }
    }

    [Fact]
    public void UndefinedValueThrows()
    {
        Assert.Throws<ArgumentOutOfRangeException>(() => ((Specification)(-1)).DefaultVersion);
    }
}
