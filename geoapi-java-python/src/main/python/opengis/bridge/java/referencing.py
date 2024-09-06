#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
#    http://www.geoapi.org
#

#
# This module requires jpy module to be installed on the local machine.
# That Java-Python module is documented at http://jpy.readthedocs.io/.
# In addition, the PYTHONPATH environmental variable must be set to the
# "geoapi/src/main/python" directory, preferably using absolute path.
#

import jpy
import opengis.referencing.cs
import opengis.referencing.crs
import opengis.referencing.datum
import opengis.referencing.operation
from opengis.bridge.java.metadata import Identifier



class IdentifiedObject(opengis.referencing.datum.IdentifiedObject):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    @abstractmethod
    def name(self):
        return Identifier(self._proxy.getName())

    def to_wkt(self):
        return self._proxy.toWKT()

    def __str__(self):
        return self._proxy.toString()



class GeodeticDatum(IdentifiedObject, opengis.referencing.datum.GeodeticDatum):
    def __init__(self, proxy):
        super().__init__(proxy)

    @property
    @abstractmethod
    def ellipsoid(self):

    @property
    @abstractmethod
    def prime_meridian(self):



class CoordinateSystemAxis(IdentifiedObject, opengis.referencing.datum.IdentifiedObject):
    def __init__(self, proxy):
        super().__init__(proxy)

    @property
    @abstractmethod
    def abbreviation(self):
        return self._proxy.getAbbreviation()

    @property
    @abstractmethod
    def direction(self):

    @property
    @abstractmethod
    def unit(self):



class CoordinateSystem(IdentifiedObject, opengis.referencing.cs.CoordinateSystem):
    def __init__(self, proxy):
        super().__init__(proxy)

    def dimension(self):
        return self._proxy.getDimension()

    def axis(self, dim: int):
        return self._proxy.getAxis(dim)



class CartesianCS(CoordinateSystem, opengis.referencing.cs.CoordinateSystem):
    def __init__(self, proxy):
        super().__init__(proxy)



class EllipsoidalCS(CoordinateSystem, opengis.referencing.cs.CoordinateSystem):
    def __init__(self, proxy):
        super().__init__(proxy)



class CoordinateReferenceSystem(IdentifiedObject, opengis.referencing.crs.ReferenceSystem):
    def __init__(self, proxy):
        super().__init__(proxy)

    @property
    @abstractmethod
    def coordinate_system(self):
        return self._proxy.getCoordinateSystem()

    def datum(self):
        return self._proxy.getDatum()



class GeographicCRS(CoordinateReferenceSystem, opengis.referencing.crs.GeodeticCRS):
    def __init__(self, proxy):
        super().__init__(proxy)



class ProjectedCRS(IdentifiedObject, opengis.referencing.crs.DerivedCRS):
    def __init__(self, proxy):
        super().__init__(proxy)

    @property
    @abstractmethod
    def base_crs(self):
        return self._proxy.getBaseCRS()
