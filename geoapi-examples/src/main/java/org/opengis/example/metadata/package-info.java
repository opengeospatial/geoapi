/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */

/**
 * Implementation of some interfaces from the {@link org.opengis.metadata} package. This package
 * demonstrates that it is possible to implement all interfaces in the {@code org.opengis.metadata}
 * package and sub-packages with minimal effort using {@link java.lang.reflect.Proxy}.
 * This implementation stores the metadata values in {@link java.util.Map} objects, but the same
 * strategy can also be applied on a wide variety of storage mechanisms like JDBC connection or a
 * LDAP protocol.
 *
 * <p>In addition to the proxy classes, this package provides an explicit implementation of the
 * {@link org.opengis.metadata.citation.Citation} interface because it is widely used by the
 * referencing packages.</p>
 *
 * <p>Every classes in this package are hereby placed into the Public Domain.
 * This means anyone is free to do whatever they wish with those files.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.example.metadata;
