#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from enum import Enum

class MaintenanceFrequencyCode(Enum):
    CONTINUAL = "continual"
    DAILY = "daily"
    WEEKLY = "weekly"
    FORTNIGHTLY = "fortnightly"
    MONTHLY = "monthly"
    QUARTERLY = "quarterly"
    BIANNUALLY = "biannually"
    ANNUALLY = "annually"
    AS_NEEDED = "asNeeded"
    IRREGULAR = "irregular"
    NOT_PLANNED = "notPlanned"
    UNKNOWN = "unknown"
    PERIODIC = "periodic"
    SEMIMONTHLY = "semimonthly"
    BIENNIALLY = "biennially"

class MaintenanceInformation(ABC):
    """Information about the scope and frequency of updating."""

    @abstractproperty
    def maintenanceAndUpdateFrequency(self) -> MaintenanceFrequencyCode:
        """Frequency with which changes and additions are made to the resource after the initial resource is completed."""
        pass

    @abstractproperty
    def maintenanceDate(self) -> Date:
        """Date information associated with maintenance of resource."""
        pass

    @abstractproperty
    def userDefinedMaintenanceFrequency(self) -> PeriodDuration:
        """Maintenance period other than those defined."""
        pass

    @abstractproperty
    def maintenanceScope(self) -> Scope:
        """Information about the scope and extent of maintenance."""
        pass

    @abstractproperty
    def maintenanceNote(self) -> str:
        """Information regarding specific requirements for maintaining the resource."""
        pass

    @abstractproperty
    def contact(self) -> Responsibility:
        """Identification of, and means of communicating with, person(s) and organisation(s) with responsibility for maintaining the metadata."""
        pass
