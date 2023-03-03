---
layout: default
permalink: "/archives/2.0/index.html"
title: "GeoAPI 2.0 (archived)"
---

# GeoAPI 2.0 (archived)

<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 my-4 rounded relative" role="alert">
  <strong class="font-bold">Note:</strong>
  <span class="block sm:inline">GeoAPI 2.0 is a legacy release. See home page for latest release.</span>
</div>

Release 2.0 of GeoAPI contained the interfaces developed by the GeoAPI project and accepted
by the Open Geospatial Consortium with the final draft of the implementation specification
_GO-1 Application Objects_
and therefore considered by the GeoAPI project as one of its official releases.
This release came in two flavors: `geoapi.jar` required a Java environment J2SE 1.4 or higher
while `geoapi-tiger.jar` requires a Java environment Java 5 or higher.

* [Defining specification](https://portal.ogc.org/files/?artifact%20id=10378) (retired)
* [Download from SourceForge](https://sourceforge.net/projects/geoapi/files/GeoAPI-2_0_0.zip/download)

## Changes since GeoAPI 1.0

The following list of changes is non-exhaustive.

### Changes in Metadata packages

* Completed interfaces and code list derived from ISO 19115.
* Replaced all `Set` references by `Collection`, except in the very few places where ISO 19115 defines explicitly the collection type.
* Replaced all `URL` references by `URI`.

### Changes in Referencing packages

* Added `SingleCRS` interface. This change reflect a part of ISO 19111 which was omitted in previous milestone.
* Renamed `ObliqueCartesianCS` as `AffineCS`. This reflect a change in OGC Topic 2 voted in July 16, 2004.
* Renamed `TemporalCS` as `TimeCS`. This reflect a change in OGC Topic 2 voted in July 16, 2004.
* Renamed `Info` as `IdentifiedObject`. This reflect a change in OGC Topic 2 voted in July 16, 2004.
* In `AffineCS`. This change is required for type safety and was forgotten in previous milestone.
* In `UserDefinedCS` creation. This part was forgotten in previous milestone.
* Added `toWKT()` method in `IdentifiedObject`.
* Renamed `NoSuchClassificationException` as `NoSuchIdentifierException`.
* Replaced all arrays by collections as the return type of the following methods: `IdentifiedObject.getIdentifiers()`, `IdentifiedObject.getAlias()` `CompoundCRS.getCoordinateReferenceSystems()`, `ConcatenatedOperation.getOperations()`, `CoordinateOperation.getPositionalAccuracy()`.
* Removed the `org.opengis.referencing.quality` package. This package is now replaced by `org.opengis.metadata.quality`. This reflect a change in OGC Topic 2 voted in July 16, 2004.
* Renamed all `OperationParameter` interfaces as `ParameterDescriptor`. This is because many users expect a parameter to contains a value. The `ParameterDescriptor` name make it clearer that this interface contains metadata about parameter, not the actual parameter value. It also consistent with usage in other Java libraries like <i>cite</i>Java Advanced Imaging<i>/cite</i>.
* In `OperationMethod`, changed the `GeneralParameterDescriptor[]` return type to `ParameterDescriptorGroup`. Generally speaking, this change was applied for most processing methods in GeoAPI returning parameters. This is a slight departure from specifications, which usually returns a sequence of parameter rather than a group.
* For convenience method `getParameter(String)` in `ParameterDescriptor`. This change make the convenience method more… convenient.
* For convenience method `getValue(String)` in `ParameterValue`. This change make the convenience method more… convenient. Also renamed the method as `parameter`, which avoid strange expression like `getValue(&quot;some_name&quot;).setValue(someValue)`. The idiom is now `parameter(&quot;some_name&quot;).setValue(someValue)` instead.
* For all methods expecting a `Locale` argument, removed this argument and replaced the `String` return type by `InternationalString`. This change was done for consistency with the metadata package.
* In `valueFile()` method, replaced the `URL` return type by `URI`.

### Changes in Geometry packages

* Set `Point` interfaces instead of an union of them. The union type do no exists in Java and this change lead to a more object oriented approach. This change was done after a comment on the GeoAPI mailing list.
* In `Envelope` interface, added convenience methods `getMinimum`, `getMaximum`, `getCenter` and `getLength`.
* In `GenericCurve`, renamed the `getParam` and `getConstructiveParam` method as `forParam` and `forConstructiveParam` respectively. The former names was misleading since those methods do not return a constructive parameter; they expect it as argument instead.
* In `Complex`, renamed the `getSuperComplexex` method as `getSuperComplexes`. The former was a typo.
* Addition of more geometry interfaces from ISO 19107. The work is not finished however.

### Changes in Grid Coverage packages

* In `Coverage` interface (method `getDimensionNames`), replaced the `String` returns type by `InternationalString`. This change is required for localization support and was forgotten in previous milestone.
* In `Coverage` interface, changed the return type from `double[]` to `float[]` for the method `evaluate(DirectPosition, float[])`. Previous return type was wrong.
* In `Coverage`, merged `getNumSources()` and `getSource(int)` methods into a single `getSources()` method.
* In `GridRange`, renamed `getLower()` and `getUpper()` as `getLowers()` and `getUppers()`.
* In `GridRange`, added `getLower(int)`, `getUpper(int)`, `getLength(int)` and `getDimension` convenience methods.
* In `Operation`, changed the `GeneralParameterValue[]`, return type to `ParameterValueGroup`. Generally speaking, this change was applied for most processing methods in GeoAPI returning parameters. This is a slight departure from specifications, which usually returns a sequence of parameter values.
* In `GridAnalysis`, renamed the `analyse` method as `analyze`. The former was a typo.
