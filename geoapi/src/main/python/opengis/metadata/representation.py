#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractmethod
from typing import Sequence
from enum import Enum



class CellGeometryCode(Enum):
    POINT = "point"
    AREA = "area"
    VOXEL = "voxel"
    STRATUM = "stratum"



class DimensionNameTypeCode(Enum):
    ROW = "row"
    COLUMN = "column"
    VERTICAL = "vertical"
    TRACK = "track"
    CROSS_TRACK = "crossTrack"
    LINE = "line"
    SAMPLE = "sample"
    TIME = "time"



class GeometricObjectTypeCode(Enum):
    COMPLEX = "complex"
    COMPOSITE = "composite"
    CURVE = "curve"
    POINT = "point"
    SOLID = "solid"
    SURFACE = "surface"



class SpatialRepresentationTypeCode(Enum):
    VECTOR = "vector"
    GRID = "grid"
    TEXT_TABLE = "textTable"
    TIN = "tin"
    STEREO_MODEL = "stereoModel"
    VIDEO = "video"



class TopologyLevelCode(Enum):
    GEOMETRY_ONLY = "geometryOnly"
    TOPOLOGY_1D = "topology1D"
    PLANAR_GRAPH = "planarGraph"
    FULL_PLANAR_GRAPH = "fullPlanarGraph"
    SURFACE_GRAPH = "surfaceGraph"
    FULL_SURFACE_GRAPH = "fullSurfaceGraph"
    TOPOLOGY_3D = "topology3D"
    FULL_TOPOLOGY_3D = "fullTopology3D"
    ABSTRACT = "abstract"



class ReferenceSystemTypeCode(Enum):
    COMPOUND_ENGINEERING_PARAMETRIC = "compoundEngineeringParametric"
    COMPOUND_ENGINEERING_PARAMETRIC_TEMPORAL = "compoundEngineeringParametricTemporal"
    COMPOUND_ENGINEERING_TEMPORAL = "compoundEngineeringTemporal"
    COMPOUND_ENGINEERING_VERTICAL = "compoundEngineeringVertical"
    COMPOUND_ENGINEERING_VERTICAL_TEMPORAL = "compoundEngineeringVerticalTemporal"
    COMPOUND_GEOGRAPHIC2D_PARAMETRIC = "compoundGeographic2DParametric"
    COMPOUND_GEOGRAPHIC2D_PARAMETRIC_TEMPORAL = "compoundGeographic2DParametricTemporal"
    COMPOUND_GEOGRAPHIC2D_TEMPORAL = "compoundGeographic2DTemporal"
    COMPOUND_GEOGRAPHIC2D_VERTICAL = "compoundGeographic2DVertical"
    COMPOUND_GEOGRAPHIC2D_VERTICAL_TEMPORAL = "compoundGeographic2DVerticalTemporal"
    COMPOUND_GEOGRAPHIC3D_TEMPORAL = "compoundGeographic3DTemporal"
    COMPOUND_PROJECTED2D_PARAMETRIC = "compoundProjected2DParametric"
    COMPOUND_PROJECTED2D_PARAMETRIC_TEMPORAL = "compoundProjected2DParametricTemporal"
    COMPOUND_PROJECTED_TEMPORAL = "compoundProjectedTemporal"
    COMPOUND_PROJECTED_VERTICAL = "compoundProjectedVertical"
    COMPOUND_PROJECTED_VERTICAL_TEMPORAL = "compoundProjectedVerticalTemporal"
    ENGINEERING = "engineering"
    ENGINEERING_DESIGN = "engineeringDesign"
    ENGINEERING_IMAGE = "engineeringImage"
    GEODETIC_GEOCENTRIC = "geodeticGeocentric"
    GEODETIC_GEOGRAPHIC_2D = "geodeticGeographic2D"
    GEODETIC_GEOGRAPHIC_3D = "geodeticGeographic3D"
    GEOGRAPHIC_IDENTIFIER = "geographicIdentifier"
    LINEAR = "linear"
    PARAMETRIC = "parametric"
    PROJECTED = "projected"
    TEMPORAL = "temporal"
    VERTICAL = "vertical"



class PixelOrientationCode(Enum):
    CENTER = "center"
    LOWER_LEFT = "lowerLeft"
    LOWER_RIGHT = "lowerRight"
    UPPER_RIGHT = "upperRight"
    UPPER_LEFT = "upperLeft"



class Dimension(ABC):
    """Axis properties."""

    @property
    @abstractmethod
    def dimension_name(self) -> DimensionNameTypeCode:
        """Name of the axis."""
        pass

    @property
    @abstractmethod
    def dimension_size(self) -> int:
        """Number of elements along the axis."""
        pass

    @property
    def resolution(self) -> float:
        """Degree of detail in the grid dataset."""
        return None

    @property
    def dimension_title(self) -> str:
        """Enhancement/modifier of the dimension name EX for other time dimension 'runtime' or dimensionName = 'column' dimensionTitle = 'Longitude'."""
        return None

    @property
    def dimension_description(self) -> str:
        """Description of the axis."""
        return None



class GeolocationInformation(ABC):

    @property
    def quality_info(self) -> Sequence['DataQuality']:
        return None



