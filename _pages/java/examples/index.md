---
layout: default
permalink: "/java/examples/index.html"
title: "GeoAPI examples in Java"
---

# GeoAPI examples in Java

There is 3 categories of examples, depending on target audience:

* [Using GeoAPI](usage.html) with third-party implementations.
  Users must select an implementation before to execute code.
  Some implementations are listed for making easier to run the examples,
  but that list does not aim to be exhaustive and is not an OGC endorsement of those implementations.
* [Implementing GeoAPI](implement.html) with simple classes in the public domain,
  which implementers can use as a starting point for their own products.
* [Testing an implementation](testing.html) with the [conformance tests](../../conformance/index.html).


## Libraries with public source code

The following projects provide GeoAPI implementations,
either directly or as wrappers around independent libraries:

* [NetCDF wrapper](https://github.com/Unidata/geoapi-netcdf-java) in Java for the UCAR netCDF library.
* [Proj.6 wrapper](https://github.com/osgeo/PROJ-JNI) in Java for version 6 or later of the PROJ library.
* [GDAL wrapper](https://github.com/Geomatys/geoapi-gdal) using Java and Python bindings to the GDAL library.
* [Apache SIS](https://sis.apache.org) in Java, in particular the
  [metadata](https://github.com/apache/sis/tree/main/endorsed/src/org.apache.sis.metadata/main/org/apache/sis/metadata/iso) and
  [referencing](https://github.com/apache/sis/tree/main/endorsed/src/org.apache.sis.referencing/main/org/apache/sis/referencing) packages.
