# Tasks: GeoAPI .NET binding

Task list derived from `CLAUDE.md` §6 (work plan) and `DESIGN.md` (FR-1 to FR-6, non-functional
requirements, milestones). Where they disagree, `CLAUDE.md` wins; differences are listed at the end.

Status: `[ ]` to do · `[~]` in review · `[x]` approved by Venkata · `[-]` deferred/blocked

## Rules for every task

1. Plan first: list files, show the Java -> C# mapping for every member, list anything the rules do not cover. No files until approved.
2. Read the Java source of every interface ported; copy `@UML` identifier and obligation exactly. Flag suspicious identifiers, never fix silently.
3. Apache 2.0 / OGC license header on every `.cs` file; XML doc comment on every public member, adapted from the Javadoc.
4. Open questions get `// TODO(SWG): ...` and are reported, not decided.
5. Finish with `dotnet build geoapi/src/main/dotnet/OpenGis.sln` (zero warnings) and `dotnet test geoapi/src/main/dotnet/OpenGis.sln`.
6. Stop for review after each task. Never commit, push or open PRs.

## Phase 0: foundation

### [x] 0.1 Solution skeleton

- Create `geoapi/src/main/dotnet/OpenGis.sln`, `Directory.Build.props`, `OpenGis/OpenGis.csproj`.
- `Directory.Build.props`: `net10.0`, `Nullable` enable, `TreatWarningsAsErrors` true, `GenerateDocumentationFile` true, `LangVersion` latest.
- Folders: `Annotation/ Util/ Util/Measure/ Metadata/ Parameter/ Referencing/{Crs,Cs,Datum,Operation}/ Geometry/`.
- Create `geoapi/src/test/dotnet/OpenGis.Tests` (xUnit; test packages allowed in the test project only).
- Checkpoint: approval needed to add `bin/` and `obj/` to `.gitignore`.
- Done when: empty solution builds with zero warnings and `dotnet test` runs.
- Done: classic `.sln` format; `geoapi/src/test/dotnet/Directory.Build.props` imports the main props; xUnit 2.9.3;
  `bin/` and `obj/` added to the root `.gitignore`. CLAUDE.md §8 test command needs updating to the solution path.

### [x] 0.2 `OpenGis.Annotation`

Source: `geoapi/src/main/java/org/opengis/annotation/`.

| Java | C# |
| --- | --- |
| `UML.java` | `UmlAttribute` (`[Uml(identifier, obligation, specification)]`, optional version) |
| `Classifier.java` | `ClassifierAttribute` |
| `Obligation.java` | `Obligation` enum, incl. `Forbidden` |
| `Specification.java` | `Specification` enum, Pascal-case members (`Iso19111`, ...) |
| `Stereotype.java` | `Stereotype` enum |
| `ResourceBundles.java` | not ported (Java-only mechanism) |

- Tests: attributes readable by reflection on types, properties and enum members.
- Done: `Version`/`DefaultVersion` are `int`; `Stereotype.DataType`; `Iso19115Part2`/`Iso19115Part3`;
  `DefaultVersion` is a C# 14 extension property in `SpecificationExtensions`; `OGC_07022` (marked for removal in Java) not ported.

### [ ] 0.3 `OpenGis.Util` and `OpenGis.Util.Measure`

Source: `geoapi/src/main/java/org/opengis/util/`.

| Java | C# |
| --- | --- |
| `GenericName`, `LocalName`, `ScopedName`, `MemberName`, `TypeName`, `NameSpace`, `NameFactory` | `IGenericName`, `ILocalName`, `IScopedName`, `IMemberName`, `ITypeName`, `INameSpace`, `INameFactory` (`Head`/`Tip`; `aName` -> `ToString()`) |
| `InternationalString` | `IInternationalString` with `ToString(CultureInfo)` |
| `CodeList` | `CodeList<T>` with static `ValueOf(string)`; `TODO(SWG)` Q5 (registry) |
| `ControlledVocabulary` | `IControlledVocabulary`; `TODO(SWG)` Q1 (enums) |
| `Factory` | `IFactory` |
| `FactoryException`, `NoSuchIdentifierException`, `UnimplementedServiceException` | concrete exception classes |
| `Record`, `RecordSchema`, `RecordType`, `Type` | Deferred to Phase 2 (full metadata). Only referenced in Javadoc by `MemberName`/`TypeName`; used in code only by `metadata/content`, `distribution`, `quality`, `spatial`. The `Type` in `TypeName.toJavaType()` and `NameFactory.createTypeName(..., Type)` is `java.lang.reflect.Type` -> `System.Type`. |

`OpenGis.Util.Measure` (no Java source; Java uses JSR-385). Basis: CLAUDE.md §3 Units, DESIGN FR-5,
Python `opengis/util/measure.py`:

