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

/**
 * @author David Zwiers
 */
public interface Lock {
    public long release() throws DataStoreException;
    public long refresh() throws DataStoreException;
    public long refresh(long requestedDuration) throws DataStoreException;
    public String getAuthId();
    public long getDuration();
    public void putState(Object key, State state) throws DataStoreException;
    public State getState(Object key);
    public void removeState(Object key);

    public static interface State {
        public long release() throws DataStoreException;
        public long refresh(long duration) throws DataStoreException;
        public void setLock(Lock lock) throws DataStoreException;
    }
}
