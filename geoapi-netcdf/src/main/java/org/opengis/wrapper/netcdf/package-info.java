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
 * <a href="http://www.unidata.ucar.edu/software/thredds/current/netcdf-java/">NetCDF</a> library.
 * This package provides adapters allowing usage of the following NetCDF services
 * as an implementation of GeoAPI interfaces:
 *
 * <ul>
 *   <li>Metadata services, as wrappers around {@link ucar.nc2.NetcdfFile}.</li>
 *   <li>Referencing services, including:
 *     <ul>
 *       <li>Coordinate Reference Systems as wrappers around the NetCDF {@link ucar.nc2.dataset.CoordinateSystem} object.</li>
 *       <li>Coordinate Operations as wrappers around the NetCDF {@link ucar.unidata.geoloc.Projection} object.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * Home page: <a href="http://www.geoapi.org/geoapi-netcdf/index.html">GeoAPI-NetCDF bindings</a>.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.wrapper.netcdf;
