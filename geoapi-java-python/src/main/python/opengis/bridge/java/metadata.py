#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

#
# This module requires jpy module to be installed on the local machine.
# That Java-Python module is documented at http://jpy.readthedocs.io/.
# In addition, the PYTHONPATH environmental variable must be set to the
# "geoapi/src/main/python" directory, preferably using absolute path.
#

import jpy
import opengis.metadata.base
import opengis.metadata.citation
import opengis.metadata.identification


class Citation(opengis.metadata.citation.Citation):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def title(self):
        return self._proxy.getTitle()

    def __str__(self):
        return self._proxy.toString()


class Identification(opengis.metadata.identification.Identification):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def citation(self):
        value = self._proxy.getCitation()
        if value:
            return Citation(value)
        else:
            return None

    @property
    def abstract(self):
        return self._proxy.getAbstract()

    def __str__(self):
        return self._proxy.toString()


class Metadata(opengis.metadata.base.Metadata):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def contact(self):
        return None

    @property
    def date_info(self):
        return None

    @property
    def identification_info(self):
        value = self._proxy.getIdentificationInfo()
        if value:
            it = value.iterator()
            if it.hasNext():
                return [Identification(it.next())]
            else:
                return []
        else:
            return []

    def __str__(self):
        return self._proxy.toString()
