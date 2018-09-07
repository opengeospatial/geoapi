#
#    GeoAPI - Java interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
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
# Usage: python3 build.py sdist
##########################################################

import setuptools
import glob
import shutil
import os


def createInit(path):
    f = open(path+"/__init__.py", "w+")
    f.close()


shutil.rmtree('dist/',    ignore_errors=True)
shutil.rmtree('temp/',    ignore_errors=True)
shutil.rmtree('opengis/', ignore_errors=True)

##########################################################
# Copy python files located in other modules.
##########################################################

# Create java library folder
directory = 'opengis/'
if not os.path.exists(directory):
    os.makedirs(directory)

# API
dir = 'opengis/metadata/'
os.makedirs(dir)
files = glob.glob('../geoapi/src/main/python/opengis/metadata/*.py')
for f in files:
    shutil.copy2(f, dir)

# Java bridge
dir = 'opengis/bridge/java/'
os.makedirs(dir)
files = glob.glob('../geoapi-java-python/src/main/python/opengis/bridge/java/*.py')
for f in files:
    shutil.copy2(f, dir)

# GDAL bridge
dir = 'opengis/wrapper/'
os.makedirs(dir)
files = glob.glob('../geoapi-gdal/src/main/python/opengis/wrapper/*.py')
for f in files:
    shutil.copy2(f, dir)

# __init__.py in each folder to include
createInit("opengis")
createInit("opengis/bridge")
createInit("opengis/bridge/java")


##########################################################
# Generate pip package.
##########################################################

import setup

##########################################################
# Cleaning, setup tool makes a lot of side files.
##########################################################
shutil.rmtree("opengis.egg-info", ignore_errors=True)
shutil.rmtree("__pycache__",      ignore_errors=True)
shutil.rmtree("opengis",          ignore_errors=True)
