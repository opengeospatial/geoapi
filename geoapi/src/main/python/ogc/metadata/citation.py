#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty

class Citation(ABC):
    """Standardized resource reference."""

    @abstractproperty
    def title(self) -> str:
        """Name by which the cited resource is known."""
        return NotImplementedError
