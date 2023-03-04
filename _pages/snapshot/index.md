---
layout: default
permalink: "/snapshot/index.html"
title: "GeoAPI development"
---

# GeoAPI developments

GeoAPI 3.1-SNAPSHOT and 4.0-SNAPSHOT are unreleased versions currently in development.
Developers working on projects who need long term stability should use the released [3.0.2](../3.0/index.html)
version of the API rather than the snapshots, formalized with the
[OGC 09-083](https://www.ogc.org/standard/geoapi/) specification document.
The rest of this page describes how the source code repository is organized
and provides links to snapshots of API documentation.

GeoAPI is developed in two branches hosted on [GitHub repository](https://github.com/opengeospatial/geoapi):

* The _master_ branch contains GeoAPI version 4.0-SNAPSHOT.
  This version has some incompatible changes compared to GeoAPI 3.0.2 release.
  Incompatibilities appear in part because of changes in international standards,
  and sometime because of programming language evolutions.
* The _3.1.x_ branch contains GeoAPI version 3.1-SNAPSHOT.
  This version is a copy of 4.0-SNAPSHOT modified for preserving backward compatibility with GeoAPI 3.0.2 release,
  sometime at the sacrifice of new functionalities.
  The incompatible changes are replaced by warnings saying that API may change in next GeoAPI version.

Furthermore each branch contains the following directories:

* The _geoapi_ directory contains everything targeted for GeoAPI 3.1 and 4.0 releases.
* The _geoapi-pending_ directory provides experimental interfaces not yet scheduled for any release.
  Interfaces that become mature enough for submission as OGC standard migrate from _geoapi-pending_ to _geoapi_ directory.


## References

All documents listed below are snapshots and may change at any time before release.

* [Specification draft](https://opengeospatial.github.io/ogcna-auto-review/23-016.html)
* [Issues tracker on GitHub](https://github.com/opengeospatial/geoapi/issues)
* **Java**
  * [Java ↔︎ OGC/ISO mapping](javadoc/content.html)
  * [Java API for GeoAPI 3.1-SNAPSHOT](javadoc/index.html)
  * [Changes compared to GeoAPI 3.0.2](../archives/snapshot/change-summary.html)
  * [Changes compared to older milestones](../archives/index.html)
* **Python**
  * [Python API for GeoAPI 4.0-SNAPSHOT](python/index.html)
  * [Source files on GitHub](https://github.com/opengeospatial/geoapi/tree/master/geoapi/src/main/python/opengis)
* **Bridges between languages**
  * [GeoAPI in Java ↔︎ GeoAPI in Python](../java-python/index.html)


## Working group

A [GeoAPI Standard Working Group](https://portal.ogc.org/?m=projects&a=view&project_id=294)
has formed within the OGC to formalize GeoAPI as a published OGC standard.
But most of the work is done publicly on the GitHub repository.
GeoAPI has a [wiki restricted to OGC members](https://portal.opengeospatial.org/twiki/bin/view/Member/GeoAPI),
but it is used mostly for OGC-specific instructions about deployment to Maven Central.
The [public wiki on GitHub](https://github.com/opengeospatial/geoapi/wiki) is used for GeoAPI roadmap.
Specification draft is [available online](https://opengeospatial.github.io/ogcna-auto-review/23-016.html).
