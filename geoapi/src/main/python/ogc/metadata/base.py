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

    @property
    def name(self) -> str:
        return None

class Metadata(ABC):
    """Root entity which defines metadata about a resource or resources."""

    @property
    def metadataIdentifier(self) -> Identifier:
        return None

    @property
    def parentMetadata(self) -> Citation:
        """Identifier and onlineResource for a parent metadata record."""
        return None

    @property
    def metadataScope(self) -> MetadataScope:
        return None

    @abstractproperty
    def contact(self) -> Responsibility:
        """Party responsible for the metadata information."""
        pass

    @abstractproperty
    def dateInfo(self) -> Date:
        """Date(s) other than creation dateEG: expiry date."""
        pass

    @property
    def metadataStandard(self) -> Citation:
        """Citation for the standards to which the metadata conforms."""
        return None

    @property
    def metadataProfile(self) -> Citation:
        return None

    @property
    def alternativeMetadataReference(self) -> Citation:
        """Unique Identifier and onlineResource for alternative metadata."""
        return None

    @property
    def metadataLinkage(self) -> OnlineResource:
        """Online location where the metadata is available."""
        return None

    @property
    def spatialRepresentationInfo(self) -> SpatialRepresentation:
        return None

    @property
    def referenceSystemInfo(self) -> ReferenceSystem:
        return None

    @property
    def metadataExtensionInfo(self) -> MetadataExtensionInformation:
        return None

    @abstractproperty
    def identificationInfo(self) -> Identification:
        pass

    @property
    def contentInfo(self) -> ContentInformation:
        return None

    @property
    def distributionInfo(self) -> Distribution:
        return None

    @property
    def dataQualityInfo(self) -> DataQuality:
        return None

    @property
    def resourceLineage(self) -> Lineage:
        return None

    @property
    def portrayalCatalogueInfo(self) -> PortrayalCatalogueReference:
        return None

    @property
    def metadataConstraints(self) -> Constraints:
        return None

    @property
    def applicationSchemaInfo(self) -> ApplicationSchemaInformation:
        return None

    @property
    def metadataMaintenance(self) -> MaintenanceInformation:
        return None

    @property
    def acquisitionInformation(self) -> AcquisitionInformation:
        return None

    @property
    def characterSet(self):
        pass

    @property
    def language(self):
        pass
