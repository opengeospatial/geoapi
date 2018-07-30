from ogc.wrapper.gdal import DataSet

path = input("Geotiff file path :")
ds = DataSet(path)
md = ds.metadata()
print(md)
print(md.identificationInfo[0].citation.title)
print(md.metadataScope[0].resourceScope)
print(md.spatialRepresentationInfo[0].axisDimensionProperties[0])
print(md.spatialRepresentationInfo[0].axisDimensionProperties[1])
print(md.spatialRepresentationInfo[0].cellGeometry)