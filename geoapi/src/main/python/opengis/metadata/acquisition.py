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
"""This is the acquisition module.

This subpackage contains geographic metadata structures regarding data
acquisition that are derived from the ISO 19115-2:2019 international
standard.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"


from abc import ABC, abstractmethod
from collections.abc import Sequence
from datetime import datetime, date
from enum import Enum
from typing import Optional

from opengis.metadata.citation import Citation, Identifier, Responsibility
from opengis.metadata.constraints import Constraints
from opengis.metadata.extent import Extent
from opengis.metadata.identification import ProgressCode
from opengis.metadata.naming import Record, RecordType
from opengis.metadata.maintenance import Scope


class ContextCode(Enum):
    """
    Designation of criterion for defining the context of the scanning
    process event.
    """

    ACQUISITION = "acquisition"
    """Event related to a specific collection."""

    PASS = "pass"
    """Event related to a sequence of collections."""

    WAY_POINT = "wayPoint"
    """Event related to a navigational manoeuvre."""


class EventTypeCode(Enum):
    """Type of event related to a platform, instrument, or sensor."""

    ANNOUNCEMENT = "announcement"
    """
    Announcementannouncement about future events relevant to the
    platform/instrument/sensor.
    """

    CALIBRATION = "calibration"
    """Calibration event for the platform/instrument/sensor."""

    CALIBRATION_COEFFICIENT_UPDATE = "calibrationCoefficientUpdate"
    """Update of calibration information for the platform/instrument/sensor."""

    DATA_LOSS = "dataLoss"
    """Event related to data loss."""

    FATAL = "fatal"
    """Event that renders the platform/instrument/sensor unusable."""

    MANOEUVRE = "manoeuvre"
    """Event related to a manoeuvre of the platform/instrument/sensor."""

    MISSING_DATA = "missingData"
    """Event related to missing data from the platform/instrument/sensor."""

    NOTICE = "notice"
    """Notice about events related to the platform/instrument/sensor."""

    PRELAUNCH = "prelaunch"
    """Event related to prelaunch period for the platform/instrument/sensor."""

    SEVERE = "severe"
    """
    Event with significant impact on the performance of the platform/
    instrument/sensor.
    """

    SWITCH_OFF = "switchOff"
    """Event related to switching off platform/instrument/sensor."""

    SWITCH_ON = "switchOn"
    """Event related to switching on platform/instrument/sensor."""

    CLEAN = "clean"
    """Event related to cleaning the platform/instrument/sensor."""


class GeometryTypeCode(Enum):
    """Geometric description of the collection."""

    POINT = "point"
    """Single geographic point of interest."""

    LINEAR = "linear"
    """Extended collection in a single vector."""

    AREAL = "areal"
    """Collection of a geographic area defined by a polygon (coverage)."""

    STRIP = "strip"
    """Series of linear collections grouped by way points."""


class ObjectiveTypeCode(Enum):
    """Temporal persistence of collection objective."""

    INSTANTANEOUS_COLLECTION = "instantaneousCollection"
    """Single instance of collection."""

    PERSISTENT_VIEW = "persistentView"
    """Multiple instances of collection."""

    SURVEY = "survey"
    """Collection over specified domain."""


class OperationTypeCode(Enum):
    """
    Code indicating whether the data contained in this packet is real
    (originates from live-fly or other non-simulated operational sources),
    simulated (originates from target simulator sources), or synthesized
    (a mix of real and simulated data).
    """

    REAL = "real"
    """Originates from live-fly or other non-simulated operational source."""

    SIMULATED = "simulated"
    """Originates from target simulator sources."""

    SYNTHESIZED = "synthesized"
    """Mix of real and simulated data."""


class PriorityCode(Enum):
    """Ordered list of priorities."""

    CRITICAL = "critical"
    """Of decisive importance."""

    HIGH_IMPORTANCE = "highImportance"
    """Requires resources to be made available."""

    MEDIUM_IMPORTANCE = "mediumImportance"
    """Normal operation priority."""

    LOW_IMPORTANCE = "lowImportance"
    """To be completed when resources are available."""


class SequenceCode(Enum):
    """Temporal relation of activation."""

    START = "start"
    """Beginning of collection."""

    END = "end"
    """End of collection."""

    INSTANTANEOUS = "instantaneous"
    """Collection without a significant duration."""


class TriggerCode(Enum):
    """Mechanism of activation."""

    AUTOMATIC = "automatic"
    """Event due to external stimuli."""

    MANUAL = "manual"
    """Event manually instigated."""

    PRE_PROGRAMMED = "preProgrammed"
    """Event instaigated by planned internal stimuli."""


class Revision(ABC):
    """History of the revision of an event"""

    @property
    @abstractmethod
    def description(self) -> str:
        """Description of the revision."""

    @property
    @abstractmethod
    def responsible_party(self) -> Sequence[Responsibility]:
        """Individual or organisation responsible for the revision."""

    @property
    @abstractmethod
    def date_info(self) -> Sequence[date]:
        """Information about dates related to the revision."""


class InstrumentEvent(ABC):
    """An event related to a platform, instrument, or sensor."""

    @property
    @abstractmethod
    def citation(self) -> Optional[Sequence[Citation]]:
        """Citation to the `InstrumentEvent`."""

    @property
    @abstractmethod
    def description(self) -> str:
        """Description of the `InstumentEvent`."""

    @property
    @abstractmethod
    def extent(self) -> Optional[Sequence[Extent]]:
        """Extent of the `InstrumentEvent`."""

    @property
    @abstractmethod
    def type(self) -> EventTypeCode:
        """Type of the `InstrumentEvent`."""

    @property
    @abstractmethod
    def revision_history(self) -> Optional[Sequence[Revision]]:
        """History of the revisions of the `InstrumentEvent`."""


class InstrumentEventList(ABC):
    """List of events relaed to platform, instrument, or sensor."""

    @property
    @abstractmethod
    def citation(self) -> Optional[Sequence[Citation]]:
        """Citation to the `InstrumentEventList`."""

    @property
    @abstractmethod
    def description(self) -> str:
        """
        Description of the language and character set used for the
        `InstrumentationEventList`.
        """

    @property
    @abstractmethod
    def locale(self) -> Optional[str]:
        """
        Description of the language and character set used for the
        `InstrumentationEventList`. A string conforming to IETF BCP 47.
        """

    @property
    @abstractmethod
    def constraints(self) -> Optional[Sequence[Constraints]]:
        """Use and access constraints."""

    @property
    @abstractmethod
    def instrumentation_event(self) -> Optional[Sequence[InstrumentEvent]]:
        """Events(s) in the list of events."""


class Instrument(ABC):
    """Designations for the measuring instruments."""

    @property
    @abstractmethod
    def citation(self) -> Optional[Sequence[Citation]]:
        """Complete citation of the instrument."""

    @property
    @abstractmethod
    def identifier(self) -> Identifier:
        """Unique identification of the instrument."""

    @property
    @abstractmethod
    def type(self) -> str:
        """
        Name of the type of instrument. Examples: framing, line-scan,
        push-broom, pan-frame.
        """

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """Textual description of the instrument."""

    @property
    @abstractmethod
    def mounted_on(self) -> Optional['Platform']:
        """Platform on which the instrument is mounted"""

    @property
    @abstractmethod
    def sensor(self) -> Optional[Sequence['Sensor']]:
        """Instrument has a sensor."""

    @property
    @abstractmethod
    def history(self) -> Optional['InstrumentEventList']:
        """List of events associated with the instrument."""


class Sensor(Instrument):
    """Specific type of instrument"""

    @property
    @abstractmethod
    def hosted(self) -> Optional[Instrument]:
        """Instrument on which the sensor is hosted"""


class Platform(ABC):
    """Designations for the platform used to acquire the dataset."""

    @property
    @abstractmethod
    def citation(self) -> Optional[Citation]:
        """Complete citation of the platform."""

    @property
    @abstractmethod
    def identifier(self) -> Identifier:
        """Unique identification of the platform."""

    @property
    @abstractmethod
    def description(self) -> str:
        """Narrative description of the platform supporting the instrument."""

    @property
    @abstractmethod
    def sponsor(self) -> Optional[Sequence[Responsibility]]:
        """
        Organisation responsible for building, launch, or operation of the
        platform.
        """

    @property
    @abstractmethod
    def other_property(self) -> Optional[Record]:
        """Instance of other property type not included in `Sensor`."""

    @property
    @abstractmethod
    def other_property_type(self) -> Optional[RecordType]:
        """Type of other property description."""

    @property
    @abstractmethod
    def instrument(self) -> Sequence[Instrument]:
        """Instrument(s) mounted on a platform"""

    @property
    @abstractmethod
    def history(self) -> Optional[Sequence[InstrumentEventList]]:
        """List of events affecting a platform."""


class PlatformPass(ABC):
    """Identification of collection coverage. Identifies a particular pass
    made by the platform during data acquisition. A platform pass is used to
    provide supporting identifying information for an event and for data
    acquisition of a particular objective."""

    @property
    @abstractmethod
    def identifier(self) -> Identifier:
        """Unique name of the pass."""

    @property
    @abstractmethod
    def extent(self) -> Optional[Extent]:
        """Temporal and spatial extent of the pass."""

    @property
    @abstractmethod
    def related_event(self) -> Optional[Sequence['Event']]:
        """Occurrence of one or more events for a pass."""


class Event(ABC):
    """
    Identification of a significant collection point within an
    operation.
    """

    @property
    @abstractmethod
    def identifier(self) -> Identifier:
        """Event name or number."""

    @property
    @abstractmethod
    def trigger(self) -> TriggerCode:
        """Initiator of the event."""

    @property
    @abstractmethod
    def context(self) -> ContextCode:
        """Meaning of the event."""

    @property
    @abstractmethod
    def sequence(self) -> SequenceCode:
        """Relative time ordering of the event."""

    @property
    @abstractmethod
    def time(self) -> datetime:
        """Time the event occurred."""

    @property
    @abstractmethod
    def expected_objective(self) -> Optional[Sequence['Objective']]:
        """An objective expected to be completed by the event."""

    @property
    @abstractmethod
    def related_pass(self) -> PlatformPass:
        """A `PlatformPass` related to the `Event`."""

    @property
    @abstractmethod
    def related_sensor(self) -> Sequence[Instrument]:
        """An `Instrument` related to
            the event."""


class EnvironmentalRecord(ABC):
    """
    Information about the environmental conditions during the acquisition.
    """

    @property
    @abstractmethod
    def average_air_temperature(self) -> Optional[float]:
        """
        Average air temperature along the flight pass during the photo flight.
        """

    @property
    @abstractmethod
    def max_relative_humidity(self) -> Optional[float]:
        """
        Maximum realative humitiy along the flight pass during the photo
        flight.
        """

    @property
    @abstractmethod
    def max_altitude(self) -> Optional[float]:
        """
        Maximum altitude during the photo flight.
        """

    @property
    @abstractmethod
    def meteorological_conditions(self) -> Optional[str]:
        """
        Meteorological conditions in the photo flight area, in particular
        clouds, snow, and wind.
        """

    @property
    @abstractmethod
    def solar_azimuth(self) -> Optional[float]:
        """
        Clockwise angle in degrees from north to the centre of the sun's disc.

        NOTE: This Angle is calculated from the nadir point of the sensor, not
        the centre point of the image.
        """

    @property
    @abstractmethod
    def solar_elevation(self) -> Optional[float]:
        """
        Angle between the horizonand the centre of the sun's disk.
        """


class Objective(ABC):
    """
    Describes the characteristics, spatial and temporal extent of the intended
    object to be observed.
    """

    @property
    @abstractmethod
    def identifier(self) -> Sequence[Identifier]:
        """Registered code used to identify the objective."""

    @property
    @abstractmethod
    def priority(self) -> Optional[str]:
        """Priority applied to the target."""

    @property
    @abstractmethod
    def type(self) -> Optional[Sequence[ObjectiveTypeCode]]:
        """Collection technique for the objective."""

    @property
    @abstractmethod
    def function(self) -> Optional[Sequence[str]]:
        """Function performed by or at the objective."""

    @property
    @abstractmethod
    def extent(self) -> Optional[Sequence[Extent]]:
        """
        Extent information including the bounding box, bounding polygon,
        vertical and temporal extent of the objective.
        """

    @property
    @abstractmethod
    def objective_occurence(self) -> Optional[Sequence[Event]]:
        """Event or events associated with objective completion."""

    @property
    @abstractmethod
    def platform_pass(self) -> Optional[Sequence[PlatformPass]]:
        """Pass of the platform over the objective."""

    @property
    @abstractmethod
    def sensing_instrument(self) -> Optional[Sequence[Instrument]]:
        """Instrument which senses the objective data."""


class Operation(ABC):
    """Designations for the operation used to acquire the dataset."""

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """
        Description of the mission on which the platform observations are part
        and the objectives of that mission.
        """

    @property
    @abstractmethod
    def citation(self) -> Optional[Citation]:
        """Identification of the mission."""

    @property
    @abstractmethod
    def identifier(self) -> Optional[Identifier]:
        """Unique identification of the operation."""

    @property
    @abstractmethod
    def status(self) -> ProgressCode:
        """Status of the data acquisition."""

    @property
    @abstractmethod
    def type(self) -> Optional[OperationTypeCode]:
        """Collection technique for the operation."""

    @property
    @abstractmethod
    def other_property(self) -> Optional[Record]:
        """Instance of other property type not included in `Sensor`."""

    @property
    @abstractmethod
    def other_property_type(self) -> Optional[RecordType]:
        """Type of other property description."""

    @property
    @abstractmethod
    def child_operation(self) -> Optional[Sequence['Operation']]:
        """Sub-missions that make up part of a larger mission."""

    @property
    @abstractmethod
    def objective(self) -> Optional[Sequence[Objective]]:
        """Object(s) or area(s) of interest to be sensed."""

    @property
    @abstractmethod
    def parent_operation(self) -> 'Operation':
        """Heritage of the operation."""

    @property
    @abstractmethod
    def plan(self) -> Optional['Plan']:
        """Plan satisfied by th operation."""

    @property
    @abstractmethod
    def platform(self) -> Optional[Sequence[Platform]]:
        """Platform (or platforms) used in the operation."""

    @property
    @abstractmethod
    def significant_event(self) -> Optional[Sequence[Event]]:
        """Record of an event occuring during an operation."""


class RequestedDate(ABC):
    """Range of date validity."""

    @property
    @abstractmethod
    def requested_date_of_collection(self) -> datetime:
        """Preferred date and time of collection."""

    @property
    @abstractmethod
    def latest_acceptable_date(self) -> datetime:
        """Latest date and time collection must be completed."""


class Requirement(ABC):
    """Requirement to be satisfied by the planned data acquisition."""

    @property
    @abstractmethod
    def citation(self) -> Optional[Citation]:
        """
        Identification of reference or guidance material for the requirement.
        """

    @property
    @abstractmethod
    def identifier(self) -> Identifier:
        """Unique name, or code, for the requirement."""

    @property
    @abstractmethod
    def requestor(self) -> Sequence[Responsibility]:
        """Origin of requirement."""

    @property
    @abstractmethod
    def recipient(self) -> Sequence[Responsibility]:
        """Person(s), or body(ies), to receive results of requirement."""

    @property
    @abstractmethod
    def priority(self) -> PriorityCode:
        """Relative ordered importance, or urgency, of the requirement."""

    @property
    @abstractmethod
    def requested_date(self) -> RequestedDate:
        """Required or preferred acquisition date and time."""

    @property
    @abstractmethod
    def expiry_date(self) -> datetime:
        """Date and time after which collection is no longer valid."""

    @property
    @abstractmethod
    def satisfied_plan(self) -> Optional[Sequence['Plan']]:
        """Plan that identifies solution to satisfy the requirement."""


class Plan(ABC):
    """
    Designations for the planning information related to meeting requirements.
    """

    @property
    @abstractmethod
    def type(self) -> Optional[GeometryTypeCode]:
        """
        Manner of sampling geometry the planner expects for collection of the
        objective data.
        """

    @property
    @abstractmethod
    def status(self) -> ProgressCode:
        """Current status of the plan (pending, completed, etc.)."""

    @property
    @abstractmethod
    def citation(self) -> Citation:
        """Identification of authority requesting target collection."""

    @property
    @abstractmethod
    def operation(self) -> Optional[Sequence[Operation]]:
        """Identification of the activity or activities that satisfy a plan."""

    @property
    @abstractmethod
    def satisfied_requirement(self) -> Optional[Sequence[Requirement]]:
        """Requirement satisfied by the plan."""


class AcquisitionInformation(ABC):
    """
    Designations for the measuring instruments and their bands, the platform
    carrying them, and the mission to which the data contributes.
    """

    @property
    @abstractmethod
    def scope(self) -> Optional[Sequence[Scope]]:
        """The specific data to which the acquisition information applies."""

    @property
    @abstractmethod
    def acquisition_plan(self) -> Optional[Sequence[Plan]]:
        """Identifies the plan as implemented by the acquisition."""

    @property
    @abstractmethod
    def acquisition_requirement(self) -> Optional[Sequence[Requirement]]:
        """
        Identifies the requirement the data acquisition intends to satisfy.
        """

    @property
    @abstractmethod
    def environmental_conditions(self) -> Optional[EnvironmentalRecord]:
        """
        A record of the environmental circumstances during the data
        acquisition.
        """

    @property
    @abstractmethod
    def instrument(self) -> Optional[Sequence[Instrument]]:
        """
        General information about the instrument used in data acquisition.
        """

    @property
    @abstractmethod
    def objective(self) -> Optional[Sequence[Objective]]:
        """Identification of the area or object to be sensed."""

    @property
    @abstractmethod
    def operation(self) -> Optional[Sequence[Operation]]:
        """
        General information about an identifiable activity which provided the
        data.
        """

    @property
    @abstractmethod
    def platform(self) -> Optional[Sequence[Platform]]:
        """
        General information about the platform from which the data were taken.
        """
