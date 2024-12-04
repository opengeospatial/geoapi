# ===-----------------------------------------------------------------------===
#    GeoAPI - Python interfaces (abstractions) for OGC/ISO standards
#    Copyright © 2013-2024 Open Geospatial Consortium, Inc.
#    http: //www.geoapi.org
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#        http: //www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
# ===-----------------------------------------------------------------------===
"""This is the `representation` module.

This module contains geographic metadata structures regarding representation
derived from the ISO 19115-1:2014 and ISO 19115-2:2019 international
standards.
"""

from __future__ import annotations

from abc import ABC, abstractmethod
from collections.abc import Sequence
from enum import Enum
from typing import Optional

import opengis.geometry.primitive as primitive
import opengis.metadata.citation as meta_citation
import opengis.metadata.maintenance as meta_maintenance
import opengis.metadata.naming as meta_naming
import opengis.metadata.quality as meta_quality
import opengis.referencing.crs as crs


__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"


class CellGeometryCode(Enum):
    """Code indicating the geometry represented by the grid cell value."""

    POINT = "point"
    """Each cell represents a point."""

    AREA = "area"
    """Each cell represents an area."""

    VOXEL = "voxel"
    """
    Each cell represents a volumetric measurement on a regular grid in
    three dimensional space.
    """

    STRATUM = "stratum"
    """Height range for a single point vertical profile."""


class DimensionNameTypeCode(Enum):
    """Name of the dimension."""

    ROW = "row"
    """Ordinate (y) axis."""

    COLUMN = "column"
    """Abscissa (x) axis."""

    VERTICAL = "vertical"
    """Vertical (z) axis."""

    TRACK = "track"
    """Along the direction of motion of the scan point."""

    CROSS_TRACK = "crossTrack"
    """Perpendicular to the direction of motion of the scan point."""

    LINE = "line"
    """Scan line of a sensor."""

    SAMPLE = "sample"
    """Element along a scan line."""

    TIME = "time"
    """Duration."""


class GeometricObjectTypeCode(Enum):
    """
    Name of point or vector objects used to locate sero-, one-, two-, or
    three-dimensional spatial locations in the dataset.
    """

    COMPLEX = "complex"
    """
    Set of geometric primitives such that their boundaries can be
    represented as a union of other primitives.
    """

    COMPOSITE = "composite"
    """Connected set of curves, solids or surfaces."""

    CURVE = "curve"
    """
    Bounded, 1-dimensional geometric primitive, representing the continuous
    image of a line.
    """

    POINT = "point"
    """
    Zero-dimensional geometric primitive, representing a position but not
    having an extent.
    """

    SOLID = "solid"
    """
    Bounded, connected 3-dimensional geometric primitive, representing the
    continuous image of a region of space.
    """

    SURFACE = "surface"
    """
    Bounded, connected 2-dimensional geometric primitive, representing the
    continuous image of a region of a plane.
    """


class PixelOrientationCode(Enum):
    """Point in a pixel corresponding to the Earth location of the pixel"""

    CENTRE = "centre"
    """
    Point halfway between the lower left and the upper right of the pixel.
    """

    LOWER_LEFT = "lowerLeft"
    """
    The corner in the pixel closest to the origin of the SRS; if two are at
    the same distance from the origin, the one with the smallest x-value.
    """

    LOWER_RIGHT = "lowerRight"
    """Next corner counterclockwise from the lower left."""

    UPPER_RIGHT = "upperRight"
    """Next corner counterclockwise from the lower right."""

    UPPER_LEFT = "upperLeft"
    """Next corner counterclockwise from the upper right."""


class SpatialRepresentationTypeCode(Enum):
    """Method used to represent geographic information in the resource."""

    VECTOR = "vector"
    """Vector data are used to represent geographic data."""

    GRID = "grid"
    """Grid data are used to represent geographic data."""

    TEXT_TABLE = "textTable"
    """Textual or tabular data are used to represent geographic data."""

    TIN = "tin"
    """Triangulated irregular network."""

    STEREO_MODEL = "stereoModel"
    """
    Three-dimensional view formed by the intersecting homologous rays of
    an overlapping pair of images.
    """

    VIDEO = "video"
    """Scene from a video recording."""


