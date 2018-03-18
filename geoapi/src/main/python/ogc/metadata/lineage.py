#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty

class NominalResolution(ABC):
    """Description: Distance between adjacent pixels
shortName: nomRes."""

    @abstractproperty
    def scanningResolution(self) -> float:
        """Description: Distance between adjacent pixels in the scan plane
shortName: scanRes."""
        pass

    @abstractproperty
    def groundResolution(self) -> float:
        """Description: Distance between adjacent pixels in the object space
shortName: groundRes."""
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
        """Description: Processing level of the source data
shortName: procLevel."""
        pass

    @abstractproperty
    def resolution(self) -> NominalResolution:
        """Description: Distance between two adjacent pixels
shortName: procResol."""
        pass

class Algorithm(ABC):
    """Description: Details of the methodology by which geographic information was derived from the instrument readings
FGDC: Algorithm_Information
shortName: Algorithm."""

    @abstractproperty
    def citation(self) -> Citation:
        """Description: information identifying the algorithm and version or date
FGDC: Algorithm_Identifiers
Position: 1
shortName: algId."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Description: information describing the algorithm used to generate the data
FGDC: Algorithm_Description
Position: 2
shortName: algDesc."""
        pass

class Processing(ABC):
    """Description: Comprehensive information about the procedure(s), process(es) and algorithm(s) applied in the process step
shortName: Procsg."""

    @abstractproperty
    def algorithm(self) -> Algorithm:
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Description: Information to identify the processing package that produced the data
FGDC: Processing_Identifiers
Position: 1
shortName: procInfoId."""
        pass

    @abstractproperty
    def softwareReference(self) -> Citation:
        """Description: Reference to document describing processing software
FGDC: Processing_Software_Reference
Position: 2
shortName: procInfoSwRef."""
        pass

    @abstractproperty
    def procedureDescription(self) -> str:
        """Description: Additional details about the processing procedures
FGDC: Processing_Procedure_Description
Position: 3
shortName: procInfoDesc."""
        pass

    @abstractproperty
    def documentation(self) -> Citation:
        """Description: Reference to documentation describing the processing
FGDC: Processing_Documentation
Position: 4
shortName: procInfoDoc."""
        pass

    @abstractproperty
    def runTimeParameters(self) -> str:
        """Description: Parameters to control the processing operations, entered at run time
FGDC: Command_Line_Processing_Parameter
Position: 5
shortName: procInfoParam."""
        pass

class ProcessStepReport(ABC):
    """Description: Report of what occured during the process step
shortName: ProcStepRep."""

    @abstractproperty
    def name(self) -> str:
        """Description: Name of the processing report
shortName: procRepName."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Description: Textual description of what occurred during the process step
shortName: procRepDesc."""
        pass

    @abstractproperty
    def fileType(self) -> str:
        """Description: Type of file that contains that processing report
shortName: procRepFilTyp."""
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
