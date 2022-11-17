#
#    GeoAPI - Java interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#
#    Permission to use, copy, and modify this software and its documentation, with
#    or without modification, for any purpose and without fee or royalty is hereby
#    granted, provided that you include the following on ALL copies of the software
#    and documentation or portions thereof, including modifications, that you make:
#
#    1. The full text of this NOTICE in a location viewable to users of the
#       redistributed or derivative work.
#    2. Notice of any changes or modifications to the OGC files, including the
#       date changes were made.
#
#    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
#    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
#    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
#    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
#    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
#
#    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
#    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
#
#    The name and trademarks of copyright holders may NOT be used in advertising or
#    publicity pertaining to the software without specific, written prior permission.
#    Title to copyright in this software and any associated documentation will at all
#    times remain with copyright holders.
#

##########################################################
# Python Wheel build script
# Author: Johann Sorel (Geomatys)
#
# Usage: python3 src/main/python/build.py sdist
##########################################################

import setuptools
import glob
import os
import os.path

# The directory relative to project root where all files will be copied or created.
buildDir = 'target/python/'


def link(sourceFile, targetFile):
    """
    "Copies" a file using hard-link instead of a real copy.
    If the target file exists, it will be removed before to be relinked.
    """
    if os.path.exists(targetFile):
        os.remove(targetFile)
    os.link(sourceFile, targetFile)


def copyPythonFiles(module, directory):
    """
    Links all python files from the given source directory to the target directory.
    The source directory is inferred from the given module name (e.g. 'geoapi') and
    directory relative to the `src/main/python` base directory. The use of hard links
    instead of copies is for efficiency since the "copied" files are only temporary.
    """
    targetDir = buildDir + directory
    os.makedirs(name=targetDir, exist_ok=True)
    files = glob.glob(module + '/src/main/python/' + directory + '/*.py')
    for sourceFile in files:
        (head, tail) = os.path.split(sourceFile)
        link(sourceFile, targetDir + '/' + tail)


##########################################################
# Copy python files located in various modules.
##########################################################

copyPythonFiles('geoapi',             'opengis')
copyPythonFiles('geoapi',             'opengis/metadata')
copyPythonFiles('geoapi',             'opengis/referencing')
copyPythonFiles('geoapi',             'opengis/geometry')
copyPythonFiles('geoapi-java-python', 'opengis/bridge')
copyPythonFiles('geoapi-java-python', 'opengis/bridge/java')


##########################################################
# Generate pip package.
##########################################################

link('src/main/python/setup.py',    buildDir + 'setup.py')
link('src/main/python/README.md',   buildDir + 'README.md')
link('src/main/python/MANIFEST.in', buildDir + 'MANIFEST.in')
link('LICENSE.txt',                 buildDir + 'LICENSE')
os.chdir(buildDir)
import setup
