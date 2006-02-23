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

// J2SE direct dependencies
import java.util.EventListener;


/**
 * Interface whose methods are invoked when types are modified in a {@link FeatureStore}.
 * To use this interface, implement its methods and invoke the
 * {@link FeatureStore#addFeatureStoreListener addFeatureStoreListener} method on feature store.
 *
 * @since GeoAPI 2.0
 */
public interface FeatureStoreListener extends EventListener {
    /**
     * Invoked when a new {@link FeatureType} has been created.
     */
    void typeAdded(FeatureStoreEvent dse);

    /**
     * Invoked when a {@link FeatureType} has been removed from a {@link FeatureStore}.
     */
    void typeRemoved(FeatureStoreEvent dse);

    /**
     * Invoked when the schema for a type has been changed.
     */
    void typeModified(FeatureStoreEvent dse);
}
