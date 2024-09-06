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
"""This is the citation module.

This module contains geographic metadata structures regarding metadata
citations derived from the ISO 19115-1:2014 international standard.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence
from datetime import datetime
from enum import Enum
from typing import Optional

from opengis.metadata.extent import Extent
from opengis.metadata.identification import BrowseGraphic


class DateTypeCode(Enum):
    """Identification of when a given event occurred."""

    CREATION = "creation"
    """Date identifies when the resource was brought into existence."""

    PUBLICATION = "publication"
    """Date identifies when the resource was issued."""

    REVISION = "revision"
    """
    Date identifies when the resource was examined or rexamined and improved
    or amended.
    """

    EXPIRY = "expiry"
    """Date identifies when resource expires."""

    LAST_UPDATE = "lastUpdate"
    """Date identifies when resource was last updated."""

    LAST_REVISION = "lastRevision"
    """Date identifies when resource was last reviewed."""

    NEXT_UPDATE = "nextUpdate"
    """Date identifies when resource will be next updated."""

    UNAVAILABLE = "unavailable"
    """Date identifies when resource became not available or obtainable."""

    IN_FORCE = "inForce"
    """Date identifies when resource became in force."""

    ADOPTED = "adopted"
    """Date identifies when resource was adopted."""

    DEPRECATED = "deprecated"
    """Date identifies when resource was deprecated."""

    SUPERSEDED = "superseded"
    """
    Date identifies when resource was superceded or replaced by another
    resource.
    """

    VALIDITY_BEGINS = "validityBegins"
    """
    Time at which the data are considered to become valid.

    NOTE: There could be quite a delay between creation and validity begins.
    """

    VALIDITY_EXPIRES = "validityExpires"
    """Time at which the data are no longer considered to be valid."""

    RELEASED = "released"
    """The date that the resource shall be released for public access."""

    DISTRIBUTION = "distribution"
    """Date identifies when an instance of the resource was distributed."""


class OnLineFunctionCode(Enum):
    """Function performed by the resource."""

    DOWNLOAD = "download"
    """
    Online instructions for transferring data from one storage device or
    system to another.
    """
    INFORMATION = "information"
    """Online information about the resource."""

    OFFLINE_ACCESS = "offlineAccess"
    """Online instructions for requesting the resource from the provider."""

    ORDER = "order"
    """Online order process for obtaining the resource."""

    SEARCH = "search"
    """
    Online search interface for seeking out information about the resource.
    """

    COMPLETE_METADATA = "completeMetadata"
    """Complete metadata provided."""

    BROWSE_GRAPHIC = "browseGraphic"
    """Browse graphic provided."""

    UPLOAD = "upload"
    """Online resource upload capability provided."""

    EMAIL_SERVICE = "emailService"
    """Online email service provided."""

    BROWSING = "browsing"
    """Online browsing provided."""

    FILE_ACCESS = "fileAccess"
    """Online file access provided."""


class PresentationFormCode(Enum):
    """Mode in which the data are represented."""

    DOCUMENT_DIGITAL = "documentDigital"
    """
    Digital representation of a primarily textual item (can contain
    illustrations also).
    """

    DOCUMENT_HARDCOPY = "documentHardcopy"
    """
    Representaiton of a primarily textual item (can contain illustrations
    also) on paper, photographic material, or other media.
    """

    IMAGE_DIGITAL = "imageDigital"
    """
    Likeness of natural or man-made features, objects, and activities acquired
    through the sensing of visual or any other segment of the electronic
    spectrum by sensors, such as thermal infrared and high resolution radar,
    and stored in digital format.
    """

    IMAGE_HARDCOPY = "imageHardcopy"
    """
    Likeness of natural or man-made features, objects, and activities acquired
    through the sensing of visual or any other segment of the electronic
    spectrum by sensors, such as thermal infrared and high resolution radar,
    and stored on paper, photographic material, or other media for use
    directly by the human user.
    """

    MAP_DIGITAL = "mapDigital"
    """Map represented in raster or vector form."""

    MAP_HARDCOPY = "mapHardcopy"
    """
    Map printed on paper, photographic material, or other media for use
    directly by the human user.
    """

    MODEL_DIGITAL = "modelDigital"
    """Multi-dimensional digital representation of a feature, process, etc."""

    MODEL_HARDCOPY = "modelHardcopy"
    """3-dimensional, physical model."""

    PROFILE_DIGITAL = "profileDigital"
    """Vertical cross-section in digital form."""

    PROFILE_HARDCOPY = "profileHardcopy"
    """Vertical cross-section on paper, etc."""

    TABLE_DIGITAL = "tableDigital"
    """"
    Digital representation of facts or figures systematically displayed,
    especially in columns.
    """

    TABLE_HARDCOPY = "tableHardcopy"
    """"
    Representation of facts or figures systematically displayed, especially in
    columns, printed on paper, photographic material, or other media.
    """

    VIDEO_DIGITAL = "videoDigital"
    """Digital video recording."""

    VIDEO_HARDCOPY = "videoHardcopy"
    """Video recording on film."""

    AUDIO_DIGITAL = "audioDigital"
    """Digital audio recording."""

    AUDIO_HARDCOPY = "audioHardcopy"
    """Audio recroding delivered by analog media, such as a magnetic tape."""

    MULTIMEDIA_DIGITAL = "multimediaDigital"
    """
    Information representation using simultaneously various digital modes
    text, sound, image.
    """

    MULTIMEDIA_HARDCOPY = "multimediaHardcopy"
    """
    Information representation using simultaneously various analog modes
    text, sound, image.
    """

    PHYSICAL_OBJECT = "physicalObject"
    """
    A physical object.

    EXAMPLE: Rock or mineral sample, microscope slide.
    """

    DIAGRAM_DIGITAL = "diagramDigital"
    """
    Information represented graphically by charts such as pie chart,
    bar chart, and other type of diagrms and recorded in digital format.
    """

    DIAGRAM_HARDCOPY = "diagramHardcopy"
    """
    Information represented graphically by charts such as pie chart,
    bar chart, and other type of diagrams and printed on paper, photographic
    material, or other media.
    """


class RoleCode(Enum):
    """Function performed by the responsible party."""

    RESOURCE_PROVIDER = "resourceProvider"
    """Party that supplies the resource."""

    CUSTODIAN = "custodian"
    """
    Party that accepts accountability and responsibility for the resource and
    ensures appropriate care an maintenance of the resource.
    """

    OWNER = "owner"
    """Party that owns the resource."""

    USER = "user"
    """Party who uses the resource."""

    DISTRIBUTOR = "distributor"
    """Party who distributes the resource."""

    ORIGINATOR = "originator"
    """Party who created the resource."""

    POINT_OF_CONTACT = "pointOfContact"
    """
    Party who can be contactedfor acquiring knowledge about or acquisition of
    the resource.
    """

    PRINCIPAL_INVESTIGATOR = "principalInvestigator"
    """
    Key party responsible for gathering information and conducting research.
    """

    PROCESSOR = "processor"
    """
    Party who has processed the data in a manner such that the resource has
    been modified.
    """

    PUBLISHER = "publisher"
    """Party who published resource."""

    AUTHOR = "author"
    """Party who authored the resource."""

    SPONSOR = "sponsor"
    """Party who speaks for the resource."""

    CO_AUTHOR = "coAuthor"
    """Party who jointly authors the resource."""

    COLLABORATOR = "collaborator"
    """
    Party who assists with the generation of the resource other than the
    principal investigator.
    """

    EDITOR = "editor"
    """
    Party who reviewed or modified the resource to improve the content.
    """

    MEDIATOR = "mediator"
    """
    A class of entity that mediates access to the resource and for whom the
    resource is intended or useful.
    """

    RIGHTS_HOLDER = "rightsHolder"
    """Party owning or managing the rights over the resource."""

    CONTRIBUTOR = "contributor"
    """Party contributing to the resource."""

    FUNDER = "funder"
    """Party providing monetary support for the resource."""

    STAKEHOLDER = "stakeholder"
    """Party who has an interest in the resource or the use of the resource."""


class TelephoneTypeCode(Enum):
    """Type of telephone."""

    VOICE = "voice"
    """Telephone provides voice service."""

    FACSIMILE = "facsimile"
    """Telephone provides facsimile service."""

    SMS = "sms"
    """Telephone provides sms service."""


class Series(ABC):
    """
    Information about the series, or aggregate resource, to which a resource
    belongs.
    """

    @property
    @abstractmethod
    def name(self) -> Optional[str]:
        """
        Name of the series, or aggregate resource, of which the resource is a
        part.
        """

    @property
    @abstractmethod
    def issue_identification(self) -> Optional[str]:
        """Information identifying the issue of the series."""

    @property
    @abstractmethod
    def page(self) -> Optional[str]:
        """
        Details on which pages of the publication the article was published.
        """


class Address(ABC):
    """Location of the responsible individual or organisation."""

    @property
    @abstractmethod
    def delivery_point(self) -> Optional[Sequence[str]]:
        """
        Address line for the location (as described in ISO 11180, Annex A).
        """

    @property
    @abstractmethod
    def city(self) -> Optional[str]:
        """City of the location."""

    @property
    @abstractmethod
    def administrative_area(self) -> Optional[str]:
        """State, province of the location."""

    @property
    @abstractmethod
    def postal_code(self) -> Optional[str]:
        """ZIP or other postal code."""

    @property
    @abstractmethod
    def country(self) -> Optional[str]:
        """Country of the physical address."""

    @property
    @abstractmethod
    def electronic_mail_address(self) -> Optional[Sequence[str]]:
        """
        Address of the electronic mailbox of the responsible organisation or
        individual.
        """


class Telephone(ABC):
    """
    Telephone numbers for contacting the responsible individual or
    organisation.
    """

    @property
    @abstractmethod
    def number(self) -> str:
        """
        Telephone number by which individuals can contact responsible
        organisation or individual.
        """

    @property
    @abstractmethod
    def number_type(self) -> Optional[TelephoneTypeCode]:
        """Type of telephone responsible organisation or individual."""


class OnlineResource(ABC):
    """
    Information about on-line sources from which the resource, specification,
    or community profile name and extended metadata elements can be obtained.
    """

    @property
    @abstractmethod
    def linkage(self) -> str:
        """
        Location (address) for on-line access using a Uniform Resource Locator/
        Uniform Resource Identifier address or similar addressing scheme such
        as http://www.statkart.no/isotc211.
        """

    @property
    @abstractmethod
    def protocol(self) -> Optional[str]:
        """Connection protocol to be used e.g. http, ftp, file."""

    @property
    @abstractmethod
    def application_profile(self) -> Optional[str]:
        """
        Name of an application profile that can be used with the online
        resource.
        """

    @property
    @abstractmethod
    def name(self) -> Optional[str]:
        """Name of the online resource."""

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """Detailed text description of what the online resource is/does."""

    @property
    @abstractmethod
    def function(self) -> Optional[OnLineFunctionCode]:
        """Code for function performed by the online resource."""

    @property
    @abstractmethod
    def protocol_request(self) -> Optional[str]:
        """
        Request used to access the resource depending on the protocol
        (to be used mainly for POST requests)

        Protocol used by the accessed resource.

        Example POST/XML:

        ```
        <Ge tFeatures service = "WFS"
                     version="2.0.0"
                     outputFormat="application/gml+xml; version=3.2"
                     xmlns=http://www.opengis.net/wfs/2.0
                     xmlns:xsi=http://www.w3.org/2001/XMLSchema-instance
                     xsi:schemaLocation="http://www.opengis.net/wfs/2.0
                        http://schemas.opengis.net/wfs/2.0.0/wfs.xsd">
        <Query typeNames="Roads" />
        </GetFeatures>
        ```
        """


class Contact(ABC):
    """
    Information required to enable contact with the responsible person and/or
    organisation.
    """

    @property
    @abstractmethod
    def phone(self) -> Optional[Sequence[Telephone]]:
        """
        Telephone numbers at which the organisation or individual may be
        contacted.
        """

    @property
    @abstractmethod
    def address(self) -> Optional[Sequence[Address]]:
        """
        Physical and email address at which the organisation or individual may
        be contacted.
        """

    @property
    @abstractmethod
    def online_resource(self) -> Optional[Sequence[OnlineResource]]:
        """
        On-line information that can be used to contact the individual or
        organisation.
        """

    @property
    @abstractmethod
    def hours_of_service(self) -> Optional[Sequence[str]]:
        """
        Time period (including time zone) when individuals can contact the
        organisation or individual.
        """

    @property
    @abstractmethod
    def contact_instructions(self) -> Optional[str]:
        """
        Supplemental instructions on how or when to contact the individual or
        organisation.
        """

    @property
    @abstractmethod
    def contact_type(self) -> Optional[str]:
        """Type of the contact."""


class Party(ABC):
    """Information about the individual and/or organisation of the party."""

    @property
    @abstractmethod
    def name(self) -> Optional[str]:
        """
        Name of the party.

        MANDATORY: if `logo` and `position_name`are `None`.
        """

    @property
    @abstractmethod
    def contact_info(self) -> Sequence[Contact]:
        """Contact information for the party."""

    @property
    @abstractmethod
    def party_identifier(self) -> Optional[Sequence['Identifier']]:
        """
        Identifier of the party.

        MANDATORY: if `name` and `logo` are `None`.
        """


class Responsibility(ABC):
    """Information about the party and their role."""

    @property
    @abstractmethod
    def role(self) -> RoleCode:
        """Function performed by the responsible party."""

    @property
    @abstractmethod
    def extent(self) -> Optional[Sequence[Extent]]:
        """Spatial or temporal extent of the role."""

    @property
    @abstractmethod
    def party(self) -> Sequence[Party]:
        """Information about the Party."""


class Individual(Party):
    """Information about the party if the party is an individual."""

    @property
    @abstractmethod
    def position_name(self) -> Optional[str]:
        """
        Position of the individual in an organisation.

        MANDATORY: if `name` and `logo` are `None`.
        """


class Organisation(Party):
    """Information about the party if the party is an organisation."""

    @property
    @abstractmethod
    def logo(self) -> Sequence[BrowseGraphic]:
        """
        Graphic identifying organisation.

        MANDATORY: if `name` and `position_name`are `None`.
        """

    @property
    @abstractmethod
    def individual(self) -> Optional[Sequence[Individual]]:
        """Individuals belonging to the Organisation."""


class Date(ABC):
    """Reference date and event used to describe it."""

    @property
    @abstractmethod
    def date(self) -> datetime:
        """Reference date for the cited resource."""

    @property
    @abstractmethod
    def date_type(self) -> DateTypeCode:
        """Event used for reference date."""


class Citation(ABC):
    """Standardized resource reference."""

    @property
    @abstractmethod
    def title(self) -> str:
        """Name by which the cited resource is known."""

    @property
    @abstractmethod
    def alternate_title(self) -> Optional[Sequence[str]]:
        """
        Short name or other language name by which the cited information is
        known.

        Example: 'DCW' as an alternative title for Digital Chart of the
        World.
        """

    @property
    @abstractmethod
    def date(self) -> Optional[Sequence[Date]]:
        """Reference date for the cited resource."""

    @property
    @abstractmethod
    def edition(self) -> Optional[str]:
        """Version of the cited resource."""

    @property
    @abstractmethod
    def edition_date(self) -> Optional[datetime]:
        """Date of the edition."""

    @property
    @abstractmethod
    def identifier(self) -> Optional[Sequence['Identifier']]:
        """Value uniquely identifying an object within a namespace."""

    @property
    @abstractmethod
    def cited_responsible_party(self) -> Optional[Sequence[Responsibility]]:
        """
        Name and position information for an individual or organisation that
        is responsible for the resource.
        """

    @property
    @abstractmethod
    def presentation_form(self) -> Optional[Sequence[PresentationFormCode]]:
        """Mode in which the resource is represented."""

    @property
    @abstractmethod
    def series(self) -> Optional[Series]:
        """
        Information about the series, or aggregate resource, of which the
        resource is a part.
        """

    @property
    @abstractmethod
    def other_citation_details(self) -> Optional[Sequence[str]]:
        """
        Other information required to complete the citation that is not
        recorded elsewhere.
        """

    @property
    @abstractmethod
    def isbn(self) -> Optional[str]:
        """International Standard Book Number."""

    @property
    @abstractmethod
    def issn(self) -> Optional[str]:
        """International Standard Serial Number."""

    @property
    @abstractmethod
    def online_resource(self) -> Optional[Sequence[OnlineResource]]:
        """Online reference to the cited resource."""

    @property
    @abstractmethod
    def graphic(self) -> Optional[Sequence['BrowseGraphic']]:
        """Citation graphic or logo for cited party."""


class Identifier(ABC):
    """Value uniquely identifying an object within a namespace."""

    @property
    @abstractmethod
    def authority(self) -> Optional[Citation]:
        """
        The person or party responsible for maintenance of the namespace.

        Citation for the code namespace and optionally the person or party
        responsible for maintenance of that namespace.
        """

    @property
    @abstractmethod
    def code(self) -> str:
        """
        Alphanumeric value identifying an instance in the namespace.

        NOTE: Avoid characters that are not legal in URLs.

        Example: EPSG::4326.
        """

    @property
    @abstractmethod
    def code_space(self) -> Optional[str]:
        """Identifier or namespace in which the code is valid."""

    @property
    @abstractmethod
    def version(self) -> Optional[str]:
        """Version identifier for the namespace."""

    @property
    @abstractmethod
    def description(self) -> Optional[str]:
        """
        Natural language description of the meaning of the code value.

        Example: for code_space = EPSG, code = 4326, description = WGS-84.
        """
