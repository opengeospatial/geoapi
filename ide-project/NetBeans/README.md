# NetBeans project configuration

This is the root directory of NetBeans project configuration for GeoAPI.
There is 3 important files that must be edited BY HAND for preserving user-
neutral configuration:


## build.xml

This Ant file contains tasks executed in addition to the default NetBeans
tasks during the build process, for:

* Copying the resources.


## nbproject/project.properties

Contains most of the project configuration. The content of this file is
modified by the NetBeans "Project properties" panel. PLEASE REVIEW MANUALLY
BEFORE COMMITTING ANY CHANGE. Please preserve the formatting for easier
reading. Ensure that all directories are relative to a variable.


## nbproject/project.xml

Edited together with `nbproject/project.properties` for declaring the source
directories.
