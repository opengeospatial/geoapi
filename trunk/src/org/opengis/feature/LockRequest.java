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
 * @since GeoAPI 1.1
 */
public interface LockRequest {
	/**
	 * Returns a duration for which the request is valid.  The special value
	 * of -1 indicates that a lock should be valid only for the duration of a
	 * transaction.
	 */
    public long getDuration();
}
