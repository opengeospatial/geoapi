#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractmethod
from typing import Sequence

import numpy as np

from opengis.referencing.cs import IdentifiedObject
from opengis.referencing.crs import CoordinateReferenceSystem
from opengis.metadata.quality import PositionalAccuracy
from opengis.metadata.extent import Extent
from opengis.metadata.citation import Citation
from opengis.geometry.primitive import DirectPosition


# TODO :
# from opengis.parameter import ParameterValueGroup, ParameterDescriptorGroup


class MathTransform(ABC):
    """
    Transforms multi-dimensional coordinate points.
    """

    @property
    @abstractmethod
    def source_dimensions(self) -> int:
        """
        Gets the dimension of input points.

        :return: The dimension of input points.
        :rtype: int
        """
        pass

    @property
    @abstractmethod
    def target_dimensions(self) -> int:
        """
        Gets the dimension of output points.

        :return: The dimension of output points.
        :rtype: int
        """
        pass

    @property
    @abstractmethod
    def is_identity(self) -> bool:
        """
        Tests whether this transform does not move any points.

        :return: True if this MathTransform is an identity transform; false otherwise.
        :rtype: bool
        """
        pass

    @property
    @abstractmethod
    def to_wkt(self) -> str:
        """
        Returns a Well Known Text (WKT) for this object. Well know text are defined in extended Backus Naur form.
        This operation may fails if an object is too complex for the WKT format capability.

        :return: The Well Known Text (WKT) for this object.
        :rtype: str
        """
        pass

    @property
    @abstractmethod
    def inverse(self):
        """
        Creates the inverse transform of this object. This method may fail if the transform is not one to one.

        :return: The inverse transform.
        :rtype: MathTransform
        """
        pass

    def transform(self, pt_src: DirectPosition, pt_dst: DirectPosition) -> DirectPosition:
        """
        Transforms the specified ptSrc and stores the result in ptDst.

        :param pt_src: the specified coordinate point to be transformed.
        :type pt_src: DirectPosition
        :param pt_dst: the specified coordinate point that stores the result of transforming ptSrc, or null.
        :type pt_dst: DirectPosition
        :return: the coordinate point after transforming ptSrc and storing the result in ptDst,
                 or a newly created point if ptDst was null.
        :rtype: DirectPosition
        """
        pass

    def transform_list(self, src_pts: np.ndarray, src_off: int, dst_pts: np.ndarray, dst_off: int, num_pts: int):
        """
        Transforms a list of coordinate point ordinal values. This method is provided for efficiently transforming many
        points. The supplied array of ordinal values will contain packed ordinal values. For example, if the source
        dimension is 3, then the ordinals will be packed in this order: (x0,y0,z0, x1,y1,z1 ...).

        :param src_pts: the array containing the source point coordinates.
        :type src_pts: numpy.ndarray
        :param src_off: the offset to the first point to be transformed in the source array.
        :type src_off: int
        :param dst_pts: the array into which the transformed point coordinates are returned. May be the same than srcPts
        :type dst_pts: numpy.ndarray
        :param dst_off: the offset to the location of the first transformed point that is stored in the destination
        :type dst_off: int
        :param num_pts: the number of point objects to be transformed.
        :type num_pts: int
        """
        pass

    def derivative(self, point: DirectPosition) -> np.ndarray:
        """
        Gets the derivative of this transform at a point. The derivative is the matrix of the non-translating portion
        of the approximate affine map at the point. The matrix will have dimensions corresponding to the source and
        target coordinate systems.

        :param point: The coordinate point where to evaluate the derivative.
                      Null value is accepted only if the derivative is the same everywhere (e.g. with affine transforms).
                      But most map projection will requires a non-null value.
        :type point: DirectPosition
        :return: The derivative at the specified point (never null).
        :rtype: numpy.ndarray
        """
        pass


class MathTransform1D(MathTransform):
    """
    Transforms one-dimensional coordinate points.
    """

    @property
    @abstractmethod
    def inverse(self):
        """
        Creates the inverse transform of this object.

        :return: The inverse transform.
        :rtype: MathTransform1D
        """
        pass

    def transform_value(self, value: float) -> float:
        """
        Transforms the specified value.

        :param value: The value to transform.
        :type value: float
        :return: the transformed value.
        :rtype: float
        """
        pass

    def derivative(self, value: float) -> float:
        """
        Gets the derivative of this function at a value. The derivative is the 1×1 matrix of the non-translating portion
        of the approximate affine map at the value.

        :param value: The value where to evaluate the derivative.
        :type value: float
        :return: The derivative at the specified point.
        :rtype: float
        """
        pass


class Formula:
    """
    Specification of the coordinate operation method formula.
    """

    @property
    def formula(self) -> str:
        """
        Formula(s) or procedure used by the operation method.

        :return: The formula used by the operation method, or null if none.
        :rtype: str
        """
        return None

    @property
    def citation(self) -> Citation:
        """
        Reference to a publication giving the formula(s) or procedure used by the coordinate operation method.

        :return: Reference to a publication giving the formula, or null if none.
        :rtype: Citation
        """
        return None


class OperationMethod(IdentifiedObject):
    """
    Definition of an algorithm used to perform a coordinate operation.
    """

    @property
    @abstractmethod
    def formula(self) -> Formula:
        """
        Formula(s) or procedure used by this operation method. This may be a reference to a publication.
        Note that the operation method may not be analytic, in which case this attribute references or
        contains the procedure, not an analytic formula.

        :return: The formula used by this method.
        :rtype: Formula
        """
        pass

    @property
    @abstractmethod
    def parameters(self):
        """
        The set of parameters.

        :return: The parameters, or an empty group if none.
        :rtype: ParameterDescriptorGroup
        """
        pass


