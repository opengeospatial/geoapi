/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */

/**
 * GeoAPI bridge between Java and Python.
 * This bridge allows the use of a Python implementation from Java, and conversely.
 * This bridge uses the <a href="http://jpy.readthedocs.io/">JPY</a> project.
 *
 * <section class="note">
 * <h1>Installation</h1>
 * As of JPY 0.9, the native library must be compiled locally by the user.
 * The installation process is <a href="http://jpy.readthedocs.io/en/latest/install.html">documented on the JPY web site</a>.
 * The following list is a reminder of the main steps (see JPY documentation for details):
 *
 * <ol>
 *   <li>Get the sources <a href="https://github.com/bcdev/jpy">from GitHub</a>.</li>
 *   <li>Set {@code JDK_HOME} and {@code JAVA_HOME} environment variables.</li>
 *   <li>At the root of JPY project, run {@code python setup.py --maven build}.
 *     <ul><li>Compilation result will be in {@code build}<i>/platform/</i> sub-directory.</li></li></ul>
 *   <li>If the above is successful, run {@code python setup.py install --user}.
 *     <ul><li>Compilation result will be in <i>platform-dependent/</i>{@code python/site-packages/} sub-directory.</li></li></ul>
 *   <li>Note the absolute path to {@code jpyconfig.properties} file in any of above-cited directories.</li>
 * </ol>
 *
 * <h1>Execution</h1>
 * <ol>
 *   <li>Apply one of the followings:<ul>
 *     <li>Set the {@code "jpy.config"} Java property to the path of above-cited {@code jpyconfig.properties} file:<ul>
 *       <li>At Java launch time with {@code java -Djpy.config=/path/to/jpyconfig.properties}</li>
 *       <li>In a Java program with {@code System.setProperty("jpy.config", "/path/to/jpyconfig.properties");}</li>
 *     </ul></li>
 *     <li>Or copy the {@code jpyconfig.properties} file to current directory.</li>
 *   </ul></li>
 * </ol>
 * </section>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 *
 * @see <a href="http://jpy.readthedocs.io/en/latest/_static/java-apidocs/index.html">JPY Javadoc</a>
 */
package org.opengis.bridge.python;
