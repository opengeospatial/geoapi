#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
#    http://www.geoapi.org
#

from abc import ABC, abstractmethod

from opengis.referencing.crs import CoordinateReferenceSystem


class DirectPosition(ABC):
    """Holds the coordinates for a position within some coordinate reference system."""

    @property
    @abstractmethod
    def coordinate_reference_system(self) -> CoordinateReferenceSystem:
        """The coordinate reference system in which the coordinate tuple is given."""
        pass

    @property
    @abstractmethod
    def dimension(self) -> int:
        """The length of coordinate sequence (the number of entries)."""
        pass
