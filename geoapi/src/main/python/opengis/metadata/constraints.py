#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from typing import Sequence
from enum import Enum



class ClassificationCode(Enum):
    UNCLASSIFIED = "unclassified"
    RESTRICTED = "restricted"
    CONFIDENTIAL = "confidential"
    SECRET = "secret"
    TOP_SECRET = "topSecret"
    SENSITIVE_BUT_UNCLASSIFIED = "sensitiveButUnclassified"
    FOR_OFFICIAL_USE_ONLY = "forOfficialUseOnly"
    PROTECTED = "protected"
    LIMITED_DISTRIBUTION = "limitedDistribution"



class RestrictionCode(Enum):
    COPYRIGHT = "copyright"
    PATENT = "patent"
    PATENT_PENDING = "patentPending"
    TRADEMARK = "trademark"
    LICENCE = "licence"
    INTELLECTUAL_PROPERTY_RIGHTS = "intellectualPropertyRights"
    RESTRICTED = "restricted"
    OTHER_RESTRICTIONS = "otherRestrictions"
    UNRESTRICTED = "unrestricted"
    LICENCE_UNRESTRICTED = "licenceUnrestricted"
    LICENCE_END_USER = "licenceEndUser"
    LICENCE_DISTRIBUTOR = "licenceDistributor"
    PRIVATE = "private"
    STATUTORY = "statutory"
    CONFIDENTIAL = "confidential"
    SENSITIVE_BUT_UNCLASSIFIED = "sensitiveButUnclassified"
    IN_CONFIDENCE = "in-confidence"



from opengis.metadata.citation import Responsibility, Citation

class Releasability(ABC):
    """State, nation or organization to which resource can be released to e.g. NATO unclassified releasable to PfP."""

    @property
    def addressee(self) -> Sequence[Responsibility]:
        """Party responsible for the Release statement."""
        return None

    @property
    def statement(self) -> str:
        """Release statement."""
        return None

    @property
    def dissemination_constraints(self) -> Sequence[RestrictionCode]:
        """Component in determining releasability."""
        return None



from opengis.metadata.maintenance import Scope

class Constraints(ABC):
    """Restrictions on the access and use of a resource or metadata."""

    @property
    def use_limitation(self) -> Sequence[str]:
        """Limitation affecting the fitness for use of the resource or metadata. Example, "not to be used for navigation"."""
        return None

    @property
    def constraint_application_scope(self) -> Scope:
        """Spatial and temporal extent of the application of the constraint restrictions."""
        return None

    @property
    def graphic(self) -> Sequence['BrowseGraphic']:
        """Graphic /symbol indicating the constraint Eg."""
        return None

    @property
    def reference(self) -> Sequence[Citation]:
        """Citation/URL for the limitation or constraint, e.g. copyright statement, license agreement, etc."""
        return None

    @property
    def releasability(self) -> Releasability:
        """Information concerning the parties to whom the resource can or cannot be released and the party responsible for determining the releasibility."""
        return None

    @property
    def responsible_party(self) -> Sequence[Responsibility]:
        """Party responsible for the resource constraints."""
        return None



class LegalConstraints(Constraints):
    """Restrictions and legal prerequisites for accessing and using the resource or metadata."""

    @property
    def access_constraints(self) -> Sequence[RestrictionCode]:
        """Access constraints applied to assure the protection of privacy or intellectual property, and any special restrictions or limitations on obtaining the resource or metadata."""
        return None

    @property
    def use_constraints(self) -> Sequence[RestrictionCode]:
        """Constraints applied to assure the protection of privacy or intellectual property, and any special restrictions or limitations or warnings on using the resource or metadata."""
        return None

    @property
    def other_constraints(self) -> Sequence[str]:
        """Other restrictions and legal prerequisites for accessing and using the resource or metadata."""
        return None



class SecurityConstraints(Constraints):
    """Handling restrictions imposed on the resource or metadata for national security or similar security concerns."""

    @abstractproperty
    def classification(self) -> ClassificationCode:
        """Name of the handling restrictions on the resource or metadata."""
        pass

    @property
    def user_note(self) -> str:
        """Explanation of the application of the legal constraints or other restrictions and legal prerequisites for obtaining and using the resource or metadata."""
        return None

    @property
    def classification_system(self) -> str:
        """Name of the classification system."""
        return None

    @property
    def handling_description(self) -> str:
        """Additional information about the restrictions on handling the resource or metadata."""
        return None
