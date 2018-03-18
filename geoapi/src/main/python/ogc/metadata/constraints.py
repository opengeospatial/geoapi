#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
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

class Releasability(ABC):
    """State, nation or organization to which resource can be released to e.g. NATO unclassified releasable to PfP."""

    @abstractproperty
    def addressee(self) -> Responsibility:
        """Party responsible for the Release statement."""
        pass

    @abstractproperty
    def statement(self) -> str:
        """Release statement."""
        pass

    @abstractproperty
    def disseminationConstraints(self) -> RestrictionCode:
        """Component in determining releasability."""
        pass

class Constraints(ABC):
    """Restrictions on the access and use of a resource or metadata."""

    @abstractproperty
    def useLimitation(self) -> str:
        """Limitation affecting the fitness for use of the resource or metadata. Example, "not to be used for navigation"."""
        pass

    @abstractproperty
    def constraintApplicationScope(self) -> Scope:
        """Spatial and temporal extent of the application of the constraint restrictions."""
        pass

    @abstractproperty
    def graphic(self) -> BrowseGraphic:
        """Graphic /symbol indicating the constraint Eg."""
        pass

    @abstractproperty
    def reference(self) -> Citation:
        """Citation/URL for the limitation or constraint, e.g. copyright statement, license agreement, etc."""
        pass

    @abstractproperty
    def releasability(self) -> Releasability:
        """Information concerning the parties to whom the resource can or cannot be released and the party responsible for determining the releasibility."""
        pass

    @abstractproperty
    def responsibleParty(self) -> Responsibility:
        """Party responsible for the resource constraints."""
        pass

class LegalConstraints(Constraints):
    """Restrictions and legal prerequisites for accessing and using the resource or metadata."""

    @abstractproperty
    def accessConstraints(self) -> RestrictionCode:
        """Access constraints applied to assure the protection of privacy or intellectual property, and any special restrictions or limitations on obtaining the resource or metadata."""
        pass

    @abstractproperty
    def useConstraints(self) -> RestrictionCode:
        """Constraints applied to assure the protection of privacy or intellectual property, and any special restrictions or limitations or warnings on using the resource or metadata."""
        pass

    @abstractproperty
    def otherConstraints(self) -> str:
        """Other restrictions and legal prerequisites for accessing and using the resource or metadata."""
        pass

class SecurityConstraints(Constraints):
    """Handling restrictions imposed on the resource or metadata for national security or similar security concerns."""

    @abstractproperty
    def classification(self) -> ClassificationCode:
        """Name of the handling restrictions on the resource or metadata."""
        pass

    @abstractproperty
    def userNote(self) -> str:
        """Explanation of the application of the legal constraints or other restrictions and legal prerequisites for obtaining and using the resource or metadata."""
        pass

    @abstractproperty
    def classificationSystem(self) -> str:
        """Name of the classification system."""
        pass

    @abstractproperty
    def handlingDescription(self) -> str:
        """Additional information about the restrictions on handling the resource or metadata."""
        pass
