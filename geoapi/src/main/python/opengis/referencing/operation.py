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
"""This is the `operation` module.

This module contains geographic metadata structures regarding referencing
system operations derived from the ISO 19111 international standard.
"""

from __future__ import annotations

from abc import ABC, abstractmethod
from collections.abc import Sequence
from typing import Any, Optional

import numpy as np

import opengis.geometry.primitive as primitive
import opengis.metadata.citation as meta_citation
import opengis.metadata.extent as meta_extent
import opengis.metadata.quality as quality
import opengis.referencing.common as ref_common
import opengis.referencing.crs as crs

# TODO :
# from opengis.parameter import ParameterValueGroup, ParameterDescriptorGroup


__author__ = "OGC Topic 2 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"


class MathTransform(ABC):
    """
    Transforms multi-dimensional coordinate points.
    """

    @property
    @abstractmethod
    def source_dimensions(self) -> int:
        """
        Returns the dimension of input points.
        """

    @property
    @abstractmethod
    def target_dimensions(self) -> int:
        """
        Returns the dimension of output points.
        """

    @property
    @abstractmethod
    def is_identity(self) -> bool:
        """
        Tests whether this transform does not move any points. True if this
        MathTransform is an identity transform; false otherwise.
        """

    @abstractmethod
    def to_wkt(self) -> str:
        """
        Returns a Well Known Text (WKT) for this object. Well know text are
        defined in extended Backus Naur form. This operation may fails if an
        object is too complex for the WKT format capability.
        """

    @abstractmethod
    def inverse(self) -> Any:
        """
        Creates the inverse transform of this object. This method may fail if
        the transform is not one to one. Should return a
        `MathTransform` object.
        """

    @abstractmethod
    def transform(self, pt_src: primitive.DirectPosition,
                  pt_dst: Optional[primitive.DirectPosition]) ->\
            primitive.DirectPosition | np.ndarray:
        """
        Transforms the specified pt_src and stores the result in pt_dst.

        Arguments:
            pt_src (DirectPosition): the specified coordinate point to
                be transformed.
            pt_dst (Optional[DirectPosition]): the specified coordinate point
                that stores the result of transforming ptSrc, or null.
        Returns:
            The coordinate point or an array of coordinate points
                after transforming pt_src and storing the result in pt_dst,
                or a newly created point if pt_dst was null.
        """

    @abstractmethod
    def transform_list(
        self,
        src_pts: np.ndarray,
        src_off: int,
        dst_pts: np.ndarray,
        dst_off: int,
        num_pts: int,
    ):
        """
        Transforms a list of coordinate point ordinal values. This method is
        provided for efficiently transforming many points. The supplied array
        of ordinal values will contain packed ordinal values. For example, if
        the source dimension is 3, then the ordinals will be packed in this
        order: (x0,y0,z0, x1,y1,z1 ...).

        :param src_pts: the array containing the source point coordinates.
        :type src_pts: numpy.ndarray
        :param src_off: the offset to the first point to be transformed in the
            source array.
        :type src_off: int
        :param dst_pts: the array into which the transformed point coordinates
            are returned. May be the same as srcPts
        :type dst_pts: numpy.ndarray
        :param dst_off: the offset to the location of the first transformed
            point that is stored in the destination
        :type dst_off: int
        :param num_pts: the number of point objects to be transformed.
        :type num_pts: int
        """

    @abstractmethod
    def derivative(self, point: primitive.DirectPosition) -> np.ndarray:
        """
        Gets the derivative of this transform at a point (never null).
        The derivative is the matrix of the non-translating portion of
        the approximate affine map at the point. The matrix will have
        dimensions corresponding to the source and target coordinate systems.

        Parameters:
            point (DirectPosition): The coordinate point where to evaluate the
                derivative. Null value is accepted only if the derivative is
                the same everywhere (e.g., with affine transforms). But most
                map projection will requires a non-null value.
        """


class MathTransform1D(MathTransform):
    """
    Transforms one-dimensional coordinate points.
    """

    @abstractmethod
    def inverse(self) -> 'MathTransform1D':
        """
        Creates the inverse transform of this object.
        """

    @abstractmethod
    def transform_value(self, value: float) -> float:
        """
        Transforms the specified value.

        Parameters:
            value (float): The value to transform.
        """

    # @abstractmethod
    # def derivative(self, value: float) -> np.ndarray:
    #     """
    #     Gets the derivative of this function at a value. The derivative is
    #     the 1x1 matrix of the non-translating portion of the approximate
    #     affine map at the value.

    #     :param value: The value where to evaluate the derivative.
    #     :type value: float
    #     :return: The derivative at the specified point, as an np.ndarray with
    #         only one element.
    #     :rtype: np.ndarray
    #     """


class Formula:
    """
    Specification of the coordinate operation method formula.
    """

    @property
    @abstractmethod
    def formula(self) -> str:
        """
        Formula(s) or procedure used by the operation method.

        :return: The formula used by the operation method, or null if none.
        :rtype: str
        """

    @property
    @abstractmethod
    def citation(self) -> Optional[meta_citation.Citation]:
        """
        Reference to a publication giving the formula(s) or procedure used by
        the coordinate operation method, or null if `None`.
        """


