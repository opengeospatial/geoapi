/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */
package org.opengis.data;

/**
 * Used to lock features when used with LockingDataSource.
 * 
 * <p>
 * A FeatureLockFactory is used to generate FeatureLocks.
 * </p>
 * 
 * <p>
 * A FeatureLock representing the Current Transaction has been provided.
 * </p>
 *
 * @author jgarnett, Refractions Research, Inc.
 * @version $Id$
 *
 * @see <a
 *      href="http://vwfs.refractions.net/docs/Database_Research.pdf">Database
 *      Reseach</a>
 * @see <a
 *      href="http://vwfs.refractions.net/docs/Transactional_WFS_Design.pdf">Transactional
 *      WFS Design</a>
 * @see <a
 *      href="http://vwfs.refractions.net/docs/Design_Implications.pdf">Design
 *      Implications</a>
 * @see FeatureLockFactory
 */
public interface FeatureLock {
    /**
     * FeatureLock representing Transaction duration locking
     * 
     * <p>
     * When this FeatureLock is used locks are expected to last until the
     * current Transasction ends with a commit() or rollback().
     * </p>
     */
    public static final FeatureLock TRANSACTION = new CurrentTransactionLock();

    /**
     * LockId used for transaction authorization.
     *
     * @return A string of the LockId.
     */
    String getAuthorization();

    /**
     * Time from now the lock will expire
     *
     * @return A long of the time till the lock expires.
     */
    long getDuration();
}


/**
 * A request for a Lock that last the duration of a transaction.
 * 
 * <p>
 * The single instance of this class is available as
 * <code>FeatureLock.TRANSACTION</code>.
 * </p>
 *
 * @author jgarnett
 */
class CurrentTransactionLock implements FeatureLock {
    /**
     * Transaction locks do not require Authorization.
     * 
     * <p>
     * Authorization is based on being on "holding" the Transaction rather than
     * supplying an authorization id.
     * </p>
     *
     * @return <code>CURRENT_TRANSACTION</code> to aid in debugging.
     *
     * @see org.geotools.data.FeatureLock#getAuthorization()
     */
    public String getAuthorization() {
        return toString();
    }

    /**
     * Transaciton locks are not held for a duration.
     * 
     * <p>
     * Any locking performed against the current Transaction is expected to
     * expire when the transaction finishes with a close or rollback
     * </p>
     *
     * @return <code>-1</code> representing an invalid duration
     *
     * @see org.geotools.data.FeatureLock#getDuration()
     */
    public long getDuration() {
        return -1;
    }

    public String toString() {
        return "CURRENT_TRANSACTION";
    }
}
