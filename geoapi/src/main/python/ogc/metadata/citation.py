#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from enum import Enum

class DateTypeCode(Enum):
    CREATION = "creation"
    PUBLICATION = "publication"
    REVISION = "revision"
    EXPIRY = "expiry"
    LAST_UPDATE = "lastUpdate"
    LAST_REVISION = "lastRevision"
    NEXT_UPDATE = "nextUpdate"
    UNAVAILABLE = "unavailable"
    IN_FORCE = "inForce"
    ADOPTED = "adopted"
    DEPRECATED = "deprecated"
    SUPERSEDED = "superseded"
    VALIDITY_BEGINS = "validityBegins"
    VALIDITY_EXPIRES = "validityExpires"
    RELEASED = "released"
    DISTRIBUTION = "distribution"

class OnLineFunctionCode(Enum):
    DOWNLOAD = "download"
    INFORMATION = "information"
    OFFLINE_ACCESS = "offlineAccess"
    ORDER = "order"
    SEARCH = "search"
    COMPLETE_METADATA = "completeMetadata"
    BROWSE_GRAPHIC = "browseGraphic"
    UPLOAD = "upload"
    EMAIL_SERVICE = "emailService"
    BROWSING = "browsing"
    FILE_ACCESS = "fileAccess"

class PresentationFormCode(Enum):
    DOCUMENT_DIGITAL = "documentDigital"
    DOCUMENT_HARDCOPY = "documentHardcopy"
    IMAGE_DIGITAL = "imageDigital"
    IMAGE_HARDCOPY = "imageHardcopy"
    MAP_DIGITAL = "mapDigital"
    MAP_HARDCOPY = "mapHardcopy"
    MODEL_DIGITAL = "modelDigital"
    MODEL_HARDCOPY = "modelHardcopy"
    PROFILE_DIGITAL = "profileDigital"
    PROFILE_HARDCOPY = "profileHardcopy"
    TABLE_DIGITAL = "tableDigital"
    TABLE_HARDCOPY = "tableHardcopy"
    VIDEO_DIGITAL = "videoDigital"
    VIDEO_HARDCOPY = "videoHardcopy"
    AUDIO_DIGITAL = "audioDigital"
    AUDIO_HARDCOPY = "audioHardcopy"
    MULTIMEDIA_DIGITAL = "multimediaDigital"
    MULTIMEDIA_HARDCOPY = "multimediaHardcopy"
    PHYSICAL_OBJECT = "physicalObject"
    DIAGRAM_DIGITAL = "diagramDigital"
    DIAGRAM_HARDCOPY = "diagramHardcopy"

class RoleCode(Enum):
    RESOURCE_PROVIDER = "resourceProvider"
    CUSTODIAN = "custodian"
    OWNER = "owner"
    USER = "user"
    DISTRIBUTOR = "distributor"
    ORIGINATOR = "originator"
    POINT_OF_CONTACT = "pointOfContact"
    PRINCIPAL_INVESTIGATOR = "principalInvestigator"
    PROCESSOR = "processor"
    PUBLISHER = "publisher"
    AUTHOR = "author"
    SPONSOR = "sponsor"
    CO_AUTHOR = "coAuthor"
    COLLABORATOR = "collaborator"
    EDITOR = "editor"
    MEDIATOR = "mediator"
    RIGHTS_HOLDER = "rightsHolder"
    CONTRIBUTOR = "contributor"
    FUNDER = "funder"
    STAKEHOLDER = "stakeholder"

class TelephoneTypeCode(Enum):
    VOICE = "voice"
    FACSIMILE = "facsimile"
    SMS = "sms"

class Series(ABC):
    """Information about the series, or aggregate resource, to which a resource belongs."""

    @abstractproperty
    def name(self) -> str:
        """Name of the series, or aggregate resource, of which the resource is a part."""
        pass

    @abstractproperty
    def issueIdentification(self) -> str:
        """Information identifying the issue of the series."""
        pass

    @abstractproperty
    def page(self) -> str:
        """Details on which pages of the publication the article was published."""
        pass

class Address(ABC):
    """Location of the responsible individual or organisation."""

    @abstractproperty
    def deliveryPoint(self) -> str:
        """Address line for the location (as described in ISO 11180, Annex A)."""
        pass

    @abstractproperty
    def city(self) -> str:
        """City of the location."""
        pass

    @abstractproperty
    def administrativeArea(self) -> str:
        """State, province of the location."""
        pass

    @abstractproperty
    def postalCode(self) -> str:
        """ZIP or other postal code."""
        pass

    @abstractproperty
    def country(self) -> str:
        """Country of the physical address."""
        pass

    @abstractproperty
    def electronicMailAddress(self) -> str:
        """Address of the electronic mailbox of the responsible organisation or individual."""
        pass

class Telephone(ABC):
    """Telephone numbers for contacting the responsible individual or organisation."""

    @abstractproperty
    def number(self) -> str:
        """Telephone number by which individuals can contact responsible organisation or individual."""
        pass

    @abstractproperty
    def numberType(self) -> TelephoneTypeCode:
        """Type of telephone responsible organisation or individual."""
        pass

