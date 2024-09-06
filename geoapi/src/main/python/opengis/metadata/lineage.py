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
"""This is the lineage module.

This module contains geographic metadata structures derived from the
ISO 19115-1:2014 and ISO 19115-2:2019 international standards regarding
the lineage of the data, that is how the data has changed and the sources
from which it is derived.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence
from datetime import datetime
from typing import Optional

from opengis.metadata.citation import Citation, Identifier, Responsibility
from opengis.metadata.identification import Resolution
from opengis.metadata.maintenance import Scope
from opengis.metadata.naming import MemberName, Record, RecordType
from opengis.metadata.service import ParameterDirection
from opengis.referencing.crs import ReferenceSystem
from opengis.util.measure import Distance


class NominalResolution(ABC):
    """
    Distance between consistent parts (centre, left side, right side) of
    adjacent pixels.
    """

    @property
    @abstractmethod
    def scanning_resolution(self) -> Distance:
        """
        Distance between consistent parts (centre, left side, right side)
        of adjacent pixels in the scan plane.
        """

    @property
    @abstractmethod
    def ground_resolution(self) -> Distance:
        """
        Distance between consistent parts (centre, left side, right side)
        of adjacent pixels in the object space.
        """


class Source(ABC):
    """
    Information about the source resource used in creating the data specified
    by the scope.
    """

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """
        Detailed description of the level of the source resource.

        MANDATORY: if `scope` is `None`.
        """

    @property
    @abstractmethod
    def source_spatial_resolution(self) -> Optional[Resolution]:
        """
        Level of detail expressed as a scale factor, a distance or an angle.
        """

    @property
    @abstractmethod
    def source_reference_system(self) -> Optional[ReferenceSystem]:
        """Spatial reference system used by the source resource."""

    @property
    @abstractmethod
    def source_citation(self) -> Optional[Citation]:
        """Recommended reference to be used for the source resource."""

    @property
    @abstractmethod
    def source_metadata(self) -> Optional[Sequence[Citation]]:
        """Identifier and link to source metadata."""

    @property
    @abstractmethod
    def scope(self) -> Optional[Scope]:
        """
        Type of resource and/or extent of the source.

        MANDATORY: if `description` is `None`.
        """

    @property
    @abstractmethod
    def source_step(self) -> Optional[Sequence['ProcessStep']]:
        """Information about process steps in which this source was used."""

    @property
    @abstractmethod
    def processed_level(self) -> Optional[Identifier]:
        """Processing level of the source data."""

    @property
    @abstractmethod
    def resolution(self) -> Optional[NominalResolution]:
        """
        Distance between consistent parts (centre, left side, right side)
        of two adjacent pixels.
        """


class Algorithm(ABC):
    """
    Details of the methodology by which geographic information was derived
    from the instrument readings.
    """

    @property
    @abstractmethod
    def citation(self) -> Citation:
        """Information identifying the algorithm and version or date."""

    @property
    @abstractmethod
    def description(self) -> str:
        """Information describing the algorithm used to generate the data."""


class ProcessParameter(ABC):
    """Parameter (value or resource) used in a process."""

    @property
    @abstractmethod
    def name(self) -> MemberName:
        """Name/type of parameter."""

    @property
    @abstractmethod
    def direction(self) -> ParameterDirection:
        """
        Indication the parameter is an input to the process, an output,
        or both.
        """

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """Narrative explaining the role of the parameter."""

    @property
    @abstractmethod
    def optionality(self) -> bool:
        """Indication the parameter is required."""

    @property
    @abstractmethod
    def repeatability(self) -> bool:
        """
        Indication if more than one value of the parameter may be provided.
        """

    @property
    @abstractmethod
    def value_type(self) -> Optional[RecordType]:
        """Data type of the value"""

    @property
    @abstractmethod
    def value(self) -> Optional[Record]:
        """Constant value."""

    @property
    @abstractmethod
    def resource(self) -> Optional[Source]:
        """Resource to be processed."""


class Processing(ABC):
    """
    Comprehensive information about the procedure(s), process(es) and
    algorithm(s) applied in the process step.
    """

    @property
    @abstractmethod
    def identifier(self) -> Identifier:
        """
        Information to identify the processing package that produced the data.
        """

    @property
    @abstractmethod
    def software_reference(self) -> Optional[Sequence[Citation]]:
        """Reference to document describing processing software."""

    @property
    @abstractmethod
    def procedure_description(self) -> Optional[str]:
        """Additional details about the processing procedures."""

    @property
    @abstractmethod
    def documentation(self) -> Optional[Sequence[Citation]]:
        """Reference to documentation describing the processing."""

    @property
    @abstractmethod
    def run_time_parameters(self) -> Optional[str]:
        """
        Parameters to control the processing operations, entered at run time.
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
    def algorithm(self) -> Optional[Sequence[Algorithm]]:
        """
        Details of the methodology by which geographic information was derived
        from the instrument readings.
        """

    @property
    @abstractmethod
    def parameter(self) -> Optional[ProcessParameter]:
        """Parameter(s) used in a process"""


