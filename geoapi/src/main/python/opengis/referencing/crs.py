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
"""This is the `crs` module.

This module contains geographic metadata structures regarding coordinate
referencing systems derived from the ISO 19111 international standard and
ISO 19115-1:2014, including adendums A1(2018) and A2(2020).
"""

from __future__ import annotations

from abc import abstractmethod
from collections.abc import Sequence
from enum import Enum
from typing import Optional

import opengis.metadata.citation as citation
import opengis.referencing.common as ref_common
import opengis.referencing.coordinate as ref_coordinate
import opengis.referencing.cs as cs
import opengis.referencing.datum as ref_datum
import opengis.referencing.operation as ref_operation


__author__ = "OGC Topic 2 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"


class ReferenceSystemTypeCode(Enum):
    """
    Defines type of reference system used.

    This class is derived from ISO 19115-1:2014, including adendums A1(2018)
    and A2(2020).
    """

    COMPOUND_ENGINEERING_PARAMETRIC = "compoundEngineeringParametric"
    """
    Compound spatio-parametric coordinate reference system containing an
    engineering coordinate reference system and a parametric reference system.

    Example:
        [local] x, y, pressure
    """

    COMPOUND_ENGINEERING_PARAMETRIC_TEMPORAL = \
        "compoundEngineeringParametricTemporal"
    """
    Compound spatio-parametric-temporal coordinate reference system containing,
    an engineering coordinate reference system, a parametric reference system,
    and a temporal coordinate reference system.

    Example:
        [local] x, y, pressure, time
    """

    COMPOUND_ENGINEERING_TEMPORAL = "compoundEngineeringTemporal"
    """
    Compound spatio-temporal coordinate reference system containing,
    an engineering coordinate reference system and a temporal coordinate
    reference system.

    Example:
        [local] x, y, time
    """

    COMPOUND_ENGINEERING_VERTICAL = "compoundEngineeringVertical"
    """
    Compound spatial coordinate reference system containing a horizontal
    engineering coordinate reference system and a vertical coordinate
    reference system.

    Example:
        [local] x, y, height
    """

    COMPOUND_ENGINEERING_VERTICAL_TEMPORAL = \
        "compoundEngineeringVerticalTemporal"
    """
    Compound spatio-temporal coordinate reference system containing an
    engineering, a vertical, and a temporal coordinate reference system.

    Example:
        [local] x, y, height, time
    """

    COMPOUND_GEOGRAPHIC2D_PARAMETRIC = "compoundGeographic2DParametric"
    """
    Compound spatio-parametric coordinate reference system containing a 2
    dimensional geographic horizontal coordinate reference system and a
    parametric reference system.

    Example:
        latitude, longitude, pressure
    """

    COMPOUND_GEOGRAPHIC2D_PARAMETRIC_TEMPORAL = \
        "compoundGeographic2DParametricTemporal"
    """
    Compound spatio-parametric-temporal coordinate reference system containing
    a 2 dimensional geographic horizontal, a parametric, and a temporal
    reference system.

    Example:
        latitude, longitude, pressure, time
    """

    COMPOUND_GEOGRAPHIC2D_TEMPORAL = "compoundGeographic2DTemporal"
    """
    Compound spatio-temporal coordinate reference system containing
    a 2 dimensional geographic horizontal coordinate reference system and a
    temporal reference system.

    Example:
        latitude, longitude, time
    """

    COMPOUND_GEOGRAPHIC2D_VERTICAL = "compoundGeographic2DVertical"
    """
    Compound coordinate reference system in which one constituent coordinate
    reference system ais a horizontal geodetic coordinate reference system and
    one is a vertical reference system.

    Example:
        latitude, longitude, [gravity-related] height or depth
    """

    COMPOUND_GEOGRAPHIC2D_VERTICAL_TEMPORAL = \
        "compoundGeographic2DVerticalTemporal"
    """
    Compound spatio-temporal coordinate reference system containing a
    2 dimensional geographic horizontal, a vertical, and a temporal coordinate
    reference system.

    Example:
        latitude, longitude, height, time
    """

    COMPOUND_GEOGRAPHIC3D_TEMPORAL = "compoundGeographic3DTemporal"
    """
    Compound spatio-temporal coordinate reference system containing a
    3 dimensional geographic and a temporal coordinate reference system.

    Example:
        latitude, longitude, ellipsoidal height, time
    """

    COMPOUND_PROJECTED2D_PARAMETRIC = "compoundProjected2DParametric"
    """
    Compound spatio-parametric coordinate reference system containing a
    projected horizontal coordinate reference system and a parametric
    reference system.

    Example:
        easting, northing, density
    """

    COMPOUND_PROJECTED2D_PARAMETRIC_TEMPORAL = \
        "compoundProjected2DParametricTemporal"
    """
    Compound spatio-parametric-temporal coordinate reference system containing
    a projected horizontal, a parametric, and a temporal coordinate reference
    system.

    Example:
        easting, northing, density, time
    """

    COMPOUND_PROJECTED_TEMPORAL = "compoundProjectedTemporal"
    """
    Compound spatial reference system containing a horizontal projected
    coordinate reference system and a vertical coordinate reference system.

    Example:
        easting, northing, [gravity-related] height or depth
    """

    COMPOUND_PROJECTED_VERTICAL = "compoundProjectedVertical"
    """
    Compound spatial reference system containing a horizontal projected
    coordinate reference system and a vertical coordinate reference system.

    Example:
        easting, northing, [gravity-related] height or depth
    """

    COMPOUND_PROJECTED_VERTICAL_TEMPORAL = "compoundProjectedVerticalTemporal"
    """
    Compound spatio-temporal coordinate reference system containing a
    projected horizontal, a vertical, and a temporal coordinate reference
    system.

    Example:
        easting, northing, height, time
    """

    ENGINEERING = "engineering"
    """
    Coordinate reference system based on an engineering datum (datum
    describing the relationship of a coordinate system to a local reference).

    Example:
        [local] x, y
    """

    ENGINEERING_DESIGN = "engineeringDesign"
    """
    Engineering coordinate reference system in which the base representation
    of a moving object is specified.

    Example:
        [local] x, y
    """

    ENGINEERING_IMAGE = "engineeringImage"
    """
    Coordinate reference system based on an image datum (engineering datum
    which defines the relationship of a coordinate system to an image).

    Example:
        row, column
    """

    GEODETIC_GEOCENTRIC = "geodeticGeocentric"
    """
    Geodetic coordinate reference system having a Cartesian 3D coordinate
    system.

    Example:
        [geocentric] X, Y, Z
    """

    GEODETIC_GEOGRAPHIC2D = "geodeticGeographic2D"
    """
    Geodetic coordinate reference system having an ellipsoidal 2D coordinate
    system.

    Example:
        latitude, longitude
    """

    GEODETIC_GEOGRAPHIC3D = "geodeticGeographic3D"
    """
    Geodetic coordinate reference system having an ellipsoidal 3D coordinate
    system.

    Example:
        latitude, longitude, ellipsoidal height
    """

    GEOGRAPHIC_IDENTIFIER = "geographicIdentifier"
    """
    Spatial reference in the form of a label or code that identifies a
    location.

    Example:
        post coade
    """

    LINEAR = "linear"
    """
    Reference system that identifies a location by reference to a segment of a
    linear geographic feature and distance along that segment from a given
    point.

    Example:
        x km along road
    """

    PARAMETRIC = "parametric"
    """
    Coordinate reference system based on a parametric datum (datum describing
    the relationship of a parametric coordinate system to an object).

    Example:
        pressure
    """

    PROJECTED = "projected"
    """
    Coordinate reference system derived from a two-dimensional geodetic
    coordinate reference system by applying a map projection.

    Example:
        easting, northing
    """

    TEMPORAL = "temporal"
    """
    Reference system against which time is measured.

    Example:
        time
    """

    VERTICAL = "vertical"
    """
    One-dimensional coordinate reference system based on a vertical datum
    (datum describing the relation of gravity-related heights or depths
    to the Earth).

    Example:
        [gravity-related] height or depth
    """


