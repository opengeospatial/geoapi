/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

// J2SE direct dependencies
import java.util.EventObject;

// OpenGIS direct dependencies
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;


/**
 * Instances of this class are passed to the methods of {@link FeatureStoreListener} when
 * the user of the {@link FeatureStore} adds, deletes, or modifies a feature type.  Events
 * regarding changes to an individual feature are given with an instance of {@code FeatureEvent}.
 *
 * @since GeoAPI 2.0
 */
public class FeatureStoreEvent extends EventObject {
    /**
     * Value that must be changed should this class ever become serialization
     * incompatible with previous versions.
     */
    private static final long serialVersionUID = -1842329357109520014L;

    /**
     * Constant that indicates that a new feature type was added to a
     * {@linkplain FeatureStore feature store}.
     */
    public static final int ADD = 1;

    /**
     * Constant that indicates that a feature type's schema was modified in a
     * {@linkplain FeatureStore feature store}.
     */
    public static final int MODIFY = 0;

    /**
     * Constant that indicates that a feature type's schema was removed from a
     * {@linkplain FeatureStore feature store}.
     */
    public static final int DELETE = -1;

    /**
     * The name of the type that was added, modified, or deleted.
     */
    private final GenericName typeName;

    /**
     * The type of this event. One of {@link #ADD}, {@link #MODIFY} or {@link #DELETE} constants.
     */
    private final int eventType;

    /**
     * Constructs a new event.
     *
     * @param featureStore The feature store on which the event initially occurred.
     * @param typeName The name of the type that was added, modified, or deleted.
     * @param eventType The type of this event. One of {@link #ADD}, {@link #MODIFY}
     *        or {@link #DELETE} constants.
     */
    public FeatureStoreEvent(final FeatureStore featureStore,
                             final GenericName  typeName,
                             final int          eventType)
    {
        super(featureStore);
        this.typeName  = typeName;
        this.eventType = eventType;
    }

    /**
     * Returns the source as a feature store.
     */
    public /*{FeatureStore}*/ Object getSource() {
        return (FeatureStore) super.getSource();
    }

    /**
     * Synonym for {@link #getSource}, but does the cast for you.
     *
     * @todo To be replaced by {@link #getSource} in a J2SE 1.5 profile.
     */
    public FeatureStore getFeatureStore() {
        return (FeatureStore) super.getSource();
    }

    /**
     * Returns the name of the {@link FeatureType} that was modified.
     */
    public GenericName getTypeName() {
        return typeName;
    }

    /**
     * Returns the type of this event.
     * One of {@link #ADD}, {@link #MODIFY} or {@link #DELETE} constants.
     */
    public int getEventType() {
        return eventType;
    }

    /**
     * Returns a string representation of this event. Of the form
     * {@code "FeatureStore (featureStore.displayName|featureStore) (ADD|MODIFY|DELETE) Event"}.
     */
    public String toString() {
        final StringBuffer buffer = new StringBuffer("FeatureStore ");
        final FeatureStore featureStore = getFeatureStore();
        if (featureStore != null) {
            final InternationalString name = featureStore.getDisplayName();
            buffer.append(name!=null ? name.toString() : featureStore.toString());
        }
        buffer.append(' ');
        switch (eventType) {
            case ADD:    buffer.append("ADD");    break;
            case MODIFY: buffer.append("MODIFY"); break;
            case DELETE: buffer.append("DELETE"); break;
        }
        buffer.append(" Event");
        return buffer.toString();
    }
}
