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
    private GenericName type;

    public DataStoreEvent(DataStore dataStore, GenericName type) {
        super(dataStore);
        this.type = type;
    }

    public DataStore getDataStore() {
        return (DataStore) super.getSource();
    }

    public GenericName getType() {
        return type;
    }
}
