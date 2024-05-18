#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
#    http://www.geoapi.org
#

from abc import ABC, abstractmethod
from enum import Enum
from datetime import datetime

from opengis.metadata.extent import Extent
from opengis.metadata.citation import Identifier



class RealizationMethod(Enum):
    """
    Specification of the method by which the vertical reference frame is realized.
    """
    LEVELLING = "levelling"
    GEOID = "geoid"
    TIDAL = "tidal"



class IdentifiedObject(ABC):
    """
    Identification and remarks information for a reference system or CRS-related object.
    """

    @property
    @abstractmethod
    def name(self) -> Identifier:
        """
        The primary name by which this object is identified.

        :return: The primary name.
        :rtype: Identifier
        """
        pass

    @property
    def remarks(self) -> str:
        """
        Comments on or information about this object, including data source information.

        :return: The remarks, or None.
        :rtype: str
        """
        return None

    @abstractmethod
    def to_wkt(self) -> str:
        """
        Returns a Well-Known Text (WKT) for this object.

        :return: The Well Know Text for this object.
        :rtype: str
        """
        pass


class Datum(IdentifiedObject):
    """
    Specifies the relationship of a coordinate system to the earth, thus creating a coordinate reference system.
    """

    @property
    def anchor_point(self) -> str:
        """
        Description, possibly including coordinates, of the point or points used to anchor the datum to the Earth.
        """
        return None

    @property
    def domain_of_validity(self) -> Extent:
        """
        Information about spatial, vertical, and temporal extent. This interface has four optional attributes
        (geographic elements, temporal elements, and vertical elements) and an element called description. At least one
        of the four shall be used.
        """
        return None

    @property
    def realization_epoch(self) -> datetime:
        """
        The time after which this datum definition is valid.
        """
        return None

    @property
    def scope(self) -> str:
        """
        Description of domain of usage, or limitations of usage, for which this datum object is valid.
        """
        return None


class TemporalDatum(Datum):
    """
    A temporal datum defines the origin of a temporal coordinate reference system.
    """

    @property
    @abstractmethod
    def anchor_point(self) -> None:
        """
        This attribute is defined in the Datum parent interface, but is not used by a temporal datum.
        """
        pass

    @property
    @abstractmethod
    def realization_epoch(self) -> None:
        """
        This attribute is defined in the Datum parent interface, but is not used by a temporal datum.
        """
        pass

    @property
    @abstractmethod
    def origin(self) -> datetime:
        """
        The date and time origin of this temporal datum.
        """
        pass


class VerticalDatum(Datum):
    """
    The method through which this vertical reference frame is realized.
    """

    @property
    def realization_method(self) -> RealizationMethod:
        """
        The type of this vertical datum.
        """
        return None


class Ellipsoid(IdentifiedObject):
    """
    Geometric figure that can be used to describe the approximate shape of the earth.
    """

    @property
    def axis_unit(self):
        """
        Linear unit of the semi-major and semi-minor axis values.
        """
        return None

    @property
    @abstractmethod
    def semi_major_axis(self) -> float:
        """
        Length of the semi-major axis of the ellipsoid. This is the equatorial radius in axis linear unit.
        """
        pass

    @property
    @abstractmethod
    def semi_minor_axis(self) -> float:
        """
        Length of the semi-minor axis of the ellipsoid. This is the polar radius in axis linear unit.
        """
        pass

    @property
    @abstractmethod
    def inverse_flattering(self) -> float:
        """
        Value of the inverse of the flattening constant.
        """
        pass

    @property
    @abstractmethod
    def is_inf_definitive(self) -> bool:
        """
        Indicates if the inverse flattening is definitive for this ellipsoid. Some ellipsoids use the IVF as the
        defining value, and calculate the polar radius whenever asked. Other ellipsoids use the polar radius to
        calculate the IVF whenever asked. This distinction can be important to avoid floating-point rounding errors.
        """
        pass

    @property
    @abstractmethod
    def is_sphere(self) -> bool:
        """
        true if the ellipsoid is degenerate and is actually a sphere.
        The sphere is completely defined by the semi-major axis, which is the radius of the sphere.
        """
        pass


class PrimeMeridian(IdentifiedObject):
    """
    A prime meridian defines the origin from which longitude values are determined.
    """

    @property
    @abstractmethod
    def greenwich_longitude(self) -> float:
        """
        Longitude of the prime meridian measured from the Greenwich meridian, positive eastward.
        """
        pass

    @property
    @abstractmethod
    def angular_unit(self):
        """
        Returns the angular unit of the Greenwich longitude.
        """
        pass


class GeodeticDatum(Datum):
    """
    Defines the location and precise orientation in 3-dimensional space of a defined ellipsoid (or sphere) that
    approximates the shape of the earth.
    """

    @property
    @abstractmethod
    def ellipsoid(self) -> Ellipsoid:
        """
        Returns the ellipsoid.
        """
        pass

    @property
    @abstractmethod
    def prime_meridian(self) -> PrimeMeridian:
        """
        Returns the prime meridian.
        """
        pass


class EngineeringDatum(Datum):
    """
    Defines the origin of an engineering coordinate reference system.
    """
