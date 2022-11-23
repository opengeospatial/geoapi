#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractmethod
from typing import Sequence
from enum import Enum



class BandDefinition(Enum):
    THREE_DB = "3dB"
    HALF_MAXIMUM = "halfMaximum"
    FIFTY_PERCENT = "fiftyPercent"
    ONE_OVER_E = "oneOverE"
    EQUIVALENT_WIDTH = "equivalentWidth"



class CoverageContentTypeCode(Enum):
    IMAGE = "image"
    THEMATIC_CLASSIFICATION = "thematicClassification"
    PHYSICAL_MEASUREMENT = "physicalMeasurement"
    AUXILLARY_INFORMATION = "auxillaryInformation"
    QUALITY_INFORMATION = "qualityInformation"
    REFERENCE_INFORMATION = "referenceInformation"
    MODEL_RESULT = "modelResult"
    COORDINATE = "coordinate"



class ImagingConditionCode(Enum):
    BLURRED_IMAGE = "blurredImage"
    CLOUD = "cloud"
    DEGRADING_OBLIQUITY = "degradingObliquity"
    FOG = "fog"
    HEAVY_SMOKE_OR_DUST = "heavySmokeOrDust"
    NIGHT = "night"
    RAIN = "rain"
    SEMI_DARKNESS = "semiDarkness"
    SHADOW = "shadow"
    SNOW = "snow"
    TERRAIN_MASKING = "terrainMasking"



class PolarisationOrientationCode(Enum):
    HORIZONTAL = "horizontal"
    VERTICAL = "vertical"
    LEFT_CIRCULAR = "leftCircular"
    RIGHT_CIRCULAR = "rightCircular"
    THETA = "theta"
    PHI = "phi"



class TransferFunctionTypeCode(Enum):
    LINEAR = "linear"
    LOGARITHMIC = "logarithmic"
    EXPONENTIAL = "exponential"



from opengis.metadata.naming import Record, MemberName, RecordType, GenericName

class RangeElementDescription(ABC):
    """Description of specific range elements."""

    @property
    @abstractmethod
    def name(self) -> str:
        """Designation associated with a set of range elements."""
        pass

    @property
    @abstractmethod
    def definition(self) -> str:
        """Description of a set of specific range elements."""
        pass

    @property
    @abstractmethod
    def range_element(self) -> Sequence[Record]:
        """Specific range elements, i.e. range elements associated with a name and definition defining their meaning."""
        pass



from opengis.metadata.citation import Identifier, Citation

class RangeDimension(ABC):
    """Information on the range of attribute values."""

    @property
    def sequence_identifier(self) -> MemberName:
        """Number that uniquely identifies instances of bands of wavelengths on which a sensor operates."""
        return None

    @property
    def description(self) -> str:
        """Description of the range of a cell measurement value."""
        return None

    @property
    def name(self) -> Sequence[Identifier]:
        """Identifiers for each attribute included in the resource. These identifiers can be used to provide names for the resource's attribute from a standard set of names."""
        return None



class AttributeGroup(ABC):

    @property
    @abstractmethod
    def content_type(self) -> Sequence[CoverageContentTypeCode]:
        """Type of information represented by the value."""
        pass

    @property
    def attribute(self) -> Sequence[RangeDimension]:
        return None



class SampleDimension(RangeDimension):
    """The characteristics of each dimension (layer) included in the resource."""

    @property
    def max_value(self) -> float:
        """Maximum value of data values in each dimension included in the resource. Restricted to UomLength in the MD_Band class."""
        return None

    @property
    def min_value(self) -> float:
        """Minimum value of data values in each dimension included in the resource. Restricted to UomLength in the MD_Band class."""
        return None

    @property
    def units(self):
        """Units of data in each dimension included in the resource. Note that the type of this is UnitOfMeasure and that it is restricted to UomLength in the MD_Band class."""
        return None

    @property
    def scale_factor(self) -> float:
        """Scale factor which has been applied to the cell value."""
        return None

    @property
    def offset(self) -> float:
        """The physical value corresponding to a cell value of zero."""
        return None

    @property
    def mean_value(self) -> float:
        """Mean value of data values in each dimension included in the resource."""
        return None

    @property
    def number_of_values(self) -> int:
        """This gives the number of values used in a thematicClassification resource EX:. the number of classes in a Land Cover Type coverage or the number of cells with data in other types of coverages."""
        return None

    @property
    def standard_deviation(self) -> float:
        """Standard deviation of data values in each dimension included in the resource."""
        return None

    @property
    def other_property_type(self) -> RecordType:
        """Type of other attribute description (i.e. netcdf/variable in ncml.xsd)."""
        return None

    @property
    def other_property(self) -> Record:
        """Instance of otherAttributeType that defines attributes not explicitly included in MD_CoverageType."""
        return None

    @property
    def bits_per_value(self) -> int:
        """Maximum number of significant bits in the uncompressed representation for the value in each band of each pixel."""
        return None

    @property
    def transfer_function_type(self) -> TransferFunctionTypeCode:
        """Type of transfer function to be used when scaling a physical value for a given element."""
        return None

    @property
    def range_element_description(self) -> Sequence[RangeElementDescription]:
        """Provides the description and values of the specific range elements of a sample dimension."""
        return None

    @property
    def nominal_spatial_resolution(self) -> float:
        """Smallest distance between which separate points can be distinguished, as specified in instrument design."""
        return None