class CoordinateOperation(IdentifiedObject):
    """
    A mathematical operation on coordinates that transforms or converts coordinates to another coordinate reference
    system.
    """

    @property
    def source_crs(self) -> CoordinateReferenceSystem:
        """
        Returns the source CRS. The source CRS is mandatory for transformations only.
        Conversions may have a source CRS that is not specified here, but through
        ``GeneralDerivedCRS.getBaseCRS()`` instead.

        :return: The source CRS, or null if not available.
        :rtype: CoordinateReferenceSystem
        """
        return None

    @property
    def target_crs(self) -> CoordinateReferenceSystem:
        """
        Returns the target CRS. The target CRS is mandatory for transformations only.
        Conversions may have a target CRS that is not specified here, but through
        ``GeneralDerivedCRS`` instead.

        :return: The target CRS, or null if not available.
        :rtype: CoordinateReferenceSystem
        """
        return None

    @property
    def operation_version(self) -> str:
        """
        Version of the coordinate transformation (i.e., instantiation due to the stochastic nature of the parameters).
        Mandatory when describing a transformation, and should not be supplied for a conversion.

        :return: The coordinate operation version, or null in none.
        :rtype: str
        """
        return None

    @property
    def coordinate_operation_accuracy(self) -> Sequence[PositionalAccuracy]:
        """
        Estimate(s) of the impact of this operation on point accuracy. Gives position error estimates for target
        coordinates of this coordinate operation, assuming no errors in source coordinates.

        :return: The position error estimates, or an empty collection if not available.
        :rtype: Sequence[PositionalAccuracy]
        """
        return None

    @property
    def domain_of_validity(self) -> Extent:
        """
        Area or region or timeframe in which this coordinate operation is valid.

        :return: The coordinate operation valid domain, or null if not available.
        :rtype: Extent
        """
        return None

    @property
    @abstractmethod
    def scope(self) -> str:
        """
        Description of domain of usage, or limitations of usage, for which this operation is valid.

        :return: A description of domain of usage.
        :rtype: str
        """
        pass

    @property
    def math_transform(self) -> MathTransform:
        """
        Gets the math transform. The math transform will transform positions in the source coordinate reference system
        into positions in the target coordinate reference system. It may be null in the case of defining conversions.

        :return: The transform from source to target CRS, or null if not applicable.
        :rtype: MathTransform
        """
        return None


class SingleOperation(CoordinateOperation):
    """
    A parameterized mathematical operation on coordinates that transforms or converts coordinates to another coordinate
    reference system.
    """

    @property
    @abstractmethod
    def method(self) -> OperationMethod:
        """
        Returns the operation method.

        :return: The operation method.
        :rtype: OperationMethod
        """
        pass

    @property
    @abstractmethod
    def parameter_values(self):
        """
        Returns the parameter values.

        :return: The parameters values.
        :rtype: ParameterValueGroup
        """
        pass


class PassThroughOperation(SingleOperation):
    """
    A pass-through operation specifies that a subset of a coordinate tuple is subject to a specific coordinate
    operation.
    """

    @property
    @abstractmethod
    def operation(self) -> SingleOperation:
        """
        Returns the operation to apply on the subset of a coordinate tuple.

        :return: The operation to apply on the subset of a coordinate tuple.
        :rtype: SingleOperation
        """
        pass

    @property
    @abstractmethod
    def modified_coordinates(self) -> np.ndarray:
        """
        Ordered sequence of positive integers defining the positions in a coordinate tuple of the coordinates affected
        by this pass-through operation.

        :return: The modified coordinates.
        :rtype: numpy.ndarray
        """
        pass


class Transformation(SingleOperation):
    """
    An operation on coordinates that usually includes a change of Datum.
    """

    @property
    @abstractmethod
    def source_crs(self) -> CoordinateReferenceSystem:
        """
        Returns the source CRS

        :return: The source CRS (never null).
        :rtype: CoordinateReferenceSystem
        """
        pass

    @property
    @abstractmethod
    def target_crs(self) -> CoordinateReferenceSystem:
        """
        Returns the target CRS.

        :return: The target CRS (never null)
        :rtype: CoordinateReferenceSystem
        """
        pass

    @property
    @abstractmethod
    def operation_version(self) -> str:
        """
        Version of the coordinate transformation (i.e., instantiation due to the stochastic nature of the parameters).
        This attribute is mandatory in a Transformation.

        :return: The coordinate operation version.
        :rtype: str
        """
        pass


class Conversion(SingleOperation):
    """
    An operation on coordinates that does not include any change of Datum.
    """

    @property
    def source_crs(self) -> CoordinateReferenceSystem:
        """
        Returns the source CRS. Conversions may have a source CRS that is not specified here, but through
        ``GeneralDerivedCRS.getBaseCRS()`` instead.

        :return: The source CRS, or null if not available.
        :rtype: CoordinateReferenceSystem
        """
        return None

    @property
    def target_crs(self) -> CoordinateReferenceSystem:
        """
        Returns the target CRS. Conversions may have a target CRS that is not specified here, but through
        ``GeneralDerivedCRS`` instead.

        :return: The target CRS, or null if not available.
        :rtype: CoordinateReferenceSystem
        """
        return None

    @property
    @abstractmethod
    def operation_version(self) -> None:
        """
        This attribute is declared in ``CoordinateOperation`` but is not used in a conversion.
        """
        pass
