#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractproperty
from typing import Sequence



from opengis.metadata.citation import Citation, Identifier, Responsibility, Date, OnlineResource

class PortrayalCatalogueReference(ABC):
    """Information identifying the portrayal catalogue used."""

    @abstractproperty
    def portrayal_catalogue_citation(self) -> Sequence[Citation]:
        """Bibliographic reference to the portrayal catalogue cited."""
        pass



from opengis.metadata.maintenance import ScopeCode, MaintenanceInformation

class MetadataScope(ABC):

    @abstractproperty
    def resource_scope(self) -> ScopeCode:
        pass

    @property
    def name(self) -> str:
        return None



from opengis.metadata.representation import SpatialRepresentation
from opengis.metadata.extension import MetadataExtensionInformation, ApplicationSchemaInformation
from opengis.metadata.identification import Identification
from opengis.metadata.content import ContentInformation
from opengis.metadata.distribution import Distribution
from opengis.metadata.quality import DataQuality
from opengis.metadata.lineage import Lineage
from opengis.metadata.constraints import Constraints
from opengis.metadata.acquisition import AcquisitionInformation

class Metadata(ABC):
    """Root entity which defines metadata about a resource or resources."""

    @property
    def metadata_identifier(self) -> Identifier:
        return None

    @property
    def parent_metadata(self) -> Citation:
        """Identifier and onlineResource for a parent metadata record."""
        return None

    @property
    def metadata_scope(self) -> Sequence[MetadataScope]:
        return None

    @abstractproperty
    def contact(self) -> Sequence[Responsibility]:
        """Party responsible for the metadata information."""
        pass

    @abstractproperty
    def date_info(self) -> Sequence[Date]:
        """Date(s) other than creation dateEG: expiry date."""
        pass

    @property
    def metadata_standard(self) -> Sequence[Citation]:
        """Citation for the standards to which the metadata conforms."""
        return None

    @property
    def metadata_profile(self) -> Sequence[Citation]:
        return None

    @property
    def alternative_metadata_reference(self) -> Sequence[Citation]:
        """Unique Identifier and onlineResource for alternative metadata."""
        return None

    @property
    def metadata_linkage(self) -> Sequence[OnlineResource]:
        """Online location where the metadata is available."""
        return None

    @property
    def spatial_representation_info(self) -> Sequence[SpatialRepresentation]:
        return None

    @property
    def reference_system_info(self):
        return None

    @property
    def metadata_extension_info(self) -> Sequence[MetadataExtensionInformation]:
        return None

    @abstractproperty
    def identification_info(self) -> Sequence[Identification]:
        pass

    @property
    def content_info(self) -> Sequence[ContentInformation]:
        return None

    @property
    def distribution_info(self) -> Sequence[Distribution]:
        return None

    @property
    def data_quality_info(self) -> Sequence[DataQuality]:
        return None

    @property
    def resource_lineage(self) -> Sequence[Lineage]:
        return None

    @property
    def portrayal_catalogue_info(self) -> Sequence[PortrayalCatalogueReference]:
        return None

    @property
    def metadata_constraints(self) -> Sequence[Constraints]:
        return None

    @property
    def application_schema_info(self) -> Sequence[ApplicationSchemaInformation]:
        return None

    @property
    def metadata_maintenance(self) -> MaintenanceInformation:
        return None

    @property
    def acquisition_information(self) -> Sequence[AcquisitionInformation]:
        return None

    @property
    def character_set(self):
        return None

    @property
    def language(self):
        return None
