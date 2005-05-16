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

import java.util.EventObject;

import org.opengis.util.GenericName;

/**
 * <p>
 * Instances of this class are passed to the methods of FeatureStoreListener when
 * the user of the FeatureStore adds, deletes, or modifies a feature type.  Events
 * regarding changes to an individual feature are given with an instance of
 * <code>FeatureEvent</code>.
 * </p>
 */
public class FeatureStoreEvent extends EventObject {
    /**
     * Value that must be changed should this class ever become serialization
     * incompatible with previous versions.
     */
    private static final long serialVersionUID = -1842329357109520014L;

    /**
     * Constant that indicates that a new feature type was added to a
     * FeatureStore.
     */
    public static final int ADD = 1;

    /**
     * Constant that indicates that a feature type's schema was modified in a
     * FeatureStore.
     */
    public static final int MODIFY = 0;

    /**
     * Constant that indicates that a feature type's schema was modified in a
     * FeatureStore.
     */
    public static final int DELETE = -1;

    /**
     * The name of the type that was added, modified, or deleted.
     */
    private GenericName typeName;

    /**
     * The type of this event.
     */
    private int eventType;

    public FeatureStoreEvent(FeatureStore featureStore, GenericName typeName, int eventType) {
        super(featureStore);
        this.typeName = typeName;
        this.eventType = eventType;
    }

    public FeatureStore getFeatureStore() {
        return (FeatureStore) super.getSource();
    }

    // consider rename to getGenericName?
    /**
     * Returns the name of the FeatureType that was modified.
     */
    public GenericName getTypeName() {
        return typeName;
    }

    /** One of ADD, MODIFY, DELETE indicating event type */
    public int getEventType() {
        return eventType;
    }

    /**
     * Of the form "FeatureStore (featureStore.displayName|featureStore) (ADD|MODIFY|DELETE) Event"
     */
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("FeatureStore ");
        FeatureStore featureStore = getFeatureStore();
        if (featureStore != null) {
            buf.append( featureStore.getDisplayName() != null ? featureStore.getDisplayName().toString() : featureStore.toString() );
        }
        switch (eventType) {
            case ADD: 
                buf.append(" ADD"); // I want my Java 5 enum class :-)
            case MODIFY: 
                buf.append(" MODIFY"); 
            case DELETE: 
                buf.append(" DELETE");
        }
        buf.append(" Event");

        return buf.toString();
    }
}
