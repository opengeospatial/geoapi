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
    private Transaction transaction;
    private int type;
    public DataStoreEvent(DataStore dataStore, GenericName typeName, Transaction transaction, int type) {
        super(dataStore);
        this.typeName = typeName;
        this.type = type;
    }

    public DataStore getDataStore() {
        return (DataStore) super.getSource();
    }

    public GenericName getTypeName() {
        return typeName;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public int getType() {
        return type;
    }
}