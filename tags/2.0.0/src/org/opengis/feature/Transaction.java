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

// J2SE depencencies
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

// Annotations
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;


/**
 * The controller for transaction with {@link FeatureStore}.
 * Shapefiles, databases, <cite>etc.</cite> are safely modified with the assistance
 * of this interface. Transactions are also to provide authorization when working with
 * locked features.
 * <p>
 * All operations are considered to be working against a transaction. The {@link #AUTO_COMMIT}
 * constant is used to represent an immediate mode where requests are immediately commited.
 * Example use:
 * <blockquote><table width="100%" border="1" cellpadding="12"><tr><td><pre> Transaction t = ...
 * t.{@linkplain #putProperty putProperty}("someHint", someValue);
 * try {
 *     {@linkplain FeatureCollection} road  = store.getFeatures("road");
 *     {@linkplain FeatureCollection} river = store.getFeatures("river");
 *
 *     road .{@linkplain FeatureCollection#setTransaction setTransaction}(t);
 *     river.{@linkplain FeatureCollection#setTransaction setTransaction}(t);
 *
 *     // Provides authorization and operates against transaction
 *     t.{@linkplain #useAuthorization useAuthorization}(authorizationID);
 *     road .{@linkplain FeatureCollection#removeAll removeAll}(road .{@linkplain FeatureCollection#subCollection subCollection}(filter));
 *     river.{@linkplain FeatureCollection#removeAll removeAll}(river.{@linkplain FeatureCollection#subCollection subCollection}(filter));
 *
 *     t.{@linkplain #commit commit}(); // Commit operations
 * }
 * catch ({@linkplain IOException} exception){
 *     t.{@linkplain #rollback rollback}();    // Cancel operations
 *     throw exception; // Propagate the exception
 * }
 * finally {
 *     t.{@linkplain #close close}(); // free resources
 * }</pre></td></tr></table></blockquote>
 * <p>
 * Example code walkthrough from the perspective of transaction:
 * <ul>
 *   <li>A new transaction is created</li>
 *   <li>A hint is provided using <code>{@linkplain #putProperty putProperty}(key, value)</code></li>
 *   <li>Transaction is provided to two {@link FeatureStore}s. This may result in several
 *       {@link org.opengis.feature.Transaction.State} instances being registered. These instances of
 *       transaction state may make use of any hint provided as {@linkplain #putProperty properties}
 *       (in previous step) when they are connected with
 *       <code>{@linkplain org.opengis.feature.Transaction.State#setTransaction State.setTransaction}(t)</code>.</li>
 *   <li><code>t.{@linkplain #useAuthorization useAuthorization}(authorizationID)</code> is called.
 *       Each {@link org.opengis.feature.Transaction.State} has its
 *       {@link org.opengis.feature.Transaction.State#addAuthorization addAuthorization}
 *       callback invoked with the value of {@code authorizationID}.</li>
 *   <li>{@link FeatureCollection#removeAll removeAll} methods are called on the two
 *       {@link FeatureCollection}s. Any of these opperations may make use of the
 *       hints provided as {@linkplain #putProperty properties}.</li>
 *   <li>The transaction is commited. All of the {@linkplain org.opengis.feature.Transaction.State transaction state}
 *       instances have there {@linkplain org.opengis.feature.Transaction.State#commit() commit commit} methods called.</li>
 *   <li>The transaction is closed. All of the {@linkplain org.opengis.feature.Transaction.State transaction state}
 *       instances have there {@linkplain org.opengis.feature.Transaction.State#close() close close} methods called.</li>
 * </ul>
 *
 * @author Jody Garnett (Refractions Research)
 * @author Chris Holmes (TOPP)
 * @since GeoAPI 2.0
 */
@XmlElement("Transaction")
public interface Transaction {
    /**
     * Marker constant used to indicate immidiate response.
     * The constant is a pure Marker (similar in spirit to a Null Object pattern).
     * All methods do nothing, this constant can be detected by interested parties
     * as a request to perform actions immidiately.
     */
    Transaction AUTO_COMMIT = new AutoCommit();

    /**
     * Provides a property for this transasction.
     * All proceeding {@link FeatureStore} operations may make use of the provided property.
     *
     * @throws IOException if there are problems with the {@linkplain FeatureStore feature store}.
     *
     * @see #getProperty
     */
    void putProperty(Object key, Object value) throws IOException;
    
