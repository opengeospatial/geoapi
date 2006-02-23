/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

// J2SE dependencies
import java.io.IOException;
import java.util.Iterator;


/**
 * Extends the {@link Iterator} interface to include a {@link #close close} method
 * for cleaning up connections to a persistent store.
 *
 * @since GeoAPI 2.0
 */
public interface FeatureIterator extends Iterator<Feature> {
    /**
     * Method inherited from Iterator that indicates whether there's another
     * feature available.
     * <p>
     * Because no exceptions are thrown in the Iterator interface, any I/O
     * exceptions that occur while executing this method must be wrapped in
     * a {@link BackingStoreException} before being thrown.
     *
     * @throws BackingStoreException If an error occurs while fetching the feature.
     */
    boolean hasNext() throws BackingStoreException;

    /**
     * Method inherited from Iterator that returns the next {@link Feature} object from
     * the iterator.
     * <p>
     * Because no exceptions are thrown in the Iterator interface, any I/O
     * exceptions that occur while executing this method must be wrapped in
     * a {@link BackingStoreException} before being thrown.
     *
     * @throws BackingStoreException If an error occurs while fetching the feature.
     */
/// /*{Feature}*/ Object next() throws BackingStoreException;

    /**
     * If supported by the underlying {@link FeatureCollection}, invoking this method
     * will remove the last Feature returned by {@link #next}.
     *
     * @throws UnsupportedOperationException If removing items is not supported
     *   by the underlying collection.
     * @throws BackingStoreException If an error occurs while removing the feature.
     */
    void remove() throws UnsupportedOperationException, BackingStoreException;

    /**
     * If applicable, closes any connection to a persistent store that backs
     * this iterator.
     *
     * @throws IOException if an error occurs while closing the iterator.
     */
    void close() throws IOException;
}
