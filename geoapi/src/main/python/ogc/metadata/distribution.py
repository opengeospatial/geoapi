#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from typing import Sequence
from enum import Enum
from ogc.metadata.citation import Identifier, Citation, OnlineResource, Responsibility
from ogc.metadata.naming import LocalName, RecordType, Record

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

    @property
    def name(self) -> 'MediumNameCode':
        """Name of the medium on which the resource can be received."""
        return None

    @property
    def density(self) -> float:
        """Density at which the data is recorded."""
        return None

    @property
    def densityUnits(self):
        """Units of measure for the recording density."""
        return None

    @property
    def volumes(self) -> int:
        """Number of items in the media identified."""
        return None

    @property
    def mediumFormat(self) -> Sequence[MediumFormatCode]:
        """Method used to write to the medium."""
        return None

    @property
    def mediumNote(self) -> str:
        """Description of other limitations or requirements for using the medium."""
        return None

    @property
    def identifier(self) -> Identifier:
        return None

class Format(ABC):
    """Description of the computer language construct that specifies the representation of data objects in a record, file, message, storage device or transmission channel."""

    @abstractproperty
    def formatSpecificationCitation(self) -> Citation:
        """Citation/URL of the specification for the format."""
        pass

    @property
    def amendmentNumber(self) -> str:
        """Amendment number of the format version."""
        return None

    @property
    def fileDecompressionTechnique(self) -> str:
        """Recommendations of algorithms or processes that can be applied to read or expand resources to which compression techniques have been applied."""
        return None

    @property
    def medium(self) -> Sequence[Medium]:
        """Medium used by the format."""
        return None

    @property
    def formatDistributor(self) -> Sequence['Distributor']:
        return None

class DataFile(ABC):

    @property
    def featureType(self) -> Sequence[LocalName]:
        return None

class DigitalTransferOptions(ABC):
    """Technical means and media by which a resource is obtained from the distributor."""

    @property
    def unitsOfDistribution(self) -> str:
        """Tiles, layers, geographic areas, etc., in which data is available. NOTE: unitsOfDistribution applies to both onLine and offLine distributions."""
        return None

    @property
    def transferSize(self) -> float:
        """Estimated size of a unit in the specified transfer format, expressed in megabytes. The transfer size is > 0.0."""
        return None

    @property
    def onLine(self) -> Sequence[OnlineResource]:
        """Information about online sources from which the resource can be obtained."""
        return None

    @property
    def offLine(self) -> Sequence[Medium]:
        """Information about offline media on which the resource can be obtained."""
        return None

    @property
    def transferFrequency(self):
        """Rate of occurrence of distribution."""
        return None

    @property
    def distributionFormat(self) -> Sequence[Format]:
        """Format of distribution."""
        return None

class StandardOrderProcess(ABC):
    """Common ways in which the resource may be obtained or received, and related instructions and fee information."""

    @property
    def fees(self) -> str:
        """Fees and terms for retrieving the resource. Include monetary units (as specified in ISO 4217)."""
        return None

    @property
    def plannedAvailableDateTime(self) -> datetime:
        """Date and time when the resource will be available."""
        return None

    @property
    def orderingInstructions(self) -> str:
        """General instructions, terms and services provided by the distributor."""
        return None

    @property
    def turnaround(self) -> str:
        """Typical turnaround time for the filling of an order."""
        return None

    @property
    def orderOptionsType(self) -> RecordType:
        """Description of the order options record."""
        return None

    @property
    def orderOptions(self) -> Record:
        """Request/purchase choices."""
        return None

class Distributor(ABC):
    """Information about the distributor."""

    @abstractproperty
    def distributorContact(self) -> Responsibility:
        """Party from whom the resource may be obtained. This list need not be exhaustive."""
        pass

    @property
    def distributionOrderProcess(self) -> Sequence[StandardOrderProcess]:
        return None

    @property
    def distributorFormat(self) -> Sequence[Format]:
        return None

    @property
    def distributorTransferOptions(self) -> Sequence[DigitalTransferOptions]:
        return None

class Distribution(ABC):
    """Information about the distributor of and options for obtaining the resource."""

    @property
    def description(self) -> str:
        return None

    @property
    def distributionFormat(self) -> Sequence[Format]:
        return None

    @property
    def distributor(self) -> Sequence[Distributor]:
        return None

    @property
    def transferOptions(self) -> Sequence[DigitalTransferOptions]:
        return None
