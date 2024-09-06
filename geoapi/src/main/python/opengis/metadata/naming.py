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
"""This is the naming module.

This module contains geographic metadata structures regarding naming derived
from the ISO 19115-1:2014 international standard.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux(Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence


class NameSpace(ABC):
    """A domain in which names are defined."""

    @property
    @abstractmethod
    def is_global(self):
        """
        Indicates whether this namespace is a "top level" namespace. Global,
        or top-level namespaces are not contained within another namespace.
        The global namespace has no parent.
        """

    @property
    @abstractmethod
    def name(self) -> 'GenericName':
        """The identifier of this namespace."""


class GenericName(ABC):
    """A sequence of identifiers rooted within the context of a namespace."""

    @property
    @abstractmethod
    def depth(self) -> int:
        """
        Indicates the number of levels specified by this name. For any
        `LocalName`, it is always one. For a `ScopedName` it is some number
        greater than or equal to 2.
        """

    @property
    @abstractmethod
    def scope(self) -> NameSpace:
        """
        The scope (name space) in which this name is local. All names carry an
        association with their scope in which they are considered local, but
        the scope can be the global namespace.
        """

    @property
    @abstractmethod
    def parsed_name(self) -> Sequence['LocalName']:
        """
        The sequence of local names making this generic name. The length of
        this sequence is the depth. It does not include the scope.
        """


class LocalName(GenericName):
    """
    Identifier within a name space for a local object. Local names are names
    that are directly accessible to and maintained by a `NameSpace`. Names are
    local to one and only one name space. The name space within which they are
    local is indicated by the scope.
    """

    @abstractmethod
    def __str__(self) -> str:
        """A string representation of this local name."""

    @property
    @abstractmethod
    def depth(self) -> int:
        """The number of levels specified by this name, which is always 1 for a local name."""
        return 1

    @property
    @abstractmethod
    def parsed_name(self) -> Sequence['LocalName']:
        """
        The sequence of local names. Since this object is itself a locale name,
        the parsed name is always a singleton containing only `self`.
        """
        return list(self)


class ScopedName(GenericName):
    """
    A composite of a `LocalName` (as head) for locating another name space,
    and a `GenericName` (as tail) valid in that name space.
    """

    @property
    @abstractmethod
    def head(self) -> LocalName:
        """
        The first element in the sequence of parsed names. The head element
        must exists in the same name space as this scoped name.
        """

    @property
    @abstractmethod
    def tail(self) -> GenericName:
        """
        Every elements in the sequence of parsed names except for the head.
        """

    @abstractmethod
    def __str__(self) -> str:
        """A string representation of this scoped name."""


class TypeName(LocalName):
    """A local name that references a record type."""

    @abstractmethod
    def __str__(self) -> str:
        """A string representation of this type name."""


class Type(ABC):
    """Base interface of type definitions."""

    @property
    @abstractmethod
    def type_name(self) -> TypeName:
        """The name that identifies this type."""


class MemberName(LocalName):
    """
    A name that references either an attribute slot in a record, an attribute,
    operation, or association role in an object instance or a type description
    in some schema.
    """

    @property
    @abstractmethod
    def attribute_type(self) -> TypeName:
        """The type of the data associated with the member."""

    @abstractmethod
    def __str__(self) -> str:
        """A string representation of this member name."""


class RecordType(Type):
    """
    The type definition of a record. A `RecordType` defines dynamically
    constructed data type.
    """

    @property
    @abstractmethod
    def type_name(self) -> TypeName:
        """The name that identifies this record type."""

    @property
    @abstractmethod
    def field_type(self):
        """The dictionary of all (name, type) pairs in this record type."""


class Record(ABC):
    """
    A list of logically related fields as (name, value) pairs in a dictionary.
    """

    @property
    @abstractmethod
    def type(self) -> RecordType:
        """The type definition of this record."""

    @property
    @abstractmethod
    def field(self):
        """The dictionary of all (name, value) pairs in this record."""
