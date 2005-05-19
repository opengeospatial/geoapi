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
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.GenericName;


/**
 * The query object is used by the {@link FeatureStore#getFeatures(Query) getFeatures} method
 * of the {@linkplain FeatureStore data store} interface, to encapsulate a request.  It defines
 * which {@linkplain FeatureType feature type} to query, what properties to retrieve and what
 * constraints (spatial and non-spatial) to apply to those properties.  It is designed to
 * closesly match a WFS Query element of a {@code getFeatures<} request.  The only difference
 * is the addition of the {@code maxFeatures} element, which allows more control over the
 * {@linkplain Feature features} selected.  It allows a full {@code getFeatures} request to
 * properly control how many features it gets from each query, instead of requesting and
 * discarding when the max is reached.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @since GeoAPI 1.1
 */
public interface Query {
    /**
     * Returns the name of the type that is to be queried.
     */
    GenericName getTypeName();

    /**
     * Returns the filter that will limit which {@linkplain Feature features} are returned.
     */
    Filter getFilter();

    /**
     * Returns the list of property names of the {@linkplain Feature features}
     * to be retrieved. This may be null if all properties are to be retrieved.
     */
    List<String> getPropertyNames();

    /**
     * Gives a mnemonic name for use by the client to identify this query.
     */
    String getHandle();

    /**
     * Returns the maximum number of features to be retrieved by the query.
     * Can return zero.  Can also return {@link Integer#MAX_VALUE} to indicate
     * that the maximum number of features is unlimited.
     */
    int getMaxFeatures();

    /**
     * Returns the feature version to query. The version attribute is included in order to
     * accommodate systems that support feature versioning. A value of {@code "ALL"} indicates
     * that all versions of a feature should be fetched. Otherwise an integer, <var>n</var>,
     * can be specified to return the <var>n</var><sup>th</sup> version of a {@linkplain Feature
     * feature}. The version numbers start at '1' which is the oldest version. If a version value
     * larger than the largest version is specified then the latest version is return. The default
     * action shall be for the query to return the latest version. Systems that do not support
     * versioning can ignore the parameter and return the only version that they have.
     * 
     * @return the version of the feature to return, or null for latest.
     */
    String getVersion();

    /**
     * Temporarily override the coordinate reference system.
     * This denotes a request to temporarily override the coordinate reference
     * system contained in the {@link FeatureStore} being queried. The same
     * coordinate values will be used, but the features created will appear in this CRS.
     * <p>
     * This change is not persistant at all, indeed it is only for the {@link Feature}s
     * returned by this {@code Query}.  It may be used in conjunction with the reprojection
     * CRS, but this one will always be used first. The reprojection CRS will perform its
     * operation on this crs.
     *
     * @return The coordinate reference system to be returned for {@link Feature}s from this
     *         {@code Query}.
     *
     * @revisit JG: Please if you possibly can treat the idea of "forcing" the CRS as a
     *          {@link FeatureCollection} configuration issue (or a {@link FeatureStore}
     *          configuration issue). Recent experience with Geotools shows the added burden
     *          on client code to be a pain.
     */
    CoordinateReferenceSystem getCoordinateSystem();

    /**
     * Request data reprojection. Gets the coordinate reference system to reproject the
     * data contained in the backend {@linkplain FeatureStore feature store} to.
     * If the feature store can optimize the reprojection it should. If not then
     * a decorator on the reader should perform the reprojection on the fly.
     *
     * @return The coordinate reference system that {@linkplain Feature features} from the
     *         {@linkplain FeatureStore feature store} should be reprojected to.
     */
    CoordinateReferenceSystem getCoordinateSystemReproject();
}
