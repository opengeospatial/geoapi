#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from enum import Enum

class ContextCode(Enum):
    ACQUISITION = "acquisition"
    PASS = "pass"
    WAY_POINT = "wayPoint"

class GeometryTypeCode(Enum):
    POINT = "point"
    LINEAR = "linear"
    AREAL = "areal"
    STRIP = "strip"

class ObjectiveTypeCode(Enum):
    INSTANTANEOUS_COLLECTION = "instantaneousCollection"
    PERSISTENT_VIEW = "persistentView"
    SURVEY = "survey"

class OperationTypeCode(Enum):
    REAL = "real"
    SIMULATED = "simulated"
    SYNTHESIZED = "synthesized"

class PriorityCode(Enum):
    CRITICAL = "critical"
    HIGH_IMPORTANCE = "highImportance"
    MEDIUM_IMPORTANCE = "mediumImportance"
    LOW_IMPORTANCE = "lowImportance"

class SequenceCode(Enum):
    START = "start"
    END = "end"
    INSTANTANEOUS = "instantaneous"

class TriggerCode(Enum):
    AUTOMATIC = "automatic"
    MANUAL = "manual"
    PRE_PROGRAMMED = "preProgrammed"

class Instrument(ABC):
    """Designations for the measuring instruments."""

    @abstractproperty
    def citation(self) -> Citation:
        """Complete citation of the instrument."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Complete citation of the instrument."""
        pass

    @abstractproperty
    def type(self) -> str:
        """Code describing the type of instrument."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Textual description of the instrument."""
        pass

    @abstractproperty
    def mountedOn(self) -> Platform:
        pass

class Platform(ABC):
    """Designations for the platform used to acquire the dataset."""

    @abstractproperty
    def citation(self) -> Citation:
        """Complete citation of the platform."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Unique identification of the platform."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Narrative description of the platform supporting the instrument."""
        pass

    @abstractproperty
    def sponsor(self) -> Responsibility:
        """Organization responsible for building, launch, or operation of the platform."""
        pass

    @abstractproperty
    def instrument(self) -> Instrument:
        pass

class PlatformPass(ABC):
    """Identification of collection coverage."""

    @abstractproperty
    def identifier(self) -> Identifier:
        """Unique name of the pass."""
        pass

    @abstractproperty
    def extent(self) -> Object:
        """Area covered by the pass."""
        pass

    @abstractproperty
    def relatedEvent(self) -> Event:
        pass

class Event(ABC):
    """Identification of a significant collection point within an operation."""

    @abstractproperty
    def identifier(self) -> Identifier:
        """Event name or number."""
        pass

    @abstractproperty
    def trigger(self) -> TriggerCode:
        """Initiator of the event."""
        pass

    @abstractproperty
    def context(self) -> ContextCode:
        """Meaning of the event."""
        pass

    @abstractproperty
    def sequence(self) -> SequenceCode:
        """Relative time ordering of the event."""
        pass

    @abstractproperty
    def time(self) -> datetime:
        """Time the event occured."""
        pass

    @abstractproperty
    def relatedPass(self) -> PlatformPass:
        pass

    @abstractproperty
    def relatedSensor(self) -> Instrument:
        pass

    @abstractproperty
    def expectedObjective(self) -> Objective:
        pass

class EnvironmentalRecord(ABC):

    @abstractproperty
    def averageAirTemperature(self) -> float:
        pass

    @abstractproperty
    def maxRelativeHumidity(self) -> float:
        pass

    @abstractproperty
    def maxAltitude(self) -> float:
        pass

    @abstractproperty
    def meteorologicalConditions(self) -> str:
        pass

class Objective(ABC):
    """Describes the characteristics, spatial and temportal extent of the intended object to be observed."""

    @abstractproperty
    def identifier(self) -> Identifier:
        """Registered code used to identify the objective."""
        pass

    @abstractproperty
    def priority(self) -> str:
        """Priority applied to the target."""
        pass

    @abstractproperty
    def type(self) -> ObjectiveTypeCode:
        """Collection technique for the objective."""
        pass

    @abstractproperty
    def function(self) -> str:
        """Function performed by or at the objective."""
        pass

    @abstractproperty
    def extent(self) -> Extent:
        """Extent information including the bounding box, bounding polygon, vertical and temporal extent of the objective."""
        pass

    @abstractproperty
    def sensingInstrument(self) -> Instrument:
        pass

    @abstractproperty
    def pass(self) -> PlatformPass:
        pass

    @abstractproperty
    def objectiveOccurence(self) -> Event:
        pass

