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

import java.util.Date;
import java.util.Arrays;
import java.util.Iterator;
import java.text.ParseException;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import ucar.nc2.NetcdfFile;
import ucar.unidata.util.DateUtil;
import ucar.nc2.ncml.NcMLReader;

import static org.opengis.test.Assert.*;


/**
 * Base class of NetCDF test cases performing I/O operations. This base class provides
 * an {@link #open(String)} method for creating {@link NetcdfFile} objects from the
 * build-in test files (see link below).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="{@svnurl netcdf}/">Build-in test files</a>
 */
public abstract strictfp class IOTestCase {
    /**
     * The {@value} test file (XML format). This test uses a file derived from the
     * <a href="http://geo-ide.noaa.gov/wiki/index.php?title=NetCDF_Attribute_Convention_for_Dataset_Discovery">NetCDF
     * Attribute Convention for Dataset Discovery</a> page on October 5, 2011.
     * <p>
     * The Coordinate Reference System of this dataset is geographic.
     *
     * @see <a href="{@svnurl netcdf}/thredds.ncml">File content</a>
     * @see NetcdfMetadataTest#testThredds()
     */
    public static final String THREDDS = "thredds.ncml";

    /**
     * The {@value} test file (binary format). This file was downloaded from the examples provided in the
     * <a href="http://www.unidata.ucar.edu/software/netcdf-java/formats/DataDiscoveryAttConvention.html">NetCDF
     * Attribute Convention for Dataset Discovery</a> page on October 5, 2011.
     * <p>
     * The Coordinate Reference System of this dataset is compound (geographic + time).
     * The values are <cite>Sea Surface Temperature</cite> (SST).
     *
     * @see NetcdfMetadataTest#testGeographicWithTime()
     */
    public static final String GEOTIME_NC = "2005092200_sst_21-24.en.nc";

    /**
     * For subclass constructors only.
     */
    protected IOTestCase() {
    }

    /**
     * Opens the given NetCDF file. This method process as below:
     * <p>
     * <ul>
     *   <li>First, try to {@linkplain Class#getResource(String) get the resource} for the given
     *       name in the package of the concrete sub-class. This package may contain any resources
     *       defined by the implementor.</li>
     *   <li>If the above resource was not found, try to get the resource in the
     *       "{@code org.opengis.wrapper.netcdf}" package. This package contains the files
     *       defined by the {@link #THREDDS} and {@link #GEOTIME_NC} constants.</li>
     * </ul>
     * <p>
     * For example if an implementor extends this class in his "{@code com.mycompany}" package
     * and provides a {@value #THREDDS} file in that package, then his test file will have
     * precedence over the {@code geoapi-netcdf} build-in file.
     *
     * @param  file The file name, typically one of the {@link #THREDDS} or {@link #GEOTIME_NC} constants.
     * @return The NetCDF file.
     * @throws IOException If an error occurred while opening the file.
     */
    protected NetcdfFile open(final String file) throws IOException {
        if (file.endsWith(".ncml")) {
            InputStream in = getClass().getResourceAsStream(file);
            if (in == null) {
                in = IOTestCase.class.getResourceAsStream(file);
                if (in == null) {
                    throw new FileNotFoundException(file);
                }
            }
            try {
                return NcMLReader.readNcML(in, null);
            } finally {
                in.close();
            }
        }
        if (file.endsWith(".nc")) {
            Class<? extends IOTestCase> loader = getClass();
            URL url = loader.getResource(file);
            if (url == null) {
                loader = IOTestCase.class;
                url = loader.getResource(file);
                if (url == null) {
                    throw new FileNotFoundException(file);
                }
            }
            if (url.getProtocol().equals("file")) try {
                return NetcdfFile.open(new File(url.toURI()).getPath());
            } catch (URISyntaxException e) {
                throw (IOException) new MalformedURLException(e.getLocalizedMessage()).initCause(e);
            } else {
                return NetcdfFile.openInMemory(file, load(loader.getResourceAsStream(file)));
            }
        }
        throw new IOException("Unknown file format: " + file);
    }

    /**
     * Returns the content of the given input stream in an array of bytes. This method is used
     * for opening a NetCDF file in memory when the resource can not be opened as a file.
     *
     * @param  in The input stream. This stream will be closed by this method.
     * @return The stream content as an array of bytes.
     * @throws IOException If an error occurred while reading the stream content.
     */
    private static byte[] load(final InputStream in) throws IOException {
        int length = 0;
        byte[] buffer = new byte[32 * 1024];
        int n;
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

    /**
     * Returns the single element from the given collection. If the given collection is null
     * or does not contains exactly one element, then an {@link AssertionError} is thrown.
     *
     * @param  <E> The type of collection elements.
     * @param  collection The collection from which to get the singleton.
     * @return The singleton element from the collection.
     */
    static <E> E getSingleton(final Iterable<? extends E> collection) {
        assertNotNull("Null collection.", collection);
        final Iterator<? extends E> it = collection.iterator();
        assertTrue("The collection is empty.", it.hasNext());
        final E element = it.next();
        assertFalse("The collection has more than one element.", it.hasNext());
        return element;
    }

    /**
     * Parses the given date using the NetCDF {@link DateUtil} utilities class.
     * If a checked {@link ParseException} occurs, it will be wrapped in an
     * unchecked {@link IllegalArgumentException}.
     *
     * @param  text The date to parse.
     * @return The parsed date.
     * @throws IllegalArgumentException If the given string can not be parsed.
     */
    static Date parseDate(final String text) throws IllegalArgumentException {
        try {
            return DateUtil.parse(text);
        } catch (ParseException e) {
            throw new IllegalArgumentException(text, e);
        }
    }
}
