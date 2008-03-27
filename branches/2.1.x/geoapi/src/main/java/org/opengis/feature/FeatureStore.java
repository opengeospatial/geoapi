/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
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
import org.opengis.feature.type.FeatureType;
import org.opengis.filter.Filter;
import org.opengis.sld.FeatureStyle;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;


/**
 * Gives a normalized interface to a data provider that can serve up collections
 * of {@link Feature} objects.
 *
 * @since GeoAPI 2.0
 * 
 * @deprecated Removed pending a redesign of feature storage interfaces
 */
@Deprecated
public interface FeatureStore {
    /**
     * Icon representing this {@code FeatureStore}.
     *
     * @return URI to a icon (GIF or PNG) representing this feature store.
     */
    URI getIcon();

    /**
     * Display name for this feature store.
     */
    InternationalString getDisplayName();

    /**
     * Description of this feature store.
     */
    InternationalString getDescription();

    /**
     * Access to metadata about this feature store.
     * <p>
     * Note this is an example of the bridge pattern, client code should not
     * cache this metadata object to ensure that they are never out of date.
     * <p>
     * It is too much overhead to indicate metadata changing with an extra set of
     * events. {@link FeatureStoreEvent}s will be used to indicate new content is
     * available.
     */
    // Metadata getMetadata();
    /**
     * Gets a list of all the names of the types held in this feature store.
     * The typical usage of these generic names will be as follows:  In most
     * cases, the {@linkplain GenericName generic name} will have a
     * {@linkplain GenericName#asLocalName local part} that is be the name of the XML
     * element used to encode such features as GML.  The {@linkplain GenericName#getScope scope}
     * of the name will either be null (if the XML element is to have no namespace) or will
     * be a name whose {@link GenericName#toString() toString()} gives the URI of an XML namespace.
     */
    List<GenericName> getTypeNames();

    /**
     * This method is similar to the {@link #getTypeNames()} method,
     * except that it acknowledges that some features only make sense as
     * children of another feature collection type. So this method returns the
     * types that are the root level "parent" types that can be retrieved from
     * this store. Implementors are free to return the same values from this
     * method as from {@code getTypeNames()}.
     */
    List<GenericName> getRootTypeNames();

    /**
     * Returns the schema of the named feature type.  May return null if a type
     * of the given name does not exist.  The {@link GenericName} passed to this
     * method must be equal to one of the elements in the list returned by the
     * {@link #getTypeNames()} method.
     */
    FeatureType getFeatureType(GenericName typeName);

    /**
     * Gets all features of the given type.
     *
     * @throws IOException if an error occurs while reading the backing store. If the cause is
     *         not a standard I/O exception, it may be wrapped in a {@link FeatureStoreException}.
     */
    FeatureCollection getFeatures(GenericName type) throws IOException;

    /**
     * Gets all features of the given type that pass some filter.
     *
     * @throws IOException if an error occurs while reading the backing store. If the cause is
     *         not a standard I/O exception, it may be wrapped in a {@link FeatureStoreException}.
     */
    FeatureCollection getFeatures(GenericName type, Filter filter) throws IOException;

    /**
     * Gets features of the given type that pass some query.  Different from
     * the method that accepts a type and a filter, this method allows for
     * retrieving only a subset of the attributes since a query can include a
     * property list.  Note that the type is indicated in the query.
     *
     * @throws IOException if an error occurs while reading the backing store. If the cause is
     *         not a standard I/O exception, it may be wrapped in a {@link FeatureStoreException}.
     */
    FeatureCollection getFeatures(Query query) throws IOException;

    /**
     * Shortcut for calling <code>{@linkplain #getFeatures getFeatures}(q)</code> then
     * calling <code>{@linkplain FeatureCollection#setTransaction setTransaction}(t)</code>
     * on the result.
     *
     * @throws IOException if an error occurs while reading the backing store. If the cause is
     *         not a standard I/O exception, it may be wrapped in a {@link FeatureStoreException}.
     */
    FeatureCollection getFeatures(Query q, Transaction t) throws IOException;

    /**
     * Registers the given feature collection so that it may hear any adds, removes,
     * or updates of {@link Feature}s of the given {@code type}.
     *
     * @param featureCollection the feature collection to register.
     * @param type the {@link FeatureType} to listen for.
     */
    void registerFeatureCollection(FeatureCollection featureCollection, GenericName type);

    /**
     * Registers the given feature collection so that it may hear
     * any adds, removes, or updates of {@link Feature}s of the given
     * {@code type} that meet the given {@code filter}.
     *
     * @param featureCollection the feature collection to register.
     * @param type the {@link FeatureType} to register with.
     * @param filter the filter to register with.
     */
    void registerFeatureCollection(FeatureCollection featureCollection, GenericName type, Filter filter);

    /**
     * Registers the given feature collection so that it may hear
     * any adds, removes, or updates of {@link Feature}s of the given
     * {@code type} that meet the given {@code filter}.
     *
     * @param featureCollection the feature collection to register.
     * @param query the query to register with.
     */
    void registerFeatureCollection(FeatureCollection featureCollection, Query query);

    /**
     * Unregisters the given feature collection so that it will no
     * longer hear any adds, removes, or updates of {@link Feature}s for
     * which it had previously registered.
     *
     * @param featureCollection the feature collection to unregister.
     */
    void unregisterFeatureCollection(FeatureCollection featureCollection);

    /**
     * If the feature store wants to provide a default style for the given type,
     * it may return something here. Otherwise, it should return null.
     */
    FeatureStyle getDefaultStyle(GenericName type);

    /**
     * Creates a new type.  If this feature store is backed by a persistent store of
     * some kind, then structures corresponding to the given type should be
     * created in this persistent store.
     *
     * @throws UnsupportedOperationException if the addition feature types is not supported.
     * @throws IllegalArgumentException If a type of the given name already exists.
     * @throws IllegalArgumentException If the given feature schema is somehow
     *         incompatible with the persisten store that backs this feature store.
     * @throws IOException if an error occurs while accessing the backing store. If the cause is
     *         not a standard I/O exception, it may be wrapped in a {@link FeatureStoreException}.
     */
    void createType(FeatureType type) throws UnsupportedOperationException, IllegalArgumentException, IOException;

    /**
     * Completely removes all the {@linkplain Feature features} of the given type and removes
     * the type itself.  If this feature store is backed by a persistent store of some
     * kind, then the corresponding structures in that store should be removed.
     *
     * @throws UnsupportedOperationException if the removal of whole feature types is not supported.
     * @throws IOException if an error occurs while accessing the backing store. If the cause is
     *         not a standard I/O exception, it may be wrapped in a {@link FeatureStoreException}.
     */
    void removeType(GenericName type) throws UnsupportedOperationException, IOException;

    /**
     * Modifies the type by changing the schema to what is passed as a parameter.
     *
     * @throws UnsupportedOperationException if the modification of types is not supported.
     * @throws IOException if an error occurs while accessing the backing store. If the cause is
     *         not a standard I/O exception, it may be wrapped in a {@link FeatureStoreException}.
     */
    void modifyType(FeatureType type) throws UnsupportedOperationException, IOException;

    /**
     * Adds a listener object whose methods will be invoked whenever a new
     * feature type is added, a feature type is deleted, or a feature type's
     * schema has been modified.
     */
    void addFeatureStoreListener(FeatureStoreListener listener);

    /**
     * Removes a listener that was previously added using the
     * {@link #addFeatureStoreListener addFeatureStoreListener} method.
     */
    void removeFeatureStoreListener(FeatureStoreListener listener);
}
