package org.opengis.feature;

/**
 * Interface whose methods are invoked when types are modified in a DataStore.
 * To use this interface, implement its methods and invoke the
 * addDataStoreListener() method on DataStore.
 */
public interface DataStoreListener {
    /**
     * Invoked when a new FeatureType has been created.
     */
    public void typeAdded(DataStoreEvent dse);

    /**
     * Invoked when a FeatureType has been removed from a DataStore.
     */
    public void typeRemoved(DataStoreEvent dse);

    /**
     * Invoked when the schema for a type has been changed.
     */
    public void typeModified(DataStoreEvent dse);
}