class OperationMethod(ref_common.IdentifiedObject):
    """
    Definition of an algorithm used to perform a coordinate operation.
    """

    @property
    @abstractmethod
    def formula(self) -> Formula:
        """
        Formula(s) or procedure used by this operation method. This may be a
        reference to a publication. Note that the operation method may not be
        analytic, in which case this attribute references or contains the
        procedure, not an analytic formula.
        """

    # @property
    # @abstractmethod
    # def parameters(self) -> Optional['ParameterDescriptorGroup']:
    #     """
    #     The set of parameters, or an empty group if `None`.
    #     """


class CoordinateOperation(ref_common.IdentifiedObject):
    """
    A mathematical operation on coordinates that transforms or converts
    coordinates to another coordinate reference system.
    """

    @property
    @abstractmethod
    def source_crs(self) -> Optional[crs.CoordinateReferenceSystem]:
        """
        Returns the source CRS. The source CRS is mandatory for
        transformations only. Conversions may have a source CRS that is not
        specified here, but through `DerivedCRS.getBaseCRS()` instead.
        `None` if not available.
        """

    @property
    @abstractmethod
    def target_crs(self) -> Optional[crs.CoordinateReferenceSystem]:
        """
        Returns the target CRS. The target CRS is mandatory for
        transformations only. Conversions may have a target CRS that is not
        specified here, but through `DerivedCRS` instead. `None` if
        not available.
        """

    @property
    @abstractmethod
    def operation_version(self) -> Optional[str]:
        """
        Version of the coordinate transformation (i.e., instantiation due to
        the stochastic nature of the parameters).

        MANDATORY:
            When describing a transformation, and should not be supplied for
            a conversion.
        """

    @property
    @abstractmethod
    def coordinate_operation_accuracy(self) ->\
            Sequence[Optional[quality.PositionalAccuracy]]:
        """
        Estimate(s) of the impact of this operation on point accuracy. Gives
        position error estimates for target coordinates of this coordinate
        operation, assuming no errors in source coordinates, or an empty
        collection if not available.
        """

    @property
    @abstractmethod
    def domain_of_validity(self) -> Optional[meta_extent.Extent]:
        """
        Area or region or timeframe in which this coordinate operation is
        valid or `None` if not available.
        """

    @property
    @abstractmethod
    def scope(self) -> str:
        """
        Description of domain of usage, or limitations of usage, for which
        this operation is valid.
        """

    @property
    @abstractmethod
    def math_transform(self) -> MathTransform:
        """
        Gets the math transform. The math transform will transform positions
        in the source coordinate reference system into positions in the target
        coordinate reference system. It may be `None` in the case of defining
        conversions or if it's not applicable.
        """


class SingleOperation(CoordinateOperation):
    """
    A parameterized mathematical operation on coordinates that transforms or
    converts coordinates to another coordinate reference system.
    """

    @property
    @abstractmethod
    def method(self) -> OperationMethod:
        """
        Returns the operation method.
        """

    # @property
    # @abstractmethod
    # def parameter_values(self) -> 'ParameterValueGroup':
    #     """
    #     Returns the parameter values.
    #     """


class PassThroughOperation(SingleOperation):
    """
    A pass-through operation specifies that a subset of a coordinate tuple is
    subject to a specific coordinate operation.
    """

    @property
    @abstractmethod
    def operation(self) -> SingleOperation:
        """
        Returns the operation to apply on the subset of a coordinate tuple.
        """

    @property
    @abstractmethod
    def modified_coordinates(self) -> Sequence[int]:
        """
        Ordered sequence of positive integers defining the positions in a
        coordinate tuple of the coordinates affected by this pass-through
        operation. Returns the modified coordinates.
        """


class Transformation(SingleOperation):
    """
    An operation on coordinates that usually includes a change of Datum.
    """

    @property
    @abstractmethod
    def source_crs(self) -> crs.CoordinateReferenceSystem:
        """
        Returns the source CRS (never null).
        """

    @property
    @abstractmethod
    def target_crs(self) -> crs.CoordinateReferenceSystem:
        """
        Returns the target CRS (never null).
        """

    @property
    @abstractmethod
    def operation_version(self) -> str:
        """
        Version of the coordinate transformation (i.e., instantiation due to
        the stochastic nature of the parameters). This attribute is mandatory
        in a Transformation.
        """


class Conversion(SingleOperation):
    """
    An operation on coordinates that does not include any change of Datum.
    """

    @property
    @abstractmethod
    def source_crs(self) -> Optional[crs.CoordinateReferenceSystem]:
        """
        Returns the source CRS. Conversions may have a source CRS that is not
        specified here, but through `DerivedCRS.getBaseCRS()` instead. `None`
        if not available.
        """

    @property
    @abstractmethod
    def target_crs(self) -> Optional[crs.CoordinateReferenceSystem]:
        """
        Returns the target CRS. Conversions may have a target CRS that is not
        specified here, but through `DerivedCRS` instead. `None`
        if not available.
        """

    @property
    @abstractmethod
    def operation_version(self) -> None:
        """
        This attribute is declared in `CoordinateOperation` but is not used in
        a conversion.
        """
        return None
