/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */

/**
 * GeoAPI implementation as wrapper around the C/C++ <a href="http://www.gdal.org/">GDAL</a> library.
 * Available GDAL drivers are <a href="http://www.gdal.org/formats_list.html">listed here</a>.
 * Some examples are:
 *
 * <table class="ogc">
 * <caption>Small subset of GDAL drivers</caption>
 *   <tr><th>Name</th>   <th>Description</th></tr>
 *   <tr><td>netCDF</td> <td>Network Common Data Form</td></tr>
 *   <tr><td>GTiff</td>  <td>GeoTIFF File Format</td></tr>
 *   <tr><td>WCS</td>    <td>OGC Web Coverage Service</td></tr>
 * </table>
 *
 * <section class="note">
 * <h1>Installation</h1>
 * GDAL needs to be built with the {@code --with-java} option.
 * If the package manager for a given platform does not support this option,
 * then GDAL may have to be <a href="https://trac.osgeo.org/gdal/wiki/BuildHints">built from the sources</a>:
 *
 * <ul>
 *   <li>Make sure that the {@code JAVA_HOME} environment variable is defined</li>
 *   <li>Run on the command-line:<ul>
 *     <li>{@code cd gdal}</li>
 *     <li>{@code ./configure --with-java=yes}</li>
 *     <li>{@code make}</li>
 *   </ul></li>
 *   <li>Optionally run the command-line as a super-user: {@code make install}</li>
 *   <li>Verify with {@code gdalinfo --version}</li>
 * </ul>
 *
 * See {@code configure --help} for a list of options.
 * For example MacOS users may need to add the {@code --with-macosx-framework} option at configuration time.
 * </section>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.wrapper.gdal;
