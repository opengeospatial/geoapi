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

import java.util.EventObject;

import org.opengis.util.GenericName;


/**
 * Instances of this class are passed to the methods of DataStoreListener when
 * the user of the DataStore adds, deletes, or modifies a feature type.
 */
public class DataStoreEvent extends EventObject {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1842329357109520014L;
    
    public static final int ADD = 1;
    public static final int MODIFY = 0;
    public static final int DELETE = -1;

    private GenericName typeName;
    private int type;
    
    public DataStoreEvent(DataStore dataStore, GenericName typeName, int type) {
        super(dataStore);
        this.typeName = typeName;
        this.type = type;
    }
    
    public DataStore getDataStore() {
        return (DataStore) super.getSource();
    }

    // consider rename to getGenericName?
    public GenericName getTypeName() {
        return typeName;
    }
    /** One of ADD, MODIFY, DELETE indicating event type */
    public int getType() {
        return type;
    }
    /**
     * Of the form "DataStore (dataStore.displayName|dataStore) (ADD|MODIFY|DELETE) Event"
     */
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append( "DataStore " );
        DataStore dataStore = getDataStore();
        if( dataStore != null ){
            buf.append( dataStore.getDisplayName() != null ? dataStore.getDisplayName() : dataStore );
        }
        switch( type ){
        case ADD: buf.append( " ADD" ); // I want my Java 5 enum class :-)
        case MODIFY: buf.append( " MODIFY" ); 
        case DELETE: buf.append( " DELETE" );
        }
        buf.append( " Event" );
        
        return buf.toString();
    }
}