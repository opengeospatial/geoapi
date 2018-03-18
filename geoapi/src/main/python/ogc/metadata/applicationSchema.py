#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty

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

    @abstractproperty
    def schemaAscii(self) -> str:
        """Full application schema given as an ASCII file."""
        pass

    @abstractproperty
    def graphicsFile(self) -> OnlineResource:
        """Full application schema given as a graphics file."""
        pass

    @abstractproperty
    def softwareDevelopmentFile(self) -> OnlineResource:
        """Full application schema given as a software development file."""
        pass

    @abstractproperty
    def softwareDevelopmentFileFormat(self) -> str:
        """Software dependent format used for the application schema software dependent file."""
        pass
