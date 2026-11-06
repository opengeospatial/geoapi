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

public sealed class ClassifierAttributeTest
{
    [Classifier(Stereotype.Abstract)]
    private interface IAbstractSample;

    private interface ISubSample : IAbstractSample;

    [Fact]
    public void AttributeUsageAllowsTypesOnly()
    {
        AttributeUsageAttribute usage = typeof(ClassifierAttribute).GetCustomAttribute<AttributeUsageAttribute>()!;
        Assert.Equal(
            AttributeTargets.Interface | AttributeTargets.Class | AttributeTargets.Struct | AttributeTargets.Enum,
            usage.ValidOn);
        Assert.False(usage.AllowMultiple);
        Assert.False(usage.Inherited);
    }

    [Fact]
    public void IsReadableByReflection()
    {
        Assert.Equal(Stereotype.Abstract, typeof(IAbstractSample).GetCustomAttribute<ClassifierAttribute>()!.Value);
    }

    [Fact]
    public void IsNotInheritedAndDefaultsToType()
    {
        Assert.Null(typeof(ISubSample).GetCustomAttribute<ClassifierAttribute>());
        Assert.Equal(Stereotype.Type, default(Stereotype));
    }
}
