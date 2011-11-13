#!/bin/sh

# Instruct bash to stop the script on error,
# or if an environment variable is not defined.
set -o errexit
set -o nounset

#
# This script needs to be run from the directory containing this "jdiff.sh" file.
# Every paths declared below this point - except ".m2" - will be relative to that
# directory.
#
cd `dirname $0`

#
# The GeoAPI versions to be compared. The OLD_NAME and NEW_NAME values
# will be used both as titles in JDiff report, and as filenames of XML
# files describing the old and new API. Names can not contain spaces.
#
export OLD_NAME=GeoAPI-3.0.0
export NEW_NAME=GeoAPI-3.1-SNAPSHOT
export OLD_URL=http://www.geoapi.org/3.0/javadoc/
export NEW_URL=http://www.geoapi.org/snapshot/javadoc/

#
# Paths to binaries and source code. Those paths are built dynamically from
# the location of this "jdiff.sh" shell script,  and assuming a local Maven
# repository located at the standard place.
#
export GEOAPI_HOME=../..
export SOURCE_PATH=$GEOAPI_HOME/geoapi/src/main/java
export TARGET_PATH=$GEOAPI_HOME/geoapi/target
export REPORT_PATH=$GEOAPI_HOME/target/site/changes/snapshot
export CLASSPATH=$TARGET_PATH/classes:$HOME/.m2/repository/javax/measure/jsr-275/0.9.3/jsr-275-0.9.3.jar

#
# Generate a XML file containing API information. The "-apiname" and "-firstsentence"
# options are specifics to the JDiff doclet.
#
javadoc -docletpath jdiff.jar -doclet jdiff.JDiff -apiname $NEW_NAME -firstsentence \
    -encoding UTF-8 -sourcepath $SOURCE_PATH -subpackages org.opengis \

#
# JDiff is bundled with the "xerces.jar" XML parser, but we will ignore it.
# Instead, we will use the XML parser provided with Sun JDK. This parser is
# specified by the -Dorg.xml.sax.driver=... property. The driver class name
# can be obtained by:
#
# System.out.println(org.xml.sax.helpers.XMLReaderFactory.createXMLReader());
#
# The path to UML.java is declared because Javadoc needs at least one class,
# but is not used by the JDiff doclet.
#
mkdir -p $REPORT_PATH
javadoc -docletpath jdiff.jar -doclet jdiff.JDiff \
    -J-Dorg.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser \
    -oldapi "$OLD_NAME" -javadocold $OLD_URL \
    -newapi "$NEW_NAME" -javadocnew $NEW_URL \
    -d $REPORT_PATH -encoding UTF-8 $SOURCE_PATH/org/opengis/annotation/UML.java
