#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractmethod
from typing import Sequence



class NameSpace(ABC):

    @property
    @abstractmethod
    def is_global(self):
        pass

    @property
    @abstractmethod
    def name(self) -> 'GenericName':
        pass



class GenericName(ABC):

    @property
    @abstractmethod
    def depth(self) -> int:
        pass

    @property
    @abstractmethod
    def scope(self) -> NameSpace:
        pass

    @property
    @abstractmethod
    def parsed_name(self) -> Sequence['LocalName']:
        pass



class LocalName(GenericName):

    @property
    @abstractmethod
    def a_name(self) -> str:
        pass

    @property
    @abstractmethod
    def parsed_name(self) -> Sequence['LocalName']:
        pass



class ScopedName(GenericName):

    @property
    @abstractmethod
    def head(self) -> LocalName:
        pass

    @property
    @abstractmethod
    def tail(self) -> GenericName:
        pass

    @property
    @abstractmethod
    def scoped_name(self) -> str:
        pass



class TypeName(LocalName):

    @property
    @abstractmethod
    def a_name(self) -> str:
        pass



class Type(ABC):

    @property
    @abstractmethod
    def type_name(self) -> TypeName:
        pass



class MemberName(LocalName):

    @property
    @abstractmethod
    def attribute_type(self) -> TypeName:
        pass

    @property
    @abstractmethod
    def a_name(self) -> str:
        pass



class RecordSchema(ABC):

    @property
    @abstractmethod
    def schema_name(self) -> LocalName:
        pass

    @property
    @abstractmethod
    def description(self):
        pass



class RecordType(Type):

    @property
    @abstractmethod
    def type_name(self) -> TypeName:
        pass

    @property
    @abstractmethod
    def schema(self) -> RecordSchema:
        pass

    @property
    @abstractmethod
    def member_types(self):
        pass



class Record(ABC):

    @property
    def record_type(self) -> RecordType:
        return None

    @property
    @abstractmethod
    def member_value(self):
        pass
