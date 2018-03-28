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



class CouplingType(Enum):
    LOOSE = "loose"
    MIXED = "mixed"
    TIGHT = "tight"



class DCPList(Enum):
    XML = "XML"
    CORBA = "CORBA"
    JAVA = "JAVA"
    COM = "COM"
    SQL = "SQL"
    SOAP = "SOAP"
    Z3950 = "Z3950"
    HTTP = "HTTP"
    FTP = "FTP"
    WEB_SERVICES = "WebServices"



class ParameterDirection(Enum):
    IN = "in"
    OUT = "out"
    IN_OUT = "in/out"



from ogc.metadata.citation import OnlineResource, Citation

class OperationMetadata(ABC):
    """Describes the signature of one and only one method provided by the service."""

    @abstractproperty
    def operationName(self) -> str:
        """A unique identifier for this interface."""
        pass

    @abstractproperty
    def distributedComputingPlatform(self) -> Sequence[DCPList]:
        """Distributed computing platforms on which the operation has been implemented."""
        pass

    @property
    def operationDescription(self) -> str:
        """Free text description of the intent of the operation and the results of the operation."""
        return None

    @property
    def invocationName(self) -> str:
        """The name used to invoke this interface within the context of the DCP. The name is identical for all DCPs."""
        return None

    @abstractproperty
    def connectPoint(self) -> Sequence[OnlineResource]:
        """Handle for accessing the service interface."""
        pass

    @property
    def parameter(self):
        return None

    @property
    def dependsOn(self) -> Sequence['OperationMetadata']:
        return None



class OperationChainMetadata(ABC):
    """Operation Chain Information."""

    @abstractproperty
    def name(self) -> str:
        """The name, as used by the service for this chain."""
        pass

    @property
    def description(self) -> str:
        """A narrative explanation of the services in the chain and resulting output."""
        return None

    @abstractproperty
    def operation(self) -> Sequence[OperationMetadata]:
        pass



from ogc.metadata.naming import ScopedName, GenericName
from ogc.metadata.identification import DataIdentification, Identification

class CoupledResource(ABC):
    """Links a given operationName (mandatory attribute of SV_OperationMetadata) with a data set identified by an 'identifier'."""

    @property
    def scopedName(self) -> ScopedName:
        """Scoped identifier of the resource in the context of the given service instance. NOTE: name of the resources (i.e. dataset) as it is used by a service instance (e.g. layer name or featureTypeName)."""
        return None

    @property
    def resourceReference(self) -> Sequence[Citation]:
        """Reference to the dataset on which the service operates."""
        return None

    @property
    def operation(self) -> OperationMetadata:
        return None

    @property
    def resource(self) -> Sequence[DataIdentification]:
        return None



from ogc.metadata.distribution import StandardOrderProcess

class ServiceIdentification(Identification):
    """Identification of capabilities which a service provider makes available to a service user through a set of interfaces that define a behaviour - See ISO 19119 for further information."""

    @abstractproperty
    def serviceType(self) -> GenericName:
        """A service type name, E.G. 'discovery', 'view', 'download', 'transformation', or 'invoke'."""
        pass

    @property
    def serviceTypeVersion(self) -> Sequence[str]:
        """Provide for searching based on the version of serviceType. For example, we may only be interested in OGC Catalogue V1.1 services. If version is maintained as a separate attribute, users can easily search for all services of a type regardless of the version."""
        return None

    @property
    def accessProperties(self) -> StandardOrderProcess:
        """Information about the availability of the service, including, 'fees' 'planned' 'available date and time' 'ordering instructions' 'turnaround'."""
        return None

    @property
    def couplingType(self) -> CouplingType:
        """Type of coupling between service and associated data (if exists)."""
        return None

    @property
    def coupledResource(self) -> Sequence[CoupledResource]:
        """Further description of the data coupling in the case of tightly coupled services."""
        return None

    @property
    def operatedDataset(self) -> Sequence[Citation]:
        """Provides a reference to the dataset on which the service operates."""
        return None

    @property
    def profile(self) -> Sequence[Citation]:
        return None

    @property
    def serviceStandard(self) -> Sequence[Citation]:
        return None

    @property
    def containsOperations(self) -> Sequence[OperationMetadata]:
        return None

    @property
    def operatesOn(self) -> Sequence[DataIdentification]:
        return None

    @property
    def containsChain(self) -> Sequence[OperationChainMetadata]:
        return None