class Band(SampleDimension):
    """Range of wavelengths in the electromagnetic spectrum."""

    @property
    def bound_max(self) -> float:
        return None

    @property
    def bound_min(self) -> float:
        return None

    @property
    def bound_units(self):
        return None

    @property
    def peak_response(self) -> float:
        """Wavelength at which the response is the highest."""
        return None

    @property
    def tone_gradation(self) -> int:
        """Number of discrete numerical values in the grid data."""
        return None

    @property
    def band_boundary_definition(self) -> BandDefinition:
        """Designation of criterion for defining maximum and minimum wavelengths for a spectral band."""
        return None

    @property
    def transmitted_polarisation(self) -> PolarisationOrientationCode:
        """Polarisation of the transmitter or detector."""
        return None

    @property
    def detected_polarisation(self) -> PolarisationOrientationCode:
        """Polarisation of the transmitter or detector."""
        return None



class ContentInformation(ABC):
    """Description of the content of a resource."""



class CoverageDescription(ContentInformation):
    """Details about the content of a resource."""

    @property
    @abstractmethod
    def attribute_description(self) -> RecordType:
        """Description of the attribute described by the measurement value."""
        pass

    @property
    def processing_level_code(self) -> Identifier:
        """Code and codespace that identifies the level of processing that has been applied to the resource."""
        return None

    @property
    def attribute_group(self) -> Sequence[AttributeGroup]:
        return None

    @property
    def range_element_description(self) -> Sequence[RangeElementDescription]:
        return None



class ImageDescription(CoverageDescription):
    """Information about an image's suitability for use."""

    @property
    def illumination_elevation_angle(self) -> float:
        """Illumination elevation measured in degrees clockwise from the target plane at intersection of the optical line of sight with the Earth's surface. For images from a scanning device, refer to the centre pixel of the image."""
        return None

    @property
    def illumination_azimuth_angle(self) -> float:
        """Illumination azimuth measured in degrees clockwise from true north at the time the image is taken. For images from a scanning device, refer to the centre pixel of the image."""
        return None

    @property
    def imaging_condition(self) -> ImagingConditionCode:
        """Conditions affected the image."""
        return None

    @property
    def image_quality_code(self) -> Identifier:
        """Code in producers code space that specifies the image quality."""
        return None

    @property
    def cloud_cover_percentage(self) -> float:
        """Area of the dataset obscured by clouds, expressed as a percentage of the spatial extent."""
        return None

    @property
    def compression_generation_quantity(self) -> int:
        """Count of the number of lossy compression cycles performed on the image."""
        return None

    @property
    def triangulation_indicator(self):
        """Indication of whether or not triangulation has been performed upon the image."""
        return None

    @property
    def radiometric_calibration_data_availability(self):
        """Indication of whether or not the radiometric calibration information for generating the radiometrically calibrated standard data product is available."""
        return None

    @property
    def camera_calibration_information_availability(self):
        """Indication of whether or not constants are available which allow for camera calibration corrections."""
        return None

    @property
    def film_distortion_information_availability(self):
        """Indication of whether or not Calibration Reseau information is available."""
        return None

    @property
    def lens_distortion_information_availability(self):
        """Indication of whether or not lens aberration correction information is available."""
        return None



class FeatureTypeInfo(ABC):

    @property
    @abstractmethod
    def feature_type_name(self) -> GenericName:
        pass

    @property
    def feature_instance_count(self) -> int:
        return None



class FeatureCatalogueDescription(ContentInformation):
    """Information identifying the feature catalogue or the conceptual schema."""

    @property
    def compliance_code(self):
        """Indication of whether or not the cited feature catalogue complies with ISO 19110."""
        return None

    @property
    def locale(self):
        """Language(s) used within the catalogue."""
        return None

    @property
    def included_with_dataset(self):
        """Indication of whether or not the feature catalogue is included with the resource."""
        return None

    @property
    def feature_types(self) -> Sequence[FeatureTypeInfo]:
        """Subset of feature types from cited feature catalogue occurring in dataset."""
        return None

    @property
    def feature_catalogue_citation(self) -> Sequence[Citation]:
        """Complete bibliographic reference to one or more external feature catalogues."""
        return None