    /**
     * Retrieves a transaction property held by this transaction.
     * This may be used to provide hints to {@link FeatureStore} implementations.
     * It operates as a blackboard for client, {@code FeatureStore} communication.
     *
     * @see #putProperty
     */
    Object getProperty(Object key);

    /**
     * Uses an Authorization token with this transaction. Any locks held against
     * this token will be released on {@linkplain #commit commit}.
     * <p>
     * All {@link FeatureCollection}s operations can make use of the provided authorization
     * for the duration of this transaction. Authorization is only maintained until this
     * transaction is {@linkplain #commit commited} or {@linkplain #rollback rolled back}.
     * That is operations that modify or delete a feature will only succeed if affected features
     * either:
     * <ul>
     *   <li>not locked</li>
     *   <li>locked with a provided {@code authorizationID}</li>
     * </ul>
     * <p>
     * You may call this method several times to provide authorization token to multiple
     * {@linkplain FeatureCollection feature collections}.
     *
     * @param authorizationID Authorization ID. Should of been obtained from a {@link LockResponse}.
     * @throws IOException if there are problems with the {@linkplain FeatureStore feature store}.
     *
     * @see #getAuthorizations
     */
    void useAuthorization(String authorizationID) throws IOException;

    /**
     * Returns the set of authorizations IDs held by this transaction.
     * This collection is reset by the next call to {@link #commit} or {@link #rollback}.
     *
     * @see #useAuthorization
     */
    Set<String> getAuthorizations();

    /**
     * Allows {@link FeatureStore} to squirel away information (and callbacks) for later.
     * The most common example is a JDBC {@code FeatureStore} saving the required
     * connection for later opperations.
     * <p>
     * This method will call
     * <code>{@linkplain org.opengis.feature.Transaction.State#setTransaction State.setTransaction}(this)</code>
     * to allow {@code State} a chance to configure itself.
     *
     * @param key Key used to externalize State
     * @param state Externalized State
     *
     * @see #removeState
     * @see #getState
     */
    void putState(Object key, State state);

    /**
     * Allows {@code FeatureStore}s to clean up information (and callbacks) they earlier provided.
     * Care should be taken when using shared State to not remove State required by another 
     * feature sources.
     * <p>
     * <code>{@linkplain org.opengis.feature.Transaction.State#setTransaction State.setTransaction}(null)</code>
     * to allow {@code State} a chance cleanup after itself.
     *
     * @param key Key that was used to externalize State
     */
    void removeState(Object key);

    /**
     * Retrieve a state used to squirel away information (and callbacks) for later.
     * The most common example is a JDBC FeatureStore saving the required
     * connection for later opperations.
     *
     * @param key Key that was used to externalize State
     * @return Current State externalized by key, or {@code null} if not found
     */
    State getState(Object key);

    /**
     * Makes all transactions made since the previous commit/rollback permanent.
     * {@link FeatureStore}s will need to issue any changes notifications using a
     * {@link FeatureEvent} to all {@code FeatureStore}s with the
     * same type name and a different transaction. {@code FeatureStore}s with the
     * same transaction will been notified of changes as the feature writer made
     * them.
     * <p>
     * Workflows:
     * <ul>
     *   <li>{@link LockRequest} + {@code Transaction} returns a {@link LockResponse}
     *       as the result of any and all lock requests made during the transaction.</li>
     *   <li>{@code Transaction} returns {@code LockResponse.NONE} when no lock requests
     *       are made.</li>
     * </ul>
     * <p>
     * For a discussion of these workflows please read the
     * {@linkplain org.opengis.feature package javadocs}.
     *
     * @return The lock response, or {@code LockResponse.NONE} if no lock requests were made.
     * @throws IOException if there are problems with the {@linkplain FeatureStore feature store}.
     */
    LockResponse commit() throws IOException;

    /**
     * Undoes all transactions made since the last commit or rollback.
     * {@link FeatureStore}s will need to issue any changes notifications using a
     * {@link FeatureEvent}. This will need to be issued to all
     * {@code FeatureStore}s with the same type name and transaction.
     *
     * @throws UnsupportedOperationException if the rollback method is not
     *         supported by this datasource.
     * @throws IOException if there are problems with the {@linkplain FeatureStore feature store}.
     */
    void rollback() throws UnsupportedOperationException, IOException;
 
