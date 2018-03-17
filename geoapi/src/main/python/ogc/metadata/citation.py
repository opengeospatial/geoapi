#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty

class Series(ABC):

    @abstractproperty
    def name(self) -> str:
        pass

    @abstractproperty
    def issueIdentification(self) -> str:
        pass

    @abstractproperty
    def page(self) -> str:
        pass

class Address(ABC):

    @abstractproperty
    def deliveryPoint(self) -> str:
        pass

    @abstractproperty
    def city(self) -> str:
        pass

    @abstractproperty
    def administrativeArea(self) -> str:
        pass

    @abstractproperty
    def postalCode(self) -> str:
        pass

    @abstractproperty
    def country(self) -> str:
        pass

    @abstractproperty
    def electronicMailAddress(self) -> str:
        pass

class Telephone(ABC):

    @abstractproperty
    def number(self) -> str:
        pass

    @abstractproperty
    def numberType(self) -> TelephoneTypeCode:
        pass

class OnlineResource(ABC):

    @abstractproperty
    def linkage(self):
        pass

    @abstractproperty
    def protocol(self) -> str:
        pass

    @abstractproperty
    def applicationProfile(self) -> str:
        pass

    @abstractproperty
    def name(self) -> str:
        pass

    @abstractproperty
    def description(self) -> str:
        pass

    @abstractproperty
    def function(self) -> OnLineFunctionCode:
        pass

    @abstractproperty
    def protocolRequest(self) -> str:
        pass

class Contact(ABC):

    @abstractproperty
    def phone(self) -> Telephone:
        pass

    @abstractproperty
    def address(self) -> Address:
        pass

    @abstractproperty
    def onlineResource(self) -> OnlineResource:
        pass

    @abstractproperty
    def hoursOfService(self) -> str:
        pass

    @abstractproperty
    def contactInstructions(self) -> str:
        pass

    @abstractproperty
    def contactType(self) -> str:
        pass

class Party(ABC):

    @abstractproperty
    def name(self) -> str:
        pass

    @abstractproperty
    def contactInfo(self) -> Contact:
        pass

class Responsibility(ABC):

    @abstractproperty
    def role(self) -> RoleCode:
        pass

    @abstractproperty
    def extent(self) -> Extent:
        pass

    @abstractproperty
    def party(self) -> Party:
        pass

class Individual(Party):

    @abstractproperty
    def positionName(self) -> str:
        pass

class Organisation(Party):

    @abstractproperty
    def logo(self) -> BrowseGraphic:
        pass

    @abstractproperty
    def individual(self) -> Individual:
        pass

class Date(ABC):

    @abstractproperty
    def date(self) -> datetime:
        pass

    @abstractproperty
    def dateType(self) -> DateTypeCode:
        pass

class Citation(ABC):
    """Standardized resource reference."""

    @abstractproperty
    def title(self) -> str:
        """Name by which the cited resource is known."""
        pass

    @abstractproperty
    def alternateTitle(self) -> str:
        pass

    @abstractproperty
    def date(self) -> Date:
        pass

    @abstractproperty
    def edition(self) -> str:
        pass

    @abstractproperty
    def editionDate(self) -> datetime:
        pass

    @abstractproperty
    def identifier(self) -> Identifier:
        pass

    @abstractproperty
    def citedResponsibleParty(self) -> Responsibility:
        pass

    @abstractproperty
    def presentationForm(self) -> PresentationFormCode:
        pass

    @abstractproperty
    def series(self) -> Series:
        pass

    @abstractproperty
    def otherCitationDetails(self) -> str:
        pass

    @abstractproperty
    def ISBN(self) -> str:
        pass

    @abstractproperty
    def ISSN(self) -> str:
        pass

    @abstractproperty
    def onlineResource(self) -> OnlineResource:
        pass

    @abstractproperty
    def graphic(self) -> BrowseGraphic:
        pass
