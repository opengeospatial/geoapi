#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty

class PortrayalCatalogueReference(ABC):
    """Information identifying the portrayal catalogue used."""

    @abstractproperty
    def portrayalCatalogueCitation(self) -> Citation:
        """Bibliographic reference to the portrayal catalogue cited."""
        pass

class MetadataScope(ABC):

    @abstractproperty
    def resourceScope(self) -> ScopeCode:
        pass

    @abstractproperty
    def name(self) -> str:
        pass

class Metadata(ABC):
    """Root entity which defines metadata about a resource or resources."""

    @abstractproperty
    def metadataIdentifier(self) -> Identifier:
        pass

    @abstractproperty
    def parentMetadata(self) -> Citation:
        """Identifier and onlineResource for a parent metadata record."""
        pass

    @abstractproperty
    def metadataScope(self) -> MetadataScope:
        pass

    @abstractproperty
    def contact(self) -> Responsibility:
        """Party responsible for the metadata information."""
        pass

    @abstractproperty
    def dateInfo(self) -> Date:
        """Date(s) other than creation dateEG: expiry date."""
        pass

    @abstractproperty
    def metadataStandard(self) -> Citation:
        """Citation for the standards to which the metadata conforms."""
        pass

    @abstractproperty
    def metadataProfile(self) -> Citation:
        pass

    @abstractproperty
    def alternativeMetadataReference(self) -> Citation:
        """Unique Identifier and onlineResource for alternative metadata."""
        pass

    @abstractproperty
    def metadataLinkage(self) -> OnlineResource:
        """Online location where the metadata is available."""
        pass

    @abstractproperty
    def spatialRepresentationInfo(self) -> SpatialRepresentation:
        pass

    @abstractproperty
    def referenceSystemInfo(self) -> ReferenceSystem:
        pass

    @abstractproperty
    def metadataExtensionInfo(self) -> MetadataExtensionInformation:
        pass

    @abstractproperty
    def identificationInfo(self) -> Identification:
        pass

    @abstractproperty
    def contentInfo(self) -> ContentInformation:
        pass

    @abstractproperty
    def distributionInfo(self) -> Distribution:
        pass

    @abstractproperty
    def dataQualityInfo(self) -> DataQuality:
        pass

    @abstractproperty
    def resourceLineage(self) -> Lineage:
        pass

    @abstractproperty
    def portrayalCatalogueInfo(self) -> PortrayalCatalogueReference:
        pass

    @abstractproperty
    def metadataConstraints(self) -> Constraints:
        pass

    @abstractproperty
    def applicationSchemaInfo(self) -> ApplicationSchemaInformation:
        pass

    @abstractproperty
    def metadataMaintenance(self) -> MaintenanceInformation:
        pass

    @abstractproperty
    def acquisitionInformation(self) -> AcquisitionInformation:
        pass

    @abstractproperty
    def characterSet(self):
        pass

    @abstractproperty
    def language(self):
        pass
