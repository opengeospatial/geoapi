# PRD: GeoAPI for .NET (OpenGIS)

October 12, 2026 · Venkata Kondepati

## Overview

GeoAPI for .NET adds a C# binding to the OGC GeoAPI standard, developed as GeoAPI Standards Working Group (SWG) work for GeoAPI 4.0. The GeoAPI maintainer has confirmed the binding is in scope; it has been requested before but lacked a volunteer inside OGC.

**Problem.** GeoAPI defines implementation-neutral interfaces for OGC/ISO geospatial models, but only for Java and Python. .NET libraries expose their own concrete types, so .NET applications are tied to one library and need custom glue to combine others (for example, NetTopologySuite geometry with ProjNet coordinate operations).

**Prior attempt.** The NetTopologySuite team maintained a separate GeoAPI.NET package. It was gutted in NTS 2.0 and archived in June 2023. The maintainers said it was never a complete port of OGC GeoAPI, the remainder was unlikely to be implemented in .NET, and the cost of keeping it standalone outweighed its value ([NTS GeoAPI #70](https://github.com/NetTopologySuite/GeoAPI/issues/70)). This PRD is designed to avoid those failure modes: it lives inside the official GeoAPI SWG, follows the normative UML, and ships with adapters and conformance tests so adoption does not depend on existing libraries changing.

## Goals, non-goals and success metrics

The goal is a normative, OGC-published C# binding of GeoAPI 4.0 that at least one real .NET library implements.

**Goals**

- Provide idiomatic C# interfaces for the GeoAPI 4.0 packages, starting with ISO 19111 referencing.
- Trace every interface and member to its source UML element and specification.
- Ship a .NET conformance test suite so implementers can verify compliance.
- Provide adapters for existing .NET libraries so the interfaces are usable from day one.
- Become part of the GeoAPI 4.0 specification, maintained by the SWG.

**Non-goals**

- Implementing coordinate transformations, geometry operations or metadata parsing. GeoAPI defines interfaces only.
- Replacing NetTopologySuite or ProjNet, or requiring them to change.
- Covering every GeoAPI package in the first release.
- Supporting .NET Framework or .NET Standard 2.0 in the first release (see Non-functional requirements).

**Success metrics**

| Metric | Target |
| --- | --- |
| Referencing interfaces accepted by the SWG | Phase 1 complete |
| UML traceability coverage | 100% of public interfaces and members |
| Conformance tests ported for covered packages | Referencing tests runnable on .NET |
| Working adapters | At least one (ProjNet or NTS) passing conformance tests |
| Independent implementers | At least one library or organization outside the SWG |
| Inclusion in GeoAPI 4.0 specification | .NET binding listed alongside Java and Python |

## Users and use cases

The primary users are .NET library authors who implement the interfaces and .NET application developers who code against them.

| User | Need | Use case |
| --- | --- | --- |
| Library implementer (e.g. ProjNet, NTS, commercial GIS SDKs) | A standard contract to implement | Implement `ICoordinateReferenceSystem` and pass the conformance suite |
| Application developer | Code once, swap libraries | Write a reprojection service against GeoAPI interfaces, switch the backing library by configuration |
| Enterprise and government integrators | Interoperability across vendors | Exchange CRS and metadata objects between components from different suppliers |
| Cross-language teams | Same model in Java, Python and .NET | Port logic or share designs across services written in different languages |
| OGC SWG and standards authors | A third binding that validates the language-neutral model | Find places where the UML is Java-specific and fix them in GeoAPI 4.0 |

## Lessons from the Java and Python bindings

The repo (master branch, GeoAPI 4.0) shows each binding is the same UML model rendered in its language's idioms, documented by mapping tables in the specification. The .NET binding should follow that pattern exactly.

| Aspect | Java (`geoapi/src/main/java`) | Python (`geoapi/src/main/python`) | Implication for .NET |
| --- | --- | --- | --- |
| Packages | `org.opengis.referencing.crs`, `.datum`, `.cs`, `.operation`, plus `metadata`, `parameter`, `util`, `geometry`, `feature`, `filter`, `temporal` | `opengis.referencing.crs`, `.datum`, `.cs`, `.operation`, `opengis.metadata`, `opengis.util` | `OpenGIS.Referencing.Crs` etc., same package split |
| Interface style | Interfaces with `getX()` methods | `ABC` classes with `@property` + `@abstractmethod`, snake_case | C# interfaces with get-only properties, PascalCase |
| Traceability | `@UML(identifier, obligation, specification, version)` on types and members; `Obligation`, `Specification`, `Stereotype` enums in `org.opengis.annotation` | Docstrings only ("derived from MD_ReferenceSystem") | A `[UML]` attribute mirroring Java, queryable by reflection |
| Optional values | `Optional<T>` used 77 times as a hint for [0..1]; spec says absence of `Optional` does not guarantee non-null | `Optional[T]` type hints | Nullable reference types (`T?`) as the hint |
| Default behavior | 161 `default` methods in 44 referencing files (e.g. `SingleCRS.getDatumEnsemble()` returns null) | Not applicable | Requires default interface members, so .NET Standard 2.1 / .NET Core 3.0+ |
| Code lists | Extensible `CodeList` class (`valueOf`) for open lists; `enum` for closed `GI_Enumeration` | `Enum` for both; spec notes Python lacks an extensible code list | `enum` for closed lists; an extensible `CodeList<T>` class for open lists |
| Units | JSR-385 (`javax.measure`), used in 8 referencing files | Own `StandardUnits` / `UnitsList` enums in `opengis.util.measure` | Minimal GeoAPI-defined unit interfaces (SWG decision) |
| Conformance | `geoapi-conformance`: validators (`CRSValidator`, `CSValidator`, `DatumValidator`), test cases (`ObjectFactoryTest`, `ParameterizedTransformTest`, `WKTParserTest`) | None of its own; a Java-Python bridge (`geoapi-java-python`) tests Python via a JVM | Port validators and test cases natively to .NET |
| Specification | Java profile annex (`profiles/java`, incl. units via JSR-385) | Python profile annex exists but is marked TODO | Add a .NET profile annex and a .NET column to every mapping table |

The specification's informative clauses (core types, date/time, collections, controlled vocabulary, names, internationalization, units) each hold a Java and a Python column. Adding a .NET column to each is the core deliverable of the specification work.

## Design principles and agreed decisions

The UML and ISO/OGC specifications are the authority; Java is a starting point, not the reference. These decisions were agreed with the GeoAPI maintainer by email in October 2026.

| Topic | Decision | Status |
| --- | --- | --- |
| Scope | .NET binding is in scope for the GeoAPI repo and GeoAPI 4.0, developed as GeoAPI SWG work | Agreed |
| Authoritative source | UML/specifications; Java interfaces may be used as inspiration or to save time | Agreed |
| Duplicated types | Where the UML duplicates a standard .NET type, use the .NET type | Agreed |
| Language style | Idiomatic C#: properties, nullable reference types, .NET naming | Agreed |
| Traceability | Each interface states the standard it derives from; mechanism is the C# contributors' choice (proposed: `[UML]` attribute) | Agreed in principle |
| Root namespace | `OpenGIS` (GIS upper case as an acronym), no `org.` prefix | Agreed |
| Package layout | Follows the UML packages, adapted to C# | Agreed |
| License and copyright | Apache 2.0 per OGC software licensing policy; OGC is copyright owner | Agreed |
| Units of measure | Minimal set of GeoAPI-defined interfaces; no dependency on a units library | Agreed |
| Repo location | `geoapi/src/main/dotnet`, beside `java` and `python` | Proposed |
| Target framework | .NET 10 LTS first; older frameworks only on demand | Proposed |
| NuGet publication | OGC-owned account, to be raised with OGC staff once a starting point exists | Open |
| Review | Maintainer reviews initially; then biweekly SWG meetings on OGC Agora | Agreed |

**Principles**

1. Interfaces only: no implementation logic beyond what Java default methods already define.
2. Minimal commitment: less API surface leaves more freedom to change later.
3. Same model, three languages: a concept maps to one C# type, documented in the specification's mapping tables.
4. Reflection-friendly: traceability metadata is available at runtime, as in Java.

## Functional requirements

Phase 1 delivers ISO 19111 referencing plus the minimum foundation it depends on; later phases follow the GeoAPI 4.0 package order.

### FR-1 Package scope by phase

| Phase | Namespaces | Source standard | Contents |
| --- | --- | --- | --- |
| 1 | `OpenGIS.Annotation` | GeoAPI | `UMLAttribute`, `Obligation`, `Specification`, `Stereotype` |
| 1 | `OpenGIS.Util` | ISO 19103 | `IGenericName` family, `IInternationalString`, `CodeList<T>`, `IFactory`, `FactoryException`, units of measure |
| 1 | `OpenGIS.Referencing`, `.Crs`, `.Cs`, `.Datum`, `.Operation` | ISO 19111:2019 | CRS, coordinate system, datum and operation interfaces, factories, `IMathTransform`, `IMatrix`, exceptions |
| 1 | `OpenGIS.Parameter` | ISO 19111 | Parameter descriptors and values used by operations |
| 1 | `OpenGIS.Metadata` (subset) | ISO 19115-1:2014 | Only what referencing needs: `ICitation`, `IIdentifier`, `IExtent` |
| 2 | `OpenGIS.Metadata` (full) | ISO 19115-1, 19115-2, 19157 | Remaining metadata and data quality |
| 3 | `OpenGIS.Geometry`, `.Feature`, `.Filter`, `.Temporal` | ISO 19107, 19109, 19143, 19108 | As stabilized in GeoAPI 4.0 |

Experimental interfaces from `geoapi-pending` are out of scope.

### FR-2 Traceability

Every public interface, property and enum member carries a `[UML]` attribute mirroring the Java annotation: identifier, obligation, specification and optional version. It is retained at runtime so tools can map C# members back to UML.

```csharp
[UML("SingleCRS", Specification.ISO_19111)]
public interface ISingleCRS : ICoordinateReferenceSystem
{
    [UML("coordinateSystem", Obligation.Mandatory, Specification.ISO_19111)]
    new ICoordinateSystem CoordinateSystem { get; }

    [UML("datum", Obligation.Conditional, Specification.ISO_19111)]
    IDatum? Datum { get; }

    [UML("datumEnsemble", Obligation.Conditional, Specification.ISO_19111)]
    IDatumEnsemble? DatumEnsemble => null;
}
```

### FR-3 Naming and member conventions

- Interfaces take the `I` prefix; Java getters become get-only properties (`getDatum()` → `Datum`).
- [0..1] and conditional properties are nullable (`T?`) as a hint, following the spec's multiplicity conventions; [0..∞] properties return empty collections, never null.
- Java default methods become default interface members with the same behavior.
- Closed enumerations become C# `enum`; open code lists use an extensible `CodeList<T>` class with a `ValueOf(string)` factory, matching Java's `CodeList`.
- Exceptions (`FactoryException`, `TransformException`, `NoSuchAuthorityCodeException`) are concrete classes, as in Java.

### FR-4 Core type mapping (proposed .NET column for the specification)

| ISO 19103 type | Java | Python | Proposed .NET |
| --- | --- | --- | --- |
| `Boolean` | `boolean` | `int` | `bool` |
| `Integer` | `int` (or `long`) | `int` | `int` (or `long`) |
| `Real` | `double` | `float` | `double` |
| `Decimal` | `BigDecimal` | `float` | `decimal` |
| `CharacterString` | `String` | `str` | `string` |
| `LanguageString` | `InternationalString` | `str` | `IInternationalString` |
| `URI` | `java.net.URI` | — | `System.Uri` |
| `UUID` | `java.util.UUID` | — | `System.Guid` |
| `Date` | `LocalDate` | `datetime.date` | `DateOnly` |
| `Time` | `OffsetTime` | `datetime.time` | `TimeOnly` (offset loss: open question) |
| `DateTime` | `ZonedDateTime` | `datetime.datetime` | `DateTimeOffset` |
| `LanguageCode` | `Locale` | `str` | `CultureInfo` |
| `CharacterSetCode` | `Charset` | `str` | `System.Text.Encoding` |
| `Set` | `java.util.Set` | `Set` | `IReadOnlySet<T>` |
| `Sequence` | `java.util.List` | `Sequence` | `IReadOnlyList<T>` |
| `Dictionary` | `java.util.Map` | `dict` | `IReadOnlyDictionary<TKey, TValue>` |

### FR-5 Units of measure

GeoAPI defines a minimal, implementation-neutral set in `OpenGIS.Util.Measure`, covering only what referencing needs. It aligns with ISO 19103:2024, which keeps only the base `Measure` and `UnitOfMeasure` types.

- `IUnitOfMeasure`: `Symbol`, `Name`, a `UnitKind` (length, angle, scale, time, other), `SystemUnit`, and conversion to the system unit.
- `IMeasure`: `Value` and `Unit`.
- No arithmetic, dimension algebra or parsing; implementers adapt their own units libraries.

### FR-6 Documentation

XML documentation comments on every public member, adapted from the Java Javadoc and UML definitions, with the UML identifier repeated in the text for search.

## Non-functional requirements

The binding targets .NET 10 LTS, lives beside the Java and Python sources, and builds independently of Maven.

| Area | Requirement |
| --- | --- |
| Target framework | `net10.0` (current LTS). Default interface members rule out .NET Standard 2.0; `netstandard2.1` revisited only on user demand |
| Language settings | Latest C# version, `<Nullable>enable</Nullable>`, warnings as errors, XML docs generated |
| Dependencies | None beyond the .NET base class library |
| Repo layout | Interfaces in `geoapi/src/main/dotnet`, mirroring `geoapi/src/main/python`; unit tests in `geoapi/src/test/dotnet`; conformance suite in `geoapi-conformance/src/main/dotnet` |
| Build | `dotnet build` / `dotnet test`; shared settings in `Directory.Build.props`; Maven build unaffected |
| CI | Separate GitHub Actions workflow: build, test, API documentation, public API diff |
| API stability | Track public surface with `Microsoft.CodeAnalysis.PublicApiAnalyzers`; breaking changes need SWG approval |
| Versioning | Package version follows GeoAPI (e.g. `4.0.0-preview.N` until GeoAPI 4.0 is final) |
| Packaging | NuGet packages `OpenGIS` (interfaces) and `OpenGIS.Conformance`; SourceLink and symbols; OGC-owned account pending OGC staff decision |
| License | Apache 2.0 header on every file; copyright Open Geospatial Consortium, Inc. |
| Documentation | API reference generated from XML docs (e.g. DocFX) and published beside the Java and Python docs on geoapi.org |
| Specification | New normative annex `profiles/dotnet` and a .NET column in each mapping table under `src/main/metanorma` |

Proposed layout:

```text
geoapi/src/main/dotnet/
  OpenGIS.sln
  Directory.Build.props
  OpenGIS/
    OpenGIS.csproj
    Annotation/
    Util/
    Referencing/
      Crs/  Cs/  Datum/  Operation/
    Parameter/
    Metadata/
geoapi/src/test/dotnet/
  OpenGIS.Tests/
geoapi-conformance/src/main/dotnet/
  OpenGIS.Conformance/
```

## Conformance and adoption

The binding succeeds only if real libraries implement it, so conformance tests and adapters ship alongside the interfaces.

**Conformance suite (`OpenGIS.Conformance`)**

- Port the Java validators first: `CRSValidator`, `CSValidator`, `DatumValidator`, `OperationValidator`, `ParameterValidator`. They check structural rules (non-null mandatory properties, axis consistency, valid ranges) on any implementation.
- Then port test cases: `ObjectFactoryTest`, `AuthorityFactoryTest`, `ParameterizedTransformTest`, `AffineTransformTest`, `WKTParserTest`, with the same sample points and tolerances.
- Package as a library implementers reference from their own xUnit, NUnit or MSTest projects.

**Adapters (separate packages, outside the normative binding)**

| Adapter | Wraps | Purpose |
| --- | --- | --- |
| `OpenGIS.Adapters.ProjNet` | ProjNet coordinate systems and transformations | First real implementation of referencing; runs the conformance suite |
| `OpenGIS.Adapters.NetTopologySuite` | NTS coordinates and envelopes | Bridge geometry types to referencing (Phase 3 geometry work) |

Adapters let applications use GeoAPI without ProjNet or NTS changing, which addresses the main reason GeoAPI.NET was dropped. Where adapters live (GeoAPI repo, OGC incubator or the library's own repo) is a SWG decision.

**Adoption activities**

- Invite ProjNet and NTS maintainers to review the referencing design before it is frozen.
- Recruit at least one commercial or government .NET implementer to the SWG meetings.
- Publish a sample application that switches implementations by configuration.

## Milestones and timeline

The proposed plan reaches a frozen referencing API by spring 2027 and a first implementation, verified by the conformance suite, by mid-2027. Dates are targets to agree with the SWG at the first Agora meeting.

| Phase | Target dates | Scope | Exit gate |
| --- | --- | --- | --- |
| 0 · Kickoff | Oct to Nov 2026 | First Agora meeting, ISO 19111 design doc, repo and CI skeleton | G1 Design approved: SWG accepts the mapping |
| 1 · Referencing | Dec 2026 to Mar 2027 | Util and annotations; CRS, CS, datum, operations; parameters; metadata subset | G2 API freeze: referencing reviewed |
| 2 · Conformance | Apr to Jun 2027 | Validators and tests, ProjNet adapter, full metadata | G3 Implemented: adapter passes the suite |
| 3 · Expansion | Jul to Dec 2027 | Geometry, feature, filter, temporal; .NET profile annex; NuGet release | Inclusion in GeoAPI 4.0 |

Each gate needs SWG sign-off before the next phase starts; the kickoff design document is the first commitment made to the maintainer.

## Open questions and risks

The main risk is the one that ended GeoAPI.NET: interfaces nobody implements. Most open questions are for the first SWG meetings.

**Open questions**

- [ ] NuGet publication under an OGC-owned account: raise with OGC staff once the Phase 1 draft exists.
- [ ] `Time` mapping: .NET has no offset-aware time-of-day type; use `TimeOnly`, `DateTimeOffset`, or a GeoAPI type?
- [ ] `PositionInTime` (Java `Temporal`): a .NET equivalent or a small GeoAPI interface?
- [ ] Extensible code lists: class-based `CodeList<T>` as in Java, or enums plus a string-backed escape hatch?
- [ ] Attribute name: `[UML]` as in Java, or a .NET-style name such as `[UmlElement]`?
- [ ] Where adapters live: GeoAPI repo, OGC incubator, or the ProjNet/NTS repos.
- [ ] Whether `netstandard2.1` is worth supporting for Unity and Xamarin-era users.

**Risks**

| Risk | Likelihood | Impact | Mitigation |
| --- | --- | --- | --- |
| No library adopts the interfaces | Medium | High | Ship adapters and conformance tests with Phase 1; engage ProjNet/NTS maintainers early |
| Single-contributor dependency | High | High | Recruit .NET users to SWG meetings; keep design docs in the repo |
| GeoAPI 4.0 UML still changing | Medium | Medium | Start with stable referencing; track Java master; public API analyzer flags drift |
| Java idioms leak into C# | Medium | Medium | Map from UML, review against .NET design guidelines each meeting |
| Default interface members surprise implementers | Low | Medium | Document in the .NET profile annex; keep defaults identical to Java |
| Units interfaces too thin for real use | Low | Medium | Validate against ProjNet's needs before freezing |

## Sources

- [opengeospatial/geoapi repository](https://github.com/opengeospatial/geoapi) (master branch, reviewed October 2026): Java interfaces, Python interfaces, `geoapi-conformance`, `geoapi-java-python`, and the specification under `src/main/metanorma`
- [Java `@UML` annotation](https://github.com/opengeospatial/geoapi/blob/master/geoapi/src/main/java/org/opengis/annotation/UML.java) and [`SingleCRS`](https://github.com/opengeospatial/geoapi/blob/master/geoapi/src/main/java/org/opengis/referencing/crs/SingleCRS.java)
- [Python `crs.py`](https://github.com/opengeospatial/geoapi/blob/master/geoapi/src/main/python/opengis/referencing/crs.py) and [`measure.py`](https://github.com/opengeospatial/geoapi/blob/master/geoapi/src/main/python/opengis/util/measure.py)
- [NetTopologySuite GeoAPI issue #70](https://github.com/NetTopologySuite/GeoAPI/issues/70): why GeoAPI.NET was removed
- [OGC software licensing policy](https://www.ogc.org/fr/about/policies/software-licenses/)
- Email correspondence with the GeoAPI maintainer, October 2026: scope, namespace, layout, license and units decisions
