#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractmethod
from typing import Sequence
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

    @property
    def name(self) -> str:
        """Name of the series, or aggregate resource, of which the resource is a part."""
        return None

    @property
    def issue_identification(self) -> str:
        """Information identifying the issue of the series."""
        return None

    @property
    def page(self) -> str:
        """Details on which pages of the publication the article was published."""
        return None



class Address(ABC):
    """Location of the responsible individual or organisation."""

    @property
    def delivery_point(self) -> Sequence[str]:
        """Address line for the location (as described in ISO 11180, Annex A)."""
        return None

    @property
    def city(self) -> str:
        """City of the location."""
        return None

    @property
    def administrative_area(self) -> str:
        """State, province of the location."""
        return None

    @property
    def postal_code(self) -> str:
        """ZIP or other postal code."""
        return None

    @property
    def country(self) -> str:
        """Country of the physical address."""
        return None

    @property
    def electronic_mail_address(self) -> Sequence[str]:
        """Address of the electronic mailbox of the responsible organisation or individual."""
        return None



class Telephone(ABC):
    """Telephone numbers for contacting the responsible individual or organisation."""

    @property
    @abstractmethod
    def number(self) -> str:
        """Telephone number by which individuals can contact responsible organisation or individual."""
        pass

    @property
    def number_type(self) -> TelephoneTypeCode:
        """Type of telephone responsible organisation or individual."""
        return None



class OnlineResource(ABC):
    """Information about on-line sources from which the resource, specification, or community profile name and extended metadata elements can be obtained."""

    @property
    @abstractmethod
    def linkage(self):
        """Location (address) for on-line access using a Uniform Resource Locator/Uniform Resource Identifier address or similar addressing scheme such as http://www.statkart.no/isotc211."""
        pass

    @property
    def protocol(self) -> str:
        """Connection protocol to be used e.g. http, ftp, file."""
        return None

    @property
    def application_profile(self) -> str:
        """Name of an application profile that can be used with the online resource."""
        return None

    @property
    def name(self) -> str:
        """Name of the online resource."""
        return None

    @property
    def description(self) -> str:
        """Detailed text description of what the online resource is/does."""
        return None

    @property
    def function(self) -> OnLineFunctionCode:
        """Code for function performed by the online resource."""
        return None

    @property
    def protocol_request(self) -> str:
        """Protocol used by the accessed resource."""
        return None



class Contact(ABC):
    """Information required to enable contact with the responsible person and/or organisation."""

    @property
    def phone(self) -> Sequence[Telephone]:
        """Telephone numbers at which the organisation or individual may be contacted."""
        return None

    @property
    def address(self) -> Sequence[Address]:
        """Physical and email address at which the organisation or individual may be contacted."""
        return None

    @property
    def online_resource(self) -> Sequence[OnlineResource]:
        """On-line information that can be used to contact the individual or organisation."""
        return None

    @property
    def hours_of_service(self) -> Sequence[str]:
        """Time period (including time zone) when individuals can contact the organisation or individual."""
        return None

    @property
    def contact_instructions(self) -> str:
        """Supplemental instructions on how or when to contact the individual or organisation."""
        return None

    @property
    def contact_type(self) -> str:
        return None



class Party(ABC):
    """Information about the individual and/or organisation of the party."""

    @property
    def name(self) -> str:
        """Name of the party."""
        return None

    @property
    def contact_info(self) -> Sequence[Contact]:
        """Contact information for the party."""
        return None



class Responsibility(ABC):
    """Information about the party and their role."""

    @property
    @abstractmethod
    def role(self) -> RoleCode:
        """Function performed by the responsible party."""
        pass

    @property
    def extent(self) -> Sequence['Extent']:
        """Spatial or temporal extent of the role."""
        return None

    @property
    @abstractmethod
    def party(self) -> Sequence[Party]:
        pass



class Individual(Party):
    """Information about the party if the party is an individual."""

    @property
    def position_name(self) -> str:
        """Position of the individual in an organisation."""
        return None



class Organisation(Party):
    """Information about the party if the party is an organisation."""

    @property
    def logo(self) -> Sequence['BrowseGraphic']:
        """Graphic identifying organization."""
        return None

    @property
    def individual(self) -> Sequence[Individual]:
        return None



from datetime import datetime

class Date(ABC):
    """Reference date and event used to describe it."""

    @property
    @abstractmethod
    def date(self) -> datetime:
        """Reference date for the cited resource."""
        pass

    @property
    @abstractmethod
    def date_type(self) -> DateTypeCode:
        """Event used for reference date."""
        pass



class Citation(ABC):
    """Standardized resource reference."""

    @property
    @abstractmethod
    def title(self) -> str:
        """Name by which the cited resource is known."""
        pass

    @property
    def alternate_title(self) -> Sequence[str]:
        """Short name or other language name by which the cited information is known. Example: DCW as an alternative title for Digital Chart of the World."""
        return None

    @property
    def date(self) -> Sequence[Date]:
        """Reference date for the cited resource."""
        return None

    @property
    def edition(self) -> str:
        """Version of the cited resource."""
        return None

    @property
    def edition_date(self) -> datetime:
        """Date of the edition."""
        return None

    @property
    def identifier(self) -> Sequence['Identifier']:
        """Value uniquely identifying an object within a namespace."""
        return None

    @property
    def cited_responsible_party(self) -> Sequence[Responsibility]:
        """Name and position information for an individual or organisation that is responsible for the resource."""
        return None

    @property
    def presentation_form(self) -> Sequence[PresentationFormCode]:
        """Mode in which the resource is represented."""
        return None

    @property
    def series(self) -> Series:
        """Information about the series, or aggregate resource, of which the resource is a part."""
        return None

    @property
    def other_citation_details(self) -> Sequence[str]:
        """Other information required to complete the citation that is not recorded elsewhere."""
        return None

    @property
    def ISBN(self) -> str:
        """International Standard Book Number."""
        return None

    @property
    def ISSN(self) -> str:
        """International Standard Serial Number."""
        return None

    @property
    def online_resource(self) -> Sequence[OnlineResource]:
        """Online reference to the cited resource."""
        return None

    @property
    def graphic(self) -> Sequence['BrowseGraphic']:
        """Citation graphic or logo for cited party."""
        return None



class Identifier(ABC):
    """Value uniquely identifying an object within a namespace."""

    @property
    def authority(self) -> Citation:
        """Citation for the code namespace and optionally the person or party responsible for maintenance of that namespace."""
        return None

    @property
    @abstractmethod
    def code(self) -> str:
        """Alphanumeric value identifying an instance in the namespace e.g. EPSG::4326."""
        pass

    @property
    def code_space(self) -> str:
        """Identifier or namespace in which the code is valid."""
        return None

    @property
    def version(self) -> str:
        """Version identifier for the namespace."""
        return None

    @property
    def description(self) -> str:
        """Natural language description of the meaning of the code value E.G for codeSpace = EPSG, code = 4326: description = WGS-84" to "for codeSpace = EPSG, code = EPSG::4326: description = WGS-84."""
        return None
