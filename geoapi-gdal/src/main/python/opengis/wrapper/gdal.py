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
from opengis.metadata.base           import Metadata, MetadataScope
from opengis.metadata.maintenance    import ScopeCode
from opengis.metadata.identification import DataIdentification
from opengis.metadata.citation       import Citation
from opengis.metadata.content        import CoverageDescription
from opengis.metadata.representation import CellGeometryCode, Dimension, DimensionNameTypeCode, GridSpatialRepresentation

#
# By default the GDAL/OGR Python bindings do not raise exceptions when errors occur.
# Instead it returns an error value such as None and writes an error message to sys.stdout.
# The following call replaces that behavior by the use of exceptions:
#
gdal.UseExceptions()

#
# A component of RasterMetadata holding information about a single dimension of the raster.
# Those axes are built by RasterMetadata.spatial_representation_info[0].axis_dimension_properties.
#


class GridAxis(Dimension):
    """Information about the x or y axis of a raster."""

    def __init__(self, dimension, size):
        """Stores information about the given raster dimension."""
        self._dimension = dimension
        self._size = size

    @property
    def dimension_name(self):
        if self._dimension == 0:
            return DimensionNameTypeCode.COLUMN
        elif self._dimension == 1:
            return DimensionNameTypeCode.ROW
        else:
            return None

    @property
    def dimension_size(self):
        return self._size

    def __str__(self):
        """Returns a string representation of this metadata for debugging purpose."""
        return "{1} {0}s".format(self.dimension_name.value, self.dimension_size)

#
# Information about a data set: title, where they are located, size, etc.
# We implement many interfaces in the same class for convenience, but this
# approach should be considered an implementation details susceptible to change.
#


class RasterMetadata(Metadata, MetadataScope, DataIdentification, Citation, CoverageDescription, GridSpatialRepresentation):
    """Metadata about a GDAL dataset for a raster. The raster is assumed two-dimensional."""

    def __init__(self, ds):
        """Fetches metadata from the given GDAL dataset."""
        self._description = ds.GetDescription()
        self._xSize       = ds.RasterXSize
        self._ySize       = ds.RasterYSize
        self._numBands    = ds.RasterCount
        cg = ds.GetMetadataItem("AREA_OR_POINT")
        if cg == "Point":
            self._cellGeometry = CellGeometryCode.POINT
        elif cg == "Area":
            self._cellGeometry = CellGeometryCode.AREA
        else:
            self._cellGeometry = None

    @property
    def metadata_scope(self):
        return [self]

    @property
    def resource_scope(self):
        return ScopeCode.DATASET

    @property
    def identification_info(self):
        return [self]

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
        return []

    @property
    def date_info(self):
        return []

    @property
    def spatial_representation_info(self):
        return [self]

    @property
    def number_of_dimensions(self):
        return 2

    @property
    def axis_dimension_properties(self):
        return [GridAxis(0, self._xSize), GridAxis(1, self._ySize)]

    @property
    def cell_geometry(self):
        return self._cellGeometry

    @property
    def transformation_parameter_availability(self):
        return False

    @property
    def content_info(self):
        return [self]

    @property
    def attribute_description(self):
        return None

    def __str__(self):
        """Returns a string representation of this metadata for debugging purpose."""
        return "Size=({0},{1},{2})".format(self._xSize, self._ySize, self._numBands)

#
# An entry point for loading a data file with GDAL.
# This API is not yet part of any GeoAPI standard.
#
class DataSet:
    """A file opened by GDAL."""

    def __init__(self, filepath):
        """Opens a dataset for the given file in read-only mode."""
        self._ds = gdal.Open(filepath)

    def metadata(self):
        """Returns information about the dataset as ISO 19115 metadata."""
        return RasterMetadata(self._ds)

    def close(self):
        pass
