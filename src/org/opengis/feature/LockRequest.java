package org.opengis.feature;

/**
 * Represents the request for a lock.
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
public class LockRequest {
    /** Special value used to ask for a Lock until the next commit or rollback */
    public static final LockRequest TRANSACTION_LOCK = new LockRequest(-1);
    
    /** Duration in miliseconds the lock is requested for */
    private long duration;
    
    public LockRequest( long duration ){
        this.duration = duration;
    }    
}