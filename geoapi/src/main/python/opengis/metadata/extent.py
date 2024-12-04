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
"""This is the `extent` module.

This module contains geographic metadata structures regarding data extent
derived from the ISO 19115-1:2014 international standard.
"""

from __future__ import annotations

from abc import ABC, abstractmethod
from collections.abc import Sequence
from datetime import datetime
from typing import Optional

import opengis.geometry.primitive as primitive
import opengis.metadata.citation as meta_citation
import opengis.referencing.crs as crs


__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"


class VerticalExtent(ABC):
    """Vertical domain of resource."""

    @property
    @abstractmethod
    def minimum_value(self) -> float:
        """Lowest vertical extent contained in the resource."""

    @property
    @abstractmethod
    def maximum_value(self) -> float:
        """Highest vertical extent contained in the resource."""

    @property
    @abstractmethod
    def vertical_crs(self) -> Optional[crs.VerticalCRS]:
        """
        Provides information about the vertical coordinate reference system
        to which the maximum and minimum elevation values are measured.

        Identifies the vertical coordinate reference system used for the
        minimum and maximum values.

        NOTE: The CRS information includes unit of measure.

        MANDATORY:
            If vertical_crs_id is `None`.
        """

    @property
    @abstractmethod
    def vertical_crs_id(self) -> Optional[crs.ReferenceSystem]:
        """
        Identifies the vertical coordinate reference system used for the
        minimum and maximum values.

        MANDATORY:
            If vertical_crs is `None`.
        """


class GeographicExtent(ABC):
    """Spatial area of the resource."""

    @property
    @abstractmethod
    def extent_type_code(self) -> bool:
        """
        Indication of whether the geographic element encompasses an area
        covered by the data or an area where data is not present.

        Default: `True`

        Domain:
            `False` = 0 = exclusion
            `True`  = 1 = inclusion
        """


class BoundingPolygon(GeographicExtent):
    """
    Encosing geometric onject which locates the resource, expressed as a set
    of (x,y) coordinate(s).

    NOTE 1: If a polygon is used it should be closed (i.e. the last point
    replicates the first point).

    NOTE 2: This type can be used to represent geometries other than polygons,
    e.g., points, lines.
    """

    @property
    @abstractmethod
    def polygon(self) -> Sequence[primitive.Geometry]:
        """
        Sets of points defining the bounding polygon or any other `Geometry`
        object (point, line, or polygon).
        """


class GeographicBoundingBox(GeographicExtent):
    """
    Geographic position of the resource.

    NOTE: This is only an approximate reference so specifying the coordinate
    reference system is unnecessary and need only be provided with a precision
    of up to two decimal places.
    """

    @property
    @abstractmethod
    def west_bound_longitude(self) -> float:
        """
        Western-most coordinate of the limit of the resource extent, expressed
        in longitude in decimal degrees (positive east).

        Domain: -180.0 <= West Bounding Logitude Value <= 180.0
        """

    @property
    @abstractmethod
    def east_bound_longitude(self) -> float:
        """
        Eastern-most coordinate of the limit of the resource extent, expressed
        in longitude in decimal degrees (positive east).

        Domain: -180.0 <= East Bounding Logitude Value <= 180.0
        """

    @property
    @abstractmethod
    def south_bound_latitude(self) -> float:
        """
        Southern-most coordinate of the limit of the resource extent,
        expressed in latitude in decimal degrees (positive north).

        Domain: -90.0 <= South Bounding Latitude Value <= 90.0;
            South Bouding Latitude Value <= North Bounding Latitude Value
        """

    @property
    @abstractmethod
    def north_bound_latitude(self) -> float:
        """
        Northern-most, coordinate of the limit of the resource extent
        expressed in latitude in decimal degrees (positive north).

        Domain: -90.0 <= North Bounding Latitude Value <= 90.0;
            North Bouding Latitude Value >= South Bounding Latitude Value
        """


class GeographicDescription(GeographicExtent):
    """Description of the geographic area using identifiers."""

    @property
    @abstractmethod
    def geographic_identifier(self) -> meta_citation.Identifier:
        """
        Identifier used to represent a geographic area.

        NOTE: a geographic identifier as described in ISO 19112.
        """


class TemporalExtent(ABC):
    """Time period covered by the content of the resource."""

    @property
    @abstractmethod
    def extent(self) -> tuple[datetime, datetime]:
        """
        Period for the content of the resource.

        Returns a tuple with the first component being the beginning `datetime`
        of the temporal period and the second component being the end
        `datetime`.
        """


class SpatialTemporalExtent(TemporalExtent):
    """Extent with respect to date/time and spatial boundaries."""

    @property
    @abstractmethod
    def vertical_extent(self) -> VerticalExtent:
        """Vertical extent component."""

    @property
    @abstractmethod
    def spatial_extent(self) -> Sequence[GeographicExtent]:
        """
        Spatial extent component of a composite spatial and temporal extent.
        """


class Extent(ABC):
    """Extent of the resource."""

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """
        Extent of the referring object.

        Sets of points defining the bounding polygon or any other
        geometry (point, line or polygon).

        MANDATORY:
            If `geographic_element`, `temproal_element`,
            and `vertical_element` are `None`.
        """

    @property
    @abstractmethod
    def geographic_element(self) -> Optional[Sequence[GeographicExtent]]:
        """
        Provides spatial component of the extent of the referring object.

        MANDATORY:
            If `description`, `temproal_element`,
            and `vertical_element` are `None`.
        """

    @property
    @abstractmethod
    def temporal_element(self) -> Optional[Sequence[TemporalExtent]]:
        """
        Provides temporal component of the extent of the referring object.

        MANDATORY:
            If `description`, `geographic_element`,
            and `vertical_element` are `None`.
        """

    @property
    @abstractmethod
    def vertical_element(self) -> Optional[Sequence[VerticalExtent]]:
        """
        Provides vertical component of the extent of the referring object.

        MANDATORY:
            If `description`, `geographic_element`,
            and `temporal_element` are `None`.
        """
