/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 *
 *    A copy of this file is given to the Proj.4 project under their own Open Source license.
 *    Because the "org.proj4" namespace is the property of the Proj.4 project, change to this
 *    file shall be made in collaboration with the Proj.4 project.
 */

/**
 * Wrappers for the <a href="http://proj.osgeo.org/">{@literal Proj.4}</a> library. A copy of this package exists also
 * in the <a href="http://svn.osgeo.org/metacrs/proj/trunk/proj/jniwrap/">jniwrap</a> directory of the Proj.4 project.
 * This GeoAPI package differs from the Proj.4 package in the following (note that this is a compatible difference):
 *
 * <ul>
 *   <li>{@link org.proj4.PJException} extends {@link org.opengis.referencing.operation.TransformException}
 *       instead than {@link java.lang.Exception}.</li>
 * </ul>
 *
 * This package is for internal use by the {@link org.opengis.wrapper.proj4} package. It should
 * generally not be used directly, unless an implementer wants more direct access to the Proj.4
 * functions.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.proj4;
