#
#    GeoAPI - Java interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#        http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
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
link('LICENSE',                     buildDir + 'LICENSE')
os.chdir(buildDir)
import setup
