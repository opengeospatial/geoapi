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
source ./setenv.sh
cd ../../../../target
mkdir tmp
cd tmp

# Unzip dependencies to be included in the single JAR files.
unzip -q -n ../../geoapi-conformance/target/geoapi-conformance-$GEOAPI_VERSION.jar
unzip -q -n ../../geoapi-pending/target/geoapi-pending-$GEOAPI_VERSION.jar
unzip -q -n $MAVEN_REPOSITORY/junit/junit/$JUNIT_VERSION/junit-$JUNIT_VERSION.jar
unzip -q -n $MAVEN_REPOSITORY/org/hamcrest/hamcrest-core/$HAMCREST_VERSION/hamcrest-core-$HAMCREST_VERSION.jar
unzip -q -n $MAVEN_REPOSITORY/javax/measure/unit-api/$UOM_VERSION/unit-api-$UOM_VERSION.jar
rm -r META-INF/maven

# Create the PACK200 bundle for the geoapi-conformance module.
jar -cmf META-INF/MANIFEST.MF geoapi-conformance.jar javax junit org
pack200 --strip-debug --no-keep-file-order --segment-limit=-1 --effort=9 geoapi-conformance.pack.gz geoapi-conformance.jar

# Create the zip file to download.
ln ../../tools/src/main/shell/resources/README/conformance.txt README.txt
zip -j -9 -q ../geoapi-conformance.zip geoapi-conformance.pack.gz ../../LICENSE.txt README.txt

# Cleanup.
cd ..
rm -r tmp
