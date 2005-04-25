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

import java.io.IOException;
import java.util.Iterator;

/**
 * Extends the java Iterator interface to include a <code>close</code> method
 * for cleaning up connections to a persistent store.
 */
public interface FeatureIterator extends Iterator<Feature> {
    /**
     * Method inherited from Iterator that indicates whether there's another
     * feature available.
     * <p>
     * Because no exceptions are thrown in the Iterator interface, any I/O
     * exceptions that occur while executing this method must be wrapped in
     * a RuntimeException before being thrown.
     */
    public boolean hasNext();

    /**
     * Method inherited from Iterator that returns the next Feature object from
     * the iterator.
     * <p>
     * Because no exceptions are thrown in the Iterator interface, any I/O
     * exceptions that occur while executing this method must be wrapped in
     * a RuntimeException before being thrown.
     */
/// public /*{Feature}*/ Object next();

    /**
     * If supported by the underlying FeatureCollection, invoking this method
     * will remove the last Feature returned by next().
     *
     * @throws UnsupportedOperationException If removing items is not supported
     *   by the underlying collection.
     */
    public void remove() throws UnsupportedOperationException;

    /**
     * If applicable, closes any connection to a persistent store that backs
     * this iterator.
     */
    public void close() throws IOException;
}
