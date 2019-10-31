import jpy

import opengis.referencing.cs
import opengis.referencing.crs
import opengis.referencing.operation


class CoordinateReferenceSystem(opengis.referencing.crs.ReferenceSystem):
    def __init__(self, proxy):
        self._proxy = proxy

    def to_wkt(self):
        return self._proxy.toWKT()

    def name(self):
        return self._proxy.getName()


class CoordinateSystemAxis(opengis.referencing.datum.IdentifiedObject):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def abbreviation(self):
        return self._proxy.getAbbreviation()

    @property
    def direction(self):
        return None

    @property
    def unit(self):
        return None

    def __str__(self):
        return self._proxy.toString()


class CartesianCS(opengis.referencing.cs.CoordinateSystem):
    def __init__(self, proxy):
        self._proxy = proxy

    def dimension(self):
        return self._proxy.getDimension()

    def axis(self, dim: int):
        return self._proxy.getAxis(dim)

    def to_wkt(self):
        return self._proxy.toWKT()

    def name(self):
        return self._proxy.getName()

    def __str__(self):
        return self._proxy.toString()


class GeodeticDatum(opengis.referencing.datum.GeodeticDatum):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def ellipsoid(self):
        return None

    @property
    def prime_meridian(self):
        return None

    def __str__(self):
        return self._proxy.toString()


class EllipsoidalCS(opengis.referencing.cs.CoordinateSystem):
    def __init__(self, proxy):
        self._proxy = proxy

    def dimension(self):
        return self._proxy.getDimension()

    def axis(self, dim: int):
        return self._proxy.getAxis(dim)

    def to_wkt(self):
        return self._proxy.toWKT()

    def name(self):
        return self._proxy.getName()

    def __str__(self):
        return self._proxy.toString()


class GeographicCRS(opengis.referencing.crs.GeodeticCRS):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def coordinate_system(self):
        return self._proxy.getCoordinateSystem()

    def to_wkt(self):
        return self._proxy.toWKT()

    def name(self):
        return self._proxy.getName()

    def datum(self):
        return self._proxy.getDatum()

    def __str__(self):
        return self._proxy.toString()


class ProjectedCRS(opengis.referencing.crs.GeneralDerivedCRS):
    def __init__(self, proxy):
        self._proxy = proxy

    @property
    def base_crs(self):
        return self._proxy.getBaseCRS()

    @property
    def coordinate_system(self):
        return self._proxy.getCoordinateSystem()

    @property
    def datum(self):
        return self._proxy.getDatum()

    def __str__(self):
        return self._proxy.toString()
