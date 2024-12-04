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
"""This is the `common` module.

This module contains geographic metadata structures derived from the
Common Classes package in the ISO 19111:2019 international standard.
"""

from __future__ import annotations

from abc import ABC, abstractmethod
from collections.abc import Sequence
from typing import Optional

import opengis.metadata.citation as meta_citation
import opengis.metadata.extent as meta_extent
import opengis.metadata.naming as naming


__author__ = "OGC Topic 2 (for abstract model and documentation), " +\
    "David Meaux (Geomatys)"


class ObjectDomain(ABC):
    """The scope and validity of a CRS-related object."""

    @property
    @abstractmethod
    def scope(self) -> str:
        """
        Description of usage, or limitations of usage, for which this object
        is valid. If unknown, enter "not known".
        """

    @property
    @abstractmethod
    def domain_of_validity(self) -> meta_extent.Extent:
        """The spatial and temporal extent in which this object is valid."""


class IdentifiedObject(ABC):
    """Identifications of a CRS-related object."""

    @property
    @abstractmethod
    def name(self) -> meta_citation.Identifier:
        """The primary name by which this object is identified."""

    @property
    @abstractmethod
    def identifier(self) -> Optional[Sequence[meta_citation.Identifier]]:
        """
        An identifier which references elsewhere the object's defining
        information; alternatively an identifier by which this object can be
        referenced.
        """

    @property
    @abstractmethod
    def alias(self) -> Optional[Sequence[naming.GenericName]]:
        """An alternative name by which this object is identified."""

    @property
    @abstractmethod
    def remarks(self) -> Optional[Sequence[str]]:
        """
        Comments on or information about this object, including data source
        information.
        """

    @property
    @abstractmethod
    def domain(self) -> Optional[Sequence[ObjectDomain]]:
        """The scope and validity of the object."""

    @abstractmethod
    def to_wkt(self) -> str:
        """Returns a Well-Known Text (WKT) for this object."""
