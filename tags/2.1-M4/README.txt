This is the root directory for the GeoAPI project.


---------------------------------------------------------------------------------------------------
Modules
---------------------------------------------------------------------------------------------------

  geoapi     contains the next GeoAPI release being put forward for approval. In order to get early
             feedback from implementors we will occasionally issue a milestone release. For details
             please visit the project website.

  nogeneric  contains a build target for a Java 1.4 distribution of geoapi suitable for use with
             J2EE. This target will producea jar that lacks generics and typenarrowing.

  erasure    Used to downgrade java 5 code to Java 1.4.

  tools      Used for generating some special pages for javadoc.



---------------------------------------------------------------------------------------------------
How to build
---------------------------------------------------------------------------------------------------

* Download and install j2SE 1.5 or above (http://java.sun.com).

* Download and install Maven 2 (http://maven.apache.org).

* Invoke "mvn install" from the command line in this directory.

A jar for J2SE 1.5 should appears in geoapi/target/.
A jar for J2SE 1.4 should appears in nogenerics/target/.
