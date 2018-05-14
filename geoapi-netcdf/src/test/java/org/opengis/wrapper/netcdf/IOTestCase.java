/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Arrays;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import ucar.nc2.NetcdfFile;
import ucar.nc2.ncml.NcMLReader;

import static org.opengis.test.Assert.*;


/**
 * Base class of NetCDF test cases performing I/O operations. This base class provides an
 * {@link #open(String)} method for creating {@link NetcdfFile} objects from the build-in
 * test files.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public abstract strictfp class IOTestCase {
    /**
     * The {@value} test file (NetCDF classic binary format). This file was downloaded from the examples provided in the
     * <a href="http://www.unidata.ucar.edu/software/thredds/current/netcdf-java/metadata/DataDiscoveryAttConvention.html">NetCDF
     * Attribute Convention for Dataset Discovery</a> page on October 5, 2011. The global attributes
     * are listed below. Note that this particular NetCDF file specifies the geographic bounding box
     * ordinates as string values rather than numerical values. Consequently the implementations to
     * be tested need to perform conversions.
     *
     * <blockquote><pre>:record = "reftime, valtime" ;
     *:history = "2003-04-07 12:12:50 - created by gribtocdl 2005-09-26T21:50:00 - edavis - add attributes for dataset discovery" ;
     *:title = "Sea Surface Temperature Analysis Model" ;
     *:Conventions = "NUWG, _Coordinates" ;
     *:GRIB_reference = "Office Note 388 GRIB" ;
     *:GRIB_URL = "http://www.nco.ncep.noaa.gov/pmb/docs/on388/" ;
     *:version = 1. ;
     *:Metadata_Conventions = "Unidata Dataset Discovery v1.0" ;
     *:summary = "NCEP SST Global 5.0 x 2.5 degree model data" ;
     *:keywords = "EARTH SCIENCE &gt; Oceans &gt; Ocean Temperature &gt; Sea Surface Temperature" ;
     *:keywords_vocabulary = "GCMD Science Keywords" ;
     *:id = "NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc" ;
     *:naming_authority = "edu.ucar.unidata" ;
     *:cdm_data_type = "Grid" ;
     *:date_created = "2005-09-22T00:00" ;
     *:creator_name = "NOAA/NWS/NCEP" ;
     *:creator_url = "" ;
     *:creator_email = "" ;
     *:geospatial_lat_min = "-90.0" ;
     *:geospatial_lat_max = "90.0" ;
     *:geospatial_lon_min = "-180.0" ;
     *:geospatial_lon_max = "180.0" ;
     *:geospatial_vertical_min = "0.0" ;
     *:geospatial_vertical_max = "0.0" ;
     *:time_coverage_start = "2005-09-22T00:00" ;
     *:time_coverage_duration = "0.0" ;
     *:license = "Freely available" ;</pre></blockquote>
     *
     * The Coordinate Reference System of this dataset is
     * {@linkplain org.opengis.referencing.crs.CompoundCRS compound}
     * ({@linkplain org.opengis.referencing.crs.GeographicCRS geographic} +
     * ({@linkplain org.opengis.referencing.crs.TemporalCRS temporal}).
     * The values are <cite>Sea Surface Temperature</cite> (SST).
     *
     * @see NetcdfMetadataTest#testNCEP()
     * @see NetcdfCRSTest#testGeographic_XYT()
     */
    public static final String NCEP = "NCEP-SST.nc";

    /**
     * The {@value} test file (HDF5 binary format). Some attributes are listed below:
     *
     * <blockquote><pre>variables:
     *    int grid_mapping_0 ;
     *        grid_mapping_0:grid_mapping_name = "lambert_conformal_conic" ;
     *        grid_mapping_0:longitude_of_central_meridian = -95.f ;
     *        grid_mapping_0:latitude_of_projection_origin = 25.f ;
     *        grid_mapping_0:standard_parallel = 25.f, 25.05f ;
     *    float CIP(time, z0, y0, x0) ;
     *        CIP:long_name = "Current Icing Product " ;
     *        CIP:valid_min = 0.f ;
     *        CIP:valid_max = 0.44f ;
     *        CIP:units = "%" ;
     *        CIP:grid_mapping = "grid_mapping_0" ;</pre></blockquote>
     *
     * The Coordinate Reference System of this dataset is
     * {@linkplain org.opengis.referencing.crs.CompoundCRS compound}
     * ({@linkplain org.opengis.referencing.crs.ProjectedCRS geographic} +
     * ({@linkplain org.opengis.referencing.crs.TemporalCRS temporal}).
     *
     * @see NetcdfMetadataTest#testCIP()
     * @see NetcdfCRSTest#testProjected_XYZT()
     */
    public static final String CIP = "CIP.nc";

    /**
     * For subclass constructors only.
     */
    protected IOTestCase() {
    }

    /**
     * Returns the length of the given file, or an arbitrary default value if unknown.
     * This method is used for creating an initial buffer of the right size when reading
     * a NetCDF file in memory.
     *
     * @param  file  the file name, typically one of the {@link #CIP} or {@link #NCEP} constants.
     * @param  defaultLength  the default value to return if the given file is not recognized.
     */
    static int getFileLength(final String file, final int defaultLength) {
        switch (file) {
            case NCEP:    return 27204;
            case CIP:     return 76184;
        }
        return defaultLength;
    }

    /**
     * Opens the given NetCDF file. This method process as below:
     *
     * <ul>
     *   <li>First, try to {@linkplain Class#getResource(String) get the resource} for the given
     *       name in the package of the concrete sub-class. This package may contain any resources
     *       defined by the implementor.</li>
     *   <li>If the above resource was not found, try to get the resource in the
     *       "{@code org.opengis.wrapper.netcdf}" package. This package contains the files
     *       defined by the {@link #CIP}, {@link #NCEP} and other constants.</li>
     *   <li>If the above resource was not found and the given file is {@linkplain File#isAbsolute()
     *       absolute}, try to open that file directly. This case should be used only for temporary
     *       testing purpose.</li>
     * </ul>
     *
     * For example if an implementor extends this class in his "{@code com.mycompany}" package
     * and provides a {@value #CIP} file in that package, then his test file will have
     * precedence over the {@code geoapi-netcdf} build-in test file.
     *
     * @param  file  the file name, typically one of the {@link #CIP} or {@link #NCEP} constants.
     * @return the NetCDF file.
     * @throws IOException if an error occurred while opening the file.
     */
    protected NetcdfFile open(final String file) throws IOException {
        /*
         * XML NcML files can be read straight from an InputStream.
         */
        if (file.endsWith(".ncml")) {
            InputStream in = getClass().getResourceAsStream(file);
            if (in == null) {
                in = IOTestCase.class.getResourceAsStream(file);
                if (in == null) {
                    final File f = new File(file);
                    if (f.isAbsolute() && f.isFile()) {
                        in = new FileInputStream(f);
                    } else {
                        throw new FileNotFoundException(file);
                    }
                }
            }
            try {
                return NcMLReader.readNcML(in, null);
            } finally {
                in.close();
            }
        }
        /*
         * Binary NetCDF files need to be read either from a file, or from a byte array in memory.
         * We give precedence to the file, but this is possible only if the test file is not read
         * from a JAR file.
         */
        if (file.endsWith(".nc")) {
            Class<? extends IOTestCase> loader = getClass();
            URL url = loader.getResource(file);
            if (url == null) {
                loader = IOTestCase.class;
                url = loader.getResource(file);
                if (url == null) {
                    final File f = new File(file);
                    if (f.isAbsolute() && f.isFile()) {
                        return NetcdfFile.open(f.getPath());
                    } else {
                        throw new FileNotFoundException(file);
                    }
                }
            }
            if (url.getProtocol().equals("file")) {
                final File f;
                try {
                    f = new File(url.toURI());
                } catch (URISyntaxException e) {
                    throw (IOException) new MalformedURLException(e.getLocalizedMessage()).initCause(e);
                }
                // If the test file is one of the predefined files, ensures that our length
                // declaration is up to date. This is only an optimisation for the case when
                // this file will be read from a byte buffer.
                final int length = getFileLength(file, -1);
                if (length >= 0) {
                    assertEquals(file, length, f.length());
                }
                return NetcdfFile.open(f.getPath());
            } else {
                return NetcdfFile.openInMemory(file, load(file, loader.getResourceAsStream(file)));
            }
        }
        throw new IOException("Unknown file format: " + file);
    }

    /**
     * Returns the content of the given input stream in an array of bytes. This method is used
     * for opening a NetCDF file in memory when the resource can not be opened as a file.
     *
     * @param  file  the file name, typically one of the {@link #CIP} or {@link #NCEP} constants.
     * @param  in    the input stream. This stream will be closed by this method.
     * @return the stream content as an array of bytes.
     * @throws IOException if an error occurred while reading the stream content.
     */
    private static byte[] load(final String file, final InputStream in) throws IOException {
        int n, length = 0;
        byte[] buffer = new byte[getFileLength(file, 32 * 1024)];               // Default to 32 kb.
        while ((n = in.read(buffer, length, buffer.length - length)) >= 0) {
            if ((length += n) == buffer.length) {
                buffer = Arrays.copyOf(buffer, length*2);
            }
        }
        in.close();
        if (length != buffer.length) {
            buffer = Arrays.copyOf(buffer, length);
        }
        return buffer;
    }
}
