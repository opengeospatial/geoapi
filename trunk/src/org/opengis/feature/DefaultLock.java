package org.opengis.feature;

import java.util.HashMap;
import java.util.Iterator;

public class DefaultLock implements Lock {
    private long duration;
    private String authId;
    private HashMap stateMap;

    public DefaultLock(long duration, String authId) {
        this.duration = duration;
        this.authId = authId;
        this.stateMap = new HashMap();
    }

    public long release() throws DataStoreException {
        long minResult = Long.MAX_VALUE;
        Throwable err = null;
        Iterator states = stateMap.values().iterator();

        while (states.hasNext()) {
            State state = (State) states.next();
            try {
                long result = state.release();
                if (result < minResult)
                    minResult = result;
            }
            catch (Throwable t) {
                err = t;
            }
        }
        if (err != null)
            throw new DataStoreException("Errors occured releasing the lock.  Last one is cause of this exception.", err);

        return minResult;
    }

    public long refresh() throws DataStoreException {
        return refresh(duration);
    }

    public long refresh(long requestedDuration) throws DataStoreException {
        long minResult = Long.MAX_VALUE;
        Throwable err = null;
        Iterator states = stateMap.values().iterator();

        while (states.hasNext()) {
            State state = (State) states.next();
            try {
                long result = state.refresh(requestedDuration);
                if (result < minResult)
                    minResult = result;
            }
            catch (Throwable t) {
                err = t;
            }
        }
        if (err != null)
            throw new DataStoreException("Errors occured releasing the lock.  Last one is cause of this exception.", err);

        return minResult;
    }

    public String getAuthId() {
        return authId;
    }

    public long getDuration() {
        return duration;
    }

    public void putState(Object key, State state) throws DataStoreException {
        stateMap.put(key, state);
        state.setLock(this);
    }

    public State getState(Object key) {
        return (State) stateMap.get(key);
    }

    public void removeState(Object key) {
        stateMap.remove(key);
    }
}