class ReferenceSystem(ref_common.IdentifiedObject):
    """
    Description of a spatial and temporal reference system used by a dataset.

    This class is derived from MD_ReferenceSystem described in the
    ISO 19115-1:2014, including addendums A1(2018) and A2(2020),
    and ISO 19115-2:2019, including addendum A1(2022), international standards.
    """
    @property
    @abstractmethod
    def reference_system_identifier(self) -> Optional[citation.Identifier]:
        """
        Identifier and codespace for reference system.

        NOTE: Refer to to SC_CRS in ISO 19111 and ISO 19111-2 when coordinate
        reference system information is not given through reference system
        identifier.

        Example:
            EPSG::4326

        MANDATORY:
            If `coordinate_reference_system` is `None`.
        """

    @property
    @abstractmethod
    def coordinate_reference_system(self) -> \
            Optional['CoordinateReferenceSystem']:
        """
        Full description of the coordinate reference system from which a set
        of coordinates is referenced.

        MANDATORY:
            If `reference_system_identifier` is `None`.
        """

    @property
    @abstractmethod
    def coordinate_epoch(self) -> Optional[ref_coordinate.DataEpoch]:
        """
        The epoch from which coordinates in a dynamic coordinate reference
        system are referenced.

        MANDATORY:
            If the coordinate reference system is dynamic.
        """

    @property
    @abstractmethod
    def reference_system_type(self) -> ReferenceSystemTypeCode:
        """
        Type of reference system used.

        Example:
            `COMPOUND_GEOGRAPHIC2D_PARAMETRIC`
            (compoundGeographic2DParametric)
        """


