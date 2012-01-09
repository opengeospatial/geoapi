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

/**
 * GeoAPI implementation as wrappers around the
 * <a href="http://www.unidata.ucar.edu/software/netcdf-java/">NetCDF</a> library.
 * The NetCDF library provides its own Coordinate Reference System implementation
 * ({@link ucar.nc2.dataset.CoordinateSystem}) and coordinate operations
 * ({@link ucar.unidata.geoloc.Projection}). This package provides adapters allowing
 * usage of the NetCDF library as an implementation of GeoAPI interfaces.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.wrapper.netcdf;
