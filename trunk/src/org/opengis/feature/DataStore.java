package org.opengis.feature;

import java.io.IOException;
import java.util.List;

import org.opengis.filter.Filter;
import org.opengis.sld.FeatureStyle;

/**
 * Gives a normalized interface to a data provider that can serve up collections
 * of Feature objects.
 */
public interface DataStore {
    /**
     * Gets a list of all the names of the types held in this DataStore.
     */
    public List/*<QName>*/ getTypeNames();

    /**
     * Gets all features of the given type.
     */
    public FeatureCollection getFeatures(QName type) throws IOException;

    /**
     * Gets all features of the given type that pass some Filter.
     */
    public FeatureCollection getFeatures(QName type, Filter f) throws IOException;

    /**
     * Gets features of the given type that pass some query.  Different from
     * the method that accepts a type and a filter, this method allows for
     * retrieving only a subset of the attributes since a query can include a
     * property list.  Note that the type is indicated in the query.
     */
    public FeatureCollection getFeatures(Query q) throws IOException;

    /**
     * Shortcut for calling getFeatures(q) then calling setTransaction(t) on
     * the result.
     */
    public FeatureCollection getFeatures(Query q, Transaction t) throws IOException;

    /**
     * If the DataStore wants to provide a default style for the given type, it
     * may return something here.  Otherwise, it should return null.
     */
    public FeatureStyle getDefaultStyle(QName type);

    /**
     * Creates a new type.  If this DataStore is backed by a persistent store of
     * some kind, then structures corresponding to the given type should be
     * created in this persistent store.
     * @throws IllegalArgumentException If a type of the given name already
     *   exists.
     * @throws IllegalArgumentException If the given feature schema is somehow
     *   incompatible with the persisten store that backs this DataStore.
     */
    public void createType(FeatureType type) throws IOException, UnsupportedOperationException;

    /**
     * Completely removes all the features of the given type and removes the
     * type itself.  If this DataStore is backed by a persistent store of some
     * kind, then the corresponding structures in that store should be removed.
     * This may throw UnsupportedOperationException if the removal of whole
     * feature types is not supported.
     */
    public void removeType(QName type) throws IOException, UnsupportedOperationException;

    /**
     * Modifies the type by changing the schema to what is passed as a
     * parameter.
     * This may throw UnsupportedOperationException if the modification of types
     * is not supported.
     */
    public void modifyType(FeatureType type) throws IOException, UnsupportedOperationException;

    /**
     * Adds a listener object whose methods will be invoked whenever a new
     * feature type is added, a feature type is deleted, or a feature type's
     * schema has been modified.
     */
    public void addDataStoreListener(DataStoreListener dsl);

    /**
     * Removes a listener that was previously added using the
     * addDataStoreListener method.
     */
    public void removeDataStoreListener(DataStoreListener dsl);
}
