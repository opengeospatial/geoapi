#!/bin/sh
#
# Update the copyright years of all files in the git repository.
# This script searches Java and Python files recursively from the
# current directory at the time this script is invoked.
#
set -o errexit
SCRIPT_DIR=`dirname "$0"`
find -type f \( -name "*.java" -o -name "*.py" \) -exec $SCRIPT_DIR/update_copyright_years.sh '{}' \;
