#
#    GeoAPI - Java interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
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
# Generate pip package
# Author: Johann Sorel (Geomatys)
#
# Usage: python3 build.py sdist
# Note: this file is called twice: by build.py and by pip install.
##########################################################

import setuptools


with open("README.md", "r") as fh:
    long_description = fh.read()

setuptools.setup(
    name                          = "opengis",
    version                       = "4.0-alpha",
    author                        = "Open Geospatial Consortium",
    author_email                  = "geoapi.swg@lists.opengeospatial.org",
    description                   = "GeoAPI - interfaces for OGC/ISO standards",
    long_description              = long_description,
    long_description_content_type = "text/markdown",
    url                           = "http://www.geoapi.org",
    packages                      = setuptools.find_packages(),
    package_data                  = {},
    classifiers=(
        "Programming Language :: Python :: 3",
        "License :: OSI Approved :: BSD License",
        "Operating System :: OS Independent",
        "Development Status :: 4 - Alpha"
    )
)