class Operation(ABC):
    """Designations for the operation used to acquire the dataset."""

    @abstractproperty
    def description(self) -> str:
        """Description of the mission on which the platform observations are part and the objectives of that mission."""
        pass

    @abstractproperty
    def citation(self) -> Citation:
        """Character string by which the mission is known."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Character string by which the mission is known."""
        pass

    @abstractproperty
    def status(self) -> ProgressCode:
        """Status of the data acquisition."""
        pass

    @abstractproperty
    def type(self) -> OperationTypeCode:
        """Status of the data acquisition."""
        pass

    @abstractproperty
    def parentOperation(self) -> Operation:
        pass

    @abstractproperty
    def childOperation(self) -> Operation:
        pass

    @abstractproperty
    def platform(self) -> Platform:
        """Platform (or platforms) used in the operation."""
        pass

    @abstractproperty
    def objective(self) -> Objective:
        pass

    @abstractproperty
    def plan(self) -> Plan:
        pass

    @abstractproperty
    def significantEvent(self) -> Event:
        pass

class RequestedDate(ABC):
    """Range of date validity."""

    @abstractproperty
    def requestedDateOfCollection(self) -> datetime:
        """Preferred date and time of collection."""
        pass

    @abstractproperty
    def latestAcceptableDate(self) -> datetime:
        """Latest date and time collection must be completed."""
        pass

class Requirement(ABC):
    """Requirement to be satisfied by the planned data acquisition."""

    @abstractproperty
    def citation(self) -> Citation:
        """Identification of reference or guidance material for the requirement."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Unique name, or code, for the requirement."""
        pass

    @abstractproperty
    def requestor(self) -> Responsibility:
        """Origin of requirement."""
        pass

    @abstractproperty
    def recipient(self) -> Responsibility:
        """Person(s), or body(ies), to recieve results of requirement."""
        pass

    @abstractproperty
    def priority(self) -> PriorityCode:
        """Relative ordered importance, or urgency, of the requirement."""
        pass

    @abstractproperty
    def requestedDate(self) -> RequestedDate:
        """Required or preferred acquisition date and time."""
        pass

    @abstractproperty
    def expiryDate(self) -> datetime:
        """Date and time after which collection is no longer valid."""
        pass

    @abstractproperty
    def satisfiedPlan(self) -> Plan:
        pass

class Plan(ABC):
    """Designations for the planning information related to meeting requirements."""

    @abstractproperty
    def type(self) -> GeometryTypeCode:
        """Manner of sampling geometry the planner expects for collection of the objective data."""
        pass

    @abstractproperty
    def status(self) -> ProgressCode:
        """Current status of the plan (pending, completed, etc.)."""
        pass

    @abstractproperty
    def citation(self) -> Citation:
        """Identification of authority requesting target collection."""
        pass

    @abstractproperty
    def operation(self) -> Operation:
        pass

    @abstractproperty
    def satisfiedRequirement(self) -> Requirement:
        pass

class AcquisitionInformation(ABC):
    """Designations for the measuring instruments and their bands, the platform carrying them, and the mission to which the data contributes."""

    @abstractproperty
    def instrument(self) -> Instrument:
        pass

    @abstractproperty
    def operation(self) -> Operation:
        pass

    @abstractproperty
    def platform(self) -> Platform:
        pass

    @abstractproperty
    def acquisitionPlan(self) -> Plan:
        pass

    @abstractproperty
    def objective(self) -> Objective:
        pass

    @abstractproperty
    def acquisitionRequirement(self) -> Requirement:
        pass

    @abstractproperty
    def environmentalConditions(self) -> EnvironmentalRecord:
        pass
