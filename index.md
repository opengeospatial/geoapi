---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: default
---

# OGC® GeoAPI

GeoAPI provides a set programming interfaces for geospatial applications.
In a series of packages or modules, GeoAPI 3.0 defines interfaces for metadata handling
and for geodetic referencing (map projections).
The GeoAPI interfaces closely follow the abstract models published collaboratively by the
[International Organization for Standardization](https://www.isotc211.org/) (ISO) in its 19100 series of documents
and the [Open Geospatial Consortium](https://www.ogc.org/) (OGC) in its abstract and implementation specifications.
GeoAPI provides an interpretation and adaptation of these standards
to match the expectations of Java or Python programmers.
These standards provide GeoAPI with the richness which comes from the expertise of the specification writers.
Clients benefit from the potential for inter-operability which comes from using a well defined, standardized data model.
Implementers benefit from having a pre-defined set of well considered,
formal boundaries to modularize their development work.

The GeoAPI interfaces provide a layer which separates client code, which call the API,
from library code, which implements the API.
This follows a similar pattern to the well known
<abbr title="Java Database Connectivity">JDBC</abbr> or
<abbr title="Open Database Connectivity">ODBC</abbr> API
which provides a standardized interface to databases.
Clients can use the API without concern for the particular implementation which they will use.

## Usage Examples

In order to use GeoAPI, developers must select a third-party implementation.
Examples about how to use GeoAPI with some third-party libraries are available
in [Java](java/examples/index.html) and [Python](python/examples/index.html).
There is also some more examples about how to [implement and test](java/examples/index.html)
a new GeoAPI implementation.