- `IUnitOfMeasure`: `Symbol`, `Name`, `Kind` (`UnitKind`), `SystemUnit`, `ToSystemUnit(double)`.
- `UnitKind`: length, angle, scale, time, other.
- `IMeasure`: `Value`, `Unit`.
- No arithmetic, parsing or dimension algebra.

## Phase 1: ISO 19111 referencing (dependency order)

### [ ] 1.1 `OpenGis.Metadata` subset

Only the members referencing needs.

| Java | C# |
| --- | --- |
| `metadata/citation/Citation` | `ICitation` |
| `metadata/Identifier` | `IIdentifier` |
| `metadata/extent/Extent` | `IExtent` (plus any extent element types it requires: confirm) |
| `metadata/quality/PositionalAccuracy` | `IPositionalAccuracy` |

### [ ] 1.2 `OpenGis.Referencing`

| Java (`referencing/`) | C# |
| --- | --- |
| `IdentifiedObject` | `IIdentifiedObject` (with `Domains`; `ObjectUsage` omitted) |
| `ReferenceSystem` | `IReferenceSystem : IIdentifiedObject` |
| `ObjectDomain` | `IObjectDomain` |
| `ReferenceSystemType` | `ReferenceSystemType` (code list or enum: per Java) |
| `AuthorityFactory`, `ObjectFactory` | `IAuthorityFactory`, `IObjectFactory` |
| `NoSuchAuthorityCodeException` | `NoSuchAuthorityCodeException` |
| `RegisterOperations` | `IRegisterOperations`; `TODO(SWG)` Q3 (discovery) |

### [ ] 1.3 `OpenGis.Referencing.Cs`

Java `referencing/cs/`: `CoordinateSystem`, `CoordinateSystemAxis`, `AffineCS`, `CartesianCS`,
`CylindricalCS`, `EllipsoidalCS`, `LinearCS`, `ParametricCS`, `PolarCS`, `SphericalCS`, `TimeCS`,
`VerticalCS`, `AxisDirection`, `RangeMeaning`, `CoordinateDataType`, `TimeUnit`, `CSFactory`,
`CSAuthorityFactory`.

- C# names: `ICoordinateSystem`, `ICoordinateSystemAxis`, `IAffineCs`, `ICartesianCs`, ..., `ICsFactory`, `ICsAuthorityFactory`.
- `AxisDirection.Opposite` kept.
- `TODO(SWG)` Q4: `GetAxis(int)` + `Dimension` vs `Axes` list.
- Confirm `TimeUnit` mapping (code list vs .NET type).

### [ ] 1.4 `OpenGis.Referencing.Datum`

Java `referencing/datum/`: `Datum`, `DatumEnsemble`, `DynamicReferenceFrame`, `Ellipsoid`,
`EngineeringDatum`, `GeodeticDatum`, `ParametricDatum`, `PrimeMeridian`, `TemporalDatum`,
`VerticalDatum`, `RealizationMethod`, `DatumFactory`, `DatumAuthorityFactory`.

- `IEllipsoid` exposes `SemiMinorAxis`, `InverseFlattening`, `IsIvfDefinitive`.
- `IGeodeticDatum.Ellipsoid` non-nullable with documented null case.
- Flag: Java `GeodeticDatum` `@UML` identifier `"GeodeticReferenceFram"` (missing "e").
- `TemporalDatum` origin type: `TODO(SWG)` Q2 if it uses `Temporal`/`Instant`.

### [ ] 1.5 `OpenGis.Referencing.Crs`

Java `referencing/crs/`: `CoordinateReferenceSystem`, `SingleCRS`, `CompoundCRS`, `CompoundCS`,
`DerivedCRS`, `EngineeringCRS`, `GeodeticCRS`, `GeographicCRS`, `ParametricCRS`, `ProjectedCRS`,
`TemporalCRS`, `VerticalCRS`, `CRSFactory`, `CRSAuthorityFactory`.

- `CoordinateSystem` on `ICoordinateReferenceSystem` (derived, no `[Uml]`); redeclared with `[Uml]` on `ISingleCrs`.
- Sub-interfaces specialize with `new` + explicit default bridge (CLAUDE.md §3 pattern).
- `conversion` -> `ConversionFromBase`.
- `CompoundCS` lives in Java `crs` package: confirm C# namespace (`Crs` as in Java, or `Cs`).

### [ ] 1.6 `OpenGis.Parameter`

Java `parameter/`: `GeneralParameterDescriptor`, `GeneralParameterValue`, `ParameterDescriptor`,
`ParameterDescriptorGroup`, `ParameterValue`, `ParameterValueGroup`, `ParameterDirection`, and
exceptions `InvalidParameterCardinalityException`, `InvalidParameterNameException`,
`InvalidParameterTypeException`, `InvalidParameterValueException`, `ParameterNotFoundException`.

- Unified parameter framework (`OperationParameter` -> `IParameterDescriptor`).
- Generic `ParameterDescriptor<T>` / `ParameterValue<T>` -> `IParameterDescriptor<T>` / `IParameterValue<T>`.

