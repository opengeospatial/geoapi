---
layout: default
permalink: "/java/examples/implement.html"
title: "GeoAPI examples in Java"
---

# Implementation examples in Java

The `geoapi-example` module provides examples of very simple GeoAPI implementations.
Those implementations are not complete, but can be used as starting points for implementers.
Since every files in this module are placed into the Public Domain,
anyone is free to do whatever they wish with those files.

* [Browse the javadoc](../../snapshot/javadoc/org.opengis.geoapi.example/module-summary.html)
* [Source code on GitHub](https://github.com/opengeospatial/geoapi/tree/master/geoapi-examples/src/main/java/org/opengis/example)

## Metadata

The `org.opengis.metadata` package and sub-packages contain a large amount of interfaces,
which may give the impression that implementing metadata is very tedious.
However the `java.lang.reflect.Proxy` class can provide dynamically generated implementations.
The metadata example in this module shows how to implement every metadata interfaces with only two small classes.

## Referencing

The ISO 19111 model contains lot of details, some of them being ignored by existing libraries.
For example some libraries make no distinction between:

* _Ellipsoid_ and _Geodetic Datum_
* _Coordinate System_ and _Coordinate Reference System_
* _Coordinate Operation_ and _Math Transform_

This `geoapi-examples` module follows the path of simpler libraries
by merging different ISO concepts in the same class.
While this is not a recommended approach for strict ISO 19111 compliance,
the intent is to demonstrate that implementers can hide some of the ISO 19111 complexity.
