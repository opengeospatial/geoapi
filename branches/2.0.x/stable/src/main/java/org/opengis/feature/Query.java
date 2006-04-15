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

// Annotations
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;


/**
 * Used by the {@link FeatureStore#getFeatures(Query) getFeatures} method
 * of the {@linkplain FeatureStore data store} interface, to encapsulate a request.
 * A query defines which {@linkplain FeatureType feature type} to query, what properties
 * to retrieve and what constraints (spatial and non-spatial) to apply to those properties.
 * It is designed to closesly match a WFS Query element of a {@code getFeatures} request.
 * A difference is the addition of the {@link #getMaxFeatures maxFeatures} element, which
 * allows more control over the {@linkplain Feature features} selected.  It allows a full
 * {@code getFeatures} request to properly control how many features it gets from each query,
 * instead of requesting and discarding when the max is reached.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=8339">Implementation specification 1.1</A>
 * @since GeoAPI 2.0
 *
 * @todo The following XML elements are not yet defined in this interface:
 *       {@code Function}, {@code SortBy}.
 */
@XmlElement("Query")
public interface Query {
    /**
     * Returns the name of the type that is to be queried.
     *
     * @todo OGC specification said that a list should be returned.
     *       We may add a {@code getTypeNames()} (plural form) in a
     *       future version.
     */
    @XmlElement("typeName")
    GenericName getTypeName();

    /**
     * Returns the name of the type that is to be queried.
     * The type name is a list of one or more feature type names that indicate
     * which types of feature instances should be included in the reponse set.
     * Specifying more than one type name indicates that a join operation is
     * being performed. All the names in the type name list must be valid types
     * that belong to this query's feature content.
     */
//    @XmlElement("typeName")
//    Collection<GenericName> getTypeNames();

    /**
     * Returns the filter that will limit which {@linkplain Feature features} are returned.
     * The filter element is used to define spatial and/or non-spatial constraints on query.
     * It may be {@code null} if there is none.
     */
    @XmlElement("Filter")
    Filter getFilter();

    /**
     * Returns the list of property names of the {@linkplain Feature features}
     * to be retrieved. This may be null if all properties are to be retrieved.
     * <p>
     * This is used to specify one or more properties of a {@linkplain Feature feature}
     * whose values are to be retrieved by a Web Feature Service.  While a Web Feature
     * Service should endeavour to satisfy the exact request specified, in some instance
     * this may not be possible.  Specifically, a Web Feature Service must generate a
     * valid GML response to a Query operation. The schema used to generate the output
     * may include properties that are mandatory.  In order that the output validates,
     * these mandatory properties must be specified in the request.  If they are not, a
     * Web Feature Service may add them automatically to the Query before processing it.
     * Thus a client application should, in general, be prepared to receive more properties
     * than it requested. Of course, using {@link FeatureAttributeDescriptor}, a client
     * application can determine which properties are mandatory and request them in the
     * first place.
     */
    @XmlElement("Property")
    List<String> getPropertyNames();

    /**
     * Gives a mnemonic name for use by the client to identify this query.
     * The handle allows a client application to assign a client-generated
     * identifier for the query. The handle is included to facilitate error
     * reporting. If one query in a {@code GetFeature} request causes an exception,
     * a WFS may report the handle to indicate which query element failed.  If the
     * handle is not present, the WFS may use other means to localize the error
     * (e.g. line numbers).
     */
    @XmlElement("handle")
    String getHandle();

    /**
     * Returns the maximum number of features to be retrieved by the query.
     * Can return zero.  Can also return {@link Integer#MAX_VALUE} to indicate
     * that the maximum number of features is unlimited.
     *
     * @todo {@code maxFeatures} is an attribute of {@code getFeature} request.
     *       We may wish to avoid duplication once WMS interfaces are provided in
     *       GeoAPI.
     */
    @Extension
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
    @XmlElement("featureVersion")
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
     * @todo JG: Please if you possibly can treat the idea of "forcing" the CRS as a
     *       {@link FeatureCollection} configuration issue (or a {@link FeatureStore}
     *       configuration issue). Recent experience with Geotools shows the added burden
     *       on client code to be a pain.
     *
     * @todo MD: This method is misnamed. {@link org.opengis.referencing.cs.CoordinateSystem}
     *       and {@link org.opengis.referencing.crs.CoordinateReferenceSystem} are not the
     *       same thing.
     */
    @Extension
    CoordinateReferenceSystem getCoordinateSystem();

    /**
     * Request data reprojection. Gets the coordinate reference system to reproject the
     * data contained in the backend {@linkplain FeatureStore feature store} to.
     * If the feature store can optimize the reprojection it should. If not then
     * a decorator on the reader should perform the reprojection on the fly.
     *
     * @return The coordinate reference system that {@linkplain Feature features} from the
     *         {@linkplain FeatureStore feature store} should be reprojected to.
     *
     * @todo MD: This method is misnamed. {@link org.opengis.referencing.cs.CoordinateSystem}
     *       and {@link org.opengis.referencing.crs.CoordinateReferenceSystem} are not the
     *       same thing.
     */
    @Extension
    CoordinateReferenceSystem getCoordinateSystemReproject();

    /**
     * Specifies a specific WFS-supported CRS that should be used for returned feature
     * geometries.  The value may be the WFS {@code "StorageSRS"} value,
     * {@code "DefaultRetrievalSRS"} value, or one of {@code "AdditionalSRS"} values.
     * If no CRS name value is supplied, then the features will be returned using either
     * the {@code "DefaultRetrievalSRS"}, if specified, and {@code "StorageSRS"} otherwise.
     * For feature types with no spatial properties, this attribute must not be specified
     * or ignored if it is specified.
     */
//    @XmlElement("srsName")
//    String getCrsName();
}
