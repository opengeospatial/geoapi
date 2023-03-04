---
layout: default
permalink: "/3.0/index.html"
title: "GeoAPI 3.0.2"
---

# GeoAPI 3.0.2

Release 3.0 of GeoAPI contains the interfaces developed by the GeoAPI project and accepted
by the Open Geospatial Consortium with the final draft of the engineering specification
_GeoAPI specification_ and therefore considered by the GeoAPI project as one of its official releases.
This release requires a Java environment Java 8 or higher.

* [Defining specification from OGC web site](https://www.ogc.org/standard/geoapi/)
* [Table of content](javadoc/content.html)
* [Browse javadoc](javadoc/index.html)
* [Usage examples](../java/examples/index.html)
* [Download from SourceForge](https://sourceforge.net/projects/geoapi/files/GeoAPI-3_0_2.zip/download)

## Download as Maven dependency

GeoAPI 3.0 is deployed on the [Maven Central Repository](https://central.sonatype.com/search?q=geoapi&namespace=org.opengis)
and can be used in a Maven project with the following declaration in the `pom.xml` file:

```xml
<dependencies>
  <dependency>
    <groupId>org.opengis</groupId>
    <artifactId>geoapi</artifactId>
    <version>3.0.2</version>
  </dependency>
</dependencies>
```

## Changes since GeoAPI 2._x_

This release contains the same API as the latest GeoAPI 2.3 milestone except for the
JSR-275 dependency (deprecated) which is replaced by JSR-385 (the Java standard API for units of measurements).
For a list of changes compared to previous versions,
see the [changes in the 2.3 series of milestones](../archives/2.3/index.html).
