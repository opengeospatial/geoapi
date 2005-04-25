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
import java.util.List;

/**
 * Instances of this class are passed to the methods of FeatureListener when a
 * new feature is added to, removed from, or changed within a given feature
 * collection.
 */
public class FeatureEvent extends EventObject {
    // IDs of the features that were affected
    private List<String> fids;

    public FeatureEvent(FeatureCollection source, List<String> fids) {
        super(source);
        this.fids = fids;
    }

    public List<String> getFeatureIDs() {
        return fids;
    }

    // Synonym for EventObject.getSource(), but does the cast for you
    public FeatureCollection getFeatureCollection() {
        return (FeatureCollection) getSource();
    }
}