class ProcessStepReport(ABC):
    """Report of what occurred during the process step."""

    @property
    @abstractmethod
    def name(self) -> str:
        """Name of the processing report."""

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """Textual description of what occurred during the process step."""

    @property
    @abstractmethod
    def file_type(self) -> Optional[str]:
        """Type of file that contains that processing report."""


class ProcessStep(ABC):
    """
    Information about an event or transformation in the life of the dataset
    including details of the algorithm and software used for processing.
    """

    @property
    @abstractmethod
    def description(self) -> str:
        """
        Description of the event, including related parameters or tolerances.
        """

    @property
    @abstractmethod
    def rationale(self) -> Optional[str]:
        """Requirement or purpose for the process step."""

    @property
    @abstractmethod
    def step_date_time(self) -> Optional[datetime]:
        """Date, time, range or period of process step."""

    @property
    @abstractmethod
    def processor(self) -> Optional[Sequence[Responsibility]]:
        """
        Identification of, and means of communication with, person(s) and
        organisation(s) associated with the process step.
        """

    @property
    @abstractmethod
    def reference(self) -> Optional[Sequence[Citation]]:
        """Process step documentation."""

    @property
    @abstractmethod
    def scope(self) -> Optional[Scope]:
        """Type of resource and/or extent to which the process step applies."""

    @property
    @abstractmethod
    def source(self) -> Optional[Sequence[Source]]:
        """
        Type of the resource and/or extent to which the process step applies.
        """

    @property
    @abstractmethod
    def output(self) -> Optional[Sequence[Source]]:
        """
        Description of the product generated as a result of the process step.
        """

    @property
    @abstractmethod
    def processing_information(self) -> Optional[Processing]:
        """
        Comprehensive information about the procedure by which the algorithm
        was applied to derive geographic data from the raw instrument
        measurements, such as datasets, software used, and the processing
        environment.
        """

    @property
    @abstractmethod
    def report(self) -> Optional[Sequence[ProcessStepReport]]:
        """Report generated by the process step."""


class Lineage(ABC):
    """
    Information about the events or source data used in constructing the data
    specified by the scope or lack of knowledge about lineage.
    """

    @property
    @abstractmethod
    def statement(self) -> Optional[str]:
        """
        General explanation of the data producer's knowledge about the lineage
        of a resource.
        """

    @property
    @abstractmethod
    def scope(self) -> Optional[Scope]:
        """
        Type of resource and/or extent to which the lineage information
        applies.
        """

    @property
    @abstractmethod
    def additional_documentation(self) -> Optional[Sequence[Citation]]:
        """
        Resource. Example: A publication that describes the whole process to
        generate this resource, e.g., a dataset.
        """

    @property
    @abstractmethod
    def process_step(self) -> Optional[Sequence[ProcessStep]]:
        """
        Information about events in the life of a resource specified by the
        scope.

        MANDATORY: if `statement` and `source` are `None`.
        """

    @property
    @abstractmethod
    def source(self) -> Optional[Sequence[Source]]:
        """
        Information about the source data used in creating the data specified
        by the scope.

        MANDATORY: if `statement` and `process_step` are `None`.
        """