class TopologyLevelCode(Enum):
    """Degree of the complexity of the spatial relationships."""

    GEOMETRY_ONLY = "geometryOnly"
    """
    Geometry objects without any additional structure which describes topology.
    """

    TOPOLOGY_1D = "topology1D"
    """
    1-Dimensional topological complex - commonly called “chain-node” topology.
    """

    PLANAR_GRAPH = "planarGraph"
    """
    1-Dimensional topological complex that is planar.

    NOTE: A planar graph is a graph that can be drawn in a plane in such a way
    that no two edges intersect except at a vertex.
    """

    FULL_PLANAR_GRAPH = "fullPlanarGraph"
    """
    2-Dimensional topological complex that is planar.

    NOTE: A 2-dimensional topological complex is commonly called
    “full topology” in a cartographic 2D environment.
    """

    SURFACE_GRAPH = "surfaceGraph"
    """
    1-Dimensional topological complex that is isomorphic to a subset of
    a surface.

    NOTE: A geometric complex is isomorphic to a topological complex if their
    elements are in a one-to-one, dimensional-and boundary-preserving
    correspondence to one another.
    """

    FULL_SURFACE_GRAPH = "fullSurfaceGraph"
    """
    2-Dimensional topological complex that is isomorphic to a subset of
    a surface.
    """

    TOPOLOGY_3D = "topology3D"
    """
    3-Dimensional topological complex.

    NOTE: A topological complex is a collection of topological primitives that
    are closed under the boundary operations.
    """

    FULL_TOPOLOGY_3D = "fullTopology3D"
    """Complete coverage of a 3D Euclidean coordinate space."""

    ABSTRACT = "abstract"
    """Topological complex without any specified geometric realisation."""


class Dimension(ABC):
    """Axis properties."""

    @property
    @abstractmethod
    def dimension_name(self) -> DimensionNameTypeCode:
        """Name of the axis."""

    @property
    @abstractmethod
    def dimension_size(self) -> int:
        """Number of elements along the axis."""

    @property
    @abstractmethod
    def resolution(self) -> float:
        """Degree of detail in the grid dataset."""

    @property
    @abstractmethod
    def dimension_title(self) -> Optional[str]:
        """
        Enhancement/modifier of the dimension name, e.g.,
        for a different time dimension: dimensiont_title = 'runtime'
        or more a more general case : dimension_name = 'column'
        dimension_title = 'Longitude'.
        """

    @property
    @abstractmethod
    def dimension_description(self) -> Optional[str]:
        """Description of the axis."""


class GeolocationInformation(ABC):
    """Geolocation information with data quality."""

    @property
    @abstractmethod
    def quality_info(self) -> Optional[Sequence[meta_quality.DataQuality]]:
        """
        Provides an overall assessment of quality of geolocation information.
        """


class GCP(ABC):
    """Information on a ground control point (GSP)."""

    @property
    @abstractmethod
    def geographic_coordinates(self) -> primitive.DirectPosition:
        """
        Geographic or map position of the control point, in either two
        or three dimensions.
        """

    @property
    @abstractmethod
    def accuracy_report(self) -> Optional[Sequence[meta_quality.Element]]:
        """Accuracy of a ground control point."""


class GCPCollection(GeolocationInformation):
    """A collection of ground control points (GCPs)."""

    @property
    @abstractmethod
    def collection_identification(self) -> int:
        """Identifier of the GCP collection."""

    @property
    @abstractmethod
    def collection_name(self) -> str:
        """Name of the GCP collection."""

    @property
    @abstractmethod
    def coordinate_reference_system(self) -> crs.ReferenceSystem:
        """Coordinate system in which the ground control points are defined."""

    @property
    @abstractmethod
    def gcp(self) -> Sequence[GCP]:
        """Ground control point(s) used in the collection."""


class GeometricObjects(ABC):
    """
    Number of objects, listed by geometric object type, used in the
    resource/dataset.
    """

    @property
    @abstractmethod
    def geometric_object_type(self) -> GeometricObjectTypeCode:
        """
        Name of point or vector objects used to locate zero-, one-, two-,
        or three-dimensional spatial locations in the resource/dataset.
        """

    @property
    @abstractmethod
    def geometric_object_count(self) -> Optional[int]:
        """
        Total number of the point or vector object type occurring in the
        resource/dataset.

        Domain: > 0
        """


class SpatialRepresentation(ABC):
    """Digital mechanism used to represent spatial information."""

    @property
    @abstractmethod
    def scope(self) -> Optional[meta_maintenance.Scope]:
        """Level and extent of the spatial representation."""


