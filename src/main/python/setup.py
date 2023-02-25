#
#    GeoAPI - Java interfaces for OGC/ISO standards
#    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
#    http://www.geoapi.org
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


##################################################################
# Generate pip package
# Author: Johann Sorel (Geomatys)
#
# Note: this file is called twice: by build.py and by pip install.
##################################################################

import setuptools


with open("README.md", "r") as fh:
    long_description = fh.read()

setuptools.setup(
    name                          = "geoapi",
    version                       = "4.0a0",
    url                           = "https://www.geoapi.org",
    description                   = "GeoAPI - interfaces for OGC/ISO standards",
    long_description              = long_description,
    long_description_content_type = "text/markdown",
    platforms                     = "OS Independent",
    keywords                      = ["geospatial", "metadata", "referencing"],
    license                       = "OGC Software License",
    packages                      = setuptools.find_packages(),
    classifiers=[
        "Development Status :: 3 - Alpha",
        "Intended Audience :: Information Technology",
        "License :: OGC Software License",
        "Natural Language :: English",
        "Operating System :: OS Independent",
        "Programming Language :: Python :: 3",
        "Topic :: Scientific/Engineering :: GIS"
    ]
)
