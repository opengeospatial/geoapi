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
import org.opengis.filter.Filter;
import org.opengis.spatialschema.geometry.Envelope;


/**
 * Highlevel API for Features from a specific location.
 * 
 * <p>
 * Individual Shapefiles, databases tables , etc. are referenced through this
 * interface. Compare and constrast with DataStore.
 * </p>
 * 
 * <p>
 * Differences from DataStore:
 * </p>
 * 
 * <ul>
 * <li>
 * This is a prototype DataSource replacement, the Transaction methods have
 * been moved to an external object, and the locking api has been intergrated.
 * </li>
 * <li>
 * FeatureCollection has been replaced with FeatureResult as we do not wish to
 * indicate that results can be stored in memory.
 * </li>
 * <li>
 * FeatureSource has been split into three interfaces, the intention is to use
 * the instanceof opperator to check capabilities rather than the previous
 * DataSourceMetaData.
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
public interface FeatureSource {
    /**
     * Access to the DataStore implementing this FeatureStore.
     * 
     * <p>
     * You may use this to access such as <code>namespace</code> provided by
     * DataStore.
     * </p>
     *
     * @return DataStore implementing this FeatureStore
     */
    DataStore getDataStore();

    /**
     * Adds a listener to the list that's notified each time a change to the
     * FeatureStore occurs.
     *
     * @param listener FeatureListener
     */
    void addFeatureListener(FeatureListener listener);

    /**
     * Removes a listener from the list that's notified each time a change to
     * the FeatureStore occurs.
     *
     * @param listener FeatureListener
     */
    void removeFeatureListener(FeatureListener listener);

    /**
     * Loads features from the datasource into the returned FeatureResults,
     * based on the passed query.
     *
     * @param query a datasource query object.  It encapsulates requested
     *        information, such as typeName, maxFeatures and filter.
     *
     * @return Collection The collection to put the features into.
     *
     * @throws DataSourceException For all data source errors.
     *
     * @see Query
     */
    FeatureResults getFeatures(Query query) throws IOException;

    /**
     * Loads features from the datasource into the returned FeatureResults,
     * based on the passed filter.
     *
     * @param filter An OpenGIS filter; specifies which features to retrieve.
     *        <tt>null</tt> is not allowed, use Filter.NONE instead.
     *
     * @return Collection The collection to put the features into.
     *
     * @throws DataSourceException For all data source errors.
     */
    FeatureResults getFeatures(Filter filter) throws IOException;

    /**
     * Loads all features from the datasource into the return FeatureResults.
     * 
     * <p>
     * Filter.NONE can also be used to get all features.  Calling this function
     * is equivalent to using {@link Query#ALL}
     * </p>
     *
     * @return Collection The collection to put the features into.
     *
     * @throws DataSourceException For all data source errors.
     */
    FeatureResults getFeatures() throws IOException;

    /**
     * Retrieves the featureType that features extracted from this datasource
     * will be created with.
     *
     * @return the schema of features created by this datasource.
     *
     * @revisit Our current FeatureType model is not yet advanced enough
     *       to handle multiple featureTypes.  Should getSchema take a
     *       typeName now that a query takes a typeName, and thus DataSources
     *       can now support multiple types? Or just wait until we can
     *       programmatically make powerful enough schemas?
     * @revisit we could also just use DataStore to capture multi
     *       FeatureTypes?
     */
    FeatureType getSchema();

    /**
     * Gets the bounding box of this datasource.
     * 
     * <p>
     * With getBounds(Query) this becomes a convenience method for
     * getBounds(Query.ALL), that is the bounds for all features contained
     * here.
     * </p>
     * 
     * <p>
     * If getBounds() returns <code>null</code> due to expense consider using
     * <code>getFeatures().getBounds()</code> as a an alternative.
     * </p>
     *
     * @return The bounding box of the datasource or null if unknown and too
     *         expensive for the method to calculate.
     *
     * @throws IOException if there are errors getting the bounding box.
     *
     * @revisit Do we need this or can we use getBounds( Query.ALL )?
     */
    Envelope getBounds() throws IOException;

    /**
     * Gets the bounding box of the features that would be returned by this
     * query.
     * 
     * <p>
     * To retrieve the bounds of the DataSource please use <code>getBounds(
     * Query.ALL )</code>.
     * </p>
     * 
     * <p>
     * This method is needed if we are to stream features to a gml out, since a
     * FeatureCollection must have a boundedBy element.
     * </p>
     * 
     * <p>
     * If getBounds(Query) returns <code>null</code> due to expense consider
     * using <code>getFeatures(Query).getBounds()</code> as a an alternative.
     * </p>
     *
     * @param query Contains the Filter and MaxFeatures to find the bounds for.
     *
     * @return The bounding box of the datasource or null if unknown and too
     *         expensive for the method to calculate or any errors occur.
     */
    Envelope getBounds(Query query) throws IOException;

    /**
     * Gets the number of the features that would be returned by this query.
     * 
     * <p></p>
     * 
     * <p>
     * If getBounds(Query) returns <code>-1</code> due to expense consider
     * using <code>getFeatures(Query).getCount()</code> as a an alternative.
     * </p>
     *
     * @param query Contains the Filter and MaxFeatures to find the bounds for.
     *
     * @return The number of Features provided by the Query or <code>-1</code>
     *         if count is too expensive to calculate or any errors or occur.
     *
     * @throws IOException if there are errors getting the count
     */
    int getCount(Query query) throws IOException;
}
