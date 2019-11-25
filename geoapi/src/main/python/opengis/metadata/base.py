#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

from abc import ABC, abstractmethod
from typing import Sequence



from opengis.metadata.citation import Citation, Identifier, Responsibility, Date, OnlineResource

class PortrayalCatalogueReference(ABC):
    """Information identifying the portrayal catalogue used."""

    @property
    @abstractmethod
    def portrayal_catalogue_citation(self) -> Sequence[Citation]:
        """Bibliographic reference to the portrayal catalogue cited."""
        pass



from opengis.metadata.maintenance import ScopeCode, MaintenanceInformation

class MetadataScope(ABC):

    @property
    @abstractmethod
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
from opengis.referencing.crs import ReferenceSystem

class Metadata(ABC):
    """Root entity which defines metadata about a resource or resources."""

    @property
    def metadata_identifier(self) -> Identifier:
        """Unique identifier for this metadata record."""
        return None

    @property
    def default_locale(self):
        """Provides information about an alternatively used localized character string for a linguistic extension."""
        return None

    @property
    def parent_metadata(self) -> Citation:
        """Identifier and onlineResource for a parent metadata record."""
        return None

    @property
    def metadata_scope(self) -> Sequence[MetadataScope]:
        """The scope or type of resource for which metadata is provided."""
        return None

    @property
    @abstractmethod
    def contact(self) -> Sequence[Responsibility]:
        """Party responsible for the metadata information."""
        pass

    @property
    @abstractmethod
    def date_info(self) -> Sequence[Date]:
        """Date(s) other than creation date. Example: expiry date."""
        pass

    @property
    def metadata_standard(self) -> Sequence[Citation]:
        """Citation for the standards to which the metadata conforms."""
        return None

    @property
    def metadata_profile(self) -> Sequence[Citation]:
        """Citation(s) for the profile(s) of the metadata standard to which the metadata conform."""
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
        """Digital representation of spatial information in the dataset."""
        return None

    @property
    def reference_system_info(self) -> Sequence[ReferenceSystem]:
        """
        Description of the spatial and temporal reference systems used in the dataset.
        The reference system may be:

        * An ISO 19111 object such as ``CoordinateReferenceSystem``.
        * A ``ReferenceSystem`` with the ``identifier`` property (from ISO 19111) sets to a list of ``Identifier`` values such as ``["EPSG::4326"]``.
        * An object with the ``referenceSystemIdentifier`` property (from ISO 19115) sets to a single ``Identifier`` value such as ``"EPSG::4326"``,
          optionally with a ``referenceSystemType`` property sets to a value such as ``geodeticGeographic2D`` or ``compoundProjectedTemporal``.

        :rtype: Sequence[ReferenceSystem]
        """
        return None

    @property
    def metadata_extension_info(self) -> Sequence[MetadataExtensionInformation]:
        """Information describing metadata extensions."""
        return None

    @property
    @abstractmethod
    def identification_info(self) -> Sequence[Identification]:
        """Basic information about the resource(s) to which the metadata applies."""
        pass

    @property
    def content_info(self) -> Sequence[ContentInformation]:
        """Information about the feature and coverage characteristics."""
        return None

    @property
    def distribution_info(self) -> Sequence[Distribution]:
        """Information about the distributor of and options for obtaining the resource(s)."""
        return None

    @property
    def data_quality_info(self) -> Sequence[DataQuality]:
        """Overall assessment of quality of a resource(s)."""
        return None

    @property
    def resource_lineage(self) -> Sequence[Lineage]:
        """Information about the provenance, sources and/or the production processes applied to the resource."""
        return None

    @property
    def portrayal_catalogue_info(self) -> Sequence[PortrayalCatalogueReference]:
        """Information about the catalogue of rules defined for the portrayal of a resource(s)."""
        return None

    @property
    def metadata_constraints(self) -> Sequence[Constraints]:
        """Restrictions on the access and use of metadata."""
        return None

    @property
    def application_schema_info(self) -> Sequence[ApplicationSchemaInformation]:
        """Information about the conceptual schema of a dataset."""
        return None

    @property
    def metadata_maintenance(self) -> MaintenanceInformation:
        """Information about the frequency of metadata updates, and the scope of those updates."""
        return None

    @property
    def acquisition_information(self) -> Sequence[AcquisitionInformation]:
        """Information about the acquisition of the data."""
        return None
