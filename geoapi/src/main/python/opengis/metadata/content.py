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
"""This is the content module.

This module contains geographic metadata structures regarding data content
derived from the ISO 19115-1:2014 and ISO 19115-2:2019 international standards.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence
from enum import Enum
from typing import Optional

from opengis.metadata.citation import Citation, Identifier
from opengis.metadata.naming import GenericName, MemberName, Record, RecordType
from opengis.util.measure import UnitOfMeasure, UomLength


class BandDefinition(Enum):
    """
    Designation of the criterion for defining wavelengths of a spectral band.
    """

    THREE_DB = "3dB"
    """
    Width of a distribution equal to the distance between the outer two points\
    on the distribution having power level half of that at the peak.
    """

    HALF_MAXIMUM = "halfMaximum"
    """
    Width of a distribution equal to the distance between the outer two points
    on the distribution having power level half of that at the peak.
    """

    FIFTY_PERCENT = "fiftyPercent"
    """
    Full spectral width of a spectral power density measured at 50 % of its
    peak height.
    """

    ONE_OVER_E = "oneOverE"
    """
    Width of a distribution equal to the distance between the outer two points
    on the distribution having power level 1/e that of the peak.
    """

    EQUIVALENT_WIDTH = "equivalentWidth"
    """
    Width of a band with full sensitivity or absorption at every wavelength
    that detects or absorbs the same amount of energy as the band described.
    """


class CoverageContentTypeCode(Enum):
    """
    Transformation function to be used when scaling a physical value of a
    given element.
    """

    IMAGE = "image"
    """
    Meaningful numerical representation of a physical parameter that is not
    the actual value of the physical parameter.
    """

    THEMATIC_CLASSIFICATION = "thematicClassification"
    """
    Code value with no quantitative meaning, used to represent a
    physical quantity.
    """

    PHYSICAL_MEASUREMENT = "physicalMeasurement"
    """Value in physical units of the quantity being measured."""

    AUXILLARY_INFORMATION = "auxillaryInformation"
    """
    Data, usually a physical measurement, used to support the calculation
    of the primary PHYSICAL_MEASUREMENT coverages in the dataset.

    EXAMPLE: Grid of aerosol optical thickness used in the calculation of a
    sea surface temperature product.
    """

    QUALITY_INFORMATION = "qualityInformation"
    """
    Data used to characterize the quality of the PHYSICAL_MEASUREMENT coverages
    in the dataset.

    NOTE: Typically included in a gmi:QE_CoverageResult.
    """

    REFERENCE_INFORMATION = "referenceInformation"
    """
    Reference information used to support the calculation or use of the
    PHYSICAL_MEASUREMENT coverages in the dataset.

    EXAMPLE: Grids of latitude/longitude used to geolocate
    the physical measurements.
    """

    MODEL_RESULT = "modelResult"
    """
    Resources with values that are calculated using a model rather than being
    observed or calculated from observations.
    """

    COORDINATE = "coordinate"
    """Data used to provide coordinate axis values."""


class ImagingConditionCode(Enum):
    """
    Code which indicates conditions which may affect the image.
    """

    BLURRED_IMAGE = "blurredImage"
    """Portion of the image is blurred."""

    CLOUD = "cloud"
    """Portion of the image is partially obscured by cloud cover."""

    DEGRADING_OBLIQUITY = "degradingObliquity"
    """
    Acute angle between the plane of the ecliptic (the plane of the Earth's
    orbit) and the plane of the celestial equator.
    """

    FOG = "fog"
    """Portion of the image is partially obscured by fog."""

    HEAVY_SMOKE_OR_DUST = "heavySmokeOrDust"
    """Portion of the image is partially obscured by heavy smoke or dust."""

    NIGHT = "night"
    """Image was taken at night."""

    RAIN = "rain"
    """Image was taken during rainfall."""

    SEMI_DARKNESS = "semiDarkness"
    """
    Image was taken during semi-dark conditions - twilight conditions.
    """
    SHADOW = "shadow"
    """Portion of the image is obscured by shadow."""

    SNOW = "snow"
    """Portion of the image is obscured by snow."""

    TERRAIN_MASKING = "terrainMasking"
    """
    The absence of collection data of a given point or area caused by the
    relative location of topographic features which obstruct the collection
    path between the collector(s) and the subject(s) of interes.
    """


class PolarisationOrientationCode(Enum):
    """Polarization of the antenna in relation to the wave form."""

    HORIZONTAL = "horizontal"
    """
    Polarization of the sensor oriented in the horizontal plane in relation to
    swathe direction.
    """

    VERTICAL = "vertical"
    """
    Polarization of the sensor oriented in the vertical plane in relation to
    swathe direction.
    """

    LEFT_CIRCULAR = "leftCircular"
    """
    Polarization of the sensor oriented in the left circular plane in relation
    to swathe direction.
    """

    RIGHT_CIRCULAR = "rightCircular"
    """
    Polarization of the sensor oriented in the right circular plane in relation
    to swathe direction.
    """

    THETA = "theta"
    """
    Polarization of the sensor oriented in the angle between +90° and 0°
    parallel to swathe direction.
    """

    PHI = "phi"
    """
    Polarization of the sensor oriented in the +90° and 0° perpendicular to
    swathe direction.
    """


class TransferFunctionTypeCode(Enum):
    """
    Transform function to be used when scaling a physical value for a given
    element.
    """

    LINEAR = "linear"
    """Function used when transformation is first order polynomial."""

    LOGARITHMIC = "logarithmic"
    """Function used when transformation is logartihmic."""

    EXPONENTIAL = "exponential"
    """Function used when transformation is exponential."""


class RangeElementDescription(ABC):
    """Description of specific range elements."""

    @property
    @abstractmethod
    def name(self) -> str:
        """Designation associated with a set of range elements."""

    @property
    @abstractmethod
    def definition(self) -> str:
        """Description of a set of specific range elements."""

    @property
    @abstractmethod
    def range_element(self) -> Sequence[Record]:
        """
        Specific range elements, i.e. range elements associated with a name
        and their definition.
        """


class RangeDimension(ABC):
    """Information on the range of attribute values."""

    @property
    @abstractmethod
    def sequence_identifier(self) -> Optional[MemberName]:
        """
        Number that uniquely identifies instances of bands of wavelengths on
        which a sensor operates.
        """

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """Description of the range of a cell measurement value."""

    @property
    @abstractmethod
    def name(self) -> Optional[Sequence[Identifier]]:
        """
        Identifiers for each attribute included in the resource.

        NOTE: These identifiers can be used to provide names for the
        resource's attribute from a standard set of names.
        """


class AttributeGroup(ABC):
    """
    Information about the `content_type` for groups of attributes for a
    specific `RangeDimension`.
    """

    @property
    @abstractmethod
    def content_type(self) -> Sequence[CoverageContentTypeCode]:
        """Type of information represented by the value."""

    @property
    @abstractmethod
    def attribute(self) -> Optional[Sequence[RangeDimension]]:
        """Information on an attribute of the resource."""


class SampleDimension(RangeDimension):
    """
    The characteristics of each dimension (layer) included in the resource.
    """

    @property
    @abstractmethod
    def max_value(self) -> Optional[float]:
        """
        Maximum value of data values in each dimension included in the
        resource.

        NOTE: Restricted to UomLength in the `Band` class.
        """

    @property
    @abstractmethod
    def min_value(self) -> Optional[float]:
        """
        Minimum value of data values in each dimension included in the
        resource.

        Restricted to `UomLength` in the `Band` class.
        """

    @property
    @abstractmethod
    def units(self) -> Optional[UnitOfMeasure]:
        """
        Units of data in each dimension included in the resource.

        NOTE: that the type of this is `UnitOfMeasure` and that it is
        restricted further for the `Band` class to being of type `UomLength`.

        MANDATORY if `max_value` or `min_value` is specified.
        """

    @property
    @abstractmethod
    def scale_factor(self) -> Optional[float]:
        """Scale factor which has been applied to the cell value."""

    @property
    @abstractmethod
    def offset(self) -> Optional[float]:
        """The physical value corresponding to a cell value of zero."""

    @property
    @abstractmethod
    def mean_value(self) -> Optional[float]:
        """
        Mean value of data values in each dimension included in the resource.
        """

    @property
    @abstractmethod
    def number_of_values(self) -> Optional[int]:
        """
        This gives the number of values used in a thematicClassification
        resource, e.g., the number of classes in a Land Cover Type coverage or
        the number of cells with data in other types of coverages.
        """

    @property
    @abstractmethod
    def standard_deviation(self) -> Optional[float]:
        """
        Standard deviation of data values in each dimension included in the
        resource.
        """

    @property
    @abstractmethod
    def other_property_type(self) -> Optional[RecordType]:
        """
        Type of other attribute description (i.e. netcdf/variable in ncml.xsd).
        """

    @property
    @abstractmethod
    def other_property(self) -> Optional[Record]:
        """
        Instance of otherAttributeType that defines attributes not explicitly
        included in `CoverageType`.
        """

    @property
    @abstractmethod
    def bits_per_value(self) -> Optional[int]:
        """
        Maximum number of significant bits in the uncompressed representation
        for the value in each band of each pixel.
        """

    @property
    @abstractmethod
    def transfer_function_type(self) -> Optional[TransferFunctionTypeCode]:
        """
        Type of transfer function to be used when scaling a physical value for
        a given element.
        """

    @property
    @abstractmethod
    def range_element_description(self) -> Sequence[RangeElementDescription]:
        """
        Provides the description and values of the specific range elements of
        a sample dimension.
        """

    @property
    @abstractmethod
    def nominal_spatial_resolution(self) -> Optional[float]:
        """
        Smallest distance between which separate points can be distinguished,
        as specified in instrument design.
        """


class Band(SampleDimension):
    """Range of wavelengths in the electromagnetic spectrum."""

    @property
    @abstractmethod
    def bound_max(self) -> Optional[float]:
        """
        Bounding maximum. The longest wavelength that the sensor is capable of
        collecting within a designated band.
        """

    @property
    @abstractmethod
    def bound_min(self) -> Optional[float]:
        """
        Bounding minimum. The shortest wavelength that the sensor is capable of
        collecting within a designated band.
        """

    @property
    @abstractmethod
    def bound_unit(self) -> Optional[UomLength]:
        """
        Bounding units. The units in which the sensor wavelengths are
        expressed.

        MANDATORY if `bound_max` or `bound_min` is specified.
        """

    @property
    @abstractmethod
    def peak_response(self) -> Optional[float]:
        """Wavelength at which the response is the highest."""

    @property
    @abstractmethod
    def tone_gradation(self) -> Optional[int]:
        """Number of discrete numerical values in the data."""

    @property
    @abstractmethod
    def band_boundary_definition(self) -> Optional[BandDefinition]:
        """
        Designation of criterion for defining maximum and minimum wavelengths
        for a spectral band.
        """

    @property
    @abstractmethod
    def transmitted_polarisation(self) -> \
            Optional[PolarisationOrientationCode]:
        """Polarisation of the transmitter or detector."""

    @property
    @abstractmethod
    def detected_polarisation(self) -> Optional[PolarisationOrientationCode]:
        """Polarisation of the transmitter or detector."""


class ContentInformation(ABC):
    """Description of the content of a resource."""


class CoverageDescription(ContentInformation):
    """Details about the content of a resource."""

    @property
    @abstractmethod
    def attribute_description(self) -> RecordType:
        """Description of the attribute described by the measurement value."""

    @property
    @abstractmethod
    def processing_level_code(self) -> Optional[Identifier]:
        """
        Code and codespace that identifies the level of processing that has
        been applied to the resource.
        """

    @property
    @abstractmethod
    def attribute_group(self) -> Optional[Sequence[AttributeGroup]]:
        """
        Information on the group(s) of related attributes of the resource
        with the same type.
        """

    @property
    @abstractmethod
    def range_element_description(self) -> Optional[Sequence[
        RangeElementDescription
    ]]:
        """
        Provides the description of the specific range elements of a coverage.
        """


class ImageDescription(CoverageDescription):
    """Information about an image's suitability for use."""

    @property
    @abstractmethod
    def illumination_elevation_angle(self) -> Optional[float]:
        """
        Illumination elevation measured in degrees clockwise from the target
        plane at intersection of the optical line of sight with the Earth's
        surface.

        NOTE: For images from a scanning device, refer to the centre pixel
        of the image.

        Domain: -90 - 90
        """

    @property
    @abstractmethod
    def illumination_azimuth_angle(self) -> Optional[float]:
        """
        Illumination azimuth measured in degrees clockwise from true north at
        the time the image is taken.

        NOTE: For images from a scanning device, refer to the centre pixel of
        the image.

        Domain: 0.00 - 360
        """

    @property
    @abstractmethod
    def imaging_condition(self) -> Optional[ImagingConditionCode]:
        """Conditions that affected the image."""

    @property
    @abstractmethod
    def image_quality_code(self) -> Optional[Identifier]:
        """Code in producer's code space that specifies the image quality."""

    @property
    @abstractmethod
    def cloud_cover_percentage(self) -> Optional[float]:
        """
        Area of the dataset obscured by clouds, expressed as a percentage of
        the spatial extent.

        Domain: 0.0 - 100.0
        """

    @property
    @abstractmethod
    def compression_generation_quantity(self) -> Optional[int]:
        """
        Count of the number of lossy compression cycles performed on the image.
        """

    @property
    @abstractmethod
    def triangulation_indicator(self) -> Optional[bool]:
        """
        Indication of whether or not triangulation has been performed upon the
        image.
        """

    @property
    @abstractmethod
    def radiometric_calibration_data_availability(self) -> Optional[bool]:
        """
        Indication of whether or not the radiometric calibration information
        for generating the radiometrically calibrated standard data product is
        available.
        """

    @property
    @abstractmethod
    def camera_calibration_information_availability(self) -> Optional[bool]:
        """
        Indication of whether or not constants are available which allow for
        camera calibration corrections.
        """

    @property
    @abstractmethod
    def film_distortion_information_availability(self) -> Optional[bool]:
        """
        Indication of whether or not Calibration Reseau information is
        available.
        """

    @property
    @abstractmethod
    def lens_distortion_information_availability(self) -> Optional[bool]:
        """
        Indication of whether or not lens aberration correction information is
        available.
        """


