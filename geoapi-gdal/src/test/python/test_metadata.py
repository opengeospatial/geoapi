#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    This file is hereby placed into the Public Domain.
#    This means anyone is free to do whatever they wish with this file.
#

#
# Demonstrate the use of GeoAPI wrappers for GDAL. This test requires the
# osgeo module to be installed on the local machine with netCDF support.
#
#   MacOS example: sudo port install gdal +hdf4 +hdf5 +netcdf
#
# In addition, the PYTHONPATH environmental variable must be set to the
# "geoapi/src/main/python:geoapi-gdal/src/main/python" directories,
# preferably using absolute path. Example on Unix systems:
#
#     export PYTHONPATH=$GEOAPI_HOME/geoapi/src/main/python
#     export PYTHONPATH=$PYTHONPATH:/$GEOAPI_HOME/geoapi-gdal/src/main/python
#
# To execute this test on command-line:
#
#     python -m unittest discover
#
import os
import unittest
from opengis.wrapper.gdal            import DataSet
from opengis.metadata.maintenance    import ScopeCode
from opengis.metadata.representation import DimensionNameTypeCode

#
# To run as a unit test on command-line:
#
#     python -m unittest discover
#
class TestGDAL(unittest.TestCase):
    """Test loading an image with GDAL."""

    def setUp(self):
        # Directory where are stored small test files.
        directory = os.path.dirname(os.path.realpath('__file__'))
        directory = os.path.join(directory, "..", "..", "..", "..", "geoapi-conformance", "src", "main", "resources", "org", "opengis", "test", "dataset")
        directory = os.path.realpath(directory)
        self.dataDirectory = directory

    def test_geographic(self):
        """Test a case using geographic coordinate reference system."""
        ds    = DataSet(os.path.join(self.dataDirectory, "Cube2D_geographic_packed.nc"))
        md    = ds.metadata()
        axis0 = md.spatial_representation_info[0].axis_dimension_properties[0]
        axis1 = md.spatial_representation_info[0].axis_dimension_properties[1]

        self.assertEqual(md.metadata_scope[0].resource_scope, ScopeCode.DATASET)
        self.assertEqual(axis0.dimension_name, DimensionNameTypeCode.COLUMN)
        self.assertEqual(axis1.dimension_name, DimensionNameTypeCode.ROW)
        self.assertEqual(axis0.dimension_size, 73)
        self.assertEqual(axis1.dimension_size, 73)
