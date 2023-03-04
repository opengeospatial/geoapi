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
by the Open Geospatial Consortium with the final version of the implementation specification
_GO-1 Application Objects_
and therefore considered by the GeoAPI project as one of its official releases.
This release came in two flavors: `geoapi.jar` required a Java environment J2SE 1.4 or higher
while `geoapi-tiger.jar` requires a Java environment Java 5 or higher.

* [Defining specification](https://portal.ogc.org/files/?artifact%20id=10378) (retired)
* [Download from SourceForge](https://sourceforge.net/projects/geoapi/files/GeoAPI-2_0_0.zip/download)

## Changes since GeoAPI 1.0

The conceptual model defining Java interfaces in the `org.opengis.referencing` packages
has been replaced by the ISO 19111 abstract model.
New packages have been added for metadata, geometry, coverage, feature, filter, style and display services.
