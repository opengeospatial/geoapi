#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from enum import Enum

class EvaluationMethodTypeCode(Enum):
    DIRECT_INTERNAL = "directInternal"
    DIRECT_EXTERNAL = "directExternal"
    INDIRECT = "indirect"

class Result(ABC):
    """Generalization of more specific result classes."""

class Element(ABC):
    """Aspect of quantitative quality information."""

    @abstractproperty
    def result(self) -> Result:
        pass

    @abstractproperty
    def dateTime(self) -> datetime:
        pass

    @abstractproperty
    def nameOfMeasure(self) -> str:
        pass

    @abstractproperty
    def evaluationMethodDescription(self) -> str:
        pass

    @abstractproperty
    def measureIdentification(self) -> Identifier:
        pass

    @abstractproperty
    def evaluationMethodType(self) -> EvaluationMethodTypeCode:
        pass

    @abstractproperty
    def evaluationProcedure(self) -> Citation:
        pass

    @abstractproperty
    def measureDescription(self) -> str:
        pass

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
    def pass(self):
        """Indication of the conformance result where 0 = fail and 1 = pass."""
        pass

class CoverageResult(Result):
    """Description: Result of a data quality measure organising the measured values as a coverage
shortName: CoverageResult."""

    @abstractproperty
    def spatialRepresentationType(self) -> SpatialRepresentationTypeCode:
        """Description: method used to spatially represent the coverage result
shortName: spaRepType."""
        pass

    @abstractproperty
    def resultFile(self) -> DataFile:
        pass

    @abstractproperty
    def resultSpatialRepresentation(self) -> SpatialRepresentation:
        pass

    @abstractproperty
    def resultContentDescription(self) -> CoverageDescription:
        pass

    @abstractproperty
    def resultFormat(self) -> Format:
        pass

class QuantitativeResult(Result):
    """The values or information about the value(s) (or set of values) obtained from applying a data quality measure."""

    @abstractproperty
    def value(self) -> Record:
        """Quantitative value or values, content determined by the evaluation procedure used, accordingly with the value type and valueStructure defined for the measure."""
        pass

    @abstractproperty
    def valueUnit(self):
        """Value unit for reporting a data quality result."""
        pass

    @abstractproperty
    def valueType(self) -> RecordType:
        pass

    @abstractproperty
    def errorStatistic(self) -> str:
        pass

class DataQuality(ABC):
    """Quality information for the data specified by a data quality scope."""

    @abstractproperty
    def scope(self) -> Scope:
        """The specific data to which the data quality information applies."""
        pass

    @abstractproperty
    def report(self) -> Element:
        pass

    @abstractproperty
    def lineage(self) -> Lineage:
        pass
