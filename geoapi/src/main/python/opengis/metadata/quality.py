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



class EvaluationMethodTypeCode(Enum):
    DIRECT_INTERNAL = "directInternal"
    DIRECT_EXTERNAL = "directExternal"
    INDIRECT = "indirect"



class ValueStructure(Enum):
    BAG = "BAG"
    SET = "SET"
    SEQUENCE = "SEQUENCE"
    TABLE = "TABLE"
    MATRIX = "MATRIX"
    COVERAGE = "COVERAGE"



class StandaloneQualityReportInformation(ABC):
    """Reference to an external stadalone quality report"""

    @property
    @abstractmethod
    def report_reference(self) -> Citation:
        """Reference to the associated standalone quality report."""
        pass

    @property
    @abstractmethod
    def abstract(self) -> str:
        """Abstract for the associated standalone quality report."""
        pass



from opengis.metadata.maintenance import Scope
from java.util import Date

class Result(ABC):
    """Generalization of more specific result classes."""

    @property
    def result_scope(self) -> Scope:
        """Scope of the result."""
        return None

    @property
    def date_time(self) -> Date:
        """Date when the result was generated."""
        return None



from opengis.metadata.citation import Citation

class EvaluationMethod(ABC):
    """Description of the evaluation method and procedure applied."""

    @property
    def evaluation_method_type(self) -> EvaluationMethodType:
        """Type of method used to evaluate quality of the data."""
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
    def reference_doc(self) -> Sequence[Citation]:
        """Information on documents which are referenced in developing and applying a data quality evaluation method."""
        return None

    @property
    def date_time(self) -> Sequence[Date]:
        """Date or range of dates on which a data quality measure was applied."""
        return None



class MeasureReference(ABC):
    """Reference to the measure used."""

    @property
    def measure_identification(self) -> Identifier:
        """Identifier of the measure, value uniquely identifying the measure within a namespace."""
        return None

    @property
    def name_of_measure(self) -> Sequence[str]:
        """Name of the test applied to the data."""
        return None

    @property
    def measure_description(self) -> str:
        """Description of the measure."""
        return None



from opengis.metadata.citation import Identifier

class Element(ABC):
    """Aspect of quantitative quality information."""

    @property
    def Standalone_quality_report_details(self) -> str:
        """Clause in the standaloneQualityReport where this data quality element or any related data quality element (original results in case of derivation or aggregation) is described."""
        return None

    @property
    def Measure(self) -> MeasureReference:
        """Reference to measure used."""
        return None

     @property
    def Evaluation_method(self) -> EvaluationMethod:
        """Evaluation information."""
        return None

    @property
    @abstractmethod
    def result(self) -> Sequence[Result]:
        """Values obtained from applying a data quality measure against a specified acceptable conformance quality level."""
        pass

     @property
    def derived_element(self) -> Sequence[Element]:
        """In case of aggregation or derivation, indicates the original element."""
        return None



class DataQuality(ABC):
    """Quality information for the data specified by a data quality scope."""

    @property
    @abstractmethod
    def scope(self) -> Scope:
        """The specific data to which the data quality information applies."""
        pass

    @property
    def report(self) -> Sequence[Element]:
        """Quantitative quality information for the data specified by the scope."""
        return None

    @property
    def standalone_quality_report(self) -> StandaloneQualityReportInformation:
        return None



from opengis.metadata.identification import BrowseGraphic

class Description(ABC):
    """Data quality measure description."""

    @property
    @abstractmethod
    def text_description(self) -> str:
        """Text description."""
        pass

    @property
    def extended_Description(self) -> BrowseGraphic:
        """Illustration."""
        return None



from org.opengis.util import TypeName

class SourceReference(ABC):
    """Reference to the source of the data quality measure."""

    @property
    @abstractmethod
    def citation(self) -> Citation:
        """References to the source."""
        pass



class BasicMeasure(ABC):
    """Data quality basic measure."""

    @property
    @abstractmethod
    def name(self) -> str:
        """Name of the data quality basic measure applied to the data."""
        pass

    @property
    @abstractmethod
    def definition(self) -> str:
        """Definition of the data quality basic measure."""
        pass

    @property
    def exemple(self) -> Description:
        """Illustration of the use of a data quality measure."""
        return None

    @property
    @abstractmethod
    def value_type(self) -> TypeName:
        """Value type for the result of the basic measure (shall be one of the data types defined in ISO/TS 19103:2005)."""
        pass



