---
layout: default
permalink: "/java/index.html"
title: "GeoAPI for Java"
---

# Java API

Java interfaces are provided in [release 3.0.2](../3.0/index.html) of GeoAPI,
which can be downloaded from
[Maven Central Repository](https://central.sonatype.com/search?q=geoapi&namespace=org.opengis)
with the following coordinates:

```xml
<dependencies>
  <dependency>
    <groupId>org.opengis</groupId>
    <artifactId>geoapi</artifactId>
    <version>3.0.2</version>
  </dependency>
</dependencies>
```

The [Javadoc](../3.0/javadoc/index.html) is available online.

The [usage examples](examples/index.html) provide starting points
for applying coordinate operations (e.g. map projections) and
for fetching some metadata.

## Known implementations

* [Apache Spatial Information System (SIS)](https://sis.apache.org).
* [GeoTools wrappers](https://github.com/Geomatys/geoapi-gt-wrappers) for the GeoTools Java library.
* [PROJ4J](https://github.com/locationtech/proj4j) version 1.4 or later in the `proj4j-geoapi` module.
* [PROJ wrappers](https://github.com/osgeo/PROJ-JNI) in Java for version 6 or later of the PROJ C/C++ library.
* [NetCDF wrappers](https://github.com/Unidata/geoapi-netcdf-java) for the Java version of the UCAR netCDF library.

## Future work

Upgrade to latest standard versions,
Python interfaces and bridge between Java and Python are in progress.
This work is described in [GeoAPI developments](../snapshot/index.html) page.
