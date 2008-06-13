/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.opengis.feature.Transaction.State;

@Deprecated
public class DefaultLock {
    private long duration;
    private String authId;
    private HashMap<Object,State> stateMap;

    public DefaultLock(long duration, String authId) {
        this.duration = duration;
        this.authId = authId;
        this.stateMap = new HashMap();
    }

    public long release() throws IOException {
        long minResult = Long.MAX_VALUE;
        Throwable err = null;

        Iterator<State> states = stateMap.values().iterator();
        while (states.hasNext()) {
            State state = (State) states.next();
            try {
                //long result = state.release();
                //if (result < minResult) minResult = result;
            }
            catch (Throwable t) {
                err = t;
            }
        }
        if (err != null) {
            //throw new IOException("Errors occured releasing the lock.  Last one is cause of this exception.", err);
        }

        return minResult;
    }

    public long refresh() throws IOException {
        return refresh(duration);
    }

    public long refresh(long requestedDuration) throws IOException {
        long minResult = Long.MAX_VALUE;
        Throwable err = null;
        Iterator states = stateMap.values().iterator();

        while (states.hasNext()) {
            State state = (State) states.next();
            try {
                // long result = state.refresh(requestedDuration);
                //if (result < minResult) minResult = result;
            }
            catch (Throwable t) {
                err = t;
            }
        }
        if (err != null) {
            //throw new IOException("Errors occured releasing the lock.  Last one is cause of this exception.").initCause(err);
        }
        return minResult;
    }

    public String getAuthId() {
        return authId;
    }

    public long getDuration() {
        return duration;
    }

    public void putState(Object key, State state) throws IOException {
        stateMap.put(key, state);
        //state.setLock(this);
    }

    public State getState(Object key) {
        return (State) stateMap.get(key);
    }

    public void removeState(Object key) {
        stateMap.remove(key);
    }
}
