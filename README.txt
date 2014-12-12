This is the root directory for the GeoAPI project.
The GeoAPI project contains the following modules:

Module             | Purpose
------------------ | ---------------------------------------------------------
geoapi             | Contains the GeoAPI normative interfaces.
geoapi-pending     | Contains experimental interfaces not yet targeted for a release.
geoapi-examples    | Standalone examples used as part of the guidelines for turning a specification into Java interfaces.
geoapi-proj4       | A GeoAPI implementation example as a wrapper around the Proj.4 library.
geoapi-netcdf      | A GeoAPI implementation example as a wrapper around the UCAR NetCDF library.
geoapi-conformance | A test suite for testing vendor implementations.
tools              | Used for generating some special pages for javadoc.

In order to get early feedback from implementors we will occasionally issue a milestone release.
For details please visit the http://www.geoapi.org project website.


# How to build

* Download and install J2SE 1.6 or above (http://java.sun.com).
* Download and install Maven 3 (http://maven.apache.org).
* Invoke `mvn install` from the command line in this directory.

A jar file will be created in the `geoapi/target/` directory.
