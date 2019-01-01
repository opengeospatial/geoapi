#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from typing import Sequence



class NominalResolution(ABC):
    """Distance between adjacent pixels."""

    @property
    def scanning_resolution(self) -> float:
        """Distance between adjacent pixels in the scan plane."""
        return None

    @property
    def ground_resolution(self) -> float:
        """Distance between adjacent pixels in the object space."""
        return None



from opengis.metadata.identification import Resolution
from opengis.metadata.citation import Citation, Identifier, Responsibility
from opengis.metadata.maintenance import Scope

class Source(ABC):
    """Information about the source resource used in creating the data specified by the scope."""

    @property
    def description(self) -> str:
        """Detailed description of the level of the source resource."""
        return None

    @property
    def source_spatial_resolution(self) -> Resolution:
        """Level of detail expressed as a scale factor, a distance or an angle."""
        return None

    @property
    def source_reference_system(self):
        """Spatial reference system used by the source resource."""
        return None

    @property
    def source_citation(self) -> Citation:
        """Recommended reference to be used for the source resource."""
        return None

    @property
    def source_metadata(self) -> Sequence[Citation]:
        """Identifier and link to source metadata."""
        return None

    @property
    def scope(self) -> Scope:
        """Type of resource and/or extent of the source."""
        return None

    @property
    def source_step(self) -> Sequence['ProcessStep']:
        return None

    @property
    def processed_level(self) -> Identifier:
        """Processing level of the source data."""
        return None

    @property
    def resolution(self) -> NominalResolution:
        """Distance between two adjacent pixels."""
        return None



class Algorithm(ABC):
    """Details of the methodology by which geographic information was derived from the instrument readings."""

    @abstractproperty
    def citation(self) -> Citation:
        """Information identifying the algorithm and version or date."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Information describing the algorithm used to generate the data."""
        pass



class Processing(ABC):
    """Comprehensive information about the procedure(s), process(es) and algorithm(s) applied in the process step."""

    @property
    def algorithm(self) -> Sequence[Algorithm]:
        return None

    @abstractproperty
    def identifier(self) -> Identifier:
        """Information to identify the processing package that produced the data."""
        pass

    @property
    def software_reference(self) -> Sequence[Citation]:
        """Reference to document describing processing software."""
        return None

    @property
    def procedure_description(self) -> str:
        """Additional details about the processing procedures."""
        return None

    @property
    def documentation(self) -> Sequence[Citation]:
        """Reference to documentation describing the processing."""
        return None

    @property
    def run_time_parameters(self) -> str:
        """Parameters to control the processing operations, entered at run time."""
        return None



class ProcessStepReport(ABC):
    """Report of what occurred during the process step."""

    @abstractproperty
    def name(self) -> str:
        """Name of the processing report."""
        pass

    @property
    def description(self) -> str:
        """Textual description of what occurred during the process step."""
        return None

    @property
    def file_type(self) -> str:
        """Type of file that contains that processing report."""
        return None



from datetime import datetime

class ProcessStep(ABC):
    """Information about an event or transformation in the life of a resource including the process used to maintain the resource."""

    @abstractproperty
    def description(self) -> str:
        """Description of the event, including related parameters or tolerances."""
        pass

    @property
    def rationale(self) -> str:
        """Requirement or purpose for the process step."""
        return None

    @property
    def step_date_time(self) -> datetime:
        """Date, time, range or period of process step."""
        return None

    @property
    def processor(self) -> Sequence[Responsibility]:
        """Identification of, and means of communication with, person(s) and organisation(s) associated with the process step."""
        return None

    @property
    def reference(self) -> Sequence[Citation]:
        """Process step documentation."""
        return None

    @property
    def scope(self) -> Scope:
        """Type of resource and/or extent to which the process step applies."""
        return None

    @property
    def source(self) -> Sequence[Source]:
        return None

    @property
    def processing_information(self) -> Processing:
        return None

    @property
    def report(self) -> Sequence[ProcessStepReport]:
        return None

    @property
    def output(self) -> Sequence[Source]:
        return None



class Lineage(ABC):
    """Information about the events or source data used in constructing the data specified by the scope or lack of knowledge about lineage."""

    @property
    def statement(self) -> str:
        """General explanation of the data producer's knowledge about the lineage of a resource."""
        return None

    @property
    def scope(self) -> Scope:
        """Type of resource and/or extent to which the lineage information applies."""
        return None

    @property
    def additional_documentation(self) -> Sequence[Citation]:
        return None

    @property
    def source(self) -> Sequence[Source]:
        return None

    @property
    def process_step(self) -> Sequence[ProcessStep]:
        return None
