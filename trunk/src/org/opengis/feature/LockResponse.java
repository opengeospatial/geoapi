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

// J2SE direct dependencies
import java.util.Set;

// Annotations
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;


/**
 * Represents a response for a lock request.
 * A lock is requested for a duration, except for the special case of a
 * {@code TRANSACTION_LOCK} where the request is until the next commit or rollback.
 * <p>
 * A successful {@linkplain FeatureCollection#lock lock request} will result in an
 * {@linkplain LockResponse#getToken authorization ID} being generated.
 * This is a simple token you can keep until you need to work with the content again.
 * Without this token you are prevented from work (at least until the duration is up).
 *
 * @author Jody Garnett (Refractions Research)
 * @since GeoAPI 2.0
 */
@XmlElement("LockFeatureResponse")
public interface LockResponse {
    /**
     * Number of features successfully locked, or -1 if unknown
     * In the rare case where you need to report back on the number of successful locks
     * you can issue a bunch of individual {@linkplain FeatureCollection#lock lock request}
     * and check the number of features locked.
     *
     * @return number of locked features or -1 if unknown.
     */
    int getNumberLocked();

    /**
     * Used to collect the results of a number of lock requests for {@link Transaction#commit()}.
     */
    void increaseNumberLocked(int amount);

	/**
     * Add an additional authorization token to collected results for {@link Transaction#commit()}.
     * Note this abstraction does not allow the collection of more than one token per
     * {@link FeatureStore}. This should not be an issue give our two workflows:
     * <ul>
     *   <li>{@link Transaction#AUTO_COMMIT AUTO_COMMIT}: Each lock method returns a different
     *        {@code LockResponse}.</li>
     *   <li>{@link Transaction} + {@code TRANSACTION_LOCK}: Sepcial case object
     *        {@code TRANSACTION_LOCK_RESPONSE} is always returned.</li>
     *   <li>{@link Transaction}: Each lock method returns null, commit returns a single
     *       {@link LockResponse}. Given that a {@code Transaction} is supposed to gather
     *       everything up into a single commit, a datastore should be capable of issuing
     *       a single authorization token (even if more then one of its {@link FeatureCollection}s
     *       was involved in the transaction.</li>
     * </ul>
     */  
    void addAuthorization(FeatureStore store, String token);

    /**
     * Retreives the authorization token for the special case of only one {@link FeatureStore} being locked.
     * This is the most common situation. Often client code will only work on one {@link FeatureCollection}
     * at a time, or several {@code FeatureCollection}s belonging to the same {@code FeatureStore}.
     *
     * @return token, or null if a single token was unavailable.
     *
     * @todo Consider renaming this method as {@code getAuthorization()} for consistency
     *       with {@link #getAuthorization} and the rest of feature API.
     */
    @XmlElement("LockID")
    String getToken();

    /**
     * Returns the authorization token for indicated feature store,
     * or null if there is no locks known for the feature store.
     * 
     * @param store The feature store to search for.
     * @return token, or null if a token for feature store was unavailable.
     */
    String getAuthorization(FeatureStore store);

    /**
     * Returns the set of locked feature stores.
     */
    @XmlElement("FeaturesLocked")
    Set<FeatureStore> getFeatureStores();
}