class Measure(ABC):
    """Data quality measure."""

    @property
    @abstractmethod
    def measure_identifier(self) -> Identifier:
        """Value uniquely identifying the measure within a namespace."""
        pass

    @property
    @abstractmethod
    def name(self) -> str:
        """Name of the data quality measure applied to the data."""
        pass

    @property
    def alias(self) -> str:
        """Another recognized name, an abbreviation or a short name for the same data quality measure."""
        return None

    @property
    @abstractmethod
    def element_name(self) -> TypeName:
        """Name of the data quality element for which quality is reported."""
        pass

    @property
    @abstractmethod
    def definition(self) -> str:
        """Definition of the fundamental concept for the data quality measure."""
        pass

    @property
    @abstractmethod
    def description(self) -> description:
        """Description of the data quality measure, including all formulae and/or illustrations needed to establish the result of applying the measure."""
        pass

    @property
    @abstractmethod
    def value_type(self) -> TypeName:
        """Value type for reporting a data quality result (shall be one of the data types defined in ISO/19103:2005)."""
        pass

    @property
    def value_structure(self) -> ValueStructure:
        """Structure for reporting a complex data quality result."""
        return None

    @property
    def example(self) -> Sequence[Description]:
        """Illustration of the use of a data quality measure."""
        return None

    @property
    @abstractmethod
    def basic_measure(self) -> BasicMeasure:
        """Definition of the fundamental concept for the data quality measure."""
        pass

    @property
    def source_reference(self) -> Sequence[SourceReference]:
        """Reference to the source of an item that has been adopted from an external source."""
        return None

    @property
    def parameter(self):
        """Reference to the source of an item that has been adopted from an external source."""
        return None



class TemporalQuality(Element):
    """Accuracy of the temporal attributes and temporal relationships of features."""



class Metaquality(Element):
    """Information about the reliability of data quality results."""

    @property
    def derived_element(self) -> Sequence[Element]:
        """Derived element."""
        return None



class Confidence(Metaquality):
    """Trustworthiness of a data quality result."""



class UsabilityElement(Element):
    """Degree of adherence of a data set to a specific set of requirements."""



class Representativity(Metaquality):
    """Trustworthiness of a data quality result."""



class DataEvaluation(EvaluationMethod):
    """Data evaluation method."""



class SimpleBasedInspection(DataEvaluation):
    """Sample based inspection."""

    @property
    @abstractmethod
    def sampling_scheme(self) -> str:
        """Information of the type of sampling scheme and description of the sampling procedure."""
        pass

    @property
    @abstractmethod
    def lot_description(self) -> str:
        """Information of how lots are defined."""
        pass

    @property
    @abstractmethod
    def simple_ratio(self) -> str:
        """Information on how many samples on average are extracted for inspection from each lot of population."""
        pass



class IndirectEvaluation(DataEvaluation):
    """Indirect evaluation."""

    @property
    @abstractmethod
    def deductive_source(self) -> str:
        """Information on which data are used as sources in deductive evaluation method."""
        pass



class Homogeneity(Metaquality):
    """Expected or tested uniformity of the results obtained for a data quality evaluation."""



class FullInspection(DataEvaluation):
    """Test of every item in the population specified by the data quality scope."""



class DescriptiveResult(Result):
    """Data quality descriptive result."""

    @property
    @abstractmethod
    def statement(self) -> str:
        """Textual expression of the descriptive result."""
        pass



class AggregationDerivation(EvaluationMethod):
    """Aggregation or derivation method."""



class PositionalAccuracy(Element):
    """Accuracy of the position of features."""



class AbsoluteExternalPositionalAccuracy(PositionalAccuracy):
    """Closeness of reported coordinate values to values accepted as or being true."""



class GriddedDataPositionalAccuracy(PositionalAccuracy):
    """Closeness of gridded data position values to values accepted as or being true."""



class RelativeInternalPositionalAccuracy(PositionalAccuracy):
    """Closeness of the relative positions of features in the scope to their respective relative positions accepted as or being true."""



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



class NonQuantitativeAttributeCorrectness(ThematicAccuracy):
    """Correctness of non-quantitative attributes."""



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

    @property
    @abstractmethod
    def specification(self) -> Citation:
        """Citation of data product specification or user requirement against which data is being evaluated."""
        pass

    @property
    @abstractmethod
    def explanation(self) -> str:
        """Explanation of the meaning of conformance for this result."""
        pass

    @property
    @abstractmethod
    def is_conform(self):
        """Indication of the conformance result where 0 = fail and 1 = pass."""
        pass



from opengis.metadata.representation import SpatialRepresentationTypeCode
from opengis.metadata.distribution import DataFile, Format
from opengis.metadata.content import CoverageDescription

class CoverageResult(Result):
    """Result of a data quality measure organising the measured values as a coverage."""

    @property
    @abstractmethod
    def spatial_representation_type(self) -> SpatialRepresentationTypeCode:
        """Method used to spatially represent the coverage result."""
        pass

    @property
    @abstractmethod
    def result_file(self) -> DataFile:
        pass

    @property
    @abstractmethod
    def result_spatial_representation(self) -> 'SpatialRepresentation':
        pass

    @property
    @abstractmethod
    def result_content_description(self) -> CoverageDescription:
        pass

    @property
    @abstractmethod
    def result_format(self) -> Format:
        pass



from opengis.metadata.naming import Record, RecordType

class QuantitativeResult(Result):
    """The values or information about the value(s) (or set of values) obtained from applying a data quality measure."""

    @property
    @abstractmethod
    def value(self) -> Sequence[Record]:
        """Quantitative value or values, content determined by the evaluation procedure used, accordingly with the value type and valueStructure defined for the measure."""
        pass

    @property
    def value_unit(self) -> Unit:
        """Value unit for reporting a data quality result."""
        return None


    @property
    def value_record_type(self) -> RecordType:
        """Value type for reporting a data quality result, depends of the implementation."""
        return None
