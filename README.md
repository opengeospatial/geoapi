This is the root directory for the GeoAPI project.
The main modules are:

Module              | Purpose
------------------- | ---------------------------------------------------------
geoapi              | The GeoAPI normative interfaces.
geoapi-conformance  | A test suite for testing vendor implementations.
geoapi-examples     | Standalone examples used as part of the guidelines for turning a specification into Java interfaces.
geoapi-proj4/netcdf | GeoAPI implementation examples as wrappers around the Proj.4 and UCAR NetCDF libraries.
geoapi-pending      | Contains experimental interfaces not yet targeted for a release.

In order to get early feedback from implementors we will occasionally issue a milestone release.
For details please visit the http://www.geoapi.org project website.


# How to build

* Download and install Java 7 or above (http://download.oracle.com/javase/7).
* Download and install Maven 3 (http://maven.apache.org).
* Invoke `mvn install` from the command line in this directory.

A jar file will be created in the `geoapi/target/` directory.
