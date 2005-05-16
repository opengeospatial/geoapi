/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

// J2SE dependencies
import java.io.IOException;
import java.util.Iterator;


/**
 * Extends the java {@link Iterator} interface to include a {@link #close} method
 * for cleaning up connections to a persistent store.
 *
 * @since GeoAPI 1.1
 */
public interface FeatureIterator extends Iterator<Feature> {
    /**
     * Method inherited from Iterator that indicates whether there's another
     * feature available.
     * <p>
     * Because no exceptions are thrown in the Iterator interface, any I/O
     * exceptions that occur while executing this method must be wrapped in
     * a {@link RuntimeException} before being thrown.
     */
    boolean hasNext();

    /**
     * Method inherited from Iterator that returns the next {@link Feature} object from
     * the iterator.
     * <p>
     * Because no exceptions are thrown in the Iterator interface, any I/O
     * exceptions that occur while executing this method must be wrapped in
     * a {@link RuntimeException} before being thrown.
     */
/// /*{Feature}*/ Object next();

    /**
     * If supported by the underlying {@link FeatureCollection}, invoking this method
     * will remove the last Feature returned by {@link #next}.
     *
     * @throws UnsupportedOperationException If removing items is not supported
     *   by the underlying collection.
     */
    void remove() throws UnsupportedOperationException;

    /**
     * If applicable, closes any connection to a persistent store that backs
     * this iterator.
     *
     * @throws IOException if an error occurs while closing the iterator.
     */
    void close() throws IOException;
}
