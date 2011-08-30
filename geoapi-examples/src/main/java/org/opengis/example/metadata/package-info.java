/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */

/**
 * Implements all metadata interfaces through a simple {@link java.lang.reflect.Proxy}. This package
 * demonstrates that it is possible to implement all interfaces in the {@link org.opengis.metadata}
 * package and sub-packages with minimal effort. This implementation stores the metadata values in
 * {@link java.util.Map} objects, but the same strategy can also be applied on a wide variety of
 * storage mechanisms like JDBC connection or a LDAP protocol.
 * <p>
 * In addition to the proxy classes, this package provides an explicit implementation of the
 * {@link org.opengis.metadata.citation.Citation} interface because it is widely used by the
 * referencing packages.
 * <p>
 * Every classes in this package are hereby placed into the Public Domain.
 * This means anyone is free to do whatever they wish with those files.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.example.metadata;
