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



class ScopeCode(Enum):
    COLLECTION_HARDWARE = "collectionHardware"
    COLLECTION_SESSION = "collectionSession"
    SERIES = "series"
    DATASET = "dataset"
    NON_GEOGRAPHIC_DATASET = "nonGeographicDataset"
    DIMENSION_GROUP = "dimensionGroup"
    FEATURE_TYPE = "featureType"
    FEATURE = "feature"
    ATTRIBUTE_TYPE = "attributeType"
    ATTRIBUTE = "attribute"
    PROPERTY_TYPE = "propertyType"
    FIELD_SESSION = "fieldSession"
    SOFTWARE = "software"
    SERVICE = "service"
    MODEL = "model"
    TILE = "tile"
    METADATA = "metadata"
    INITIATIVE = "initiative"
    SAMPLE = "sample"
    DOCUMENT = "document"
    REPOSITORY = "repository"
    AGGREGATE = "aggregate"
    PRODUCT = "product"
    COLLECTION = "collection"
    COVERAGE = "coverage"
    APPLICATION = "application"



class ScopeDescription(ABC):
    """Description of the class of information covered by the information."""

    @property
    def attributes(self) -> Sequence[str]:
        """Instances of attribute types to which the information applies."""
        return None

    @property
    def features(self) -> Sequence[str]:
        """Instances of feature types to which the information applies."""
        return None

    @property
    def feature_instances(self) -> Sequence[str]:
        """Feature instances to which the information applies."""
        return None

    @property
    def attribute_instances(self) -> Sequence[str]:
        """Attribute instances to which the information applies."""
        return None

    @property
    def dataset(self) -> str:
        """Dataset to which the information applies."""
        return None

    @property
    def other(self) -> str:
        """Class of information that does not fall into the other categories to which the information applies."""
        return None



from opengis.metadata.extent import Extent

class Scope(ABC):
    """New: information about the scope of the resource."""

    @abstractproperty
    def level(self) -> ScopeCode:
        """Description of the scope."""
        pass

    @property
    def extent(self) -> Sequence[Extent]:
        return None

    @property
    def level_description(self) -> Sequence[ScopeDescription]:
        return None



from opengis.metadata.citation import Date, Responsibility

class MaintenanceInformation(ABC):
    """Information about the scope and frequency of updating."""

    @property
    def maintenance_and_update_frequency(self) -> MaintenanceFrequencyCode:
        """Frequency with which changes and additions are made to the resource after the initial resource is completed."""
        return None

    @property
    def maintenance_date(self) -> Sequence[Date]:
        """Date information associated with maintenance of resource."""
        return None

    @property
    def user_defined_maintenance_frequency(self):
        """Maintenance period other than those defined."""
        return None

    @property
    def maintenance_scope(self) -> Sequence[Scope]:
        """Information about the scope and extent of maintenance."""
        return None

    @property
    def maintenance_note(self) -> Sequence[str]:
        """Information regarding specific requirements for maintaining the resource."""
        return None

    @property
    def contact(self) -> Sequence[Responsibility]:
        """Identification of, and means of communicating with, person(s) and organisation(s) with responsibility for maintaining the metadata."""
        return None
