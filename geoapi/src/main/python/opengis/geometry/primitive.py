#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
