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
"""This is the `maintenance` module.

This module contains geographic metadata structures regarding data maintenance
derived from the ISO 19115-1:2014 international standard.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence, Set
from datetime import timedelta
from enum import Enum
from typing import Optional

from opengis.metadata.citation import Date, Responsibility
from opengis.metadata.extent import Extent


class MaintenanceFrequencyCode(Enum):
    """
    Frequency with which modifications and deletions are made to the data
    after it is first produced
    """

    CONTINUAL = "continual"
    """Resource is repeatedly and frequentlyupdated."""

    DAILY = "daily"
    """Resource is updated each day"""

    WEEKLY = "weekly"
    """Resource is updated on a weekly basis."""

    FORTNIGHTLY = "fortnightly"
    """Resource is updated every two weeks."""

    MONTHLY = "monthly"
    """Resource is updated each month."""

    QUARTERLY = "quarterly"
    """Resource is updatedevery three months."""

    BIANNUALLY = "biannually"
    """Resource is updated twice each year."""

    ANNUALLY = "annually"
    """Resource is updated every year."""

    AS_NEEDED = "asNeeded"
    """Resource is updated as deemed necessary."""

    IRREGULAR = "irregular"
    """Resource is updated in intervals that are uneven in duration."""

    NOT_PLANNED = "notPlanned"
    """There are no plans to update the data."""

    UNKNOWN = "unknown"
    """Frequency of maintenance for the data is not known."""

    PERIODIC = "periodic"
    """Resource is updated at regular intervals."""

    SEMIMONTHLY = "semimonthly"
    """Resource is updated twice monthly."""

    BIENNIALLY = "biennially"
    """Resource is updated every two years."""


class ScopeCode(Enum):
    """Class of information to which the referencing entity applies."""

    ATTRIBUTE = "attribute"
    """Information applies to the attribute value."""

    ATTRIBUTE_TYPE = "attributeType"
    """Information applies to the characteristic of a feature."""

    COLLECTION_HARDWARE = "collectionHardware"
    """Information applies to the collection hardware class."""

    COLLECTION_SESSION = "collectionSession"
    """Information applies to the collection session."""

    DATASET = "dataset"
    """Information applies to the dataset."""

    SERIES = "series"
    """Information applies to the series."""

    NON_GEOGRAPHIC_DATASET = "nonGeographicDataset"
    """Information applies to the non-geographic dataset."""

    DIMENSION_GROUP = "dimensionGroup"
    """Information applies to a dimension group."""

    FEATURE = "feature"
    """Information applies to a featiure."""

    FEATURE_TYPE = "featureType"
    """Information applies to a feature type."""

    PROPERTY_TYPE = "propertyType"
    """Information applies to a property type."""

    FIELD_SESSION = "fieldSession"
    """Information applies to a field session."""

    SOFTWARE = "software"
    """Information applies to a computer program or routine."""

    SERVICE = "service"
    """
    Information applies to a capability which a service provider entity makes
    available to s service user entity through a set of interfaces that define
    a behaviour, such as a use case.
    """

    MODEL = "model"
    """
    Information applies to a copy or imitation of an existing or hypothetical
    object.
    """

    TILE = "tile"
    """Information applies to a tile, a spatial subset of geographic data."""

    METADATA = "metadata"
    """Information applies to metadata."""

    INITIATIVE = "initiative"
    """Information applies to an intitiative."""

    SAMPLE = "sample"
    """Information applies to a sample."""

    DOCUMENT = "document"
    """Information applies to a document."""

    REPOSITORY = "repository"
    """Information applies to a repository."""

    AGGREGATE = "aggregate"
    """Information applies to an aggregate resource."""

    PRODUCT = "product"
    """Metadata describing an ISO 19131 data product specification."""

    COLLECTION = "collection"
    """Information applies to an unstructured set."""

    COVERAGE = "coverage"
    """Information applies to a coverage."""

    APPLICATION = "application"
    """
    Information resource hosted on a specific set of hardware and accessible
    over a network.
    """


class ScopeDescription(ABC):
    """Description of the class of information covered by the information."""

    @property
    @abstractmethod
    def attributes(self) -> Optional[Set[str]]:
        """
        Instances of attribute types to which the information applies.

        MANDATORY:
            If `features`, `feature_instances`, `attribute_instances`,
            `dataset`, and `other` are `None`.
        """

    @property
    @abstractmethod
    def features(self) -> Optional[Set[str]]:
        """
        Instances of feature types to which the information applies.

        MANDATORY:
            If `attributes`, `feature_instances`, `attribute_instances`,
            `dataset`, and `other` are `None`.
        """

    @property
    @abstractmethod
    def feature_instances(self) -> Optional[Set[str]]:
        """
        Feature instances to which the information applies.

        MANDATORY:
            If `attributes`, `features`, `attribute_instances`,
            `dataset`, and `other` are `None`.
        """

    @property
    @abstractmethod
    def attribute_instances(self) -> Optional[Set[str]]:
        """
        Attribute instances to which the information applies.

        MANDATORY:
            If `attributes`, `features`, `feature_instances`,
            `dataset`, and `other` are `None`.
        """

    @property
    @abstractmethod
    def dataset(self) -> Optional[str]:
        """
        Dataset to which the information applies.

        MANDATORY:
            If `attributes`, `features`, `feature_instances`,
            `attribute_instances`, and `other` are `None`.
        """

    @property
    @abstractmethod
    def other(self) -> Optional[str]:
        """
        Class of information that does not fall into the other categories to
        which the information applies.

        MANDATORY:
            If `attributes`, `features`, `feature_instances`,
            `attribute_instances`, and `dataset` are `None`.
        """


class Scope(ABC):
    """
    The target resource and physical extent for which information is reported.
    Information about the scope of the resource.
    """

    @property
    @abstractmethod
    def level(self) -> ScopeCode:
        """Description of the scope."""

    @property
    @abstractmethod
    def extent(self) -> Optional[Sequence[Extent]]:
        """
        Information about the horizontal, vertical, and temporal extent of
        the specified resource.
        """

    @property
    @abstractmethod
    def level_description(self) -> Optional[Sequence[ScopeDescription]]:
        """
        Detailed information/listing of the items specified by the level.
        """


class MaintenanceInformation(ABC):
    """Information about the scope and frequency of updating."""

    @property
    @abstractmethod
    def maintenance_and_update_frequency(self) -> Optional[
        MaintenanceFrequencyCode
    ]:
        """
        Frequency with which changes and additions are made to the resource
        after the initial resource is completed.

        MANDATORY:
            If `user_defined_maintenance_frequency` is `None`.
        """

    @property
    @abstractmethod
    def maintenance_date(self) -> Optional[Sequence[Date]]:
        """Date information associated with maintenance of resource."""

    @property
    @abstractmethod
    def user_defined_maintenance_frequency(self) -> Optional[timedelta]:
        """
        Maintenance period other than those defined.

        MANDATORY:
            If `user_defined_maintenance_frequency` is `None`.
        """

    @property
    @abstractmethod
    def maintenance_scope(self) -> Optional[Sequence[Scope]]:
        """Information about the scope and extent of maintenance."""

    @property
    @abstractmethod
    def maintenance_note(self) -> Optional[Sequence[str]]:
        """
        Information regarding specific requirements for maintaining the
        resource.
        """

    @property
    @abstractmethod
    def contact(self) -> Optional[Sequence[Responsibility]]:
        """
        Identification of, and means of communicating with, person(s) and
        organisation(s) with responsibility for maintaining the metadata.
        """
