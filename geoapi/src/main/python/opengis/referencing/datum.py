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
"""This is the `datum` module.

This module contains geographic metadata structures regarding datums derived
from the ISO 19111 international standard.
"""

__author__ = "OGC Topic 2 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"

from abc import abstractmethod
from datetime import datetime
from enum import Enum

from opengis.metadata.extent import Extent
from opengis.referencing.common import IdentifiedObject


class RealizationMethod(Enum):
    """
    Specification of the method by which the vertical reference frame is
    realized.
    """
    LEVELLING = "levelling"
    """
    The realization is by adjustment of a levelling network fixed to one or
    more tide gauges.
    """

    GEOID = "geoid"
    """
    The realization is through a geoid height model or a height correction
    model. This is applied to a specified geodetic CRS.
    """

    TIDAL = "tidal"
    """The realization is through a tidal model or by tidal predictions."""


class Datum(IdentifiedObject):
    """
    Specifies the relationship of a coordinate system to the earth, thus
    creating a coordinate reference system.
    """

    @property
    @abstractmethod
    def anchor_point(self) -> str | None:
        """
        Description, possibly including coordinates, of the point or points
        used to anchor the datum to the Earth.
        """

    @property
    @abstractmethod
    def domain_of_validity(self) -> Extent:
        """
        Information about spatial, vertical, and temporal extent. This
        interface has four optional attributes (geographic elements, temporal
        elements, and vertical elements) and an element called description.
        At least one of the four shall be used.
        """

    @property
    @abstractmethod
    def realization_epoch(self) -> datetime | None:
        """
        The time after which this datum definition is valid.
        """

    @property
    @abstractmethod
    def scope(self) -> str:
        """
        Description of domain of usage, or limitations of usage, for which
        this datum object is valid.
        """


class TemporalDatum(Datum):
    """
    A temporal datum defines the origin of a temporal coordinate reference
    system.
    """

    @property
    @abstractmethod
    def anchor_point(self) -> None:
        """
        This attribute is defined in the Datum parent interface, but is not
        used by a temporal datum.
        """

    @property
    @abstractmethod
    def realization_epoch(self) -> None:
        """
        This attribute is defined in the Datum parent interface, but is not
        used by a temporal datum.
        """

    @property
    @abstractmethod
    def origin(self) -> datetime:
        """
        The date and time origin of this temporal datum.
        """


class VerticalDatum(Datum):
    """
    The method through which this vertical reference frame is realized.
    """

    @property
    @abstractmethod
    def realization_method(self) -> RealizationMethod:
        """
        The type of this vertical datum.
        """


class Ellipsoid(IdentifiedObject):
    """
    Geometric figure that can be used to describe the approximate shape of the
    Earth.
    """

    @property
    @abstractmethod
    def axis_unit(self):
        """
        Linear unit of the semi-major and semi-minor axis values.
        """

    @property
    @abstractmethod
    def semi_major_axis(self) -> float:
        """
        Length of the semi-major axis of the ellipsoid. This is the equatorial
        radius in axis linear unit.
        """

    @property
    @abstractmethod
    def semi_minor_axis(self) -> float:
        """
        Length of the semi-minor axis of the ellipsoid. This is the polar
        radius in axis linear unit.
        """

    @property
    @abstractmethod
    def inverse_flattering(self) -> float:
        """
        Value of the inverse of the flattening constant.
        """

    @property
    @abstractmethod
    def is_inf_definitive(self) -> bool:
        """
        Indicates if the inverse flattening is definitive for this ellipsoid.
        Some ellipsoids use the IVF as the defining value, and calculate the
        polar radius whenever asked. Other ellipsoids use the polar radius to
        calculate the IVF whenever asked. This distinction can be important to
        avoid floating-point rounding errors.
        """

    @property
    @abstractmethod
    def is_sphere(self) -> bool:
        """
        `True` if the ellipsoid is degenerate and is actually a sphere.
        The sphere is completely defined by the semi-major axis, which is the
        radius of the sphere.
        """


class PrimeMeridian(IdentifiedObject):
    """
    A prime meridian defines the origin from which longitude values are
    determined.
    """

    @property
    @abstractmethod
    def greenwich_longitude(self) -> float:
        """
        Longitude of the prime meridian measured from the Greenwich meridian,
        positive eastward.
        """

    @property
    @abstractmethod
    def angular_unit(self):
        """
        Returns the angular unit of the Greenwich longitude.
        """


class GeodeticDatum(Datum):
    """
    Defines the location and precise orientation in 3-dimensional space of a
    defined ellipsoid (or sphere) that approximates the shape of the earth.
    """

    @property
    @abstractmethod
    def ellipsoid(self) -> Ellipsoid:
        """
        Returns the ellipsoid.
        """

    @property
    @abstractmethod
    def prime_meridian(self) -> PrimeMeridian:
        """
        Returns the prime meridian.
        """


class EngineeringDatum(Datum):
    """
    Defines the origin of an engineering coordinate reference system.
    """
