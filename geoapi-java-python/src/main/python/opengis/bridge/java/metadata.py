#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
#

#
# This module requires jpy module to be installed on the local machine.
# That Java-Python module is documented at http://jpy.readthedocs.io/.
# In addition, the PYTHONPATH environmental variable must be set to the
# "geoapi/src/main/python" directory, preferably using absolute path.
#

import jpy
import opengis.metadata.base
import opengis.metadata.citation
import opengis.metadata.identification



class MetadataScope(opengis.metadata.base.MetadataScope):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def resource_scope(self):
        return self._proxy.getResourceScope()



class Citation(opengis.metadata.citation.Citation):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def title(self):
        return self._proxy.getTitle()

    def __str__(self):
        return self._proxy.toString()



class Identifier(opengis.metadata.citation.Identifier):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def code(self):
        return self._proxy.getCode()

    @property
    def code_space(self):
        return self._proxy.getCodeSpace()

    @property
    def version(self):
        return self._proxy.getVersion()

    @property
    def description(self):
        return self._proxy.getDescription()

    def __str__(self):
        return self._proxy.toString()



class Identification(opengis.metadata.identification.Identification):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def citation(self):
        value = self._proxy.getCitation()
        if value:
            return Citation(value)
        else:
            return None

    @property
    def abstract(self):
        return self._proxy.getAbstract()

    def __str__(self):
        return self._proxy.toString()



class Dimension(opengis.metadata.representation.Dimension):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def dimension_name(self):
        return self._proxy.getDimensionName()

    @property
    def dimension_size(self) -> int:
        return self._proxy.getDimensionSize()



class GridSpatialRepresentation(opengis.metadata.representation.SpatialRepresentation):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def number_of_dimensions(self):
        return self._proxy.getNumberOfDimensions()

    @property
    def axis_dimension_properties(self):
        value = self._proxy.getAxisDimensionProperties()
        if value:
            it = value.iterator()
            if it.hasNext():
                return [Dimension(it.next()), Dimension(it.next())]
            else:
                return []
        else:
            return []

    @property
    def cell_geometry(self):
        return self._proxy.getCellGeometry()

    @property
    def transformation_parameter_availability(self):
        return self._proxy.getTransformationParameterAvailability()



class Metadata(opengis.metadata.base.Metadata):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def metadata_scope(self):
        value = self._proxy.getMetadataScopes()
        if value:
            it = value.iterator()
            if it.hasNext():
                return [MetadataScope(it.next())]
            else:
                return []
        else:
            return []

    @property
    def contact(self):
        return None

    @property
    def date_info(self):
        return None

    @property
    def identification_info(self):
        value = self._proxy.getIdentificationInfo()
        if value:
            it = value.iterator()
            if it.hasNext():
                return [Identification(it.next())]
            else:
                return []
        else:
            return []

    @property
    def spatial_representation_info(self):
        value = self._proxy.getSpatialRepresentationInfo()
        if value:
            it = value.iterator()
            if it.hasNext():
                return [GridSpatialRepresentation(it.next())]
            else:
                return []
        else:
            return []

    def __str__(self):
        return self._proxy.toString()
