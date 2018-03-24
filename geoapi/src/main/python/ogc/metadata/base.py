#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from typing import Sequence



from ogc.metadata.citation import Citation, Identifier, Responsibility, Date, OnlineResource

class PortrayalCatalogueReference(ABC):
    """Information identifying the portrayal catalogue used."""

    @abstractproperty
    def portrayalCatalogueCitation(self) -> Sequence[Citation]:
        """Bibliographic reference to the portrayal catalogue cited."""
        pass



from ogc.metadata.maintenance import ScopeCode, MaintenanceInformation

class MetadataScope(ABC):

    @abstractproperty
    def resourceScope(self) -> ScopeCode:
        pass

    @property
    def name(self) -> str:
        return None



from ogc.metadata.spatialRepresentation import SpatialRepresentation
from ogc.metadata.applicationSchema import MetadataExtensionInformation, ApplicationSchemaInformation
from ogc.metadata.identification import Identification
from ogc.metadata.content import ContentInformation
from ogc.metadata.distribution import Distribution
from ogc.metadata.dataQuality import DataQuality
from ogc.metadata.lineage import Lineage
from ogc.metadata.constraints import Constraints
from ogc.metadata.acquisition import AcquisitionInformation

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
    def metadataScope(self) -> Sequence[MetadataScope]:
        return None

    @abstractproperty
    def contact(self) -> Sequence[Responsibility]:
        """Party responsible for the metadata information."""
        pass

    @abstractproperty
    def dateInfo(self) -> Sequence[Date]:
        """Date(s) other than creation dateEG: expiry date."""
        pass

    @property
    def metadataStandard(self) -> Sequence[Citation]:
        """Citation for the standards to which the metadata conforms."""
        return None

    @property
    def metadataProfile(self) -> Sequence[Citation]:
        return None

    @property
    def alternativeMetadataReference(self) -> Sequence[Citation]:
        """Unique Identifier and onlineResource for alternative metadata."""
        return None

    @property
    def metadataLinkage(self) -> Sequence[OnlineResource]:
        """Online location where the metadata is available."""
        return None

    @property
    def spatialRepresentationInfo(self) -> Sequence[SpatialRepresentation]:
        return None

    @property
    def referenceSystemInfo(self):
        return None

    @property
    def metadataExtensionInfo(self) -> Sequence[MetadataExtensionInformation]:
        return None

    @abstractproperty
    def identificationInfo(self) -> Sequence[Identification]:
        pass

    @property
    def contentInfo(self) -> Sequence[ContentInformation]:
        return None

    @property
    def distributionInfo(self) -> Sequence[Distribution]:
        return None

    @property
    def dataQualityInfo(self) -> Sequence[DataQuality]:
        return None

    @property
    def resourceLineage(self) -> Sequence[Lineage]:
        return None

    @property
    def portrayalCatalogueInfo(self) -> Sequence[PortrayalCatalogueReference]:
        return None

    @property
    def metadataConstraints(self) -> Sequence[Constraints]:
        return None

    @property
    def applicationSchemaInfo(self) -> Sequence[ApplicationSchemaInformation]:
        return None

    @property
    def metadataMaintenance(self) -> MaintenanceInformation:
        return None

    @property
    def acquisitionInformation(self) -> Sequence[AcquisitionInformation]:
        return None

    @property
    def characterSet(self):
        return None

    @property
    def language(self):
        return None
