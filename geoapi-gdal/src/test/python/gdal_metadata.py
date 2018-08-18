#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    http://www.geoapi.org
#
#    This file is hereby placed into the Public Domain.
#    This means anyone is free to do whatever they wish with this file.
#

#
# Demonstrate the use of GeoAPI wrappers for GDAL.
# See ../main/python/ogc/wrapper/gdal.py for setup instruction.
#

from ogc.wrapper.gdal import DataSet

path = input("Path to a GeoTIFF file:")
ds = DataSet(path)
md = ds.metadata()

print("Title:")
print(md.identificationInfo[0].citation.title)

print("Two first axes:")
print(md.spatialRepresentationInfo[0].axisDimensionProperties[0])
print(md.spatialRepresentationInfo[0].axisDimensionProperties[1])

print("Complete metadata:")
print(md)
