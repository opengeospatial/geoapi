#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
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

    @abstractproperty
    def dimensionName(self) -> DimensionNameTypeCode:
        """Name of the axis."""
        pass

    @abstractproperty
    def dimensionSize(self) -> int:
        """Number of elements along the axis."""
        pass

    @abstractproperty
    def resolution(self) -> float:
        """Degree of detail in the grid dataset."""
        pass

    @abstractproperty
    def dimensionTitle(self) -> str:
        """Enhancement/modifier of the dimension name EX for other time dimension 'runtime' or dimensionName = 'column' dimensionTitle = 'Longitude'."""
        pass

    @abstractproperty
    def dimensionDescription(self) -> str:
        """Description of the axis."""
        pass

class GeolocationInformation(ABC):

    @abstractproperty
    def qualityInfo(self) -> DataQuality:
        pass

class GCP(ABC):

    @abstractproperty
    def geographicCoordinates(self) -> DirectPosition:
        pass

    @abstractproperty
    def accuracyReport(self) -> Element:
        pass

class GCPCollection(GeolocationInformation):

    @abstractproperty
    def gcp(self) -> GCP:
        pass

    @abstractproperty
    def collectionIdentification(self) -> int:
        pass

    @abstractproperty
    def collectionName(self) -> str:
        pass

    @abstractproperty
    def coordinateReferenceSystem(self) -> ReferenceSystem:
        pass

class GeometricObjects(ABC):
    """Number of objects, listed by geometric object type, used in the dataset."""

    @abstractproperty
    def geometricObjectType(self) -> GeometricObjectTypeCode:
        """Name of point or vector objects used to locate zero-, one-, two-, or three-dimensional spatial locations in the dataset."""
        pass

    @abstractproperty
    def geometricObjectCount(self) -> int:
        """Total number of the point or vector object type occurring in the dataset."""
        pass

class SpatialRepresentation(ABC):
    """Digital mechanism used to represent spatial information."""

class GridSpatialRepresentation(SpatialRepresentation):
    """Information about grid spatial objects in the resource."""

    @abstractproperty
    def numberOfDimensions(self) -> int:
        """Number of independent spatial-temporal axes."""
        pass

    @abstractproperty
    def axisDimensionProperties(self) -> Dimension:
        """Information about spatial-temporal axis properties."""
        pass

    @abstractproperty
    def cellGeometry(self) -> CellGeometryCode:
        """Identification of grid data as point or cell."""
        pass

    @abstractproperty
    def transformationParameterAvailability(self):
        """Indication of whether or not parameters for transformation between image coordinates and geographic or map coordinates exist (are available)."""
        pass

class VectorSpatialRepresentation(SpatialRepresentation):
    """Information about the vector spatial objects in the resource."""

    @abstractproperty
    def topologyLevel(self) -> TopologyLevelCode:
        """Code which identifies the degree of complexity of the spatial relationships."""
        pass

    @abstractproperty
    def geometricObjects(self) -> GeometricObjects:
        """Information about the geometric objects used in the resource."""
        pass

class Georectified(GridSpatialRepresentation):
    """Grid whose cells are regularly spaced in a geographic (i.e., lat / long) or map coordinate system defined in the Spatial Referencing System (SRS) so that any cell in the grid can be geolocated given its grid coordinate and the grid origin, cell spacing, and orientation."""

    @abstractproperty
    def checkPointAvailability(self):
        """Indication of whether or not geographic position points are available to test the accuracy of the georeferenced grid data."""
        pass

    @abstractproperty
    def checkPointDescription(self) -> str:
        """Description of geographic position points used to test the accuracy of the georeferenced grid data."""
        pass

    @abstractproperty
    def cornerPoints(self) -> Point:
        """Earth location in the coordinate system defined by the Spatial Reference System and the grid coordinate of the cells at opposite ends of grid coverage along two diagonals in the grid spatial dimensions. There are four corner points in a georectified grid; at least two corner points along one diagonal are required. The first corner point corresponds to the origin of the grid."""
        pass

    @abstractproperty
    def centrePoint(self) -> Point:
        """Earth location in the coordinate system defined by the Spatial Reference System and the grid coordinate of the cell halfway between opposite ends of the grid in the spatial dimensions."""
        pass

    @abstractproperty
    def pointInPixel(self) -> PixelOrientationCode:
        """Point in a pixel corresponding to the Earth location of the pixel."""
        pass

    @abstractproperty
    def transformationDimensionDescription(self) -> str:
        """General description of the transformation."""
        pass

    @abstractproperty
    def transformationDimensionMapping(self) -> str:
        """Information about which grid axes are the spatial (map) axes."""
        pass

    @abstractproperty
    def checkPoint(self) -> GCP:
        pass

class Georeferenceable(GridSpatialRepresentation):
    """Grid with cells irregularly spaced in any given geographic/map projection coordinate system, whose individual cells can be geolocated using geolocation information supplied with the data but cannot be geolocated from the grid properties alone."""

    @abstractproperty
    def controlPointAvailability(self):
        """Indication of whether or not control point(s) exists."""
        pass

    @abstractproperty
    def orientationParameterAvailability(self):
        """Indication of whether or not orientation parameters are available."""
        pass

    @abstractproperty
    def orientationParameterDescription(self) -> str:
        """Description of parameters used to describe sensor orientation."""
        pass

    @abstractproperty
    def georeferencedParameters(self) -> Record:
        """Terms which support grid data georeferencing."""
        pass

    @abstractproperty
    def parameterCitation(self) -> Citation:
        """Reference providing description of the parameters."""
        pass

    @abstractproperty
    def geolocationInformation(self) -> GeolocationInformation:
        pass
