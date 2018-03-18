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
    """Description: Designations for the measuring instruments
FGDC: Platform_and_Instrument_Identification
shortName: PltfrmInstId."""

    @abstractproperty
    def citation(self) -> Citation:
        """Description: complete citation of the instrument
FGDC: Instrument_Full_Name
Position: 1
shortName: instNam
Conditional: if shortName not specified."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Description: complete citation of the instrument
FGDC: Instrument_Full_Name
Position: 1
shortName: instNam
Conditional: if shortName not specified."""
        pass

    @abstractproperty
    def type(self) -> str:
        """Description: Code describing the type of instrument
shortName: instType."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Description: Textual description of the instrument
shortName: instDesc."""
        pass

    @abstractproperty
    def mountedOn(self) -> Platform:
        pass

class Platform(ABC):
    """Description: Designations for the platform used to acquire the dataset
shortName: PltfrmId."""

    @abstractproperty
    def citation(self) -> Citation:
        """Description: complete citation of the platform
FGDC: Platform_Full_Name
Position: 3
shortName: pltNam
Conditional: if shortName not specified."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Description: Unique identification of the platform
shortName: pltId."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Description: Narrative description of the platform supporting the instrument
FGDC: Platform_Description
Position: 2
shortName: pltfrmDesc."""
        pass

    @abstractproperty
    def sponsor(self) -> Responsibility:
        """Description: organization responsible for building, launch, or operation of the platform
FGDC: Platform_Sponsor
Position: 6
shortName: pltfrmSpnsr."""
        pass

    @abstractproperty
    def instrument(self) -> Instrument:
        pass

class PlatformPass(ABC):
    """Description: identification of collection coverage
shortName: PlatformPass."""

    @abstractproperty
    def identifier(self) -> Identifier:
        """Description: unique name of the pass
shortName: passId."""
        pass

    @abstractproperty
    def extent(self) -> Object:
        """Description: Area covered by the pass
shortName: passExt."""
        pass

    @abstractproperty
    def relatedEvent(self) -> Event:
        pass

class Event(ABC):
    """Description: identification of a significant collection point within an operation
shortName: Event."""

    @abstractproperty
    def identifier(self) -> Identifier:
        """Description: Event name or number
shortName: evtId."""
        pass

    @abstractproperty
    def trigger(self) -> TriggerCode:
        """Description: Initiator of the event
shortName: evtTrig."""
        pass

    @abstractproperty
    def context(self) -> ContextCode:
        """Description: Meaning of the event
shortName: evtCntxt."""
        pass

    @abstractproperty
    def sequence(self) -> SequenceCode:
        """Description: Relative time ordering of the event
shortName: evtSeq."""
        pass

    @abstractproperty
    def time(self) -> datetime:
        """Description: Time the event occured
shortName: evtTime."""
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
    """Description: Describes the characteristics, spatial and temportal extent of the intended object to be observed 
shortName: TargetId."""

    @abstractproperty
    def identifier(self) -> Identifier:
        """Description: Registered code used to identify the objective
Postion: 1
shortName: targetId."""
        pass

    @abstractproperty
    def priority(self) -> str:
        """Description: priority applied to the target
Position: 3
shortName: trgtPriority."""
        pass

    @abstractproperty
    def type(self) -> ObjectiveTypeCode:
        """Description: collection technique for the objective
Position: 4
shortName: trgtType."""
        pass

    @abstractproperty
    def function(self) -> str:
        """Description: function performed by or at the objective
Position: 5
shortName: trgtFunct."""
        pass

    @abstractproperty
    def extent(self) -> Extent:
        """Description: extent information including the bounding box, bounding polygon, vertical and temporal extent of the objective
Position: 6
shortName: trgtExtent."""
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
    """Description: Designations for the operation used to acquire the dataset
shortName: MssnId."""

    @abstractproperty
    def description(self) -> str:
        """Description: Description of the mission on which the platform observations are part and the objectives of that mission
FGDC: Mission_Description
Position: 3
shortName: mssnDesc."""
        pass

    @abstractproperty
    def citation(self) -> Citation:
        """Description: character string by which the mission is known
FGDC: Mission_Name
Position: 1
shortName: pltMssnNam
NITF_ACFTA:AC_MSN_ID."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Description: character string by which the mission is known
FGDC: Mission_Name
Position: 1
shortName: pltMssnNam
NITF_ACFTA:AC_MSN_ID."""
        pass

    @abstractproperty
    def status(self) -> ProgressCode:
        """Description: status of the data acquisition
FGDC: Mission_Start_Date
Position: 4
shortName: mssnStatus."""
        pass

    @abstractproperty
    def type(self) -> OperationTypeCode:
        """Description: status of the data acquisition
FGDC: Mission_Start_Date
Position: 4
shortName: mssnStatus."""
        pass

    @abstractproperty
    def parentOperation(self) -> Operation:
        pass

    @abstractproperty
    def childOperation(self) -> Operation:
        pass

    @abstractproperty
    def platform(self) -> Platform:
        """Description: Platform (or platforms) used in the operation."""
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
    """Description: range of date validity
shortName: ReqstDate."""

    @abstractproperty
    def requestedDateOfCollection(self) -> datetime:
        """Description: preferred date and time of collection
shortName: collectDate."""
        pass

    @abstractproperty
    def latestAcceptableDate(self) -> datetime:
        """Description: latest date and time collection must be completed
shortName: latestDate."""
        pass

class Requirement(ABC):
    """Description: requirement to be satisfied by the planned data acquisition
shortName: Requirement."""

    @abstractproperty
    def citation(self) -> Citation:
        """Description: identification of reference or guidance material for the requirement
shortName: reqRef."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Description: unique name, or code, for the requirement
shortName: reqId."""
        pass

    @abstractproperty
    def requestor(self) -> Responsibility:
        """Description: origin of requirement
shortName: requestor."""
        pass

    @abstractproperty
    def recipient(self) -> Responsibility:
        """Description: person(s), or body(ies), to recieve results of requirement
shortName: recipient."""
        pass

    @abstractproperty
    def priority(self) -> PriorityCode:
        """Description: relative ordered importance, or urgency, of the requirement
shortName: reqPri."""
        pass

    @abstractproperty
    def requestedDate(self) -> RequestedDate:
        """Description: required or preferred acquisition date and time
shortName: reqDate."""
        pass

    @abstractproperty
    def expiryDate(self) -> datetime:
        """Description: date and time after which collection is no longer valid
shortName: reqExpire."""
        pass

    @abstractproperty
    def satisfiedPlan(self) -> Plan:
        pass

class Plan(ABC):
    """Description: Designations for the planning information related to meeting requirements
shortName: PlanId."""

    @abstractproperty
    def type(self) -> GeometryTypeCode:
        """Description: manner of sampling geometry the planner expects for collection of the objective data
Postion: 2
shortName: planType."""
        pass

    @abstractproperty
    def status(self) -> ProgressCode:
        """Description: current status of the plan (pending, completed, etc.)
shortName: planStatus."""
        pass

    @abstractproperty
    def citation(self) -> Citation:
        """Description: Identification of authority requesting target collection
Postion: 1
shortName: planReqId."""
        pass

    @abstractproperty
    def operation(self) -> Operation:
        pass

    @abstractproperty
    def satisfiedRequirement(self) -> Requirement:
        pass

class AcquisitionInformation(ABC):
    """Description: Designations for the measuring instruments and their bands, the platform carrying them, and the mission to which the data contributes
FGDC: Platform_and_Instrument_Identification, Mission_Information
shortName: PltfrmInstId."""

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