    /**
     * Provides an opportunity for a transaction to free any
     * {@link org.opengis.feature.Transaction.State State} it maintains.
     * This method should call
     * <code>{@linkplain org.opengis.feature.Transaction.State#setTransaction State.setTransaction}(null)</code>
     * on all state it maintains.
     * <p>
     * It is hoped that FeatureStore implementations that have externalized
     * their state with the transaction take the opportunity to revert to
     * {@link #AUTO_COMMIT}.
     *
     * @throws IOException if there are problems with the {@linkplain FeatureStore feature store}.
     */
    void close() throws IOException;

    /**
     * {@link FeatureStore} implementations can use this interface to externalize the
     * state they require to implement {@link Transaction} support.
     * The {@linkplain #commit commit} and {@linkplain #rollback rollback} methods will
     * be called as required. The intention is that several {@code FeatureStore}s can
     * share common transaction state (example: Postgis feature stores sharing a connection
     * to the same database).
     *
     * @author Jody Garnett, Refractions Reasearch Inc.
     * @since GeoAPI 2.0
     */
    public static interface State {
        /**
         * Provides configuration information for this {@code Transaction.State}.
         * This method is called with non null {@code transaction} when this state is
         * {@linkplain Transaction#putState put} into a {@linkplain Transaction transaction}.
         * This transaction will be used to determine correct event notification.
         * <p>
         * This method is called again with {@code null} when state is
         * {@linkplain Transaction#removeState removed} (usually during
         * {@link Transaction#close}).
         */
        void setTransaction(Transaction transaction);

        /**
         * Call back used for
         * <code>{@linkplain Transaction#useAuthorization Transaction.useAuthorization}(authorizationID)</code>.
         */
        void addAuthorization(String authorizationID) throws IOException;

        /**
         * Call back used for {@link Transaction#commit}.
         */
        LockResponse commit() throws IOException;

        /**
         * Call back used for {@link Transaction#rollback}
         */
        void rollback() throws IOException;
    }
}

/**
 * NullObject indicating AUTO_COMMIT mode.
 * It follows the pattern of "Null Object" or more accuratly "Special Case".
 *
 * @author Jody Garnett
 * @since GeoAPI 2.0
 */
final class AutoCommit implements Transaction {
    /**
     * AutoCommit does not support properties.
     * @throws IOException Indicating to client code that properties are not supported
     */
    public void putProperty(Object key, Object value) throws IOException {
        throw new UnsupportedOperationException("AUTO_COMMIT does not support properties");        
    }

    /** AutoCommit cannot retain properties */
    public Object getProperty(Object key) {
        return null;
    }

    /** AutoCommit cannot retain authorizations */ 
    public void useAuthorization(String authorizationID) throws IOException {
        throw new IOException("Authorization IDs are not valid for AutoCommit Transaction");
    }

    /** AutoCommit cannot retain authorizations */ 
    public Set<String> getAuthorizations() {
        return Collections.EMPTY_SET;
    }

    /** AutoCommit cannot retain state - it is infact stateless */
    public void putState(Object key, State state) {
    }

    /** AutoCommit cannot retain state - it is infact stateless */
    public void removeState(Object key) {
    }

    /** AutoCommit cannot retain state - it is infact stateless */
    public State getState(Object key) {
        return null;
    }

    /** AutoCommit commits all the time - so this is a NOP */
    public LockResponse commit() {
        // No lock requests have been buffered (they were auto commited after all)
        return /* LockResponse.PENDING */ null;
    }

	/**
     * AutoCommit does not support rollback - it is already too late.
     * @throws IOException Indicating to client code that rollback cannot be supported
     */
    public void rollback() throws IOException {
        // Conversly we could treat this as a a NOP
        // Justification - the commit has already occured a rollback
        // would acomplish nothing. And nothing is just what we can do.
        throw new IOException("AUTO_COMMIT does not support rollback");
    }

    /** AutoCommit does not maintain State - so this is a NOP */
    public void close() {
        // We have no state to clean up after
    }
}
