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
import java.util.Set;

import org.opengis.feature.FeatureAttributeDescriptor;
import org.opengis.filter.Filter;


/**
 * Provides storage of data for Features.
 * 
 * <p>
 * Individual shapefiles, database tables, etc. are modified through this
 * interface.
 * </p>
 * 
 * <p>
 * This is a prototype DataSource replacement please see FeatureSource for more
 * information.
 * </p>
 *
 * @author Jody Garnett
 * @author Ray Gallagher
 * @author Rob Hranac, TOPP
 * @author Chris Holmes, TOPP
 * @version $Id$
 */
public interface FeatureStore extends FeatureSource {
    /**
     * Adds all features from the passed feature collection to the datasource.
     *
     * @param reader The reader from which to add the features.
     *
     * @return the FeatureIds of the newly added features.
     *
     * @throws DataSourceException if anything goes wrong.
     * @throws UnsupportedOperationException if the addFeatures method is not
     *         supported by this datasource.
     */
    Set addFeatures(FeatureReader reader) throws IOException;

    /**
     * Removes all of the features specificed by the passed filter from the
     * collection.
     *
     * @param filter An OpenGIS filter; specifies which features to remove.
     *
     * @throws DataSourceException If anything goes wrong.
     * @throws UnsupportedOperationException if the removeFeatures method is
     *         not supported by this datasource.
     */
    void removeFeatures(Filter filter) throws IOException;

    /**
     * Modifies the passed attribute types with the passed objects in all
     * features that correspond to the passed OGS filter.
     *
     * @param attrs The attributes to modify.
     * @param values The values to put in the attributes.
     * @param filter An OGC filter to note which attributes to modify.
     *
     * @throws DataSourceException if the attribute and object arrays are not
     *         eqaul length, if the object types do not match the attribute
     *         types, or if there are backend errors.
     * @throws UnsupportedOperationException if the modifyFeatures method is
     *         not supported by this datasource.
     */
    void modifyFeatures(FeatureAttributeDescriptor [] attrs, Object [] values,
            Filter filter) throws IOException;

    /**
     * Modifies the passed attribute types with the passed objects in all
     * features that correspond to the passed OGS filter.  A convenience
     * method for single attribute modifications.
     *
     * @param attr The attribute to modify.
     * @param value The value to put in the attribute.
     * @param filter An OGC filter to note which attributes to modify.
     *
     * @throws DataSourceException If modificaton is not supported, if the
     *         object type do not match the attribute type.
     * @throws UnsupportedOperationException if the modifyFeatures method is
     *         not supported by this datasource.
     */
    void modifyFeatures(FeatureAttributeDescriptor attr, Object value,
            Filter filter) throws IOException;

    /**
     * Deletes the all the current Features of this datasource and adds the new
     * collection.  Primarily used as a convenience method for file
     * datasources.
     *
     * @param reader - the collection to be written
     *
     * @throws DataSourceException if there are any datasource errors.
     * @throws UnsupportedOperationException if the setFeatures method is not
     *         supported by this datasource.
     */
    void setFeatures(FeatureReader reader) throws IOException;

    /**
     * Provides a transaction for commit/rollback control of this FeatureStore.
     * 
     * <p>
     * This method operates as a replacement for setAutoCommitMode.  When a
     * transaction is provided you are no longer automatically committing.
     * </p>
     * 
     * <p>
     * In order to return to AutoCommit mode supply the Transaction.AUTO_COMMIT
     * to this method. Since this represents a return to AutoCommit mode the
     * previous Transaction will be commited.
     * </p>
     *
     * @throws DataSourceException if there are any datasource errors.
     */
    void setTransaction(Transaction transaction);

    /**
     * Used to access the Transaction this DataSource is currently opperating
     * against.
     * 
     * <p>
     * Example Use: adding features to a road DataSource
     * </p>
     * <pre><code>
     * Transaction t = roads.getTransaction();
     * try{
     *     roads.addFeatures( features );
     *     roads.getTransaction().commit();
     * }
     * catch( IOException erp ){
     *     //something went wrong;
     *     roads.getTransaction().rollback();
     * }
     * </code></pre>
     *
     * @return Transaction in use, or <code>Transaction.AUTO_COMMIT</code>
     */
    Transaction getTransaction();
}
