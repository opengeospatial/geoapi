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
