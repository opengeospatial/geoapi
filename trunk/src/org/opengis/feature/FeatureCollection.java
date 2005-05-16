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

// J2SE direct dependencies
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

// OpenGIS direct dependencies
import org.opengis.filter.Filter;


/**
 * 
 *
 * @since GeoAPI 1.1
 */
public interface FeatureCollection extends Feature, Collection<Feature> {
    /**
     * Returns an iterator that enumerates all of the features in this
     * collection.  The object returned from this method is always of type
     * {@link FeatureIterator}.
     */
    /*{FeatureIterator}*/ Iterator<Feature> iterator();

    /**
     * Creates a {@link Feature} array and populates it.
     * 
     * @throws OutOfMemoryError if the feature collection is too large to fit into memory.
     */
    Object[] toArray() throws OutOfMemoryError;

    /**
     * Populates the given array with the features in this collection.  If the
     * passed array is null or is not sufficiently large to contain all of the
     * features in this collection, a new Feature array will be created and
     * returned.
     * 
     * @throws OutOfMemoryError if the feature collection is too large to fit into memory.
     */
    /*{Feature}*/ Object[] toArray(/*{Feature}*/ Object[] buffer);

    /**
     * Returns {@code true} if this collection contains no {@link Feature}s.
     */
    boolean isEmpty();

    /**
     * Returns the size of the collection, if known.  It may return -1 if the
     * size is not known or would be too costly to compute (such as when the
     * features are streaming from a remote source).
     */
    int size();

    /**
     * Checks if the given feature is a member of this collection.
     */
    boolean contains(/*{Feature}*/ Object o);

    /**
     * Checks if every feature in the given collection is also a member of this
     * feature collection.
     */
    boolean containsAll(Collection<Feature> c);

    /**
     * Adds the given feature to this collection.  If this collection is backed
     * by a persistent store of some kind, then the given feature should
     * immediately be written to that persistent store.  If you want to add
     * multiple features to this collection, note that it may be considerably
     * more efficient to call the {@link #addAll} method.  The addition of
     * features takes place within the context of the current transaction on
     * this feature collection.
     *
     * @throws UnsupportedOperationException if the addition of new features is not supported.
     */
    boolean add(/*{Feature}*/ Object o) throws UnsupportedOperationException;

    /**
     * Adds all of the features contained in the given collection to this
     * collection.  If this collection is backed by a persistent store of some
     * kind, then the added features should immediately be written to that
     * persistent store.  The addition of features takes place within
     * the context of the current transaction on this feature collection.
     *
     * @throws UnsupportedOperationException if the addition of new features is not supported.
     */
    boolean addAll(Collection<Feature> c) throws UnsupportedOperationException;

    /**
     * Removes all of the features contained in this collection.  If this
     * collection is backed by a persistent store of some kind, then this method
     * should cause all of the features from this collection to be removed
     * from that persistent store.  This operation takes place within the
     * context of the current transaction on this feature collection.
     *
     * @throws UnsupportedOperationException if the removal of features is not supported.
     */
    void clear() throws UnsupportedOperationException;

    /**
     * Removes the given feature from this collection.  If this collection is
     * backed by a persistent store of some kind, then this method should cause
     * the given feature to be removed from the persistent store.  This
     * operation takes place within the context of the current transaction on
     * this feature collection.
     *
     * @throws UnsupportedOperationException if the removal of features is not supported.
     */
    boolean remove(/*{Feature}*/ Object o) throws UnsupportedOperationException;

    /**
     * Removes the given features from this collection.  If this collection is
     * backed by a persistent store of some kind, then this method should cause
     * all of the given features to be removed from the persistent store.  This
     * operation takes place within the context of the current transaction on
     * this feature collection.
     *
     * @throws UnsupportedOperationException if the removal of features is not supported.
     */
    boolean removeAll(Collection<Feature> c) throws UnsupportedOperationException;

    /**
     * Removes any features from this collection that are not present in the
     * given collection.  If this collection is backed by a persistent store of
     * some kind, then this method should cause the removed features to be
     * removed from the persistent store as well.  This operation takes place
     * within the context of the current transaction on this feature collection.
     *
     * @throws UnsupportedOperationException if the removal of features is not supported.
     */
    boolean retainAll(Collection<Feature> c) throws UnsupportedOperationException;

    /**
     * Returns a collection whose contents are the subset of features in this
     * collection that pass the given filter.  This is semantically equivalent
     * to going back to the {@link FeatureStore} that created this collection
     * and AND-ing the filter that produced this collection with the given filter.
     * <p>
     * Compare this method to {@link java.util.List#subList(int,int)}
     * from the Java Collections framework.
     */
    FeatureCollection subCollection(Filter filter);

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
     * Indicate the duration, and any additional information for any
     * subsequent lock operations.
     * <p>
     * You can use {@link LockRequest#TRANSACTION_LOCK} to request that
     * the lock only endure for the duration of the current transaction.
     */
    void setLockRequest(LockRequest lock);

    /**
     * Lock indicated features.
     * <p>
     * Note to unlock, or opperate on these features, one needs to perform a {@link Transaction}
     * with the authorization token provided by {@link LockResponse}.
     * <p>
     * Workflows:
     * <ul>
     * <li>{@link LockRequest} + {@link Transaction#AUTO_COMMIT} returns a
     *     {@link LockResponse} indicating the success of the opperation and
     *     and authoriazation tokens aquired.</li>
     * <li>{@link LockRequest#TRANSACTION_LOCK} + {@link Transaction} returns
     *     {@link LockResponse#TRANSACTION_LOCKRESPONSE} indicating
     *     a short term lock is held that will expire at the next commit or rollback.
     *     Use this workflow to reserve content before starting edits.</li>
     * <li>{@link LockRequest} + {@link Transaction} returns
     *     {@link LockResponse#PENDING}, check the result of commit to discover
     *     the success of of any lock methods made during the transaction.</li>
     * </ul>
     * For a discussion of these workflows please read the package javadocs.
     *
     * @return Lock response for {@link Transaction#AUTO_COMMIT},
     *         {@link LockResponse#TRANSACTION_LOCK} for a short term lock, or
     *         {@link LockResponse#PENDING} when used in a {@link Transaction}.
     */
    LockResponse lockAll(Collection<Feature> c);
    
    /**
     * Lock this collection of features.
     * <p>
     * Note to unlock, or opperate on these features, one needs to perform a {@link Transaction}
     * with the authorization token provided by {@link LockResponse}.
     * </p> 
     * <p>
     * Workflows:
     * <ul>
     * <li>{@link LockRequest} + {@link Transaction#AUTO_COMMIT} returns a
     *     {@link LockResponse} indicating the success of the opperation and
     *     and authoriazation tokens aquired.</li>
     * <li>{@link LockRequest#TRANSACTION_LOCK} + {@link Transaction} returns
     *     {@link LockResponse#TRANSACTION_LOCKRESPONSE} indicating
     *     a short term lock is held that will expire at the next commit or rollback.
     *     Use this workflow to reserve content before starting edits.</li>
     * <li>{@link LockRequest} + {@link Transaction} returns
     *     {@link LockResponse#PENDING}, check the result of commit to discover the
     *     success of of any lock methods made during the transaction.</li>
     * </ul>
     * For a discussion of these workflows please read the package javadocs.
     *
     * @return LockResponse for Transaction.AUTO_COMMIT, LockResponse.TRANSACTION_LOCK for a short term lock, or LockResponse.PENDING when used in a Transaction.
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
