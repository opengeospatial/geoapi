/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2006 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

/**
 * FeatureCollection backed by a data source such as a file for database.
 * <p>
 * This interface captures all the state associated with interacting with
 * a data source providing features. This state is primarly captured with
 * two constructs: the current <code>Transaction</code> and the current
 * <code>LockRequest</code>. These ideas work in tandom to dicate the context
 * used for opperatations against this FeatureCollection, or any Colleciton
 * obtained via subCollection.
 * </p>
 * <p>
 * This interface also allows for events based on feature modificaiton.
 * </p>
 * @author Ian Turton (CCG)
 * @author Rob Hranac (VFNY)
 * @author Ian Schneider (USDA-ARS)
 * @author Jody Garnett (Refracitons Research)
 * 
 * @since GeoAPI 2.1
 * @see java.util.Collection
 */
public interface DataFeatureCollection extends FeatureCollection {
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
    //void setTransaction(Transaction t);
    
    /**
     * Retrieves the current transaction on this feature collection or null if
     * there is no current transaction.
     */
    //Transaction getTransaction();
    
    
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
    //LockResponse lock();
    
    /**
     * Returns the request indicating the duration for the {@link #lockAll} method.
     */
    //LockRequest getLockRequest();

    /**
     * Indicates the duration, and any additional information for any
     * subsequent lock operations.
     * <p>
     * Implementations may provide some {@code TRANSACTION_LOCK} constant to request that the
     * lock only endure for the duration of the current {@linkplain Transaction transaction}.
     */
    //void setLockRequest(LockRequest lock);
    
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
    void close();
    
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
