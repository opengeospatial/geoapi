#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
#    http://www.geoapi.org
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
    @abstractmethod
    def resource_scope(self):
        return self._proxy.getResourceScope()



class Citation(opengis.metadata.citation.Citation):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    @abstractmethod
    def title(self):
        return self._proxy.getTitle()

    def __str__(self):
        return self._proxy.toString()



class Identifier(opengis.metadata.citation.Identifier):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    @abstractmethod
    def code(self):
        return self._proxy.getCode()

    @property
    @abstractmethod
    def code_space(self):
        return self._proxy.getCodeSpace()

    @property
    @abstractmethod
    def version(self):
        return self._proxy.getVersion()

    @property
    @abstractmethod
    def description(self):
        return self._proxy.getDescription()

    def __str__(self):
        return self._proxy.toString()



class Identification(opengis.metadata.identification.Identification):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    @abstractmethod
    def citation(self):
        value = self._proxy.getCitation()
        if value:
            return Citation(value)
        else:

    @property
    @abstractmethod
    def abstract(self):
        return self._proxy.getAbstract()

    def __str__(self):
        return self._proxy.toString()



class Dimension(opengis.metadata.representation.Dimension):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    @abstractmethod
    def dimension_name(self):
        return self._proxy.getDimensionName()

    @property
    @abstractmethod
    def dimension_size(self) -> int:
        return self._proxy.getDimensionSize()



class GridSpatialRepresentation(opengis.metadata.representation.SpatialRepresentation):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    @abstractmethod
    def number_of_dimensions(self):
        return self._proxy.getNumberOfDimensions()

    @property
    @abstractmethod
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
    @abstractmethod
    def cell_geometry(self):
        return self._proxy.getCellGeometry()

    @property
    @abstractmethod
    def transformation_parameter_availability(self):
        return self._proxy.getTransformationParameterAvailability()



class Metadata(opengis.metadata.base.Metadata):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    @abstractmethod
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
    @abstractmethod
    def contact(self):

    @property
    @abstractmethod
    def date_info(self):

    @property
    @abstractmethod
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
    @abstractmethod
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