### [ ] 1.7 `OpenGis.Referencing.Operation` and `OpenGis.Geometry` (two types)

Java `referencing/operation/`: `CoordinateOperation`, `SingleOperation`, `ConcatenatedOperation`,
`Conversion`, `Transformation`, `PassThroughOperation`, `PointMotionOperation`, `OperationMethod`,
`Formula`, `MathTransform`, `MathTransform1D`, `MathTransform2D`, `Matrix`, `MathTransformFactory`,
`CoordinateOperationFactory`, `CoordinateOperationAuthorityFactory`, and exceptions
`TransformException`, `NoninvertibleTransformException`, `OperationNotFoundException`.

- `ICoordinateOperation.MathTransform` (separate from the operation).
- Array transforms: `ReadOnlySpan<double>` source, `Span<double>` target.
- `Inverse()`, `Transform(...)`, `ToWkt()` stay methods.

Java `geometry/`: `DirectPosition` -> `OpenGis.Geometry.IDirectPosition`, `Envelope` -> `IEnvelope`. Nothing else from geometry.

## SWG deliverables (only when requested)

- [ ] S.1 Agenda for the first Agora meeting (open questions Q1 to Q5 below).
- [ ] S.2 ISO 19111 member mapping (Java member -> UML id -> C# member), generated from Java `@UML`, as a Markdown table in `geoapi/src/main/dotnet/docs/`.

## Later phases (not scheduled)

- Phase 2: conformance suite in `geoapi-conformance/src/main/dotnet` (validators `CRSValidator`, `CSValidator`, `DatumValidator`, `OperationValidator`, `ParameterValidator`; then `ObjectFactoryTest`, `AuthorityFactoryTest`, `ParameterizedTransformTest`, `AffineTransformTest`, `WKTParserTest`); ProjNet adapter; full metadata (ISO 19115-1/-2, 19157).
- Phase 3: `OpenGis.Geometry`, `.Feature`, `.Filter`, `.Temporal`; .NET profile annex; NuGet release.

## Deferred / blocked (needs a decision)

- [-] `Microsoft.CodeAnalysis.PublicApiAnalyzers` (DESIGN NFR): conflicts with "no dependencies beyond the BCL".
- [-] GitHub Actions workflow (DESIGN NFR): `.github` must not be modified without approval.
- [-] NuGet packaging, SourceLink, symbols, versioning `4.0.0-preview.N` (DESIGN NFR): publication not decided.
- [x] User README (`geoapi/src/main/dotnet/README.md`): update its Status table as each step lands.
- [ ] DocFX API reference site (DESIGN NFR): agreed to add with 0.3, as a local dotnet tool (no library dependency);
  one overview page per namespace written with each step. Publishing on geoapi.org is the maintainer's decision.
- [-] Specification `.NET` column and `profiles/dotnet` annex: `src/main/metanorma` on hold.
- [-] D.1 Refresh DESIGN.md naming to `OpenGis` conventions (optional; your call).

## Open questions (do not decide)

| # | Question | Affects |
| --- | --- | --- |
| Q1 | Enumerations vs `IControlledVocabulary` (C# enums can't implement interfaces) | 0.2, 0.3, every enum |
| Q2 | .NET types for `PositionInTime`, `Time`, `Instant` | 1.4 (`TemporalDatum`), 1.5 (`TemporalCrs`), 1.2 (`IObjectDomain`?) |
| Q3 | Implementation discovery for `IRegisterOperations` | 1.2 |
| Q4 | `GetAxis(int)` + `Dimension` vs `Axes` list | 1.3 |
| Q5 | Whether the `CodeList<T>` registry lives in the base class | 0.3 |

Also open from DESIGN.md: NuGet under OGC account; where adapters live; `netstandard2.1` support.
Closed: attribute name is `[Uml]` (`UmlAttribute`).

## DESIGN.md vs CLAUDE.md differences (CLAUDE.md applies)

| DESIGN.md | CLAUDE.md |
| --- | --- |
| Root `OpenGIS`; `UMLAttribute`/`[UML]`; `ISingleCRS`; `Specification.ISO_19111` | `OpenGis`; `UmlAttribute`/`[Uml]`; `ISingleCrs`; `Specification.Iso19111` |
| Annotation has no `ClassifierAttribute` | `ClassifierAttribute` and `Obligation.Forbidden` included |
| Metadata subset: `ICitation`, `IIdentifier`, `IExtent` | adds `IPositionalAccuracy` |
| `Time` -> `TimeOnly` | open question Q2 |
| Collections -> `IReadOnlySet`/`IReadOnlyList` | `IReadOnlyCollection<T>` by default; list/set only when ordered/unique |
| Attribute name is an open question | decided: `[Uml]` |
| PublicApiAnalyzers, CI, NuGet, SourceLink, DocFX | no extra dependencies, no `.github` edits, no packaging config |
| Geometry entirely in Phase 3 | `IDirectPosition`, `IEnvelope` added in 1.7 |