class FeatureTypeInfo(ABC):
    """Information about the occurring feature type."""

    @property
    @abstractmethod
    def feature_type_name(self) -> GenericName:
        """Name of the feature type."""

    @property
    @abstractmethod
    def feature_instance_count(self) -> Optional[int]:
        """
        Number of occurences of feature instances for this type.

        Domain: > 0
        """


class FeatureCatalogueDescription(ContentInformation):
    """
    Information identifying the feature catalogue or the conceptual schema.
    """

    @property
    @abstractmethod
    def compliance_code(self) -> Optional[bool]:
        """
        Indication of whether or not the cited feature catalogue complies with
        ISO 19110.
        """

    @property
    @abstractmethod
    def locale(self) -> Optional[Sequence[str]]:
        """
        Language(s) and character set(s) used within the catalogue. A string
        conforming to IETF BCP 47.
        """

    @property
    @abstractmethod
    def included_with_dataset(self) -> Optional[bool]:
        """
        Indication of whether or not the feature catalogue is included with
        the resource.
        """

    @property
    @abstractmethod
    def feature_types(self) -> Optional[Sequence[FeatureTypeInfo]]:
        """
        Subset of feature types from cited feature catalogue occurring in
        dataset.
        """

    @property
    @abstractmethod
    def feature_catalogue_citation(self) -> Optional[Sequence[Citation]]:
        """
        Complete bibliographic reference to one or more external feature
        catalogues.

        MANADTORY: if a Feature Catalogue is not provided with the resource
            and `FeatureCatalogue` is `None`.
        """


# ISO 19115:2014 MD_FeatureCatalogue
# Need to implement ISO 19110 FC_FeatureCatalogue and related objects
# class FeatureCatalogue(ContentInformation):
#     """A catalogue of feature types."""

#     @property
#     @abstractmethod
#     def feature_catalogue(self) -> Optional[Sequence[FC_FeatureCatalogue]]:
#         """
#         The catalogue of feature types, attribution, operations, and
#         relationships used by the resource.

#         Derived from FC_FeatureCatalogue from ISO 19110.
#         """
