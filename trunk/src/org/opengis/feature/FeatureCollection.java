package org.opengis.feature;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public interface FeatureCollection extends Feature, Collection {
    /**
     * Returns an iterator that enumerates all of the features in this
     * collection.  The object returned from this method is always of type
     * FeatureIterator.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public Iterator/*<Feature>*/ iterator();

    /**
     * Creates a Feature array and populates it.  Note that this method may
     * throw an OutOfMemoryException if the feature collection is too large to
     * fit into memory.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public Object [] toArray();

    /**
     * Populates the given array with the features in this collection.  If the
     * passed array is null or is not sufficiently large to contain all of the
     * features in this collection, a new Feature array will be created and
     * returned.  Note that this method may throw an OutOfMemoryException if the
     * feature collection is too large to fit into memory.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public Object [] toArray(Object [] buffer);

    /**
     * Returns true if this collection contains no Features.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public boolean isEmpty();

    /**
     * Returns the size of the collection, if known.  It may return -1 if the
     * size is not known or would be too costly to compute (such as when the
     * features are streaming from a remote source).
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public int size();

    /**
     * Checks if the given feature is a member of this collection.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public boolean contains(Object /*Feature*/ o);

    /**
     * Checks if every feature in the given Collection is also a member of this
     * Collection.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public boolean containsAll(Collection/*<Feature>*/ c);

    /**
     * Adds the given Feature to this collection.  If this collection is backed
     * by a persistent store of some kind, then the given feature should
     * immediately be written to that persistent store.  If you want to add
     * multiple features to this collection, note that it may be considerably
     * more efficient to call the addAll(Collection) method.  The addition of
     * features takes place within the context of the current transaction on
     * this FeatureCollection.
     * <p>
     * This method may throw an UnsupportedOperationException if the addition
     * of new features is not supported.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public boolean add(Object /*Feature*/ o);

    /**
     * Adds all of the Features contained in the given collection to this
     * collection.  If this collection is backed by a persistent store of some
     * kind, then the added features should immediately be written to that
     * persistent store.  If you want to add multiple features to this
     * collection, note that it may be considerably more efficient to call the
     * addAll(Collection) method.  The addition of features takes place within
     * the context of the current transaction on this FeatureCollection.
     * <p>
     * This method may throw an UnsupportedOperationException if the addition
     * of new features is not supported.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public boolean addAll(Collection/*<Feature>*/ c);

    /**
     * Removes all of the Features contained in this collection.  If this
     * collection is backed by a persistent store of some kind, then this method
     * should cause all of the features from this collection to be removed
     * from that persistent store.  This operation takes place within the
     * context of the current transaction on this FeatureCollection.
     * This method may throw an UnsupportedOperationException if the removal of
     * features is not supported.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public void clear();

    /**
     * Removes the given feature from this collection.  If this collection is
     * backed by a persistent store of some kind, then this method should cause
     * the given feature to be removed from the persistent store.  This
     * operation takes place within the context of the current transaction on
     * this feature collection.
     * This method may throw an UnsupportedOperationException if the removal of
     * features is not supported.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public boolean remove(Object /*Feature*/ o);

    /**
     * Removes the given features from this collection.  If this collection is
     * backed by a persistent store of some kind, then this method should cause
     * all of the given features to be removed from the persistent store.  This
     * operation takes place within the context of the current transaction on
     * this feature collection.
     * This method may throw an UnsupportedOperationException if the removal of
     * features is not supported.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public boolean removeAll(Collection/*<Feature>*/ c);

    /**
     * Removes any features from this collection that are not present in the
     * given collection.  If this collection is backed by a persistent store of
     * some kind, then this method should cause the removed features to be
     * removed from the persistent store as well.  This operation takes place
     * within the context of the current transaction on this feature collection.
     * This method may throw an UnsupportedOperationException if the removal
     * of features is not supported.
     * This method is inherited from <code>Collection</code>, but included here
     * for completeness.
     */
    public boolean retainAll(Collection/*<Feature>*/ c);

    /**
     * If some sort of connection was opened to a backing store to support this
     * collection, then this method closes this connection.  Users of this API
     * should always call this method before releasing the references to this
     * object so that resources get cleaned up in a timely fashion.  Those who
     * implement this interface are also encouraged to implement the finalize
     * method just in case the user forgets to call close so that perhaps things
     * might get cleaned up.
     * <p>
     * After invoking this method, all references to the feature listener
     * objects will be cleared and no further events will be fired.
     */
    public void close() throws IOException;

    /**
     * Begins a new transaction on this collection.  Any calls that may modify
     * this collection or any features within it are done within the context of
     * the given transaction.
     * <p>
     * Implementors of this method should attach some state to the given
     * transaction.  When the transaction is complete, either commit() or
     * rollback() will be called on the state object that was attached.
     */
    public void setTransaction(Transaction t);

    /**
     * Retrieves the current transaction on this FeatureCollection or null if
     * there is no current transaction.
     */
    public Transaction getTransaction();

    /**
     * Should cause all features in this feature collection to be locked.
     * Implementor of this method should attach some state to the given lock.
     */
    public void setLock(Lock lock);

    /**
     * Returns the current lock on these features or null if none.
     */
    public Lock getLock();

    /**
     * Adds a listener whose methods will be called whenever a new feature is
     * added or removed from this collection.  Since FeatureCollection objects
     * may be the result of a query, the methods of the FeatureListener may
     * be invoked when a Feature's attributes have been updated in such a way
     * that its values now pass the filter (or no longer pass the filter).
     */
    public void addFeatureListener(FeatureListener fl);

    /**
     * Removes a listener that was previously added with addFeatureListener.
     */
    public void removeFeatureListener(FeatureListener fl);
}
