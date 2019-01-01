#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from typing import Sequence



class NameSpace(ABC):

    @abstractproperty
    def is_global(self):
        pass

    @abstractproperty
    def name(self) -> 'GenericName':
        pass



class GenericName(ABC):

    @abstractproperty
    def depth(self) -> int:
        pass

    @abstractproperty
    def scope(self) -> NameSpace:
        pass

    @abstractproperty
    def parsed_name(self) -> Sequence['LocalName']:
        pass



class LocalName(GenericName):

    @abstractproperty
    def a_name(self) -> str:
        pass

    @abstractproperty
    def parsed_name(self) -> Sequence['LocalName']:
        pass



class ScopedName(GenericName):

    @abstractproperty
    def head(self) -> LocalName:
        pass

    @abstractproperty
    def tail(self) -> GenericName:
        pass

    @abstractproperty
    def scoped_name(self) -> str:
        pass



class TypeName(LocalName):

    @abstractproperty
    def a_name(self) -> str:
        pass



class Type(ABC):

    @abstractproperty
    def type_name(self) -> TypeName:
        pass



class MemberName(LocalName):

    @abstractproperty
    def attribute_type(self) -> TypeName:
        pass

    @abstractproperty
    def a_name(self) -> str:
        pass



class RecordSchema(ABC):

    @abstractproperty
    def schema_name(self) -> LocalName:
        pass

    @abstractproperty
    def description(self):
        pass



class RecordType(Type):

    @abstractproperty
    def type_name(self) -> TypeName:
        pass

    @abstractproperty
    def schema(self) -> RecordSchema:
        pass

    @abstractproperty
    def member_types(self):
        pass



class Record(ABC):

    @property
    def record_type(self) -> RecordType:
        return None

    @abstractproperty
    def member_value(self):
        pass
