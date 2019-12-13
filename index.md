---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: default
---

<div class="font-sans">
    <h2 class="text-2xl font-bold">OGC&reg; GeoAPI</h2>
</div>

<p>GeoAPI provides a set programming interfaces for geospatial applications. In a series of packages or modules,
GeoAPI 3.0 defines interfaces for metadata handling and for geodetic referencing (map projections).
The GeoAPI interfaces closely follow the abstract models published collaboratively by the
<a class="externalLink" href="https://www.isotc211.org/">International Organization for Standardization</a> (ISO) in its 19100
series of documents and the <a class="externalLink" href="https://www.opengeospatial.org/">Open Geospatial Consortium</a> (OGC) in
its abstract and implementation specifications. GeoAPI provides an interpretation and adaptation of these
standards to match the expectations of Java or Python programmers. These standards provide GeoAPI with
the richness which comes from the expertise of the specification writers. Clients benefit from the potential
for inter-operability which comes from using a well defined, standardized data model. Implementers benefit
from having a pre-defined set of well considered, formal boundaries to modularize their development work.</p>

<p class="text-justify tracking-normal subpixel-antialiased my-6">The GeoAPI interfaces provide a layer which separates
client code, which call the API, from library code, which implements the API. This follows a similar pattern to 
the well known <abbr title="Java Database Connectivity">JDBC</abbr> or
<abbr title="Open Database Connectivity">ODBC</abbr> API which provides a standardized interface to databases.
Clients can use the API without concern for the particular implementation which they will use.</p>

<div class="font-sans">
    <h2 class="text-2xl font-bold">Usage Examples</h2>
</div>

<p>In order to use GeoAPI, developers must select a third-party implementation. Examples about how to use
GeoAPI with some third-party libraries are available in <a href="{{ '/java/examples/usage.html' | relative_url }}">Java</a> and
<a href="{{ '/python/examples/index.html' | relative_url }}">Python</a>. There is also some more examples about how to
<a href="{{ '/java/examples/index.html' | relative_url }}">implement and test</a> a new GeoAPI implementation.</p>
