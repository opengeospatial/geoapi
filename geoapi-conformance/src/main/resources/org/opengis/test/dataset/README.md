# Directory content

## Cube2D_geographic_packed.nc
A two-dimensional netCDF file containing temperature data packed as 16 bits signed integers (only the positive range is used).
The Coordinate Reference System is a geographic with latitudes ranging from 90°S to 90°N and longitudes from 180°E to 180°W.
Based on the freely available `2005092200_sst_21-24.en.nc` test file downloaded from
[Unidate](http://www.unidata.ucar.edu/software/netcdf-java/formats/DataDiscoveryAttConvention.html) on October 5, 2011.
Decimated and transformed for GeoAPI conformance test purpose.


## Cube4D_projected_float.nc
A four-dimensional netCDF file containing "Current Icing Product" data, decimated and cropped.
The Coordinate Reference System is projected, with an elevation above geoid and a time axis.
The geographic area is East of North America.
Based on a file was kindly provided by Aaron Braeckel from UCAR.
Cropped and transformed for GeoAPI conformance test purpose.


## MovingFeatures.nc
A small extract of `JaPOPPO.csv` example file from Moving Features standard working group.
Encoded as specified in [OGC 16-114](http://docs.opengeospatial.org/bp/16-114r3/16-114r3.html).
