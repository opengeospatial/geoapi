#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
#    http://www.geoapi.org
#

from abc import abstractmethod
from typing import Sequence

from opengis.referencing.cs \
    import CoordinateSystem, VerticalCS, TimeCS, AffineCS, CartesianCS, EllipsoidalCS
from opengis.referencing.datum \
    import IdentifiedObject, Datum, VerticalDatum, TemporalDatum, EngineeringDatum, GeodeticDatum
from opengis.metadata.extent import Extent


class ReferenceSystem(IdentifiedObject):
    """
    Description of a spatial and temporal reference system used by a dataset.
    """

    @property
    def domain_of_validity(self) -> Extent:
        """
        Area or region or timeframe in which this (coordinate) reference system is valid.

        :return: The reference system valid domain, or null if not available.
        :rtype: Extent
        """
        return None

    @property
    def scope(self) -> str:
        """
        Description of domain of usage, or limitations of usage, for which this Reference System object is valid.

        :return: The domain of usage, or null if none.
        :rtype: str
        """
        return None


class CoordinateReferenceSystem(ReferenceSystem):
    """
    Abstract coordinate reference system, usually defined by a coordinate system and a datum.
    """


class SingleCRS(CoordinateReferenceSystem):
    """
    Abstract coordinate reference system, consisting of a single Coordinate System and a single Datum.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> CoordinateSystem:
        """
        Returns the coordinate system.

        :return: The coordinate system.
        :rtype: CoordinateSystem
        """
        pass

    @property
    @abstractmethod
    def datum(self) -> Datum:
        """
        Returns the datum.

        :return: The datum
        :rtype: Datum
        """
        return None


class CompoundCRS(CoordinateReferenceSystem):
    """
    A coordinate reference system describing the position of points through two or more independent coordinate
    reference systems.
    """

    @property
    @abstractmethod
    def components(self) -> Sequence[SingleCRS]:
        """
        The ordered list of coordinate reference systems.

        :return: The ordered list of coordinate reference systems.
        :rtype: Sequence[SingleCRS]
        """
        pass


class VerticalCRS(SingleCRS):
    """
    A 1D coordinate reference system used for recording heights or depths.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> VerticalCS:
        """
        Returns the coordinate system, which must be vertical.

        :return: The coordinate system.
        :rtype: VerticalCS
        """
        pass

    @property
    @abstractmethod
    def datum(self) -> VerticalDatum:
        """
        Returns the datum, which must be vertical.

        :return: The datum
        :rtype: VerticalDatum
        """
        pass


class TemporalCRS(SingleCRS):
    """
    A 1D coordinate reference system used for the recording of time.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> TimeCS:
        """
        Returns the coordinate system, which must be temporal.

        :return: The coordinate system.
        :rtype: TimeCS
        """
        pass

    @property
    @abstractmethod
    def datum(self) -> TemporalDatum:
        """
        Returns the datum, which must be temporal.

        :return: The datum
        :rtype: TemporalDatum
        """
        pass


class EngineeringCRS(SingleCRS):
    """
    A contextually local coordinate reference system.
    """

    @property
    @abstractmethod
    def datum(self) -> EngineeringDatum:
        """
        Returns the datum, which must be an engineering one.

        :return: The datum
        :rtype: EngineeringDatum
        """
        pass


class DerivedCRS(SingleCRS):
    """
    A coordinate reference system that is defined by its coordinate conversion from another coordinate reference system
    (not by a datum).
    """

    @property
    @abstractmethod
    def base_crs(self) -> CoordinateReferenceSystem:
        """
        Returns the base coordinate reference system.

        :return: The base coordinate reference system.
        :rtype: CoordinateReferenceSystem
        """
        pass

    @property
    @abstractmethod
    def conversion_from_base(self):
        """
        Returns the conversion from the base CRS to this CRS.

        :return: The conversion from the base CRS.
        :rtype: Conversion
        """
        pass


class GeodeticCRS(SingleCRS):
    """
    A coordinate reference system associated with a geodetic reference frame.
    """

    @property
    @abstractmethod
    def datum(self) -> GeodeticDatum:
        """
        Returns the datum, which must be geodetic.

        :return: The datum.
        :rtype: GeodeticDatum
        """
        pass


class GeographicCRS(GeodeticCRS):
    """
    A coordinate reference system based on an ellipsoidal approximation of the geoid; this provides an accurate
    representation of the geometry of geographic features for a large portion of the earth's surface.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> EllipsoidalCS:
        """
        Returns the coordinate system, which must be ellipsoidal.

        :return: The coordinate system.
        :rtype: EllipsoidalCS
        """
        pass


class ProjectedCRS(DerivedCRS):
    """
    A 2D coordinate reference system used to approximate the shape of the earth on a planar surface.
    """

    @property
    @abstractmethod
    def conversion_from_base(self):
        """
        Returns the map projection from the base CRS to this CRS.

        :return: The conversion from the base CRS.
        :rtype: Conversion
        """
        pass

    @property
    @abstractmethod
    def base_crs(self) -> GeographicCRS:
        """
        Returns the base coordinate reference system, which must be geographic.

        :return: The base coordinate reference system.
        :rtype: GeographicCRS
        """
        pass

    @property
    @abstractmethod
    def coordinate_system(self) -> CartesianCS:
        """
        Returns the coordinate system, which must be cartesian.

        :return: The coordinate system.
        :rtype: CartesianCS
        """
        pass

    @property
    @abstractmethod
    def datum(self) -> GeodeticDatum:
        """
        Returns the datum.

        :return: The datum.
        :rtype: GeodeticDatum
        """
        pass
