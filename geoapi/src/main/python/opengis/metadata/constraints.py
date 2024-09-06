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
"""This is the constraints module.

This module contains geographic metadata structures regarding data constraints
derived from the ISO 19115-1:2014 international standard.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux(Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence
from enum import Enum
from typing import Optional

from opengis.metadata.citation import (
    Citation,
    Responsibility,
)
from opengis.metadata.identification import BrowseGraphic
from opengis.metadata.maintenance import Scope


class ClassificationCode(Enum):
    """Name of the handling restrictions on the resource."""

    UNCLASSIFIED = "unclassified"
    """Available for general disclosure."""

    RESTRICTED = "restricted"
    """Not for general disclosure."""

    CONFIDENTIAL = "confidential"
    """Available for someone who can be entrusted with information."""

    SECRET = "secret"
    """
    Kept or meant to be kept private, unknown, or hidden from all but a select
    group of people.
    """

    TOP_SECRET = "topSecret"
    """Of the highest secrecy."""

    SENSITIVE_BUT_UNCLASSIFIED = "sensitiveButUnclassified"
    """
    Although unclassified, requries strict controls over its distribution.
    """

    FOR_OFFICIAL_USE_ONLY = "forOfficialUseOnly"
    """
    Unclassified information that is to be used only for official purposes
    determined by the designating body.
    """

    PROTECTED = "protected"
    """Compromise of the information could cause damage."""

    LIMITED_DISTRIBUTION = "limitedDistribution"
    """Destination limited by designating body."""


class RestrictionCode(Enum):
    """Limitation(s) placed upon the access or use of the data."""

    COPYRIGHT = "copyright"
    """
    Exclusive right tot he publication, production, or sale of the rights to a
    literary, dramatic, musical, or artistic work, or to the use of a
    commercial print or label, granted by law for a specified period of time
    to an author, composer, artist, distributor.
    """

    PATENT = "patent"
    """
    Government has granted exclusive right to make, sell, use or license an
    invention or discovery.
    """

    PATENT_PENDING = "patentPending"
    """Produced or sold information awaiting a patent."""

    TRADEMARK = "trademark"
    """
    A name, symbol, or other device identifying a product, officially
    registered and legally restricted to the use of the owner or manufacturer.
    """

    LICENCE = "licence"
    """Formal permission to do something."""

    INTELLECTUAL_PROPERTY_RIGHTS = "intellectualPropertyRights"
    """
    Rights to financial benefit from and control of distribution of
    non-tangible property that is a result of creativity.
    """

    RESTRICTED = "restricted"
    """Withheld from general circulation or disclosure."""

    OTHER_RESTRICTIONS = "otherRestrictions"
    """Limitation not listed."""

    UNRESTRICTED = "unrestricted"
    """No constraints exist."""

    LICENCE_UNRESTRICTED = "licenceUnrestricted"
    """Formal permission not required to use resource."""

    LICENCE_END_USER = "licenceEndUser"
    """
    Fromal permission required for a person pr an entity to use the resource
    and that may differ from the person that orders or purchases it.
    """

    LICENCE_DISTRIBUTOR = "licenceDistributor"
    """
    Formal permission required for a person or entity to commercialize or
    distribute the resource.
    """

    PRIVATE = "private"
    """
    Protects rights of individual or organisations from observation,
    intrusion, or attention of others.
    """

    STATUTORY = "statutory"
    """Prescribed by law."""

    CONFIDENTIAL = "confidential"
    """
    Not available to the public.

    NOTE: Contains information that could be prejudicial to a commercial,
    industrial, or national interest.
    """

    SENSITIVE_BUT_UNCLASSIFIED = "sensitiveButUnclassified"
    """
    Although unclassified, requires strict controls over its distribution.
    """

    IN_CONFIDENCE = "in-confidence"
    "With trust."


class Releasability(ABC):
    """
    State, nation or organisation to which resource can be released,
    e.g., NATO unclassified releasable to PfP.
    """

    @property
    @abstractmethod
    def addressee(self) -> Optional[Sequence[Responsibility]]:
        """
        Party to which the release statement applies.

        MANDATORY: if `statement` is `None`.
        """

    @property
    @abstractmethod
    def statement(self) -> Optional[str]:
        """
        Release statement.

        MANDATORY: if `addressee` is `None`.
        """

    @property
    @abstractmethod
    def dissemination_constraints(self) -> Optional[Sequence[RestrictionCode]]:
        """Component in determining releasability."""


class Constraints(ABC):
    """Restrictions on the access and use of a resource or metadata."""

    @property
    @abstractmethod
    def use_limitation(self) -> Optional[Sequence[str]]:
        """
        Limitation affecting the fitness for use of the resource or metadata.
        For example, "not to be used for navigation".
        """

    @property
    @abstractmethod
    def constraint_application_scope(self) -> Optional[Scope]:
        """
        Spatial and temporal extent of the application of the constraint
        restrictions.
        """

    @property
    @abstractmethod
    def graphic(self) -> Optional[Sequence[BrowseGraphic]]:
        """Graphic /symbol indicating the constraint Eg."""

    @property
    @abstractmethod
    def reference(self) -> Optional[Sequence[Citation]]:
        """
        Citation/URL for the limitation or constraint,
        e.g., copyright statement, license agreement, etc.
        """

    @property
    @abstractmethod
    def releasability(self) -> Optional[Releasability]:
        """
        Information concerning the parties to whom the resource can or cannot
        be released and the party responsible for determining the
        releasibility.
        """

    @property
    @abstractmethod
    def responsible_party(self) -> Optional[Sequence[Responsibility]]:
        """Party responsible for the resource constraints."""


class LegalConstraints(Constraints):
    """
    Restrictions and legal prerequisites for accessing and using the resource
    or metadata.
    """

    @property
    @abstractmethod
    def access_constraints(self) -> Optional[Sequence[RestrictionCode]]:
        """
        Access constraints applied to assure the protection of privacy or
        intellectual property, and any special restrictions or limitations on
        obtaining the resource or metadata.

        MANDATORY: if `use_constraints`, `other_constrints`, `use_limitations`,
            or `releasability` are `None`.
        """

    @property
    @abstractmethod
    def use_constraints(self) -> Optional[Sequence[RestrictionCode]]:
        """
        Constraints applied to assure the protection of privacy or
        intellectual property, and any special restrictions or limitations or
        warnings on using the resource or metadata.

        MANDATORY: if `access_constraints`, `other_constrints`,
            `use_limitations`, or `releasability` are `None`.
        """

    @property
    @abstractmethod
    def other_constraints(self) -> Optional[Sequence[str]]:
        """
        Other restrictions and legal prerequisites for accessing and using the
        resource or metadata.

        MANDATORY: if `access_constraints`, `use_constrints`,
            `use_limitations`, or `releasability` are `None`.
        """


class SecurityConstraints(Constraints):
    """
    Handling restrictions imposed on the resource or metadata for national
    security or similar security concerns.
    """

    @property
    @abstractmethod
    def classification(self) -> ClassificationCode:
        """Name of the handling restrictions on the resource or metadata."""

    @property
    @abstractmethod
    def user_note(self) -> Optional[str]:
        """
        Explanation of the application of the legal constraints or other
        restrictions and legal prerequisites for obtaining and using the
        resource or metadata.
        """

    @property
    @abstractmethod
    def classification_system(self) -> Optional[str]:
        """Name of the classification system."""

    @property
    @abstractmethod
    def handling_description(self) -> Optional[str]:
        """
        Additional information about the restrictions on handling the resource
        or metadata.
        """
