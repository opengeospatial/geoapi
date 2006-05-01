/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

// J2SE direct dependencies
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

// OpenGIS direct dependencies
import org.opengis.filter.Filter;
//import org.opengis.filter.sort.SortBy;

// Annotations
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;


/**
 * Represents a collection of {@linkplain Feature features}. Implementations and client code
 * should adhere to the rules set forth by {@link java.util.Collection}. That is, some methods
 * are optional to implement, and may throw an {@link UnsupportedOperationException}.
 *
 * @author Ian Turton (CCG)
 * @author Rob Hranac (VFNY)
 * @author Ian Schneider (USDA-ARS)
 * @since GeoAPI 2.0
 *
 * @see java.util.Collection
 */
@XmlElement("FeatureCollection")
public interface FeatureCollection extends Collection<Feature>, Feature {
    /**
     * Returns an iterator that enumerates all of the features in this
     * collection.  The object returned from this method is always of type
     * {@link FeatureIterator}.
     *
     * @throws BackingStoreException If an error occurs while fetching the features.
     */
    /*{FeatureIterator}*/ Iterator<Feature> iterator() throws BackingStoreException;

    /**
     * Creates a {@link Feature} array and populates it.
     * 
     * @throws BackingStoreException If an error occurs while fetching the features.
     * @throws OutOfMemoryError if the feature collection is too large to fit into memory.
     */
    Object[] toArray() throws BackingStoreException, OutOfMemoryError;

    /**
     * Populates the given array with the features in this collection.  If the
     * passed array is null or is not sufficiently large to contain all of the
     * features in this collection, a new Feature array will be created and
     * returned.
     * 
     * @throws BackingStoreException If an error occurs while fetching the features.
     * @throws OutOfMemoryError if the feature collection is too large to fit into memory.
     */
    /*{Feature}*/ Object[] toArray(/*{Feature}*/ Object[] buffer)
            throws BackingStoreException, OutOfMemoryError;

    /**
     * Returns {@code true} if this collection contains no {@link Feature}s.
     *
     * @throws BackingStoreException If an error occurs while checking for features.
     */
    boolean isEmpty() throws BackingStoreException;

    /**
     * Returns the size of the collection, if known.  It may return -1 if the
     * size is not known or would be too costly to compute (such as when the
     * features are streaming from a remote source).
     *
     * @throws BackingStoreException If an error occurs while checking for features.
     */
    @XmlElement("numberOfFeatures")
    int size() throws BackingStoreException;

    /**
     * Checks if the given feature is a member of this collection.
     *
     * @throws BackingStoreException If an error occurs while searching the feature.
     */
    boolean contains(/*{Feature}*/ Object o) throws BackingStoreException;

    /**
     * Checks if every feature in the given collection is also a member of this
     * feature collection.
     *
     * @throws BackingStoreException If an error occurs while searching the features.
     */
    boolean containsAll(Collection<Feature> c) throws BackingStoreException;

    /**
     * Adds the given feature to this collection.  If this collection is backed
     * by a persistent store of some kind, then the given feature should
     * immediately be written to that persistent store.  If you want to add
     * multiple features to this collection, note that it may be considerably
     * more efficient to call the {@link #addAll} method.  The addition of
     * features takes place within the context of the current
     * {@linkplain Transaction transaction} on this feature collection.
     *
     * @throws UnsupportedOperationException if the addition of new features is not supported.
     * @throws BackingStoreException If an error occurs while adding the feature.
     */
    boolean add(/*{Feature}*/ Object o) throws UnsupportedOperationException, BackingStoreException;

    /**
     * Adds all of the features contained in the given collection to this
     * collection.  If this collection is backed by a persistent store of some
     * kind, then the added features should immediately be written to that
     * persistent store.  The addition of features takes place within
     * the context of the current {@linkplain Transaction transaction}
     * on this feature collection.
     *
     * @throws UnsupportedOperationException if the addition of new features is not supported.
     * @throws BackingStoreException If an error occurs while adding the features.
     */
    boolean addAll(Collection<Feature> c) throws UnsupportedOperationException, BackingStoreException;

