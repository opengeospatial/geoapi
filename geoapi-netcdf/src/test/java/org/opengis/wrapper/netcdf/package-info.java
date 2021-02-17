/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */

/**
 * Tests the <a href="http://www.geoapi.org/java/examples/netcdf/index.html">GeoAPI-netCDF bindings</a>.
 * Other projects can leverage the test cases defined in this package by sub-classing
 * and overriding the {@code wrap(…)} method. For example another netCDF wrapper
 * implementation could test their metadata wrapper as below:
 *
 * <blockquote><pre>public class MyTest extends NetcdfMetadataTest {
 *    &#64;Override
 *    protected Metadata wrap(NetcdfFile file) throws IOException {
 *        return new MyWrapper(file);
 *    }
 *}</pre></blockquote>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.wrapper.netcdf;
