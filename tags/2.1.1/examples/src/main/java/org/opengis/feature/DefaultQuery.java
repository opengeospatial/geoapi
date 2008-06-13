/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

import java.util.Arrays;
import java.util.List;

import org.opengis.feature.type.Name;
import org.opengis.feature.type.TypeName;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * The query object is used by the {@link DataStore#getFeatures(Query)} method
 * of the DataStore interface, to encapsulate a request.  It defines which
 * feature type to query, what properties to retrieve and what constraints
 * (spatial and non-spatial) to apply to those properties.  It is designed to
 * closesly match a WFS Query element of a <code>getFeatures</code> request.
 * The only difference is the addition of the maxFeatures element, which
 * allows more control over the features selected.  It allows a full
 * <code>getFeatures</code> request to properly control how many features it
 * gets from each query, instead of requesting and discarding when the max is
 * reached.
 *
 * @author Chris Holmes
 */
@Deprecated
public class DefaultQuery implements Query {
    /** The properties to fetch */
    private List/*<String>*/ properties;

    /** The maximum numbers of features to fetch */
    private int maxFeatures;

    /** The filter to constrain the request. */
    private Filter filter;

    /** The Name to get */
    private Name Name;

    /** The handle associated with this query. */
    private String handle;

    /** Coordinate System associated with this query */
    private CoordinateReferenceSystem coordinateSystem;

    /** Reprojection associated associated with this query */
    private CoordinateReferenceSystem coordinateSystemReproject;

    private String version;

    /**
     * No argument constructor.
     */
    public DefaultQuery() {
        this( (Name) null);
    }

    /**
     * Query with Name.
     *
     * @param Name the name of the featureType to retrieve
     */
    public DefaultQuery(Name Name){
        this(Name, null);
    }

    /**
     * Constructor that sets only the filter.
     * <p>
     * Using a Query with only a filter and no Name almost always results in
     * an error.
     * <p>
     * Please use DefaultQuery( Name, filter )
     * </p>
     * @param filter the OGC filter to constrain the request.
     * @deprecated Please use DefaultQuery( Name, filter ) instead.
     */
    public DefaultQuery(Filter filter) {
        this(null, filter);
    }

    /**
     * Constructor with Name and filter.  Note that current datasource
     * implementations only have one type per datasource, so the Name
     * field will likely be ignored.
     *
     * @param Name the name of the featureType to retrieve.
     * @param filter the OGC filter to constrain the request.
     */
    public DefaultQuery(Name Name, Filter filter) {
        this(Name, filter, (List) null);
    }

    /**
     * Constructor that sets the filter and properties
     *
     * @param filter the OGC filter to constrain the request.
     * @param properties an array of the properties to fetch.
     *
     * @deprecated Use a constructor with the Name, as datastores depend on
     *             it.
     */
    public DefaultQuery(Filter filter, String[] properties) {
        this(null, filter, properties);
    }

    public DefaultQuery(Name Name, Filter filter, String [] properties) {
        this(Name, filter, properties==null ? (List) null : Arrays.asList(properties), -1, null);
    }

    public DefaultQuery(Name Name, Filter filter, List properties) {
        this(Name, filter, properties, -1, null);
    }

    /**
     * Constructor that sets all fields.
     *
     * @param Name the name of the featureType to retrieve.
     * @param filter the OGC filter to constrain the request.
     * @param maxFeatures the maximum number of features to be returned.
     * @param propNames an array of the properties to fetch.
     * @param handle the name to associate with the query.
     */
    public DefaultQuery(Name Name, Filter filter,
            List/*<String>*/ propNames, int maxFeatures, String handle) {
        this.Name = Name;
        this.filter = filter;
        this.maxFeatures = maxFeatures;
        this.properties = propNames;
        this.handle = handle;
    }

    /**
     * Copy contructor, clones the state of a generic Query into a DefaultQuery
     *
     * @param query
     */
    public DefaultQuery(Query query) {
      this(query.getTypeName(), query.getFilter(), query.getPropertyNames(),
              query.getMaxFeatures(), query.getHandle());
    }

    /**
     * The property names is used to specify the attributes that should be
     * selected for the return feature collection.  If the property array is
     * null, then the datasource should return all available properties, its
     * full schema.  If an array of  specified then the full schema should be
     * used (all property names). The property names can be determined with a
     * getSchema call from the DataSource interface.
     * <p>
     * This replaces our funky setSchema method of retrieving select
     * properties.  I think it makes it easier to understand how to get
     * certain properties out of the datasource, instead of having users get
     * the  schema and then compose a new schema using the attributes that
     * they want.  The old way was also giving me problems because I couldn't
     * have multiple object reuse the same datasource object, since some other
     * object could come along and change its schema, and would then return
     * the wrong properties.
     *
     * @return the property names to be used in the returned FeatureCollection.
     */
    public List getPropertyNames() {
        return properties;
    }

    /**
     * Sets the properties to retrieve from the db.  If the boolean to load all
     * properties is set to true then the AttributeTypes that are not in the
     * database's schema will just be filled with null values.
     *
     * @param propNames The names of attributes to load from the datasouce.
     */
    public void setPropertyNames(String[] propNames) {
        this.properties = Arrays.asList(propNames);
    }

    public void setPropertyNames(List propNames) {
        this.properties = propNames;
    }

    /**
     * The optional maxFeatures can be used to limit the number of features
     * that a query request retrieves.  If no maxFeatures is specified then
     * all features should be returned.
     * <p>
     * This is the only method that is not directly out of the Query element in
     * the WFS spec.  It is instead a part of a <code>getFeatures</code>
     * request, which can hold one or more queries.  But each of those in turn
     * will need a maxFeatures, so it is needed here.
     *
     * @return the max features the getFeature call should return.
     */
    public int getMaxFeatures() {
        return this.maxFeatures;
    }

    /**
     * Sets the max features to retrieved by this query.
     *
     * @param maxFeatures the maximum number of features the getFeature call
     *        should return.
     */
    public void setMaxFeatures(int maxFeatures) {
        this.maxFeatures = maxFeatures;
    }

    /**
     * The Filter can be used to define constraints on a query.  If no Filter
     * is present then the query is unconstrained and all feature instances
     * should be retrieved.
     *
     * @return The filter that defines constraints on the query.
     */
    public Filter getFilter() {
        return this.filter;
    }

    /**
     * Sets the filter to constrain the query.
     *
     * @param filter the OGC filter to limit the datasource getFeatures
     *        request.
     */
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    /**
     * The Name attribute is used to indicate the name of the feature type
     * to be queried.
     * <p>
     * The DataStore API does not assume one feature type per datastore.
     * It currently makes use of this field to to specify with each request
     * what type to get.
     * <p>
     * Notes:
     * <ul>
     * <li>Origional example from SYS-TECHNOLOGIES used QName here, Name is
     *     method compatable with QName and is used in a consistent manner with
     *     the intention here (ie to look up the correct dataset).
     * <li>The GeoAPI 2.0.x interface used GenericName - unfortantly GenericName
     *     cannot be used to indicate which type is to be used (it already *is* the
     *     type being discovered). GenericName subclass FeatureType is what we would
     *     expect to discover - "name" indicating that FeatureType has a name.
     * </ul>
     * @return the name of the feature type to be returned with this query.
     */
    public TypeName getTypeName() {
        return (TypeName) Name;
    }

    /**
     * Sets the Name.
     *
     * @param Name the name of the featureType to retrieve.
     */
    public void setName(Name Name) {
        this.Name = Name;
    }

    /**
     * The handle attribute is included to allow a client to associate  a
     * mnemonic name to the Query request. The purpose of the handle attribute
     * is to provide an error handling mechanism for locating  a statement
     * that might fail.
     *
     * @return the mnemonic name of the query request.
     */
    public String getHandle() {
        return this.handle;
    }

    /**
     * Sets a mnemonic name for the query request.
     *
     * @param handle the name to refer to this query.
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

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
     * <p>
     * This is ready for use, it will be up to data store implementors to
     * support it.
     * </p>
     *
     * @return the version of the feature to return, or null for latest.
     */
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * getCoordinateSystem purpose.
     * <p>
     * Description ...
     * </p>
     */
    public CoordinateReferenceSystem getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * getCoordinateSystemReproject purpose.
     * <p>
     * Description ...
     * </p>
     */
    public CoordinateReferenceSystem getCoordinateSystemReproject() {
        return coordinateSystemReproject;
    }

    /**
     * setCoordinateSystem purpose.
     * <p>
     * Description ...
     * </p>
     * @param system
     */
    public void setCoordinateSystem(CoordinateReferenceSystem system) {
        coordinateSystem = system;
    }

    /**
     * setCoordinateSystemReproject purpose.
     * <p>
     * Description ...
     * </p>
     * @param system
     */
    public void setCoordinateSystemReproject(CoordinateReferenceSystem system) {
        coordinateSystemReproject = system;
    }

}
