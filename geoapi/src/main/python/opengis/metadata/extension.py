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
"""This is the `extension` module.

This module contains geographic metadata structures for metadata elements that
are not contained in the ISO 19115-1:2014 international standard.
"""

from __future__ import annotations

from abc import ABC, abstractmethod
from collections.abc import Sequence
from enum import Enum
from typing import Optional

import opengis.metadata.citation as meta_citation


__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"


class DatatypeCode(Enum):
    """Datatype of element or entity."""

    CLASS = "class"
    """
    Descriptor of a set of objects that share the same attributes, operations,
    methods, relationships, and behaviour.
    """

    CODE_LIST = "codelist"
    """
    Flexible enumeration useful for expressing a long list of values, can be
    extended.
    """

    ENUMERATION = "enumeration"
    """
    Data type whose instances form a list of named literal values, not
    extendable.
    """

    CODE_LIST_ELEMENT = "codelistElement"
    """Permissible value for a codelist or enumeration."""

    ABSTRACT_CLASS = "abstractClass"
    """Class that cannot be directly instantiated"""

    AGGREGATE_CLASS = "aggregateClass"
    """
    Class that is composed of classes it is connected to by an aggregate
    relationship.
    """

    SPECIFIED_CLASS = "specifiedClass"
    """Subclass that may be substituted for its superclass."""

    DATATYPE_CLASS = "datatypeClass"
    """
    Class  with few or no operations whose primary purpose is to hold the
    abstract state of another class for transmittal, storage, encoding, or
    persistent storage.
    """

    INTERFACE_CLASS = "interfaceClass"
    """
    Named set of operations that characterize the bahaviour of an element.
    """

    UNION_CLASS = "unionClass"
    """Class describing a selection of one of the specified types."""

    META_CLASS = "metaClass"
    """Class whose instances are classes."""

    TYPE_CLASS = "typeClass"
    """
    Class used for specification of a domain of instances (objects), together
    with the operations applicable to the objects. A type may have attributes
    and associations.
    """

    CHARACTER_STRING = "characterString"
    """Textual infromation."""

    INTEGER = "integer"
    """Numerical field."""

    ASSOCIATION = "association"
    """
    Semantic relationship between two classes that involves connections among
    their instances.
    """


class ObligationCode(Enum):
    """Obligation of the element or entity."""

    MANDATORY = "mandatory"
    """Element is always required."""

    OPTIONAL = "optional"
    """Element is not required."""

    CONDITIONAL = "conditional"
    """element is required when a specific condition is met."""

    FORBIDDEN = None
    """
    The element should always be `None`. This obligation code is used only
    when a sub-class overrides an association and force it to a `None`
    value. An example is
    `opengis.referencing.datum.TemporalDatum.anchor_point`.
    """


class ApplicationSchemaInformation(ABC):
    """Information about the application schema used to build the dataset."""

    @property
    @abstractmethod
    def name(self) -> meta_citation.Citation:
        """Name of the application schema used."""

    @property
    @abstractmethod
    def schema_language(self) -> str:
        """Identification of the schema language used."""

    @property
    @abstractmethod
    def constraint_language(self) -> str:
        """Formal language used in Application Schema."""

    @property
    @abstractmethod
    def schema_ascii(self) -> Optional[str]:
        """Full application schema given as an ASCII file."""

    @property
    @abstractmethod
    def graphics_file(self) -> Optional[meta_citation.OnlineResource]:
        """Full application schema given as a graphics file."""

    @property
    @abstractmethod
    def software_development_file(self) -> \
            Optional[meta_citation.OnlineResource]:
        """Full application schema given as a software development file."""

    @property
    @abstractmethod
    def software_development_file_format(self) -> Optional[str]:
        """
        Software dependent format used for the application schema software
        dependent file.
        """


class ExtendedElementInformation(ABC):
    """
    New metadata element, not found in ISO 19115, which is required to
    describe geographic data.
    """

    @property
    @abstractmethod
    def name(self) -> Optional[str]:
        """
        Name of the extended metadata element.

        MANDATORY:

        if `data_type` != CODE_LIST and `datatype` != ENUMERATION
            and `data_type` != CODE_LIST_ELEMENT.
        """

    @property
    @abstractmethod
    def definition(self) -> str:
        """Definition of the extended element."""

    @property
    @abstractmethod
    def obligation(self) -> Optional[ObligationCode]:
        """
        Obligation of the extended element.

        MANDATORY:
            If `data_type` != CODE_LIST and `datatype` != ENUMERATION
            and `data_type` != CODE_LIST_ELEMENT.
        """

    @property
    @abstractmethod
    def condition(self) -> Optional[str]:
        """
        Condition under which the extended element is mandatory.

        MANDATORY:
            If `obligation` == .
        """

    @property
    @abstractmethod
    def data_type(self) -> DatatypeCode:
        """
        Code which identifies the kind of value provided in the extended
        element.
        """

    @property
    @abstractmethod
    def maximum_occurrence(self) -> Optional[int]:
        """
        Maximum occurrence of the extended element.

        MANDATORY:
            If `data_type` != CODE_LIST and `datatype` != ENUMERATION
            and `data_type` != CODE_LIST_ELEMENT.
        """

    @property
    @abstractmethod
    def domain_value(self) -> Optional[str]:
        """
        Valid values that can be assigned to the extended element.

        MANDATORY:
            If `data_type` != CODE_LIST and `datatype` != ENUMERATION
            and `data_type` != CODE_LIST_ELEMENT.
        """

    @property
    @abstractmethod
    def parent_entity(self) -> Sequence[str]:
        """
        Name of the metadata entity(s) under which this extended metadata
        element may appear. The name(s) may be standard metadata element(s) or
        other extended metadata element(s).
        """

    @property
    @abstractmethod
    def rule(self) -> str:
        """
        Specifies how the extended element relates to other existing elements
        and entities.
        """

    @property
    @abstractmethod
    def rationale(self) -> Optional[str]:
        """Reason for creating the extended element."""

    @property
    @abstractmethod
    def source(self) -> Sequence[meta_citation.Responsibility]:
        """Name of the person or organisation creating the extended element."""

    @property
    @abstractmethod
    def concept_name(self) -> Optional[str]:
        """
        The name of the item.

        MANDATORY:
            If `data_type` != CODE_LIST and `datatype` != ENUMERATION
            and `data_type` != CODE_LIST_ELEMENT.
        """

    @property
    @abstractmethod
    def code(self) -> Optional[str]:
        """Language neutral identifier.

        MANDATORY:
            If `data_type` != CODE_LIST and `datatype` != ENUMERATION
            and `data_type` != CODE_LIST_ELEMENT.
        """


class MetadataExtensionInformation(ABC):
    """Information describing metadata extensions."""

    @property
    @abstractmethod
    def extension_on_line_resource(self) -> \
            Optional[meta_citation.OnlineResource]:
        """
        Information about on-line sources containing the community profile
        name and the extended metadata elements and information for all new
        metadata elements about on-line sources containing the community
        profile name, the extended metadata elements and information about all
        new metadata elements.
        """

    @property
    @abstractmethod
    def extended_element_information(self) -> Sequence[
        ExtendedElementInformation
    ]:
        """
        Provides information about a new metadata element, not found
        in ISO 19115, which is required to describe the resource.
        """
