#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from typing import Sequence

class GeographicExtent(ABC):
    """Spatial area of the resource."""

    @property
    def extentTypeCode(self):
        """Indication of whether the geographic element encompasses an area covered by the data or an area where data is not present."""
        return None

class GeographicBoundingBox(GeographicExtent):
    """Geographic position of the resource. NOTE: This is only an approximate reference so specifying the coordinate reference system is unnecessary and need only be provided with a precision of up to two decimal places."""

    @abstractproperty
    def westBoundLongitude(self) -> float:
        """Western-most coordinate of the limit of the resource extent, expressed in longitude in decimal degrees (positive east)."""
        pass

    @abstractproperty
    def eastBoundLongitude(self) -> float:
        """Eastern-most coordinate of the limit of the resource extent, expressed in longitude in decimal degrees (positive east)."""
        pass

    @abstractproperty
    def southBoundLatitude(self) -> float:
        """Southern-most coordinate of the limit of the resource extent, expressed in latitude in decimal degrees (positive north)."""
        pass

    @abstractproperty
    def northBoundLatitude(self) -> float:
        """Northern-most, coordinate of the limit of the resource extent expressed in latitude in decimal degrees (positive north)."""
        pass

class GeographicDescription(GeographicExtent):
    """Description of the geographic area using identifiers."""

    @abstractproperty
    def geographicIdentifier(self) -> Identifier:
        """Identifier used to represent a geographic area e.g. a geographic identifier as described in ISO 19112."""
        pass

class BoundingPolygon(GeographicExtent):
    """Enclosing geometric object which locates the resource, expressed as a set of (x,y) coordinate (s). NOTE: If a polygon is used it should be closed (last point replicates first point)."""

    @abstractproperty
    def polygon(self) -> Sequence['Object']:
        """Sets of points defining the bounding polygon or any other GM_Object geometry (point, line or polygon)."""
        pass

class VerticalExtent(ABC):
    """Vertical domain of resource."""

    @abstractproperty
    def minimumValue(self) -> float:
        """Lowest vertical extent contained in the resource."""
        pass

    @abstractproperty
    def maximumValue(self) -> float:
        """Highest vertical extent contained in the resource."""
        pass

    @property
    def verticalCRS(self) -> 'VerticalCRS':
        return None

class TemporalExtent(ABC):
    """Time period covered by the content of the resource."""

    @abstractproperty
    def extent(self) -> Primitive:
        """Period for the content of the resource."""
        pass

class SpatialTemporalExtent(TemporalExtent):
    """Extent with respect to date/time and spatial boundaries."""

    @property
    def verticalExtent(self) -> VerticalExtent:
        """Vertical extent component."""
        return None

    @abstractproperty
    def spatialExtent(self) -> Sequence[GeographicExtent]:
        pass

class Extent(ABC):
    """Extent of the resource."""

    @property
    def description(self) -> str:
        """Sets of points defining the bounding polygon or any other GM_Object geometry (point, line or polygon)."""
        return None

    @property
    def geographicElement(self) -> Sequence[GeographicExtent]:
        return None

    @property
    def temporalElement(self) -> Sequence[TemporalExtent]:
        return None

    @property
    def verticalElement(self) -> Sequence[VerticalExtent]:
        return None
