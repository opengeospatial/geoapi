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

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Quick implementation of Transaction api.
 * <p>
 * Please see Transaction interface for an outline of what this class is all
 * about.
 * </p>
 *
 * @author Jody Garnett, Refractions Research
 *
 * @revisit Implementation should not be part of GeoAPI...
 */
public class DefaultTransaction implements Transaction {
    /** Records State by key */
    Map stateLookup = new HashMap();

    /** Records properties by key */
    Map propertyLookup = new HashMap();

    /** Handle used to identify Transaction for the user */
    String handle;

    /** Records current Authorizations */
    Set authorizations = new HashSet();

    public DefaultTransaction() {
        Throwable t = new Throwable("who called me?");
        StackTraceElement e = t.getStackTrace()[1];
        handle = e.getClassName()+"."+e.getMethodName()+ " Transaction";
    }
    public DefaultTransaction( String handle ){
        this.handle = handle;
    }

    /**
     * Remembers Externalized State for a DataSource.
     * 
     * <p>
     * This is the GOF Memento pattern: a FeatureSource is able to externalize
     * its internal State required for Transaction support and have this class
     * manage it. It may retrieve this State with getState( key ).
     * </p>
     * 
     * <p>
     * In addition several FeatureSource implementations may share State, a
     * common example is JDBCDataSources keeping a shared JDBC connection
     * using the JDBC URL as a key.
     * </p>
     *
     * @param key Key used to externalize State
     * @param state Externalized State (Momeneto)
     *
     * @throws IllegalArgumentException When Transaction already using key
     */
    public void putState(Object key, State state) {
        if (stateLookup.containsKey(key)) {
            State current = (State) stateLookup.get(key);

            if (state == current) {
                throw new IllegalArgumentException(
                    "Transaction already has an this State for key: " + key
                    + ". Please check for existing State before creating your own.");
            } else {
                throw new IllegalArgumentException(
                    "Transaction already has an entry for key:" + key
                    + ". Please check for existing State before creating your own.");
            }
        } else {
            stateLookup.put(key, state);

            // allow configuration
            state.setTransaction(this);
        }
    }

    /**
     * Removes state from DefaultTransaction's care.
     * 
     * <p>
     * Currently does not complain if there is no State associated with key to
     * remove - this may change in the future.
     * </p>
     *
     * @param key
     *
     * @throws IllegalArgumentException If no State was maintained for supplied
     *         <code>key</code>
     */
    public void removeState(Object key) {
        if( stateLookup == null){
            throw new IllegalStateException("Transaction has been closed");
        }        
        if (stateLookup.containsKey(key)) {
            State state = (State) stateLookup.remove(key);
            state.setTransaction(null);
        } else {
            throw new IllegalArgumentException(
                "Transaction does not no anything about key:" + key
                + ". Has this key already been removed?");
        }
    }

    /**
     * Returns externalized state or <code>null</code> if not available.
     * 
     * <p>
     * Used by DataStore implementations to externalize information required
     * for Transaction support using the GOF Momento pattern.
     * </p>
     *
     * @param key
     *
     * @return Previously externalized State.
     */
    public State getState(Object key) {
        if( stateLookup == null){
            throw new IllegalStateException("Transaction has been closed");
        }
        return (State) stateLookup.get(key);
    }

    /**
     * Commits all modifications against this Transaction.
     * 
     * <p>
     * This implementation will call commit() on all State managed by this
     * Transaction. This allows DataStores to provide their own implementation
     * of commit().
     * </p>
     *
     * @throws IOException Encountered problem maintaining transaction state
     * @throws DataSourceException See IOException
     */
    public void commit() throws IOException {
        State state;
        int problemCount = 0;
        IOException io = null;

        for (Iterator i = stateLookup.values().iterator(); i.hasNext();) {
            state = (State) i.next();

            try {
                state.commit();
            } catch (IOException e) {
                problemCount++;
                io = e;
            }
        }

        if (io != null) {
            if (problemCount == 1) {
                throw io;
            }

            throw new DataSourceException("Commit encountered " + problemCount
                + " problems - the first was", io);
        }
        authorizations.clear();        
    }

    /**
     * Rollsback all modifications against this Transaction.
     * 
     * <p>
     * This implementation will call rollback() on all State managed by this
     * Transaction. This allows DataStores to provide their own implementation
     * of rollback().
     * </p>
     *
     * @throws IOException Encountered problem maintaining transaction State
     * @throws DataSourceException IOException
     */
    public void rollback() throws IOException {
        int problemCount = 0;
        IOException io = null;
        State state;

        for (Iterator i = stateLookup.values().iterator(); i.hasNext();) {
            state = (State) i.next();

            try {
                state.rollback();
            } catch (IOException e) {
                problemCount++;
                io = e;
            }
        }

        if (io != null) {
            if (problemCount == 1) {
                throw io;
            }

            throw new DataSourceException("Rollback encountered "
                + problemCount + " problems - the first was", io);
        }
        authorizations.clear();
    }
    /**
     * Frees all State held by this Transaction.
     */
    public synchronized void close(){
        for( Iterator i=stateLookup.values().iterator(); i.hasNext();){
            State state = (State) i.next();
            state.setTransaction( null );            
        }
        stateLookup.clear();
        stateLookup = null;
        authorizations.clear();
        authorizations = null;
        propertyLookup.clear();
        propertyLookup = null;
    }
    /**
     * The current set of Authorization IDs held by this Transaction.
     * 
     * <p>
     * This set is reset by the next call to commit or rollback.
     * </p>
     *
     * @return Set of Authorization IDs
     */
    public Set getAuthorizations() {
        if( authorizations == null){
            throw new IllegalStateException("Transaction has been closed");
        }        
        return Collections.unmodifiableSet(authorizations);
    }

    /**
     * Provides an authorization ID allowing access to locked Features.
     * 
     * <p>
     * Remember authorizations are cleared after every commit/rollback.
     * </p>
     *
     * @param authID Provided Authorization ID
     *
     * @throws IOException Encountered problems maintaing Transaction State
     * @throws DataSourceException See IOException
     */
    public void addAuthorization(String authID) throws IOException {
        if( authorizations == null){
            throw new IllegalStateException("Transaction has been closed");
        }                
        int problemCount = 0;
        IOException io = null;
        State state;
        authorizations.add(authID);

        for (Iterator i = stateLookup.values().iterator(); i.hasNext();) {
            state = (State) i.next();

            try {
                state.addAuthorization(authID);
            } catch (IOException e) {
                problemCount++;
                io = e;
            }
        }

        if (io != null) {
            if (problemCount == 1) {
                throw io;
            }
            throw new DataSourceException("setAuthorization encountered "
                + problemCount + " problems - the first was", io);
        }
    }
    
    public String toString() {
        return handle;
    }

    /**
     * Implementation of getProperty.
     */
    public Object getProperty(Object key) {
        if( propertyLookup == null){
            throw new IllegalStateException("Transaction has been closed");
        }        
        return propertyLookup.get( key );
    }

    /**
     * Implementation of addProperty.
     * 
     * @param key
     * @param value
     * @throws IOException
     */
    public void putProperty(Object key, Object value) throws IOException {
        if( propertyLookup == null){
            throw new IllegalStateException("Transaction has been closed");
        }        
        propertyLookup.put( key, value );        
    }

}
