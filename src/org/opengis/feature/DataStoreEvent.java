package org.opengis.feature;

import java.util.EventObject;


/**
 * Instances of this class are passed to the methods of DataStoreListener when
 * the user of the DataStore adds, deletes, or modifies a feature type.
 */
public class DataStoreEvent extends EventObject {
    private QName type;

    public DataStoreEvent(DataStore dataStore, QName type) {
        super(dataStore);
        this.type = type;
    }

    public DataStore getDataStore() {
        return (DataStore) super.getSource();
    }

    public QName getType() {
        return type;
    }
}
