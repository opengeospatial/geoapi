/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */
package org.opengis.data;

import java.util.EventObject;

import org.opengis.spatialschema.geometry.Envelope;


/**
 * A simple event object to represent all events triggered by DataStore
 * instances (typically change events).
 * 
 * <p>
 * The "Source" FeatureEvents is taken to be a <code>FeatureSource</code>,
 * rather than <code>DataStore</code>. The is due to FeatureSource having a a
 * hold of Transaction information.
 * </p>
 * 
 * <p>
 * DataStore implementations will actually keep the list listeners by
 * DataSource, and can report FeatureWriter modifications as required (by
 * filtering the Listener list by typeName and Transaction).
 * </p>
 * 
 * <p>
 * The commit opperation will also need to provide notification.
 * </p>
 *
 * @author Jody Garnett
 * @author Ray Gallagher
 * @version $Id$
 */
public class FeatureEvent extends EventObject {
    private static final long serialVersionUID = 1L;

    /**
     * Event type constant denoting the adding of a feature.
     * 
     * <p>
     * This EventType is used when FeatureWriter.write() is called when
     * <code>FeatureWriter.hasNext()</code> has previously returned
     * <code>false</code>. This action represents a newly create Feature being
     * passed to the DataStore.
     * </p>
     * 
     * <p>
     * The FeatureWriter making the modification will need to check that
     * <code>typeName</code> it is modifing matches the
     * <code>FeatureSource.getSchema().getTypeName()</code> before sending
     * notification to any listeners on the FeatureSource.
     * </p>
     * 
     * <p>
     * If the FeatureWriter is opperating against a Transaction it will need
     * ensure that to check the FeatureSource.getTransaction() for a match
     * before sending notification to any listeners on the FeatureSource.
     * </p>
     * 
     * <p>
     * FeatureEvent.getBounds() should reflect the the Bounding Box of the
     * newly created Features.
     * </p>
     */
    public static final int FEATURES_ADDED = 1;

    /**
     * Event type constant denoting that features in the collection has been
     * modified.
     * 
     * <p>
     * This EventType is used when a FeatureWriter.write() is called when
     * <code>FeatureWriter.hasNext()</code> returns <code>true</code> and the
     * current Feature has been changed. This EventType is also used when a
     * Transaction <code>commit()</code> or <code>rolledback</code> is called.
     * </p>
     * 
     * <p>
     * The FeatureWriter making the modification will need to check that
     * <code>typeName</code> it is modifing matches the
     * <code>FeatureSource.getSchema().getTypeName()</code> before sending
     * notification to any listeners on the FeatureSource.
     * </p>
     * 
     * <p>
     * If the FeatureWriter is opperating against a Transaction it will need
     * ensure that to check the FeatureSource.getTransaction() for a match
     * before sending notification to any listeners on the FeatureSource. All
     * FeatureSources of the same typename will need to be informed of a
     * <code>commit</code>, except ones in the same Transaction,  and only
     * FeatureSources in the same Transaction will need to be informed of a
     * rollback.
     * </p>
     * 
     * <p>
     * FeatureEvent.getBounds() should reflect the the BoundingBox of the
     * FeatureWriter modified Features. This may not be possible during a
     * <code>commit()</code> or <code>rollback()</code> opperation.
     * </p>
     */
    public static final int FEATURES_CHANGED = 0;

    /**
     * Event type constant denoting the removal of a feature.
     * 
     * <p>
     * This EventType is used when FeatureWriter.remove() is called. This
     * action represents a Feature being removed from the DataStore.
     * </p>
     * 
     * <p>
     * The FeatureWriter making the modification will need to check that
     * <code>typeName</code> it is modifing matches the
     * <code>FeatureSource.getSchema().getTypeName()</code> before sending
     * notification to any listeners on the FeatureSource.
     * </p>
     * 
     * <p>
     * If the FeatureWriter is opperating against a Transaction it will need
     * ensure that to check the FeatureSource.getTransaction() for a match
     * before sending notification to any listeners on the FeatureSource.
     * </p>
     * 
     * <p>
     * FeatureEvent.getBounds() should reflect the the Bounding Box of the
     * removed Features.
     * </p>
     */
    public static final int FEATURES_REMOVED = -1;

    /** Indicates one of FEATURES_ADDED, FEATURES_REMOVED, FEATURES_CHANGED */
    private int type;

    /**
     * Indicates the bounds in which the modification occured.
     * 
     * <p>
     * This value is allowed to by <code>null</code> if this information is not
     * known.
     * </p>
     */
    private Envelope bounds;

    /**
     * Constructs a new FeatureEvent.
     *
     * @param featureSource The DataStore that fired the event
     * @param eventType One of FEATURE_CHANGED, FEATURE_REMOVED or
     *        FEATURE_ADDED
     * @param bounds The area modified by this change
     */
    public FeatureEvent(FeatureSource featureSource, int eventType,
        Envelope bounds) {
        super(featureSource);
        this.type = eventType;
        this.bounds = bounds;
    }

    /**
     * Provides access to the FeatureSource which fired the event.
     *
     * @return The FeatureSource which was the event's source.
     */
    public FeatureSource getFeatureSource() {
        return (FeatureSource) source;
    }

    /**
     * Provides information on the type of change that has occured. Possible
     * types are: add, remove, change
     *
     * @return an int which must be one of FEATURES_ADDED, FEATURES_REMOVED,
     *         FEATURES_CHANGED
     */
    public int getEventType() {
        return type;
    }

    /**
     * Provides access to the area modified (if known).
     *
     * @return A bounding box of the modifications or <code>null</code> if
     *         unknown.
     */
    public Envelope getBounds() {
        return bounds;
    }
}
