#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from enum import Enum

class MediumFormatCode(Enum):
    CPIO = "cpio"
    TAR = "tar"
    HIGH_SIERRA = "highSierra"
    ISO_9660 = "iso9660"
    ISO_9660_ROCK_RIDGE = "iso9660RockRidge"
    ISO_9660_APPLE_HFS = "iso9660AppleHFS"
    UDF = "udf"

class Medium(ABC):
    """Information about the media on which the resource can be distributed."""

    @abstractproperty
    def name(self) -> MediumNameCode:
        """Name of the medium on which the resource can be received."""
        pass

    @abstractproperty
    def density(self) -> float:
        """Density at which the data is recorded."""
        pass

    @abstractproperty
    def densityUnits(self):
        """Units of measure for the recording density."""
        pass

    @abstractproperty
    def volumes(self) -> int:
        """Number of items in the media identified."""
        pass

    @abstractproperty
    def mediumFormat(self) -> MediumFormatCode:
        """Method used to write to the medium."""
        pass

    @abstractproperty
    def mediumNote(self) -> str:
        """Description of other limitations or requirements for using the medium."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        pass

class Format(ABC):
    """Description of the computer language construct that specifies the representation of data objects in a record, file, message, storage device or transmission channel."""

    @abstractproperty
    def formatSpecificationCitation(self) -> Citation:
        """Citation/URL of the specification for the format."""
        pass

    @abstractproperty
    def amendmentNumber(self) -> str:
        """Amendment number of the format version."""
        pass

    @abstractproperty
    def fileDecompressionTechnique(self) -> str:
        """Recommendations of algorithms or processes that can be applied to read or expand resources to which compression techniques have been applied."""
        pass

    @abstractproperty
    def medium(self) -> Medium:
        """Medium used by the format."""
        pass

    @abstractproperty
    def formatDistributor(self) -> Distributor:
        pass

class DigitalTransferOptions(ABC):
    """Technical means and media by which a resource is obtained from the distributor."""

    @abstractproperty
    def unitsOfDistribution(self) -> str:
        """Tiles, layers, geographic areas, etc., in which data is available. NOTE: unitsOfDistribution applies to both onLine and offLine distributions."""
        pass

    @abstractproperty
    def transferSize(self) -> float:
        """Estimated size of a unit in the specified transfer format, expressed in megabytes. The transfer size is > 0.0."""
        pass

    @abstractproperty
    def onLine(self) -> OnlineResource:
        """Information about online sources from which the resource can be obtained."""
        pass

    @abstractproperty
    def offLine(self) -> Medium:
        """Information about offline media on which the resource can be obtained."""
        pass

    @abstractproperty
    def transferFrequency(self) -> PeriodDuration:
        """Rate of occurrence of distribution."""
        pass

    @abstractproperty
    def distributionFormat(self) -> Format:
        """Format of distribution."""
        pass

class StandardOrderProcess(ABC):
    """Common ways in which the resource may be obtained or received, and related instructions and fee information."""

    @abstractproperty
    def fees(self) -> str:
        """Fees and terms for retrieving the resource. Include monetary units (as specified in ISO 4217)."""
        pass

    @abstractproperty
    def plannedAvailableDateTime(self) -> datetime:
        """Date and time when the resource will be available."""
        pass

    @abstractproperty
    def orderingInstructions(self) -> str:
        """General instructions, terms and services provided by the distributor."""
        pass

    @abstractproperty
    def turnaround(self) -> str:
        """Typical turnaround time for the filling of an order."""
        pass

    @abstractproperty
    def orderOptionsType(self) -> RecordType:
        """Description of the order options record."""
        pass

    @abstractproperty
    def orderOptions(self) -> Record:
        """Request/purchase choices."""
        pass

class Distributor(ABC):
    """Information about the distributor."""

    @abstractproperty
    def distributorContact(self) -> Responsibility:
        """Party from whom the resource may be obtained. This list need not be exhaustive."""
        pass

    @abstractproperty
    def distributionOrderProcess(self) -> StandardOrderProcess:
        pass

    @abstractproperty
    def distributorFormat(self) -> Format:
        pass

    @abstractproperty
    def distributorTransferOptions(self) -> DigitalTransferOptions:
        pass

class Distribution(ABC):
    """Information about the distributor of and options for obtaining the resource."""

    @abstractproperty
    def description(self) -> str:
        pass

    @abstractproperty
    def distributionFormat(self) -> Format:
        pass

    @abstractproperty
    def distributor(self) -> Distributor:
        pass

    @abstractproperty
    def transferOptions(self) -> DigitalTransferOptions:
        pass