    /**
     * Removes all of the features contained in this collection.  If this
     * collection is backed by a persistent store of some kind, then this method
     * should cause all of the features from this collection to be removed
     * from that persistent store.  This operation takes place within the
     * context of the current {@linkplain Transaction transaction} on this
     * feature collection.
     *
     * @throws UnsupportedOperationException if the removal of features is not supported.
     * @throws BackingStoreException If an error occurs while removing the features.
     */
    void clear() throws UnsupportedOperationException, BackingStoreException;

    /**
     * Removes the given feature from this collection.  If this collection is
     * backed by a persistent store of some kind, then this method should cause
     * the given feature to be removed from the persistent store.  This
     * operation takes place within the context of the current
     * {@linkplain Transaction transaction} on this feature collection.
     *
     * @throws UnsupportedOperationException if the removal of features is not supported.
     * @throws BackingStoreException If an error occurs while removing the feature.
     */
    boolean remove(/*{Feature}*/ Object o) throws UnsupportedOperationException, BackingStoreException;

    /**
     * Removes the given features from this collection.  If this collection is
     * backed by a persistent store of some kind, then this method should cause
     * all of the given features to be removed from the persistent store.  This
     * operation takes place within the context of the current
     * {@linkplain Transaction transaction} on this feature collection.
     *
     * @throws UnsupportedOperationException if the removal of features is not supported.
     * @throws BackingStoreException If an error occurs while removing the features.
     */
    boolean removeAll(Collection<Feature> c) throws UnsupportedOperationException, BackingStoreException;

    /**
     * Removes any features from this collection that are not present in the
     * given collection.  If this collection is backed by a persistent store of
     * some kind, then this method should cause the removed features to be
     * removed from the persistent store as well.  This operation takes place
     * within the context of the current {@linkplain Transaction transaction}
     * on this feature collection.
     *
     * @throws UnsupportedOperationException if the removal of features is not supported.
     * @throws BackingStoreException If an error occurs while removing the features.
     */
    boolean retainAll(Collection<Feature> c) throws UnsupportedOperationException, BackingStoreException;

    /**
     * Returns a collection whose contents are the subset of features in this
     * collection that pass the given filter.  This is semantically equivalent
     * to going back to the {@link FeatureStore} that created this collection
     * and AND-ing the filter that produced this collection with the given filter.
     * <p>
     * Compare this method to {@link java.util.List#subList(int,int)}
     * from the Java Collections framework.
     *
     * @throws BackingStoreException If an error occurs while fetching the features.
     */
    FeatureCollection subCollection(Filter filter) throws BackingStoreException;

    /** Returns an ordered feature collection whoes concepts are sorted
     * according to the provided order.
     * <p>
     * You may cascade several sort calls together to acomplish grouping.
     * @param order
     * @return FeatureList in the indicated order
     * @throws BackingStoreException
     */
     //FeatureList sort( SortBy order ) throws BackingStoreException;
    
    /**
     * If some sort of connection was opened to a backing store to support this
     * collection, then closes this connection.  Users of this API should always
     * call this method before releasing the references to this object so that
     * resources get cleaned up in a timely fashion.  Those who implement this
     * interface are also encouraged to implement the {@link Object#finalize finalize()}
     * method just in case the user forgets to call close so that perhaps things
     * might get cleaned up.
     * <p>
     * After invoking this method, all references to the feature listener
     * objects will be cleared and no further events will be fired.
     */
    void close() throws IOException;

    /**
     * Begins a new transaction on this collection.  Any calls that may modify
     * this collection or any features within it are done within the context of
     * the given transaction.
     * <p>
     * Implementors of this method should attach some state to the given
     * transaction by calling {@link Transaction#putState}, passing in a
     * key that identifies this type of collection and a value that is a
     * data store-specific implementation of the {@link Transaction.State}
     * interface.  When the transaction is complete, either
     * {@link Transaction.State#commit() commit()} or
     * {@link Transaction.State#rollback() rollback()}
     * will be called on the state object that was attached.
     */
    void setTransaction(Transaction t);

