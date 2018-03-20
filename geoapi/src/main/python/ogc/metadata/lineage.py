#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty

class NominalResolution(ABC):
    """Distance between adjacent pixels."""

    @abstractproperty
    def scanningResolution(self) -> float:
        """Distance between adjacent pixels in the scan plane."""
        pass

    @abstractproperty
    def groundResolution(self) -> float:
        """Distance between adjacent pixels in the object space."""
        pass

class Source(ABC):
    """Information about the source resource used in creating the data specified by the scope."""

    @abstractproperty
    def description(self) -> str:
        """Detailed description of the level of the source resource."""
        pass

    @abstractproperty
    def sourceSpatialResolution(self) -> Resolution:
        """Level of detail expressed as a scale factor, a distance or an angle."""
        pass

    @abstractproperty
    def sourceReferenceSystem(self) -> ReferenceSystem:
        """Spatial reference system used by the source resource."""
        pass

    @abstractproperty
    def sourceCitation(self) -> Citation:
        """Recommended reference to be used for the source resource."""
        pass

    @abstractproperty
    def sourceMetadata(self) -> Citation:
        """Identifier and link to source metadata."""
        pass

    @abstractproperty
    def scope(self) -> Scope:
        """Type of resource and/or extent of the source."""
        pass

    @abstractproperty
    def sourceStep(self) -> ProcessStep:
        pass

    @abstractproperty
    def processedLevel(self) -> Identifier:
        """Processing level of the source data."""
        pass

    @abstractproperty
    def resolution(self) -> NominalResolution:
        """Distance between two adjacent pixels."""
        pass

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

    @abstractproperty
    def algorithm(self) -> Algorithm:
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Information to identify the processing package that produced the data."""
        pass

    @abstractproperty
    def softwareReference(self) -> Citation:
        """Reference to document describing processing software."""
        pass

    @abstractproperty
    def procedureDescription(self) -> str:
        """Additional details about the processing procedures."""
        pass

    @abstractproperty
    def documentation(self) -> Citation:
        """Reference to documentation describing the processing."""
        pass

    @abstractproperty
    def runTimeParameters(self) -> str:
        """Parameters to control the processing operations, entered at run time."""
        pass

class ProcessStepReport(ABC):
    """Report of what occured during the process step."""

    @abstractproperty
    def name(self) -> str:
        """Name of the processing report."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Textual description of what occurred during the process step."""
        pass

    @abstractproperty
    def fileType(self) -> str:
        """Type of file that contains that processing report."""
        pass

class ProcessStep(ABC):
    """Information about an event or transformation in the life of a resource including the process used to maintain the resource."""

    @abstractproperty
    def description(self) -> str:
        """Description of the event, including related parameters or tolerances."""
        pass

    @abstractproperty
    def rationale(self) -> str:
        """Requirement or purpose for the process step."""
        pass

    @abstractproperty
    def stepDateTime(self) -> datetime:
        """Date, time, range or period of process step."""
        pass

    @abstractproperty
    def processor(self) -> Responsibility:
        """Identification of, and means of communication with, person(s) and organisation(s) associated with the process step."""
        pass

    @abstractproperty
    def reference(self) -> Citation:
        """Process step documentation."""
        pass

    @abstractproperty
    def scope(self) -> Scope:
        """Type of resource and/or extent to which the process step applies."""
        pass

    @abstractproperty
    def source(self) -> Source:
        pass

    @abstractproperty
    def processingInformation(self) -> Processing:
        pass

    @abstractproperty
    def report(self) -> ProcessStepReport:
        pass

    @abstractproperty
    def output(self) -> Source:
        pass

class Lineage(ABC):
    """Information about the events or source data used in constructing the data specified by the scope or lack of knowledge about lineage."""

    @abstractproperty
    def statement(self) -> str:
        """General explanation of the data producer's knowledge about the lineage of a resource."""
        pass

    @abstractproperty
    def scope(self) -> Scope:
        """Type of resource and/or extent to which the lineage information applies."""
        pass

    @abstractproperty
    def additionalDocumentation(self) -> Citation:
        pass

    @abstractproperty
    def source(self) -> Source:
        pass

    @abstractproperty
    def processStep(self) -> ProcessStep:
        pass