class OnlineResource(ABC):
    """Information about on-line sources from which the resource, specification, or community profile name and extended metadata elements can be obtained."""

    @abstractproperty
    def linkage(self):
        """Location (address) for on-line access using a Uniform Resource Locator/Uniform Resource Identifier address or similar addressing scheme such as http://www.statkart.no/isotc211."""
        pass

    @abstractproperty
    def protocol(self) -> str:
        """Connection protocol to be used e.g. http, ftp, file."""
        pass

    @abstractproperty
    def applicationProfile(self) -> str:
        """Name of an application profile that can be used with the online resource."""
        pass

    @abstractproperty
    def name(self) -> str:
        """Name of the online resource."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Detailed text description of what the online resource is/does."""
        pass

    @abstractproperty
    def function(self) -> OnLineFunctionCode:
        """Code for function performed by the online resource."""
        pass

    @abstractproperty
    def protocolRequest(self) -> str:
        """Protocol used by the accessed resource."""
        pass

class Contact(ABC):
    """Information required to enable contact with the responsible person and/or organisation."""

    @abstractproperty
    def phone(self) -> Telephone:
        """Telephone numbers at which the organisation or individual may be contacted."""
        pass

    @abstractproperty
    def address(self) -> Address:
        """Physical and email address at which the organisation or individual may be contacted."""
        pass

    @abstractproperty
    def onlineResource(self) -> OnlineResource:
        """On-line information that can be used to contact the individual or organisation."""
        pass

    @abstractproperty
    def hoursOfService(self) -> str:
        """Time period (including time zone) when individuals can contact the organisation or individual."""
        pass

    @abstractproperty
    def contactInstructions(self) -> str:
        """Supplemental instructions on how or when to contact the individual or organisation."""
        pass

    @abstractproperty
    def contactType(self) -> str:
        pass

class Party(ABC):
    """Information about the individual and/or organisation of the party."""

    @abstractproperty
    def name(self) -> str:
        """Name of the party."""
        pass

    @abstractproperty
    def contactInfo(self) -> Contact:
        """Contact information for the party."""
        pass

class Responsibility(ABC):
    """Information about the party and their role."""

    @abstractproperty
    def role(self) -> RoleCode:
        """Function performed by the responsible party."""
        pass

    @abstractproperty
    def extent(self) -> Extent:
        """Spatial or temporal extent of the role."""
        pass

    @abstractproperty
    def party(self) -> Party:
        pass

class Individual(Party):
    """Information about the party if the party is an individual."""

    @abstractproperty
    def positionName(self) -> str:
        """Position of the individual in an organisation."""
        pass

class Organisation(Party):
    """Information about the party if the party is an organisation."""

    @abstractproperty
    def logo(self) -> BrowseGraphic:
        """Graphic identifying organization."""
        pass

    @abstractproperty
    def individual(self) -> Individual:
        pass

class Date(ABC):
    """Reference date and event used to describe it."""

    @abstractproperty
    def date(self) -> datetime:
        """Reference date for the cited resource."""
        pass

    @abstractproperty
    def dateType(self) -> DateTypeCode:
        """Event used for reference date."""
        pass

class Citation(ABC):
    """Standardized resource reference."""

    @abstractproperty
    def title(self) -> str:
        """Name by which the cited resource is known."""
        pass

    @abstractproperty
    def alternateTitle(self) -> str:
        """Short name or other language name by which the cited information is known. Example: DCW as an alternative title for Digital Chart of the World."""
        pass

    @abstractproperty
    def date(self) -> Date:
        """Reference date for the cited resource."""
        pass

    @abstractproperty
    def edition(self) -> str:
        """Version of the cited resource."""
        pass

    @abstractproperty
    def editionDate(self) -> datetime:
        """Date of the edition."""
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        """Value uniquely identifying an object within a namespace."""
        pass

    @abstractproperty
    def citedResponsibleParty(self) -> Responsibility:
        """Name and position information for an individual or organisation that is responsible for the resource."""
        pass

    @abstractproperty
    def presentationForm(self) -> PresentationFormCode:
        """Mode in which the resource is represented."""
        pass

    @abstractproperty
    def series(self) -> Series:
        """Information about the series, or aggregate resource, of which the resource is a part."""
        pass

    @abstractproperty
    def otherCitationDetails(self) -> str:
        """Other information required to complete the citation that is not recorded elsewhere."""
        pass

    @abstractproperty
    def ISBN(self) -> str:
        """International Standard Book Number."""
        pass

    @abstractproperty
    def ISSN(self) -> str:
        """International Standard Serial Number."""
        pass

    @abstractproperty
    def onlineResource(self) -> OnlineResource:
        """Online reference to the cited resource."""
        pass

    @abstractproperty
    def graphic(self) -> BrowseGraphic:
        """Citation graphic or logo for cited party."""
        pass