class GridSpatialRepresentation(SpatialRepresentation):
    """Information about grid spatial objects in the resource."""

    @property
    @abstractmethod
    def number_of_dimensions(self) -> int:
        """Number of independent spatial-temporal axes."""

    @property
    @abstractmethod
    def axis_dimension_properties(self) -> Sequence[Dimension]:
        """Information about spatial-temporal axis properties."""

    @property
    @abstractmethod
    def cell_geometry(self) -> CellGeometryCode:
        """Identification of grid data as point or cell."""

    @property
    @abstractmethod
    def transformation_parameter_availability(self) -> bool:
        """
        Indication of whether or not parameters for transformation between
        image coordinates and geographic or map coordinates exist
        (are available).
        """


class VectorSpatialRepresentation(SpatialRepresentation):
    """Information about the vector spatial objects in the resource."""

    @property
    @abstractmethod
    def topology_level(self) -> Optional[TopologyLevelCode]:
        """
        Code which identifies the degree of complexity of the spatial
        relationships.
        """

    @property
    @abstractmethod
    def geometric_objects(self) -> Optional[Sequence[GeometricObjects]]:
        """Information about the geometric objects used in the resource."""


class Georectified(GridSpatialRepresentation):
    """
    Grid whose cells are regularly spaced in a geographic (i.e. lat / long) or
    map coordinate system defined in the Spatial Referencing System (SRS) so
    that any cell in the grid can be geolocated given its grid coordinate and
    the grid origin, cell spacing, and orientation.
    """

    @property
    @abstractmethod
    def check_point_availability(self) -> bool:
        """
        Indication of whether or not geographic position points are available
        to test the accuracy of the georeferenced grid data.
        """

    @property
    @abstractmethod
    def check_point_description(self) -> Optional[str]:
        """
        Description of geographic position points used to test the accuracy of
        the georeferenced grid data.

        MANDATORY:
            If `check_point_availability` == `True`.
        """

    @property
    @abstractmethod
    def corner_points(self) -> Optional[Sequence[primitive.Point]]:
        """
        Earth location in the coordinate system defined by the Spatial
        Reference System and the grid coordinate of the cells at opposite ends
        of grid coverage along two diagonals in the grid spatial dimensions.
        There are four corner points in a georectified grid; at least two
        corner points along one diagonal are required. The first corner point
        corresponds to the origin of the grid.

        NOTE: The length of the `Sequence` of `Points` should be 2 - 4
        (i.e. 2, 3, or 4).
        """

    @property
    @abstractmethod
    def centre_point(self) -> Optional[primitive.Point]:
        """
        Earth location in the coordinate system defined by the Spatial
        Reference System and the grid coordinate of the cell halfway between
        opposite ends of the grid in the spatial dimensions.
        """

    @property
    @abstractmethod
    def point_in_pixel(self) -> PixelOrientationCode:
        """
        Point in a pixel corresponding to the Earth location of the pixel.
        """

    @property
    @abstractmethod
    def transformation_dimension_description(self) -> Optional[str]:
        """General description of the transformation."""

    @property
    @abstractmethod
    def transformation_dimension_mapping(self) -> Optional[Sequence[str]]:
        """
        Information about which grid axes are the spatial (map) axes.

        NOTE: The length of the `Sequence` of `str` should be 2. That is
        len(list(str)) should return 2.
        """

    @property
    @abstractmethod
    def check_point(self) -> Optional[Sequence[GCP]]:
        """
        Geographic references used to validate georectification of the data.
        """


class Georeferenceable(GridSpatialRepresentation):
    """
    ISO 19115-1: Grid with cells irregularly spaced in any given
    geographic/map projection coordinate system, whose individual cells can be
    geolocated using geolocation information supplied with the data but cannot
    be geolocated from the grid properties alone.

    ISO 19115-2: Description of information provided in metadata that allows
    the geographic or map location of the raster points to be located.
    """

    @property
    @abstractmethod
    def control_point_availability(self) -> bool:
        """Indication of whether or not control point(s) exists."""

    @property
    @abstractmethod
    def orientation_parameter_availability(self) -> bool:
        """
        Indication of whether or not orientation parameters are available.
        """

    @property
    @abstractmethod
    def orientation_parameter_description(self) -> Optional[str]:
        """Description of parameters used to describe sensor orientation."""

    @property
    @abstractmethod
    def georeferenced_parameters(self) -> meta_naming.Record:
        """Terms which support grid data georeferencing."""

    @property
    @abstractmethod
    def parameter_citation(self) -> Optional[Sequence[meta_citation.Citation]]:
        """Reference providing description of the parameters."""

    @property
    @abstractmethod
    def geolocation_information(self) -> Sequence[GeolocationInformation]:
        """
        Information that can be used to geo-locate the data.
        """