class CoordinateReferenceSystem(ReferenceSystem):
    """
    Abstract coordinate reference system, usually defined by a coordinate
    system and a datum.
    """


class SingleCRS(CoordinateReferenceSystem):
    """
    Abstract coordinate reference system, consisting of a single Coordinate
    System and a single Datum.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> cs.CoordinateSystem:
        """
        Returns the coordinate system.

        :return: The coordinate system.
        :rtype: CoordinateSystem
        """

    @property
    @abstractmethod
    def datum(self) -> ref_datum.Datum:
        """
        Returns the datum.

        :return: The datum
        :rtype: Datum
        """


class CompoundCRS(CoordinateReferenceSystem):
    """
    A coordinate reference system describing the position of points through
    two or more independent coordinate reference systems.
    """

    @property
    @abstractmethod
    def components(self) -> Sequence[SingleCRS]:
        """
        The ordered list of coordinate reference systems.

        :return: The ordered list of coordinate reference systems.
        :rtype: Sequence[SingleCRS]
        """


class VerticalCRS(SingleCRS):
    """
    A 1D coordinate reference system used for recording heights or depths.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> cs.VerticalCS:
        """
        Returns the coordinate system, which must be vertical.

        :return: The coordinate system.
        :rtype: VerticalCS
        """

    @property
    @abstractmethod
    def datum(self) -> ref_datum.VerticalDatum:
        """
        Returns the datum, which must be vertical.

        :return: The datum
        :rtype: VerticalDatum
        """


class TemporalCRS(SingleCRS):
    """
    A 1D coordinate reference system used for the recording of time.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> cs.TimeCS:
        """
        Returns the coordinate system, which must be temporal.

        :return: The coordinate system.
        :rtype: TimeCS
        """

    @property
    @abstractmethod
    def datum(self) -> ref_datum.TemporalDatum:
        """
        Returns the datum, which must be temporal.

        :return: The datum
        :rtype: TemporalDatum
        """


class EngineeringCRS(SingleCRS):
    """
    A contextually local coordinate reference system.
    """

    @property
    @abstractmethod
    def datum(self) -> ref_datum.EngineeringDatum:
        """
        Returns the datum, which must be an engineering one.

        :return: The datum
        :rtype: EngineeringDatum
        """


class DerivedCRS(SingleCRS):
    """
    A coordinate reference system that is defined by its coordinate conversion
    from another coordinate reference system (not by a datum).
    """

    @property
    @abstractmethod
    def base_crs(self) -> CoordinateReferenceSystem:
        """
        Returns the base coordinate reference system.

        :return: The base coordinate reference system.
        :rtype: CoordinateReferenceSystem
        """

    @property
    @abstractmethod
    def conversion_from_base(self) -> ref_operation.Conversion:
        """
        Returns the conversion from the base CRS to this CRS.

        :return: The conversion from the base CRS.
        :rtype: Conversion
        """


class GeodeticCRS(SingleCRS):
    """
    A coordinate reference system associated with a geodetic reference frame.
    """

    @property
    @abstractmethod
    def datum(self) -> ref_datum.GeodeticDatum:
        """
        Returns the datum, which must be geodetic.

        :return: The datum.
        :rtype: GeodeticDatum
        """


class GeographicCRS(GeodeticCRS):
    """
    A coordinate reference system based on an ellipsoidal approximation of the
    geoid; this provides an accurate representation of the geometry of
    geographic features for a large portion of the earth's surface.
    """

    @property
    @abstractmethod
    def coordinate_system(self) -> cs.EllipsoidalCS:
        """
        Returns the coordinate system, which must be ellipsoidal.

        :return: The coordinate system.
        :rtype: EllipsoidalCS
        """


class ProjectedCRS(DerivedCRS):
    """
    A 2D coordinate reference system used to approximate the shape of the
    Earth on a planar surface.
    """

    @property
    @abstractmethod
    def conversion_from_base(self) -> ref_operation.Conversion:
        """
        Returns the map projection from the base CRS to this CRS.

        :return: The conversion from the base CRS.
        :rtype: Conversion
        """

    @property
    @abstractmethod
    def base_crs(self) -> GeographicCRS:
        """
        Returns the base coordinate reference system, which must be geographic.

        :return: The base coordinate reference system.
        :rtype: GeographicCRS
        """

    @property
    @abstractmethod
    def coordinate_system(self) -> cs.CartesianCS:
        """
        Returns the coordinate system, which must be cartesian.

        :return: The coordinate system.
        :rtype: CartesianCS
        """

    @property
    @abstractmethod
    def datum(self) -> ref_datum.GeodeticDatum:
        """
        Returns the datum.

        :return: The datum.
        :rtype: GeodeticDatum
        """
