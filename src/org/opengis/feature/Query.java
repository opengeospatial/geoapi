package org.opengis.feature;

import java.util.List;

import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface Query {
    /**
     * Returns the name of the type that is to be queried.
     */
    public QName getTypeName();

    /**
     * Returns the Filter that will limit which features are returned.
     */
    public Filter getFilter();

    /**
     * Returns the list of property names of the features to be retrieved.
     * This may be null if all properties are to be retrieved.
     */
    public List/*<String>*/ getPropertyNames();

    /**
     * Gives a mnemonic name for use by the client to identify this query.
     */
    public String getHandle();

    /**
     * Returns the maximum number of features to be retrieved by the query.
     * Can return zero.  Can also return -1 or Integer.MAX_VALUE to indicate
     * that the maximum number of features is unlimited.
     */
    public int getMaxFeatures();

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
    public String getVersion();

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
    public CoordinateReferenceSystem getCoordinateSystem();

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
    public CoordinateReferenceSystem getCoordinateSystemReproject();
}
