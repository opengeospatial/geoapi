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
import java.io.IOException;
import java.net.URI;

// OpenGIS direct dependencies
import org.opengis.filter.Filter;
import org.opengis.sld.FeatureStyle;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;


/**
 * Gives a normalized interface to a data provider that can serve up collections
 * of {@link Feature} objects.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface FeatureStore {
    /**
     * Icon representing this FeatureStore.
     *
     * @return URI to a icon (GIF or PNG) representing this FeatureStore.
     * @revisit Assumed to point to a 16x16 icon?
     * @revisit Should the return type be URI?
     */
    URI getIcon();

    /**
     * Display name for this {@code FeatureStore}.
     */
    InternationalString getDisplayName();

    /**
     * Description of this {@code FeatureStore}.
     */
    InternationalString getDescription();

    /**
     * Access to metadata about this FeatureStore.
     * <p>
     * Note this is an example of the bridge pattern, client code should not
     * cache this metadata object to ensure that they are never out of date.
     * <p>
     * It is too much overhead to indicate metadata changing with an extra set of
     * events, FeatureStoreEvents will be used to indicate new content is available. 
     */
    // Metadata getMetadata();

    /**
     * Gets a list of all the names of the types held in this data store.
     * Objects in the returned list will be instances of GenericName.
     * <p>
     * The typical usage of these GenericNames will be as follows:  In most
     * cases, the GenericName will have a local part that is be the name of the
     * XML element used to encode such features as GML.  The scope of the name
     * will either be null (if the XML element is to have no namespace) or will
     * be a LocalName whose toString() gives the URI of an XML namespace.
     */
    List<GenericName> getTypeNames();

    /**
     * This method is similar to the <code>getTypeNames()</code> method,
     * except that it acknowledges that some features only make sense as
     * children of another feature collection type. So this method returns the
     * types that are the root level "parent" types that can be retrieved from
     * this store. Implementors are free to return the same values from this
     * method as from <code>getTypeNames()</code>.
     */
    List<GenericName> getRootTypeNames();

    /**
     * Returns the schema of the named feature type.  May return null if a type
     * of the given name does not exist.  The GenericName passed to this method
     * must be equal to one of the elements in the list returned by the
     * <code>getTypeName()</code> method.
     */
    FeatureType getFeatureType(GenericName typeName);

    /**
     * Gets all features of the given type.
     */
    FeatureCollection getFeatures(GenericName type) throws IOException;

    /**
     * Gets all features of the given type that pass some filter.
     */
    FeatureCollection getFeatures(GenericName type, Filter filter) throws IOException;

    /**
     * Gets features of the given type that pass some query.  Different from
     * the method that accepts a type and a filter, this method allows for
     * retrieving only a subset of the attributes since a query can include a
     * property list.  Note that the type is indicated in the query.
     */
    FeatureCollection getFeatures(Query query) throws IOException;

    /**
     * Shortcut for calling getFeatures(q) then calling setTransaction(t) on the
     * result.
     */
    FeatureCollection getFeatures(Query q, Transaction t) throws IOException;

    /**
     * Registers the given <code>featureCollection</code> so that it may hear
     * any adds, removes, or updates of <code>Feature</code> s of the given
     * <code>type</code>.
     * 
     * @param featureCollection the <code>FeatureCollection</code> to register
     * @param type the <code>FeatureType</code> to listen for
     */
    void registerFeatureCollection(FeatureCollection featureCollection, GenericName type);

    /**
     * Registers the given <code>featureCollection</code> so that it may hear
     * any adds, removes, or updates of <code>Feature</code> s of the given
     * <code>type</code> that meet the given <code>filter</code>.
     * 
     * @param featureCollection the <code>FeatureCollection</code> to register
     * @param type the <code>FeatureType</code> to register with
     * @param filter the <code>Filter</code> to register with
     */
    void registerFeatureCollection(
            FeatureCollection featureCollection,
            GenericName type,
            Filter filter);

    /**
     * Registers the given <code>featureCollection</code> so that it may hear
     * any adds, removes, or updates of <code>Feature</code> s of the given
     * <code>type</code> that meet the given <code>filter</code>.
     * 
     * @param featureCollection the <code>FeatureCollection</code> to register
     * @param query the <code>Query</code> to register with
     */
    void registerFeatureCollection(FeatureCollection featureCollection, Query query);

    /**
     * Unregisters the given <code>featureCollection</code> so that it will no
     * longer hear any adds, removes, or updates of <code>Feature</code> s for
     * which it had previously registered.
     * 
     * @param featureCollection the <code>FeatureCollection</code> to unregister.
     */
    void unregisterFeatureCollection(FeatureCollection featureCollection);

    /**
     * If the FeatureStore wants to provide a default style for the given type,
     * it may return something here. Otherwise, it should return null.
     */
    FeatureStyle getDefaultStyle(GenericName type);

    /**
     * Creates a new type.  If this FeatureStore is backed by a persistent store of
     * some kind, then structures corresponding to the given type should be
     * created in this persistent store.
     * 
     * @throws IllegalArgumentException If a type of the given name already exists.
     * @throws IllegalArgumentException If the given feature schema is somehow
     *         incompatible with the persisten store that backs this FeatureStore.
     */
    void createType(FeatureType type) throws IOException, UnsupportedOperationException;

    /**
     * Completely removes all the features of the given type and removes the
     * type itself.  If this FeatureStore is backed by a persistent store of some
     * kind, then the corresponding structures in that store should be removed.
     * This may throw UnsupportedOperationException if the removal of whole
     * feature types is not supported.
     */
    void removeType(GenericName type) throws IOException, UnsupportedOperationException;

    /**
     * Modifies the type by changing the schema to what is passed as a
     * parameter. This may throw UnsupportedOperationException if the
     * modification of types is not supported.
     */
    void modifyType(FeatureType type) throws IOException, UnsupportedOperationException;

    /**
     * Adds a listener object whose methods will be invoked whenever a new
     * feature type is added, a feature type is deleted, or a feature type's
     * schema has been modified.
     */
    void addFeatureStoreListener(FeatureStoreListener listener);

    /**
     * Removes a listener that was previously added using the
     * addFeatureStoreListener method.
     */
    void removeFeatureStoreListener(FeatureStoreListener listener);
}
