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

import java.util.EventListener;

/**
 * Interface whose methods are invoked when types are modified in a DataStore.
 * To use this interface, implement its methods and invoke the
 * addDataStoreListener() method on DataStore.
 */
public interface DataStoreListener extends EventListener {
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
