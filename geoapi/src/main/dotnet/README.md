# GeoAPI for .NET

> **Warning:** this binding is not final and is subject to change. It is being developed by the
> OGC GeoAPI Standards Working Group (SWG) for GeoAPI 4.0, and its design is under review.

GeoAPI for .NET provides C# interfaces for OGC/ISO geospatial standards, the same model already
available in Java and Python. It defines interfaces only: implementations come from separate libraries.
Code written against these interfaces can switch between implementations without changes.

Every type and member is traceable to the UML element of the standard it comes from,
through attributes that can be read at run time.

## Requirements

- .NET 10 (`net10.0`) or later.
- No dependencies beyond the .NET base class library.

## Status

| Namespace | Standard | Status |
| --- | --- | --- |
| `OpenGis.Annotation` | GeoAPI | Available |
| `OpenGis.Util`, `OpenGis.Util.Measure` | ISO 19103 | In progress |
| `OpenGis.Metadata` (subset needed by referencing) | ISO 19115-1 | Planned |
| `OpenGis.Referencing`, `.Crs`, `.Cs`, `.Datum`, `.Operation` | ISO 19111 | Planned |
| `OpenGis.Parameter` | ISO 19111 | Planned |
| `OpenGis.Geometry` (`IDirectPosition`, `IEnvelope` only) | ISO 19107 | Planned |

No NuGet package is published yet.

## Using the library

Until a package is published, reference the project from your own project file:

```xml
<ItemGroup>
  <ProjectReference Include="path/to/geoapi/src/main/dotnet/OpenGis/OpenGis.csproj" />
</ItemGroup>
```

### Reading UML traceability

`[Uml]` gives the UML identifier, obligation and specification of a type or member.
`[Classifier]` gives the UML stereotype of a type (for example, abstract).

```csharp
using System;
using System.Reflection;
using OpenGis.Annotation;

UmlAttribute uml = typeof(Obligation).GetCustomAttribute<UmlAttribute>()!;
Console.WriteLine($"{uml.Identifier} from {uml.Specification}:{uml.Specification.DefaultVersion}");
// Prints: MD_ObligationCode from Iso19115:2014
```

When `UmlAttribute.Version` is 0, the element comes from the specification's default version.
Members without `[Uml]` are GeoAPI convenience members, not part of the UML.

## Conventions

These rules apply to all namespaces. Knowing them lets you find any UML element in C#.

| UML / ISO | C# |
| --- | --- |
| Package, for example ISO 19111 `crs` | Namespace `OpenGis.Referencing.Crs` |
| Class `GeographicCRS` | Interface `IGeographicCrs` (`I` prefix) |
| Class `CI_Citation` | Interface `ICitation` (ISO two-letter prefix dropped) |
| Acronyms of 3+ letters | Pascal case: `Crs`, `Cs`, `Wkt`, `Uml` |
| Code list `MD_TopicCategoryCode` | Class `TopicCategory` (`Code` suffix dropped) |
| Attribute `coordinateSystem` | Get-only property `CoordinateSystem` |
| Operation, for example `inverse` | Method `Inverse()` |
| Multiplicity [0..1] or conditional | Nullable type (`T?`) |
| Multiplicity [0..*] | Read-only collection, never null (empty when there are no values) |

A non-nullable property may still return null in rare cases; each such case is documented on the member.

### Implementing the interfaces

- Implement only the members without a body. Interfaces provide defaults for convenience members,
  with the same behavior as in Java.
- When a sub-interface narrows a property type (for example a geographic CRS whose coordinate system is
  always ellipsoidal), it redeclares the property with `new` and forwards the base property to it.
  Implement the narrower property; the base one follows automatically.
- Mandatory properties must not return null. Optional and conditional ones may.

## Building and testing

From the repository root:

```bash
dotnet build geoapi/src/main/dotnet/OpenGis.sln
dotnet test  geoapi/src/main/dotnet/OpenGis.sln
```

The build treats warnings as errors and requires XML documentation on every public member.
Unit tests are in `geoapi/src/test/dotnet/OpenGis.Tests`.

## More information

- [DESIGN.md](DESIGN.md): goals, scope and design decisions.
- [TASKS.md](TASKS.md): implementation plan and progress.
- [GeoAPI website](http://www.geoapi.org): the Java and Python bindings and the GeoAPI specification.

## License

Apache License 2.0. Copyright © 2026 Open Geospatial Consortium, Inc.
