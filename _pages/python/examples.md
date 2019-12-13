---
permalink: "/python/examples/index.html"
layout: default
title: "GeoAPI examples in Python"
---
<h1>GeoAPI examples in Python</h1>

<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 my-4 rounded relative" role="alert">
  <strong class="font-bold">Note:</strong>
  <span class="block sm:inline">this page describes a work in progress.</span>
</div>

<p>
  GeoAPI provides a set of Java and Python language programming interfaces for geospatial applications.
  Those interfaces closely follow the abstract models published collaboratively
  by <abbr title="International Organization for Standardization">ISO</abbr> and
  <abbr title="Open Geospatial Consortium">OGC</abbr>.
  GeoAPI does not provide implementations except bridges between programming languages;
  implementations must be obtained from other projects.
</p>

<p>
  Contrarily to Java where a standard mechanism exists for discovering implementations at run time,
  GeoAPI does not provide guidance at this time about how to discover Python implementations.
  The examples in this page assume that the first step is implementation-specific.
  After the first line of code, all properties are derived from <abbr>OGC</abbr>/<abbr>ISO</abbr> specifications.
</p>

<details class="code">
  <summary>Get metadata properties</summary>
<pre># Following line is implementation-specific.
md = my_implementation.getMetadata()

# Assuming that `md` type is <a href="../../snapshot/python/metadata.html#opengis.metadata.base.Metadata">opengis.metadata.base.Metadata</a>,
# all remaining lines below should be implementation-neutral.
# This <abbr>API</abbr> uses properties derived from <abbr>OGC</abbr>/<abbr>ISO</abbr> specifications.

axis0 = md.spatial_representation_info[0].axis_dimension_properties[0]
axis1 = md.spatial_representation_info[0].axis_dimension_properties[1]

print("Resource title:      ", md.identification_info[0].citation.title)
print("Resource scope:      ", md.metadata_scope[0].resource_scope)
print("Name of first axis:  ", axis0.dimension_name)
print("Size of first axis:  ", axis0.dimension_size)
print("Name of second axis: ", axis1.dimension_name)
print("Size of second axis: ", axis1.dimension_size)</pre>
</details>
