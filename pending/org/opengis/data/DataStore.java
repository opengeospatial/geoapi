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
import java.net.URI;

import org.opengis.feature.FeatureType;

/**
 * Represents a Physical Store for FeatureTypes.
 * <p>
 * The source of data for FeatureTypes. Shapefiles, databases tables, etc. are
 * referenced through this interface.
 * <p>
 * Summary of our requirements:
 * <ul>
 *   <li>Provides lookup of available Feature Types</li>
 *   <li>Provides access to low-level Readers/Writers API for a feature
 *       type</li>
 *   <li>Provides access to high-level FeatureSource/Store/Locking API a feature
 *       type</li>
 *   <li>Handles the conversion of filters into data source specific
 *       queries</li>
 *   <li>Handles creation of new Feature Types</li>
 *   <li>Provides access of Feature Type Schema information</li>
 * </ul>
 * <p>
 * Suggestions:
 * <ul>
 * <li>Andrea - Support for a reprojection based FeatureSource. Needs to be
 * added to DataStore (rather than a wrapper based) to allow for DataStores
 * </li>
 * <li>
 * Jody - I agree, propose:
 * <pre><code>
 * getFeatureReader( Query, Transaction )
 * - Transaction is still orthognal
 * 
 * getFeatureSource( Query )
 * - Query allows override, and reprojection...
 * 
 * getFeatureStore( typeName )
 * - write access is till by typeName
 *   (just like getFeatureWriter)
 * - consider spliting API into to based on read-only, read/write ability? 
 * </code></pre>
 * </li>
 * </ul>
 * 
 *
 * @author Jody Garnett, Refractions Research
 */
public interface DataStore {
    /**
     * Creates storage for a new <code>featureType</code>.
     * 
     * <p>
     * The provided <code>featureType</code> we be accessable by the typeName
     * provided by featureType.getTypeName().
     * </p>
     *
     * @param featureType FetureType to add to DataStore
     *
     * @throws IOException If featureType cannot be created
     */
    void createSchema(FeatureType featureType) throws IOException;

    /**
     * Modifies an existing FeatureType.
     * <p>
     * The provided featureType should completely cover the existing schema.
     * All attributes should be accounted for and the typeName should match.
     * </p>
     * <p>
     * Suggestions:
     * </p>
     * <ul>
     * <li>Sean - don't do this</li>
     * <li>Jody - Just allow changes to metadata: CS, namespace, and others</li> 
     * <li>James - Allow change/addition of attribtues</li> 
     * </ul>
     * @param typeName
     * @throws IOException
     */
    void updateSchema( String typeName, FeatureType featureType ) throws IOException;

    /**
     * Retrieves a list of of the available FeatureTypes.
     * 
     * <p>
     * This is simply a list of the FeatureType names as aquiring the actual
     * FeatureType schemas may be expensive.
     * </p>
     *
     * <p>
     * Warning: this list may not be unique - the types may be
     * in separate namespaces.
     * </p>
     * 
     * <p>
     * If you need to worry about such things please consider the use of
     * the Catalog and CatalogEntry interface - many DataStores support this.
     * getTypeNames is really a convience method for a Catalog.iterator() where
     * the name of each entry is returned.
     * </p>
     * 
     * @return typeNames for available FeatureTypes.
     */
    String[] getTypeNames() throws IOException;    

    String[] getTypeNames( URI namespace ) throws IOException;

    URI [] getNamespaces() throws IOException;

    /**
     * Retrieve FeatureType metadata by <code>typeName</code>.
     * 
     * <p>
     * Retrieves the Schema information as a FeatureType object.
     * </p>
     *
     * @param typeName typeName of requested FeatureType
     *
     * @return FeatureType for the provided typeName
     *
     * @throws IOException If typeName cannot be found
     */
    FeatureType getSchema(String typeName) throws IOException;

    /**
     * Access a FeatureSource for Query providing a high-level API.
     * <p>
     * The provided Query does not need to completely cover the existing
     * schema for Query.getTypeName(). The result will mostly likely only be
     * a FeatureSource and probably wont' allow write access by the
     * FeatureStore method.
     * </p>
     * <p>
     * By using Query we allow support for reprojection, in addition
     * to overriding the CoordinateSystem used by the native FeatureType.
     * </p>
     * <p>
     * We may wish to limit this method to only support Queries using
     * Filter.ALL.
     * </p>
     * <p>
     * Update - GeoServer has an elegatent implementation of this functionality
     * that we could steal. GeoServerFeatureSource, GeoServerFeatureStore and
     * GeoServerFeatureLocking serve as a working prototype.
     * </p> 
     * @param query Query.getTypeName() locates FeatureType being viewed
     *
     * @return FeatureSource providing opperations for featureType
     * @throws IOException If FeatureSource is not available
     * @throws SchemaException If fetureType is not covered by existing schema
     */
    FeatureSource getView( Query query ) throws IOException, SchemaException;

    /**
     * Access a FeatureSource for typeName providing a high-level API.
     * 
     * <p>
     * The resulting FeatureSource may implment more functionality:
     * </p>
     * <pre><code>
     * 
     * FeatureSource fsource = dataStore.getFeatureSource( "roads" );
     * FeatureStore fstore = null;
     * if( fsource instanceof FeatureLocking ){
     *     fstore = (FeatureStore) fs;
     * }
     * else {
     *     System.out.println("We do not have write access to roads");
     * }
     * </code>
     * </pre>
     *
     * @param typeName
     *
     * @return FeatureSource (or subclass) providing opperations for typeName
     */
    FeatureSource getFeatureSource(String typeName) throws IOException;

    /**
     * Retrieve a per featureID based locking service from this DataStore.
     * 
     * <p>
     * It is common to return an instanceof InProcessLockingManager for
     * DataStores that do not provide native locking.
     * </p>
     * 
     * <p>
     * AbstractFeatureLocking makes use of this service to provide locking
     * support. You are not limitied by this implementation and may simply
     * return <code>null</code> for this value.
     * </p>
     *
     * @return DataStores may return <code>null</code>, if the handling locking
     *         in another fashion.
     */
    LockingManager getLockingManager();
}
