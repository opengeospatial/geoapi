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

import java.io.IOException;

import org.opengis.feature.FeatureType;
import org.opengis.spatialschema.geometry.Envelope;


/**
 * Highlevel API for Features from a specific Query.
 * 
 * <p>
 * The can opperate as as a kind of Prepaired Query. It is a Query that knows
 * enough information to be rerun. We may wish to rename this class as
 * QueryResults.
 * </p>
 * 
 * <p>
 * Differences from FeatureCollection:
 * </p>
 * 
 * <ul>
 * <li>
 * This API opperates as a source of FeatureReaders (rather than a source of
 * iterators)
 * </li>
 * <li>
 * As a Prepaired Query thise class allows other "results" to be asked against
 * the same query. getBounds() is the most logical example, but we may be able
 * to think of others. In Candidate "results" should all be reproduceable by
 * streaming over the generated FeatureReader.
 * </li>
 * </ul>
 * 
 * <p>
 * Ideas:
 * </p>
 * 
 * <ul>
 * <li>
 * Chris had the idea of a collection() method that would construct a
 * FeatureCollection (by iterating through the FeartureReader once). This
 * would be a nice transition piece to allow us to test against existing
 * renderers. (we could add the method in deprecated form until the Renderers
 * adapt to the new API).
 * </li>
 * <li>
 * Ian had the idea of asynchronous error handling as provided by a High-Level
 * version of FeatureReader. This class might be the place for such work?
 * Something similar to jdbc getWarnings()?
 * </li>
 * <li>
 * The idea of forcing a CoordinateSystem is also looking for a home, this may
 * be the place for such work (although I recomend proving a a "hint" in the
 * Query interface).
 * </li>
 * </ul>
 * 
 *
 * @author Jody Garnett
 * @author Ray Gallagher
 * @author Rob Hranac, TOPP
 * @author Chris Holmes, TOPP
 * @version $Id$
 */
public interface FeatureResults {
    /**
     * Returns the FeatureType of this FeatureResuls.
     *
     * @return A FeatureType for
     *
     * @throws IOException if their is a problem getting the FeatureType.
     */
    FeatureType getSchema() throws IOException;

    /**
     * Provides access to the Features, please note that FeatureReader is a
     * blocking api.
     *
     * @return A FeatureReader streaming over the FeatureResults
     */
    FeatureReader reader() throws IOException;

    /**
     * Returns the bounding box of this FeatureResults.
     * 
     * <p>
     * This opperation may be expensive. Consider
     * <code>FeatureSource.getBounds( Query )</code> as an alternative.
     * </p>
     * This method is logically the same as:
     * <pre>
     * <code>
     * <b>public</b> Envelope getBounds() <b>throws</b> IOException {
     *     Envelope newBBox = <b>new</b> Envelope();
     *     Envelope internal;
     *     Feature feature;
     * 
     *     <b>for</b> (FeatureReader r = reader(); r.hasNext();) {
     *         feature = r.next();
     *         internal = feature.getDefaultGeometry().getEnvelopeInternal();
     *         newBBox.expandToInclude(internal);
     *     }
     *     <b>return</b> newBBox;  
     * }
     * </code>
     * </pre>
     *
     * @return Bounding box of this FeatureResults
     */
    Envelope getBounds() throws IOException;

    /**
     * Returns the number of Features in this FeatureResults.
     * 
     * <p>
     * This opperation may be expensive. Consider <code>FeatureSource.getCount(
     * Query )</code> as an alternative.
     * </p>
     * This method is logically the same as:
     * <pre>
     * <code>
     * <b>public</b> int getCount() <b>throws</b> IOException {
     *     <b>int</b> count = 0;
     *     <b>for</b> (FeatureReader r = reader(); r.hasNext(); count++) {
     *         r.next();
     *     }
     *     <b>return</b> count;  
     * }
     * </code>
     * </pre>
     *
     * @return The number of Features in this FeatureResults.
     *
     * @throws IOException If there are problems getting the count
     */
    int getCount() throws IOException;
}
