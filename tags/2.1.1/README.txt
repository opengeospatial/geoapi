This is the root directory for the GeoAPI project.

---------------------------------------------------------------------------------------------------
WARNING
---------------------------------------------------------------------------------------------------
This release is not an OGC approved standard. It is an intermediate step on the migration path from
Java 1.4 to Java 5, from JSR-108 to JSR-275 and for some changes in ISO standards / GeoAPI in the
form of deprecated methods to be removed in GeoAPI 2.2.


---------------------------------------------------------------------------------------------------
Modules
---------------------------------------------------------------------------------------------------

  geoapi     Contains the next GeoAPI release being put forward for approval. In order to get early
             feedback from implementors we will occasionally issue a milestone release. For details
             please visit the http://geoapi.sourceforge.net/ project website.

  nogeneric  Contains a build target for a Java 1.4 distribution of geoapi suitable for use with
             J2EE. This target will produces JAR that lacks generics and typenarrowing. GeoAPI 2.1
             releases are the last ones to contain this module - it is deleted from GeoAPI 2.2.

  erasure    Used to downgrade java 5 code to Java 1.4. GeoAPI 2.1 releases are the last ones to
             contain this module - it is deleted from GeoAPI 2.2.

  tools      Used for generating some special pages for javadoc.



---------------------------------------------------------------------------------------------------
How to build
---------------------------------------------------------------------------------------------------

* Download and install j2SE 1.5 or above (http://java.sun.com).

* Download and install Maven 2 (http://maven.apache.org).

* Invoke "mvn install" from the command line in this directory.

A jar for J2SE 1.5 should appears in geoapi/target/.
A jar for J2SE 1.4 should appears in nogenerics/target/.
