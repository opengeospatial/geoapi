#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
#    http://www.geoapi.org
#

from abc import ABC, abstractmethod
from typing import Sequence



class NameSpace(ABC):
    """A domain in which names are defined."""

    @property
    @abstractmethod
    def is_global(self):
        """Indicates whether this namespace is a "top level" namespace. Global, or top-level namespaces are not contained within another namespace. The global namespace has no parent."""
        pass

    @property
    @abstractmethod
    def name(self) -> 'GenericName':
        """The identifier of this namespace."""
        pass



class GenericName(ABC):
    """A sequence of identifiers rooted within the context of a namespace."""

    @property
    @abstractmethod
    def depth(self) -> int:
        """Indicates the number of levels specified by this name. For any ``LocalName``, it is always one. For a ``ScopedName`` it is some number greater than or equal to 2."""
        pass

    @property
    @abstractmethod
    def scope(self) -> NameSpace:
        """The scope (name space) in which this name is local. All names carry an association with their scope in which they are considered local, but the scope can be the global namespace."""
        pass

    @property
    @abstractmethod
    def parsed_name(self) -> Sequence['LocalName']:
        """The sequence of local names making this generic name. The length of this sequence is the depth. It does not include the scope."""
        pass



class LocalName(GenericName):
    """
    Identifier within a name space for a local object.
    Local names are names which are directly accessible to and maintained by a ``NameSpace``.
    Names are local to one and only one name space.
    The name space within which they are local is indicated by the scope.
    """

    @property
    @abstractmethod
    def __str__(self) -> str:
        """A string representation of this local name."""
        pass

    @property
    def depth(self) -> int:
        """The number of levels specified by this name, which is always 1 for a local name."""
        return 1

    @property
    def parsed_name(self) -> Sequence['LocalName']:
        """The sequence of local names. Since this object is itself a locale name, the parsed name is always a singleton containing only ``self``."""
        return list(self)



class ScopedName(GenericName):
    """A composite of a ``LocalName`` (as head) for locating another name space, and a ``GenericName`` (as tail) valid in that name space."""

    @property
    @abstractmethod
    def head(self) -> LocalName:
        """The first element in the sequence of parsed names. The head element must exists in the same name space than this scoped name."""
        pass

    @property
    @abstractmethod
    def tail(self) -> GenericName:
        """Every elements in the sequence of parsed names except for the head."""
        pass

    @property
    @abstractmethod
    def __str__(self) -> str:
        """A string representation of this scoped name."""
        pass



class TypeName(LocalName):
    """A local name that references a record type."

    @property
    @abstractmethod
    def __str__(self) -> str:
        """A string representation of this type name."""
        pass



class Type(ABC):
    """Base interface of type definitions."""

    @property
    @abstractmethod
    def type_name(self) -> TypeName:
        """The name that identifies this type."""
        pass



class MemberName(LocalName):
    """A name that references either an attribute slot in a record, an attribute, operation, or association role in an object instance or a type description in some schema."""

    @property
    @abstractmethod
    def attribute_type(self) -> TypeName:
        """The type of the data associated with the member."""
        pass

    @property
    @abstractmethod
    def __str__(self) -> str:
        """A string representation of this member name."""
        pass



class RecordType(Type):
    """The type definition of a record. A ``RecordType`` defines dynamically constructed data type."""

    @property
    @abstractmethod
    def type_name(self) -> TypeName:
        """The name that identifies this record type."""
        pass

    @property
    @abstractmethod
    def field_type(self):
        """The dictionary of all (name, type) pairs in this record type."""
        pass



class Record(ABC):
    """A list of logically related fields as (name, value) pairs in a dictionary."""

    @property
    def type(self) -> RecordType:
        """The type definition of this record."""
        return None

    @property
    @abstractmethod
    def field(self):
        """The dictionary of all (name, value) pairs in this record."""
        pass
