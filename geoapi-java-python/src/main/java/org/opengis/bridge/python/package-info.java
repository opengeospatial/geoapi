/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/**
 * GeoAPI bridge between Java and Python.
 * This bridge allows the use of a Python implementation from Java, and conversely.
 * This bridge uses the <a href="http://jpy.readthedocs.io/">JPY</a> project.
 *
 * <h2>Installation</h2>
 * As of JPY 0.9, the native library must be compiled locally by the user.
 * The installation process is <a href="http://jpy.readthedocs.io/en/latest/install.html">documented on the JPY web site</a>.
 * The following list is a reminder of the main steps (see JPY documentation for details):
 *
 * <ol>
 *   <li>Get the sources <a href="https://github.com/bcdev/jpy">from GitHub</a>.</li>
 *   <li>Set {@code JDK_HOME} and {@code JAVA_HOME} environment variables.</li>
 *   <li>At the root of JPY project, run {@code python setup.py --maven build}.<ul>
 *     <li>Compilation result will be in {@code build}<i>/platform/</i> sub-directory.</li>
 *   </ul></li>
 *   <li>If the above is successful, run {@code python setup.py install --user}.<ul>
 *     <li>Compilation result will be in <i>platform-dependent/</i>{@code python/site-packages/} sub-directory.</li>
 *   </ul></li>
 *   <li>Note the absolute path to {@code jpyconfig.properties} file in any of above-cited directories.</li>
 * </ol>
 *
 * <h2>Execution</h2>
 * <ol>
 *   <li>Apply one of the followings:<ul>
 *     <li>Set the {@code "jpy.config"} Java property to the path of above-cited {@code jpyconfig.properties} file:<ul>
 *       <li>At Java launch time with {@code java -Djpy.config=/path/to/jpyconfig.properties}</li>
 *       <li>In a Java program with {@code System.setProperty("jpy.config", "/path/to/jpyconfig.properties");}</li>
 *     </ul></li>
 *     <li>Or copy the {@code jpyconfig.properties} file to current directory.</li>
 *   </ul></li>
 * </ol>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 *
 * @see <a href="http://jpy.readthedocs.io/en/latest/_static/java-apidocs/index.html">JPY Javadoc</a>
 */
package org.opengis.bridge.python;
