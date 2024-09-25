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
"""This is the `service` module.

This module contains geographic metadata structures regarding data services
derived from the ISO 19115-1:2014 international standard.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence
from enum import Enum
from typing import Optional

from opengis.metadata.citation import Citation, OnlineResource
from opengis.metadata.distribution import StandardOrderProcess
from opengis.metadata.identification import DataIdentification, Identification
from opengis.metadata.naming import GenericName, MemberName, ScopedName


class CouplingType(Enum):
    """Class of information to which the referencing entity applies."""

    LOOSE = "loose"
    """
    Service instance is loosely coupled with a data instance,
    i.e. no `DataIdentification` class has to be described.
    """

    MIXED = "mixed"
    """
    Service instance is mixed coupled with a data instance,

    i.e. `DataIdentification` describes the associated data
    instance and additionally the service instance might work
    with other external data instances.
    """

    TIGHT = "tight"
    """
    Service instance is tightly coupled with a data instance,
    i.e. `DataIdentification` class MUST be described.
    """


class DCPList(Enum):
    """Class of information to which the referencing entity applies."""
    XML = "XML"
    "Extensible Markup Language"

    CORBA = "CORBA"
    """Common Object request Broker Architecture"""

    JAVA = "JAVA"
    """Object-oriented programming language"""

    COM = "COM"
    """Component Object Model"""

    SQL = "SQL"
    """Structured Query Language"""

    SOAP = "SOAP"
    """Simple Object Access Protocol"""

    Z3950 = "Z3950"
    """ISO 23950"""

    HTTP = "HTTP"
    """Hypertext Transfer Protocol"""

    FTP = "FTP"
    """File Transfer Protocol"""

    WEB_SERVICES = "WebServices"
    """Web service"""


class ParameterDirection(Enum):
    """
    Identifies the parameter as an input to the process, or an output,
    or both.
    """

    IN = "in"
    """Input to a process."""

    OUT = "out"
    """Output of a process."""

    IN_OUT = "in/out"
    """Both an input and an output."""


class OperationChainMetadata(ABC):
    """Operation Chain Information."""

    @property
    @abstractmethod
    def name(self) -> str:
        """The name, as used by the service for this chain."""

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """
        A narrative explanation of the services in the chain and resulting
        output.
        """

    @property
    @abstractmethod
    def operation(self) -> Sequence['OperationMetadata']:
        """(Ordered) information about the operation applied by the chain."""


class CoupledResource(ABC):
    """
    Links a given operationName (mandatory attribute of `OperationMetadata`)
    with a dataset identified by an 'identifier'.
    """

    @property
    @abstractmethod
    def scoped_name(self) -> Optional[ScopedName]:
        """
        Scoped identifier of the resource in the context of the given service
        instance.

        Links a given `operation_name` (mandatory attribute of
        `OperationMetadata` with a resource identified by an `Identifier`.

        NOTE: name of the resources (i.e. dataset) as it is used by
        a service instance

        Example: layer name or `feature_type_name`.
        """

    @property
    @abstractmethod
    def resource_reference(self) -> Optional[Sequence[Citation]]:
        """
        Reference to the dataset on which the service operates.

        NOTE: For one resource either `resource` or `resource_reference`
        should be used but not both for the same resource.
        """

    @property
    @abstractmethod
    def resource(self) -> Optional[Sequence[DataIdentification]]:
        """
        The tightly coupled resource.

        NOTE 1: This attribute should be implemented by reference.

        NOTE 2: For one resource either `resource` or `resource_reference`
        should be used but not both for the same resource.
        """

    @property
    @abstractmethod
    def operation(self) -> Optional['OperationMetadata']:
        """
        The service operation.

        NOTE: This attribute should be implemented by reference.
        """


class ServiceIdentification(Identification):
    """
    Identification of capabilities which a service provider makes available to
    a service user through a set of interfaces that define a behaviour.

    NOTE: See ISO 19119 for further information.
    """

    @property
    @abstractmethod
    def service_type(self) -> GenericName:
        """
        A service type name, e.g., 'discovery', 'view', 'download',
        'transformation', or 'invoke'.
        """

    @property
    @abstractmethod
    def service_type_version(self) -> Optional[Sequence[str]]:
        """
        Provide for searching based on the version of serviceType.

        For example, we may only be interested in OGC Catalogue V1.1 services.
        If version is maintained as a separate attribute, users can easily
        search for all services of a type regardless of the version.
        """

    @property
    @abstractmethod
    def access_properties(self) -> Optional[StandardOrderProcess]:
        """
        Information about the availability of the service, including 'fees',
        'planned', 'available date and time', 'ordering instructions',
        and 'turnaround'.
        """

    @property
    @abstractmethod
    def coupling_type(self) -> Optional[CouplingType]:
        """
        Type of coupling between service and associated data (if exists).

        MANDATORY: if `coupled_resource` is not `None`.
        """

    @property
    @abstractmethod
    def coupled_resource(self) -> Optional[Sequence[CoupledResource]]:
        """
        Further description of the data coupling in the case of tightly
        coupled services.
        """

    @property
    @abstractmethod
    def operated_dataset(self) -> Optional[Sequence[Citation]]:
        """
        Provides a reference to the dataset on which the service operates.
        """

    @property
    @abstractmethod
    def profile(self) -> Optional[Sequence[Citation]]:
        """Profile to which the service adheres."""

    @property
    @abstractmethod
    def service_standard(self) -> Optional[Sequence[Citation]]:
        """Standard to which the service adheres."""

    @property
    @abstractmethod
    def contains_operations(self) -> Optional[Sequence['OperationMetadata']]:
        """
        Provides information about the operations that comprise the service.
        """

    @property
    @abstractmethod
    def operates_on(self) -> Optional[Sequence[DataIdentification]]:
        """
        Provides information about the resources on which the service operates.

        NOTE: Either `operated_dataset` or `operates_on`may be used but not
            both for the same resource.
        """

    @property
    @abstractmethod
    def contains_chain(self) -> Optional[Sequence[OperationChainMetadata]]:
        """Provides infromation about the chain applied by the resource."""


class Parameter(ABC):
    """Parameter information."""

    @property
    @abstractmethod
    def name(self) -> MemberName:
        """The name, as used by the service, for this parameter."""

    @property
    @abstractmethod
    def direction(self) -> ParameterDirection:
        """
        Indication if the parameter is an input to the service, an output,
        or both.
        """

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """A narrative explanation of the role of the parameter."""

    @property
    @abstractmethod
    def optionality(self) -> bool:
        """Indication if the parameter is required."""

    @property
    @abstractmethod
    def repeatability(self) -> bool:
        """
        Indication if more than one value of the parameter may be provided.
        """


class OperationMetadata(ABC):
    """
    Describes the signature of one and only one method provided by the service.
    """

    @property
    @abstractmethod
    def operation_name(self) -> str:
        """A unique identifier for this interface."""

    @property
    @abstractmethod
    def distributed_computing_platform(self) -> Sequence[DCPList]:
        """
        Distributed computing platforms on which the operation has been
        implemented.
        """

    @property
    @abstractmethod
    def operation_description(self) -> Optional[str]:
        """
        Free text description of the intent of the operation and the results
        of the operation.
        """

    @property
    @abstractmethod
    def invocation_name(self) -> Optional[str]:
        """
        The name used to invoke this interface within the context of the DCP.
        The name is identical for all DCPs.
        """

    @property
    @abstractmethod
    def connect_point(self) -> Sequence[OnlineResource]:
        """Handle for accessing the service interface."""

    @property
    @abstractmethod
    def parameters(self) -> Optional[Sequence[Parameter]]:
        """The parameters that are required for this interface."""

    @property
    @abstractmethod
    def depends_on(self) -> Optional[Sequence['OperationMetadata']]:
        """
        List of operation that must be completed immediately before current
        operation is invoked.
        """