    /**
     * Retrieves the current transaction on this feature collection or null if
     * there is no current transaction.
     */
    Transaction getTransaction();

    /**
     * Indicates the duration, and any additional information for any
     * subsequent lock operations.
     * <p>
     * Implementations may provide some {@code TRANSACTION_LOCK} constant to request that the
     * lock only endure for the duration of the current {@linkplain Transaction transaction}.
     */
    void setLockRequest(LockRequest lock);

    /**
     * Lock indicated features.
     * To unlock, or operate on these features, one needs to perform a
     * {@linkplain Transaction transaction} with the authorization token provided
     * by {@link LockResponse}.
     * <p>
     * Workflows:
     * <ul>
     *   <li>{@link LockRequest} + {@link Transaction#AUTO_COMMIT AUTO_COMMIT} returns a
     *       {@link LockResponse} indicating the success of the operation and authorization
     *       tokens aquired.</li>
     *   <li>{@code TRANSACTION_LOCK} + {@link Transaction} returns {@code TRANSACTION_LOCKRESPONSE}
     *       indicating a short term lock is held that will expire at the next commit or rollback.
     *       Use this workflow to reserve content before starting edits.</li>
     *   <li>{@link LockRequest} + {@link Transaction} returns {@code PENDING}. Check the result
     *       of commit to discover the success of any lock methods made during the transaction.</li>
     * </ul>
     * For a discussion of these workflows please read the
     * {@linkplain org.opengis.feature package javadocs}.
     *
     * @return Lock response for {@link Transaction#AUTO_COMMIT AUTO_COMMIT},
     *         {@code TRANSACTION_LOCK} for a short term lock, or {@code PENDING}
     *         when used in a {@linkplain Transaction transaction}.
     */
    LockResponse lockAll(Collection<Feature> c);

    /**
     * Lock this collection of features. To unlock, or operate on these features, one needs
     * to perform a {@linkplain Transaction transaction} with the authorization token provided
     * by {@link LockResponse}.
     * <p>
     * Workflows:
     * <ul>
     *   <li>{@link LockRequest} + {@link Transaction#AUTO_COMMIT AUTO_COMMIT} returns a
     *       {@link LockResponse} indicating the success of the operation and authorization
     *       tokens aquired.</li>
     *   <li>{@code TRANSACTION_LOCK} + {@link Transaction} returns {@code TRANSACTION_LOCKRESPONSE}
     *       indicating a short term lock is held that will expire at the next commit or rollback.
     *       Use this workflow to reserve content before starting edits.</li>
     *   <li>{@link LockRequest} + {@link Transaction} returns {@code PENDING}. Check the result
     *       of commit to discover the success of any lock methods made during the transaction.</li>
     * </ul>
     * For a discussion of these workflows please read the
     * {@linkplain org.opengis.feature package javadocs}.
     *
     * @return Lock response for {@link Transaction#AUTO_COMMIT AUTO_COMMIT},
     *         {@code TRANSACTION_LOCK} for a short term lock, or {@code PENDING}
     *         when used in a {@linkplain Transaction transaction}.
     */
    LockResponse lock();
    
    /**
     * Returns the request indicating the duration for the {@link #lockAll} method.
     */
    LockRequest getLockRequest();

    /**
     * Adds a listener whose methods will be called whenever a new feature is
     * added or removed from this collection.  Since {@code FeatureCollection} objects
     * may be the result of a query, the methods of the {@code FeatureListener} may
     * be invoked when a feature's attributes have been updated in such a way
     * that its values now pass the filter (or no longer pass the filter).
     */
    void addFeatureListener(FeatureListener fl);

    /**
     * Removes a listener that was previously added with {@link #addFeatureListener}.
     */
    void removeFeatureListener(FeatureListener fl);
}
