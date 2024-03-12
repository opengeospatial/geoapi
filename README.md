# GeoAPI interfaces for OGC/ISO conceptual models

GeoAPI provides a set of Java and Python language programming interfaces for geospatial applications.
The interfaces developed by the GeoAPI project include data structures and manipulation methods needed
for geographic information system applications. The GeoAPI interfaces closely follow the abstract models
and concrete specifications published collaboratively by the International Organization for Standardization (ISO)
in its 19100 series of documents and the Open Geospatial Consortium (OGC) in its abstract and implementation standards.

The current release is GeoAPI 3.0.2, published as an
[Open Geospatial Consortium standard](http://www.opengeospatial.org/standards/geoapi/) and
[available on Maven Central](http://search.maven.org/#artifactdetails%7Corg.opengis%7Cgeoapi%7C3.0.2%7Cbundle).
Development branches on GitHub are "3.1" for GeoAPI 3.1 (backward compatible with GeoAPI 3.0)
and "master" for GeoAPI 4.0. The main sub-directories are:

* **[geoapi](https://github.com/opengeospatial/geoapi/tree/master/geoapi)**
  contains the normative interfaces proposed for next GeoAPI releases.
* **[geoapi-pending](https://github.com/opengeospatial/geoapi/tree/master/geoapi-pending)**
  contains experimental interfaces not yet targeted for a release.
* **[geoapi-conformance](https://github.com/opengeospatial/geoapi/tree/master/geoapi-conformance)**
  contains a test suite for testing vendor implementations.
* **[src/main/metanorma](https://github.com/opengeospatial/geoapi/tree/master/src/main/metanorma)**
  contains the GeoAPI specification.
* Other directories are build tools or examples.

See the [wiki page](https://github.com/opengeospatial/geoapi/wiki) for the roadmap and completion status.


## Development snapshots

GeoAPI development branches require Java 18 and [Maven 3](http://maven.apache.org) for building,
but the compilation result can be run on Java 11.
The GeoAPI 3.0 stable release requires only Java 8.

* **[OGC specification](https://opengeospatial.github.io/ogcna-auto-review/23-016.html)**
  is built in HTML format with `mvn pre-site -N`.\
  The output file will be created in the `target/site/` directory.
* **[Java interfaces](http://www.geoapi.org/snapshot/javadoc/index.html)**
  are built as a Java Archive File (JAR) with `mvn install`.\
  The output file will be created in the `geoapi/target/` directory.
* **[Python interfaces](http://www.geoapi.org/snapshot/python/index.html)**
  are located in the `geoapi/src/main/python` sub-directory
  and do not need to be built.
