#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
#    http://www.geoapi.org
#

#
# To run this demo, the PYTHONPATH environmental variable must be set to a directory which
# contain at least the "geoapi/src/main/python" and "geoapi-java-python/src/main/python"
# content (such directory can be created for example by "geoapi-python-wheel" module; we may
# provide a simpler approach in the future if https://github.com/opengeospatial/geoapi/issues/30
# is done.)
#
# In addition, JPY_PY_CONFIG environment variable shall be set to the path to a jpyconfig.py file,
# and that file shall contain paths to a Java implementation of GeoAPI as documented in
# http://www.geoapi.org/java-python/index.html
#
# To execute this test on command-line:
#
#     cd $GEOAPI_HOME/geoapi-java-python/src/test/python
#     python -m unittest discover
#

import jpyutil
jpyutil.init_jvm()

import jpy
import opengis.bridge.java.referencing
import unittest


class TestReferencing(unittest.TestCase):

    def test_find_crs(self):
        self._handler = jpy.get_type('org.opengis.bridge.python.PythonHelper')
        value = self._handler.findCoordinateReferenceSystem("EPSG:3395")
        if value:
            crs = opengis.bridge.java.referencing.CoordinateReferenceSystem(value)
            self.assertEqual(crs.name.code, "WGS 84 / World Mercator")
