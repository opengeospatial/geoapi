#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from typing import Sequence
from enum import Enum



class EvaluationMethodTypeCode(Enum):
    DIRECT_INTERNAL = "directInternal"
    DIRECT_EXTERNAL = "directExternal"
    INDIRECT = "indirect"



class Result(ABC):
    """Generalization of more specific result classes."""



from datetime import datetime
from opengis.metadata.citation import Identifier, Citation

class Element(ABC):
    """Aspect of quantitative quality information."""

    @property
    def name_of_measure(self) -> Sequence[str]:
        """Name of the test applied to the data."""
        return None

    @property
    def measure_identification(self) -> Identifier:
        """Code identifying a registered standard procedure."""
        return None

    @property
    def measure_description(self) -> str:
        """Description of the measure being determined."""
        return None

    @property
    def evaluation_method_type(self) -> EvaluationMethodTypeCode:
        """Type of method used to evaluate quality of the dataset."""
        return None

    @property
    def evaluation_method_description(self) -> str:
        """Description of the evaluation method."""
        return None

    @property
    def evaluation_procedure(self) -> Citation:
        """Reference to the procedure information."""
        return None

    @property
    def date_time(self) -> Sequence[datetime]:
        """Date or range of dates on which a data quality measure was applied."""
        return None

    @abstractproperty
    def result(self) -> Sequence[Result]:
        """Values obtained from applying a data quality measure against a specified acceptable conformance quality level."""
        pass



class PositionalAccuracy(Element):
    """Accuracy of the position of features."""



class AbsoluteExternalPositionalAccuracy(PositionalAccuracy):
    """Closeness of reported coordinate values to values accepted as or being true."""



class GriddedDataPositionalAccuracy(PositionalAccuracy):
    """Closeness of gridded data position values to values accepted as or being true."""



class RelativeInternalPositionalAccuracy(PositionalAccuracy):
    """Closeness of the relative positions of features in the scope to their respective relative positions accepted as or being true."""



class TemporalAccuracy(Element):
    """TODO"""


class TemporalConsistency(TemporalAccuracy):
    """Correctness of ordered events or sequences, if reported."""



class TemporalValidity(TemporalAccuracy):
    """Validity of data specified by the scope with respect to time."""



class AccuracyOfATimeMeasurement(TemporalAccuracy):
    """Correctness of the temporal references of an item (reporting of error in time measurement)."""



class ThematicAccuracy(Element):
    """Accuracy of quantitative attributes and the correctness of non-quantitative attributes and of the classifications of features and their relationships."""



class ThematicClassificationCorrectness(ThematicAccuracy):
    """Comparison of the classes assigned to features or their attributes to a universe of discourse."""



class QuantitativeAttributeAccuracy(ThematicAccuracy):
    """Accuracy of quantitative attributes."""



class LogicalConsistency(Element):
    """Degree of adherence to logical rules of data structure, attribution and relationships (data structure can be conceptual, logical or physical)."""



class ConceptualConsistency(LogicalConsistency):
    """Adherence to rules of the conceptual schema."""



class DomainConsistency(LogicalConsistency):
    """Adherence of values to the value domains."""



class FormatConsistency(LogicalConsistency):
    """Degree to which data is stored in accordance with the physical structure of the dataset, as described by the scope."""



class TopologicalConsistency(LogicalConsistency):
    """Correctness of the explicitly encoded topological characteristics of the dataset as described by the scope."""



class Completeness(Element):
    """Presence and absence of features, their attributes and their relationships."""



class CompletenessCommission(Completeness):
    """Excess data present in the dataset, as described by the scope."""



class CompletenessOmission(Completeness):
    """Data absent from the dataset, as described by the scope."""



class ConformanceResult(Result):
    """Information about the outcome of evaluating the obtained value (or set of values) against a specified acceptable conformance quality level."""

    @abstractproperty
    def specification(self) -> Citation:
        """Citation of data product specification or user requirement against which data is being evaluated."""
        pass

    @abstractproperty
    def explanation(self) -> str:
        """Explanation of the meaning of conformance for this result."""
        pass

    @abstractproperty
    def is_conform(self):
        """Indication of the conformance result where 0 = fail and 1 = pass."""
        pass



from opengis.metadata.representation import SpatialRepresentationTypeCode
from opengis.metadata.distribution import DataFile, Format
from opengis.metadata.content import CoverageDescription

class CoverageResult(Result):
    """Result of a data quality measure organising the measured values as a coverage."""

    @abstractproperty
    def spatial_representation_type(self) -> SpatialRepresentationTypeCode:
        """Method used to spatially represent the coverage result."""
        pass

    @abstractproperty
    def result_file(self) -> DataFile:
        pass

    @abstractproperty
    def result_spatial_representation(self) -> 'SpatialRepresentation':
        pass

    @abstractproperty
    def result_content_description(self) -> CoverageDescription:
        pass

    @abstractproperty
    def result_format(self) -> Format:
        pass



from opengis.metadata.naming import Record, RecordType

class QuantitativeResult(Result):
    """The values or information about the value(s) (or set of values) obtained from applying a data quality measure."""

    @abstractproperty
    def value(self) -> Sequence[Record]:
        """Quantitative value or values, content determined by the evaluation procedure used, accordingly with the value type and valueStructure defined for the measure."""
        pass

    @abstractproperty
    def value_unit(self):
        """Value unit for reporting a data quality result."""
        pass

    @property
    def value_type(self) -> RecordType:
        return None

    @property
    def error_statistic(self) -> str:
        return None



from opengis.metadata.maintenance import Scope
from opengis.metadata.lineage import Lineage

class DataQuality(ABC):
    """Quality information for the data specified by a data quality scope."""

    @abstractproperty
    def scope(self) -> Scope:
        """The specific data to which the data quality information applies."""
        pass

    @property
    def report(self) -> Sequence[Element]:
        return None

    @property
    def lineage(self) -> Lineage:
        return None
