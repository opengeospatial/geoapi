---
layout: default
permalink: "/archives/2.3/index.html"
title: GeoAPI 2.3 (archived)
---

# GeoAPI 2.3 (archived)

<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 my-4 rounded relative" role="alert">
  <strong class="font-bold">Note:</strong>
  <span class="block sm:inline">GeoAPI 2.3 is a legacy milestone. See home page for latest release.</span>
</div>

GeoAPI 2.3 was the development branch of [GeoAPI 3.0](../../3.0/index.html).
This milestone required a Java environment Java 5 or higher.

## Changes in API

The following table gives the links to all changes in the 2.3 series of milestones.
The cell in the upper-left corner links to the most recent API changes.
The last column links to the API changes in any milestone since 2.3-M1.
The changes since 2.3-M1 give the cleanest picture of what actually changed,
because the changes since the previous version are often &quot;polluted&quot;
by the methods that are deprecated before to be removed.

| Change since             | &nbsp;&nbsp;M5&nbsp;&nbsp;    | &nbsp;&nbsp;M4&nbsp;&nbsp;    | &nbsp;&nbsp;M3&nbsp;&nbsp;    | &nbsp;&nbsp;M2&nbsp;&nbsp;    | &nbsp;&nbsp;M1&nbsp;&nbsp;    |
| ------------------------ |:-----------------------------:|:-----------------------------:|:-----------------------------:|:-----------------------------:|:-----------------------------:|
| Changes in **M6** since: | [M5](../2.3-M6/since-M5.html) | [M4](../2.3-M6/since-M4.html) | [M3](../2.3-M6/since-M3.html) | [M2](../2.3-M6/since-M2.html) | [M1](../2.3-M6/since-M1.html) |
| Changes in **M5** since: |                               | [M4](../2.3-M5/since-M4.html) | [M3](../2.3-M5/since-M3.html) | [M2](../2.3-M5/since-M2.html) | [M1](../2.3-M5/since-M1.html) |
| Changes in **M4** since: |                               |                               | [M3](../2.3-M4/since-M3.html) | [M2](../2.3-M4/since-M2.html) | [M1](../2.3-M4/since-M1.html) |
| Changes in **M3** since: |                               |                               |                               | [M2](../2.3-M3/since-M2.html) | [M1](../2.3-M3/since-M1.html) |
| Changes in **M2** since: |                               |                               |                               |                               | [M1](../2.3-M2/since-M1.html) |


## Resolved issues

* Milestone 2.3-M9
  * [GEO-184](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-184) — Move Factory and related exception classes to org.opengis.util
* Milestone 2.3-M8
  * Minor compatible editions only.
* Milestone 2.3-M7
  * [GEO-165](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-165) — Upgrade the JSR-275 dependency. There is no change in GeoAPI interfaces.
* Milestone 2.3-M6
  * [GEO-187](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-187) — Typo in metadata method names or annotations. This is completing the fix provided in 2.3-M5 by fixing the case of letters in class names.
* Milestone 2.3-M5
  * [GEO-187](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-187) — Typo in metadata method names or annotations
  * [GEO-072](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-72)  — Proposal for RecordType/Record implementation
  * [GEO-183](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-183) — Need to simplify the distribution (geoapi / geoapi-pending / geoapi-dummy-pending)
* Milestone 2.3-M4
  * [GEO-168](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-168) — Proposal implementation of the ISO 19115-2
  * [GEO-174](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-174) — New ISO 19111:2007 interface: CC_Formula
  * [GEO-182](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-182) — OperationMethod.[source|target]Dimensions now optional in latest ISO 19111
* Milestone 2.3-M3
  * [GEO-175](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-175) — Remove VerticalDatumType.ELLIPSOIDAL
  * [GEO-133](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-133) — ISO 19111 departure in VerticalDatumType.ELLIPSOIDAL
  * [GEO-167](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-167) — Move to plurial some getters that returns a collection
  * [GEO-085](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-85)  — Bearing has angle with no units.
  * [GEO-156](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-156) — NPE in org.opengis.feature.IllegalAttributeException.toString()
  * [GEO-16](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-169)9 — Consider making CodeList.valueOf(String) to ignore whitespaces
* Milestone 2.3-M2
  * [GEO-162](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-162) — Util: Drop the Clonable interface since it's almost not used.
  * [GEO-163](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-163) — Annotation: Drop the Profile annotation and Compliance enum
  * [GEO-166](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-166) — Specifying the condition when an attribute is annotated with UML(obligation=CONDITIONAL)
  * [GEO-171](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-171) — Move some interfaces from &quot;geoapi&quot; to &quot;geoapi-pending&quot;
  * [GEO-158](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-158) — PropertyIsLike option to ignore case
  * [GEO-161](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-161) — ISO 19111 departure in CompoundCRS
  * [GEO-178](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-178) — IncompatibleOperationException is a checked exception, but not used anywhere
  * [GEO-170](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-170) — NameFactory.createNameSpace(...) need to be more extensible
  * [GEO-176](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-176) — Javadocs for all CodeLists need to distinguish family() and values()
  * [GEO-173](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-173) — Drop org.opengis.referencing.operation.Operation
  * [GEO-177](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-177) — Add CoordinateOperationAuthorityFactory.createOperationMethod(String);
* Milestone 2.3-M1
  * [GEO-071](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-71)  — Incorrect implementation of org.opengis.util.GenericName
  * [GEO-077](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-77)  — Standardized mapping: ISO 19103 to Java/GeoAPI
  * [GEO-172](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-172) — New ISO 19111:2007 interface: CS_GeodeticCS
  * [GEO-054](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-54)  — Add setter methods to metadata interfaces
  * [GEO-083](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-83)  — Metadata is missing the &quot;application&quot; package
  * [GEO-145](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-145) — Make GeneralName easier to understand
  * [GEO-009](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-9)   — Create a link toward JIRA from the GeoAPI main page
  * [GEO-042](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-42)  — Publish a table of stable modules and modules in progress
  * [GEO-074](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-74)  — Consider removing org.opengis.display.go.style package (to be replaced by org.opengis.sld).
  * [GEO-132](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-132) — Document better the departure from OGC/ISO specifications
  * [GEO-140](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-140) — Add a @pending javadoc annotation to any experimental interface
  * [GEO-157](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-157) — Split geoapi in two modules: &quot;geoapi&quot; and &quot;pending&quot;
  * [GEO-160](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-160) — Remove packages GO, Layer and clean deprecated sld packages classes
  * [GEO-164](https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-164) — Create a specification draft for June 2009 OGC meeting (Boston)
