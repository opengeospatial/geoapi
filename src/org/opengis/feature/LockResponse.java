package org.opengis.feature;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Represents a response for a lock request.
 * <p>
 * A lock is requested for a duration, except for the special case of a TRANSACTION_LOCK
 * where the request is until the next commit or rollback.
 * </p>
 * <p>
 * A successful lock request will result in an AuthorizationID being generated for you.
 * This is a simple token you can keep until you need to work with the content again.
 * Without this token you are prevented from work (at least until the duration is up).
 * </p>
 * <p>
 * Of course the fact that this is simply a long right now may indicate its future
 * replacement. I am going to try and have commit and lock opperations return a
 * LockResult, if that does not meet with approval this object can become a collector
 * in the style of JUnitTestResults.
 * </p>
 * @author Jody Garnett
 */
public class LockResponse {
    
    /**
     * Special case used to indicate a lock lasting until the end of the transaction.
     * <p>
     * TODO: Make sure this is a read only implemetnation
     * </p>
     */
    static final LockResponse TRANSACTION_LOCK_RESPONSE = new LockResponse();
    
    /**
     * Special case used to indicate result is pending, used by Transasction.AUTO_COMMIT.
     * <p>
     * Please check the LockResponse returned by commit.
     * </p>
     * <p>
     * TODO: Make sure this is a read only implemetnation
     * </p>
     */  
    static final LockResponse PENDING = new LockResponse();
    
    /**
     * Special case used to indicate no features were locked.
     * <p>
     * This is the lock response returned by commit when no lock requets
     * were made.
     * </p>
     * <p>
     * TODO: Make sure this is a read only implemetnation
     * </p>
     */  
    static final LockResponse NONE = new LockResponse();
    
    /** Authorization tokens by FeatureStore */
    Map/*<FeatureStore,String>*/ authorizationMap;
    
    /**
     * Number of Features locked, or -1 if unknown.
     */
    int numberLocked;
    
    /**
     * Constructor usable for use with lock( Filter ) under AUTO_COMMIT.
     * 
     * @param featureStore FeatureStore being locked
     * @param token Authorization token for later opperations on locked Features
     * @param numberLocked Number of Features successfully locked, or -1 for unknown
     */
    public LockResponse( FeatureStore featureStore, String token, int numberLocked ){
        authorizationMap = new HashMap();
        authorizationMap.put( featureStore, token );
        this.numberLocked = numberLocked;
    }
    /** Constructor for use of with a commit() implementation.
     * <p>
     * The results of the lock requests will need to be gathered using:
     * <ul>
     * <li>increaseNumberLocked( amount )
     * <li>addAuthorizationToken( featureStore, token )
     * </ul>
     * </p>
     */
    public LockResponse(){
        authorizationMap = new HashMap();
        numberLocked = 0;
    }
    /**
     * Number of features successfully locked, or -1 if unknown
     * <p>
     * In the rare case where you need to report back on the number of successful locks
     * you can issue a bunch of individual lock( FidFilter ) requests and check the
     * number of features locked vs the number of features indicated in your FidFilter.
     * </p>
     * @return number of locked features or -1 if unknown.
     */
    public int getNumberLocked() {
        return numberLocked;
    }    
    
    /** Used to collect the results of a number of lock requests for commit() */
    public void increaseNumberLocked( int amount ){
        numberLocked += amount;
    }
    /**
     * Add an additional authorization token to collected results for commit().
     * <p>
     * Note this abstraction does not allow the collection of more then one token per
     * FeatureStore. This should not be an issue give our two workflows:
     * <ul>
     * <li>Transaction.AUTO_COMMIT: Each lock method returns a different LockResponse.
     * <li>Transaction + FeatureRequest.TRANSACTION_LOCK: Sepcial Case object TRANSACTION_LOCK_RESPONSE is always returned
     * <li>Transaction: Each lock method returns null, commit returns a single LockResponse.
     * Given that a Transaction is supposed to gather everything up into a single commit a datastore
     * should be capable of issuing a single authorization token (even if more then one of
     * its FeatureCollections was involved in the transaction.
     * </ul>
     * </p>
     */  
    public void addAuthorization( FeatureStore featureStore, String token ){
        authorizationMap.put( featureStore, token );
    }
    
    /**
     * Authorization token for indicated featureStore, or null if no locks known for featureStore.
     * 
     * @param featureStore FeatureStore to search for
     * @return token, or null if a token for featureStore was unavailable
     */
    public String getAuthorization( FeatureStore featureStore ){
        return (String) authorizationMap.get( featureStore );
    }
    
    /** Set of locked FeatureStores */
    public Set/*<FeatureStore>*/ getFeatureStores(){
        return Collections.unmodifiableSet( authorizationMap.keySet() );
    }
    
    /**
     * Retrives the authorization token for the special case of only one FeatureStore being locked.
     * <p>
     * This is the most common situration, often client code will only work on one FeatureCollection
     * at a time, or several FeatureCollections belonging to the same FeatureStore.
     * </p>
     * @return token, or null if a single token was unavailable.
     */
    public String getToken(){
        if( authorizationMap.size() == 1 ){
            return (String) authorizationMap.values().iterator().next();
        }
        return null;
    }
}