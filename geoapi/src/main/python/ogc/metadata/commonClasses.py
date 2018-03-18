#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from enum import Enum

class ProgressCode(Enum):
    COMPLETED = "completed"
    HISTORICAL_ARCHIVE = "historicalArchive"
    OBSOLETE = "obsolete"
    ON_GOING = "onGoing"
    PLANNED = "planned"
    REQUIRED = "required"
    UNDER_DEVELOPMENT = "underDevelopment"
    FINAL = "final"
    PENDING = "pending"
    RETIRED = "retired"
    SUPERSEDED = "superseded"
    TENTATIVE = "tentative"
    VALID = "valid"
    ACCEPTED = "accepted"
    NOT_ACCEPTED = "notAccepted"
    WITHDRAWN = "withdrawn"
    PROPOSED = "proposed"
    DEPRECATED = "deprecated"

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

class SpatialRepresentationTypeCode(Enum):
    VECTOR = "vector"
    GRID = "grid"
    TEXT_TABLE = "textTable"
    TIN = "tin"
    STEREO_MODEL = "stereoModel"
    VIDEO = "video"

class Identifier(ABC):
    """Value uniquely identifying an object within a namespace."""

    @abstractproperty
    def authority(self) -> Citation:
        """Citation for the code namespace and optionally the person or party responsible for maintenance of that namespace."""
        pass

    @abstractproperty
    def code(self) -> str:
        """Alphanumeric value identifying an instance in the namespace e.g. EPSG::4326."""
        pass

    @abstractproperty
    def codeSpace(self) -> str:
        """Identifier or namespace in which the code is valid."""
        pass

    @abstractproperty
    def version(self) -> str:
        """Version identifier for the namespace."""
        pass

    @abstractproperty
    def description(self) -> str:
        """Natural language description of the meaning of the code value E.G for codeSpace = EPSG, code = 4326: description = WGS-84" to "for codeSpace = EPSG, code = EPSG::4326: description = WGS-84."""
        pass

class ScopeDescription(ABC):
    """Description of the class of information covered by the information."""

    @abstractproperty
    def attributes(self) -> str:
        """Instances of attribute types to which the information applies."""
        pass

    @abstractproperty
    def features(self) -> str:
        """Instances of feature types to which the information applies."""
        pass

    @abstractproperty
    def featureInstances(self) -> str:
        """Feature instances to which the information applies."""
        pass

    @abstractproperty
    def attributeInstances(self) -> str:
        """Attribute instances to which the information applies."""
        pass

    @abstractproperty
    def dataset(self) -> str:
        """Dataset to which the information applies."""
        pass

    @abstractproperty
    def other(self) -> str:
        """Class of information that does not fall into the other categories to which the information applies."""
        pass

class Scope(ABC):
    """New: information about the scope of the resource."""

    @abstractproperty
    def level(self) -> ScopeCode:
        """Description of the scope."""
        pass

    @abstractproperty
    def extent(self) -> Extent:
        pass

    @abstractproperty
    def levelDescription(self) -> ScopeDescription:
        pass

class BrowseGraphic(ABC):
    """Graphic that provides an illustration of the dataset (should include a legend for the graphic, if applicable)."""

    @abstractproperty
    def fileName(self):
        """Name of the file that contains a graphic that provides an illustration of the dataset."""
        pass

    @abstractproperty
    def fileDescription(self) -> str:
        """Text description of the illustration."""
        pass

    @abstractproperty
    def fileType(self) -> str:
        pass

    @abstractproperty
    def imageConstraints(self) -> Constraints:
        """Restriction on access and/or use of browse graphic."""
        pass

    @abstractproperty
    def linkage(self) -> OnlineResource:
        """Link to browse graphic."""
        pass
