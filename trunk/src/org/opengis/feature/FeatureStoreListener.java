/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

import java.util.EventListener;

/**
 * Interface whose methods are invoked when types are modified in a FeatureStore.
 * To use this interface, implement its methods and invoke the
 * addFeatureStoreListener() method on FeatureStore.
 */
public interface FeatureStoreListener extends EventListener {
    /**
     * Invoked when a new FeatureType has been created.
     */
    public void typeAdded(FeatureStoreEvent dse);

    /**
     * Invoked when a FeatureType has been removed from a FeatureStore.
     */
    public void typeRemoved(FeatureStoreEvent dse);

    /**
     * Invoked when the schema for a type has been changed.
     */
    public void typeModified(FeatureStoreEvent dse);
}
