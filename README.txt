This is the root directory for the GeoAPI project.


---------------------------------------------------------------------------------------------------
Modules
---------------------------------------------------------------------------------------------------

  geoapi              Contains the GeoAPI release. In order to get early feedback from implementors
                      we will occasionally issue a milestone release.  For details please visit the
                      http://www.geoapi.org/ project website.

  geoapi-pending      Contains work in progress.

  geoapi-examples     Examples used as part of the guidelines for turning a specification into Java
                      interfaces. The same example is shown for a range of tradeoffs from a simple
                      immutable interface, through to data objects and finally objects with event
                      notification.

  geoapi-conformance  A test suite for testing vendor implementations.

  tools               Used for generating some special pages for javadoc.


---------------------------------------------------------------------------------------------------
How to build
---------------------------------------------------------------------------------------------------

* Download and install j2SE 1.5 or above (http://java.sun.com).

* Download and install Maven 2 (http://maven.apache.org).

* Invoke "mvn install" from the command line in this directory.

A jar for will be created in the geoapi/target/ directory.
