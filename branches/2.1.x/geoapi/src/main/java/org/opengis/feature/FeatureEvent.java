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

// J2SE dependencies
import java.util.EventObject;
import java.util.List;


/**
 * Instances of this class are passed to the methods of {@link FeatureListener} when a
 * new feature is added to, removed from, or changed within a given feature collection.
 *
 * @since GeoAPI 2.0
 * 
 * @deprecated Removed pending a redesign of feature storage interfaces
 */
 @Deprecated
public class FeatureEvent extends EventObject {
    /**
     * Serial version for compatibility with different versions.
     */
    private static final long serialVersionUID = -8757455878914840213L;

    /**
     * IDs of the features that were affected.
     */
    private final List<String> fids;

    /**
     * Constructs a new event.
     *
     * @param source The source for this event.
     * @param fids IDs of the features that were affected.
     */
    public FeatureEvent(final FeatureCollection source, final List<String> fids) {
        super(source);
        this.fids = fids;
    }

    /**
     * Returns the IDs of the features that were affected.
     */
    public List<String> getFeatureIDs() {
        return fids;
    }

    /**
     * Returns the source as a feature collection.
     */
    public /*{FeatureCollection}*/ Object getSource() {
        return (FeatureCollection) super.getSource();
    }

    /**
     * Synonym for {@link #getSource}, but does the cast for you.
     *
     * @todo To be replaced by {@link #getSource} in a J2SE 1.5 profile.
     */
    public FeatureCollection getFeatureCollection() {
        return (FeatureCollection) super.getSource();
    }
}
