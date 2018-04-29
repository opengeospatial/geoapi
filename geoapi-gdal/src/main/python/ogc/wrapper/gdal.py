#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    This file is hereby placed into the Public Domain.
#    This means anyone is free to do whatever they wish with this file.
#

#
# This module requires osgeo module to be installed on the local machine.
# GDAL/OGR Python binding API is documented at http://gdal.org/python/.
# In addition, the PYTHONPATH environmental variable must be set to the
# "geoapi/src/main/python" directory, preferably using absolute path.
#

from osgeo import gdal
from ogc.metadata.base import Metadata
from ogc.metadata.identification import DataIdentification
from ogc.metadata.citation import Citation

#
# By default the GDAL/OGR Python bindings do not raise exceptions when errors occur.
# Instead it returns an error value such as None and writes an error message to sys.stdout.
# The following call replaces that behavior by the use of exceptions:
#
gdal.UseExceptions()

#
# Information about a data set: title, where they are located, size, etc.
# We implement many interfaces in the same class for convenience, but this
# approach should be considered an implementation details susceptible to change.
#
class RasterMetadata(Metadata, DataIdentification, Citation):
    """Metadata about a GDAL dataset for a raster. The raster is assumed two-dimensional."""

    def __init__(self, ds):
        """Fetches metadata from the given GDAL dataset."""
        self._description = ds.GetDescription()
        self._xSize       = ds.RasterXSize
        self._ySize       = ds.RasterYSize
        self._numBands    = ds.RasterCount

    @property
    def identificationInfo(self):
        return self

    @property
    def citation(self):
        return self

    @property
    def title(self):
        return self._description

    @property
    def abstract(self):
        return None

    @property
    def language(self):
        return None

    @property
    def contact(self):
        return None

    @property
    def dateInfo(self):
        return None

    def __str__(self):
        """Returns a string representation of this metadata for debugging purpose."""
        return "Size=({0},{1}), NumBands={2}".format(self._xSize, self._ySize, self._numBands)


class DataSet:
    """A file opened by GDAL."""

    def __init__(self, filepath):
        """Opens a dataset for the given file in read-only mode."""
        self._ds = gdal.Open(filepath)

    def metadata(self):
        """Returns information about the dataset as ISO 19115 metadata."""
        return RasterMetadata(self._ds)
