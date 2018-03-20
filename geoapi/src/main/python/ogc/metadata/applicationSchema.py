#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from enum import Enum

class DatatypeCode(Enum):
    CLASS = "class"
    CODE_LIST = "codelist"
    ENUMERATION = "enumeration"
    CODE_LIST_ELEMENT = "codelistElement"
    ABSTRACT_CLASS = "abstractClass"
    AGGREGATE_CLASS = "aggregateClass"
    SPECIFIED_CLASS = "specifiedClass"
    DATATYPE_CLASS = "datatypeClass"
    INTERFACE_CLASS = "interfaceClass"
    UNION_CLASS = "unionClass"
    META_CLASS = "metaClass"
    TYPE_CLASS = "typeClass"
    CHARACTER_STRING = "characterString"
    INTEGER = "integer"
    ASSOCIATION = "association"

class ObligationCode(Enum):
    MANDATORY = "mandatory"
    OPTIONAL = "optional"
    CONDITIONAL = "conditional"
    FORBIDDEN = "null"

class ApplicationSchemaInformation(ABC):
    """Information about the application schema used to build the dataset."""

    @abstractproperty
    def name(self) -> Citation:
        """Name of the application schema used."""
        pass

    @abstractproperty
    def schemaLanguage(self) -> str:
        """Identification of the schema language used."""
        pass

    @abstractproperty
    def constraintLanguage(self) -> str:
        """Formal language used in Application Schema."""
        pass

    @property
    def schemaAscii(self) -> str:
        """Full application schema given as an ASCII file."""
        return None

    @property
    def graphicsFile(self) -> OnlineResource:
        """Full application schema given as a graphics file."""
        return None

    @property
    def softwareDevelopmentFile(self) -> OnlineResource:
        """Full application schema given as a software development file."""
        return None

    @property
    def softwareDevelopmentFileFormat(self) -> str:
        """Software dependent format used for the application schema software dependent file."""
        return None

class ExtendedElementInformation(ABC):
    """New metadata element, not found in ISO 19115, which is required to describe geographic data."""

    @abstractproperty
    def name(self) -> str:
        """Name of the extended metadata element."""
        pass

    @abstractproperty
    def definition(self) -> str:
        """Definition of the extended element."""
        pass

    @property
    def obligation(self) -> ObligationCode:
        """Obligation of the extended element."""
        return None

    @property
    def condition(self) -> str:
        """Condition under which the extended element is mandatory."""
        return None

    @abstractproperty
    def dataType(self) -> DatatypeCode:
        """Code which identifies the kind of value provided in the extended element."""
        pass

    @property
    def maximumOccurrence(self) -> int:
        """Maximum occurrence of the extended element."""
        return None

    @property
    def domainValue(self) -> str:
        """Valid values that can be assigned to the extended element."""
        return None

    @abstractproperty
    def parentEntity(self) -> str:
        """Name of the metadata entity(s) under which this extended metadata element may appear. The name(s) may be standard metadata element(s) or other extended metadata element(s)."""
        pass

    @abstractproperty
    def rule(self) -> str:
        """Specifies how the extended element relates to other existing elements and entities."""
        pass

    @property
    def rationale(self) -> str:
        """Reason for creating the extended element."""
        return None

    @abstractproperty
    def source(self) -> Responsibility:
        """Name of the person or organisation creating the extended element."""
        pass

class MetadataExtensionInformation(ABC):
    """Information describing metadata extensions."""

    @property
    def extensionOnLineResource(self) -> OnlineResource:
        """Information about on-line sources containing the community profile name and the extended metadata elements. Information for all new metadata elements."""
        return None

    @property
    def extendedElementInformation(self) -> ExtendedElementInformation:
        return None
