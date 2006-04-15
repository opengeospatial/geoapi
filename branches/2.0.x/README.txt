This is the root directory for the GeoAPI project.

- geoapi    contains the latest GeoAPI release, with minor bug fixes. Changes that do not
            impact the API (javadoc clarifications, bug fixes in our very few implementations
            like code lists, etc.) are applied directly in this directory. Those changes may
            lead to a dot-dot release (e.g. 2.0.1 after 2.0.0).

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