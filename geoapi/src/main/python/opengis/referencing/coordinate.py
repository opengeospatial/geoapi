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
"""This is the `coordinates` module.

This module contains geographic metadata structures derived from the
Coordinates package in the ISO 19111:2019 international standard.
"""

__author__ = "OGC Topic 2 (for abstract model and documentation), " +\
    "David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence
from typing import Optional

from opengis.geometry.primitive import DirectPosition
from opengis.referencing.crs import CoordinateReferenceSystem
from opengis.util.measure import Measure


class DataEpoch(ABC):
    """
    Time attribute of a coordinate set that is referenced to a dynamic CRS.
    """

    @property
    @abstractmethod
    def coordinate_epoch(self) -> Measure:
        """
        The date at which coordinates are referenced to a dynamic coordinate
        reference system, expressed as a decimal year in the Gregorian
        calendar.

        Example:
            2017-03-25 in the Gregorian calendar is epoch 2017.23.
        """


class CoordinateSet(ABC):
    """
    Description of the coordinate tuples in a coordinate set. A single
    coordinate tuple is treated as a special case of coordinate set containing
    only one member.
    """

    @property
    @abstractmethod
    def coordinate_tuple(self) -> Sequence[DirectPosition]:
        """Position described by a coordinate tuple."""


class CoordinateMetadata(ABC):
    """
    Metadata required to reference coordinates.
    """

    @property
    @abstractmethod
    def coordinate_referencea_system(self) -> CoordinateReferenceSystem:
        """
        Identifier of the coordinate reference system to which a coordinate
        set is referenced.
        """

    @property
    @abstractmethod
    def coordinate_epoch(self) -> Optional[DataEpoch]:
        """
        Epoch at which coordinate referenced to a dynamic CRS are valid.

        MANDATORY:
            If the coordinate reference system is dynamic.
        """
