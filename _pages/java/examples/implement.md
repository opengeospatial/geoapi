---
layout: default
permalink: "/java/examples/implement.html"
title: "GeoAPI examples in Java"
---

<h1>Implementation examples in Java</h1>

<p>
  The <code>geoapi-example</code> module provides examples of very simple GeoAPI implementations.
  Those implementations are not complete, but can be used as starting points for implementers.
  Since every files in this module are placed into the Public Domain,
  anyone is free to do whatever they wish with those files.
</p>

<ul>
  <li><a href="doc/index.html">Browse the javadoc</a></li>
  <li><a href="https://github.com/opengeospatial/geoapi/tree/master/geoapi-examples/src/main/java/org/opengis/example"
         class="externalLink">Source code on GitHub</a></li>
</ul>

<h2>Metadata</h2>

<p>
  The <code>org.opengis.metadata</code> package and sub-packages contain a large amount of interfaces,
  which may give the impression that implementing metadata is very tedious.
  However the <code>java.lang.reflect.Proxy</code> class can provide dynamically generated implementations.
  The metadata example in this module shows how to implement every metadata interfaces with only two small classes.
</p>

<h2>Referencing</h2>

<p>
  The ISO 19111 model contains lot of details, some of them being ignored by existing libraries.
  For example some libraries make no distinction between:
</p>

<ul>
  <li><i>Ellipsoid</i> and <i>Geodetic Datum</i></li>
  <li><i>Coordinate System</i> and <i>Coordinate Reference System</i></li>
  <li><i>Coordinate Operation</i> and <i>Math Transform</i></li>
</ul>

<p>
  This <code>geoapi-examples</code> module follows the path of simpler libraries
  by merging different ISO concepts in the same class.
  While this is not a recommended approach for strict ISO 19111 compliance,
  the intent is to demonstrate that implementers can hide some of the ISO 19111 complexity.
</p>
