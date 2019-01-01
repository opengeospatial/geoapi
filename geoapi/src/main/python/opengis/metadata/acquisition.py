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



from opengis.metadata.citation import Citation, Identifier, Responsibility

class Instrument(ABC):
    """Designations for the measuring instruments."""

    @property
    def citation(self) -> Sequence[Citation]:
        """Complete citation of the instrument."""
        return None

    @abstractproperty
    def identifier(self) -> Identifier:
        """Complete citation of the instrument."""
        pass

    @abstractproperty
    def type(self) -> str:
        """Code describing the type of instrument."""
        pass

    @property
    def description(self) -> str:
        """Textual description of the instrument."""
        return None

    @property
    def mounted_on(self) -> 'Platform':
        return None



class Platform(ABC):
    """Designations for the platform used to acquire the dataset."""

    @property
    def citation(self) -> Citation:
        """Complete citation of the platform."""
        return None

    @abstractproperty
    def identifier(self) -> Identifier:
        """Unique identification of the platform."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Narrative description of the platform supporting the instrument."""
        pass

    @property
    def sponsor(self) -> Sequence[Responsibility]:
        """Organization responsible for building, launch, or operation of the platform."""
        return None

    @abstractproperty
    def instrument(self) -> Sequence[Instrument]:
        pass



class PlatformPass(ABC):
    """Identification of collection coverage."""

    @abstractproperty
    def identifier(self) -> Identifier:
        """Unique name of the pass."""
        pass

    @property
    def extent(self):
        """Area covered by the pass."""
        return None

    @property
    def related_event(self) -> Sequence['Event']:
        return None



from datetime import datetime

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
        """Time the event occurred."""
        pass

    @property
    def related_pass(self) -> PlatformPass:
        return None

    @property
    def related_sensor(self) -> Sequence[Instrument]:
        return None

    @property
    def expected_objective(self) -> Sequence['Objective']:
        return None



class EnvironmentalRecord(ABC):

    @abstractproperty
    def average_air_temperature(self) -> float:
        pass

    @abstractproperty
    def max_relative_humidity(self) -> float:
        pass

    @abstractproperty
    def max_altitude(self) -> float:
        pass

    @abstractproperty
    def meteorological_conditions(self) -> str:
        pass



from opengis.metadata.extent import Extent

class Objective(ABC):
    """Describes the characteristics, spatial and temporal extent of the intended object to be observed."""

    @abstractproperty
    def identifier(self) -> Sequence[Identifier]:
        """Registered code used to identify the objective."""
        pass

    @property
    def priority(self) -> str:
        """Priority applied to the target."""
        return None

    @property
    def type(self) -> Sequence[ObjectiveTypeCode]:
        """Collection technique for the objective."""
        return None

    @property
    def function(self) -> Sequence[str]:
        """Function performed by or at the objective."""
        return None

    @property
    def extent(self) -> Sequence[Extent]:
        """Extent information including the bounding box, bounding polygon, vertical and temporal extent of the objective."""
        return None

    @property
    def sensing_instrument(self) -> Sequence[Instrument]:
        return None

    @property
    def platformPass(self) -> Sequence[PlatformPass]:
        return None

    @abstractproperty
    def objective_occurence(self) -> Sequence[Event]:
        pass



from opengis.metadata.identification import ProgressCode

class Operation(ABC):
    """Designations for the operation used to acquire the dataset."""

    @property
    def description(self) -> str:
        """Description of the mission on which the platform observations are part and the objectives of that mission."""
        return None

    @property
    def citation(self) -> Citation:
        """Character string by which the mission is known."""
        return None

    @property
    def identifier(self) -> Identifier:
        """Character string by which the mission is known."""
        return None

    @abstractproperty
    def status(self) -> ProgressCode:
        """Status of the data acquisition."""
        pass

    @property
    def type(self) -> OperationTypeCode:
        """Status of the data acquisition."""
        return None

    @property
    def parent_operation(self) -> 'Operation':
        return None

    @property
    def child_operation(self) -> Sequence['Operation']:
        return None

    @property
    def platform(self) -> Sequence[Platform]:
        """Platform (or platforms) used in the operation."""
        return None

    @property
    def objective(self) -> Sequence[Objective]:
        return None

    @property
    def plan(self) -> 'Plan':
        return None

    @property
    def significant_event(self) -> Sequence[Event]:
        return None



class RequestedDate(ABC):
    """Range of date validity."""

    @abstractproperty
    def requested_date_of_collection(self) -> datetime:
        """Preferred date and time of collection."""
        pass

    @abstractproperty
    def latest_acceptable_date(self) -> datetime:
        """Latest date and time collection must be completed."""
        pass



class Requirement(ABC):
    """Requirement to be satisfied by the planned data acquisition."""

    @property
    def citation(self) -> Citation:
        """Identification of reference or guidance material for the requirement."""
        return None

    @abstractproperty
    def identifier(self) -> Identifier:
        """Unique name, or code, for the requirement."""
        pass

    @abstractproperty
    def requestor(self) -> Sequence[Responsibility]:
        """Origin of requirement."""
        pass

    @abstractproperty
    def recipient(self) -> Sequence[Responsibility]:
        """Person(s), or body(ies), to receive results of requirement."""
        pass

    @abstractproperty
    def priority(self) -> PriorityCode:
        """Relative ordered importance, or urgency, of the requirement."""
        pass

    @abstractproperty
    def requested_date(self) -> RequestedDate:
        """Required or preferred acquisition date and time."""
        pass

    @abstractproperty
    def expiry_date(self) -> datetime:
        """Date and time after which collection is no longer valid."""
        pass

    @property
    def satisfied_plan(self) -> Sequence['Plan']:
        return None



class Plan(ABC):
    """Designations for the planning information related to meeting requirements."""

    @property
    def type(self) -> GeometryTypeCode:
        """Manner of sampling geometry the planner expects for collection of the objective data."""
        return None

    @abstractproperty
    def status(self) -> ProgressCode:
        """Current status of the plan (pending, completed, etc.)."""
        pass

    @abstractproperty
    def citation(self) -> Citation:
        """Identification of authority requesting target collection."""
        pass

    @property
    def operation(self) -> Sequence[Operation]:
        return None

    @property
    def satisfied_requirement(self) -> Sequence[Requirement]:
        return None



class AcquisitionInformation(ABC):
    """Designations for the measuring instruments and their bands, the platform carrying them, and the mission to which the data contributes."""

    @property
    def instrument(self) -> Sequence[Instrument]:
        return None

    @property
    def operation(self) -> Sequence[Operation]:
        return None

    @property
    def platform(self) -> Sequence[Platform]:
        return None

    @property
    def acquisition_plan(self) -> Sequence[Plan]:
        return None

    @property
    def objective(self) -> Sequence[Objective]:
        return None

    @property
    def acquisition_requirement(self) -> Sequence[Requirement]:
        return None

    @property
    def environmental_conditions(self) -> EnvironmentalRecord:
        return None
