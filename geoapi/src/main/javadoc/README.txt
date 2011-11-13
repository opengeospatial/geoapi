The content of this directory is copied by an explicit
maven-resources-plugin:copy-resources  goal configured
in the root pom.xml file.  For an unknown reason, this
goal is not executed by "mvn deploy" as of Maven 3.0.3.
We need an explicit "mvn install deploy",  even if the
"package" phase was supposed to be implicit.
