#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from typing import Sequence

class NameSpace(ABC):

    @abstractproperty
    def isGlobal(self):
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
    def parsedName(self) -> Sequence['LocalName']:
        pass

class LocalName(GenericName):

    @abstractproperty
    def aName(self) -> str:
        pass

    @abstractproperty
    def parsedName(self) -> Sequence['LocalName']:
        pass

class ScopedName(GenericName):

    @abstractproperty
    def head(self) -> LocalName:
        pass

    @abstractproperty
    def tail(self) -> GenericName:
        pass

    @abstractproperty
    def scopedName(self) -> str:
        pass

class TypeName(LocalName):

    @abstractproperty
    def aName(self) -> str:
        pass

class Type(ABC):

    @abstractproperty
    def typeName(self) -> TypeName:
        pass

class MemberName(LocalName):

    @abstractproperty
    def attributeType(self) -> TypeName:
        pass

    @abstractproperty
    def aName(self) -> str:
        pass

class RecordSchema(ABC):

    @abstractproperty
    def schemaName(self) -> LocalName:
        pass

    @abstractproperty
    def description(self):
        pass

class RecordType(Type):

    @abstractproperty
    def typeName(self) -> TypeName:
        pass

    @abstractproperty
    def schema(self) -> RecordSchema:
        pass

    @abstractproperty
    def memberTypes(self):
        pass

class Record(ABC):

    @property
    def recordType(self) -> RecordType:
        return None

    @abstractproperty
    def memberValue(self):
        pass
