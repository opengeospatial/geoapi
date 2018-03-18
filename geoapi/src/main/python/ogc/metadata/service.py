#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
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

class OperationMetadata(ABC):
    """Describes the signature of one and only one method provided by the service."""

    @abstractproperty
    def operationName(self) -> str:
        """A unique identifier for this interface."""
        pass

    @abstractproperty
    def distributedComputingPlatform(self) -> DCPList:
        """Distributed computing platforms on which the operation has been implemented."""
        pass

    @abstractproperty
    def operationDescription(self) -> str:
        """Free text description of the intent of the operation and the results of the operation."""
        pass

    @abstractproperty
    def invocationName(self) -> str:
        """The name used to invoke this interface within the context of the DCP. The name is identical for all DCPs."""
        pass

    @abstractproperty
    def connectPoint(self) -> OnlineResource:
        """Handle for accessing the service interface."""
        pass

    @abstractproperty
    def parameter(self) -> OperationParameter:
        pass

    @abstractproperty
    def dependsOn(self) -> OperationMetadata:
        pass

class OperationChainMetadata(ABC):
    """Operation Chain Information."""

    @abstractproperty
    def name(self) -> str:
        """The name, as used by the service for this chain."""
        pass

    @abstractproperty
    def description(self) -> str:
        """A narrative explanation of the services in the chain and resulting output."""
        pass

    @abstractproperty
    def operation(self) -> OperationMetadata:
        pass

class CoupledResource(ABC):
    """Links a given operationName (mandatory attribute of SV_OperationMetadata) with a data set identified by an 'identifier'."""

    @abstractproperty
    def scopedName(self) -> ScopedName:
        """Scoped identifier of the resource in the context of the given service instance. NOTE: name of the resources (i.e. dataset) as it is used by a service instance (e.g. layer name or featureTypeName)."""
        pass

    @abstractproperty
    def resourceReference(self) -> Citation:
        """Reference to the dataset on which the service operates."""
        pass

    @abstractproperty
    def operation(self) -> OperationMetadata:
        pass

    @abstractproperty
    def resource(self) -> DataIdentification:
        pass

class ServiceIdentification(Identification):
    """Identification of capabilities which a service provider makes available to a service user through a set of interfaces that define a behaviour - See ISO 19119 for further information."""

    @abstractproperty
    def serviceType(self) -> GenericName:
        """A service type name, E.G. 'discovery', 'view', 'download', 'transformation', or 'invoke'."""
        pass

    @abstractproperty
    def serviceTypeVersion(self) -> str:
        """Provide for searching based on the version of serviceType. For example, we may only be interested in OGC Catalogue V1.1 services. If version is maintained as a separate attribute, users can easily search for all services of a type regardless of the version."""
        pass

    @abstractproperty
    def accessProperties(self) -> StandardOrderProcess:
        """Information about the availability of the service, including, 'fees' 'planned' 'available date and time' 'ordering instructions' 'turnaround'."""
        pass

    @abstractproperty
    def couplingType(self) -> CouplingType:
        """Type of coupling between service and associated data (if exists)."""
        pass

    @abstractproperty
    def coupledResource(self) -> CoupledResource:
        """Further description of the data coupling in the case of tightly coupled services."""
        pass

    @abstractproperty
    def operatedDataset(self) -> Citation:
        """Provides a reference to the dataset on which the service operates."""
        pass

    @abstractproperty
    def profile(self) -> Citation:
        pass

    @abstractproperty
    def serviceStandard(self) -> Citation:
        pass

    @abstractproperty
    def containsOperations(self) -> OperationMetadata:
        pass

    @abstractproperty
    def operatesOn(self) -> DataIdentification:
        pass

    @abstractproperty
    def containsChain(self) -> OperationChainMetadata:
        pass
