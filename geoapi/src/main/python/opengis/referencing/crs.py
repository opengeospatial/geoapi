from abc import property, abstractmethod

from opengis.referencing.cs \
    import IdentifiedObject, CoordinateSystem, VerticalCS, TimeCS, AffineCS, CartesianCS, EllipsoidalCS
from opengis.metadata.datum \
    import Datum, VerticalDatum, TemporalDatum, ImageDatum, EngineeringDatum, GeodeticDatum
from opengis.metadata.extent import Extent


class ReferenceSystem(IdentifiedObject):
    """
    Description of a spatial and temporal reference system used by a dataset.
    """

    @property
    def domain_of_validity(self) -> Extent:
        """
        Area or region or timeframe in which this (coordinate) reference system is valid.
        """
        return None

    @property
    def scope(self) -> str:
        """
        Description of domain of usage, or limitations of usage, for which this Reference System object is valid.
        """
        return None


class CoordinateReferenceSystem(ReferenceSystem):
    """
    Abstract coordinate reference system, usually defined by a coordinate system and a datum.
    """


class CompoundCRS(CoordinateReferenceSystem):
    """
    A coordinate reference system describing the position of points through two or more independent coordinate
    reference systems.
    """

    @property
    @abstractmethod
    def components(self) -> list:
        """
        The ordered list of coordinate reference systems.
        """
        pass


class SingleCRS(CoordinateReferenceSystem):
    """
    Abstract coordinate reference system, consisting of a single Coordinate System and a single Datum.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> CoordinateSystem:
        pass

    @property
    def datum(self) -> Datum:
        return None


class VerticalCRS(SingleCRS):
    """
    A 1D coordinate reference system used for recording heights or depths.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> VerticalCS:
        pass

    @property
    @abstractmethod
    def datum(self) -> VerticalDatum:
        pass


class TemporalCRS(SingleCRS):
    """
    A 1D coordinate reference system used for the recording of time.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> TimeCS:
        pass

    @property
    @abstractmethod
    def datum(self) -> TemporalDatum:
        pass


class ImageCRS(SingleCRS):
    """
    An engineering coordinate reference system applied to locations in images.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> AffineCS:
        pass

    @property
    @abstractmethod
    def datum(self) -> ImageDatum:
        pass


class EngineeringCRS(SingleCRS):
    """
    A contextually local coordinate reference system.
    """

    @property
    @abstractmethod
    def datum(self) -> EngineeringDatum:
        pass


# from org.opengis.referencing.operation import Conversion
# TODO: conversion_from_base(self) -> Conversion (mandatory)

class GeneralDerivedCRS(SingleCRS):
    """
    A coordinate reference system that is defined by its coordinate conversion from another coordinate reference system
    (not by a datum).
    """

    @property
    @abstractmethod
    def base_crs(self) -> CoordinateReferenceSystem:
        pass


class GeodeticCRS(SingleCRS):
    """
    A coordinate reference system associated with a geodetic datum.
    """

    @property
    @abstractmethod
    def datum(self) -> GeodeticDatum:
        pass


class DerivedCRS(GeneralDerivedCRS):
    """
    A coordinate reference system that is defined by its coordinate conversion from another coordinate reference system
    but is not a projected coordinate reference system.
    """


class GeographicCRS(GeodeticCRS):
    """
    A coordinate reference system based on an ellipsoidal approximation of the geoid; this provides an accurate
    representation of the geometry of geographic features for a large portion of the earth's surface.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> EllipsoidalCS:
        pass


# from org.opengis.referencing.operation import Projection
# TODO: conversion_from_base(self) -> Projection
class ProjectedCRS(GeneralDerivedCRS):
    """
    A 2D coordinate reference system used to approximate the shape of the earth on a planar surface.
    """

    @property
    @abstractmethod
    def base_crs(self) -> GeographicCRS:
        pass

    @property
    @abstractmethod
    def coordinate_system(self) -> CartesianCS:
        pass

    @property
    @abstractmethod
    def datum(self) -> GeodeticDatum:
        pass
