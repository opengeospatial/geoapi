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

import java.net.URI;

import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Encapsulates a data request.
 * 
 * <p>
 * The query object is used by the FeatureSource.getFeatures(Query) to
 * encapsulate a request. For this use it the
 * FeatureSource.getSchema().getTypeName() should match the one provided by
 * the Query, or the Query should not provide one.
 * </p>
 * 
 * <p>
 * Suggested Extensions (Jody):
 * </p>
 * 
 * <ul>
 * <li>
 * Transient CoordianteSystem override
 * done getCoordianteSystem()
 * </li>
 * <li>
 * Transient Geometry reproject to an alternate CoordinateSystem
 * - done getCoordinateSystemReproject()
 * </li>
 * <li>
 * Consider Namespace, FeatueType name override
 * - not done considered evil
 * </li>
 * <li>
 * DataStore.getFeatureReader( Query, Transaction )
 * </li>
 * <li>
 * DataStore.getView( Query )
 * - prototype in AbstractDataStore (not really ready for primetime, see Expr)
 * </li>
 * </ul>
 * 
 *
 * @author Chris Holmes
 * @version $Id$
 */
public interface Query {
    /**
     * The properties array is used to specify the attributes that should be
     * selected for the return feature collection.
     * <p>
     * The available properties can be determined with a getSchema call from
     * the DataSource interface.  A datasource can use {@link
     * #retrieveAllProperties()} as a shortcut to determine if all its
     * available properties should be returned (same as checking to see if
     * getProperties is ALL_NAMES, but clearer)
     * </p>
     * 
     * <p>
     * If properties that are not part of the datasource's schema are requested
     * then the datasource shall throw an exception.
     * </p>
     * 
     * <p>
     * This replaces our funky setSchema method of retrieving select
     * properties.  It makes it easier to understand how to get certain
     * properties out of the datasource, instead of having users get the
     * schema and then compose a new schema using the attributes that they
     * want.  The old way had problems because one couldn't have multiple
     * object reuse the same datasource object, since some other object could
     * come along and change its schema, and would then return the wrong
     * properties.
     * </p>
     *
     * @return the attributes to be used in the returned FeatureCollection.
     *
     * @revisit make a FidProperties object, instead of an array size 0.
     *       I think Query.FIDS fills this role to some degree.
     *       Query.FIDS.equals( filter ) would meet this need?
     */
    String[] getPropertyNames();

    /**
     * Convenience method to determine if the query should use the full schema
     * (all properties) of the data source for the features returned.  This
     * method is equivalent to if (query.getProperties() == null), but allows
     * for more clarity on the part of datasource implementors, so they do not
     * need to examine and use null values.  All Query implementations should
     * return true for this function if getProperties returns null.
     *
     * @return if all datasource attributes should be included in the schema of
     *         the returned FeatureCollection.
     */
    boolean retrieveAllProperties();

    /**
     * The optional maxFeatures can be used to limit the number of features
     * that a query request retrieves.  If no maxFeatures is specified then
     * all features should be returned.
     * 
     * <p>
     * This is the only method that is not directly out of the Query element in
     * the WFS spec.  It is instead a part of a GetFeature request, which can
     * hold one or more queries.  But each of those in turn will need a
     * maxFeatures, so it is needed here.
     * </p>
     *
     * @return the max features the getFeature call should return.
     */
    int getMaxFeatures();

    /**
     * The Filter can be used to define constraints on a query.  If no Filter
     * is present then the query is unconstrained and all feature instances
     * should be retrieved.
     *
     * @return The filter that defines constraints on the query.
     */
    Filter getFilter();

    /**
     * The typeName attribute is used to indicate the name of the feature type
     * to be queried.  If no typename is specified, then the default typeName
     * should be returned from the dataStore.  If the datasstore only
     * supports one feature type then this part of the query may be ignored.
     * 
     * @return the name of the feature type to be returned with this query.
     */
    String getTypeName();

    /**
     * The namespace attribute is used to indicate the namespace of the
     * schema being represented. 
     * 
     * @return the gml namespace of the feature type to be returned with this query
     */
    URI getNamespace();

    /**
     * The handle attribute is included to allow a client to associate  a
     * mnemonic name to the Query request. The purpose of the handle attribute
     * is to provide an error handling mechanism for locating  a statement
     * that might fail.
     *
     * @return the mnemonic name of the query request.
     */
    String getHandle();

    /**
     * From WFS Spec:  The version attribute is included in order to
     * accommodate systems that  support feature versioning. A value of ALL
     * indicates that all versions of a feature should be fetched. Otherwise
     * an integer, n, can be specified  to return the n th version of a
     * feature. The version numbers start at '1'  which is the oldest version.
     * If a version value larger than the largest version is specified then
     * the latest version is return. The default action shall be for the query
     * to return the latest version. Systems that do not support versioning
     * can ignore the parameter and return the only version  that they have.
     * 
     * @return the version of the feature to return, or null for latest.
     */
    String getVersion();

    /**
     * Temporarily override the coordinate system.
     * <p>
     * This denotes a request to Temporarily to override the coordinate
     * system contained in the FeatureSource being queried. The same coordinate
     * values will be used, but the features created will appear in this
     * Coordinate System.
     * </p>
     * <p>
     * This change is not persistant at all, indeed it is only for the Features
     * returned by this Query.  It may be used in conjunction with the
     * reprojection Coordinate System, but this one will always be used first,
     * the reprojection CS will perform its operation on this cs.
     * </p>
     *
     * @return The coordinate system to be returned for Features from this
     *         Query (override the set coordinate system).
     */
    CoordinateReferenceSystem getCoordinateSystem();

    /**
     * Request data reprojection.
     * <p>
     * Gets the coordinate System to reproject the data contained in the
     * backend datastore to.
     * </p>
     * <p>
     * If the DataStore can optimize the reprojection it should, if not then a
     * decorator on the reader should perform the reprojection on the fly.
     * </p>
     * <p>
     * If the datastore has the wrong CS then getOverrideCS() should be set to
     * the CS to be used, this will perform
     * the reprojection on that.
     *
     * @return The coordinate system that Features from the datasource should
     *         be reprojected to.
     */
    CoordinateReferenceSystem getCoordinateSystemReproject();
}
