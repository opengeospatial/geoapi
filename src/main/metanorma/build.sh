#!/bin/bash
#
# Builds GeoAPI specification with Metanorma in a Linux environment.
# This script assumes that the `metanorma` command-line is available
# on the path. See https://www.metanorma.org/install/linux/
#
# The main purpose of this script is to write the output in the target
# directory for avoiding to pollute the source directory.
#

# Stop the script on first error.
set -o errexit

SOURCE_DIR="`dirname $0`"
SOURCE_DIR="`realpath $SOURCE_DIR`"
TARGET_DIR="`realpath $SOURCE_DIR/../../../target`"
mkdir --parents --verbose $TARGET_DIR

metanorma compile --output-dir "$TARGET_DIR" --agree-to-terms --type ogc --extensions html standard.adoc

# Following directory is empty after a successful build.
rmdir _files
