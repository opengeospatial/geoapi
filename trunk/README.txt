This is the root directory for the GeoAPI project.

- geoapi    contains the next GeoAPI release being put forward for approval. In order to get early
            feedback from implementors we will occasionally issue a milestone release. For details
            please visit the project website.

- dist      contains a build target for a Java 1.4 distribution of geoapi suitable for use with
            J2EE. This target will producea jar that lacks generics and typenarrowing.

- tools-ant Used to downgrade java 5 code to Java 1.4


To build the first time:
 cd trunk/tools-ant
 mvn install           (to be done only once, or everytime erasure.build.xml changed).
 cd dist
 mvn package 

To build normally:
 mvn install