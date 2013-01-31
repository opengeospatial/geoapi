#!/bin/sh

# -----------------------------------------------
# Creates the PACK200 file with sample JAR files.
# This is used only at build time, and shall be
# run from the root of the GeoAPI project.
#
# See http://www.geoapi.org/tools/index.html
# -----------------------------------------------

# Instruct bash to stop the script on error,
# or if an environment variable is not defined.
set -o errexit
set -o nounset

# This script needs to be run from the "<root>/target/tmp" directory.
cd `dirname $0`
cd ../../../../target
mkdir tmp
cd tmp

# Declaration of version numbers for all dependencies.
export GEOAPI_VERSION=3.1-SNAPSHOT
export JSR275_VERSION=0.9.3
export JUNIT_VERSION=4.11
export HAMCREST_VERSION=1.3
export MAVEN_REPOSITORY=~/.m2/repository

# Unzip dependencies to be included in the single JAR files.
unzip -q -n ../../geoapi-conformance/target/geoapi-conformance-$GEOAPI_VERSION.jar
unzip -q -n ../../geoapi-pending/target/geoapi-pending-$GEOAPI_VERSION.jar
unzip -q -n $MAVEN_REPOSITORY/junit/junit/$JUNIT_VERSION/junit-$JUNIT_VERSION.jar
unzip -q -n $MAVEN_REPOSITORY/org/hamcrest/hamcrest-core/$HAMCREST_VERSION/hamcrest-core-$HAMCREST_VERSION.jar
unzip -q -n $MAVEN_REPOSITORY/javax/measure/jsr-275/$JSR275_VERSION/jsr-275-$JSR275_VERSION.jar
rm -r META-INF/maven

# Create the PACK200 bundle for the geoapi-conformance module.
jar -cmf META-INF/MANIFEST.MF geoapi-conformance.jar javax junit org
pack200 --strip-debug --no-keep-file-order --segment-limit=-1 --effort=9 geoapi-conformance.pack.gz geoapi-conformance.jar

# Create the zip file to download.
# The zip file will include the Proj.4 wrappers as an example.
ln ../../tools/src/main/shell/resources/README/conformance.txt README.txt
zip -j -9 -q ../geoapi-conformance.zip geoapi-conformance.pack.gz ../../geoapi-proj4/target/geoapi-proj4-$GEOAPI_VERSION.jar ../../LICENSE.txt README.txt

# Cleanup.
cd ..
rm -r tmp
