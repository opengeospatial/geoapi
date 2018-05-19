/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.io.IOException;
import org.opengis.test.dataset.TestData;

import ucar.nc2.NetcdfFile;


/**
 * Base class of netCDF test cases performing I/O operations. This base class provides an
 * {@link #open(TestData)} method for creating {@link NetcdfFile} objects from the build-in
 * test files.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
abstract strictfp class IOTestCase {
    /**
     * For subclass constructors only.
     */
    IOTestCase() {
    }

    /**
     * Opens the given netCDF file.
     *
     * @param  file  one of the {@code NETCDF_*} enumeration constants.
     * @return the netCDF file.
     * @throws IOException if an error occurred while opening the file.
     */
    final NetcdfFile open(final TestData file) throws IOException {
        /*
         * Binary netCDF files need to be read either from a file, or from a byte array in memory.
         * Reading from a file is not possible if the test file is in geoapi-conformance JAR file.
         * But since those test files are less than 15 kilobytes, loading them in memory is okay.
         */
        String location = file.location().toString();
        location = location.substring(location.lastIndexOf('/') + 1);
        return NetcdfFile.openInMemory(location, file.content());
    }
}
