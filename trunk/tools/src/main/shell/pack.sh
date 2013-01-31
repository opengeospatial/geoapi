#!/bin/sh

# -----------------------------------------------
# Creates the PACK200 file with sample JAR files.
# This is used only at build time, and shall be
# run from the root of the GeoAPI project.
# -----------------------------------------------

set -o errexit
set -o nounset

export GEOAPI_VERSION=3.1-SNAPSHOT
export JSR275_VERSION=0.9.3
export JUNIT_VERSION=4.11
export MAVEN_REPOSITORY=~/.m2/repository/

mkdir target/tmp
cd target/tmp

# Unzip dependencies to be included in the single JAR files.
unzip -q -n ../../geoapi-conformance/target/geoapi-conformance-$GEOAPI_VERSION.jar
unzip -q -n ../../geoapi-pending/target/geoapi-pending-$GEOAPI_VERSION.jar
unzip -q -n $MAVEN_REPOSITORY/repository/junit/junit/$JUNIT_VERSION/junit-$JUNIT_VERSION.jar
unzip -q -n $MAVEN_REPOSITORY/repository/javax/measure/jsr-275/$JSR275_VERSION/jsr-275-$JSR275_VERSION.jar
rm -r META-INF/maven

# Create the JAR files, the PACK200 bundle and the zip file to download.
# The zip file will include the Proj.4 wrappers as an example.
jar -cmf META-INF/MANIFEST.MF geoapi-conformance.jar javax junit org
pack200 --strip-debug --no-keep-file-order --segment-limit=-1 --effort=9 geoapi-conformance.pack.gz geoapi-conformance.jar
zip -j -9 -q ../geoapi-conformance.zip geoapi-conformance.pack.gz ../../geoapi-proj4/target/geoapi-proj4-$GEOAPI_VERSION.jar ../../tools/src/main/shell/README.txt

# Cleanup.
cd -
rm -r target/tmp
