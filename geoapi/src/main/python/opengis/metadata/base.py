# ===-----------------------------------------------------------------------===
#    GeoAPI - Python interfaces (abstractions) for OGC/ISO standards
#    Copyright © 2013-2024 Open Geospatial Consortium, Inc.
#    http: //www.geoapi.org
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#        http: //www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
# ===-----------------------------------------------------------------------===
"""This is the `base` module.

This subpackage contains geographic metadata structures regarding data
acquisition that are derived from theISO 19115-1:2014 and ISO 19115-2:2019
international standards.
"""

__author__ = "OGC Topic 11 (for abstract model and documentation), " +\
    "Martin Desruisseaux (Geomatys), David Meaux (Geomatys)"

from abc import ABC, abstractmethod
from collections.abc import Sequence
from typing import Optional

from opengis.metadata.acquisition import AcquisitionInformation
from opengis.metadata.citation import (
    Citation,
    Date,
    Identifier,
    OnlineResource,
    Responsibility,
)
from opengis.metadata.constraints import Constraints
from opengis.metadata.content import ContentInformation
from opengis.metadata.distribution import Distribution
from opengis.metadata.extension import (
    ApplicationSchemaInformation,
    MetadataExtensionInformation,
)
from opengis.metadata.identification import Identification
from opengis.metadata.lineage import Lineage
from opengis.metadata.maintenance import MaintenanceInformation, ScopeCode
from opengis.metadata.quality import DataQuality
from opengis.metadata.representation import SpatialRepresentation
from opengis.referencing.crs import ReferenceSystem


class PortrayalCatalogueReference(ABC):
    """Information identifying the portrayal catalogue used."""

    @property
    @abstractmethod
    def portrayal_catalogue_citation(self) -> Sequence[Citation]:
        """Bibliographic reference to the portrayal catalogue cited."""


class MetadataScope(ABC):
    """Information about the scope of the resource."""

    @property
    @abstractmethod
    def resource_scope(self) -> ScopeCode:
        """Code for the scope. Default = 'dataset'."""

    @property
    @abstractmethod
    def name(self) -> Optional[str]:
        """Description of the scope."""


class Metadata(ABC):
    """Root entity which defines metadata about a resource or resources."""

    @property
    @abstractmethod
    def metadata_identifier(self) -> Optional[Identifier]:
        """Unique identifier for this metadata record."""

    @property
    @abstractmethod
    def default_locale(self) -> Optional[str]:
        """
        Language and character set used for documenting metadata.
        A string conforming to IETF BCP 47.

        MANDATORY: if UTF-8 not used and not defined in encoding.
        """

    @property
    @abstractmethod
    def parent_metadata(self) -> Optional[Citation]:
        """
        Identification of the parent metadata record.

        MANDATORY: if there is an upper level object.
        """

    @property
    @abstractmethod
    def contact(self) -> Sequence[Responsibility]:
        """Party responsible for the metadata information."""

    @property
    @abstractmethod
    def date_info(self) -> Sequence[Date]:
        """
        Date(s) associated with the metadata.

        NOTE: 'Creation' date must be provided, others can also be provided.
        """

    @property
    @abstractmethod
    def metadata_standard(self) -> Optional[Sequence[Citation]]:
        """
        Citation for the standards to which the metadata conforms.

        NOTE: Metadata standard citations should include an identifier.
        """

    @property
    @abstractmethod
    def metadata_profile(self) -> Optional[Sequence[Citation]]:
        """
        Citation(s) for the profile(s) of the metadata standard to which the
        metadata conform.

        NOTE: Metadata citations should include an identifier.
        """

    @property
    @abstractmethod
    def alternative_metadata_reference(self) -> Optional[Sequence[Citation]]:
        """
        Reference to alternative metadata,e.g., Dublin Core, FGDC, or metadata
        in a non-ISO standard for the same resource.
        """

    @property
    @abstractmethod
    def other_locale(self) -> Optional[Sequence[str]]:
        """
        Provides information about an alternatively used localized character
        strings. A string conforming to IETF BCP 47.
        """

    @property
    @abstractmethod
    def metadata_linkage(self) -> Optional[Sequence[OnlineResource]]:
        """Online location where the metadata is available."""

    @property
    @abstractmethod
    def spatial_representation_info(self) -> Optional[Sequence[
        SpatialRepresentation
    ]]:
        """Digital representation of spatial information in the dataset."""

    @property
    @abstractmethod
    def reference_system_info(self) -> Optional[Sequence[ReferenceSystem]]:
        """
        Description of the spatial and temporal reference systems used in
        the dataset.

        The reference system may be:
        - An ISO 19111 object such as `CoordinateReferenceSystem`.
        - A `ReferenceSystem` with the `identifier` property (from
            ISO 19111) sets to a list of `Identifier` values such as
            `["EPSG::4326"]`.
        - An object with the `referenceSystemIdentifier` property (from
            ISO 19115) sets to a single `Identifier` value such as
                `"EPSG::4326"`,
        The ReferenceSystem object may optionally have a
            `reference_system_type_code` property set to a value such as
            `geodeticGeographic2D` or `compoundProjectedTemporal`.
        """

    @property
    @abstractmethod
    def metadata_extension_info(self) -> Optional[Sequence[
        MetadataExtensionInformation
    ]]:
        """Information describing metadata extensions."""

    @property
    @abstractmethod
    def identification_info(self) -> Sequence[Identification]:
        """
        Basic information about the resource(s) to which the metadata applies.
        """

    @property
    @abstractmethod
    def content_info(self) -> Optional[Sequence[ContentInformation]]:
        """Information about the feature and coverage characteristics."""

    @property
    @abstractmethod
    def distribution_info(self) -> Optional[Sequence[Distribution]]:
        """
        Information about the distributor of and options for obtaining the
        resource(s).
        """

    @property
    @abstractmethod
    def data_quality_info(self) -> Optional[Sequence[DataQuality]]:
        """Overall assessment of quality of a resource(s)."""

    @property
    @abstractmethod
    def portrayal_catalogue_info(self) -> Optional[Sequence[
        PortrayalCatalogueReference
    ]]:
        """
        Information about the catalogue of rules defined for the portrayal of
        a resource(s).
        """

    @property
    @abstractmethod
    def metadata_constraints(self) -> Optional[Sequence[Constraints]]:
        """Restrictions on the access and use of metadata."""

    @property
    @abstractmethod
    def application_schema_info(self) -> Optional[Sequence[
        ApplicationSchemaInformation
    ]]:
        """Information about the conceptual schema of a dataset."""

    @property
    @abstractmethod
    def metadata_maintenance(self) -> Optional[MaintenanceInformation]:
        """
        Information about the frequency of metadata updates, and the scope of
        those updates.
        """

    @property
    @abstractmethod
    def resource_lineage(self) -> Optional[Sequence[Lineage]]:
        """
        Information about the provenance, sources and/or the production
        processes applied to the resource.
        """

    @property
    @abstractmethod
    def metadata_scope(self) -> Optional[Sequence[MetadataScope]]:
        """
        The scope or type of resource for which metadata is provided.

        MANDATORY: if `Metadata` is about a resource other than a dataset.
        """

    @property
    @abstractmethod
    def acquisition_information(self) -> Optional[
        Sequence[AcquisitionInformation]
    ]:
        """Information about the acquisition of the data."""
