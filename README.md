GeoAPI provides a set of Java and Python language programming interfaces for geospatial applications.
The interfaces developed by the GeoAPI project include data structures and manipulation methods needed
for geographic information system applications. The GeoAPI interfaces closely follow the abstract model
and concrete specifications published collaboratively by the International Organization for Standardization (ISO)
in its 19100 series of documents and the Open Geospatial Consortium (OGC) in its abstract and implementation specifications.

The current release is GeoAPI 3.0.1, published as an
[Open Geospatial Consortium standard](http://www.opengeospatial.org/standards/geoapi/).
Development branches on GitHub are "3.1" for GeoAPI 3.1 (backward compatible with GeoAPI 3.0)
and "master" for GeoAPI 4.0. The main sub-directories are:

* **geoapi**              contains the normative interfaces proposed for next GeoAPI releases.
* **geoapi-pending**      contains experimental interfaces not yet targeted for a release.
* **geoapi-conformance**  contains a test suite for testing vendor implementations.
* **src/main/asciidoc**   contains the GeoAPI specification.
* Other directories are examples, including netCDF and Proj.4 bindings.


# How to build

Python interfaces are located in the `geoapi/src/main/python` sub-directory and do not need any build process.

Java interfaces requires [Maven 3](http://maven.apache.org) with Java 9 for building,
but the compilation result can be used with Java 7 or later.

* To build Java Archive File (JAR): `mvn package` or `mvn install`.
  The output file will be created in the `geoapi/target/` directory.

* To build the specification (HTML format): `mvn pre-site -N`.
  The output file will be created in the `target/site/` directory.