class GCP(ABC):

    @property
    @abstractmethod
    def geographic_coordinates(self):
        pass

    @property
    def accuracy_report(self) -> Sequence['Element']:
        return None



class GCPCollection(GeolocationInformation):

    @property
    @abstractmethod
    def gcp(self) -> Sequence[GCP]:
        """Ground control point(s) used in the collection."""
        pass

    @property
    @abstractmethod
    def collection_identification(self) -> int:
        """Identifier of the GCP collection."""
        pass

    @property
    @abstractmethod
    def collection_name(self) -> str:
        """Name of the GCP collection."""
        pass

    @property
    @abstractmethod
    def coordinate_reference_system(self):
        """Coordinate system in which the ground control points are defined."""
        # See https://github.com/opengeospatial/geoapi/issues/57
        pass



class GeometricObjects(ABC):
    """Number of objects, listed by geometric object type, used in the dataset."""

    @property
    @abstractmethod
    def geometric_object_type(self) -> GeometricObjectTypeCode:
        """Name of point or vector objects used to locate zero-, one-, two-, or three-dimensional spatial locations in the dataset."""
        pass

    @property
    def geometric_object_count(self) -> int:
        """Total number of the point or vector object type occurring in the dataset."""
        return None



class SpatialRepresentation(ABC):
    """Digital mechanism used to represent spatial information."""



class GridSpatialRepresentation(SpatialRepresentation):
    """Information about grid spatial objects in the resource."""

    @property
    @abstractmethod
    def number_of_dimensions(self) -> int:
        """Number of independent spatial-temporal axes."""
        pass

    @property
    def axis_dimension_properties(self) -> Sequence[Dimension]:
        """Information about spatial-temporal axis properties."""
        return None

    @property
    @abstractmethod
    def cell_geometry(self) -> CellGeometryCode:
        """Identification of grid data as point or cell."""
        pass

    @property
    @abstractmethod
    def transformation_parameter_availability(self):
        """Indication of whether or not parameters for transformation between image coordinates and geographic or map coordinates exist (are available)."""
        pass



class VectorSpatialRepresentation(SpatialRepresentation):
    """Information about the vector spatial objects in the resource."""

    @property
    def topology_level(self) -> TopologyLevelCode:
        """Code which identifies the degree of complexity of the spatial relationships."""
        return None

    @property
    def geometric_objects(self) -> Sequence[GeometricObjects]:
        """Information about the geometric objects used in the resource."""
        return None



class Georectified(GridSpatialRepresentation):
    """Grid whose cells are regularly spaced in a geographic (i.e., lat / long) or map coordinate system defined in the Spatial Referencing System (SRS) so that any cell in the grid can be geolocated given its grid coordinate and the grid origin, cell spacing, and orientation."""

    @property
    @abstractmethod
    def check_point_availability(self):
        """Indication of whether or not geographic position points are available to test the accuracy of the georeferenced grid data."""
        pass

    @property
    def check_point_description(self) -> str:
        """Description of geographic position points used to test the accuracy of the georeferenced grid data."""
        return None

    @property
    @abstractmethod
    def corner_points(self):
        """Earth location in the coordinate system defined by the Spatial Reference System and the grid coordinate of the cells at opposite ends of grid coverage along two diagonals in the grid spatial dimensions. There are four corner points in a georectified grid; at least two corner points along one diagonal are required. The first corner point corresponds to the origin of the grid."""
        pass

    @property
    def centre_point(self):
        """Earth location in the coordinate system defined by the Spatial Reference System and the grid coordinate of the cell halfway between opposite ends of the grid in the spatial dimensions."""
        return None

    @property
    @abstractmethod
    def point_in_pixel(self) -> PixelOrientationCode:
        """Point in a pixel corresponding to the Earth location of the pixel."""
        pass

    @property
    def transformation_dimension_description(self) -> str:
        """General description of the transformation."""
        return None

    @property
    def transformation_dimension_mapping(self) -> Sequence[str]:
        """Information about which grid axes are the spatial (map) axes."""
        return None

    @property
    def check_point(self) -> Sequence[GCP]:
        return None



from opengis.metadata.naming import Record
from opengis.metadata.citation import Citation

class Georeferenceable(GridSpatialRepresentation):
    """Grid with cells irregularly spaced in any given geographic/map projection coordinate system, whose individual cells can be geolocated using geolocation information supplied with the data but cannot be geolocated from the grid properties alone."""

    @property
    @abstractmethod
    def control_point_availability(self):
        """Indication of whether or not control point(s) exists."""
        pass

    @property
    @abstractmethod
    def orientation_parameter_availability(self):
        """Indication of whether or not orientation parameters are available."""
        pass

    @property
    def orientation_parameter_description(self) -> str:
        """Description of parameters used to describe sensor orientation."""
        return None

    @property
    @abstractmethod
    def georeferenced_parameters(self) -> Record:
        """Terms which support grid data georeferencing."""
        pass

    @property
    def parameter_citation(self) -> Sequence[Citation]:
        """Reference providing description of the parameters."""
        return None

    @property
    @abstractmethod
    def geolocation_information(self) -> Sequence[GeolocationInformation]:
        pass
