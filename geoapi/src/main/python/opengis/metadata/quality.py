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
"""This is the `quality` module.


This module contains geographic metadata structures derived from the
ISO 19115-1:2014 international standard and data quality structures derived
from the ISO 19157:2013, including addendum A1 (2018) international standard.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence
from datetime import datetime
from enum import Enum
from typing import Optional

from opengis.metadata.citation import Citation, Identifier
from opengis.metadata.content import RangeDimension
from opengis.metadata.distribution import DataFile, Format
from opengis.metadata.identification import BrowseGraphic
from opengis.metadata.maintenance import Scope
from opengis.metadata.naming import Record, RecordType, TypeName
from opengis.metadata.representation import (
    SpatialRepresentationTypeCode,
    SpatialRepresentation,
)
from opengis.util.measure import UnitOfMeasure


class EvaluationMethodTypeCode(Enum):
    """Type of method for evaluating an identified data quality measure."""

    # DQ_EvaluationMethodTypeCode: directInternal
    DIRECT_INTERNAL = "001"
    """
    Method of evaluating the quality of a data set based on inspection
    of items within the data set, where all data required is internal
    to the data set being evaluated.
    """

    # DQ_EvaluationMethodTypeCode: directExternal
    DIRECT_EXTERNAL = "002"
    """
    Method of evaluating the quality of a data set based on inspection
    of items within the data set, where reference data external to the data
    set being evaluated is required.
    """

    # DQ_EvaluationMethodTypeCode: indirect
    INDIRECT = "003"
    """
    Method of evaluating the quality of a data set based on external knowledge.
    """


class ValueStructure(Enum):
    """The way in which values are grouped together."""

    # DQM_ValueStructure: bag
    BAG = "001"
    """
    Finite, unordered collection of related items (objects or values).
    """

    # DQM_ValueStructure: set
    SET = "002"
    """
    Unordered collection of related items (objects or values) with no
    repetition (ISO 19107:2003).
    """

    # DQM_ValueStructure: sequence
    SEQUENCE = "003"
    """
    Finite, ordered collection of related items (objects or values) that may
    be repeated (ISO 19107:2003).
    """

    # DQM_ValueStructure: table
    TABLE = "004"
    """
    An arrangement of data in which each item may be identified by means of
    arguments or keys (ISO/IEC 2382-4:1999).
    """

    # DQM_ValueStructure: matrix
    MATRIX = "005"
    """Rectangular array of numbers (ISO/TS 19129:2009)."""

    # DQM_ValueStructure: coverage
    COVERAGE = "006"
    """
    Feature that acts as a function to return values from its range for any
    direct position within its spatial, temporal or spatiotemporal domain
    (ISO 19123:2005).
    """


class StandaloneQualityReportInformation(ABC):
    """Reference to an external stadalone quality report"""

    @property
    @abstractmethod
    def report_reference(self) -> Citation:
        """Reference to the associated standalone quality report."""

    @property
    @abstractmethod
    def abstract(self) -> str:
        """Abstract for the associated standalone quality report."""


class Result(ABC):
    """Generalization of more specific result classes."""

    @property
    @abstractmethod
    def result_scope(self) -> Scope:
        """Scope of the result."""

    @property
    @abstractmethod
    def date_time(self) -> datetime:
        """Date when the result was generated."""


class EvaluationMethod(ABC):
    """Description of the evaluation method and procedure applied."""

    @property
    @abstractmethod
    def evaluation_method_type(self) -> EvaluationMethodTypeCode:
        """Type of method used to evaluate quality of the data."""

    @property
    @abstractmethod
    def evaluation_method_description(self) -> str:
        """Description of the evaluation method."""

    @property
    @abstractmethod
    def evaluation_procedure(self) -> Citation:
        """Reference to the procedure information."""

    @property
    @abstractmethod
    def reference_doc(self) -> Sequence[Citation]:
        """
        Information on documents which are referenced in developing and
        applying a data quality evaluation method.
        """

    @property
    @abstractmethod
    def date_time(self) -> Sequence[datetime]:
        """
        Date or range of dates on which a data quality measure was applied.
        """


class MeasureReference(ABC):
    """Reference to the measure used."""

    @property
    @abstractmethod
    def measure_identification(self) -> Identifier:
        """
        Identifier of the measure, value uniquely identifying the measure
        within a namespace.
        """

    @property
    @abstractmethod
    def name_of_measure(self) -> Sequence[str]:
        """Name of the test applied to the data."""

    @property
    @abstractmethod
    def measure_description(self) -> str:
        """Description of the measure."""


class Element(ABC):
    """Aspect of quantitative quality information."""

    @property
    @abstractmethod
    def standalone_quality_report_details(self) -> str:
        """
        Clause in the standaloneQualityReport where this data quality element
        or any related data quality element (original results in case of
        derivation or aggregation) is described.
        """

    @property
    @abstractmethod
    def measure(self) -> MeasureReference:
        """Reference to measure used."""

    @property
    @abstractmethod
    def evaluation_method(self) -> EvaluationMethod:
        """Evaluation information."""

    @property
    @abstractmethod
    def result(self) -> Sequence[Result]:
        """
        Values obtained from applying a data quality measure against a
        specified acceptable conformance quality level.
        """

    @property
    @abstractmethod
    def derived_element(self) -> Sequence['Element']:
        """
        In case of aggregation or derivation, indicates the original element.
        """


class DataQuality(ABC):
    """Quality information for the data specified by a data quality scope."""

    @property
    @abstractmethod
    def scope(self) -> Scope:
        """The specific data to which the data quality information applies."""

    @property
    @abstractmethod
    def report(self) -> Sequence[Element]:
        """
        Quantitative quality information for the data specified by the scope.
        """

    @property
    @abstractmethod
    def standalone_quality_report(self) -> \
            Optional[StandaloneQualityReportInformation]:
        """Reference and abstract of the attached standalone quality report."""


class Description(ABC):
    """Data quality measure description."""

    @property
    @abstractmethod
    def text_description(self) -> str:
        """Text description."""

    @property
    @abstractmethod
    def extended_description(self) -> BrowseGraphic:
        """Illustration."""


class SourceReference(ABC):
    """Reference to the source of the data quality measure."""

    @property
    @abstractmethod
    def citation(self) -> Citation:
        """References to the source."""


class BasicMeasure(ABC):
    """Data quality basic measure."""

    @property
    @abstractmethod
    def name(self) -> str:
        """Name of the data quality basic measure applied to the data."""

    @property
    @abstractmethod
    def definition(self) -> str:
        """Definition of the data quality basic measure."""

    @property
    @abstractmethod
    def exemple(self) -> Description:
        """Illustration of the use of a data quality measure."""

    @property
    @abstractmethod
    def value_type(self) -> TypeName:
        """
        Value type for the result of the basic measure (shall be one of the
        data types defined in ISO/TS 19103:2005).
        """


class Measure(ABC):
    """Data quality measure."""

    @property
    @abstractmethod
    def measure_identifier(self) -> Identifier:
        """Value uniquely identifying the measure within a namespace."""

    @property
    @abstractmethod
    def name(self) -> str:
        """Name of the data quality measure applied to the data."""

    @property
    @abstractmethod
    def alias(self) -> str:
        """
        Another recognized name, an abbreviation or a short name for the same
        data quality measure.
        """

    @property
    @abstractmethod
    def element_name(self) -> TypeName:
        """Name of the data quality element for which quality is reported."""

    @property
    @abstractmethod
    def definition(self) -> str:
        """
        Definition of the fundamental concept for the data quality measure.
        """

    @property
    @abstractmethod
    def description(self) -> Description:
        """
        Description of the data quality measure, including all formulae and/or
        illustrations needed to establish the result of applying the measure.
        """

    @property
    @abstractmethod
    def value_type(self) -> TypeName:
        """
        Value type for reporting a data quality result (shall be one of the
        data types defined in ISO/19103:2005).
        """

    @property
    @abstractmethod
    def value_structure(self) -> ValueStructure:
        """Structure for reporting a complex data quality result."""

    @property
    @abstractmethod
    def example(self) -> Sequence[Description]:
        """Illustration of the use of a data quality measure."""

    @property
    @abstractmethod
    def basic_measure(self) -> BasicMeasure:
        """
        Definition of the fundamental concept for the data quality measure.
        """

    @property
    @abstractmethod
    def source_reference(self) -> Sequence[SourceReference]:
        """
        Reference to the source of an item that has been adopted from an
        external source.
        """

    @property
    @abstractmethod
    def parameter(self):
        """
        Reference to the source of an item that has been adopted from an
        external source.
        """


class TemporalQuality(Element):
    """
    Accuracy of the temporal attributes and temporal relationships of features.
    """


class Metaquality(Element):
    """Information about the reliability of data quality results."""

    @property
    @abstractmethod
    def derived_element(self) -> Sequence[Element]:
        """Derived element."""


class Confidence(Metaquality):
    """Trustworthiness of a data quality result."""


class Representativity(Metaquality):
    """Trustworthiness of a data quality result."""


class DataEvaluation(EvaluationMethod):
    """Data evaluation method."""


class SimpleBasedInspection(DataEvaluation):
    """Sample based inspection."""

    @property
    @abstractmethod
    def sampling_scheme(self) -> str:
        """
        Information of the type of sampling scheme and description of the
        sampling procedure.
        """

    @property
    @abstractmethod
    def lot_description(self) -> str:
        """Information of how lots are defined."""

    @property
    @abstractmethod
    def simple_ratio(self) -> str:
        """
        Information on how many samples on average are extracted for
        inspection from each lot of population.
        """


class IndirectEvaluation(DataEvaluation):
    """Indirect evaluation."""

    @property
    @abstractmethod
    def deductive_source(self) -> str:
        """
        Information on which data are used as sources in deductive evaluation
        method.
        """


class Homogeneity(Metaquality):
    """
    Expected or tested uniformity of the results obtained for a data quality
    evaluation.
    """


class FullInspection(DataEvaluation):
    """
    Test of every item in the population specified by the data quality scope.
    """


class DescriptiveResult(Result):
    """Data quality descriptive result."""

    @property
    @abstractmethod
    def statement(self) -> str:
        """Textual expression of the descriptive result."""


class AggregationDerivation(EvaluationMethod):
    """Aggregation or derivation method."""


class PositionalAccuracy(Element):
    """Accuracy of the position of features."""


class AbsoluteExternalPositionalAccuracy(PositionalAccuracy):
    """
    Closeness of reported coordinate values to values accepted as or being
    true.
    """


class GriddedDataPositionalAccuracy(PositionalAccuracy):
    """
    Closeness of gridded data position values to values accepted as or being
    true.
    """


class RelativeInternalPositionalAccuracy(PositionalAccuracy):
    """
    Closeness of the relative positions of features in the scope to their
    respective relative positions accepted as or being true.
    """


class TemporalConsistency(TemporalQuality):
    """Correctness of ordered events or sequences, if reported."""


class TemporalValidity(TemporalQuality):
    """Validity of data specified by the scope with respect to time."""


class AccuracyOfATimeMeasurement(TemporalQuality):
    """
    Correctness of the temporal references of an item (reporting of error in
    time measurement).
    """


class ThematicAccuracy(Element):
    """
    Accuracy of quantitative attributes and the correctness of
    non-quantitative attributes and of the classifications of features and
    their relationships.
    """


class ThematicClassificationCorrectness(ThematicAccuracy):
    """
    Comparison of the classes assigned to features or their attributes to a
    universe of discourse.
    """


class QuantitativeAttributeAccuracy(ThematicAccuracy):
    """Accuracy of quantitative attributes."""


class NonQuantitativeAttributeCorrectness(ThematicAccuracy):
    """Correctness of non-quantitative attributes."""


class LogicalConsistency(Element):
    """
    Degree of adherence to logical rules of data structure, attribution and
    relationships (data structure can be conceptual, logical or physical).
    """


class ConceptualConsistency(LogicalConsistency):
    """Adherence to rules of the conceptual schema."""


class DomainConsistency(LogicalConsistency):
    """Adherence of values to the value domains."""


class FormatConsistency(LogicalConsistency):
    """
    Degree to which data is stored in accordance with the physical structure
    of the dataset, as described by the scope.
    """


class TopologicalConsistency(LogicalConsistency):
    """
    Correctness of the explicitly encoded topological characteristics of the
    dataset as described by the scope.
    """


class Completeness(Element):
    """
    Presence and absence of features, their attributes and their relationships.
    """


class CompletenessCommission(Completeness):
    """Excess data present in the dataset, as described by the scope."""


class CompletenessOmission(Completeness):
    """Data absent from the dataset, as described by the scope."""


class ConformanceResult(Result):
    """
    Information about the outcome of evaluating the obtained value (or set of
    values) against a specified acceptable conformance quality level.
    """

    @property
    @abstractmethod
    def specification(self) -> Citation:
        """
        Citation of data product specification or user requirement against
        which data is being evaluated.
        """

    @property
    @abstractmethod
    def explanation(self) -> str:
        """Explanation of the meaning of conformance for this result."""

    @property
    @abstractmethod
    def is_conform(self):
        """Indication of the conformance result where 0 = fail and 1 = pass."""


class CoverageResult(Result):
    """
    Result of a data quality measure organising the measured values as a
    coverage.
    """

    @property
    @abstractmethod
    def spatial_representation_type(self) -> SpatialRepresentationTypeCode:
        """Method used to spatially represent the coverage result."""

    @property
    @abstractmethod
    def result_spatial_representation(self) -> SpatialRepresentation:
        """
        Gives a numerical representation of the data quality measurements
        making up the coverage result.
        """

    @property
    @abstractmethod
    def result_content(self) -> Optional[Sequence[RangeDimension]]:
        """
        Describes the content of the result coverage, when the quality
        coverage is included in the described resource, i.e. the semantic
        definition of the data quality measures.

        MANDATORY: if `result_format` and `result_file` not provided.
        """

    @property
    @abstractmethod
    def result_format(self) -> Optional[Format]:
        """
        Provides information about the data format of the result coverage data.

        MANDATORY: if `result_content` not provided.
        """

    # Below property not defined in ISO 19157:2023
    @property
    @abstractmethod
    def result_file(self) -> Optional[DataFile]:
        """
        Provides information about the data file containing the result
        coverage data.

        MANDATORY: if `result_content` not provided.
        """


class QuantitativeResult(Result):
    """
    The values or information about the value(s) (or set of values) obtained
    from applying a data quality measure.
    """

    @property
    @abstractmethod
    def value(self) -> Sequence[Record]:
        """
        Quantitative value or values, content determined by the evaluation
        procedure used, accordingly with the value type and valueStructure
        defined for the measure.
        """

    @property
    @abstractmethod
    def value_unit(self) -> UnitOfMeasure:
        """Value unit for reporting a data quality result."""

    @property
    @abstractmethod
    def value_record_type(self) -> RecordType:
        """
        Value type for reporting a data quality result, depends of the
        implementation.
        """
